package com.barunsw.ojt.yhkim.day13;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DBExplorerDialog extends JDialog {
	private static Logger LOGGER = LoggerFactory.getLogger(DBExplorerDialog.class);

	public static final int WIDTH 	= 400;
	public static final int HEIGHT 	= 200;
	
	private JLabel jLabel_Name = new JLabel("DB명");
	private JLabel jLabel_Id = new JLabel("아이디");
	private JLabel jLabel_Pw = new JLabel("패스워드");
	
	private JTextField jTextField_Name = new JTextField();
	private JTextField jTextField_Id = new JTextField();
	private JTextField jTextField_Pw = new JTextField();
	
	private JButton jButton_Save = new JButton("접속");
	
	private DBExplorerFrame frame;
	
	public DBExplorerDialog(DBExplorerFrame frame) {
		super(frame, "DB접속", true);
		this.frame = frame;		
		
		try {
			initComponent();
			initEvent();
		}
		catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
		}
		
	}
	
	private void initComponent() throws Exception {
		this.setLayout(new GridBagLayout());

		jLabel_Name.setPreferredSize(new Dimension(50, 30));
		jLabel_Name.setHorizontalAlignment(JLabel.CENTER);
		jTextField_Name.setPreferredSize(new Dimension(100, 30));
		
		jLabel_Id.setPreferredSize(new Dimension(50, 30));
		jLabel_Id.setHorizontalAlignment(JLabel.CENTER);
		jTextField_Id.setPreferredSize(new Dimension(100, 30));

		jLabel_Pw.setPreferredSize(new Dimension(50, 30));
		jLabel_Pw.setHorizontalAlignment(JLabel.CENTER);
		jTextField_Pw.setPreferredSize(new Dimension(100, 30));

		this.add(jLabel_Name, 
				new GridBagConstraints(0, 0, 1, 1,
				0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(5, 5, 5, 5),
				0, 0));
		this.add(jTextField_Name, 
				new GridBagConstraints(1, 0, 1, 1,
				1.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(5, 5, 5, 5),
				0, 0));
		this.add(jLabel_Id, 
				new GridBagConstraints(0, 1, 1, 1,
				0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(5, 5, 5, 5),
				0, 0));
		this.add(jTextField_Id, 
				new GridBagConstraints(1, 1, 1, 1,
				1.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(5, 5, 5, 5),
				0, 0));
		this.add(jLabel_Pw, 
				new GridBagConstraints(0, 2, 1, 1,
				0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(5, 5, 5, 5),
				0, 0));
		this.add(jTextField_Pw, 
				new GridBagConstraints(1, 2, 1, 1,
				1.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(5, 5, 5, 5),
				0, 0));		
		this.add(jButton_Save, 
				new GridBagConstraints(0, 3, 2, 1,
				1.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(5, 5, 5, 5),
				0, 0));		
	}
	
	private void initEvent() {
		jButton_Save.addActionListener(new DBExplorerDialog_jButton_Save_ActionListener(this));
		this.addWindowListener(new DBExplorerDialog_this_WindowAdapter(this));
	}
	
	void jButton_Save_actionPerformed(ActionEvent e) {
		String name = jTextField_Name.getText();
		String id = jTextField_Id.getText();
		String pw = jTextField_Pw.getText();
		
		frame.setDBInfo(name, id, pw);
		
		dispose();
	}
	
	void windowClosing(WindowEvent e) {	
		int result = JOptionPane.showConfirmDialog(DBExplorerDialog.this, 
				"정말로 종료하시겠습니까?", "종료", JOptionPane.OK_CANCEL_OPTION);
		if (result == JOptionPane.OK_OPTION) {
			System.exit(0);
		}
	}
}

class DBExplorerDialog_jButton_Save_ActionListener implements ActionListener {
	private DBExplorerDialog  adaptee;	
	
	public DBExplorerDialog_jButton_Save_ActionListener(DBExplorerDialog adaptee) {
		this.adaptee = adaptee;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		adaptee.jButton_Save_actionPerformed(e);
	}
}

class DBExplorerDialog_this_WindowAdapter extends WindowAdapter {
	private DBExplorerDialog adaptee;
	
	public DBExplorerDialog_this_WindowAdapter(DBExplorerDialog adaptee) {
		this.adaptee = adaptee;
	}
	
	@Override
	public void windowClosing(WindowEvent e) {
		adaptee.windowClosing(e);
	}
}

