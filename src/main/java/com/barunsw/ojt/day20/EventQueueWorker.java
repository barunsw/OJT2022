package com.barunsw.ojt.day20;

import java.util.ArrayList;
import java.util.List;

public class EventQueueWorker extends QueueWorker {
	private List<EventListener> eventListenerList = 
			new ArrayList<EventListener>();
	
	@Override
	public void processObject(Object o) {
		// TODO Auto-generated method stub
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
