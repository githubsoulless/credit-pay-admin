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
<script type="text/javascript" src="${ctx }/static/js/My97DatePicker/WdatePicker.js"></script>
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
		if($('#content').val() == ''){
			alert('请填写短信内容');
			$('#content').focus();
			return false;
		}
		if($('input:radio[name="pushRange"]:checked').length == 0){
			alert('请选择推送范围');
			return false;
		}
		if(1 == $('input:radio[name="pushRange"]:checked').val()){
			if($('input:checkbox[name="levelIds"]:checked').length == 0){
				alert("请选择等级");
				return false;
			}
		}
		if(2 == $('input:radio[name="pushRange"]:checked').val()){
			if($('#mobileNum').val() == ''){
				alert('请填写手机号，多个手机号请用";"隔开');
				return false;
			}
		}
		if(3 == $('input:radio[name="pushRange"]:checked').val()){
			if($('#agentLevel').val() == ''){
				alert('请选择代理级别');
				return false;
			}
			if($('#agentId').val() == ''){
				alert('请选择代理');
				return false;
			}
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
	function changeAgent(val) {
		var agentList=eval('${agentListJson}');
		$("#agentId").empty();
		$("#agentId").append("<option value=''>请选择</option>");
		for(var i=0;i<agentList.length;i++){
			if(agentList[i].level==val){
				$("#agentId").append("<option value='"+agentList[i].agentId+"'>"+agentList[i].agentName+"</option>");
			}
		}
	}
</script>
</head>
<body onload="init()">
	<form action="${ctx }/smsMessage/add" id="subForm" method="post"
		target="hidden_frame">
		<input type="hidden" name="type" id="type" value="add">
		<div class="begin">
			<table border="" cellspacing="" style="line-height: 3.0" cellpadding="">
				<tr>
					<td class="width90"><span style="color:red;">*</span><span>短信内容：</span></td>
					<td><span>
						<textarea rows="7" cols="50" maxlength="500" style="font-size: 13px;" id="content" name="content" placeholder="必填，500字以内"></textarea>
					</span></td>
				</tr>
				<tr>
					<td class="width90"><span><span style="color:red;">*</span>推送范围：</span></td>
					<td><span>
						<input type="radio" name="pushRange" value="1" id="pushRange1"/><label for="pushRange1">按等级</label>&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="radio" name="pushRange" value="2" id="pushRange2"/><label for="pushRange2">指定用户</label>&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="radio" name="pushRange" value="3" id="pushRange3"/><label for="pushRange3">按代理</label>
					</span></td>
				</tr>
				<tr class="forLevels" style="display: none;">
					<td class="width90"></td>
					<td><span>
						<c:forEach items="${levelList }" var="level">
							<input type="checkbox" name="levelIds" value="${level.levelId }" id="levelId${level.levelId }"/><label for="levelId${level.levelId }">${level.levelName }</label>&nbsp;&nbsp;&nbsp;&nbsp;
						</c:forEach>
					</span></td>
				</tr>
				<tr class="forUsers" style="display: none">
					<td class="width90">手机号：</td>
					<td><span>
						<textarea rows="7" cols="50" name="mobileNum" id="mobileNum" placeholder='多个手机号请用","隔开'></textarea>
					</span></td>
				</tr>
				<tr class="forAgents" style="display: none">
					<td></td>
					<td>
						<span>
							<select id="agentLevel" onchange="changeAgent(this.value)" style="width: 130px;height: 28px;line-height: 28px;padding-left: 5px;">
	  					   		<option value="">全部</option>
								<option value="1">一级代理</option>
								<option value="2">二级代理</option>
								<option value="3">三级代理</option>
	  					 	</select> 	
							<select id="agentId" name="agentId" style="width: 130px;height: 28px;line-height: 28px;padding-left: 5px;">
	  					   		<option value="">全部</option>
	  						 </select> 
						</span>
					</td>
				</tr>
				<tr class="textcenter">
					<td colspan="2">
						<button class="btn1" type="button" onclick="subForm()">发送</button>
						<button class="btn2" onclick="cancelVal()">取消</button>
					</td>
				</tr>

			</table>
		</div>
	</form>
	<iframe name='hidden_frame' id="hidden_frame" style='display: none'></iframe>
</body>

<script type="text/javascript">
$('input:radio[name="pushRange"]').change(function(){
	var type = $(this).val();
	if(1 == type){
		$(".forLevels").show();
		$('.forUsers').hide();
		$('.forAgents').hide();
	}
	if(2 == type){
		$(".forLevels").hide();
		$('.forUsers').show();
		$('.forAgents').hide();
	}
	if(3 == type){
		$(".forLevels").hide();
		$('.forUsers').hide();
		$('.forAgents').show();
	}
});
</script>
</html>