package com.barunsw.ojt.day18_2;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class AddressVo implements Serializable {
	private static final long serialVersionUID = 5178783147247060706L;
	
	private int seq;
	private String name;
	private int age;
	
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
	
	public int getAge() {
		return age;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
