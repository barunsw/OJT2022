package com.barunsw.ojt.yhkim.day18;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.barunsw.ojt.vo.AddressBookVo;

public class MybatisAddressBookImpl implements AddressBookInterface {
	private static Logger LOGGER = LoggerFactory.getLogger(MybatisAddressBookImpl.class);
	private static SqlSessionFactory sqlSessionFactory = SqlSessionFactoryManager.getSqlSessionFactory();

	@Override
	public List<AddressBookVo> selectAddressList(AddressBookVo paramData) {		
		List<AddressBookVo> addressBookList = new ArrayList<>();
		
		try (SqlSession session = sqlSessionFactory.openSession()) {
			AddressBookInterface mapper = session.getMapper(AddressBookInterface.class);
			addressBookList = mapper.selectAddressList(paramData);
		}
		catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		
		return addressBookList;
	}

	@Override
	public int insertAddress(AddressBookVo paramData) throws Exception {
		int result = 0;
		try (SqlSession session = sqlSessionFactory.openSession()) {
			AddressBookInterface mapper = session.getMapper(AddressBookInterface.class);

			result = mapper.insertAddress(paramData);
			session.commit();
		} 
		catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return result;
	}

	@Override
	public int updateAddress(AddressBookVo paramData) throws Exception {
		int result = 0;
		try (SqlSession session = sqlSessionFactory.openSession()) {
			AddressBookInterface mapper = session.getMapper(AddressBookInterface.class);

			result = mapper.updateAddress(paramData);
			session.commit();
		}
		catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return result;
	}

	@Override
	public int deleteAddress(AddressBookVo paramData) throws Exception {
		int result = 0;
		try (SqlSession session = sqlSessionFactory.openSession()) {
			AddressBookInterface mapper = session.getMapper(AddressBookInterface.class);

			result = mapper.deleteAddress(paramData);
			session.commit();
		}		
		catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return result;
	}

}