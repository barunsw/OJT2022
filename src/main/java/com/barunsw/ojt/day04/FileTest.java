package com.barunsw.ojt.day04;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.barunsw.ojt.vo.Person;

public class FileTest {
	private static Logger LOGGER = LoggerFactory.getLogger(FileTest.class);
	
	public static void main(String[] args) {
		String filePath = "data/addressbook.dat";
		
		List<Person> personList = new ArrayList<Person>();
		
		try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(filePath))) {
			Object o = null;
			while ((o = inputStream.readObject()) != null) {
				if (o instanceof Person) {
					Person p = (Person)o;
					
					personList.add(p);
				}
			}
		}
		catch (EOFException eofe) {}
		catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
		}
		
		for (Person p : personList) {
			LOGGER.debug("p:" + p);
		}
		
/*		
		Person p1 = new Person("배수현", 48, Gender.MAN);
		Person p2 = new Person("홍길동", 30, Gender.MAN);
		Person p3 = new Person("유관순", 20, Gender.WOMAN);
		
		personList.add(p1);
		personList.add(p2);
		personList.add(p3);
		
		try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(filePath))) {
			for (Person p : personList) {
				outputStream.writeObject(p);
			}
		}
		catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
		}
*/		
	}
}
