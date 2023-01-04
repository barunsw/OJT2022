package com.barunsw.ojt.jmlee.day16;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.barunsw.ojt.constants.Gender;
import com.barunsw.ojt.vo.AddressBookVo;

public class ClientMain {
	private static final Logger LOGGER = LogManager.getLogger(ClientMain.class);
	
	public static void main(String[] args) {
		try {
			BookInterface addressBookIf = 
					new SocketBookImpl("localhost", ServerMain.PORT);
			
			AddressBookVo insert = new AddressBookVo();
			insert.setName("이순신");
			insert.setBirthday("2023");
			insert.setGender(Gender.MAN);
			insert.setPhoneNumber("0909");
			insert.setAddress("관악");
			addressBookIf.insertAddress(insert);

//			Thread.sleep(10_000);
			
		} 
		catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
	}
}
