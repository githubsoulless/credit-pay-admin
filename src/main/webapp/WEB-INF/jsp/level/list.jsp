<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="chrone" uri="/tag/chrone-taglib" %>
<%@taglib prefix="page" uri="/tag/mypage-taglib" %>
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
<link href="${ctx }/static/css/page.css" rel="stylesheet" type="text/css" />
<script type="text/javascript"  src="${ctx }/static/js/My97DatePicker/WdatePicker.js"></script>
<title>用户等级设置</title>
<style type="text/css">
.width80 {
	width: 80px;
}

</style>
<script language="javascript">
(function() {
	var _skin, _lhgcore;
	var _search = window.location.search;
	if (_search) {
		_skin = _search.split('demoSkin=')[1];
	};
	
	document.write('<scr'+'ipt src="${ctx}/static/js/lhgdialog.js?skin=' + (_skin || 'idialog') +'"></sc'+'ript>');
	window._isDemoSkin = !!_skin;
})();

function fastSearch(){
	document.getElementById("searchForm").submit();
}

function addClose(){
	if(document.getElementById("closeTp").value=="1"){
		document.getElementById("searchForm").submit();
	}
}
function callBack(result) {
// 	hideWait();
	if (result == "success") {
		alert("操作成功!");
// 		D.getElementById('closeTp').value = "1";
// 		api.close();
	} else {
		alert("操作失败," + result + "!");
	}
}
function init() {
	var result = "${message}";
	if (result != "") {
		callBack(result);
	}
}
</script>
<style type="text/css">
	table.table1{
		border : 2px solid;
	}
	table.table1 tr td{
		white-space:nowrap;
	}
