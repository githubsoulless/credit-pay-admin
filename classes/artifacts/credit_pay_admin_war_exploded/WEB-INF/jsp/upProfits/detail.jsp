<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="chrone" uri="/tag/chrone-taglib" %>
<%
	request.setAttribute("ctx", request.getContextPath());
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src='<c:url value="/static/js/jquery-1.7.1.min.js"/>'></script>
<script type="text/javascript" src='<c:url value="/static/js/loading.js"/>' language="javascript"></script>
<link href="${ctx }/static/css/bootstrap/bootstrap.min.css" rel="stylesheet" type="text/css" />
<link href="${ctx }/static/css/bootstrap/ace.min.css" rel="stylesheet" type="text/css" />
<link href="${ctx }/static/css/alert.css" rel="stylesheet" type="text/css" />
<title>分润明细</title>
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
</script>
</head>
<body>
<div class="begin">
<table >
				<tr>
					<td width="17%">&nbsp;</td>
					<td width="17%">&nbsp;</td>
					<td width="17%">&nbsp;</td>
					<td width="17%">&nbsp;</td>
					<td width="17%">&nbsp;</td>
					<td width="17%">&nbsp;</td>
				</tr>
				<tr>
					<td align="right">升级订单号：</td>
					<td>${upProfits.orderNo}</td>
					<td class="width90">分润生成时间：</td>
					<td><fmt:formatDate value="${upProfits.rowCrtTs}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<td></td>
					<td></td>
				</tr>
				<tr><td colspan="6">
					<table width="100%" id="sample-table-1" class="table table-striped table-bordered table-hover">
						<tr>
							<td>序号</td>
							<td>分润类型</td>
							<td>受益人账号</td>
							<td>真实姓名</td>
							<td>代理名称</td>
							<td>受益金额</td>
						</tr>
						<c:forEach items="${list }" var="p" varStatus="i">
							<tr>
								<td>${i.index+1 }</td>
								<td>
										<c:if test="${p.profitsNum == 1 }">
											二级推荐人分润
										</c:if>
										<c:if test="${p.profitsNum == 2 }">
											一级推荐人分润
										</c:if>
										<c:if test="${p.profitsNum == 3 }">
											三级代理分润
										</c:if>
										<c:if test="${p.profitsNum == 4}">
											二级代理分润
										</c:if>
										<c:if test="${p.profitsNum == 5 }">
											一级代理分润
										</c:if>
								</td>
								<td>${p.profitsUserId }</td>
								<td>${p.accountName }</td>
								<td>${p.agentName }</td>
								<td><chrone:fen2Yuan amt="${p.amount }"/></td>
							</tr>
						</c:forEach>
					</table>
				</td></tr>
				<tr class="textcenter">
					<td colspan="6">
						<button class="btn2" onclick="cancelVal()">关闭</button>
					</td>
				</tr>

			</table>
		</div>
<iframe name='hidden_frame' id="hidden_frame" style='display: none'></iframe>
</body>
</html>