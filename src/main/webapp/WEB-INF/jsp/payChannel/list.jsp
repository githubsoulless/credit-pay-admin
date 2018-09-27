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
<title>通道管理</title>
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
	var url = "url:${ctx }/payChannel/add?type=toAdd&a="+encodeURIComponent(new Date());
	$.dialog({content:url ,
		        title:'添加通道',
		        lock: true,
				background: '#FFF', /* 背景色 默认的遮罩背景色为:#DCE2F1浅蓝护眼色 */
				max: false,
				height:400,
				width:880,
				min: false,
				opacity: 0.5,	/* 透明度 */
				close: function(){
					addClose();
				}
	});
}
function update(id){
	var url = "url:${ctx }/payChannel/update?id="+id+"&type=toUpdate&a="+encodeURIComponent(new Date());
	$.dialog({content:url ,
		        title:'修改通道信息',
		        lock: true,
				background: '#FFF', /* 背景色 默认的遮罩背景色为:#DCE2F1浅蓝护眼色 */
				max: false,
				height:400,
				width:880,
				min: false,
				opacity: 0.5,	/* 透明度 */
				close: function(){
					addClose();
				}
	});
}
function detail(id){
	var url = "url:${ctx }/payChannel/detail?id="+id +"&a="+encodeURIComponent(new Date());
	$.dialog({content:url ,
		        title:'通道信息',
		        lock: true,
				background: '#FFF', /* 背景色 默认的遮罩背景色为:#DCE2F1浅蓝护眼色 */
				max: false,
				height:400,
				width:880,
				min: false,
				opacity: 0.5,	/* 透明度 */
				close: function(){
					addClose();
				}
	});
}
function updateStatus(id,type){
	var url = "${ctx }/payChannel/close?id="+id +"&type="+type+"&a="+encodeURIComponent(new Date());
	var memo = "";
	if(type == '0'){
		memo = "是否确认关闭当前通道?";
	}
	if(type == '1'){
		memo = "是否确认开启当前通道?";
	}
	if(confirm(memo)){
		$.ajax({
			type : "GET",
			url : url,
			dateType : "json",
			async : false,
			contentType : 'application/json',
			success : function(r) {
				if (r == 1) {
					if(type == '0'){
						alert('关闭成功')
					}
					if(type == '1'){
						alert('开启成功')
					}
					fastSearch();
				}
				if (r.code == 0) {
					alert('修改失败，请关闭重试');
				}
				if (r.code == 99) {
					alert('网络繁忙,请稍后再试');
				}
			}
		});
	}
	
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
				<form action="${ctx}/payChannel/list"  id="searchForm"   name="searchForm" method="post" class="form-inline" role="form">
					<div class="form-group">&nbsp;&nbsp;
						<label class="control-label" for="code">通道代码：</label>
						<input  class="input-sm" type="text" id="code"  name="code" maxlength="20"  value="${payChannel.code}" /> 
					</div>
					<div class="form-group">&nbsp;&nbsp;
						<label class="control-label" for="name">通道名称：</label>
						<input  class="input-sm" type="text" id="name"  name="name" maxlength="20"  value="${payChannel.name}" placeholder="支持模糊查询" /> 
					</div>
					<div class="form-group">&nbsp;&nbsp;
						<label class="control-label padding-left" for="status">通道状态：</label>
						<select id="status" name="status" class="input-sm">
	  					   	<option value="">全部</option>
	  					   	<option value="1" ${payChannel.status==1?'selected="selected"':'' }>正常</option>
	  					   	<option value="0" ${payChannel.status==0?'selected="selected"':'' }>关闭</option>
	  					</select> 
					</div>
					<div class="form-group">&nbsp;&nbsp;
						<label class="control-label padding-left" for="chnlType">通道类型：</label>
						<select id="chnlType" name="chnlType" class="input-sm">
	  					   	<option value="">全部</option>
	  					   	<option value="0" ${payChannel.chnlType==0?'selected="selected"':'' }>快捷</option>
	  					   	<option value="1" ${payChannel.chnlType==1?'selected="selected"':'' }>代付</option>
	  					   	<option value="2" ${payChannel.chnlType==2?'selected="selected"':'' }>实名认证</option>
	  					   	<option value="3" ${payChannel.chnlType==3?'selected="selected"':'' }>钱包提现</option>
	  					   	<option value="4" ${payChannel.chnlType==4?'selected="selected"':'' }>快捷消费</option>
	  					   	<option value="5" ${payChannel.chnlType==5?'selected="selected"':'' }>快捷消费出款</option>
	  					   	<option value="6" ${payChannel.chnlType==6?'selected="selected"':'' }>服务费支付</option>
	  					</select>
					</div>
					<div class="form-group">&nbsp;&nbsp;
						<label class="control-label padding-left" for="settleType">结算方式：</label>
						<select id="settleType" name="settleType" class="input-sm">
	  					   	<option value="">全部</option>
	  					   	<option value="0" ${payChannel.settleType==0?'selected="selected"':'' }>D0</option>
	  					   	<option value="1" ${payChannel.settleType==1?'selected="selected"':'' }>T1</option>
	  					</select>
					</div>
					<br>
					<div class="form-group">&nbsp;&nbsp;
						<label class="control-label padding-left" for="orderBy">排序：</label>
						<select id="orderBy" name="orderBy" class="input-sm">
	  					   	<option value="">默认</option>
	  					   	<option value="1" ${payChannel.orderBy==1?'selected="selected"':'' }>权重由高到低</option>
	  					   	<option value="2" ${payChannel.orderBy==2?'selected="selected"':'' }>权重由低到高</option>
	  					</select>
					</div>
					<div class="form-group">
						&nbsp;&nbsp;<button type="button" class="btn btn-primary"  onclick="fastSearch()">查询</button>
						<chrone:isAuth authCode="600000301">
						&nbsp;&nbsp;<button type="button" class="btn btn-success" onclick="add()">添加通道</button>
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
										<th>通道代码</th>
										<th>通道名称</th>
										<th>通道类型</th>
										<th>结算方式</th>
										<th>通道状态</th>
										<th>权重</th>
										<th>费率类型</th>
										<th>通道费率</th>
										<th>单笔最小金额(元)</th>
										<th>单笔最大金额(元)</th>
										<th>操作</th>
									</tr>
								</thead>

								<tbody>
									<c:forEach items="${list }" varStatus="i" var="l">
										<tr>
											<td>${i.index+1}</td>
											<td>${l.code}</td>
											<td >${l.name}</td>
											<td >
												<c:if test="${l.chnlType==0 }">快捷</c:if>
												<c:if test="${l.chnlType==1 }">代付</c:if>
												<c:if test="${l.chnlType==2 }">实名认证</c:if>
												<c:if test="${l.chnlType==3 }">钱包提现</c:if>
												<c:if test="${l.chnlType==4 }">快捷消费</c:if>
												<c:if test="${l.chnlType==5 }">快捷消费出款</c:if>
												<c:if test="${l.chnlType==6 }">服务费支付</c:if>
											</td>
											<td>
												<c:if test="${l.chnlType!=2 }">
													<c:if test="${l.settleType==0 }">D0</c:if>
													<c:if test="${l.settleType==1 }">T1</c:if>
												</c:if>
											</td>
											<td>
												<c:if test="${l.status==0 }">关闭</c:if>
												<c:if test="${l.status==1 }">正常</c:if>
											</td>
											<td >${l.priority}</td>
											<td>
												<c:if test="${l.feeType==0 }">百分比</c:if>
												<c:if test="${l.feeType==1 }">按笔</c:if>
											</td>
											<td>
												<c:if test="${l.feeType==0 }">
													<fmt:formatNumber value="${l.feeRate*100 }" type="currency" pattern="0.00"/>%
												</c:if>
												<c:if test="${l.feeType==1 }">
													<fmt:formatNumber value="${l.feeRate/100 }" type="currency" pattern="0.00"/>元
												</c:if>
											</td>
											<td>
												<c:if test="${l.chnlType!=2 && l.chnlType !=3 }">
													<chrone:fen2Yuan amt="${l.minAmount }"></chrone:fen2Yuan>
												</c:if>
											</td>
											<td>
												<c:if test="${l.chnlType!=2 && l.chnlType !=3 }">
													<chrone:fen2Yuan amt="${l.maxAmount }"></chrone:fen2Yuan>
												</c:if>
											</td>
											<td>
												<chrone:isAuth authCode="600000304">
													<a href="#" onclick="detail('${l.id}')">详情</a>
												</chrone:isAuth>
												<chrone:isAuth authCode="600000302">
													&nbsp;&nbsp;<a href="#" onclick="update('${l.id }')">修改</a>
												</chrone:isAuth>
												<chrone:isAuth authCode="600000303">
													&nbsp;&nbsp;
													<c:if test="${l.status == 0 }">
														<a href="#" onclick="updateStatus('${l.id }','1')">开启</a>													
													</c:if>
													<c:if test="${l.status == 1 }">
														<a href="#" onclick="updateStatus('${l.id }','0')">关闭</a>
													</c:if>
												</chrone:isAuth>
											</td>
										</tr>
									</c:forEach>
									<tr>
										<td colspan="12" align="left">查询结果：${page.rowTotal }条</td>
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
<form id="pageForm" action="${ctx }/payChannel/list"  method="post" >
	<input type="hidden"  name="start" id="start"></input>
	<input type="hidden"  name="code" value="${payChannel.code}"></input>
	<input type="hidden"  name="name" value="${payChannel.name}"></input>
	<input type="hidden"  name="status" value="${payChannel.status}"></input>
	<input type="hidden"  name="chnlType" value="${payChannel.chnlType}"></input>
	<input type="hidden"  name="settleType" value="${payChannel.settleType}"></input>
	<input type="hidden"  name="orderBy" value="${payChannel.orderBy}"></input>
</form>
<input type="hidden"  id="closeTp"/>
<iframe name='hidden_frame' id="hidden_frame" style='display: none'></iframe>
</body>
</html>