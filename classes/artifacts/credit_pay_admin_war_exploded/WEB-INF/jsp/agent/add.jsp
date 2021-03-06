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
<title>Insert title here</title>
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
		if ($("#agentName").val() == "") {
			alert("请输入代理名称");
			$("#agentName").focus();
			return;
		}
		if ($("#userId").val() == "") {
			alert("请输入绑定用户账号");
			$("#userId").focus();
			return;
		}
		if ($("#level").val() == "") {
			alert("请选择代理级别");
			$("#level").focus();
			return;
		}
		if ($("#parentAgentId").val() == "-1") {
			alert("上级代理不正确");
			$("#parentAgentId").focus();
			return;
		}
		if ($("#linkName").val() == "") {
			alert("请输入业务联系人");
			$("#linkName").focus();
			return;
		}
		if ($("#mobile").val() == "") {
			alert("请输入联系电话");
			$("#mobile").focus();
			return;
		}
		if ($("#email").val() == "") {
			alert("请输入联系邮箱");
			$("#email").focus();
			return;
		}
		if ($("#address").val() == "") {
			alert("请输入联系地址");
			$("#address").focus();
			return;
		}
		if ($("#agentLoginId").val() == "") {
			alert("请输入代理登录账号");
			$("#agentLoginId").focus();
			return;
		}
		if(!/^[0-9a-zA-Z]{6,20}$/.test($("#agentLoginId").val())){
			alert("代理登录账号格式不正确");
			$("#agentLoginId").focus();
			return;
		}
		if ($("#password").val() == "") {
			alert("请输入初始登录密码");
			$("#password").focus();
			return;
		}
		if(!/^[0-9a-zA-Z]{6,24}$/.test($("#password").val())){
			alert("初始登录密码格式不正确");
			$("#password").focus();
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
function changeLevel(val) {
		$("#parentAgentId").empty();
			$("#parentAgentId").append("<option value=''>平台	</option>");
		if(val==1){
		}else if(val==2){
			<c:forEach items="${agentList }" var="agt">
			<c:if test="${agt.level==1 }">
			$("#parentAgentId").append("<option value='${agt.agentId}'>${agt.agentName}</option>");
			</c:if>
			</c:forEach>
		}else if(val==3){
			<c:forEach items="${agentList }" var="agt">
			<c:if test="${agt.level==1 || agt.level==2 }">
			$("#parentAgentId").append("<option value='${agt.agentId}'>${agt.agentName}</option>");
			</c:if>
			</c:forEach>
		}else{
			$("#parentAgentId").append("<option value='-1'>请选择</option>");
		}
}
</script>
<style type="text/css">
.begin2{
	padding:20px 0px;
}
</style>
</head>
<body onload="init()">
	<form action="${ctx }/agent/add" id="subForm" method="post"
		target="hidden_frame">
		<input type="hidden" name="type" value="add">
		<div class="begin begin2">
			<table border="" cellspacing="" cellpadding="" style="white-space: nowrap;">
				<tr>
					<td class="width90"><span style="color:red;">*</span><span>代理名称：</span></td>
					<td><span><input name="agentName" id="agentName" value="" maxlength="50" class="ipt" /></span></td>
					<td class="width90"><span style="color:red;">*</span><span>绑定用户账号：</span></td>
					<td><span> <input name="userId" id="userId" value="" maxlength="11" class="ipt"/>
					</span></td>
				</tr>
				<tr>
					<td class="width90"><span><span style="color:red;">*</span>代理级别：</span></td>
					<td><span>
						<select  name="level" id="level"  onchange="changeLevel(this.value)">
							<option value="">请选择</option>
							<option value="1">一级代理</option>
							<option value="2">二级代理</option>
							<option value="3">三级代理</option>
						</select>
					</span></td>
					<td class="width90"><span style="color:red;">*</span><span>上级代理：</span></td>
					<td><span>
						<select  name="parentAgentId" id="parentAgentId">
							<option value="-1">请选择</option>
						</select>
					</span></td>
				</tr>
				<tr>
					<td class="width90"><span style="color:red;">*</span><span>业务联系人：</span></td>
					<td><span><input name="linkName" id="linkName" value="" maxlength="20" class="ipt" /></span></td>
					<td class="width90"><span style="color:red;">*</span><span> 联系电话：</span></td>
					<td><span><input name="mobile" id="mobile" value="" maxlength="15" class="ipt" />
					</span></td>
				</tr>
				<tr>
					<td class="width90"><span style="color:red;">*</span><span>联系邮箱：</span></td>
					<td><span><input name="email" id="email" value="" maxlength="50" class="ipt" /></span></td>
					<td class="width90"><span style="color:red;">*</span><span> 联系地址：</span></td>
					<td><span><input name="address" id="address" value="" maxlength="100" class="ipt" />
					</span></td>
				</tr>
				<tr>
					<td class="width90"><span style="color:red;">*</span><span>代理登录账号：</span></td>
					<td><span><input name="agentLoginId" id="agentLoginId" value="" maxlength="20" class="ipt" placeholder="6-20位字母和数字" /></span></td>
					<td class="width90"><span style="color:red;">*</span><span>  初始登录密码：</span></td>
					<td><span><input name="password" id="password" maxlength="24" class="ipt"  placeholder="6-24位字母和数字" />
					</span></td>
				</tr>
				<tr class="textcenter">
					<td colspan="4">
						<button class="btn1" type="button" onclick="subForm()">创建</button>
						<button class="btn2" onclick="cancelVal()">取消</button>
					</td>
				</tr>

			</table>
		</div>
	</form>
	<iframe name='hidden_frame' id="hidden_frame" style='display: none'></iframe>
</body>
</html>