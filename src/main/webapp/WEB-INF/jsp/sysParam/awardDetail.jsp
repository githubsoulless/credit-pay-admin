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
<script type="text/javascript" src="${ctx }/static/js/My97DatePicker/WdatePicker.js"></script>
<title>平台参数设置</title>
<style type="text/css">
.fieldset1 {
	padding: .35em .625em .75em;
	margin: 0 2px;
	border: 1px solid silver
}

.legend1 {
	padding: .5em;
	border: 0;
	width: auto
}
.margin10{
	margin: 10px 0px;
}
.color2500f9{
	color: #2500f9;
}
.colorRed{
	color: #F70909;
}
.width100{
	width: 100px;
}
.divbtn{
	margin-top: 20px;
}
</style>
<script language="javascript">
	(function() {
		var _skin, _lhgcore;
		var _search = window.location.search;
		if (_search) {
			_skin = _search.split('demoSkin=')[1];
		};

		document.write('<scr' + 'ipt src="${ctx}/static/js/lhgdialog.js?skin=' + (_skin || 'idialog') + '"></sc'+'ript>');
		window._isDemoSkin = !!_skin;
	})();

	function fastSearch() {
		document.getElementById("searchForm").submit();
	}

	function addClose() {
		if (document.getElementById("closeTp").value == "1") {
			document.getElementById("searchForm").submit();
		}
	}
	function callBack(result) {
		//hideWait();
		if (result == "success") {
			alert("操作成功!");
			//D.getElementById('closeTp').value = "1";
			//api.close();
		} else {
			alert("操作失败," + result + "!");
		}
	}
	function init() {
		changeProfitsType('${params.profits_type }')
		var result = "${message}";
		if (result != "") {
			callBack(result);
		}
	}
function changeProfitsType(val){
	if(val==1){
		$(".profitsType1").show();
		$(".profitsType2").hide();
	}else{
		$(".profitsType1").hide();
		$(".profitsType2").show();
	}
}
function awardRegTypeSel(obj){
	if(obj !="0"){
		$("#awardRegBeginTimeOuter").show();
	}else{
		$("#awardRegBeginTimeOuter").hide();
	}
}

function awardFastPayTypeSel(obj){
	if(obj=="2"){
		$("#awardFastPayTotal_outer").show();
	}else{
		$("#awardFastPayTotal_outer").hide();
	}
	if(obj !="0"){
		$("#awardFastPayBeginTimeOuter").show();
	}else{
		$("#awardFastPayBeginTimeOuter").hide();
	}
}

function awardWMFastPayTypeSel(obj){
	if(obj=="2"){
		$("#awardWMFastPayTotal_outer").show();
	}else{
		$("#awardWMFastPayTotal_outer").hide();
	}
	
	if(obj !="0"){
		$("#awardWMFastPayBeginTimeOuter").show();
	}else{
		$("#awardWMFastPayBeginTimeOuter").hide();
	}
}

function init_award_param(){
	
	$("#awardObject").val('${params.award_object}');
	
	$("#awardRegisterType").val('${params.award_register_type}');
	$("#awardRegisterDirUserAmount").val('${params.award_register_diruser_amount}');
	$("#awardRegisterAgentAmount").val('${params.award_register_agent_amount}');
	
	$("#awardFastPayType").val('${params.award_fastpay_type}');
	$("#awardFastPayDirUserAmount").val('${params.award_fastpay_diruser_amount}');
	$("#awardFastPayAgentAmount").val('${params.award_fastpay_agent_amount}');
	$("#awardFastPayTotal").val('${params.award_fastpay_total}');
	
	$("#awardWMFastPayType").val('${params.award_WMFastpay_type}');
	$("#awardWMFastPayDirUserAmount").val('${params.award_WMfastpay_diruser_amount}');
	$("#awardWMFastPayAgentAmount").val('${params.award_WMfastpay_agent_amount}');
	$("#awardWMFastPayTotal").val('${params.award_WMFastpay_total}');

	$("#awardRegBeginTime").val('${params.award_reg_begin_time}');
	$("#awardFastPayBeginTime").val('${params.award_fastpay_begin_time}');
	$("#awardWMFastPayBeginTime").val('${params.award_WMfastpay_begin_time}');
	
	awardRegTypeSel('${params.award_reg_type}')
	awardFastPayTypeSel('${params.award_fastpay_type}');
	awardWMFastPayTypeSel('${params.award_WMFastpay_type}');
	
}

$(function(){
	init_award_param();
})

