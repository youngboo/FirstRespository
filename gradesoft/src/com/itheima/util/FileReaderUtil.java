package com.itheima.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;

public class FileReaderUtil {
	private static String path;
	
	public FileReaderUtil(String path){
		FileReaderUtil.path = path;
	}
	
	public static FileReaderUtil getInstance(String path){
		return new FileReaderUtil(path);
	}
	public static void textToXml() throws Exception{
		BufferedReader bfr = new BufferedReader(new FileReader(path));
		BufferedWriter bfw = new BufferedWriter(new FileWriter("d:\\ip.txt"));
		String line = null;
		int lineNum = 1;
		while ((line = bfr.readLine())!=null) {
			if(line.contains("WORKGROUP")||line.contains("MSHOME")||line.contains("XFIRE")||line.contains("FK")){
				bfw.write(lineNum+" : "+line);
				bfw.newLine();
				lineNum++;
				}
		}
		bfw.flush();
		bfr.close();
		bfw.close();
	}
	
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		FileReaderUtil.path = path;
	}
	
}
