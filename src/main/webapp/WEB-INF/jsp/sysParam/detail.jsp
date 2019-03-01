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
function awardFastPayTypeSel(obj){
	if(obj=="2"){
		$("#awardFastPayTotal_outer").show();
	}else{
		$("#awardFastPayTotal_outer").hide();
	}
}

function awardWMFastPayTypeSel(obj){
	if(obj=="2"){
		$("#awardWMFastPayTotal_outer").show();
	}else{
		$("#awardWMFastPayTotal_outer").hide();
	}
}

function init_award_param(){
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
	
	$("#awardFastPayBeginTime").val('${params.award_fastpay_begin_time}');
	$("#awardWMFastPayBeginTime").val('${params.award_WMfastpay_begin_time}');
	
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
					<input type="hidden" value="0" name="type"/>
					<!-- 抽奖次数设置 -->
					<fieldset class="fieldset1">
						<legend class="legend1">抽奖次数设置</legend>
						<div class="col-lg-4 col-md-6 margin10">
							新用户注册奖励：<input type="text" class="input-sm" id="lotteryTimesFNewUser" name="lotteryTimesFNewUser" value="${params.lottery_times_f_new_user }" 
												onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')">次抽奖机会
						</div>
						<div class="col-lg-4 col-md-6 margin10">
							每执行完1个还款计划，奖励：<input type="text" class="input-sm" id="lotteryTimesFOneRePayPlan" name="lotteryTimesFOneRePayPlan" value="${params.lottery_times_f_one_repay_play }" 
												onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')">次抽奖机会
						</div>
						<div class="col-lg-4 col-md-6 margin10">
							每成功绑定一张信用卡，奖励：<input type="text" class="input-sm" id="lotteryTimesFBindOneCard" name="lotteryTimesFBindOneCard" value="${params.lottery_times_f_bind_one_card }" 
												onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')">次抽奖机会
						</div>
						<div class="col-lg-4 col-md-6 margin10">
							每推荐1个新用户，奖励：<input type="text" class="input-sm" id="lotteryTimesFRecomOneUser" name="lotteryTimesFRecomOneUser" value="${params.lottery_times_f_recom_one_user }" 
												onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')">次抽奖机会
						</div>
					</fieldset>
					<!--短信模板设置-->
					<fieldset class="fieldset1">
						<legend class="legend1">短信模板设置</legend>
						<div class="col-lg-4 col-md-6 margin10">
							用户注册：<textarea rows="5" cols="50" id="templateUserRegist" name="templateUserRegist">${params.template_user_regist }</textarea>
						</div>
						<div class="col-lg-4 col-md-6 margin10">
							重设密码：<textarea rows="5" cols="50" id="templateResetPassword" name="templateResetPassword">${params.template_reset_password }</textarea>
						</div>
						<div class="col-lg-4 col-md-12 margin10">
							计划失败通知：<textarea rows="5" cols="50" id="templatePlanFaildNotice" name="templatePlanFaildNotice">${params.template_plan_faild_notice }</textarea>	
						</div>
					</fieldset>
					
					<!--奖励设置-->
					<fieldset class="fieldset1">
						<legend class="legend1">组合奖励设置</legend>
						<div class="col-lg-4 col-md-6 margin10">
							<p style="font-weight: bold;">用户注册奖励</p>
							<div style="margin-bottom: 10px;">
								奖励类型：
								<select name="awardRegisterType" id="awardRegisterType">
									<option value="0">无任何奖励</option>
									<option value="1">注册奖励</option>
									<option value="2">实名认证奖励</option>
								</select>
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
								&nbsp;&nbsp;开始生效时间<input type="text" id="awardFastPayBeginTime" value="" disabled="disabled">
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
								&nbsp;&nbsp;开始生效时间<input type="text" id="awardWMFastPayBeginTime" value="" disabled="disabled" >
							</div>
							<div>
								直邀奖励：<input type="text" id="awardWMFastPayDirUserAmount" name="awardWMFastPayDirUserAmount" value="0" size="10"  oninput = "value=value.replace(/[^\d]/g,'')">元
								代理奖励：<input type="text" id="awardWMFastPayAgentAmount" name="awardWMFastPayAgentAmount" value="0" size="10"  oninput = "value=value.replace(/[^\d]/g,'')">元
							</div>
						</div>
					</fieldset>
					
					<div class="align-center divbtn">
						<chrone:isAuth authCode="600000201">
							<div align="center"><button type="button" class="btn btn-primary"  onclick="fastSearch()">保存设置</button></div>
						</chrone:isAuth>
					</div>
				</form>
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