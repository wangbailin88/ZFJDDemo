package com.ushine.common.utils;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 文件工具类
 * @author Franklin
 *
 */
public class FileUtils {
	private static final Logger logger = 
			LoggerFactory.getLogger(FileUtils.class);
	
	public static void createDir(String dir) throws Exception {
		File fileDir = new File(dir);
		// 检查文件夹是否存在
		if(!fileDir.isDirectory()) {
			fileDir.mkdir();
		} else {
			logger.warn("文件夹已经存在, 创建操作失败: " + dir);
		}
	}
	
	/**
	 * 将文件移动到指定的文件夹，如果目标文件夹
	 * 不存在，自动创建
	 * @param dir String 目标文件夹
	 * @param files File[] 需要移动的文件
	 * @return File[] 移动后的文件
	 */
	public static File[] renameToDir(String dir, File[] files) throws Exception {
		File[] newFiles = new File[files.length];
		File fileDir = new File(dir);
		// 检查文件夹是否存在
		if(!fileDir.isDirectory()) {
			fileDir.mkdir();
		}
		// 循环移动文件
		for(int i=0; i<files.length; i++) {
			File file = new File(fileDir + "/" + files[i].getName());
			files[i].renameTo(file);
			newFiles[i] = file;
		}
		return newFiles;
	}
	
	/**
	 * 删除指定文件
	 * @param files File[] 需要删除的文件
	 */
	public static void deleteFiles(File[] files) throws Exception {
		for(File file : files) {
			if(file.exists()){
				file.delete();
			} else {
				logger.warn("没有找到指定文件, 删除操作失败: " + file.getPath());
			}
		}
	}
	
	/**
	 * 删除指定的文件夹
	 * @param dir
	 */
	public static void deleteDir(String dir) throws Exception {
		File tDir = new File(dir);
		if (!tDir.isDirectory()) {
			logger.warn("没有找到指定文件夹, 删除操作失败: " + tDir.getPath());
			return;
	    }
		File[] tmpFiles = tDir.listFiles();
		for(File file : tmpFiles) {
			if(file.isDirectory()) {
				deleteDir(file.getPath());
			}
			file.delete();
		}
		tDir.delete();
	}
	
}
