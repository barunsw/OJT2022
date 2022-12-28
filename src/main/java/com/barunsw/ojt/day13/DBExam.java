package com.barunsw.ojt.day13;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DBExam {
	private static Logger LOGGER = LoggerFactory.getLogger(FileExam.class);
	
	private final static String URL = "jdbc:mysql://localhost:3306/BARUN_RENTAL?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC&useSSL=false";
	private final static String USER = "root";
	private final static String PASSWD = "real3817";

	public static void main(String[] args) throws Exception {
		Class.forName("org.mariadb.jdbc.Driver");
		
		Connection conn = DriverManager.getConnection(URL, USER, PASSWD);
		LOGGER.debug("isClosed:" + conn.isClosed());
		
		Statement stmt = conn.createStatement();
		ResultSet resultSet = stmt.executeQuery("SHOW TABLES");
		while (resultSet.next()) {
			LOGGER.debug(resultSet.getString(1));
		}
		
		DatabaseMetaData metaData = conn.getMetaData();
		String[] types = {"TABLE"};
		ResultSet tables = metaData.getTables(null, null, "%", types);
		while (tables.next()) {
			LOGGER.debug(tables.getString("TABLE_NAME"));
		}
	}
}
