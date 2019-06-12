<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"> 
<html>
  <head>
    <title>油田数据分析系统</title>
	<meta http-equiv="keywords" content="油田数据分析系统,首页"/>
	<meta http-equiv="description" content="油田数据分析系统"/>
	<link href="/plugins/layui/css/layui.css" rel="stylesheet" type="text/css"/>
	<link href="/css/diyReset.css" rel="stylesheet" type="text/css"/>
	<link href="/css/common.css" rel="stylesheet" type="text/css"/>
	<link href="/css/index.css" rel="stylesheet" type="text/css"/>
	<link href="/css/dottingAnimation.css" rel="stylesheet" type="text/css"/>
	<link href="/plugins/layui/css/modules/layui-icon-extend/iconfont.css" rel="stylesheet" type="text/css"/>
	<link href="/plugins/pace/pace-theme-flash.min.css" rel="stylesheet" type="text/css"/>
	<script src="/plugins/pace/pace.min.js"></script>
	<script type="text/javascript">
		var groupName = "${sessionScope.groupName}";
		var userName = "${sessionScope.userName}";
	</script>
  </head>
  
  <body class="layui-layout-body">
  	<div class="layui-layout layui-layout-admin">
  		<div class="layui-header">
  			<div class="layui-logo">油田数据分析系统 <i id="animation-left-nav" class="layui-icon layui-icon-shrink-right"></i></div>
  			<ul class="layui-nav layui-layout-right">
  				<li class="weather">
  					<div id="tp-weather-widget"></div>
	  					<script>(function(T,h,i,n,k,P,a,g,e){g=function(){P=h.createElement(i);a=h.getElementsByTagName(i)[0];P.src=k;P.charset="utf-8";P.async=1;a.parentNode.insertBefore(P,a)};T["ThinkPageWeatherWidgetObject"]=n;T[n]||(T[n]=function(){(T[n].q=T[n].q||[]).push(arguments)});T[n].l=+new Date();if(T.attachEvent){T.attachEvent("onload",g)}else{T.addEventListener("load",g,false)}}(window,document,"script","tpwidget","//widget.seniverse.com/widget/chameleon.js"))</script>
						<script>tpwidget("init", {
					        "flavor": "slim",
					        "location": "WX4FBXXFKE4F",
					        "geolocation": "enabled",
					        "language": "zh-chs",
					        "unit": "c",
					        "theme": "chameleon",
					        "container": "tp-weather-widget",
					        "bubble": "enabled",
					        "alarmType": "badge",
					        "color": "#F47837",
					        "uid": "UC6AD9E048",
					        "hash": "76465b415261736ddd08da3f7f9b24d0"
					    });
					    tpwidget("show");</script>
  				</li>
  				 <li class="layui-nav-item">
	                <a href="javascript:;" style="cursor:default;">
	                	<i class="layui-icon layui-icon-username headsmIcon"></i>
	                   	<span id="userName"></span>
	                </a>
	            </li>
  				<li class="layui-nav-item">
	                <a href="javascript:;" style="cursor:default;">
	                   	<span id="groupName"></span>
	                </a>
	            </li>
	            <li class="tuichu">
	            	 <a id="loginOut" href="javascript:;" title="退出">
	            	 	<i class="iconfont icon-tuichu"></i>
	            	 </a>
	            </li>
	    	</ul>
  		</div>
  		<div class="layui-side layui-bg-black layui-side-menu">
  			<div class="layui-side-scroll">
  				<!-- 左侧导航区域（可配合layui已有的垂直导航） -->
  				<ul id="leftSideNav" class="layui-nav layui-nav-tree" lay-fliter="leftSideNav">
  					<li class="layui-nav-item navLi layui-this"><a href="javascript:void(0)" path="common.do?action=goZsPage" tab-id="1">作业注水合格率</a></li>
  					<li class="layui-nav-item navLi"><a href="javascript:void(0)" path="common.do?action=goTpPage" tab-id="2">油综调配见效率</a></li>
  					<!-- li class="layui-nav-item navLi"><a href="javascript:void(0)" path="shuizongtiaopei.html" tab-id="3">水综调配方案</a></li>
  					<li class="layui-nav-item navLi"><a href="javascript:void(0)" path="shuizongcengduan.html" tab-id="4">水综层段合格率</a></li-->
  				</ul>
  			</div>
  		</div>
  		<!-- iframe -->
  		<div class="content-body">
  			<!-- 内容主体区域 -->
  			<div class="layui-tab layui-tab-brief" lay-filter="mainTab" lay-allowClose="true">
	            <ul class="layui-tab-title">
	                <li class="layui-this"><i class="layui-icon">&#xe68e;</i>首页</li>
	            </ul>
	            <div class="layui-tab-content">
	                <div class="layui-tab-item layui-show">
	                	<iframe id="mainIframe" src="common.do?action=goZsPage" frameborder="0" scrolling="yes" width="100%" height="100%"></iframe>
	                </div>
	            </div>
	        </div>
  		</div>
  		<!-- footer -->
  		<!--  div class="layui-footer">
	        © 2018 版权所有 Copyright@2018 Sandy.wm All Rights Reserved.
	    </div-->
  	</div>
  	
  	<script src="/plugins/jquery/jquery.min.js"></script>
  	<script src="/plugins/layui/layui.js"></script>
  	<script type="text/javascript">
  		$('#userName').html(userName);
  		$('#groupName').html('科室：' + groupName)
	  	layui.use(['element','layer'], function(){
	 		 //动态加载模块
	       var element = layui.element,
	       	layer = layui.layer,
	       	$ = layui.jquery,
	       	i = 0;
	       $(".navLi").click(function () {
	       	var title = $(this).text();
		        var path = $(this).children('a').attr('path');
		        var tabId = $(this).children('a').attr('tab-id');
		        commonCreateIf(tabId,title,path);
		    });
	       function commonCreateIf(tabId,title,path){
	       	// 去重复选项卡
		        for (var i = 0; i < $('.mainIframe').length; i++) {
		            if ($('.mainIframe').eq(i).attr('tab-id') == tabId) {
		                element.tabChange("mainTab", tabId);
		                event.stopPropagation();
		                return;
		            }
		        }
		        // 添加选项卡
		        element.tabAdd("mainTab", {
		            title: title,
		            content: "<iframe src='" + path + "' tab-id='" + tabId + "' class='mainIframe' frameborder='0' scrolling='yes' width='100%' height='100%'></iframe>",
		            id: tabId
		        });
		        // 切换选项卡
		        element.tabChange("mainTab", tabId);	
	       }
	       $("#animation-left-nav").click(function(){
				//这里定义一个全局变量来方便判断动画收缩的效果,也就是放在最外面
				if(i == 0){
					$(this).removeClass("layui-icon-shrink-right").addClass("layui-icon-spread-left");
					$(".layui-side").stop().animate({width:'toggle'});
					$(".content-body").stop().animate({left:'0px'});			
					//$(".layui-footer").animate({left:'0px'});
					i++;
				}else{
					$(this).removeClass("layui-icon-spread-left").addClass("layui-icon-shrink-right");
					$(".layui-side").stop().animate({width:'toggle'});
					$(".content-body").stop().animate({left:'200px'});
					//$(".layui-footer").animate({left:'200px'});
					i--;
				}		
			});
			//左侧导航栏收缩提示
			$('#animation-left-nav').hover(function(){
				if(i==0){
					layer.tips('收缩左侧导航栏', '#animation-left-nav', {tips:[2,'#FF8000'],time:0});
				}else{
					layer.tips('展开左侧导航栏', '#animation-left-nav', {tips:[2,'#FF8000'],time:0});
				}
				
			},function(){
				layer.closeAll('tips'); 
			});
	   });
  		$("#loginOut").on('click',function(){
			layer.confirm('确认退出系统么？', {
			  title:'提示',
			  skin: 'layui-layer-molv',
			  btn: ['确定','取消'] //按钮
			},function(){
				window.location.href = "login.do?action=loginOut";
			});
		});
  	</script>
  </body>
</html>
