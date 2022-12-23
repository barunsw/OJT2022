package com.barunsw.ojt.yhkim.day10;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.barunsw.ojt.intf.AddressBookInterface;

public class TestPanel extends JPanel {
	private static final Logger LOGGER = LogManager.getLogger(TestPanel.class);

	private GridBagLayout gridBagLayout = new GridBagLayout();
	
	private JScrollPane jScrollPane_Tree = new JScrollPane();
	
	private JTree jTree_Result = new JTree();
	
	private DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode("root");
	private DefaultTreeModel treeModel = new DefaultTreeModel(rootNode);
	
	private AddressBookInterface addressBookIf;
	
	public TestPanel() {
		try {
			initAddressBookIf();
			initComponent();
			initTree();
			initData();
		}
		catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
		}
	}
	
	private void initAddressBookIf() throws Exception {
		String className = SwingTest.properties.getProperty("address_if_class");
		
		LOGGER.debug("className:" + className);
				
		Object o = Class.forName(className).newInstance();
		if (o instanceof AddressBookInterface) {
			addressBookIf = (AddressBookInterface)o; 
		}
	}
	
	private void initComponent() throws Exception {
		this.setLayout(gridBagLayout);

		this.add(jScrollPane_Tree, 
				new GridBagConstraints(0, 0, 1, 1,
						1.0, 1.0,
						GridBagConstraints.CENTER, GridBagConstraints.BOTH,
						new Insets(5, 5, 5, 5),
						0, 0));
		
		jScrollPane_Tree.getViewport().add(jTree_Result);
	}
	
	private void initTree() {
		jTree_Result.setModel(treeModel);
		// root노드 숨김
		jTree_Result.setRootVisible(false);
	}
	
	private void initData() {
		
		Person onePerson = new Person();
		onePerson.setAge(1);
		onePerson.setName("홍길동");
		
		DefaultMutableTreeNode oneNode = new DefaultMutableTreeNode();
		oneNode.setUserObject(onePerson);
		
		rootNode.add(oneNode);

		onePerson = new Person();
		onePerson.setAge(2);
		onePerson.setName("유관순");

		oneNode = new DefaultMutableTreeNode();
		oneNode.setUserObject(onePerson);

		rootNode.add(oneNode);
		
		treeModel.reload();

	}
	
	void jTree_Result_mouseReleased(MouseEvent e) {
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