</style>
</head>
<body onload="init()">
	<div class="page-content">
		<div class="row">
			<div class="col-xs-12">
				<!-- PAGE CONTENT BEGINS -->
				<div class="row">
					<div class=""><!-- col-xs-12 -->
						<div class="table-responsive" style="overflow: auto;">
							<form action="${ctx}/level/update" id="searchForm" name="searchForm" method="post" class="form-inline" role="form">
									
									<c:forEach items="${list }" varStatus="i" var="l">
										<table id="sample-table-1" class="table1 table table-striped table-bordered table-hover">
										<tbody>
											
										<tr>
											<td rowspan="2">升级序号</td>
											<td rowspan="2">等级名称</td>
											<td>APP内显示</td>
											<td colspan="2" style="width:260px;">推荐用户数量自动升级</td>
											<!-- <td>服务费率</td> -->
											<td>提现手续费</td>
											<td colspan="2">还款计划允许金额(元)</td>
											<td colspan="2">钱包提现金额(元)</td>
										</tr>
										<tr>
											<td>
											<input type="hidden" name="levels[${i.index }].levelId" value="${l.levelId }">
												<select name="levels[${i.index }].status" id="status-${l.levelId }" class="input-sm form-control">
													<option  value="0" <c:if test="${l.status == 0 }">selected="selected"</c:if>>显示</option>
													<option value="1" <c:if test="${l.status == 1 }">selected="selected"</c:if>>隐藏</option>
												</select>
											</td>
											<td  colspan="2">
												<div class="input-group">
													<input name="levels[${i.index }].levelUpUser" id="levelUpUser-${l.levelId }" value="${l.levelUpUser }" type="text" class="input-sm width80" maxlength="12" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')">
													<span class="input-span">人，0表示禁用推荐升级</span>
												</div>
											</td>
											<%-- <td>
												<div class="input-group">
													<input name="levels[${i.index }].feeRate" id="feeRate-${l.levelId }" value="<fmt:formatNumber value="${l.feeRate }" type="currency" pattern="0.00"/>" type="text" class="input-sm" maxlength="12" onkeypress="if(!this.value.match(/^[/+/-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" onkeyup="if(!this.value.match(/^[/+/-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" onblur="if(!this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?|\.\d*?)?$/))this.value=this.o_value;else{if(this.value.match(/^\.\d+$/))this.value=0+this.value;if(this.value.match(/^\.$/))this.value=0;this.o_value=this.value}">
													<span class="input-span">%</span>
												</div>
											</td> --%>
											<td>
												<div class="input-group">
													<input name="levels[${i.index }].txRateFJ" id="txRate-${l.levelId }" value="<chrone:fen2Yuan amt="${l.txRate }"/>" type="text" class="input-sm" maxlength="12" onkeypress="if(!this.value.match(/^[/+/-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" onkeyup="if(!this.value.match(/^[/+/-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" onblur="if(!this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?|\.\d*?)?$/))this.value=this.o_value;else{if(this.value.match(/^\.\d+$/))this.value=0+this.value;if(this.value.match(/^\.$/))this.value=0;this.o_value=this.value}">
													<span class="input-span">元/笔</span>
												</div>
											</td>
											<td colspan="2">
												<div class="input-group">
													<input name="levels[${i.index }].payMinAmtFJ" id="payMinAmt-${l.levelId }" value="<chrone:fen2Yuan amt="${l.payMinAmt }"/>" type="text" class="input-sm" maxlength="12" onkeypress="if(!this.value.match(/^[/+/-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" onkeyup="if(!this.value.match(/^[/+/-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" onblur="if(!this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?|\.\d*?)?$/))this.value=this.o_value;else{if(this.value.match(/^\.\d+$/))this.value=0+this.value;if(this.value.match(/^\.$/))this.value=0;this.o_value=this.value}">
													-
													<input name="levels[${i.index }].payMaxAmtFJ" id="payMaxAmt-${l.levelId }" value="<chrone:fen2Yuan amt="${l.payMaxAmt }"/>" type="text" class="input-sm" maxlength="12" onkeypress="if(!this.value.match(/^[/+/-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" onkeyup="if(!this.value.match(/^[/+/-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" onblur="if(!this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?|\.\d*?)?$/))this.value=this.o_value;else{if(this.value.match(/^\.\d+$/))this.value=0+this.value;if(this.value.match(/^\.$/))this.value=0;this.o_value=this.value}">
												</div>
											</td>
											<td colspan="2">
												<div class="input-group">	
													<input name="levels[${i.index }].txMinAmtFJ" id="txMinAmt-${l.levelId }" value="<chrone:fen2Yuan amt="${l.txMinAmt }"/>" type="text" class="input-sm" maxlength="12" onkeypress="if(!this.value.match(/^[/+/-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" onkeyup="if(!this.value.match(/^[/+/-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" onblur="if(!this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?|\.\d*?)?$/))this.value=this.o_value;else{if(this.value.match(/^\.\d+$/))this.value=0+this.value;if(this.value.match(/^\.$/))this.value=0;this.o_value=this.value}">
													-
													<input name="levels[${i.index }].txMaxAmtFJ" id="txMaxAmt-${l.levelId }" value="<chrone:fen2Yuan amt="${l.txMaxAmt }"/>" type="text" class="input-sm" maxlength="12" onkeypress="if(!this.value.match(/^[/+/-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" onkeyup="if(!this.value.match(/^[/+/-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" onblur="if(!this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?|\.\d*?)?$/))this.value=this.o_value;else{if(this.value.match(/^\.\d+$/))this.value=0+this.value;if(this.value.match(/^\.$/))this.value=0;this.o_value=this.value}">
												</div>
											</td>
										</tr>
										<tr>
											<td rowspan="2">${l.levelId }</td>
											<td rowspan="2"><input name="levels[${i.index }].levelName" id="levelName-${l.levelId }" value="${l.levelName }" type="text" class="input-sm"  maxlength="45"></td>
											<td>升级费(元)</td>
											<td colspan="2">升级返润方式</td>
											<td>二级用户</td>
											<td>一级用户</td>
											<td>三级代理</td>
											<td>二级代理</td>
											<td>一级代理</td>
										</tr>
										<tr>
											<td>
												<input id="upgradeFee" name="levels[${i.index }].upgradeFeeFJ" value="<chrone:fen2Yuan amt="${l.upgradeFee }"/>" type="text" class="input-sm" onkeypress="if(!this.value.match(/^[/+/-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" onkeyup="if(!this.value.match(/^[/+/-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" onblur="if(!this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?|\.\d*?)?$/))this.value=this.o_value;else{if(this.value.match(/^\.\d+$/))this.value=0+this.value;if(this.value.match(/^\.$/))this.value=0;this.o_value=this.value}">
											</td>
											<td colspan="2">
												<select id="profitsType" name="levels[${i.index }].profitsType" class="input-sm">
													<option value="1" <c:if test="${l.profitsType == 1 }">selected="selected"</c:if>>按比例分配</option>
													<option value="0" <c:if test="${l.profitsType == 0 }">selected="selected"</c:if>>固定金额分配</option>
												</select>
											</td>
