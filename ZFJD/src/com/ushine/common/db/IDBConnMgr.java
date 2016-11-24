package com.ushine.common.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * 数据库连接管理器
 * 
 * @author Franklin
 *
 */
public interface IDBConnMgr {
	
	/**
	 * 获取数据库连接
	 * @return java.sql.Connection
	 */
	public Connection getConnection() throws Exception;
	
	/**
	 * 关闭数据库连接
	 * @param conn java.sql.Connection
	 */
	public void closeConn(Connection conn);
	
	/**
	 * 关闭数据库连接
	 * @param statement java.sql.Statement
	 */
	public void closeConn(Statement statement);
	
	/**
	 * 关闭数据库连接
	 * @param ps java.sql.PreparedStatement
	 */
	public void closeConn(PreparedStatement ps);
	
	/**
	 * 关闭数据库连接
	 * @param rs java.sql.ResultSet
	 */
	public void closeConn(ResultSet rs);
	
	/**
	 * 关闭数据库连接
	 * @param conn java.sql.Connection
	 * @param ps java.sql.Statement
	 * @param rs java.sql.ResultSet
	 */
	public void closeConn(Connection conn, Statement ps, ResultSet rs);
	
	/**
	 * 关闭数据库连接
	 * @param conn java.sql.Connection
	 * @param ps java.sql.PreparedStatement
	 * @param rs java.sql.ResultSet
	 */
	public void closeConn(Connection conn, PreparedStatement ps, ResultSet rs);
	
}
