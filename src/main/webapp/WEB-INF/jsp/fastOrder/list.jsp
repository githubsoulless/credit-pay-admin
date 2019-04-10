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
function updateState(orderNo){
	if(!confirm("是否确认将该订单重发代付？")){
		return;
	}
	$.ajax({
        type: "POST",
        dataType:"text",
        async:false,
        beforeSend:function(XHR){showWait();},
        url: "${ctx }/fastOrder/updateState",
        data:"orderNo="+orderNo,
        success: function(msg){
	       	 if(msg=='timeout'){
	       		 window.location.reload(true);
	       		 return;
	       	 }
       	 	hideWait();
             if(msg=='success'){
             	 alert("操作成功！");
             }else{
            	 alert("操作失败,"+msg+"!");
             }
         	document.getElementById("searchForm").submit();
        }
   });
}

function agentpay(){
	var url = "url:${ctx }/fastOrder/toAdd";
	$.dialog({content:url ,
		        title:'手动代付',
		        lock: true,
				background: '#FFF', /* 背景色 默认的遮罩背景色为:#DCE2F1浅蓝护眼色 */
				max: false,
				height:300,
				width:700,
				min: false,
				opacity: 0.5,	/* 透明度 */
				close: function(){
					addClose();
				}
	});
}
function exportExcel(){
	if(!confirm("是否导出记录到Excel?")){
		return;
	}
	var from=document.getElementById("searchForm");
	from.action='${ctx}/fastOrder/exportExcel';
	from.submit();
	from.action='${ctx}/fastOrder/list';
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
				<form action="${ctx}/fastOrder/list"  id="searchForm"   name="searchForm" method="post" class="form-inline" role="form">
					<div class="form-group">&nbsp;&nbsp;
						<label class="control-label" for="orderNo">内部流水号：</label>
						<input  class="input-sm" type="text" id="orderNo"  name="orderNo" maxlength="30"  value="${order.orderNo}" /> 
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
	  					   	<option value="1" ${order.orderTp==1?'selected="selected"':'' }>结算</option>
	  					   	<option value="2" ${order.orderTp==2?'selected="selected"':'' }>服务费</option>
	  					   </select> 
					</div>
					<div class="form-group">&nbsp;&nbsp;
						<label class="control-label padding-left" for="orderTp">订单状态：</label>
						<select id="paySt" name="paySt" class="input-sm">
	  					   	<option value="">全部</option>
	  					   	<option value="0" ${order.paySt==0?'selected="selected"':'' }>待支付</option>
	  					   	<option value="1" ${order.paySt==1?'selected="selected"':'' }>处理中</option>
	  					   	<option value="2" ${order.paySt==2?'selected="selected"':'' }>支付成功</option>
	  					   	<option value="3" ${order.paySt==3?'selected="selected"':'' }>支付失败</option>
	  					   	<option value="4" ${order.paySt==4?'selected="selected"':'' }>已重发</option>
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
						<label class="control-label" for="agentId">代理ID：</label>
						<input  class="input-sm" type="text" id="agentId"  name="agentId" maxlength="20"  value="${order.agentId}" /> 
					</div>
					<div class="form-group">&nbsp;&nbsp;
						<label class="control-label" for="settleOrderNo">关联订单号：</label>
						<input  class="input-sm" type="text" id="settleOrderNo"  name="settleOrderNo" maxlength="40"  value="${order.settleOrderNo}" /> 
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
					<div class="form-group">
						&nbsp;&nbsp;<button type="button" class="btn btn-primary"  onclick="fastSearch()">查询</button>
						
						<chrone:isAuth authCode="120000103">
							&nbsp;&nbsp;<button type="button" class="btn btn-success" onclick="agentpay()">手动代付</button>
						</chrone:isAuth>
						
						<chrone:isAuth authCode="120000104">
							&nbsp;&nbsp;<button type="button" class="btn btn-success" onclick="exportExcel()">导出</button>
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
						<div class="table-responsive" style="overflow: auto;">
							<table id="sample-table-1"
								class="table table1 table-striped table-bordered table-hover">
								<thead>
									<tr>
										<th>序号</th>
										<th>内部流水号</th>
										<th>所属代理</th>
										<th>用户账号</th>
										<th>卡编号</th>
										<th>订单类型</th>
										<th>订单金额</th>
										<th>交易服务费</th>
										<th>通道手续费</th>
										<th>总利润</th>
										<th>用户分润</th>
										<th>代理分润</th>
										<th>平台利润</th>
										<th>卡号</th>
										<th>持卡人姓名</th>
										<th>订单状态</th>
										<th>订单生成时间</th>
										<th>支付时间</th>
										<th>关联订单号</th>
										<th>交易通道</th>
										<th>描述</th>
										<th>操作</th>
									</tr>
								</thead>

								<tbody>
									<c:forEach items="${list }" varStatus="i" var="l">
										<tr>
											<td>${i.index+1}</td>
											<td>${l.orderNo}</td>
											<td>
												<c:if test="${!empty l.agentId}">
												${l.agentName}(${l.agentId})
												</c:if>
											</td>
											<td>${l.userId}</td>
											<td >${l.cardId }</td>
											<td>
												<c:if test="${l.orderTp==0 }">消费</c:if>
												<c:if test="${l.orderTp==1 }">结算</c:if>
												<c:if test="${l.orderTp==2 }">服务费</c:if>
											</td>
											<td ><chrone:fen2Yuan amt="${l.amount}"/> </td>
											<td ><chrone:fen2Yuan amt="${l.fee}"/> </td>
											<td ><chrone:fen2Yuan amt="${l.chnlFee}"/> </td>
											<td ><chrone:fen2Yuan amt="${l.fee-l.chnlFee}"/> </td>
											<td ><chrone:fen2Yuan amt="${l.userProfits}"/> </td>
											<td ><chrone:fen2Yuan amt="${l.agentProfits}"/> </td>
											<td ><chrone:fen2Yuan amt="${l.fee-l.chnlFee-l.userProfits-l.agentProfits}"/> </td>
											<td><chrone:HiddenStr head="4" srcStr="${l.cardNo}" footer="4"/> </td>
											<td >${l.cardName}</td>
											<td >
												<c:if test="${l.paySt==0 }">待支付</c:if>
												<c:if test="${l.paySt==1 }">处理中</c:if>
												<c:if test="${l.paySt==2 }">支付成功</c:if>
												<c:if test="${l.paySt==3 }">支付失败</c:if>
												<c:if test="${l.paySt==4 }">已重发</c:if>
											</td>
											<td><fmt:formatDate value="${l.createTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
											<td><fmt:formatDate value="${l.payTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
											<td>${l.settleOrderNo}</td>
											<td>${l.chnlName}</td>
											<td>${l.memo}</td>
											<td>
												<c:if test="${l.paySt==3 && l.orderTp==1 }">
													<chrone:isAuth authCode="120000101">
														<a href="javascript:updateState('${l.orderNo}')">重发代付</a>
													</chrone:isAuth>
												</c:if> 
											</td>
										</tr>
									</c:forEach>
									<tr>
										<td colspan="3" align="left">查询结果：${page.rowTotal }条</td>
										<td colspan="3" align="right">合计：</td>
										<td><chrone:fen2Yuan amt="${countMap.sumamt }"/></td>
										<td><chrone:fen2Yuan amt="${countMap.sumfee }"/></td>
										<td><chrone:fen2Yuan amt="${countMap.sumChnlfee }"/></td>
										<td><chrone:fen2Yuan amt="${countMap.sumfee-countMap.sumChnlfee }"/></td>
										<td><chrone:fen2Yuan amt="${countMap.sumUserProfits }"/></td>
										<td><chrone:fen2Yuan amt="${countMap.sumAgentProfits }"/></td>
										<td><chrone:fen2Yuan amt="${countMap.sumfee-countMap.sumChnlfee-countMap.sumUserProfits-countMap.sumAgentProfits }"/></td>
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
<form id="pageForm" action="${ctx }/fastOrder/list"  method="post" >
	<input type="hidden"  name="start" id="start"></input>
	<input type="hidden"  name="orderNo" value="${order.orderNo}"></input>
	<input type="hidden"  name="startAmtStr" value="${order.startAmtStr}"></input>
	<input type="hidden"  name="endAmtStr" value="${order.endAmtStr}"></input>
	<input type="hidden"  name="orderTp" value="${order.orderTp}"></input>
	<input type="hidden"  name="paySt" value="${order.paySt}"></input>
	<input type="hidden"  name="startDate" value="${order.startDate}"></input>
	<input type="hidden"  name="endDate" value="${order.endDate}"></input>
	<input type="hidden"  name="cardNo" value="${order.cardNo}"></input>
	<input type="hidden"  name="cardId" value="${order.cardId}"></input>
	<input type="hidden"  name="userId" value="${order.userId}"></input>
	<input type="hidden"  name="chnlId" value="${order.chnlId}"></input>
	<input type="hidden"  name="settleOrderNo" value="${order.settleOrderNo}"></input>
	<input type="hidden"  name="agentId" value="${order.agentId}"></input>
</form>
<input type="hidden"  id="closeTp"/>
</body>
</html>