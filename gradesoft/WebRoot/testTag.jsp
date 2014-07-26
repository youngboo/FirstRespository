<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.itheima.com/myjstl/core" prefix="myc"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title></title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	  </head>
  <body>
  	<myc:if test="false">
  		true的输出
  	</myc:if>
  	<myc:if test=""></myc:if>
  	<%
  		List list = new ArrayList();
  		list.add("sdf");
  		list.add("asdf");
  		list.add("asdf1");
  		list.add("asdf2");
  		list.add("asdf3");
  		pageContext.setAttribute("list", list);
  	 %>
  	<myc:forEach items="${list }" var="a">
  		${a}<br/>
  	</myc:forEach>
  </body>
</html>
