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
<script type="text/javascript"  src="${ctx }/static/js/My97DatePicker/WdatePicker.js"></script>
<title>代理查询</title>
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

function addClose(){
	if(document.getElementById("closeTp").value=="1"){
		document.getElementById("searchForm").submit();
	}
}
function add(){
	var url = "url:${ctx }/agent/add?type=toAdd&a="+encodeURIComponent(new Date());
	$.dialog({content:url ,
		        title:'添加代理',
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
function update(agentId){
	var url = "url:${ctx }/agent/update?agentId="+agentId+"&type=toAdd&a="+encodeURIComponent(new Date());
	$.dialog({content:url ,
		        title:'修改代理信息',
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
function subAgentList(agentId){
	var url = "url:${ctx }/agent/getSubAgentList?agentId="+agentId+"&a="+encodeURIComponent(new Date());
	$.dialog({content:url ,
		        title:'所有下级代理信息',
		        lock: true,
				background: '#FFF', /* 背景色 默认的遮罩背景色为:#DCE2F1浅蓝护眼色 */
				max: false,
				height:500,
				width:700,
				min: false,
				opacity: 0.5,	/* 透明度 */
				close: function(){
					addClose();
				}
	});
}
function changeAgent(type,val) {
	var agentList=eval('${agentListJson}');
		$("#agentId2").empty();
		$("#agentId2").append("<option value=''>二级代理</option>");
		for(var i=0;i<agentList.length;i++){
			if(agentList[i].parentAgentId==val){
				$("#agentId2").append("<option value='"+agentList[i].agentId+"'>"+agentList[i].agentName+"</option>");
			}
		}

}

function resetPwd(agentId){
	var url = "url:${ctx }/agent/resetPwd?agentId="+agentId+"&type=toAdd&a="+encodeURIComponent(new Date());
	$.dialog({content:url ,
		        title:'重置代理登录密码',
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
</script>
<style type="text/css">
table.table1{
	overflow：auto;
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
				<form action="${ctx}/agent/list"  id="searchForm"   name="searchForm" method="post" class="form-inline" role="form">
					<div class="form-group">&nbsp;&nbsp;
						<label class="control-label" for="agentName">代理名称：</label>
						<input  class="input-sm" type="text" id="agentName"  name="agentName" maxlength="20"  value="${agent.agentName}" placeholder="支持模糊查询" /> 
					</div>
					<div class="form-group">&nbsp;&nbsp;
						<label class="control-label" for="agentId">代理ID：</label>
						<input  class="input-sm" type="text" id="agentId"  name="agentId" maxlength="32"  value="${agent.agentId}" /> 
					</div>
					<div class="form-group">&nbsp;&nbsp;
						<label class="control-label padding-left" for="level">代理级别：</label>
						<select id="level" name="level" class="input-sm">
	  					   	<option value="">全部</option>
	  					   	<option value="1" ${agent.level==1?'selected="selected"':'' }>一级代理</option>
	  					   	<option value="2" ${agent.level==2?'selected="selected"':'' }>二级代理</option>
	  					   	<option value="3" ${agent.level==3?'selected="selected"':'' }>三级代理</option>
	  					   </select> 
					</div>
					<div class="form-group">&nbsp;&nbsp;
						<label class="control-label" for="startDate">创建日期：</label>
						<input  class="Wdate input-sm" style="height: 30px" type="text" id="startDate"  name="startDate"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'endDate\')}'});"  value="${agent.startDate}"  />&nbsp;- 
						<input  class="Wdate input-sm" style="height: 30px" type="text" id="endDate"  name="endDate"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'startDate\')}'});"  value="${agent.endDate}"  /> 
					</div>
					<br/>
					<div class="form-group">&nbsp;&nbsp;
						<label class="control-label" for="cardName">代理绑定账号：</label>
						<input  class="input-sm" type="text" id="userId"  name="userId" maxlength="15"  value="${agent.userId}" /> 
					</div>
					<div class="form-group">
						&nbsp;&nbsp;<button type="button" class="btn btn-primary"  onclick="fastSearch()">查询</button>
						<chrone:isAuth authCode="200000101">
						&nbsp;&nbsp;<button type="button" class="btn btn-success" onclick="add()">添加代理</button>
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
						<div class="table-responsive">
							<table id="sample-table-1"
								class="table table1 table-striped table-bordered table-hover">
								<thead>
									<tr>
										<th>序号</th>
										<th>代理ID</th>
										<th>代理名称</th>
										<th>代理级别</th>
										<th>直属上级代理</th>
										<th>代理绑定用户账号</th>
										<th>用户姓名</th>
										<th>下级代理数量</th>
										<th>创建时间</th>
										<th>最后操作人</th>
										<th>最后修改时间</th>
										<th>操作</th>
									</tr>
								</thead>

								<tbody>
									<c:forEach items="${list }" varStatus="i" var="l">
										<tr>
											<td>${i.index+1}</td>
											<td>${l.agentId}</td>
											<td >${l.agentName}</td>
											<td >
												<c:if test="${l.level==1 }">一级代理</c:if>
												<c:if test="${l.level==2 }">二级代理</c:if>
												<c:if test="${l.level==3 }">三级代理</c:if>
											</td>
											<td >
												<c:forEach items="${agentList }" var="agt">
													<c:if test="${agt.agentId==l.parentAgentId }">
														${agt.agentName }(${agt.agentId})
													</c:if>
												</c:forEach>
												<c:if test="${empty l.parentAgentId }">平台</c:if>
											</td>
											<td >${l.userId}</td>
											<td >${l.accountName}</td>
											<td >
												<chrone:isAuth authCode="200000104">
												<a href="#" onclick="subAgentList('${l.agentId}')">
												</chrone:isAuth>
												${l.subAgentCount }
												<chrone:isAuth authCode="200000104">
												</a>
												</chrone:isAuth>
											</td>
											<td><fmt:formatDate value="${l.rowCrtTs}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
											<td >${l.recUpdUsr }</td>
											<td><fmt:formatDate value="${l.recUpdTs}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
											<td >
												<chrone:isAuth authCode="200000101">
												<a href="#" onclick="update('${l.agentId}')">修改</a>
												</chrone:isAuth>
												<chrone:isAuth authCode="200000103">
												&nbsp;&nbsp;<a href="#" onclick="resetPwd('${l.agentId}')">重置密码</a>
												</chrone:isAuth>
											</td>
										</tr>
									</c:forEach>
									<tr>
										<td colspan="12" align="left">查询结果：${page.rowTotal }条</td>
									</tr>
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
<form id="pageForm" action="${ctx }/agent/list"  method="post" >
	<input type="hidden"  name="start" id="start"></input>
	<input type="hidden"  name="agentId" value="${agent.agentId}"></input>
	<input type="hidden"  name="agentName" value="${agent.agentName}"></input>
	<input type="hidden"  name="level" value="${agent.level}"></input>
	<input type="hidden"  name="userId" value="${agent.userId}"></input>
	<input type="hidden"  name="startDate" value="${agent.startDate}"></input>
	<input type="hidden"  name="endDate" value="${agent.endDate}"></input>
</form>
<input type="hidden"  id="closeTp"/>
<iframe name='hidden_frame' id="hidden_frame" style='display: none'></iframe>
</body>
</html>