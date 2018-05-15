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
<title>还款参数设置</title>
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
		var reg = /^[0-9]+(.[0-9]{0,2})?$/;
		if(!reg.test($("#transPayMaxAmt").val())) {
			alert('请输入正确的单笔消费最大金额，最多保留两位小数');
			$("#transPayMaxAmt").focus();
			return;
		}
		if(!reg.test($("#planRePayLessAmt").val())) {
			alert('请输入正确的单个还款计划最小金额，最多保留两位小数');
			$("#planRePayLessAmt").focus();
			return;
		}
		if(!reg.test($("#planRePayMoreAmt").val())) {
			alert('请输入正确的单个还款计划最大金额，最多保留两位小数');
			$("#planRePayMoreAmt").focus();
			return;
		}
		if(!reg.test($("#profitsDirectUserFee").val())) {
			alert('请输入正确的直接推荐人分润比率，最多保留两位小数');
			$("#profitsDirectUserFee").focus();
			return;
		}
		if(!reg.test($("#profitsInDirectUserFee").val())) {
			alert('请输入正确的间接推荐人分润比率，最多保留两位小数');
			$("#profitsInDirectUserFee").focus();
			return;
		}
		if(!reg.test($("#profitsUpUserFee").val())) {
			alert('请输入正确的上上上级推荐人分润比率，最多保留两位小数');
			$("#profitsUpUserFee").focus();
			return;
		}
		if(!reg.test($("#profitsLevelOneAgentBase").val())) {
			alert('请输入正确的一级代理商分润比率，最多保留两位小数');
			$("#profitsLevelOneAgentBase").focus();
			return;
		}
		if(!reg.test($("#profitsLevelTwoAgentBase").val())) {
			alert('请输入正确的二级代理商分润比率，最多保留两位小数');
			$("#profitsLevelTwoAgentBase").focus();
			return;
		}
		if(!reg.test($("#profitsLevelThreeAgentBase").val())) {
			alert('请输入正确的三级代理商分润比率，最多保留两位小数');
			$("#profitsLevelThreeAgentBase").focus();
			return;
		}
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
</script>
</head>
<body onload="init()">
	<div class="page-content">
		<div class="row">
			<div class="col-xs-12">
				<form action="${ctx}/sysParam/update" id="searchForm" name="searchForm" method="post" class="form-inline" role="form">
					<input type="hidden" value="1" name="type"/>
						<!--制定计划参数-->
					<fieldset class="fieldset1">
						<legend class="legend1">计划参数</legend>
						<div class="input-group col-lg-6 col-md-6 margin10">
							交易允许执行时间：
							<input class="Wdate input-xs" style="height: 30px" type="text" id="transStartTime" name="transStartTime"
								onclick="WdatePicker({dateFmt:'HH:mm:ss',maxDate:'#F{$dp.$D(\'transEndTime\')}'});"
								value="${params.trans_start_time}" /> &nbsp;-&nbsp;
								<input class="Wdate input-xs" style="height: 30px" type="text" id="transEndTime" name="transEndTime"
								onclick="WdatePicker({dateFmt:'HH:mm:ss',minDate:'#F{$dp.$D(\'transStartTime\')}'});"
								value="${params.trans_end_time}" />
						</div>
						<div class="col-lg-3 col-md-6 margin10">
							单笔消费最大金额：<input type="text" class="input-sm" id="transPayMaxAmt" name="transPayMaxAmt" value="<fmt:formatNumber value="${params.trans_pay_max_amt }" type="currency" pattern="0.00"/>" 
												onkeypress="if(!this.value.match(/^[/+/-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" 
												onkeyup="if(!this.value.match(/^[/+/-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" 
												onblur="if(!this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?|\.\d*?)?$/))this.value=this.o_value;else{if(this.value.match(/^\.\d+$/))this.value=0+this.value;if(this.value.match(/^\.$/))this.value=0;this.o_value=this.value}">
											<span class=""> 元</span>
						</div>
						<div class="col-lg-3 col-md-12 margin10">
							单卡单日最多消费：<input type="text" class="input-sm" id="transCardPayMaxCount" name="transCardPayMaxCount" value="${params.trans_card_pay_max_count }" 
												onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')">
											<span class=""> 笔</span>
						</div>
						<div class="col-lg-12 col-md-12 margin10">
							单个还款计划金额≤ <input type="text" class="input-sm" id="planRePayLessAmt" name="planRePayLessAmt" value="<fmt:formatNumber value="${params.plan_repay_less_amt }" type="currency" pattern="0.00"/>" 
												onkeypress="if(!this.value.match(/^[/+/-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" 
												onkeyup="if(!this.value.match(/^[/+/-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" 
												onblur="if(!this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?|\.\d*?)?$/))this.value=this.o_value;else{if(this.value.match(/^\.\d+$/))this.value=0+this.value;if(this.value.match(/^\.$/))this.value=0;this.o_value=this.value}"> 元时，信用卡内至少留存
								<select name="planRePayLessAmtType" id="planRePayLessAmtType" class="input-sm width100">
									<option value="0" <c:if test="${params.plan_repay_less_amt_type == 0 }">selected="selected"</c:if>>金额</option>
									<option value="1" <c:if test="${params.plan_repay_less_amt_type == 1 }">selected="selected"</c:if>>比例</option>
								</select>
								<input id="planCardLessBalance" name="planCardLessBalance" value="<fmt:formatNumber value="${params.plan_card_less_balance }" type="currency" pattern="0.00"/>" type="text" class="input-sm" 
									onkeypress="if(!this.value.match(/^[/+/-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" 
									onkeyup="if(!this.value.match(/^[/+/-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" 
									onblur="if(!this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?|\.\d*?)?$/))this.value=this.o_value;else{if(this.value.match(/^\.\d+$/))this.value=0+this.value;if(this.value.match(/^\.$/))this.value=0;this.o_value=this.value}"> 
								<span id="fPlanRePayLessAmtType">
									<c:if test="${params.plan_repay_less_amt_type == 0 }">元</c:if>
									<c:if test="${params.plan_repay_less_amt_type == 1 }">%</c:if>
								</span>
						</div>
						<div class="col-lg-12 col-md-12 margin10">
							单个还款计划金额> <input type="text" class="input-sm" id="planRePayMoreAmt" name="planRePayMoreAmt" value="<fmt:formatNumber value="${params.plan_repay_more_amt }" type="currency" pattern="0.00"/>" 
												onkeypress="if(!this.value.match(/^[/+/-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" 
												onkeyup="if(!this.value.match(/^[/+/-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" 
												onblur="if(!this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?|\.\d*?)?$/))this.value=this.o_value;else{if(this.value.match(/^\.\d+$/))this.value=0+this.value;if(this.value.match(/^\.$/))this.value=0;this.o_value=this.value}"> 元时，信用卡内至少留存
								<select name="planRePayMoreAmtType" id="planRePayMoreAmtType" class="input-sm width100">
									<option value="0" <c:if test="${params.plan_repay_more_amt_type == 0 }">selected="selected"</c:if>>金额</option>
									<option value="1" <c:if test="${params.plan_repay_more_amt_type == 1 }">selected="selected"</c:if>>比例</option>
								</select>
								<input id="planCardMoreBalance" name="planCardMoreBalance" value="<fmt:formatNumber value="${params.plan_card_more_balance }" type="currency" pattern="0.00"/>" type="text" class="input-sm" 
											onkeypress="if(!this.value.match(/^[/+/-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" 
											onkeyup="if(!this.value.match(/^[/+/-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" 
											onblur="if(!this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?|\.\d*?)?$/))this.value=this.o_value;else{if(this.value.match(/^\.\d+$/))this.value=0+this.value;if(this.value.match(/^\.$/))this.value=0;this.o_value=this.value}"> 
								<span id="fPlanRePayMoreAmtType">
									<c:if test="${params.plan_repay_more_amt_type == 0 }">元</c:if>
									<c:if test="${params.plan_repay_more_amt_type == 1 }">%</c:if>
								</span>
						</div>
						<div class="col-lg-12 col-md-12 margin10">
						每个自然月用户有&nbsp;<input type="text" size="4" class="input-sm"  id="closePlanCount" name="closePlanCount" value="${params.close_plan_count }" 
						onkeypress="if(!this.value.match(/^[/+/-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" 
												onkeyup="if(!this.value.match(/^[/+/-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" 
												onblur="if(!this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?|\.\d*?)?$/))this.value=this.o_value;else{if(this.value.match(/^\.\d+$/))this.value=0+this.value;if(this.value.match(/^\.$/))this.value=0;this.o_value=this.value}"
						/>&nbsp;次主动终止计划的机会
						<span style="color:blue;">（次数修改后，于下月1日起生效；填写-1表示不限制终止操作次数）</span>
						</div>
						<div class="col-lg-12 col-md-12 margin10">
						终止计划扣除违约金 = 计划剩余服务费 ×
						<input type="text" size="4" class="input-sm" id="closePlanFee" name="closePlanFee" value="<fmt:formatNumber value="${params.close_plan_fee }" type="currency" pattern="0.00"/>" 
												onkeypress="if(!this.value.match(/^[/+/-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" 
												onkeyup="if(!this.value.match(/^[/+/-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" 
												onblur="if(!this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?|\.\d*?)?$/))this.value=this.o_value;else{if(this.value.match(/^\.\d+$/))this.value=0+this.value;if(this.value.match(/^\.$/))this.value=0;this.o_value=this.value}"> %
						</div>
					</fieldset>
					<!--还款分润参数-用户-->
					<fieldset class="fieldset1">
						<legend class="legend1">还款分润参数-用户</legend>
						<div class="col-lg-10 col-md-10 margin10">
							<input type="radio" name="profitsType" value="1" ${ params.profits_type==1?'checked="checked"':''} onclick="changeProfitsType(this.value)">方案1：推荐关系固定分润&nbsp;&nbsp;
							<input type="radio" name="profitsType" value="2" ${ params.profits_type==2?'checked="checked"':''} onclick="changeProfitsType(this.value)">方案2：比较等级差值分润
						</div>
						<div class="col-lg-12 col-md-12 margin10 profitsType2">
						规则说明：只有推荐人等级胜出才可以得分润，一笔交易最多只有两个用户得到返润。假定，平台发展了用户A，A发展了B，B发展了C，C制定还款计划，
						<br/>
						◆ 两个用户得分润：C &lt; B &lt; A，<span class="color2500f9">B分润金额 = 消费金额×(C等级费率-B等级费率)，A分润金额 = 消费金额×(B等级费率-A等级费率)</span>。
						<br/>
						◆ 无用户分润：① C为最高等级；② A和B ≦ C。
						<br/>
						◆ 一个用户得分润：① B ≦ C &lt; A，此时B无分润，<span class="color2500f9">A分润金额 = 消费金额×(C等级费率-A等级费率)</span>；
						② B > C，且A ≦ B，此时A无分润，<span class="color2500f9">B分润金额 = 消费金额×(C等级费率-B等级费率)</span>。
						</div>
						<div class="col-lg-4 col-md-4 margin10 profitsType1">
							直接推荐人分润比率：<input type="text" class="input-sm" id="profitsDirectUserFee" name="profitsDirectUserFee" value="<fmt:formatNumber value="${params.profits_direct_user_fee }" type="currency" pattern="0.00"/>" 
												onkeypress="if(!this.value.match(/^[/+/-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" 
												onkeyup="if(!this.value.match(/^[/+/-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" 
												onblur="if(!this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?|\.\d*?)?$/))this.value=this.o_value;else{if(this.value.match(/^\.\d+$/))this.value=0+this.value;if(this.value.match(/^\.$/))this.value=0;this.o_value=this.value}"> %
						</div>
						<div class="col-lg-4 col-md-8 margin10 profitsType1">
							间接推荐人分润比率：<input type="text" class="input-sm" id="profitsInDirectUserFee" name="profitsInDirectUserFee" value="<fmt:formatNumber value="${params.profits_indirect_user_fee }" type="currency" pattern="0.00"/>" 
												onkeypress="if(!this.value.match(/^[/+/-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" 
												onkeyup="if(!this.value.match(/^[/+/-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" 
												onblur="if(!this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?|\.\d*?)?$/))this.value=this.o_value;else{if(this.value.match(/^\.\d+$/))this.value=0+this.value;if(this.value.match(/^\.$/))this.value=0;this.o_value=this.value}"> %
						</div>
						<div class="col-lg-4 col-md-12 margin10 profitsType1">
							上上上级推荐人分润比率：<input type="text" class="input-sm" id="profitsUpUserFee" name="profitsUpUserFee" value="<fmt:formatNumber value="${params.profits_up_user_fee }" type="currency" pattern="0.00"/>" 
													onkeypress="if(!this.value.match(/^[/+/-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" 
													onkeyup="if(!this.value.match(/^[/+/-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" 
													onblur="if(!this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?|\.\d*?)?$/))this.value=this.o_value;else{if(this.value.match(/^\.\d+$/))this.value=0+this.value;if(this.value.match(/^\.$/))this.value=0;this.o_value=this.value}"> %
						</div>
						<div class="col-lg-12 col-md-12 margin10 color2500f9 profitsType1">
							计算还款分润的公式：分润金额 = 计划还款总额 * 各级推荐人的分润比率
						</div>
						<div class="col-lg-12 col-md-12 margin10 colorRed">
							<img src="${ctx}/static/css/easyui/bootstrap/images/validatebox_warning.png" windth='20' height='20' >方案调整并保存后，即时生效，请谨慎操作
						</div>
					</fieldset>
					<!--还款分润参数-代理-->
					<fieldset class="fieldset1">
						<legend class="legend1">还款分润参数-代理</legend>
						<div class="col-lg-4 col-md-4 margin10">
							一级代理成本基数：<input type="text" class="input-sm" id="profitsLevelOneAgentBase" name="profitsLevelOneAgentBase" value="<fmt:formatNumber value="${params.profits_level_one_agent_base }" type="currency" pattern="0.00"/>" 
												onkeypress="if(!this.value.match(/^[/+/-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" 
												onkeyup="if(!this.value.match(/^[/+/-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" 
												onblur="if(!this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?|\.\d*?)?$/))this.value=this.o_value;else{if(this.value.match(/^\.\d+$/))this.value=0+this.value;if(this.value.match(/^\.$/))this.value=0;this.o_value=this.value}"> %
						</div>
						<div class="col-lg-4 col-md-4 margin10">
							二级代理成本基数：<input type="text" class="input-sm" id="profitsLevelTwoAgentBase" name="profitsLevelTwoAgentBase" value="<fmt:formatNumber value="${params.profits_level_two_agent_base }" type="currency" pattern="0.00"/>" 
												onkeypress="if(!this.value.match(/^[/+/-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" 
												onkeyup="if(!this.value.match(/^[/+/-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" 
												onblur="if(!this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?|\.\d*?)?$/))this.value=this.o_value;else{if(this.value.match(/^\.\d+$/))this.value=0+this.value;if(this.value.match(/^\.$/))this.value=0;this.o_value=this.value}"> %
						</div>
						<div class="col-lg-4 col-md-4 margin10">
							三级代理成本基数：<input type="text" class="input-sm" id="profitsLevelThreeAgentBase" name="profitsLevelThreeAgentBase" value="<fmt:formatNumber value="${params.profits_level_three_agent_base }" type="currency" pattern="0.00"/>" 
												onkeypress="if(!this.value.match(/^[/+/-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" 
												onkeyup="if(!this.value.match(/^[/+/-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" 
												onblur="if(!this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?|\.\d*?)?$/))this.value=this.o_value;else{if(this.value.match(/^\.\d+$/))this.value=0+this.value;if(this.value.match(/^\.$/))this.value=0;this.o_value=this.value}"> %
						</div>
						<div class="col-lg-12 col-md-12 margin10 color2500f9">
							代理分润采用固定差额方式
						</div>
					</fieldset>
					<div class="align-center divbtn">
						<chrone:isAuth authCode="600000701">
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