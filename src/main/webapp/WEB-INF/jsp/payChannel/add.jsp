<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	request.setAttribute("ctx", request.getContextPath());
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src='<c:url value="/static/js/jquery-1.7.1.min.js"/>'></script>
<script type="text/javascript" src='<c:url value="/static/js/loading.js"/>' language="javascript"></script>
<script type="text/javascript" src="${ctx }/static/js/My97DatePicker/WdatePicker.js"></script>
<link href="${ctx }/static/css/alert.css" rel="stylesheet" type="text/css" />
<title>Insert title here</title>
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
		if ($("#code").val() == "") {
			alert("请输入通道代码");
			$("#code").focus();
			return;
		}
		if ($("#name").val() == "") {
			alert("请输入通道名称");
			$("#name").focus();
			return;
		}
		if ($("#chnlType").val() == "") {
			alert("请选择通道类型");
			$("#chnlType").focus();
			return;
		}
		var reg = /^[0-9]+(.[0-9]{0,2})?$/;
		if(2 != $("#chnlType").val()){
			if ($("#settleType").val() == "") {
				alert("请选择结算方式");
				$("#settleType").focus();
				return;
			}
			if ($("#minAmountDou").val() == "") {
				alert("请输入单笔最小金额");
				$("#minAmountDou").focus();
				return;
			}
			if(!reg.test($("#minAmountDou").val())) {
				alert('请输入正确的单笔最小金额，最多保留两位小数');
				$("#minAmountDou").focus();
				return;
			}
			if ($("#maxAmountDou").val() == "") {
				alert("请输入单笔最大金额");
				$("#maxAmountDou").focus();
				return;
			}
			if(!reg.test($("#maxAmountDou").val())) {
				alert('请输入正确的单笔最大金额，最多保留两位小数');
				$("#maxAmountDou").focus();
				return;
			}
			if ($("#daySumCnt").val() == "") {
				alert("请输入单日累计笔数");
				$("#daySumCnt").focus();
				return;
			}
			if ($("#daySumAmtDou").val() == "") {
				alert("请输入单日累计金额");
				$("#daySumAmtDou").focus();
				return;
			}
			if(!reg.test($("#daySumAmtDou").val())) {
				alert('请输入正确的单日累计金额，最多保留两位小数');
				$("#daySumAmtDou").focus();
				return;
			}
		}
		if(4 == $("#chnlType").val()){
			if ($("#payFeeRate").val() == "") {
				alert("请输入单笔结算手续费");
				$("#payFeeRate").focus();
				return;
			}
			if ($("#integralType").val() == "") {
				alert("请选择是否有积分");
				return;
			}
			if(!reg.test($("#upperlimit").val())) {
				alert('请输入正确的封顶手续费，最多保留两位小数');
				$("#upperlimit").focus();
				return;
			}
		}
		if ($("#feeType").val() == "") {
			alert("请选择费率类型");
			$("#feeType").focus();
			return;
		}
		if ($("#feeRate").val() == "") {
			alert("请输入通道费率");
			$("#feeRate").focus();
			return;
		}
		
		if(!reg.test($("#feeRate").val())) {
			alert('请输入正确的通道费率，最多保留两位小数');
			$("#feeRate").focus();
			return;
		}
		if ($("#priority").val() == "") {
			alert("请输入通道权重");
			$("#priority").focus();
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
	}
</script>
<script type="text/javascript">
function upType(){
	var feeType = $('#feeType').val();
	if(1 == feeType){
		$('#toFen').html('元');
	}
	if(0 == feeType){
		$('#toFen').html('%');
	}
	if(2 == feeType){
	}
};

