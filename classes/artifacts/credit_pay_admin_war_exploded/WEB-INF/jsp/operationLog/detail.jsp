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
<title></title>
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
					<td width="80%">&nbsp;</td>
				</tr>
				<tr>
					<td align="left">操作内容：</td>
				</tr>
				<tr>
					<td>
						${operationLog.content }
					</td>
				</tr>
				<tr class="textcenter">
					<td>
						<button class="btn2" onclick="cancelVal()">关闭</button>
					</td>
				</tr>

			</table>
		</div>
<iframe name='hidden_frame' id="hidden_frame" style='display: none'></iframe>
</body>
</html>