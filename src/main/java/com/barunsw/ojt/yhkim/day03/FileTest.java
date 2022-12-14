package com.barunsw.ojt.yhkim.day03;

import java.io.*;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileTest {
	private static Logger LOGGER = LoggerFactory.getLogger(FileTest.class);

	public static void main(String[] args) throws IOException {

		// 생성자
		// File(String pathname)
		File file1 = new File("data/HelloWorld.txt");

		// File(File parent, String child)
		File file2 = new File("data");
		File file3 = new File(file2, "HelloWorld.txt");

		// File(String parent, String child)
		File file4 = new File("data", "HelloWorld.txt");
		
		
		// 파일 조회 관련 메소드
		// String getName() 파일이나 폴더의 이름 반환
		LOGGER.debug("file1.getName(): " + file1.getName()); // HelloWorld.txt

		// String getPath() 파일의 경로를 문자열로 반환
		LOGGER.debug("file1.getPath(): " + file1.getPath()); // data\HelloWorld.txt

		// File getAbsoluteFile() 파일의 절대 경로 반환
		LOGGER.debug("file1.getAbsoluteFile(): " + file1.getAbsoluteFile()); // C:\\Users\\user\\git\\OJT2022\\data\\HelloWorld.txt

		// String getAbsolutePath() 파일의 절대 경로를 문자열로 반환
		LOGGER.debug("file1.getAbsolutePath(): " + file1.getAbsolutePath()); // C:\\Users\\user\\git\\OJT2022\\data\\HelloWorld.txt

		// String getCanonicalFile() 파일의 정규 경로 반환
		LOGGER.debug("file1.getCanonicalFile(): " + file1.getCanonicalFile()); // C:\\Users\\user\\git\\OJT2022\\data\\HelloWorld.txt

		// String getCanonicalPath() 파일의 정규 경로를 문자열로 반환
		LOGGER.debug("file1.getCanonicalPath(): " + file1.getCanonicalPath()); // C:\\Users\\user\\git\\OJT2022\\data\\HelloWorld.txt

		// File getParentFile() 부모 경로 반환
		LOGGER.debug("file1.getParentFile(): " + file1.getParentFile()); // data

		// String getParent() 부모 경로에 대한 경로명을 문자열로 반환
		LOGGER.debug("file1.getParent(): " + file1.getParent()); // data

		// long getTotalSpace() 하드디스크의 총 용량 반환
		LOGGER.debug("file1.getTotalSpace(): " + file1.getTotalSpace()); // 918378844160

		// long getUsableSpace() 하드디스크의 사용 가능한 용량 반환
		LOGGER.debug("file1.getUsableSpace(): " + file1.getUsableSpace()); // 853204889600

		// long getFreeSpace() 하드디스크의 남은 용량 반환
		LOGGER.debug("file1.getFreeSpace(): " + file1.getFreeSpace()); // 853204889600

		// int hashCode() hash code 반환
		LOGGER.debug("file1.hashCode(): " + file1.hashCode()); // 1003209317

		// long lastModified() 해당 경로 파일의 최종 수정 일자 반환
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd / HH:mm:ss");
		LOGGER.debug(sf.format(file1.lastModified())); // 2022-12-14 / 10:24:32

		// long length() 해당 경로 파일의 길이 반환
		LOGGER.debug("file1.length(): " + file1.length()); // 125

		// Path toPath() java.nio.file.Path 객체 반환
		Path file_to_path = file1.toPath();
		LOGGER.debug("file1.toPath(): " + file_to_path.toString()); // data\HelloWorld.txt

		// URI toURI() URI 형태로 파일 경로 반환
		LOGGER.debug("file1.toURI(): " + file1.toURI()); // file:/C:/Users/user/git/OJT2022/data/HelloWorld.txt

		
		// 파일 및 디렉토리 조작 관련 메소드
		// boolean mkdir()	해당 경로에 폴더를 생성
		// boolean createNewFile() 주어진 이름의 파일이 없으면 새로 생성
		// boolean mkdirs()	존재하지 않는 부모 디렉토리까지 포함하여 해당 경로에 폴더 생성
		// boolean renameTo(File dest)	파일를 dest로 이름뿐만 아니라 경로도 변경

		
		// 파일 체크 관련 메소드
		// boolean exists()	파일의 존재 여부 반환
		LOGGER.debug("file1.exists(): " + file1.exists()); // true
		
		// boolean isAbsolute()	해당 경로가 절대 경로인지 여부 반환
		LOGGER.debug("file1.isAbsolute(): " + file1.isAbsolute()); // false

		// boolean isDirectory() 해당 경로가 디렉토리인지 여부 반환
		LOGGER.debug("file1.isDirectory(): " + file1.isDirectory()); // false
				
		// boolean isFile()	해당 경로가 file인지 여부 반환
		LOGGER.debug("file1.isFile(): " + file1.isFile()); // true
				
		// boolean isHidden() 해당 경로가 숨김 파일인지 여부 반환
		LOGGER.debug("file1.isHidden(): " + file1.isHidden()); // false
		
		// 파일 권한 관련 메소드
		// boolean canExecute()	파일 실행 권한이 있는지 여부 반환
		LOGGER.debug("file1.canExecute(): " + file1.canExecute()); // true
		// boolean canRead() 파일을 읽기 권한이 있는지 여부 반환
		LOGGER.debug("file1.canRead(): " + file1.canRead()); // true
		// boolean canWrite() 파일을 쓰기 권한이 있는지 여부를 반환
		LOGGER.debug("file1.canWrite(): " + file1.canWrite()); // true	
		
		
		// InputStream
		// int available() 스트림에서 읽을 수 있는 바이트 개수 반환
		// abstract int read() 1바이트를 읽고 읽은 바이트 반환
		// int read(byte[] b) 지정된 배열 크기만큼 읽고 버퍼 배열에 저장 후 읽은 개수 반환
		// int read(byte[] b, int off, int len) b배열의 len만큼 읽어 off 위치에 저장 후 읽은 개수 반환
		// void close() 스트림 닫고 자원 해제
		
		// OutputStream
		// abstract void read() 하위 1바이트 출력
		// void read(byte[] b) 버퍼의 내용을 출력
		// void write(byte[] b, int off, int len) b배열에 off부터 len만큼 출력
		// void flush()	버퍼에 남아있는 출력 스트림 출력
		// void close() 스트림 닫고 자원 해제
		
		byte[] buf = new byte[10];

		File srcFile = new File("data/HelloWorld.txt");
		File destFile = new File("data/HelloWorld2.txt");
		
		// Stream 선언을 try 괄호 안에 넣어주면 finally를 이용해 close()를 사용할 필요 없음
		try (InputStream inputStream = new FileInputStream(srcFile);
				OutputStream outputStream = new FileOutputStream(destFile)) {
			int readNum = 0;
			
			// int available()
			LOGGER.debug("file1.available(): " + inputStream.available()); // 125	
			
			// abstract int read()
			LOGGER.debug(String.format("file1.read(): %c", inputStream.read())); // H	

			// int read(byte[] b)
			while ((readNum = inputStream.read(buf)) > 0) {
				// void write(byte[] b, int off, int len)
				outputStream.write(buf, 0, readNum); // H를 제외한 글이 저장됨
			}
		}
		catch (FileNotFoundException fnfe) {
			LOGGER.error(fnfe.getMessage(), fnfe);
		}
		catch (IOException ioe) {
			LOGGER.error(ioe.getMessage(), ioe);
		}
		
		// ﻿Reader
		// FileReader, InputStreamReader, BufferedReader

		// Writer
		// FileWriter, PrintWriter, OutputWriter, BufferedWriter

		char[] buf2 = new char[10];
		
		try (Reader reader = new FileReader(file1)) {
			reader.read(buf2);
			LOGGER.debug(String.format("reader: %s", new String(buf2))); // Hello(\r)(\n)Wor
		}
		catch (FileNotFoundException fnfe) {
			LOGGER.error(fnfe.getMessage(), fnfe);
		}
		catch (IOException ioe) {
			LOGGER.error(ioe.getMessage(), ioe);
		}
		
//		List<String> repo = new ArrayList<>();
//		
//		File srcFile2 = new File("data/HelloWorld.txt");
//		
//		// 한 줄씩 읽어오기 위해 BufferedReader 사용
//		try (BufferedReader br = new BufferedReader(new FileReader(srcFile2))) {
//			String readLine = null;		
//			// void readLine() 데이터 라인 단위로 읽기 가능함
//			while ((readLine = br.readLine()) != null) {
//				LOGGER.debug(String.format("readData:[%s]", readLine));
//				
//				repo.add(readLine);
//			}
//		}
//		catch (FileNotFoundException fnfe) {
//			LOGGER.error(fnfe.getMessage(), fnfe);
//		}
//		catch (IOException ioe) {
//			LOGGER.error(ioe.getMessage(), ioe);
//		}
//		
//		File destFile2 = new File("data/HelloWorld2.txt");
//		try (BufferedWriter bw = new BufferedWriter(new FileWriter(destFile2))) {
//			// 문자열과 문자열이 만나면 "\n"을 추가
//			String s = StringUtils.join(repo, "\n");
//
//			bw.write(s);
//		}
//		catch (FileNotFoundException fnfe) {
//			LOGGER.error(fnfe.getMessage(), fnfe);
//		}
//		catch (IOException ioe) {
//			LOGGER.error(ioe.getMessage(), ioe);
//		}

//		File destFile2 = new File("data/HelloWorld2.txt");
//
//		// 한 줄씩 읽어오기 위해 BufferedReader 사용
//		try (BufferedReader br = new BufferedReade r(new FileReader(srcFile2));
//				BufferedWriter bw = new BufferedWriter(new FileWriter(destFile2))) {
//			String readLine = null;		
//			// void readLine() 데이터 라인 단위로 읽기 가능함
//			while ((readLine = br.readLine()) != null) {
//				LOGGER.debug(String.format("readData:[%s]", readLine));
//				
//				bw.write(readLine);
//				bw.newLine();
//			}
//		}
//		catch (FileNotFoundException fnfe) {
//			LOGGER.error(fnfe.getMessage(), fnfe);
//		}
//		catch (IOException ioe) {
//			LOGGER.error(ioe.getMessage(), ioe);
//		}
		

	}
}
