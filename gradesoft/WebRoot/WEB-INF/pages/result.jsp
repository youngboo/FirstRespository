<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title></title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">

 <script>
	function getImg() {
		var imgObj = document.getElementById('autoImg');
		imgObj.src =<%=request.getContextPath() %> + '/servlet/AutoImgServlet?'
				+new Date();
	}

	function isValue() {
		var textObj = document.getElementById('autoImgInput');
		alert(textObj.value);
		if ('"+attribute+"' == textObj.value) {
			alert('验证码正确');
		} else {
			alert('验证码错误');
		}
	}
</script>
 
</head>
<body>
${requestScope.message}
${requestScope.result}<br/>
${requestScope.great }${requestScope.good }
	
</body>
</html>
