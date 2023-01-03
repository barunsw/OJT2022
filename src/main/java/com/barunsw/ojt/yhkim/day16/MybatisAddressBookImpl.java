package com.barunsw.ojt.yhkim.day16;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.barunsw.ojt.intf.AddressBookInterface;
import com.barunsw.ojt.vo.AddressBookVo;

public class MybatisAddressBookImpl implements AddressBookInterface {
	private static Logger LOGGER = LoggerFactory.getLogger(MybatisAddressBookImpl.class);
	private static SqlSessionFactory sqlSessionFactory = SqlSessionFactoryManager.getSqlSessionFactory();

	@Override
	public List<AddressBookVo> selectAddressBook(AddressBookVo paramData) {
		
		List<AddressBookVo> addressBookList = new ArrayList<>();
		
		try (SqlSession session = sqlSessionFactory.openSession()) {
			AddressBookInterface mapper = session.getMapper(AddressBookInterface.class);
			addressBookList = mapper.selectAddressBook(new AddressBookVo());
		}
		catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return addressBookList;
	}

	@Override
	public int insertAddressBook(AddressBookVo paramData) throws Exception {
		int result = 0;
		try (SqlSession session = sqlSessionFactory.openSession()) {
			AddressBookInterface mapper = session.getMapper(AddressBookInterface.class);

			result = mapper.insertAddressBook(paramData);
			session.commit();
		} 
		catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return result;
	}

	@Override
	public int updateAddressBook(AddressBookVo paramData) throws Exception {
		int result = 0;
		try (SqlSession session = sqlSessionFactory.openSession()) {
			AddressBookInterface mapper = session.getMapper(AddressBookInterface.class);

			result = mapper.updateAddressBook(paramData);
			session.commit();
		}
		catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return result;
	}

	@Override
	public int deleteAddressBook(AddressBookVo paramData) throws Exception {
		int result = 0;
		try (SqlSession session = sqlSessionFactory.openSession()) {
			AddressBookInterface mapper = session.getMapper(AddressBookInterface.class);

			result = mapper.deleteAddressBook(paramData);
			session.commit();
		}		
		catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return result;
	}

}