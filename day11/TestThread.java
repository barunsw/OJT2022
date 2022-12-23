package com.barunsw.ojt.day11;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TestThread extends Thread {
	private static final Logger LOGGER = LogManager.getLogger(TestThread.class);
	
	private int id;
	
	public TestThread(int id) {
		this.id = id;
	}
	
	@Override
	public void run() {
		for (int i = 0; i < 100; i++) {
			LOGGER.debug(String.format("[%d]%d", id, i));
		}
	}
}
