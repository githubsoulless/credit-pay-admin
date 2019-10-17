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
<title>操作记录>></title>
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
function detail(id){
	var url = "url:${ctx}/operationLog/detail?id="+id+"&a="+encodeURIComponent(new Date());
	$.dialog({content:url ,
		        title:'详情',
		        lock: true,
				background: '#FFF', /* 背景色 默认的遮罩背景色为:#DCE2F1浅蓝护眼色 */
				max: false,
				height:500,
				width:1000,
				min: false,
				opacity: 0.5,	/* 透明度 */
				close: function(){
					addClose();
				}
	});
}
</script>
</head>
<body>
	<div class="page-content">
		<div class="page-header">
			<div class="row">
				<form action="${ctx}/operationLog/list"  id="searchForm"   name="searchForm" method="post" class="form-inline" role="form">
					<div class="form-group">&nbsp;&nbsp;
						<label class="control-label" for="type">操作类型：</label>
						<select class="input-sm" name="type">
							<option value="">全部</option>
							<c:forEach items="${types }" var="entry">
								<option <c:if test="${operationLog.type == entry.key}">selected="selected"</c:if> value="${entry.key }">${entry.value }</option>
							</c:forEach>
						</select>
					</div>
					<div class="form-group">&nbsp;&nbsp;
						<label class="control-label" for="operationUser">操作人：</label>
						<input  class="input-sm" type="text" id="operationUser"  name="operationUser" maxlength="20"  value="${operationLog.operationUser}" /> 
					</div>
					<div class="form-group">&nbsp;&nbsp;
						<label class="control-label" for="startDate">操作日期：</label>
						<input  class="Wdate input-sm" style="height: 30px" type="text" id="startDate"  name="startDate"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'endDate\')}'});"  value="${operationLog.startDate}"  />&nbsp;- 
						<input  class="Wdate input-sm" style="height: 30px" type="text" id="endDate"  name="endDate"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'startDate\')}'});"  value="${operationLog.endDate}"  /> 
					</div>
					<br>
					<div class="form-group">&nbsp;&nbsp;
						<label class="control-label" for="content">操作内容：</label>
						<input  class="input-sm" type="text" id="content"  name="content" value="${operationLog.content}" placeholder="支持模糊查询" /> 
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
										<th>操作类型</th>
										<th>操作时间</th>
										<th>操作人</th>
										<th>操作内容</th>
										<th>来源IP</th>
									</tr>
								</thead>

								<tbody>
									<c:forEach items="${list }" varStatus="i" var="l">
										<tr>
											<td>${i.index+1}</td>
											<td>${types[l.type]}</td>
											<td><fmt:formatDate value="${l.rowCrtTs}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
											<td>${l.operationUser}</td>
											<td>
												<c:if test="${l.type == 1 }"><a href="javascript:detail('${ l.id}')">详情</a>&nbsp;&nbsp;</c:if>	
												<c:if test="${l.type != 1 }">${l.content }</c:if>
											</td>
											<td>${l.ip }</td>
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
<form id="pageForm" action="${ctx }/operationLog/list"  method="post" >
	<input type="hidden"  name="start" id="start"></input>
	<input type="hidden"  name="type" value="${operationLog.type}"></input>
	<input type="hidden"  name="operationUser" value="${operationLog.operationUser}"></input>
	<input type="hidden"  name="content" value="${operationLog.content}"></input>
	<input type="hidden"  name="startDate" value="${operationLog.startDate}"></input>
	<input type="hidden"  name="endDate" value="${operationLog.endDate}"></input>
</form>
<input type="hidden"  id="closeTp"/>
</body>
</html>