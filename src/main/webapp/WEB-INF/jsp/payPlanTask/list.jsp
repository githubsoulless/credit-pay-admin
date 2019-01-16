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
				<form action="${ctx}/payPlanTask/list"  id="searchForm"   name="searchForm" method="post" class="form-inline" role="form">
					<div class="form-group">&nbsp;&nbsp;
						<label class="control-label" for="taskId">任务流水号：</label>
						<input  class="input-sm" type="text" id="taskId"  name="taskId" maxlength="20"  value="${payPlanTask.taskId}" /> 
					</div>
					<div class="form-group">&nbsp;&nbsp;
						<label class="control-label padding-left" for="type">任务类型：</label>
						<select id="type" name="type" class="input-sm">
	  					   	<option value="">全部</option>
	  					   	<option value="0" ${payPlanTask.type==0?'selected="selected"':'' }>消费</option>
	  					   	<option value="1" ${payPlanTask.type==1?'selected="selected"':'' }>还款</option>
	  					   </select> 
					</div>
					<div class="form-group">&nbsp;&nbsp;
						<label class="control-label padding-left" for="type">任务状态：</label>
						<select id="status" name="status" class="input-sm">
	  					   	<option value="">全部</option>
	  					   	<option value="0" ${payPlanTask.status==0?'selected="selected"':'' }>进行中</option>
	  					   	<option value="1" ${payPlanTask.status==1?'selected="selected"':'' }>执行失败</option>
	  					   	<option value="2" ${payPlanTask.status==2?'selected="selected"':'' }>已完成</option>
	  					   	<option value="3" ${payPlanTask.status==3?'selected="selected"':'' }>已终止</option>
	  					   </select> 
					</div>
					<div class="form-group">&nbsp;&nbsp;
						<label class="control-label" for="startDate">预计执行日期：</label>
						<input  class="Wdate input-sm" style="height: 30px" type="text" id="startDate"  name="startDate"  onclick="WdatePicker({dateFmt:'yyyyMMdd',maxDate:'#F{$dp.$D(\'endDate\')}'});"  value="${payPlanTask.startDate}"  />&nbsp;- 
						<input  class="Wdate input-sm" style="height: 30px" type="text" id="endDate"  name="endDate"  onclick="WdatePicker({dateFmt:'yyyyMMdd',minDate:'#F{$dp.$D(\'startDate\')}'});"  value="${payPlanTask.endDate}"  /> 
					</div>
					<div class="form-group">&nbsp;&nbsp;
						<label class="control-label" for="cardId">卡编号：</label>
						<input  class="input-sm" type="text" id="cardId"  name="cardId" maxlength="20"  value="${payPlanTask.cardId}" /> 
					</div>
					<div class="form-group">&nbsp;&nbsp;
						<label class="control-label" for="planId">还款计划单ID：</label>
						<input  class="input-sm" type="text" id="planId"  name="planId" maxlength="20"  value="${payPlanTask.planId}" /> 
					</div>
					<div class="form-group">&nbsp;&nbsp;
						<label class="control-label" for="cardNo">信用卡号：</label>
						<input  class="input-sm" type="text" id="cardNo"  name="cardNo" maxlength="20"  value="${payPlanTask.cardNo}" /> 
					</div>
					<div class="form-group">&nbsp;&nbsp;
						<label class="control-label" for="cardName">持卡人姓名：</label>
						<input  class="input-sm" type="text" id="cardName"  name="cardName" maxlength="20"  value="${payPlanTask.cardName}" /> 
					</div>
					<div class="form-group">&nbsp;&nbsp;
						<label class="control-label" for="userId">用户账号：</label>
						<input  class="input-sm" type="text" id="userId"  name="userId" maxlength="20"  value="${payPlanTask.userId}" /> 
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
										<th>任务流水号</th>
										<th>任务类型</th>
										<th>所属计划单ID</th>
										<th>卡编号</th>
										<th>交易金额</th>
										<th>预计执行日期</th>
										<th>任务状态</th>
										<th>消费行业</th>
										<!-- <th>消费城市</th> -->
										<th>交易时间</th>
										<th>信用卡号</th>
										<th>所属银行</th>
										<th>持卡人姓名</th>
										<th>所属用户账号</th>
										<th>备注</th>
									</tr>
								</thead>

								<tbody>
									<c:forEach items="${list }" varStatus="i" var="l">
										<tr>
											<td>${i.index+1}</td>
											<td>${l.taskId}</td>
											<td>
												<c:if test="${l.type==0 }">消费</c:if>
												<c:if test="${l.type==1 }">还款</c:if>
											</td>
											<td>${l.planId}</td>
											<td >${l.cardId}</td>
											<td ><chrone:fen2Yuan amt="${l.amount}"/> </td>
											<td>${l.planDt}</td>
											<td >
												<c:if test="${l.status==0 }">进行中</c:if>
												<c:if test="${l.status==1 }">执行失败</c:if>
												<c:if test="${l.status==2 }">已完成</c:if>
												<c:if test="${l.status==3 }">已终止</c:if>
											</td>
											<td>
												<c:if test="${l.industry=='01' }">超市百货</c:if>
												<c:if test="${l.industry=='02' }">餐饮</c:if>
												<c:if test="${l.industry=='03' }">娱乐</c:if>
												<c:if test="${l.industry=='04' }">零售</c:if>
												<c:if test="${l.industry=='05' }">快餐</c:if>
												<c:if test="${l.industry=='99' }">其他</c:if>
											</td>
											<!-- <td>${l.city}</td> -->
											<td><fmt:formatDate value="${l.finshTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
											<td><chrone:HiddenStr head="4" srcStr="${l.cardNo}" footer="4"/> </td>
											<td>${l.bankName}</td>
											<td>${l.cardName}</td>
											<td>${l.userId}</td>
											<td >${l.remark }</td>
										</tr>
									</c:forEach>
									<tr>
										<td colspan="2" align="left">查询结果：${page.rowTotal }条</td>
										<td colspan="3" align="right">合计：</td>
										<td><chrone:fen2Yuan amt="${countMap.sumPlanAmt }"/></td>
										<td colspan="10" align="right"></td>
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
<form id="pageForm" action="${ctx }/payPlanTask/list"  method="post" >
	<input type="hidden"  name="start" id="start"></input>
	<input type="hidden"  name="planId" value="${payPlanTask.planId}"></input>
	<input type="hidden"  name="taskId" value="${payPlanTask.taskId}"></input>
	<input type="hidden"  name="type" value="${payPlanTask.type}"></input>
	<input type="hidden"  name="status" value="${payPlanTask.status}"></input>
	<input type="hidden"  name="cardId" value="${payPlanTask.cardId}"></input>
	<input type="hidden"  name="cardNo" value="${payPlanTask.cardNo}"></input>
	<input type="hidden"  name="cardName" value="${payPlanTask.cardName}"></input>
	<input type="hidden"  name="userId" value="${payPlanTask.userId}"></input>
	<input type="hidden"  name="startDate" value="${payPlanTask.startDate}"></input>
	<input type="hidden"  name="endDate" value="${payPlanTask.endDate}"></input>
</form>
<input type="hidden"  id="closeTp"/>
</body>
</html>