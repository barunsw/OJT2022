package com.barunsw.ojt.yhkim.day07;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.barunsw.ojt.constants.Gender;

public class AddressBook implements Serializable {
	
	private int seq;
	private String name;
	private String birthday;
	private Gender gender;
	private String phoneNumber;
	private String address;
	
	public int getSeq() {
		return seq;
	}
	
	public void setSeq(int seq) {
		this.seq = seq;
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
