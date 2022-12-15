package com.barunsw.ojt.intf;

import java.util.List;

import com.barunsw.ojt.vo.AddressBookVo;

public interface AddressBookInterface {
	/**
	 * 주소록 정보를 조회한다.
	 * @param paramData 주소록 정보 리스트
	 * @return
	 */
	public List<AddressBookVo> selectAddressBook(AddressBookVo paramData);
	
	/**
	 * 주소록 정보를 생성한다.
	 * @param paramData 주소록 정보
	 * @return
	 * @throws Exception
	 */
	public int insertAddressBook(AddressBookVo paramData) throws Exception;
	
	/**
	 * 주소록 정보를 수정한다.
	 * @param paramData 주소록 정보
	 * @return
	 * @throws Exception
	 */
	public int updateAddressBook(AddressBookVo paramData) throws Exception;
	
	/**
	 * 주소록 정보를 삭제한다.
	 * @param paramData 주소록 정보
	 * @return
	 * @throws Exception
	 */
	public int deleteAddressBook(AddressBookVo paramData) throws Exception;
}
