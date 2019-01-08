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
</head>
<body onload="init()">
	<form action="${ctx }/payChannel/detail" id="subForm" method="post"
		target="hidden_frame">
		<input type="hidden" name="type" id="type" value="add">
		<div class="begin">
			<table border="" cellspacing="" cellpadding="">
				<tr>
					<td class="width90"><span style="color:red;">*</span><span>通道代码：</span></td>
					<td><span><input name="code" id="code" value="${payChannel.code }" readonly="readonly" maxlength="20" class="ipt" /></span></td>
					<td class="width90"><span style="color:red;">*</span><span>通道名称：</span></td>
					<td><span> <input name="name" id="name" value="${payChannel.name }" readonly="readonly" maxlength="16" class="ipt"/>
					</span></td>
				</tr>
				<tr>
					<td class="width90"><span><span style="color:red;">*</span>通道类型：</span></td>
					<td><span>
						<select  name="chnlType" id="chnlType" disabled="disabled" >
							<option value="0" <c:if test="${payChannel.chnlType == 0 }">selected="selected"</c:if>>快捷</option>
							<option value="1" <c:if test="${payChannel.chnlType == 1 }">selected="selected"</c:if>>代付</option>
							<option value="2" <c:if test="${payChannel.chnlType == 2 }">selected="selected"</c:if>>实名认证</option>
							<option value="3" <c:if test="${payChannel.chnlType == 3 }">selected="selected"</c:if>>钱包提现</option>
							<option value="4" <c:if test="${payChannel.chnlType == 4 }">selected="selected"</c:if>>快捷消费</option>
							<option value="5" <c:if test="${payChannel.chnlType == 5 }">selected="selected"</c:if>>快捷消费出款</option>
							<option value="6" <c:if test="${payChannel.chnlType == 6 }">selected="selected"</c:if>>服务费支付</option>
							<option value="7" <c:if test="${payChannel.chnlType == 7 }">selected="selected"</c:if>>扫码支付</option>
						</select>
					</span></td>
					<td class="width90"><span style="color:red;">*</span><span>通道权重：</span></td>
					<td><span><input name="priority" id="priority" maxlength="10" value="${payChannel.priority }" readonly="readonly" class="ipt"  />
					</span></td>
					
				</tr>
				<tr>
					<td class="width90"><span style="color:red;">*</span><span>费率类型：</span></td>
					<td><span>
						<select  name="feeType" id="feeType" disabled="disabled" >
							<option value="0" <c:if test="${payChannel.feeType == 0 }">selected="selected"</c:if>>百分比</option>
							<option value="1" <c:if test="${payChannel.feeType == 1 }">selected="selected"</c:if>>按笔</option>
						</select>
					</span></td>
					<td class="width90"><span style="color:red;">*</span><span> 通道费率：</span></td>
					<td><span>
						<c:if test="${payChannel.feeType == 0 }">
							<input name="feeRate" id="feeRate" type="text" value="<fmt:formatNumber value="${payChannel.feeRate*100 }" type="currency" pattern="0.00"/>" readonly="readonly" maxlength="15" style="width: 80px;height: 28px;line-height: 28px;border: 1px solid #a9a9a9;padding-left: 5px; " />%
						</c:if>
						<c:if test="${payChannel.feeType == 1 }">
							<input name="feeRate" id="feeRate" type="text" readonly="readonly" value="<fmt:formatNumber value="${payChannel.feeRate/100 }" type="currency" pattern="0.00"/>" maxlength="15" style="width: 170px;height: 28px;line-height: 28px;border: 1px solid #a9a9a9;padding-left: 5px; " />
							元<c:if test="${payChannel.chnlType ==2 || payChannel.chnlType ==3 }">/笔</c:if>
						</c:if>
						<c:if test="${payChannel.chnlType == 4}">
							<span class="fast_settle">
								，封顶手续费<input name="upperlimit" id="upperlimit" readonly="readonly" value="<fmt:formatNumber value="${payChannel.upperlimit*100 }" type="currency" pattern="0.00"/>" maxlength="15" style="width: 80px;height: 28px;line-height: 28px;border: 1px solid #a9a9a9;padding-left: 5px; "
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
							<select name="integralType" id="integralType" disabled="disabled" >
								<option value="0" <c:if test="${payChannel.integralType == 0 }">selected="selected"</c:if>>无积分</option>
								<option value="1" <c:if test="${payChannel.integralType == 1 }">selected="selected"</c:if>>有积分</option>
							</select>
						</span></td>
						<td class="width90"><span style="color:red;">*</span><span>单笔结算：</span></td>
						<td><span><input name="payFeeRate" id="payFeeRate" readonly="readonly" value="<fmt:formatNumber value="${payChannel.payFeeRate/100 }" type="currency" pattern="0.00"/>" maxlength="15" class="ipt" value="0.00" 
										onkeypress="if(!this.value.match(/^[/+/-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" 
							 			onkeyup="if(!this.value.match(/^[/+/-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" 
							 			onblur="if(!this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?|\.\d*?)?$/))this.value=this.o_value;else{if(this.value.match(/^\.\d+$/))this.value=0+this.value;if(this.value.match(/^\.$/))this.value=0;this.o_value=this.value}"/>
							 			元/笔
						</span></td>
					</tr>
				</c:if>
				<c:if test="${payChannel.chnlType == 0 || payChannel.chnlType == 4 }">
					<tr>
						<td class="width90"><span style="color:red;">*</span><span>单笔最小金额(元)：</span></td>
						<td><span><input name="minAmount" id="minAmount" maxlength="15" readonly="readonly" value='<chrone:fen2Yuan amt="${payChannel.minAmount }"></chrone:fen2Yuan>' class="ipt" /></span></td>
						<td class="width90"><span style="color:red;">*</span><span> 单笔最大金额(元)：</span></td>
						<td><span><input name="maxAmount" id="maxAmount" maxlength="15" readonly="readonly" class="ipt" value='<chrone:fen2Yuan amt="${payChannel.maxAmount }"></chrone:fen2Yuan>' />
						</span></td>
					</tr>
					<tr>
						<td class="width90"><span style="color:red;">*</span><span>单日累计笔数：</span></td>
						<td><span><input name="daySumCnt" id="daySumCnt" maxlength="10" class="ipt" value="${payChannel.daySumCnt }" readonly="readonly" /></span></td>
						<td class="width90"><span style="color:red;">*</span><span>单日累计金额(元)：</span></td>
						<td><span><input name="daySumAmount" id="daySumAmount" maxlength="15" readonly="readonly" class="ipt" value='<chrone:fen2Yuan amt="${payChannel.daySumAmount }"></chrone:fen2Yuan>' />
						</span></td>
					</tr>
				</c:if>
				<tr>
					<td class="width90"><span>允许交易时间：</span></td>
					<td><span>
						<input class="Wdate" style="height: 20px;width: 80px;" readonly="readonly" type="text" id="startDate" name="startDate" value="<fmt:formatDate value="${payChannel.startTime}" pattern="HH:mm:ss" />" /> 
						&nbsp;-&nbsp;
						<input class="Wdate" style="height: 20px;width: 80px;" readonly="readonly" type="text" id="endDate" name="endDate" value="<fmt:formatDate value="${payChannel.endTime}" pattern="HH:mm:ss" />" />
					</span></td>
					<c:if test="${payChannel.chnlType == 0 || payChannel.chnlType ==4 }">
						<td class="width90"><span style="color:red;">*</span><span>结算方式：</span></td>
						<td><span>
							<select name="settleType" id="settleType" disabled="disabled" >
								<option value="0" <c:if test="${payChannel.settleType == 0 }">selected="selected"</c:if>>D0</option>
								<option value="1" <c:if test="${payChannel.settleType == 1 }">selected="selected"</c:if>>T1</option>
							</select>
						</span></td>
					</c:if>
				</tr>

			</table>
		</div>
	</form>
	<iframe name='hidden_frame' id="hidden_frame" style='display: none'></iframe>
</body>
</html>