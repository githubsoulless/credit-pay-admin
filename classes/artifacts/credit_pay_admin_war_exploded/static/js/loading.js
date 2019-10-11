function showWait() {
		var sWidth, sHeight;
		sWidth = document.documentElement.scrollWidth;//浏览器工作区域内页面宽度 或使用 screen.width//屏幕的宽度   
		sHeight = document.documentElement.scrollHeight;//屏幕高度（垂直分辨率）   
		var loaddiv;
		if (loaddiv = document.getElementById("loaddiv")) {
			loaddiv.style.display = "block";
		} else {
			loaddiv = document.createElement("DIV");
			loaddiv.id = "loaddiv";
			loaddiv.style.position = "absolute";
			loaddiv.style.zIndex = 1000;
			loaddiv.style.display = "";
			loaddiv.style.left = 0;
			loaddiv.style.top = 0;
			loaddiv.style.border = "1px solid gray";
			loaddiv.style.background = "#ffffff";
			loaddiv.style.padding = "5";
		}
		var winObj=$(window);
		//居中位置计算
		var browserwidth = winObj.width();
		var browserheight = winObj.height();
		var scrollLeft = winObj.scrollLeft();
		var scrollTop = winObj.scrollTop();
		var left = scrollLeft + (browserwidth - 100) / 2;
		var top = scrollTop + (browserheight+20) / 2;
		loaddiv.style.left = left + "px";
		loaddiv.style.top = top + "px";
		loaddiv.innerHTML = "<img src='../static/img/loading2.gif' /><h align='center'>正在处理，请稍等......</h>";
		document.body.style.cursor = "wait";

		//背景层（大小与窗口有效区域相同，即当弹出对话框时，背景显示为放射状透明灰色）   
		var bgObj;
		if (bgObj = document.getElementById("bgDiv")) {
			bgObj.style.display = "block";
		} else {
			bgObj = document.createElement("div");//创建一个div对象（背景层） //动态创建元素，这里创建的是 div   
			//定义div属性，即相当于(相当于，但确不是，必须对对象属性进行定义   
			//<div id="bgDiv" style="position:absolute; top:0; background-color:#777; filter:progid:DXImagesTransform.Microsoft.Alpha(style=3,opacity=25,finishOpacity=75); opacity:0.6; left:0; width:918px; height:768px; z-index:10000;"></div>   
			bgObj.setAttribute('id', 'bgDiv');
			bgObj.style.position = "absolute";
			bgObj.style.top = "0";
			bgObj.style.background = "#ffffff";
			bgObj.style.display = "";
			bgObj.style.filter = "progid:DXImageTransform.Microsoft.Alpha(style=3,opacity=25,finishOpacity=75";
			bgObj.style.opacity = "0.6";
			bgObj.style.left = "0";
			bgObj.style.width = sWidth + "px";
			bgObj.style.height = sHeight + "px";
			bgObj.style.zIndex = "1000000";
		}
		document.body.appendChild(bgObj);//在body内添加该div对象   
		document.body.appendChild(loaddiv);
	}
	
	function hideWait(){
		document.getElementById("loaddiv").style.display = "none";
		document.getElementById("bgDiv").style.display = "none";
		document.body.style.cursor = "";
	}
	
	   //控制tr的颜色
 function showColor(a){
			a.style.backgroundColor="";
	}
	function hideColor(a){


		a.style.backgroundColor="#c2e0f8";

	}
	
	 function showImg(filePath){
			var pathUrl = "url:showImg.jsp?filePath="+filePath+"&a="+encodeURIComponent(new Date());
			$.dialog({content:pathUrl ,
				        title:'查看图片',
				        lock: false,
						background: '#FFF',  //背景色 默认的遮罩背景色为:#DCE2F1浅蓝护眼色 
						max: true,
						//height:'100%',
						width:'max',
						zIndex : 999999,
						min: false,
						resize :true,
						drag: false, 				// 是否允许用户拖动位置
						cache: false, 
						opacity: 0.5	 //透明度 
						
			});
	 }

