package com.barunsw.ojt.jmlee.day15;

import java.net.ServerSocket;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServerTest {
	private static Logger LOGGER = LoggerFactory.getLogger(ServerTest.class);
	
	public static final int PORT = 5000;
	
	private boolean runFlag;
	
	public ServerTest() {	
	}
	
	public void run() {
		LOGGER.debug("+++ server start");
		
		try {
			ServerSocket serverSocket = new ServerSocket(PORT);
			//serverSocket.bind(new InetSocketAddress("0.0.0.0", PORT));
			
			runFlag = true;
			while (runFlag) {
				LOGGER.debug("==== 클라이언트 연결 대기중 ====");
				Socket clientSocket = serverSocket.accept();
				
				// 클라이언트가 접속 할 때마다 스레드 생성
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
		new ServerTest().run();
	}
}
