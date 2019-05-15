<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<!DOCTYPE html> 
<html>
  <head>
    <title>油田数据分析系统--用户登录</title>
	<link href="/plugins/layui/css/layui.css" rel="stylesheet" type="text/css"/>
	<link href="/plugins/layui/css/modules/layui-icon-extend/iconfont.css" rel="stylesheet" type="text/css"/>
	<link href="/plugins/pace/pace-theme-flash.min.css" rel="stylesheet" type="text/css"/>
	<link href="/css/login.css" rel="stylesheet" type="text/css"/>
	<script src="/plugins/pace/pace.min.js"></script>
  </head>
  
  <body style="background:#f1f1f1;">   
  	<div class="layadmin-user-login layadmin-user-display-show">
	    <div class="layadmin-user-login-main">
	      <div class="layadmin-user-login-box layadmin-user-login-header">
	        <h2>油田数据分析系统</h2>  
	        <p>Oilfield Data Analysis system</p>
	      </div>
	      <div class="layadmin-user-login-box layadmin-user-login-body layui-form">
	        <div class="layui-form-item">
	          <label class="layadmin-user-login-icon iconfont icon-wode"></label>
	          <input type="text" id="account" placeholder="请输入账号" maxlength="12" autocomplete="off" class="layui-input">
	        </div>
	        <div class="layui-form-item">
	          <label class="layadmin-user-login-icon iconfont icon-mima" for="LAY-user-login-password"></label>
	          <input type="password" id="password" placeholder="请输入密码" maxlength="16" autocomplete="off" class="layui-input">
	        </div>
	        <div class="layui-form-item">
	          <button id="button" class="layui-btn layui-btn-fluid">登录</button>
	        </div>
	      </div>
	    </div>
	    <input id="roleIdInp" type="hidden"/>
	    <div class="layui-trans layadmin-user-login-footer">
	      <p>© 2019  版权所有 Copyright@2019 Sandy.wm All Rights Reserved.</p>
	      <p>为了能有更好的体验推荐使用360浏览器或谷歌chrome浏览器</p>
	    </div>
  	</div>
	<script src="/plugins/layui/layui.js"></script>
	<script type="text/javascript">
		layui.use(['layer','jquery'],function(){
			var layer = layui.layer,
				$ = layui.jquery,
				form = layui.form,
				common = layui.common;
			$("#button").on('click',function(){
				login();
			});
			$(function(){
				$("#account").on("keypress",function(){
					enterPress(event);
				});
				$("#password").on("keypress",function(){
					enterPress(event);
				});
			});
			function login(){
				var account = $.trim($("#account").val());
				var password = $.trim($("#password").val());
				 if(account == ""){
					layer.msg("账号不能为空", {icon:5,anim:6,time:1000});
					$("#account").focus().addClass("layui-form-danger");
				}else if(password == ""){
					layer.msg("密码不能为空", {icon:5,anim:6,time:1000});
					$("#password").focus().addClass("layui-form-danger");
				}else{
					layer.load();
					$.ajax({
				        type:"post",
				        async:false,
				        dataType:"json",
				        url:"/login.do?action=login",
				        data:{account:account,password:password},
				        success:function (json){
				        	layer.closeAll('loading');
				        	if(json['result'] == 'succ'){
				        		window.location.href = '/common.do?action=welcome';
				        	}else if(json['result'] == 'fail'){
				        		layer.msg("账号密码错误", {icon:5,anim:6,time:2000});
				        	}
				        }
				    });
				}
			}
			//回车事件
			function enterPress(e){
				var e = e || window.event;
				if(e.keyCode == 13){
					login();
				}
			}
		});

		
	</script>
  </body>
  
</html>
