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
<title>信用卡查询</title>
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
				<form action="${ctx}/card/list"  id="searchForm"   name="searchForm" method="post" class="form-inline" role="form">
					<div class="form-group">&nbsp;&nbsp;
						<label class="control-label" for="cardNo">信用卡号：</label>
						<input  class="input-sm" type="text" id="cardNo"  name="cardNo" value="${card.cardNo}" /> 
					</div>
					<div class="form-group">&nbsp;&nbsp;
						<label class="control-label" for="cardName">持卡人姓名：</label>
						<input  class="input-sm" type="text" id="cardName"  name="cardName" maxlength="15"  value="${card.cardName}" /> 
					</div>
					<div class="form-group">&nbsp;&nbsp;
						<label class="control-label" for="cardName">用户账号：</label>
						<input  class="input-sm" type="text" id="userId"  name="userId" maxlength="15"  value="${card.userId}" /> 
					</div>
					<div class="form-group">&nbsp;&nbsp;
						<label class="control-label" for="cardId">卡编号：</label>
						<input  class="input-sm" type="text" id="cardId"  name="cardId" value="${card.cardId}" /> 
					</div>
					<br>
					<div class="form-group">&nbsp;&nbsp;
						<label class="control-label" for="startDate">添加日期：</label>
						<input  class="Wdate input-sm" style="height: 30px" type="text" id="startDate"  name="startDate"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'endDate\')}'});"  value="${card.startDate}"  />&nbsp;- 
						<input  class="Wdate input-sm" style="height: 30px" type="text" id="endDate"  name="endDate"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'startDate\')}'});"  value="${card.endDate}"  /> 
					</div>
					<div class="form-group">&nbsp;&nbsp;
						<label class="control-label padding-left" for="status">状态：</label>
						<select id="status" name="status" class="input-sm">
	  					   	<option value="">全部</option>
	  					   	<option value="0" ${card.status==0?'selected="selected"':'' }>正常</option>
	  					   	<option value="1" ${card.status==1?'selected="selected"':'' }>已删除</option>
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
										<th>卡编号</th>
										<th>信用卡号</th>
										<th>所属银行</th>
										<th>持卡人姓名</th>
										<th>所属用户账号</th>
										<th>卡添加时间</th>
										<th>卡状态</th>
										<th>账单日</th>
										<th>最后还款日</th>
										<th>计划数量</th>
									</tr>
								</thead>

								<tbody>
									<c:forEach items="${list }" varStatus="i" var="l">
										<tr>
											<td>${i.index+1}</td>
											<td>${l.cardId}</td>
											<td><chrone:HiddenStr head="4" srcStr="${l.cardNo}" footer="4"/> </td>
											<td >${l.bankNm}</td>
											<td >${l.cardName}</td>
											<td >${l.userId}</td>
											<td><fmt:formatDate value="${l.rowCrtTs}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
											<td >
												<c:if test="${l.status==0 }">正常</c:if>
												<c:if test="${l.status==1 }">已删除</c:if>
											</td>
											<td >${l.billDt }</td>
											<td >${l.lastPayDt }</td>
											<td >${l.planCount }</td>
										</tr>
									</c:forEach>
									<tr>
										<td align="left" colspan="11">查询结果${page.rowTotal }条</td>
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
<form id="pageForm" action="${ctx }/card/list"  method="post" >
	<input type="hidden"  name="start" id="start"></input>
	<input type="hidden"  name="cardNo" value="${card.cardNo}"></input>
	<input type="hidden"  name="cardName" value="${card.cardName}"></input>
	<input type="hidden"  name="userId" value="${card.userId}"></input>
	<input type="hidden"  name="status" value="${card.status}"></input>
	<input type="hidden"  name="startDate" value="${card.startDate}"></input>
	<input type="hidden"  name="endDate" value="${card.endDate}"></input>
</form>
<input type="hidden"  id="closeTp"/>
</body>
</html>