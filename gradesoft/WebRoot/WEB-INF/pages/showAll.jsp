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
    欢迎来到后台管理
    
    
    	<myc:if test="${pageContext.request.remoteAddr=='127.0.0.1' }">
		<myc:if test="${empty requestScope.message }">
			<form method="post" id="imgForm">
				<input type="text" name="authcode" style="width:100px" id="imgTex">
				<img alt="" src="" id="autoImg" onclick="getImg()">${requestScope.note
				}<br> <br> <input type="button" value="输入验证码下载此次评分情况"
					onclick="subImgText()"><input type="button"
					value="输入验证码下载历次评分情况" onclick="subImgTextAll()">
			</form>
			<script>
				function subImgText() {
					var formObj = document.getElementById("imgForm");
					formObj.action = "/gradesoft/servlet/DownLoadServlet?type=this";
					formObj.submit();
				}
				function subImgTextAll() {
					var formObj = document.getElementById("imgForm");
					formObj.action = "/gradesoft/servlet/DownLoadServlet?type=all";
					formObj.submit();
				}
				getImg();
			</script>
		</myc:if>
	</myc:if>
  </body>
</html>
