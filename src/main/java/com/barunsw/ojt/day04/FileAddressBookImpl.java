package com.barunsw.ojt.day04;

import java.util.ArrayList;
import java.util.List;

import com.barunsw.ojt.intf.AddressBookInterface;
import com.barunsw.ojt.vo.AddressBookVo;

public class FileAddressBookImpl implements AddressBookInterface {
	private List<AddressBookVo> addressBookList = new ArrayList<>();
	
	public FileAddressBookImpl(String filePath) throws Exception {
		loadAddressBook();
	}
	
	private void loadAddressBook() throws Exception {
		// 파일로부터 Address 정보를 가져와서 addressBookList에 담는다.(ObjectInputStream)
		// AddressBookVo의 SEQ_NUM를 가장 큰 seqNum + 1로 reset한다.
	}
	
	private void saveAddressBook() throws Exception {
		// addressBookList의 데이터를 파일에 저장한다.(ObjectOutputStream)
	}
	
	@Override
	public List<AddressBookVo> selectAddressBook(AddressBookVo paramData) {
		// TODO Auto-generated method stub
		return addressBookList;
	}

	@Override
	public int insertAddressBook(AddressBookVo paramData) throws Exception {
		// TODO Auto-generated method stub
		addressBookList.add(paramData);
		saveAddressBook();
		
		return 0;
	}

	@Override
	public int updateAddressBook(AddressBookVo paramData) throws Exception {
		// TODO Auto-generated method stub
		// 동일한 seqNum의 주소 정보를 addressBookList에 찾아 해당 index의 데이터를 치환한다.
		saveAddressBook();
		return 0;
	}

	@Override
	public int deleteAddressBook(AddressBookVo paramData) throws Exception {
		// TODO Auto-generated method stub
		// 동일한 seqNum의 주소 정보를 addressBookList에 찾아 해당 index의 데이터를 삭제한다.
		saveAddressBook();
		return 0;
	}

}
