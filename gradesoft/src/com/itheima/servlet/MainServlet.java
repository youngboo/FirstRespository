package com.itheima.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.itheima.util.Dom4JUtil;

/**
 * 
 * @author Youngbo
 * 
 */
@SuppressWarnings("serial")
public class MainServlet extends HttpServlet {
	Document document = Dom4JUtil.getDocument();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		String remoteAddr = request.getRemoteAddr();
		ServletContext context = this.getServletContext();
		System.out.println(remoteAddr + "来访问了");

		String names = "";
		if (context.getAttribute("address") != null
				&& !"".equals(context.getAttribute("address"))) {
			names = (String) context.getAttribute("address");
		}

		String address = request.getRemoteAddr();
		if (!names.contains(address)) {
			//用于get方式提交的中文乱码处理
//			String grade = new String(request.getParameter("grade").getBytes("iso-8859-1"),"UTF-8");
			String grade = request.getParameter("grade");
			if (grade != null && !"".equals(grade)&&("优".equals(grade)||"良".equals(grade))) {
				Element root = document.getRootElement();
				Element gradeEle = DocumentHelper.createElement("grade");
				
//				String addressSub = address.substring(0, address.length()-1);
//				String addressRel = address.substring(0, address.length()-1)+"*";
				gradeEle.setText(address+" : "+grade);
				root.add(gradeEle);
				Dom4JUtil.writeDocument2Xml(document);
			}
			context.setAttribute("address", address + " " + names);
			request.setAttribute("message","提交成功");
			response.addHeader("refresh", "1;url="+request.getContextPath()+"/servlet/ShowResultServlet");
			RequestDispatcher requestDispatcher = request
					.getRequestDispatcher("/confirm.jsp");
			requestDispatcher.forward(request, response);
		} else {
			request.setAttribute("message", "你只有一次打分机会");
			RequestDispatcher requestDispatcher = request
					.getRequestDispatcher("/confirm.jsp");
			requestDispatcher.forward(request, response);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);

	}

	@Override
	public void destroy() {
		Dom4JUtil.deleteXml(document);
		super.destroy();
	}
	

}
