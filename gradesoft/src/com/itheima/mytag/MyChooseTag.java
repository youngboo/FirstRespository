package com.itheima.mytag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class MyChooseTag extends SimpleTagSupport {
	private boolean flag = true;
	
	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	@Override
	public void doTag() throws JspException, IOException {
	}
	
}
