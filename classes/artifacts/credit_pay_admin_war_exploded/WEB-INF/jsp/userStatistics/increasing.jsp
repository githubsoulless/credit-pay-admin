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
 <script src='<c:url value="/static/js/echarts-2.2.7/js/dist/echarts.js"/>'></script>
<title>日增统计</title>
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

function fastSearch(val){
	$('#flag').val(val);
	document.getElementById("searchForm").submit();
}

function addClose(){
	if(document.getElementById("closeTp").value=="1"){
		document.getElementById("searchForm").submit();
	}
}
function changeDateType(){
	fastSearch('1');
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
               'echarts/chart/line',
               'echarts/chart/bar' // 使用柱状图就加载bar模块，按需加载
           ],
           function (ec) {
               // 基于准备好的dom，初始化echarts图表
               var myChart = ec.init(document.getElementById('main')); 
               var option = {
               	    tooltip : {
               	        trigger: 'axis'
               	    },
               	    legend: {
               	        data:['新注册用户数量']
               	    },
               	    toolbox: {
               	        show : false,
               	        feature : {
               	            mark : {show: true},
               	            magicType : {show: true, type: ['line', 'bar']},
               	            restore : {show: true}
               	        }
               	    },
               	    xAxis : [
               	        {
               	            type : 'category',
               	            position: 'bottom',
               	            boundaryGap: true,
               	            axisLine : {    // 轴线
               	                show: true,
               	                lineStyle: {
               	                    color: 'green',
               	                    type: 'solid',
               	                    width: 2
               	                }
               	            },
               	            axisTick : {    // 轴标记
               	                show:true,
               	                length: 10,
               	                lineStyle: {
               	                    color: 'red',
               	                    type: 'solid',
               	                    width: 2
               	                }
               	            },
               	            axisLabel : {
               	                show:true,
               	                interval: 'auto',
               	                rotate: 45,
               	                margin: 8,
               	                formatter: '{value}',
               	                textStyle: {
               	                    color: 'blue',
               	                    fontFamily: 'sans-serif',
               	                    fontSize: 15,
               	                    fontStyle: 'italic',
               	                    fontWeight: 'bold'
               	                }
               	            },
               	            splitLine : {
               	                show:true,
               	                lineStyle: {
               	                    color: '#483d8b',
               	                    type: 'dashed',
               	                    width: 1
               	                }
               	            },
               	            splitArea : {
               	                show: true,
               	                areaStyle:{
               	                    color:['rgba(144,238,144,0.3)','rgba(135,200,250,0.3)']
               	                }
               	            },
               	            data : [
									<c:forEach items="${listMap}" varStatus="i" var="l">${l.days},</c:forEach>
               	            ]
               	        },
               	         {
               	            type : 'category',
               	            data : [
									<c:forEach  items="${listMap}" varStatus="i" var="l">${l.days},</c:forEach>
               	                    ]
               	        }
               	    ],
               	    yAxis : [
               	        {
               	            type : 'value',
               	            position: 'left',
               	            boundaryGap: [0,0.1],
               	            axisLine : {    // 轴线
               	                show: true,
               	                lineStyle: {
               	                    color: 'red',
               	                    type: 'dashed',
               	                    width: 2
               	                }
               	            },
               	            axisTick : {    // 轴标记
               	                show:true,
               	                length: 10,
               	                lineStyle: {
               	                    color: 'green',
               	                    type: 'solid',
               	                    width: 2
               	                }
               	            },
               	            axisLabel : {
               	                show:true,
               	                interval: 'auto',    // {number}
               	                rotate: -45,
               	                margin: 18,
               	                formatter: '{value} 人',    // Template formatter!
               	                textStyle: {
               	                    color: '#1e90ff',
               	                    fontFamily: 'verdana',
               	                    fontSize: 10,
               	                    fontStyle: 'normal',
               	                    fontWeight: 'bold'
               	                }
               	            },
               	            splitLine : {
               	                show:true,
               	                lineStyle: {
               	                    color: '#483d8b',
               	                    type: 'dotted',
               	                    width: 2
               	                }
               	            },
               	            splitArea : {
               	                show: true,
               	                areaStyle:{
               	                    color:['rgba(205,92,92,0.3)','rgba(255,215,0,0.3)']
               	                }
               	            }
               	        },
               	        {
               	            type : 'value',
               	            splitNumber: 10,
               	            axisLabel : {
               	                formatter: function (value) {
               	                    return value + ' 人'
               	                }
               	            },
               	            splitLine : {
               	                show: false
               	            }
               	        } 
               	    ],
               	    series : [
               	        {
               	            name: '新注册用户数量',
               	            type: 'line',
               	            data:[
								<c:forEach  items="${listMap}" varStatus="i" var="l">${l.count},</c:forEach>
								]
               	        },
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
					<li role="presentation"><a href='<c:url value="/userStatistics/list?type=1"/>' data-toggle="tab">等级分布</a></li>
					<li role="presentation" class="active mark10px"><a href="#" data-toggle="tab">日增统计</a></li>
				</ul>
				<div class="myTabContent tab-content">
					<!--日增统计-->
					<div class="tab-pane fade in active row" id="statistics">
						<!--查询-->
						<div class="row margin10px">
							<form action='<c:url value="/userStatistics/list"/>' id="searchForm" name="searchForm" method="post" class="form-inline" role="form">
							<div class="form-group">
								<select id="dateType" name="dateType" onchange="changeDateType()" class="input-sm">
	  					   			<option value="1" <c:if test="${dateType ==1 }">selected="selected"</c:if>>最近7天</option>
	  					   			<option value="2" <c:if test="${dateType ==2 }">selected="selected"</c:if>>最近15天</option>
	  					   			<option value="3" <c:if test="${dateType ==3 }">selected="selected"</c:if>>最近30天</option>
	  							</select>
							</div>
							<div class="form-group">&nbsp;&nbsp;
								<label class="control-label" for="startDate">注册日期：</label>
								<input class="Wdate input-sm" style="height: 30px" type="text" id="startDate" name="startDate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'endDate\')}'});" value="${appUser.startDate }">&nbsp;-
								<input class="Wdate input-sm" style="height: 30px" type="text" id="endDate" name="endDate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'startDate\')}'});" value="${appUser.endDate }">
							</div>
							<input type="hidden" value="2" name="type" />
							<input type="hidden" value="${flag }" id="flag" name="flag" />
							<div class="form-group">
									&nbsp;&nbsp;<button type="button" class="btn btn-primary glyphicon glyphicon-search" onclick="fastSearch('2')">查询</button>
							</div>
							</form>
						</div>
						<div class="trendPic">
							<div id="main" style="height:400px"></div>
						</div>
						<div class="" style="margin-top: 110px;">
							<table class="table table-bordered text-center table-hover userGradet">
								<thead>
									<tr class="">
										<th class="text-center">日期</th>
										<th class="text-center">新注册用户数量</th>
										<th class="text-center">累计用户数量</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${list }" var="l">
										<tr>
										<td>${l.days }</td>
										<td>${l.count }</td>
										<td>${l.count + l.totalcount }</td>
									</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>

				</div>
			</div>
		</div>
</body>
</html>