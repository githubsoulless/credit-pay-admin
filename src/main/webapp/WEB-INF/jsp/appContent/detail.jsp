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
<script type="text/javascript" src="${ctx }/static/js/My97DatePicker/WdatePicker.js"></script>
<title>App内容修改</title>
<style type="text/css">
.fieldset1 {
	padding: .35em .625em .75em;
	margin: 0 2px;
	border: 1px solid silver
}

.legend1 {
	padding: .5em;
	border: 0;
	width: auto
}
.margin10{
	margin: 10px 0px;
}
.color2500f9{
	color: #2500f9;
}
.colorRed{
	color: #F70909;
}
.width100{
	width: 100px;
}
.divbtn{
	margin-top: 20px;
}
</style>
<script language="javascript">
	(function() {
		var _skin, _lhgcore;
		var _search = window.location.search;
		if (_search) {
			_skin = _search.split('demoSkin=')[1];
		};

		document.write('<scr' + 'ipt src="${ctx}/static/js/lhgdialog.js?skin=' + (_skin || 'idialog') + '"></sc'+'ript>');
		window._isDemoSkin = !!_skin;
	})();

	function fastSearch() {
		document.getElementById("searchForm").submit();
	}
	function addClose() {
		if (document.getElementById("closeTp").value == "1") {
			document.getElementById("searchForm").submit();
		}
	}
	function callBack(result) {
		alert(result)
		if (result == "success") {
			alert("操作成功!");
			D.getElementById('closeTp').value = "1";
			api.close();
		} else {
			alert("操作失败," + result + "!");
		}
	}
	function init() {
		<%--changeProfitsType('${params.profits_type }')--%>
		var result = "${message}";
		if (result != "") {
			callBack(result);
		}
	}
	$(function(){
		$(".imgs-container").mouseover(function(){
			$(this).find(".tool").toggle();
		}).mouseout(function(){
			$(this).find(".tool").toggle();
		})

	})
	function upload(){
		var url = "url:${ctx }/appContent/toUpload?type=toAdd&a="+encodeURIComponent(new Date());
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
				window.location.reload();
			}
		});
	}
	function del_banner(bannerId){

		$.ajax({
			type: "POST",
			async:false,
			url: "${ctx}/appContent/delete",
			data:{"bannerId":bannerId},
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
	function upd_banner(bannerId){
		var url = "url:${ctx }/appContent/toUpdateAppBanner?bannerId="+bannerId;
		$.dialog({content:url ,
			title:'修改banner信息',
			lock: true,
			background: '#FFF', /* 背景色 默认的遮罩背景色为:#DCE2F1浅蓝护眼色 */
			max: false,
			height:350,
			width:750,
			min: false,
			opacity: 0.5,	/* 透明度 */
			close: function(){
				addClose();
				window.location.reload();
			}
		});
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
<body onload="init()">
	<div class="page-content">
		<div class="row">
			<div class="col-xs-12">
				<form action="${ctx}/appContent/update" id="searchForm" name="searchForm" method="post" class="form-inline" role="form">
					<input type="hidden" value="0" name="type"/>
					<fieldset class="fieldset1">
						<legend class="legend1">App内容修改</legend>
						<div class="col-lg-6 col-md-8 margin10">
							客服电话：<input type="text"  id="tel" name="tel" value="${appContent.tel}" style="width: 350px; length:10px" >
						</div>
						<div class="col-lg-6 col-md-8 margin10">
							软件下载地址：<input type="text"  id="downloadUrl" name="downloadUrl" value="${appContent.downloadUrl}" style="width: 350px;">
						</div>
						<div class="col-lg-6 col-md-8 margin10">
							背景图片下载地址：<input type="text"  id="backgroundUrl" name="backgroundUrl" value="${appContent.backgroundUrl}" style="width: 350px;">
						</div>
						<div class="col-lg-6 col-md-8 margin10">

							客服电话2(没有可不填)：<input type="text"  id="reservation1" name="reservation1" value="${appContent.reservation1}" style="width: 350px; length:10px" >
						</div>
						<div>
							公司简介文字：<textarea rows="3" cols="130" id="introduction" name="introduction">${appContent.introduction }</textarea>
						</div>
					</fieldset>
					<div class="align-center divbtn">
						<div align="center"><button type="button" class="btn btn-primary"  onclick="fastSearch()">保存设置</button></div>
					</div>
				</form>
				<!--appBanner设置-->
				<fieldset class="fieldset1">
					<legend class="legend1">Banner上传</legend>
					<div class="page-header">
						<div class="row">
							<div class="form-group">
								&nbsp;&nbsp;<button type="button" class="btn btn-primary"  onclick="upload()">上传图片</button>&nbsp;&nbsp;（图片尺寸大小尽量一致）
							</div>
						</div>
					</div>

					<c:if test="${empty list}"><div style="margin:0 auto;width:100px;font-size:14px;">暂无数据</div></c:if>
				<div>
					<c:forEach items="${list }" varStatus="i" var="ban">
						<div class="imgs-container" >
							<div>图片标题为：${ban.bannerTitle}</div>
							<div>图片详情地址为：${ban.bannerUrl}</div>
							<div><img src="${ctx}/download/appContent/${ban.bannerName}"></div>
							<div class="tool">
								<a href="#" onclick="del_banner('${ban.bannerId}')">删除</a>
								<a href="#" onclick="upd_banner('${ban.bannerId}')">修改</a>

							</div>
						</div>
					</c:forEach>
				</div>
				</fieldset>
				<div class="page-header">
					<div class="row">
						<div class="form-group">

							<div colspan="12" align="left">查询结果：${page.rowTotal }条</div>

							<page:page name="page"  formId="pageForm"></page:page>

						</div>
					</div>
				</div>
	<form id="pageForm" action="${ctx }/appContent/detail"  method="post" >
		<input type="hidden"  name="start" id="start"></input>
	</form>
	<input type="hidden" id="closeTp" />
			</div></div></div>
</body>

</html>