package com.barunsw.ojt.jmlee.day16;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.barunsw.ojt.vo.AddressBookVo;

public class SwingAddressBookImpl implements BookInterface {
   	
	private static final Logger LOGGER = LoggerFactory.getLogger(SwingAddressBookImpl.class);	
	private static final SqlSessionFactory sqlSessionFactory = SqlSessionFactoryManager.getSqlSessionFactory();
	
	@Override
	public List<AddressBookVo> selectAddressList(AddressBookVo addressBookVo) throws Exception {

		List<AddressBookVo> addressBooklist = new ArrayList<>();

		try (SqlSession session = sqlSessionFactory.openSession()) {
			BookInterface mapper = session.getMapper(BookInterface.class);

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
	public List<AddressBookVo> selectOneAddress(AddressBookVo addressBookVo) throws Exception {

		List<AddressBookVo> addressBooklist = new ArrayList<>();

		try (SqlSession session = sqlSessionFactory.openSession()) {
			BookInterface mapper = session.getMapper(BookInterface.class);

			addressBooklist = mapper.selectOneAddress(addressBookVo);

			for (AddressBookVo s : addressBooklist) {
				LOGGER.debug(s.toString());
			}

		} catch (Exception ex) {
			LOGGER.debug(ex.getMessage(), ex);
		}
		return addressBooklist;

	}

	public int insertAddress(AddressBookVo addressBookVo) throws Exception {

		try (SqlSession session = sqlSessionFactory.openSession()) {
			LOGGER.debug("insert 호출");
			BookInterface mapper = session.getMapper(BookInterface.class);
			
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
	public int updateAddress(AddressBookVo addressBookVo) throws Exception {
		
		try (SqlSession session = sqlSessionFactory.openSession()) {
			LOGGER.debug("update 호출");
			BookInterface mapper = session.getMapper(BookInterface.class);
			
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
	public int deleteAddress(AddressBookVo addressBookVo) throws Exception {
		
		try (SqlSession session = sqlSessionFactory.openSession()) {
			LOGGER.debug("Delete 호출");
			BookInterface mapper = session.getMapper(BookInterface.class);
			
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
