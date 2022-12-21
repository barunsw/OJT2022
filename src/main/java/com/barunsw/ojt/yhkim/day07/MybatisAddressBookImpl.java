package com.barunsw.ojt.yhkim.day07;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.barunsw.ojt.intf.AddressBookInterface;
import com.barunsw.ojt.vo.AddressBookVo;

public class MybatisAddressBookImpl implements AddressBookInterface {
   private static Logger LOGGER = LogManager.getLogger(AddressBookInterface.class);
   private static SqlSessionFactory sqlSessionFactory = SqlSessionFactoryManager.getSqlSessionFactory();

   @Override
   public List<AddressBookVo> selectAddressBook(AddressBookVo paramData) {

      try (SqlSession session = sqlSessionFactory.openSession()) {
         AddressBookDao mapper = session.getMapper(AddressBookDao.class);
         
         List<AddressBookVo> addressBookList = mapper.selectAddressBook(new AddressBookVo());
         
         for (int i = 0; i < addressBookList.size(); i++) {
            LOGGER.debug(String.format("[%d]%s", i, addressBookList.get(i)));
         }
         
         return addressBookList;
      } 
   }

   @Override
   public int insertAddressBook(AddressBookVo paramData) throws Exception {
      try (SqlSession session = sqlSessionFactory.openSession()) {
         AddressBookDao mapper = session.getMapper(AddressBookDao.class);
         
         int result = mapper.insertAddressBook(paramData);
         session.commit();
         
         return 0;
      } 
   }

   @Override
   public int updateAddressBook(AddressBookVo paramData) throws Exception {
      try (SqlSession session = sqlSessionFactory.openSession()) {
         AddressBookDao mapper = session.getMapper(AddressBookDao.class);
         
         int result = mapper.updateAddressBook(paramData);
         session.commit();
         
         return 0;
      }    
   }

   @Override
   public int deleteAddressBook(AddressBookVo paramData) throws Exception {
      try (SqlSession session = sqlSessionFactory.openSession()) {
         AddressBookDao mapper = session.getMapper(AddressBookDao.class);
         
         int result = mapper.deleteAddressBook(paramData);
         session.commit();

         return 0;
      }
   }

}