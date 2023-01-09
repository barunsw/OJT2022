package com.barunsw.ojt.yhkim.day21;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.barunsw.ojt.vo.BoardVo;

public class ClientImpl extends UnicastRemoteObject implements ClientInterface {
	private static final Logger LOGGER = LogManager.getLogger(QueueWorker.class);

	private ClientPanel panel;
	protected ClientImpl(ClientPanel panel) throws RemoteException {
		super();
		
		this.panel = panel;
	}

	@Override
	public void pushAlarm(BoardVo boardVo) throws Exception {
		LOGGER.debug(String.format("pushAlarm(%s)", boardVo.toString()));
		ClientMain.eventQueueWorker.push(boardVo);
	}

}