<!-- 											<td> -->
<!-- 												<div class="input-group"> -->
<%-- 													<input id="directUserFee" name="levels[${i.index }].directUserFee"  --%>
<%-- 													value="<fmt:formatNumber value="${l.directUserFee }" type="currency" pattern="0.00"/>"  --%>
<!-- 													type="text" class="input-sm" maxlength="12"  -->
<!-- 													onkeypress="if(!this.value.match(/^[/+/-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" onkeyup="if(!this.value.match(/^[/+/-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" onblur="if(!this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?|\.\d*?)?$/))this.value=this.o_value;else{if(this.value.match(/^\.\d+$/))this.value=0+this.value;if(this.value.match(/^\.$/))this.value=0;this.o_value=this.value}"> -->
<!-- 													<span class="input-span"> -->
<%-- 														<c:if test="${l.profitsType == 0 }">元</c:if> --%>
<%-- 														<c:if test="${l.profitsType == 1 }">%</c:if> --%>
<!-- 													</span> -->
<!-- 												</div> -->
<!-- 											</td> -->
											<td>
												<div class="input-group">
													<input id="indirectUserFee" name="levels[${i.index }].indirectUserFee" value="<fmt:formatNumber value="${l.indirectUserFee }" type="currency" pattern="0.00"/>" type="text" class="input-sm" maxlength="12" onkeypress="if(!this.value.match(/^[/+/-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" onkeyup="if(!this.value.match(/^[/+/-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" onblur="if(!this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?|\.\d*?)?$/))this.value=this.o_value;else{if(this.value.match(/^\.\d+$/))this.value=0+this.value;if(this.value.match(/^\.$/))this.value=0;this.o_value=this.value}">
													<span class="input-span">
														<c:if test="${l.profitsType == 0 }">元</c:if>
														<c:if test="${l.profitsType == 1 }">%</c:if>
													</span>
												</div>
											</td>
											<td>
												<div class="input-group">
													<input id="upUserFee" name="levels[${i.index }].upUserFee" value="<fmt:formatNumber value="${l.upUserFee }" type="currency" pattern="0.00"/>" type="text" class="input-sm" maxlength="12" onkeypress="if(!this.value.match(/^[/+/-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" onkeyup="if(!this.value.match(/^[/+/-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" onblur="if(!this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?|\.\d*?)?$/))this.value=this.o_value;else{if(this.value.match(/^\.\d+$/))this.value=0+this.value;if(this.value.match(/^\.$/))this.value=0;this.o_value=this.value}">
													<span class="input-span">
														<c:if test="${l.profitsType == 0 }">元</c:if>
														<c:if test="${l.profitsType == 1 }">%</c:if>
													</span>
												</div>
											</td>
											<td>
												<div class="input-group">
													<input id="directAgentFee" name="levels[${i.index }].directAgentFee" value="<fmt:formatNumber value="${l.directAgentFee }" type="currency" pattern="0.00"/>" type="text" class="input-sm" maxlength="12" onkeypress="if(!this.value.match(/^[/+/-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" onkeyup="if(!this.value.match(/^[/+/-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" onblur="if(!this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?|\.\d*?)?$/))this.value=this.o_value;else{if(this.value.match(/^\.\d+$/))this.value=0+this.value;if(this.value.match(/^\.$/))this.value=0;this.o_value=this.value}">
													<span class="input-span">
														<c:if test="${l.profitsType == 0 }">元</c:if>
														<c:if test="${l.profitsType == 1 }">%</c:if>
													</span>
												</div>
											</td>
											<td>
												<div class="input-group">
													<input id="indirectAgentFee" name="levels[${i.index }].indirectAgentFee" value="<fmt:formatNumber value="${l.indirectAgentFee }" type="currency" pattern="0.00"/>" type="text" class="input-sm" maxlength="12" onkeypress="if(!this.value.match(/^[/+/-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" onkeyup="if(!this.value.match(/^[/+/-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" onblur="if(!this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?|\.\d*?)?$/))this.value=this.o_value;else{if(this.value.match(/^\.\d+$/))this.value=0+this.value;if(this.value.match(/^\.$/))this.value=0;this.o_value=this.value}">
													<span class="input-span">
														<c:if test="${l.profitsType == 0 }">元</c:if>
														<c:if test="${l.profitsType == 1 }">%</c:if>
													</span>
												</div>
											</td>
											<td>
												<div class="input-group">
													<input id="upAgentFee" name="levels[${i.index }].upAgentFee" value="<fmt:formatNumber value="${l.upAgentFee }" type="currency" pattern="0.00"/>" type="text" class="input-sm" maxlength="12" onkeypress="if(!this.value.match(/^[/+/-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" onkeyup="if(!this.value.match(/^[/+/-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" onblur="if(!this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?|\.\d*?)?$/))this.value=this.o_value;else{if(this.value.match(/^\.\d+$/))this.value=0+this.value;if(this.value.match(/^\.$/))this.value=0;this.o_value=this.value}">
													<span class="input-span">
														<c:if test="${l.profitsType == 0 }">元</c:if>
														<c:if test="${l.profitsType == 1 }">%</c:if>
													</span>
												</div>
											</td>
										</tr>
										<tr>
											<td colspan="10"></td>
										</tr>
										</tbody>
								</table>
									</c:forEach>
								<chrone:isAuth authCode="600000101">
									<div align="center"><button type="button" class="btn btn-primary"  onclick="fastSearch()">保存设置</button></div>
								</chrone:isAuth>
							</form>
						</div>
						<!-- /.table-responsive -->
					</div>
					<!-- /span -->
				</div>
				<!-- /row -->
			</div>
		</div>
	</div>
<input type="hidden"  id="closeTp"/>
</body>
<script type="text/javascript">
	$(document).on('change','#profitsType',function(e){
		var profitsType = $(this).parent().parent().find('#profitsType');
		if(profitsType.val() == 0){
			$(this).parent().parent().find('.input-span').html('元');
		}
		if(profitsType.val() == 1){
			$(this).parent().parent().find('.input-span').html('%');
		}
	});
</script>
</html>