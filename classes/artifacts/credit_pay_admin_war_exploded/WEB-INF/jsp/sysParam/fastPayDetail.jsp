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
		changeFastProfitsType('${params.fast_profits_type }')
		var result = "${message}";
		if (result != "") {
			callBack(result);
		}
	}
function changeFastProfitsType(val){
	if(val==1){
		$(".profitsType1").show();
		$(".profitsType2").hide();
	}else{
		$(".profitsType1").hide();
		$(".profitsType2").show();
	}
}
</script>
</head>
<body onload="init()">
	<div class="page-content">
		<div class="row">
			<div class="col-xs-12">
				<form action="${ctx}/sysParam/update" id="searchForm" name="searchForm" method="post" class="form-inline" role="form">
					<input type="hidden" value="2" name="type"/>
					<!--快捷消费参数-->
					<fieldset class="fieldset1">
						<legend class="legend1">快捷消费参数</legend>
						<div class="input-group col-lg-4 col-md-4 margin10">
							交易开放时间段：
							<input size="10" class="Wdate input-xs" style="height: 30px" type="text" id="fastStartTime" name="fastStartTime"
								onclick="WdatePicker({dateFmt:'HH:mm:ss',maxDate:'#F{$dp.$D(\'fastEndTime\')}'});"
								value="${params.fast_start_time}" /> &nbsp;-&nbsp;
								<input size="10" class="Wdate input-xs" style="height: 30px" type="text" id="fastEndTime" name="fastEndTime"
								onclick="WdatePicker({dateFmt:'HH:mm:ss',minDate:'#F{$dp.$D(\'fastStartTime\')}'});"
								value="${params.fast_end_time}" />
						</div>
						<div class="input-group col-lg-4 col-md-4 margin10">
							同张信用卡单日最多交易：<input type="text" size="10" class="input-sm" id="fastCreditPayDayCount" name="fastCreditPayDayCount" value="${params.fast_credit_pay_day_count }" 
												onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')">
											<span class=""> 笔</span>
						</div>
						<div class="input-group col-lg-4 col-md-4 margin10">
							同张结算卡单日最多结算：<input type="text" size="10" class="input-sm" id="fastSettlePayDayCount" name="fastSettlePayDayCount" value="${params.fast_settle_pay_day_count }" 
												onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')">
											<span class=""> 笔</span>
						</div>
					</fieldset>
					<!--快捷分润参数-用户-->
					<fieldset class="fieldset1">
						<legend class="legend1">快捷分润参数-用户</legend>
						<div class="col-lg-10 col-md-10 margin10">
							<input type="radio" name="fastProfitsType" value="1" ${ params.fast_profits_type==1?'checked="checked"':''} onclick="changeFastProfitsType(this.value)">方案1：推荐关系固定分润&nbsp;&nbsp;
							<input type="radio" name="fastProfitsType" value="2" ${ params.fast_profits_type==2?'checked="checked"':''} onclick="changeFastProfitsType(this.value)">方案2：比较等级差值分润
						</div>
						<div class="col-lg-12 col-md-12 margin10 profitsType2">
						规则说明：只有推荐人等级胜出才可以得分润，一笔交易最多只有两个用户得到返润。假定，平台发展了用户A，A发展了B，B发展了C，C完成了一笔快捷消费，
						<br/>
						◆ 两个用户得分润：C &lt; B &lt; A，<span class="color2500f9">B分润金额 = 消费金额×(C等级费率-B等级费率)，A分润金额 = 消费金额×(B等级费率-A等级费率)</span>。
						<br/>
						◆ 无用户分润：① C为最高等级；② A和B ≦ C。
						<br/>
						◆ 一个用户得分润：① B ≦ C &lt; A，此时B无分润，<span class="color2500f9">A分润金额 = 消费金额×(C等级费率-A等级费率)</span>；
						② B > C，且A ≦ B，此时A无分润，<span class="color2500f9">B分润金额 = 消费金额×(C等级费率-B等级费率)</span>。
						</div>
						<div class="col-lg-4 col-md-4 margin10 profitsType1">
							直接推荐人分润比率：<input type="text" class="input-sm" id="fastProfitsDirectUserFee" name="fastProfitsDirectUserFee" value="<fmt:formatNumber value="${params.fast_profits_direct_user_fee }" type="currency" pattern="0.00"/>" 
												onkeypress="if(!this.value.match(/^[/+/-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" 
												onkeyup="if(!this.value.match(/^[/+/-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" 
												onblur="if(!this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?|\.\d*?)?$/))this.value=this.o_value;else{if(this.value.match(/^\.\d+$/))this.value=0+this.value;if(this.value.match(/^\.$/))this.value=0;this.o_value=this.value}"> %
						</div>
						<div class="col-lg-4 col-md-8 margin10 profitsType1">
							间接推荐人分润比率：<input type="text" class="input-sm" id="fastProfitsInDirectUserFee" name="fastProfitsInDirectUserFee" value="<fmt:formatNumber value="${params.fast_profits_indirect_user_fee }" type="currency" pattern="0.00"/>" 
												onkeypress="if(!this.value.match(/^[/+/-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" 
												onkeyup="if(!this.value.match(/^[/+/-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" 
												onblur="if(!this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?|\.\d*?)?$/))this.value=this.o_value;else{if(this.value.match(/^\.\d+$/))this.value=0+this.value;if(this.value.match(/^\.$/))this.value=0;this.o_value=this.value}"> %
						</div>
						<div class="col-lg-4 col-md-12 margin10 profitsType1">
							上上上级推荐人分润比率：<input type="text" class="input-sm" id="fastProfitsUpUserFee" name="fastProfitsUpUserFee" value="<fmt:formatNumber value="${params.fast_profits_up_user_fee }" type="currency" pattern="0.00"/>" 
													onkeypress="if(!this.value.match(/^[/+/-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" 
													onkeyup="if(!this.value.match(/^[/+/-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" 
													onblur="if(!this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?|\.\d*?)?$/))this.value=this.o_value;else{if(this.value.match(/^\.\d+$/))this.value=0+this.value;if(this.value.match(/^\.$/))this.value=0;this.o_value=this.value}"> %
						</div>
						<div class="col-lg-12 col-md-12 margin10 color2500f9 profitsType1">
							计算快捷分润的公式：分润金额 = 快捷消费总额 * 各级推荐人的分润比率
						</div>
						<div class="col-lg-12 col-md-12 margin10 colorRed">
							<img src="${ctx}/static/css/easyui/bootstrap/images/validatebox_warning.png" windth='20' height='20' >方案调整并保存后，即时生效，请谨慎操作
						</div>
					</fieldset>
					<!--快捷分润参数-代理-->
					<fieldset class="fieldset1">
						<legend class="legend1">快捷分润参数-代理</legend>
						<div class="col-lg-4 col-md-4 margin10">
							一级代理成本基数：<input type="text" class="input-sm" id="fastProfitsLevelOneAgentBase" name="fastProfitsLevelOneAgentBase" value="<fmt:formatNumber value="${params.fast_profits_level_one_agent_base }" type="currency" pattern="0.00"/>" 
												onkeypress="if(!this.value.match(/^[/+/-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" 
												onkeyup="if(!this.value.match(/^[/+/-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" 
												onblur="if(!this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?|\.\d*?)?$/))this.value=this.o_value;else{if(this.value.match(/^\.\d+$/))this.value=0+this.value;if(this.value.match(/^\.$/))this.value=0;this.o_value=this.value}"> %
						</div>
						<div class="col-lg-4 col-md-4 margin10">
							二级代理成本基数：<input type="text" class="input-sm" id="fastProfitsLevelTwoAgentBase" name="fastProfitsLevelTwoAgentBase" value="<fmt:formatNumber value="${params.fast_profits_level_two_agent_base }" type="currency" pattern="0.00"/>" 
												onkeypress="if(!this.value.match(/^[/+/-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" 
												onkeyup="if(!this.value.match(/^[/+/-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" 
												onblur="if(!this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?|\.\d*?)?$/))this.value=this.o_value;else{if(this.value.match(/^\.\d+$/))this.value=0+this.value;if(this.value.match(/^\.$/))this.value=0;this.o_value=this.value}"> %
						</div>
						<div class="col-lg-4 col-md-4 margin10">
							三级代理成本基数：<input type="text" class="input-sm" id="fastProfitsLevelThreeAgentBase" name="fastProfitsLevelThreeAgentBase" value="<fmt:formatNumber value="${params.fast_profits_level_three_agent_base }" type="currency" pattern="0.00"/>" 
												onkeypress="if(!this.value.match(/^[/+/-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" 
												onkeyup="if(!this.value.match(/^[/+/-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" 
												onblur="if(!this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?|\.\d*?)?$/))this.value=this.o_value;else{if(this.value.match(/^\.\d+$/))this.value=0+this.value;if(this.value.match(/^\.$/))this.value=0;this.o_value=this.value}"> %
						</div>
						<div class="col-lg-12 col-md-12 margin10 color2500f9">
							代理分润采用固定差额方式
						</div>
					</fieldset>
					
					<div class="align-center divbtn">
						<chrone:isAuth authCode="600000801">
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