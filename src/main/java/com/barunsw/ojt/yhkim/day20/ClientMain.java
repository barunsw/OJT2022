package com.barunsw.ojt.yhkim.day20;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Toolkit;

import javax.swing.UIManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class ClientMain {
	private static final Logger LOGGER = LogManager.getLogger(ClientMain.class);
	
	public static EventQueueWorker eventQueueWorker = new EventQueueWorker();
	
	public static void main(String[] args) {
		try {
			eventQueueWorker.start();
			
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} 
		catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		
		ChatFrame frame = new ChatFrame();
		
		// 화면의 전체 크기
		Dimension scrDim = Toolkit.getDefaultToolkit().getScreenSize();
		
		int xPos = (scrDim.width - ChatFrame.WIDTH) / 2;
		int yPos = (scrDim.height - ChatFrame.HEIGHT) / 2;
		
		// 1)과 2)를 동시에 처리
		frame.setBounds(new Rectangle(xPos, yPos, ChatFrame.WIDTH, ChatFrame.HEIGHT));
		// 3) 프레임 표시
		frame.setVisible(true);
	}
}
