package com.barunsw.ojt.yhkim.day11;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JPanel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class ClockPanel extends JPanel {
	private static Logger LOGGER = LogManager.getLogger(ClockPanel.class);
	
	private Timer timer;
	
	public ClockPanel() {
		try {
			initComponent();
			
			initTimer();
		}
		catch (Exception ex) {
			
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
				initData();
				ClockPanel.this.repaint();
			}
		}, 0, 1000L);
	}
	
	private void initData() {
		// 시침의 위치
		// 분침의 위치
		// 초침의 위치
	}
	
	// ClockPanel 객체의  repaint 메소드 호출하면 paintComponent가 호출
	
	@Override
	public void paintComponent(Graphics g) {
		//Graphics2D g2d = (Graphics2D)g;
		LOGGER.debug("paintComponent");
		
		g.setColor(Color.white);
		g.fillRect(0,  0, this.getWidth(), this.getHeight());

		g.setColor(Color.black);
		g.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 20));
		g.drawString("테스트", 100, 100);

		g.setColor(Color.blue);
		g.drawOval(200, 200, 200, 100);

		g.setColor(Color.green);
		g.drawLine(300, 300, 400, 400);
		
		// 중앙의 점과 시침의 위치를 이어서 선을 긋는다.
		// 중앙의 점과 분침의 위치를 이어서 선을 긋는다.
		// 중앙의 점과 초침의 위치를 이어서 선을 긋는다.
	}
}
