package com.barunsw.ojt.jmlee.day20;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ClientImpl extends UnicastRemoteObject
	implements ClientInterface {
	private static final Logger LOGGER = LogManager.getLogger(ClientImpl.class);
	
	public ClientImpl() throws RemoteException {
		super();
	}
	
	@Override
	public void push(String msg) throws RemoteException {
		LOGGER.debug(String.format("Message : %s", msg));
		
		ClientMain.eventQueueWorker.push(msg);
	}
}
