<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="chrone" uri="/tag/chrone-taglib" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%
	request.setAttribute("ctx", request.getContextPath());
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="${ctx }/static/css/bootstrap/bootstrap.min.css" rel="stylesheet" type="text/css" />
	<link href="${ctx }/static/css/bootstrap/ace.min.css" rel="stylesheet" type="text/css" />
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
		if ($("#banner").val() == "") {
			alert("请选择文件");
			return false;
		}
		// showWait();
		document.getElementById("subForm").submit();
		// window.location.reload();
	}
	function callBack(result) {
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
	<form action="${ctx }/appContent/upload" id="subForm" method="post" enctype="multipart/form-data" target="hidden_frame">
		<div class="begin">
			<table border="" cellspacing="" style="line-height: 3.0" cellpadding="">
				<tr>
					<td class="width90"><span>选择图片：</span></td>
					<td colspan="3"><span>
						<input type="file" name="banner" id="banner" />
					</span></td>
				</tr>
				<tr>
					<td>添加图片标题：<input type="text" id="bannerTitle" name="bannerTitle"></td>
				</tr>
				<tr>
					<td>添加图片详情地址：<input type="text" id="bannerUrl" name="bannerUrl"></td>
				</tr>
				<tr class="textcenter">
					<td colspan="4">
						<button class="btn1" type="button" onclick="subForm()">上传</button>
						<button class="btn2" onclick="cancelVal()">取消</button>
					</td>
				</tr>

			</table>
		</div>
	</form>
	<iframe name='hidden_frame' id="hidden_frame" style='display: none'></iframe>
	<input type="hidden"  id="closeTp"/>
</body>
</html>