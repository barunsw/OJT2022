package com.barunsw.ojt.jmlee.day15;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClientImpl {
	
	private static Logger LOGGER = LoggerFactory.getLogger(ClientImpl.class);
	private InputStream inputStream;
	private OutputStream outputStream;
	private ClientPanel clientPanel;
	
	Socket socket;
	Thread inputThread;

	public ClientImpl(ClientPanel panel) {
		clientPanel = panel;
	}
	
	public void run() {
		LOGGER.debug("+++ client start");
		
		try {
			socket = new Socket();
			socket.connect(new InetSocketAddress("localhost", ServerTest.PORT));
			
			inputStream = socket.getInputStream();
			
			inputThread = new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						byte[] buf = new byte[1024];
						int readNum = 0;
						while ((readNum = inputStream.read(buf)) > 0) {
							String readData = new String(buf, 0, readNum);
							LOGGER.debug("readData:" + readData);
							clientPanel.receive(readData);
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
		
		LOGGER.debug("+++ client end");
	}
	
	public void send(String message) {
		
		try {
			Thread outputThread = new Thread(new Runnable() {
				@Override
				public void run() {
					try { 
					outputStream = socket.getOutputStream();
					outputStream.write(message.getBytes());
					outputStream.flush();
				}
				catch (Exception ex) {
					LOGGER.error(ex.getMessage(), ex);
				}
			}
			});
			outputThread.start();
		}
		catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
		}
	}
	
}
