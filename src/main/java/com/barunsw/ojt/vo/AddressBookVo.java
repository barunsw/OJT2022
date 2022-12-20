package com.barunsw.ojt.vo;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.barunsw.ojt.constants.Gender;

public class AddressBookVo implements Serializable {
	private static int SEQ_NUM = 1;
	
	private int seqNum;
	private String name;
	private String birthday;
	private Gender gender;
	private String phoneNumber;
	private String address;
	
	public AddressBookVo() {		
	}
	
	public AddressBookVo(String name, String birthday, Gender gender, String phoneNumber, String address) {
		this.seqNum = generateSeqNum();
		this.name = name;
		this.birthday = birthday;
		this.gender = gender;
		this.phoneNumber = phoneNumber;
		this.address = address;
	}
	
	public static int generateSeqNum() {
		return SEQ_NUM++;
	}
	
	public static void resetSeqNum(int seqNum) {
		SEQ_NUM = seqNum;
	}
	
	public int getSeqNum() {
		return seqNum;
	}
	
	public void setSeqNum(int seqNum) {
		this.seqNum = seqNum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
