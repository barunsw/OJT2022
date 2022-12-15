package com.barunsw.ojt.vo;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.barunsw.ojt.constants.Gender;

public class Person implements Serializable {
	private String name;
	private int age;
	private Gender gender;
	
	public Person() {
	}
	
	public Person(String name, int age, Gender gender) {
		this.name = name;
		this.age = age;
		this.gender = gender;
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
	
	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}
/*
	@Override
	public int hashCode() {
		return age;
	}
*/
	@Override
	public boolean equals(Object o) {
		if (o instanceof Person) {
			Person anotherPerson = (Person)o;
			return this.age == anotherPerson.age;
		}
		else {
			return false;
		}
		
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
