package com.barunsw.ojt.jmlee.day16;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Constructor;
import java.net.Socket;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.barunsw.ojt.constants.Gender;
import com.barunsw.ojt.vo.AddressBookVo;

public class ClientSocketHandler extends Thread {
	private static final Logger LOGGER = LogManager.getLogger(ClientSocketHandler.class);
	
	private Socket clientSocket;
	
	private BookInterface addressBookIf = new SwingAddressBookImpl();
	
	private final int KEY = 0;
	private final int VALUE = 1;
	
	public ClientSocketHandler(Socket clientSocket) throws Exception {

		this.clientSocket = clientSocket;	// 서버에서 사용할 인터페이스 구현체를 받는다
	}
	
	private AddressBookVo parseCmd(String paramStr) {
		AddressBookVo addressBookVo = new AddressBookVo();
		
		String[] paramList = paramStr.split(",");
		for (String oneParam : paramList) {
			String[] oneParamData = oneParam.split("=");
			String key = oneParamData[KEY].trim();
			String val = oneParamData[VALUE].trim();//상수로 선언해서
					
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
			case "GENDER":
				addressBookVo.setGender(Gender.getGender(val));
				break;
			case "PHONENUMBER":
				addressBookVo.setPhoneNumber(val);
				break;
			case "ADDRESS":
				addressBookVo.setAddress(val);
				break;
			}
		}
		return addressBookVo;
	}
	
	@Override
	public void run() {
		LOGGER.debug("ClientSocketHandler run");
		
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));) {
			
			String readLine = null;
			while ((readLine = reader.readLine()) != null) {
				LOGGER.debug(String.format("========= readLine:%s", readLine));
				
				String[] cmdSplit = readLine.split(":"); // : 로 명령어를 구분
				
				CmdType cmd = CmdType.getCmdType(cmdSplit[KEY]); // 명령어
				switch (cmd) {
				case SELECT:
					writer.write(handleSelect()+"\n");
					writer.flush();
					break;
				case SELECTONE:
					AddressBookVo addressBookVo = parseCmd(cmdSplit[VALUE]); // 데이터 값
					writer.write(handleOneSelect(addressBookVo)+"\n");
					writer.flush();
					break;
				case INSERT:
					addressBookVo = parseCmd(cmdSplit[VALUE]);
					handleInsert(addressBookVo); 
					break;
				case UPDATE:
					addressBookVo = parseCmd(cmdSplit[VALUE]);
					handleUpdate(addressBookVo);
					break;
				case DELETE:
					addressBookVo = parseCmd(cmdSplit[VALUE]);
					handleDelete(addressBookVo);
					break;
				}
			}
		}
		catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
		}
		
		LOGGER.debug("ClientSocketHandler 종료");
	}

	private String handleSelect() throws Exception {
		List<AddressBookVo> addressbookList = addressBookIf.selectAddressList(new AddressBookVo());
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append("SELECT");
		for (AddressBookVo addressBook : addressbookList) {
			stringBuffer.append(String.format("NAME=%s,BIRTHDAY=%s,GENDER=%s,PHONENUMBER=%s,ADDRESS=%s;",
												addressBook.getName(),addressBook.getBirthday(),addressBook.getGender(),
												addressBook.getPhoneNumber(),addressBook.getAddress()));
		}
		LOGGER.debug(stringBuffer.toString());
		return stringBuffer.toString();
	}
	
	private String handleOneSelect(AddressBookVo addressBookVo) throws Exception {
		List<AddressBookVo> addressbookList = addressBookIf.selectOneAddress(addressBookVo);
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append("SELECTONE");
		for (AddressBookVo addressBook : addressbookList) {
			stringBuffer.append(String.format("NAME=%s,BIRTHDAY=%s,GENDER=%s,PHONENUMBER=%s,ADDRESS=%s;",
												addressBook.getName(),addressBook.getBirthday(),addressBook.getGender(),
												addressBook.getPhoneNumber(),addressBook.getAddress()));
		}
		LOGGER.debug(stringBuffer.toString());
		return stringBuffer.toString();
	}
	
	private int handleInsert(AddressBookVo addressBookVo) throws Exception {
		return addressBookIf.insertAddress(addressBookVo);
	}
	
	private int handleUpdate(AddressBookVo addressBookVo) throws Exception {
		return addressBookIf.updateAddress(addressBookVo);
		
	}
	
	private int handleDelete(AddressBookVo addressBookVo) throws Exception {
		return addressBookIf.deleteAddress(addressBookVo);
		
	}
	
	
}
