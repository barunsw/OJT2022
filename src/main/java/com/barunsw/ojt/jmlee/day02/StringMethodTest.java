package com.barunsw.ojt.jmlee.day02;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class Person {
	private String name;
	private int age;
	
	public Person(String name, int age) {
		this.name = name;
		this.age = age;
	}
	
	@Override
	public String toString() {
		return "이름 : " + name + " 나이: " + age;
	}
	
}


public class StringMethodTest {

	
	private static Logger LOGGER = LoggerFactory.getLogger(StringMethodTest.class); 
	
	public static void main(String[] args) {

//		String str = "ABCD";
//		LOGGER.debug("charAt(0) = " + str.charAt(0));
//		
//		String str = "ABCD";
//		LOGGER.debug("codePointAt(0) = " + str.codePointAt(0));
		
//		String str = "ABCD";
//		LOGGER.debug("codePointBefore(1) = " + str.codePointBefore(1));
		
//		String str = "ABCD";
//		LOGGER.debug("(str.codePointCount(3, 4) = " + (str.codePointCount(3, 4)));
		
//		String str = "ABCD";
//		LOGGER.debug("str.compareTo('ABCD') = " + str.compareTo("ABCD"));
//		LOGGER.debug("str.compareTo('A') = " + str.compareTo("A"));
//		LOGGER.debug("str.compareTo('ABCB') = " + str.compareTo("ABCB"));
		
//		String str = "ABCD";
//		LOGGER.debug("str.compareToIgnoreCase('abcd') = " + str.compareToIgnoreCase("abcd"));
//		LOGGER.debug("str.compareToIgnoreCase('aBcD') = " + str.compareToIgnoreCase("aBcD"));
		
//		String str = "ABCD";
//		String result = str.concat(null);
//		LOGGER.debug(result);
		
//		String str = "가나다라";
//		String str2 = "가나";
//		String str3 = "나가다";
//		if (str.contains(str2)) {
//			LOGGER.debug("str에 str2가 포함돼있다.");
//		} else {
//			LOGGER.debug("str에 str2가 포함돼있지 않다.");
//		}
//		if (str.contains(str3)) {
//			LOGGER.debug("str에 str3가 포함돼있다.");
//		} else {
//			LOGGER.debug("str에 str3가 포함돼있지 않다.");
//		}

//		String str = "가나다";
//		String str2 = "가나다";
//		String str3 = "가나";
//		if (str.contentEquals(str2)) {
//			LOGGER.debug("str에 str2가 포함돼있다");
//		} else {
//			LOGGER.debug("str에 str2가 포함되지않았다");
//		}		
//		if (str.contentEquals(str3)) {
//			LOGGER.debug("str에 str3가 포함돼있다");
//		} else {
//			LOGGER.debug("str에 str3가 포함되지않았다");
//		}		

//		char[] c = { '가', '나', '다', '라', '마' };
//
//		String str = "";
//		LOGGER.debug(str.copyValueOf(c));
//		LOGGER.debug(str.copyValueOf(c, 0, 2));

//		String str = "ABCD";
//		LOGGER.debug("str.equals('ABCD') = " + str.equals("ABCD"));
//		LOGGER.debug("str.equals('ABC') = " + str.equals("ABC"));

//		String str = "ABCD";
//		LOGGER.debug("str.equalsIgnoreCase('ABCD') = " + str.equalsIgnoreCase("ABCD"));
//		LOGGER.debug("str.equalsIgnoreCase('AbCd') = " + str.equalsIgnoreCase("AbCd"));

//		String str = "String Sample";
//		boolean bool = true;
//		int i = 1234;
//		float n = 2.43043f;
//		LocalDateTime now = LocalDateTime.now();
//		
//		String result = String.format("문자열 서식: %s, %S", str, str);
//		LOGGER.debug(result);
//		
//		result = String.format("boolean 서식 문자열: %B, %b", bool, bool);
//		LOGGER.debug(result);
//		
//		result = String.format("정수 서식: %d, %o, Dx, %05d", i, i, i, i);
//		LOGGER.debug(result); // %05d → 5자리수, 공백을 0으로 채움
//		
//		result = String.format("float 서식: %.1f", n);
//		LOGGER.debug(result);
//		
//        result = String.format("날짜/시간 서식: %tY년 %<tm월 %<td일 %<tH시 %<tM분 %<tS초", now);
//        LOGGER.debug(result);  // $< 로 상대 인덱스 지정하여 직전의 인수와 같은 인덱스를 이용

//		String str = "Good Java Programing";
//			
//		LOGGER.debug("" + str.indexOf("Java"));
//		LOGGER.debug("" + str.indexOf("apple"));

//		String str = "Good Java Programing";
//		String str2 = "";
//			
//		LOGGER.debug("" + str.isEmpty());
//		LOGGER.debug("" + str2.isEmpty());

//		String str = "Good Java Programing";
//		String str2 = "Good Python Programing";
//		
//		LOGGER.debug("문자열이 일치한가? : " + str.matches("(.*)Java(.*)"));
//		LOGGER.debug("문자열이 일치한가? : " + str2.matches("(.*)Java(.*)"));

//		String str = "Good Java Programing";
//		
//		LOGGER.debug("str.replace('Java', 'Python') >> " + str.replace("Java", "Python"));

//		String str = "Good Java Programing";
//		
//		LOGGER.debug("str.substring(5) >> " + str.substring(5));

//		String str = "      Good Java Programing     ";
//		
//		LOGGER.debug("[" + str + "]");
//		LOGGER.debug("[" + str.trim() + "]");

//		String str = "Programing";
//		char[] charArr = str.toCharArray();
//		
//		for(int i = 0; i < charArr.length; i++) {
//			LOGGER.debug(charArr[i]+"");
//		}

//		String str1 = "1234";
//		String str2 = String.valueOf(10);
//		String str3 = String.valueOf(3.14);
//		String str4 = String.valueOf(true);
//		
//		LOGGER.debug(str1);
//		LOGGER.debug(str2);
//		LOGGER.debug(str3);
//		LOGGER.debug(str4);
//		LOGGER.debug(str1+str2+str3+str4);
	
		Person person = new Person("이순신",20);
		
		LOGGER.debug(person+"");
		
	}
	

}
