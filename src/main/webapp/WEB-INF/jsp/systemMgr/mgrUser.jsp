<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="chrone" uri="/tag/chrone-taglib" %>
<%@taglib prefix="page" uri="/tag/mypage-taglib" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
<title>Insert title here</title>
<script language="javascript">
(function() {
	var _skin, _lhgcore;
	var _search = window.location.search;
	if (_search) {
		_skin = _search.split('demoSkin=')[1];
	};
	
	document.write('<scr'+'ipt src="${ctx}/static/js/lhgdialog.js?skin=' + (_skin || 'idialog') +'"></sc'+'ript>');
	window._isDemoSkin = !!_skin;
})();

function fastSearch(){
	document.getElementById("searchForm").submit();
}

function update(loginId){
	var url = "url:${ctx }/mgrUser/update?type=toUpdate&loginId="+loginId+"&a="+encodeURIComponent(new Date());
	$.dialog({content:url ,
		        title:'修改用户信息',
		        lock: true,
				background: '#FFF', /* 背景色 默认的遮罩背景色为:#DCE2F1浅蓝护眼色 */
				max: false,
				height:300,
				width:700,
				min: false,
				opacity: 0.5,	/* 透明度 */
				close: function(){
					addClose();
				}
	});
}
function add(){
	var url = "url:${ctx }/mgrUser/add?type=toAdd&a="+encodeURIComponent(new Date());
	$.dialog({content:url ,
		        title:'添加用户',
		        lock: true,
				background: '#FFF', /* 背景色 默认的遮罩背景色为:#DCE2F1浅蓝护眼色 */
				max: false,
				height:300,
				width:700,
				min: false,
				opacity: 0.5,	/* 透明度 */
				close: function(){
					addClose();
				}
	});
}
function addClose(){
	if(document.getElementById("closeTp").value=="1"){
		document.getElementById("searchForm").submit();
	}
}

