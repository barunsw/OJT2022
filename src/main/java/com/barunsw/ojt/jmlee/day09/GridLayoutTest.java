package com.barunsw.ojt.jmlee.day09;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

public class GridLayoutTest extends JFrame{

	GridLayoutTest() {
		setTitle("GridLayout");
		setLayout(new GridLayout(3,3)); // 행과 열이 3 X 3 
		setSize(500,500);

		JButton btns[] = new JButton[9]; // 9개의 버튼 리스트 생성
		
		for (int i = 0; i < btns.length; i++) {
			btns[i] = new JButton("버튼" + (i+1));
		}
		
		add(btns[0]);
		add(btns[1]);
		add(btns[2]);
		add(btns[3]);
		add(btns[4]);
		add(btns[5]);
		add(btns[6]);
		add(btns[7]);
		add(btns[8]);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE); // 창을 닫으면 자동으로 프로세스 종료
		setLocationRelativeTo(null);  			  // 창을 가운데에 띄우는 메소드
	}
	
	public static void main(String[] args) {
		new GridLayoutTest().setVisible(true);
				
	}

}
