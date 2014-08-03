package com.itheima.servlet.view;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Document;
import org.dom4j.Element;

import com.itheima.domain.RegionPara;
import com.itheima.util.Dom4JUtil;

/**
 * 用于主页显示
 * 
 * @author yannnn
 * 
 */
@SuppressWarnings("serial")
public class ShowMainPage extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String attribute = RegionPara.MAIN_PAGE;
		// 获得ip列表，判断ip是否合法
		String remoteAddr = request.getRemoteAddr();
		if (remoteAddr == null) {
			request.setAttribute(attribute, "地址是啥啊");
		} else if ("".equals(remoteAddr)) {
			request.setAttribute(attribute, "妈蛋，地址获取不到啊");
		} else {
			System.out.println(remoteAddr);
			// 读取xml文件，判断该ip是否已经评过分
			Document document = Dom4JUtil.getDocument();
			Element rootElement = document.getRootElement();
			List elements = rootElement.elements("grade");
			if (elements.size() > 91) {
				request.setAttribute(attribute, "投票结束");
				return;
			} else if (!elements.isEmpty()) {
				for (Object object : elements) {
					Element ele = (Element) object;
					if (ele.getText() != null && !"".equals(ele.getText())) {
						String text = ele.getText();
						if (text.contains(remoteAddr)) {
							request.setAttribute(attribute, "您已经评过分");
						}
					}
				}
			}

		}

		// 转发到首页
		request.getRequestDispatcher("/WEB-INF/pages/mainPage.jsp").forward(
				request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);

	}

}
