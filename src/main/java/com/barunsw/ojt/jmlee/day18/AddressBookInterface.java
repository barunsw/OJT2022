package com.barunsw.ojt.jmlee.day18;

import java.rmi.Remote;
import java.util.List;

import com.barunsw.ojt.vo.AddressBookVo;

public interface AddressBookInterface extends Remote {
	public List<AddressBookVo> selectAddressList(AddressBookVo addressBookVo) throws Exception;
	public int insertAddress(AddressBookVo addressBookVo) throws Exception;
	public int updateAddress(AddressBookVo addressBookVo) throws Exception;
	public int deleteAddress(AddressBookVo addressBookVo) throws Exception;
}
