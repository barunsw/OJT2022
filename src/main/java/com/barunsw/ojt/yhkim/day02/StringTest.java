package com.barunsw.ojt.yhkim.day02;

import java.math.BigDecimal;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.barunsw.ojt.constants.Gender;
import com.barunsw.ojt.vo.Person;

public class StringTest {
	private static Logger LOGGER = LoggerFactory.getLogger(StringTest.class);
	
	public static void main(String[] args) {
		
		String a = new String("Hello");
		String b = "Hello";
		
		// charAt(int index) 
		// 특정 인덱스에 해당하는 글자 반환
		LOGGER.debug(String.format("a의 1번째 글자 : %c", a.charAt(0))); // H
		
		// codePointAt(int index) 
		// 특정 인덱스에 해당하는 유니코드 반환
		LOGGER.debug(String.format("a의 1번째 글자에 해당하는 유니코드 : %d", a.codePointAt(0))); // 72

		// codePointBefore(int index)
		// 특정 인덱스 값의 직전 값을 유니코드로 반환
		LOGGER.debug(String.format("a의 2번째 글자 직전 값에 해당하는 유니코드 : %d", a.codePointBefore(1))); // 72
		
		// codePointCount(int beginIndex, int endIndex)
		// 문자열의 beginIndex 위치(포함)에서 endIndex 위치(비포함)까지의 유니코드 개수 반환
		LOGGER.debug(String.format("a의 유니코드 개수 : %d", a.codePointCount(0, a.length()))); // 5
		
		// compareTo(String anotherString)
		// 문자열 비교하여 같은 경우 0 반환
		LOGGER.debug(String.format("a.compareTo(b) : %d", a.compareTo(b))); // 0
		// 같은 위치의 문자가 다른 경우 한 자리씩 비교하여 다른 두 문자의 아스키 코드 값의 차 반환 (l의 아스키 코드 값 - i의 아스키 코드 값 = 43 - 40)
		LOGGER.debug(String.format("a.compareTo(\"Helio\") : %d", a.compareTo("Helio"))); // 3
		// 문자열의 길이가 다르면 차이만큼 반환
		LOGGER.debug(String.format("a.compareTo(\"Hell\") : %d", a.compareTo("Hell"))); // 1
		
		// compareToIgnoreCase(String str)
		// compareTo 메소드와 같은 원리지만 대소문자를 무시하여 비교
		LOGGER.debug(String.format("a와 HeLlo의 문자열 비교 : %d", a.compareToIgnoreCase("HeLlo"))); // 0
		
		// concat(String str)
		// 두 문자열을 연결해줌
		LOGGER.debug(String.format("a와 b의 문자열 연결 : %s", a.concat(b))); // HelloHello		

		// contains(CharSequence s)
		// 문자열에 특정 문자열이 포함되어 있는지 확인하여 boolean 형태로 반환
		LOGGER.debug(String.format("a에 H가 포함되어 있는지 비교 : %b", a.contains("H"))); // true
		LOGGER.debug(String.format("a에 Z가 포함되어 있는지 비교 : %b", a.contains("Z"))); // false
		
		// contentEquals(CharSequence s) / contentEquals(StringBuffer sb)
		// 다른 객체와 문자열이 동일한지만 확인하여 boolean 형태로 반환
		LOGGER.debug(String.format("a와 b의 문자열 비교 : %b", a.contentEquals(b))); // true
		LOGGER.debug(String.format("a.contentEquals(new StringBuffer(\"Hello\") : %b", a.contentEquals(new StringBuffer("Hello")))); // true
		
		// toCharArray()
		// 문자열을 한 글자씩 쪼개서 이를 char 타입의 배열에 넣어줌
		char[] arr = a.toCharArray();
		LOGGER.debug(String.format("a.toCharArray() : %s", Arrays.toString(arr))); // [H, e, l, l, o]
		
		// copyValueOf(char[] data) / copyValueOf(char[] data, int offset, int count)
		// 배열값을 연결하여 문자열로 만들어 반환
		LOGGER.debug(String.format("arr 문자열 : %s", String.copyValueOf(arr))); // Hello
		LOGGER.debug(String.format("arr의 1~4번째 자리 문자열 : %s", String.copyValueOf(arr, 0, 4))); // Hell

		// startsWith(String prefix)
		// 특정 문자열로 시작하는지 확인하여 boolean 반환
		LOGGER.debug(String.format("a.startsWith(\"H\") : %b", a.startsWith("H"))); // true
		LOGGER.debug(String.format("a.startsWith(\"p\") : %b", a.startsWith("p"))); // false
		
		// endsWith(String suffix)
		// 특정 문자열로 끝나는지 확인하여 boolean 반환
		LOGGER.debug(String.format("a.endsWith(\"o\") : %b", a.endsWith("o"))); // true
		LOGGER.debug(String.format("a.endsWith(\"p\") : %b", a.endsWith("p"))); // false
		
		// equals(Object anObject)
		// 다른 객체와 문자열을 비교하여 boolean 형태로 반환
		LOGGER.debug(String.format("a와 Hello의 문자열 비교 : %b", a.equals("Hello"))); // true
		LOGGER.debug(String.format("a와 Hell의 문자열 비교 : %b", a.equals("Hell"))); // false
		
		// equalsIgnoreCase(String anotherString)
		// 문자열의 대소문자를 구분하지 않고 비교하여 boolean 형태로 반환
		LOGGER.debug(String.format("a와 HeLlo의 문자열 비교 : %b", a.equalsIgnoreCase("HeLlo"))); // true
		
		// getBytes()
		// 문자열을 인코딩된 byte 배열로 반환
		LOGGER.debug(String.format("a를 byte 배열로 반환 %s", Arrays.toString(a.getBytes()))); // [72, 101, 108, 108, 111]

		// getChars(int srcBegin, int srcEnd, char[] dst, int dstBegin)
		// getChars(string 복사 시작, string 복사 종료, char 배열, char 배열 데이터 삽입 시작 인덱스)
		// 문자열에서 지정 범위 데이터를 문자 배열로 복사
		char arr2[] = new char[6];
		a.getChars(0, 4, arr2, 1);
		LOGGER.debug(String.format("a.getChars(0, 4, arr2, 1) : %s", Arrays.toString(arr2))); // [ , H, e, l, l, ]
		
		// hashCode()
		// 문자열을 해시코드 형태로 반환
		LOGGER.debug(String.format("a의 해시코드 : %s", a.hashCode())); // 69609650
		
		// indexOf(String str) / indexOf(String str, int fromIndex)
		// 해당하는 문자열의 인덱스 값을 반환, fromIndex부터 검색하게 할 수 있음
		LOGGER.debug(String.format("a의 'H'의 인덱스 값 : %s", a.indexOf("H"))); // 0
		// 해당하는 문자열이 없으면 -1을 반환
		LOGGER.debug(String.format("a의 'C'의 인덱스 값 : %s", a.indexOf("C"))); // -1
		
		// intern()
		// 문자열 풀에서 해당 문자열을 조회하여 존재하는 경우 반환, 아닌 경우 풀에 문자열을 등록하고 해당 문자열을 반환
		String c = a.intern();
		LOGGER.debug(String.format("a == b : %b", a == b)); // false
		LOGGER.debug(String.format("b == c : %b", b == c)); // true
		
		// length()
		// 문자열의 길이를 반환
		LOGGER.debug(String.format("a.length() : %d", a.length())); // 5
		
		// isEmpty()
		// 문자열 길이가 0이면 true, 아니면 false룰 반환
		LOGGER.debug(String.format("a.isEmpty() : %b", a.isEmpty())); // false
		
		// offsetByCodePoints(int index, int codePointOffset)
		// 지정된 인덱스에서 codePointOffset 코드포인트로 오프셋된 문자열 내의 인덱스를 반환
		LOGGER.debug(String.format("a.offsetByCodePoints(1, 2) : %d", a.offsetByCodePoints(1, 2))); // 3
				
		// regionMatches(boolean ignoreCase, int toffset, String other, int ooffset, int len)
		// igonoreCase가 true면 대소문자 구분없이 toffset위치부터 len길이까지, 주어진 문자열의 ooffset위치부터 len길이까지의 문자열을 비교
		LOGGER.debug(String.format("a.offsetByCodePoints(1, 2) : %b", a.regionMatches(true, 0, b, 0, 2))); // true
		
		// format(Locale l, String format, Object... args)
		// 서식 문자열을 사용한 형식 문자열을 생성 (%d, %s, %f, %b ...)
		float i = 9.3333f;
		LOGGER.debug(String.format("a를 format을 활용하여 출력 : %s", a)); // Hello
		LOGGER.debug(String.format("9.3333의 소수 1번째 자리까지 출력 : %.1f", i)); // 9.3
				
		String str2 = "Wonderful World";
		
		// lastIndexOf(String str)
		// 뒤에서부터 str이 발견된 인덱스를 반환, 없으면 -1 반환
		LOGGER.debug(String.format("str2.indexOf('W') : %d", str2.indexOf('W'))); // 0
		LOGGER.debug(String.format("str2.lastIndexOf('W') : %d", str2.lastIndexOf('W'))); // 10
		
		// toLowerCase()
		// 문자열을 모두 소문자로 변환
		LOGGER.debug(String.format("str2.toLowerCase() : %s", str2.toLowerCase())); // wonderful world
		
		// toUpperCase()
		// 문자열을 모두 대문자로 변환
		LOGGER.debug(String.format("str2.toUpperCase() : %s", str2.toUpperCase())); // WONDERFUL WORLD

		// matches(String regex)
		// 정규식과 문자열이 일치하는지 확인하여 boolean 형태로 반환
		LOGGER.debug(String.format("str2.matches(\"world\") : %s", str2.matches("world"))); // false
		LOGGER.debug(String.format("str2.matches(\"(.*)world(.*)\") : %s", str2.matches("(.*)world(.*)"))); // true	
		
		// replace(charSequence target, charSequence replacement)
		// target을 replacement로 대체
		LOGGER.debug(String.format("str2.replace(\"w\", \"b\") : %s", str2.replace("w", "b"))); // bonderful borld
		
		// replaceAll(String regex, String replacement)
		LOGGER.debug(String.format("str2.replaceAll(\".\", \"^\") : %s", str2.replaceAll(".", "^"))); // .(마침표)는 정규식으로 모든 문자표
		
		// replaceFirst(String regex, String replacement)
		LOGGER.debug(String.format("str2.replaceFirst(\"w\", \"b\") : %s", str2.replaceFirst("w", "b"))); // bonderful world
		
		String num = " 010-1234-5678 ";
		
		// trim()
		// 문자열의 공백을 없애준다.
		LOGGER.debug(String.format("num : %s", num.trim())); // 010-1234-5678
		
		// split(String regex)
		String[] numArr = num.split("-");
		LOGGER.debug(String.format("Arrays.toString(numArr) : %s", Arrays.toString(numArr)));
		
		// subSequence(int beginIndex, int endIndex)
		// beginIndex(포함)부터 endIndex(비포함)까지 반환
		LOGGER.debug(String.format("num.subSequence(3, 6) : %s", num.subSequence(3, 6))); // 0-1
		
		// substring(int beginIndex) / substring(int beginIndex, int endIndex)
		// beginIndex(포함)부터 endIndex(비포함)까지 반환		
		LOGGER.debug(String.format("num.substring(3) : %s", num.substring(3))); // 0-1234-5678 
		LOGGER.debug(String.format("num.substring(3, 6) : %s", num.substring(3, 6))); // 0-1
		
		// valueOf()
		// 괄호안의 해당 객체를 String 객체로 변환
		LOGGER.debug(String.format(String.valueOf(10)+String.valueOf(false))); // 10false

		// toString()
		// 상위 클래스인 Object클래스가 가진 toString 메서드를 오버라이딩하여 사용
		Person p = new Person();
		p.setName("홍길동");
		p.setAge(20);
		p.setGender(Gender.MAN);
		
		LOGGER.debug(String.format("p.toString() : %s", p.toString())); 
		// com.barunsw.ojt.vo.Person@63eef88a[name=홍길동,age=20,gender=MAN]

	}
}
