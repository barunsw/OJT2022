package com.barunsw.ojt.jmlee.day07;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.barunsw.ojt.constants.Gender;
import com.barunsw.ojt.intf.AddressBookInterface;
import com.barunsw.ojt.vo.AddressBookVo;

public class DBTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(DBTest.class);
	
	private static final SqlSessionFactory sqlSessionFactory = SqlSessionFactoryManager.getSqlSessionFactory();
																// ↑ ﻿모든 db의 쿼리 작업을 관리해주는역할
	public static void main(String[] args) {

		AddressBookInterface addressBook = new MybatisAddressBookImpl();
		
		//﻿mybatis에서는 기존의 커넥션 방식보다 단계가 간편화된다
		try (SqlSession session = sqlSessionFactory.openSession()){
// 			select
			
			addressBook.selectAddressBook(null);
			
//			insert		
//			AddressBookVo insert = new AddressBookVo();
//			insert.setName("김종국");
//			insert.setBirthday("1999");
//			insert.setGender(Gender.MAN);
//			insert.setPhoneNumber("5999");
//			insert.setAddress("평양");
//			
//			addressBook.insertAddressBook(insert);
			
			
//			Update
//			AddressBookVo update = new AddressBookVo();
//			update.setSeqNum(3);
//			update.setName("강호동");
//			addressBook.updateAddressBook(update);
			
//			Delete
//			AddressBookVo delete = new AddressBookVo();
//			delete.setSeqNum(4);
//			addressBook.deleteAddressBook(delete);
			
			
			
		
		}
		catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
		}
		
	}

}
