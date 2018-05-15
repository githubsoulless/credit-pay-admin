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
<link href="${ctx }/static/css/userAnalysis.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src='<c:url value="/static/js/loading.js"/>' language="javascript"></script>
<link href="${ctx }/static/css/page.css" rel="stylesheet" type="text/css" />
<script type="text/javascript"  src="${ctx }/static/js/My97DatePicker/WdatePicker.js"></script>
<%-- <script type="text/javascript" src='<c:url value="/static/js/bootstrap.min.js"/>' language="javascript"></script> --%>
 <script src='<c:url value="/static/js/echarts-2.2.7/js/dist/echarts.js"/>'></script>
<title>用户统计</title>
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
<script type="text/javascript">
       // 路径配置
       require.config({
           paths: {
               echarts: '${ctx}/static/js/echarts-2.2.7/js/dist'
           }
       });
       
    	// 使用
       require(
           [
               'echarts',
               'echarts/chart/pie' // 使用柱状图就加载bar模块，按需加载
           ],
           function (ec) {
               // 基于准备好的dom，初始化echarts图表
               var myChart = ec.init(document.getElementById('main')); 
               var option = {
               	    title : {
               	        text: '',
               	        x:'center'
               	    },
               	    tooltip : {
               	        trigger: 'item',
               	        formatter: "{a} <br/>{b} : {c} ({d}%)"
               	    },
               	    legend: {
               	        orient : 'vertical',
               	        x : 'left',
               	        data:[
								<c:forEach items="${list}" var="l">'${l.levelName}',</c:forEach>
               	              ]
               	    },
               	    toolbox: {
               	        show : true,
               	        feature : {
               	            saveAsImage : {show: true}
               	        }
               	    },
               	    calculable : true,
               	    series : [
               	        {
               	            name:'',
               	            type:'pie',
               	            radius : '55%',
               	            center: ['50%', '60%'],
               	            data:[
								<c:forEach items="${list}" var="l">{value:${l.levelCount}, name:'${l.levelName}'},</c:forEach>
               	            ]
               	        }
               	    ]
               	};
       
               // 为echarts对象加载数据 
               myChart.setOption(option); 
           }
       );
   </script>
</head>
<body>
	<div class="row">
			<div class="col-xs-12">
				<ul class="nav nav-pills tab-select">
					<li role="presentation" class="active mark10px"><a href='<c:url value="/userStatistics/list?type=1"/>' data-toggle="tab">等级分布</a></li>
					<li role="presentation"><a href='<c:url value="/userStatistics/list?type=2"/>' data-toggle="tab">日增统计</a></li>
				</ul>
				<div class="myTabContent tab-content">
					<!--等级分布-->
					<div class="tab-pane fade in active row" id="grade">
						<div class="col-lg-6 col-md-6 center-block piePic" >
							<div style="margin-left:80px;width:400px; height:450px; float:left; display:inline;" id="main"></div>  
						</div>
						<div class="col-lg-6 col-md-6 tableShow">
							<table class="table table-bordered text-center table-hover userGradeo">
								<thead>
									<tr class="">
										<th class="text-center">等级</th>
										<th class="text-center">用户数量</th>
										<th class="text-center">占比</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${list }" var="l">
										<tr>
											<td>${l.levelName }</td>
											<td>${l.levelCount }</td>
											<td>${l.proportion }%</td>
										</tr>
									</c:forEach>
									<tr class="fontWeight">
										<td>合计：</td>
										<td>${totalCount }</td>
										<td></td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
</body>
</html>