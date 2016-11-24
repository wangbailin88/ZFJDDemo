package com.ushine.common.db;


/**
 * 用于创建数据库连接管理器
 * @author Franklin
 *
 */
public class DBConnMgrFactory {

	/**
	 * 获取数据库连接管理器
	 * @return IDBConnMgr
	 */
	public static IDBConnMgr getInstance() {
		
		return DBConnMgrImpl.getInstance();
	}
	
}
