package com.barunsw.ojt.day04;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.barunsw.ojt.constants.Gender;
import com.barunsw.ojt.intf.AddressBookInterface;
import com.barunsw.ojt.vo.AddressBookVo;

public class AdressBookMain {
	private static Logger LOGGER = LoggerFactory.getLogger(AdressBookMain.class);
	
	public static void main(String[] args) {
		try {
			AddressBookInterface addressBook = new FileAddressBookImpl("data/addressbook.dat");
			
			AddressBookVo addressBookVo = new AddressBookVo("배수현", "1975-09-04", Gender.MAN, "010-0000-0000", "서울");
	
			addressBook.insertAddressBook(addressBookVo);
			
			List<AddressBookVo> addressBookList = addressBook.selectAddressBook(new AddressBookVo());
			for (AddressBookVo oneAddressBook : addressBookList) {
				LOGGER.debug(oneAddressBook.toString());
			}
		}
		catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
		}
	}
}
