package com.barunsw.ojt.yhkim.day11;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimerTask;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TestTimerTask extends TimerTask {
	private static final Logger LOGGER = LogManager.getLogger(TestThread.class);
	
	private int id;
	
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.KOREAN);
	
	public TestTimerTask(int id) {
		this.id = id;
	}
	
	@Override
	public void run() {
		String currentTime = sdf.format(Calendar.getInstance().getTime());
		
		LOGGER.debug(String.format("[%d]%s", id, currentTime));
		
		try {
			Thread.sleep(2000);
		}
		catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		
	}
}
