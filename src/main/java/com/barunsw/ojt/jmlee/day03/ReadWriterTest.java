package com.barunsw.ojt.jmlee.day03;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ReadWriterTest {

	private static Logger LOGGER = LoggerFactory.getLogger(ReadWriterTest.class);
	
	public static void main(String[] args) {

		char[] buf = new char[20];

		File file = new File("data/TextTest.txt");
		try(FileReader reader = new FileReader(file)) {
			
			reader.read(buf);
			LOGGER.debug(String.format("reader : %s ", new String(buf)));

			
		} catch (FileNotFoundException fnfe) {
			LOGGER.error(fnfe.getMessage());

		} catch (IOException ioe) {
			LOGGER.error(ioe.getMessage());
		}

	}

}
