package com.barunsw.ojt.day05;

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
	private final static String PASSWD = "real3817";
	

	public DBAddressBookImpl() throws Exception {
		Class.forName("org.mariadb.jdbc.Driver");
		
		// = new org.mariadb.jdbc.Driver();
	}
	
	@Override
	public List<AddressBookVo> selectAddressBook(AddressBookVo paramData) {
		/*
		 * Connection: 데이터베이스와 연결하는 객체
		 * DriverManager: JDBC 드라이버 세트 관리
		 * DriverManager.getConnection(연결문자열, DB_ID, DB_PW): Connection 객체 생성, Class.forName()메소두룰 통해 생성
		 * 연결문자열: jdbc:Driver종류://IP:port번호/DB명
		 * Statement: SQL 구문을 실행(전달)
		 * PreparedStatement: Statement 클래스 기능 향상
		 * 
		 */
		List<AddressBookVo> addressList = new ArrayList<>();
		
		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWD);
			Statement stmt = conn.createStatement();) {
			
			ResultSet resultSet = stmt.executeQuery("SELECT * FROM TB_PERSON");
			while (resultSet.next()) {
				int seq 		= resultSet.getInt(1);
				String name 	= resultSet.getString(2);
				String _gender	= resultSet.getString(3);
				String birth	= resultSet.getString(4);
				String phone	= resultSet.getString(5);
				String address	= resultSet.getString(6);
				
				Gender gender 	= Gender.getGender(_gender);
				
				AddressBookVo addressBookVo = new AddressBookVo();
				addressBookVo.setSeqNum(seq);
				addressBookVo.setGender(gender);
				
				addressList.add(addressBookVo);
				
				LOGGER.debug(String.format("seq:%s, name:%s, gender:%s, birth:%s, phone:%s, address:%s", seq, name, gender, birth, phone, address));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		for (AddressBookVo oneAddressBook : addressList) {
			LOGGER.debug(oneAddressBook.toString());
		}
		
		return addressList;
	}

	@Override
	public int insertAddressBook(AddressBookVo paramData) throws Exception {
		// TODO Auto-generated method stub
		String pstmtSql = String.format("INSERT INTO TB_PERSON(NAME, BIRTH, PHONE, ADDRESS) "
				+ "VALUES (?, ?, ?, ?)");
		
		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWD);
				PreparedStatement pstmt = conn.prepareStatement(pstmtSql);
				Statement stmt = conn.createStatement();) {
			
			//1
			String insertSql = String.format("INSERT INTO TB_PERSON(NAME, BIRTH, PHONE, ADDRESS) VALUES ('%s', '%s', '%s', '%s')", paramData.getName(), paramData.getBirthday(), paramData.getPhoneNumber(), paramData.getAddress());
			int result = stmt.executeUpdate(insertSql);
			LOGGER.debug("result:" + result);
			
			// 2
			pstmt.setString(1, "이윤정");
			pstmt.setString(2, "1994");
			pstmt.setString(3, "2564");
			pstmt.setString(4, "서울");

			int result2 = pstmt.executeUpdate();
			LOGGER.debug("result2:" + result2);
			
		}
		
		return 0;
	}

	@Override
	public int updateAddressBook(AddressBookVo paramData) throws Exception {
		return 0;
	}

	@Override
	public int deleteAddressBook(AddressBookVo paramData) throws Exception {
		return 0;
	}

}
