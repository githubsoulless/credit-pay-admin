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
<link href="${ctx }/static/css/bootstrap/bootstrap.min.css" rel="stylesheet" type="text/css" />
<link href="${ctx }/static/css/bootstrap/ace.min.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src='<c:url value="/static/js/loading.js"/>' language="javascript"></script>
<link href="${ctx }/static/css/page.css" rel="stylesheet" type="text/css" />
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
		<div class="begin">
			<table border="" cellspacing="" style="line-height: 3.0" cellpadding="">
				<tr>
					<td class="width90"><span>消息标题：</span></td>
					<td colspan="3"><span>${message.title }</span></td>
				</tr>
				<tr>
					<td class="width90"><span>创建时间：</span></td>
					<td><span><fmt:formatDate value="${message.creatTime}" pattern="yyyy-MM-dd HH:mm:ss" /></span></td>
					<td class="width90"><span>推送范围：</span></td>
					<td><span>
						<c:if test="${message.pushRange == 0 }">全部</c:if>
						<c:if test="${message.pushRange == 1 }">${message.levelName }</c:if>
						<c:if test="${message.pushRange == 2 }">${message.userId }</c:if>
						<c:if test="${message.pushRange == 3 }">${message.agentName }</c:if>
					</span></td>
				</tr>
				<tr>
					<td colspan="4"></td>
				</tr>
				<tr>
					<td class="width90">阅读统计</td>
					<td>
						<span>
							<input type="radio" name="type" value="1" id="1" <c:if test="${type ==1 }">checked="checked"</c:if>/><label for="1">总数(${totalRow })</label>	
							<input type="radio" name="type" value="2" id="2" <c:if test="${type ==2 }">checked="checked"</c:if>/><label for="2">未读用户数(${notReadCount})</label>
						</span>
					</td>
				</tr>
				<tr>
					<td class="width90"><span></span></td>
					<td colspan="3">
						<table id="sample-table-1" class="table table1 table-striped table-bordered table-hover">
							<thead>
								<tr>
									<th>序号</th>
									<th>用户账号</th>
									<c:if test="${type == 1 }">
									<th>阅读时间</th>
									</c:if>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${list }" varStatus="i" var="l">
									<tr>
										<td>${i.index+1}</td>
										<td>${l.userId }</td>
										<c:if test="${type == 1 }">
										<td><fmt:formatDate value="${l.readTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
										</c:if>
									</tr>
								</c:forEach>
								<tr>
									<c:if test="${type == 1 }">
										<td colspan="3" align="left">查询结果：${page.rowTotal }条</td>
									</c:if>
									<c:if test="${type == 2 }">
										<td colspan="2" align="left">查询结果：${page.rowTotal }条</td>
									</c:if>
								</tr>
							</tbody>
						</table>
						<page:page name="page"  formId="pageForm"></page:page>
					</td>
				</tr>
				<tr class="textcenter">
					<td colspan="2">
						<button class="btn2" onclick="cancelVal()">关闭</button>
					</td>
				</tr>

			</table>
		</div>
	
<form id="pageForm" action="${ctx }/message/userlist"  method="post" >
	<input type="hidden"  name="start" id="start"></input>
	<input type="hidden"  name="messageId" value="${message.id}"></input>
	<input type="hidden"  name="type" value="${type}"></input>
</form>

<input type="hidden"  id="closeTp"/>
<iframe name='hidden_frame' id="hidden_frame" style='display: none'></iframe>
</body>
<script type="text/javascript">
$('input:radio[name="type"]').change(function(){
	var type = $(this).val();
	$('#start').val(0);
	if(1 == type){
		$('input[name="type"]').val("1");
	}
	if(2 == type){
		$('input[name="type"]').val("2");
	}
	$('#pageForm').submit();
});
</script>
</html>