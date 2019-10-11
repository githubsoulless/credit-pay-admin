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
<title>中奖查询</title>
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
				<form action="${ctx}/lotteryDetail/list"  id="searchForm" name="searchForm" method="post" class="form-inline" role="form">
					<div class="form-group">&nbsp;&nbsp;
						<label class="control-label" for="userId">注册账号：</label>
						<input  class="input-sm" type="text" id="userId"  name="userId" maxlength="32"  value="${lotteryDetail.userId}" /> 
					</div>
					<div class="form-group">&nbsp;&nbsp;
						<label class="control-label" for="startDate">变动日期：</label>
						<input  class="Wdate input-sm" style="height: 30px" type="text" id="startDate"  name="startDate"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'endDate\')}'});"  value="${lotteryDetail.startDate}"  />&nbsp;- 
						<input  class="Wdate input-sm" style="height: 30px" type="text" id="endDate"  name="endDate"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'startDate\')}'});"  value="${lotteryDetail.endDate}"  /> 
					</div>
					<div class="form-group">&nbsp;&nbsp;
						<label class="control-label padding-left" for="changeReason">变动原因：</label>
						<select id="changeReason" name="changeReason" class="input-sm">
	  					   	<option value="">全部</option>
	  					   	<option value="0" ${lotteryDetail.changeReason==0?'selected="selected"':'' }>抽奖</option>
	  					   	<option value="1" ${lotteryDetail.changeReason==1?'selected="selected"':'' }>新用户注册</option>
	  					   	<option value="2" ${lotteryDetail.changeReason==2?'selected="selected"':'' }>绑定信用卡</option>
	  					   	<option value="3" ${lotteryDetail.changeReason==3?'selected="selected"':'' }>完成还款计划</option>
	  					   	<option value="4" ${lotteryDetail.changeReason==4?'selected="selected"':'' }>推荐他人注册</option>
	  					</select> 
					</div>
					<div class="form-group">&nbsp;&nbsp;
						<label class="control-label" for="memo">备注：</label>
						<input  class="input-sm" type="text" id="memo"  name="memo" maxlength="32"  value="${lotteryDetail.memo}" /> 
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
										<th>变动时间</th>
										<th>注册账号</th>
										<th>抽奖次数</th>
										<th>变动后次数</th>
										<th>变动原因</th>
										<th>备注</th>
									</tr>
								</thead>

								<tbody>
									<c:forEach items="${list }" varStatus="i" var="l">
										<tr>
											<td>${i.index+1}</td>
											<td><fmt:formatDate value="${l.rowCrtTs}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
											<td>${l.userId }</td>
											<td>
												<c:choose>
													<c:when test="${l.changeReason==0}"><span style="color: #03e803">${l.lotteryCount }</span></c:when>
													<c:otherwise><span style="color: red;">+${l.lotteryCount }</span></c:otherwise>
												</c:choose>
												
												
											</td>
											<td>${l.changedCount }</td>
											<td>
												<c:if test="${l.changeReason==0 }">抽奖</c:if>
												<c:if test="${l.changeReason==1 }">新用户注册</c:if>
												<c:if test="${l.changeReason==2 }">绑定信用卡</c:if>
												<c:if test="${l.changeReason==3 }">完成还款计划</c:if>
												<c:if test="${l.changeReason==4 }">推荐他人注册</c:if>
											</td>
											<td>${l.memo }</td>
										</tr>
									</c:forEach>
									<tr>
										<td colspan="7" align="left">查询结果：${page.rowTotal }条</td>
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
<form id="pageForm" action="${ctx }/lotteryDetail/list"  method="post" >
	<input type="hidden"  name="start" id="start"></input>
	<input type="hidden"  name="changeReason" value="${lotteryDetail.changeReason}"></input>
	<input type="hidden"  name="userId" value="${lotteryDetail.userId}"></input>
	<input type="hidden"  name="startDate" value="${lotteryDetail.startDate}"></input>
	<input type="hidden"  name="endDate" value="${lotteryDetail.endDate}"></input>
	<input type="hidden"  name="memo" value="${lotteryDetail.memo}"></input>
</form>
<input type="hidden"  id="closeTp"/>
<iframe name='hidden_frame' id="hidden_frame" style='display: none'></iframe>
</body>
</html>