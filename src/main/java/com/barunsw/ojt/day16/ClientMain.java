package com.barunsw.ojt.day16;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ClientMain {
	private static final Logger LOGGER = LogManager.getLogger(ClientMain.class);
	
	public static void main(String[] args) {
		try {
			AddressBookInterface addressBookIf = 
					new SocketAddressBookImpl("localhost", ServerMain.PORT);
			
			AddressVo addressVo = new AddressVo();
			addressVo.setName("홍길동");
			addressVo.setAge(20);
			
			addressBookIf.insertAddress(addressVo);
			
			Thread.sleep(10_000);
		} 
		catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
	}
}
