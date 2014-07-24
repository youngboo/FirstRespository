package com.itheima.test;

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
}
