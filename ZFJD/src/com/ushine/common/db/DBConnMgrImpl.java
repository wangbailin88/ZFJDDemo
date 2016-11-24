package com.ushine.common.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ushine.common.config.Configured;
import com.ushine.common.config.Constant;

/**
 * 数据库连接管理器实现
 * 
 * @author Franklin
 *
 */
public class DBConnMgrImpl implements IDBConnMgr {
	private static final Logger logger = LoggerFactory.getLogger(DBConnMgrImpl.class);
	
	private Configured conf = null;
	
	private static IDBConnMgr connMgr = null;
	
	private String driver = null; // 数据库驱动
	
	private String url = null; // 数据库地址
	
	private String userName = null; // 数据库用户名
	
	private String password = null; // 数据库密码
	
	private DBConnMgrImpl() {
		try {
			conf = Configured.getInstance();
			driver = conf.get(Constant.DB_DRIVER);
			url = conf.get(Constant.DB_URL);
			userName = conf.get(Constant.DB_UNAME);
			password = conf.get(Constant.DB_PASSD);
		} catch (Exception e) {
			logger.error("获取DBconnMgr失败，异常停止应用程序.", e);
			System.exit(-1);
		}
	}
	
	public Connection getConnection() throws Exception {
		Connection conn = null;
		try {
			Class.forName(driver);
			return DriverManager.getConnection(url, userName, password);
		} catch(Exception e) {
			throw new Exception("获取数据库连接异常.", e);
		} finally {
			closeConn(conn);
		}
	}
	
	public void closeConn(Connection conn) {
		try {
			if(conn != null) {
				conn.close();
				conn = null;
			}
		} catch(SQLException e) {
			logger.error("关闭数据库连接(Connection)错误.", e);
		}
	}
	
	public void closeConn(Statement statement) {
		try {
			if(statement != null) {
				statement.close();
				statement = null;
			}
		} catch(SQLException e) {
			logger.error("关闭数据库连接(Statement)错误.", e);
		}
	}
	
	public void closeConn(PreparedStatement ps) {
		try {
			if(ps != null) {
				ps.close();
				ps = null;
			}
		} catch(SQLException e) {
			logger.error("关闭数据库连接(PreparedStatement)错误.", e);
		}
	}
	
	public void closeConn(ResultSet rs) {
		try {
			if(rs != null) {
				rs.close();
				rs = null;
			}
		} catch(SQLException e) {
			logger.error("关闭数据库连接(ResultSet)错误.", e);
		}
	}
	
	public void closeConn(Connection conn, PreparedStatement ps, ResultSet rs) {
		closeConn(rs);
		closeConn(ps);
		closeConn(conn);
	}

	public void closeConn(Connection conn, Statement ps, ResultSet rs) {
		closeConn(rs);
		closeConn(ps);
		closeConn(conn);
	}
	
	/**
	 * 获取JDBC连接管理器
	 * @return IDBConnMgr
	 */
	public static IDBConnMgr getInstance() {
		if(connMgr == null) { 
			connMgr = new DBConnMgrImpl();
		}
		return connMgr;
	}
	
}
 