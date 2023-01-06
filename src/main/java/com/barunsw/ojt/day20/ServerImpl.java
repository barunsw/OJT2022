package com.barunsw.ojt.day20;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ServerImpl extends UnicastRemoteObject 
	implements ServerInterface {
	private static final Logger LOGGER = LogManager.getLogger(ServerImpl.class);
	
	private Map<String, ClientInterface> clientRepo = 
			new ConcurrentHashMap<>();
	
	public ServerImpl() throws RemoteException {
		super();
	}
	
	@Override
	public void register(String name, ClientInterface clientInterface) throws RemoteException {
		// TODO Auto-generated method stub
		LOGGER.debug("register");
		
		synchronized (clientRepo) {
			clientRepo.put(name, clientInterface);
		}
	}
	
	@Override
	public void deregister(String name) throws RemoteException {
		LOGGER.debug("deregister");
		// 지워준다.
	}

	@Override
	public void send(String name, String msg) throws RemoteException {
		LOGGER.debug(String.format("Server send name : [%s] msg : [%s]",name, msg));
		
		synchronized (clientRepo) {
			for (ClientInterface oneClient : clientRepo.values()) {
				oneClient.push(String.format("[%s]:%s", name, msg));
			}
		}
	}
}
