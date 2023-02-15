package com.barunsw.ojt.jmlee.day15;

import java.net.ServerSocket;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServerMain {
	private static Logger LOGGER = LoggerFactory.getLogger(ServerMain.class);
	
	public static final int PORT = 5000;
	
	private boolean runFlag;
	
	public ServerMain() {	
	}
	
	public void run() {
		LOGGER.debug("+++ server start");
		
		try {
			ServerSocket serverSocket = new ServerSocket(PORT);
			//serverSocket.bind(new InetSocketAddress("0.0.0.0", PORT));
			
			runFlag = true;
			while (runFlag) {
				Socket clientSocket = serverSocket.accept();

				ClientSocketHandler clientSocketHandler = new ClientSocketHandler(clientSocket);
				clientSocketHandler.start();
			}
		}
		catch (Exception ex) {
			LOGGER.error(ex.getMessage());
		}
		
		LOGGER.debug("+++ server end");
	}
	
	public static void main(String[] args) {
		new ServerMain().run();
	}
}
