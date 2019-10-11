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
<title>用户钱包明细</title>
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
				<form action="${ctx}/accountMgr/accountRealTimeStatistics"  id="searchForm"   name="searchForm" method="post" class="form-inline" role="form">
					<div class="form-group">&nbsp;&nbsp;
						<label class="control-label" for="userId">用户账号：</label>
						<input  class="input-sm" type="text" id="userId"  name="userId" maxlength="20"  value="${account.userId}" /> 
					</div>
					<div class="form-group">&nbsp;&nbsp;
						<label class="control-label" for="orderBy">排序：</label>
						<select id="orderBy" name="orderBy" class="input-sm">
	  					   	<option value="1" ${account.orderBy==1?'selected="selected"':'' }>可提现金额高到低</option>
	  					   	<option value="2" ${account.orderBy==2?'selected="selected"':'' }>待结算金额高到低</option>
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
						<div class="table-responsive" style="overflow: auto;">
							<table id="sample-table-1"
								class="table table-striped table-bordered table-hover">
								<thead>
									<tr>
										<th>序号</th>
										<th>用户账号</th>
										<th>商户名称</th>
										<th>真实姓名</th>
										<th>等级</th>
										<th>待结算金额</th>
										<th>可提现金额</th>
									</tr>
								</thead>

								<tbody>
									<c:forEach items="${list }" varStatus="i" var="l">
										<tr>
											<td>${i.index+1}</td>
											<td>${l.userId}</td>
											<td>${l.merName}</td>
											<td>${l.accountName}</td>
											<td>${l.levelName}</td>
											<td>
												<chrone:fen2Yuan amt="${l.untransfer }"></chrone:fen2Yuan>
											</td>
											<td>
												<chrone:fen2Yuan amt="${l.available }"></chrone:fen2Yuan>
											</td>
									</c:forEach>
									<tr>
										<td colspan="2" align="left">查询结果：${page.rowTotal }条</td>
										<td colspan="3" align="right">合计：</td>
										<td><chrone:fen2Yuan amt="${l.SumUntransfer }"/></td>
										<td><chrone:fen2Yuan amt="${countMap.sumAvailable }"/></td>
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
<form id="pageForm" action="${ctx }/accountMgr/accountRealTimeStatistics"  method="post" >
	<input type="hidden"  name="start" id="start"></input>
	<input type="hidden"  name="userId" value="${account.userId}"></input>
	<input type="hidden"  name="orderBy" value="${account.orderBy}"></input>
</form>
<input type="hidden"  id="closeTp"/>
</body>
</html>