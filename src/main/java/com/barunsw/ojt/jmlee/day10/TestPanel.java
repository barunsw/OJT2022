package com.barunsw.ojt.jmlee.day10;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
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

import com.barunsw.ojt.constants.Gender;
import com.barunsw.ojt.vo.AddressBookVo;

public class TestPanel extends JPanel {
	private static final Logger LOGGER = LogManager.getLogger(TestPanel.class);
	
	private GridBagLayout gridBagLayout = new GridBagLayout();
	
	private JPanel jPanel_Right = new JPanel();
	private JPanel jPanel_Search = new JPanel();
	private JPanel jPanel_Button = new JPanel();
	
	private JLabel jLabel_GroupId = new JLabel("그룹아이디");
	private JLabel jLabel_Group = new JLabel("그룹명");
	private JLabel jLabel_ParentGroup = new JLabel("상위그룹명");
	
	private JTextField jTextField_GroupId = new JTextField();
	private JTextField jTextField_Group = new JTextField();
	private JTextField jTextField_ParentGroup = new JTextField();
	
	private JButton jButton_Save = new JButton("등록");
	private JButton jButton_Update = new JButton("수정");
	private JButton jButton_Delete = new JButton("삭제");
	private JButton jButton_Reset = new JButton("비우기");

	private JScrollPane jScrollPane_Tree = new JScrollPane();
	
	private JTree jTree_Result = new JTree();
	
	private DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode("그룹");
	private DefaultTreeModel treeModel = new DefaultTreeModel(rootNode);
	
	private GroupInterface groupBookIf;
	
