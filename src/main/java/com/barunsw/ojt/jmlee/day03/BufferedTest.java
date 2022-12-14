package com.barunsw.ojt.jmlee.day03;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class BufferedTest {

	private static Logger LOGGER = LoggerFactory.getLogger(ReadWriterTest.class);

	public static void main(String[] args) {

		File file = new File("data/TextTest.txt");
		
		List<String> repo = new ArrayList<>();

		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			String readLine = null; // readline으로 한줄씩 읽어오기가 가능
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
			//문자열이 만나면 결합하게 하는함수 \n 으로 개행
			String s = StringUtils.join(repo, "\n");  
			
			writer.write(s);
		}
		catch (FileNotFoundException fnfe) {
			LOGGER.error(fnfe.getMessage(), fnfe);
		}
		catch (IOException ioe) {
			LOGGER.error(ioe.getMessage(), ioe);
		}
		
	}

}
