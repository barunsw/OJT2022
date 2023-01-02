package com.barunsw.ojt.yhkim.day15;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClientSocketHandler extends Thread {
	private static Logger LOGGER = LoggerFactory.getLogger(ClientSocketHandler.class);
	
	private static List<ClientSocketHandler> clientRepo = new ArrayList<ClientSocketHandler>();
	
	private Socket socket;
	private InputStream inputStream;
	private OutputStream outputStream;	
	
	public ClientSocketHandler(Socket socket) throws Exception {
		this.socket = socket;
		
		inputStream = socket.getInputStream();
		outputStream = socket.getOutputStream();
		
		clientRepo.add(this);
	}
	
	@Override
	public void run() {
		try {
			byte[] buf = new byte[1024];
			
			int readNum = 0;
			while ((readNum = inputStream.read(buf)) > 0) {
				chkRepo();
				
				String readData = new String(buf, 0, readNum);
				LOGGER.debug("readData: " + readData);
				ClientSocketHandler.sendAll(readData);
			}
		}
		catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
		}
	}
	
	public void sendMessage(String message) {
		try {
			outputStream.write(message.getBytes());
			outputStream.flush();
		}
		catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
		}
	}
	
	public static void sendAll(String message) {
		for (ClientSocketHandler clientHandler : clientRepo) {
				clientHandler.sendMessage(message);				
		}
	}
	
	public static void chkRepo() {
		for (int i = 0; i< clientRepo.size(); i++) {
			if (!clientRepo.get(i).isAlive()) {
				clientRepo.remove(i);
			}
		}
		LOGGER.debug("{}", clientRepo);
	}
}
