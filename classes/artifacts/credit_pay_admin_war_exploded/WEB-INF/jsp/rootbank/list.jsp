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
												<div id="chnl_list_${l.bankNo}">
													<div id="add_${l.bankNo}" name="bank_rule" chnl_rule='${l.chnlRule}' bank_no="${l.bankNo}"  style="float: left;">
														
													</div>
												</div>
											</td>
										</tr>
									</c:forEach>
									
									<script>
										$(function(){
											var size = $("div[name='bank_rule']").size()
											$("div[name='bank_rule']").each(function(index){
												var rule = $(this).attr("chnl_rule");
												var bank_no = $(this).attr("bank_no");
												if(rule != "" && bank_no != ""){
													init_rule(rule,bank_no);			
												}	
											})
										})
									
										function init_rule(rule,bankNo){
											var rule_array = rule.split(",");
											var chnls = $.parseJSON('${chnls}');
											if(rule_array.length >0){
												//有几个规则则显示几个
												for(var i=0;i<rule_array.length;i++){
													var gen_id = guid();
													var dom = '<div style="float: left;margin-left:10px;text-align: left">';
														dom+='<select id="'+gen_id+'">'
														if(chnls != null && chnls.length>0){
															for(var j=0;j<chnls.length;j++){
																if(chnls[j].code == rule_array[i]){
																	dom+='<option value="'+chnls[j].code+'" selected="selected" >'+chnls[j].name+'</option>';	
																}else{
																	dom+='<option value="'+chnls[j].code+'">'+chnls[j].name+'</option>';
																}
															}
														}
														dom+='</select>'
													dom+= '<br>';
													dom+= '<a href="javascript:void(0)" onclick="move_rule(this,\'up\')">上移</a>&nbsp;';
													dom+= '<a href="javascript:void(0)" onclick="move_rule(this,\'down\')">下移</a>&nbsp;';
													dom+= '<a href="javascript:void(0)" onclick="del_obj(this)">删除</a>&nbsp;';
													dom+= '</div>';
													$("#add_"+bankNo).append(dom);
													
													remove_more_option(gen_id,bankNo);
												}
											}
										}
									
										function add_rule(id){
											var gen_id = guid();
											var chnls = $.parseJSON('${chnls}');
											var dom = '<div style="float: left;margin-left:10px;text-align: left">';
											if(chnls != null && chnls.length>0){
												dom+='<select id="'+gen_id+'">'
												for(var i=0;i<chnls.length;i++){
													dom+='<option value="'+chnls[i].code+'">'+chnls[i].name+'</option>';														
												}
												dom+='</select>'
											}
											dom+= '<br>';
											dom+= '<a href="javascript:void(0)" onclick="move_rule(this,\'up\')">上移</a>&nbsp;';
											dom+= '<a href="javascript:void(0)" onclick="move_rule(this,\'down\')">下移</a>&nbsp;';
											dom+= '<a href="javascript:void(0)" onclick="del_obj(this)">删除</a>&nbsp;';
											dom+= '</div>';
											$("#add_"+id).append(dom);

											remove_more_option(gen_id,id);
										}
										
										function remove_more_option(gen_id,id){
											var selected_array = new Array();
											$("#chnl_list_"+id).find("select").each(function(){
												if($(this).attr("id") != gen_id){
													var opt = $(this).find("option:selected").val();
													selected_array.push(opt);
												}
											})
											for (var i=0;i<selected_array.length; i++ ){
												$("#"+gen_id+" option[value='"+selected_array[i]+"']").remove();
											}
											
										}
										
										function submit_rule(id){
											var chnlRule = "";
											$("#add_"+id).find("select").each(function(){
												var opt = $(this).find("option:selected").val();
												chnlRule = chnlRule+opt+",";
											})
											chnlRule = chnlRule.substr(0,chnlRule.length-1);
											$.ajax({
										        type: "POST",
										        async:false,
										        url: "${ctx }/rootbank/update",
										        data:{"bankNo":id,"chnlRule":chnlRule},
										        success: function(msg){
										        	var obj = $.parseJSON(msg);
										        	if(obj.respCode == 200){
										        		alert("修改成功!");
										        		window.location.reload();
										        	}else{
										        		alert("修改失败["+obj.respMsg+"]!");
										        	}
										        }
										   }); 
										}
										function move_rule(obj,type){
											var current_obj = $(obj).parent();
											var up_obj = $(obj).parent().prev()
											var down_obj = $(obj).parent().next()
											if(type == "up"){
												if(up_obj != null){
													$(current_obj).insertBefore(up_obj);
												}
											}else if(type == "down"){
												if(down_obj != null){
													$(current_obj).insertAfter(down_obj);
												}
											}
										}
										function del_obj(obj){
											var current_obj = $(obj).parent();
											$(current_obj).remove();
										}
										function guid() {
											  function S4() {
											    return (((1+Math.random())*0x10000)|0).toString(16).substring(1);
											  }
											  return (S4()+S4()+""+S4()+""+S4()+""+S4()+""+S4()+S4()+S4());
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