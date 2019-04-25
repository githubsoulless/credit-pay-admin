<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="chrone" uri="/tag/chrone-taglib" %>
<%
	request.setAttribute("ctx", request.getContextPath());
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src='<c:url value="/static/js/jquery-1.7.1.min.js"/>'></script>
<script type="text/javascript" src='<c:url value="/static/js/loading.js"/>' language="javascript"></script>
<link href="${ctx }/static/css/bootstrap/bootstrap.min.css" rel="stylesheet" type="text/css" />
<link href="${ctx }/static/css/bootstrap/ace.min.css" rel="stylesheet" type="text/css" />
<link href="${ctx }/static/css/alert.css" rel="stylesheet" type="text/css" />
<title>Insert title here</title>
<style type="text/css">
.input_sy{
	width: 208px;
	height: 25px;
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
</script>
</head>
<body>
<div class="begin">
<table >
				<tr>
					<td width="17%">&nbsp;</td>
					<td width="17%">&nbsp;</td>
					<td width="17%">&nbsp;</td>
					<td width="17%">&nbsp;</td>
					<td width="17%">&nbsp;</td>
					<td width="17%">&nbsp;</td>
				</tr>
				<c:set var="sumXfNum" value="0"/>
				<c:set var="sumXfAmt" value="0"/>
				<c:set var="sumPayNum" value="0"/>
				<c:set var="sumPayAmt" value="0"/>
				<c:forEach items="${payPlanTaskList }" var="p">
					<c:if test="${p.status==2 }">
						<c:if test="${p.type==0 }">
							<c:set var="sumXfNum" value="${sumXfNum+1}"/>
							<c:set var="sumXfAmt" value="${sumXfAmt+p.amount}"/>
						</c:if> 
						<c:if test="${p.type==1 }">
							<c:set var="sumPayNum" value="${sumPayNum+1}"/>
							<c:set var="sumPayAmt" value="${sumPayAmt+p.amount}"/>
						</c:if> 
					</c:if>
				</c:forEach>
				<tr>
					<td align="right">还款计划单ID：</td>
					<td>${payPlan.planId}</td>
					<td class="width90">计划总金额(元)：</td>
					<td><span><chrone:fen2Yuan amt="${payPlan.planAmt }"/> </span></td>
					<td class="width90">计划状态：</td>
					<td>
						<c:if test="${payPlan.status==0 }">进行中</c:if>
						<c:if test="${payPlan.status==1 }">执行失败</c:if>
						<c:if test="${payPlan.status==2 }">已完成</c:if>
						<c:if test="${payPlan.status==3 }">已终止</c:if>
						<c:if test="${payPlan.status==4 }">处理中</c:if>
					</td>
				</tr>
				<tr>
					<td class="width90">预计消费笔数：</td>
					<td><span>${payPlan.xfNum }</span></td>
					<td class="width90">已消费笔数：</td>
					<td><span>${sumXfNum}</span></td>
					<td class="width90">已消费金额：</td>
					<td><span><chrone:fen2Yuan amt="${sumXfAmt}"/></span></td>
				</tr>
				<tr>
					<td class="width90">预计还款笔数：</td>
					<td><span>${payPlan.payNum}</span></td>
					<td class="width90">已还款笔数：</td>
					<td><span>${sumPayNum }</span></td>
					<td class="width90">已还款金额：</td>
					<td><span><chrone:fen2Yuan amt="${sumPayAmt }"/></span></td>
				</tr>
				<tr>
					<td class="width90">己执行手续费：</td>
					<td><span><chrone:fen2Yuan amt="${execTotalFee }"/></span></td>
					<td class="width90">总手续费：</td>
					<td><span><chrone:fen2Yuan amt="${totalFee }"/></span></td>
					<td class="width90"></td>
					<td><span></span></td>
				</tr>
				<tr><td colspan="6">
					<table width="100%" id="sample-table-1"
								class="table table-striped table-bordered table-hover">
						<tr>
							<td>序号</td>
							<td>任务类型</td>
							<td>交易金额</td>
							<td>除手续费金额</td>
							<td>包含代付费</td>
							<td>任务流水号</td>
							<td>预计执行日期</td>
							<td>预计执行时间</td>
							<td>任务状态</td>
							<td>消费行业</td>
							<!-- <td>消费城市</td> -->
							<td>实际执行时间</td>
							<td>备注</td>
						</tr>
						<c:forEach items="${payPlanTaskList }" var="p" varStatus="i">
							<tr>
								<td>${i.index+1 }</td>
								<td>
									<c:if test="${p.type==0 }">消费</c:if> 
									<c:if test="${p.type==1 }">还款</c:if> 
								</td>
								<td>
									<chrone:fen2Yuan amt="${p.amount }"/>
								</td>
								<td>
									<chrone:fen2Yuan amt="${p.actualAmt }"/>
								</td>
								<td>
									<c:if test="${p.includeAgentFee==true}">是</c:if> 
									<c:if test="${p.includeAgentFee==false}">否</c:if> 
								</td>
								<td>${p.taskId }</td>
								<td>${p.planDt }</td>
								<td>${p.executeTime }</td>
								<td>
									<c:if test="${p.status==0 }">进行中</c:if>
									<c:if test="${p.status==1 }">执行失败</c:if>
									<c:if test="${p.status==2 }">已完成</c:if>
									<c:if test="${p.status==3 }">已终止</c:if>
								</td>
								<td>
									<c:if test="${p.industry=='01' }">超市百货</c:if>
									<c:if test="${p.industry=='02' }">餐饮</c:if>
									<c:if test="${p.industry=='03' }">娱乐</c:if>
									<c:if test="${p.industry=='04' }">零售</c:if>
									<c:if test="${p.industry=='05' }">快餐</c:if>
									<c:if test="${p.industry=='99' }">其他</c:if>
								</td>
								<!-- <td>${p.city}</td> -->
								<td><fmt:formatDate value="${p.finshTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
								<td>${p.remark }</td>
							</tr>
						</c:forEach>
					</table>
				</td></tr>
				<tr class="textcenter">
					<td colspan="6">
						<button class="btn2" onclick="cancelVal()">关闭</button>
					</td>
				</tr>

			</table>
		</div>
<iframe name='hidden_frame' id="hidden_frame" style='display: none'></iframe>
</body>
</html>