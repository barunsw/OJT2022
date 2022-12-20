package com.barunsw.ojt.jmlee.day04;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.barunsw.ojt.intf.AddressBookInterface;
import com.barunsw.ojt.vo.AddressBookVo;

public class FileAddressBookImpl implements AddressBookInterface {
	private List<AddressBookVo> addressBookList = new ArrayList<AddressBookVo>();

	private static Logger LOGGER = LoggerFactory.getLogger(FileAddressBookImpl.class);
	
	private String filePath = ""; // static으로 filepath를 지정해줘서 메모리를 아낄수있게함 
	
	public FileAddressBookImpl(String filePath) throws Exception {
		this.filePath = filePath;
		
		File file = new File(filePath); // 파일이 존재하지 않으면 새로 생성 후 진행
		boolean isExists = file.exists();
		
		if (!isExists) {
			LOGGER.debug("파일생성");
			file.createNewFile();
		}
		else {
			LOGGER.debug("파일있음" );
		}
		
		loadAddressBook();
	}

	private void loadAddressBook() {
		// 파일로부터 Address 정보를 가져와서 addressBookList에 담는다.(ObjectInputStream)
		// AddressBookVo의 SEQ_NUM를 가장 큰 seqNum + 1로 reset한다.
		int lastSeqNum = 0;
		System.out.println(new File(filePath).getAbsolutePath());
		try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(filePath))) {
			Object o = null;
			while ((o = inputStream.readObject()) != null) {
				if(o instanceof AddressBookVo) {
				AddressBookVo addressBook = (AddressBookVo)o;
				addressBookList.add(addressBook);
				}
			}
			lastSeqNum = addressBookList.size() -1;
			addressBookList.get(lastSeqNum).getSeqNum();
		}
		catch (EOFException eofe) {}
		catch (Exception ex) {
			LOGGER.error(ex.getMessage(),ex);
		}
		if (addressBookList.size() > 0) {
			AddressBookVo.resetSeqNum(lastSeqNum +1) ;
		}
		
	}
	
	private void saveAddressBook() throws Exception {
		// addressBookList의 데이터를 파일에 저장한다.(ObjectOutputStream)
		try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(filePath))) {
			System.out.println("addressBookList:" + addressBookList.size());
			for (AddressBookVo addressBook : addressBookList) {
				outputStream.writeObject(addressBook);
			}
			
		}
		catch (Exception ex) {
			LOGGER.error(ex.getMessage(),ex);
		} 
		finally {
			System.out.println("addressBookList-size:"+addressBookList.size());
		}
	}
	
	@Override
	public List<AddressBookVo> selectAddressBook(AddressBookVo paramData) {
		for (AddressBookVo oneAddressBook : addressBookList) {
			LOGGER.debug(oneAddressBook.toString());
			}
		return addressBookList;
	}

	@Override
	public int insertAddressBook(AddressBookVo paramData) throws Exception {
		// TODO Auto-generated method stub
		LOGGER.debug(paramData.getSeqNum()+"");
		addressBookList.add(paramData);
		LOGGER.debug(paramData.getName()+" 정보등록 완료");
		saveAddressBook();
		
		return 0;
	}

	@Override
	public int updateAddressBook(AddressBookVo paramData) throws Exception {
		// 동일한 seqNum의 주소 정보를 addressBookList에 찾아 해당 index의 데이터를 치환한다.

		for ( int i = 0; i < addressBookList.size(); i++ ) {
			if ( paramData.getSeqNum() == addressBookList.get(i).getSeqNum() ) {
				addressBookList.set(i, paramData);				
			}
			else {
				LOGGER.debug("잘못된 값입니다.");
			}
		} 
		LOGGER.debug(paramData.getName() + " 님의 정보수정 완료");
		saveAddressBook();
		return 0;
	}

	@Override
	public int deleteAddressBook(AddressBookVo paramData) throws Exception {
		// 동일한 seqNum의 주소 정보를 addressBookList에 찾아 해당 index의 데이터를 삭제한다.
		//LOGGER.debug(addressBookList.indexOf(paramData)+" index of");
		//addressBookList.remove(addressBookList.set(addressBookList.indexOf(paramData), paramData));
		
		for ( int i = 0; i < addressBookList.size(); i++ ) {
			System.out.println("paramData.getSeqNum():"+paramData.getSeqNum());
			System.out.println("addressBookList.get(i).getSeqNum():"+addressBookList.get(i).getSeqNum());
			if ( paramData.getSeqNum() == addressBookList.get(i).getSeqNum() ) {
				addressBookList.remove(i);
			}
		}
		LOGGER.debug(paramData.getSeqNum() + "");
		LOGGER.debug("삭제된 seq : " + paramData.getSeqNum() + "" + paramData.getName() + " 님의 정보삭제 완료");
		saveAddressBook();
		return 0;
	}

}
