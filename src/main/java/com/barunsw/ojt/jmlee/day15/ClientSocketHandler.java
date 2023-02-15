package com.barunsw.ojt.jmlee.day15;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClientSocketHandler extends Thread {
	private static Logger LOGGER = LoggerFactory.getLogger(ClientSocketHandler.class);
	
	private static List<ClientSocketHandler> clientRepo = new ArrayList<ClientSocketHandler>();
	
	private Socket socket;
	private BufferedReader reader;
	private BufferedWriter writer;	
	
	public ClientSocketHandler(Socket socket) throws Exception {
		this.socket = socket;
				
		reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));		
		
		clientRepo.add(this);
	}
	
	@Override
	public void run() {
		try {
			String readLine = null;
			while ((readLine = reader.readLine()) != null ) {
				ClientSocketHandler.sendAll(readLine);
			}
		}
		catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
		}
	}
	
	public void sendMessage(String message) {
		try {
			writer.write(message+"\n");
			writer.flush();
		}
		catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
		}
	}
	
	public static void sendAll(String message) {
		for (int i = 0; i< clientRepo.size(); i++) {
			if (!clientRepo.get(i).isAlive()) {
				clientRepo.remove(i);
			}
			else {
				clientRepo.get(i).sendMessage(message);
			}
		}
	}
}
