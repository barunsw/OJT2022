package com.barunsw.ojt.day16;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SocketAddressBookImpl implements AddressBookInterface {
	private static final Logger LOGGER = LogManager.getLogger(SocketAddressBookImpl.class);
	
	private Socket clientSocket;
	
	private BufferedReader reader;
	private BufferedWriter writer;
	
	public SocketAddressBookImpl(String host, int port) throws Exception {
		clientSocket = new Socket(host, port);
		
		reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		writer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));		
	}
	
	@Override
	public List<AddressVo> selectAddressList(AddressVo addressVo) throws Exception {
		String command = String.format("SELECT\n");

		writer.write(command);
		writer.flush();
		
		StringBuffer buf = new StringBuffer();
		
		String readLine = null;
		while ((readLine = reader.readLine()) != null) {
			buf.append(readLine + "\n");
		}
		
		LOGGER.debug("received:" + readLine);
		
		List<AddressVo> list = null;
		
		// buf의 내용을 list에 넣는다.
		
		return list;
	}

	@Override
	public int insertAddress(AddressVo addressVo) throws Exception {
		String command = String.format("INSERT:NAME=%s,AGE=%s\n", 
				addressVo.getName(), addressVo.getAge());

		LOGGER.debug("command:" + command);
		
		writer.write(command);

		LOGGER.debug("writer.write()");
		
		writer.flush();
		
		LOGGER.debug("writer.flush()");
		
		return 0;
	}

	@Override
	public int updateAddress(AddressVo addressVo) throws Exception {
		String command = String.format("UPDATE:NAME=%s,AGE=%s\n", 
				addressVo.getName(), addressVo.getAge());

		writer.write(command);
		writer.flush();
		
		return 0;
	}

	@Override
	public int deleteAddress(AddressVo addressVo) throws Exception {
		String command = String.format("DELETE:NAME=%s,AGE=%s\n", 
				addressVo.getName(), addressVo.getAge());

		writer.write(command);
		writer.flush();
		
		return 0;
	}

	public void close() {
		
	}
}
