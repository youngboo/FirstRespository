package com.itheima.servlet;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Document;
import org.dom4j.Element;

import com.itheima.util.Dom4JUtil;
import com.sun.xml.internal.bind.v2.runtime.output.Encoded;

public class DownLoadServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		resp.addHeader("Progam", "no-cache");
		resp.addHeader("Expires", "-1");
		resp.addHeader("Cache-Control", "no-cache");
		
//		resp.setHeader("Expires", "-1");
//		resp.setHeader("Cache-Cotrol", "no-cache");
//		resp.setHeader("Pragma", "no-cache");
		OutputStream out = resp.getOutputStream();
	
		//读取xml文件信息，输出
		String path = getServletContext().getRealPath("/WEB-INF/grades.xml");
		Document document = Dom4JUtil.getDocument();
		Element rootElement = document.getRootElement();
		if (rootElement.elements().size()==0) {
			out.write("文件没有内容，请稍候下载<a href='/gradesofe/servlet'>返回打分页面</a>".getBytes("UTF-8"));
		}else{
			String filename = URLEncoder.encode("打分情况.xml","UTF-8");
			resp.addHeader("Content-Disposition", "attachment;filename="+filename);
			resp.addHeader("Content-Type", "application/octet-stream");
			
			FileInputStream in = new FileInputStream(path);
			int len = 0;
			byte[] b = new byte[1024]; 
			while((len =in.read(b))!=-1){
				out.write(b,0,len);
				out.flush();
			}
			in.close();
		}
		
		
	}

	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}

	
}
