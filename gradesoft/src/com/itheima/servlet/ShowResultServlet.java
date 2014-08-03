package com.itheima.servlet;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.itheima.util.Dom4JUtilResult;

/**
 * 用于评分结果的显示
 * 使用session判断用户是否是第一次访问结果页面
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
		
		
		

		
//		StringBuilder sb = null;
//		int greatC = 0;
//		int goodC = 0;
		// List<String> results = new ArrayList<String>();
/*		Map<String, String> results = new LinkedHashMap<String, String>();
		Element rootElement = document.getRootElement();
		List elements = rootElement.elements("grade");
		if (elements.size() == 0) {
			request.setAttribute("message", "没有人评分");
		} else {
			sb = new StringBuilder();
			greatC = 0;
			goodC = 0;
			for (Object object : elements) {
				Element gradeObj = (Element) object;
				String text = gradeObj.getText();
				if (text != null && !"".equals(text)) {
					if (text.contains("优")) {
						greatC++;
					} else if (text.contains("良")) {
						goodC++;
					}
					String[] split = text.split(":");
					results.put(split[0], split[1]);
					sb.append(text + "<br/> ");
				}
				int count = goodC + greatC;
				System.out.println("优：" + greatC + "    良：" + goodC
						+ "    投票总人数：" + count);

				request.setAttribute("count", goodC + greatC);


			}

		}*/
//		writeResultXml(greatC, goodC);
//		System.out.println(goodC + greatC);
		// 转发到jsp页面
		// request.setAttribute("result", sb.toString());
//		request.setAttribute("results", results);
		//使用session判断用户是否是第一次访问结果页面
		HttpSession session = request.getSession();
		String string = UUID.randomUUID().toString();
		session.setAttribute("isFirst", string);
		System.out.println("放入域中的token"+string);
		
		RequestDispatcher requestDispatcher = request
				.getRequestDispatcher("/WEB-INF/pages/result.jsp");
		requestDispatcher.forward(request, response);

	}

	/**
	 * 把评分情况写入xml中
	 * 
	 * @param greatC
	 * @param goodC
	 */
	private void writeResultXml(int greatC, int goodC) {
		int count = greatC+goodC;
		Document documentResult = Dom4JUtilResult.getDocument();
		Element rootElement1 = documentResult.getRootElement();
		List elements = rootElement1.elements("result");
		if (elements.isEmpty()) {
			Element createElement = DocumentHelper.createElement("result");
			createElement.addAttribute("优", String.valueOf(greatC))
					.addAttribute("良", String.valueOf(goodC))
					.addAttribute("投票人数", String.valueOf(count));
			DateFormat format = DateFormat.getDateInstance();
			String format2 = format.format(new Date());
			createElement.setText(format2);
			rootElement1.add(createElement);
		} else {
			for (Object object : elements) {
				Element ele = (Element) object;
				String format = DateFormat.getDateInstance().format(new Date());
				if (format.equals(ele.getText())) {
					ele.setAttributeValue("优", String.valueOf(greatC));
					ele.setAttributeValue("良", String.valueOf(greatC));
					ele.setAttributeValue("投票人数",
							String.valueOf(count));
					Dom4JUtilResult.writeDocument2Xml(documentResult);
					return;
				}
			}
			Element createElement = DocumentHelper.createElement("result");
			createElement.addAttribute("优", String.valueOf(greatC))
					.addAttribute("良", String.valueOf(goodC))
					.addAttribute("投票人数", String.valueOf(count));
			DateFormat format = DateFormat.getDateInstance();
			String format2 = format.format(new Date());
			createElement.setText(format2);
			rootElement1.add(createElement);
		}
		Dom4JUtilResult.writeDocument2Xml(documentResult);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);

	}

}
