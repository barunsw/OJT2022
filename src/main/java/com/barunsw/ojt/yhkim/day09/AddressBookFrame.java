package com.barunsw.ojt.yhkim.day09;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SpringLayout;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.barunsw.ojt.constants.Gender;
import com.barunsw.ojt.day09.CommonTableModel;
import com.barunsw.ojt.intf.AddressBookInterface;
import com.barunsw.ojt.vo.AddressBookVo;
import com.barunsw.ojt.yhkim.day09.MybatisAddressBookImpl;

public class AddressBookFrame extends JFrame {
	private static Logger LOGGER = LoggerFactory.getLogger(AddressBookFrame.class);
	
	private AddressBookInterface addressBook = new MybatisAddressBookImpl();

	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
	
	private JPanel jPanel_Main = new JPanel();
	private JPanel jPanel_Left = new JPanel();
	private JPanel jPanel_Right = new JPanel();
	
	private JLabel jLabel_SeqNum = new JLabel("번호");
	private JLabel jLabel_Name = new JLabel("이름");
	private JLabel jLabel_Birthday = new JLabel("생일");
	private JLabel jLabel_Gender = new JLabel("성별");
	private JLabel jLabel_PhoneNumber = new JLabel("Phone");
	private JLabel jLabel_Address = new JLabel("주소");

	private JTextField jTextField_SeqNum = new JTextField();
	private JTextField jTextField_Name = new JTextField();
	private JTextField jTextField_Birthday = new JTextField();
	private JTextField jTextField_Gender = new JTextField();
	private JTextField jTextField_PhoneNumber = new JTextField();
	private JTextField jTextField_Address = new JTextField();
	
	private JButton jButton_Save = new JButton("저장");
	private JButton jButton_Delete = new JButton("삭제");
	private JButton jButton_Update = new JButton("수정");
	private JButton jButton_Reset = new JButton("비우기");

	
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
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		jLabel_SeqNum.setPreferredSize(new Dimension(100, 30));
		jLabel_SeqNum.setHorizontalAlignment(JLabel.CENTER);
		jTextField_SeqNum.setPreferredSize(new Dimension(400, 30));
		jTextField_SeqNum.setEditable(false);
		
		jLabel_Name.setPreferredSize(new Dimension(100, 30));
		jLabel_Name.setHorizontalAlignment(JLabel.CENTER);
		jTextField_Name.setPreferredSize(new Dimension(400, 30));
		
		jLabel_Birthday.setPreferredSize(new Dimension(100, 30));
		jLabel_Birthday.setHorizontalAlignment(JLabel.CENTER);
		jTextField_Birthday.setPreferredSize(new Dimension(400, 30));

		jLabel_Gender.setPreferredSize(new Dimension(100, 30));
		jLabel_Gender.setHorizontalAlignment(JLabel.CENTER);
		jTextField_Gender.setPreferredSize(new Dimension(400, 30));
	
		jLabel_PhoneNumber.setPreferredSize(new Dimension(100, 30));
		jLabel_PhoneNumber.setHorizontalAlignment(JLabel.CENTER);
		jTextField_PhoneNumber.setPreferredSize(new Dimension(400, 30));
		
		jLabel_Address.setPreferredSize(new Dimension(100, 30));
		jLabel_Address.setHorizontalAlignment(JLabel.CENTER);
		jTextField_Address.setPreferredSize(new Dimension(400, 30));

		
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
		
		jPanel_Left.add(jLabel_Gender, new GridBagConstraints(2, 0, 1, 1,
				0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(5, 5, 5, 5),
				0, 0));
		jPanel_Left.add(jTextField_Gender, new GridBagConstraints(3, 0, 1, 1,
				1.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(5, 5, 5, 5),
				0, 0));
		
		jPanel_Left.add(jLabel_Birthday, new GridBagConstraints(0, 1, 1, 1,
				0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(5, 5, 5, 5),
				0, 0));
		jPanel_Left.add(jTextField_Birthday, new GridBagConstraints(1, 1, 1, 1,
				1.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(5, 5, 5, 5),
				0, 0));
		
