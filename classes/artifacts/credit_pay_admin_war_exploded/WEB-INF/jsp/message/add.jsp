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
		if($('#title').val() == ''){
			alert('请填写消息标题');
			$('#title').focus();
			return false;
		}
		if($('input:radio[name="pushRange"]:checked').length == 0){
			alert('请选择推送范围');
			return false;
		}
		if(2 == $('input:radio[name="pushRange"]:checked').val()){
			if($('#userId').val() == '' || $('#userId').val() == null){
				alert('请选择指定用户');
				return false;
			}
		}
		if($('#content').val() == ""){
			alert('请填写消息内容');
			$('#content').focus();
			return false;
		}
		
		showWait();
		document.getElementById("subForm").submit();
	}
	
	function searchUser(){
		var userMob = $('#userMob').val();
		if(userMob == ''){
			alert('请输入手机号');
			$('#userMob').focus();
			return false;
		}
		var url = "${ctx}/appUser/getUsersForMsg";
		$("#userId").empty();
		
		$.ajax({
			type : "POST",
			url : url,
			dateType : "text",
			data : {'userId' : userMob},
			success : function(r) {
				for(var i=0;i<r.length;i++){
					$("#userId").append("<option value='"+r[i].userId+"'>"+ r[i].userId + "-" + r[i].merName+"</option>");
				}
			}
		});
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
	function changeAgent(type,val) {
		var agentList=eval('${agentListJson}');
		if(type==1){//1级
			$("#agentId2").empty();
			$("#agentId3").empty();
			$("#agentId2").append("<option value=''>二级代理</option>");
			$("#agentId3").append("<option value=''>三级代理</option>");
			for(var i=0;i<agentList.length;i++){
				if(agentList[i].parentAgentId==val){
					$("#agentId2").append("<option value='"+agentList[i].agentId+"'>"+agentList[i].agentName+"</option>");
				}
			}
		}else if(type==2){
			$("#agentId3").empty();
			$("#agentId3").append("<option value=''>三级代理</option>");
			for(var i=0;i<agentList.length;i++){
				if(agentList[i].parentAgentId==val){
					$("#agentId3").append("<option value='"+agentList[i].agentId+"'>"+agentList[i].agentName+"</option>");
				}
			}
		}

	}
</script>
</head>
<body onload="init()">
	<form action="${ctx }/message/add" id="subForm" method="post"
		target="hidden_frame">
		<input type="hidden" name="type" id="type" value="add">
		<div class="begin">
			<table border="" cellspacing="" style="line-height: 3.0" cellpadding="">
				<tr>
					<td class="width90"><span style="color:red;">*</span><span>消息标题：</span></td>
					<td><span><input name="title" id="title" maxlength="30" placeholder="必填，30个字以内" style="width: 300px;height: 28px;line-height: 28px;border: 1px solid #a9a9a9;padding-left: 5px;"/></span></td>
				</tr>
				<tr>
					<td class="width90"><span><span style="color:red;">*</span>是否置顶：</span></td>
					<td><span>
						<input type="radio" name="isTop" value="0" id="top0" checked="checked"/><label for="top0">否</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="radio" name="isTop" value="1" id="top1"/><label for="top1">是</label>
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
						<select name="levelId" id="levelId" style="width: 130px;height: 28px;line-height: 28px;padding-left: 5px;">
							<option value="">全部</option>
							<c:forEach items="${levelList }" var="level">
								<option value="${level.levelId }">${level.levelName }</option>
							</c:forEach>
						</select>
					</span></td>
				</tr>
				<tr class="forUsers" style="display: none">
					<td class="width90">手机号：</td>
					<td><span>
						<input type="text" id="userMob" />&nbsp;&nbsp; <button class="btn1" type="button" onclick="searchUser()">搜索</button>
					</span></td>
				</tr>
				<tr class="forUsers" style="display: none">
					<td></td>
					<td>
						<span>
							<select name="userId" id="userId">
								
							</select>
						</span>
					</td>
				</tr>
				<tr class="forAgents" style="display: none">
					<td></td>
					<td>
						<span>
							<select id="agentId1" name="agentId1" onchange="changeAgent(1,this.value)" style="width: 130px;height: 28px;line-height: 28px;padding-left: 5px;">
	  					   		<option value="">全部</option>
								<c:forEach items="${agentList }" var="agt">
									<c:if test="${agt.level==1 }">
										<option value='${agt.agentId}'>${agt.agentName}</option>
									</c:if>
								</c:forEach>
	  					 	</select> 	
							<select id="agentId2" name="agentId2" onchange="changeAgent(2,this.value)" style="width: 130px;height: 28px;line-height: 28px;padding-left: 5px;">
	  					   		<option value="">全部</option>
	  					   		<c:forEach items="${agentList }" var="agt">
									<c:if test="${agt.level==2}">   <!-- && agt.parentAgentId==appuser.agentId1 -->
										<option value='${agt.agentId}'>${agt.agentName}</option>
									</c:if>
								</c:forEach>
	  						 </select> 
							<select id="agentId3" name="agentId3" style="width: 130px;height: 28px;line-height: 28px;padding-left: 5px;">
	  					   		<option value="">全部</option>
	  					   		<c:forEach items="${agentList }" var="agt">
									<c:if test="${agt.level==3}">   <!-- && agt.parentAgentId==appuser.agentId2 -->
										<option value='${agt.agentId}'>${agt.agentName}</option>
									</c:if>
								</c:forEach>
	  					 	</select> 
						</span>
					</td>
				</tr>
				<tr>
					<td class="width90"><span><span style="color:red;">*</span>消息内容：</span></td>
					<td>
						<span>
							<textarea rows="7" cols="50" maxlength="500" style="font-size: 13px;" id="content" name="content" placeholder="必填，500字以内"></textarea>
						</span>
					</td>
				</tr>
				<tr class="textcenter">
					<td colspan="2">
						<button class="btn1" type="button" onclick="subForm()">创建</button>
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