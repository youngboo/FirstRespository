package com.itheima.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Document;
import org.dom4j.Element;

import com.itheima.util.Dom4JUtil;

public class ShowResultServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		response.addHeader("Progam", "no-cache");
		response.addHeader("Expires", "-1");
		response.addHeader("Cache-Control", "no-cache");

		Document document = Dom4JUtil.getDocument();
		List<?> elements = document.getRootElement().elements("grade");
		if (elements.size() == 0) {
			response.getWriter().write("没有人打分");
		} else {
			StringBuilder sb = new StringBuilder();
			int count = 0;
			int greatC = 0;
			int goodC = 0;
			for (Object object : elements) {
				Element gradeObj = (Element) object;
				String text = gradeObj.getText();
				if (text != null && !"".equals(text)) {
					if (text.contains("优")) {
						// text = text.replace("great", "优");
						greatC++;
						count += 20;
					} else if (text.contains("良")) {
						// text = text.replace("good", "良");
						goodC++;
						count += 10;
					}
				}
				sb.append(text + "<br/> ");

			}
			/**
			 * <script> function getImg(){ var imgObj =
			 * document.getElementById('autoImg'); imgObj.src
			 * =request.getContextPath
			 * ()+'/servlet/AutoImgServlet?'+System.currentTimeMillis() }
			 * function getImgText(){ var textObj =
			 * document.getElementById('autoImgInput');
			 * 
			 * textObj.value } function isValue(){ var textObj =
			 * document.getElementById('autoImgInput'); alert(textObj.value);
			 * if('"+attribute+"'==textObj.value){ alert('验证码正确');}
			 * else{alert('验证码错误');} } </script>
			 */

			String src = request.getContextPath() + "/servlet/AutoImgServlet?"
					+ System.currentTimeMillis();
			PrintWriter out = response.getWriter();
			out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
			out.println("<HTML>");
			out.println("  <HEAD><script>	function getImg(){ 	var imgObj = document.getElementById('autoImg'); imgObj.src ='"
					+ request.getContextPath()
					+ "/servlet/AutoImgServlet?"
					+ System.currentTimeMillis()
					+ "' }function isValue(){ var textObj = document.getElementById('autoImgInput');if(==textObj.value){	alert('验证码正确');}}</script><TITLE>show</TITLE></HEAD>");
			out.println("  <BODY>");
			out.write(sb.toString() + "优的个数:" + greatC + "&nbsp;&nbsp;良的个数:"
					+ goodC);
			out.write("<br/><a href='" + request.getContextPath()
					+ "/servlet/DownLoadServlet'>下载本次评分数据</a>");
			out.write("<br/><input type='text' id='autoImgInput' onchange='isValue()'><img id='autoImg' src="
					+ src
					+ " onclick='getImg()'/><a href='javascript:getImg()'>看不清？</a>");
			out.println("  </BODY>");
			out.println("</HTML>");
			out.flush();
			out.close();
		}

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);

	}

}
