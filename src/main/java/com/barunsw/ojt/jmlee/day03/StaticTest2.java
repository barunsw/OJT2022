package com.barunsw.ojt.jmlee.day03;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class Name{
    static void print() { //클래스 메소드
	System.out.println("내 이름은 이재민입니다.");
    }

    void print2() { //인스턴스 메소드
	System.out.println("내 이름은 이재민2입니다.");
    }
}

public class StaticTest2 {
	
	private static Logger LOGGER = LoggerFactory.getLogger(ReadWriterTest.class);

	public static void main(String[] args) {
		
		 Name.print(); //인스턴스를 생성하지 않아도 호출이 가능
	    	
	     Name name = new Name(); //인스턴스 생성
	     name.print2(); //인스턴스를 생성하여야만 호출이 가능

	}

}
