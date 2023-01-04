package com.barunsw.ojt.jmlee.day16;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.barunsw.ojt.vo.AddressBookVo;

public class SocketCommandVo implements Serializable {
	private CmdType cmdType;
	private AddressBookVo addressBookVo;
	
	public CmdType getCmdType() {
		return cmdType;
	}
	
	public void setCmdType(CmdType cmdType) {
		this.cmdType = cmdType;
	}
	
	public AddressBookVo getAddressBookVo() {
		return addressBookVo;
	}
	
	public void setAddressBookVo(AddressBookVo addressBookVo) {
		this.addressBookVo = addressBookVo;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
