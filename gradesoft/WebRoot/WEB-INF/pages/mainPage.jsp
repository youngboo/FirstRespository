<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.itheima.com/myjstl/core" prefix="myc"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>My JSP 'mainPage.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript">
	function submitForm1(thisObj) {
		var isSub = window.confirm("确认评分：" + thisObj.value + "？你只有一次打分机会");
		if (isSub) {
			formObject = document.getElementById("form1");
			formObject.submit();
		}
	}
/* 	function isImg() {
		var fileObj = document.getElementById("imgFile");
		var fileNamedd = fileObj.value;
		var formObj = document.getElementById("form2");
		fileNamedd = fileNamedd.substr(fileNamedd.lastIndexOf("\.") + 1,
				fileNamedd.length);
		if ("jpg" == fileNamedd.toLowerCase()|| "gif" == fileNamedd.toLowerCase()||"jpeg" == fileNamedd.toLowerCase()) {
			formObj.submit();
		}else{
			alert("请上传指定文件类型，jpg/jpeg或gif");
		}
	}
	function subForm(){
		var formObj = document.getElementById("form2");
			formObj.submit();
	}
	 */
</script>


  </head>
  
  <body>
  ${requestScope.main_page }
  <c:if test="${empty requestScope.main_page }">
  
	<table align="center" style="border:1px green solid;width:400px">
		<tr align="center">
			<td>请打分：
				<form action="${pageContext.request.contextPath}/servlet/MainServlet" method="post" id="form1">
					<label for="great">优</label><input type="radio" name="grade"
						value="优" id="great" onclick="submitForm1(this)" />
		 <label for="good">良</label><input type="radio" name="grade" value="良"
						id="good" onclick="submitForm1(this)" />
				</form></td>
		</tr>
		<tr>
			<td>
				<!-- <form action="servlet/UploadServlet" method="post"
					enctype="multipart/form-data" id="form2"> 请上传你珍藏的图片：<input
				type="file" onchange="subForm()" id="imgFile" name="imgFile"> <label
				for="imgFile">目前支持jpg和gif类型的图片</label></form> --></td>
		</tr>
		<tr align="center">
			<td><a href="/gradesoft/servlet/ShowResultServlet">跳转到结果页面</a></td>
		</tr>
	</table>  	
  </c:if>
 <%--  <c:if test="${!empty requestScope.main_page }">
  	<% response.sendRedirect(request.getContextPath()+"/servlet/ShowResultServlet"); %>
  </c:if> --%>
  
  <myc:if test="${pageContext.request.remoteAddr=='127.0.0.1' }">
  <a href="${pageContext.request.contextPath}/servlet/ShowAll">跳转到后台管理</a>
  </myc:if>
  
  </body>
</html>
