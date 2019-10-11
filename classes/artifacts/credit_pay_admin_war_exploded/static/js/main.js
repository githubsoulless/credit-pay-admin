 function open1(plugin, url){
        if ($('#tt').tabs('exists', plugin)){
        $('#tt').tabs('select', plugin);
        var tab = $('#tt').tabs('getSelected');
        $('#tt').tabs('update', {
        	 tab: tab,
             options: {
            	 title:plugin,
                 content: '<iframe src="'+ url+ '" width="100%" height="98%" frameborder="0" scrolling="auto"></iframe>'
             }
        });
        
    } else {
    	$('#tt').tabs('add',{
            title:plugin,
            content: '<iframe src="'+ url+ '" width="100%" height="98%" frameborder="0" scrolling="auto"></iframe>',
            closable:true,
            extractor:function(data){
                return data;
            }
        });
    }
}

 $(function(){
	 /* var datelocalweek=new Array("星期日", "星期一", "星期二","星期三","星期四", "星期五","星期六");  */
	    var datelocalnow=new Date(); 
	    var datelocalyear=datelocalnow.getFullYear(); 
	    var datelocalmonth=(datelocalmonth="0"+(datelocalnow.getMonth()+1)).substr(datelocalmonth.length-2,2); 
	    var datelocalday=(datelocalday="0"+datelocalnow.getDate()).substr(datelocalday.length-2,2); 
	    /* var datelocalweekday=datelocalweek[datelocalnow.getDay()];  */
	    $("#datep").html('【今天是'+datelocalyear+"年"+datelocalmonth+"月"+datelocalday+"日"/* +" "+datelocalweekday */+'】');
	    
	    $(".firstLevel a").click(function(event){
	    	event.preventDefault();
	    	$(".firstLevel li").removeClass("se");
	    	$(event.target).closest("li").addClass("se");
	    	
	    	$(".secondLevel li").hide();
	    	$(".secondLevel li").removeClass("select");
	    	$('.'+$(event.target).attr("href")).show();
	    });
	    $(".secondLevel a").click(function(event){
	    	$(".secondLevel li").removeClass("select");
	    	$(event.target).closest("li").addClass("select");
	    });
	    
	   
	    /**
	     * 选项卡右键操作开始
	     *   
	     *  
	     */
//	    //刷新
//	    $("#m-refresh").click(function(){
//	        var currTab = $('#tt').tabs('getSelected');    //获取选中的标签项
//	        var url = $(currTab.panel('options').content).attr('src');    //获取该选项卡中内容标签（iframe）的 src 属性
//	        /* 重新设置该标签 */
//	        $('#tt').tabs('update',{
//	            tab:currTab,
//	            options:{
//	                content: createTabContent(url)
//	            }
//	        })
//	    });
	    
	    //关闭所有
	    $("#m-closeall").click(function(){
	        $(".tabs li").each(function(i, n){
	            var title = $(n).text();
	            $('#tt').tabs('close',title);    
	        });
	    });
	    
	    //除当前之外关闭所有
	    $("#m-closeother").click(function(){
	        var currTab = $('#tt').tabs('getSelected');
	        currTitle = currTab.panel('options').title;    
	        
	        $(".tabs li").each(function(i, n){
	            var title = $(n).text();
	            
	            if(currTitle != title){
	                $('#tt').tabs('close',title);            
	            }
	        });
	    });
	    
	    //关闭当前
	    $("#m-close").click(function(){
	        var currTab = $('#tt').tabs('getSelected');
	        currTitle = currTab.panel('options').title;    
	        $('#tt').tabs('close', currTitle);
	    });
	    /**
	     * 选项卡右键操作结束
	     */
 });
