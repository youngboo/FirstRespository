package com.itheima.test;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.tree.ElementIterator;
import org.junit.Test;

import com.itheima.util.Dom4JUtil;

public class saveGrade {
	Document document = Dom4JUtil.getDocument();

	@Test
	public void test1() {
		Element createElement = DocumentHelper.createElement("grade");
		createElement.setText("dsf");
		document.getRootElement().add(createElement);
		Dom4JUtil.writeDocument2Xml(document);

	}

	@Test
	public void test2() {
		Document document = Dom4JUtil.getDocument();
		Element rootElement = document.getRootElement();
		Iterator elementIterator = rootElement.elementIterator();
		while (elementIterator.hasNext()) {
			Object next = elementIterator.next();
			if (next instanceof Element) {
				rootElement.remove((Element) next);
			}

		}
		Dom4JUtil.writeDocument2Xml(document);

	}
	@Test
	public void test3() throws Exception {
//		URLEncoder encoder = URLEncoder.encode(s, enc);
		String s = "%e6%80%8e%e6%a0%b7%e8%a7%a3%e9%a2%98%ef%bc%9a%e6%95%b0%e5%ad%a6%e6%80%9d%e7%bb%b4%e7%9a%84%e6%96%b0%e6%96%b9%e6%b3%95";
		String decode = URLDecoder.decode(s, "UTF-8");
		
		System.out.println(decode);
	}
	@Test
	public void test4() throws Exception {
		String string = Dom4JUtil.getString();
		System.out.println(string);
	}
	
}
