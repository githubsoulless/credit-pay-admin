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
<title>渠道余额查询</title>
<style type="text/css">
.width80 {
	width: 80px;
}

</style>
<script language="javascript">
function init() {
	var result = "${message}";
	if (result != "") {
		callBack(result);
	}
}
</script>
<style type="text/css">
	table.table1{
		border : 2px solid;
	}
	table.table1 tr td{
		white-space:nowrap;
	}
</style>
</head>
<body onload="init()">
	<div class="page-content">
		<div class="row">
			<div class="col-xs-12">
				<!-- PAGE CONTENT BEGINS -->
				<div class="row">
					<div class=""><!-- col-xs-12 -->
						<div class="table-responsive" style="overflow: auto;">
										<table id="sample-table-1" class="table1 table table-striped table-bordered table-hover">
										<tbody>
											
										<tr>
											<td>账户名称</td>
											<td>账户余额(元)</td>
										</tr>
										<tr>
											<td>
												任务交易账户
											</td>
											<td>
												<div class="input-group">
		 											<chrone:fen2Yuan amt="${fastPayMap.balance }"/>
												</div>
											</td>
										</tr>
										<tr>
											<td>
												钱包提现账户
											</td>
											<td>
												<div class="input-group">
		 											<chrone:fen2Yuan amt="${agentPayMap.balance }"/>
												</div>
											</td>
										</tr>
										<tr>
											<td colspan="2"></td>
										</tr>
										</tbody>
								</table>
						</div>
						<!-- /.table-responsive -->
					</div>
					<!-- /span -->
				</div>
				<!-- /row -->
			</div>
		</div>
	</div>
<input type="hidden"  id="closeTp"/>
</body>
</html>