package com.barunsw.ojt.jmlee.day10;

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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



public class TestPanel extends JPanel {
	private static final Logger LOGGER = LogManager.getLogger(TestPanel.class);

	private GridBagLayout gridBagLayout = new GridBagLayout();

	
	// 프레임 설정
	private JPanel jPanel_Main = new JPanel();
	private JPanel jPanel_Top = new JPanel();
	private JPanel jPanel_Bottom = new JPanel();
	private JTree jTree_Result = new JTree();
	
	// 라벨설정
	private JLabel jLabel_group_id = new JLabel("그룹 ID");
	private JLabel jLabel_group_name = new JLabel("그룹명");
	private JLabel jLabel_parent_group_id = new JLabel("상위 그룹명");
	// 텍스트 박스 설정
	private JTextField jTextField_group_id = new JTextField();
	private JTextField jTextField_group_name = new JTextField();
	private JTextField jTextField_parent_group_id = new JTextField();
	// 버튼 설정
	private JButton jButton_Save = new JButton("저장");
	private JButton jButton_Update = new JButton("수정");
	private JButton jButton_Delete = new JButton("삭제");
	
	private JScrollPane jScrollPane_Tree = new JScrollPane();
	
	private DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode("root");
	private DefaultTreeModel treeModel = new DefaultTreeModel(rootNode);
	
