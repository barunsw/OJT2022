package com.barunsw.ojt.jmlee.day03;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileClassTest {

	private static Logger LOGGER = LoggerFactory.getLogger(FileClassTest.class);
	
	public static void main(String[] args) throws IOException {

		File file = new File("data/HelloWorld.txt");
		
		LOGGER.debug("====클래스의 조회 관련 메서드====");
		LOGGER.debug("file.getAbsoluteFile() = " + file.getAbsoluteFile()); //파일의 절대경로를 반환한다.
		LOGGER.debug("file.getAbsolutePath() = " + file.getAbsolutePath()); //파일의 절대경로를 문자열로 반환한다.
		LOGGER.debug("file.getCanonicalFile() = " + file.getCanonicalFile()); //파일의 정규 경로를 반환한다.
		LOGGER.debug("file.getName() = " + file.getName()); //파일이나 폴더의 이름을 넘겨준다.
		LOGGER.debug("file.getParent() = " + file.getParent()); //부모경로에 대한 경로명을 문자열로 반환한다.
		LOGGER.debug("file.getParentFile() = " + file.getParentFile()); //부모 폴더를 File의 형태로 반환한다.
		LOGGER.debug("file.getPath() = " + file.getPath()); //파일의 경로를 문자열의 형태로 반환한다.
		LOGGER.debug("file.hashCode() = " + file.hashCode()); //해쉬코드를 반환한다.
		LOGGER.debug("file.length() = " + file.length()); //해당 경로파일의 길이를 반환한다.
		LOGGER.debug("file.toURI() = " + file.toURI()); //URI형태로 파일경로를 반환한다.
		
		LOGGER.debug("====파일 체크 관련 메서드====");
		LOGGER.debug("file.exist() = " + file.exists()); //파일의 존재 여부를 반환한다.
		LOGGER.debug("file.isAbsolute() = " + file.isAbsolute()); //해당 경로가 절대경로인지 여부를 반환한다.
		LOGGER.debug("file.isDirectory() = " + file.isDirectory()); //해당 경로가 디렉토리인지 여부를 반환한다.
		LOGGER.debug("file.isFile() = " + file.isFile()); //해당 경로가 file인지 여부를 반환한다.
		LOGGER.debug("file.isHidden() = " + file.isHidden()); //해당 경로가 숨김 파일인지 여부를 반환한다.
		
		LOGGER.debug("====파일 권한 관련 메서드====");
		LOGGER.debug("file.canExecute() = " + file.canExecute()); //파일 실행 권한이 있는지 여부를 반환한다.
		LOGGER.debug("file.canRead() = " + file.canRead()); //파일 읽기 권한이 있는지 여부를 반환한다.
		LOGGER.debug("file.canWrite() = " + file.canWrite()); //파일 작성 권한이 있는지 여부를 반환한다.
		LOGGER.debug("file.setReadOnly() = " + file.setReadOnly()); //파일을 읽기 전용으로 변경한다.
		
	}

}
