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
<link href="${ctx }/static/css/bootstrap/bootstrap.min.css" rel="stylesheet" type="text/css" />
<link href="${ctx }/static/css/bootstrap/ace.min.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src='<c:url value="/static/js/loading.js"/>' language="javascript"></script>
<link href="${ctx }/static/css/page.css" rel="stylesheet" type="text/css" />
<script type="text/javascript"  src="${ctx }/static/js/My97DatePicker/WdatePicker.js"></script>
<title>APP消息推送管理</title>
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
function add(){
	var url = "url:${ctx }/message/add?type=toAdd&a="+encodeURIComponent(new Date());
	$.dialog({content:url ,
		        title:'创建消息',
		        lock: true,
				background: '#FFF', /* 背景色 默认的遮罩背景色为:#DCE2F1浅蓝护眼色 */
				max: false,
				height:500,
				width:760,
				min: false,
				opacity: 0.5,	/* 透明度 */
				close: function(){
					addClose();
				}
	});
}
function detail(id){
	var url = "url:${ctx }/message/detail?id=" + id + "&a="+encodeURIComponent(new Date());
	$.dialog({content:url ,
		        title:'消息详情',
		        lock: true,
				background: '#FFF', /* 背景色 默认的遮罩背景色为:#DCE2F1浅蓝护眼色 */
				max: false,
				height:500,
				width:760,
				min: false,
				opacity: 0.5,	/* 透明度 */
				close: function(){
					addClose();
				}
	});
}
function userlist(id){
	var url = "url:${ctx }/message/userlist?messageId=" + id + "&a="+encodeURIComponent(new Date());
	$.dialog({content:url ,
		        title:'推送列表',
		        lock: true,
				background: '#FFF', /* 背景色 默认的遮罩背景色为:#DCE2F1浅蓝护眼色 */
				max: false,
				height:500,
				width:760,
				min: false,
				opacity: 0.5,	/* 透明度 */
				close: function(){
					addClose();
				}
	});
}

