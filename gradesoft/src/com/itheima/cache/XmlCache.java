package com.itheima.cache;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


import com.itheima.util.Dom4JUtil;

/**
 * 定时将xml文件读取到内存中，单例
 * 
 * @author yannnn
 * 
 */
public class XmlCache {
	static ByteArrayOutputStream out = null;
	private static XmlCache xmlCache = null;

	private XmlCache() {
		ScheduledExecutorService ex2 = Executors.newScheduledThreadPool(1);
		ex2.scheduleAtFixedRate(new Runnable() {
			public void run() {
				try {
					FileInputStream in = Dom4JUtil.getDomInput();
					out = new ByteArrayOutputStream();
					int len = 0;
					byte[] b = new byte[1024];
					while ((len = in.read(b)) != -1) {
						out.write(b, 0, len);
					}
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}, 0, 2, TimeUnit.SECONDS);

	}

	public static XmlCache getXmlCache() {
		if (xmlCache == null) {
			synchronized (XmlCache.class) {
				if (xmlCache == null) {
					xmlCache = new XmlCache();
					return xmlCache;
				}
			}
		}
		return xmlCache;
	}

	/**
	 * 取得读取到的二进制xml文件，如果没有读取到，则返回null
	 * 
	 * @return byte[]
	 */
	public byte[] getByteArray() {
		if (out.toByteArray() != null && out.toByteArray().length > 0) {
			return out.toByteArray();
		}
		return null;
	}

}
