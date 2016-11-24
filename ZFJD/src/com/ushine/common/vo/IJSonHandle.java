package com.ushine.common.vo;

import java.util.List;

/**
 * 定义JSON处理接口，主要用于将对象转换成JSON字符串
 * 该接口根据不同的服务接口实现不同的转换方式
 * @author franklin
 *
 */
public interface IJSonHandle<T> {

	/**
	 * 将List转换成JSon
	 * @param list List<T>
	 * @return String
	 */
	public String listToJSon(List<T> list);
	
	/**
	 * 将包含分页信息的视图对象转换成JSon
	 * @param vo ViewPagingObject
	 * @return String
	 */
	public String voToJSon(PagingObject<T> vo);
	
	/**
	 * 生成ExtJS树形JSon
	 * @return String
	 */
	public String toExtTreeJSon(List<T> list);
	
	/**
	 * 生成ExtJS ComboBox JSon
	 * @return String
	 */
	public String toExtComboJSon(List<T> list);
	
}
