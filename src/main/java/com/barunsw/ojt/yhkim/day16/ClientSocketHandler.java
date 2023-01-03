package com.barunsw.ojt.yhkim.day16;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.barunsw.ojt.constants.Gender;
import com.barunsw.ojt.intf.AddressBookInterface;
import com.barunsw.ojt.vo.AddressBookVo;

public class ClientSocketHandler extends Thread {
	private static Logger LOGGER = LoggerFactory.getLogger(ClientSocketHandler.class);

	private Socket clientSocket;
	
	private AddressBookInterface addressBookIf = new MybatisAddressBookImpl();
		
	public ClientSocketHandler(Socket clientSocket) {
		this.clientSocket = clientSocket;
	}
	
	private AddressBookVo parseCmd(String paramStr) {
		AddressBookVo addressBookVo = new AddressBookVo();
		
		String[] paramList = paramStr.split(",");
		for (String oneParam : paramList) {
			String[] oneParamData = oneParam.split("=");
			String key = oneParamData[0].trim();
			String val = oneParamData[1].trim();
			
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
	public void run() {
		LOGGER.debug("+++ ClientSocketHandler run");
			//SELECT:SEQ=1;
			//INSERT:NAME=홍길동,OLD=14,;
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()))) {
			String readLine = null;
			while ((readLine = reader.readLine()) != null) {
				LOGGER.debug(String.format("+++ readLine:%s", readLine));
				
				String[] cmdSplit = readLine.split(":");
				
				CmdType cmd = CmdType.getCmdType(cmdSplit[0]);
				switch (cmd) {
				case SELECT:
					writer.write(handleSelect()+"\n");
					writer.flush();
					break;
				case INSERT:
					AddressBookVo addressBookVo = parseCmd(cmdSplit[1]);
					handleInsert(addressBookVo);
					break;
				case UPDATE:
					addressBookVo = parseCmd(cmdSplit[1]);
					handleUpdate(addressBookVo);
					break;
				case DELETE:
					addressBookVo = parseCmd(cmdSplit[1]);
					handleDelete(addressBookVo);
					break;
				}
			}
		}
		catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
		}
		
		LOGGER.debug("--- ClientSocketHandler run");
	}
	
	private String handleSelect() throws Exception {
		StringBuffer buf = new StringBuffer();

		List<AddressBookVo> addressBookList = addressBookIf.selectAddressBook(new AddressBookVo());
		for (AddressBookVo b : addressBookList) {
			buf.append("SEQNUM="+b.getSeqNum()+",NAME="+b.getName()+",BIRTHDAY="+b.getBirthday()+",GENDER="+b.getGender()+",PHONENUMBER="+b.getPhoneNumber()+",ADDRESS="+b.getAddress()+";");
		}
		
		LOGGER.debug(buf.toString());
		
		return buf.toString();
	}
	
	private int handleInsert(AddressBookVo addressBookVo) throws Exception {
		LOGGER.debug(String.format("+++ handleInsert [%s]", addressBookVo));
		
		int result = addressBookIf.insertAddressBook(addressBookVo);
		LOGGER.debug(String.format("insert result : %d", result));
		
		return result;
	}
	
	private int handleUpdate(AddressBookVo addressBookVo) throws Exception {
		LOGGER.debug(String.format("+++ handleUpdate [%s]", addressBookVo));
		
		int result = addressBookIf.updateAddressBook(addressBookVo);
		LOGGER.debug(String.format("update result : %d", result));
		
		return result;
	}
	
	private int handleDelete(AddressBookVo addressBookVo) throws Exception {
		LOGGER.debug(String.format("+++ handleDelete [%s]", addressBookVo));
		
		int result = addressBookIf.deleteAddressBook(addressBookVo);
		LOGGER.debug(String.format("delete result : %d", result));
		
		return result;
	}
}
