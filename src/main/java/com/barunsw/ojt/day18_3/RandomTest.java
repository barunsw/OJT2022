package com.barunsw.ojt.day18_3;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.barunsw.ojt.constants.Severity;

public class RandomTest {
	private static Logger LOGGER = LogManager.getLogger(RandomTest.class);
	
	public static void main(String[] args) throws Exception {
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					int boardId = (int)(Math.random() * 32);
					int severity = (int)(Math.random() * 4);
					
					Severity sev = Severity.items[severity];

					LOGGER.debug("boardId:" + boardId + ", severity:" + sev);
					
					try {
						Thread.sleep(1000);
					}
					catch (InterruptedException ex) {
						
					}
				}
			}
		});
		
		t.start();
		
		t.join();
	}
}
