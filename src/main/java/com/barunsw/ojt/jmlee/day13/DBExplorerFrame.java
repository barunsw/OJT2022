package com.barunsw.ojt.jmlee.day13;

import javax.swing.JFrame;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DBExplorerFrame extends JFrame {
	private static Logger LOGGER = LoggerFactory.getLogger(DBExplorerFrame.class);

	public static final int WIDTH 	= 800;
	public static final int HEIGHT 	= 600;
	
	private DBExplorerPanel dbExplorerPanel = new DBExplorerPanel();
	
	public DBExplorerFrame() {
		try {
			initComponent();
		}
		catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
		}
	}
	
	private void initComponent() throws Exception {
		this.setTitle("﻿DB Explorer");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setContentPane(dbExplorerPanel);
	}

}
