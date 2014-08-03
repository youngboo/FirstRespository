package com.itheima.util;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class MyDataSource {
	private List<Connection> connPool = new ArrayList<Connection>();
	
	private MyDataSource(){
		
	}
	private static MyDataSource datasource = null;
	
	
	
}
