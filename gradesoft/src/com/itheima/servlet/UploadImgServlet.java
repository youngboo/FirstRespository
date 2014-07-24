package com.itheima.servlet;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UploadImgServlet extends HttpServlet {

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		response.setContentType("text/html;charset=UTF-8");
//		request.setCharacterEncoding("UTF-8");
//		File file = new File("imgs");
			//接收上传数据，并存放在服务器中
		//1,判断文件类型，必须是图片类型的
//		String parameter = request.getParameter("imgFile");
//		String header = request.getHeader("Content-Type");
//		System.out.println(header);
		
		ServletInputStream inputStream = request.getInputStream();
		String path = getServletContext().getRealPath("/imgs/1.jpg");
		System.out.println(path);
		FileOutputStream out = new FileOutputStream(new File(path));
		BufferedInputStream bfi = new BufferedInputStream(inputStream);
		BufferedOutputStream bfo = new BufferedOutputStream(System.out);
		ByteArrayOutputStream bo = new ByteArrayOutputStream();
		
		
		int line =0;
		byte[] b = new byte[1024];
		while((line = bfi.read())!=-1){
//			bfo.write(line);
			bo.write(b,0,line);
		}
		bfi.close();
		bfo.close();
//		bo.close();
		System.out.println(bo.toString());
		
//		int len = 0;
//		byte[] b = new byte[1024];
//		while((len = inputStream.read(b))!=-1){
//			System.out.println(new String(b,0,len));
//			out.write(b, 0, len);
////			out.flush();
//		}
//		out.close();
		
		
		//2，确定存放位置，防止多个文件打开速度下降
		//3，解决中文乱码
		
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

doGet(request, response);
	}

}
