package com.barunsw.ojt.day09;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AddressBookFrame extends JFrame {
	private static Logger LOGGER = LoggerFactory.getLogger(AddressBookFrame.class);
	
	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;

	private JPanel jPanel_Main = new JPanel();
	private JPanel jPanel_Left = new JPanel();
	private JPanel jPanel_Right = new JPanel();
	
	private JLabel jLabel_Name = new JLabel("이름");
	private JLabel jLabel_Gendor = new JLabel("성별");
	private JLabel jLabel_Email = new JLabel("E-mail");
	private JLabel jLabel_Phone = new JLabel("Phone");
	
	private JTextField jTextField_Name = new JTextField();
	private JTextField jTextField_Gendor = new JTextField();
	private JTextField jTextField_Email = new JTextField();
	private JTextField jTextField_Phone = new JTextField();
	
	private JButton jButton_Save = new JButton("저장");
	private JButton jButton_Delete = new JButton("삭제");
	
	private JScrollPane jScrollPane_Userlist = new JScrollPane();
	private JTable jTable_UserList = new JTable();
	
	private CommonTableModel tableModel = new CommonTableModel();
	
	public AddressBookFrame() {
		try {
			initComponent();
			initTable();
			initEvent();
			initData();
		}
		catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
		}
	}
	

	private void initComponent() {
		this.setTitle("주소록");
		
		jLabel_Name.setPreferredSize(new Dimension(100, 30));
		jLabel_Name.setHorizontalAlignment(JLabel.CENTER);
		jTextField_Name.setPreferredSize(new Dimension(400, 30));
		
		jLabel_Gendor.setPreferredSize(new Dimension(100, 30));
		jLabel_Gendor.setHorizontalAlignment(JLabel.CENTER);
		jTextField_Gendor.setPreferredSize(new Dimension(400, 30));
		
		jLabel_Email.setPreferredSize(new Dimension(100, 30));
		jLabel_Email.setHorizontalAlignment(JLabel.CENTER);
		jTextField_Email.setPreferredSize(new Dimension(400, 30));
		
		jLabel_Phone.setPreferredSize(new Dimension(100, 30));
		jLabel_Phone.setHorizontalAlignment(JLabel.CENTER);
		jTextField_Phone.setPreferredSize(new Dimension(400, 30));
		
		jPanel_Main.setLayout(new GridBagLayout());
		
		
		jPanel_Left.setLayout(new GridBagLayout());
		jPanel_Right.setLayout(new GridBagLayout());
		
		jPanel_Left.add(jLabel_Name, new GridBagConstraints(0, 0, 1, 1,
				1.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(5, 5, 5, 5),
				0, 0));
		jPanel_Left.add(jTextField_Name, new GridBagConstraints(1, 0, 1, 1,
				1.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(5, 5, 5, 5),
				0, 0));
		
		jPanel_Left.add(jLabel_Gendor, new GridBagConstraints(2, 0, 1, 1,
				0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(5, 5, 5, 5),
				0, 0));
		jPanel_Left.add(jTextField_Gendor, new GridBagConstraints(3, 0, 1, 1,
				1.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(5, 5, 5, 5),
				0, 0));
		
		jPanel_Left.add(jLabel_Email, new GridBagConstraints(0, 1, 1, 1,
				0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(5, 5, 5, 5),
				0, 0));
		jPanel_Left.add(jTextField_Email, new GridBagConstraints(1, 1, 1, 1,
				1.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(5, 5, 5, 5),
				0, 0));
		
		jPanel_Left.add(jLabel_Phone, new GridBagConstraints(2, 1, 1, 1,
				0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(5, 5, 5, 5),
				0, 0));
		jPanel_Left.add(jTextField_Phone, new GridBagConstraints(3, 1, 1, 1,
				1.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(5, 5, 5, 5),
				0, 0));
		
		jPanel_Right.add(jButton_Save, new GridBagConstraints(0, 0, 1, 1,
				0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(5, 5, 5, 5),
				0, 0));
		jPanel_Right.add(jButton_Delete, new GridBagConstraints(0, 1, 1, 1,
				0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(5, 5, 5, 5),
				0, 0));
		
		jPanel_Main.add(jPanel_Left, new GridBagConstraints(0, 0, 1, 1,
				0.8, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(5, 5, 5, 5),
				0, 0));
		jPanel_Main.add(jPanel_Right, new GridBagConstraints(1, 0, 1, 1,
				0.2, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(5, 5, 5, 5),
				0, 0));
		jPanel_Main.add(jScrollPane_Userlist, new GridBagConstraints(0, 1, 2, 1,
				1.0, 1.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 0, 0, 0),
				0, 0));
		//jTable_UserList.setPreferredSize(new Dimension(800, 600));
		jScrollPane_Userlist.getViewport().setView(jTable_UserList);
		
		this.setContentPane(jPanel_Main);
		
	}
	
	public void initTable() {
		Vector columns = new Vector();
		columns.add("순번");
		columns.add("이름");
		columns.add("성별");
		columns.add("E-mail");
		columns.add("Phone");
		tableModel.setColumnData(columns);
		
		jTable_UserList.setModel(tableModel);
	}
	
	public void initEvent() {
		jButton_Save.addActionListener(new AddressBookFrame_jButton_Save_ActionListener(this));
	}
	
	private void initData() {
		// TODO 처음에 DB 테이블 리스트를 조회해서 jTable에 구성
		
	}
	
	public void jButton_Save_actionPerformed(ActionEvent e) {
		String name = jTextField_Name.getText();
		String gendor = jTextField_Gendor.getText();
		String email = jTextField_Email.getText();
		String phone = jTextField_Phone.getText();
		Vector tableData = new Vector();
		Vector oneRowInfo = new Vector();
		oneRowInfo.add(tableModel.getRowCount());
		oneRowInfo.add(name);
		oneRowInfo.add(gendor);
		oneRowInfo.add(email);
		oneRowInfo.add(phone);
		tableData.add(oneRowInfo);
		tableModel.setData(tableData);
		tableModel.fireTableDataChanged();
	}
}

class AddressBookFrame_jButton_Save_ActionListener implements ActionListener {
	private AddressBookFrame adaptee;
	
	public AddressBookFrame_jButton_Save_ActionListener(AddressBookFrame adaptee) {
		this.adaptee = adaptee;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		adaptee.jButton_Save_actionPerformed(e);
	}
}


