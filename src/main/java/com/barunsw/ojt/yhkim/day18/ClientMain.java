package com.barunsw.ojt.yhkim.day18;

import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.barunsw.ojt.constants.Gender;
import com.barunsw.ojt.vo.AddressBookVo;

public class ClientMain {
	private static final Logger LOGGER = LogManager.getLogger(ClientMain.class);
	
	public static void main(String[] args) {
		LOGGER.debug(String.format("+++ ClientMain started."));

		try {
			Registry registry = LocateRegistry.getRegistry(ServerMain.PORT);
			
			Remote remote = registry.lookup("ADDRESSBOOK");
			if (remote instanceof AddressBookInterface) {
				AddressBookInterface addressBookIf = (AddressBookInterface)remote;

				List<AddressBookVo> addressList = addressBookIf.selectAddressList(new AddressBookVo());
				for (AddressBookVo oneAddressBookVo : addressList) {
					LOGGER.debug(String.format("select oneAddressBookVo : %s", oneAddressBookVo.toString()));
				}

				AddressBookVo selectOneAddress = new AddressBookVo();
				selectOneAddress.setSeqNum(205);
				List<AddressBookVo> addressBookList2 = addressBookIf.selectAddressList(selectOneAddress);
				for (AddressBookVo oneAddressBookVo : addressBookList2) {
					LOGGER.debug(String.format("SeqNum %d : %s", oneAddressBookVo.getSeqNum(), oneAddressBookVo.toString()));
				}
				
				// INSERT
				AddressBookVo insertAddress = new AddressBookVo();
				insertAddress.setName("박길동");
				insertAddress.setBirthday("20202020");
				insertAddress.setGender(Gender.MAN);
				insertAddress.setPhoneNumber("1020202020");
				insertAddress.setAddress("제주");
				addressBookIf.insertAddress(insertAddress);
				
				// UPDATE
				AddressBookVo updateAddress = new AddressBookVo();
				updateAddress.setSeqNum(205);
				updateAddress.setName("초록색");
				updateAddress.setBirthday("20202020");
				updateAddress.setGender(Gender.MAN);
				updateAddress.setPhoneNumber("1020202020");
				updateAddress.setAddress("제주");
//				addressBookIf.updateAddress(updateAddress);
				
				// DELETE
				AddressBookVo deleteAddress = new AddressBookVo();
				deleteAddress.setSeqNum(206); 
//				addressBookIf.deleteAddress(deleteAddress);
				

			}
			
			Thread.sleep(10_000);			
		} 
		catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
	}
}
