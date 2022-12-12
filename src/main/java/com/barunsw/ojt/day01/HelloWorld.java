package com.barunsw.ojt.day01;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloWorld {
	private static Logger LOGGER = LoggerFactory.getLogger(HelloWorld.class);
	
	public static void main(String[] args) {
		//System.out.println("Hello World");
		LOGGER.debug("Hello World");
	}
}
