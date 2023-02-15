package com.barunsw.ojt.jmlee.day15;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ClientPanel extends JPanel {
	private static Logger LOGGER = LoggerFactory.getLogger(ClientPanel.class);
	
	private ClientImpl clientImpl;

	private JTextArea jTextArea_Chat = new JTextArea(8, 8);
	private JScrollPane jScrollPane_Chat = new JScrollPane();
	
	private JTextField jTextField_Input = new JTextField();
	private JButton jButton_Send = new JButton("전송");
	
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("[HH:mm:ss]");
	private String name;
	
	public ClientPanel() {
		try {
			clientImpl = new ClientImpl(this);
			initChat();
			initInfo();
			initComponent();
			initEvent();
		}
		catch (Exception ex) {
			LOGGER.error(ex.getMessage());
		}
	}
	
	private void initChat() {
		clientImpl.run();
	}
	
	private void initInfo() {
		String name = JOptionPane.showInputDialog("사용자의 이름을 입력하세요."); 
		LOGGER.debug(name);
		clientImpl.sendMessage("=== "+name +"님이 입장하셨습니다. ===");
		this.name = name;
	}
	
	private void initComponent() {
		this.setLayout(new GridBagLayout());
		
		jTextArea_Chat.setEditable(false);
		jTextField_Input.setPreferredSize(new Dimension(400, 30));

		this.add(jScrollPane_Chat,						
				new GridBagConstraints(0, 0, 2, 1,
				1.0, 1.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(5, 5, 5, 5),
				0, 0));
		jScrollPane_Chat.getViewport().add(jTextArea_Chat);

		this.add(jTextField_Input,						
				new GridBagConstraints(0, 1, 1, 1,
				1.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(5, 5, 5, 5),
				0, 0));
		
		this.add(jButton_Send,						
				new GridBagConstraints(1, 1, 1, 1,
				0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(5, 5, 5, 5),
				0, 0));
		
	}
	
	private void send() {
		String msg = jTextField_Input.getText();
		clientImpl.sendMessage(simpleDateFormat.format(new Date())+" "+name +": "+msg);		
		jTextField_Input.setText(null);
	}
	
	void receive(String msg) {
		jTextArea_Chat.append(msg+"\n");
	}
	
	void stopClient() {
		clientImpl.stopClient();
	}
	
	private void initEvent(){
		jTextField_Input.addKeyListener(new ClientPanel_jTextField_Input_KeyListener(this));
		jButton_Send.addActionListener(new ClientPanel_jButton_Send_ActionListner(this));
	}
	
	void jButton_Send_actionPerformed(ActionEvent e) {
		send();
	}
	
	void jTextField_Input_KeyListener(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			send();
		}
	}
	
}

class ClientPanel_jButton_Send_ActionListner implements ActionListener {
	private ClientPanel  adaptee;	
	
	public ClientPanel_jButton_Send_ActionListner(ClientPanel adaptee) {
		this.adaptee = adaptee;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		adaptee.jButton_Send_actionPerformed(e);
	}
}

class ClientPanel_jTextField_Input_KeyListener extends KeyAdapter {
	private ClientPanel adaptee;

	public ClientPanel_jTextField_Input_KeyListener(ClientPanel adaptee) {
		this.adaptee = adaptee;
	}

	public void keyPressed(KeyEvent e) {
		adaptee.jTextField_Input_KeyListener(e);
	}
}
