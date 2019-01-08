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
		/* if ($("#feeType").val() == "") {
			alert("请选择费率类型");
			$("#feeType").focus();
			return;
		} */
		if ($("#feeRate").val() == "") {
			alert("请输入通道费率");
			$("#feeRate").focus();
			return;
		}
		var reg = /^[0-9]+(.[0-9]{0,2})?$/;
		if(!reg.test($("#feeRate").val())) {
			alert('请输入正确的通道费率，最多保留两位小数');
			$("#feeRate").focus();
			return;
		}
		if($('#chnlType').val() != 1 && $('#chnlType').val() != 2 && $('#chnlType').val() != 3){
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
	};
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
};
</script>
</head>
<body onload="init()">
	<form action="${ctx }/payChannel/update" id="subForm" method="post" target="hidden_frame">
		<input type="hidden" name="type" id="type" value="update">
		<div class="begin">
			<table border="" cellspacing="" cellpadding="">
				<tr>
					<td class="width90"><span style="color:red;">*</span><span>通道代码：</span></td>
					<td><span>
						${payChannel.code }
						<input name="id" id="id" value="${payChannel.id }" type="hidden" class="ipt" />
					</span></td>
					<td class="width90"><span style="color:red;">*</span><span>通道名称：</span></td>
					<td><span>
						${payChannel.name }
					</span></td>
				</tr>
				<tr>
					<td class="width90"><span><span style="color:red;">*</span>通道类型：</span></td>
					<td><span>
						<c:if test="${payChannel.chnlType == 0 }">快捷</c:if>
						<c:if test="${payChannel.chnlType == 1 }">代付</c:if>
						<c:if test="${payChannel.chnlType == 2 }">实名认证</c:if>
						<c:if test="${payChannel.chnlType == 3 }">钱包提现</c:if>
						<c:if test="${payChannel.chnlType == 4 }">快捷消费</c:if>
						<c:if test="${payChannel.chnlType == 5 }">快捷消费出款</c:if>
						<c:if test="${payChannel.chnlType == 6 }">服务费支付</c:if>
						<c:if test="${payChannel.chnlType == 7 }">扫码支付</c:if>
						<input name="chnlType" id="chnlType" value="${payChannel.chnlType }" type="hidden" class="ipt"/>
					</span></td>
					<td class="width90"><span style="color:red;">*</span><span>通道权重：</span></td>
					<td><span><input name="priority" id="priority" maxlength="10" type="text" value="${payChannel.priority }" class="ipt" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')" />
					</span></td>
					
				</tr>
				<tr>
					<td class="width90"><span style="color:red;">*</span><span>费率类型：</span></td>
					<td><span>
						<select name="feeType2" id="feeType2" disabled="disabled" onchange="upType()" >
							<option value="0" <c:if test="${payChannel.feeType == 0 }">selected="selected"</c:if>>百分比</option>
							<option value="1" <c:if test="${payChannel.feeType == 1 }">selected="selected"</c:if>>按笔</option>
						</select>
						<input type="hidden" value="${payChannel.feeType }" name="feeType">
					</span></td>
					<td class="width90"><span style="color:red;">*</span><span> 通道费率：</span></td>
					<td><span>
						<c:if test="${payChannel.feeType == 0 }">
							<input readonly="readonly" name="feeRate" id="feeRate" value="<fmt:formatNumber value="${payChannel.feeRate*100 }" type="currency" pattern="0.00"/>" maxlength="15" style="width: 80px;height: 28px;line-height: 28px;border: 1px solid #a9a9a9;padding-left: 5px; background: #f5f5f5" type="text" width="90px;"
							 	onkeypress="if(!this.value.match(/^[/+/-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" 
						 		onkeyup="if(!this.value.match(/^[/+/-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" 
								 onblur="if(!this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?|\.\d*?)?$/))this.value=this.o_value;else{if(this.value.match(/^\.\d+$/))this.value=0+this.value;if(this.value.match(/^\.$/))this.value=0;this.o_value=this.value}" />
						</c:if>
						<c:if test="${payChannel.feeType == 1 }">
							<input readonly="readonly" name="feeRate" id="feeRate" value="<fmt:formatNumber value="${payChannel.feeRate/100 }" type="currency" pattern="0.00"/>" type="text" maxlength="15" style="width: 80px;height: 28px;line-height: 28px;border: 1px solid #a9a9a9;padding-left: 5px;background: #f5f5f5 " width="80%" 
								onkeypress="if(!this.value.match(/^[/+/-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" 
						 		onkeyup="if(!this.value.match(/^[/+/-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" 
								 onblur="if(!this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?|\.\d*?)?$/))this.value=this.o_value;else{if(this.value.match(/^\.\d+$/))this.value=0+this.value;if(this.value.match(/^\.$/))this.value=0;this.o_value=this.value}" />
						</c:if>
						<span id="toFen">
							<c:if test="${payChannel.feeType == 0 }">
								%
							</c:if>
							<c:if test="${payChannel.feeType == 1 }">
								元<c:if test="${payChannel.chnlType ==2 || payChannel.chnlType ==3 }">/笔</c:if>
							</c:if>
						</span>
						<c:if test="${payChannel.chnlType == 4}">
							<span class="fast_settle">
								，封顶手续费<input name="upperlimitDou" id="upperlimitDou" value="<fmt:formatNumber value="${payChannel.upperlimit*100 }" type="currency" pattern="0.00"/>" maxlength="15" style="width: 80px;height: 28px;line-height: 28px;border: 1px solid #a9a9a9;padding-left: 5px; "
								 onkeypress="if(!this.value.match(/^[/+/-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" 
								 onkeyup="if(!this.value.match(/^[/+/-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" 
								 onblur="if(!this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?|\.\d*?)?$/))this.value=this.o_value;else{if(this.value.match(/^\.\d+$/))this.value=0+this.value;if(this.value.match(/^\.$/))this.value=0;this.o_value=this.value}" />
								 元
							</span>
						</c:if>
					</span></td>
				</tr>
				<c:if test="${payChannel.chnlType == 4}">
					<tr class="fast_settle">
						<td class="width90"><span style="color:red;">*</span><span> 快捷积分：</span></td>
						<td><span>
							<select name="integralType" id="integralType" disabled="disabled">
								<option value="">请选择</option>
								<option value="0" <c:if test="${payChannel.integralType == 0 }">selected="selected"</c:if>>无积分</option>
								<option value="1" <c:if test="${payChannel.integralType == 1 }">selected="selected"</c:if>>有积分</option>
							</select>
						</span></td>
						<td class="width90"><span style="color:red;">*</span><span>单笔结算：</span></td>
						<td><span><input readonly="readonly" name="payFeeRate" id="payFeeRate" value="<fmt:formatNumber value="${payChannel.payFeeRate/100 }" type="currency" pattern="0.00"/>" maxlength="15" class="ipt" style="background:#f5f5f5" value="0.00" 
										onkeypress="if(!this.value.match(/^[/+/-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" 
							 			onkeyup="if(!this.value.match(/^[/+/-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" 
							 			onblur="if(!this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?|\.\d*?)?$/))this.value=this.o_value;else{if(this.value.match(/^\.\d+$/))this.value=0+this.value;if(this.value.match(/^\.$/))this.value=0;this.o_value=this.value}"/>
							 			元/笔
						</span></td>
					</tr>
				</c:if>
				<c:if test="${payChannel.chnlType == 0 || payChannel.chnlType == 1 || payChannel.chnlType == 4 }">
					<tr>
						<td class="width90"><span style="color:red;">*</span><span>单笔最小金额(元)：</span></td>
						<td><span><input <c:if test="${payChannel.chnlType == 1}">readonly = "readonly" style='background:#ebebe4'</c:if> name="minAmountDou" id="minAmountDou" type="text" maxlength="15" class="ipt" value='<chrone:fen2Yuan amt="${payChannel.minAmount }"></chrone:fen2Yuan>' 
						 				onkeypress="if(!this.value.match(/^[/+/-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" 
							 			onkeyup="if(!this.value.match(/^[/+/-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" 
									 	onblur="if(!this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?|\.\d*?)?$/))this.value=this.o_value;else{if(this.value.match(/^\.\d+$/))this.value=0+this.value;if(this.value.match(/^\.$/))this.value=0;this.o_value=this.value}" />
						 	</span></td>
						<td class="width90"><span style="color:red;">*</span><span> 单笔最大金额(元)：</span></td>
						<td><span><input <c:if test="${payChannel.chnlType == 1}">readonly = "readonly" style='background:#ebebe4'</c:if>   name="maxAmountDou" id="maxAmountDou" type="text" maxlength="15" class="ipt" value='<chrone:fen2Yuan amt="${payChannel.maxAmount }"></chrone:fen2Yuan>' 
										onkeypress="if(!this.value.match(/^[/+/-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" 
							 			onkeyup="if(!this.value.match(/^[/+/-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" 
										onblur="if(!this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?|\.\d*?)?$/))this.value=this.o_value;else{if(this.value.match(/^\.\d+$/))this.value=0+this.value;if(this.value.match(/^\.$/))this.value=0;this.o_value=this.value}" />
						</span></td>
					</tr>
					<tr>
						<td class="width90"><span style="color:red;">*</span><span>单日累计笔数：</span></td>
						<td><span><input <c:if test="${payChannel.chnlType == 1}">readonly = "readonly" style='background:#ebebe4'</c:if>  name="daySumCnt" id="daySumCnt" type="text" maxlength="10" class="ipt" value="${payChannel.daySumCnt }" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')" /></span></td>
						<td class="width90"><span style="color:red;">*</span><span>单日累计金额(元)：</span></td>
						<td><span><input <c:if test="${payChannel.chnlType == 1}">readonly = "readonly" style='background:#ebebe4'</c:if>  name="daySumAmtDou" id="daySumAmtDou" type="text" maxlength="15" class="ipt" value="<chrone:fen2Yuan amt="${payChannel.daySumAmount }"></chrone:fen2Yuan>" 
									onkeypress="if(!this.value.match(/^[/+/-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" 
							 		onkeyup="if(!this.value.match(/^[/+/-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" 
									 onblur="if(!this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?|\.\d*?)?$/))this.value=this.o_value;else{if(this.value.match(/^\.\d+$/))this.value=0+this.value;if(this.value.match(/^\.$/))this.value=0;this.o_value=this.value}" />
						</span></td>
					</tr>
				</c:if>
				<tr>
					<td class="width90"><span>允许交易时间：</span></td>
					<td><span>
						<input class="Wdate" style="height: 20px;width: 80px;" type="text" id="startDate" name="startDate"
								onclick="WdatePicker({dateFmt:'HH:mm:ss',maxDate:'#F{$dp.$D(\'endDate\')}'});"
								value="${payChannel.startDate}" /> &nbsp;-&nbsp;
								<input class="Wdate" style="height: 20px;width: 80px;" type="text" id="endDate" name="endDate"
								onclick="WdatePicker({dateFmt:'HH:mm:ss',minDate:'#F{$dp.$D(\'startDate\')}'});"
								value="${payChannel.endDate}" />
					</span></td>
					<c:if test="${payChannel.chnlType == 0 || payChannel.chnlType == 4}">
						<td class="width90"><span style="color:red;">*</span><span>结算方式：</span></td>
						<td><span>
							<c:if test="${payChannel.settleType == 0 }">D0</c:if>
							<c:if test="${payChannel.settleType == 1 }">T1</c:if>
						</span></td>
					</c:if>
				</tr>
				<tr class="textcenter">
					<td colspan="4">
						<button class="btn1" type="button" onclick="subForm()">确定修改</button>
						<button class="btn2" onclick="cancelVal()">取消</button>
					</td>
				</tr>
			</table>
		</div>
	</form>
	<iframe name='hidden_frame' id="hidden_frame" style='display: none'></iframe>
</body>
</html>