	public TestPanel() {
		try {
			initAddressBookIf();
			initComponent();
			initEvent();
			initTree();
			initData();
		}
		catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
		}
	}
	
	private void initAddressBookIf() throws Exception {
		String className = SwingTest.properties.getProperty("group_if_class");
		
		LOGGER.debug("className:" + className);
				
		Object o = Class.forName(className).newInstance();
		if (o instanceof GroupInterface) {
			groupBookIf = (GroupInterface)o; 
		}
	}
	
	private void initComponent() throws Exception {
		this.setLayout(gridBagLayout);		
		
		jPanel_Right.setLayout(gridBagLayout);
		jPanel_Search.setLayout(gridBagLayout);
		jPanel_Button.setLayout(gridBagLayout);
	
		jPanel_Search.setBackground(Color.cyan);
		jPanel_Button.setBackground(Color.red);
		
		jLabel_GroupId.setPreferredSize(new Dimension(100, 30));
		jLabel_GroupId.setHorizontalAlignment(JLabel.CENTER);
		jTextField_GroupId.setPreferredSize(new Dimension(400, 30));
		jTextField_GroupId.setEditable(false);

		jLabel_Group.setPreferredSize(new Dimension(100, 30));
		jLabel_Group.setHorizontalAlignment(JLabel.CENTER);
		jTextField_Group.setPreferredSize(new Dimension(400, 30));

		jLabel_ParentGroup.setPreferredSize(new Dimension(100, 30));
		jLabel_ParentGroup.setHorizontalAlignment(JLabel.CENTER);
		jTextField_ParentGroup.setPreferredSize(new Dimension(400, 30));

		
		jPanel_Search.add(jLabel_Group, new GridBagConstraints(0, 0, 1, 1,
				1.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(5,5,5,5),
				0, 0));
		jPanel_Search.add(jTextField_Group, new GridBagConstraints(1, 0, 1, 1,
				1.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(5,5,5,5),
				0, 0));
		jPanel_Search.add(jLabel_ParentGroup, new GridBagConstraints(0, 1, 1, 1,
				1.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(5,5,5,5),
				0, 0));
		jPanel_Search.add(jTextField_ParentGroup, new GridBagConstraints(1, 1, 1, 1,
				1.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(5,5,5,5),
				0, 0));
		jPanel_Search.add(jLabel_GroupId, new GridBagConstraints(0, 2, 1, 1,
				1.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(5,5,5,5),
				0, 0));
		jPanel_Search.add(jTextField_GroupId, new GridBagConstraints(1, 2, 1, 1,
				1.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(5,5,5,5),
				0, 0));
		jPanel_Button.add(jButton_Save,  new GridBagConstraints(0, 0, 1, 1,
				1.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(5,5,5,5),
				0, 0));
		jPanel_Button.add(jButton_Update,  new GridBagConstraints(1, 0, 1, 1,
				1.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(5,5,5,5),
				0, 0));
		jPanel_Button.add(jButton_Delete,  new GridBagConstraints(0, 1, 1, 1,
				1.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(5,5,5,5),
				0, 0));
		jPanel_Button.add(jButton_Reset,  new GridBagConstraints(1, 1, 1, 1,
				1.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(5,5,5,5),
				0, 0));
		jPanel_Right.add(jPanel_Search, new GridBagConstraints(0, 0, 1, 1,
				1.0, 0.5,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(5, 5, 5, 5),
				0, 0));
		jPanel_Right.add(jPanel_Button, new GridBagConstraints(0, 1, 1, 1,
				1.0, 0.5,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(5, 5, 5, 5),
				0, 0));
		this.add(jPanel_Right, 
				new GridBagConstraints(1, 0, 1, 1,
						0.5, 1.0,
						GridBagConstraints.CENTER, GridBagConstraints.BOTH,
						new Insets(5, 5, 5, 5),
						0, 0));
		this.add(jScrollPane_Tree, 
				new GridBagConstraints(0, 0, 1, 1,
						0.5, 1.0,
						GridBagConstraints.CENTER, GridBagConstraints.BOTH,
						new Insets(5, 5, 5, 5),
						0, 0));
		
		jScrollPane_Tree.getViewport().add(jTree_Result);
	}
	
	private void initEvent() {
		jButton_Save.addActionListener(new TestPanel_jButton_Save_ActionListener(this));
		jButton_Delete.addActionListener(new TestPanel_jButton_Delete_ActionListener(this));
		jButton_Update.addActionListener(new TestPanel_jButton_Update_ActionListener(this));
		jButton_Reset.addActionListener(new TestPanel_jButton_Reset_ActionListener(this));
		jTree_Result.addMouseListener(new TestPanel_jTree_Result_MouseAdapter(this));
	}
	
	private void initTree() {
		treeModel.setRoot(rootNode);
		jTree_Result.setModel(treeModel);
		// root노드 숨김
		// jTree_Result.setRootVisible(false);
	}
	
	public void treeReset() {
		rootNode.removeAllChildren();
		initData();
	}
	
	private void initData() {
	
		List<GroupVo> groupBookList = groupBookIf.selectGroup(null);
		Vector<DefaultMutableTreeNode> parents = new Vector<>();
		
		for (int i = 0; i < groupBookList.size(); i++) {

			GroupVo oneGroup = groupBookList.get(i);
		        
			DefaultMutableTreeNode oneNode = new DefaultMutableTreeNode();
			oneNode.setUserObject(oneGroup);
			
			if(oneGroup.getParent_group_id() == 0) {				
				parents.add(oneNode);
			}
			else {
				for (int j = 0; j < parents.size(); j++) {
					if (((GroupVo) parents.get(j).getUserObject()).getGroup_id() == oneGroup.getParent_group_id()) {
						parents.get(j).add(oneNode);
						break;
					}
				}
			}
		}

		for(DefaultMutableTreeNode par : parents) {
			rootNode.add(par);
		}
		
		treeModel.reload();
	}
	
	void jTree_Result_mouseReleased(MouseEvent e) {
		 TreePath tp = jTree_Result.getPathForLocation(e.getX(), e.getY());
		 
		GroupVo g = new GroupVo();
		 
		 if(tp!=null) {
			jTextField_ParentGroup.setText((tp.getPath()[1]).toString());
			if(tp.getPath().length > 2) {
				g.setGroup_name((tp.getPath()[2]).toString());
				g = groupBookIf.selectOneGroup(g);
				jTextField_GroupId.setText(g.getGroup_id()+"");
				jTextField_Group.setText((tp.getPath()[2]).toString());				 
			} 
			else { 
				jTextField_GroupId.setText(null);
				jTextField_Group.setText("상위 그룹입니다");
			}
		 }
	}
	
	public void textFieldReset() {
		jTextField_GroupId.setText(null);
		jTextField_Group.setText(null);
		jTextField_ParentGroup.setText(null);
	}
	
	public void jButton_Save_actionPerformed(ActionEvent e) {
		String group = jTextField_Group.getText();
		String parentGroup = jTextField_ParentGroup.getText();	
		
		int num = 0;
		if (!group.equals("")) {
			GroupVo g = new GroupVo();
			g.setGroup_name(parentGroup);
			GroupVo g2 = groupBookIf.selectOneGroup(g);
			num = g2.getGroup_id();
		}
		else {
			group = parentGroup;
			num = 0;
		}

		GroupVo oneGroup = new GroupVo();
		oneGroup.setGroup_name(group);
		oneGroup.setParent_group_id(num);
		
		try {			
			groupBookIf.insertGroup(oneGroup);
			treeReset();
			textFieldReset();
		}
		catch (Exception ex) {
			LOGGER.debug(ex.toString());
		}
	}
	
	public void jButton_Delete_actionPerformed(ActionEvent e) {
		
		String groupId = jTextField_GroupId.getText();

		GroupVo p = new GroupVo();
		p.setGroup_id(Integer.parseInt(groupId));

		try {
			groupBookIf.deleteGroup(p);	
			treeReset();
			textFieldReset();
		}
		catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
		}
	}
	
	public void jButton_Update_actionPerformed(ActionEvent e) {
		String groupId = jTextField_GroupId.getText();
		String group = jTextField_Group.getText();
		String parentGroup = jTextField_ParentGroup.getText();
		
		GroupVo g = new GroupVo();
		g.setGroup_name(parentGroup);
	
		GroupVo g2 = groupBookIf.selectOneGroup(g);
		int num = g2.getGroup_id();
		
		GroupVo oneGroup = new GroupVo();
		oneGroup.setGroup_id(Integer.parseInt(groupId));
		oneGroup.setGroup_name(group);
		oneGroup.setParent_group_id(num);
		
		try {			
			groupBookIf.updateGroup(oneGroup);
			treeReset();
			textFieldReset();
		}
		catch (Exception ex) {
			LOGGER.debug(ex.toString());
		}
	}
	
	public void jButton_Reset_actionPerformed(ActionEvent e) {
		textFieldReset();
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
	private TestPanel  adaptee;	
	
	public TestPanel_jButton_Save_ActionListener(TestPanel adaptee) {
		this.adaptee = adaptee;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		adaptee.jButton_Save_actionPerformed(e);
	}
}

class TestPanel_jButton_Delete_ActionListener implements ActionListener {
	private TestPanel  adaptee;	
	
	public TestPanel_jButton_Delete_ActionListener(TestPanel adaptee) {
		this.adaptee = adaptee;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		adaptee.jButton_Delete_actionPerformed(e);
	}
}

class TestPanel_jButton_Update_ActionListener implements ActionListener {
	private TestPanel  adaptee;	
	
	public TestPanel_jButton_Update_ActionListener(TestPanel adaptee) {
		this.adaptee = adaptee;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		adaptee.jButton_Update_actionPerformed(e);
	}
}

class TestPanel_jButton_Reset_ActionListener implements ActionListener {
	private TestPanel  adaptee;	
	
	public TestPanel_jButton_Reset_ActionListener(TestPanel adaptee) {
		this.adaptee = adaptee;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		adaptee.jButton_Reset_actionPerformed(e);
	}
}