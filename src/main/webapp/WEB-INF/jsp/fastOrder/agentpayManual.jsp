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
		
		if ($("#userId").val() == "") {
			alert("请输入用户ID");
			return;
		}
		if ($("#amount").val() == "") {
			alert("请输入金额");
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
function queryFee(){
	
	if ($("#userId").val() == "") {
		alert("请输入用户ID");
		return;
	}
	if ($("#amount").val() == "") {
		alert("请输入金额");
		return;
	}
	
	$.ajax({
        type: "POST",
        async:false,
        url: "${ctx }/fastOrder/calcFee",
        data:$("#subForm").serialize(),
        success: function(msg){
        	if(msg != null){
        		var data = msg.split(",");
        		$("#plantFee").text("平台手续费:["+data[0]+"]分");
        		$("#chnlFee").text("通道手续费["+data[1]+"]分");
        		$("#profit").text("利润["+data[2]+"]分");
        		$("#actualAgentPay").text("应代付金额["+data[3]+"]分(包含代付费)");
        		
        		setTimeout(function(){
        			$("#amount").val(data[3])
        		},1000)
        	}
        }
   });
}
	

	
	
</script>
<style type="text/css">
.begin2{
	padding:20px 0px;
}
</style>
</head>
<body onload="init()">
	<form action="${ctx }/fastOrder/agentpayManual" id="subForm" method="post"
		target="hidden_frame">
		<input type="hidden" name="type" value="add">
		<div class="begin begin2">
			<table border="" cellspacing="" cellpadding="" style="white-space: nowrap;">
				<tr>
					<td class="width90"><span style="color:red;">*</span><span>用户ID：</span></td>
					<td><span><input name="userId" id="userId" value="" maxlength="50" class="ipt" /></span></td>
					
					<td class="width90"><span style="color:red;">*</span><span>金额：</span></td>
					<td><span><input name="amount" id="amount" value="" maxlength="20" class="ipt" />单位分(输入消费金额自动计算实际代付金额)</span></td>
				</tr>
				
				<tr>
					<td class="width90"><span><span style="color:red;">*</span>支付通道</span></td>
					<td><span>
						<select  name="channel" id="channel" >
							<option value="reapalfast">融宝快捷</option>
							<option value="huifu">汇付</option>
							<option value="yspay">银盛</option>
							<option value="yakupay">雅酷</option>
						</select>
					</span></td>
				</tr>	
				
				<tr>
				<td class="width90">
					<span id="plantFee"></span><br>
					<span id="chnlFee"></span><br>
					<span id="profit"></span><br>
					<span id="actualAgentPay"></span>
				</td>
				<td></td>
				</tr>	
				
				<tr class="textcenter">
					<td colspan="4">
						<button class="btn1" type="button" onclick="queryFee()">查询手续费</button>
						<button class="btn1" type="button" onclick="subForm()">生成代付</button>
						<button class="btn2" onclick="cancelVal()">取消</button>
					</td>
				</tr>

			</table>
		</div>
	</form>
	<iframe name='hidden_frame' id="hidden_frame" style='display: none'></iframe>
</body>
</html>