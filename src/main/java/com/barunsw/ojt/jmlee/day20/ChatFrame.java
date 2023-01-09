package com.barunsw.ojt.jmlee.day20;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ChatFrame extends JFrame {

	private static Logger LOGGER = LoggerFactory.getLogger(ChatFrame.class);
	
	private ChatPanel panel = new ChatPanel();
	public static final int WIDTH = 400;
	public static final int HEIGHT = 500;
	
	public ChatFrame() {
		try {
			initComponent();
			initEvent();
		} catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
		}
	}

	private void initEvent() {
		this.addWindowListener(new ClientFrame_this_WindowAdapter(this));
	}

	private void initComponent() {
		this.setTitle("RMI Chat");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.add(panel);
	}

	void windowClosing(WindowEvent e) {
		LOGGER.debug("windowClosing");
		
		int result = JOptionPane.showConfirmDialog(ChatFrame.this, 
				"정말로 종료하시겠습니까?", "종료", JOptionPane.OK_CANCEL_OPTION);
		if (result == JOptionPane.OK_OPTION) {
			System.exit(0);
		}
	}

	class ClientFrame_this_WindowAdapter extends WindowAdapter {
		private ChatFrame adaptee;
		
		public ClientFrame_this_WindowAdapter(ChatFrame adaptee) {
			this.adaptee = adaptee;
		}
		
		@Override
		public void windowClosing(WindowEvent e) {
			adaptee.windowClosing(e);
		}
	}
}
