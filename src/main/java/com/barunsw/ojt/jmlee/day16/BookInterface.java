package com.barunsw.ojt.jmlee.day16;

import java.util.List;

import com.barunsw.ojt.vo.AddressBookVo;

public interface BookInterface {
	public List<AddressBookVo> selectAddressList(AddressBookVo addressBookVo) throws Exception;
	public List<AddressBookVo> selectOneAddress(AddressBookVo addressBookVo) throws Exception;
	public int insertAddress(AddressBookVo addressBookVo) throws Exception;
	public int updateAddress(AddressBookVo addressBookVo) throws Exception;
	public int deleteAddress(AddressBookVo addressBookVo) throws Exception;
}
