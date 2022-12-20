package com.barunsw.ojt.jmlee.day07;

import java.util.List;

import com.barunsw.ojt.vo.AddressBookVo;

public interface AddressBookDao {

	public List<AddressBookVo> selectAddressBookList(AddressBookVo paramData);
	public int insertAddressBook(AddressBookVo paramData);
	public int updateAddressBook(AddressBookVo paramData);
	public int deleteAddressBook(AddressBookVo paramData);
}
