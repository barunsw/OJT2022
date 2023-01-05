package com.barunsw.ojt.day18_2;

import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ClientMain {
	private static final Logger LOGGER = LogManager.getLogger(ClientMain.class);
	
	public static void main(String[] args) {
		try {
			Registry registry = LocateRegistry.getRegistry(ServerMain.PORT);
			
			Remote remote = registry.lookup("ADDRESSBOOK");
			if (remote instanceof AddressBookInterface) {
				AddressBookInterface addressBookIf = 
						(AddressBookInterface)remote;

				List<AddressVo> addressList = addressBookIf.selectAddressList(new AddressVo());
				for (AddressVo oneAddressVo : addressList) {
					LOGGER.debug("select oneAddressVo:" + oneAddressVo);
				}

				AddressVo addressVo = new AddressVo();
				addressVo.setName("홍길동");
				addressVo.setAge(20);

				addressBookIf.insertAddress(addressVo);
			}
			
			Thread.sleep(10_000);
		} 
		catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
	}
}
