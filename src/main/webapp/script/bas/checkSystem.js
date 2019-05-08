"use strict"
/**
 * @Title: checkSystemAdd
 * @author: shirui
 * @date: 2019年1月29日9:33:44
 * @Description: 日常检查体系管理-新增
 * @param: 
 */
function checkSystemAdd() {
    var checkTypeCode = $("#checkTypeCode").val();
	layer.open({
		type : 2,
		title : '新增',
		skin : 'self-def', /* 自定义按钮样式 */
		anim : '5', /* 动画效果 */
		area : [ '1300px', '700px' ],
		content : ctx + '/bas/checkSystem/form/?checkTypeCode='+checkTypeCode,
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
 * @Title: checkSystemEdit
 * @author: shirui
 * @date: 2019年1月23日9:33:44
 * @Description: 日常检查体系管理-修改
 * @param: id
 */
function checkSystemEdit(id) {
    var checkTypeCode = $("#checkTypeCode").val();
	layer.open({
		type : 2,
		title : '修改',
		skin : 'self-def', /* 自定义按钮样式 */
		anim : '5', /* 动画效果 */
		area : [ '1300px', '700px' ],
		content : ctx + '/bas/checkSystem/form?id='+id+'&checkTypeCode='+checkTypeCode,
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
 * @Title: checkSystemView
 * @author: shirui
 * @date: 2019年1月23日9:33:41
 * @Description: 日常检查体系管理-查看
 * @param: id
 */
function checkSystemView(id) {
	layer.open({
		type : 2,
		title : '查看',
		skin : 'self-def', /* 自定义按钮样式 */
		anim : '5', /* 动画效果 */
		area : [ '1300px', '700px' ],
		content : ctx + '/bas/checkSystem/view?id=' + id,
		btn : [ '关闭' ],
		yes : function(index) {
			layer.close(index);
		}
	});
}
