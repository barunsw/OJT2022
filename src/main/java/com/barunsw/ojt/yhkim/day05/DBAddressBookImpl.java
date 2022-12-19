package com.barunsw.ojt.yhkim.day05;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
import com.barunsw.ojt.intf.AddressBookInterface;
import com.barunsw.ojt.vo.AddressBookVo;

public class DBAddressBookImpl implements AddressBookInterface {

	private static Logger LOGGER = LoggerFactory.getLogger(DBAddressBookImpl.class);
	
	private final static String URL = "jdbc:mysql://localhost:3306/OJT?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC&useSSL=false";
	private final static String USER = "root";
	private final static String PASSWD = "1234";

	/*
	 * Connection: 데이터베이스와 연결하는 객체
	 * DriverManager: JDBC 드라이버 세트 관리
	 * DriverManager.getConnection(연결문자열, DB_ID, DB_PW): Connection 객체 생성, Class.forName()메소드를 통해 생성
	 * 연결문자열: jdbc:Driver종류://IP:port번호/DB명
	 * Statement: SQL 구문을 실행(전달)
	 * PreparedStatement: Statement 클래스 기능 향상
	 * 
	 */
	
	public DBAddressBookImpl() throws Exception {
		Class.forName("org.mariadb.jdbc.Driver");
		
		// = new org.mariadb.jdbc.Driver();
	}
	
	@Override
	public List<AddressBookVo> selectAddressBook(AddressBookVo paramData) {

		List<AddressBookVo> addressBookList = new ArrayList<>();

		String pstmtSql = String.format("SELECT * FROM TB_ADDRESS");
		
		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWD);
				PreparedStatement pstmt = conn.prepareStatement(pstmtSql);
				Statement stmt = conn.createStatement();) {
			// pstmt
			// ResultSet resultSet = stmt.executeQuery(pstmtSql);
			// stmt
			ResultSet resultSet = stmt.executeQuery("SELECT * FROM TB_ADDRESS");
			while (resultSet.next()) {

				int seq 		= resultSet.getInt(1);
				String name 	= resultSet.getString(2);
				String birthday	= resultSet.getString(3);
				String _gender	= resultSet.getString(4);
				String phone	= resultSet.getString(5);
				String address	= resultSet.getString(6);
				
				Gender gender 	= Gender.getGender(_gender);
				
				AddressBookVo addressBookVo = new AddressBookVo();
				addressBookVo.setSeqNum(seq);
				addressBookVo.setName(name);
				addressBookVo.setBirthday(birthday);
				addressBookVo.setGender(gender);
				addressBookVo.setPhoneNumber(phone);
				addressBookVo.setAddress(address);
				
				addressBookList.add(addressBookVo);
				
				// LOGGER.debug(String.format("seq:%d, name:%s, gender:%s, birthday:%s, phone:%s, address:%s", seq, name, gender, birthday, phone, address));
			}	
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return addressBookList;
	}

	@Override
	public int insertAddressBook(AddressBookVo paramData) throws Exception {
		
		String pstmtSql = String.format("INSERT INTO TB_ADDRESS(NAME, BIRTHDAY, GENDER, PHONENUMBER, ADDRESS) "
				+ "VALUES (?, ?, ?, ?, ?)");
		
		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWD);
				PreparedStatement pstmt = conn.prepareStatement(pstmtSql);
				Statement stmt = conn.createStatement();) {
			
			// stmt
			String insertSql = String.format("INSERT INTO TB_ADDRESS(NAME, BIRTHDAY, GENDER, PHONENUMBER, ADDRESS) VALUES ('%s', '%s', '%s', '%s', '%s')"
					, paramData.getName(), paramData.getBirthday(), paramData.getGender(), paramData.getPhoneNumber(), paramData.getAddress());
			int result = stmt.executeUpdate(insertSql);
			LOGGER.debug("result:" + result);
			
			// pstmt			
//			pstmt.setString(1, paramData.getName());
//			pstmt.setString(2, paramData.getBirthday());
//			pstmt.setString(3, paramData.getGender().toString());
//			pstmt.setString(4, paramData.getPhoneNumber());
//			pstmt.setString(5, paramData.getAddress());
//			
//			int result2 = pstmt.executeUpdate();
//			LOGGER.debug("result2:" + result2);
			
		}
		
		return 0;
	}
  

	@Override
	public int updateAddressBook(AddressBookVo paramData) throws Exception {

		String pstmtSql = String.format("UPDATE TB_ADDRESS SET NAME = ?, BIRTHDAY = ?, GENDER = ?, PHONENUMBER = ?, ADDRESS = ? WHERE SEQ = ?");
		
		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWD);
				PreparedStatement pstmt = conn.prepareStatement(pstmtSql);
				Statement stmt = conn.createStatement();) {
			
			// stmt
			String updateSql = String.format("UPDATE TB_ADDRESS SET NAME='%s', BIRTHDAY='%s', GENDER='%s', PHONENUMBER='%s', ADDRESS='%s' WHERE SEQ=%d"
					, paramData.getName(), paramData.getBirthday(), paramData.getGender(), paramData.getPhoneNumber(), paramData.getAddress(), paramData.getSeqNum());			
			int result = stmt.executeUpdate(updateSql);
			LOGGER.debug("result:" + result);
			
			// pstmt
//			pstmt.setString(1, paramData.getName());
//			pstmt.setString(2, paramData.getBirthday());
//			pstmt.setString(3, paramData.getGender().toString());
//			pstmt.setString(4, paramData.getPhoneNumber());
//			pstmt.setString(5, paramData.getAddress());
//			pstmt.setInt(6, paramData.getSeqNum());
//
//			int result2 = pstmt.executeUpdate();
//			LOGGER.debug("result2:" + result2);

		}
		
		return 0;
	}

	@Override
	public int deleteAddressBook(AddressBookVo paramData) throws Exception {

		String pstmtSql = String.format("DELETE FROM TB_ADDRESS WHERE SEQ = ?");

		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWD);
				PreparedStatement pstmt = conn.prepareStatement(pstmtSql);
				Statement stmt = conn.createStatement();) {
			
			// stmt
			String deleteSql = String.format("DELETE FROM TB_ADDRESS WHERE SEQ=%d", paramData.getSeqNum());
			int result = stmt.executeUpdate(deleteSql);
			LOGGER.debug("result: " + result);
			
			// pstmt
//			pstmt.setInt(1, paramData.getSeqNum());
//			int result2 = pstmt.executeUpdate();
//			LOGGER.debug("result2: " + result2);

		}
		
		return 0;
	}

}
