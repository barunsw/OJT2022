package com.barunsw.ojt.jmlee.day21;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ClientFrame extends JFrame {
	private static final Logger LOGGER = LogManager.getLogger(ClientFrame.class);
	
	public static final int WIDTH 	= 870;
	public static final int HEIGHT 	= 635;
	
	private ClientPanel clientPanel = new ClientPanel();
	
	public ClientFrame() {
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

		this.setContentPane(clientPanel);
		
		// 윈도우 이벤트
		this.addWindowListener(new TestFrame_this_WindowAdapter(this));
	}
	
	void windowClosing(WindowEvent e) {
		LOGGER.debug("windowClosing");
		
		int result = JOptionPane.showConfirmDialog(ClientFrame.this, 
				"정말로 종료하시겠습니까?", "종료", JOptionPane.OK_CANCEL_OPTION);
		if (result == JOptionPane.OK_OPTION) {
			ClientMain.eventQueueWorker.removeEventListener(clientPanel);
			ClientMain.eventQueueWorker.close();
			System.exit(0);
		}
	}
}

class TestFrame_this_WindowAdapter extends WindowAdapter {
	private ClientFrame adaptee;
	
	public TestFrame_this_WindowAdapter(ClientFrame adaptee) {
		this.adaptee = adaptee;
	}
	
	@Override
	public void windowClosing(WindowEvent e) {
		adaptee.windowClosing(e);
	}
}