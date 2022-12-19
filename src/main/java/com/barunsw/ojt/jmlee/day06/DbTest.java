package com.barunsw.ojt.jmlee.day06;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.barunsw.ojt.constants.Gender;
import com.barunsw.ojt.vo.AddressBookVo;


public class DbTest {

	private static Logger LOGGER = LoggerFactory.getLogger(DbTest.class);
	
	public static void main(String[] args) throws Exception {

		DBAddressBookImpl addressBook = new DBAddressBookImpl();
		
		addressBook.selectAddressBookVos(null);		
		AddressBookVo paramVo = new AddressBookVo();
		
		paramVo.setSeqNum(2);
		paramVo.setAddress("대전");
		paramVo.setGender(Gender.WOMAN);
//		paramVo.setSeqNum(3);
//		paramVo.setName("홍길동");
//		paramVo.setPhoneNumber("9509");
//		paramVo.setAddress("인천");
//		
//		addressBook.insertAddressBook(paramVo);
		LOGGER.debug("====UPDATE====");
		addressBook.updateaAddressBook(paramVo);		
//		LOGGER.debug("====DELETE====");
//		addressBook.deleteAddressBook(paramVo);
		addressBook.selectAddressBookVos(null);
		

	}

}
