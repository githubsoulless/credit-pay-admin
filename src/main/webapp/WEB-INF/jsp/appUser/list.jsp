<%@page import="net.chrone.creditpay.util.RedisClient"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="chrone" uri="/tag/chrone-taglib" %>
<%@taglib prefix="page" uri="/tag/mypage-taglib" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	request.setAttribute("ctx", request.getContextPath());
String regionStr = RedisClient.getByKey(RedisClient.CACHE_PREFIX_REGION_LIST);
request.setAttribute("regionList", regionStr);
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
<title>用户查询</title>
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

var region=eval('${regionList}');
function changeProv(provCd){
	$("#cityId").empty();
	$("#cityId").append("<option value=''>请选择</option>");
	$("#countyCd").empty();
	$("#countyCd").append("<option value=''>请选择</option>");
	for(var i=0;i<region.length;i++){
		if(provCd==region[i].provCd){
			var citys = region[i].citys;
			for(var j=0;j<citys.length;j++){
				$("#cityId").append("<option value='"+citys[j].cityCd+"'>"+citys[j].cityNm+"</option>");
			}
			return;
		}
	}
}
function changeCity(cityCd){
	$("#countyCd").empty();
	$("#countyCd").append("<option value=''>请选择</option>");
	for(var i=0;i<region.length;i++){
		if($("#provId").val()==region[i].provCd){
			var citys = region[i].citys;
			for(var j=0;j<citys.length;j++){
				if(cityCd==citys[j].cityCd){
					var regions = citys[j].regions;
					for(var a=0;a<regions.length;a++){
						$("#countyCd").append("<option value='"+regions[a].countyCd+"'>"+regions[a].countyNm+"</option>");
					}
				}
			}
			return;
		}
	}
}

