package com.barunsw.ojt.yhkim.day16;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.barunsw.ojt.constants.Gender;
import com.barunsw.ojt.intf.AddressBookInterface;
import com.barunsw.ojt.vo.AddressBookVo;

public class ClientMain {
	private static Logger LOGGER = LoggerFactory.getLogger(ClientMain.class);
	
	public static void main(String[] args) {
		LOGGER.debug(String.format("ClientMain started."));

		try {
			AddressBookInterface addressBookIf = 
					new SocketAddressBookImpl("localhost", ServerMain.PORT);
			
			// INSERT
			AddressBookVo insertAddress = new AddressBookVo();
			insertAddress.setName("홍길동");
			insertAddress.setBirthday("20202020");
			insertAddress.setGender(Gender.MAN);
			insertAddress.setPhoneNumber("1020202020");
			insertAddress.setAddress("제주");
//			addressBookIf.insertAddressBook(insertAddress);
			
			// UPDATE
			AddressBookVo updateAddress = new AddressBookVo();
			updateAddress.setSeqNum(192);
			updateAddress.setName("초록색");
			updateAddress.setBirthday("20202020");
			updateAddress.setGender(Gender.MAN);
			updateAddress.setPhoneNumber("1020202020");
			updateAddress.setAddress("제주");
//			addressBookIf.updateAddressBook(updateAddress);
			
			// DELETE
			AddressBookVo deleteAddress = new AddressBookVo();
			deleteAddress.setSeqNum(192);
//			addressBookIf.deleteAddressBook(deleteAddress);
			
			// SELETE
			List<AddressBookVo> addressBookList = addressBookIf.selectAddressBook(new AddressBookVo());
			for (AddressBookVo oneAddressVo : addressBookList) {
				LOGGER.debug(oneAddressVo.toString());
			}

			Thread.sleep(10_000);
		} 
		catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
	}
}
