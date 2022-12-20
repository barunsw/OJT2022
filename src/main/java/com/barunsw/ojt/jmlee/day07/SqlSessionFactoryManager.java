package com.barunsw.ojt.jmlee.day07;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SqlSessionFactoryManager {
	private static final Logger LOGGER = LogManager.getLogger(SqlSessionFactoryManager.class);
	private static final SqlSessionFactory sqlMapper;
//  ﻿처음엔 null이었 맵퍼가 static블럭이 실행된후 리턴되어 객체가 생성된다
	static {
		String resource = "com/barunsw/ojt/jmlee/day07/SqlMapConfig.xml";

		Reader reader = null;

		try {
			reader = Resources.getResourceAsReader(resource);
		} 
		catch ( IOException ex ) {
			LOGGER.error(ex.getMessage(), ex);
		}
		
		long startTime = System.currentTimeMillis();
		
		sqlMapper = new SqlSessionFactoryBuilder().build(reader, "development", System.getProperties());
		
		long endTime = System.currentTimeMillis();
		
		System.out.println(String.format("SqlSessionFactoryManager created(%s)", (endTime - startTime)));
	}

	public static SqlSessionFactory getSqlSessionFactory() {
		return sqlMapper;  
	}
}

