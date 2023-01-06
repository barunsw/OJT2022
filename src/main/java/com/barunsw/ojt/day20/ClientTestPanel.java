package com.barunsw.ojt.day20;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ClientTestPanel implements EventListener {
	private static final Logger LOGGER = LogManager.getLogger(ClientTestPanel.class);
	
	public ClientTestPanel() {
		ClientMain.eventQueueWorker.addEventListener(this);
	}

	@Override
	public void push(Object o) {
		// TODO Auto-generated method stub
		if (o instanceof String) {
			String str = (String)o;
			
			LOGGER.debug("push:" + str);
		}
	}
}
