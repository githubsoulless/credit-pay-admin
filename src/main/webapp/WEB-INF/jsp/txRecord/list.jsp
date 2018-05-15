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
<title>提现查询</title>
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
				<form action="${ctx}/txRecord/list"  id="searchForm"   name="searchForm" method="post" class="form-inline" role="form">
					<div class="form-group">&nbsp;&nbsp;
						<label class="control-label" for="userId">用户账号：</label>
						<input  class="input-sm" type="text" id="userId"  name="userId" maxlength="20"  value="${txRecord.userId}" /> 
					</div>
					<div class="form-group">&nbsp;&nbsp;
						<label class="control-label" for="cardName">真实姓名：</label>
						<input  class="input-sm" type="text" id="cardName"  name="cardName" maxlength="20"  value="${txRecord.cardName}" /> 
					</div>
					<div class="form-group">&nbsp;&nbsp;
						<label class="control-label" for="recordId">提现订单号：</label>
						<input  class="input-sm" type="text" id="recordId"  name="recordId" value="${txRecord.recordId}" /> 
					</div>
					<div class="form-group">&nbsp;&nbsp;
						<label class="control-label" for="startDate">提现日期：</label>
						<input  class="Wdate input-sm" style="height: 30px" type="text" id="startDate"  name="startDate"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'endDate\')}'});"  value="${txRecord.startDate}"  />&nbsp;- 
						<input  class="Wdate input-sm" style="height: 30px" type="text" id="endDate"  name="endDate"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'startDate\')}'});"  value="${txRecord.endDate}"  /> 
					</div>
					<br>
					<div class="form-group">&nbsp;&nbsp;
						<label class="control-label" for="status">状态：</label>
						<select id="status" name="status" class="input-sm">
	  					   	<option value="">全部</option>
	  					   	<option value="0" ${txRecord.status==0?'selected="selected"':'' }>待处理</option>
	  					   	<option value="1" ${txRecord.status==1?'selected="selected"':'' }>处理中</option>
	  					   	<option value="2" ${txRecord.status==2?'selected="selected"':'' }>成功</option>
	  					   	<option value="3" ${txRecord.status==3?'selected="selected"':'' }>失败</option>
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
						<div class="table-responsive">
							<table id="sample-table-1"
								class="table table1 table-striped table-bordered table-hover">
								<thead>
									<tr>
										<th>序号</th>
										<th>提现订单号</th>
										<th>用户账号</th>
										<th>真实姓名</th>
										<th>提现金额</th>
										<th>手续费</th>
										<th>到账金额</th>
										<th>提现时间</th>
										<th>状态</th>
										<th>银行卡号</th>
										<th>开户行</th>
									</tr>
								</thead>

								<tbody>
									<c:forEach items="${list }" varStatus="i" var="l">
										<tr>
											<td>${i.index+1}</td>
											<td>${l.recordId}</td>
											<td>${l.userId}</td>
											<td>${l.cardName}</td>
											<td><chrone:fen2Yuan amt="${l.amount }"/></td>
											<td><chrone:fen2Yuan amt="${l.fee }"/></td>
											<td><chrone:fen2Yuan amt="${l.amount-l.fee }"/></td>
											<td><fmt:formatDate value="${l.createTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
											<td>
												<c:if test="${l.status==0 }">待处理</c:if>
												<c:if test="${l.status==1 }">处理中</c:if>
												<c:if test="${l.status==2 }">成功</c:if>
												<c:if test="${l.status==3 }">失败</c:if>
											</td>
											<td >${l.cardNo }</td>
											<td >${l.bankNm }</td>
										</tr>
									</c:forEach>
									<tr>
										<td colspan="2" align="left">查询结果：${page.rowTotal }条</td>
										<td colspan="2" align="right">合计：</td>
										<td><chrone:fen2Yuan amt="${countMap.sumAmount }"/></td>
										<td><chrone:fen2Yuan amt="${countMap.sumFee }"/></td>
										<td><chrone:fen2Yuan amt="${countMap.arrAmt }"/></td>
										<td colspan="4" align="right"></td>
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
<form id="pageForm" action="${ctx }/txRecord/list"  method="post" >
	<input type="hidden"  name="start" id="start"></input>
	<input type="hidden"  name="userId" value="${txRecord.userId}"></input>
	<input type="hidden"  name="cardName" value="${txRecord.cardName}"></input>
	<input type="hidden"  name="recordId" value="${txRecord.recordId}"></input>
	<input type="hidden"  name="status" value="${txRecord.status}"></input>
	<input type="hidden"  name="startDate" value="${txRecord.startDate}"></input>
	<input type="hidden"  name="endDate" value="${txRecord.endDate}"></input>
</form>
<input type="hidden"  id="closeTp"/>
</body>
</html>