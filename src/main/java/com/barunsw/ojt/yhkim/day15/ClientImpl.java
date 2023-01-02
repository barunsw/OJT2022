package com.barunsw.ojt.yhkim.day15;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.barunsw.ojt.day15.ServerSocketTest;

public class ClientImpl {
	
	private static Logger LOGGER = LoggerFactory.getLogger(ClientImpl.class);
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
			socket.connect(new InetSocketAddress("localhost", ServerSocketTest.PORT));

			InputStream inputStream = socket.getInputStream();

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
	}


	public void sendMessage(String msg) {
		LOGGER.debug("sendMessage : "+msg);

		try {			
			Thread outputThread = new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						OutputStream outputStream = socket.getOutputStream();
						outputStream.write(msg.getBytes());
						outputStream.flush();
					} 
					catch (Exception ex) {
						LOGGER.debug(ex.getMessage());
					}
				}
			});
			outputThread.start();
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
