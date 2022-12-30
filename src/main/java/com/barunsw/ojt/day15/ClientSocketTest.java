package com.barunsw.ojt.day15;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClientSocketTest {
	private static Logger LOGGER = LoggerFactory.getLogger(ClientSocketTest.class);

	public ClientSocketTest() {
		
	}
	
	public void run() {
		LOGGER.debug("+++ client start");
		
		try {
			Socket socket = new Socket();
			socket.connect(new InetSocketAddress("localhost", ServerSocketTest.PORT));
			
			InputStream inputStream = socket.getInputStream();
			
			Thread inputThread = new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						byte[] buf = new byte[1024];

						int readNum = 0;
						while ((readNum = inputStream.read(buf)) > 0) {
							String readData = new String(buf, 0, readNum);
							LOGGER.debug("readData:" + readData);
						}
					}
					catch (Exception ex) {
						LOGGER.error(ex.getMessage(), ex);
					}
				}
			});
			inputThread.start();
			
			OutputStream outputStream = socket.getOutputStream();
			
			outputStream.write("Hello World".getBytes());
			outputStream.flush();
			Thread.sleep(100);
			
			outputStream.write("Hello World".getBytes());
			outputStream.flush();
			Thread.sleep(100);
			
			outputStream.write("Hello World".getBytes());
			outputStream.flush();
			Thread.sleep(100);
		}
		catch (Exception ex) {
			LOGGER.error(ex.getMessage());
		}
		
		LOGGER.debug("+++ client end");
	}
	
	public static void main(String[] args) {
		new ClientSocketTest().run();
	}
}