		jPanel_Left.add(jLabel_PhoneNumber, new GridBagConstraints(2, 1, 1, 1,
				0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(5, 5, 5, 5),
				0, 0));
		jPanel_Left.add(jTextField_PhoneNumber, new GridBagConstraints(3, 1, 1, 1,
				1.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(5, 5, 5, 5),
				0, 0));
		
		jPanel_Left.add(jLabel_SeqNum, new GridBagConstraints(0, 2, 1, 1,
				0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(5, 5, 5, 5),
				0, 0));
		jPanel_Left.add(jTextField_SeqNum, new GridBagConstraints(1, 2, 1, 1,
				1.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(5, 5, 5, 5),
				0, 0));
		
		jPanel_Left.add(jLabel_Address, new GridBagConstraints(2, 2, 1, 1,
				0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(5, 5, 5, 5),
				0, 0));
		jPanel_Left.add(jTextField_Address, new GridBagConstraints(3, 2, 1, 1,
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
		jPanel_Right.add(jButton_Update, new GridBagConstraints(0, 2, 1, 1,
				0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(5, 5, 5, 5),
				0, 0));
		jPanel_Right.add(jButton_Reset, new GridBagConstraints(0, 3, 1, 1,
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
		columns.add("생일");
		columns.add("성별");
		columns.add("전화번호");
		columns.add("주소");
		
		tableModel.setColumnData(columns);
		jTable_UserList.setModel(tableModel);
	}
	
	public void initEvent() { 
		jButton_Save.addActionListener(new AddressBookFrame_jButton_Save_ActionListener(this));
		jButton_Delete.addActionListener(new AddressBookFrame_jButton_Delete_ActionListener(this));
		jButton_Update.addActionListener(new AddressBookFrame_jButton_Update_ActionListener(this));
		jButton_Reset.addActionListener(new AddressBookFrame_jButton_Reset_ActionListener(this));
		jTable_UserList.addMouseListener(new AddressBookFrame_jTable_Result_MouseAdapter(this));
	}
	
	private void initData() {
		// TODO 처음에 DB 테이블 리스트를 조회해서 jTable에 구성
		List<AddressBookVo> addressBookList = addressBook.selectAddressBook(null);
		
		Vector dataline = new Vector<>();
		
		for (AddressBookVo a : addressBookList) {
			Vector v = new Vector();
			v.add(a.getSeqNum());
			v.add(a.getName());
			v.add(a.getBirthday());
			v.add(a.getGender().toString());
			v.add(a.getPhoneNumber());
			v.add(a.getAddress());
			
			dataline.add(v);
		}
		
		tableModel.setData(dataline);
		tableModel.fireTableDataChanged();
	}
	
	public void textFieldReset() {
		jTextField_SeqNum.setText(null);
		jTextField_Name.setText(null);
		jTextField_Birthday.setText(null);
		jTextField_Gender.setText(null);
		jTextField_PhoneNumber.setText(null);
		jTextField_Address.setText(null);
	}
	
	public void jButton_Save_actionPerformed(ActionEvent e) {
		String name = jTextField_Name.getText();
		String birthday = jTextField_Birthday.getText();
		String gender = jTextField_Gender.getText();
		String phoneNumber = jTextField_PhoneNumber.getText();
		String address = jTextField_Address.getText();
		
		AddressBookVo b = new AddressBookVo();
		b.setName(name);
		b.setBirthday(birthday);
		b.setGender(Gender.getGender(gender));
		b.setPhoneNumber(phoneNumber);
		b.setAddress(address);
		
		try {			
			addressBook.insertAddressBook(b);
			initData();
			textFieldReset();
		}
		catch (Exception ex) {
			LOGGER.debug(ex.toString());
		}
	}
	
	public void jButton_Delete_actionPerformed(ActionEvent e) {
		int seqNum = Integer.parseInt(jTextField_SeqNum.getText());
		AddressBookVo b = new AddressBookVo();
		b.setSeqNum(seqNum);

		try {
			addressBook.deleteAddressBook(b);	
			initData();
			textFieldReset();
		}
		catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
		}
		
	}
	
	public void jButton_Update_actionPerformed(ActionEvent e) {
		
		int seqNum = Integer.parseInt(jTextField_SeqNum.getText());
		String name = jTextField_Name.getText();
		String birthday = jTextField_Birthday.getText();
		String gender = jTextField_Gender.getText();
		String phoneNumber = jTextField_PhoneNumber.getText();
		String address = jTextField_Address.getText();
		
		AddressBookVo b = new AddressBookVo();
		b.setSeqNum(seqNum);
		b.setName(name);
		b.setBirthday(birthday);
		b.setGender(Gender.getGender(gender));
		b.setPhoneNumber(phoneNumber);
		b.setAddress(address);
		
		try {
			addressBook.updateAddressBook(b);
			initData();
			textFieldReset();
		}
		catch (Exception ex) {
			LOGGER.debug(ex.getMessage(), ex);
		}
	}
	
	public void jButton_Reset_actionPerformed(ActionEvent e) {
		textFieldReset();
	}
	
	public void jTable_Result_mouseReleased() {
		int selectedRow = jTable_UserList.getSelectedRow();
		
		if(selectedRow >= 0) {
			int seqNum = (int) jTable_UserList.getValueAt(selectedRow, 0);
			String name = (String) jTable_UserList.getValueAt(selectedRow, 1);
			String birthday = (String)jTable_UserList.getValueAt(selectedRow, 2);
			String gender = (String)jTable_UserList.getValueAt(selectedRow, 3);
			String phoneNumber = (String)jTable_UserList.getValueAt(selectedRow, 4);
			String address = (String)jTable_UserList.getValueAt(selectedRow, 5);
			
			jTextField_SeqNum.setText(Integer.toString(seqNum));
			jTextField_Name.setText(name);
			jTextField_Birthday.setText(birthday);
			jTextField_Gender.setText(gender);
			jTextField_PhoneNumber.setText(phoneNumber);
			jTextField_Address.setText(address);
		}
		
	}
	
}

class AddressBookFrame_jButton_Save_ActionListener implements ActionListener {
	private AddressBookFrame  adaptee;	
	
	public AddressBookFrame_jButton_Save_ActionListener(AddressBookFrame adaptee) {
		this.adaptee = adaptee;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		adaptee.jButton_Save_actionPerformed(e);
	}
}

class AddressBookFrame_jButton_Delete_ActionListener implements ActionListener {
	private AddressBookFrame  adaptee;	
	
	public AddressBookFrame_jButton_Delete_ActionListener(AddressBookFrame adaptee) {
		this.adaptee = adaptee;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		adaptee.jButton_Delete_actionPerformed(e);
	}
}

class AddressBookFrame_jButton_Update_ActionListener implements ActionListener {
	private AddressBookFrame  adaptee;	
	
	public AddressBookFrame_jButton_Update_ActionListener(AddressBookFrame adaptee) {
		this.adaptee = adaptee;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		adaptee.jButton_Update_actionPerformed(e);
	}
}

class AddressBookFrame_jButton_Reset_ActionListener implements ActionListener {
	private AddressBookFrame  adaptee;	
	
	public AddressBookFrame_jButton_Reset_ActionListener(AddressBookFrame adaptee) {
		this.adaptee = adaptee;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		adaptee.jButton_Reset_actionPerformed(e);
	}
}
class AddressBookFrame_jTable_Result_MouseAdapter extends MouseAdapter {
	private AddressBookFrame  adaptee;	
	
	public AddressBookFrame_jTable_Result_MouseAdapter(AddressBookFrame adaptee) {
		this.adaptee = adaptee;
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		adaptee.jTable_Result_mouseReleased();
	}

}