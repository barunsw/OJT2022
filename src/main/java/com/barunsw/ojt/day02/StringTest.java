package com.barunsw.ojt.day02;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.barunsw.ojt.constants.Gender;
import com.barunsw.ojt.vo.Person;

public class StringTest {
	private static Logger LOGGER = LoggerFactory.getLogger(StringTest.class);
	
	public static void main(String[] args) {
		String a = "Hello World";
		String b = "Hello World";
		String c = "hELLO wORLD";
		
		if (a == b) {
			LOGGER.debug("레퍼런스가 같다.");
		}
		else {
			LOGGER.debug("레퍼런스가 다르다.");
		}
		
		if (a.equals(b)) {
			LOGGER.debug("내용이 같다.");
		}
		else {
			LOGGER.debug("내용이 다르다.");
		} 
		
		if (a.equalsIgnoreCase(c)) {
			LOGGER.debug("내용이 같다.");
		}
		else {
			LOGGER.debug("내용이 다르다.");
		} 
		
		LOGGER.debug("endsWith:" + a.endsWith("World"));
		
		float f = 1.33333f;
		
		LOGGER.debug("a=" + a + ", b=" + b + ", c=" + c);
		LOGGER.debug(String.format("a=%s, b=%s, c=%s", a, b, c));
		LOGGER.debug(String.format("f=%.3f", f));
		

		Person p = new Person();
		p.setName("배수현");
		p.setAge(48);
		p.setGender(Gender.MAN);
		
		LOGGER.debug(p.toString());

		LOGGER.debug("p.hashCode:" + p.hashCode());
		
		Person p1 = new Person();
		p1.setName("홍길동");
		p1.setAge(48);
		p1.setGender(Gender.MAN);

		LOGGER.debug("p1.hashCode:" + p1.hashCode());
		
		HashMap<Person, Person> map = new HashMap<>();
		map.put(p , p);
		map.put(p1 , p1);
		
		Iterator<Person> iter = map.keySet().iterator();
		while (iter.hasNext()) {
			Object key = iter.next();
			LOGGER.debug(String.format("[%s]:%s", key, map.get(key)));
		}
		
		String t = "    \t\nHello World\n\n\n\nHello World\n\n\n\n";
		
		LOGGER.debug(String.format("[%s]", t.trim()));
		
		
		//byte(1), char, boolean(1), short(2), int(4), long(8), float(4), double(8) 
		
//		Byte b1;
//		Character c1;
//		Boolean b2;
//		Short s1;
//		Integer i = 1;
		
		int i = 1;
		//String is = "" + i;
		String is = Integer.toString(i);		
		String ls = Long.toString(100L);
		
		int ss = 40000;
		short ii = (short)ss;
		
		LOGGER.debug("ii:" + ii);
		
		BigDecimal total = new BigDecimal(1);
		for (int j = 1; j <= 100; j++) {
			total = total.multiply(new BigDecimal(j));
		}
		
		LOGGER.debug("total=" + total);
	}
}
