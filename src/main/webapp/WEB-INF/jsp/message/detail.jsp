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
<body onload="init()">
	<form action="${ctx }/message/add" id="subForm" method="post"
		target="hidden_frame">
		<input type="hidden" name="type" id="type" value="add">
		<div class="begin">
			<table border="" cellspacing="" style="line-height: 3.0" cellpadding="">
				<tr>
					<td class="width90"><span>消息标题：</span></td>
					<td><span>${message.title }</span></td>
				</tr>
				<tr>
					<td class="width90"><span>创建时间：</span></td>
					<td><span><fmt:formatDate value="${message.creatTime}" pattern="yyyy-MM-dd HH:mm:ss" /></span></td>
				</tr>
				<tr>
					<td class="width90"><span>是否置顶：</span></td>
					<td><span>
						<c:if test="${message.isTop == 0 }">否</c:if>
						<c:if test="${message.isTop == 1 }">是</c:if>
					</span></td>
				</tr>
				<tr>
					<td class="width90">推送范围</td>
					<td><span>
						<c:if test="${message.pushRange == 0 }">全部</c:if>
						<c:if test="${message.pushRange == 1 }">${message.levelName }</c:if>
						<c:if test="${message.pushRange == 2 }">${message.userId }</c:if>
						<c:if test="${message.pushRange == 3 }">${message.agentName }</c:if>
					</span></td>
				</tr>
				<tr>
					<td class="width90"><span>消息内容：</span></td>
					<td>
						<span>
							<textarea rows="7" cols="50" maxlength="500" style="font-size: 13px;" id="content" name="content">${message.content }</textarea>
						</span>
					</td>
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
</html>