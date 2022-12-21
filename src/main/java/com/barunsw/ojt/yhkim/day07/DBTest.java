package com.barunsw.ojt.yhkim.day07;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.barunsw.ojt.constants.Gender;
import com.barunsw.ojt.intf.AddressBookInterface;
import com.barunsw.ojt.vo.AddressBookVo;

public class DBTest {
	private static Logger LOGGER = LoggerFactory.getLogger(DBTest.class);

	public static void main(String[] args) throws Exception {

		AddressBookInterface bookService = new MybatisAddressBookImpl();

		List<AddressBookVo> addressBookList = bookService.selectAddressBook(null);
		for(AddressBookVo b : addressBookList) {
			LOGGER.debug(b.toString());
		}

		AddressBookVo insertData = new AddressBookVo();
		insertData.setName("김하나");
		insertData.setBirthday("2000");
		insertData.setGender(Gender.MAN);
		insertData.setPhoneNumber("010-1344-1234");
		insertData.setAddress("강릉");

		int insertResult = bookService.insertAddressBook(insertData);

		addressBookList = bookService.selectAddressBook(null);
		for (AddressBookVo b : addressBookList) {
			LOGGER.debug(b.toString());
		}

		AddressBookVo updateData = new AddressBookVo();
		updateData.setSeqNum(126);
		updateData.setName("abc");
		updateData.setBirthday("2000");
		updateData.setGender(Gender.WOMAN);
		updateData.setPhoneNumber("010-1344-1234");
		updateData.setAddress("강릉");

		int updateResult = bookService.updateAddressBook(updateData);

		addressBookList = bookService.selectAddressBook(null);
		for (AddressBookVo b : addressBookList) {
			LOGGER.debug(b.toString());
		}

		AddressBookVo deleteData = new AddressBookVo();
		deleteData.setSeqNum(135);

		int deleteResult = bookService.deleteAddressBook(deleteData);

		addressBookList = bookService.selectAddressBook(null);
		for (AddressBookVo b : addressBookList) {
			LOGGER.debug(b.toString());
		}

	}
}
