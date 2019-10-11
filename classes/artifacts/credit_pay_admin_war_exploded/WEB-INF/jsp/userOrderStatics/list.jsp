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
<title>用户月交易统计报表</title>
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
	from.action='${ctx }/userOrderStatics/exportExcel';
	from.target='hidden_frame';
	from.submit();
	from.action='${ctx }/userOrderStatics/list';
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
				<form action="${ctx}/userOrderStatics/list"  id="searchForm"   name="searchForm" method="post" class="form-inline" role="form">
					<div class="form-group">&nbsp;&nbsp;
						<label class="control-label" for="startDate">交易月份：</label>
						<input  class="Wdate input-sm" style="height: 30px" type="text" id="monthStr"  name="monthStr"  onclick="WdatePicker({dateFmt:'yyyyMM'});"  value="${appUser.monthStr}"  />&nbsp;
					</div>
					<div class="form-group">&nbsp;&nbsp;
						<label class="control-label" for="userId">用户账号：</label>
						<input  class="input-sm" type="text" id="userId"  name="userId" maxlength="15"  value="${appuser.userId}" /> 
					</div>
					<div class="form-group">&nbsp;&nbsp;
						<label class="control-label padding-left" for="certStatus">实名认证：</label>
						<select id="certStatus" name="certStatus" class="input-sm">
	  					   	<option value="">全部</option>
	  					   	<option value="0" ${appuser.certStatus=='0'?'selected="selected"':'' }>未认证</option>
	  					   	<option value="1" ${appuser.certStatus=='1'?'selected="selected"':'' }>已认证</option>
	  					   	<option value="2" ${appuser.certStatus=='2'?'selected="selected"':'' }>认证失败</option>
	  					</select> 
					</div>
					<div class="form-group">
						&nbsp;&nbsp;<button type="button" class="btn btn-primary"  onclick="fastSearch()">查询</button>
						<chrone:isAuth authCode="110000701">
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
						<div class="table-responsive" style="overflow: auto;">
							<table id="sample-table-1"
								class="table table1 table-striped table-bordered table-hover">
								<thead>
									<tr>
										<th>序号</th>
										<th>用户账户</th>
										<th>用户姓名</th>
										<th>注册时间</th>
										<th>认证状态</th>
										<th>当月交易总额</th>
									</tr>
								</thead>

								<tbody>
									<c:forEach items="${list }" varStatus="i" var="l">
										<tr>
											<td>${i.index+1}</td>
											<td>${l.userId}</td>
											<td>${l.accountName}</td>
											<td ><fmt:formatDate value="${l.rowCrtTs}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
											<td>
												<c:if test="${l.certStatus == 0 }">未认证</c:if>
												<c:if test="${l.certStatus == 1 }">已认证</c:if>
												<c:if test="${l.certStatus == 2 }">认证失败</c:if>
											</td>
											<td ><chrone:fen2Yuan amt="${l.sumOrder}"/> </td>
										</tr>
									</c:forEach>
									<tr>
										<td colspan="6" align="left">查询结果：${page.rowTotal }条</td>
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
<form id="pageForm" action="${ctx}/userOrderStatics/list"  method="post" >
	<input type="hidden"  name="start" id="start"></input>
	<input type="hidden"  name="startDate" value="${appUser.startDate}"></input>
	<input type="hidden"  name="userId" value="${appUser.userId}"></input>
	<input type="hidden"  name="monthStr" value="${appUser.monthStr}"></input>
	<input type="hidden"  name="certStatus" value="${appUser.certStatus}"></input>
</form>
<input type="hidden"  id="closeTp"/>
<iframe name='hidden_frame' id="hidden_frame" style='display: none'></iframe>
</body>
</html>