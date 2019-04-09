<%@page import="net.chrone.creditpay.util.RedisClient"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	request.setAttribute("ctx", request.getContextPath());
String regionStr = RedisClient.getByKey(RedisClient.CACHE_PREFIX_REGION_LIST);
request.setAttribute("regionList", regionStr);
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
	var region=eval('${regionList}');
	function changeProv(provCd){
		$("#cityId").empty();
		$("#cityId").append("<option value=''>请选择</option>");
		$("#countyCd").empty();
		$("#countyCd").append("<option value=''>请选择</option>");
		for(var i=0;i<region.length;i++){
			if(provCd==region[i].provCd){
				var citys = region[i].citys;
				for(var j=0;j<citys.length;j++){
					$("#cityId").append("<option value='"+citys[j].cityCd+"'>"+citys[j].cityNm+"</option>");
				}
				return;
			}
		}
	}
	function changeCity(cityCd){
		$("#countyCd").empty();
		$("#countyCd").append("<option value=''>请选择</option>");
		for(var i=0;i<region.length;i++){
			if($("#provId").val()==region[i].provCd){
				var citys = region[i].citys;
				for(var j=0;j<citys.length;j++){
					if(cityCd==citys[j].cityCd){
						var regions = citys[j].regions;
						for(var a=0;a<regions.length;a++){
							$("#countyCd").append("<option value='"+regions[a].countyCd+"'>"+regions[a].countyNm+"</option>");
						}
					}
				}
				return;
			}
		}
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
		if($("#provId").val()=='460'){
			if ($("#cityId").val() == "") {
				alert("请选区区县");
				$("#cityId").focus();
				return;
			}
		}else{
			if ($("#countyCd").val() == "") {
				alert("请选区区县");
				$("#countyCd").focus();
				return;
			}
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
		for(var i=0;i<region.length;i++){
			$("#provId").append("<option value='"+region[i].provCd+"'>"+region[i].provNm+"</option>");
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
					<td class="width90"><span><span style="color:red;">*</span>代理区域：</span></td>
					<td  colspan="3"><span>
					<select style="width: 184px;" id="provId"  onchange="changeProv(this.value)">
						<option value="">请选择</option>
					</select>
					<select  style="width: 184px;" id="cityId"  name="cityId" onchange="changeCity(this.value)">
						<option value="">请选择</option>
					</select>
					<select  style="width: 184px;" id="countyCd" name="countyCd">
						<option value="">请选择</option>
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