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
		var reg = /^[0-9]+(.[0-9]{0,2})?$/;
		
		var coupanCount = $('#coupanCount').val();
		var reg2 = /^[0-9]*[1-9][0-9]*$/;
		if(coupanCount != -1 && !reg2.test(coupanCount)){
			alert('请填写正确的剩余数量');
			$('#coupanCount').focus();
			return false;
		}
		if($('input:radio[name="lotteryType"]:checked').val() == null){
			alert('请选择是否可参与抽奖');
			return false;
		}
		if(1 == $('input:radio[name="lotteryType"]:checked').val()) {
			var probabilityWinning = $('#probabilityWinning').val();
			if(!reg.test(probabilityWinning)){
				alert('请输入正确的中奖概率，最多保留两位小数');
				$("#probabilityWinning").focus();
				return false;
			}
			if(probabilityWinning > 100){
				alert('中奖概率不能大于100%');
				$("#probabilityWinning").focus();
				return false;
			}
			if($('input:checkbox[name="levelIds"]:checked').length == 0){
				alert('请选择可抽奖级别');
				return false;
			}
		}
		if($('input:radio[name="validityType"]:checked').val() == null){
			alert('请选择有效期');
			return false;
		}
		if(0 == $('input:radio[name="validityType"]:checked').val()) {
			var fixedDays = $('#fixedDays').val();
			//fixedDays <= 0 && 
			if(!reg2.test(fixedDays)){
				alert('请输入正确的有效期天数，最多保留两位小数');
				$("#fixedDays").focus();
				return false;
			}
		}
		if(1 == $('input:radio[name="validityType"]:checked').val()) {
			var startDate = $('#startDate').val();
			var endDate = $('#endDate').val();
			if(startDate == null || endDate == null){
				alert('请选择自定义有效期');
				return false;
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
	}
	
</script>
</head>
<body onload="init()">
	<form action="${ctx }/couponMgr/update" id="subForm" method="post"
		target="hidden_frame">
		<input type="hidden" name="type" id="type" value="update">
		<div class="begin">
			<table border="" cellspacing="" style="line-height: 3.0" cellpadding="">
				<tr>
					<td class="width90"><span>ID：</span></td>
					<td><span>${coupon.id }</span></td>
				</tr>
				<tr>
					<td class="width90"><span>优惠券名称：</span></td>
					<td><span>${coupon.name }</span></td>
				</tr>
				<tr>
					<td class="width90"><span>面值：</span></td>
					<td><span><chrone:fen2Yuan amt="${coupon.amount }">元</chrone:fen2Yuan>
					</span></td>
				</tr>
				<tr>
					<td class="width90"><span>订单金额：</span></td>
					<td><span>
						<c:if test="${coupon.limitAmountType==0 }">不限制</c:if>
						<c:if test="${coupon.limitAmountType==1 }">满<chrone:fen2Yuan amt="${coupon.limitAmount }"></chrone:fen2Yuan>元可使用</c:if>
					</span></td>
				</tr>
				<tr>
					<td class="width90"><span>剩余数量：</span></td>
					<td><span><input name="coupanCount" id="coupanCount" maxlength="10" value="${coupon.coupanCount }" style="width: 160px;height: 28px;line-height: 28px;border: 1px solid #a9a9a9;padding-left: 5px; " /></span><span>&nbsp;&nbsp;-1表示不限量</span></td>
				</tr>
				<tr>
					<td class="width90"><span style="color:red;">*</span><span>有效期：</span></td>
					<td><span>
						<input type="radio" name="validityType" id="guding" value="0" <c:if test="${coupon.validityType==0 }">checked="checked"</c:if> /><label for="guding">固定天数，自用户领取之日起</label>
						<input name="fixedDays" id="fixedDays" maxlength="10" <c:if test="${coupon.validityType==1 }">readonly="readonly"</c:if> value="${coupon.fixedDays }" style="width: 60px;height: 28px;line-height: 28px;border: 1px solid #a9a9a9;padding-left: 5px; " onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')" />天有效
						<br>
						<input type="radio" name="validityType" value="1" id="zidingyi" <c:if test="${coupon.validityType==1 }">checked="checked"</c:if> /><label for="zidingyi">自定义，生效于</label>
						<input  class="Wdate input-sm" style="height: 30px" readonly="readonly" <c:if test="${coupon.validityType==0 }">disabled="disabled"</c:if> value="${coupon.startDate }" type="text" id="startDate"  name="startDate"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'%y-%M-%d',maxDate:'#F{$dp.$D(\'endDate\')}'});" />&nbsp;--&nbsp; 
						<input  class="Wdate input-sm" style="height: 30px" readonly="readonly" <c:if test="${coupon.validityType==0 }">disabled="disabled"</c:if> type="text" value="${coupon.endDate }" id="endDate"  name="endDate"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'startDate\')}'});" /> 
					</span></td>
				</tr>
				<tr>
					<td class="width90"><span style="color:red;">*</span><span>优惠券状态：</span></td>
					<td><span>
						<input type="radio" name="status" value="1" <c:if test="${coupon.status==1 }">checked="checked"</c:if> />启用&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="radio" name="status" value="0" <c:if test="${coupon.status==0 }">checked="checked"</c:if> />禁用
					</span></td>
				</tr>
				<tr>
					<td class="width90"><span style="color:red;">*</span><span>参与抽奖：</span></td>
					<td><span>
						<input type="radio" name="lotteryType" value="0" id="no" <c:if test="${coupon.lotteryType==0 }">checked="checked"</c:if> /><label for="no">不可抽奖</label>&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="radio" name="lotteryType" value="1" id="yes" <c:if test="${coupon.lotteryType==1 }">checked="checked"</c:if> /><label for="yes">可抽奖，中奖概率为</label>
						<input type="text" name="probabilityWinning" id="probabilityWinning" value="<fmt:formatNumber value="${coupon.probabilityWinning*100 }" type="currency" pattern="0.00"/>" <c:if test="${coupon.lotteryType==0 }">readonly="readonly"</c:if> style="width: 80px;height: 28px;line-height: 28px;border: 1px solid #a9a9a9;padding-left: 5px; " onkeypress="if(!this.value.match(/^[/+/-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" 
						 		onkeyup="if(!this.value.match(/^[/+/-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" 
								onblur="if(!this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?|\.\d*?)?$/))this.value=this.o_value;else{if(this.value.match(/^\.\d+$/))this.value=0+this.value;if(this.value.match(/^\.$/))this.value=0;this.o_value=this.value}"/> %
						<span style="color: #989898;">&nbsp;&nbsp;可精确到0.01%</span>
					</span></td>
				</tr>
				
				<tr class="forLotteryType" <c:if test="${coupon.lotteryType==0 }">style="display: none"</c:if>>
					<td class="width90"><span style="color:red;">*</span><span>可抽奖级别：</span></td>
					<td><span>
						<c:forEach items="${levels }" var="level">
								<input type="checkbox" name="levelIds" 
									<c:forEach items="${coupon.levels }" var="lev">
										<c:if test="${lev.levelId == level.levelId }">checked="checked"</c:if>
									</c:forEach>
								value="${level.levelId }" id="${level.levelId }" />
								<label for="${level.levelId }">${level.levelName }</label>&nbsp;&nbsp;&nbsp;	
						</c:forEach>
					</span></td>
				</tr>
				<tr>
					<td class="width90"><span>使用说明：</span></td>
					<td>
						<span>
							<textarea rows="7" cols="50" maxlength="50" style="font-size:13px;" name="couponDesc" placeholder="选填，50字以内">${coupon.couponDesc }</textarea>
						</span>
					</td>
				</tr>
				<tr class="textcenter">
					<td colspan="2">
						<input type="hidden" value="${coupon.id }" name="id"/>
						<button class="btn1" type="button" onclick="subForm()">保存</button>
						<button class="btn2" onclick="cancelVal()">取消</button>
					</td>
				</tr>

			</table>
		</div>
	</form>
	<iframe name='hidden_frame' id="hidden_frame" style='display: none'></iframe>
</body>

<script type="text/javascript">
$('input:radio[name="limitAmountType"]').change(function(){
	var type = $(this).val();
	if(0 == type){
		$("#limitAmount").attr("readonly", true);
	}
	if(1 == type){
		$("#limitAmount").attr("readonly", false);
	}
});
$('input:radio[name="lotteryType"]').change(function(){
	var type = $(this).val();
	if(0 == type){
		$("#probabilityWinning").attr("readonly", true);
		$('.forLotteryType').hide();
	}
	if(1 == type){
		$("#probabilityWinning").attr("readonly", false);
		$('.forLotteryType').show();
	}
});
$('input:radio[name="validityType"]').change(function(){
	var type = $(this).val();
	if(0 == type){
		$("#fixedDays").attr("readonly", false);
		$('#startDate').attr('disabled', true);
		$('#endDate').attr('disabled', true);
	}
	if(1 == type){
		$("#fixedDays").attr("readonly", true);
		$('#startDate').attr('disabled', false);
		$('#endDate').attr('disabled', false);
	}
});
</script>
</html>