function del(id){
	var url = "${ctx }/message/delete?id=" + id + "&a="+encodeURIComponent(new Date());
	if(confirm('确认删除此条消息吗？一旦删除将无法恢复')){
		$.ajax({
			type : "POST",
			url : url,
			dateType : "text",
			success : function(r) {
				if("0" == r){
					alert('删除成功');
					document.getElementById("searchForm").submit();
				}
			}
		});
	}
}
function istop(id,type){
	var url = "${ctx }/message/istop?id=" + id + "&type="+type+"&a="+encodeURIComponent(new Date());
	var message="";
	if(type==1){
		message="置顶";
	}else{
		message="取消置顶";
	}
	if(confirm('确认'+message+'此条消息吗？')){
		$.ajax({
			type : "POST",
			url : url,
			dateType : "text",
			success : function(r) {
				if("0" == r){
					alert('操作成功');
					document.getElementById("searchForm").submit();
				}
			}
		});
	}
}
function changeAgent(type,val) {
	var agentList=eval('${agentListJson}');
	if(type==1){//1级
		$("#agentId2").empty();
		$("#agentId3").empty();
		$("#agentId2").append("<option value=''>二级代理</option>");
		$("#agentId3").append("<option value=''>三级代理</option>");
		for(var i=0;i<agentList.length;i++){
			if(agentList[i].parentAgentId==val){
				$("#agentId2").append("<option value='"+agentList[i].agentId+"'>"+agentList[i].agentName+"</option>");
			}
		}
	}else if(type==2){
		$("#agentId3").empty();
		$("#agentId3").append("<option value=''>三级代理</option>");
		for(var i=0;i<agentList.length;i++){
			if(agentList[i].parentAgentId==val){
				$("#agentId3").append("<option value='"+agentList[i].agentId+"'>"+agentList[i].agentName+"</option>");
			}
		}
	}

}
</script>
<style type="text/css">
table.table1{
	overflow：auto;
	text-align: center;
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
		<div class="page-header">
			<div class="row">
				<form action="${ctx}/message/list"  id="searchForm"   name="searchForm" method="post" class="form-inline" role="form">
					<div class="form-group">&nbsp;&nbsp;
						<label class="control-label" for="title">消息标题：</label>
						<input  class="input-sm" type="text" id="title"  name="title" maxlength="30"  value="${message.title}" placeholder="支持模糊查询" /> 
					</div>
					<div class="form-group">&nbsp;&nbsp;
						<label class="control-label padding-left" for="msgType">消息类型：</label>
						<select id="msgType" name="msgType" class="input-sm">
	  					   	<option value="">全部</option>
	  					   	<option value="0" ${message.msgType==0?'selected="selected"':'' }>自动推送</option>
	  					   	<option value="1" ${message.msgType==1?'selected="selected"':'' }>手动推送</option>
	  					</select> 
					</div>
					<div class="form-group">&nbsp;&nbsp;
						<label class="control-label padding-left" for="isTop">是否置顶：</label>
						<select id="isTop" name="isTop" class="input-sm">
	  					   	<option value="">全部</option>
	  					   	<option value="0" ${message.isTop==0?'selected="selected"':'' }>否</option>
	  					   	<option value="1" ${message.isTop==1?'selected="selected"':'' }>是</option>
	  					</select>
					</div>
					<div class="form-group">&nbsp;&nbsp;
						<label class="control-label" for="startDate">创建日期：</label>
						<input  class="Wdate input-sm" style="height: 30px" type="text" id="startDate"  name="startDate"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'endDate\')}'});"  value="${message.startDate}"  />&nbsp;- 
						<input  class="Wdate input-sm" style="height: 30px" type="text" id="endDate"  name="endDate"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'startDate\')}'});"  value="${message.endDate}"  /> 
					</div>
					<br><br>
					<div class="form-group">&nbsp;&nbsp;
						<label class="control-label padding-left" for="levelIds">推送范围：</label>
						<span><input type="radio" name="pushRange" <c:if test="${message.pushRange == 0 }">checked="checked"</c:if> id="all" value="0"/><label for="all">不限</label></span>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<span>
							<input type="radio" name="pushRange" id="levels" <c:if test="${message.pushRange == 1 }">checked="checked"</c:if> value="1"/><label for="levels">按等级</label>
							<select id="levelId" name="levelId" <c:if test="${message.pushRange != 1 }">disabled="disabled"</c:if> class="input-sm">
	  					   		<option value="">全部</option>
	  					   		<c:forEach items="${levelList }" var="level">
	  					   		<option value="${level.levelId }" <c:if test="${message.levelId == level.levelId }">selected="selected"</c:if>>${level.levelName }</option>
	  					   		</c:forEach>
	  						</select>
						</span>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<span>
							<input type="radio" name="pushRange" <c:if test="${message.pushRange == 2 }">checked="checked"</c:if> id="users" value="2"/><label for="users">指定用户</label>
							<input  class="input-sm" type="text" <c:if test="${message.pushRange != 2 }">readonly="readonly"</c:if> id="userId" name="userId" maxlength="11"  value="${message.userId}" placeholder="手机号"/>
						</span>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<span>
							<input type="radio" name="pushRange" <c:if test="${message.pushRange == 3 }">checked="checked"</c:if> id="agents" value="3"/><label for="agents">按代理</label>
							<select id="agentId1" name="agentId1" <c:if test="${message.pushRange != 3 }">disabled="disabled"</c:if> class="input-sm" onchange="changeAgent(1,this.value)">
	  					   	<option value="">一级代理</option>
								<c:forEach items="${agentList }" var="agt">
									<c:if test="${agt.level==1 }">
										<option value='${agt.agentId}' ${message.agentId1==agt.agentId?'selected="selected"':'' }>${agt.agentName}</option>
									</c:if>
								</c:forEach>
	  					 </select> 	
						<select id="agentId2" name="agentId2" <c:if test="${message.pushRange != 3 }">disabled="disabled"</c:if> class="input-sm"  onchange="changeAgent(2,this.value)">
	  					   	<option value="">二级代理</option>
	  					   	<c:forEach items="${agentList }" var="agt">
									<c:if test="${agt.level==2}">   <!-- && agt.parentAgentId==appuser.agentId1 -->
										<option value='${agt.agentId}' ${message.agentId2==agt.agentId?'selected="selected"':'' }>${agt.agentName}</option>
									</c:if>
								</c:forEach>
	  					 </select> 
						<select id="agentId3" name="agentId3" <c:if test="${message.pushRange != 3 }">disabled="disabled"</c:if> class="input-sm">
	  					   	<option value="">三级代理</option>
	  					   	<c:forEach items="${agentList }" var="agt">
									<c:if test="${agt.level==3}">   <!-- && agt.parentAgentId==appuser.agentId2 -->
										<option value='${agt.agentId}' ${message.agentId3==agt.agentId?'selected="selected"':'' }>${agt.agentName}</option>
									</c:if>
								</c:forEach>
	  					 </select> 	
						</span>
					</div>
					<br><br>
					<div class="form-group">&nbsp;&nbsp;
						<label class="control-label" for="tloguserid">创建人：</label>
						<input  class="input-sm" type="text" id="tloguserid"  name="tloguserid" maxlength="30"  value="${message.tloguserid}" /> 
					</div>
					<div class="form-group">
						&nbsp;&nbsp;<button type="button" class="btn btn-primary"  onclick="fastSearch()">查询</button>
						<chrone:isAuth authCode="800000201">
						&nbsp;&nbsp;<button type="button" class="btn btn-success" onclick="add()">创建消息</button>
						</chrone:isAuth>
					</div>
				</form>
			</div>
		</div>
		<!-- /.page-header -->

		<div class="row">
			<div class="col-xs-12">
				<!-- PAGE CONTENT BEGINS -->

				<div class="row">
					<div class="col-xs-12">
						<div class="table-responsive" style="overflow: auto;">
							<table id="sample-table-1"
								class="table table1 table-striped table-bordered table-hover">
								<thead>
									<tr>
										<th>序号</th>
										<th>消息标题</th>
										<th>消息类型</th>
										<th>是否置顶</th>
										<th>推送范围</th>
										<th>阅读统计(未读数/总数)</th>
										<th>创建时间</th>
										<th>创建人</th>
										<th>操作</th>
									</tr>
								</thead>

								<tbody>
									<c:forEach items="${list }" varStatus="i" var="l">
										<tr>
											<td>${i.index+1}</td>
											<td>${l.title }</td>
											<td>
												<c:if test="${l.msgType ==0 }">自动推送</c:if>
												<c:if test="${l.msgType ==1 }">手动推送</c:if>
											</td>
											<td>
												<c:if test="${l.isTop ==0 }">否</c:if>
												<c:if test="${l.isTop ==1 }">是</c:if>
											</td>
											<td>
												<c:if test="${l.pushRange == 0 }">全部</c:if>
												<c:if test="${l.pushRange == 1 }">${l.levelName }</c:if>
												<c:if test="${l.pushRange == 2 }">${l.userId }</c:if>
												<c:if test="${l.pushRange == 3 }">${l.agentName }</c:if>
											</td>
											<td>
												<span <c:if test="${l.notReadCount > 0 }">style="color: red;"</c:if>>${l.notReadCount }</span>
												/${l.totalCount }
											</td>
											<td><fmt:formatDate value="${l.creatTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
											<td>${l.tloguserid }</td>
											<td>
												<chrone:isAuth authCode="800000202">
													<a href="#" onclick="detail('${l.id}')">详情</a>
												</chrone:isAuth>
												<chrone:isAuth authCode="800000203">
													&nbsp;&nbsp;<a href="#" onclick="userlist('${l.id}')">推送列表</a>
												</chrone:isAuth>
												<chrone:isAuth authCode="800000204">
													&nbsp;&nbsp;<a href="#" onclick="del('${l.id}')">删除</a>
												</chrone:isAuth>
												<chrone:isAuth authCode="800000205">
													<c:if test="${l.isTop ==0 }">
														&nbsp;&nbsp;<a href="#" onclick="istop('${l.id}',1)">置顶</a>
													</c:if>
													<c:if test="${l.isTop ==1 }">
														&nbsp;&nbsp;<a href="#" onclick="istop('${l.id}',0)">取消置顶</a>
													</c:if>
												</chrone:isAuth>
											</td>
										</tr>
									</c:forEach>
									<tr>
										<td colspan="9" align="left">查询结果：${page.rowTotal }条</td>
									</tr>
								</tbody>
							</table>
									<page:page name="page"  formId="pageForm"></page:page>
						</div>
						<!-- /.table-responsive -->
					</div>
					<!-- /span -->
				</div>
				<!-- /row -->
			</div>
		</div>
	</div>
