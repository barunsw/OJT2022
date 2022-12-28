package com.barunsw.ojt.jmlee.day13;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileExplorerPanel extends JPanel {
		
	private static Logger LOGGER = LoggerFactory.getLogger(FileExam.class);
	
	private GridBagLayout gridBagLayout = new GridBagLayout();
	
	// 프레임 설정
	private JPanel jPanel_Main = new JPanel();
	private JScrollPane jScrollPane_Tree = new JScrollPane();
	private JScrollPane jScrollPane_Table = new JScrollPane();
	private JSplitPane jSplitPane = new JSplitPane();
	
	// 컴포넌트 
	private JTree jTree_FileTree = new JTree();
	private JTable jTable_FileTable = new JTable();
	private CommonTableModel tableModel = new CommonTableModel();
	
	//텍스트 필드
	private JTextField jTextField_Path = new JTextField();
	
	// 트리 생성
	private DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode(
			new FileVo("C:", "", "", new File("/").getAbsolutePath()));
	
	private DefaultTreeModel treeModel = new DefaultTreeModel(rootNode);
	
	// 날짜 포멧
	private static SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public FileExplorerPanel() {
		try {
			initComponent();
			initTree();
			initTable();
			initTableData();
			initEvent();
			initData(rootNode);
		}
		catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
		}
	}
	



	private void initComponent() {
		LOGGER.debug("initComponent");
		this.setLayout(gridBagLayout);
		jPanel_Main.setLayout(gridBagLayout);
		
		this.add(jPanel_Main, new GridBagConstraints(0, 0, 1, 1,
				1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 0, 0, 0),
				0, 0));
		this.add(jSplitPane, new GridBagConstraints(0, 1, 1, 1,
				1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 5, 5, 5),
				0, 0));
		jPanel_Main.add(jTextField_Path, new GridBagConstraints(0, 0, 1, 1,
				1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(5, 5, 5, 5),
				0, 0));
		
		jScrollPane_Table.getViewport().add(jTable_FileTable);
		jScrollPane_Tree.getViewport().add(jTree_FileTree);
		
		jSplitPane.setRightComponent(jScrollPane_Table);
		jSplitPane.setLeftComponent(jScrollPane_Tree);
		
		jSplitPane.setDividerLocation(200);
		
		jTree_FileTree.addMouseListener(null);
		
		
	}
		

	private void initEvent() {
		// TODO Auto-generated method stub
		
	}

	private void initTree() {
		
		LOGGER.debug("rootNode" + rootNode);
		treeModel.setRoot(rootNode);
		jTree_FileTree.setModel(treeModel);
		treeModel.reload();
		
	}
	
	private void initData(DefaultMutableTreeNode rootNode) {
		// TODO Auto-generated method stub
		
	}
	
	private void initTable() {
		Vector columns = new Vector();
		columns.add("이름");
		columns.add("수정한 날짜");
		columns.add("유형");
		columns.add("크기");
		
		tableModel.setColumnData(columns);
		jTable_FileTable.setModel(tableModel);
		
	}
	
	private void initTableData() {
		LOGGER.debug("initTableData");
		Vector pathList = new Vector();
		
		String currentDirStr = System.getProperty("user.dir");
		File currentDir = new File(currentDirStr);
		currentDir.getParentFile();

		File[] files = currentDir.listFiles(); 	// currentDir에 있는 파일 리스트 출력
		for (File oneFile : files) {			// 불러온 파일리스트 oneFile로 오브젝트화
			String lastModified = date.format(new Date(oneFile.lastModified()));
			Vector data = new Vector();			
			data.add(oneFile.getName());
			data.add(lastModified);
			data.add((oneFile.isDirectory()?"폴더":"파일")); // ENUM 으로 대체할 예정
			data.add(oneFile.length());
			pathList.add(data);

		}
	
		tableModel.setData(pathList);			// 테이블 모델에 pathlist 셋팅
		tableModel.fireTableDataChanged();
	}
	
}


