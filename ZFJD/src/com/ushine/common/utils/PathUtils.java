package com.ushine.common.utils;

public class PathUtils {

	/**
	 * 获取当前文件路径
	 * @param Class aClass 类
	 * @return String WebRoot/WEB-INF/classes/
	 */
	@SuppressWarnings("rawtypes")
	public static String getWebClassesPath(Class aClass) {
		String path = aClass.getProtectionDomain().
				getCodeSource().getLocation().getPath();
		return path;
	}
	
	/**
	 * 获取当前工程的WEB-INF路径
	 * @param Class aClass 类
	 * @return String WebRoot/WEB-INF/
	 * @throws IllegalAccessException
	 */
	@SuppressWarnings("rawtypes")
	public static String getWebInfPath(Class aClass) {
		String path = getWebClassesPath(aClass);
		int begin = path.indexOf("/classes/");
		return path.substring(0, begin+1);
	}
	
	/**
	 * 获取当前工程的WEB-INF/config路径
	 * @param Class aClass 类
	 * @return String WebRoot/WEB-INF/config/
	 * @throws IllegalAccessException
	 */
	@SuppressWarnings("rawtypes")
	public static String getConfigPath(Class aClass) {
		String path = getWebClassesPath(aClass);
		int begin = path.indexOf("/classes/");
		return path.substring(0, begin+1) + "config/";
	}
	
	
	/**
	 * 获取当前工程路径
	 * @param Class aClass 类
	 * @return String WebRoot/
	 * @throws IllegalAccessException
	 */
	@SuppressWarnings("rawtypes")
	public static String getWebRoot(Class aClass) {
		String path = getWebClassesPath(aClass);
		int begin = path.indexOf("/WEB-INF/classes/");
		return path.substring(0, begin+1);
	}
	
	public static void main(String[] args) {
		System.out.println(getWebClassesPath(PathUtils.class));
		System.out.println(getConfigPath(PathUtils.class));
		System.out.println(getWebRoot(PathUtils.class));
		System.out.println(getWebInfPath(PathUtils.class));
	}
	
}