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
<body>
	<form action="" id="subForm" method="post"
		target="hidden_frame">
		<input type="hidden" name="type" id="type" value="add">
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
					<td><span><chrone:fen2Yuan amt="${coupon.amount }"></chrone:fen2Yuan>元</span></td>
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
					<td><span>
						<c:choose>
							<c:when test="${coupon.coupanCount == -1 }">不限量</c:when>
							<c:otherwise>${coupon.coupanCount }</c:otherwise>
						</c:choose>
					</span></td>
				</tr>
				<tr>
					<td class="width90"><span>参与抽奖：</span></td>
					<td><span>
						<c:if test="${coupon.lotteryType ==0 }">不可抽奖</c:if>
						<c:if test="${coupon.lotteryType ==1 }">可抽奖，中奖概率为<fmt:formatNumber value="${coupon.probabilityWinning*100 }" type="currency" pattern="0.00"/>%</c:if>
					</span></td>
				</tr>
				<c:if test="${coupon.lotteryType ==1 }">
				<tr>
					<td class="width90"><span>可抽奖级别：</span></td>
					<td><span>
						<c:forEach items="${coupon.levels }" var="level">
							${level.levelName }&nbsp;&nbsp;&nbsp;
						</c:forEach>
					</span></td>
				</tr>
				</c:if>
				<tr>
					<td class="width90"><span>有效期：</span></td>
					<td><span>
						<c:if test="${coupon.validityType==0 }">固定天数，${coupon.fixedDays }天有效</c:if>
						<c:if test="${coupon.validityType==1 }">自定义天数，
							生效于<fmt:formatDate value="${coupon.effectStartTime}" pattern="yyyy-MM-dd HH:mm:ss" />&nbsp;--&nbsp;
							<fmt:formatDate value="${coupon.effectEndTime}" pattern="yyyy-MM-dd HH:mm:ss" />
						</c:if>
					</span></td>
				</tr>
				<tr>
					<td class="width90"><span>优惠券状态：</span></td>
					<td><span>
						<c:if test="${coupon.status==0 }">禁用</c:if>
						<c:if test="${coupon.status==1 }">启用</c:if>
					</span></td>
				</tr>
				<tr>
					<td class="width90"><span>使用说明：</span></td>
					<td><span>${coupon.couponDesc }</span></td>
				</tr>
				<tr class="textcenter">
					<td colspan="2">
						<button class="btn2" onclick="cancelVal()">关闭</button>
					</td>
				</tr>

			</table>
		</div>
	</form>
	<iframe name='hidden_frame' id="hidden_frame" style='display: none'></iframe>
</body>
</html>