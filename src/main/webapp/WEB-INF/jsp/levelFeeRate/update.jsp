<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="chrone" uri="/tag/chrone-taglib" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<link href="${ctx }/static/css/alert.css" rel="stylesheet" type="text/css" />
<title></title>
<style type="text/css">
input{
	width:55px;
	text-align: right;
	margin-left: 8px;
	margin-right: 6px;
}
table tr td{
	padding: 10px 2px;
}
</style>
<script type="text/javascript">
(function() {
	var _skin, _lhgcore;
	var _search = window.location.search;
	if (_search) {
		_skin = _search.split('demoSkin=')[1];
	};
	
	document.write('<scr'+'ipt src="${ctx}/static/js/lhgdialog.js?skin=' + (_skin || 'idialog') +'"></sc'+'ript>');
	window._isDemoSkin = !!_skin;
})();
	var api = frameElement.api, W = api.opener, D = W.document; // api.opener 为载加lhgdialog.min.js文件的页面的window对象
	function cancelVal() {
		api.close();
	}
	function subForm() {
		if($('#feeRate0').val() == 0){
			$('#upperlimit0').val('0');
			$('#payFee0').val('0');
		}
		if($('#feeRate1').val() == 0){
			$('#upperlimit1').val('0');
			$('#payFee1').val('0');
		}
		if($('#feeRate2').val() == 0){
			$('#upperlimit2').val('0');
			$('#payFee2').val('0');
		}
		if($('#feeRate3').val() == 0){
			$('#upperlimit3').val('0');
			$('#payFee3').val('0');
		}
		var reg = /^[0-9]+(.[0-9]{0,2})?$/;

		if(!reg.test($("#feeRate_reapal").val())) {
			alert('请输入正确的还款服务费，最多保留两位小数');
			$("#feeRate").focus();
			return;
		}
		
	
		if(!reg.test($("#feeRate_reapalfast").val())) {
			alert('请输入RB通道正确的费率，最多保留两位小数');
			$("#feeRate_reapalfast").focus();
			return;
		}
		if(!reg.test($("#feeRate_yitong").val())) {
			alert('请输入YT通道正确的费率，最多保留两位小数');
			$("#feeRate_yitong").focus();
			return;
		}
		
		if(!reg.test($("#feeRate_huifu").val())) {
			alert('请输入HF通道正确的费率，最多保留两位小数');
			$("#feeRate_huifu").focus();
			return;
		}
	
	
		if(!reg.test($("#upperlimit_reapalfast").val())) {
			alert('请输入RB正确的封顶手续费，最多保留两位小数');
			$("#upperlimit_reapalfast").focus();
			return;
		}
		if(!reg.test($("#upperlimit_yitong").val())) {
			alert('请输入YT正确的封顶手续费，最多保留两位小数');
			$("#upperlimit_yitong").focus();
			return;
		}
		if(!reg.test($("#upperlimit_huifu").val())) {
			alert('请输入HF正确的封顶手续费，最多保留两位小数');
			$("#upperlimit_huifu").focus();
			return;
		}
		
		
		if(!reg.test($("#payFee_reapal").val())) {
			alert('请输入RB完美还款正确的单笔结算固定手续费，最多保留两位小数');
			$("#payFee_reapalfast").focus();
			return;
		}
		if(!reg.test($("#payFee_reapalfast").val())) {
			alert('请输入RB正确的单笔结算固定手续费，最多保留两位小数');
			$("#payFee_reapalfast").focus();
			return;
		}
		if(!reg.test($("#payFee_yitong").val())) {
			alert('请输入YT正确的单笔结算固定手续费，最多保留两位小数');
			$("#payFee_yitong").focus();
			return;
		}
		
		if(!reg.test($("#payFee_huifu").val())) {
			alert('请输入HF正确的单笔结算固定手续费，最多保留两位小数');
			$("#payFee_huifu").focus();
			return;
		}
		
		showWait();
		document.getElementById("subForm").submit();
	}
	function callBack(result) {
		hideWait();
		if (result == "success") {
			alert("操作成功!");
			D.getElementById('closeTp').value = "1";
			api.close();
		} else {
			alert("操作失败," + result + "!");
		}
	}
	function init() {
		var result = "${message}";
		if (result != "") {
			parent.callBack(result);
		}
	};