	private GroupInterface addressBookIf;
	
	
	public TestPanel() {
		try {
			initAddressBookIf();
			initComponent();
			initTree();
			initData();
			initEvent();
		}
		catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
		}
	}
	
	private void initAddressBookIf() throws Exception {
		String className = SwingTest.properties.getProperty("address_if_class");
		
		LOGGER.debug("className:" + className);
				
		Object o = Class.forName(className).newInstance();
		if (o instanceof GroupInterface) {
			addressBookIf = (GroupInterface)o; 
		}
	}
	
	private void initComponent() {
		this.setLayout(gridBagLayout);
		
		jLabel_group_id.setPreferredSize(new Dimension(100, 30));
		jLabel_group_id.setHorizontalAlignment(JLabel.CENTER);
		jTextField_group_id.setPreferredSize(new Dimension(400, 30));
		
		
		jLabel_group_name.setPreferredSize(new Dimension(100, 30));
		jLabel_group_name.setHorizontalAlignment(JLabel.CENTER);
		jTextField_group_name.setPreferredSize(new Dimension(400, 30));
		
		jLabel_parent_group_id.setPreferredSize(new Dimension(100, 30));
		jLabel_parent_group_id.setHorizontalAlignment(JLabel.CENTER);
		jTextField_parent_group_id.setPreferredSize(new Dimension(400, 30));
		
		jPanel_Main.setLayout(gridBagLayout);
		jPanel_Top.setLayout(gridBagLayout);
		jPanel_Bottom.setLayout(gridBagLayout);
		
		jPanel_Top.add(jLabel_group_name, new GridBagConstraints(0, 0, 1, 1,
				0.2, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH, 
				new Insets(5, 5, 5, 5),
				0, 0));
		jPanel_Top.add(jTextField_group_name, new GridBagConstraints(1, 0, 1, 1,
				1.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH, 
				new Insets(5, 5, 5, 5),
				0, 0));
		jPanel_Top.add(jLabel_parent_group_id, new GridBagConstraints(0, 1, 1, 1,
				0.2, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH, 
				new Insets(5, 5, 5, 5),
				0, 0));
		jPanel_Top.add(jTextField_parent_group_id, new GridBagConstraints(1, 1, 1, 1,
				1.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH, 
				new Insets(5, 5, 5, 5),
				0, 0));
		jPanel_Top.add(jLabel_group_id, new GridBagConstraints(0, 2, 1, 1,
				0.2, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH, 
				new Insets(5, 5, 5, 5),
				0, 0));
		jPanel_Top.add(jTextField_group_id, new GridBagConstraints(1, 2, 1, 1,
				1.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH, 
				new Insets(5, 5, 5, 5),
				0, 0));
		
		//버튼
		jPanel_Bottom.add(jButton_Save, new GridBagConstraints(0, 0, 1, 1,
				1.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(5, 5, 5, 5),
				0, 0));
		jPanel_Bottom.add(jButton_Update, new GridBagConstraints(1, 0, 1, 1,
				1.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(5, 5, 5, 5),
				0, 0));
		jPanel_Bottom.add(jButton_Delete, new GridBagConstraints(2, 0, 1, 1,
				1.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(5, 5, 5, 5),
				0, 0));
		
		jPanel_Main.add(jPanel_Top, new GridBagConstraints(0, 0, 1, 1,
				1.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(5, 5, 5, 5),
				0, 0));
		jPanel_Main.add(jPanel_Bottom, new GridBagConstraints(0, 1, 1, 1,
				1.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(5, 5, 5, 5),
				0, 0));
		
		this.add(jScrollPane_Tree, new GridBagConstraints(0, 0, 1, 1,
				1.0, 1.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(5, 5, 5, 5),
				0, 0));
		this.add(jPanel_Main, new GridBagConstraints(1, 0, 1, 1,
				0.6, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH, 
				new Insets(5, 5, 5, 5),
				0, 0));
		
		jScrollPane_Tree.getViewport().add(jTree_Result);
	}
	
	private void initTree() {
		jTree_Result.setModel(treeModel);
		treeModel.setRoot(rootNode);
		// root노드 숨김
		//jTree_Result.setRootVisible(true);
	}
	
	public void resetTree() {
		rootNode.removeAllChildren();
		initData();
	}
	
	public void textFieldReset() {
		jTextField_group_id.setText(null);
		jTextField_group_name.setText(null);
		jTextField_parent_group_id.setText(null);
	}
	
	private void initData() {
		
		List<GroupVo> groupList = addressBookIf.selectGroup(null);
		Vector<DefaultMutableTreeNode> parent = new Vector<>();
		
		for (int i = 0; i < groupList.size(); i++) {
			GroupVo oneGroup = groupList.get(i);
			
			DefaultMutableTreeNode oneNode = new DefaultMutableTreeNode();
			oneNode.setUserObject(oneGroup);
		
			if (oneGroup.getParent_group_id() == 0) { // Parent_group_id가 0을 부모노드로 설정
				parent.add(oneNode);
			}
			else {
				for (int son = 0; son < parent.size(); son++) {
					if (((GroupVo) parent.get(son).getUserObject()).getGroup_id() == oneGroup.getParent_group_id()) {
						parent.get(son).add(oneNode);
						break;
					}
				}
			}
		}
		for (DefaultMutableTreeNode par : parent) {
			rootNode.add(par);
		}
		treeModel.reload();
		
	}
	
	private void initEvent() {
		jButton_Save.addActionListener(new TestPanel_jButton_Save_ActionListener(this));
		jButton_Delete.addActionListener(new TestPanel_jButton_Delete_ActionListener(this));
		jButton_Update.addActionListener(new TestPanel_jButton_Update_ActionListener(this));
		jTree_Result.addMouseListener(new TestPanel_jTree_Result_MouseAdapter(this));
	}
	
	void jTree_Result_mouseReleased(MouseEvent e) {
		//사용자가 tree를 클릭한 경로를 가져온다. 없을시 null
		TreePath tp = jTree_Result.getPathForLocation(e.getX(), e.getY());

		GroupVo msR = new GroupVo();
		
		if (tp != null) {
			jTextField_parent_group_id.setText((tp.getPath()[1].toString()));
			if (tp.getPath().length > 2) {
				msR.setGroup_name((tp.getPath()[2]).toString());
				msR = addressBookIf.selectOneGroup(msR);
				jTextField_group_id.setText(msR.getGroup_id()+"");
				jTextField_group_name.setText((tp.getPath()[2].toString()));
			}
			else {
				jTextField_group_id.setText(null);
				jTextField_group_name.setText("상위 그룹입니다.");
			}
		}
	
	}

	public void jButton_Save_actionPerformed(ActionEvent e) {
<<<<<<< HEAD
		String group = jTextField_group_name.getText();
		String parentGroup = jTextField_parent_group_id.getText();
		
		int num = 0;
		if (!group.equals("")) {
			GroupVo g = new GroupVo();
			g.setGroup_name(parentGroup);
			GroupVo g2 = new GroupVo();
			num = g2.getGroup_id();
		}
		else {
			group = parentGroup;
			num = 0;
		}
		GroupVo oneGroup = new GroupVo();
		oneGroup.setGroup_name(group);
		oneGroup.setParent_group_id(num);
		
=======
		// 입력값 호출
		String groupId = jTextField_group_id.getText();
		String groupName = jTextField_group_name.getText();
		String parentGroup = jTextField_parent_group_id.getText();

		groupVo.setGroup_name(parentGroup);
		
		// 부모정보 입력을 위한 기존정보 호출
		GroupVo g = addressBookIf.selectOneGroup(groupVo);
		int num = g.getGroup_id();
		
		// 추가될 객체 생성
		GroupVo oneGroup = new GroupVo();
		oneGroup.setGroup_id(Integer.parseInt(groupId));
		oneGroup.setGroup_name(groupName);
		oneGroup.setParent_group_id(num); // 노드 : 부모번호 == 자식번호
	
>>>>>>> 3d663cf ([이재민] JTree 일부내용 수정)
		try {
			addressBookIf.insertGroup(oneGroup);
			resetTree();
			textFieldReset();
		} catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
		} 
		
	}

	public void jButton_Delete_actionPerformed(ActionEvent e) {

<<<<<<< HEAD
		String groupId = jTextField_group_id.getText();
		
		GroupVo g = new GroupVo();
		g.setGroup_id(Integer.parseInt(groupId));
=======
		String groupId = jTextField_group_id.getText(); // 텍스트 필드의 그룹ID 호출

		groupVo.setGroup_id(Integer.parseInt(groupId));
>>>>>>> 3d663cf ([이재민] JTree 일부내용 수정)
		
		try {
			addressBookIf.deleteGroup(g);
			resetTree();
			textFieldReset();
		}
		catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
		}
		
	}

	public void jButton_Update_Performed(ActionEvent e) {

		String groupId = jTextField_group_id.getText();
		String groupName = jTextField_group_name.getText();
		String parentGroup = jTextField_parent_group_id.getText();
		
		GroupVo g = new GroupVo();
		g.setGroup_name(parentGroup);
		
		GroupVo g2 = addressBookIf.selectOneGroup(g);
		int num = g2.getGroup_id();
		
		GroupVo oneGroup = new GroupVo();
		oneGroup.setGroup_id(Integer.parseInt(groupId));
		oneGroup.setGroup_name(groupName);
		oneGroup.setParent_group_id(num);
		
		try {
			addressBookIf.updateGroup(oneGroup);
			resetTree();
			textFieldReset();
			
		} catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
		}
	}
	
}

class TestPanel_jTree_Result_MouseAdapter extends MouseAdapter {
	private TestPanel adaptee;
	
	public TestPanel_jTree_Result_MouseAdapter(TestPanel adaptee) {
		this.adaptee = adaptee;
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		adaptee.jTree_Result_mouseReleased(e);
	}
}
	
class TestPanel_jButton_Save_ActionListener implements ActionListener {
	private TestPanel adaptee;
	
	public TestPanel_jButton_Save_ActionListener(TestPanel adaptee) {
		this.adaptee = adaptee;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		adaptee.jButton_Save_actionPerformed(e);
	}
}

class TestPanel_jButton_Delete_ActionListener implements ActionListener {
	private TestPanel adaptee;
	
	public TestPanel_jButton_Delete_ActionListener(TestPanel adaptee) {
		this.adaptee = adaptee;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		adaptee.jButton_Delete_actionPerformed(e);
	}
	
}

class TestPanel_jButton_Update_ActionListener implements ActionListener {
	private TestPanel adaptee;
	
	public TestPanel_jButton_Update_ActionListener(TestPanel adaptee) {
		this.adaptee = adaptee;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		adaptee.jButton_Update_Performed(e);		
	}
}

