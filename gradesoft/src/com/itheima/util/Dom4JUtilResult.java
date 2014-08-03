package com.itheima.util;

import java.io.FileOutputStream;
import java.net.URL;
import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

/**
 * 用于存储xml
 * @author yannnn
 *
 */
public class Dom4JUtilResult {
//	static String path = "C:\\apache-tomcat-6.0.37\\webapps\\gradesoft\\WEB-INF\\grades.xml";
	private static String path;
	static{
		ClassLoader cl = Dom4JUtilResult.class.getClassLoader();
		URL resource = cl.getResource("results.xml");
		 path = resource.getPath();
	}
	

	public static Document getDocument() {
			
				SAXReader reader = new SAXReader();
		Document document = null;
		try {
			document = reader.read(path);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return document;
	}

	public static void writeDocument2Xml(Document document) {
		OutputFormat format = OutputFormat.createPrettyPrint();
		XMLWriter writer;
		try {
			writer = new XMLWriter(new FileOutputStream(path), format);
			writer.write(document);
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void deleteXml(Document document) {
		Element rootElement = document.getRootElement();
		Iterator<?> elementIterator = rootElement.elementIterator();
		while (elementIterator.hasNext()) {
			Object next = elementIterator.next();
			if (next instanceof Element) {
				rootElement.remove((Element) next);
			}

		}
		Dom4JUtilResult.writeDocument2Xml(document);

	}
}
