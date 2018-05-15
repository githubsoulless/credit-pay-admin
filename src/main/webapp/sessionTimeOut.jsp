<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
	String ctx = request.getContextPath();
	request.setAttribute("ctx", ctx);
%>
<html>
<head>
<title>超时</title>
	<script type="text/javascript">
	if (window.top!=window.self)      
	{     
		window.top.location.href="${ctx}";     
	}else{
		window.location.href="${ctx}";     
	}
	</script>
</head>
<body>
</body>
</html>