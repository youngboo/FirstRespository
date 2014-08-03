package com.itheima.test;

import org.junit.Test;

import com.itheima.cache.XmlCache;
import com.itheima.util.FileReaderUtil;

public class testOther {
	
	@Test
	public void test1() throws Exception{
		FileReaderUtil.getInstance("d:\\ipp.txt").textToXml();
	}
	@Test
	public void test2MacMatch() throws Exception{
		String line = "192.168.1.2         N/A                 N/A                 N/A                 N/A                                     ";
		String ll = line.trim();
System.out.println(ll);
		if (true) {
			System.out.println("不合法");
		}else{
			System.out.println("合法的ip");
		}
	}
}
