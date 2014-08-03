package com.itheima.servlet;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.dom4j.Document;

import com.itheima.cache.XmlCache;
import com.itheima.util.Dom4JUtil;

/**
 * 用于ajax请求的返回信息
 * 
 * @author yannnn
 * 
 */
@SuppressWarnings("serial")
public class ReturnAjaxInfo extends HttpServlet {
	XmlCache xmlCache = XmlCache.getXmlCache();

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ServletContext context = getServletContext();

		String resultPage = "ajaxType";
		String parameter = request.getParameter(resultPage);
		if (parameter != null && !"".equals(parameter)) {
			Document document = Dom4JUtil.getDocument();
			Integer xmlLength = Dom4JUtil.getXmlLength(document);

			// 从session中取标记,第一次访问除了xml文件长度为0，都要返回信息
			String resultPara = request.getParameter("isFirst");
			HttpSession session = request.getSession();
			if (session.getAttribute("isFirst") != null&&session.getAttribute("isFirst").equals(resultPara)) {
				System.out.println("从客户端获取到的token"+resultPara);
					if (xmlLength == 0) {
						System.out.println("第一次请求，服务端没有东西");
						response.setContentType("text/html;charset=UTF-8");
						response.getOutputStream().write("没有任何人评分".getBytes("UTF-8"));
						return;
					}else{
						System.out.println("第一次请求，服务端有东西");
						byte[] byteArray = xmlCache.getByteArray();
						System.out.println(parameter);
						if (byteArray != null) {
							response.setContentType("text/xml;charset=UTF-8");
							response.getOutputStream().write(byteArray);
							context.setAttribute("xmlsize", xmlLength);
							session.removeAttribute("isFirst");
					}
						
					return;
				}
			}
			Integer xmlsize = 0;
			if (context.getAttribute("xmlsize") != null) {
				xmlsize = (Integer) context.getAttribute("xmlsize");
			}
			if (xmlLength == 0) {
				System.out.println("非一次请求，服务端没值");
				response.setContentType("text/html;charset=UTF-8");
				response.getOutputStream().write("没有任何人评分".getBytes("UTF-8"));
			} else if (xmlLength != xmlsize && xmlLength > 0) {
				System.out.println("非一次请求，服务端有");
				byte[] byteArray = xmlCache.getByteArray();
				System.out.println(parameter);
				if (byteArray != null) {
					response.setContentType("text/xml;charset=UTF-8");
					response.getOutputStream().write(byteArray);
					context.setAttribute("xmlsize", xmlLength);

				}
			} else if (xmlLength == xmlsize) {
				response.setStatus(304);
			}

		}
		/*
		 * Element rootElement = document.getRootElement(); List elements =
		 * rootElement.elements("grade"); if
		 * (context.getAttribute("xmlsize")!=null) { //获取域中储存的 int xmlsize =
		 * (Integer) context.getAttribute("xmlsize");
		 * //如果xml文档变化了，则开始读取内存中的xml文件 if (elements.size()>xmlsize) {
		 * 
		 * context.setAttribute("xmlsize", elements.size()); } }
		 * 
		 * String path = getServletContext().getRealPath("/grades.xml");
		 * FileInputStream in = new FileInputStream(path); OutputStream out =
		 * response.getOutputStream(); int len = 0; byte[] b = new byte[1024];
		 * while((len = in.read(b))!=-1){ out.write(b, 0, len);
		 * System.out.println(new String(b, 0, len)); out.flush(); } in.close();
		 * 
		 * // 判断文档中是否有人评分，如果有，开始读取
		 * 
		 * Document document = Dom4JUtil.getDocument();
		 * document.getRootElement(); Element rootElement =
		 * document.getRootElement(); List elements =
		 * rootElement.elements("grade"); if (elements!=null&&elements.size() >
		 * 0) {
		 * 
		 * }
		 */
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);

	}

	public String getServletInfo() {
		return "This is my default servlet created by Eclipse";
	}

}
