<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<!DOCTYPE html> 
<html>
  <head>
    <title>调配见效率</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache"> 
	<meta http-equiv="keywords" content="调配见效率">
	<meta http-equiv="description" content="调配见效率">
	<link href="/plugins/layui/css/layui.css" rel="stylesheet" type="text/css"/>
	<link href="/css/common.css" rel="stylesheet" type="text/css"/>
	<link href="/css/dottingAnimation.css" rel="stylesheet" type="text/css"/>
	<link href="/plugins/pace/pace-theme-flash.min.css" rel="stylesheet" type="text/css"/>
	<link href="/Module/shuizongManager/css/zuoyezhushui.css" rel="stylesheet" type="text/css"/>
	<script src="/plugins/pace/pace.min.js" type="text/javascript"></script>	
	
  </head>
  <body style="background:#f2f2f2;">
  	 <div class="layui-fluid" style="margin-top:0px;">
		<div class="layui-row">
			<div class="layui-col-md12 layui-col-lg12">
				<div class="layui-card layui-tab layui-tab-brief" lay-filter="jiaoxiaolvTab" style="margin:0;">
					<ul class="layui-tab-title">
						<li class="layui-this" isClick="false">油井信息</li>
						<li id="zsPartNav" isClick="false">见效率分析</li>
					</ul>
					<!-- 油井信息查询条件 -->
					<div class="layui-form searchForm infoForm layui-clear">
						<div class="itemDivs" style="width:150px;">
							<div class="layui-input-inline">
								<span class="symbol">井</span>
								<input type="text" style="width:78%;" placeholder="请输入注水井号" maxlength="20" class="layui-input inpJhNum"/> 
							</div>
						</div>
						<div class="itemDivs dateBox">
							<div class="layui-input-inline" style="width:120px;">
								<input type="text" id="stDate" class="layui-input" readonly placeholder="请选择起始时间" />
							</div>
							<span>至</span>
							<div class="layui-input-inline" style="width:120px;">
								<input type="text" id="edDate" class="layui-input" readonly placeholder="请选择结束时间" />
							</div>
						</div>
						<div class="itemDivs">
							<div class="layui-input-inline">
								<button id="queryBtn_list" class="layui-btn"><i class="layui-icon layui-icon-search"></i></button>
							</div>
						</div>
						<a class="resetBtn resetList" href="javascript:void(0)">重置<i class="layui-icon layui-icon-refresh"></i></a>
					</div>
					<!-- 见效率查询条件 -->
					<!-- div class="layui-form searchForm hglForm layui-clear">
						<div class="itemDivs">
							<div class="layui-input-inline" style="width:75px;">
								<input type="text" id="yearInp" placeholder="选择年份" readonly class="layui-input"/> 
								<strong class="yearTxt">年</strong>
							</div>
						</div>
						<div class="itemDivs">
							<div class="layui-input-inline">
								<button id="queryBtn_hgl" class="layui-btn"><i class="layui-icon layui-icon-search"></i></button>
							</div>
						</div>
						<div class="addFileBox">
							<a id="selFileBtn" opts="addHgl" href="javascript:void(0)" class="layui-btn layui-btn-normal"><i class="layui-icon layui-icon-add-1"></i>添加文件</a>
						</div>
					</div -->
					<!-- 查询条件 -->
					<div class="layui-form searchForm jxlForm layui-clear">
						<div class="addFileBox">
							<a id="selFileBtn_jxl" opts="addJxl" href="javascript:void(0)" class="layui-btn layui-btn-normal"><i class="layui-icon layui-icon-add-1"></i>添加文件</a>
						</div>
					</div>
					
  					<div class="layui-card-body layui-tab-content">
						<!-- 油井信息 -->
  						<div class="layui-tab-item layui-show">
							<div id="youjingInfoList">
								<p class="tipsTxt">请根据条件查看具体井号的油井信息！</p>
								<div class="listYoujing">
									<table id="listTable" class="layui-table" lay-filter="listTable"></table>
								</div>
								<!-- p id="totalAnay"></p -->
							</div>
						</div>
						<!-- 注水合格率分析 -->
						<!-- div id="zsPart" class="layui-tab-item">
							<div id="hglList">
								<table id="hglTable" class="layui-table" lay-filter="hglTable"></table>
							</div>
						</div -->
						<!-- 调配见效率分析 -->
						<div id="cdPart" class="layui-tab-item">
							<div id="jxlList">
								<table id="jxlTable" class="layui-table" lay-filter="jxlTable"></table>
							</div>
						</div>
  					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="indexLayer">
		<div class="loadingWrap">
			<p class="upTipsTxt">上传成功，<span id="countNum_up"></span>秒后开始自动读取</p>
			<div class="spinnerBox">
				<div class="spinner"></div>
				<p>正在读取中<span class="dotting"></span></p><p>请勿刷新页面</p>
			</div>
			<div class="succBox">
				<i class="layui-icon layui-icon-ok-circle readSucc"></i>
				<p class="succTxt">读取成功!</p>
				<p class="downTxt layui-clear">
					<a class="closeBtn" opts="" href="javascript:void(0)">关闭</a>
					<a class="downBtn" opts="" filePath="" href="javascript:void(0)">下载</a>
				</p>
			</div>
			
		</div>
	</div>
	<script src="/plugins/jquery/jquery.min.js"></script>
    <script src="/plugins/layui/layui.js"></script>
    <script type="text/javascript">
    	/*var moduleId = '${ sessionScope.moduleId }';
    	if(moduleId.indexOf('zs') >= 0){
    		$('#zsPartNav').show();
    	}
    	if(moduleId.indexOf('cd') >= 0){
    		$('#cdPartNav').show();
    	}*/
	    layui.config({
			base: '/plugins/frame/js/'
		}).use(['element','laydate','table','layer','upLoadFiles','common'],function(){
			var element = layui.element,layer = layui.layer,laydate=layui.laydate,
				table = layui.table,globalUpload = layui.upLoadFiles,common = layui.common;
			
			laydate.render({elem:'#stDate'});
			laydate.render({elem:'#edDate'});
			laydate.render({elem: '#yearInp',type: 'year'});
			
			//tab点击事件的监听 点拨指导 重点 难点 关键点 易混点
			element.on('tab(jiaoxiaolvTab)', function(data){
				var isClick = $(this).attr('isClick');
				if(data.index == 0){
					$('.infoForm').show();
					$('.jxlForm').hide();
				}else if(data.index == 1){//调配见效率
					$('.infoForm').hide();
					$('.jxlForm').show();
					page.loadJxlResList();
					if(isClick == 'false'){
						page.upLoadJxlFile();
					}
					$(this).attr('isClick','true');
					$('.closeBtn').attr('opts','closeJxl');
					$('.downBtn').attr('opts','downJxl');
				}
			});
			
			var page = {
				init : function(){
					var currYear = new Date().getFullYear(); //获取当前年份
				    $('#yearInp').val(currYear);
					this.bindEvent();
				},
				upLoadJxlFile : function(){
					var url = '/common.do?action=uploadFile&opt=tp';
					globalUpload.uploadFiles(url,1,'impJxlFile','selFileBtn_jxl');
				},
				bindEvent : function(){
					var _this = this;
					$('.closeBtn').on('click',function(){
						var opts = $(this).attr('opts');
						if(opts == 'closeJxl'){
							_this.loadJxlResList();
						}
						_this.commonHide();
					});
					$('.downBtn').on('click',function(){
						var filePath = $(this).attr('filePath'),
							opts = $(this).attr('opts');
						if(opts == 'downJxl'){
							_this.loadJxlResList();
						}
						common.downFiles(filePath,0);
						_this.commonHide();
					});
					//油井信息查询
					$('#queryBtn_list').on('click',function(){
						var jhVal = $('.inpJhNum').val(),
							sDateVal = $('#stDate').val(),
							edDateVal = $('#edDate').val();
						if(jhVal == ''){
							layer.msg('请输入井号', {icon:5,anim:6,time:2000});
							return;
						}
						if(sDateVal == ''){
							layer.msg('请选择起始时间', {icon:5,anim:6,time:2000});
							return;
						}else if(edDateVal == ''){
							layer.msg('请选择结束时间', {icon:5,anim:6,time:2000});
							return;
						}else if(sDateVal != '' && edDateVal == ''){
							layer.msg('请选择结束时间', {icon:5,anim:6,time:2000});
							return;
						}else if(sDateVal == '' && edDateVal != ''){
							layer.msg('请选择起始时间', {icon:5,anim:6,time:2000});
							return;
						}else if(sDateVal > edDateVal){
							layer.msg('起始时间不能大于结束时间', {icon:5,anim:6,time:2000});
							return;
						}
						$('.tipsTxt').hide();
						$('.listYoujing').show();
						_this.loadyoujingInfoList();
					});
					//重置注水信息查询条件
					$('.resetList').on('click',function(){
						$('.inpJhNum').val('');
						$('#stDate').val('');
						$('#edDate').val('');
						$('.tipsTxt').show();
						$('.listYoujing').hide();
						//$('#totalAnay').html('');
					});
				},
				commonHide : function(){
					$('.indexLayer').hide();
					$('.loadingWrap').hide();
					$('.upTipsTxt').show();
					$('.succBox').hide();
				},
				//调配见效率分析结果list
				loadJxlResList : function(){
					layer.load('1');
					table.render({
						elem: '#jxlTable',
						height: 'full-100',
						url :'/common.do?action=getTpjxlData',
						method : 'post',
						page : true,
						even : true,
						limit : 40,
						limits:[10,20,30,40,50],
						cellMinWidth : 150,
						text: {none : '暂无相关数据'},
						cols : [[
							{field : '', title: '序号', type:'numbers',fixed: 'left' , align:'center'},
							{field : 'fileName', title: '文件名', align:'center' },
							{field : 'date', title: '日期',align:'center'},
							{field : 'fxDate', title: '分析日期',align:'center'},
							{field : '', title: '操作',align:'center',templet:function(d){
								var str = '';
								str += '<a href="javascript:void(0)" class="layui-btn layui-btn-xs" lay-event="download" filePath="'+ d.filePath +'"><i class="layui-icon layui-icon-download-circle"></i>下载</a>';
								str += '<a href="javascript:void(0)" class="layui-btn layui-btn-xs layui-btn-danger" lay-event="delete" fileName="'+ d.fileName +'"><i class="layui-icon layui-icon-delete"></i>删除</a>';
								return str;
							}}
							
						]],
						done : function(res, curr, count){
							layer.closeAll('loading');
						}
					});
					table.on('tool(jxlTable)',function(obj){
						if(obj.event == 'download'){//下载调配见效率文件
							var filePath = $(this).attr('filePath');
							common.downFiles(filePath,0);
						}else if(obj.event == 'delete'){
							var fileName = $(this).attr('fileName');
							layer.confirm('确定要删除文件[<span style="color:#F47837">' + fileName + '</span>]?',{
								title:'删除文件?',
							  	skin: 'layui-layer-molv',
							  	btn: ['确定','取消'] //按钮
							},function(){//注水合格率删除
								page.delFileFun(fileName,'common.do?action=delTpjxlData','hgl');
							});
						}
					});
				},
				delFileFun : function(fileName,url,opt){
					layer.load('1');
					var _this = this;
					$.ajax({
    					type:'post',
    			        dataType:'json',
    			        data : {fileName : fileName},
    			        url:url,
    			        success:function (json){
    			        	layer.closeAll('loading');	
    			        	if(json['result'] == 'success'){
    			        		layer.msg('删除成功',{icon:1,time:1500},function(){
    			        			if(opt == 'jxl'){
    			        				_this.loadJxlResList();
    			        			}
    			        		});
    			        	}
    			        }
    				});
				},
				//加载油井list
				loadyoujingInfoList : function(){
					layer.load('1');
					var jhVal = $('.inpJhNum').val(),
						sDateVal = $('#stDate').val(),
						edDateVal = $('#edDate').val();
					var field = {jh:jhVal,sDate:sDateVal,eDate:edDateVal};
		    		table.render({
						elem: '#listTable',
						height: 'full-120',
						url :'/common.do?action=getPageyjJxlData',
						method : 'post',
						where:field,
						page : true,
						even : true,
						limit : 40,
						limits:[10,20,30,40,50],
						cellMinWidth : 80,
						text: {none : '暂无相关数据'},
						cols : [[
							{field : '', title: '序号', type:'numbers', align:'center'},
							{field : 'jh', title: '井号', align:'center' },
							{field : 'rq', title: '日期',align:'center'},
							{field : 'db', title: '方',align:'center'},
							{field : 'yz', title: '嘴',align:'center'},
							{field : 'pl', title: '排量',align:'center'},
							{field : 'bj', title: '工',align:'center'},
							{field : 'cc', title: '作制',align:'center'},
							{field : 'cc1', title: '度',align:'center'},
							{field : 'scsj', title: '时间',align:'center'},
							{field : 'rcyl1', title: '产液',align:'center'},
							{field : 'rcyl', title: '产油',align:'center'},
							{field : 'hs', title: '含水',align:'center'},
							{field : 'rcql', title: '日产气',align:'center'},
							{field : 'bx', title: '泵效',align:'center'},
							{field : 'qyb', title: '气油比',align:'center'},
							{field : 'bs', title: '泵深',align:'center'},
							{field : 'bz', title: '备注',align:'center'}
							
						]],
						done : function(res, curr, count){
							layer.closeAll('loading');
							if(res.msg == 'success'){
								//$('#totalAnay').html('井号<strong class="jinghao">' + res.jh_tj + '</strong>合格天数：<strong class="jinghao">' + res.hgDays + '天 </strong>合格率：<strong class="jinghao">' + res.hgRate + '</strong>注水天数：<strong class="jinghao">' + res.zsDays + '天</strong>结论：<strong class="jinghao">' + res.result + '</strong>');
							}
						}
					});
				}
			};
			page.init();
		});
    </script>
  </body>
</html>
