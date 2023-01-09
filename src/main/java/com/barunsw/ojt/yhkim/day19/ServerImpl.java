package com.barunsw.ojt.yhkim.day19;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.barunsw.ojt.constants.Severity;
import com.barunsw.ojt.vo.BoardVo;

public class ServerImpl extends UnicastRemoteObject implements ServerInterface {	
	private static Logger LOGGER = LoggerFactory.getLogger(ServerImpl.class);
	private static SqlSessionFactory sqlSessionFactory = SqlSessionFactoryManager.getSqlSessionFactory();
	
	private List<BoardVo> boardList;
	private List<ClientInterface> clientList = new ArrayList<>();

	public ServerImpl() throws RemoteException {
		super();
		
		initData();
		initAlarmGenerator();	
	}
	
	private void initData() {
		try {
			boardList = selectBoardList();
		} catch (RemoteException e) {
			LOGGER.debug(e.getMessage(),e);
		}
		
	}
	
	private void initAlarmGenerator() {
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					int boardId = (int) (Math.random() * 39);
					int severity = (int) (Math.random() * 4);
					Severity sev = Severity.items[severity];

					BoardVo vo = new BoardVo();
					vo.setBoardId(boardId);
					vo.setSeverity(severity);
					
					LOGGER.debug("boardId:" + boardId + ", severity:" + sev);
					
					try {
						for (ClientInterface client : clientList) {
							client.pushAlarm(vo);
						}
						Thread.sleep(1000);
					}
					catch (Exception e) {
						LOGGER.error(e.getMessage(), e);
					}
				}
			}
		});
		
		t.start();		
	}

	@Override
	public int register(ClientInterface clientInterface) {
		clientList.add(clientInterface);
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
}
