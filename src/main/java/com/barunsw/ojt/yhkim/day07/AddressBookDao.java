package com.barunsw.ojt.yhkim.day07;

import java.util.List;

import com.barunsw.ojt.yhkim.day07.AddressBook;

public interface AddressBookDao {
	/**
	 * 주소록 정보를 조회한다.
	 * @param paramData 주소록 정보 리스트
	 * @return
	 */
	public List<AddressBook> selectAddressBook(AddressBook paramData);
	
	/**
	 * 주소록 정보를 생성한다.
	 * @param paramData 주소록 정보
	 * @return
	 * @throws Exception
	 */
	public int insertAddressBook(AddressBook paramData) throws Exception;
	
	/**
	 * 주소록 정보를 수정한다.
	 * @param paramData 주소록 정보
	 * @return
	 * @throws Exception
	 */
	public int updateAddressBook(AddressBook paramData) throws Exception;
	
	/**
	 * 주소록 정보를 삭제한다.
	 * @param paramData 주소록 정보
	 * @return
	 * @throws Exception
	 */
	public int deleteAddressBook(AddressBook paramData) throws Exception;
	
	/**
	 * 이름이 포함된 주소록 정보를 조회한다.
	 * @param paramData 주소록 정보 리스트
	 * @return
	 * @throws Exception
	 */
	public List<AddressBook> selectAddressByName(AddressBook paramData) throws Exception;
	
	/**
	 * seq 번호의 주소록 정보를 조회한다.
	 * @param paramData 주소록 정보
	 * @return
	 * @throws Exception
	 */
	public AddressBook selectAddressBySeq(AddressBook paramData) throws Exception;

}
