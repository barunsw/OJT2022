package com.barunsw.ojt.jmlee.day09;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

public class FlowLayoutTest extends JFrame {

	FlowLayoutTest() {
		setTitle("FlowLayout");
		setLayout(new FlowLayout(FlowLayout.CENTER, 15, 15));
		// new FlowLayout(int align,int hgap,int vgap) 생성자로 정렬기준 뿐만 아니라 상하좌우 간격도 지정 가능
		setSize(500,500);
		
		JButton btns[] = new JButton[5]; // 5개의 버튼 리스트 생성
		
		for (int i = 0; i < btns.length; i++) {
			btns[i] = new JButton("버튼" + (i+1));
		}
		
		add(btns[0]);
		add(btns[1]);
		add(btns[2]);
		add(btns[3]);
		add(btns[4]);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE); // 창을 닫으면 자동으로 프로세스 종료
		setLocationRelativeTo(null);  			  // 창을 가운데에 띄우는 메소드
	}
	
	public static void main(String[] args) {

		new FlowLayoutTest().setVisible(true);
		
	}

}
