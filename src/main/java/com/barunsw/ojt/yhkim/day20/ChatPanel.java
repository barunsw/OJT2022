package com.barunsw.ojt.yhkim.day20;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
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

public class ChatPanel extends JPanel implements EventListener {
   private static Logger LOGGER = LoggerFactory.getLogger(ChatPanel.class);

   private ServerInterface serverIf;
   private ClientInterface clientIf;
   
   private GridBagLayout gridBagLayout = new GridBagLayout();
   
   private JPanel jPanel_Top = new JPanel();
   private JPanel jPanel_Bottom = new JPanel();
   
   private JTextArea jTextArea_Chat = new JTextArea();
   private JScrollPane jScrollPane_Chat = new JScrollPane();
   
   private JTextField jTextField_Input = new JTextField();

   private JButton jButton_Start = new JButton("접속");
   private JButton jButton_Stop = new JButton("종료");
   private JButton jButton_Send = new JButton("전송");
   
   private User user;
   SimpleDateFormat simpleDateFormat = new SimpleDateFormat("[HH:mm:ss]");
   
   public ChatPanel() {
      try {
         initComponent();
         initEvent();
      }
      catch (Exception ex) {
         LOGGER.error(ex.getMessage(), ex);
      }
   }
   
   private void initComponent() throws Exception {
      this.setLayout(new BorderLayout());
      
      jPanel_Top.setLayout(gridBagLayout);
      jPanel_Bottom.setLayout(gridBagLayout);
      
      jTextField_Input.setPreferredSize(new Dimension(400, 30));
      jTextField_Input.setEditable(false);
      jTextArea_Chat.setEditable(false);
      jButton_Stop.setEnabled(false);
      jButton_Send.setEnabled(false);
      
      jPanel_Top.add(jButton_Start,                  
            new GridBagConstraints(0, 0, 1, 1,
            0.5, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(5, 5, 5, 5),
            0, 0));
      
      jPanel_Top.add(jButton_Stop,                  
            new GridBagConstraints(1, 0, 1, 1,
            0.5, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(5, 5, 5, 5),
            0, 0));
      
      jPanel_Bottom.add(jScrollPane_Chat,                  
            new GridBagConstraints(0, 0, 2, 1,
            1.0, 1.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(5, 5, 5, 5),
            0, 0));
      jScrollPane_Chat.getViewport().add(jTextArea_Chat);

      jPanel_Bottom.add(jTextField_Input,                  
            new GridBagConstraints(0, 1, 1, 1,
            1.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(5, 5, 5, 5),
            0, 0));
      
      jPanel_Bottom.add(jButton_Send,                  
            new GridBagConstraints(1, 1, 1, 1,
            0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(5, 5, 5, 5),
            0, 0));
   
      this.add(jPanel_Top, BorderLayout.NORTH);
      this.add(jPanel_Bottom, BorderLayout.CENTER);
   }
   
   private void initRmi() throws Exception {
      String name = JOptionPane.showInputDialog("사용자의 이름을 입력하세요."); 
      LOGGER.debug(String.format("+++ 사용자 이름 %s", name));

      Registry registry = LocateRegistry.getRegistry(ServerMain.PORT);
            
      Remote remote = registry.lookup(ServerMain.BIND_NAME);
      if (remote instanceof ServerInterface) {
         serverIf = (ServerInterface)remote;
      }
      
      clientIf = new ClientImpl();
      user = new User(serverIf.getSeqNum(), name);
      
      if (serverIf != null) {
         serverIf.register(user, clientIf);
      }   
   }
   
   private void initEvent() {
      ClientMain.eventQueueWorker.addEventListener(this);
      
      jButton_Start.addActionListener(new ChatPanel_jButton_Start_ActionListener(this));
      jButton_Stop.addActionListener(new ChatPanel_jButton_Stop_ActionListener(this));
      jButton_Send.addActionListener(new ChatPanel_jButton_Send_ActionListener(this));
      jTextField_Input.addKeyListener(new ChatPanel_jTextField_Input_KeyListener(this));
   }
   
   void jButton_Start_actionPerformed(ActionEvent e) {
      jButton_Start.setEnabled(false);
      jButton_Stop.setEnabled(true);   
      jButton_Send.setEnabled(true);      
      jTextField_Input.setEditable(true);
      
      try {
         if (user != null) {
            serverIf.register(user, clientIf);
         }
         else {
            initRmi();            
         }
      } 
      catch (Exception ex) {
         LOGGER.debug(ex.getMessage(), ex);
      }
   }
   
   void jButton_Stop_actionPerformed(ActionEvent e) {
      jButton_Start.setEnabled(true);
      jButton_Stop.setEnabled(false);      
      jButton_Send.setEnabled(false);      
      jTextField_Input.setEditable(false);
      
      try {
         serverIf.deregister(user);
      } 
      catch (Exception ex) {
         LOGGER.debug(ex.getMessage(), ex);
      }
   }
   
   void jButton_Send_actionPerformed(ActionEvent e) {   
      send();
   }
   
   void jTextField_Input_KeyListener(KeyEvent e) {
      if(e.getKeyCode() == KeyEvent.VK_ENTER) {
         send();
      }
   }
   
   void send() {
      String message = String.format("%s : %s", simpleDateFormat.format(new Date()), jTextField_Input.getText());
      try {
         serverIf.send(user, message);
      } 
      catch (Exception ex) {
         LOGGER.debug(ex.getMessage(), ex);
      }
      
      jTextField_Input.setText(null);
   }
   
   @Override
   public void push(Object o) {
      if (o instanceof String) {
         jTextArea_Chat.append(o.toString()+"\n");         
      }
   }
}

class ChatPanel_jButton_Start_ActionListener implements ActionListener {
   private ChatPanel  adaptee;   
   
   public ChatPanel_jButton_Start_ActionListener(ChatPanel adaptee) {
      this.adaptee = adaptee;
   }
   
   @Override
   public void actionPerformed(ActionEvent e) {
      adaptee.jButton_Start_actionPerformed(e);
   }
}

class ChatPanel_jButton_Stop_ActionListener implements ActionListener {
   private ChatPanel  adaptee;   
   
   public ChatPanel_jButton_Stop_ActionListener(ChatPanel adaptee) {
      this.adaptee = adaptee;
   }
   
   @Override
   public void actionPerformed(ActionEvent e) {
      adaptee.jButton_Stop_actionPerformed(e);
   }
}

class ChatPanel_jButton_Send_ActionListener implements ActionListener {
   private ChatPanel  adaptee;   
   
   public ChatPanel_jButton_Send_ActionListener(ChatPanel adaptee) {
      this.adaptee = adaptee;
   }
   
   @Override
   public void actionPerformed(ActionEvent e) {
      adaptee.jButton_Send_actionPerformed(e);
   }
}

class ChatPanel_jTextField_Input_KeyListener extends KeyAdapter {
   private ChatPanel adaptee;

   public ChatPanel_jTextField_Input_KeyListener(ChatPanel adaptee) {
      this.adaptee = adaptee;
   }

   public void keyPressed(KeyEvent e) {
      adaptee.jTextField_Input_KeyListener(e);
   }
}
