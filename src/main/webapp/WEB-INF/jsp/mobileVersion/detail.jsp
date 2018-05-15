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
		if ($("#appVersion").val() == "") {
			alert("请填写版本号");
			$("#appVersion").focus();
			return false;
		}
		if($("input[name='file']").val() == ""){
			alert('请选择需要上传的应用文件');
			return false;
		}
		var location=$("input[name='file']").val(); 
	     var point = location.lastIndexOf("."); 
	     var type = location.substr(point);
	     alert(type);
	     if(type != ".apk"){
	    	alert("应用文件类型错误");
			return false;
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
	<form action="${ctx }/mobileVersion/add" id="subForm" method="post" enctype="multipart/form-data" target="hidden_frame">
		<div class="begin">
			<table border="" cellspacing="" style="line-height: 3.0" cellpadding="">
				<tr>
					<td class="width90"><span>操作系统：</span></td>
					<td><span><c:if test="${mobileVersion.osType =='0' }">Android</c:if></span></td>
					<td class="width90"><span><span style="color: red;">*</span>版本号</span></td>
					<td><span>${mobileVersion.appVersion }</span></td>
				</tr>
				<tr>
					<td class="width90"><span>更新机制：</span></td>
					<td>
						<span>
							<c:if test="${mobileVersion.isUpversion==1 }">强制更新</c:if>
							<c:if test="${mobileVersion.isUpversion==0 }">选择更新</c:if>
						</span>
					</td>
					<td class="width90"><span>状态：</span></td>
					<td><span>
						<c:if test="${mobileVersion.appState==0 }">停用</c:if>
						<c:if test="${mobileVersion.appState==1 }">启用</c:if>
					</span></td>
				</tr>
				<tr>
					<td class="width90"><span>应用文件：</span></td>
					<td><span>${mobileVersion.oldFileName }</span></td>
					<td class="width90"><span>文件大小：</span></td>
					<td><span><fmt:formatNumber value="${mobileVersion.fileSize/1024/1024 }" type="currency" pattern="0.0"/>MB</span></td>
				</tr>
				<tr>
					<td class="width90"><span>版本描述：</span></td>
					<td colspan="3">
						<span>
							<textarea rows="7" cols="50" maxlength="50" readonly="readonly" style="font-size: 13px;" name="versionDesc" >${mobileVersion.versionDesc }</textarea>
						</span>
					</td>
				</tr>
				<tr class="textcenter">
					<td colspan="4">
						<button class="btn2" onclick="cancelVal()">关闭</button>
					</td>
				</tr>

			</table>
		</div>
	</form>
	<iframe name='hidden_frame' id="hidden_frame" style='display: none'></iframe>
</body>
</html>