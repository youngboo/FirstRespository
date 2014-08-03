package com.itheima.test;

import org.junit.Test;

interface Fu{
	void a();
}
 class ImplementTest implements Fu{

	public void a() {
		
	}

}
class zi extends ImplementTest{
	
}
public class TestImpl{
	@Test
	public void test1(){
	zi	z = new zi();
	z.a();
	}
}