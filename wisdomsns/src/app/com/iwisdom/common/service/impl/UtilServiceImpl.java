package com.iwisdom.common.service.impl;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.iwisdom.common.dao.MenuDao;
import com.iwisdom.common.dao.UtilDao;
import com.iwisdom.common.entity.Menu;
import com.iwisdom.common.service.UtilService;
import com.iwisdom.common.util.SystemUtil;
public class UtilServiceImpl implements UtilService{
	private UtilDao utilDao;
	private MenuDao menuDao ;
	public MenuDao getMenuDao() {
		return menuDao;
	}

	public void setMenuDao(MenuDao menuDao) {
		this.menuDao = menuDao;
	}

	public UtilDao getUtilDao() {
		return utilDao;
	}

	public void setUtilDao(UtilDao utilDao) {
		this.utilDao = utilDao;
	}

	public List<Map> getCountries() {
		return utilDao.getCountries();
	}

	public List<Map> getCountryProvinces(String countryId) {
		return utilDao.getCountryProvinces(countryId);
	}

	public List<Map> getProvinceCities(String provincePrefix) {
		
		return utilDao.getProvinceCities(provincePrefix);
	}

	public List<Map> getProvinces() {
		return utilDao.getProvinces();
	}

	public List<Map> getCityDistricts(String cityId) {
		
		return utilDao.getCityDistricts(cityId);
	}

