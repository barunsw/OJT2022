package com.barunsw.ojt.jmlee.day19;

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
		
		initData();
		initAlarmGenerator();
	}
	
	private void initData() {
		
	}
	
	private void initAlarmGenerator() {
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					int boardId = (int)(Math.random() * 38);
					int severity = (int)(Math.random() * 4);
					
					Severity sev = Severity.items[severity];
					BoardVo severityRandom = new BoardVo();
					severityRandom.setBoardId(boardId);
					severityRandom.setSeverity(severity);
					
					LOGGER.debug("boardId:" + boardId + ", severity:" + sev);
					
					try {
						client.pushAlarm(severityRandom);
						Thread.sleep(100);
					}
					catch (Exception ex) {
						
					}
				}
			}
		});
		t.start();
	}

	@Override
	public int register(ClientInterface client) {
			this.client = client;
			LOGGER.debug("크ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㄹㄹㄹㅇㄹㅇㄹㅇㄹㅇㅇ"+client);
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
