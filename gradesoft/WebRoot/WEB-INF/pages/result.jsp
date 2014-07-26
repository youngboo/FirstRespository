<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.itheima.com/myjstl/core" prefix="myc"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title></title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">

<script type="text/javascript">
	function getImg() {
		var imgObj = document.getElementById("autoImg");
		imgObj.src = "/gradesoft/servlet/AutoImgServlet?" + new Date();
		var imgText = document.getElementById("imgTex");
		imgText.value = "";
		imgText.onfouse();

	}
	function disposeStr(str) {
		alert(str.length());
		var subStr = str.substring(0, str.length - 1) + "*";
		alert();
	}
/* 	alert(str.substring(0, str.length - 1) + "*"); */
</script>
<script type="text/css">
.odd{
	  	 background:#c3f3c3;
	  }
 .even{
	  	 background:#f3c3f3;
	  }
	
</script>
</head>
<body>
	${requestScope.message}

	<br>
	<myc:if test="${empty requestScope.message }">
		<table align="center" style="border:1px red solid;width:400px">
			<tr align="center">
				<th>投票顺序</th>
				<th>IP值</th>
				<th>评分</th>
			</tr>
			<c:forEach items="${requestScope.results }" var="r" varStatus="vs">
				<tr align="center" class="${vs.count%2==0?'odd':'even'}">
					<td>${vs.count }</td>
					<td>${r.key}</td>
					<td>${r.value}</td>
				</tr>
			</c:forEach>
			<tr>
				<td>&nbsp;&nbsp;</td>
				<td>&nbsp;&nbsp;</td>
				<td>&nbsp;&nbsp;</td>

			</tr>
			<tr align="center">
				<td>优：${requestScope.great }</td>
				<td>良：${requestScope.good }</td>
				<td>共有${requestScope.great+requestScope.good }人投票</td>
			</tr>
		</table>

	</myc:if>
	<%-- <myc:if test="${empty requestScope.message}">
		<form action="/gradesoft/servlet/DownLoadServlet" method="get"
			id="imgForm">
			<input type="text" name="authcode" style="width:100px" id="imgTex">
			<img alt="" src="" id="autoImg" onclick="getImg()">${requestScope.note
			}<br>
			<br> <input type="button" value="输入验证码下载此次评分情况"
				onclick="subImgText()"> <input type="submit"
				value="输入验证码下载此次评分情况">
		</form>
		<script>
			function subImgText() {
				var formObj = document.getElementById("imgForm");
				formObj.submit();
			}
			getImg();
		</script>
	</myc:if> --%>

</body>
</html>
