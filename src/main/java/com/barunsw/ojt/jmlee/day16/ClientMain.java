package com.barunsw.ojt.jmlee.day16;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.barunsw.ojt.constants.Gender;
import com.barunsw.ojt.vo.AddressBookVo;

public class ClientMain {
	private static final Logger LOGGER = LogManager.getLogger(ClientMain.class);
	
	public static void main(String[] args) {
		try {
			BookInterface addressBookIf = 
					new SocketBookImpl("localhost", ServerMain.PORT);
			
			// SELECT
//			List<AddressBookVo> addressBookList = addressBookIf.selectAddressList(new AddressBookVo());
//			for (AddressBookVo oneAddressVo : addressBookList) {
//				LOGGER.debug(oneAddressVo.toString());
//			}
			
			AddressBookVo addressbookVo = new AddressBookVo();
			
			//SELECT ONE
//			addressbookVo.setSeqNum(1);
//			addressBookIf.selectOneAddress(addressbookVo);
			
			// SELECT
			addressBookIf.selectAddressList(addressbookVo);
			
			// INSERT
//			addressbookVo.setName("가나다");
//			addressbookVo.setBirthday("2023");
//			addressbookVo.setGender(Gender.MAN);
//			addressbookVo.setPhoneNumber("0909");
//			addressbookVo.setAddress("관악");
//			LOGGER.debug("클라이언트 Insert 호출");
//			addressBookIf.insertAddress(addressbookVo);
			
			// UPDATE
//			addressbookVo.setSeqNum(37);
//			addressbookVo.setName("강감찬");
//			addressbookVo.setBirthday("1523");
//			addressbookVo.setGender(Gender.WOMAN);
//			addressbookVo.setPhoneNumber("0909");
//			addressbookVo.setAddress("관악");
//			addressBookIf.updateAddress(addressbookVo);
			// DELETE
//			addressbookVo.setSeqNum(42);
//			addressBookIf.deleteAddress(addressbookVo);
			
//			Thread.sleep(10_000);
			
			
		} 
		catch (Exception e) {
			LOGGER.error(e.getMessage()	, e);
		}
	}
}
