package com.barunsw.ojt.jmlee.day21;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import com.barunsw.ojt.vo.BoardVo;

public class ClientImpl extends UnicastRemoteObject implements ClientInterface {

	private ClientPanel panel;
	
	public ClientImpl(ClientPanel panel) throws RemoteException {
		this.panel = panel;
	}
	
	@Override
	public void pushAlarm(BoardVo boardVo) throws RemoteException {
		ClientMain.eventQueueWorker.push(boardVo);
	}

}