function upChnlType(){
	var chnlType = $('#chnlType').val();
	if(0 == chnlType){
		$('#minAmountDou').attr('readonly', false);
		$('#maxAmountDou').attr('readonly', false);
		$('#daySumCnt').attr('readonly', false);
		$('#daySumAmtDou').attr('readonly', false);
		$('#settleType').attr('readonly', false);
		$('#upperlimit').attr('readonly', true);
		$('#payFeeRate').attr('readonly', true);
		$('#integralType').attr('readonly', true);
		$('#feeType').empty();
		$('.fast_settle').hide();
		$('.notForCert').show();
		$('#toFen').html('%');
		$('#feeType').append("<option value=''>全部</option>");
		$('#feeType').append("<option value='0'>百分比</option>");
		$('#feeType').append("<option value='1'>按笔</option>");
		/* if(1 == chnlType){
			$('.notForCert').hide();
			$('#feeType').append("<option value='1'>按笔</option>");
			$('#toFen').html('元');
		} */
	}
	
	if(1 == chnlType || 2 == chnlType || 3 == chnlType){
		$('#minAmountDou').attr('readonly', true);
		$('#maxAmountDou').attr('readonly', true);
		$('#daySumCnt').attr('readonly', true);
		$('#daySumAmtDou').attr('readonly', true);
		$('#settleType').attr('readonly', true);
		$('#upperlimit').attr('readonly', true);
		$('#payFeeRate').attr('readonly', true);
		$('#integralType').attr('readonly', true);
		$('#toFen').html('元/笔');
		$('.notForCert').hide();
		$('.fast_settle').hide();
		$('#feeType').empty();
		$('#feeType').append("<option value='1' selected='selected'>按笔</option>");
	}
	if(4 == chnlType){
		$('.fast_settle').show();
		$('.notForCert').show();
		$('#minAmountDou').attr('readonly', false);
		$('#maxAmountDou').attr('readonly', false);
		$('#daySumCnt').attr('readonly', false);
		$('#daySumAmtDou').attr('readonly', false);
		$('#settleType').attr('readonly', false);
		$('#upperlimit').attr('readonly', false);
		$('#payFeeRate').attr('readonly', false);
		$('#integralType').attr('readonly', false);
		$('#feeType').empty();
		$('#feeType').append("<option value='0'>百分比</option>");
		if(0 == chnlType){
			$('#toFen').html('%');
		}
		if(1 == chnlType){
			$('#toFen').html('元');
		}
	}
}
</script>
</head>
<body onload="init()">
	<form action="${ctx }/payChannel/add" id="subForm" method="post"
		target="hidden_frame">
		<input type="hidden" name="type" id="type" value="add">
		<div class="begin">
			<table border="" cellspacing="" cellpadding="">
				<tr>
					<td class="width90"><span><span style="color:red;">*</span>通道类型：</span></td>
					<td><span>
						<select  name="chnlType" id="chnlType" onchange="upChnlType()">
							<option value="0" selected="selected">快捷</option>
							<option value="1">代付</option>
							<option value="2">实名认证</option>
							<option value="3">钱包提现</option>
							<option value="4">快捷消费</option>
							<option value="5">快捷消费出款</option>
							<option value="6">服务费支付</option>
						</select>
					</span></td>
					<td class="width90"><span style="color:red;">*</span><span>通道权重：</span></td>
					<td><span><input name="priority" id="priority" maxlength="10" class="ipt" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')" />
					</span></td>
				</tr>
				<tr>
					<td class="width90"><span style="color:red;">*</span><span>通道代码：</span></td>
					<td><span><input name="code" id="code" value="" maxlength="20" class="ipt" /></span></td>
					<td class="width90"><span style="color:red;">*</span><span>通道名称：</span></td>
					<td><span> <input name="name" id="name" value="" maxlength="16" class="ipt"/>
					</span></td>
				</tr>
				<tr>
					<td class="width90"><span style="color:red;">*</span><span>计费方式：</span></td>
					<td><span>
						<select name="feeType" id="feeType" onchange="upType()" >
							<option value="">请选择</option>
							<option value="0">百分比</option>
							<option value="1">按笔</option>
						</select>
					</span></td>
					<td class="width90"><span style="color:red;">*</span><span> 通道费率：</span></td>
					<td><span>
						<input name="feeRate" id="feeRate" maxlength="15" style="width: 80px;height: 28px;line-height: 28px;border: 1px solid #a9a9a9;padding-left: 5px; "
						 onkeypress="if(!this.value.match(/^[/+/-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" 
						 onkeyup="if(!this.value.match(/^[/+/-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" 
						 onblur="if(!this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?|\.\d*?)?$/))this.value=this.o_value;else{if(this.value.match(/^\.\d+$/))this.value=0+this.value;if(this.value.match(/^\.$/))this.value=0;this.o_value=this.value}" />
						<span id="toFen">%</span>
						<span class="fast_settle" style="display: none">
							，封顶手续费<input name="upperlimit" id="upperlimit" value="0" maxlength="15" style="width: 80px;height: 28px;line-height: 28px;border: 1px solid #a9a9a9;padding-left: 5px; "
							 onkeypress="if(!this.value.match(/^[/+/-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" 
							 onkeyup="if(!this.value.match(/^[/+/-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" 
							 onblur="if(!this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?|\.\d*?)?$/))this.value=this.o_value;else{if(this.value.match(/^\.\d+$/))this.value=0+this.value;if(this.value.match(/^\.$/))this.value=0;this.o_value=this.value}" />
							 元
						</span>
					</span></td>
				</tr>
				<tr class="fast_settle" style="display: none">
					<td class="width90"><span style="color:red;">*</span><span> 快捷积分：</span></td>
					<td><span>
						<select name="integralType" id="integralType" >
							<option value="">请选择</option>
							<option value="0">无积分</option>
							<option value="1">有积分</option>
						</select>
					</span></td>
					<td class="width90"><span style="color:red;">*</span><span>单笔结算：</span></td>
					<td><span><input name="payFeeRate" id="payFeeRate" maxlength="15" class="ipt" value="0.00" 
									onkeypress="if(!this.value.match(/^[/+/-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" 
						 			onkeyup="if(!this.value.match(/^[/+/-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" 
						 			onblur="if(!this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?|\.\d*?)?$/))this.value=this.o_value;else{if(this.value.match(/^\.\d+$/))this.value=0+this.value;if(this.value.match(/^\.$/))this.value=0;this.o_value=this.value}"/>
						 			元/笔
					</span></td>
				</tr>
				<tr class="notForCert">
					<td class="width90"><span style="color:red;">*</span><span>单笔最小金额(元)：</span></td>
					<td><span>
					<input name="minAmountDou" id="minAmountDou" maxlength="15" class="ipt" value="0.00" 
									onkeypress="if(!this.value.match(/^[/+/-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" 
						 			onkeyup="if(!this.value.match(/^[/+/-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" 
						 			onblur="if(!this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?|\.\d*?)?$/))this.value=this.o_value;else{if(this.value.match(/^\.\d+$/))this.value=0+this.value;if(this.value.match(/^\.$/))this.value=0;this.o_value=this.value}"/>
						</span></td>
					<td class="width90"><span style="color:red;">*</span><span> 单笔最大金额(元)：</span></td>
					<td><span><input name="maxAmountDou" id="maxAmountDou" maxlength="15" class="ipt" value="0.00" 
									onkeypress="if(!this.value.match(/^[/+/-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" 
									onkeyup="if(!this.value.match(/^[/+/-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" 
						 			onblur="if(!this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?|\.\d*?)?$/))this.value=this.o_value;else{if(this.value.match(/^\.\d+$/))this.value=0+this.value;if(this.value.match(/^\.$/))this.value=0;this.o_value=this.value}"/>
					</span></td>
				</tr>
				<tr class="notForCert">
					<td class="width90"><span style="color:red;">*</span><span>单日累计笔数：</span></td>
					<td><span><input name="daySumCnt" id="daySumCnt" maxlength="10" class="ipt" value="0" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')" /></span></td>
					<td class="width90"><span style="color:red;">*</span><span>单日累计金额(元)：</span></td>
					<td><span><input name="daySumAmtDou" id="daySumAmtDou" maxlength="15" class="ipt" value="0.00" 
									onkeypress="if(!this.value.match(/^[/+/-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" 
						 			onkeyup="if(!this.value.match(/^[/+/-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" 
						 			onblur="if(!this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?|\.\d*?)?$/))this.value=this.o_value;else{if(this.value.match(/^\.\d+$/))this.value=0+this.value;if(this.value.match(/^\.$/))this.value=0;this.o_value=this.value}"/>
					</span></td>
				</tr>
				<tr>
					<td class="width90"><span>允许交易时间：</span></td>
					<td><span>
						<input class="Wdate" style="height: 20px;width: 80px;" type="text" id="startDate" name="startDate"
								onclick="WdatePicker({dateFmt:'HH:mm:ss',maxDate:'#F{$dp.$D(\'endDate\')}'});"
								value="${startDate}" /> &nbsp;-&nbsp;
								<input class="Wdate" style="height: 20px;width: 80px;" type="text" id="endDate" name="endDate"
								onclick="WdatePicker({dateFmt:'HH:mm:ss',minDate:'#F{$dp.$D(\'startDate\')}'});"
								value="${endDate}" />
					</span></td>
					<td class="width90 notForCert"><span style="color:red;">*</span><span>结算方式：</span></td>
					<td class="notForCert"><span>
						<select name="settleType" id="settleType" >
							<option value="-1">请选择</option>
							<option value="0">D0</option>
							<option value="1">T1</option>
						</select>
					</span></td>
				</tr>
				<tr class="textcenter">
					<td colspan="4">
						<button class="btn1" type="button" onclick="subForm()">确定添加</button>
						<button class="btn2" onclick="cancelVal()">取消</button>
					</td>
				</tr>

			</table>
		</div>
	</form>
	<iframe name='hidden_frame' id="hidden_frame" style='display: none'></iframe>
</body>
</html>