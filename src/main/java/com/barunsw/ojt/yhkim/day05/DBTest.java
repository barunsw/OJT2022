package com.barunsw.ojt.yhkim.day05;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.barunsw.ojt.constants.Gender;
import com.barunsw.ojt.intf.AddressBookInterface;
import com.barunsw.ojt.vo.AddressBookVo;

public class DBTest {
	private static Logger LOGGER = LoggerFactory.getLogger(DBTest.class);

	public static void main(String[] args) throws Exception {
		AddressBookInterface addressBook = new FileAddressBookImpl();
		
		addressBook.selectAddressBook(null);  // 연락처 조회문
		
		AddressBookVo paramVo = new AddressBookVo("홍길동", "2000", Gender.MAN, "12345", "제주");
		AddressBookVo paramVo2 = new AddressBookVo("홍미미", "2000", Gender.MAN, "12345", "제주");
		LOGGER.debug("paramVo 데이터의 seq : "+paramVo.getSeqNum());
		LOGGER.debug("paramVo2 데이터의 seq : "+paramVo2.getSeqNum());

//		paramVo.setName("홍길동");
//		paramVo.setBirthday("1994");
//		paramVo.setPhoneNumber("1234");
//		paramVo.setAddress("서울");
		
		addressBook.insertAddressBook(paramVo);
		addressBook.insertAddressBook(paramVo2);

		addressBook.selectAddressBook(null);  
		
		paramVo.setPhoneNumber("dddddd");
		addressBook.updateAddressBook(paramVo);
		
		//addressBook.deleteAddressBook(paramVo);
		
	}
}
