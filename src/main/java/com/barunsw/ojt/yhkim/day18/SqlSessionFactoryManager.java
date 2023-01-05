package com.barunsw.ojt.yhkim.day18;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class SqlSessionFactoryManager {
	private static Logger LOGGER = LoggerFactory.getLogger(SqlSessionFactoryManager.class);
	// 데이터베이스와의 연결과 SQL의 실행에 대한 모든 것을 가진 가장 중요한 객체
	private static final SqlSessionFactory SQLMAPPER;

	// 가장 먼저 실행. 맨 처음에만 실행되고 이후 부터는 실행되지 않아 매번 connection하지 않아도 됨.
	static {
		// config.xml 파일 경로 설정
		String resource = "com/barunsw/ojt/yhkim/day18/SqlMapConfig.xml";
		
		// 데이터를 읽어오는것 (Reader or InputStream)
		Reader reader = null;
		try {
			reader = Resources.getResourceAsReader(resource);
		}
		catch (IOException ioe) {
			LOGGER.error(ioe.getMessage(), ioe);
		}
		// build 설정
		SQLMAPPER = new SqlSessionFactoryBuilder().build(reader, "development", System.getProperties());
	}
	
	public static SqlSessionFactory getSqlSessionFactory() {
		return SQLMAPPER;
	}
}