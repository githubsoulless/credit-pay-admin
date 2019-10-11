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
<title>用户查询</title>
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

function exportExcel(){
	if(!confirm("是否导出记录到Excel?")){
		return;
	}
	var from=document.getElementById("searchForm");
	from.action='${ctx}/appUser/exportExcel';
	from.submit();
	from.action='${ctx}/appUser/list';
}
</script>
</head>
<body>
	<div class="page-content">
		<div class="page-header">
			<div class="row">
				<form action="${ctx}/cardvaluate/list"  id="searchForm"   name="searchForm" method="post" class="form-inline" role="form">
					<div class="form-group">&nbsp;&nbsp;
						<label class="control-label padding-left">&nbsp;&nbsp;&nbsp;&nbsp;处理状态：</label>
						<select id="status" name="status" class="input-sm">
	  					   		<option value="">全部</option>
 					   			<option value="true" <c:if test="${cardValuate.status==true}">selected="selected"</c:if>>成功</option>
 					   			<option value="false" <c:if test="${cardValuate.status==false}">selected="selected"</c:if>>失败</option>
	  					</select> 
					</div>
					
					<div class="form-group">&nbsp;&nbsp;
						<label class="control-label">用户ID：</label>
						<input  class="input-sm" type="text" id="userId" size="16"  name="userId" maxlength="20"  value="${cardValuate.userId}" />&nbsp;- 
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
								class="table1 table table-striped table-bordered table-hover">
								<thead>
									<tr>
										<th>序号</th>
										<th>测评用户</th>
										<th>卡号</th>
										<th>身份证号</th>
										<th>姓名</th>
										<th>手机号</th>
										<th>状态</th>
										<th>支付金额</th>
										<th>支付方式</th>
										<th>快捷订单ID</th>
										<th>支付状态</th>
										<th>测评时间</th>
										<th>备注</th>
										<th>测评结果</th>
									</tr>
								</thead>

								<tbody>
									<c:forEach items="${list }" varStatus="i" var="l">
										<tr>
											<td>${i.index+1}</td>
											<td>${l.userId}</td>
											<td>${l.cardNo}</td>
											<td >${l.certId}</td>
											<td >${l.name}</td>
											<td >${l.mobile}</td>
											<td >
												<c:if test="${l.status==true}">成功</c:if>
												<c:if test="${l.status==false}">失败</c:if>
											</td>
											<td >
												<chrone:fen2Yuan amt="${l.money}"/>
											</td>
											<td>
												<c:if test="${l.payType==0}">钱包</c:if>
												<c:if test="${l.payType==1}">快捷</c:if>
											</td>
											<td>
												${l.orderNo}
											</td>
											<td>
												<c:if test="${l.paySt==2}">成功</c:if>
												<c:if test="${l.paySt!=2}">失败</c:if>
											</td>
											<td>
												<fmt:formatDate value="${l.createTime}" pattern="yyyy-MM-dd HH:mm:ss" />
											</td>
											<td>${l.memo}</td>
											<td>${l.url}</td>
										</tr>
									</c:forEach>
									<tr>
										<td colspan="17" align="left">查询结果：${page.rowTotal }</td>
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
<form id="pageForm" action="${ctx }/cardvaluate/list"  method="post" >
	<input type="hidden"  name="start" id="start"></input>
	<input type="hidden"  name="status" value="${cardValuate.status}"></input>
</form>
<input type="hidden"  id="closeTp"/>
</body>
</html>