function update(userId){
	var url = "url:${ctx}/appUser/update?type=toUpdate&userId="+userId+"&a="+encodeURIComponent(new Date());
	$.dialog({content:url ,
		        title:'修改用户信息',
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
function detail(userId){
	var url = "url:${ctx}/appUser/detail?userId="+userId+"&a="+encodeURIComponent(new Date());
	$.dialog({content:url ,
		        title:'用户详情',
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
function addClose(){
	if(document.getElementById("closeTp").value=="1"){
		document.getElementById("searchForm").submit();
	}
}
function exportExcel(){
	if(!confirm("是否导出记录到Excel?")){
		return;
	}
	var from=document.getElementById("searchForm");
	from.action='${ctx}/appUser/exportExcel';
	from.submit();
	from.action='${ctx}/appUser/list';
}
function init(){
	for(var i=0;i<region.length;i++){
		$("#provId").append("<option value='"+region[i].provCd+"'>"+region[i].provNm+"</option>");
	}
	if('${appuser.agentId}'!=''){
		for(var i=0;i<region.length;i++){
			if('${region.fyProvCd}'==region[i].provCd){
				var citys = region[i].citys;
				for(var j=0;j<citys.length;j++){
					$("#cityId").append("<option value='"+citys[j].cityCd+"'>"+citys[j].cityNm+"</option>");
					if('${region.fyRegionCd}'==citys[j].cityCd){
						var regions = citys[j].regions;
						for(var a=0;a<regions.length;a++){
							$("#countyCd").append("<option value='"+regions[a].countyCd+"'>"+regions[a].countyNm+"</option>");
						}
					}
				}
			}
		}
	}
	$("#provId").val('${region.fyProvCd}');
	$("#cityId").val('${region.fyRegionCd}');
	$("#countyCd").val('${region.fyCountyCd}');
}
</script>
</head>
<body onload="init()">
	<div class="page-content">
		<div class="page-header">
			<div class="row">
				<form action="${ctx}/appUser/list"  id="searchForm"   name="searchForm" method="post" class="form-inline" role="form">
					<div class="form-group">&nbsp;&nbsp;
						<label class="control-label" for="userId">用户账号：</label>
						<input  class="input-sm" type="text" id="userId"  name="userId" maxlength="15"  value="${appuser.userId}" /> 
					</div>
					<div class="form-group">&nbsp;&nbsp;
						<label class="control-label" for="userId">登录账号：</label>
						<input  class="input-sm" type="text" id="loginId"  name="loginId" maxlength="15"  value="${appuser.loginId}" /> 
					</div>
					<div class="form-group">&nbsp;&nbsp;
						<label class="control-label" for="userId">推荐人账号：</label>
						<input  class="input-sm" type="text" id="parentUserId"  name="parentUserId" maxlength="15"  value="${appuser.parentUserId}" /> 
					</div>
					<div class="form-group">&nbsp;&nbsp;
						<label class="control-label" for="userId">真实姓名：</label>
						<input  class="input-sm" type="text" id="accountName"  name="accountName" maxlength="15"  value="${appuser.accountName}" /> 
					</div>
					<div class="form-group">&nbsp;&nbsp;
						<label class="control-label" for="startDate">注册日期：</label>
						<input  class="Wdate input-sm" style="height: 30px" type="text" id="startDate"  name="startDate"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'endDate\')}'});"  value="${appuser.startDate}"  />&nbsp;- 
						<input  class="Wdate input-sm" style="height: 30px" type="text" id="endDate"  name="endDate"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'startDate\')}'});"  value="${appuser.endDate}"  /> 
					</div>
					<div class="form-group">&nbsp;&nbsp;
						<label class="control-label padding-left" for="levelId">&nbsp;&nbsp;&nbsp;&nbsp;等级：</label>
						<select id="levelId" name="levelId" class="input-sm">
	  					   	<option value="">全部</option>
	  					   	<c:forEach items="${levelList }" var="lvl">
	  					   			<option value="${lvl.levelId}" ${appuser.levelId==lvl.levelId?'selected="selected"':'' }>${lvl.levelName}</option>
							</c:forEach>
	  					</select> 
					</div>
					<div class="form-group">&nbsp;&nbsp;
						<label class="control-label padding-left" for="status">状态：</label>
						<select id="status" name="status" class="input-sm">
	  					   	<option value="">全部</option>
	  					   	<option value="0" ${appuser.status=='0'?'selected="selected"':'' }>正常</option>
	  					   	<option value="1" ${appuser.status=='1'?'selected="selected"':'' }>禁用</option>
	  					   </select> 
					</div>
					<div class="form-group">&nbsp;&nbsp;
						<label class="control-label" for="agentId">所属区县：</label>
						<select id="provId"  onchange="changeProv(this.value)">
							<option value="">请选择</option>
						</select>
						<select  id="cityId" onchange="changeCity(this.value)">
							<option value="">请选择</option>
						</select>
						<select id="countyCd" name="agentId">
							<option value="">请选择</option>
						</select>
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
						
						<chrone:isAuth authCode="100000105">
							&nbsp;&nbsp;<button type="button" class="btn btn-success" onclick="exportExcel()">导出</button>
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
								class="table1 table table-striped table-bordered table-hover">
								<thead>
									<tr>
										<th>序号</th>
										<th>用户账号</th>
										<th>登录账号</th>
										<th>真实姓名</th>
										<th>昵称</th>
										<th>等级</th>
										<th>状态</th>
										<th>实名认证</th>
										<th>注册时间</th>
										<th>最后登录时间</th>
										<th>信用卡数量</th>
										<th>直接下级用户</th>
										<th>所有下级用户</th>
										<th>推荐人</th>
										<th>所属区县</th>
<!-- 										<th>剩余体验计划次数</th> -->
										<th>操作</th>
									</tr>
								</thead>

								<tbody>
									<c:forEach items="${list }" varStatus="i" var="l">
										<tr>
											<td>${i.index+1}</td>
											<td>${l.userId}</td>
											<td>${l.loginId}</td>
											<td >${l.accountName}</td>
											<td >${l.merName}</td>
											<td >
												<c:forEach items="${levelList }" var="lvl">
													<c:if test="${lvl.levelId== l.levelId}">
														${lvl.levelName}
													</c:if>
												</c:forEach>
											</td>
											<td >
												<c:if test="${l.status==0 }">正常</c:if>
												<c:if test="${l.status==1 }">禁用</c:if>
											</td>
											<td>
												<c:if test="${l.certStatus == 0 }">未认证</c:if>
												<c:if test="${l.certStatus == 1 }">已认证</c:if>
												<c:if test="${l.certStatus == 2 }">认证失败</c:if>
											</td>
											<td><fmt:formatDate value="${l.rowCrtTs}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
											<td ><fmt:formatDate value="${l.lastLoginTs}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
											<td >${l.cardNum }</td>
											<td >
												<chrone:isAuth authCode="100000103"><a href="${ctx }/appUser/directUserList?parentUserId=${l.userId}"></chrone:isAuth>
												${l.directCount }
												<chrone:isAuth authCode="100000103"></a></chrone:isAuth>
											</td>
											<td >
												<chrone:isAuth authCode="100000104"><a href="${ctx }/appUser/subUserList?parentUserId=${l.userId}"></chrone:isAuth>
												${l.subUserCount}
												<chrone:isAuth authCode="100000104"></a></chrone:isAuth>
											</td>
											<td >${l.parentUserId }</td>
											<td >${l.countyNm }</td>
<%-- 											<td >${l.tyCount }</td> --%>
											<td>
												<c:if test="${l.userId != '13888888888' }">
													<chrone:isAuth authCode="100000102">
					                            		<a href="javascript:detail('${ l.userId}')">查看</a>&nbsp;&nbsp;
					                            	</chrone:isAuth>
					                            	<chrone:isAuth authCode="100000101">
					                            		<a href="javascript:update('${ l.userId}')">修改</a>&nbsp;&nbsp;
					                            	</chrone:isAuth>
				                            	</c:if>
				                            </td>
										</tr>
									</c:forEach>
									<tr>
										<td colspan="17" align="left">查询结果：${page.rowTotal }</td>
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
<form id="pageForm" action="${ctx }/appUser/list"  method="post" >
	<input type="hidden"  name="start" id="start"></input>
	<input type="hidden"  name="userId" value="${appuser.userId}"></input>
	<input type="hidden"  name="parentUserId" value="${appuser.parentUserId}"></input>
	<input type="hidden"  name="loginId" value="${appuser.loginId}"></input>
	<input type="hidden"  name="accountName" value="${appuser.accountName}"></input>
	<input type="hidden"  name="levelId" value="${appuser.levelId}"></input>
	<input type="hidden"  name="status" value="${appuser.status}"></input>
	<input type="hidden"  name="certStatus" value="${appuser.certStatus}"></input>
	<input type="hidden"  name="startDate" value="${appuser.startDate}"></input>
	<input type="hidden"  name="endDate" value="${appuser.endDate}"></input>
	<input type="hidden"  name="agentId" value="${appuser.agentId}"></input>
	<input type="hidden"  name="agentLevel" value="${appuser.agentLevel}"></input>
</form>
<input type="hidden"  id="closeTp"/>
</body>
</html>