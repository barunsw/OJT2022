package com.barunsw.ojt.jmlee.day21;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EventQueueWorker extends QueueWorker {
	private static final Logger LOGGER = LogManager.getLogger(EventQueueWorker.class);

	private List<EventListener> eventListenerList = 
			new ArrayList<EventListener>();
	
	@Override
	public void processObject(Object o) {
		for (EventListener oneEventListener : eventListenerList) {
			oneEventListener.push(o);
		}
	}

	public void addEventListener(EventListener listener) {
		eventListenerList.add(listener);
	}

	public void removeEventListener(EventListener listener) {
		eventListenerList.remove(listener);
	}
	
}
