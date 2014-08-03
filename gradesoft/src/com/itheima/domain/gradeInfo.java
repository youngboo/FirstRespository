package com.itheima.domain;
/**
 * 用于记录每天的打分信息
 * @author yannnn

 */
/*
  create table gradeInfo(
 date varchar(20),
 name varchar(20),
 greatCount INT,
 goodCount INT,
 studentCount INT
 );
 */
public class gradeInfo {
	/**记录日期*/
	private String date;
	/**记录演讲人*/
	private String name;
	/**记录当日打优的个数*/
	private Integer greatConunt;
	/**记录当日打良的个数*/
	private Integer goodConunt;
	/**总人数*/
	private Integer studentCount;
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getGreatConunt() {
		return greatConunt;
	}
	public void setGreatConunt(Integer greatConunt) {
		this.greatConunt = greatConunt;
	}
	public Integer getGoodConunt() {
		return goodConunt;
	}
	public void setGoodConunt(Integer goodConunt) {
		this.goodConunt = goodConunt;
	}
	public Integer getStudentCount() {
		return studentCount;
	}
	public void setStudentCount(Integer studentCount) {
		this.studentCount = studentCount;
	}
	private gradeInfo(String date, String name, Integer greatConunt,
			Integer goodConunt, Integer studentCount) {
		super();
		this.date = date;
		this.name = name;
		this.greatConunt = greatConunt;
		this.goodConunt = goodConunt;
		this.studentCount = studentCount;
	}
	private gradeInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "gradeInfo [date=" + date + ", name=" + name + ", greatConunt="
				+ greatConunt + ", goodConunt=" + goodConunt
				+ ", studentCount=" + studentCount + "]";
	}
	
	
}
