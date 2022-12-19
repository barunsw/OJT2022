package com.barunsw.ojt.jmlee.day06;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.barunsw.ojt.constants.Gender;
import com.barunsw.ojt.vo.AddressBookVo;

public class DBAddressBookImpl {
	
	private static Logger LOGGER = LoggerFactory.getLogger(DBAddressBookImpl.class);
	
	private static final String DB_URL = "jdbc:mysql://localhost:3306/ojt"; // DB 주소
	private static final String DB_USERNAME = "root";						// DB 사용자
	private static final String DB_PASSWORD = "1234";					// DB PW
	
	
	public DBAddressBookImpl() throws Exception {
		Class.forName("org.mariadb.jdbc.Driver");
	}
	
	public List<AddressBookVo> selectAddressBookVos(AddressBookVo paramData){
		
		List<AddressBookVo> addressBookList = new ArrayList<AddressBookVo>();
		
		try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
				Statement stmt = conn.createStatement();) {
			
			ResultSet resultSet = stmt.executeQuery("SELECT * FROM tb_addressbook");
			while (resultSet.next()) {						// next()는 데이터를 처음부터 읽어오기 때문에 roof를 돌면서 한줄 씩 출력하게 하는 함수
				int seq		= resultSet.getInt(1); 
				String name		= resultSet.getString(2);
				String birth	= resultSet.getString(3);
				String _gender	= resultSet.getString(4);
				String phone	= resultSet.getString(5);
				String address	= resultSet.getString(6);
				
				Gender gender = Gender.getGender(_gender);
				
				// DB에서 가져온 데이터를 Vo에 담는다
				AddressBookVo addressBookVo = new AddressBookVo();
				addressBookVo.setSeqNum(seq);
				addressBookVo.setName(name);
				addressBookVo.setBirthday(birth);
				addressBookVo.setGender(gender);
				addressBookVo.setPhoneNumber(phone);
				addressBookVo.setAddress(address);
				// List에 한번에 Vo데이터를 담아준다.
				addressBookList.add(addressBookVo);
				
				LOGGER.debug(String.format("seq : %s, name : %s, gender : %s, birth : %s, phone : %s, address : %s", seq, name, gender, birth, phone, address ));
			}
			
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		
		for (AddressBookVo AddressBook : addressBookList) {
			LOGGER.debug(AddressBook.toString());
		}
		
		return addressBookList;
	}
	
	public int insertAddressBook(AddressBookVo paramData) throws Exception {
		String pstmtSql = "INSERT INTO tb_addressbook(NAME, GENDER, BIRTH, PHONE, ADDRESS) "
				+ "VALUES (?, ?, ?, ?, ?)";
		
		try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
				PreparedStatement pstmt = conn.prepareStatement(pstmtSql);
				//Statement stmt = conn.createStatement();
				){
			
			//1 Statement 방식
//			String insertSql = String.format("INSERT INTO tb_addressbook(NAME, GENDER, BIRTH, PHONE, ADDRESS) VALUES ('%s', '%s', '%s', '%s', '%s')", 
//									paramData.getName(), paramData.getGender(), paramData.getBirthday(), paramData.getPhoneNumber(), paramData.getAddress());
//			int result = stmt.executeUpdate(insertSql);
//			LOGGER.debug(result + "개 작업 수행 완료.");
//			
			// 2 PreparedStatement 방식
			pstmt.setString(1, paramData.getName());
			pstmt.setString(2, paramData.getGender().toString());
			pstmt.setString(3, paramData.getBirthday());
			pstmt.setString(4, paramData.getPhoneNumber());
			pstmt.setString(5, paramData.getAddress());

			int result2 = pstmt.executeUpdate();
			LOGGER.debug(result2 + "개 작업 수행 완료.");
			
		}
		return 0;
	}
	
	public int updateaAddressBook(AddressBookVo paramData) throws Exception {
		String pstmtSql = "UPDATE tb_addressbook SET NAME=?, BIRTH=?, GENDER=?, PHONE=?, ADDRESS=? WHERE SEQNUM=?";
		
		try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
				PreparedStatement pstmt = conn.prepareStatement(pstmtSql);
				//Statement stmt = conn.createStatement();
				){
			
//			String UpdateSql = String.format("UPDATE tb_addressbook SET NAME='%s', BIRTH='%s', GENDER='%s', PHONE='%s', ADDRESS='%s' WHERE SEQNUM=%d"
//					, paramData.getName(), paramData.getBirthday(), paramData.getGender(), paramData.getPhoneNumber(), paramData.getAddress(), paramData.getSeqNum());
//			
//			int result = stmt.executeUpdate(UpdateSql);
//			LOGGER.debug(result + "개 작업 수행 완료.");

			
			pstmt.setString(1, paramData.getName());
			pstmt.setString(2, paramData.getBirthday());
			pstmt.setString(3, paramData.getGender().toString());
			pstmt.setString(4, paramData.getPhoneNumber());
			pstmt.setString(5, paramData.getAddress());
			pstmt.setInt(6, paramData.getSeqNum());
			
			int result2 = pstmt.executeUpdate();
			LOGGER.debug(result2 + "개 작업 수행 완료.");

		}
		
		return 0;
	}
	
	public int deleteAddressBook(AddressBookVo paramData) throws Exception {
		String pstmtSql = "DELETE FROM tb_addressbook WHERE SEQNUM=?";
		
		try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
				PreparedStatement pstmt = conn.prepareStatement(pstmtSql);) {
			
			pstmt.setInt(1, paramData.getSeqNum()); // 삭제할 seq 불러오기
			
			// 수행한 작업 갯수 출력하는 구문
			int result = pstmt.executeUpdate();
			LOGGER.debug(result + "개 작업 수행 완료.");
		}
		return 0;
	}
	
	
}
