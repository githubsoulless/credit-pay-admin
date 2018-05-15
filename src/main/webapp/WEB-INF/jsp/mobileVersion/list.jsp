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
<title>版本管理</title>
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
function add(){
	var url = "url:${ctx }/mobileVersion/toAdd?type=toAdd&a="+encodeURIComponent(new Date());
	$.dialog({content:url ,
		        title:'创建版本',
		        lock: true,
				background: '#FFF', /* 背景色 默认的遮罩背景色为:#DCE2F1浅蓝护眼色 */
				max: false,
				height:460,
				width:750,
				min: false,
				opacity: 0.5,	/* 透明度 */
				close: function(){
					addClose();
				}
	});
}
function update(appVersion,osType){
	var url = "url:${ctx }/mobileVersion/toUpdate?appVersion=" + appVersion + "&osType=" + osType +"&type=toUpdate&a="+encodeURIComponent(new Date());
	$.dialog({content:url ,
		        title:'修改版本信息',
		        lock: true,
				background: '#FFF', /* 背景色 默认的遮罩背景色为:#DCE2F1浅蓝护眼色 */
				max: false,
				height:460,
				width:720,
				min: false,
				opacity: 0.5,	/* 透明度 */
				close: function(){
					addClose();
				}
	});
}
function detail(appVersion, osType){
	var url = "url:${ctx }/mobileVersion/detail?appVersion=" + appVersion + "&osType=" + osType +"&a="+encodeURIComponent(new Date());
	$.dialog({content:url ,
		        title:'版本详情',
		        lock: true,
				background: '#FFF', /* 背景色 默认的遮罩背景色为:#DCE2F1浅蓝护眼色 */
				max: false,
				height:550,
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
				<form action="${ctx}/mobileVersion/list"  id="searchForm"   name="searchForm" method="post" class="form-inline" role="form">
					<div class="form-group">&nbsp;&nbsp;
						<label class="control-label" for="appVersion">版本号：</label>
						<input  class="input-sm" type="text" id="appVersion"  name="appVersion" maxlength="10"  value="${mobileVersion.appVersion}" /> 
					</div>
					<div class="form-group">&nbsp;&nbsp;
						<label class="control-label padding-left" for="osType">操作系统：</label>
						<select id="osType" name="osType" class="input-sm">
	  					   	<option value="">全部</option>
	  					   	<option value="0" ${mobileVersion.osType=="0"?'selected="selected"':'' }>Android</option>
	  					   	<option value="1" ${mobileVersion.osType=="1"?'selected="selected"':'' }>IOS</option>
	  					</select> 
					</div>
					<div class="form-group">&nbsp;&nbsp;
						<label class="control-label padding-left" for="appState">状态：</label>
						<select id="appState" name="appState" class="input-sm">
	  					   	<option value="">全部</option>
	  					   	<option value="0" ${mobileVersion.appState==0?'selected="selected"':'' }>停用</option>
	  					   	<option value="1" ${mobileVersion.appState==1?'selected="selected"':'' }>启用</option>
	  					</select>
					</div>
					<div class="form-group">
						&nbsp;&nbsp;<button type="button" class="btn btn-primary"  onclick="fastSearch()">查询</button>
						<chrone:isAuth authCode="800000101">
						&nbsp;&nbsp;<button type="button" class="btn btn-success" onclick="add()">创建版本</button>
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
						<div class="table-responsive">
							<table id="sample-table-1"
								class="table table1 table-striped table-bordered table-hover">
								<thead>
									<tr>
										<th>序号</th>
										<th>操作系统</th>
										<th>版本号</th>
										<th>状态</th>
										<th>更新机制</th>
										<th>创建时间</th>
										<th>最后操作人</th>
										<th>操作</th>
									</tr>
								</thead>

								<tbody>
									<c:forEach items="${list }" varStatus="i" var="l">
										<tr>
											<td>${i.index+1}</td>
											<td>
												<c:if test="${l.osType ==0 }">Android</c:if>
												<c:if test="${l.osType ==1 }">IOS</c:if>
											</td>
											<td>${l.appVersion }</td>
											<td>
												<c:if test="${l.appState ==0 }">禁用</c:if>
												<c:if test="${l.appState ==1 }">启用</c:if>
											</td>
											<td>
												<c:if test="${l.isUpversion ==0 }">选择更新</c:if>
												<c:if test="${l.isUpversion ==1 }">强制更新</c:if>
											</td>
											<td><fmt:formatDate value="${l.regTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
											<td>${l.operator }</td>
											<td>
												<chrone:isAuth authCode="800000103">
													<a href="#" onclick="detail('${l.appVersion}', ${l.osType })">详情</a>
												</chrone:isAuth>
												<chrone:isAuth authCode="800000102">
													&nbsp;&nbsp;<a href="#" onclick="update('${l.appVersion}', '${l.osType }')">修改</a>
												</chrone:isAuth>
											</td>
										</tr>
									</c:forEach>
									<tr>
										<td colspan="8" align="left">查询结果：${page.rowTotal }条</td>
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
<form id="pageForm" action="${ctx }/mobileVersion/list"  method="post" >
	<input type="hidden"  name="start" id="start"></input>
	<input type="hidden"  name="appVersion" value="${mobileVersion.appVersion}"></input>
	<input type="hidden"  name="osType" value="${mobileVersion.osType}"></input>
	<input type="hidden"  name="appState" value="${mobileVersion.appState}"></input>
</form>
<input type="hidden"  id="closeTp"/>
<iframe name='hidden_frame' id="hidden_frame" style='display: none'></iframe>
</body>
</html>