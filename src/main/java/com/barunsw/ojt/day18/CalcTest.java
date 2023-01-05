package com.barunsw.ojt.day18;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CalcTest {
	private static Logger LOGGER = LogManager.getLogger(CalcTest.class);
	
	public static void main(String[] args) throws Exception {
		CalcInterface calc = new CalcImpl();
		LOGGER.debug(String.format("1 + 2 = %s", calc.add(1, 2)));
	}
}
