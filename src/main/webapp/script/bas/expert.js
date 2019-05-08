"use strict"
/**
 * @Title: expertAdd
 * @author: lxy
 * @date: 2019年1月23日9:33:44
 * @Description: 专家库-新增
 * @param: 
 */
function expertAdd() {
	layer.open({
		type : 2,
		title : '新增',
		skin : 'self-def', /* 自定义按钮样式 */
		anim : '5', /* 动画效果 */
		area : [ '750px', '400px' ],
		content : ctx + '/bas/expert/form',
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
 * @Title: expertEdit
 * @author: lxy
 * @date: 2019年1月23日9:33:44
 * @Description: 专家库-修改
 * @param: 
 */
function expertEdit(id) {
	layer.open({
		type : 2,
		title : '修改',
		skin : 'self-def', /* 自定义按钮样式 */
		anim : '5', /* 动画效果 */
		area : [ '750px', '400px' ],
		content : ctx + '/bas/expert/form?id='+id,
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
 * @Title: expertDelete
 * @author: lxy
 * @date: 2019年1月23日9:33:41
 * @Description: 专家库-删除
 * @param: id
 */
function expertDelete(id) {
	layer.open({
		type : 2,
		title : '查看',
		skin : 'self-def', /* 自定义按钮样式 */
		anim : '5', /* 动画效果 */
		area : [ '750px', '400px' ],
		content : ctx + '/bas/expert/view?id=' + id,
		btn : [ '关闭' ],
		yes : function(index) {
			layer.close(index);
		}
	});
	layer.confirm('is not?', {icon: 3, title:'提示'}, function(index){
		  //do something
		  
		  layer.close(index);
	});
}

/**
 * @Title: expertView
 * @author: lxy
 * @date: 2019年1月23日9:33:41
 * @Description: 专家库-查看
 * @param: id
 */
function expertView(id) {
	layer.open({
		type : 2,
		title : '查看',
		skin : 'self-def', /* 自定义按钮样式 */
		anim : '5', /* 动画效果 */
		area : [ '750px', '400px' ],
		content : ctx + '/bas/expert/view?id=' + id,
		btn : [ '关闭' ],
		yes : function(index) {
			layer.close(index);
		}
	});
}
