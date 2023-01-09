package com.barunsw.ojt.jmlee.day21;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.barunsw.ojt.constants.Severity;
import com.barunsw.ojt.vo.BoardVo;

public class ServerImpl extends UnicastRemoteObject implements ServerInterface {
	
	private static final Logger LOGGER = LogManager.getLogger(ServerImpl.class);
	private static final SqlSessionFactory sqlSessionFactory = SqlSessionFactoryManager.getSqlSessionFactory();
	
	private ClientInterface client;
	
	public ServerImpl() throws RemoteException {
		super();
		
	}
	
	
	private void initAlarmGenerator() {
		for (int i = 0; i < 100; i ++) {
			int boardId = (int) (Math.random() * 38);
			int severity = (int) (Math.random() * 4);
			
			BoardVo vo = new BoardVo();
			vo.setBoardId(boardId);
			vo.setSeverity(severity);
			try {
				client.pushAlarm(vo);
			}
			catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
			}
		}
	}

	@Override
	public int register(ClientInterface client) {
			this.client = client;
			initAlarmGenerator();
			LOGGER.debug(String.format("======= client : %s" ,client));
		return 0;
	}

	@Override
	public List<BoardVo> selectBoardList(BoardVo boardVo) throws RemoteException {
		
		List<BoardVo> boardList = new ArrayList<>();

		try (SqlSession session = sqlSessionFactory.openSession()) {
			ServerInterface mapper = session.getMapper(ServerInterface.class);

			boardList = mapper.selectBoardList(boardVo);

			for (BoardVo s : boardList) {
				LOGGER.debug(s.toString());
			}

		} catch (Exception ex) {
			LOGGER.debug(ex.getMessage(), ex);
		}
		return boardList;
	}
}
