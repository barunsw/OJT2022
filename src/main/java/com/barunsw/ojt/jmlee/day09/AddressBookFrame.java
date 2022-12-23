package com.barunsw.ojt.jmlee.day09;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.barunsw.ojt.constants.Gender;
import com.barunsw.ojt.intf.AddressBookInterface;
import com.barunsw.ojt.jmlee.day07.SqlSessionFactoryManager;
import com.barunsw.ojt.vo.AddressBookVo;

public class AddressBookFrame extends JFrame {
	
	
	private static Logger LOGGER = LoggerFactory.getLogger(AddressBookFrame.class);
	
	private static final SqlSessionFactory sqlSessionFactory = SqlSessionFactoryManager.getSqlSessionFactory();
	
	AddressBookInterface addressBook = new SwingAddressBookImpl();
	
	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;

	private JPanel jPanel_Main = new JPanel();
	private JPanel jPanel_Left = new JPanel();
	private JPanel jPanel_Right = new JPanel();
	// 라벨 설정
	private JLabel jLabel_seqNum = new JLabel("순번");
	private JLabel jLabel_Name = new JLabel("이름");
	private JLabel jLabel_Birthday = new JLabel("생일");
	private JLabel jLabel_gender = new JLabel("성별");
	private JLabel jLabel_phoneNumber = new JLabel("전화번호");
	private JLabel jLabel_address = new JLabel("주소");
	// 텍스트 박스 설정
	private JTextField jTextField_seqNum= new JTextField();
	private JTextField jTextField_Name = new JTextField();
	private JTextField jTextField_Birthday = new JTextField();
	private JTextField jTextField_gender = new JTextField();
	private JTextField jTextField_phoneNumber = new JTextField();
	private JTextField jTextField_address = new JTextField();
	// 버튼 설정
	private JButton jButton_Save = new JButton("저장");
	private JButton jButton_Update = new JButton("수정");
	private JButton jButton_Delete = new JButton("삭제");
	private JButton jButton_Reset = new JButton("초기화");
	
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
		setDefaultCloseOperation(EXIT_ON_CLOSE); // 창 닫으면 자동으로 프로세스 종료
	}
	

	private void initComponent() {
		this.setTitle("주소록");
		
		jLabel_seqNum.setPreferredSize(new Dimension(100, 30));
		jLabel_seqNum.setHorizontalAlignment(JLabel.CENTER);
		jTextField_seqNum.setPreferredSize(new Dimension(400, 30));
		jTextField_seqNum.setEnabled(false); // index는 수정할 일이 없으므로 비활성화
		
		jLabel_Name.setPreferredSize(new Dimension(100, 30));
		jLabel_Name.setHorizontalAlignment(JLabel.CENTER);
		jTextField_Name.setPreferredSize(new Dimension(400, 30));
		
		jLabel_Birthday.setPreferredSize(new Dimension(100, 30));
		jLabel_Birthday.setHorizontalAlignment(JLabel.CENTER);
		jTextField_Birthday.setPreferredSize(new Dimension(400, 30));
		
		jLabel_gender.setPreferredSize(new Dimension(100, 30));
		jLabel_gender.setHorizontalAlignment(JLabel.CENTER);
		jTextField_gender.setPreferredSize(new Dimension(400, 30));		
		
		jLabel_phoneNumber.setPreferredSize(new Dimension(100, 30));
		jLabel_phoneNumber.setHorizontalAlignment(JLabel.CENTER);
		jTextField_phoneNumber.setPreferredSize(new Dimension(400, 30));
		
		jLabel_address.setPreferredSize(new Dimension(100, 30));
		jLabel_address.setHorizontalAlignment(JLabel.CENTER);
		jTextField_address.setPreferredSize(new Dimension(400, 30));
		
		jPanel_Main.setLayout(new GridBagLayout());
		
		
		jPanel_Left.setLayout(new GridBagLayout());
		jPanel_Right.setLayout(new GridBagLayout());
		
		jPanel_Left.add(jLabel_seqNum, new GridBagConstraints(0, 0, 1, 1,
				1.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH, 
				new Insets(5, 5, 5, 5),
				0, 0));
		jPanel_Left.add(jTextField_seqNum, new GridBagConstraints(1, 0, 1, 1,
				0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(5, 5, 5, 5),
				0, 0));
		
		jPanel_Left.add(jLabel_Name, new GridBagConstraints(0, 1, 1, 1,
				1.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(5, 5, 5, 5),
				0, 0));
		jPanel_Left.add(jTextField_Name, new GridBagConstraints(1, 1, 1, 1,
				1.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(5, 5, 5, 5),
				0, 0));
		
		jPanel_Left.add(jLabel_Birthday, new GridBagConstraints(0, 2, 1, 1, 
				0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,  
				new Insets(5, 5, 5, 5),
				0, 0));
		jPanel_Left.add(jTextField_Birthday, new GridBagConstraints(1, 2, 1, 1, 
				1.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(5, 5, 5, 5),
				0, 0));
		
		jPanel_Left.add(jLabel_gender, new GridBagConstraints(2, 0, 1, 1,
				0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(5, 5, 5, 5),
				0, 0));
		jPanel_Left.add(jTextField_gender, new GridBagConstraints(3, 0, 1, 1,
				1.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(5, 5, 5, 5),
				0, 0));
		
		jPanel_Left.add(jLabel_phoneNumber, new GridBagConstraints(2, 1, 1, 1,
				0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(5, 5, 5, 5),
				0, 0));
		jPanel_Left.add(jTextField_phoneNumber, new GridBagConstraints(3, 1, 1, 1,
				1.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(5, 5, 5, 5),
				0, 0));
		
		jPanel_Left.add(jLabel_address, new GridBagConstraints(2, 2, 1, 1,
				0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(5, 5, 5, 5),
				0, 0));
		jPanel_Left.add(jTextField_address, new GridBagConstraints(3, 2, 1, 1,
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
		jButton_Update.addActionListener(new AddressBookFrame_jButton_Update_ActionListener(this));
		jButton_Delete.addActionListener(new AddressBookFrame_jButton_Delete_ActionListener(this));
		jButton_Reset.addActionListener(new AddressBookFrame_jButton_Reset_ActionListener(this));
		jTable_UserList.addMouseListener(new AddressBookFrame_jTable_Result_MouseAdapter(this));
		
	}
	
	private void initData() {
		
		List<AddressBookVo> addressBooklist = addressBook.selectAddressBook(null);

		Vector dataList = new Vector<>();
				
			for (AddressBookVo addressbook : addressBooklist) {
				LOGGER.info("addressbook : " + addressbook);
				Vector data = new Vector();
				data.add(addressbook.getSeqNum());
				data.add(addressbook.getName());
				data.add(addressbook.getBirthday());
				data.add(addressbook.getGender());
				data.add(addressbook.getPhoneNumber());
				data.add(addressbook.getAddress());
				
				dataList.add(data);
			}
			
			tableModel.setData(dataList);
			tableModel.fireTableDataChanged();
		
	}
	
	public void textFieldReset() {
		jTextField_seqNum.setText(null);
		jTextField_Name.setText(null);
		jTextField_Birthday.setText(null);
		jTextField_gender.setText(null);
		jTextField_phoneNumber.setText(null);
		jTextField_address.setText(null);
	}
	
	public void jButton_Save_actionPerformed(ActionEvent e) {
		String name = jTextField_Name.getText();
		String birthday= jTextField_Birthday.getText();
		String gender = jTextField_gender.getText();
		String phoneNumber = jTextField_phoneNumber.getText();
		String address = jTextField_address.getText();
		
		AddressBookVo data = new AddressBookVo(); // 객체에서 데이터 호출 후 SET
		data.setName(name);
		data.setBirthday(birthday);
		data.setGender(Gender.getGender(gender));
		data.setPhoneNumber(phoneNumber);
		data.setAddress(address);
		
		try {			
			addressBook.insertAddressBook(data); // 가져온 데이터로 insert 쿼리 실행
			initData();							 // insert 후 데이터 조회
			textFieldReset();				 	 // tesxtFiled 초기화	
		}
		catch (Exception ex) {
			LOGGER.debug(ex.getMessage(), ex);
		}
		
	}
	
	public void jButton_Update_actionPerformed(ActionEvent e) {
		int seqNum = Integer.parseInt(jTextField_seqNum.getText());
		String name = jTextField_Name.getText();
		String birthday = jTextField_Birthday.getText();
		String gender = jTextField_gender.getText();
		String phoneNumber = jTextField_phoneNumber.getText();
		String address = jTextField_address.getText();
		
		AddressBookVo data = new AddressBookVo();
		data.setSeqNum(seqNum);
		data.setName(name);
		data.setBirthday(birthday);
		data.setGender(Gender.getGender(gender));
		data.setPhoneNumber(phoneNumber);
		data.setAddress(address);
		
		try {
			addressBook.updateAddressBook(data);
			initData();
			textFieldReset();
		}
		catch (Exception ex) {
			LOGGER.debug(ex.getMessage(), ex);
		}
	}
	
	
	public void jButton_Delete_actionPerformed(ActionEvent e) {
		int seqNum = Integer.parseInt(jTextField_seqNum.getText());
		AddressBookVo data = new AddressBookVo();
		data.setSeqNum(seqNum);
		
		try {
			addressBook.deleteAddressBook(data);	
			initData();
			textFieldReset();
		}
		catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
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
			String gender = String.valueOf(jTable_UserList.getValueAt(selectedRow, 3));
			String phoneNumber = (String)jTable_UserList.getValueAt(selectedRow, 4);
			String address = (String)jTable_UserList.getValueAt(selectedRow, 5);
			
			jTextField_seqNum.setText(Integer.toString(seqNum));
			jTextField_Name.setText(name);
			jTextField_Birthday.setText(birthday);
			jTextField_gender.setText(gender);
			jTextField_phoneNumber.setText(phoneNumber);
			jTextField_address.setText(address);
		}
		
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

class AddressBookFrame_jButton_Update_ActionListener implements ActionListener {
	private AddressBookFrame adaptee;
	
	public AddressBookFrame_jButton_Update_ActionListener(AddressBookFrame adaptee) {
		this.adaptee = adaptee;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		adaptee.jButton_Update_actionPerformed(e);
		
	}
	
}

class AddressBookFrame_jButton_Delete_ActionListener implements ActionListener {
	private AddressBookFrame adaptee;
	
	public AddressBookFrame_jButton_Delete_ActionListener(AddressBookFrame adaptee) {
		this.adaptee = adaptee;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		adaptee.jButton_Delete_actionPerformed(e);
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


