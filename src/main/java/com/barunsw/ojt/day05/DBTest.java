package com.barunsw.ojt.day05;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.barunsw.ojt.intf.AddressBookInterface;
import com.barunsw.ojt.vo.AddressBookVo;

public class DBTest {
	private static Logger LOGGER = LoggerFactory.getLogger(DBTest.class);

	public static void main(String[] args) throws Exception {
		AddressBookInterface addressBook = new DBAddressBookImpl();
		
		addressBook.selectAddressBook(null);  // 연락처 조회문
		
		AddressBookVo paramVo = new AddressBookVo();
		paramVo.setName("홍길동");
		paramVo.setBirthday("1994");
		paramVo.setPhoneNumber("1234");
		paramVo.setAddress("서울");
		
		//addressBook.insertAddressBook(paramVo);
		
	}
}
