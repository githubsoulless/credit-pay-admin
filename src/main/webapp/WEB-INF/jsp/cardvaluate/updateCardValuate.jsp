<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="chrone" uri="/tag/chrone-taglib" %>
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
        function subForm() {
            var feeOfDouble = $("#feeOfDouble").val()*1
            var feeVal = $("#fee").text()*1

            if($("#feeOfDouble").val()==""&&$("#oldFeeOfDouble").val()==""){
                alert("操作成功!");
                D.getElementById('closeTp').value = "1";
                api.close();
            }

            if($("#feeOfDouble").val()==""||$("#oldFeeOfDouble").val()==""){
                alert("原价或现价不能为空");
                $("#oldFeeOfDouble").focus();
                $("#feeOfDouble").val("");
                $("#oldFeeOfDouble").val("");

                return;
            }
            if($("#feeOfDouble").val()<0||$("#oldFeeOfDouble").val()<0){
                alert("金额不能为负数");
                $("#oldFeeOfDouble").focus();
                $("#feeOfDouble").val("");
                $("#oldFeeOfDouble").val("");
                return;
            }

            if(!/^\d{0,8}\.{0,1}(\d{1,2})?$/.test($("#feeOfDouble").val())||!/^\d{0,8}\.{0,1}(\d{1,2})?$/.test($("#oldFeeOfDouble").val())){
                alert("金额不能超过两位小数");
                $("#oldFeeOfDouble").focus();
                $("#feeOfDouble").val("");
                $("#oldFeeOfDouble").val("");
                return;
            }


            if($("#feeOfDouble").val()<7||$("#oldFeeOfDouble").val()<7){
                alert("金额必须大于7元");
                $("#oldFeeOfDouble").focus();
                $("#feeOfDouble").val("");
                $("#oldFeeOfDouble").val("");
                return;
            }

            showWait();
            document.getElementById("subForm").submit();
        }
        function callBack(result) {
            hideWait();
            if (result == "success") {
                alert("操作成功!");
                D.getElementById('closeTp').value = "1";
                api.close();
            } else {
                alert("操作失败," + result + "!");
            }
        }
        function init() {
            var result = "${message}";
            if (result != "") {
                parent.callBack(result);
            }
        }

    </script>
    <style type="text/css">
        .begin2{
            padding:20px 0px;
        }
    </style>
</head>
<body onload="init()">
<form action="${ctx }/cardvaluate/updateCardValuate" id="subForm" method="post"
      target="hidden_frame">
    <input type="hidden" name="type" value="updateCardValuate">
    <%--<div class="begin begin">--%>
    <div class="begin">
        <%--<table border="" cellspacing="" cellpadding="" style="white-space: nowrap;">--%>
        <table border="" cellspacing="" cellpadding="">
            <tr>
                <td >原价：<span id="oldFee" ><chrone:fen2Yuan amt="${cardValuateFee.oldFee}"/></span></td>
            </tr>
             <tr>
                <td >现价：<span id="fee"><chrone:fen2Yuan amt="${cardValuateFee.fee}"/></span></td>
            </tr>
            <tr>
                <td><span>请输入修改后的原价：</span></td>
            </tr>
            <tr>
                <td><span><input name="oldFeeOfDouble" id="oldFeeOfDouble" type="text"  maxlength="50" class="ipt" /></span></td>
            </tr>
            <tr>
                <td><span>请输入修改后的现价：</span></td>
            </tr>
            <tr>
                <td><span><input name="feeOfDouble" id="feeOfDouble" type="text"  maxlength="50" class="ipt" /></span></td>
            </tr>
            <tr class="textcenter">
                <td colspan="4">
                    <button class="btn1" type="button" onclick="subForm()">保存</button>
                    <button class="btn2" onclick="cancelVal()">取消</button>
                </td>
            </tr>
        </table>
    </div>
</form>
<iframe name='hidden_frame' id="hidden_frame" style='display: none'></iframe>
</body>
</html>