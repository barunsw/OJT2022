package com.barunsw.ojt.jmlee.day20;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ChatFrame extends JFrame {
	private static final Logger LOGGER = LogManager.getLogger(ChatFrame.class);
	
	public static final int WIDTH  = 500;
	public static final int HEIGHT = 600;
	
	private ChatPanel chatPanel = new ChatPanel();
	
	public ChatFrame() {
		try {
			initComponent();
		}
		catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
		}
	}
	
	private void initComponent() throws Exception {
		// 타이틀
		this.setTitle("SwingTest");
		// 기본적인 닫힘 오퍼레이션
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		this.setContentPane(chatPanel);
		
		// 윈도우 이벤트
		this.addWindowListener(new TestFrame_this_WindowAdapter(this));
	}
	
	void windowClosing(WindowEvent e) {
		LOGGER.debug("windowClosing");
		
		int result = JOptionPane.showConfirmDialog(ChatFrame.this, 
				"정말로 종료하시겠습니까?", "종료", JOptionPane.OK_CANCEL_OPTION);
		if (result == JOptionPane.OK_OPTION) {
			System.exit(0);
		}
	}
}

class TestFrame_this_WindowAdapter extends WindowAdapter {
	private ChatFrame adaptee;
	
	public TestFrame_this_WindowAdapter(ChatFrame adaptee) {
		this.adaptee = adaptee;
	}
	
	@Override
	public void windowClosing(WindowEvent e) {
		adaptee.windowClosing(e);
	}
}