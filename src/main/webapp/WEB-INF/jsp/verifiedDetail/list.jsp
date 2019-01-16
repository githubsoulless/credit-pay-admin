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
<title>实名认证</title>
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
				<form action="${ctx}/verifiedDetail/list"  id="searchForm"   name="searchForm" method="post" class="form-inline" role="form">
					<div class="form-group">&nbsp;&nbsp;
						<label class="control-label" for="userId">注册账号：</label>
						<input  class="input-sm" type="text" id="userId"  name="userId" maxlength="20"  value="${verifiedDetail.userId}" /> 
					</div>
					<div class="form-group">&nbsp;&nbsp;
						<label class="control-label" for="startDate">认证日期：</label>
						<input  class="Wdate input-sm" style="height: 30px" type="text" id="startDate"  name="startDate"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'endDate\')}'});"  value="${verifiedDetail.startDate}"  />&nbsp;- 
						<input  class="Wdate input-sm" style="height: 30px" type="text" id="endDate"  name="endDate"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'startDate\')}'});"  value="${verifiedDetail.endDate}"  /> 
					</div>
					<div class="form-group">&nbsp;&nbsp;
						<label class="control-label" for="reqStatus">请求状态：</label>
						<select id="reqStatus" name="reqStatus" class="input-sm">
	  					   	<option value="">全部</option>
	  					   	<option value="0" ${verifiedDetail.reqStatus==0?'selected="selected"':'' }>请求成功</option>
	  					   	<option value="1" ${verifiedDetail.reqStatus==1?'selected="selected"':'' }>请求失败</option>
	  					   </select> 
					</div>
					<div class="form-group">&nbsp;&nbsp;
						<label class="control-label" for="code">通道代码：</label>
						<input  class="input-sm" type="text" id="code"  name="code" maxlength="20"  value="${verifiedDetail.code}" /> 
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
										<th>注册账号</th>
										<th>真实姓名</th>
										<th>身份证号</th>
										<th>卡号</th>
										<th>请求状态</th>
										<th>核查结果</th>
										<th>认证时间</th>
										<th>通道代码</th>
									</tr>
								</thead>

								<tbody>
									<c:forEach items="${list }" varStatus="i" var="l">
										<tr>
											<td>${i.index+1}</td>
											<td>${l.userId}</td>
											<td>${l.accountName}</td>
											<td><chrone:HiddenStr head="6" srcStr="${l.certNo }" footer="4"></chrone:HiddenStr></td>
											<td><chrone:HiddenStr head="4" srcStr="${l.cardNo }" footer="4"></chrone:HiddenStr></td>
											<td>
												<c:if test="${l.reqStatus == 0 }">请求成功</c:if>
												<c:if test="${l.reqStatus == 1 }">请求失败</c:if>
											</td>
											<td>
												<c:if test="${l.verifiedResult == 0 }">核查一致</c:if>
												<c:if test="${l.verifiedResult == 1 }">验证失败</c:if>
											</td>
											<td><fmt:formatDate value="${l.rowCrtTs}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
											<td >${l.code }</td>
										</tr>
									</c:forEach>
									<tr>
										<td colspan="9" align="left">查询结果：${page.rowTotal }</td>
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
<form id="pageForm" action="${ctx }/verifiedDetail/list"  method="post" >
	<input type="hidden"  name="start" id="start"></input>
	<input type="hidden"  name="userId" value="${verifiedDetail.userId}"></input>
	<input type="hidden"  name="reqStatus" value="${verifiedDetail.reqStatus}"></input>
	<input type="hidden"  name="startDate" value="${verifiedDetail.startDate}"></input>
	<input type="hidden"  name="endDate" value="${verifiedDetail.endDate}"></input>
</form>
<input type="hidden"  id="closeTp"/>
</body>
</html>