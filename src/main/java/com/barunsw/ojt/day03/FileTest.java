package com.barunsw.ojt.day03;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileTest {
	private static Logger LOGGER = LoggerFactory.getLogger(FileTest.class);
	
	public static void main(String[] args) {
		File file = new File("data/HelloWorld.txt");
		//File file = new File("C:\\Users\\sbae7\\git\\OJT2022\\data\\HelloWorld.txt");
		//File file = new File("C:/Users/sbae7/git/OJT2022/data/HelloWorld.txt");
		
		//LOGGER.debug("filePath:" + file.getAbsolutePath());
		//LOGGER.debug("exists:" + file.exists());
		
		// Stream, Reader/Writer
		/*
		InputStream inputStream = null;
		try {
			inputStream = new FileInputStream(file);
			
			byte[] buf = new byte[19];
			inputStream.read(buf);
			
			LOGGER.debug(String.format("readData:[%s]", new String(buf)));
		}
		catch (FileNotFoundException fnfe) {
			LOGGER.error(fnfe.getMessage(), fnfe);
		}
		catch (IOException ioe) {
			LOGGER.error(ioe.getMessage(), ioe);
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
		}
		*/
		
		
		/*Reader reader = null;
		try {
			reader = new FileReader(file);
			
			char[] buf = new char[15];
			reader.read(buf);
			
			LOGGER.debug(String.format("readData:[%s]", new String(buf)));
		}
		catch (FileNotFoundException fnfe) {
			LOGGER.error(fnfe.getMessage(), fnfe);
		}
		catch (IOException ioe) {
			LOGGER.error(ioe.getMessage(), ioe);
		}
		finally {
			if (reader != null) {
				try {
					reader.close();
				}
				catch (IOException ioe) {
					LOGGER.error(ioe.getMessage(), ioe);
				}
			}
		}*/
		
		
		/*
		List<String> repo = new ArrayList<>();
		
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			
			String readLine = null;
			while ((readLine = reader.readLine()) != null) {
				LOGGER.debug(String.format("readData:[%s]", readLine));
				
				repo.add(readLine);
			}
		}
		catch (FileNotFoundException fnfe) {
			LOGGER.error(fnfe.getMessage(), fnfe);
		}
		catch (IOException ioe) {
			LOGGER.error(ioe.getMessage(), ioe);
		}
		finally {
			if (reader != null) {
				try {
					reader.close();
				}
				catch (IOException ioe) {
					LOGGER.error(ioe.getMessage(), ioe);
				}
			}
		}
		
		File f2 = new File("data/HelloWorld2.txt");
		try (Writer writer = new FileWriter(f2)) {
			String s = StringUtils.join(repo, "\n");
			
			writer.write(s);
		}
		catch (FileNotFoundException fnfe) {
			LOGGER.error(fnfe.getMessage(), fnfe);
		}
		catch (IOException ioe) {
			LOGGER.error(ioe.getMessage(), ioe);
		}
		*/
		/*
		try (InputStream inputStream2 = new FileInputStream(file)) {
			
		}
		catch (FileNotFoundException fnfe) {
			LOGGER.error(fnfe.getMessage(), fnfe);
		}
		catch (IOException ioe) {
			LOGGER.error(ioe.getMessage(), ioe);
		}
		*/
		
		
		// copy
		byte[] buf = new byte[10];

		File srcFile = new File("data/HelloWorld.txt");
		File destFile = new File("data/HelloWorld3.txt");
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
		
		// ObjectInputStream / ObjectOutputStream 
	}
}
