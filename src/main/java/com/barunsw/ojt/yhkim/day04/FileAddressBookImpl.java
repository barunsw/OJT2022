package com.barunsw.ojt.yhkim.day04;

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
	private static Logger LOGGER = LoggerFactory.getLogger(FileAddressBookImpl.class);

	private List<AddressBookVo> addressBookList = new ArrayList<>();
	
	private String filePath = "";
	
	public FileAddressBookImpl(String filePath) throws Exception {
		this.filePath = filePath;
		
        // 파일 존재 여부 확인하고 없으면 새로운 파일 생성
		File file = new File(filePath);
		boolean isExists = file.exists();
		if (!isExists) {
			file.createNewFile();
		}
		
		loadAddressBook();
	}
	
	private void loadAddressBook() {
		// 파일로부터 Address 정보를 가져와서 addressBookList에 담는다.(ObjectInputStream)
		// AddressBookVo의 SEQ_NUM를 가장 큰 seqNum + 1로 reset한다.

		try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(filePath))) {
			Object o = null;	
			
			
			while ((o = inputStream.readObject()) != null) {
				if (o instanceof AddressBookVo) {
					AddressBookVo oneAddressBook = (AddressBookVo)o;
					
					addressBookList.add(oneAddressBook);
				}
			}
		}
		catch (EOFException eofe) {}
		catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
		}	
		
		if (addressBookList.size() > 0) {
			AddressBookVo.resetSeqNum(addressBookList.get(addressBookList.size()-1).getSeqNum()+1);			
		}
	}
	
	private void saveAddressBook() {
		// addressBookList의 데이터를 파일에 저장한다.(ObjectOutputStream)
		
		try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(filePath))) {
			for (AddressBookVo oneAddressBook : addressBookList) {
				outputStream.writeObject(oneAddressBook);
			}
		}
		catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
		}
		
	    LOGGER.debug("addressBookList의 데이터를 파일에 저장했습니다");
	}
	
	@Override
	public List<AddressBookVo> selectAddressBook(AddressBookVo paramData) {
		// addressBookList의 리스트를 반환한다.
//		for (AddressBookVo oneAddressBook : addressBookList) {
//			LOGGER.debug(oneAddressBook.toString());
//		}

		return addressBookList;
	}

	@Override
	public int insertAddressBook(AddressBookVo paramData) throws Exception {
		// addressBookList에 정보를 등록해 파일에 저장한다.
		addressBookList.add(paramData);
		LOGGER.debug(paramData.getName()+"님의 정보를 등록했습니다");
		    
		saveAddressBook();
		return 0;
	}

	@Override
	public int updateAddressBook(AddressBookVo paramData) throws Exception {
		// 동일한 seqNum의 주소 정보를 addressBookList에 찾아 해당 index의 데이터를 치환한다.

//		for (AddressBookVo b : addressBookList) {
//			if (b.getSeqNum() == paramData.getSeqNum()) {
//				
//				b.setName(paramData.getName());
//				b.setBirthday(paramData.getBirthday());
//				b.setGender(paramData.getGender());
//				b.setPhoneNumber(paramData.getPhoneNumber());
//				b.setAddress(paramData.getAddress());
//				
//				// b = paramData;
//				
//				break;
//			}
//		}
		
		for (int i = 0; i < addressBookList.size(); i++) {
			if (addressBookList.get(i).getSeqNum() == paramData.getSeqNum()) {
				addressBookList.set(i, paramData);
				
				break;
			}
		}
		
	    LOGGER.debug(paramData.getName()+"님의 정보를 수정했습니다");

		saveAddressBook();
		return 0;
	}

	@Override
	public int deleteAddressBook(AddressBookVo paramData) throws Exception {
		// 동일한 seqNum의 주소 정보를 addressBookList에 찾아 해당 index의 데이터를 삭제한다.

//		for (AddressBookVo b : addressBookList) {
//			if (b.getSeqNum() == paramData.getSeqNum()) {
//				addressBookList.remove(b);
//				
//				break;
//			}
//		}
		
		for (int i = 0; i < addressBookList.size(); i++) {
			if (addressBookList.get(i).getSeqNum() == paramData.getSeqNum()) {
				addressBookList.remove(i);
				
				break;
			}
		}
		
	    LOGGER.debug(paramData.getName()+"님의 정보를 삭제했습니다");
		
		saveAddressBook();
		return 0;
	}

}
