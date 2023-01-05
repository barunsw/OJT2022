package com.barunsw.ojt.day18_2;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AddressBookImpl extends UnicastRemoteObject implements AddressBookInterface {
	private static final Logger LOGGER = LogManager.getLogger(AddressBookImpl.class);
	
	public AddressBookImpl() throws RemoteException {
		super();
	}
	
	@Override
	public List<AddressVo> selectAddressList(AddressVo addressVo) throws RemoteException {
		// TODO Auto-generated method stub
		LOGGER.debug("selectAddressList:" + addressVo);
		
		List<AddressVo> addressList = new ArrayList<>();
		
		final AddressVo oneAddressVo = new AddressVo();
		oneAddressVo.setAge(10);
		oneAddressVo.setName("홍길동");
		
		addressList.add(oneAddressVo);
		
		return addressList;
	}

	@Override
	public int insertAddress(AddressVo addressVo) throws RemoteException {
		// TODO Auto-generated method stub
		LOGGER.debug("insertAddress:" + addressVo);
		return 0;
	}

	@Override
	public int updateAddress(AddressVo addressVo) throws RemoteException {
		// TODO Auto-generated method stub
		LOGGER.debug("updateAddress:" + addressVo);
		return 0;
	}

	@Override
	public int deleteAddress(AddressVo addressVo) throws RemoteException {
		// TODO Auto-generated method stub
		LOGGER.debug("deleteAddress:" + addressVo);
		return 0;
	}

}
