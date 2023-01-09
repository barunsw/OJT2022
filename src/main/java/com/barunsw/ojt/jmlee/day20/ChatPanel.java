package com.barunsw.ojt.jmlee.day20;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ChatPanel extends JPanel implements EventListener {
	
	private static final Logger LOGGER = LogManager.getLogger(ClientMain.class);
	
	private GridBagLayout gridBagLayout = new GridBagLayout();	
	private JTextArea jTextArea_Chat = new JTextArea();			// 채팅창
	private JScrollPane jScrollPane_Chat = new JScrollPane(); 	// 채팅창 스크롤바 
	
	private JTextField jTextField_User = new JTextField();
	private JTextField jTextField_Chat = new JTextField();	 	// 채팅 입력
	private JButton jButton_Send = new JButton("전송");			// 전송 버튼
	//private JButton jButton_Conn = new JButton("접속"); // 접속 버튼
	private JToggleButton jToggleButton_Conn = new JToggleButton("접속 / 해제"); // 접속 버튼
	
	private ClientInterface clientIf;
	private ServerInterface serverIf;
	
	private String user;
	
	public ChatPanel() {
		try {
			initComponent();
			initRmi();
			initEvent();
		}
		catch (Exception ex) {
			
		}
	}
	
	private void initComponent() throws Exception {
		setLayout(gridBagLayout);
		
		jTextArea_Chat.setEditable(false);
		
		this.add(jScrollPane_Chat, 
				new GridBagConstraints(0, 1, 4, 1,
						1.0, 1.0,
						GridBagConstraints.CENTER, GridBagConstraints.BOTH,
						new Insets(5, 5, 5, 5),
						0, 0));	
		this.add(jTextField_User, 
				new GridBagConstraints(0, 2, 1, 1,
						0.0, 0.0,
						GridBagConstraints.CENTER, GridBagConstraints.BOTH,
						new Insets(5, 5, 5, 5),
						0, 0));
		this.add(jToggleButton_Conn,
				new GridBagConstraints(0, 0, 1, 1,
						0.0, 0.0,
						GridBagConstraints.CENTER, GridBagConstraints.BOTH,
						new Insets(5, 5, 5, 5),
						0, 0));
		this.add(jTextField_Chat, 
				new GridBagConstraints(1, 2, 1, 1,
						1.0, 0.0,
						GridBagConstraints.CENTER, GridBagConstraints.BOTH,
						new Insets(5, 5, 5, 5),
						0, 0));
		this.add(jButton_Send, 
				new GridBagConstraints(3, 2, 1, 1,
						0.0, 0.0,
						GridBagConstraints.CENTER, GridBagConstraints.BOTH,
						new Insets(5, 5, 5, 5),
						0, 0));
		
		jScrollPane_Chat.getViewport().add(jTextArea_Chat);	
		
	}
	
	
	private void initEvent() {
		jButton_Send.addActionListener(new ChatPanel_jButton_Send_ActionListener(this));
		jToggleButton_Conn.addActionListener(new ChatPanel_jToggleButton_Conn_ActionListener(this));
		jTextField_Chat.addKeyListener(new ClientPanel_jTextField_Chat_KeyListener(this));
	}
	
	private void initRmi() throws Exception {					// RMI 활성화
		user = JOptionPane.showInputDialog("대화명을 입력하세요");
		LOGGER.debug(String.format("접속 유저 :  %s" , user));
		jTextField_User.setText(user);
		
		ClientMain.eventQueueWorker.addEventListener(this);
		
		clientIf = new ClientImpl();
		
		Registry registry = LocateRegistry.getRegistry(ServerMain.PORT);
				
		Remote remote = registry.lookup(ServerMain.BIND_NAME);
		if (remote instanceof ServerInterface) {
			serverIf = (ServerInterface)remote;
		}
	}
	
	// 접속 버튼 상태에 따라 regist, deregist 한다.
	public void jToggleButton_Conn_ActionListener() throws RemoteException {
		if (!jTextField_User.getText().isEmpty()) { // 이름을 입력하지 않으면 실행 X
			if (jToggleButton_Conn.isSelected()) {		// 버튼이 선택되면 접속 버튼 비활성화
				jToggleButton_Conn.setEnabled(true);
				jTextField_User.setEditable(false);	// 이름 수정 불가능
				
				serverIf.register(user, clientIf);	// 레지스터 추가 
				serverIf.send(user, "님이 입장하셨습니다.");
			}
			else {
				jTextField_User.setEditable(true);
				serverIf.deregister(user);
				serverIf.send(user, "님이 나가셨습니다.");
			}
		}
	}
	
	// 전송 버튼을 누르면 serverIf.send한다.
	void jButton_Send_ActionListener() throws RemoteException {
		if (!jTextField_Chat.getText().isEmpty()) {
			serverIf.send(jTextField_User.getText(), jTextField_Chat.getText());
			jTextField_Chat.setText(null);
		}
		else {
			JOptionPane.showMessageDialog(this, "내용을 입력해주세요", "Alert", JOptionPane.INFORMATION_MESSAGE);
		}
	} 
	
	public void jTextField_Chat_KeyListener(KeyEvent e) throws RemoteException {
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			serverIf.send(jTextField_User.getText(), jTextField_Chat.getText());
			jTextField_Chat.setText(null);
		}
	}
	
	@Override
	public void push(Object o) {
		jTextArea_Chat.append(o.toString() + "\n");
		LOGGER.debug("client push : " + o);
		
	}
}

class ChatPanel_jButton_Send_ActionListener implements ActionListener {
	private ChatPanel adaptee;
	
	public ChatPanel_jButton_Send_ActionListener(ChatPanel adaptee) {
		this.adaptee = adaptee;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			adaptee.jButton_Send_ActionListener();
		} catch (RemoteException rmEx) {
			rmEx.printStackTrace();
		}		
	}
}

class ClientPanel_jTextField_Chat_KeyListener extends KeyAdapter {
	private ChatPanel adaptee;
	
	public ClientPanel_jTextField_Chat_KeyListener(ChatPanel adaptee) {
		this.adaptee = adaptee;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		try {
			adaptee.jTextField_Chat_KeyListener(e);
		} catch (RemoteException rmEx) {
			rmEx.printStackTrace();
		}
	}
}

class ChatPanel_jToggleButton_Conn_ActionListener implements ActionListener {
	private ChatPanel adaptee;
	
	public ChatPanel_jToggleButton_Conn_ActionListener(ChatPanel adaptee) {
		this.adaptee = adaptee;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			adaptee.jToggleButton_Conn_ActionListener();
		}
		catch (RemoteException rmEx) {
			rmEx.printStackTrace();
		}
	}
}