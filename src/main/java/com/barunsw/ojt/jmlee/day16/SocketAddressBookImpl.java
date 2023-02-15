package com.barunsw.ojt.jmlee.day16;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.barunsw.ojt.constants.Gender;
import com.barunsw.ojt.intf.AddressBookInterface;
import com.barunsw.ojt.vo.AddressBookVo;

public class SocketAddressBookImpl implements AddressBookInterface {
	private static Logger LOGGER = LoggerFactory.getLogger(SocketAddressBookImpl.class);

	private Socket clientSocket;

	private BufferedReader reader;
	private BufferedWriter writer;

	private static final int KEY = 0;
	private static final int VALUE = 1;
	
	public SocketAddressBookImpl(String host, int port) throws Exception {
		clientSocket = new Socket(host, port);

		reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		writer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));		
	}
	
	private AddressBookVo parseCmd(String paramStr) {
		AddressBookVo addressBookVo = new AddressBookVo();
		String[] paramList = paramStr.split(",");
		for (String oneParam : paramList) {
			String[] oneParamData = oneParam.split("=");
			String key = oneParamData[KEY].trim();
			String val = oneParamData[VALUE].trim();

			switch (key) {
			case "SEQNUM":
				addressBookVo.setSeqNum(Integer.parseInt(val));
				break;
			case "NAME":
				addressBookVo.setName(val);
				break;
			case "BIRTHDAY":
				addressBookVo.setBirthday(val);
				break;
			case "ADDRESS":
				addressBookVo.setAddress(val);
				break;
			case "GENDER":
				addressBookVo.setGender(Gender.getGender(val));
				break;
			case "PHONENUMBER":
				addressBookVo.setPhoneNumber(val);
				break;
			}
		}

		return addressBookVo;
	}

	@Override
	public List<AddressBookVo> selectAddressBook(AddressBookVo paramData) {
		String command = String.format("SELECT:SEQNUM=%s\n", paramData.getSeqNum());

		List<AddressBookVo> addressBookList = new ArrayList<>();

		try {
			LOGGER.debug(command);
			writer.write(command);
			writer.flush();

			StringBuffer buf = new StringBuffer();
			String readLine = null;
			while ((readLine = reader.readLine()) != null) {
				buf.append(readLine);
				break;
			}		
			
			LOGGER.debug(String.format("received: %s", buf.toString()));

			String[] bufSplit = buf.toString().split(";");
			for(int i = 0; i < bufSplit.length; i++) {
				addressBookList.add(parseCmd(bufSplit[i]));
			}
		} 
		catch (IOException e) {
			e.printStackTrace();
		}

		return addressBookList;
	}

	@Override
	public int insertAddressBook(AddressBookVo paramData) throws Exception {
		String command = String.format("INSERT:NAME=%s,BIRTHDAY=%s,GENDER=%s,PHONENUMBER=%s,ADDRESS=%s\n", 
				paramData.getName(), paramData.getBirthday(), paramData.getGender(), paramData.getPhoneNumber(), paramData.getAddress());

		LOGGER.debug(command);
		writer.write(command);
		writer.flush();

		return 0;
	}

	@Override
	public int updateAddressBook(AddressBookVo paramData) throws Exception {
		String command = String.format("UPDATE:SEQNUM=%s,NAME=%s,BIRTHDAY=%s,GENDER=%s,PHONENUMBER=%s,ADDRESS=%s\n", 
				paramData.getSeqNum(), paramData.getName(), paramData.getBirthday(), paramData.getGender(), paramData.getPhoneNumber(), paramData.getAddress());

		LOGGER.debug(command);
		writer.write(command);
		writer.flush();		

		return 0;
	}

	@Override
	public int deleteAddressBook(AddressBookVo paramData) throws Exception {
		String command = String.format("DELETE:SEQNUM=%s\n", paramData.getSeqNum());

		LOGGER.debug(command);
		writer.write(command);
		writer.flush();		
		
		return 0;
	}

}
