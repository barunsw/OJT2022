package com.barunsw.ojt.jmlee.day04;

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
			
			AddressBookVo addressBookVo = new AddressBookVo("강감찬", "1975-09-04", Gender.MAN, "010-0000-0000", "서울");
			AddressBookVo addressBookVo2 = new AddressBookVo("이재민", "1995-09-15", Gender.MAN, "010-0000-0000", "대전");
			AddressBookVo addressBookVo3 = new AddressBookVo("정은원", "2000-04-06", Gender.MAN, "010-0000-0000", "인천");
			AddressBookVo addressBookVo4 = new AddressBookVo("이순신", "1950-02-06", Gender.MAN, "010-0000-0000", "경기");
			
//			addressBook.insertAddressBook(addressBookVo);  // 연락처 등록문
//			addressBook.insertAddressBook(addressBookVo2);
//			addressBook.insertAddressBook(addressBookVo3);
//			addressBook.insertAddressBook(addressBookVo4);
//			
//			AddressBookVo deleteTagetAddressBookVo = new AddressBookVo();
//			deleteTagetAddressBookVo.setSeqNum(3);
//			addressBook.deleteAddressBook(deleteTagetAddressBookVo);// 연락처 삭제문
			
			addressBookVo2.setSeqNum(2);
//			addressBookVo2.setName("홍길동");
//			addressBook.updateAddressBook(addressBookVo3);
//			addressBook.selectAddressBook(null);  // 연락처 조회문
			addressBook.deleteAddressBook(addressBookVo2);
			
	//		addressBookVo.setBirthday("2020-01-01"); // 연락처 수정
	//		addressBook.updateAddressBook(addressBookVo); // 연락처 업데이트
	//
			addressBook.selectAddressBook(null);
	//		LOGGER.debug("NEXT SEQ_NUM : " + (AddressBookVo.generateSeqNum()));
	//		addressBook.selectAddressBook(null);
			
			} 
			catch (Exception ex) {
				LOGGER.error(ex.getMessage(), ex);
		}		
		
	}
}