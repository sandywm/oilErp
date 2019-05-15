/**
 * @Description: 上传通用
 * @author: hlf
 */
var succFileName = '';//判断当前上传的文件类型是否是正确的
layui.define(['element','jquery','upload','form'],function(exports){
	var element = layui.element,
	upload = layui.upload,form = layui.form;
	var obj = {
		data : {
			globalOpts : '',
    		indexLayer : '<div class="indexLayer"><div class="loadingWrap"></div></div>',
    		upSuccTips : '<p class="upTipsTxt">上传成功，<span id="countNum_up"></span>秒后开始自动读取</p>',
    		readingTips : '<div class="spinner"></div><p>正在读取中<span class="dotting"></span></p><p>请勿刷新页面</p>',
    		readSuccTips : '<i class="layui-icon layui-icon-ok-circle readSucc"></i><p class="succTxt">读取成功!</p><p class="downTxt layui-clear"><a class="closeBtn" href="javascript:void(0)" onclick="closeLayer()">关闭</a><a class="downBtn" href="javascript:void(0)">下载</a></p>'
		},
		uploadFiles : function(url,maxNumber,opts){
			this.data.globalOpts = opts;
			var _this = this
			 //,alreadyUploadFiles={}//记录已经上传成功的文件相对路径（后台返回）
			,uploadListIns=upload.render({
				  elem : '#selFileBtn'
				  ,url: url//这里设置自己的上传接口
				  ,accept: 'file'
				  ,exts : 'xlsx'
				  ,multiple: false
				  ,auto: true
				  ,number:1
				  ,errorMsg: function(content){
				  	layer.msg(content, {icon: 2, shift: 6});
				  }
				 ,before : function(obj){
					  var that = this;
					  //假如用户分了两次或者多次上传若个干文件，那么，用已经上传的文件和当前已经选中的文件的数量相加和maxNum做比较
					  var hasUpDoneLen = $('.hasUpDone').length;
					  var noUpDoneLen = $('.noUpDone').length;
					  if((hasUpDoneLen + noUpDoneLen) > maxNumber){
						  that.errorMsg('最多只能上传'+ maxNumber +'个文件');
						  return false;
					  }
				  }
				  ,choose: function(obj){
				  	  var that = this;
				      obj.preview(function(index, file, result){ 
				      	//var files = that.files = obj.pushFile(); //将每次选择的文件追加到文件队列
				      	var fileNames = file.name.split('.')[0]; 
						var fileType = file.name.substr(file.name.lastIndexOf('.'));
						var size = file.size;
				        if(fileNames.length > 50){
							layer.msg('上传的文件名不能超过50个字符',{icon:5,anim:6,time:1000});
		    				return;
						}
						if(fileType == '.jpg' || fileType == '.png' || fileType == '.bmp' || fileType == '.gif' || fileType == '.jpeg'){
			    			//如果是图片，单个图片不能大于5M
			    			if(size > (5 * 1024 * 1024)){
			    				layer.msg('上传的图片文件不能大于5M',{icon:5,anim:6,time:1000});
			    				return;
			    			}
			    		}else{
			    			//如果是其他文件类类型，单个文件不能大于10M
			    			if(opts == 'impHglFile'){//注水合格率分析
			    				if(size > (20 * 1024 * 1024)){
				    				layer.msg('上传的文件不能大于20M',{icon:5,anim:6,time:2000});
				    				return;
				    			}
			    			}else{
			    				if(size > (10 * 1024 * 1024)){
				    				layer.msg('上传的单个文件不能大于10M',{icon:5,anim:6,time:2000});
				    				return;
				    			}
			    			}
			    		}
				  	});
				     layer.load();
				  }
				  ,done: function(res, index, upload){
					layer.closeAll('loading');
				    if(res.msg == 'success'){ //上传成功
				    	if(opts == 'impHglFile'){
				    		//执行读取通知书 调出遮罩层	
				    		succFileName = res.fileName;
				    		$('.indexLayer').show();
				    		$('.loadingWrap').show();
				    		//$('body').append(_this.data.indexLayer);
							//$('body').find('.loadingWrap').html(_this.data.upSuccTips);
							_this.showTime(3,'countNum_up',opts,false);
				    	}
				    }else if(res.msg == 'suffixError'){
						layer.msg('上传文件格式必须是xlsx',{icon:5,anim:6,time:2000});
					}else if(res.msg == 'fileNameError'){
						layer.msg('文件名称格式错误',{icon:5,anim:6,time:2000});
					}else if(res.msg == 'outSize'){
						layer.msg('单个文件大小不能超过20M',{icon:5,anim:6,time:2000});
					}
				  }
			});
		},
		//倒计时
		showTime : function(count,obj,opts,isReadSucFlag){
			var _this = this,tmpUrl = '';
			$('#'+obj).html(count);
			if(count == 0){
				if(isReadSucFlag){//批量读取成功
					//parent.$('body').find('.indexLayer').remove();
				}else{//上传成功，倒计时开始批量读取
					//parent.$('body').find('.loadingWrap').html(_this.data.readingTips);
					$('.upTipsTxt').hide();
					$('.spinnerBox').show();
					if(opts == 'impHglFile'){//读取通知书
						tmpUrl = '/common.do?action=dealZsExcel';
					}
					_this.readImportFile(tmpUrl,succFileName);
				}
			}else{
				count -= 1;
				setTimeout(function () {
					_this.showTime(count,obj,opts,isReadSucFlag);
				}, 1000);
			}
		},
		//批量读取通知书/费用单据
		readImportFile : function(tmpUrl,fileName){
			var _this = this;
			$.ajax({
				type:"post",
				dataType:"json",
				async:true,
				data:{excelName:fileName},
				url:tmpUrl,
				success:function(json){
					if(json['result'] == 'success'){
						$('.downBtn').attr('filePath',fileName);
						$('.upTipsTxt').hide();
						$('.spinnerBox').hide();
						$('.succBox').show();
						//parent.$('body').find('.loadingWrap').html(_this.data.readSuccTips);
						//_this.showTime(3,parent.$('body').find('#countNum'),_this.data.globalOpts,true);
					}else if(json['result'] == 'contentError'){
						layer.msg('上传文件内容格式错误',{icon:5,anim:6,time:2000});
					}else if(json['result'] == 'sheetError'){
						layer.msg('上传文件中必须包含2个工作表',{icon:5,anim:6,time:2000});
					}
				}
			});
		}
	};
	//输出接口
    exports('upLoadFiles', obj);
});