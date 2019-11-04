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
    <link href="${ctx }/static/css/bootstrap/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <link href="${ctx }/static/css/bootstrap/ace.min.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src='<c:url value="/static/js/jquery-1.7.1.min.js"/>'></script>
    <script type="text/javascript" src='<c:url value="/static/js/loading.js"/>' language="javascript"></script>
    <link href="${ctx }/static/css/alert.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="${ctx }/static/js/My97DatePicker/WdatePicker.js"></script>
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
        function fastSearch() {
            document.getElementById("searchForm").submit();

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
                callBack(result);
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
<div class="page-content">
    <div class="row">
        <div class="col-xs-12">
            <form action="${ctx}/appContent/updateAppBanner" id="searchForm" name="searchForm" method="post" class="form-inline" role="form">
                <input type="hidden" value="0" name="type"/>
                    <table>
                     <tr>
                         <td>
                        图片标题为：<input name="bannerTitle" id="bannerTitle" type="text"  maxlength="50" class="ipt" value="${appBanner.bannerTitle}"/>
                         </td>
                     </tr>
                    <tr>
                        <td>
                        图片详情地址为：<input name="bannerUrl" id="bannerUrl" type="text"  maxlength="50" class="ipt" value="${appBanner.bannerUrl}"/>
                        </td>
                    </tr>

                    <tr class="textcenter">
                        <td colspan="4">
                            <button class="btn1" type="button" onclick="fastSearch()">保存</button>
                            <button class="btn2" onclick="cancelVal()">取消</button>
                            <input type="hidden"  name="bannerId" id="bannerId" value="${appBanner.bannerId}"></input>
                        </td>
                    </tr>
                    </table>
            </form>
        </div>
    </div>
</div>
<iframe name='hidden_frame' id="hidden_frame" style='display: none'></iframe>
<input type="hidden"  id="closeTp"/>
</body>
</html>