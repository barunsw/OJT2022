package com.barunsw.ojt.jmlee.day16;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.barunsw.ojt.vo.AddressBookVo;

public class SocketCommandVo implements Serializable {
	private CmdType cmdType;
	private AddressBookVo addressbookVo;
	
	public CmdType getCmdType() {
		return cmdType;
	}
	
	public void setCmdType(CmdType cmdType) {
		this.cmdType = cmdType;
	}
	
	public AddressBookVo getAddressBookVo() {
		return addressbookVo;
	}
	
	public void setAddressVo(AddressBookVo addressbookVo) {
		this.addressbookVo = addressbookVo;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
