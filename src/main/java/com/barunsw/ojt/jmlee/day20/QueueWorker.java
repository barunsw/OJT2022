package com.barunsw.ojt.jmlee.day20;

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
    	LOGGER.debug("=====Queue Worker PUSH=====", t);
		messageRepository.add(t);	// 입력된 메세지 리스트에 추가
		synchronized (waitObject) {
			waitObject.notify();  	// 기다리던 객체를 꺠운다
		}
    }

    public void run() {
        T t = null;
        runFlag = true;
        LOGGER.debug("쓰레드 시작");
        while ( runFlag ) {
            synchronized ( waitObject ) {
                if ( messageRepository.size() > 0 ) {	// 입력된 메세지가 하나 이상이면 지워준다
                    t = messageRepository.remove(0);
                }
                else {									// 메세지가 없으면
                    t = null;

                    try {
                        waitObject.wait();				// 계속 대기
                    }
                    catch ( Exception ex ) {
                        ex.printStackTrace();
                    }
                }
            }

            try {
                if ( t != null ) {
                	try{ 
                		processObject(t);
                	} catch(Exception e ) {
                		e.printStackTrace();
                	}
                }
            }
            catch ( Exception ex ) {
                ex.printStackTrace();
            }
        }
    }
    
    public void close() {
    	LOGGER.debug("쓰레드 종료");
    	runFlag = false;
    	
    	synchronized ( waitObject ) {
    		waitObject.notify();
    	}
    }
	
	public int getBufferSize() {
		return messageRepository.size();
	}
}