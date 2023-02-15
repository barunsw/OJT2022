package com.barunsw.ojt.jmlee.day21;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class QueueWorker<T> extends Thread {
	private static final Logger LOGGER = LogManager.getLogger(QueueWorker.class);
	
    private LinkedList<T> eventLinkedList = new LinkedList<T>();
    private List<T> messageRepository = Collections.synchronizedList(eventLinkedList);

    private Object waitObject = new Object();
    
    private boolean runFlag;
    
    public abstract void processObject(T t);

    public void push(T t) {
		messageRepository.add(t);
		if (messageRepository.size() == 100) {
			synchronized (waitObject) {
				waitObject.notify();
			}			
		}
    }

    public void run() {
        T t = null;
        runFlag = true;
        
        while ( runFlag ) {
            synchronized ( waitObject ) {
                if ( messageRepository.size() > 0 ) {
                	try {
                		Thread.sleep(1000);
                	} 
                	catch (Exception e) {
                		LOGGER.error(e.getMessage(), e);
                	}
                	
                	LOGGER.debug(String.format("getBufferSize() : [%d]", getBufferSize()));
                    t = messageRepository.remove(0);
                }
                else {
                    t = null;

                    try {
                        waitObject.wait();
                    }
                    catch ( Exception ex ) {
                        ex.printStackTrace();
                    }
                }
            }

            try {
                if ( t != null ) { 		
                	processObject(t);
                }
            }
            catch ( Exception ex ) {
                ex.printStackTrace();
            }
        }
    }
    
    public void close() {
    	runFlag = false;
    	
    	synchronized ( waitObject ) {
    		waitObject.notify();
    	}
    }
	
	public int getBufferSize() {
		return messageRepository.size();
	}
}