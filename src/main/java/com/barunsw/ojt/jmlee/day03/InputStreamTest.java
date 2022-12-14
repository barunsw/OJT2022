package com.barunsw.ojt.jmlee.day03;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class InputStreamTest {

	private static Logger LOGGER = LoggerFactory.getLogger(InputStreamTest.class);
	
	public static void main(String[] args) {

		/* File file = new File("data/TextTest.txt");
		
		InputStream inputStream = null;
		try {
			inputStream = new FileInputStream(file);
			
			byte[] buf = new byte[21];
			inputStream.read(buf);
			
			LOGGER.debug(String.format("readData:[%s] ", new String(buf)));
			
			
		} catch (FileNotFoundException fnfe) { // 파일이 없는경우 예외
			LOGGER.error(fnfe.getMessage());
		} 
		
		catch (IOException ioe) { // 입출력에 문제가 있는경우 예외
			LOGGER.error(ioe.getMessage());
		}
		finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				}
				catch (IOException ioe) {
					LOGGER.error(ioe.getMessage(), ioe);
				}
			}
		} */
		
		byte[] buf = new byte[10];

		File srcFile = new File("data/TextTest.txt");
		File destFile = new File("data/TextTest2.txt");
        // try괄호 안에 stream을 사용하면 finally를 작성하지 않아도 close()를 사용한것과 동일하다.
		try (InputStream inputStream = new FileInputStream(srcFile);
				OutputStream outputStream = new FileOutputStream(destFile)) {
			int readNum = 0;
			while ((readNum = inputStream.read(buf)) > 0) {
				outputStream.write(buf, 0, readNum);
			}
		}
		catch (FileNotFoundException fnfe) {
			LOGGER.error(fnfe.getMessage(), fnfe);
		}
		catch (IOException ioe) {
			LOGGER.error(ioe.getMessage(), ioe);
		}

	}

}
