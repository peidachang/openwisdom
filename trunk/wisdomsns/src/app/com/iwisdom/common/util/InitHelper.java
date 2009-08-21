package com.iwisdom.common.util;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.FileOutputStream;
import java.io.Writer;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

import com.iwisdom.common.entity.Menu;

public class InitHelper {

	public static void createXMLFile(java.util.List<Menu> menus) {
		DocumentBuilderFactory builderFactory = DocumentBuilderFactory
				.newInstance();
		DocumentBuilder builder = null;
		try {
			builder = builderFactory.newDocumentBuilder();

		} catch (Exception e) {
			e.printStackTrace();

		}

		Document doc = builder.newDocument();
		Element root = doc.createElement("tree");
		root.setAttribute("id", "-1");
		doc.appendChild(root);

		try {
			
			for(int i=0 ;i<menus.size();i++) {
				Menu m = (Menu)menus.get(i);
				Element node = doc.createElement("item");
				node.setAttribute("text", m.getMenuName());
				node.setAttribute("id", String.valueOf(m.getMenuId()));
				root.appendChild(node);



			}
			FileOutputStream fos = new FileOutputStream("e:/menu.xml");
			OutputStreamWriter outwriter = new OutputStreamWriter(fos);
			// ((XmlDocument)doc).write(outwriter); //出错！
			callWriteXmlFile(doc, outwriter, "utf-8");
			outwriter.close();
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void callWriteXmlFile(Document doc, Writer w, String encoding) {
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
}
