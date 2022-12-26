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
	private int sec, min, hour, xSec, ySec, xMin, yMin, xHour, yHour;
	private int ox = 300, oy = 300;
	private int r = 200;

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

		Calendar cal = Calendar.getInstance();
		sec = cal.get(Calendar.SECOND);
		min = cal.get(Calendar.MINUTE);
		hour = cal.get(Calendar.HOUR);

		LOGGER.debug(hour + "시 "+ min + "분 " + sec + "초");

		sec = sec * 6 - 90;
		xSec = (int) (150 * Math.cos(Math.PI * sec / 180) + ox);
		ySec = (int) (150 * Math.sin(Math.PI * sec / 180) + oy);

		min = min * 6 + sec / 60 - 90;
		xMin = (int) (125 * Math.cos(Math.PI * min / 180) + ox);
		yMin = (int) (125 * Math.sin(Math.PI * min / 180) + oy);

		hour = hour * 30 + hour / 2 - 90;
		xHour = (int) (100 * Math.cos(Math.PI * hour / 180) + ox);
		yHour = (int) (100 * Math.sin(Math.PI * hour / 180) + oy);

	}

	// ClockPanel 객체의  repaint 메소드 호출하면 paintComponent가 호출

	@Override
	public void paintComponent(Graphics g) {

		g.setColor(Color.white);
		g.fillRect(0,  0, this.getWidth(), this.getHeight());

		g.setColor(Color.black);
		g.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 20));
		g.drawString("아날로그 시계", 50, 50);

		g.setColor(Color.black);
		g.drawOval(100, 100, 400, 400);

		g.setColor(Color.black);
		
		int j = 6;
		for (int i = 0; i < 360; i += 6) { // 시계 테두리 눈금
			int x1 = (int) (r * Math.sin(Math.PI * i / 180) + ox);
			int y1 = (int) (r * Math.cos(Math.PI * i / 180) + oy);
			int x2 = (int) ((r - 5) * Math.sin(Math.PI * i / 180) + ox);
			int y2 = (int) ((r - 5) * Math.cos(Math.PI * i / 180) + oy);

			if ((i % 30 == 0) && (j <= 12) && (i != 360)) {
 				x2 = (int) ((r - 20) * Math.sin(Math.PI * i / 180) + ox);
				y2 = (int) ((r - 20) * Math.cos(Math.PI * i / 180) + oy);
				
				g.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 20));
				g.drawString(Integer.toString(j), x2 - 10, y2 + 5);
				j--;
				
				if (j == 0) {
					j = 12;
				}
			}
			else {
				g.drawLine(x1, y1, x2, y2);				
			}
		}

		g.setColor(Color.red);
		g.drawLine(ox, oy, xSec, ySec);

		g.setColor(Color.blue);
		g.drawLine(ox, oy, xMin, yMin);

		g.setColor(Color.green);
		g.drawLine(ox, oy, xHour, yHour);

	}
}
