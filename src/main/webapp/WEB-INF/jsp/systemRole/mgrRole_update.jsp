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
<script type="text/javascript" src="${ctx}/static/js/tree/jquery.ztree.core-3.5.min.js" language="javascript"></script>
<script type="text/javascript" src="${ctx}/static/js/tree/jquery.ztree.excheck-3.5.min.js" language="javascript"></script>
<link href="${ctx }/static/css/alert.css" rel="stylesheet" type="text/css" />
<link href="${ctx }/static/css/zTreeStyle/zTreeStyle.css" rel="stylesheet" type="text/css" />
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
	var setting = {
			check: {
				enable: true,
				nocheckInherit: true
			},
			data: {
				simpleData: {
					enable: true
				}
			}
		};
	setting.check.chkboxType = { "Y" : "ps", "N" : "s" };
		var zNodes =[<c:forEach items='${menuList}' var='m'>
		{ id:${m.menuId}, pId:${m.menuParent}, name:"${m.menuNm}",
			<c:forEach items='${roleMenuList}' var='rm'><c:if test='${ rm.menuId==m.menuId}'>checked:true,</c:if></c:forEach>
			open:true},
		</c:forEach>];

		$(document).ready(function(){
			$.fn.zTree.init($("#treeDemo"), setting, zNodes);
			$("#nocheckTrue").bind("click", {nocheck: true}, nocheckNode);
			$("#nocheckFalse").bind("click", {nocheck: false}, nocheckNode);
		});
	function subForm(){
		var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
		var nodes = treeObj.getCheckedNodes(true);
		var menuIds="";
		for(var i=0;i<nodes.length;i++){
			menuIds+=nodes[i].id+"|";
		}
		if(menuIds==""){
			alert("请最少选择一个权限");
			return;
		}
		$("#menuIds").val(menuIds);
		W.showWait();
		document.getElementById("subForm").submit();
	}
	function callBack(result) {
		W.hideWait();
		if(result=="success"){
			alert("操作成功!");
			D.getElementById('closeTp').value = "1";
			api.close();
		}else{
			alert("操作失败,"+result+"!");
		}
	}
	function init(){
		var result="${message}";
		if(result!=""){
			parent.callBack(result);
		}
	}
</script>
</head>
<body onload="init()">
	<form action="${ctx }/mgrRole/update" id="subForm" method="post" target="hidden_frame">
		<input type="hidden" name="type" value="update">
		<input type="hidden" name="menuIds" id="menuIds" >
		<input type="hidden" name="roleId" value="${roleInf.roleId }" >
		<div class="begin">
			<table border="" cellspacing="" cellpadding="">
				<tr>
					<td class="width90"><span>角色名称：</span></td>
					<td><span>${roleInf.roleNm}</span></td>
				</tr>
				<tr>
					<td class="width90"><span>角色权限：</span></td>
					<td><div class="zTreeDemoBackground left">
								<ul id="treeDemo" class="ztree"></ul>
							</div>
					</td>
				</tr>
				<tr class="textcenter">
					<td colspan="4">
						<button class="btn1" type="button" onclick="subForm()">保存修改</button>
						<button class="btn2" onclick="cancelVal()">取消</button>
					</td>
				</tr>

			</table>
		</div>
	</form>
	<iframe name='hidden_frame' id="hidden_frame" style='display: none'></iframe>
</body>
</html>