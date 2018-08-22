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
<title>等级费率设置</title>
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
function update(levelId,levelType){
	var url = "url:${ctx }/levelFeeRate/update?level.levelId="+levelId+"&level.levelType="+levelType+"&type=toUpdate&a="+encodeURIComponent(new Date());
	$.dialog({content:url ,
		        title:'等级费率设置',
		        lock: true,
				background: '#FFF', /* 背景色 默认的遮罩背景色为:#DCE2F1浅蓝护眼色 */
				max: false,
				height:400,
				width:750,
				min: false,
				opacity: 0.5,	/* 透明度 */
				close: function(){
					addClose();
				}
	});
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
		<div class="row">
			<div class="col-xs-12">
				<!-- PAGE CONTENT BEGINS -->
				<form action='<c:url value="/levelFeeRate/list"/>' id="searchForm" method="post"></form>
				<div class="row">
					<div class="col-xs-12">
						<div class="table-responsive">
							<table id="sample-table-1"
								class="table table1 table-striped table-bordered table-hover">
								<thead>
									<tr>
										<th>等级名称</th>
										<th>还款服务费</th>
										<th>RB快捷</th>
										<th>YT快捷</th>
										<th>操作</th>
									</tr>
								</thead>

								<tbody>
									<c:forEach items="${list }" varStatus="i" var="l">
										<tr>
											<td>${l.levelName}</td>
											<td>
												<c:forEach items="${l.listLevelFeeRate }" var="lfr">
													<c:if test="${lfr.payChnlCode eq 'reapal' }">
														<c:if test="${lfr.feeRate > 0 }">
															<fmt:formatNumber value="${lfr.feeRate }" type="currency" pattern="0.00"/>%+<chrone:fen2Yuan amt="${lfr.payFee }"/>元/笔
														</c:if>
													</c:if>
												</c:forEach>
											</td>
											<td>
												<c:forEach items="${l.listLevelFeeRate }" var="lfr">
													<c:if test="${lfr.payChnlCode eq 'reapalfast' }">
														<c:if test="${lfr.feeRate > 0 }">
															<fmt:formatNumber value="${lfr.feeRate }" type="currency" pattern="0.00"/>%
															<c:if test="${lfr.payFee > 0 }">
																+<chrone:fen2Yuan amt="${lfr.payFee }"/>元/笔
															</c:if>
															<c:if test="${lfr.upperlimit > 0 }">
																，封顶<chrone:fen2Yuan amt="${lfr.upperlimit }"/>元
															</c:if>
														</c:if>
													</c:if>
												</c:forEach>
											</td>
											<td>
												<c:forEach items="${l.listLevelFeeRate }" var="lfr">
													<c:if test="${lfr.payChnlCode eq 'yitong' }">
														<c:if test="${lfr.feeRate > 0 }">
															<fmt:formatNumber value="${lfr.feeRate }" type="currency" pattern="0.00"/>%
															<c:if test="${lfr.payFee > 0 }">
																+<chrone:fen2Yuan amt="${lfr.payFee }"/>元/笔
															</c:if>
															<c:if test="${lfr.upperlimit > 0 }">
																，封顶<chrone:fen2Yuan amt="${lfr.upperlimit }"/>元
															</c:if>
														</c:if>
													</c:if>
												</c:forEach>
											</td>
											<td>
												<chrone:isAuth authCode="600000601">
													<a href="#" onclick="update('${l.levelId }','${l.levelType}')">修改</a>
												</chrone:isAuth>
											</td>
										</tr>
									</c:forEach>
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
<iframe name='hidden_frame' id="hidden_frame" style='display: none'></iframe>
</body>
</html>