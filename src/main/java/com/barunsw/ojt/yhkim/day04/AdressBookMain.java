package com.barunsw.ojt.yhkim.day04;

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
			
			AddressBookVo addressBookVo = new AddressBookVo("홍길일", "1975-09-04", Gender.MAN, "010-0000-0000", "서울");
			AddressBookVo addressBookVo2 = new AddressBookVo("홍길이", "1999-09-04", Gender.MAN, "010-0000-0000", "서울");
			AddressBookVo addressBookVo3 = new AddressBookVo("홍길삼", "1975-09-04", Gender.MAN, "010-0000-0000", "서울");
			AddressBookVo addressBookVo4 = new AddressBookVo("홍길사", "1975-09-05", Gender.MAN, "010-0000-0000", "서울");

			// 회원 등록
			addressBook.insertAddressBook(addressBookVo);
			addressBook.insertAddressBook(addressBookVo2);
			addressBook.insertAddressBook(addressBookVo3);
			addressBook.insertAddressBook(addressBookVo4);


			List<AddressBookVo> addressBookList = addressBook.selectAddressBook(new AddressBookVo());
			for (AddressBookVo oneAddressBook : addressBookList) {
				LOGGER.debug(oneAddressBook.toString());
			}
			
//			// 회원 생일 수정
//			addressBookVo.setBirthday("2020-12-12");
//			// 수정한 정보 파일 저장
//			addressBook.updateAddressBook(addressBookVo);
//			
//			for (AddressBookVo oneAddressBook : addressBookList) {
//				LOGGER.debug(oneAddressBook.toString());
//			}
//			
//			// 회원 삭제
//			addressBook.deleteAddressBook(addressBookVo);
//			
//			for (AddressBookVo oneAddressBook : addressBookList) {
//				LOGGER.debug(oneAddressBook.toString());
//			}
//			
//			LOGGER.debug("NEXT SEQ_NUM : " + (AddressBookVo.generateSeqNum()));
//			
//			addressBook.selectAddressBook(new AddressBookVo());
			
		}
		catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
			
		}	
	}
}
