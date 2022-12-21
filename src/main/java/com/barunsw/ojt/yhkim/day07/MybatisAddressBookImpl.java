package com.barunsw.ojt.yhkim.day07;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.barunsw.ojt.intf.AddressBookInterface;
import com.barunsw.ojt.vo.AddressBookVo;

public class MybatisAddressBookImpl implements AddressBookInterface {
	private static Logger LOGGER = LogManager.getLogger(MybatisAddressBookImpl.class);
	private static SqlSessionFactory sqlSessionFactory = SqlSessionFactoryManager.getSqlSessionFactory();

	@Override
	public List<AddressBookVo> selectAddressBook(AddressBookVo paramData) {
		
		List<AddressBookVo> addressBookList = new ArrayList<>();
		
		try (SqlSession session = sqlSessionFactory.openSession()) {
			AddressBookInterface mapper = session.getMapper(AddressBookInterface.class);

			addressBookList = mapper.selectAddressBook(new AddressBookVo());

//			for (AddressBookVo b : addressBookList) {
//				LOGGER.debug(b.toString());
//			}

		}
		catch (Exception e) {
			LOGGER.debug(e);
		}
		return addressBookList;
	}

	@Override
	public int insertAddressBook(AddressBookVo paramData) throws Exception {
		int result = 0;
		try (SqlSession session = sqlSessionFactory.openSession()) {
			AddressBookInterface mapper = session.getMapper(AddressBookInterface.class);

			result = mapper.insertAddressBook(paramData);
			LOGGER.debug("INSERT : "+result);

			session.commit();
		} 
		catch (Exception e) {
			LOGGER.debug(e);
		}
		return result;
	}

	@Override
	public int updateAddressBook(AddressBookVo paramData) throws Exception {
		int result = 0;
		try (SqlSession session = sqlSessionFactory.openSession()) {
			AddressBookInterface mapper = session.getMapper(AddressBookInterface.class);

			result = mapper.updateAddressBook(paramData);
			LOGGER.debug("UPDATE : "+result);

			session.commit();
		}
		catch (Exception e) {
			LOGGER.debug(e);
		}
		return result;
	}

	@Override
	public int deleteAddressBook(AddressBookVo paramData) throws Exception {
		int result = 0;
		try (SqlSession session = sqlSessionFactory.openSession()) {
			AddressBookInterface mapper = session.getMapper(AddressBookInterface.class);

			result = mapper.deleteAddressBook(paramData);
			LOGGER.debug("DELETE : "+result);

			session.commit();
		}		
		catch (Exception e) {
			LOGGER.debug(e);
		}
		return result;
	}

}