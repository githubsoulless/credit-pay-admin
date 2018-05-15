<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%request.setAttribute("ctx", request.getContextPath());
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>找不到页面</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<style type="text/css">
/* 	body { */
/* 	 background-image: url('img/404.jpg'); */
/* 	 background-repeat:  no-repeat; */
/* 	} */
	</style>
  </head>
  
  <body style="background-color: gray;">
  <br/><br/><br/><br/><br/><br/>
    <center>
	<img alt="404" src="${ctx}/static/img/404.jpg"><br/><br/><br/>
    </center>
  </body>
</html>