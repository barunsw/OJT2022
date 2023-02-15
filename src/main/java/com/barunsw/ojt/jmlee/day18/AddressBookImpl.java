package com.barunsw.ojt.jmlee.day18;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.barunsw.ojt.vo.AddressBookVo;

public class AddressBookImpl extends UnicastRemoteObject implements AddressBookInterface {
	private static final Logger LOGGER = LoggerFactory.getLogger(AddressBookImpl.class);
	
	private AddressBookInterface addressBookIf = new MybatisAddressBookImpl();

	public AddressBookImpl() throws RemoteException {
		super();
	}
	
	@Override
	public List<AddressBookVo> selectAddressList(AddressBookVo addressBookVo) throws Exception {
		LOGGER.debug("selectAddressList:" + addressBookVo);
		return addressBookIf.selectAddressList(addressBookVo);
	}

	@Override
	public int insertAddress(AddressBookVo addressBookVo) throws Exception {
		LOGGER.debug("insertAddress:" + addressBookVo);
		return addressBookIf.insertAddress(addressBookVo);
	}

	@Override
	public int updateAddress(AddressBookVo addressBookVo) throws Exception {
		LOGGER.debug("updateAddress:" + addressBookVo);
		return addressBookIf.updateAddress(addressBookVo);
	}

	@Override
	public int deleteAddress(AddressBookVo addressBookVo) throws Exception {
		LOGGER.debug("deleteAddress:" + addressBookVo);
		return addressBookIf.deleteAddress(addressBookVo);
	}

}
