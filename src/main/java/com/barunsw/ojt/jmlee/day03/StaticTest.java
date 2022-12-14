package com.barunsw.ojt.jmlee.day03;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class Number{
    static int num = 0; //클래스 필드
    int num2 = 0; //인스턴스 필드
}

public class StaticTest {

	private static Logger LOGGER = LoggerFactory.getLogger(ReadWriterTest.class);

	
	public static void main(String[] args) {

		    Number number1 = new Number(); //첫번째 number
		    Number number2 = new Number(); //두번쨰 number
		    	
		    number1.num++; //클래스 필드 num을 1증가시킴
		    number1.num2++; //인스턴스 필드 num을 1증가시킴
		    LOGGER.debug("number2 : " + number2.num); //두번째 number의 클래스 필드 출력
		    LOGGER.debug("number2 : " + number2.num2);//두번째 number의 인스턴스 필드 출력
		    
	}

}