</script>
</head>
<body onload="init()">
	<form action="${ctx }/levelFeeRate/update" id="subForm" method="post" class="form-inline" role="form" target="hidden_frame">
		<input type="hidden" name="type" id="type" value="update">
		<div class="begin">
			<table border="" cellspacing="" cellpadding="">
				<tr>
					<td class="width90"><span>等级名称：</span></td>
					<td>
						<span>
							${level.levelName }
							<input name="level.levelId" id="levelId" value="${level.levelId }" type="hidden" class="ipt" />
							<input name="level.levelType" id="levelType" value="${level.levelType }" type="hidden" class="ipt" />
						</span>
					</td>
				</tr>
				
				<tr>
					<td class="width90"><span>RB完美还款：</span></td>
					<td>
						<span>
							<c:forEach items="${level.listLevelFeeRate }" varStatus="i" var="lfr">
								<c:if test="${lfr.payChnlCode eq 'reapal' }">
									<input name="levelFeeRates[${i.index }].id" value="${lfr.id }" type="hidden" class="ipt" />
									费率<input name="levelFeeRates[${i.index }].feeRate" id="feeRate_reapal" value='<fmt:formatNumber value="${lfr.feeRate }" type="currency" pattern="0.00"/>' type="text" class="input-sm" maxlength="12" onkeypress="if(!this.value.match(/^[/+/-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" onkeyup="if(!this.value.match(/^[/+/-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" onblur="if(!this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?|\.\d*?)?$/))this.value=this.o_value;else{if(this.value.match(/^\.\d+$/))this.value=0+this.value;if(this.value.match(/^\.$/))this.value=0;this.o_value=this.value}" />%，
									单笔代付费<input name="levelFeeRates[${i.index }].payFeeFj" id="payFee_reapal" value='<chrone:fen2Yuan amt="${lfr.payFee }"/>' type="text" class="input-sm" maxlength="12" onkeypress="if(!this.value.match(/^[/+/-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" onkeyup="if(!this.value.match(/^[/+/-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" onblur="if(!this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?|\.\d*?)?$/))this.value=this.o_value;else{if(this.value.match(/^\.\d+$/))this.value=0+this.value;if(this.value.match(/^\.$/))this.value=0;this.o_value=this.value}" />元/笔
								</c:if>
							</c:forEach>
						</span>
					</td>
				</tr>
				
				<tr>
					<td class="width90"><span>RB快捷：</span></td>
					<td>
						<span>
							<c:forEach items="${level.listLevelFeeRate }" varStatus="i" var="lfr">
								<c:if test="${lfr.payChnlCode eq 'reapalfast' }">
									<input name="levelFeeRates[${i.index }].id" value="${lfr.id }" type="hidden" class="ipt" />
									费率<input name="levelFeeRates[${i.index }].feeRate" id="feeRate_reapalfast" value='<fmt:formatNumber value="${lfr.feeRate }" type="currency" pattern="0.00"/>' type="text" class="input-sm" maxlength="12" onkeypress="if(!this.value.match(/^[/+/-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" onkeyup="if(!this.value.match(/^[/+/-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" onblur="if(!this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?|\.\d*?)?$/))this.value=this.o_value;else{if(this.value.match(/^\.\d+$/))this.value=0+this.value;if(this.value.match(/^\.$/))this.value=0;this.o_value=this.value}" />%，
									封顶手续费<input name="levelFeeRates[${i.index }].upperlimitFj" id="upperlimit_reapalfast" value='<chrone:fen2Yuan amt="${lfr.upperlimit }"/>' type="text" class="input-sm" maxlength="12" onkeypress="if(!this.value.match(/^[/+/-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" onkeyup="if(!this.value.match(/^[/+/-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" onblur="if(!this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?|\.\d*?)?$/))this.value=this.o_value;else{if(this.value.match(/^\.\d+$/))this.value=0+this.value;if(this.value.match(/^\.$/))this.value=0;this.o_value=this.value}" />元，
									单笔结算固定<input name="levelFeeRates[${i.index }].payFeeFj" id="payFee_reapalfast" value='<chrone:fen2Yuan amt="${lfr.payFee }"/>' type="text" class="input-sm" maxlength="12" onkeypress="if(!this.value.match(/^[/+/-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" onkeyup="if(!this.value.match(/^[/+/-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" onblur="if(!this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?|\.\d*?)?$/))this.value=this.o_value;else{if(this.value.match(/^\.\d+$/))this.value=0+this.value;if(this.value.match(/^\.$/))this.value=0;this.o_value=this.value}" />元/笔
								</c:if>
							</c:forEach>
						</span>
					</td>
				</tr>
				
				<tr>
					<td class="width90"><span>YT快捷：</span></td>
					<td>
						<span>
							<c:forEach items="${level.listLevelFeeRate }" varStatus="i" var="lfr">
								<c:if test="${lfr.payChnlCode eq 'yitong' }">
									<input name="levelFeeRates[${i.index }].id" value="${lfr.id }" type="hidden" class="ipt" />
									费率<input name="levelFeeRates[${i.index }].feeRate" id="feeRate_yitong" value='<fmt:formatNumber value="${lfr.feeRate }" type="currency" pattern="0.00"/>' type="text" class="input-sm" maxlength="12" onkeypress="if(!this.value.match(/^[/+/-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" onkeyup="if(!this.value.match(/^[/+/-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" onblur="if(!this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?|\.\d*?)?$/))this.value=this.o_value;else{if(this.value.match(/^\.\d+$/))this.value=0+this.value;if(this.value.match(/^\.$/))this.value=0;this.o_value=this.value}" />%，
									封顶手续费<input name="levelFeeRates[${i.index }].upperlimitFj" id="upperlimit_yitong" value='<chrone:fen2Yuan amt="${lfr.upperlimit }"/>' type="text" class="input-sm" maxlength="12" onkeypress="if(!this.value.match(/^[/+/-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" onkeyup="if(!this.value.match(/^[/+/-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" onblur="if(!this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?|\.\d*?)?$/))this.value=this.o_value;else{if(this.value.match(/^\.\d+$/))this.value=0+this.value;if(this.value.match(/^\.$/))this.value=0;this.o_value=this.value}" />元，
									单笔结算固定<input name="levelFeeRates[${i.index }].payFeeFj" id="payFee_yitong" value='<chrone:fen2Yuan amt="${lfr.payFee }"/>' type="text" class="input-sm" maxlength="12" onkeypress="if(!this.value.match(/^[/+/-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" onkeyup="if(!this.value.match(/^[/+/-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" onblur="if(!this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?|\.\d*?)?$/))this.value=this.o_value;else{if(this.value.match(/^\.\d+$/))this.value=0+this.value;if(this.value.match(/^\.$/))this.value=0;this.o_value=this.value}" />元/笔
								</c:if>
							</c:forEach>
						</span>
					</td>
				</tr>
				
				<tr>
					<td class="width90"><span>HF快捷：</span></td>
					<td>
						<span>
							<c:forEach items="${level.listLevelFeeRate }" varStatus="i" var="lfr">
								<c:if test="${lfr.payChnlCode eq 'huifu' }">
									<input name="levelFeeRates[${i.index }].id" value="${lfr.id }" type="hidden" class="ipt" />
									费率<input name="levelFeeRates[${i.index }].feeRate" id="feeRate_huifu" value='<fmt:formatNumber value="${lfr.feeRate }" type="currency" pattern="0.00"/>' type="text" class="input-sm" maxlength="12" onkeypress="if(!this.value.match(/^[/+/-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" onkeyup="if(!this.value.match(/^[/+/-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" onblur="if(!this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?|\.\d*?)?$/))this.value=this.o_value;else{if(this.value.match(/^\.\d+$/))this.value=0+this.value;if(this.value.match(/^\.$/))this.value=0;this.o_value=this.value}" />%，
									封顶手续费<input name="levelFeeRates[${i.index }].upperlimitFj" id="upperlimit_huifu" value='<chrone:fen2Yuan amt="${lfr.upperlimit }"/>' type="text" class="input-sm" maxlength="12" onkeypress="if(!this.value.match(/^[/+/-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" onkeyup="if(!this.value.match(/^[/+/-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" onblur="if(!this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?|\.\d*?)?$/))this.value=this.o_value;else{if(this.value.match(/^\.\d+$/))this.value=0+this.value;if(this.value.match(/^\.$/))this.value=0;this.o_value=this.value}" />元，
									单笔结算固定<input name="levelFeeRates[${i.index }].payFeeFj" id="payFee_huifu" value='<chrone:fen2Yuan amt="${lfr.payFee }"/>' type="text" class="input-sm" maxlength="12" onkeypress="if(!this.value.match(/^[/+/-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" onkeyup="if(!this.value.match(/^[/+/-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" onblur="if(!this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?|\.\d*?)?$/))this.value=this.o_value;else{if(this.value.match(/^\.\d+$/))this.value=0+this.value;if(this.value.match(/^\.$/))this.value=0;this.o_value=this.value}" />元/笔
								</c:if>
							</c:forEach>
						</span>
					</td>
				</tr>
				<tr>
					<td class="width90"><span>YS快捷：</span></td>
					<td>
						<span>
							<c:forEach items="${level.listLevelFeeRate }" varStatus="i" var="lfr">
								<c:if test="${lfr.payChnlCode eq 'yspay' }">
									<input name="levelFeeRates[${i.index }].id" value="${lfr.id }" type="hidden" class="ipt" />
									费率<input name="levelFeeRates[${i.index }].feeRate" id="feeRate_yspay" value='<fmt:formatNumber value="${lfr.feeRate }" type="currency" pattern="0.00"/>' type="text" class="input-sm" maxlength="12" onkeypress="if(!this.value.match(/^[/+/-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" onkeyup="if(!this.value.match(/^[/+/-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" onblur="if(!this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?|\.\d*?)?$/))this.value=this.o_value;else{if(this.value.match(/^\.\d+$/))this.value=0+this.value;if(this.value.match(/^\.$/))this.value=0;this.o_value=this.value}" />%，
									封顶手续费<input name="levelFeeRates[${i.index }].upperlimitFj" id="upperlimit_yspay" value='<chrone:fen2Yuan amt="${lfr.upperlimit }"/>' type="text" class="input-sm" maxlength="12" onkeypress="if(!this.value.match(/^[/+/-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" onkeyup="if(!this.value.match(/^[/+/-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" onblur="if(!this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?|\.\d*?)?$/))this.value=this.o_value;else{if(this.value.match(/^\.\d+$/))this.value=0+this.value;if(this.value.match(/^\.$/))this.value=0;this.o_value=this.value}" />元，
									单笔结算固定<input name="levelFeeRates[${i.index }].payFeeFj" id="payFee_yspay" value='<chrone:fen2Yuan amt="${lfr.payFee }"/>' type="text" class="input-sm" maxlength="12" onkeypress="if(!this.value.match(/^[/+/-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" onkeyup="if(!this.value.match(/^[/+/-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" onblur="if(!this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?|\.\d*?)?$/))this.value=this.o_value;else{if(this.value.match(/^\.\d+$/))this.value=0+this.value;if(this.value.match(/^\.$/))this.value=0;this.o_value=this.value}" />元/笔
								</c:if>
							</c:forEach>
						</span>
					</td>
				</tr>
				<tr>
					<td class="width90"><span>YAKUPAY快捷：</span></td>
					<td>
						<span>
							<c:forEach items="${level.listLevelFeeRate }" varStatus="i" var="lfr">
								<c:if test="${lfr.payChnlCode eq 'yakupay' }">
									<input name="levelFeeRates[${i.index }].id" value="${lfr.id }" type="hidden" class="ipt" />
									费率<input name="levelFeeRates[${i.index }].feeRate" id="feeRate_yakupay" value='<fmt:formatNumber value="${lfr.feeRate }" type="currency" pattern="0.00"/>' type="text" class="input-sm" maxlength="12" onkeypress="if(!this.value.match(/^[/+/-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" onkeyup="if(!this.value.match(/^[/+/-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" onblur="if(!this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?|\.\d*?)?$/))this.value=this.o_value;else{if(this.value.match(/^\.\d+$/))this.value=0+this.value;if(this.value.match(/^\.$/))this.value=0;this.o_value=this.value}" />%，
									封顶手续费<input name="levelFeeRates[${i.index }].upperlimitFj" id="upperlimit_yakupay" value='<chrone:fen2Yuan amt="${lfr.upperlimit }"/>' type="text" class="input-sm" maxlength="12" onkeypress="if(!this.value.match(/^[/+/-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" onkeyup="if(!this.value.match(/^[/+/-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" onblur="if(!this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?|\.\d*?)?$/))this.value=this.o_value;else{if(this.value.match(/^\.\d+$/))this.value=0+this.value;if(this.value.match(/^\.$/))this.value=0;this.o_value=this.value}" />元，
									单笔结算固定<input name="levelFeeRates[${i.index }].payFeeFj" id="payFee_yakupay" value='<chrone:fen2Yuan amt="${lfr.payFee }"/>' type="text" class="input-sm" maxlength="12" onkeypress="if(!this.value.match(/^[/+/-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" onkeyup="if(!this.value.match(/^[/+/-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" onblur="if(!this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?|\.\d*?)?$/))this.value=this.o_value;else{if(this.value.match(/^\.\d+$/))this.value=0+this.value;if(this.value.match(/^\.$/))this.value=0;this.o_value=this.value}" />元/笔
								</c:if>
							</c:forEach>
						</span>
					</td>
				</tr>
				
				<tr class="textcenter">
					<td colspan="4">
						<button class="btn1" type="button" onclick="subForm()">保存</button>
						<button class="btn2" onclick="cancelVal()">取消</button>
					</td>
				</tr>
			</table>
		</div>
	</form>
	<iframe name='hidden_frame' id="hidden_frame" style='display: none'></iframe>
</body>
</html>