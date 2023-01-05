package com.barunsw.ojt.jmlee.day18;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AddressBookImpl extends UnicastRemoteObject implements RMIBookInterface {
	private static final Logger LOGGER = LogManager.getLogger(AddressBookImpl.class);
	private static final SqlSessionFactory sqlSessionFactory = SqlSessionFactoryManager.getSqlSessionFactory();
	
	public AddressBookImpl() throws RemoteException {
		super();
	}
	
	@Override
	public List<AddressBookVo> selectAddressList(AddressBookVo addressBookVo) throws RemoteException {
		
		List<AddressBookVo> addressBooklist = new ArrayList<>();

		try (SqlSession session = sqlSessionFactory.openSession()) {
			RMIBookInterface mapper = session.getMapper(RMIBookInterface.class);

			addressBooklist = mapper.selectAddressList(new AddressBookVo());

			for (AddressBookVo s : addressBooklist) {
				LOGGER.debug(s.toString());
			}

		} catch (Exception ex) {
			LOGGER.debug(ex.getMessage(), ex);
		}
		return addressBooklist;
	}

	@Override
	public int insertAddress(AddressBookVo addressBookVo) throws RemoteException {
		try (SqlSession session = sqlSessionFactory.openSession()) {
			LOGGER.debug("insert 호출");
			RMIBookInterface mapper = session.getMapper(RMIBookInterface.class);
			
			mapper.insertAddress(addressBookVo);
			session.commit();
			LOGGER.debug("insert end");	
		}
		catch (Exception ex) {
			LOGGER.debug(ex.getMessage(), ex);
		}
		return 0;
	}

	@Override
	public int updateAddress(AddressBookVo addressBookVo) throws RemoteException {
		try (SqlSession session = sqlSessionFactory.openSession()) {
			LOGGER.debug("update 호출");
			RMIBookInterface mapper = session.getMapper(RMIBookInterface.class);
			
			mapper.updateAddress(addressBookVo);
			session.commit();
			LOGGER.debug("update end");	
		}		
		catch (Exception ex) {
			LOGGER.debug(ex.getMessage(), ex);
		}
		return 0;
	}

	@Override
	public int deleteAddress(AddressBookVo addressBookVo) throws RemoteException {
		try (SqlSession session = sqlSessionFactory.openSession()) {
			LOGGER.debug("Delete 호출");
			RMIBookInterface mapper = session.getMapper(RMIBookInterface.class);
			
			mapper.deleteAddress(addressBookVo);
			session.commit();
			LOGGER.debug("Delete end");	
		}				
		catch (Exception ex) {
			LOGGER.debug(ex.getMessage(), ex);
		}
		return 0;
	}

}
