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
<link href="${ctx }/static/css/statisticsTask.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src='<c:url value="/static/js/loading.js"/>' language="javascript"></script>
<link href="${ctx }/static/css/page.css" rel="stylesheet" type="text/css" />
<script type="text/javascript"  src="${ctx }/static/js/My97DatePicker/WdatePicker.js"></script>
<title>计划统计</title>
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

function fastSearch(val){
	$("#flag").val(val);
	document.getElementById("searchForm").submit();
}

function addClose(){
	if(document.getElementById("closeTp").value=="1"){
		document.getElementById("searchForm").submit();
	}
}
function changeDateType(){
	fastSearch('1');
}

function refreshTask(){
	$.ajax({
		type : "GET",
		url : '${ctx}/taskStatistics/realTimeTask',
		dateType : "json",
		async : false,
		contentType : 'application/json',
		success : function(r) {
			$('#countPayTaskD').html(r.countPayTaskD);
			$('#sumPayTaskAmtD').html(r.sumPayTaskAmtD);
			$('#countRePayTaskD').html(r.countRePayTaskD);
			$('#sumRePayTaskAmtD').html(r.sumRePayTaskAmtD);
			$('#countPayTaskT').html(r.countPayTaskT);
			$('#sumPayTaskAmtT').html(r.sumPayTaskAmtT);
			$('#countRePayTaskT').html(r.countRePayTaskT);
			$('#sumRePayTaskAmtT').html(r.sumRePayTaskAmtT);
			$('#countPayTask').html(r.countPayTask);
			$('#sumPayTaskAmt').html(r.sumPayTaskAmt);
			$('#countRePayTask').html(r.countRePayTask);
			$('#sumRePayTaskAmt').html(r.sumRePayTaskAmt);
			$('#realTime').html(r.realTime);
		}
	});
//document.getElementById("searchForm").submit();
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
<body onload="refreshTask()">
	<div class="page-content">
		<div class="row divTask">
			<div id="qq">
				<div class="col-lg-5 col-md-6">
					<table class="table table-bordered text-center tableTasko">
						<thead>
							<tr class="">
								<th class="text-left" colspan="6">昨日任务总览</th>
							</tr>
							
						</thead>
						<tbody>
							<tr>
								<td colspan="6" class=""></td>
							</tr>
							<tr>
								<td colspan="3" class="fontsize20 borderddd">消费</td>
								<td colspan="3" class="fontsize20">还款</td>
							</tr>
							<tr>
								<td>成功</td>
								<td>${yesterdayTask.countPaySuccess }笔</td>
								<td class="borderddd"><chrone:fen2Yuan amt="${yesterdayTask.sumPaySuccessAmt }"></chrone:fen2Yuan> 元</td>
								<td>成功</td>
								<td>${yesterdayTask.countRePaySuccess }笔</td>
								<td><chrone:fen2Yuan amt="${yesterdayTask.sumRePaySuccessAmt }"></chrone:fen2Yuan> 元</td>
							</tr>
							<tr>
								<td>失败</td>
								<td>${yesterdayTask.countPayFaild }笔</td>
								<td class="borderddd"><chrone:fen2Yuan amt="${yesterdayTask.sumRePayFaildAmt }"></chrone:fen2Yuan> 元</td>
								<td>失败</td>
								<td>${yesterdayTask.countRePayFaild }笔</td>
								<td><chrone:fen2Yuan amt="${yesterdayTask.sumRePayFaildAmt }"></chrone:fen2Yuan> 元</td>
							</tr>
							<tr>
								<td>终止</td>
								<td>${yesterdayTask.countPayStop }笔</td>
								<td class="borderddd"><chrone:fen2Yuan amt="${yesterdayTask.sumPayStopAmt }"></chrone:fen2Yuan>元</td>
								<td>终止</td>
								<td>${yesterdayTask.countRePayStop }笔</td>
								<td><chrone:fen2Yuan amt="${yesterdayTask.sumRePayStopAmt }"></chrone:fen2Yuan>元</td>
							</tr>
							<tr>
								<td colspan="6" class="">&nbsp;</td>
							</tr>
						</tbody>
					</table>
				</div>
				<div class="col-lg-5 col-md-6">
					<table class="table table-bordered text-center tableTaskt">
						<thead>
							<tr class="">
								<th class="text-left" colspan="5" style="position: relative;">任务实时统计  
									<span class="glyphicon glyphicon-refresh refreshr" onclick="refreshTask()" style="position: absolute;right: 2%;top: 8px;">刷新</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td colspan="5" class=""></td>
							</tr>
							<tr>
								<td colspan="3" class="fontsize20 borderddd">剩余消费任务</td>
								<td colspan="2" class="fontsize20">剩余还款任务</td>
							</tr>
							<tr>
								<td>今天</td>
								<td><span id="countPayTaskD">${taskRealTimeStatistics.countPayTaskD }</span>笔</td>
								<td class="borderddd"><span id="sumPayTaskAmtD"><chrone:fen2Yuan amt="${taskRealTimeStatistics.sumPayTaskAmtD }"></chrone:fen2Yuan></span>元</td>
								<td><span id="countRePayTaskD">${taskRealTimeStatistics.countRePayTaskD }</span>笔</td>
								<td><span id="sumRePayTaskAmtD"><chrone:fen2Yuan amt="${taskRealTimeStatistics.sumRePayTaskAmtD }"></chrone:fen2Yuan></span>元</td>
							</tr>
							<tr>
								<td>明天</td>
								<td><span id="countPayTaskT">${taskRealTimeStatistics.countPayTaskT }</span>笔</td>
								<td class="borderddd"><span id="sumPayTaskAmtT"><chrone:fen2Yuan amt="${taskRealTimeStatistics.sumPayTaskAmtT }"></chrone:fen2Yuan></span>元</td>
								<td><span id="countRePayTaskT">${taskRealTimeStatistics.countRePayTaskT }</span>笔</td>
								<td><span id="sumRePayTaskAmtT"><chrone:fen2Yuan amt="${taskRealTimeStatistics.sumRePayTaskAmtT }"></chrone:fen2Yuan></span>元</td>
							</tr>
							<tr>
								<td>所有</td>
								<td><span id="countPayTask">${taskRealTimeStatistics.countPayTask }</span>笔</td>
								<td class="borderddd"><span id="sumPayTaskAmt"><chrone:fen2Yuan amt="${taskRealTimeStatistics.sumPayTaskAmt }"></chrone:fen2Yuan></span>元</td>
								<td><span id="countRePayTask">${taskRealTimeStatistics.countRePayTask }</span>笔</td>
								<td><span id="sumRePayTaskAmt"><chrone:fen2Yuan amt="${taskRealTimeStatistics.sumRePayTaskAmt }"></chrone:fen2Yuan></span>元</td>
							</tr>
							<tr>
								<td colspan="5" class="text-left">数据更新于：<span id="realTime">${realTime }</span></td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	
		<div class="page-header">
			<div class="row">
				<form action="${ctx}/taskStatistics/list"  id="searchForm"   name="searchForm" method="post" class="form-inline" role="form">
					<div class="form-group">&nbsp;&nbsp;
						<label class="control-label" for="startDate">自定义任务查询：</label>
					</div>
					<div class="form-group">&nbsp;&nbsp;
						<select id="dateType" name="dateType" class="input-sm" onchange="changeDateType()">
	  					   	<option value="1" ${dateType==1?'selected="selected"':'' }>最近7天</option>
	  					   	<option value="2" ${dateType==2?'selected="selected"':'' }>最近15天</option>
	  					   	<option value="3" ${dateType==3?'selected="selected"':'' }>最近30天</option>
	  					   </select> 
					</div>
					<input type="hidden" value="${flag }" name="flag" id="flag"/>
					<div class="form-group">&nbsp;&nbsp;
						<label class="control-label" for="startDate">任务日期：</label>
						<input  class="Wdate input-sm" style="height: 30px" type="text" id="startDate"  name="startDate"  onclick="WdatePicker({dateFmt:'yyyyMMdd',maxDate:'#F{$dp.$D(\'endDate\')}'});"  value="${payPlanTask.startDate}"  />&nbsp;- 
						<input  class="Wdate input-sm" style="height: 30px" type="text" id="endDate"  name="endDate"  onclick="WdatePicker({dateFmt:'yyyyMMdd',minDate:'#F{$dp.$D(\'startDate\')}'});"  value="${payPlanTask.endDate}"  /> 
					</div>
					
					<div class="form-group">
						&nbsp;&nbsp;<button type="button" class="btn btn-primary"  onclick="fastSearch('2')">查询</button>
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
										<th>日期</th>
										<th>成功消费数量</th>
										<th>成功消费金额</th>
										<th>成功还款笔数</th>
										<th>成功还款金额</th>
										<th>消费失败数量</th>
										<th>消费失败金额</th>
										<th>还款失败笔数</th>
										<th>还款失败金额</th>
										<th>消费终止数量</th>
										<th>消费终止金额</th>
										<th>还款终止笔数</th>
										<th>还款终止金额</th>
									</tr>
								</thead>

								<tbody>
									<c:forEach items="${list }" varStatus="i" var="l">
										<tr>
											<td>${l.plan_dt}</td>
											<td >${l.countPaySuccess}</td>
											<td><chrone:fen2Yuan amt="${l.sumPaySuccessAmt}"></chrone:fen2Yuan></td>
											<td >${l.countRePaySuccess}</td>
											<td ><chrone:fen2Yuan amt="${l.sumRePaySuccessAmt}"></chrone:fen2Yuan></td>
											<td >${l.countPayFaild}</td>
											<td ><chrone:fen2Yuan amt="${l.sumPayFaildAmt}"></chrone:fen2Yuan></td>
											<td >${l.countRePayFaild}</td>
											<td ><chrone:fen2Yuan amt="${l.sumRePayFaildAmt}"></chrone:fen2Yuan></td>
											<td >${l.countPayStop}</td>
											<td ><chrone:fen2Yuan amt="${l.sumPayStopAmt}"></chrone:fen2Yuan></td>
											<td >${l.countRePayStop}</td>
											<td ><chrone:fen2Yuan amt="${l.sumRePayStopAmt}"></chrone:fen2Yuan></td>
										</tr>
									</c:forEach>
									<tr>
										<td>合计：</td>
										<td >${countMap.countPaySuccess}</td>
										<td ><chrone:fen2Yuan amt="${countMap.sumPaySuccessAmt}"></chrone:fen2Yuan></td>
										<td >${countMap.countRePaySuccess}</td>
										<td ><chrone:fen2Yuan amt="${countMap.sumRePaySuccessAmt}"></chrone:fen2Yuan></td>
										<td >${countMap.countPayFaild}</td>
										<td ><chrone:fen2Yuan amt="${countMap.sumPayFaildAmt}"></chrone:fen2Yuan></td>
										<td >${countMap.countRePayFaild}</td>
										<td ><chrone:fen2Yuan amt="${countMap.sumRePayFaildAmt}"></chrone:fen2Yuan></td>
										<td >${countMap.countPayStop}</td>
										<td ><chrone:fen2Yuan amt="${countMap.sumPayStopAmt}"></chrone:fen2Yuan></td>
										<td >${countMap.countRePayStop}</td>
										<td ><chrone:fen2Yuan amt="${countMap.sumRePayStopAmt}"></chrone:fen2Yuan></td>
									</tr>
								</tbody>
							</table>
						</div>
						<!-- /.table-responsive -->
					</div>
					<!-- /span -->
				</div>
				<!-- /row -->
			</div>
		</div>
	</div>
</body>
</html>