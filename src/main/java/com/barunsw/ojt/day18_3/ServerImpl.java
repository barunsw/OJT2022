package com.barunsw.ojt.day18_3;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import com.barunsw.ojt.vo.BoardVo;

public class ServerImpl extends UnicastRemoteObject implements ServerInterface {
	public ServerImpl() throws RemoteException {
		super();
		
		initData();
		initAlarmGenerator();
	}
	
	private void initData() {
		
	}
	
	private void initAlarmGenerator() {
		
	}

	@Override
	public int register(ClientInterface clientInterface) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<BoardVo> selectBoardList() {
		// TODO Auto-generated method stub
		return null;
	}
}
