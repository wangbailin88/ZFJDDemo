package com.ushine.common.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.ushine.core.po.Department;
import com.ushine.core.po.Menu;
import com.ushine.core.po.Module;
import com.ushine.core.po.Operation;
import com.ushine.core.po.Organiz;
import com.ushine.core.po.Permit;
import com.ushine.core.po.Resource;
import com.ushine.core.po.Role;

public class InitUtils {
	/**
	 * @param args
	 */
	public static List<Operation> getOperations(){
		// TODO Auto-generated method stub
		//1.创建DOM解析器工厂实例
		DocumentBuilderFactory dbf= DocumentBuilderFactory.newInstance();
		List<Operation> operations=new ArrayList<Operation>();
		try {
			//2.从DOM工厂中获得解析器对象
			DocumentBuilder db =dbf.newDocumentBuilder();
			//3.得到dom对象
			Document doc=db.parse(new File("WebRoot/WEB-INF/config/app-init-config.xml"));
			//Element root = doc.getDocumentElement();
			NodeList operationList=doc.getElementsByTagName("operation");
			for(int i=0;i<operationList.getLength();i++){
				Node operation=operationList.item(i);
				Element element=(Element) operation;
				Operation o=new Operation();
				o.setName(element.getAttribute("name"));
				o.setType(Integer.parseInt(element.getAttribute("type")));
				o.setCode(element.getAttribute("code"));
				operations.add(o);
			}
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return operations;
	}
	public static List<Module> getModules(){
		DocumentBuilderFactory dbf= DocumentBuilderFactory.newInstance();
		//2.从DOM工厂中获得解析器对象
		List<Module> modules=new ArrayList<Module>();
		try {
			DocumentBuilder db = dbf.newDocumentBuilder();
			//3.得到dom对象
			Document doc=db.parse(new File("WebRoot/WEB-INF/config/app-init-config.xml"));
			Element root = doc.getDocumentElement();
			NodeList modulesList=root.getElementsByTagName("module");
			for(int i=0;i<modulesList.getLength();i++){
				Node module=modulesList.item(i);
				Element element=(Element) module;
				Module m=new Module();
				m.setName(element.getAttribute("name"));
				List<Menu> menus=new ArrayList<Menu>();
				for(Node node=module.getFirstChild();node !=null;node=node.getNextSibling()){
					//是否是元素节点。
					if(node.getNodeType()==Node.ELEMENT_NODE){
						Menu menu=new Menu();
						Element Element1=(Element)node;
						menu.setName(Element1.getAttribute("name"));
						menu.setClassName(Element1.getAttribute("class"));
						menu.setIcon(Element1.getAttribute("icon"));
						menu.setUrl(Element1.getAttribute("url"));
						menu.setModule(m);
						List<Resource> resources=new ArrayList<Resource>();
						for(Node node1=Element1.getFirstChild();node1 !=null;node1=node1.getNextSibling()){
							if(node1.getNodeType()==Node.ELEMENT_NODE){
								Resource r=new Resource();
								Element Element2=(Element)node1;
								r.setType(Integer.parseInt(Element2.getAttribute("type")));
								r.setIcon(Element2.getAttribute("icon"));
								r.setInteceptor(Element2.getAttribute("inteceptor"));
								r.setLink(Element2.getAttribute("link"));
								r.setName(Element2.getAttribute("name"));
								r.setClassName(Element2.getAttribute("class"));
								r.setCode(Element2.getAttribute("code"));
								r.setMenu(menu);
								resources.add(r);
							}
						}
						menu.setResources(resources);
						menus.add(menu);
					}
					
				}
				m.setMenus(menus);
				modules.add(m);
			}
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return modules;
	}
	public static List<Organiz> getOrganiz(){
		DocumentBuilderFactory dbf= DocumentBuilderFactory.newInstance();
		//2.从DOM工厂中获得解析器对象
		List<Organiz> modules=new ArrayList<Organiz>();
		try {
			DocumentBuilder db = dbf.newDocumentBuilder();
			//3.得到dom对象
			Document doc=db.parse(new File("WebRoot/WEB-INF/config/app-init-config.xml"));
			Element root = doc.getDocumentElement();
			NodeList modulesList=root.getElementsByTagName("org");
			for(int i=0;i<modulesList.getLength();i++){
				Node module=modulesList.item(i);
				Element element=(Element) module;
				Organiz o=new Organiz();
				o.setName(element.getAttribute("name"));
				List<Department> depts=new ArrayList<Department>();
				for(Node node=module.getFirstChild();node !=null;node=node.getNextSibling()){
					//是否是元素节点。
					if(node.getNodeType()==Node.ELEMENT_NODE){
						Department d=new Department();
						d.setOrganiz(o);
						Element Element1=(Element)node;
						d.setName(Element1.getAttribute("name"));
						depts.add(d);
					}
				}
				o.setDepts(depts);
				modules.add(o);
			}
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return modules;
	}
	public static List<Role> getRoles(){
		DocumentBuilderFactory dbf= DocumentBuilderFactory.newInstance();
		//2.从DOM工厂中获得解析器对象
		List<Role> roles=new ArrayList<Role>();
		try {
			DocumentBuilder db = dbf.newDocumentBuilder();
			//3.得到dom对象
			Document doc=db.parse(new File("WebRoot/WEB-INF/config/app-init-config.xml"));
			Element root = doc.getDocumentElement();
			NodeList modulesList=root.getElementsByTagName("role");
			for(int i=0;i<modulesList.getLength();i++){
				Node module=modulesList.item(i);
				Element element=(Element) module;
				Role role=new Role();
				role.setName(element.getAttribute("name"));
				List<Permit> permits=new ArrayList<Permit>();
				for(Node node=module.getFirstChild();node !=null;node=node.getNextSibling()){
					//是否是元素节点。
					if(node.getNodeType()==Node.ELEMENT_NODE){
						Permit p=new Permit();
						Element Element1=(Element)node;
						Resource r=new Resource();
						r.setCode(Element1.getAttribute("resource"));
						Operation o=new Operation();
						o.setCode(Element1.getAttribute("operation"));
						p.setOperation(o);
						p.setResource(r);
						permits.add(p);
					}
					role.setPermits(permits);
				}
				roles.add(role);
			}
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return roles;

	}
}