	public void initMenuXMLData() {
		StringBuffer rootPath = SystemUtil.getSystemRootPath(UtilServiceImpl.class);
		rootPath.append("/data/menu/menu.xml");
		File menuFile = new File(rootPath.toString());
		if(!menuFile.exists()){
			try {
				if(menuFile.createNewFile())
					createXML(menuFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void callWriteXmlFile(Document doc, Writer w, String encoding) {
		try {
			Source source = new DOMSource(doc);

			Result result = new StreamResult(w);

			Transformer xformer = TransformerFactory.newInstance()
					.newTransformer();
			xformer.setOutputProperty(OutputKeys.ENCODING, encoding);
			xformer.transform(source, result);

		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		}
	}
	public void createXML(File file) {
		DocumentBuilderFactory builderFactory = DocumentBuilderFactory
				.newInstance();
		DocumentBuilder builder = null;
		try {
			builder = builderFactory.newDocumentBuilder();

		Document doc = builder.newDocument();
		Element root = doc.createElement("tree");
		root.setAttribute("id", "0");
		doc.appendChild(root);
		createChildren(doc,root,0);
		FileOutputStream fos = new FileOutputStream(file);
		OutputStreamWriter outwriter = new OutputStreamWriter(fos);
		callWriteXmlFile(doc, outwriter, "GBK");
		outwriter.close();
		fos.close();
		
		} catch (Exception e) {
			e.printStackTrace();

		}
		
	}
	public void createRoleMenus(java.util.Set<Menu> menus,int roleId){
		StringBuffer rootPath = SystemUtil.getSystemRootPath(UtilServiceImpl.class);
		rootPath.append("/data/menu/menu_"+roleId+".xml");
		File menuFile = new File(rootPath.toString());
		
		try {
		DocumentBuilderFactory builderFactory = DocumentBuilderFactory
		.newInstance();
		DocumentBuilder builder = null;
		
			builder = builderFactory.newDocumentBuilder();
		
		Document doc = builder.newDocument();
		Element root = doc.createElement("tree");
		root.setAttribute("id", "0");
		doc.appendChild(root);
		java.util.Iterator<Menu> iterator = menus.iterator();
		Map<Integer,Element> map = new java.util.HashMap<Integer,Element>();
		while(iterator.hasNext()){
			Menu menu = (Menu)iterator.next();
			/**
			Menu m = (Menu)iterator.next();
			int pid = m.getPid();
			System.out.println("id = "+m.getMenuId()+",pid = "+m.getPid());
			if(pid!=0){
			if(!map.containsKey(new Integer(pid))){
			Menu pm = menuDao.getMenuById(pid);
			Element pnode = doc.createElement("item");
			pnode.setAttribute("id", String.valueOf(pid));
			pnode.setAttribute("pid", "0");
			pnode.setAttribute("name", pm.getMenuName());
			root.appendChild(pnode);
			
			Element node = doc.createElement("item");
			node.setAttribute("id", String.valueOf(m.getMenuId()));
			node.setAttribute("pid", String.valueOf(pid));
			node.setAttribute("name", m.getMenuName());
			node.setAttribute("url", m.getMenuUrl());
			pnode.appendChild(node);
			
			map.put(new Integer(pid), pnode);
			}else{
				Element node = doc.createElement("item");
				node.setAttribute("id", String.valueOf(m.getMenuId()));
				node.setAttribute("pid", String.valueOf(pid));
				node.setAttribute("name", m.getMenuName());
				node.setAttribute("url", m.getMenuUrl());
				Element pnode = (Element)map.get(new Integer(pid));
				pnode.appendChild(node);
			}
		}else{
			if(!map.containsKey(new Integer(m.getMenuId()))){
			Element node = doc.createElement("item");
			node.setAttribute("id", String.valueOf(m.getMenuId()));
			node.setAttribute("pid", "0");
			node.setAttribute("name", m.getMenuName());
			root.appendChild(node);
			map.put(new Integer(m.getMenuId()), node);
			}
		}
		*/
			map = createNode(map,menu,doc,root);
		}
		FileOutputStream fos = new FileOutputStream(menuFile);
		OutputStreamWriter outwriter = new OutputStreamWriter(fos);
		callWriteXmlFile(doc, outwriter, "GBK");
		outwriter.close();
		fos.close();
		
		} catch (Exception e) {
			e.printStackTrace();
		
		}
		
		
	}
	public Map<Integer,Element> createNode(Map<Integer,Element> map,Menu menu,Document doc,Element root){
		int pid = menu.getPid();
		System.out.println("pid = "+pid +",id = "+menu.getMenuId()+",map.size = "+map.size()+",map.value = "+map.get(new Integer(menu.getMenuId())));
		if(pid==0){
			if(!map.containsKey(new Integer(menu.getMenuId()))){
			Element node = doc.createElement("item");
			node.setAttribute("id", String.valueOf(menu.getMenuId()));
			node.setAttribute("pid", "0");
			node.setAttribute("name", menu.getMenuName());
			root.appendChild(node);
			map.put(new Integer(menu.getMenuId()), node);
			}
		}
		if(!map.containsKey(new Integer(menu.getMenuId()))){
				map = createNode(map,menuDao.getMenuById(pid),doc,root);
			}
		if(!map.containsKey(new Integer(menu.getMenuId()))){
				Element node = doc.createElement("item");
				node.setAttribute("id", String.valueOf(menu.getMenuId()));
				node.setAttribute("pid", String.valueOf(pid));
				node.setAttribute("name", menu.getMenuName());
				node.setAttribute("url", menu.getMenuUrl());
				Element pnode = (Element)map.get(new Integer(pid));
				pnode.appendChild(node);
				map.put(new Integer(menu.getMenuId()), node);
		}
		return map;
		
	}
	public  void createChildren(Document doc,Element element,int parentId){

			List<Menu> menus = menuDao.getChildrenMenus(parentId);
			for(int i=0 ;i<menus.size();i++) {
				Menu m = (Menu)menus.get(i);
				Element node = doc.createElement("item");
				try {
					node.setAttribute("name",m.getMenuName());
					System.out.println(new String( m.getMenuName()));
				} catch (DOMException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
				node.setAttribute("id", String.valueOf(m.getMenuId()));
				createChildren(doc,node,m.getMenuId());
				
				element.appendChild(node);
			}
			

	}
	
}
