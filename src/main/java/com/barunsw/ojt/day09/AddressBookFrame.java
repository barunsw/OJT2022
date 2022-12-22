package com.barunsw.ojt.day09;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AddressBookFrame extends JFrame {
	private static Logger LOGGER = LoggerFactory.getLogger(AddressBookFrame.class);
	
	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;

	private JPanel jPanel_Main = new JPanel();
	
	public AddressBookFrame() {
		try {
			initComponent();
		}
		catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
		}
	}
	
	private void initComponent() {
		this.setTitle("주소록");
		
		this.setContentPane(jPanel_Main);
		
		JTextField jTextField_Name = new JTextField();
		jTextField_Name.setPreferredSize(new Dimension(100, 22));
		
		jPanel_Main.add(jTextField_Name);
		jPanel_Main.add(new JButton("생성"));
	}
}
