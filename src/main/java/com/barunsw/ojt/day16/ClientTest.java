package com.barunsw.ojt.day16;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ClientTest {
	private static final Logger LOGGER = LogManager.getLogger(ClientTest.class);
	
	public static void main(String[] args) throws Exception {
		Socket socket = new Socket("localhost", ServerTest.PORT);
		
		// 위의 과정과 같다.
		//Socket socket2 = new Socket();
		//socket2.connect(new InetSocketAddress("localhost", ServerTest.PORT));
		
		try (OutputStream outputStream = socket.getOutputStream();
				InputStream inputStream = socket.getInputStream()) {
			outputStream.write("Hello World".getBytes());
			//outputStream.flush();

			byte[] buf = new byte[1024];
			int readNum = inputStream.read(buf);
			
			LOGGER.debug("read:" + new String(buf, 0, readNum));
		}
	}
}
