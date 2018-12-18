<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	request.setAttribute("ctx", request.getContextPath());
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src='<c:url value="/static/js/jquery-1.7.1.min.js"/>'></script>
<script type="text/javascript" src='<c:url value="/static/js/loading.js"/>' language="javascript"></script>
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
function subForm(){
	if($("#loginId").val()==""){
		alert("登录账户不能为空");
		$("#loginId").focus();
		return;
	}
	/* if($("#certNo").val()==""){
		alert("身份证不能为空");
		$("#certNo").focus();
		return;
	} */
	/* if($("#parentUserId").val()==""){
		alert("推荐人不能为空");
		$("#parentUserId").focus();
		return;
	}
	if($("#parentUserId").val() == '${appuser.userId}'){
		alert("推荐人不能是本人");
		$("#parentUserId").focus();
		return;
	} */
	
	W.showWait();
	document.getElementById("subForm").submit();
}
function callBack(result) {
	W.hideWait();
	if(result=="success"){
		alert("操作成功!");
		D.getElementById('closeTp').value = "1";
		api.close();
	}else{
		alert("操作失败,"+result+"!");
	}
}
function init(){
	var result="${message}";
	if(result!=""){
		parent.callBack(result);
	}
}
</script>
</head>
<body onload="init()">
<form action="${ctx}/appUser/update" id="subForm" method="post" target="hidden_frame">
<input name="userId" type="hidden" value="${appuser.userId }"/>
<input name="type" type="hidden" value="update"/>
<div class="begin">
<table style="white-space: nowrap;">
<tr><td colspan="6" class="tdaa" >基本信息</td></tr>
				<tr>
					<td align="right">注册帐号：</td>
					<td>${appuser.userId}</td>
					<td class="width90"><span>等级：</span></td>
					<td><span>
						<select id="levelId" name="levelId">
	  					   	<c:forEach items="${levelList }" var="lvl">
	  					   			<option value="${lvl.levelId}" ${appuser.levelId==lvl.levelId?'selected="selected"':'' }>${lvl.levelName}</option>
							</c:forEach>
	  					</select> 
					</span></td>
					<td class="width90">注册时间：</td>
					<td><fmt:formatDate value="${appuser.rowCrtTs}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
				</tr>
				<tr>
					<td class="width90"><span>登录账户：</span></td>
					<td><span><input  class="ipt" type="text" id="loginId" name="loginId" maxlength="15"  value="${appuser.loginId}" /></span></td>
					<td class="width90"><span>真实姓名：</span></td>
					<td><span><input  class="ipt" type="text" id="accountName" readonly="readonly" name="accountName" maxlength="15"  value="${appuser.accountName}" />
					</span></td>
					<td class="width90"><span>身份证：</span></td>
					<td><span><input  class="ipt" type="text" id="certNo" readonly="readonly" name="certNo" maxlength="18"  value="${appuser.certNo}" />
					</span></td>
				</tr>
				<tr>
					<td class="width90"><span>推荐人：</span></td>
					<td><span>${appuser.parentUserId }</span></td>
					<td class="width90"><span>所属代理：</span></td>
					<td><span>
						<select id="agentId3" disabled="disabled" name="agentId" class="input-sm">
							<option value=""></option>
							<c:forEach items="${agentList }" var="agt">
		  					   	<option value="${agt.agentId}" ${appuser.agentId==agt.agentId?'selected="selected"':'' }>${agt.agentName }</option>
							</c:forEach>
	  					 </select>
					</span></td>
					<td class="width90"><span>状态：</span></td>
					<td><span>
						<select id="status" name="status">
	  					   	<option value="0" ${appuser.status=='0'?'selected="selected"':'' }>正常</option>
	  					   	<option value="1" ${appuser.status=='1'?'selected="selected"':'' }>禁用</option>
	  					   </select> 
					</span></td>
				</tr>
				<tr>
					<td class="width90"><span>最近修改时间：</span></td>
					<td><fmt:formatDate value="${appuser.rowCrtTs}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<tr><td colspan="6" >&nbsp;</td></tr>
				<tr><td colspan="6" class="tdaa" >结算卡信息</td></tr>
				<tr>
					<td class="width90"><span>结算卡号：</span></td>
					<td><span>${appuser.cardNo }</span></td>
					<td class="width90"><span>支行名称：</span></td>
					<td><span> ${appuser.pmsBankName }</span></td>
					<td class="width90">联行号：<span></span></td>
					<td><span>${appuser.pmsBankNo }</span></td>
				</tr>
				<tr>
					<td class="width90"><span>预留手机：</span></td>
					<td><span>${appuser.mobile}</span></td>
					<td class="width90"><span></span></td>
					<td><span> </span></td>
					<td class="width90"><span></span></td>
					<td><span></span></td>
				</tr>
				<tr><td colspan="6" >&nbsp;</td></tr>
				<tr><td colspan="6" class="tdaa" >照片信息</td></tr>
				<tr>
					<td colspan="2" align="center">
						<span>
							<c:if test="${not empty appuser.certCorrect }">
								<img alt="" class="pimg" width="300px;" height="180px;" src="${ctx }/appUser/img?fileName=${appuser.certCorrect}"/>
							</c:if>
							<c:if test="${empty appuser.certCorrect }">
								<img alt="" class="pimg" src="${ctx }/static/img/default.png"/>
							</c:if>
						</span>
					</td>
					<td colspan="2" align="center" class="pimg">
						<span>
							<c:if test="${not empty appuser.certOpposite }">
								<img alt="" class="pimg" width="300px;" height="180px;" src="${ctx }/appUser/img?fileName=${appuser.certOpposite}"/>
							</c:if>
							<c:if test="${empty appuser.certOpposite }">
								<img alt="" class="pimg" src="${ctx }/static/img/default.png"/>
							</c:if>
						</span>
					</td>
					<td colspan="2" align="center">
						<span>
							<c:if test="${not empty appuser.certMeet }">
								<img alt="" class="pimg" width="300px;" height="180px;" src="${ctx }/appUser/img?fileName=${appuser.certMeet}"/>
							</c:if>
							<c:if test="${empty appuser.certMeet }">
								<img alt="" class="pimg" src="${ctx }/static/img/default.png"/>
							</c:if>
						</span>
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center"><span>身份证正面</span></td>
					<td colspan="2" align="center"><span>身份证背面</span></td>
					<td colspan="2" align="center"><span>手持合照</span></td>
				</tr>
				
				<tr class="textcenter">
					<td colspan="6">
						<button class="btn1" type="button" onclick="subForm()">保存</button>
						<button class="btn2" onclick="cancelVal()">取消</button>
					</td>
				</tr>

			</table>
		</div>
		<div id="outerdiv" style="position:fixed;top:0;left:0;background:rgba(0,0,0,0.7);z-index:2;width:100%;height:100%;display:none;">
			<div id="innerdiv" style="position:absolute;">
				<img id="bigimg" style="border:5px solid #fff;" src="" />
			</div>
		</div>
	</form>
