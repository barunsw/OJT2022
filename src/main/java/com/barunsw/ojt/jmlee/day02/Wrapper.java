package com.barunsw.ojt.jmlee.day02;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Wrapper {

	private static Logger LOGGER = LoggerFactory.getLogger(StringMethodTest.class); 

	public static void main(String[] args) {

//		Integer num = new Integer(17); // 박싱
//        int n = num.intValue(); //언박싱
//        System.out.println(n);
//        
//        int i = 1;
//        Integer integer = i;		// int -> Integer ( Auto boxing )
//         
//        int i2 = integer;		 // Integer -> int ( Auto unboxing )
		
		Integer num = new Integer(10); 
		Integer num2 = new Integer(10); 
		int i = 10; // 기본타입
				 
		LOGGER.debug("래퍼클래스 == 기본 : "+(num == i)); // true
		LOGGER.debug("래퍼클래스.equals(기본) : "+num.equals(i)); // true
		LOGGER.debug("래퍼클래스 == 래퍼클래스 : "+(num == num2)); // false
		LOGGER.debug("래퍼클래스.equals(래퍼클래스) : "+num.equals(num2)); // true

		
	}

}
