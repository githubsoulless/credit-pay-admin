<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	request.setAttribute("ctx", request.getContextPath());
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<link href="${ctx }/static/css/refundSearch.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src='${ctx }/static/js/jquery-1.7.1.min.js'></script>
<title>融亿达管理平台</title>
</head>

<body>
	<div class="manage-container">
		<div class="manage-box">
			<div class="manage-content">
				<p class="manage-title">融亿达管理平台</p>
				<div class="manage-smallbg">
					<div class="manage-cont">
						<form id="loginForm" action='${ctx }/public/login' method="post">
							<div class="">
								<p class="manage-userp">
									<span class="manage-userimg"> <img src="${ctx }/static/imgs/user.png" class="icon" />
									</span><input type="text" name="username"  id="username" value="${username}" class="m-user"placeholder="请输入用户名" />
								</p>
								<i class="prompt"></i>
							</div>
							<div class="">
								<p class="manage-userp">
									<span class="manage-userimg manage-userimg-lock"> <img src="${ctx }/static/imgs/lock.png" class="icon" />
									</span> <input type="password" name="password"  id="password"  class="m-user" placeholder="请输入密码" />
								</p>
								<i class="prompt"></i>
							</div>
							<div class="manage-code-box">
								<p class="manage-userp manage-userpkey">
									<span class="manage-userimg manage-userimg-key"> <img src="${ctx }/static/imgs/key.png" class="icon" />
									</span> <input type="text" name="securityCode" id="securityCode" class="m-user" maxlength="4" onkeydown="LoginSubmit(event)" placeholder="请输入验证码"/>
								</p>
								<img  alt="看不清，换一张" id="imgObj" title="看不清，换一张"  src='<c:url value="/public/randomImage"/>' onclick="changeImg()" class="codeimg" width="140" height="40" /> 
								<i class="prompt" style="float: left;">${errorMessage}</i>
							</div>
							<button class="login-button" onclick="sub()">登录</button>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script>
		function sub() {
			if ($("#username").val() == "") {
				$("#username").focus();
				return false;
			}
			if ($("#password").val() == "") {
				$("#password").focus();
				return false;
			}
			if ($("#securityCode").val() == "") {
				$("#securityCode").focus();
				return false;
			}
			$("#loginForm").submit();
		}

		function changeImg() {
			$("#imgObj").attr("src", $("#imgObj").attr("src") + "?temp=" + new Date());
		}

		//首页输入密码后默认回车提交登录事件
		function LoginSubmit(evt) {
			if (evt.keyCode == 13) {
				evt.keyCode = 9;
				evt.returnValue = false;
				sub();
			}
		}
	</script>
</body>
</html>
