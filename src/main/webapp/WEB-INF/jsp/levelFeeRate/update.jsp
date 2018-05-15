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
		if(!reg.test($("#feeRate").val())) {
			alert('请输入正确的还款服务费，最多保留两位小数');
			$("#feeRate").focus();
			return;
		}
		for(var i=0;i<4;i++){
			if(!reg.test($("#feeRate"+i).val())) {
				alert('请输入正确的费率，最多保留两位小数');
				$("#feeRate"+i).focus();
				return;
			}
		}
		for(var i=0;i<4;i++){
			if(!reg.test($("#upperlimit"+i).val())) {
				alert('请输入正确的封顶手续费，最多保留两位小数');
				$("#upperlimit"+i).focus();
				return;
			}
		}
		for(var i=0;i<4;i++){
			if(!reg.test($("#payFee"+i).val())) {
				alert('请输入正确的单笔结算固定手续费，最多保留两位小数');
				$("#payFee"+i).focus();
				return;
			}
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
						</span>
					</td>
				</tr>
				<tr>
					<td class="width90"><span>还款服务费：</span></td>
					<td>
						<span>
							<input name="level.feeRate" id="feeRate" value='<fmt:formatNumber value="${level.feeRate }" type="currency" pattern="0.00"/>' type="text" class="input-sm" maxlength="12" onkeypress="if(!this.value.match(/^[/+/-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" onkeyup="if(!this.value.match(/^[/+/-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" onblur="if(!this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?|\.\d*?)?$/))this.value=this.o_value;else{if(this.value.match(/^\.\d+$/))this.value=0+this.value;if(this.value.match(/^\.$/))this.value=0;this.o_value=this.value}" />%，
							还款代付费<chrone:fen2Yuan amt="${level.plan_df_fee/100 }"/>元/笔
						</span>
					</td>
				</tr>
				<tr>
					<td class="width90"><span>快捷D0-无积分：</span></td>
					<td>
						<span>
							<c:forEach items="${level.listLevelFeeRate }" varStatus="i" var="lfr">
								<c:if test="${lfr.settleType == 0 && lfr.integralType == 0 }">
									<input name="levelFeeRates[${i.index }].id" value="${lfr.id }" type="hidden" class="ipt" />
									费率<input name="levelFeeRates[${i.index }].feeRate" id="feeRate${i.index }" value='<fmt:formatNumber value="${lfr.feeRate }" type="currency" pattern="0.00"/>' type="text" class="input-sm" maxlength="12" onkeypress="if(!this.value.match(/^[/+/-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" onkeyup="if(!this.value.match(/^[/+/-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" onblur="if(!this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?|\.\d*?)?$/))this.value=this.o_value;else{if(this.value.match(/^\.\d+$/))this.value=0+this.value;if(this.value.match(/^\.$/))this.value=0;this.o_value=this.value}" />%，
									封顶手续费<input name="levelFeeRates[${i.index }].upperlimitFj" id="upperlimit${i.index }" value='<chrone:fen2Yuan amt="${lfr.upperlimit }"/>' type="text" class="input-sm" maxlength="12" onkeypress="if(!this.value.match(/^[/+/-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" onkeyup="if(!this.value.match(/^[/+/-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" onblur="if(!this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?|\.\d*?)?$/))this.value=this.o_value;else{if(this.value.match(/^\.\d+$/))this.value=0+this.value;if(this.value.match(/^\.$/))this.value=0;this.o_value=this.value}" />元，
									单笔结算固定<input name="levelFeeRates[${i.index }].payFeeFj" id="payFee${i.index }" value='<chrone:fen2Yuan amt="${lfr.payFee }"/>' type="text" class="input-sm" maxlength="12" onkeypress="if(!this.value.match(/^[/+/-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" onkeyup="if(!this.value.match(/^[/+/-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" onblur="if(!this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?|\.\d*?)?$/))this.value=this.o_value;else{if(this.value.match(/^\.\d+$/))this.value=0+this.value;if(this.value.match(/^\.$/))this.value=0;this.o_value=this.value}" />元/笔
								</c:if>
							</c:forEach>
						</span>
					</td>
				</tr>
				<tr>
					<td class="width90"><span>快捷D0-有积分：</span></td>
					<td>
						<span>
							<c:forEach items="${level.listLevelFeeRate }" varStatus="i" var="lfr">
								<c:if test="${lfr.settleType == 0 && lfr.integralType == 1 }">
									<input name="levelFeeRates[${i.index }].id" value="${lfr.id }" type="hidden" class="ipt" />
									费率<input name="levelFeeRates[${i.index }].feeRate" id="feeRate${i.index }" value='<fmt:formatNumber value="${lfr.feeRate }" type="currency" pattern="0.00"/>' type="text" class="input-sm" maxlength="12" onkeypress="if(!this.value.match(/^[/+/-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" onkeyup="if(!this.value.match(/^[/+/-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" onblur="if(!this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?|\.\d*?)?$/))this.value=this.o_value;else{if(this.value.match(/^\.\d+$/))this.value=0+this.value;if(this.value.match(/^\.$/))this.value=0;this.o_value=this.value}" />%，
									封顶手续费<input name="levelFeeRates[${i.index }].upperlimitFj" id="upperlimit${i.index }" value='<chrone:fen2Yuan amt="${lfr.upperlimit }"/>' type="text" class="input-sm" maxlength="12" onkeypress="if(!this.value.match(/^[/+/-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" onkeyup="if(!this.value.match(/^[/+/-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" onblur="if(!this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?|\.\d*?)?$/))this.value=this.o_value;else{if(this.value.match(/^\.\d+$/))this.value=0+this.value;if(this.value.match(/^\.$/))this.value=0;this.o_value=this.value}" />元，
									单笔结算固定<input name="levelFeeRates[${i.index }].payFeeFj" id="payFee${i.index }" value='<chrone:fen2Yuan amt="${lfr.payFee }"/>' type="text" class="input-sm" maxlength="12" onkeypress="if(!this.value.match(/^[/+/-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" onkeyup="if(!this.value.match(/^[/+/-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" onblur="if(!this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?|\.\d*?)?$/))this.value=this.o_value;else{if(this.value.match(/^\.\d+$/))this.value=0+this.value;if(this.value.match(/^\.$/))this.value=0;this.o_value=this.value}" />元/笔
								</c:if>
							</c:forEach>
						</span>
					</td>
				</tr>
				<tr>
					<td class="width90"><span>快捷T1-无积分：</span></td>
					<td>
						<span>
							<c:forEach items="${level.listLevelFeeRate }" varStatus="i" var="lfr">
								<c:if test="${lfr.settleType == 1 && lfr.integralType == 0 }">
									<input name="levelFeeRates[${i.index }].id" value="${lfr.id }" type="hidden" class="ipt" />
									费率<input name="levelFeeRates[${i.index }].feeRate" id="feeRate${i.index }" value='<fmt:formatNumber value="${lfr.feeRate }" type="currency" pattern="0.00"/>' type="text" class="input-sm" maxlength="12" onkeypress="if(!this.value.match(/^[/+/-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" onkeyup="if(!this.value.match(/^[/+/-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" onblur="if(!this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?|\.\d*?)?$/))this.value=this.o_value;else{if(this.value.match(/^\.\d+$/))this.value=0+this.value;if(this.value.match(/^\.$/))this.value=0;this.o_value=this.value}" />%，
									封顶手续费<input name="levelFeeRates[${i.index }].upperlimitFj" id="upperlimit${i.index }" value='<chrone:fen2Yuan amt="${lfr.upperlimit }"/>' type="text" class="input-sm" />元，
									单笔结算固定<input name="levelFeeRates[${i.index }].payFeeFj" id="payFee${i.index }" value='<chrone:fen2Yuan amt="${lfr.payFee }"/>' type="text" class="input-sm" />元/笔
								</c:if>
							</c:forEach>
						</span>
					</td>
				</tr>
				<tr>
					<td class="width90"><span>快捷T1-无积分：</span></td>
					<td>
						<span>
							<c:forEach items="${level.listLevelFeeRate }" varStatus="i" var="lfr">
								<c:if test="${lfr.settleType == 1 && lfr.integralType == 1 }">
									<input name="levelFeeRates[${i.index }].id" value="${lfr.id }" type="hidden" class="ipt" />
									费率<input name="levelFeeRates[${i.index }].feeRate" id="feeRate${i.index }" value='<fmt:formatNumber value="${lfr.feeRate }" type="currency" pattern="0.00"/>' type="text" class="input-sm" maxlength="12" onkeypress="if(!this.value.match(/^[/+/-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" onkeyup="if(!this.value.match(/^[/+/-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" onblur="if(!this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?|\.\d*?)?$/))this.value=this.o_value;else{if(this.value.match(/^\.\d+$/))this.value=0+this.value;if(this.value.match(/^\.$/))this.value=0;this.o_value=this.value}" />%，
									封顶手续费<input name="levelFeeRates[${i.index }].upperlimitFj" id="upperlimit${i.index }" value='<chrone:fen2Yuan amt="${lfr.upperlimit }"/>' type="text" class="input-sm" maxlength="12" onkeypress="if(!this.value.match(/^[/+/-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" onkeyup="if(!this.value.match(/^[/+/-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" onblur="if(!this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?|\.\d*?)?$/))this.value=this.o_value;else{if(this.value.match(/^\.\d+$/))this.value=0+this.value;if(this.value.match(/^\.$/))this.value=0;this.o_value=this.value}" />元，
									单笔结算固定<input name="levelFeeRates[${i.index }].payFeeFj" id="payFee${i.index }" value='<chrone:fen2Yuan amt="${lfr.payFee }"/>' type="text" class="input-sm" maxlength="12" onkeypress="if(!this.value.match(/^[/+/-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" onkeyup="if(!this.value.match(/^[/+/-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" onblur="if(!this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?|\.\d*?)?$/))this.value=this.o_value;else{if(this.value.match(/^\.\d+$/))this.value=0+this.value;if(this.value.match(/^\.$/))this.value=0;this.o_value=this.value}" />元/笔
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