package com.barunsw.ojt.jmlee.day15;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

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
	
	private GridBagLayout gridBagLayout = new GridBagLayout();
	
	private JTextArea jTextArea_Chat = new JTextArea();			// 채팅창
	private JScrollPane jScrollPane_Chat = new JScrollPane(); 	// 채팅창 스크롤바 
	
	private JTextField jTextField_User = new JTextField();
	private JTextField jTextField_Chat = new JTextField();	 	// 채팅 입력
	private JButton jButton_Send = new JButton("전송");			// 전송버튼
	
	private ClientImpl clientImpl;
	
	private String user;
	private String message;
	
	public ClientPanel() {
		try {
			clientImpl = new ClientImpl(this);
			start();
			initUser();
			initComponent();
			initEvent();
		}
		catch (Exception ex) {
			LOGGER.debug(ex.getMessage(), ex);
		}
		
	}
	private void start() {
		clientImpl.run();
	}
	
	private void initUser() {
		user = JOptionPane.showInputDialog("대화명을 입력하세요");
		LOGGER.debug("접속 유저 " + user);
			if (user.length() == 0 || user == null) {
				System.exit(0);		// 입력 없으면 강제종료
			}
		clientImpl.send(user + "이(가) 입장하였습니다. ");
	}

	private void initEvent() {
		jTextField_Chat.addKeyListener(new ClientPanel_jTextField_Chat_KeyListener(this));
		jButton_Send.addActionListener(new ClientPanel_jButton_Send_MouseAdapter(this));
	}
	
	private void sendMessage() {
		message = jTextField_Chat.getText();
		clientImpl.send(user + " : " + message);
		jTextField_Chat.setText(null);
	}
	
	void receive(String message) {
		jTextArea_Chat.append(message + "\n"); 
	}

	private void initComponent() {
		setLayout(gridBagLayout);
				
		jTextArea_Chat.setEditable(false);
		jTextField_User.setText(user);
		
		this.add(jScrollPane_Chat, 
				new GridBagConstraints(0, 0, 3, 1,
						1.0, 1.0,
						GridBagConstraints.CENTER, GridBagConstraints.BOTH,
						new Insets(5, 5, 5, 5),
						0, 0));	
		this.add(jTextField_User, 
				new GridBagConstraints(0, 1, 1, 1,
						0.0, 0.0,
						GridBagConstraints.CENTER, GridBagConstraints.BOTH,
						new Insets(5, 5, 5, 5),
						0, 0));	
		this.add(jTextField_Chat, 
				new GridBagConstraints(1, 1, 1, 1,
						1.0, 0.0,
						GridBagConstraints.CENTER, GridBagConstraints.BOTH,
						new Insets(5, 5, 5, 5),
						0, 0));
		this.add(jButton_Send, 
				new GridBagConstraints(2, 1, 1, 1,
						0.0, 0.0,
						GridBagConstraints.CENTER, GridBagConstraints.BOTH,
						new Insets(5, 5, 5, 5),
						0, 0));
		
		jScrollPane_Chat.getViewport().add(jTextArea_Chat);		
		
	}
	
	public void jButton_Send_actionPerformed(ActionEvent e) {
		sendMessage();
	}

	public void jTextField_Chat_KeyListener(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
		sendMessage();
		}
	}
	
}
class ClientPanel_jButton_Send_MouseAdapter implements ActionListener {
	private ClientPanel adaptee;
	
	public ClientPanel_jButton_Send_MouseAdapter(ClientPanel adaptee) {
		this.adaptee = adaptee;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		adaptee.jButton_Send_actionPerformed(e);
		
	}
}

class ClientPanel_jTextField_Chat_KeyListener extends KeyAdapter {
	private ClientPanel adaptee;
	
	public ClientPanel_jTextField_Chat_KeyListener(ClientPanel adaptee) {
		this.adaptee = adaptee;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		adaptee.jTextField_Chat_KeyListener(e);
	}
}
