<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%request.setAttribute("ctx", request.getContextPath()); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>融亿达还款管理平台</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<meta http-equiv = "X-UA-Compatible" content = "IE=edge,chrome=1" /> 
<script type="text/javascript" src='<c:url value="/static/js/jquery-1.7.1.min.js"/>'></script>
<script type="text/javascript" src='<c:url value="/static/js/main.js"/>'></script>
<link rel="stylesheet" type="text/css" href='<c:url value="/static/css/index.css"/>' />
<link rel="stylesheet" type="text/css" href='<c:url value="/static/css/easyui/icon.css"/>' />
<link rel="stylesheet" type="text/css" href='<c:url value="/static/css/easyui/bootstrap/easyui.css"/>' />
<script type="text/javascript" src='<c:url value="/static/js/jquery.easyui.min.js"/>'></script>
<style type="text/css">
	.nav{padding-left: 20px;}
	.nav ul li{float: left;margin-right: 20px;cursor: pointer;}
	.nav ul li.current{color: #0b87c0;border-bottom: 3px solid #0b87c0;}
</style>
<script type="text/javascript">
    function open1(plugin, url){
        if ($('#tt').tabs('exists', plugin)){
            $('#tt').tabs('select', plugin);
            var tab = $('#tt').tabs('getSelected');
            $('#tt').tabs('update', {
            	 tab: tab,
                 options: {
                	 title:plugin,
                     content: '<iframe src="'+ url+ '" width="100%" height="100%" frameborder="0" scrolling="auto"></iframe>'
                 }
            });
            
        } else {
        	$('#tt').tabs('add',{
                title:plugin,
                content: '<iframe src="'+ url+ '" width="100%" height="100%" frameborder="0" scrolling="auto"></iframe>',
                closable:true,
                extractor:function(data){
                    return data;
                }
            });
        }
    }
    
	$(function(){
		
	    $("#tt").dblclick(function(e){        
	        $('#menu').menu('show', {
	            left: e.pageX,
	            top: e.pageY
	        });
	    });
	    $('#menuUL').tree({
	    	onClick: function(node){
	    		if(node.id==undefined){
	    			return;
	    		}
	    		$('#menuUL').tree('collapseAll');
	    		$('#menuUL').tree("toggle",node.target);
	    	}
	    });
	    
	    $('#menuUL').tree('collapseAll');
		var node = $('#menuUL').tree("getRoots")[0];
		$('#menuUL').tree("toggle",node.target);
	});

</script>

</head>
<% Date date = new Date(); 
	SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
	SimpleDateFormat formatYear = new SimpleDateFormat("yyyy");
%>
<body class="easyui-layout" style="text-align: left;">
	<div data-options="region:'north'" style="background: -ms-linear-gradient(top, #93daff,#4fc3ff);background: -moz-linear-gradient(top,#93daff,#4fc3ff);background: -webkit-gradient(linear, 0% 0%, 0% 100%,from(#93daff), to(#4fc3ff));background: -webkit-gradient(linear, 0% 0%, 0% 100%, from(#93daff),to(#4fc3ff));
									background: -webkit-linear-gradient(top, #93daff, #4fc3ff);background: -o-linear-gradient(top, #93daff, #4fc3ff);height:93px;border: 0px;">
		<div class="top">
            <span class="logo" style="color: white;width: 400px;"><strong style="font-size:25px;">融亿达管理平台</strong></span>
            <div class="topRt clearfix" style="font-size: 20px;">
                <div class="w-wel"><span class="">欢迎你，${sessionScope.LOGIN_SESSION.loginId }&nbsp;!</span></div>
                <div class="u-user"><span>[平台用户]</span></div>
                <div class="p-pwd">
                	<a href="#" class="reKey" onclick="open1('修改密码', '<c:url value="/mgrPwd_update?type=toUpdate" />')">修改密码</a>
                	<a href="#" class="exit"  onclick="javascript:window.location.href='<c:url value="public/loginOut"/>'" >退出系统</a>
                </div>
            </div>
        </div>
	</div>
	<!-- 功能菜单导航 -->
	<c:set var="inCount" value="0"/>
   <div data-options="region:'west',split:true,title:'功能导航'" style="width: 250px; padding: 5px;${inCount!=0?'dsiplay:none;':''}" id="menu">
	 <c:forEach var="p1" items="${ sessionScope.LOGIN_MENU}" varStatus="i">
		 <c:if test="${p1.menuParent=='-1' }">
			<ul class="easyui-tree" id="menuUL"  style="${inCount!=0?'display:none;':''}">
	            	<c:forEach var="p" items="${ sessionScope.LOGIN_MENU}">
	            		<c:if test="${p.menuParent==p1.menuId }">
		            		<li iconCls="icon-menu" id="${p.menuId}">
		            			<c:if test="${empty p.menuUrl }">
			            			<span >${p.menuNm }</span>
		            			</c:if>
		            			<c:if test="${not empty p.menuUrl }">
			            			<span >
			            				<a href="#" onclick="open1('${p.menuNm }','<c:url value="/${p.menuUrl}"/>')">${p.menuNm }</a>
			            			</span>
		            			</c:if>
		            		<c:forEach var="m" items="${ sessionScope.LOGIN_MENU}">
		            			<c:if test="${ m.menuParent==p.menuId}">
		            			<ul>
			            			<li iconCls="icon-diaodan">
									<a href="#" onclick="open1('${m.menuNm }','<c:url value="/${m.menuUrl}"/>')">${m.menuNm }</a></li>
		            			</ul>
		            			</c:if>
		            		</c:forEach>
	            		</c:if>
	            	</c:forEach>
	          </ul>
			<c:set var="inCount" value="${inCount+1 }"/>
		</c:if>
      </c:forEach>
			</div>
	<div data-options="region:'south',border:false" style="height:30px; background: #f0f0f0; padding: 5px;"></div>
	<div data-options="region:'center'">
		<div id="tt" class="easyui-tabs" fit="true" border="false" plain="true">
		</div>
		
<!-- 选项卡右键操作 开始-->		
    <div id="menu" class="easyui-menu" style="width:150px;">
  <!--    <div id="m-refresh">刷新</div>
    <div class="menu-sep"></div>
   -->
    <div id="m-closeall">全部关闭</div>
    <div id="m-closeother">除此之外全部关闭</div>
    <div id="m-close">关闭</div>
<!-- 选项卡右键操作结束 -->	
</div>
	</div>
</body>
</html>
