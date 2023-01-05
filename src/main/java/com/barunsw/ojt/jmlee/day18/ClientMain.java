package com.barunsw.ojt.jmlee.day18;

import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.barunsw.ojt.constants.Gender;

public class ClientMain {
	private static final Logger LOGGER = LogManager.getLogger(ClientMain.class);
	
	public static void main(String[] args) {
		try {
			Registry registry = LocateRegistry.getRegistry(ServerMain.PORT);
			
			Remote remote = registry.lookup("ADDRESSBOOK");
			if (remote instanceof RMIBookInterface) {
				RMIBookInterface addressBookIf = 
						(RMIBookInterface)remote;

				AddressBookVo addressVo = new AddressBookVo();
				// Select
//				addressBookIf.selectAddressList(addressVo);
				
				// Insert
//				addressVo.setName("가나다");
//				addressVo.setBirthday("2023");
//				addressVo.setGender(Gender.MAN);
//				addressVo.setPhoneNumber("0909");
//				addressVo.setAddress("관악");
//				LOGGER.debug("클라이언트 Insert 호출");
//				addressBookIf.insertAddress(addressVo);

				// Update
//				addressVo.setSeqNum(57);
//				addressVo.setName("강감찬");
//				addressVo.setBirthday("1523");
//				addressVo.setGender(Gender.WOMAN);
//				addressVo.setPhoneNumber("0909");
//				addressVo.setAddress("관악");
//				addressBookIf.updateAddress(addressVo);
				
				// Delete
				addressVo.setSeqNum(57);
				addressBookIf.deleteAddress(addressVo);
				
				
			}
			
			Thread.sleep(10_000);
		} 
		catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
	}
}
