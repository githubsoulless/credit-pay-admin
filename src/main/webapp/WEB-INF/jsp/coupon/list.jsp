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
<title>优惠券管理</title>
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
	var url = "url:${ctx }/couponMgr/add?type=toAdd&a="+encodeURIComponent(new Date());
	$.dialog({content:url ,
		        title:'添加优惠券',
		        lock: true,
				background: '#FFF', /* 背景色 默认的遮罩背景色为:#DCE2F1浅蓝护眼色 */
				max: false,
				height:660,
				width:920,
				min: false,
				opacity: 0.5,	/* 透明度 */
				close: function(){
					addClose();
				}
	});
}
function update(id){
	var url = "url:${ctx }/couponMgr/update?id=" + id +"&type=toUpdate&a="+encodeURIComponent(new Date());
	$.dialog({content:url ,
		        title:'修改优惠券信息',
		        lock: true,
				background: '#FFF', /* 背景色 默认的遮罩背景色为:#DCE2F1浅蓝护眼色 */
				max: false,
				height:660,
				width:920,
				min: false,
				opacity: 0.5,	/* 透明度 */
				close: function(){
					addClose();
				}
	});
}
function detail(id){
	var url = "url:${ctx }/couponMgr/detail?id=" + id + "&a="+encodeURIComponent(new Date());
	$.dialog({content:url ,
		        title:'优惠券信息',
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
function sendCoupon(id){
	var url = "url:${ctx }/couponMgr/sendCoupon?id=" + id +"&type=toSend";
	$.dialog({content:url ,
		        title:'手动发放优惠券',
		        lock: true,
				background: '#FFF', /* 背景色 默认的遮罩背景色为:#DCE2F1浅蓝护眼色 */
				max: false,
				height:360,
				width:820,
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
				<form action="${ctx}/couponMgr/list"  id="searchForm"   name="searchForm" method="post" class="form-inline" role="form">
					<div class="form-group">&nbsp;&nbsp;
						<label class="control-label" for="name">优惠券名称：</label>
						<input  class="input-sm" type="text" id="name"  name="name" maxlength="10"  value="${coupon.name}" placeholder="支持模糊查询" /> 
					</div>
					<div class="form-group">&nbsp;&nbsp;
						<label class="control-label" for="id">ID：</label>
						<input  class="input-sm" type="text" id="id"  name="id" maxlength="20"  value="${coupon.id}" /> 
					</div>
					<div class="form-group">&nbsp;&nbsp;
						<label class="control-label padding-left" for="status">状态：</label>
						<select id="status" name="status" class="input-sm">
	  					   	<option value="">全部</option>
	  					   	<option value="1" ${coupon.status==1?'selected="selected"':'' }>启用</option>
	  					   	<option value="0" ${coupon.status==0?'selected="selected"':'' }>禁用</option>
	  					</select> 
					</div>
					<div class="form-group">&nbsp;&nbsp;
						<label class="control-label padding-left" for="lotteryType">参与抽奖：</label>
						<select id="lotteryType" name="lotteryType" class="input-sm">
	  					   	<option value="">全部</option>
	  					   	<option value="1" ${coupon.lotteryType==1?'selected="selected"':'' }>可抽奖</option>
	  					   	<option value="0" ${coupon.lotteryType==0?'selected="selected"':'' }>不可抽奖</option>
	  					</select>
					</div>
					<br>
					<div class="form-group">&nbsp;&nbsp;
						<label class="control-label padding-left" for="levelIds">可抽奖级别：</label>
						<select id="levelId" name="levelId" class="input-sm">
	  					   	<option value="">不限</option>
	  					   	<c:forEach items="${levels }" var="level">
	  					   		<option value="${level.levelId }"
									<c:if test="${coupon.levelId == level.levelId }">selected="selected"</c:if>
	  					   		>${level.levelName }</option>
	  					   	</c:forEach>
	  					</select>
						<%-- <c:forEach items="${levels }" var="level">
							<input type="checkbox" name="levelIds" 
							<c:forEach items="${coupon.levelIds }" var="lev">
								<c:if test="${lev == level.levelId }">checked="checked"</c:if>
							</c:forEach>
							 value="${level.levelId }" id="${level.levelId }" /><label for="${level.levelId }">${level.levelName }</label>
						</c:forEach> --%>
					</div>
					<div class="form-group">
						&nbsp;&nbsp;<button type="button" class="btn btn-primary"  onclick="fastSearch()">查询</button>
						<chrone:isAuth authCode="900000101">
						&nbsp;&nbsp;<button type="button" class="btn btn-success" onclick="add()">添加优惠券</button>
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
										<th>ID</th>
										<th>优惠券名称</th>
										<th>面值</th>
										<th>状态</th>
										<th>剩余数量</th>
										<th>参与抽奖</th>
										<th>中奖概率</th>
										<th>已使用/已领取数量</th>
										<th>已过期数量</th>
										<th>有效期</th>
										<th>可抽奖级别</th>
										<th>操作</th>
									</tr>
								</thead>

								<tbody>
									<c:forEach items="${list }" varStatus="i" var="l">
										<tr>
											<td>${i.index+1}</td>
											<td>${l.id }</td>
											<td>${l.name }</td>
											<td><chrone:fen2Yuan amt="${l.amount }"></chrone:fen2Yuan>元</td>
											<td>
												<c:if test="${l.status ==0 }">禁用</c:if>
												<c:if test="${l.status ==1 }">启用</c:if>
											</td>
											<td>
												<c:choose>
													<c:when test="${l.coupanCount == -1 }">
														不限制
													</c:when>
													<c:otherwise>
														${l.coupanCount }
													</c:otherwise>
												</c:choose>
											</td>
											<td>
												<c:if test="${l.lotteryType == 0 }">不可抽奖</c:if>
												<c:if test="${l.lotteryType == 1 }">可抽奖</c:if>
											</td>
											<td>
												<c:if test="${l.lotteryType == 0 }">--</c:if>
												<c:if test="${l.lotteryType == 1 }"><fmt:formatNumber value="${l.probabilityWinning*100 }" type="currency" pattern="0.00"/>%</c:if>
											</td>
											<td>${l.usedCount }/${l.totalCount }</td>
											<td>${l.overdueCount }</td>
											<td>
												<c:if test="${l.validityType == 0 }">固定天数</c:if>
												<c:if test="${l.validityType == 1 }">自定义</c:if>
											</td>
											<td>
												<c:forEach items="${l.levels }" var="level">${level.levelName }&nbsp;&nbsp;</c:forEach>
											</td>
											<td>
												<chrone:isAuth authCode="900000103">
													<a href="#" onclick="detail('${l.id}')">详情</a>
												</chrone:isAuth>
												<chrone:isAuth authCode="900000102">
													&nbsp;&nbsp;<a href="#" onclick="update('${l.id}')">修改</a>
												</chrone:isAuth>
												<chrone:isAuth authCode="900000104">
													<c:if test="${l.status ==1 }">
														&nbsp;&nbsp;<a href="#" onclick="sendCoupon('${l.id}')">手动发放</a>
													</c:if>
												</chrone:isAuth>
											</td>
										</tr>
									</c:forEach>
									<tr>
										<td colspan="13" align="left">查询结果：${page.rowTotal }条</td>
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
<form id="pageForm" action="${ctx }/couponMgr/list"  method="post" >
	<input type="hidden"  name="start" id="start"></input>
	<input type="hidden"  name="name" value="${coupon.name}"></input>
	<input type="hidden"  name="id" value="${coupon.id}"></input>
	<input type="hidden"  name="status" value="${coupon.status}"></input>
	<input type="hidden"  name="lotteryType" value="${coupon.lotteryType}"></input>
	<input type="hidden"  name="levelId" value="${coupon.levelId}"></input>
</form>
<input type="hidden"  id="closeTp"/>
<iframe name='hidden_frame' id="hidden_frame" style='display: none'></iframe>
</body>
</html>