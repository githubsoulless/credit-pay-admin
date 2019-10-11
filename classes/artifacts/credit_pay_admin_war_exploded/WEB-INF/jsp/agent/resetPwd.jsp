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
<script type="text/javascript" src='<c:url value="/static/js/jquery-1.7.1.min.js"/>'></script>
<script type="text/javascript" src='<c:url value="/static/js/loading.js"/>' language="javascript"></script>
<link href="${ctx }/static/css/alert.css" rel="stylesheet" type="text/css" />
<title>重置代理登录密码</title>
<script type="text/javascript">
(function() {
	var _skin, _lhgcore;
	var _search = window.location.search;
	if (_search) {
		_skin = _search.split('demoSkin=')[1];
	};
	
	document.write('<scr'+'ipt src="${ctx}/static/js/lhgdialog.js?skin=' + (_skin || 'idialog') +'"></sc'+'ript>');
	window._isDemoSkin = !!_skin;
})();
	var api = frameElement.api, W = api.opener, D = W.document; // api.opener 为载加lhgdialog.min.js文件的页面的window对象
	function cancelVal() {
		api.close();
	}
	function subForm() {
		if ($("#password").val() == "") {
			alert("请输入新登录密码");
			$("#password").focus();
			return;
		}
		if(!/^[0-9a-zA-Z]{6,24}$/.test($("#password").val())){
			alert("初始登录密码格式不正确");
			$("#password").focus();
			return;
		}
		if ($("#password1").val() == "") {
			alert("请再次输入新登录密码");
			$("#password1").focus();
			return;
		}
		if(!/^[0-9a-zA-Z]{6,24}$/.test($("#password1").val())){
			alert("新登录密码格式不正确");
			$("#password1").focus();
			return;
		}
		if ($("#password1").val() != $("#password").val()) {
			alert("两次输入密码不一致");
			$("#password1").focus();
			return;
		}
		showWait();
		document.getElementById("subForm").submit();
	}
	function callBack(result) {
		hideWait();
		if (result == "success") {
			alert("操作成功!");
			D.getElementById('closeTp').value = "1";
			api.close();
		} else {
			alert("操作失败," + result + "!");
		}
	}
	function init() {
		var result = "${message}";
		if (result != "") {
			parent.callBack(result);
		}
	}

</script>
</head>
<body onload="init()">
	<form action="${ctx }/agent/resetPwd" id="subForm" method="post"
		target="hidden_frame">
		<input type="hidden" name="type" value="resetpwd">
		<input type="hidden" name="agentId" value="${agent.agentId }">
		<input type="hidden" name="loginId" value="${agent.agentLoginId }">
		<div class="begin">
			<table border="" cellspacing="" cellpadding="">
				<tr>
					<td class="width90"><span>代理名称：</span></td>
					<td><span>${agent.agentName }</span></td>
				</tr>
				<tr>
					<td class="width90"><span>代理登录账号：</span></td>
					<td><span>${agent.agentLoginId }</span></td>
				</tr>
				<tr>
					<td class="width90"><span style="color:red;">*</span><span>重置登录密码：</span></td>
					<td><span><input name="loginPwd" id="password" placeholder="长度6-24位，支持字母和数字" maxlength="24" class="ipt" /></span></td>
				</tr>
				<tr>
					<td class="width90"><span style="color:red;">*</span><span>确认新密码：</span></td>
					<td><span><input name="loginPwd2" id="password1" maxlength="24" placeholder="再次输入新密码" class="ipt" /></span></td>
				</tr>
				<tr class="textcenter">
					<td colspan="2">
						<button class="btn1" type="button" onclick="subForm()">重置</button>
						<button class="btn2" onclick="cancelVal()">取消</button>
					</td>
				</tr>

			</table>
		</div>
	</form>
	<iframe name='hidden_frame' id="hidden_frame" style='display: none'></iframe>
</body>
</html>