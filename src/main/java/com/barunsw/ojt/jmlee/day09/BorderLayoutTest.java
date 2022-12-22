package com.barunsw.ojt.jmlee.day09;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

public class BorderLayoutTest extends JFrame {
	
	BorderLayoutTest() {
		// BorderLayout은 기본 설정이라 따로 선언하지 않아도 된다.
		//setLayout(new BorderLayout());
		setTitle("BorderLayout");
		setSize(500,500);
		
		JButton btns[] = new JButton[5]; // 5개의 버튼 리스트 생성
		
		for (int i = 0; i < btns.length; i++) {
			btns[i] = new JButton("버튼" + (i+1));
		}
		
		// 상, 하, 좌, 우, 중앙 배치
		add(btns[0], BorderLayout.EAST);
		add(btns[1], BorderLayout.WEST);
		add(btns[2], BorderLayout.SOUTH);
		add(btns[3], BorderLayout.NORTH);
		add(btns[4], BorderLayout.CENTER);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE); // 창을 닫으면 자동으로 프로세스 종료
		setLocationRelativeTo(null);  			  // 창을 가운데에 띄우는 메소드
	}
	
	
	public static void main(String[] args) {

		new BorderLayoutTest().setVisible(true);
		
	}

}
