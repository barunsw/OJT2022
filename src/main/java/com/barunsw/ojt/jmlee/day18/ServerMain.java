package com.barunsw.ojt.jmlee.day18;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ServerMain {
	private static final Logger LOGGER = LogManager.getLogger(ServerMain.class);
	
	public static final int PORT = 50000;

	private boolean runFlag;
	
	public void start() {
		LOGGER.debug(String.format("+++ ServerMain started."));

		try {
			Registry registry = LocateRegistry.createRegistry(PORT);
			
			AddressBookInterface addressBookIf = 
					new AddressBookImpl();
			
			registry.rebind("ADDRESSBOOK", addressBookIf);
		} 
		catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		
		LOGGER.debug(String.format("--- ServerMain started."));
	}
	
	public static void main(String[] args) {
		new ServerMain().start();
	}
}
