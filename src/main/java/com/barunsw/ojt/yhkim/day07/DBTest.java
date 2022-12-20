package com.barunsw.ojt.yhkim.day07;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.barunsw.ojt.constants.Gender;
import com.barunsw.ojt.yhkim.day07.AddressBookDao;
import com.barunsw.ojt.yhkim.day07.AddressBook;

public class DBTest {
	private static Logger LOGGER = LoggerFactory.getLogger(DBTest.class);

	public static void main(String[] args) throws Exception {
		
		SqlSessionFactory sqlSessionFactory = SqlSessionFactoryManager.getSqlSessionFactory();
		
		try (SqlSession session = sqlSessionFactory.openSession()){
			AddressBookDao mapper = session.getMapper(AddressBookDao.class);
			
			List<AddressBook> addressBookList = mapper.selectAddressBook(null);
			for (AddressBook b : addressBookList) {
				LOGGER.debug(b.toString());
			}
			
			AddressBook insertData = new AddressBook();
			insertData.setName("김하나");
			insertData.setBirthday("2000");
			insertData.setGender(Gender.MAN);
			insertData.setPhoneNumber("010-1344-1234");
			insertData.setAddress("강릉");
			
			int insertResult = mapper.insertAddressBook(insertData);
			
			addressBookList = mapper.selectAddressBook(null);
			for (AddressBook b : addressBookList) {
				LOGGER.debug(b.toString());
			}
			
			AddressBook updateData = new AddressBook();
			updateData.setSeq(126);
			updateData.setName("abc");
			updateData.setBirthday("2000");
			updateData.setGender(Gender.WOMAN);
			updateData.setPhoneNumber("010-1344-1234");
			updateData.setAddress("강릉");
			
			int updateResult = mapper.updateAddressBook(updateData);
			
			addressBookList = mapper.selectAddressBook(null);
			for (AddressBook b : addressBookList) {
				LOGGER.debug(b.toString());
			}
			
			AddressBook deleteData = new AddressBook();
			deleteData.setSeq(135);
			
			int deleteResult = mapper.deleteAddressBook(deleteData);
			
			addressBookList = mapper.selectAddressBook(null);
			for (AddressBook b : addressBookList) {
				LOGGER.debug(b.toString());
			}

			AddressBook findData = new AddressBook();
			findData.setName("나");
			
			addressBookList = mapper.selectAddressByName(findData);
			for (AddressBook b : addressBookList) {
				LOGGER.debug(b.toString());
			}
			
			AddressBook findData2 = new AddressBook();
			findData2.setSeq(137);
			
			LOGGER.debug(mapper.selectAddressBySeq(findData2).toString());
			
			session.commit();
			
		}
		catch(Exception e) {
			LOGGER.error(e.getMessage());
		}
	}
}
