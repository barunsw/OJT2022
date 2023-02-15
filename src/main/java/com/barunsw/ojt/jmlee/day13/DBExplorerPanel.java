package com.barunsw.ojt.jmlee.day13;

import java.awt.BorderLayout;
import java.awt.Color;
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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DBExplorerPanel extends JPanel {
	private static Logger LOGGER = LoggerFactory.getLogger(DBExplorerPanel.class);
	
	private GridBagLayout gridBagLayout = new GridBagLayout();
	
	private JPanel jPanel_Right = new JPanel();
	private JPanel jPanel_Search = new JPanel();
	//private JPanel jPanel_Result = new JPanel();
	
	private JTextArea jTextArea_Search = new JTextArea(10, 7);
	private JTextField jTextField_Result = new JTextField();
	
	private JButton jButton_Save = new JButton("입력");

	private JScrollPane jScrollPane_Search = new JScrollPane();
	private JScrollPane jScrollPane_Tree = new JScrollPane();
	private JScrollPane jScrollPane_Table = new JScrollPane();
	
	private JTree jTree_Result = new JTree();
	private JTable jTable_Result = new JTable();
	
	private DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode("OJT");
	private DefaultTreeModel treeModel = new DefaultTreeModel(rootNode);
	
	private CommonTableModel tableModel = new CommonTableModel();

	private JSplitPane jsp = new JSplitPane();
	private JSplitPane jsp2 = new JSplitPane();
		
	private DBExplorerImpl dbImpl;
	 
	public DBExplorerPanel() {
		try {
			initDBIf();
			initComponent();
			initTree();
			initTreeData();
			initEvent();
		}
		catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
		}
	}
	private void initDBIf() throws Exception {
		dbImpl = new DBExplorerImpl();
	}
	
	private void initComponent() throws Exception {
		this.setLayout(new BorderLayout());	
		
		jScrollPane_Tree.getViewport().add(jTree_Result);
		jScrollPane_Table.getViewport().setView(jTable_Result);
		jScrollPane_Table.setPreferredSize(new Dimension(400, 300));
		
		jPanel_Right.setBackground(Color.red);
		jPanel_Search.setBackground(Color.blue);

		jPanel_Search.setLayout(gridBagLayout);	
		
		jPanel_Search.add(jButton_Save, 
				new GridBagConstraints(0, 0, 1, 1,
				1.0, 1.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(5, 5, 5, 5),
				0, 0));
		jPanel_Search.add(jScrollPane_Search, 
						new GridBagConstraints(0, 1, 1, 1,
						1.0, 1.0,
						GridBagConstraints.CENTER, GridBagConstraints.BOTH,
						new Insets(5, 5, 5, 5),
						0, 0));
		jScrollPane_Search.getViewport().add(jTextArea_Search);

		jPanel_Search.add(jTextField_Result, 
						new GridBagConstraints(0, 2, 1, 1,
						1.0, 1.0,
						GridBagConstraints.CENTER, GridBagConstraints.BOTH,
						new Insets(5, 5, 5, 5),
						0, 0));
		jTextField_Result.setEditable(false);

		jsp2.setOrientation(JSplitPane.VERTICAL_SPLIT);
		jsp2.setTopComponent(jPanel_Search);
		jsp2.setBottomComponent(jScrollPane_Table);

		jPanel_Right.add(jsp2);

		jsp.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
		jsp.setLeftComponent(jScrollPane_Tree);
		jsp.setRightComponent(jPanel_Right);
		
		jsp.setDividerLocation(250);

		this.add(jsp, BorderLayout.CENTER);
	}
	
	private void initTree() {
		jTree_Result.setModel(treeModel);
		// jTree_Result.setRootVisible(false);
	}
	
	private void initTreeData() {
		List<String> listTable = dbImpl.selectTableName();
		
		for(String l : listTable) {
			List<String> tableColumns = dbImpl.selectColumnName(l);
			
			DefaultMutableTreeNode oneNode = new DefaultMutableTreeNode(l);
			for(String t : tableColumns) {
				DefaultMutableTreeNode oneNode2 = new DefaultMutableTreeNode(t);
				oneNode.add(oneNode2);
			}
			rootNode.add(oneNode);
			
		}
		
	}
	
	private void clearTable() {
		tableModel = new CommonTableModel();
		jTable_Result.setModel(tableModel);		

//		tableModel.setColumnData(null);
//		tableModel.setData(null);
//		tableModel.fireTableDataChanged();
	}
		
	private void initEvent() {
		jButton_Save.addActionListener(new DBExplorerPanel_jButton_Save_ActionListener(this));
	}
	
	public void jButton_Save_actionPerformed(ActionEvent e) {
		String sql = jTextArea_Search.getText();
		String msg = "";

		if(!(sql.toLowerCase()).startsWith("select")) {
			int result = dbImpl.changeTable(sql);
			msg = String.valueOf(result)+"개의 row가 변경되었습니다.";
			clearTable();			
		} 
		else {
			Vector<List> v = dbImpl.selectTable(sql);		
			msg = String.valueOf(v.size())+"개의 row를 출력했습니다.";
			makeTable(v);			
		}
		
		jTextField_Result.setText(msg);
	}
	
	private void makeTable(Vector<List> v) {
		tableModel = new CommonTableModel();

		Vector columns = new Vector();
		
		int cnt = v.get(0).size();
		for (int i = 0; i < cnt; i++) {
			columns.add(v.get(0).get(i));
		}
	
		tableModel.setColumnData(columns);
		
		Vector dataline = new Vector();
		for (int i = 1; i<v.size(); i++) {
			dataline.add(v.get(i));			
		}
		
		tableModel.setData(dataline);
		jTable_Result.setModel(tableModel);		
	}
}

class DBExplorerPanel_jButton_Save_ActionListener implements ActionListener {
	private DBExplorerPanel  adaptee;	
	
	public DBExplorerPanel_jButton_Save_ActionListener(DBExplorerPanel adaptee) {
		this.adaptee = adaptee;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		adaptee.jButton_Save_actionPerformed(e);
	}
}