package com.barunsw.ojt.yhkim.day09;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Test extends Thread {
	private static Logger LOGGER = LogManager.getLogger(Test.class);

	int seq;
	
	public Test(int seq) {
		this.seq = seq;
	}
	
    public void run() { 
    	LOGGER.debug(this.seq + " thread start.");
    	try {
    		Thread.sleep(1000); // 1초 대기
    	} 
    	catch (Exception e) {
    	}
    	LOGGER.debug(this.seq + " thread end.");
    }

	public static void main(String[] args) {
		for(int i = 0; i < 10; i++) {
			Thread t = new Test(i);
			t.start();
		}
	}

}