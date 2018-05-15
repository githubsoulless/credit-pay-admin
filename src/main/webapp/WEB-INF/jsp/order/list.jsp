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
<style type="text/css">
.form-group{
	padding-top: 10px;
}
</style>
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
				<form action="${ctx}/order/list"  id="searchForm"   name="searchForm" method="post" class="form-inline" role="form">
					<div class="form-group">&nbsp;&nbsp;
						<label class="control-label" for="orderNo">内部流水号：</label>
						<input  class="input-sm" type="text" id="orderNo"  name="orderNo"   value="${order.orderNo}" /> 
					</div>
					<div class="form-group">&nbsp;&nbsp;
						<label class="control-label" for="busiOrderNo">业务流水号：</label>
						<input  class="input-sm" type="text" id="busiOrderNo"  name="busiOrderNo" value="${order.busiOrderNo}" /> 
					</div>
					<div class="form-group">&nbsp;&nbsp;
						<label class="control-label" for="planId">所属计划ID：</label>
						<input  class="input-sm" type="text" id="planId"  name="planId" maxlength="20"  value="${order.planId}" /> 
					</div>
					<div class="form-group">&nbsp;&nbsp;
						<label class="control-label" for="planId">所属任务ID：</label>
						<input  class="input-sm" type="text" id="taskId"  name="taskId" maxlength="20"  value="${order.taskId}" /> 
					</div>
					<div class="form-group">&nbsp;&nbsp;
						<label class="control-label" for="startAmtStr">订单金额：</label>
						<input  class="input-sm" type="text" id="startAmtStr" size="6"  name="startAmtStr" maxlength="20"  value="${order.startAmtStr}" />&nbsp;- 
						<input  class="input-sm" type="text" id="endAmtStr"  size="6" name="endAmtStr" maxlength="20"  value="${order.endAmtStr}" />
					</div>
					<div class="form-group">&nbsp;&nbsp;
						<label class="control-label padding-left" for="orderTp">订单类型：</label>
						<select id="orderTp" name="orderTp" class="input-sm">
	  					   	<option value="">全部</option>
	  					   	<option value="0" ${order.orderTp==0?'selected="selected"':'' }>消费</option>
	  					   	<option value="1" ${order.orderTp==1?'selected="selected"':'' }>还款</option>
	  					   	<option value="2" ${order.orderTp==2?'selected="selected"':'' }>提现</option>
	  					   </select> 
					</div>
					<div class="form-group">&nbsp;&nbsp;
						<label class="control-label padding-left" for="orderTp">订单状态：</label>
						<select id="paySt" name="paySt" class="input-sm">
	  					   	<option value="">全部</option>
	  					   	<option value="1" ${order.paySt==1?'selected="selected"':'' }>待支付</option>
	  					   	<option value="2" ${order.paySt==2?'selected="selected"':'' }>支付成功</option>
	  					   	<option value="3" ${order.paySt==3?'selected="selected"':'' }>支付失败</option>
	  					   </select> 
					</div>
					<div class="form-group">&nbsp;&nbsp;
						<label class="control-label" for="startDate">订单生成时间：</label>
						<input  class="Wdate input-sm" style="height: 30px" type="text" id="startDate"  name="startDate"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'endDate\')}'});"  value="${order.startDate}"  />&nbsp;- 
						<input  class="Wdate input-sm" style="height: 30px" type="text" id="endDate"  name="endDate"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'startDate\')}'});"  value="${order.endDate}"  /> 
					</div>
					<div class="form-group">&nbsp;&nbsp;
						<label class="control-label" for="cardId">卡编号：</label>
						<input  class="input-sm" type="text" id="cardId"  name="cardId" maxlength="20"  value="${order.cardId}" /> 
					</div>
					<div class="form-group">&nbsp;&nbsp;
						<label class="control-label" for="cardNo">卡号：</label>
						<input  class="input-sm" type="text" id="cardNo"  name="cardNo" maxlength="20"  value="${order.cardNo}" /> 
					</div>
					<div class="form-group">&nbsp;&nbsp;
						<label class="control-label" for="userId">用户账号：</label>
						<input  class="input-sm" type="text" id="userId"  name="userId" maxlength="20"  value="${order.userId}" /> 
					</div>
					<div class="form-group">&nbsp;&nbsp;
						<label class="control-label" for="chnlOrderNo">通道交易号：</label>
						<input  class="input-sm" type="text" id="chnlOrderNo"  name="chnlOrderNo" maxlength="20"  value="${order.chnlOrderNo}" /> 
					</div>
					<div class="form-group">&nbsp;&nbsp;
						<label class="control-label padding-left" for="orderTp">交易通道：</label>
						<select id="chnlId" name="chnlId" class="input-sm">
	  					   	<option value="">全部</option>
	  					   	<c:forEach items="${channelList }" var="chnl">
	  					   		<option value="${chnl.id }" ${order.chnlId==chnl.id?'selected="selected"':'' }>${chnl.name }</option>
	  					   	</c:forEach>
	  					   </select> 
					</div>
					<div class="form-group">&nbsp;&nbsp;
						<label class="control-label padding-left" for="orderTp">结算方式：</label>
						<select id="settleType" name="settleType" class="input-sm">
	  					   	<option value="">全部</option>
	  					   	<option value="0" ${order.settleType==0?'selected="selected"':'' }>D0</option>
	  					   	<option value="1" ${order.settleType==1?'selected="selected"':'' }>T1</option>
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
										<th>内部流水号</th>
										<th>业务流水号</th>
										<th>所属计划ID</th>
										<th>所属任务ID</th>
										<th>订单类型</th>
										<th>订单金额</th>
										<th>通道手续费</th>
										<th>卡号</th>
										<th>持卡人姓名</th>
										<th>订单状态</th>
										<th>订单生成时间</th>
										<th>支付时间</th>
										<th>通道交易号</th>
										<th>交易通道</th>
										<th>所属用户账号</th>
										<th>卡编号</th>
										<th>备注</th>
									</tr>
								</thead>

								<tbody>
									<c:forEach items="${list }" varStatus="i" var="l">
										<tr>
											<td>${i.index+1}</td>
											<td>${l.orderNo}</td>
											<td>${l.busiOrderNo}</td>
											<td>${l.planId}</td>
											<td>${l.taskId}</td>
											<td>
												<c:if test="${l.orderTp==0 }">消费</c:if>
												<c:if test="${l.orderTp==1 }">还款</c:if>
												<c:if test="${l.orderTp==2 }">提现</c:if>
											</td>
											<td ><chrone:fen2Yuan amt="${l.amount}"/> </td>
											<td ><chrone:fen2Yuan amt="${l.fee}"/> </td>
											<td><chrone:HiddenStr head="4" srcStr="${l.cardNo}" footer="4"/> </td>
											<td >${l.cardName}</td>
											<td >
												<c:if test="${l.paySt==1 }">待支付</c:if>
												<c:if test="${l.paySt==2 }">支付成功</c:if>
												<c:if test="${l.paySt==3 }">支付失败</c:if>
											</td>
											<td><fmt:formatDate value="${l.createTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
											<td><fmt:formatDate value="${l.payTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
											<td>${l.chnlOrderNo}</td>
											<td>${l.chnlName}</td>
											<td>${l.userId}</td>
											<td >${l.cardId }</td>
											<td >${l.remark }</td>
										</tr>
									</c:forEach>
									<tr>
										<td colspan="3" align="left">查询结果：${page.rowTotal }条</td>
										<td colspan="3" align="right">合计：</td>
										<td><chrone:fen2Yuan amt="${countMap.sumamt }"/></td>
										<td><chrone:fen2Yuan amt="${countMap.sumfee }"/></td>
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
<form id="pageForm" action="${ctx }/order/list"  method="post" >
	<input type="hidden"  name="start" id="start"></input>
	<input type="hidden"  name="orderNo" value="${order.orderNo}"></input>
	<input type="hidden"  name="busiOrderNo" value="${order.busiOrderNo}"></input>
	<input type="hidden"  name="planId" value="${order.planId}"></input>
	<input type="hidden"  name="startAmtStr" value="${order.startAmtStr}"></input>
	<input type="hidden"  name="endAmtStr" value="${order.endAmtStr}"></input>
	<input type="hidden"  name="orderTp" value="${order.orderTp}"></input>
	<input type="hidden"  name="paySt" value="${order.paySt}"></input>
	<input type="hidden"  name="startDate" value="${order.startDate}"></input>
	<input type="hidden"  name="endDate" value="${order.endDate}"></input>
	<input type="hidden"  name="cardNo" value="${order.cardNo}"></input>
	<input type="hidden"  name="cardId" value="${order.cardId}"></input>
	<input type="hidden"  name="userId" value="${order.userId}"></input>
	<input type="hidden"  name="chnlOrderNo" value="${order.chnlOrderNo}"></input>
	<input type="hidden"  name="chnlId" value="${order.chnlId}"></input>
	<input type="hidden"  name="taskId" value="${order.taskId}"></input>
</form>
<input type="hidden"  id="closeTp"/>
</body>
</html>