</script>
</head>
<body onload="init()">
	<div class="page-content">
		<div class="row">
			<div class="col-xs-12">
				<form action="${ctx}/sysParam/update" id="searchForm" name="searchForm" method="post" class="form-inline" role="form">
					<input type="hidden" value="3" name="type"/>
					<!--奖励设置-->
					<fieldset class="fieldset1">
						<div style="color: #990000">提示：奖励制度由三种类型，保存设置实时生效，请慎重增加奖励规则。请参照奖励池预充值资金，由资金池余额不足导致奖励失败问题，无法补发奖励</div>
						<legend class="legend1">组合奖励设置</legend>
						
						 <div class="col-lg-4 col-md-6 margin10">
							<p style="font-weight: bold;">规则对象<span style="color: #990000">注：判断新旧用户依据为 用户注册时间 与 规则生成时间对比</span></p>
							<div style="margin-bottom: 10px;">
								规则对象：
								<select name="awardObject" id="awardObject">
									<option value="1">新用户</option>
									<option value="2">旧用户</option>
									<option value="3">新旧用户</option>
								</select>
							</div>
						</div>
						
						<div class="col-lg-4 col-md-6 margin10">
							<p style="font-weight: bold;">用户注册奖励</p>
							<div style="margin-bottom: 10px;">
								奖励类型：
								<select name="awardRegisterType" id="awardRegisterType" onchange="awardRegTypeSel(this.value)">
									<option value="0">无任何奖励</option>
									<option value="1">注册奖励</option>
									<option value="2">实名认证奖励</option>
								</select>
								<span id="awardRegBeginTimeOuter">&nbsp;&nbsp;开始生效时间<input type="text" id="awardRegBeginTime" value="" disabled="disabled"></span>
							</div>
							<div>
								直邀奖励：<input type="text" id="awardRegisterDirUserAmount" name="awardRegisterDirUserAmount" value="0" size="10"  oninput = "value=value.replace(/[^\d]/g,'')">元
								代理奖励：<input type="text" id="awardRegisterAgentAmount" name="awardRegisterAgentAmount" value="0" size="10"  oninput = "value=value.replace(/[^\d]/g,'')">元
							</div>
						</div>
						
						<div class="col-lg-4 col-md-6 margin10">
							<p style="font-weight: bold;">快捷刷卡消费奖励</p>
							<div style="margin-bottom: 10px;">
								奖励类型：
								<select name="awardFastPayType" id="awardFastPayType" onchange="awardFastPayTypeSel(this.value)">
									<option value="0">无任何奖励</option>
									<option value="1">首次刷卡奖励</option>
									<option value="2">累积刷卡奖励</option>
								</select>
								<span id="awardFastPayTotal_outer" style="display:none;">
									&nbsp;&nbsp;累积金额：<input type="text" id="awardFastPayTotal" name="awardFastPayTotal" value="0" size="10"  oninput = "value=value.replace(/[^\d]/g,'')">元
								</span>
								<span id="awardFastPayBeginTimeOuter">&nbsp;&nbsp;开始生效时间<input type="text" id="awardFastPayBeginTime" value="" disabled="disabled"></span>
								
							</div>
							<div>
								直邀奖励：<input type="text" id="awardFastPayDirUserAmount" name="awardFastPayDirUserAmount" value="0" size="10"  oninput = "value=value.replace(/[^\d]/g,'')">元
								代理奖励：<input type="text" id="awardFastPayAgentAmount" name="awardFastPayAgentAmount" value="0" size="10"  oninput = "value=value.replace(/[^\d]/g,'')">元
							</div>
						</div>
						
						<div class="col-lg-4 col-md-6 margin10">
							<p style="font-weight: bold;">完美刷卡消费奖励</p>
							<div style="margin-bottom: 10px;">
								奖励类型：
								<select name="awardWMFastPayType" id="awardWMFastPayType" onchange="awardWMFastPayTypeSel(this.value)">
									<option value="0">无任何奖励</option>
									<option value="1">首次刷卡奖励</option>
									<option value="2">累积刷卡奖励</option>
								</select>
								<span id="awardWMFastPayTotal_outer" style="display: none;">
									&nbsp;&nbsp;累积金额：<input type="text" id="awardWMFastPayTotal"  name="awardWMFastPayTotal" value="0" size="10"  oninput = "value=value.replace(/[^\d]/g,'')">元
								</span>
								<span id="awardWMFastPayBeginTimeOuter">&nbsp;&nbsp;开始生效时间<input type="text" id="awardWMFastPayBeginTime" value="" disabled="disabled" ></span>
							</div>
							<div>
								直邀奖励：<input type="text" id="awardWMFastPayDirUserAmount" name="awardWMFastPayDirUserAmount" value="0" size="10"  oninput = "value=value.replace(/[^\d]/g,'')">元
								代理奖励：<input type="text" id="awardWMFastPayAgentAmount" name="awardWMFastPayAgentAmount" value="0" size="10"  oninput = "value=value.replace(/[^\d]/g,'')">元
							</div>
						</div>
						<div class="align-center divbtn"></div>
						<chrone:isAuth authCode="600000201">
							<div align="center" style="clear: both"><button type="button" class="btn btn-primary"  onclick="fastSearch()">保存设置</button></div>
						</chrone:isAuth>
					</fieldset>
				</form>
			</div>
		</div>
		
		
		<div class="row">
			<div class="col-xs-12">
				<!-- PAGE CONTENT BEGINS -->
				<div class="row">
					<div class="col-xs-12">
						<div class="table-responsive" style="overflow: auto;">
							<table id="sample-table-1"
								class="table table1 table-striped table-bordered table-hover">
								<thead>
									<tr>
										<th>序号</th>
										<th>奖励对象</th>
										<th>注册奖励类型</th>
										<th>注册直邀奖励金额</th>
										<th>注册直属代理奖励金额</th>
										<th>注册奖励开始时间</th>
										<th>快捷奖励类型</th>
										<th>快捷直邀奖励金额</th>
										<th>快捷直属代理奖励金额</th>
										<th>快捷累积奖励金额</th>
										<th>快捷奖励开始时间</th>
										<th>完美奖励类型</th>
										<th>完美直邀奖励金额</th>
										<th>完美直属代理奖励金额</th>
										<th>完美累积奖励金额</th>
										<th>完美奖励开始时间</th>
										<th>规则创建时间</th>
										<th>规则创建人</th>
									</tr>
								</thead>

								<tbody>
								<c:forEach items="${list }" varStatus="i" var="l">
										<tr>
											<td>${l.id}</td>
											<td>
												<c:if test="${l.awardObject==1 }">新用户</c:if>
												<c:if test="${l.awardObject==2 }">老用户</c:if>
												<c:if test="${l.awardObject==3 }">新老用户</c:if>
											</td>
											<td>
												<c:if test="${l.regAwardType==0 }">无任何奖励</c:if>
												<c:if test="${l.regAwardType==1 }">注册奖励</c:if>
												<c:if test="${l.regAwardType==2 }">实名认证奖励</c:if>
											</td>
											<td>${l.regAwardDiruserMoney}</td>
											<td>${l.regAwardAgentMoney}</td>
											<td><fmt:formatDate value="${l.regAwardBeginTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
											<td>
												<c:if test="${l.fastpayAwardType==0 }">无任何奖励</c:if>
												<c:if test="${l.fastpayAwardType==1 }">首刷奖励</c:if>
												<c:if test="${l.fastpayAwardType==2 }">累积奖励</c:if>
											</td>
											<td>${l.fastpayAwardDiruserMoney}</td>
											<td>${l.fastpayAwardAgentMoney}</td>
											<td>${l.fastpayAwardTotal}</td>
											<td><fmt:formatDate value="${l.fastpayAwardBeginTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
											<td>
												<c:if test="${l.wanmeiAwardType==0 }">无任何奖励</c:if>
												<c:if test="${l.wanmeiAwardType==1 }">首刷奖励</c:if>
												<c:if test="${l.wanmeiAwardType==2 }">累积奖励</c:if>
											</td>
											<td>${l.wanmeiAwardDiruserMoney}</td>
											<td>${l.wanmeiAwardAgentMoney}</td>
											<td>${l.wanmeiAwardTotal}</td>
											<td><fmt:formatDate value="${l.wanmeiAwardBeginTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
											<td><fmt:formatDate value="${l.createTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
											<td>${l.createUser}</td>
										</tr>
								</c:forEach>
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
	<input type="hidden" id="closeTp" />
</body>

<script type="text/javascript">
$('#planRePayLessAmtType').change(function(){
	var type = $(this).val();
	if(type == 0){
		$('#fPlanRePayLessAmtType').html('元');
	}
	if(type == 1){
		$('#fPlanRePayLessAmtType').html('%');
	}
});
$('#planRePayMoreAmtType').change(function(){
	var type = $(this).val();
	if(type == 0){
		$('#fPlanRePayMoreAmtType').html('元');
	}
	if(type == 1){
		$('#fPlanRePayMoreAmtType').html('%');
	}
});
$('#fastRateType').change(function(){
	var type = $(this).val();
	if(type == 0){
		$('#fastRateValType').html('元');
	}
	if(type == 1){
		$('#fastRateValType').html('%');
	}
});

function copyAmt(id,id2){
	$('#'+id2).val($('#'+id).val());
}
</script>
</html>