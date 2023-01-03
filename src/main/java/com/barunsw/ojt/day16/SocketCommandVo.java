package com.barunsw.ojt.day16;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class SocketCommandVo implements Serializable {
	private CmdType cmdType;
	private AddressVo addressVo;
	
	public CmdType getCmdType() {
		return cmdType;
	}
	
	public void setCmdType(CmdType cmdType) {
		this.cmdType = cmdType;
	}
	
	public AddressVo getAddressVo() {
		return addressVo;
	}
	
	public void setAddressVo(AddressVo addressVo) {
		this.addressVo = addressVo;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
