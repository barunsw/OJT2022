package com.barunsw.ojt.day20;

import javax.swing.JPanel;

public class ChatPanel extends JPanel implements EventListener {
	private ServerInterface serverIf;
	
	public ChatPanel() {
		try {
			initComponent();
			initRmi();
		}
		catch (Exception ex) {
			
		}
	}
	
	private void initComponent() throws Exception {
		
	}
	
	private void initRmi() throws Exception {
		
	}
	
	// 접속 버튼 상태에 따라 regist, deregist 한다.

	// 전송 버튼을 누르면 serverIf.send한다.
	
	@Override
	public void push(Object o) {
		// textarea에 append한다.		
	}
}
