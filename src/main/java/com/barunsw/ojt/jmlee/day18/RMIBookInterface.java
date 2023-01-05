package com.barunsw.ojt.jmlee.day18;

import java.rmi.Remote;
import java.util.List;

public interface RMIBookInterface extends Remote {
	public List<AddressBookVo> selectAddressList(AddressBookVo addressVo) throws Exception;
	public int insertAddress(AddressBookVo addressVo) throws Exception;
	public int updateAddress(AddressBookVo addressVo) throws Exception;
	public int deleteAddress(AddressBookVo addressVo) throws Exception;
}
