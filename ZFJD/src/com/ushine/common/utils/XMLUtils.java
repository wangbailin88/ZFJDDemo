package com.ushine.common.utils;


import java.io.File;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



/**
 * XML文档工具类
 * @author Franklin
 *
 */
public class XMLUtils {
	private static final Logger logger = LoggerFactory.getLogger(XMLUtils.class);
	
	private Document doc = null;
	
	private Element root = null;
	
	/**
	 * 加载XML要操作的文件
	 * @param fileName String XML文件名
	 */
	public XMLUtils(String fileName) throws Exception {
		try {
        	SAXReader reader = new SAXReader();
        	doc = reader.read(new File(fileName));
        	root = doc.getRootElement();
        	logger.debug("成功加载XML文件: " + fileName + ".");
        } catch (Exception e) {
        	throw new Exception("加载XML文件失败:" + fileName + ",关闭应用程序.", e);
        }
	}
	
	/**
	 * 取得根节点下的单个子节点
	 * @param nodeName String 节点名称
	 * @return Element 节点
	 */
	public Element getNode(String nodeName) {
		return getNode(null, nodeName);
	}
	
	/**
	 * 取得某节点的单个子节点
	 * @param parent Element 父节点，为空查询根节点下的子节点
	 * @param nodeName String 节点名称
	 * @return Element 节点
	 */
	public Element getNode(Element parent, String nodeName) {
		if(parent==null) {
			return root.element(nodeName);
		} else {
			return parent.element(nodeName);
		}
	}
	
	/**
	 * 取得根节点下指定的子节点集合
	 * @param nodeName String 节点名称
	 * @return List<Element> 节点集合
	 */
	public List<Element> getNodes(String nodeName) {
		return getNodes(null, nodeName);
	}
	
	/**
	 * 取得某节点的子节点集合
	 * @param parent Element 父节点，为空查询根节点下的子节点
	 * @param nodeName String 节点名称
	 * @return List<Element> 节点集合
	 */
	@SuppressWarnings("unchecked")
	public List<Element> getNodes(Element parent, String nodeName) {
		if(parent==null) {
			parent = root;
		}
		return (List<Element>)parent.elements(nodeName);
	}
	
	/**
	 * 获取指定指定的值
	 * @param element Element 节点
	 * @return String 节点值
	 */
	public String getNodeVal(Element element) {
		return element.getText();
	}
	
	/**
	 * 获取指定节点的属性值
	 * @param element Element 节点
	 * @param attrName String 属性名称 
	 * @return String 属性值
	 */
	public String getNodeAttrVal(Element element, String attrName) {
		return element.attributeValue(attrName);
	}
	
}
