package com.barunsw.ojt.yhkim.day13;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DBExplorerImpl {
	private static Logger LOGGER = LoggerFactory.getLogger(DBExplorerImpl.class);
	
	public static String NAME = "";
	private static String URL = "";
	private static String USER = "";
	private static String PASSWD = "";

	public DBExplorerImpl() throws Exception {
		Class.forName("org.mariadb.jdbc.Driver");
	}
	
	public void setInfo(String name, String id, String pw) {
		NAME = name;
		URL = "jdbc:mysql://localhost:3306/"+name+"?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC&useSSL=false";
		USER = id;
		PASSWD = pw;
	}

	public List<String> selectTableName(){
		List<String> list = new ArrayList<>();
		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWD);
				Statement stmt = conn.createStatement();){
			ResultSet rs = stmt.executeQuery("SHOW TABLES");
			
			while(rs.next()) {
				list.add(rs.getString(1));
			}
		}
		catch(Exception e) {
			LOGGER.error(e.getMessage());
		}

		return list;
	}
	
	public List<String> selectColumnName(String table){
		List<String> list = new ArrayList<>();
		
		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWD);
				Statement stmt = conn.createStatement();){
			ResultSet rs = stmt.executeQuery("SELECT * FROM "+table);
			ResultSetMetaData rsmd = rs.getMetaData();
			int cols = rsmd.getColumnCount();
			
			for(int i = 1; i<=cols; i++) {
				String colInfo = rsmd.getColumnName(i) +" "+rsmd.getColumnTypeName(i)
				+"("+rsmd.getColumnType(i)+")";
				
				list.add(colInfo);
			}
		}
		catch(Exception e) {
			LOGGER.error(e.getMessage());
		}

		return list;
	}
	
	public Vector<List> selectTable(String sql) {
		Vector<List> result = new Vector();
		Vector col = new Vector();
		Vector col2 = new Vector();
		
		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWD);
				Statement stmt = conn.createStatement();){
			ResultSet rs = stmt.executeQuery(sql);
			ResultSetMetaData rsmd = rs.getMetaData();
			int cols = rsmd.getColumnCount();
			
			for(int i = 1; i<=cols; i++) {				
				col.add(rsmd.getColumnName(i));
				col2.add(rsmd.getColumnType(i));
			}
			
			result.add(col);
			
			while(rs.next()) {
				Vector<String> v = new Vector<>();
				for(int i = 1; i <= cols; i++) {
					if(col2.get(i-1).equals("INTEGER")) {
						v.add(String.valueOf(rs.getInt(i)));											
					}
					else {
						v.add(rs.getString(i));
					}
				}
				result.add(v);
			}

		}
		catch(Exception e) {
			LOGGER.error(e.getMessage());
		}
				
		return result;
	}
	
	public int changeTable(String sql) {
		int result = 0;
		
		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWD);
				Statement stmt = conn.createStatement();) {
			result = stmt.executeUpdate(sql);			
		}		
		catch(Exception e) {
			LOGGER.error(e.getMessage());
		}
		
		return result;
	}

}
