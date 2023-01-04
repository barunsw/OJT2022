package com.barunsw.ojt.jmlee.day16;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.barunsw.ojt.constants.Gender;
import com.barunsw.ojt.vo.AddressBookVo;

public class SocketBookImpl implements BookInterface {
	private static final Logger LOGGER = LogManager.getLogger(SocketBookImpl.class);
	
	private Socket clientSocket;
	
	private BufferedReader reader;
	private BufferedWriter writer;
	
	public SocketBookImpl(String host, int port) throws Exception {
		clientSocket = new Socket(host, port);		
		reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		writer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));		
	}
	
	@Override
	public List<AddressBookVo> selectAddressList(AddressBookVo addressBookVo) throws Exception {

			String command = String.format("SELECT\n");
			
			writer.write(command);
			writer.flush();
			
			StringBuffer buf = new StringBuffer();
			String readLine = null;
			
			while ((readLine = reader.readLine()) != null) {
				if (readLine.startsWith("SELECT")) {
					buf.append(readLine + "\n");
					break;
				}
			}
			LOGGER.debug("received:" + readLine);

			List<AddressBookVo> addressList = new ArrayList<>();
			String data[] = buf.toString().split("&");
			
			for (String read : data) {
				LOGGER.debug("read:" + read);
				AddressBookVo address = parseCmd(read);
				addressList.add(address);
				LOGGER.debug("addressList:" + addressList);

			}
			LOGGER.debug("addressList:" + addressList);

			return addressList;
			
		}
	
	private AddressBookVo parseCmd(String paramStr) throws Exception {
		AddressBookVo addressVo = new AddressBookVo();

		String[] paramList = paramStr.split(",");

		for (String oneParam : paramList) {
			String[] oneParamData = oneParam.split("=");
			String key = oneParamData[0].trim();
			String val = oneParamData[1].trim();

			switch (key) {
			case "SELECT:NAME": //첫 이름은 SELECT가 붙어있어서 이렇게 찾아줘야하는 듯
			case "NAME":
				addressVo.setName(val);
				break;
			case "AGE":
				addressVo.setBirthday(val);
				break;
			case "GENDER":
				addressVo.setGender(Gender.getGender(val));
				break;
			case "PHONENUMBER":
				addressVo.setPhoneNumber(val);
			case "ADDRESS":
				addressVo.setAddress(val);
				break;
			}
		}
		return addressVo;
	}
	

	@Override
	public int insertAddress(AddressBookVo addressBookVo) throws Exception {
		String command = String.format("INSERT:NAME=%s,BIRTHDAY=%s,GENDER=%s,PHONENUMBER=%s,ADDRESS=%s\n", 
				addressBookVo.getName(), addressBookVo.getBirthday(), addressBookVo.getGender(),
				addressBookVo.getPhoneNumber(), addressBookVo.getAddress());

		LOGGER.debug("command:" + command);
		
		writer.write(command);

		LOGGER.debug("writer.write()");
		
		writer.flush();
		
		LOGGER.debug("writer.flush()");
		
		return 0;
	}

	@Override
	public int updateAddress(AddressBookVo addressBookVo) throws Exception {
		String command = String.format("UPDATE:NAME=%s, BIRTHDAY=%s, GENDER=%s, PHONENUMBER=%s, ADDRESS=%s\n", 
				addressBookVo.getName(), addressBookVo.getBirthday(), addressBookVo.getGender(),
				addressBookVo.getPhoneNumber(), addressBookVo.getAddress());

		writer.write(command);
		writer.flush();
		
		return 0;
	}

	@Override
	public int deleteAddress(AddressBookVo addressBookVo) throws Exception {
		String command = String.format("DELETE:NAME=%s", addressBookVo.getName());

		writer.write(command);
		writer.flush();
		
		return 0;
	}

	public void close() {
		
	}
}
