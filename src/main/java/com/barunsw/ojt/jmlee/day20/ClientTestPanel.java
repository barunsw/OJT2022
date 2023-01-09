package com.barunsw.ojt.jmlee.day20;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ClientTestPanel implements EventListener {
	private static final Logger LOGGER = LogManager.getLogger(ClientTestPanel.class);
	
	public ClientTestPanel() {
		ClientMain.eventQueueWorker.addEventListener(this);
	}

	@Override
	public void push(Object o) {
		if (o instanceof String) {
			String str = (String)o;
			
			LOGGER.debug(String.format("push: %s" , str));
		}
	}
}
