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
<title>还款分润明细</title>
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
function detail(profitsId){
	var url = "url:${ctx}/payProfits/detail?profitsId="+profitsId+"&a="+encodeURIComponent(new Date());
	$.dialog({content:url ,
		        title:'分润明细',
		        lock: true,
				background: '#FFF', /* 背景色 默认的遮罩背景色为:#DCE2F1浅蓝护眼色 */
				max: false,
				height:500,
				width:1000,
				min: false,
				opacity: 0.5,	/* 透明度 */
				close: function(){
					addClose();
				}
	});
}
</script>
</head>
<body>
	<div class="page-content">
		<div class="page-header">
			<div class="row">
				<form action="${ctx}/payProfits/list"  id="searchForm"   name="searchForm" method="post" class="form-inline" role="form">
					<div class="form-group">&nbsp;&nbsp;
						<label class="control-label" for="userId">用户账号：</label>
						<input  class="input-sm" type="text" id="userId"  name="userId" maxlength="20"  value="${payProfits.userId}" /> 
					</div>
					<div class="form-group">&nbsp;&nbsp;
						<label class="control-label" for="accountName">真实姓名：</label>
						<input  class="input-sm" type="text" id="accountName"  name="accountName" maxlength="20"  value="${payProfits.accountName}" /> 
					</div>
					<div class="form-group">&nbsp;&nbsp;
						<label class="control-label" for="planId">还款计划ID：</label>
						<input  class="input-sm" type="text" id="planId"  name="planId" value="${payProfits.planId}" /> 
					</div>
					<div class="form-group">&nbsp;&nbsp;
						<label class="control-label" for="taskId">还款任务ID：</label>
						<input  class="input-sm" type="text" id="taskId"  name="taskId" value="${payProfits.taskId}" /> 
					</div>
					<div class="form-group">&nbsp;&nbsp;
						<label class="control-label" for="startDate">分润生成日期：</label>
						<input  class="Wdate input-sm" style="height: 30px" type="text" id="startDate"  name="startDate"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'endDate\')}'});"  value="${payProfits.startDate}"  />&nbsp;- 
						<input  class="Wdate input-sm" style="height: 30px" type="text" id="endDate"  name="endDate"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'startDate\')}'});"  value="${payProfits.endDate}"  /> 
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
										<th>用户账号</th>
										<th>真实姓名</th>
										<th>还款计划ID</th>
										<th>还款任务ID</th>
										<th>订单金额</th>
										<th>服务费</th>
										<th>用户分润</th>
										<th>代理分润</th>
										<th>分润生成时间</th>
										<th>操作</th>
									</tr>
								</thead>

								<tbody>
									<c:forEach items="${list }" varStatus="i" var="l">
										<tr>
											<td>${i.index+1}</td>
											<td>${l.userId}</td>
											<td>${l.accountName}</td>
											<td>${l.planId}</td>
											<td>${l.taskId}</td>
											<td><chrone:fen2Yuan amt="${l.planAmt }"/></td>
											<td><chrone:fen2Yuan amt="${l.fee }"/></td>
											<td><chrone:fen2Yuan amt="${l.userProfits }"/></td>
											<td><chrone:fen2Yuan amt="${l.agentProfits }"/></td>
											<td><fmt:formatDate value="${l.rowCrtTs}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
											<td>
												<chrone:isAuth authCode="500000201">
				                            		<a href="javascript:detail('${ l.profitsId}')">分润明细</a>&nbsp;&nbsp;
				                            	</chrone:isAuth>
											</td>
										</tr>
									</c:forEach>
									<tr>
										<td colspan="3" align="left">查询结果：${page.rowTotal }条</td>
										<td colspan="2" align="right">合计：</td>
										<td><chrone:fen2Yuan amt="${countMap.sumPlanAmt }"/></td>
										<td><chrone:fen2Yuan amt="${countMap.sumFee }"/></td>
										<td><chrone:fen2Yuan amt="${countMap.sumUserProfits }"/></td>
										<td><chrone:fen2Yuan amt="${countMap.sumAgentProfits }"/></td>
										<td colspan="2" align="right"></td>
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
<form id="pageForm" action="${ctx }/payProfits/list"  method="post" >
	<input type="hidden"  name="start" id="start"></input>
	<input type="hidden"  name="userId" value="${payProfits.userId}"></input>
	<input type="hidden"  name="accountName" value="${payProfits.accountName}"></input>
	<input type="hidden"  name="planId" value="${payProfits.planId}"></input>
	<input type="hidden"  name="taskId" value="${payProfits.taskId}"></input>
	<input type="hidden"  name="startDate" value="${payProfits.startDate}"></input>
	<input type="hidden"  name="endDate" value="${payProfits.endDate}"></input>
</form>
<input type="hidden"  id="closeTp"/>
</body>
</html>