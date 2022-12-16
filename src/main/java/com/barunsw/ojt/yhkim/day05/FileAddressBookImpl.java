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

import com.barunsw.ojt.intf.AddressBookInterface;
import com.barunsw.ojt.vo.AddressBookVo;

public class FileAddressBookImpl implements AddressBookInterface {
	private List<AddressBookVo> addressBookList = new ArrayList<AddressBookVo>();

	private static Logger LOGGER = LoggerFactory.getLogger(FileAddressBookImpl.class);
	
	private final static String URL = "jdbc:mysql://localhost:3306/OJT?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC&useSSL=false";
	private final static String USER = "root";
	private final static String PASSWD = "1234";
	

	public FileAddressBookImpl() throws Exception {
		Class.forName("org.mariadb.jdbc.Driver");
	}
	
	@Override
	public List<AddressBookVo> selectAddressBook(AddressBookVo paramData) {
		/*
		 * Connection: 데이터베이스와 연결하는 객체
		 * DriverManager: JDBC 드라이버 세트 관리
		 * DriverManager.getConnection(연결문자열, DB_ID, DB_PW): Connection 객체 생성, Class.forName()메소em룰 통해 생성
		 * 연결문자열: jdbc:Driver종류://IP:port번호/DB명
		 * Statement: SQL 구문을 실행(전달)
		 * PreparedStatement: Statement 클래스 기능 향상
		 * 
		 */
		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWD);
			Statement stmt = conn.createStatement();) {
			
			//수행결과로 ResultSet 객체의 값을 반환, SELECT문을 수행할 때 사용
			ResultSet resultSet = stmt.executeQuery("SELECT * FROM TB_ADDRESS");
			while (resultSet.next()) {
				// //결과값 = rs.getString("column명-문자 type"); 또는 getInt("column명-정수 type");    

				String seq 		= resultSet.getString(1);
				String name 	= resultSet.getString(2);
				String birthday	= resultSet.getString(3);
				String gender	= resultSet.getString(4);
				String phone	= resultSet.getString(5);
				String address	= resultSet.getString(6);
				
				LOGGER.debug(String.format("seq:%s, name:%s, gender:%s, birthday:%s, phone:%s, address:%s", seq, name, gender, birthday, phone, address));
			}
			
			ResultSet resultSet2 = stmt.executeQuery("SELECT SEQ FROM TB_ADDRESS ORDER BY SEQ DESC LIMIT 1");
			while(resultSet2.next()) {
				String setSeq = resultSet2.getString(1);
				
				AddressBookVo.resetSeqNum(Integer.parseInt(setSeq)+1);
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
			
			//1
			String insertSql = String.format("INSERT INTO TB_ADDRESS(NAME, BIRTHDAY, GENDER, PHONENUMBER, ADDRESS) VALUES ('%s', '%s', '%s', '%s', '%s')", paramData.getName(), paramData.getBirthday(), paramData.getGender(), paramData.getPhoneNumber(), paramData.getAddress());
			int result = stmt.executeUpdate(insertSql);
			LOGGER.debug("result:" + result);
			
			// 2
//			pstmt.setString(1, "이윤정");
//			pstmt.setString(2, "1994");
//			pstmt.setString(3, "2564");
//			pstmt.setString(4, "서울");
//
//			int result2 = pstmt.executeUpdate();
//			LOGGER.debug("result2:" + result2);
			
		}
		return 0;
	}


	@Override
	public int updateAddressBook(AddressBookVo paramData) throws Exception {
		// 동일한 seqNum의 주소 정보를 addressBookList에 찾아 해당 index의 데이터를 치환한다.
		
		String pstmtSql = String.format("UPDATE TB_ADDRESS SET NAME=?, BIRTHDAY=?, GENDER=?, PHONENUMBER=?, ADDRESS=?, WHERE SEQ=?");

		
		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWD);
				PreparedStatement pstmt = conn.prepareStatement(pstmtSql);
				Statement stmt = conn.createStatement();) {
			
			//1
			String insertSql = String.format("UPDATE TB_ADDRESS SET NAME='%s', BIRTHDAY='%s', GENDER='%s', PHONENUMBER='%s', ADDRESS='%s' WHERE SEQ=%d"
					, paramData.getName(), paramData.getBirthday(), paramData.getGender(), paramData.getPhoneNumber(), paramData.getAddress(), paramData.getSeqNum());
			LOGGER.debug("수정할 데이터의 seq : "+paramData.getSeqNum());
			// 수행결과로 Int 타입의 값을 반환, SELECT 구문 제외하고 모두 사용
			int result = stmt.executeUpdate(insertSql);
			LOGGER.debug("result:" + result);
			
			// 2
//			pstmt.setString(1, paramData.getName());
//			pstmt.setString(2, paramData.getBirthday());
//			pstmt.setString(3, paramData.getPhoneNumber());
//			pstmt.setString(4, paramData.getAddress());
//			pstmt.setInt(5, paramData.getSeqNum());
//
//			int result2 = pstmt.executeUpdate();
//			LOGGER.debug("result2:" + result2);
			
		}
		return 0;

	}

	@Override
	public int deleteAddressBook(AddressBookVo paramData) throws Exception {
		// 동일한 seqNum의 주소 정보를 addressBookList에 찾아 해당 index의 데이터를 삭제한다.
		
		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWD);
				Statement stmt = conn.createStatement();) {
			
			LOGGER.debug("삭제할 데이터의 seq : "+paramData.getSeqNum());

			String insertSql = String.format("DELETE FROM TB_ADDRESS WHERE SEQ=%d", paramData.getSeqNum());

			int result = stmt.executeUpdate(insertSql);
			LOGGER.debug("result:" + result);

			
		}
		return 0;

	}

}
