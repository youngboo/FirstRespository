package com.itheima.test;

import java.net.URLDecoder;
import java.text.DateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.junit.Test;

import com.itheima.util.Dom4JUtil;
import com.itheima.util.Dom4JUtilResult;

public class saveGrade {
	@Test
	public void test4() throws Exception {
		int greatC = 0;
		int goodC = 0;
		Document document = Dom4JUtil.getDocument();
		Element rootElement = document.getRootElement();
		List elements = rootElement.elements("grade");
		if (elements.size() == 0) {
		} else {
			// int greatC = 0;
			// int goodC = 0;
			for (Object object : elements) {
				Element gradeObj = (Element) object;
				String text = gradeObj.getText();
				if (text != null && !"".equals(text)) {
					if (text.contains("优")) {
						greatC++;
					} else if (text.contains("良")) {
						goodC++;
					}
				}
			}
		}
		System.out.println(greatC+":"+goodC);
		Document documentResult = Dom4JUtilResult.getDocument();
		Element rootElement1 = documentResult.getRootElement();

		Element createElement = DocumentHelper.createElement("result");
		createElement.addAttribute("优", String.valueOf(greatC))
				.addAttribute("良", String.valueOf(goodC))
				.addAttribute("投票人数", String.valueOf(greatC + goodC));
		DateFormat format = DateFormat.getDateInstance();
		String format2 = format.format(new Date());
		createElement.setText(format2);
		rootElement1.add(createElement);
		Dom4JUtilResult.writeDocument2Xml(documentResult);
	}
	@Test
	public void test5(){
		Document documentResult = Dom4JUtilResult.getDocument();
		Element rootElement1 = documentResult.getRootElement();
		List elements = rootElement1.elements("result");
		if (elements.isEmpty()) {
			
		}else{
			for (Object object : elements) {
				Element ele = (Element)object;
				String format = DateFormat.getDateInstance().format(new Date());
				if (format.equals(ele.getText())) {
					System.out.println(ele.attributeValue("优"));
					ele.setAttributeValue("优", "222");
					System.out.println(ele.attributeValue("优"));
					ele.attributeValue("优", "333");
					System.out.println(ele.attributeValue("优"));
				}
			}
		}
		Dom4JUtilResult.writeDocument2Xml(documentResult);
	}
}
