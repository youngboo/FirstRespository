package com.itheima.mytag;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class MyForEachTag extends SimpleTagSupport{
	private List<?> items; 
	private String var;
	
	
	public void setItems(List<?> items) {
		this.items = items;
	}


	public void setVar(String var) {
		this.var = var;
	}


	@Override
	public void doTag() throws JspException, IOException {
		for (int i = 0; i < items.size(); i++) {
		getJspContext().setAttribute(var, items.get(i), PageContext.PAGE_SCOPE);
		getJspBody().invoke(null);
		}
	}
	
}
