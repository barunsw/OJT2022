package com.barunsw.ojt.jmlee.day11;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.util.Timer;

import javax.swing.UIManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ThreadTest {
	private static final Logger LOGGER = LogManager.getLogger(ThreadTest.class);

	public static void main(String[] args) {

//		LOGGER.debug("+++ activeCount:" + Thread.activeCount());

//		for (int i = 0; i < 10; i++) {
//			TestThread t = new TestThread(i);
//			t.start();
//			
//			LOGGER.debug(String.format("[%d] activeCount:%d", i, Thread.activeCount()));
//		}



/*		Timer timer = new Timer();
		for (int i = 0; i < 3; i++) {
			TestTimerTask t = new TestTimerTask(i);
			
			timer.schedule(t, 0, 1000L);
			timer.scheduleAtFixedRate(t, 0, 1000L);
			
			LOGGER.debug(String.format("[%d] activeCount:%d", 0, Thread.activeCount()));
		}
*/

//		for (int i = 0; i < 3; i++) {
//			TestThread2 t = new TestThread2(i);
//			t.start();
//			
//			LOGGER.debug(String.format("[%d] activeCount:%d", i, Thread.activeCount()));
//		}


		try {
			// Look and Feel UIManager를 통해 ui 쉽게 변경 가능
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		}
		catch (Exception ex) {
		}
		
		TestFrame frame = new TestFrame();
		
		// 화면의 전체 크기
		frame.setSize(new Dimension(ClockPanel.WIDTH, ClockPanel.HEIGHT));
		// 화면 가운데 정렬
		frame.setLocationRelativeTo(null); 
		// 1)과 2)를 동시에 처리
//		frame.setBounds(new Rectangle(xPos, yPos, TestFrame.WIDTH, TestFrame.HEIGHT));
		// 3) 프레임 표시
		frame.setVisible(true);

//		LOGGER.debug("--- activeCount:" + Thread.activeCount());		
			
	}
}