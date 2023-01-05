package com.barunsw.ojt.day18_2;

import java.rmi.Remote;
import java.util.List;

public interface AddressBookInterface extends Remote {
	public List<AddressVo> selectAddressList(AddressVo addressVo) throws Exception;
	public int insertAddress(AddressVo addressVo) throws Exception;
	public int updateAddress(AddressVo addressVo) throws Exception;
	public int deleteAddress(AddressVo addressVo) throws Exception;
}
