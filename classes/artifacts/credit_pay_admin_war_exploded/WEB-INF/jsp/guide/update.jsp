<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="chrone" uri="/tag/chrone-taglib" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
<script type="text/javascript" charset="utf-8" src="${ctx }/static/ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="${ctx }/static/ueditor/ueditor.all.min.js"></script>
<!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
<!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
<script type="text/javascript" charset="utf-8" src="${ctx }/static/ueditor/lang/zh-cn/zh-cn.js"></script>
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
		if ($("#title").val() == "") {
			alert("请输入资讯标题");
			$("#title").focus();
			return;
		}
		if ($("#status").val() == "") {
			alert("请选择激活状态");
			$("#status").focus();
			return;
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
	};
</script>
</head>
<body onload="init()">
	<form action="${ctx }/guide/update" id="subForm" method="post" enctype="multipart/form-data"
	 target="hidden_frame">
		<input type="hidden" name="type" id="type" value="update">
		<div class="begin">
			<table border="" cellspacing="" cellpadding="">
				<tr>
					<td class="width90"><span style="color:red;">*</span><span>资讯标题：</span></td>
					<td><span><input name="title" id="title" value="${guide.title}" maxlength="10" class="ipt"/>
					<input name="guideId" id="guideId" value="${guide.guideId }" type="hidden" class="ipt" />
					</span></td>
				</tr>
				<tr>
					<td class="width90"><span style="color:red;">*</span><span>缩略图：</span></td>
					<td><span><img src="${ctx}/download/guideImg/${guide.guideId }"  height="20px" width="10px">
					<input type="file" name=imgs id="imgs" multiple="multiple"/></span></td>
				</tr>
				<tr>
					<td class="width90"><span style="color:red;">*</span><span>激活状态：</span></td>
					<td><span>
						<select name="status" id="status" >
							<option value="0" <c:if test="${guide.status == 0 }">selected="selected"</c:if>>使用中</option>
							<option value="1" <c:if test="${guide.status == 1 }">selected="selected"</c:if>>禁用</option>
						</select>
					</span></td>
				</tr>
				<tr style="height: 80px;width: 730px">
					<td class="width90"><span style="color:red;">*</span><span>资讯详情：</span></td>
					<td><span>
					<script id="content" name="content" type="text/plain"> <c:out value="${guide.contentStr}" escapeXml="false"/></script>
					<script type="text/javascript" charset="utf-8">UE.getEditor('content');</script> 
					</span></td>
				</tr>
				<tr class="textcenter">
					<td colspan="4">
						<button class="btn1" type="button" onclick="subForm()">确定添加</button>
						<button class="btn2" onclick="cancelVal()">取消</button>
					</td>
				</tr>

			</table>
		</div>
	</form>
	<iframe name='hidden_frame' id="hidden_frame" style='display: none'></iframe>
</body>
</html>