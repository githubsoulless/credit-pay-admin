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
<title>还款计划查询</title>
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
function detail(planId){
	var url = "url:${ctx}/payPlan/detail?planId="+planId+"&a="+encodeURIComponent(new Date());
	$.dialog({content:url ,
		        title:'计划详情',
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
				<form action="${ctx}/payPlan/list"  id="searchForm"   name="searchForm" method="post" class="form-inline" role="form">
					<div class="form-group">&nbsp;&nbsp;
						<label class="control-label" for="planId">还款计划单ID：</label>
						<input  class="input-sm" type="text" id="planId"  name="planId" maxlength="20"  value="${payPlan.planId}" /> 
					</div>
					<div class="form-group">&nbsp;&nbsp;
						<label class="control-label" for="cardNo">信用卡号：</label>
						<input  class="input-sm" type="text" id="cardNo"  name="cardNo" maxlength="20"  value="${payPlan.cardNo}" /> 
					</div>
					<div class="form-group">&nbsp;&nbsp;
						<label class="control-label" for="cardName">持卡人姓名：</label>
						<input  class="input-sm" type="text" id="cardName"  name="cardName" maxlength="20"  value="${payPlan.cardName}" /> 
					</div>
					<div class="form-group">&nbsp;&nbsp;
						<label class="control-label" for="userId">用户账号：</label>
						<input  class="input-sm" type="text" id="userId"  name="userId" maxlength="20"  value="${payPlan.userId}" /> 
					</div>
					<div class="form-group">&nbsp;&nbsp;
						<label class="control-label" for="cardId">卡编号：</label>
						<input  class="input-sm" type="text" id="cardId"  name="cardId" maxlength="20"  value="${payPlan.cardId}" /> 
					</div>
					<div class="form-group">&nbsp;&nbsp;
						<label class="control-label" for="startDate">计划创建日期：</label>
						<input  class="Wdate input-sm" style="height: 30px" type="text" id="startDate"  name="startDate"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'endDate\')}'});"  value="${payPlan.startDate}"  />&nbsp;- 
						<input  class="Wdate input-sm" style="height: 30px" type="text" id="endDate"  name="endDate"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'startDate\')}'});"  value="${payPlan.endDate}"  /> 
					</div>
					<div class="form-group">&nbsp;&nbsp;
						<label class="control-label padding-left" for="status">计划状态：</label>
						<select id="status" name="status" class="input-sm">
	  					   	<option value="">全部</option>
	  					   	<option value="0" ${payPlan.status==0?'selected="selected"':'' }>进行中</option>
	  					   	<option value="1" ${payPlan.status==1?'selected="selected"':'' }>执行失败</option>
	  					   	<option value="2" ${payPlan.status==2?'selected="selected"':'' }>已完成</option>
	  					   	<option value="3" ${payPlan.status==3?'selected="selected"':'' }>已终止</option>
	  					   	<option value="4" ${payPlan.status==4?'selected="selected"':'' }>待处理</option>
	  					   </select> 
					</div>
					<div class="form-group">&nbsp;&nbsp;
						<label class="control-label" for="cardId">备注：</label>
						<input  class="input-sm" type="text" placeholder="支持模糊查询" id="remark"  name="remark" maxlength="20"  value="${payPlan.remark}" /> 
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
										<th>还款计划单ID</th>
										<th>信用卡号</th>
										<th>所属银行</th>
										<th>持卡人姓名</th>
										<th>所属用户账号</th>
										<th>卡编号</th>
										<th>计划总金额</th>
										<th>服务费</th>
										<th>代付费</th>
										<th>优惠金额</th>
										<th>已使用优惠金额</th>
										<th>任务天数</th>
										<th>计划状态</th>
										<th>计划创建时间</th>
										<th>已消费/预计笔数</th>
										<th>已还款/预计笔数</th>
										<th>备注</th>
									</tr>
								</thead>

								<tbody>
									<c:forEach items="${list }" varStatus="i" var="l">
										<tr>
											<td>${i.index+1}</td>
											<td>
												<chrone:isAuth authCode="300000101">
													<a href="#" onclick="detail('${l.planId}')">
												</chrone:isAuth>
													${l.planId}
												<chrone:isAuth authCode="300000101">
													</a>
												</chrone:isAuth>
											</td>
											<td ><chrone:HiddenStr head="4" srcStr="${l.cardNo}" footer="4"/> </td>
											<td >${l.bankName}</td>
											<td >${l.cardName}</td>
											<td >${l.userId}</td>
											<td >${l.cardId}</td>
											<td ><chrone:fen2Yuan amt="${l.planAmt}"/></td>
											<td ><chrone:fen2Yuan amt="${l.fee}"/> </td>
											<td ><chrone:fen2Yuan amt="${l.dfFee}"/> </td>
											<td ><chrone:fen2Yuan amt="${l.fee-l.payAmt}"/> </td>
											<td >
<%-- 												<fmt:formatNumber var="tempAmt" value="${(l.fee-l.payAmt)*(l.successXfCount/l.xfNum)}" pattern="#"/> --%>
												<chrone:fen2Yuan amt="${l.usePreAmt}"/>
											<td >${l.planDtNum}</td>
											<td >
												<c:if test="${l.status==0 }">进行中</c:if>
												<c:if test="${l.status==1 }">执行失败</c:if>
												<c:if test="${l.status==2 }">已完成</c:if>
												<c:if test="${l.status==3 }">已终止</c:if>
												<c:if test="${l.status==4 }">待处理</c:if>
											</td>
											<td><fmt:formatDate value="${l.rowCrtTs}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
											<td >${l.successXfCount}/${l.xfNum }</td>
											<td >${l.successPayCount}/${l.payNum }</td>
											<td >${l.remark }</td>
										</tr>
									</c:forEach>
									<tr>
										<td colspan="2" align="left">查询结果：${page.rowTotal }条</td>
										<td colspan="5" align="right">合计：</td>
										<td><chrone:fen2Yuan amt="${countMap.sumPlanAmt }"/></td>
										<td><chrone:fen2Yuan amt="${countMap.sumFee }"/></td>
										<td><chrone:fen2Yuan amt="${countMap.sumDfFee }"/></td>
										<td><chrone:fen2Yuan amt="${countMap.sumPayAmt }"/></td>
										<td><chrone:fen2Yuan amt="${countMap.sumUsePreAmt }"/></td>
										<td colspan="6" align="right"></td>
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
<form id="pageForm" action="${ctx }/payPlan/list"  method="post" >
	<input type="hidden"  name="start" id="start"></input>
	<input type="hidden"  name="planId" value="${payPlan.planId}"></input>
	<input type="hidden"  name="cardNo" value="${payPlan.cardNo}"></input>
	<input type="hidden"  name="cardName" value="${payPlan.cardName}"></input>
	<input type="hidden"  name="cardId" value="${payPlan.cardId}"></input>
	<input type="hidden"  name="userId" value="${payPlan.userId}"></input>
	<input type="hidden"  name="status" value="${payPlan.status}"></input>
	<input type="hidden"  name="startDate" value="${payPlan.startDate}"></input>
	<input type="hidden"  name="endDate" value="${payPlan.endDate}"></input>
	<input type="hidden"  name="remark" value="${payPlan.remark}"></input>
</form>
<input type="hidden"  id="closeTp"/>
</body>
</html>