<iframe name='hidden_frame' id="hidden_frame" style='display: none'></iframe>
</body>
<script type="text/javascript">
function imgShow(outerdiv, innerdiv, bigimg, _this){  
    var src = _this.attr("src");//获取当前点击的pimg元素中的src属性  
    $(bigimg).attr("src", src);//设置#bigimg元素的src属性  
        /*获取当前点击图片的真实大小，并显示弹出层及大图*/  
    $("<img/>").attr("src", src).load(function(){  
        var windowW = $(window).width();//获取当前窗口宽度  
        var windowH = $(window).height();//获取当前窗口高度  
        var realWidth = this.width;//获取图片真实宽度  
        var realHeight = this.height;//获取图片真实高度  
        var imgWidth, imgHeight;  
        var scale = 0.8;//缩放尺寸，当图片真实宽度和高度大于窗口宽度和高度时进行缩放  
          
        if(realHeight>windowH*scale) {//判断图片高度  
            imgHeight = windowH*scale;//如大于窗口高度，图片高度进行缩放  
            imgWidth = imgHeight/realHeight*realWidth;//等比例缩放宽度  
            if(imgWidth>windowW*scale) {//如宽度扔大于窗口宽度  
                imgWidth = windowW*scale;//再对宽度进行缩放  
            }  
        } else if(realWidth>windowW*scale) {//如图片高度合适，判断图片宽度  
            imgWidth = windowW*scale;//如大于窗口宽度，图片宽度进行缩放  
                        imgHeight = imgWidth/realWidth*realHeight;//等比例缩放高度  
        } else {//如果图片真实高度和宽度都符合要求，高宽不变  
            imgWidth = realWidth;  
            imgHeight = realHeight;  
        }  
                $(bigimg).css("width",imgWidth);//以最终的宽度对图片缩放  
          
        var w = (windowW-imgWidth)/2;//计算图片与窗口左边距  
        var h = (windowH-imgHeight)/2;//计算图片与窗口上边距  
        $(innerdiv).css({"top":h, "left":w});//设置#innerdiv的top和left属性  
        $(outerdiv).fadeIn("fast");//淡入显示#outerdiv及.pimg  
    });  
      
    $(outerdiv).click(function(){//再次点击淡出消失弹出层  
        $(this).fadeOut("fast");  
    });  
}  
$(function(){  
    $(".pimg").click(function(){  
        var _this = $(this);//将当前的pimg元素作为_this传入函数  
        imgShow("#outerdiv", "#innerdiv", "#bigimg", _this);  
    });  
});  
</script>
</html>