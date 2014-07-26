package com.itheima.mytag;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class MyForEachTagPlus extends SimpleTagSupport {
	private Object items;
	private String var;

	public void setItems(Object items) {
		this.items = items;
	}

	public void setVar(String var) {
		this.var = var;
	}

	@Override
	public void doTag() throws JspException, IOException {
		if (items instanceof Collection) {
			if (items instanceof Map) {
			
			}
		}
	}

}
