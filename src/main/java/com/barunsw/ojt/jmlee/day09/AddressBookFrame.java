package com.barunsw.ojt.jmlee.day09;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.barunsw.ojt.intf.AddressBookInterface;
import com.barunsw.ojt.jmlee.day07.AddressBookDao;
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
	private JLabel jLabel_Name = new JLabel("이름");
	private JLabel jLabel_Birthday = new JLabel("생일");
	private JLabel jLabel_gender = new JLabel("성별");
	private JLabel jLabel_phoneNumber = new JLabel("전화번호");
	private JLabel jLabel_address = new JLabel("주소");
	// 텍스트 박스 설정
	private JTextField jTextField_Name = new JTextField();
	private JTextField jTextField_Birthday = new JTextField();
	private JTextField jTextField_gender = new JTextField();
	private JTextField jTextField_phoneNumber = new JTextField();
	private JTextField jTextField_address = new JTextField();
	// 버튼 설정
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
		
		jPanel_Left.add(jLabel_address, new GridBagConstraints(5, 1, 1, 1,
				0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(5, 5, 5, 5),
				0, 0));
		jPanel_Left.add(jTextField_address, new GridBagConstraints(6, 1, 1, 1,
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
		columns.add("생일");
		columns.add("성별");
		columns.add("전화번호");
		columns.add("주소");
		tableModel.setColumnData(columns);
		
		jTable_UserList.setModel(tableModel);
	}
	
	public void initEvent() {
		jButton_Save.addActionListener(new AddressBookFrame_jButton_Save_ActionListener(this));
	}
	
	private void initData() {
		
		List<AddressBookVo> addressBooklist = new ArrayList<>();

		Vector dataList = new Vector<>();
		
		try (SqlSession session = sqlSessionFactory.openSession()) {
			AddressBookDao mapper = session.getMapper(AddressBookDao.class);
			
				addressBooklist = mapper.selectAddressBookList(new AddressBookVo());
			
			for (AddressBookVo s : addressBooklist) {
				LOGGER.info("onePerson" + s);
				Vector data = new Vector();
				data.add(s.getSeqNum());
				data.add(s.getName());
				data.add(s.getBirthday());
				data.add(s.getGender());
				data.add(s.getPhoneNumber());
				data.add(s.getAddress());
				
				dataList.add(data);
			}
			
			tableModel.setData(dataList);
			tableModel.fireTableDataChanged();
			
		} catch (Exception ex) {
			LOGGER.debug(ex.getMessage(), ex);
		}
		
	}
	
	public void jButton_Save_actionPerformed(ActionEvent e) {
		String seqNum = jTextField_Name.getText();
		String name = jTextField_Name.getText();
		String birthday= jTextField_Birthday.getText();
		String gender = jTextField_gender.getText();
		String phoneNumber = jTextField_phoneNumber.getText();
		String address = jTextField_address.getText();
		Vector tableData = new Vector();
		Vector oneRowInfo = new Vector();
		oneRowInfo.add(tableModel.getRowCount());
		oneRowInfo.add(name);
		oneRowInfo.add(birthday);
		oneRowInfo.add(gender);
		oneRowInfo.add(phoneNumber);
		oneRowInfo.add(address);
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


