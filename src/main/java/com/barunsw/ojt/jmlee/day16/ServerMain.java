package com.barunsw.ojt.jmlee.day16;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ServerMain {
	private static final Logger LOGGER = LogManager.getLogger(ServerMain.class);
	
	public static final int PORT = 5010;
	public static Properties serverProperties = new Properties();
	
	private boolean runFlag;
	
	public void start() throws Exception {
		LOGGER.debug(String.format("ServerMain started."));

		runFlag = true;
		
		try (ServerSocket serverSocket = new ServerSocket(PORT)) {
			while (runFlag) {
				Socket socket = serverSocket.accept();

				LOGGER.debug(String.format("client connected(%s)", socket.getRemoteSocketAddress()));

				ClientSocketHandler handler = new ClientSocketHandler(socket);
				handler.start();
			}
		} 
		catch (IOException e) {
			LOGGER.error(e.getMessage(), e);
		}
	}
	
	public static void main(String[] args) throws Exception {
		new ServerMain().start();
	}
}
