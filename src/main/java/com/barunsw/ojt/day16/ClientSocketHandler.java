package com.barunsw.ojt.day16;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ClientSocketHandler extends Thread {
	private static final Logger LOGGER = LogManager.getLogger(ClientSocketHandler.class);
	
	private Socket clientSocket;
	
	private AddressBookInterface addressBookIf;
	
	public ClientSocketHandler(Socket clientSocket) {
		this.clientSocket = clientSocket;
	}
	
	private AddressVo parseCmd(String paramStr) {
		AddressVo addressVo = new AddressVo();
		
		String[] paramList = paramStr.split(",");
		for (String oneParam : paramList) {
			String[] oneParamData = oneParam.split("=");
			String key = oneParamData[0].trim();
			String val = oneParamData[1].trim();
			
			switch (key) {
			case "NAME":
				addressVo.setName(val);
				break;
			case "AGE":
				addressVo.setAge(Integer.parseInt(val));
				break;
			}
		}
		
		return addressVo;
	}
	
	@Override
	public void run() {
		LOGGER.debug("+++ ClientSocketHandler run");
			//SELECT:SEQ=1;
			//INSERT:NAME=홍길동,OLD=14,;
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
			String readLine = null;
			while ((readLine = reader.readLine()) != null) {
				LOGGER.debug(String.format("+++ readLine:%s", readLine));
				
				String[] cmdSplit = readLine.split(":");
				
				String cmd = cmdSplit[0];
				switch (cmd) {
				case "INSERT":
					AddressVo addressVo = parseCmd(cmdSplit[1]);
					handleInsert(addressVo);
					break;
				}
			}
		}
		catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
		}
		
		LOGGER.debug("--- ClientSocketHandler run");
	}
	
	private void handleSelect() throws Exception {
		try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()))) {
			// List<AddressBookVo> addrList = addrImpl.selectList();
			// List -> String
			//writer.write(message);
		}
	}
	
	private void handleInsert(AddressVo addressVo) {
		LOGGER.debug(String.format("+++ handleInsert [%s]", addressVo));
	}
}
