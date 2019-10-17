<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<title>Insert title here</title>
<style type="text/css">
.input_sy{
	width: 208px;
	height: 25px;
}
</style>
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
function subForm(){
	if($("#userName").val()==""){
		alert("请输入用户姓名");
		$("#userName").focus();
		return;
	}
	if(!/^[\u4e00-\u9fa5]{2,20}$/.test($("#userName").val())){
		alert("用户姓名格式不正确");
		$("#userName").focus();
		return;
	}
	if($("#loginPwd1").val()!=""&&!/^[0-9a-zA-Z]{6,20}$/.test($("#loginPwd1").val())){
		alert("登录密码格式不正确");
		$("#loginPwd1").focus();
		return;
	}
	if($("#loginPwd1").val()!=""&&$("#loginPwd2").val()==""){
		alert("请输入登录密码确认");
		$("#loginPwd2").focus();
		return;
	}
	if($("#loginPwd1").val()!=""&&$("#loginPwd1").val()!=$("#loginPwd2").val()){
		alert("两次输入的密码不一致");
		$("#loginPwd2").focus();
		return;
	}
	if($("#mobileNo").val()!=""&&!/^1[3|4|5|7|8][0-9]\d{8}$/.test($("#mobileNo").val())){
		alert("手机号格式错误!");
		$("#mobileNo").focus();
		return;
	}
	if($("#email").val()!=""&&!/^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/.test($("#email").val())){
		alert("邮箱格式错误!");
		$("#email").focus();
		return;
		}
	W.showWait();
	document.getElementById("subForm").submit();
}
function callBack(result) {
	W.hideWait();
	if(result=="success"){
		alert("操作成功!");
		D.getElementById('closeTp').value = "1";
		api.close();
	}else{
		alert("操作失败,"+result+"!");
	}
}
function init(){
	var result="${message}";
	if(result!=""){
		parent.callBack(result);
	}
}
</script>
</head>
<body onload="init()">
<form action="${ctx}/mgrUser/update" id="subForm" method="post" target="hidden_frame">
<input name="loginId" type="hidden" value="${mgrUser.loginId }"/>
<div class="begin">
<table >
<tr>
	<td align="right">账户登录名：</td>
	<td>${mgrUser.loginId }</td>
		<td class="width90"><span>*用户姓名：</span></td>
					<td><span> <input name="userName" id="userName" value="${mgrUserInf.userName }" class="ipt" placeholder="2-20,支持中文"/>
					</span></td>
				</tr>
				<tr>
					<td class="width90"><span>登录密码：</span></td>
					<td><span><input type="password" name="loginPwd" id="loginPwd1" class="ipt" maxlength="20"  placeholder="6-20,支持字母、数字"/></span></td>
					<td class="width90"><span>登录密码确认：</span></td>
					<td><span> <input type="password" id="loginPwd2" class="ipt" maxlength="20"  placeholder="再次输入登录密码"/>
					</span></td>
				</tr>
				<tr>
					<td class="width90"><span>手机号：</span></td>
					<td><span><input name="mobileNo" id="mobileNo" value="${mgrUserInf.mobileNo }" maxlength="11" class="ipt" placeholder="有效手机号"/></span></td>
					<td class="width90"><span>邮箱：</span></td>
					<td><span><input name="email" id="email" value="${mgrUserInf.email }" maxlength="50" class="ipt" placeholder="常用邮箱"/>
					</span></td>
				</tr>
				<tr>
					<td class="width90"><span>选择角色：</span></td>
					<td><span>
					<select  name="roleId">
						<c:forEach items="${roleList }" var="r">
							<option value="${r.roleId }" ${mgrUserInf.roleId==r.roleId?'selected="selected"':'' } >${r.roleNm }</option>
						</c:forEach>
					</select>
					</span></td>
					<td class="width90"><span></span></td>
					<td><span> </span></td>
				</tr>
				<tr class="textcenter">
					<td colspan="4">
						<button class="btn1" type="button" onclick="subForm()">确定修改</button>
						<button class="btn2" onclick="cancelVal()">取消</button>
					</td>
				</tr>

			</table>
		</div>
	</form>
<iframe name='hidden_frame' id="hidden_frame" style='display: none'></iframe>
</body>
</html>