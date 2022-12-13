package com.barunsw.ojt.jmlee.day02;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class slf4jTest {

	private static  Logger logger = LogManager.getLogger(slf4jTest.class);
	
	public static void main(String[] args) {

		for (int count = 1; count <= 5; count++) {
			logger.info("log4j TEST", count);
		}
		
	}

}
