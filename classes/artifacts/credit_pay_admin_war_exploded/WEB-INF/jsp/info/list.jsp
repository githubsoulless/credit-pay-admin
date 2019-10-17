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
<script type="text/javascript" src='<c:url value="/static/js/jquery-1.7.1.min.js"/>'></script>
<link href="${ctx }/static/css/bootstrap/bootstrap.min.css" rel="stylesheet" type="text/css" />
<link href="${ctx }/static/css/bootstrap/ace.min.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src='<c:url value="/static/js/loading.js"/>' language="javascript"></script>
<link href="${ctx }/static/css/page.css" rel="stylesheet" type="text/css" />
<script type="text/javascript"  src="${ctx }/static/js/My97DatePicker/WdatePicker.js"></script>
<title>行业资讯</title>
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

function add(){
	var url = "url:${ctx }/info/toAdd?a="+encodeURIComponent(new Date());
	$.dialog({content:url ,
		        title:'添加资讯',
		        lock: true,
				background: '#FFF', /* 背景色 默认的遮罩背景色为:#DCE2F1浅蓝护眼色 */
				max: false,
				height:400,
				width:880,
				min: false,
				opacity: 0.5,	/* 透明度 */
				close: function(){
					addClose();
				}
	});
}

function find(infoId){
	var url = "url:${ctx }/info/find?infoId="+infoId+"&a="+encodeURIComponent(new Date());
	$.dialog({content:url ,
		        title:'查看资讯详情',
		        lock: true,
				background: '#FFF', /* 背景色 默认的遮罩背景色为:#DCE2F1浅蓝护眼色 */
				max: false,
				height:400,
				width:880,
				min: false,
				opacity: 0.5,	/* 透明度 */
				close: function(){
					addClose();
				}
	});
}
function addClose(){
	if(document.getElementById("closeTp").value=="1"){
		document.getElementById("searchForm").submit();
	}
}

function delGuide(infoId){
	if(confirm("是否确认删除此指南?")){
		$.ajax({
			url : "${ctx }/info/delete",
	        type : "POST",
	        data : {"infoId":infoId},
	        dataType: "json",
	        success : function(r) {
	        	if (r > 0) {
					alert('删除成功');
					document.getElementById("searchForm").submit();
				}
				if (r.code == 0) {
					alert('网络繁忙,请稍后再试');
				}
	        }
	    });
	}
}
function update(infoId){
	var url = "url:${ctx }/info/toUpdate?infoId="+infoId+"&a="+encodeURIComponent(new Date());
	$.dialog({content:url ,
		        title:'修改通道信息',
		        lock: true,
				background: '#FFF', /* 背景色 默认的遮罩背景色为:#DCE2F1浅蓝护眼色 */
				max: false,
				height:400,
				width:880,
				min: false,
				opacity: 0.5,	/* 透明度 */
				close: function(){
					addClose();
				}
	});
}
</script>
</head>
<body>
	<div class="page-content">
		<div class="page-header">
			<div class="row">
				<form action="${ctx}/info/list"  id="searchForm"   name="searchForm" method="post" class="form-inline" role="form">
					<div class="form-group">
						<chrone:isAuth authCode="900000701">
						&nbsp;&nbsp;<button type="button" class="btn btn-success" onclick="add()">添加资讯</button>
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
										<th>标题</th>
										<th>略缩图</th>
										<th>计划状态</th>
										<th>资讯详情</th>
										<th>操作</th>
									</tr>
								</thead>

								<tbody>
									<c:forEach items="${list }" varStatus="i" var="l">
										<tr>
											<td>${i.index+1}</td>
											<td>${l.title}</td>
											<td>
												 <img src="${ctx}/download/infoImg/${l.infoId }" height="20px" width="10px">
											</td>
											<td >
												<c:if test="${l.status==0 }">使用中</c:if>
												<c:if test="${l.status==1 }">禁用</c:if>
											</td>
											<td>
											<chrone:isAuth authCode="900000706">
													&nbsp;&nbsp;<a href="#" onclick="find('${l.infoId }')">查看资讯详情</a>
												</chrone:isAuth>
											</td>
											<td>
												<chrone:isAuth authCode="900000702">
													&nbsp;&nbsp;<a href="#" onclick="update('${l.infoId }')">修改</a>
												</chrone:isAuth>
												<chrone:isAuth authCode="900000703">
													&nbsp;&nbsp;<a href="#" onclick="delGuide('${l.infoId }')">删除</a>
												</chrone:isAuth>
											</td>
										</tr>
									</c:forEach>
									<tr>
										<td colspan="12" align="left">查询结果：${page.rowTotal }条</td>
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
<form id="pageForm" action="${ctx }/info/list"  method="post" >
<input type="hidden"  name="start" id="start"></input>
<input type="hidden"  name="title" value="${information.title}"></input>
<input type="hidden"  name="titleImg" value="${information.titleImg}"></input>
<input type="hidden"  name="status" value="${information.status}"></input>
<input type="hidden"  name="content" value="${information.content}"></input>
</form>
<input type="hidden"  id="closeTp"/>
<iframe name='hidden_frame' id="hidden_frame" style='display: none'></iframe>
</body>
</html>