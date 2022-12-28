package com.barunsw.ojt.jmlee.day13;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileExplorerFrame extends JFrame {

	private static Logger LOGGER = LoggerFactory.getLogger(FileExplorerFrame.class);
	
	private FileExplorerPanel panel = new FileExplorerPanel();
	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
	
	public FileExplorerFrame() {
		try {
			initComponent();
		} catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
		}
	}

	private void initComponent() {
		this.setTitle("File Explorer");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.add(panel);
		this.addWindowListener(new FileExplorerFrame_this_WindowAdapter(this));
	}

	void windowClosing(WindowEvent e) {
		LOGGER.debug("windowClosing");
		
		int result = JOptionPane.showConfirmDialog(FileExplorerFrame.this, 
				"정말로 종료하시겠습니까?", "종료", JOptionPane.OK_CANCEL_OPTION);
		if (result == JOptionPane.OK_OPTION) {
			System.exit(0);
		}
	}

		class FileExplorerFrame_this_WindowAdapter extends WindowAdapter {
		private FileExplorerFrame adaptee;
		
		public FileExplorerFrame_this_WindowAdapter(FileExplorerFrame adaptee) {
			this.adaptee = adaptee;
		}
		
		@Override
		public void windowClosing(WindowEvent e) {
			adaptee.windowClosing(e);
		}
	}
}