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
<title>升级查询</title>
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
				<form action="${ctx}/levelOrder/list"  id="searchForm"   name="searchForm" method="post" class="form-inline" role="form">
					<div class="form-group">&nbsp;&nbsp;
						<label class="control-label" for="cardName">用户账号：</label>
						<input  class="input-sm" type="text" id="userId"  name="userId" maxlength="15"  value="${levelOrder.userId}" /> 
					</div>
					<div class="form-group">&nbsp;&nbsp;
						<label class="control-label" for="userName">真实姓名：</label>
						<input  class="input-sm" type="text" id="userName"  name="userName" maxlength="20"  value="${levelOrder.userName}" /> 
					</div>
					<div class="form-group">&nbsp;&nbsp;
						<label class="control-label" for="orderNo">升级订单号：</label>
						<input  class="input-sm" type="text" id="orderNo"  name="orderNo" value="${levelOrder.orderNo}" /> 
					</div>
					<div class="form-group">&nbsp;&nbsp;
						<label class="control-label" for="startDate">订单生成日期：</label>
						<input  class="Wdate input-sm" style="height: 30px" type="text" id="startDate"  name="startDate"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'endDate\')}'});"  value="${levelOrder.startDate}"  />&nbsp;- 
						<input  class="Wdate input-sm" style="height: 30px" type="text" id="endDate"  name="endDate"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'startDate\')}'});"  value="${levelOrder.endDate}"  /> 
					</div>
					<br>
					<div class="form-group">&nbsp;&nbsp;
						<label class="control-label padding-left" for="paySt">订单状态：</label>
						<select id="paySt" name="paySt" class="input-sm">
	  					   	<option value="">全部</option>
	  					   	<option value="1" ${levelOrder.paySt==1?'selected="selected"':'' }>待支付</option>
	  					   	<option value="2" ${levelOrder.paySt==2?'selected="selected"':'' }>支付成功</option>
	  					   	<option value="3" ${levelOrder.paySt==3?'selected="selected"':'' }>已关闭</option>
	  					   </select> 
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
								class="table table1 table-striped table-bordered table-hover">
								<thead>
									<tr>
										<th>序号</th>
										<th>升级订单号</th>
										<th>用户账号</th>
										<th>真实姓名</th>
										<th>订单金额</th>
										<th>升级前等级</th>
										<th>升级后等级</th>
										<th>订单生成时间</th>
										<th>订单状态</th>
										<th>支付时间</th>
									</tr>
								</thead>

								<tbody>
									<c:forEach items="${list }" varStatus="i" var="l">
										<tr>
											<td>${i.index+1}</td>
											<td>${l.orderNo}</td>
											<td >${l.userId}</td>
											<td >${l.userName}</td>
											<td ><chrone:fen2Yuan amt="${l.amount }"/></td>
											<td >
												<c:forEach items="${levelList }" var="lvl">
													<c:if test="${lvl.levelId== l.theLevel}">
														${lvl.levelName}
													</c:if>
												</c:forEach>
											</td>
											<td >
												<c:forEach items="${levelList }" var="lvl">
													<c:if test="${lvl.levelId== l.endLevel}">
														${lvl.levelName}
													</c:if>
												</c:forEach>
											</td>
											<td><fmt:formatDate value="${l.rowCrtTs}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
											<td >
												<c:if test="${l.paySt==1 }">待支付</c:if>
												<c:if test="${l.paySt==2 }">支付成功</c:if>
												<c:if test="${l.paySt==3 }">已关闭</c:if>
											</td>
											<td><fmt:formatDate value="${l.payTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
										</tr>
									</c:forEach>
									<tr>
										<td colspan="2" align="left">查询结果：${page.rowTotal }条</td>
										<td colspan="2" align="right">合计：</td>
										<td><chrone:fen2Yuan amt="${countMap.sumAmount }"/></td>
										<td colspan="5" align="right"></td>
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
<form id="pageForm" action="${ctx }/levelOrder/list"  method="post" >
	<input type="hidden"  name="start" id="start"></input>
	<input type="hidden"  name="userId" value="${levelOrder.userId}"></input>
	<input type="hidden"  name="userName" value="${levelOrder.userName}"></input>
	<input type="hidden"  name="orderNo" value="${levelOrder.orderNo}"></input>
	<input type="hidden"  name="status" value="${levelOrder.paySt}"></input>
	<input type="hidden"  name="startDate" value="${levelOrder.startDate}"></input>
	<input type="hidden"  name="endDate" value="${levelOrder.endDate}"></input>
</form>
<input type="hidden"  id="closeTp"/>
</body>
</html>