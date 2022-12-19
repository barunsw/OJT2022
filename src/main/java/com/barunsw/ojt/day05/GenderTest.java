package com.barunsw.ojt.day05;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.barunsw.ojt.constants.Gender;

public class GenderTest {
	private static Logger LOGGER = LoggerFactory.getLogger(GenderTest.class);
	
	public static void main(String[] args) {
		LOGGER.debug(Gender.MAN.toString());
	}
}
