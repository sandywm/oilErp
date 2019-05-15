/**
 * @Description: 基础配置
 * @author: hlf
 */
var noGradInfo = '';//根据科目联动年级时用到 存在年级 清空 不存在有值 noGradInfo
//自定义模块
layui.define(['form'],function(exports){
	var $ = layui.jquery,form=layui.form;
    var obj = {
		//通用下载附件方法
		downFiles : function(downFilePath,downFileType){
			layer.load('1');
			var url = '';
			if(downFileType == 0){//下载通用附件
				url = '/common.do?action=downZipFile';
			}
			var form = $("<form>");   //定义一个form表单
			form.attr('style', 'display:none'); //在form表单中添加查询参数
			form.attr('target', '');
			form.attr('method', 'post');
			form.attr('action', url);
			var input1 = $('<input>');
			input1.attr('type', 'hidden');
			input1.attr('name', 'fileUrl');
			input1.attr('value', escape(downFilePath));
			$('body').append(form);  //将表单放置在web中 
			form.append(input1);   //将查询参数控件提交到表单上
		  	form.submit();
		  	layer.closeAll('loading');
		}
    };
    //输出接口
    exports('common', obj);
});
