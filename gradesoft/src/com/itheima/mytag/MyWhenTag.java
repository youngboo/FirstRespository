package com.itheima.mytag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class MyWhenTag extends SimpleTagSupport{
	private boolean test;
	@Override
	public void doTag() throws JspException, IOException {
		if (test) {
			getJspBody().invoke(null);
			MyChooseTag parent = (MyChooseTag)getParent();
			parent.setFlag(!test);
			
		}	}
	
}

