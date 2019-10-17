<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="chrone" uri="/tag/chrone-taglib"%>
<%@taglib prefix="page" uri="/tag/mypage-taglib"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
		//按等级
		if(1 == $('input:radio[name="sendType"]:checked').val()){
			if($('input:checkbox[name="levelIds"]:checked').length == 0){
				alert('请选择适用级别');
				return false;
			}
		}
		//指定用户
		if(2 == $('input:radio[name="sendType"]:checked').val()){
			if(userIds == "" || userIds == null){
				alert('请输入手机号');
				return false;
			}
		}
		//按代理
		if(3 == $('input:radio[name="sendType"]:checked').val()){
			if(agentId == "" || agentId == null){
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
	
</script>
</head>
<body onload="init()">
	<form action="${ctx }/couponMgr/sendCoupon" id="subForm" method="post"
		target="hidden_frame">
		<input type="hidden" name="type" id="type" value="send">
		<div class="begin">
			<table border="" cellspacing="" style="line-height: 3.0" cellpadding="">
				<tr>
					<td class="width90"><span>ID：</span></td>
					<td><span>${coupon.id }</span></td>
					<td class="width90"><span>优惠券名称：</span></td>
					<td><span>${coupon.name }</span></td>
				</tr>
				<tr>
					<td class="width90"><span>面值：</span></td>
					<td><span><chrone:fen2Yuan amt="${coupon.amount }">元</chrone:fen2Yuan>
					</span></td>
					<td class="width90"><span>订单金额：</span></td>
					<td><span>
						<c:if test="${coupon.limitAmountType==0 }">不限制</c:if>
						<c:if test="${coupon.limitAmountType==1 }">满<chrone:fen2Yuan amt="${coupon.limitAmount }"></chrone:fen2Yuan>元可使用</c:if>
					</span></td>
				</tr>
				<tr>
					<td class="width90"><span>剩余数量：</span></td>
					<td>
						<span>
							<c:choose>
								<c:when test="${coupon.coupanCount == -1 }">不限量</c:when>
								<c:otherwise>${coupon.coupanCount }</c:otherwise>
							</c:choose>
						</span>
					</td>
					<td class="width90"><span></span></td>
					<td><span>
					</span></td>
				</tr>
				<tr>
					<td class="width90"><span>目标用户：</span></td>
					<td colspan="3"><span>
						<input type="radio" name="sendType" value="1" id="sendType1" checked="checked" /><label for="sendType1">按等级</label>&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="radio" name="sendType" value="2" id="sendType2" /><label for="sendType2">指定用户</label>&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="radio" name="sendType" value="3" id="sendType3" /><label for="sendType3">按代理</label>&nbsp;&nbsp;&nbsp;&nbsp;
					</span></td>
				</tr>
				<tr>
					<td class="width90"></td>
					<td colspan="3">
							<div id="forLevel" class="forType">
								<c:forEach items="${levels }" var="level">
									<input type="checkbox" name="levelIds" 
									value="${level.levelId }" id="${level.levelId }" />
									<label for="${level.levelId }">${level.levelName }</label>&nbsp;&nbsp;&nbsp;	
								</c:forEach>
							</div>
							<div id="forUsers" class="forType" style="display: none;">
								手机号：<textarea rows="5" cols="50" style="font-size: 13px;" name="userIds" id="userIds" placeholder='多个手机号请用","隔开' ></textarea>
							</div>
							<div id="forAgent" class="forType" style="display: none;">
								代理级别:
									<select id="agentLevel">
										<option value="">请选择</option>
										<option value="1">一级代理</option>
										<option value="2">二级代理</option>
										<option value="3">三级代理</option>
									</select>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								代理：
									<select id="agentId" name="agentId">
										<option value="">请选择</option>
									</select>
									
							</div>
					</td>
				</tr>
				<tr class="textcenter">
					<td colspan="4">
						<input type="hidden" name="id" value="${coupon.id }" />
						<button class="btn1" type="button" onclick="subForm()">确认发放</button>
						<button class="btn2" onclick="cancelVal()">取消</button>
					</td>
				</tr>

			</table>
		</div>
	</form>
	<iframe name='hidden_frame' id="hidden_frame" style='display: none'></iframe>
</body>

<script type="text/javascript">
$('input:radio[name="sendType"]').change(function(){
	var type = $(this).val();
	if(1 == type){
		$('.forType').hide();
		$('#forLevel').show();
	}
	if(2 == type){
		$('.forType').hide();
		$('#forUsers').show();
	}
	if(3 == type){
		$('.forType').hide();
		$('#forAgent').show();
	}
});
$('#agentLevel').change(function(){
	var agentList=eval('${agentListJson}');
	var level = $(this).val();
	$("#agentId").empty();
	$("#agentId").append("<option value=''>请选择</option>");
	for(var i=0;i<agentList.length;i++){
		if(agentList[i].level == level){
			$("#agentId").append("<option value='"+agentList[i].agentId+"'>"+agentList[i].agentName+"</option>");
		}
	}
})

</script>
</html>