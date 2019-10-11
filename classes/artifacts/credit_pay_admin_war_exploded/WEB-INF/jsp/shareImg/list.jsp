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
<script type="text/javascript" src="${ctx }/static/js/My97DatePicker/WdatePicker.js"></script>
<link href="${ctx }/static/css/page.css" rel="stylesheet" type="text/css" />
<script type="text/javascript"  src="${ctx }/static/js/My97DatePicker/WdatePicker.js"></script>
<title>多图分享</title>
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
function addClose(){
	if(document.getElementById("closeTp").value=="1"){
		window.location.reload();
	}
}

function upload(){
	var url = "url:${ctx }/shareImg/toUpload?type=toAdd&a="+encodeURIComponent(new Date());
	$.dialog({content:url ,
		        title:'上传图片',
		        lock: true,
				background: '#FFF', /* 背景色 默认的遮罩背景色为:#DCE2F1浅蓝护眼色 */
				max: false,
				height:460,
				width:750,
				min: false,
				opacity: 0.5,	/* 透明度 */
				close: function(){
					addClose();
				}
	});
}

function del_img(imgName){
	
	$.ajax({
        type: "POST",
        async:false,
        url: "${ctx}/shareImg/delete",
        data:{"imgName":imgName},
        success: function(resMsg){
        	var json = eval("("+resMsg+")");
        	if(json.code!='200'){
	        	alert(json.msg);	
        	}else{
				alert('删除成功!');
				window.location.reload();
        	}
        }
   });
	
}
/**
 * type=1 禁用
 * type=2 恢复
 * type=3 上移
 * type=4 下移
 */
function up_img(imgName,type){
	
	$.ajax({
        type: "POST",
        async:false,
        url: "${ctx}/shareImg/update",
        data:{"imgName":imgName,"type":type},
        success: function(resMsg){
        	var json = eval("("+resMsg+")");
        	if(json.code!='200'){
	        	alert(json.msg);	
        	}else{
				window.location.reload();
        	}
        }
   });
}



$(function(){
	 $(".imgs-container").mouseover(function(){
			$(this).find(".tool").toggle();
		}).mouseout(function(){
			$(this).find(".tool").toggle();
		})
	
})


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
.imgs-container{width:200px;float: left;border:1px solid #e2e2e2;margin-left:10px;margin-bottom:10px;overflow: hidden;text-align: center;position: relative}
.imgs-container img{width:200px;}
.gray { 
    -webkit-filter: grayscale(100%);
    -moz-filter: grayscale(100%);
    -ms-filter: grayscale(100%);
    -o-filter: grayscale(100%);
    filter: grayscale(100%);
    filter: gray;
}
.imgs-container .forbidden{position: absolute;left:0px;bottom:0px;z-index:100;width:200px;height:266px;line-height: 266px;text-align: center;font-size:25px;font-weight: bold;color: #ff0000;}
.imgs-container .tool{display: none;width:200px;position: absolute;bottom:0px;left:0px;z-index:200;background: #D4D4D4;height:30px;;line-height: 30px;text-align: right;opacity: 0.7;}
.imgs-container .tool a{margin-right:5px;}
</style>
</head>
<body>
	<div class="page-content">
		<div class="page-header">
			<div class="row">
					<div class="form-group">
						&nbsp;&nbsp;<button type="button" class="btn btn-primary"  onclick="upload()">上传图片</button>
					</div>
			</div>
		</div>
		<!-- /.page-header -->
		<c:if test="${empty list}"><div style="margin:0 auto;width:100px;font-size:14px;">暂无数据</div></c:if>
		<c:forEach items="${list }" varStatus="i" var="l">
			<div class="imgs-container" >
				<c:choose>
					<c:when test="${l.imgUse}">
						<img src="${ctx}/download/shareImg/${l.imgName}">
						<div class="tool">
							<a href="#" onclick="del_img('${l.imgName}')">删除</a>
							<a href="#" onclick="up_img('${l.imgName}','1')" >禁用</a>
							<a href="#"  onclick="up_img('${l.imgName}','3')" >上移</a>
							<a href="#"  onclick="up_img('${l.imgName}','4')" >下移</a>
						</div>
					</c:when>
					<c:otherwise>
							<img src="${ctx}/download/shareImg/${l.imgName}" class="gray">
							<div class="forbidden">己禁用</div>
							<div class="tool">
								<a href="#" onclick="del_img('${l.imgName}')">删除</a>
								<a href="#" onclick="up_img('${l.imgName}','2')" >启用</a>
								<a href="#"  onclick="up_img('${l.imgName}','3')" >上移</a>
								<a href="#"  onclick="up_img('${l.imgName}','4')" >下移</a>
							</div>
					</c:otherwise>
				</c:choose>
			</div>
		</c:forEach>
	</div>
<form id="pageForm" action="${ctx }/order/list"  method="post" >
	<input type="hidden"  name="start" id="start"></input>
	<input type="hidden"  name="orderNo" value="${order.orderNo}"></input>
	<input type="hidden"  name="busiOrderNo" value="${order.busiOrderNo}"></input>
	<input type="hidden"  name="planId" value="${order.planId}"></input>
	<input type="hidden"  name="startAmtStr" value="${order.startAmtStr}"></input>
	<input type="hidden"  name="endAmtStr" value="${order.endAmtStr}"></input>
	<input type="hidden"  name="orderTp" value="${order.orderTp}"></input>
	<input type="hidden"  name="paySt" value="${order.paySt}"></input>
	<input type="hidden"  name="startDate" value="${order.startDate}"></input>
	<input type="hidden"  name="endDate" value="${order.endDate}"></input>
	<input type="hidden"  name="cardNo" value="${order.cardNo}"></input>
	<input type="hidden"  name="cardId" value="${order.cardId}"></input>
	<input type="hidden"  name="userId" value="${order.userId}"></input>
	<input type="hidden"  name="chnlOrderNo" value="${order.chnlOrderNo}"></input>
	<input type="hidden"  name="chnlId" value="${order.chnlId}"></input>
	<input type="hidden"  name="taskId" value="${order.taskId}"></input>
</form>
<input type="hidden"  id="closeTp"/>
</body>
</html>