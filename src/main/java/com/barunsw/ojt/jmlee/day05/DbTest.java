package com.barunsw.ojt.jmlee.day05;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.barunsw.ojt.constants.Gender;
import com.barunsw.ojt.vo.AddressBookVo;


public class DbTest {

	private static Logger LOGGER = LoggerFactory.getLogger(DbTest.class);
	
	public static void main(String[] args) throws Exception {

		FileAddressBookImpl addressBook = new FileAddressBookImpl();
		
		addressBook.selectAddressBookVos(null);		
//		AddressBookVo paramVo = new AddressBookVo();
		
//		paramVo.setName("이재민");
//		paramVo.setGender(Gender.MAN);
//		paramVo.setBirthday("1995");
//		paramVo.setPhoneNumber("1234");
//		paramVo.setAddress("서울");
//		
//		addressBook.insertAddressBook(paramVo);
//		addressBook.selectAddressBookVos(null);		

	}

}
