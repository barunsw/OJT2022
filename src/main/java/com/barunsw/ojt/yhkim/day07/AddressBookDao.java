package com.barunsw.ojt.yhkim.day07;

import java.util.List;

import com.barunsw.ojt.vo.AddressBookVo;

public interface AddressBookDao {

   public List<AddressBookVo> selectAddressBook(AddressBookVo addressBookVo);
   
   public int insertAddressBook(AddressBookVo paramData) throws Exception;

   public int updateAddressBook(AddressBookVo paramData) throws Exception;

   public int deleteAddressBook(AddressBookVo paramData) throws Exception;

}