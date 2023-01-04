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
	
	private final int KEY = 0;
	private final int VALUE = 1;
	
	public SocketBookImpl(String host, int port) throws Exception {
		clientSocket = new Socket(host, port);		
		reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		writer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));	
		LOGGER.debug("==================== 소켓 생성 ================ " + clientSocket);
	}
	
	private AddressBookVo parseCmd(String paramStr) throws Exception {
		AddressBookVo addressVo = new AddressBookVo();
		
		String[] paramList = paramStr.split(",");	// ,로 컬럼 정보 가져옴
		for (String oneParam : paramList) {
			String[] oneParamData = oneParam.split("="); 
			String key = oneParamData[KEY].trim(); // 열 정보
			String val = oneParamData[VALUE].trim(); // 데이터 값
			
			switch (key) {
			case "SEQNUM":
				addressVo.setSeqNum(Integer.parseInt(val));
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
			LOGGER.debug("received:" + readLine); //write한 값을 readline으로 한줄로 가져옴

			List<AddressBookVo> addressList = new ArrayList<>();
			String data[] = buf.toString().split(";");	// buf에 담긴 값들을 ; 로 나누고 data에 넣음
			
			for (String read : data) {
				LOGGER.debug("read:" + read);
				AddressBookVo address = parseCmd(read);
				addressList.add(address);
			}

			return addressList;
			
		}
	
	@Override
	public List<AddressBookVo> selectOneAddress(AddressBookVo addressBookVo) throws Exception {
		String command = String.format("SELECTONE:SEQNUM=%s\n",addressBookVo.getSeqNum());
		
		writer.write(command);
		LOGGER.debug("command : " + command);
		writer.flush();
		
		StringBuffer buf = new StringBuffer();
		String readLine = null;

		while ((readLine = reader.readLine()) != null) {	
			if (readLine.startsWith("SELECTONE")) {		
				buf.append(readLine + "\n");
				break;
			}
		}
		LOGGER.debug("received:" + readLine); 

		List<AddressBookVo> addressList = new ArrayList<>();
		String data[] = buf.toString().split(";");	
		
		for (String read : data) {
			LOGGER.debug("read:" + read);
			AddressBookVo address = parseCmd(read);
			addressList.add(address);
		}

		return addressList;
		
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
		String command = String.format("UPDATE:SEQNUM=%s,NAME=%s,BIRTHDAY=%s,GENDER=%s,PHONENUMBER=%s,ADDRESS=%s\n", 
				addressBookVo.getSeqNum(), addressBookVo.getName(), addressBookVo.getBirthday(), addressBookVo.getGender(),
				addressBookVo.getPhoneNumber(), addressBookVo.getAddress());

		writer.write(command);
		writer.flush();
		
		return 0;
	}

	@Override
	public int deleteAddress(AddressBookVo addressBookVo) throws Exception {
		String command = String.format("DELETE:SEQNUM=%s\n", addressBookVo.getSeqNum());

		writer.write(command);
		writer.flush();
		
		return 0;
	}

}
