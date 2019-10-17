<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	request.setAttribute("ctx", request.getContextPath());
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript"
	src='<c:url value="/static/js/jquery-1.7.1.min.js"/>'></script>
<script type="text/javascript"
	src='<c:url value="/static/js/loading.js"/>' language="javascript"></script>
<link href="${ctx }/static/css/alert.css" rel="stylesheet"
	type="text/css" />
<title>Insert title here</title>
<script type="text/javascript">
	function cancelVal() {
		$("input[type=password]").val("");
	}
	function subForm() {

		if ($("#oldPassWord").val() == "") {
			alert("请输入原密码");
			$("#oldPassWord").focus();
			return;
		}
		var message = "";
		$.ajax({
			type : "POST",
			dataType : "text",
			async : false,
			beforeSend : function(XHR) {
				showWait();
			},
			url : "${ctx}/checkLoginPwd",
			data : "oldPassWord=" + $("#oldPassWord").val(),
			success : function(msg) {
				if (msg == 'timeout') {
					window.location.reload(true);
					return;
				}
				hideWait();
				message = msg;
			}
		});
		if (message != 'true') {
			alert(message);
			$("#oldPassWord").focus();
			return;
		}
		if ($("#newPassWord").val() == "") {
			alert("请输入新登录密码");
			$("#newPassWord").focus();
			return;
		}
		if (!/^[0-9a-zA-Z]{6,20}$/.test($("#newPassWord").val())) {
			alert("新登录密码格式不正确");
			$("#newPassWord").focus();
			return;
		}
		if ($("#newPassWord").val() == $("#oldPassWord").val()) {
			alert("新密码不能与旧密码一致");
			$("#newPassWord").focus();
			return;
		}
		if ($("#newPassWord1").val() == "") {
			alert("请输入校验密码");
			$("#newPassWord1").focus();
			return;
		}
		if ($("#newPassWord").val() != $("#newPassWord1").val()) {
			alert("两次输入的密码不一致");
			$("#newPassWord1").focus();
			return;
		}
		showWait();
		document.getElementById("subUpdForm").submit();
	}
	function init() {
		var result = "${message}";
		if (result != "") {
			if (result == "success") {
				alert("密码修改成功,请重新登录");
				parent.location.href = "${ctx}/public/loginOut";
			} else {
				alert(result);
			}
		}
	}
</script>
</head>
<body onload="init()">
	<form action="mgrPwd_update" id="subUpdForm" method="post">
		<input type="hidden" name="type" value="update">
		<div class="begin">
			<table>
				<tr>
					<td class="width90"><span>用户登录ID：</span></td>
					<td><span> ${sessionScope.LOGIN_SESSION.loginId}
					</span></td>
				</tr>
				<tr>
					<td class="width90"><span>原登录密码：</span></td>
					<td><span><input type="password" placeholder="输入原密码" name="oldPassWord" id="oldPassWord" class="ipt" ></span></td>
				</tr>
				<tr>
					<td class="width90"><span>新登录密码：</span></td>
					<td><span><input type="password" placeholder="6-20,支持字母、数字" name="newPassWord" id="newPassWord"  class="ipt" ></span></td>
				</tr>
				<tr>
					<td class="width90"><span>校验新密码：</span></td>
					<td><span><input type="password" placeholder="校验新密码" name="newPassWord1" id="newPassWord1"  class="ipt" ></span></td>
				</tr>
				<tr class="textcenter">
					<td colspan="2">
						<button class="btn1" type="button" onclick="subForm()">确定修改</button>
						<button class="btn2" type="button" onclick="cancelVal()">清空</button>
					</td>
				</tr>

			</table>
		</div>
	</form>
</body>
</html>