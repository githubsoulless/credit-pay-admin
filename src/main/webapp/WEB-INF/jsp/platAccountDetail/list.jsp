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
<title>平台账务</title>
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
				<form action="${ctx}/platAccountDetail/list"  id="searchForm"   name="searchForm" method="post" class="form-inline" role="form">
					<div class="form-group">&nbsp;&nbsp;
						<label class="control-label" for="accountLogId">关联订单：</label>
						<input  class="input-sm" type="text" id="accountLogId"  name="accountLogId" value="${accountDetail.accountLogId}" /> 
					</div>
					<div class="form-group">&nbsp;&nbsp;
						<label class="control-label" for="transType">变动类型：</label>
						<select id="transType" name="transType" class="input-sm">
	  					   	<option value="">全部</option>
	  					   	 <option value="0" ${accountDetail.transType==0?'selected="selected"':'' }>升级分润</option>
	  					   	<option value="1" ${accountDetail.transType==1?'selected="selected"':'' }>还款推广收益</option>
	  					   	<option value="4" ${accountDetail.transType==1?'selected="selected"':'' }>快捷推广收益</option>
	  					   	<option value="2" ${accountDetail.transType==2?'selected="selected"':'' }>钱包提现</option>
	  					   	<option value="3" ${accountDetail.transType==3?'selected="selected"':'' }>其他</option>
	  					   	
	  					</select> 
					</div>
					<div class="form-group">&nbsp;&nbsp;
						<label class="control-label" for="balanceType">账户类型：</label>
						<select id="balanceType" name="balanceType" class="input-sm">
	  					   	<option value="">全部</option>
	  					   	<option value="0" ${accountDetail.balanceType==0?'selected="selected"':'' }>可用</option>
	  					   	<option value="1" ${accountDetail.balanceType==1?'selected="selected"':'' }>冻结</option>
	  					</select>  
					</div>
					<div class="form-group">&nbsp;&nbsp;
						<label class="control-label" for="startDate">变动日期：</label>
						<input  class="Wdate input-sm" style="height: 30px" type="text" id="startDate"  name="startDate"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'endDate\')}'});"  value="${accountDetail.startDate}"  />&nbsp;- 
						<input  class="Wdate input-sm" style="height: 30px" type="text" id="endDate"  name="endDate"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'startDate\')}'});"  value="${accountDetail.endDate}"  /> 
					</div>
					<div class="form-group">&nbsp;&nbsp;
						<label class="control-label padding-left" for="dcFlag">收支类型：</label>
						<select id="dcFlag" name="dcFlag" class="input-sm">
	  					   	<option value="">全部</option>
	  					   	<option value="0" ${accountDetail.dcFlag==0?'selected="selected"':'' }>收入</option>
	  					   	<option value="1" ${accountDetail.dcFlag==1?'selected="selected"':'' }>支出</option>
	  					   </select> 
					</div>
					<div class="form-group">&nbsp;&nbsp;
						<label class="control-label" for="memo">备注：</label>
						<input  class="input-sm" type="text" id="memo"  name="memo" value="${accountDetail.memo}" /> 
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
								class="table table-striped table-bordered table-hover">
								<thead>
									<tr>
										<th>序号</th>
										<th>变动类型</th>
										<th>账户类型</th>
										<th>变动金额</th>
										<th>变动后余额</th>
										<th>变动时间</th>
										<th>关联订单</th>
										<th>备注</th>
									</tr>
								</thead>

								<tbody>
									<c:forEach items="${list }" varStatus="i" var="l">
										<tr>
											<td>${i.index+1}</td>
											<td>
												<c:if test="${l.transType==0 }">升级分润</c:if>
												<c:if test="${l.transType==1 }">还款推广收益</c:if>
												<c:if test="${l.transType==4 }">快捷推广收益</c:if>
												<c:if test="${l.transType==2 }">钱包提现</c:if>
												<c:if test="${l.transType==3 }">其他</c:if>
											</td>
											<td>
												<c:if test="${l.balanceType==0 }">可用</c:if>
												<c:if test="${l.balanceType==1 }">冻结</c:if>
											</td>
											<td >
												<c:if test="${l.dcFlag == 1 }">
													<span style="color: green;">-<chrone:fen2Yuan amt="${l.avalCredit}"/></span>		
												</c:if>
												<c:if test="${l.dcFlag == 0 }">
													<span style="color: red;">+<chrone:fen2Yuan amt="${l.avalDebit}"/></span>		
												</c:if>
											</td>
											<td >
												<chrone:fen2Yuan amt="${l.available}"/>
											</td>
											<td><fmt:formatDate value="${l.rowCrtTs}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
											<td>${l.accountLogId }</td>
											<td >${l.memo }</td>
										</tr>
									</c:forEach>
									<tr>
										<td colspan="2" align="left">查询结果：${page.rowTotal }条</td>
										<td colspan="8" align="right">
											合计：&nbsp;&nbsp;&nbsp;
											<span style="color: red;">收入：<chrone:fen2Yuan amt="${countMap.incomeAmt }"/></span>
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<span style="color: green;">支出：<chrone:fen2Yuan amt="${countMap.outcomeAmt }"/></span>
										</td>
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
<form id="pageForm" action="${ctx }/platAccountDetail/list"  method="post" >
	<input type="hidden"  name="start" id="start"></input>
	<input type="hidden"  name="transType" value="${accountDetail.transType}"></input>
	<input type="hidden"  name="balanceType" value="${accountDetail.balanceType}"></input>
	<input type="hidden"  name="dcFlag" value="${accountDetail.dcFlag}"></input>
	<input type="hidden"  name="accountLogId" value="${accountDetail.accountLogId}"></input>
	<input type="hidden"  name="memo" value="${accountDetail.memo}"></input>
	<input type="hidden"  name="startDate" value="${accountDetail.startDate}"></input>
	<input type="hidden"  name="endDate" value="${accountDetail.endDate}"></input>
</form>
<input type="hidden"  id="closeTp"/>
</body>
</html>