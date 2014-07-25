package com.itheima.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Document;
import org.dom4j.Element;

import com.itheima.util.Dom4JUtil;
/**
 * 主要用于评分结果的显示
 * @author yannnn
 *
 */
@SuppressWarnings("serial")
public class ShowResultServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		response.addHeader("Progam", "no-cache");
		response.addHeader("Expires", "-1");
		response.addHeader("Cache-Control", "no-cache");

		StringBuilder sb = new StringBuilder();
		Document document = Dom4JUtil.getDocument();
		List<?> elements = document.getRootElement().elements("grade");
		if (elements.size() == 0) {
			request.setAttribute("message", "没有人评分");
		} else {
			sb = new StringBuilder();
			int greatC = 0;
			int goodC = 0;
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
				sb.append(text + "<br/> ");
				
				request.setAttribute("great", "优:"+greatC+"&nbsp;&nbsp;");
				request.setAttribute("good", "良:"+goodC);

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
			/*String src = contextPath + "/servlet/AutoImgServlet?"
					+ System.currentTimeMillis();
			PrintWriter out = response.getWriter();
			out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
			out.println("<HTML>");
			out.println("  <HEAD><script>	function getImg(){ alert('asdf');	var imgObj = document.getElementById('autoImg'); imgObj.src ='"
					+ contextPath
					+ "/servlet/AutoImgServlet?"
					+ System.currentTimeMillis()
					+ "' }function isValue(){ var textObj = document.getElementById('autoImgInput');if(==textObj.value){	alert('验证码正确');}}</script><TITLE>show</TITLE></HEAD>");
			out.println("  <BODY>");
			out.write(sb.toString() + "优的个数:" + greatC + "&nbsp;&nbsp;良的个数:"
					+ goodC);
			out.write("<br/><a href='" + contextPath
					+ "/servlet/DownLoadServlet'>下载本次评分数据</a>");
//			out.write("<br/><input type='text' id='autoImgInput' onchange='isValue()'><img id='autoImg' src="
//					+ src
//					+ " onclick='getImg()'/><a href='javascript:getImg()'>看不清？</a>");
			out.println("  </BODY>");
			out.println("</HTML>");
			out.flush();
			out.close();
*/		}
		//转发到jsp页面
		request.setAttribute("result", sb.toString());
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/pages/result.jsp");
		requestDispatcher.forward(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);

	}

}
