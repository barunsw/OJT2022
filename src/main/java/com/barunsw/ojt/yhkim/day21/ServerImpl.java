package com.barunsw.ojt.yhkim.day21;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.barunsw.ojt.constants.Severity;
import com.barunsw.ojt.vo.BoardVo;

public class ServerImpl extends UnicastRemoteObject implements ServerInterface {	
	private static Logger LOGGER = LoggerFactory.getLogger(ServerImpl.class);
	private static SqlSessionFactory sqlSessionFactory = SqlSessionFactoryManager.getSqlSessionFactory();
	
	private ClientInterface client;
	
	public ServerImpl() throws RemoteException {
		super();
	}

	@Override
	public int register(ClientInterface clientInterface) {
		client = clientInterface;
		alarmGenerator();
		
		return 0;
	}

	@Override
	public List<BoardVo> selectBoardList() throws RemoteException {
		List<BoardVo> boardList = new ArrayList<>();
		
		try (SqlSession session = sqlSessionFactory.openSession()) {
			ServerInterface mapper = session.getMapper(ServerInterface.class);
			boardList = mapper.selectBoardList();
		}
		catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		
		return boardList;
	}
	
	public void alarmGenerator() {
		for (int i = 0; i < 100; i++) {
			int boardId = (int) (Math.random() * 39);
			int severity = (int) (Math.random() * 4);
			
			BoardVo vo = new BoardVo();
			vo.setBoardId(boardId);
			vo.setSeverity(severity);
			
			try {
				client.pushAlarm(vo);
			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
			}
		}		
	}
}
