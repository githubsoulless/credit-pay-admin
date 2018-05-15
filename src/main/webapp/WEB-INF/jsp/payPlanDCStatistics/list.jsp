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
<title>计划收支报表</title>
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
function exportExcel(){
	if(!confirm("是否导出全部记录到Excel?")){
		return;
	}
	var from=document.getElementById("searchForm");
	from.action='${ctx }/payPlanDCStatistics/exportExcel';
	from.target='hidden_frame';
	from.submit();
	from.action='${ctx }/payPlanDCStatistics/list';
	from.target='';
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
				<form action="${ctx}/payPlanDCStatistics/list"  id="searchForm"   name="searchForm" method="post" class="form-inline" role="form">
					<div class="form-group">&nbsp;&nbsp;
						<label class="control-label" for="plan_id">还款计划单ID：</label>
						<input  class="input-sm" type="text" id="plan_id"  name="plan_id" maxlength="20"  value="${dcStatisticsDTO.plan_id}" /> 
					</div>
					<div class="form-group">&nbsp;&nbsp;
						<label class="control-label" for="startDate">创建日期：</label>
						<input  class="Wdate input-sm" style="height: 30px" type="text" id="startDate"  name="startDate"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'endDate\')}'});"  value="${dcStatisticsDTO.startDate}"  />&nbsp;- 
						<input  class="Wdate input-sm" style="height: 30px" type="text" id="endDate"  name="endDate"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'startDate\')}'});"  value="${dcStatisticsDTO.endDate}"  /> 
					</div>
					<div class="form-group">&nbsp;&nbsp;
						<label class="control-label" for="status">计划状态：</label>
						<select id="status" name="status" class="input-sm" >
							<option value="">全部</option>
							<option value='0' ${dcStatisticsDTO.status==0?'selected="selected"':'' }>进行中</option>
							<option value='1' ${dcStatisticsDTO.status==1?'selected="selected"':'' }>执行失败</option>
							<option value='2' ${dcStatisticsDTO.status==2?'selected="selected"':'' }>已完成</option>
							<option value='3' ${dcStatisticsDTO.status==3?'selected="selected"':'' }>已终止</option>
	  					 </select> 	
					</div>
					<div class="form-group">
						&nbsp;&nbsp;<button type="button" class="btn btn-primary"  onclick="fastSearch()">查询</button>
						<chrone:isAuth authCode="110000401">
						&nbsp;&nbsp;<button type="button" class="btn btn-success" onclick="exportExcel()">导出数据</button>
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
										<th>还款计划单ID</th>
										<th>计划状态</th>
										<th>计划创建时间</th>
										<th>计划总金额</th>
										<th>服务费</th>
										<th>代付费</th>
										<th>优惠金额</th>
										<th>用户分润</th>
										<th>代理分润</th>
										<th>服务费退款</th>
										<th>代付费退款</th>
										<th>服务费成本</th>
										<th>代付费成本</th>
										<th>计划收益</th>
									</tr>
								</thead>

								<tbody>
									<c:forEach items="${list }" varStatus="i" var="l">
										<tr>
											<td>${i.index+1}</td>
											<td>${l.plan_id}</td>
											<td >
												<c:if test="${l.status==0 }">进行中</c:if>
												<c:if test="${l.status==1 }">执行失败</c:if>
												<c:if test="${l.status==2 }">已完成</c:if>
												<c:if test="${l.status==3 }">已终止</c:if>
											</td>
											<td ><fmt:formatDate value="${l.row_crt_ts}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
											<td ><chrone:fen2Yuan amt="${l.plan_amt}"/> </td>
											<td ><chrone:fen2Yuan amt="${l.fee}"/> </td>
											<td ><chrone:fen2Yuan amt="${l.df_fee}"/> </td>
											<td ><chrone:fen2Yuan amt="${l.fee-l.pay_amt}"/> </td>
											<td ><chrone:fen2Yuan amt="${l.user_profits}"/> </td>
											<td ><chrone:fen2Yuan amt="${l.agent_profits}"/> </td>
											<td ><chrone:fen2Yuan amt="${l.refund_fee}"/> </td>
											<td ><chrone:fen2Yuan amt="${l.refund_dffee}"/> </td>
											<td ><chrone:fen2Yuan amt="${l.xf_chnl_amt}"/> </td>
											<td ><chrone:fen2Yuan amt="${l.df_chnl_amt}"/> </td>
											<td ><chrone:fen2Yuan amt="${l.df_fee+l.pay_amt-l.user_profits-l.agent_profits-l.refund_fee-l.refund_dffee-l.xf_chnl_amt-l.df_chnl_amt}"/> 
											</td>
										</tr>
									</c:forEach>
									<tr>
										<td colspan="3" align="left">查询结果：${page.rowTotal }条</td>
										<td align="right">合计：</td>
										<td ><chrone:fen2Yuan amt="${countMap.sum_plan_amt}"/> </td>
										<td ><chrone:fen2Yuan amt="${countMap.sum_fee}"/> </td>
										<td ><chrone:fen2Yuan amt="${countMap.sum_df_fee}"/> </td>
										<td ><chrone:fen2Yuan amt="${countMap.sum_fee-countMap.sum_pay_amt}"/> </td>
										<td ><chrone:fen2Yuan amt="${countMap.sum_user_profits}"/> </td>
										<td ><chrone:fen2Yuan amt="${countMap.sum_agent_profits}"/> </td>
										<td ><chrone:fen2Yuan amt="${countMap.sum_refund_fee}"/> </td>
										<td ><chrone:fen2Yuan amt="${countMap.sum_refund_dffee}"/> </td>
										<td ><chrone:fen2Yuan amt="${countMap.sum_xf_chnl_amt}"/> </td>
										<td ><chrone:fen2Yuan amt="${countMap.sum_df_chnl_amt}"/> </td>
										<td ><chrone:fen2Yuan amt="${countMap.sum_df_fee+countMap.sum_pay_amt-countMap.sum_user_profits-countMap.sum_agent_profits-countMap.sum_refund_fee-l.sum_refund_dffee-countMap.sum_xf_chnl_amt-countMap.sum_df_chnl_amt}"/> </td>
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
<form id="pageForm" action="${ctx }/payPlanDCStatistics/list"  method="post" >
	<input type="hidden"  name="start" id="start"></input>
	<input type="hidden"  name="plan_id" value="${dcStatisticsDTO.plan_id}"></input>
	<input type="hidden"  name="status" value="${dcStatisticsDTO.status}"></input>
	<input type="hidden"  name="startDate" value="${dcStatisticsDTO.startDate}"></input>
	<input type="hidden"  name="endDate" value="${dcStatisticsDTO.endDate}"></input>
</form>
<input type="hidden"  id="closeTp"/>
<iframe name='hidden_frame' id="hidden_frame" style='display: none'></iframe>
</body>
</html>