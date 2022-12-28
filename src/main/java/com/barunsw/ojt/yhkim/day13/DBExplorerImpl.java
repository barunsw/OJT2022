package com.barunsw.ojt.yhkim.day13;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.barunsw.ojt.yhkim.day10.GroupBookInterface;
import com.barunsw.ojt.yhkim.day10.GroupBookVo;

public class DBExplorerImpl {
	private static Logger LOGGER = LoggerFactory.getLogger(DBExplorerImpl.class);
	
	private final static String URL = "jdbc:mysql://localhost:3306/OJT?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC&useSSL=false";
	private final static String USER = "root";
	private final static String PASSWD = "1234";

	public DBExplorerImpl() throws Exception {
		Class.forName("org.mariadb.jdbc.Driver");		
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
