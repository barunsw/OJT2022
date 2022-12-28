package com.barunsw.ojt.yhkim.day13;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DBExam {
	private static Logger LOGGER = LoggerFactory.getLogger(DBExam.class);
	
	private final static String URL = "jdbc:mysql://localhost:3306/OJT?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC&useSSL=false";
	private final static String USER = "root";
	private final static String PASSWD = "1234";

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
		List<String> list = new ArrayList();
		List<Map<String, String>> map = new ArrayList<>();
		
		//HashMap<String, String> map = new HashMap<String, String>();
		
		while (tables.next()) {
			LOGGER.debug(tables.getString("TABLE_NAME"));
			list.add(tables.getString("TABLE_NAME"));
		}
		
		for(String l : list) {
			String sql = "select * from "+l;
			Statement stmt2 = conn.createStatement();
			ResultSet rs = stmt2.executeQuery(sql);
			
			ResultSetMetaData rsmd = rs.getMetaData();
			
			int cols = rsmd.getColumnCount();
			
			for(int i = 1; i<=cols; i++) {
				String colInfo = rsmd.getColumnName(i) +" "+rsmd.getColumnTypeName(i)
				+"("+rsmd.getColumnType(i)+")";
				//LOGGER.debug(colInfo);
				HashMap<String, String> m = new HashMap<String, String>();
				m.put(l, colInfo);
				map.add(m);
			}
		}
		for(int i = 0; i<map.size(); i++) {
			LOGGER.debug(map.get(i).toString());
		}
	}
}
