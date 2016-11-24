package com.ushine.common.utils;

import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExportDMUtils {
	private static final Logger logger = LoggerFactory.getLogger(ExportDMUtils.class);
	
	public static void exportDM() {
		try {
			logger.info("开始导出数据模型创建数据表.");
			Configuration cfg = HibernateUtils.getConfiguration();
			SchemaExport export = new SchemaExport(cfg);
			export.create(true, true);
			logger.info("数据表创建完成.");
		} catch(Exception e) {
			logger.error("数据表创建异常.", e);
		}
	}
	
	/**
	 * 程序员手动操作 
	 */
	public static void main(String[] args) {
		exportDM();
	}
	
}
