package com.barunsw.ojt.yhkim.day13;

import java.awt.BorderLayout;
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
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DBExplorerPanel extends JPanel {
	private static Logger LOGGER = LoggerFactory.getLogger(DBExplorerPanel.class);
		
	private JPanel jPanel_Right = new JPanel();
	private JPanel jPanel_Search = new JPanel();
		
	private JTextArea jTextArea_Search = new JTextArea(10, 7);
	private JTextField jTextField_Result = new JTextField();
	
	private JButton jButton_Save = new JButton("입력");

	private JScrollPane jScrollPane_Search = new JScrollPane();
	private JScrollPane jScrollPane_Tree = new JScrollPane();
	private JScrollPane jScrollPane_Table = new JScrollPane();
	
	private JTree jTree_Result = new JTree();
	private JTable jTable_Result = new JTable();
	
	private DefaultMutableTreeNode rootNode;
	private DefaultTreeModel treeModel;
	
	private CommonTableModel tableModel = new CommonTableModel();

	private JSplitPane jSplitPane_Main = new JSplitPane();
	private JSplitPane jSplitPane_Right = new JSplitPane();
		
	private DBExplorerImpl dbImpl;
	
	private JPopupMenu jPopupMenu_Table = new JPopupMenu();
	
	private JMenuItem jMenuItem_Data = new JMenuItem("DATA");
	 
	public DBExplorerPanel(DBExplorerImpl dbImpl) {
		try {
			initDBIf(dbImpl);
			initComponent();
			initTree();
			initTreeData();
			initMenu();
			initEvent();
		}
		catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
		}
	}
	
	private void initDBIf(DBExplorerImpl dbImpl) throws Exception {
		this.dbImpl = dbImpl;
	}
	
	private void initComponent() throws Exception {
		this.setLayout(new BorderLayout());	
		jPanel_Right.setLayout(new BorderLayout());
		
		jScrollPane_Tree.getViewport().add(jTree_Result);
		jScrollPane_Table.getViewport().setView(jTable_Result);
		jScrollPane_Table.setPreferredSize(new Dimension(400, 300));

		jPanel_Search.setLayout(new GridBagLayout());	
		
		jPanel_Search.add(jButton_Save, 
				new GridBagConstraints(0, 0, 1, 1,
				1.0, 0.0,
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
						1.0, 0.0,
						GridBagConstraints.CENTER, GridBagConstraints.BOTH,
						new Insets(5, 5, 5, 5),
						0, 0));
		jTextField_Result.setEditable(false);

		jSplitPane_Right.setOrientation(JSplitPane.VERTICAL_SPLIT);
		jSplitPane_Right.setTopComponent(jPanel_Search);
		jSplitPane_Right.setBottomComponent(jScrollPane_Table);
		jSplitPane_Right.setOneTouchExpandable(true);

		jPanel_Right.add(jSplitPane_Right, BorderLayout.CENTER);

		jSplitPane_Main.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
		jSplitPane_Main.setLeftComponent(jScrollPane_Tree);
		jSplitPane_Main.setRightComponent(jPanel_Right);
		jSplitPane_Main.setOneTouchExpandable(true);
		jSplitPane_Main.setDividerLocation(250);

		this.add(jSplitPane_Main, BorderLayout.CENTER);
	}
	
	private void initTree() {
		rootNode = new DefaultMutableTreeNode(DBExplorerImpl.NAME);		
		treeModel = new DefaultTreeModel(rootNode);
		jTree_Result.setModel(treeModel);
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
	
	private void initMenu() {
		jPopupMenu_Table.add(jMenuItem_Data);
	}
	
	private void initEvent() {
		jButton_Save.addActionListener(new DBExplorerPanel_jButton_Save_ActionListener(this));
		jMenuItem_Data.addActionListener(new DBExplorerPanel_jMenuItem_Data_ActionListener(this));
		jTree_Result.addMouseListener(new DBExplorerPanel_jTree_Result_MouseAdapter(this));
	}
	
	void jButton_Save_actionPerformed(ActionEvent e) {
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
	
	private void clearTable() {
		tableModel = new CommonTableModel();
		jTable_Result.setModel(tableModel);		
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
		jTable_Result.setRowHeight(30);
	}
	
	void jMenuItem_Data_actionPerformed(ActionEvent e) {		
		String table = jTree_Result.getLastSelectedPathComponent().toString();		
		Vector<List> v = dbImpl.selectTable("SELECT * FROM "+table);		
		jTextField_Result.setText(String.valueOf(v.size())+"개의 row를 출력했습니다.");
		makeTable(v);			
	}
	
	void jTree_Result_mouseReleased(MouseEvent e) {
//		if (e.getModifiersEx() == MouseEvent.BUTTON3_DOWN_MASK) {
			TreePath tp = jTree_Result.getPathForLocation(e.getX(), e.getY());

			if (tp != null) {			
				if (tp.getPathCount() == 2) {
					jPopupMenu_Table.show(jTree_Result, e.getX(), e.getY());
				}
			}
//		}
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

class DBExplorerPanel_jMenuItem_Data_ActionListener implements ActionListener {
	private DBExplorerPanel  adaptee;	
	
	public DBExplorerPanel_jMenuItem_Data_ActionListener(DBExplorerPanel adaptee) {
		this.adaptee = adaptee;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		adaptee.jMenuItem_Data_actionPerformed(e);
	}
}

class DBExplorerPanel_jTree_Result_MouseAdapter extends MouseAdapter {
	private DBExplorerPanel adaptee;
	
	public DBExplorerPanel_jTree_Result_MouseAdapter(DBExplorerPanel adaptee) {
		this.adaptee = adaptee;
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON3) {
			adaptee.jTree_Result_mouseReleased(e);
		}
	}
}