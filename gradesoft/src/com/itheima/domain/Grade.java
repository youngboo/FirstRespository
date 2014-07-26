package com.itheima.domain;

public enum Grade {
great("优"),good("良"),fine("中"),bad("差");
	private String name;

private Grade(String name) {
	this.name = name;
	}


@Override
public String toString() {
	return this.name;
}}
	
	
	
	