<form id="pageForm" action="${ctx }/message/list"  method="post" >
	<input type="hidden"  name="start" id="start"></input>
	<input type="hidden"  name="title" value="${message.title}"></input>
	<input type="hidden"  name="msgType" value="${message.msgType}"></input>
	<input type="hidden"  name="isTop" value="${message.isTop}"></input>
	<input type="hidden"  name="pushRange" value="${message.pushRange}"></input>
	<input type="hidden"  name="levelId" value="${message.levelId}"></input>
	<input type="hidden"  name="userId" value="${message.userId}"></input>
		<input type="hidden"  name="agentId1" value="${message.agentId1}"></input>
	<input type="hidden"  name="agentId2" value="${message.agentId2}"></input>
	<input type="hidden"  name="agentId3" value="${message.agentId3}"></input>
	<input type="hidden"  name="tloguserid" value="${message.tloguserid}"></input>
</form>
<input type="hidden"  id="closeTp"/>
<iframe name='hidden_frame' id="hidden_frame" style='display: none'></iframe>
</body>
<script type="text/javascript">
	$('input:radio[name="pushRange"]').change(function(){
		var pushRange = $(this).val();
		if(0 == pushRange){
			$('#levelId').attr('disabled', true);
			$('#userId').attr('readonly', true);
			$('#agentId1').attr('disabled', true);
			$('#agentId2').attr('disabled', true);
			$('#agentId3').attr('disabled', true);
		}
		if(1 == pushRange){
			$('#levelId').attr('disabled', false);
			$('#userId').attr('readonly', true);
			$('#agentId1').attr('disabled', true);
			$('#agentId2').attr('disabled', true);
			$('#agentId3').attr('disabled', true);
		}
		if(2 == pushRange){
			$('#levelId').attr('disabled', true);
			$('#userId').attr('readonly', false);
			$('#agentId1').attr('disabled', true);
			$('#agentId2').attr('disabled', true);
			$('#agentId3').attr('disabled', true);
		}
		if(3 == pushRange){
			$('#levelId').attr('disabled', true);
			$('#userId').attr('readonly', true);
			$('#agentId1').attr('disabled', false);
			$('#agentId2').attr('disabled', false);
			$('#agentId3').attr('disabled', false);
		}
	});
</script>
</html>