package com.itheima.servlet;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.dom4j.Document;
import org.dom4j.Element;

import com.itheima.util.Dom4JUtil;

@SuppressWarnings("serial")
public class DownLoadServlet extends HttpServlet
{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		resp.setContentType("text/html;charset=UTF-8");

		resp.setHeader("Expires", "-1");
		resp.setHeader("Cache-Cotrol", "no-cache");
		resp.setHeader("Pragma", "no-cache");
		HttpSession session = req.getSession();
		
		String sessionAuthcode = (String) session.getAttribute("autoImgVal");
		String typedAuthcode = req.getParameter("authcode");

		//验证码一致时可以下载,否则要求重新输入验证码
		if(sessionAuthcode.equals(typedAuthcode))
		{
			download(req,resp);
		}
		else
		{
			//暂时方案时Refresh到输入验证码的页面
			req.setAttribute("note", "验证码错误");
			String url = "/servlet/ShowResultServlet";
			RequestDispatcher requestDispatcher = req.getRequestDispatcher(url);
			requestDispatcher.forward(req, resp);
			
			//resp.setHeader("Refresh", "0;URL="+url);
		}

	}

	private void download(HttpServletRequest req, HttpServletResponse resp) throws IOException, UnsupportedEncodingException, FileNotFoundException, ServletException
	{
		// 读取xml文件信息，输出
		String path = getServletContext().getRealPath("/WEB-INF/grades.xml");
		Document document = Dom4JUtil.getDocument();
		Element rootElement = document.getRootElement();
		if (rootElement.elements().size() == 0)
		{
			req.setAttribute("note", "文件没有任何内容，请稍后下载");
			String url = "/servlet/ShowResultServlet";
			RequestDispatcher requestDispatcher = req.getRequestDispatcher(url);
			requestDispatcher.forward(req, resp);
			//out.write("文件没有内容，请稍候下载<a href='/gradesofe/servlet'>返回打分页面</a>"
					//.getBytes("UTF-8"));
		}
		else
		{
			ServletOutputStream out = resp.getOutputStream();
			String filename = URLEncoder.encode("打分情况.xml", "UTF-8");
			resp.addHeader("Content-Disposition", "attachment;filename="
					+ filename);
			resp.addHeader("Content-Type", "application/octet-stream");

			FileInputStream in = new FileInputStream(path);
			int len = 0;
			byte[] b = new byte[1024];
			while ((len = in.read(b)) != -1)
			{
				out.write(b, 0, len);
				out.flush();
			}
			in.close();
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		doGet(req, resp);
	}

}
