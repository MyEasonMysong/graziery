"use strict"

/**
 * @Title: planAdd
 * @author: ZMY
 * @date: 2019年03月27日10:33:44
 * @Description: 年度检查计划-新增
 * @param: 
 */
function planAdd() {
	layer.open({
		type : 2,
		title : '新建检查计划',
		skin : 'self-def', /* 自定义按钮样式 */
		anim : '5', /* 动画效果 */
		area : [ '80%', '600px' ],
		content : ctx + '/bus/busPlan/form',
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
 * @Title: selectEnts
 * @author: ZMY
 * @date: 2019年03月27日13:33:44
 * @Description: 年度检查计划-新增-选择检查对象
 * @param:
 */
function selectEnts() {
    layer.open({
        type : 2,
        title : '',
        skin : 'self-def', /* 自定义按钮样式 */
        anim : '5', /* 动画效果 */
        area : [ '80%', '450px' ],
        content : ctx + '/bas/ent/selectForPlan',
        btnAlign: 'c',
    });
}
