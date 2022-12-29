package com.barunsw.ojt.yhkim.day13;

import javax.swing.JFrame;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DBExplorerFrame extends JFrame {
	private static Logger LOGGER = LoggerFactory.getLogger(DBExplorerFrame.class);

	public static final int WIDTH 	= 800;
	public static final int HEIGHT 	= 600;
	
	private DBExplorerDialog dbExplorerDialog;
	private DBExplorerImpl dbExplorerImpl;
	private DBExplorerPanel dbExplorerPanel;

	
	public DBExplorerFrame() {
		try {
			initComponent();
		}
		catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
		}
	}

	private void initComponent() throws Exception {
		dbExplorerImpl = new DBExplorerImpl();
		
		dbExplorerDialog = new DBExplorerDialog(this);		
		dbExplorerDialog.setSize(dbExplorerDialog.WIDTH, dbExplorerDialog.HEIGHT);
		dbExplorerDialog.setLocationRelativeTo(null);
		dbExplorerDialog.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		dbExplorerDialog.setVisible(true);	
		
		dbExplorerPanel = new DBExplorerPanel(dbExplorerImpl);
		this.setContentPane(dbExplorerPanel);				
		this.setTitle("﻿DB Explorer");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	void setDBInfo(String name, String id, String pw) {
		dbExplorerImpl.setInfo(name, id, pw);
	}

}
