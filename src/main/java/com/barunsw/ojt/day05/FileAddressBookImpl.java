package com.barunsw.ojt.day05;

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
	
	private static String FILE_PATH = ""; // static으로 filepath를 지정해줘서 메모리를 아낄수있게함 
	
	private final static String URL = "jdbc:mysql://localhost:3306/OJT?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC&useSSL=false";
	private final static String USER = "root";
	private final static String PASSWD = "real3817";
	
	private void resetPath(String filePath) {
		FILE_PATH = filePath;
	}
	
	public FileAddressBookImpl(String filePath) throws Exception {
		
		File file = new File(filePath); // 파일이 존재하지 않으면 새로 생성 후 진행
		boolean isExists = file.exists();
		
		if (!isExists) {
			file.createNewFile();
		}
		
		resetPath(filePath);
		loadAddressBook();
	}

	public FileAddressBookImpl() throws Exception {
		Class.forName("org.mariadb.jdbc.Driver");
		// TODO Auto-generated constructor stub
	}

	private void loadAddressBook() {
		// 파일로부터 Address 정보를 가져와서 addressBookList에 담는다.(ObjectInputStream)
		// AddressBookVo의 SEQ_NUM를 가장 큰 seqNum + 1로 reset한다.
		try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(FILE_PATH ))) {
			Object o = null;
			while ((o = inputStream.readObject()) != null) {
				if(o instanceof AddressBookVo) {
				AddressBookVo addressBook = (AddressBookVo)o;
				addressBookList.add(addressBook);
				}
			}
			
		}
		catch (EOFException eof) {}
		catch (Exception ex) {
			LOGGER.error(ex.getMessage(),ex);
		}
	
	}
	
	private void saveAddressBook() throws Exception {
		// addressBookList의 데이터를 파일에 저장한다.(ObjectOutputStream)
		AddressBookVo.resetSeqNum(1);
		
		try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
			for (AddressBookVo addressBook : addressBookList) {
				addressBook.setSeqNum(AddressBookVo.generateSeqNum());
				outputStream.writeObject(addressBook);
			}
		}
		catch (Exception ex) {
			LOGGER.error(ex.getMessage(),ex);
		}
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
		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWD);
			Statement stmt = conn.createStatement();) {
			
			ResultSet resultSet = stmt.executeQuery("SELECT * FROM TB_PERSON");
			while (resultSet.next()) {
				String seq 		= resultSet.getString(1);
				String name 	= resultSet.getString(2);
				String gender	= resultSet.getString(3);
				String birth	= resultSet.getString(4);
				String phone	= resultSet.getString(5);
				String address	= resultSet.getString(6);
				
				LOGGER.debug(String.format("seq:%s, name:%s, gender:%s, birth:%s, phone:%s, address:%s", seq, name, gender, birth, phone, address));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
//		for (AddressBookVo oneAddressBook : addressBookList) {
//			LOGGER.debug(oneAddressBook.toString());
//			}
		return addressBookList;
	}

	@Override
	public int insertAddressBook(AddressBookVo paramData) throws Exception {
		// TODO Auto-generated method stub
//		addressBookList.add(paramData);
//		LOGGER.debug(paramData.getName()+" 정보등록 완료");
//		saveAddressBook();
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
		// 동일한 seqNum의 주소 정보를 addressBookList에 찾아 해당 index의 데이터를 치환한다.
		addressBookList.set(paramData.getSeqNum()-1, paramData);
		LOGGER.debug(paramData.getName() + " 님의 정보수정 완료");
		saveAddressBook();
		return 0;
	}

	@Override
	public int deleteAddressBook(AddressBookVo paramData) throws Exception {
		// 동일한 seqNum의 주소 정보를 addressBookList에 찾아 해당 index의 데이터를 삭제한다.

		addressBookList.remove(paramData.getSeqNum()-1);
		LOGGER.debug(paramData.getName() + " 님의 정보삭제 완료");
		saveAddressBook();
		return 0;
	}

}
