<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="chrone" uri="/tag/chrone-taglib"%>
<%@taglib prefix="page" uri="/tag/mypage-taglib"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
<title>优惠券明细</title>
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
<body>
	<div class="page-content">
		<div class="page-header">
			<div class="row">
				<form action="${ctx}/couponDetail/list"  id="searchForm" name="searchForm" method="post" class="form-inline" role="form">
					<div class="form-group">&nbsp;&nbsp;
						<label class="control-label" for="id">序列号：</label>
						<input  class="input-sm" type="text" id="id" name="id" maxlength="32" value="${couponDetail.id}" /> 
					</div>
					<div class="form-group">&nbsp;&nbsp;
						<label class="control-label" for="couponId">所属优惠券ID：</label>
						<input  class="input-sm" type="text" id="couponId" name="couponId" maxlength="32" value="${couponDetail.couponId}" /> 
					</div>
					<div class="form-group">&nbsp;&nbsp;
						<label class="control-label padding-left" for="status">使用状态：</label>
						<select id="status" name="status" class="input-sm">
	  					   	<option value="">全部</option>
	  					   	<option value="0" ${couponDetail.status==0?'selected="selected"':'' }>未使用</option>
	  					   	<option value="1" ${couponDetail.status==1?'selected="selected"':'' }>已使用</option>
	  					   	<option value="2" ${couponDetail.status==2?'selected="selected"':'' }>已过期</option>
	  					</select> 
					</div>
					<div class="form-group">&nbsp;&nbsp;
						<label class="control-label" for="userId">领取用户账号：</label>
						<input  class="input-sm" type="text" id="userId"  name="userId" maxlength="32"  value="${couponDetail.userId}" /> 
					</div>
					<br>
					<div class="form-group">&nbsp;&nbsp;
						<label class="control-label" for="startDate">领取日期：</label>
						<input  class="Wdate input-sm" style="height: 30px" type="text" id="startDate"  name="startDate"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'endDate\')}'});"  value="${couponDetail.startDate}"  />&nbsp;- 
						<input  class="Wdate input-sm" style="height: 30px" type="text" id="endDate"  name="endDate"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'startDate\')}'});"  value="${couponDetail.endDate}"  /> 
					</div>
					<div class="form-group">&nbsp;&nbsp;
						<label class="control-label" for="orderId">关联订单号：</label>
						<input  class="input-sm" type="text" id="orderId"  name="orderId" maxlength="32"  value="${couponDetail.orderId}" /> 
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
										<th>序列号</th>
										<th>所属优惠券</th>
										<th>面值(元)</th>
										<th>使用状态</th>
										<th>领取用户账号</th>
										<th>领取时间</th>
										<th>生效起始日期</th>
										<th>生效截止日期</th>
										<th>关联订单号</th>
									</tr>
								</thead>

								<tbody>
									<c:forEach items="${list }" varStatus="i" var="l">
										<tr>
											<td>${i.index+1}</td>
											<td>${l.id }</td>
											<td>${l.couponName }</td>
											<td><chrone:fen2Yuan amt="${l.amount }"></chrone:fen2Yuan>元</td>
											<td>
												<c:if test="${l.status ==0 }">未使用</c:if>
												<c:if test="${l.status ==1 }">已使用</c:if>
												<c:if test="${l.status ==2 }">已过期</c:if>
											</td>
											<td>${l.userId }</td>
											<td><fmt:formatDate value="${l.rowCrtTs}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
											<td><fmt:formatDate value="${l.effectStartTime}" pattern="yyyy-MM-dd" /></td>
											<td><fmt:formatDate value="${l.effectEndTime}" pattern="yyyy-MM-dd" /></td>
											<td>${l.orderId }</td>
										</tr>
									</c:forEach>
									<tr>
										<td colspan="2" align="left">查询结果：${page.rowTotal }条</td>
										<td colspan="1" align="right">合计：</td>
										<td><chrone:fen2Yuan amt="${countMap.sumAmount }"/></td>
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
<form id="pageForm" action="${ctx }/couponDetail/list"  method="post" >
	<input type="hidden"  name="start" id="start"></input>
	<input type="hidden"  name="id" value="${couponDetail.id}"></input>
	<input type="hidden"  name="couponId" value="${couponDetail.couponId}"></input>
	<input type="hidden"  name="status" value="${couponDetail.status}"></input>
	<input type="hidden"  name="userId" value="${couponDetail.userId}"></input>
	<input type="hidden"  name="startDate" value="${couponDetail.startDate}"></input>
	<input type="hidden"  name="endDate" value="${couponDetail.endDate}"></input>
	<input type="hidden"  name="orderId" value="${couponDetail.orderId}"></input>
</form>
<input type="hidden"  id="closeTp"/>
<iframe name='hidden_frame' id="hidden_frame" style='display: none'></iframe>
</body>
</html>