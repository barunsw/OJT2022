package com.barunsw.ojt.jmlee.day15;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ClientFrame extends JFrame {
	private static Logger LOGGER = LoggerFactory.getLogger(ClientFrame.class);
	
	public static final int WIDTH = 500;
	public static final int HEIGHT = 600;
	
	private ClientPanel clientPanel;

	public ClientFrame() {
		try {
			initComponent();
			initEvent();
		}
		catch (Exception ex) {
			LOGGER.error(ex.getMessage());
		}
	}
	
	private void initComponent() {
		clientPanel = new ClientPanel();
		this.setContentPane(clientPanel);
	}
	
	private void initEvent() {
		this.addWindowListener(new ClientFrame_this_WindowAdpater(this));
	}
	
	void windowClosing(WindowEvent e) {	
		int result = JOptionPane.showConfirmDialog(ClientFrame.this, 
				"정말로 종료하시겠습니까?", "종료", JOptionPane.OK_CANCEL_OPTION);
		if (result == JOptionPane.OK_OPTION) {
			clientPanel.stopClient();
			System.exit(0);
		}
	}
}

class ClientFrame_this_WindowAdpater extends WindowAdapter {
	private ClientFrame adaptee;
	
	public ClientFrame_this_WindowAdpater(ClientFrame adaptee) {
		this.adaptee = adaptee;
	}
	
	@Override
	public void windowClosing(WindowEvent e) {
		adaptee.windowClosing(e);
	}
}
