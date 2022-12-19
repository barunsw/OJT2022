package com.barunsw.ojt.yhkim.day05;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.barunsw.ojt.constants.Gender;
import com.barunsw.ojt.intf.AddressBookInterface;
import com.barunsw.ojt.vo.AddressBookVo;

public class DBTest {
	private static Logger LOGGER = LoggerFactory.getLogger(DBTest.class);

	public static void main(String[] args) throws Exception {
		AddressBookInterface addressBook = new DBAddressBookImpl();
		
		List<AddressBookVo> addressBookList = addressBook.selectAddressBook(null); 

		AddressBookVo paramVo = new AddressBookVo();
		paramVo.setName("홍길동");
		paramVo.setBirthday("1994");
		paramVo.setGender(Gender.MAN);
		paramVo.setPhoneNumber("1234");
		paramVo.setAddress("서울");
		
		addressBook.insertAddressBook(paramVo);
		
		addressBookList = addressBook.selectAddressBook(null);
		for(AddressBookVo b : addressBookList) {
			LOGGER.debug(b.toString());
		}
		
		AddressBookVo updateParam = new AddressBookVo();
		updateParam.setSeqNum(115);
		updateParam.setName("홍길동");
		updateParam.setBirthday("2022");
		updateParam.setGender(Gender.MAN);
		updateParam.setPhoneNumber("0908");
		updateParam.setAddress("대구");
		
		addressBook.updateAddressBook(updateParam);
		
		addressBookList = addressBook.selectAddressBook(null);
		for(AddressBookVo b : addressBookList) {
			LOGGER.debug(b.toString());
		}

		AddressBookVo delParam = new AddressBookVo();
		delParam.setSeqNum(114);
		
		addressBook.deleteAddressBook(delParam);
		
		addressBookList = addressBook.selectAddressBook(null);
		for(AddressBookVo b : addressBookList) {
			LOGGER.debug(b.toString());
		}
		
	}
}
