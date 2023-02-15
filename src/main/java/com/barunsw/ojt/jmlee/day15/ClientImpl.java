package com.barunsw.ojt.jmlee.day15;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClientImpl {
	
	private static Logger LOGGER = LoggerFactory.getLogger(ClientImpl.class);
	private ClientPanel clientPanel;
	private Socket socket;
	private BufferedReader reader;
	private BufferedWriter writer;	

	
	public ClientImpl(ClientPanel panel) throws Exception {
		clientPanel = panel;
		
		socket = new Socket("localhost", ServerMain.PORT);
		
		reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));		

	}
	
	public void run() {
		LOGGER.debug("+++ client start");

		try {			
			Thread inputThread = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					String readLine = null;
					while ((readLine = reader.readLine()) != null ) {
						clientPanel.receive(readLine);
					}

				}
				catch (Exception ex) {
					LOGGER.error(ex.getMessage(), ex);
				}
			}
		});
		inputThread.start();

		}
		catch (Exception ex) {
			LOGGER.error(ex.getMessage());
		}
	}


	public void sendMessage(String message) {
		LOGGER.debug(String.format("sendMessage(%s)", message));

		try {						
			writer.write(message+"\n");
			writer.flush();
		} 
		catch (Exception ex) {
			LOGGER.error(ex.getMessage(),ex);

		}
	}
	
	public void stopClient() {
		try {
			if (socket != null && !socket.isClosed()) {
				socket.close();
				LOGGER.debug("+++ socket close");
			}
		}
		catch (Exception ex) {
			LOGGER.error(ex.getMessage());
		}
	}
}
