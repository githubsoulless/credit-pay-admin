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
<title>银行管理</title>
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

</script>
<style type="text/css">
table.table1{
	overflow：auto;
}
table.table1 tr td{
	white-space:nowrap;
}
table.table1 tr th{
	white-space:nowrap;
}
</style>
</head>
<body>
	<div class="page-content">
		<!-- /.page-header -->
		<div class="row">
			<div class="col-xs-12">
				<!-- PAGE CONTENT BEGINS -->

				<div class="row">
					<div class="col-xs-12">
						<div class="table-responsive">
							<table id="sample-table-1"
								class="table table1 table-striped table-bordered table-hover">
								<thead>
									<tr>
										<th width="50">序号</th>
										<th width="80">银行代码</th>
										<th width="100">&nbsp;银行名称</th>
										<th>快捷通道最优排序</th>
									</tr>
								</thead>

								<tbody>
									<c:forEach items="${list }" varStatus="i" var="l">
										<tr>
											<td>${i.index+1}</td>
											<td>${l.bankNo}</td>
											<td>
												${l.bankNm}
												<a href="javascript:void(0)" onclick="add_rule('${l.bankNo}')"  style="float:right;display:block;">添加</a>
												<a href="javascript:void(0)" onclick="submit_rule('${l.bankNo}')"  style="float:right;display:block;">提交</a>
											</td>
											<td>
												<div id="add_${l.bankNo}"  style="float: left;">
												</div>
											</td>
										</tr>
									</c:forEach>
									
									<script>
										function add_rule(id){
											var chnls = $.parseJSON('${chnls}');
											var dom = '<div style="float: left;margin-left:10px;text-align: left">';
											if(chnls != null && chnls.length>0){
												dom+='<select>'
												for(var i=0;i<chnls.length;i++){
													dom+='<option value="'+chnls[i].code+'">'+chnls[i].name+'</option>';														
												}
												dom+='</select>'
											}
											dom+= '<br>';
											dom+= '<a href="javascript:void(0)" onclick="up_rule(this)">上移</a>&nbsp;';
											dom+= '<a href="#">下移</a>&nbsp;';
											dom+= '<a href="#">删除</a>&nbsp;';
											dom+= '</div>';
											$("#add_"+id).append(dom);
										}
										
										function submit_rule(id){
											var chnlRule = "";
											$("#add_"+id).find("select").each(function(){
												var opt = $(this).find("option:selected").val();
												chnlRule = chnlRule+opt+",";
											})
											chnlRule = chnlRule.substr(0,chnlRule.length-1);
											alert("chnlRule:"+chnlRule)
											/* $.ajax({
										        type: "POST",
										        async:false,
										        url: "${ctx }/rootbank/update",
										        data:{"bankNo":bankNo,"chnlRule":chnlRule},
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
										   }); */
										}
										function up_rule(obj){
											
											alert($(obj).parent().html());
											
											
										}
										
										
									</script>
												
								</tbody>
							</table>
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
<iframe name='hidden_frame' id="hidden_frame" style='display: none'></iframe>
</body>
</html>