function freeze(loginId){
	if(!confirm("是否确认将该用户冻结？冻结后用户无法登录系统")){
		return;
	}
	$.ajax({
        type: "POST",
        dataType:"text",
        async:false,
        beforeSend:function(XHR){showWait();},
        url: "${ctx }/mgrUser/freeze",
        data:"loginId="+loginId,
        success: function(msg){
	       	 if(msg=='timeout'){
	       		 window.location.reload(true);
	       		 return;
	       	 }
       	 	hideWait();
             if(msg=='success'){
             	 alert("操作成功！");
             }else{
            	 alert("操作失败,"+msg+"!");
             }
         	document.getElementById("searchForm").submit();
        }
   });
}
function unfreeze(loginId){
	if(!confirm("是否确认将该用户解冻？")){
		return;
	}
	$.ajax({
        type: "POST",
        dataType:"text",
        async:false,
        beforeSend:function(XHR){showWait();},
        url: "${ctx }/mgrUser/unfreeze",
        data:"loginId="+loginId,
        success: function(msg){
	       	 if(msg=='timeout'){
	       		 window.location.reload(true);
	       		 return;
	       	 }
       	 	hideWait();
             if(msg=='success'){
             	 alert("操作成功！");
             }else{
            	 alert("操作失败,"+msg+"!");
             }
         	document.getElementById("searchForm").submit();
        }
   });
}
function disable(loginId){
	if(!confirm("是否确认将该用户永久停用？\n停用后的用户禁止登录系统，且无法恢复正常")){
		return;
	}
	$.ajax({
        type: "POST",
        dataType:"text",
        async:false,
        beforeSend:function(XHR){showWait();},
        url: "${ctx }/mgrUser/disable",
        data:"loginId="+loginId,
        success: function(msg){
	       	 if(msg=='timeout'){
	       		 window.location.reload(true);
	       		 return;
	       	 }
       	 	hideWait();
             if(msg=='success'){
             	 alert("操作成功！");
             }else{
            	 alert("操作失败,"+msg+"!");
             }
         	document.getElementById("searchForm").submit();
        }
   });
}
</script>
<style type="text/css">
table.table1{
	overflow：auto;
	text-align: center;
}
table.table1 tr td{
	white-space:nowrap;
}
table.table1 tr th{
	white-space:nowrap;
}
</style>
</head>
<body>
	<div class="page-content">
		<div class="page-header">
			<div class="row">
				<form action="${ctx}/mgrUser/list"  id="searchForm"   name="searchForm" method="post" class="form-inline" role="form">
					<div class="form-group">&nbsp;&nbsp;
						<label class="control-label" for="loginId">账户登录名：</label>
						<input  class="input-sm" type="text" id="loginId"  name="loginId" maxlength="15"  value="${mgrUser.loginId}"  placeholder="登录名"  /> 
					</div>
					<div class="form-group">&nbsp;&nbsp;
						<label class="control-label" for="userName">用户姓名：</label>
						<input  class="input-sm" type="text" id="userName"  name="userName" maxlength="15"  value="${mgrUser.userName}"  placeholder="用户姓名"  /> 
					</div>
					<div class="form-group">&nbsp;&nbsp;
						<label class="control-label padding-left" for="userSt">用户状态：</label>
						<select id="userSt" name="userSt" class="input-sm">
	  					   	<option value="">全部</option>
	  					   	<option value="1" ${mgrUser.userSt=='1'?'selected="selected"':'' }>正常</option>
	  					   	<option value="2" ${mgrUser.userSt=='2'?'selected="selected"':'' }>冻结</option>
	  					   	<option value="3" ${mgrUser.userSt=='3'?'selected="selected"':'' }>停用</option>
	  					   </select> 
					</div>
					<div class="form-group">
						&nbsp;&nbsp;<button type="button" class="btn btn-primary"  onclick="fastSearch()">查询</button>
						<chrone:isAuth authCode="700000101">
						&nbsp;&nbsp;<button type="button" class="btn btn-success" onclick="add()">添加用户</button>
						</chrone:isAuth>
					</div>
				</form>
			</div>
		</div>
		<!-- /.page-header -->

		<div class="row">
			<div class="col-xs-12">
				<!-- PAGE CONTENT BEGINS -->

				<div class="row">
					<div class="col-xs-12">
						<div class="table-responsive" style="overflow: auto;">
							<table id="sample-table-1"
								class="table table1 table-striped table-bordered table-hover">
								<thead>
									<tr>
										<th>序号</th>
										<th>账户登录名</th>
										<th>用户姓名</th>
										<th>所属角色</th>
										<th>手机号</th>
										<th>邮箱</th>
										<th>状态</th>
										<th>最近修改时间</th>
										<th>最近操作人</th>
										<th>创建时间</th>
										<th>最后登录时间</th>
										<th>最后登录IP</th>
										<th>操作</th>
									</tr>
								</thead>

								<tbody>
									<c:forEach items="${list }" varStatus="i" var="l">
										<tr>
											<td>${i.index+1}</td>
											<td>${l.loginId}</td>
											<td >${l.userName}</td>
											<td >${l.roleNm}</td>
											<td >${l.mobileNo }</td>
											<td>${l.email}</td>
											<td >
												<c:if test="${l.userSt==\"1\"}">正常</c:if> 
												<c:if test="${l.userSt==\"2\"}">冻结</c:if> 
												<c:if test="${l.userSt==\"3\"}">停用</c:if>
											</td>
											<td ><fmt:formatDate value="${l.recUpdTs}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
											<td >${l.recUpdUsr }</td>
											<td ><fmt:formatDate value="${l.rowCrtTs}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
											<td ><fmt:formatDate value="${l.lastLoginTs}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
											<td >${l.lastLoginIp }</td>
											<td>
												<c:if test="${l.userSt==\"1\"}">
													<chrone:isAuth authCode="700000102">
														<a href="javascript:update('${ l.loginId}')">修改</a>&nbsp;&nbsp;
	                            						</chrone:isAuth>
													<chrone:isAuth authCode="700000103">
														<a href="javascript:freeze('${ l.loginId}')">冻结</a>&nbsp;&nbsp;
	                            					</chrone:isAuth>
													<chrone:isAuth authCode="700000104">
														<a href="javascript:disable('${ l.loginId}')">永久停用</a>&nbsp;&nbsp;
	                            					</chrone:isAuth>
												</c:if> 
												<c:if test="${l.userSt==\"2\"}">
													<chrone:isAuth authCode="700000103">
														<a href="javascript:unfreeze('${ l.loginId}')">解冻</a>&nbsp;&nbsp;
	                            		</chrone:isAuth>
													<chrone:isAuth authCode="700000104">
														<a href="javascript:disable('${ l.loginId}')">永久停用</a>&nbsp;&nbsp;
	                            		</chrone:isAuth>
												</c:if> <c:if test="${l.userSt==\"3\"}">
	                            		---
	                            	</c:if></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
											<page:page name="page"  formId="pageForm"></page:page>
						</div>
						<!-- /.table-responsive -->
					</div>
					<!-- /span -->
				</div>
				<!-- /row -->
			</div>
		</div>
	</div>
<form id="pageForm" action="${ctx }/mgrUser/list"  method="post" >
	<input type="hidden"  name="start" id="start"></input>
	<input type="hidden"  name="loginId"  value="${mgrUser.loginId}"></input>
	<input type="hidden"  name="userName"  value="${mgrUser.userName}"></input>
	<input type="hidden"  name="userSt"  value="${mgrUser.userSt}"></input>
</form>
<input type="hidden"  id="closeTp"/>
</body>
</html>