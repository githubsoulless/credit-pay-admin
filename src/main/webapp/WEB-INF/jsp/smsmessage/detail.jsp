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
<body>
	<form action="${ctx }/smsMessage/detail" id="subForm" method="post"
		target="hidden_frame">
		<div class="begin">
			<table border="" cellspacing="" style="line-height: 3.0" cellpadding="">
				<tr>
					<td class="width90"><span>创建时间：</span></td>
					<td><span>
						<fmt:formatDate value="${smsMessage.creatTime}" pattern="yyyy-MM-dd HH:mm:ss" />
					</span></td>
				</tr>
				<tr>
					<td class="width90"><span>推送范围：</span></td>
					<td><span>
						<c:if test="${smsMessage.pushRange == 1 }">
							<c:forEach items="${smsMessage.levelNames }" var="name">${name }&nbsp;&nbsp;&nbsp;&nbsp;</c:forEach>
						</c:if>
						<c:if test="${smsMessage.pushRange == 2 }">指定用户</c:if>
						<c:if test="${smsMessage.pushRange == 3 }">${smsMessage.agentName }</c:if>
					</span></td>
				</tr>
				<c:if test="${smsMessage.pushRange == 2 }">
				<tr>
					<td class="width90">手机号：</td>
					<td><span>
						<textarea rows="7" cols="50" name="mobileNum" id="mobileNum">${smsMessage.mobileNum }</textarea>
					</span></td>
				</tr>
				</c:if>
				<tr>
					<td class="width90"><span>短信内容：</span></td>
					<td><span>
						<textarea rows="7" cols="50" maxlength="500" style="font-size: 13px;" id="content" name="content">${smsMessage.content }</textarea>
					</span></td>
				</tr>
				<tr class="textcenter">
					<td colspan="2">
						<button class="btn2" onclick="cancelVal()">关闭</button>
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