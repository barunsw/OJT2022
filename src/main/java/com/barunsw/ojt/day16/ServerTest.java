package com.barunsw.ojt.day16;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ServerTest {
	private static final Logger LOGGER = LogManager.getLogger(ServerTest.class);
	
	public static final int PORT = 50000;
	
	public static void main(String[] args) throws Exception {
		LOGGER.debug("+++ initServer");
		
		ServerSocket serverSocket = new ServerSocket(PORT);
		
		while (true) {
			Socket clientSocket = serverSocket.accept();
			
			LOGGER.debug(String.format("clientSocket remote:%s", 
					clientSocket.getRemoteSocketAddress()));

			byte[] buf = new byte[1024];
			int readNum = 0;

			try (InputStream inputStream = clientSocket.getInputStream();
					OutputStream outputStream = clientSocket.getOutputStream()) {
				readNum = inputStream.read(buf);
				LOGGER.debug("readNum:" + readNum);

				String readMessage = new String(buf, 0, readNum);
				readMessage = readMessage.toUpperCase();

				outputStream.write(readMessage.getBytes());
				//outputStream.flush();
			}
		}

		//LOGGER.debug("--- initServer");
	}
}
