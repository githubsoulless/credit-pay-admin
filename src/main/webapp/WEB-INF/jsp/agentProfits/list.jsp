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
<title>代理分润明细</title>
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
</script>
</head>
<body>
	<div class="page-content">
		<div class="page-header">
			<div class="row">
				<form action="${ctx}/agentProfits/list"  id="searchForm"   name="searchForm" method="post" class="form-inline" role="form">
					<div class="form-group">&nbsp;&nbsp;
						<label class="control-label" for="agentName">代理商名称：</label>
						<input  class="input-sm" type="text" id="agentName" name="agentName" maxlength="20"  value="${agentProfits.agentName}" /> 
					</div>
					<div class="form-group">&nbsp;&nbsp;
						<label class="control-label" for="agentId">代理商ID：</label>
						<input  class="input-sm" type="text" id="agentId"  name="agentId" maxlength="20"  value="${agentProfits.agentId}" /> 
					</div>
					<div class="form-group">&nbsp;&nbsp;
						<label class="control-label" for="profitsType">分润类型：</label>
						<select class="input-sm" name="profitsType" id="profitsType">
							<option value="">全部</option>
							<option value="1" <c:if test="${agentProfits.profitsType == 1 }" >selected="selected"</c:if>>还款分润</option>
							<option value="2" <c:if test="${agentProfits.profitsType == 2 }" >selected="selected"</c:if>>用户升级分润</option>
							<option value="3" <c:if test="${agentProfits.profitsType == 3 }" >selected="selected"</c:if>>快捷分润</option>
						</select>
					</div>
					<div class="form-group">&nbsp;&nbsp;
						<label class="control-label" for="orderNo">关联订单号：</label>
						<input  class="input-sm" type="text" id="orderNo"  name="orderNo" value="${agentProfits.orderNo}" /> 
					</div>
					<div class="form-group">&nbsp;&nbsp;
						<label class="control-label" for="userId">任务流水号：</label>
						<input  class="input-sm" type="text" id="taskId"  name="taskId" value="${agentProfits.taskId}" /> 
					</div>
					<div class="form-group">&nbsp;&nbsp;
						<label class="control-label" for="userId">受益人账号：</label>
						<input  class="input-sm" type="text" id="userId"  name="userId" value="${agentProfits.userId}" /> 
					</div>
					<br>
					<div class="form-group">&nbsp;&nbsp;
						<label class="control-label" for="level">代理级别：</label>
						<select class="input-sm" name="level" id="level">
							<option value="">全部级别</option>
							<option value="1" <c:if test="${agentProfits.level == 1 }" >selected="selected"</c:if>>一级代理</option>
							<option value="2" <c:if test="${agentProfits.level == 2 }" >selected="selected"</c:if>>二级代理</option>
							<option value="3" <c:if test="${agentProfits.level == 3 }" >selected="selected"</c:if>>三级代理</option>
						</select>
					</div>
					<div class="form-group">&nbsp;&nbsp;
						<label class="control-label" for="startDate">分润生成日期：</label>
						<input  class="Wdate input-sm" style="height: 30px" type="text" id="startDate"  name="startDate"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'endDate\')}'});"  value="${agentProfits.startDate}"  />&nbsp;- 
						<input  class="Wdate input-sm" style="height: 30px" type="text" id="endDate"  name="endDate"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'startDate\')}'});"  value="${agentProfits.endDate}"  /> 
					</div>
					<div class="form-group">
						&nbsp;&nbsp;<button type="button" class="btn btn-primary"  onclick="fastSearch()">查询</button>
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
								class="table table-striped table-bordered table-hover">
								<thead>
									<tr>
										<th>序号</th>
										<th>代理ID</th>
										<th>代理名称</th>
										<th>代理级别</th>
										<th>分润类型</th>
										<th>分润金额</th>
										<th>关联订单号</th>
										<th>任务流水号</th>
										<th>交易用户账号</th>
										<th>交易用户姓名</th>
										<th>订单金额</th>
										<th>受益人账号</th>
										<th>真实姓名</th>
										<th>分润生成时间</th>
									</tr>
								</thead> 

								<tbody>
									<c:forEach items="${list }" varStatus="i" var="l">
										<tr>
											<td>${i.index+1}</td>
											<td>${l.agentId}</td>
											<td>${l.agentName}</td>
											<td>
												<c:if test="${l.level ==1 }">一级代理</c:if>
												<c:if test="${l.level ==2 }">二级代理</c:if>
												<c:if test="${l.level ==3 }">三级代理</c:if>
											</td>
											<td>
												<c:if test="${l.profitsType == 1 }">还款分润</c:if>
												<c:if test="${l.profitsType == 2 }">用户升级分润</c:if>
												<c:if test="${l.profitsType == 3 }">快捷分润</c:if>
											</td>
											<td><chrone:fen2Yuan amt="${l.profitsAmount }"/></td>
											<td>${l.orderNo }</td>
											<td>${l.taskId }</td>
											<td>${l.srcUserId }</td>
											<td>${l.srcAccountName }</td>
											<td><chrone:fen2Yuan amt="${l.amount }"/></td>
											<td>${l.userId }</td>
											<td>${l.accountName }</td>
											<td><fmt:formatDate value="${l.rowCrtTs}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
										</tr>
									</c:forEach>
									<tr>
										<td colspan="2" align="right">查询结果${page.rowTotal }条</td>
										<td colspan="3" align="right">合计：</td>
										<td><chrone:fen2Yuan amt="${countMap.assumProfitsAmount }"/></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td><chrone:fen2Yuan amt="${countMap.sumAmount }"/></td>
										<td colspan="3" align="right"></td>
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
<form id="pageForm" action="${ctx }/agentProfits/list"  method="post" >
	<input type="hidden"  name="start" id="start"></input>
	<input type="hidden"  name="agentId" value="${agentProfits.agentId}"></input>
	<input type="hidden"  name="userId" value="${agentProfits.userId}"></input>
	<input type="hidden"  name="accountName" value="${agentProfits.accountName}"></input>
	<input type="hidden"  name="profitsType" value="${agentProfits.profitsType}"></input>
	<input type="hidden"  name="orderNo" value="${agentProfits.orderNo}"></input>
	<input type="hidden"  name="level" value="${agentProfits.level}"></input>
	<input type="hidden"  name="startDate" value="${agentProfits.startDate}"></input>
	<input type="hidden"  name="endDate" value="${agentProfits.endDate}"></input>
	<input type="hidden"  name="taskId" value="${agentProfits.taskId}"></input>
</form>
<input type="hidden"  id="closeTp"/>
</body>
</html>