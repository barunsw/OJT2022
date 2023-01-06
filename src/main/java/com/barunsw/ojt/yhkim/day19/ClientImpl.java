package com.barunsw.ojt.yhkim.day19;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.barunsw.ojt.vo.BoardVo;

public class ClientImpl extends UnicastRemoteObject implements ClientInterface {

	private ClientPanel panel;
	protected ClientImpl(ClientPanel panel) throws RemoteException {
		super();
		
		this.panel = panel;
	}

	@Override
	public void pushAlarm(BoardVo boardVo) throws Exception {
		panel.changeSeverity(boardVo);
	}

}

