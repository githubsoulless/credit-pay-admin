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
<title>预警级设置</title>
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
	for(var i=0;i<${listCount};i++){
		if($("#status-"+i).val()=='1'){
			if($("#amt1-"+i).val()==""&&$("#amt2-"+i).val()==""&&$("#amt3-"+i).val()==""){
				alert("预警开启最少填写一个阶梯金额");
				$("#amt1-"+i).focus();
				return;
			}
			if(parseFloat($("#amt1-"+i).val())<=0&&parseFloat($("#amt2-"+i).val())<=0&&parseFloat($("#amt3-"+i).val())<=0){
				alert("预警开启最少填写一个阶梯金额");
				$("#amt1-"+i).focus();
				return;
			}
			if($("#emails-"+i).val()==""&&$("#moblies-"+i).val()==""){
				alert("预警开启最少填写一个邮箱或者手机号");
				$("#emails-"+i).focus();
				return;
			}
		}
	}
	document.getElementById("searchForm").submit();
}

function addClose(){
	if(document.getElementById("closeTp").value=="1"){
		document.getElementById("searchForm").submit();
	}
}
function callBack(result) {
	if (result == "success") {
		alert("操作成功!");
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
							<form action="${ctx}/smsWarning/detail" id="searchForm" name="searchForm" method="post" class="form-inline" role="form">
									<input type="hidden" name="type" value="update">
									<c:forEach items="${list }" varStatus="i" var="l">
										<table id="sample-table-1" class="table1 table table-striped table-bordered table-hover">
										<tbody>
											
										<tr>
											<td>序号</td>
											<td>预警名称</td>
											<td>预警状态</td>
											<td>第一阶梯金额</td>
											<td>第二阶梯金额</td>
											<td>第三阶梯金额</td>
											<td>预警邮箱</td>
											<td>预警电话</td>
										</tr>
										<tr>
											<td>
												${i.index+1 }
											</td>
											<td>
												<c:if test="${l.warnType==0 }">交易账户</c:if>
												<c:if test="${l.warnType==1 }">提现账户</c:if>
												<input type="hidden" name="smsWarnings[${i.index}].warnType" value="${l.warnType}">
											</td>
											<td>
												<div class="input-group">
		 											<select name="smsWarnings[${i.index}].status" id="status-${i.index }" class="input-sm form-control">
															<option  value="0" <c:if test="${l.status == 0 }">selected="selected"</c:if>>关闭</option>
															<option value="1" <c:if test="${l.status == 1 }">selected="selected"</c:if>>开启</option>
													</select>
												</div>
											</td>
											<td>
												<div class="input-group">
													<input name="smsWarnings[${i.index}].amt1Str" id="amt1-${i.index}" value='<chrone:fen2Yuan amt="${l.amt1 }"/>' type="text" class="input-sm" maxlength="12" onkeypress="if(!this.value.match(/^[/+/-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" onkeyup="if(!this.value.match(/^[/+/-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" onblur="if(!this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?|\.\d*?)?$/))this.value=this.o_value;else{if(this.value.match(/^\.\d+$/))this.value=0+this.value;if(this.value.match(/^\.$/))this.value=0;this.o_value=this.value}">
													<span class="input-span">元</span>
													<br/>金额为0元阶梯金额不生效
												</div>
											</td>
											<td>
												<div class="input-group">
													<input name="smsWarnings[${i.index}].amt2Str" id="amt2-${i.index}" value='<chrone:fen2Yuan amt="${l.amt2 }"/>' type="text" class="input-sm" maxlength="12" onkeypress="if(!this.value.match(/^[/+/-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" onkeyup="if(!this.value.match(/^[/+/-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" onblur="if(!this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?|\.\d*?)?$/))this.value=this.o_value;else{if(this.value.match(/^\.\d+$/))this.value=0+this.value;if(this.value.match(/^\.$/))this.value=0;this.o_value=this.value}">
													<span class="input-span">元</span>
													<br/>金额为0元阶梯金额不生效
												</div>
											</td>
											<td>
												<div class="input-group">
													<input name="smsWarnings[${i.index}].amt3Str" id="amt3-${i.index}" value='<chrone:fen2Yuan amt="${l.amt3 }"/>' type="text" class="input-sm" maxlength="12" onkeypress="if(!this.value.match(/^[/+/-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" onkeyup="if(!this.value.match(/^[/+/-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" onblur="if(!this.value.match(/^(?:[/+/-]?\d+(?:\.\d+)?|\.\d*?)?$/))this.value=this.o_value;else{if(this.value.match(/^\.\d+$/))this.value=0+this.value;if(this.value.match(/^\.$/))this.value=0;this.o_value=this.value}">
													<span class="input-span">元</span>	
													<br/>金额为0元阶梯金额不生效											
													</div>
											</td>
											<td>
												<div class="input-group">	
												<textarea name="smsWarnings[${i.index}].emails" id="emails-${i.index}" rows="4" cols="25" placeholder="最多支持6个邮箱,用英文逗号隔开">${l.emails }</textarea>
												<br/>最多支持6个邮箱,用英文逗号隔开
												</div>
											</td>
											<td>
											<div class="input-group">	
												<textarea name="smsWarnings[${i.index}].moblies" id="moblies-${i.index}"  rows="4" cols="25"  placeholder="最多支持6个手机号,用英文逗号隔开">${l.moblies }</textarea>
												<br/>最多支持6个手机号,用英文逗号隔开
											</div>
											</td>
										</tr>
										<tr>
											<td colspan="8"></td>
										</tr>
										</tbody>
								</table>
									</c:forEach>
									
								<chrone:isAuth authCode="600000400">
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
</html>