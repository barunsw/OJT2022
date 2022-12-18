package com.barunsw.ojt.jmlee.day05;

import java.security.KeyStore.ProtectionParameter;
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

public class FileAddressBookImpl {

	private List<AddressBookVo> addresBookList = new ArrayList<AddressBookVo>();
	
	private static Logger LOGGER = LoggerFactory.getLogger(FileAddressBookImpl.class);
	
	private static final String DB_URL = "jdbc:mysql://localhost:3306/ojt"; // DB 주소

	private static final String DB_USERNAME = "root";						// DB 사용자

	private static final String DB_PASSWORD = "java1234";					// DB PW
	
	
	public FileAddressBookImpl() throws Exception {
		Class.forName("org.mariadb.jdbc.Driver");
	}
	
	public List<AddressBookVo> selectAddressBookVos(AddressBookVo paramData){
		
		try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
				Statement stmt = conn.createStatement();) {
			
			ResultSet resultSet = stmt.executeQuery("SELECT * FROM tb_addressbook");
			while (resultSet.next()) {						// next()는 데이터를 처음부터 읽어오기 때문에 roof를 돌면서 한줄 씩 출력하게 하는 함수
				String seq		= resultSet.getString(1); 
				String name		= resultSet.getString(2);
				String birth	= resultSet.getString(3);
				String gender	= resultSet.getString(4);
				String phone	= resultSet.getString(5);
				String address	= resultSet.getString(6);
				
				LOGGER.debug(String.format("seq : %s, name : %s, gender : %s, birth : %s, phone : %s, address : %s", seq, name, gender, birth, phone, address ));
			}
			
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return addresBookList;
	}
	
	public int insertAddressBook(AddressBookVo paramData) throws Exception {
		String pstmtSql = String.format("INSERT INTO tb_addressbook(NAME, GENDER, BIRTH, PHONE, ADDRESS) "
				+ "VALUES (?, ?, ?, ?, ?)");
		
		try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
				PreparedStatement pstmt = conn.prepareStatement(pstmtSql);
				Statement stmt = conn.createStatement();) {
			
			//1 Statement 방식
			String insertSql = String.format("INSERT INTO tb_addressbook(NAME, GENDER, BIRTH, PHONE, ADDRESS) VALUES ('%s', '%s', '%s', '%s', '%s')", 
									paramData.getName(), paramData.getGender(), paramData.getBirthday(), paramData.getPhoneNumber(), paramData.getAddress());
			int result = stmt.executeUpdate(insertSql);
			LOGGER.debug(result + "개 작업 수행 완료.");
			
			// 2 PreparedStatement 방식
//			pstmt.setString(1, "김재민");
//			pstmt.setString(2, "");
//			pstmt.setString(3, "1995");
//			pstmt.setString(4, "2509");
//			pstmt.setString(5, "서울");

			int result2 = pstmt.executeUpdate();
			LOGGER.debug(result2 + "개 작업 수행 완료.");
			
		}
		return 0;
	}
	
	public int updateaAddressBook(AddressBookVo paramData) throws Exception {
		String pstmtSql = String.format("UPDATE tb_addressbook SET NAME=?, BIRTH=?, GENDER=?, PHONE=?, ADDRESS=?, WHERE SEQ=?");
		
		try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
				PreparedStatement pstmt = conn.prepareStatement(pstmtSql);
				Statement stmt = conn.createStatement();
				){
			
			String insertSql = String.format("UPDATE tb_addressbook SET NAME='%s', BIRTH='%s', GENDER='%s', PHONE='%s', ADDRESS='%s' WHERE SEQ=%d"
					, paramData.getName(), paramData.getBirthday(), paramData.getGender(), paramData.getPhoneNumber(), paramData.getAddress(), paramData.getSeqNum());
			
			int result = stmt.executeUpdate(insertSql);
			LOGGER.debug(result + "개 작업 수행 완료.");

			//pstmt 방식은 gender 업데이트 과정에서 enum을 처리할 때 문제로 업데이트가 쉽지않은거같음
//			pstmt.setString(1, paramData.getName());
//			pstmt.setString(2, paramData.getBirthday());
//			pstmt.setString(3, paramData.getPhoneNumber());
//			pstmt.setString(4, paramData.getAddress());
//			pstmt.setInt(5, paramData.getSeqNum());
			
//			int result2 = pstmt.executeUpdate();
//			LOGGER.debug("result2 : " + result2);
		}
		
		return 0;
	}
	
	public int deleteAddressBook(AddressBookVo paramData) throws Exception {
		String pstmtSql = String.format("DELETE FROM tb_addressbook WHERE SEQ=?");
		
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
