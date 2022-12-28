package com.barunsw.ojt.day13;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileExam {
	private static Logger LOGGER = LoggerFactory.getLogger(FileExam.class);
	
	public static void main(String[] args) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
//		Properties props = System.getProperties();
//		Iterator iter = props.keySet().iterator();
//		while (iter.hasNext()) {
//			Object key = iter.next();
//			
//			LOGGER.debug(String.format("[%s]:%s", key, props.get(key)));
//		}
		
		// 내 jvm의 os 정보를 알아오기 위해
		String osName = System.getProperty("os.name");
		LOGGER.debug("osName:" + osName);
		
		// user.home
		// file.separator
		// user.dir

		String currentDirStr = System.getProperty("user.dir");
		
		LOGGER.debug("currentDir:" + currentDirStr);
		
		File currentDir = new File(currentDirStr);
		
currentDir.getParentFile();

		File[] files = currentDir.listFiles();
		for (File oneFile : files) {
			String lastModified = sdf.format(new Date(oneFile.lastModified()));
			
			LOGGER.debug(String.format("%s\t%s\t%s\t%s", oneFile.getName(), oneFile.length(), lastModified, (oneFile.isDirectory()?"파일폴더":"파일")));
		}
	}
}
