package com.itheima.servlet;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AutoImgServlet extends HttpServlet {

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
		//获取到验证图片的值
		String imgValue = drawImg(response);
		System.out.println("放入session的验证码"+imgValue);
		HttpSession session = request.getSession();
		session.setAttribute("autoImgVal", imgValue);
//		放到域中
	}

	private String drawImg(HttpServletResponse resp) {
		resp.setHeader("Content-Type", "image/jpeg");
		resp.addHeader("Progam", "no-cache");
		resp.addHeader("Expires", "-1");
		resp.addHeader("Cache-Control", "no-cache");
		
		
		int width = 100,height = 20;
		BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
		Graphics g = img.getGraphics();
		//画框
		g.setColor(Color.BLACK);
		g.drawRect(0, 0, width, height);
		//画背景
		g.setColor(Color.white);
		g.fillRect(0, 0, width, height);
		
		//画线条
		Random r = new Random();
		g.setColor(Color.yellow);
		for (int i = 0; i < 20; i++) {
			int x = r.nextInt(width);
			int y = r.nextInt(height);
			g.drawLine(x, y, r.nextInt(width), r.nextInt(height));
		}
	
		g.setColor(Color.CYAN);
		g.setFont(new Font("微软雅黑",Font.BOLD,20));
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 4; i++) {
			String s =String.valueOf( r.nextInt(9));
			sb.append(s);
			g.drawString(s, 20*i,20);
		}
		try {
			ImageIO.write(img, "jpg", resp.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb.toString();
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
