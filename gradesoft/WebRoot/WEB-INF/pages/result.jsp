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
		imgObj.src = "/gradesoft/servlet/AutoImgServlet?" + new Date().getTime();
		var imgText = document.getElementById("imgTex");
		imgText.value = "";
		imgText.onfouse();

	}
	function disposeStr(str) {
		alert(str.length());
		var subStr = str.substring(0, str.length - 1) + "*";
		alert();
	}
	function loadXMLDoc() {
		var xmlhttp;
		var txt, x, i;
		if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
			xmlhttp = new XMLHttpRequest();
		} else {// code for IE6, IE5
			xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
		xmlhttp.onreadystatechange = function() {
			if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
				xmlDoc = xmlhttp.responseXML;
				if (xmlDoc==null) {
					var txtDoc = xmlhttp.responseText;
					document.getElementById("myDiv").innerHTML = txtDoc;
				}else{
				txt = "";
				x = xmlDoc.getElementsByTagName("grade");
				for (i = 0; i < x.length; i++) {
				var nodeee = x[i].childNodes[0].nodeValue;
				var nodespl = nodeee.split(":");
					txt = txt + x[i].childNodes[0].nodeValue + "<br />";
				document.getElementById("myDiv").innerHTML = txt;
				var tdObj1 = document.createElement("td");
				tdObj1.innerHTML = "123";
				var tdObj2 = document.createElement("td");
				tdObj1.innerHTML = x[i].childNodes[0].nodeValue;
				var tdObj3 = document.createElement("td");
				tdObj1.innerHTML = x[i].childNodes[0].nodeValue;
				var trObj = document.createElement("tr");
				trObj.appendChild(tdObj1);
				trObj.appendChild(tdObj2);
				trObj.appendChild(tdObj3);
				document.getElementById("aTable").insertRow(trObj);
				}
				/* var rowww = "<tr><td>"+i+"</td><td>"+i+"</td><td>"+i+"</td></tr>";
				alert(rowww); */
				
				}
			}
		}
		xmlhttp.open("GET",
				"${pageContext.request.contextPath}/servlet/ReturnAjaxInfo?isFirst=${sessionScope.isFirst}&ajaxType=showResult&time="
						+ new Date().getTime(), true);
		xmlhttp.send();
	}
	setInterval("loadXMLDoc()","2000");
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
<div id="myDiv"></div>
	${requestScope.message}


	<br>
	<myc:if test="${empty requestScope.message }">
		<table align="center" style="border:1px red solid;width:400px" id="aTable">
			<tr align="center">
				<td>优：*</td>
				<td>良：*</td>
				<td>共有${requestScope.count }人投票</td>
			</tr>
			<tr>
				<td>&nbsp;&nbsp;</td>
				<td>&nbsp;&nbsp;</td>
				<td>&nbsp;&nbsp;</td>

			</tr>
			<tr align="center">
				<th>投票顺序</th>
				<th>IP值</th>
				<th>评分</th>
			</tr>
			<c:forEach items="${requestScope.results }" var="r" varStatus="vs">
				<tr align="center" class="${vs.count%2==0?'odd':'even'}">
					<td>${vs.count }</td>
					<td>${r.key}</td>
					<td>*</td>
				</tr>
			</c:forEach>

		</table>

	</myc:if>
	<%-- <myc:if test="${empty requestScope.message}">
	
	</myc:if> --%>
</body>
</html>
