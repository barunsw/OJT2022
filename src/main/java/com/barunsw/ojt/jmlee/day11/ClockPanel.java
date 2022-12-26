package com.barunsw.ojt.jmlee.day11;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JPanel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ClockPanel extends JPanel {
	private static Logger LOGGER = LogManager.getLogger(ClockPanel.class);
	
	private Timer timer;
	private int sec, min, hour, secX, secY, minX, minY, hourX, hourY; 	
	private int oX =300, oY = 300;
	private int r = 200;
	Calendar cal;
	
	public ClockPanel() {
		try {
			initComponent();
			initTimer();
		}
		catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
		}
		
	}
	
	private void initComponent() {
	}
	
	private void initTimer() {
		
		// 익명 Thread 하나 만들고, 
		// run()에서 무한루프 돌면서, 현재시간 정보 구하고,
		// repaint 호출
		// repaint에서는 시간 정보를 가지고 그림을 그린다.

		timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			public void run() {
				initData(); // 시간정보를 가져온다
				ClockPanel.this.repaint(); // 계속 다시 그려준다
			}
		}, 0, 1000L);
	}
	
	private void initData() {

		//LOGGER.debug("init data");

		cal = Calendar.getInstance();
		sec = cal.get(Calendar.SECOND);
		min = cal.get(Calendar.MINUTE);
		hour = cal.get(Calendar.HOUR);
		
		LOGGER.debug(String.format("%d 시 %d분 %d초", hour, min, sec));
		
		sec = sec * 6 - 90; //초를 구하는 공식
		// 초침
		secX = (int) (130 * Math.cos((sec * Math.PI / 180)) + oX);
		secY = (int) (130 * Math.sin((sec * Math.PI / 180)) + oY);
		// 분침
		min = min * 6 + (sec / 60 - 90); //분을 구하는 공식
		minX = (int) (100 * Math.cos((min * Math.PI / 180)) + oX);
		minY = (int) (100 * Math.sin((min * Math.PI / 180)) + oY);
		// 시침
		hour = hour * 6 + (min / 2 - 90); //시를 구하는 공식
		hourX = (int) (80 * Math.cos((hour * Math.PI / 180)) + oX);
		hourY = (int) (80 * Math.sin((hour * Math.PI / 180)) + oY);
		
	}
	
	// ClockPanel 객체의  repaint 메소드 호출하면 paintComponent가 호출
	
	@Override
	public void paintComponent(Graphics g) {
		//Graphics2D g2d = (Graphics2D)g;
		//LOGGER.debug("paintComponent");
		
		g.setColor(Color.black); // 배경색
		g.fillRect(0,  0, this.getWidth(), this.getHeight());

		g.setColor(Color.WHITE); // 글자색
		g.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 20));
		g.drawString("Analog Clock", 230, 80);
		
		for (int i = 0; i <= 360; i += 6) {
			int x1 = (int) (r * Math.sin(Math.PI * i / 180) + oX);
			int y1 = (int) (r * Math.cos(Math.PI * i / 180) + oY);
			int x2 = (int) ((r - 5) * Math.sin(Math.PI * i / 180) + oX);
			int y2 = (int) ((r - 5) * Math.cos(Math.PI * i / 180) + oY);
			
			if (i % 30 == 0) {
				int j = i;
				x2 = (int) ((r - 30) * Math.sin(Math.PI * i /180) + oX);
				y2 = (int) ((r - 30) * Math.cos(Math.PI * i /180) + oY);
			}
			g.drawLine(x1, y1, x2, y2);
		}
		
		g.setColor(Color.red);
		g.drawLine(oX, oY, secX, secY);			// 초침
		g.setColor(Color.green);
		g.drawLine(oX, oY, minX, minY);			// 분침
		g.setColor(Color.yellow);
		g.drawLine(oX, oY, hourX, hourY);		// 시침


	}

}
