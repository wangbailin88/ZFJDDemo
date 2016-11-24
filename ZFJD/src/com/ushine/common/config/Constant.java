package com.ushine.common.config;

/**
 * 系统常量
 * @author franklin
 *
 */
public class Constant {
	
	/**
	 * 信息状态：正常：
	 */
	public static final int NORMAL = 1;
	
	/**
	 * 信息状态：停用
	 */
	public static final int STOP = 0;
	
	/**
	 * 信息状态：删除
	 */
	public static final int DELETE = -1;
	/**
	 * 子任务层级
	 */
	public static final String TASK_LEVEL="task.level"; 
	
	/**
	 * HDFS URL
	 */
	public static final String HDFS_URL = "fs.defaultFS";
	
	/**
	 * 数据库驱动
	 */
	public static final String DB_DRIVER = "db.driver";
	
	/**
	 * 数据库连接
	 */
	public static final String DB_URL = "db.url";
	
	/**
	 * 数据库用户名
	 */
	public static final String DB_UNAME = "db.uName";
	
	/**
	 * 数据库密码
	 */
	public static final String DB_PASSD = "db.passd";
	
	/**
	 * HBase Master 服务器
	 */
	public static final String HBASE_ROOT = "hbase.root";
	
	/**
	 * HBase Zookeeper 服务器
	 */
	public static final String ZK_QUORUM = "zookeeper.quorum";
	
	/**
	 * HBase 数据表名称
	 */
	public static final String HBASE_TABLE = "hbase.table";
	
	public static final String HBASE_CF = "INFO";
	
	/**
	 * HBase 创建表时预设Region数量
	 */
	public static final String HBASE_REGION_SIZE = "hbase.region.size";
	
	/**
	 * HBASE 是否关闭日志(HLog)
	 */
	public static final String HBASE_PUT_LOG = "hbase.put.isCloseHLog";

	/**
	 * HBASE 客户端缓存
	 */
	public static final String HBASE_CLIENT_BUFFER = "hbase.client.WriteBufferSize";
	
	/**
	 * HBASE Scan Caching
	 */
	public static final String HBASE_SCAN_CASH = "hbase.scan.cash";
	
	/**
	 * ES 集群名称
	 */
	public static final String ES_NAME = "es.cluster.name";
	
	/**
	 * ES 服务器集群
	 */
	public static final String ES_SERBER = "es.server";
	
	/**
	 * ES 数据库名称
	 */
	public static final String ES_SCHEMA = "es.schema";
	
	/**
	 * 嫌疑抽检同时运行任务数量
	 */
	public static final String BSEARCH_TASK_THREAD = "batchSearch.task.thread";

	/**
	 * 嫌疑抽检最大任务队列
	 */
	public static final String BSEARCH_TASK_MAX = "batchSearch.task.max";
	
	/**
	 * 嫌疑抽检名单同时处理数
	 */
	public static final String BSEARCH_HANDLE_THREAD = "batchSearch.handle.thread";

	/**
	 * 嫌疑抽检名单最大处理数量
	 */
	public static final String BSEARCH_HANDLE_MAX = "batchSearch.handle.max";
	
	/**
	 * 嫌疑抽检数据表名称
	 */
	public static final String BSEARCH_SCHEMA = "batchSearch.schema";
	
	/**
	 * 存放预警布控任务命中的名单索引
	 */
	public static final String WARN_CLASSIFY_INDEX = "warn.classify.index";
	
	/**
	 * 存放预警布控任务命中的数据索引
	 */
	public static final String WARN_DATAS_INDEX = "warn.datas.index";
	
	/**
	 * 存放预警布控任务命中的数据
	 */
	public static final String WARN_DATAS_TABLE = "warn.datas.table";
	
	/**
	 * 同时执行保存中间库的任务数量
	 */
	public static final String TMPTABLE_THREAD_SIZE = "tmptable.thread.size";

	/**
	 * 保存中间库的任务队列
	 */
	public static final String TMPTABLE_THREAD_MAX = "tmptable.thread.max";
	
	/**
	 * 保存中间库时批量提交数量
	 */
	public static final String TMPTABLE_COMMIT_SIZE = "tmptable.commit.size";
	
			/**
	 * 数据分析任务的执行线程数量
	 */
	public static final String ANALYSIS_THREAD_SIZE = "analysis.thread.size";

	/**
	 * 数据分析任务的等待线程数量
	 */
	public static final String ANALYSIS_THREAD_MAX = "analysis.thread.max";
	
	/**
	 * 创建索引时的批量提交数量
	 */
	public static final String ANALYSIS_INDEX_COMMIT = "analysis.index.commit";
	
	/**
	 * 数据导入应用程序Webservice地址
	 */
	public static final String E_IMPORT_URL = "eimport.url";
	
	/**
	 * 预警布控应用程序Webservice地址
	 */
	public static final String WARNING_URL = "warning.url";
	
	/**
	 * 导出数据webserverce地址
	 */
	public static final String EXPORT_URL = "export.url";
	
	/**
	 * 导出数据下载地址
	 */
	public static final String EXPORT_DOWN_URL ="export.down.url";
	
	/**
	 * 数据挖掘数量限制
	 */
	public static final String GRAPHIC_DATA_SIZE ="graphic.data.size";
}
