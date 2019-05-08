"use strict"
/**
 * @Title: entAdd
 * @author: lxy
 * @date: 2019年1月23日9:33:44
 * @Description: 企业安全档案-新增
 * @param: 
 */
function entAdd() {
	layer.open({
		type : 2,
		title : '新增',
		skin : 'self-def', /* 自定义按钮样式 */
		anim : '5', /* 动画效果 */
		area : [ '750px', '500px' ],
		content : ctx + '/bas/ent/form',
		btn : [ '保存', '取消' ],
		yes : function(index, layero) {
			var iframeWin = window[layero.find('iframe')[0]['name']];
			iframeWin.$("#inputForm").submit();
			return false; 
		},
		btn2 : function(index) {
			layer.close(index);
		}
	});
}
/**
 * @Title: entEdit
 * @author: lxy
 * @date: 2019年1月23日9:33:44
 * @Description: 企业安全档案-修改
 * @param: 
 */
function entEdit(id) {
	layer.open({
		type : 2,
		title : '修改',
		skin : 'self-def', /* 自定义按钮样式 */
		anim : '5', /* 动画效果 */
		area : [ '750px', '500px' ],
		content : ctx + '/bas/ent/form?id='+id,
		btn : [ '保存', '取消' ],
		yes : function(index, layero) {
			var iframeWin = window[layero.find('iframe')[0]['name']];
			iframeWin.$("#inputForm").submit();
			return false;
		},
		btn2 : function(index) {
			layer.close(index);
		}
	});
}

/**
 * @Title: entView
 * @author: lxy
 * @date: 2019年1月23日9:33:41
 * @Description: 企业安全档案-查看
 * @param: id
 */
function entView(id) {
	layer.open({
		type : 2,
		title : '查看',
		skin : 'self-def', /* 自定义按钮样式 */
		anim : '5', /* 动画效果 */
		area : [ '750px', '500px' ],
		content : ctx + '/bas/ent/view?id=' + id,
		btn : [ '关闭' ],
		yes : function(index) {
			layer.close(index);
		}
	});
}

/**
 * @Title: entQrView
 * @author: lxy
 * @date: 2019年1月23日9:33:41
 * @Description: 企业安全档案-查看
 * @param: id
 */
function entQrView(id) {
	layer.open({
		type : 2,
		title : '二维码查看',
		skin : 'self-def', /* 自定义按钮样式 */
		anim : '5', /* 动画效果 */
		area : [ '450px', '450px' ],
		content : ctx + '/bas/ent/qrView?id=' + id,
		btn : [ '下载','关闭' ],
		yes : function(index) {
			self.location.href=ctx+"/bas/ent/downQrCode?id="+id;
			return false;
		},
		/*btn2 : function(index) {
			//layer.close(index);
			console.log("打印");
		},*/
		btn2 : function(index) {
			layer.close(index);
			//console.log("关闭");
		},
	});
}

function batchDownQrCode(){
	var vals = [];
	$("input[name='id']:checkbox:checked").each(function (index, item) {
        vals.push($(this).val());
    });
    var ids=vals.join(",");
    if(ids==""){
    	layer.alert('请选择企业', {icon: 7});
    	return false;
    }
    self.location.href=ctx+"/bas/ent/batchDownQrCode?ids="+ids;
    
}