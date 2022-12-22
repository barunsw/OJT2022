package com.barunsw.ojt.jmlee.day09;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.barunsw.ojt.constants.Gender;
import com.barunsw.ojt.intf.AddressBookInterface;
import com.barunsw.ojt.jmlee.day07.AddressBookDao;
import com.barunsw.ojt.jmlee.day07.SqlSessionFactoryManager;
import com.barunsw.ojt.vo.AddressBookVo;

public class SwingAddressBookImpl implements AddressBookInterface {
   	
	private static final Logger LOGGER = LoggerFactory.getLogger(SwingAddressBookImpl.class);
	
	private static final SqlSessionFactory sqlSessionFactory = SqlSessionFactoryManager.getSqlSessionFactory();

	@Override
	public List<AddressBookVo> selectAddressBook(AddressBookVo paramData) {
		
		List<AddressBookVo> addressBooklist = new ArrayList<>();
		
		try (SqlSession session = sqlSessionFactory.openSession()) {
			AddressBookDao mapper = session.getMapper(AddressBookDao.class);
			
				addressBooklist = mapper.selectAddressBookList(new AddressBookVo());
			
			for (AddressBookVo s : addressBooklist) {
				LOGGER.debug(s.toString());
			}
//			for (int i = 0; i < addressBooklist.size(); i++) {
//				LOGGER.debug(String.format("[%d]%s", i, addressBooklist.get(i)));
//			}
		} catch (Exception ex) {
			LOGGER.debug(ex.getMessage(), ex);
		}
		return addressBooklist;
	}

	@Override
	public int insertAddressBook(AddressBookVo paramData) throws Exception {
		// TODO Auto-generated method stub
		try (SqlSession session = sqlSessionFactory.openSession()) {
			System.out.println("insert 호출");
			AddressBookDao mapper = session.getMapper(AddressBookDao.class);
			
			mapper.insertAddressBook(paramData);
			
			session.commit();
			System.out.println("insert end");	
		}
		catch (Exception ex) {
			LOGGER.debug(ex.getMessage(), ex);
		}
		
		
		return 0;
	}

	@Override
	public int updateAddressBook(AddressBookVo paramData) throws Exception {
		try (SqlSession session = sqlSessionFactory.openSession()) {
			LOGGER.debug("update 호출");
			AddressBookDao mapper = session.getMapper(AddressBookDao.class);
			
			mapper.updateAddressBook(paramData);
			session.commit();
			LOGGER.debug("update end");	
		}		
		catch (Exception ex) {
			LOGGER.debug(ex.getMessage(), ex);
		}
		return 0;
	}

	@Override
	public int deleteAddressBook(AddressBookVo paramData) throws Exception {
		try (SqlSession session = sqlSessionFactory.openSession()) {
			LOGGER.debug("Delete 호출");
			AddressBookDao mapper = session.getMapper(AddressBookDao.class);
			
			mapper.deleteAddressBook(paramData);
			session.commit();
			LOGGER.debug("Delete end");	
		}				
		catch (Exception ex) {
			LOGGER.debug(ex.getMessage(), ex);
		}
		return 0;
	}

}
