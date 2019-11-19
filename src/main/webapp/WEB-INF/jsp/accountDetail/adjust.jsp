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
            if($("#srcUserId").val()==""){
                alert("手机号不能为空")
                return;
            }
            if(!/^-?[1-9]\d*$/.test($("#srcAmt").val())){
                alert("金额不能为小数或者不为空")
                return;
            }
            if($("#memo").val()==""){
                alert("备注不能为空")
                return;
            }

            document.getElementById("subForm").submit();
        }
        function callBack(result) {
            if (result == "success") {
                alert("操作成功!");
                D.getElementById('closeTp').value = "1";
                api.close();
                window.location.reload();
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
<form action="${ctx }/accountDetail/adjust" id="subForm" method="post" target="hidden_frame">
    <div class="begin">
        <%--<table border="" cellspacing="" cellpadding="" style="white-space: nowrap;">--%>
        <table border="" cellspacing="" cellpadding="">

            <tr>
                <td><span>手机号：</span></td>
            </tr>
            <tr>
                <td><span><input name="srcUserId" id="srcUserId" type="text"  maxlength="50" class="ipt" /></span></td>
            </tr>
            <tr>
                <td><span>调账金额[单位分]：</span></td>
            </tr>
            <tr>
                <td><span><input name="srcAmt" id="srcAmt" type="text"  maxlength="50" class="ipt" /></span></td>
            </tr>
            <tr>
                <td><div class="form-group">&nbsp;&nbsp;
                    <label class="control-label" for="transType">变动类型：</label>
                    <select id="transType" name="transType" class="input-sm">
                        <%--<option value="">全部</option>--%>
                        <option value="0" ${accountDetail.transType==0?'selected="selected"':'' }>升级分润</option>
                        <option value="1" ${accountDetail.transType==1?'selected="selected"':'' }>还款推广收益</option>
                        <option value="4" ${accountDetail.transType==4?'selected="selected"':'' }>快捷推广收益</option>
                        <option value="5" ${accountDetail.transType==5?'selected="selected"':'' }>推广奖励收益</option>
                        <option value="2" ${accountDetail.transType==2?'selected="selected"':'' }>钱包提现</option>
                        <option value="3" ${accountDetail.transType==3?'selected="selected"':'' }>其他</option>

                        <option value="22" ${accountDetail.transType==22?'selected="selected"':'' }>直邀奖励</option>
                        <option value="23" ${accountDetail.transType==23?'selected="selected"':'' }>间邀奖励</option>
                        <option value="24" ${accountDetail.transType==24?'selected="selected"':'' }>团队流水奖</option>
                        <option value="25" ${accountDetail.transType==25?'selected="selected"':'' }>领导奖</option>
                        <option value="26" ${accountDetail.transType==26?'selected="selected"':'' }>分红</option>
                    </select>
                </div></td>
            </tr>
            <tr>
                <td><span>备注：</span></td>
            </tr>
            <tr>
                <td><span><input name="memo" id="memo" type="text"  maxlength="50" class="ipt" value="手动调账"/></span></td>
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