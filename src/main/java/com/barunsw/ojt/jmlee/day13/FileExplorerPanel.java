package com.barunsw.ojt.jmlee.day13;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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

	// 텍스트 필드
	private JTextField jTextField_Path = new JTextField();

	// 트리 생성
	// private DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode(
	// new FileVo("C:", "", "", new File("/").getAbsolutePath()));
	//
	// private DefaultTreeModel treeModel = new DefaultTreeModel(rootNode);

	private DefaultMutableTreeNode rootNode;

	private DefaultTreeModel treeModel;

	// 날짜 포멧
	private static SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public FileExplorerPanel() {
		try {
			// run();
			initComponent();
			initTree();
			initTable();
			initEvent();
		} catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
		}
	}

	private void initComponent() {
		LOGGER.debug("initComponent");
		this.setLayout(gridBagLayout);
		jPanel_Main.setLayout(gridBagLayout);

		this.add(jPanel_Main, new GridBagConstraints(0, 0, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER,
				GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
		this.add(jSplitPane, new GridBagConstraints(0, 1, 1, 1, 1.0, 1.0, GridBagConstraints.CENTER,
				GridBagConstraints.BOTH, new Insets(0, 5, 5, 5), 0, 0));
		jPanel_Main.add(jTextField_Path, new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0, GridBagConstraints.CENTER,
				GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), 0, 0));

		jScrollPane_Table.getViewport().add(jTable_FileTable);
		jScrollPane_Tree.getViewport().add(jTree_FileTree);

		jSplitPane.setRightComponent(jScrollPane_Table);
		jSplitPane.setLeftComponent(jScrollPane_Tree);
		jSplitPane.setOneTouchExpandable(true);

		jSplitPane.setDividerLocation(200);
	}

	private void initEvent() {
		jTree_FileTree.addMouseListener(new ExplorerPanel_jTree_FileTree_MouseAdapter(this));
		jTextField_Path.addKeyListener(new ExplorerPanel_jTextField_Path_KeyAdpater(this));
	}

	private void initTree() {
		File fileRoot = new File("C:/");
		rootNode = new DefaultMutableTreeNode(new FileNode(fileRoot));

		treeModel = new DefaultTreeModel(rootNode);

		// jTree_FileTree = new JTree(treeModel);
		jTree_FileTree.setModel(treeModel);
		// jTree_FileTree.setShowsRootHandles(true);

		// CreateChildNodes ccn = new CreateChildNodes(fileRoot, rootNode);
		// ccn.createChildren(fileRoot, rootNode);
//		initTreeData(rootNode);

//		jTree_FileTree.updateUI();
	}

	/**
	 * 파라미터로 넘어온 treeNode의 자식 노드를 구해서 붙인다.
	 * 
	 * @param parentTreeNode
	 */
	private void initTreeData(DefaultMutableTreeNode parentTreeNode) {
		// parentTreeNode가 들어오면 자식 노드를 다 지운다.
		parentTreeNode.removeAllChildren();

		// UserObject인 FileVo를 가지고 온다.
		FileNode fileNode = (FileNode) parentTreeNode.getUserObject();

		// FileVo의 경로를 가지고 자식 디렉토리를 구한다.
		File file = fileNode.getFile();

		File[] listFiles = file.listFiles();

		for (File oneFile : listFiles) {
			if (oneFile.isDirectory()) {
				// 자식 디렉토리를 FileNode로 만든다.
				FileNode oneFileNode = new FileNode(oneFile);

				// parentTreeNode에 붙인다.
				DefaultMutableTreeNode childTreeNode = new DefaultMutableTreeNode(oneFileNode);
				parentTreeNode.add(childTreeNode);
			}
		}
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

	private void initTableData(String pathDir) {
		Vector pathList = new Vector();
		File currentDir = new File(pathDir);
		File[] files = currentDir.listFiles(); // currentDir에 있는 파일 리스트 출력

		for (File oneFile : files) { // 불러온 파일리스트 oneFile로 오브젝트화
			String lastModified = date.format(new Date(oneFile.lastModified()));

			Vector data = new Vector();
			data.add(oneFile.getName());
			data.add(lastModified);
			data.add((oneFile.isDirectory() ? "폴더" : "파일")); // ENUM 으로 대체할 예정
			data.add(oneFile.length());

			pathList.add(data);
		}

		tableModel.setData(pathList); // 테이블 모델에 pathlist 셋팅
		tableModel.fireTableDataChanged();
	}

	private void clearTableData() {
		tableModel.removeData();
		tableModel.fireTableDataChanged();
	}

	void jTree_FileTree_mouseReleased(MouseEvent e) {
		Object o = jTree_FileTree.getLastSelectedPathComponent();
		if (o instanceof DefaultMutableTreeNode) {
			DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) o;
			Object userObject = selectedNode.getUserObject();

			LOGGER.debug(((FileNode) userObject).getFile().getAbsolutePath());
			String filePath = ((FileNode) userObject).getFile().getAbsolutePath();
			jTextField_Path.setText(filePath);

			initTreeData(selectedNode);

			if (selectedNode.getChildCount() > 0) {
				initTableData(filePath);
			} else {
				clearTableData();
			}
		}
	}

	void jTextField_Path_KeyAdpater(KeyEvent e) {
		String filePath = jTextField_Path.getText();
		initTableData(filePath);
	}

	class ExplorerPanel_jTree_FileTree_MouseAdapter extends MouseAdapter {
		private FileExplorerPanel adaptee;

		public ExplorerPanel_jTree_FileTree_MouseAdapter(FileExplorerPanel adaptee) {
			this.adaptee = adaptee;
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			adaptee.jTree_FileTree_mouseReleased(e);
		}
	}

	class ExplorerPanel_jTextField_Path_KeyAdpater extends KeyAdapter {
		private FileExplorerPanel adaptee;

		public ExplorerPanel_jTextField_Path_KeyAdpater(FileExplorerPanel adaptee) {
			this.adaptee = adaptee;
		}

		public void keyPressed(KeyEvent e) {
			int key = e.getKeyCode();

			if (key == KeyEvent.VK_ENTER) {
				adaptee.jTextField_Path_KeyAdpater(e);
			}
		}
	}
}