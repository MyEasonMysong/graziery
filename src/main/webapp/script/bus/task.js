"use strict"

/**
 * @Title: taskAdd
 * @author: ZMY
 * @date: 2019年03月27日18:33:44
 * @Description: 专项任务-新增
 * @param: 
 */
function taskAdd() {
	layer.open({
		type : 2,
		title : '新建专项任务',
		skin : 'self-def', /* 自定义按钮样式 */
		anim : '5', /* 动画效果 */
		area : [ '80%', '600px' ],
		content : ctx + '/bus/busTask/form',
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
function selectCheckSystem() {
    layer.open({
        type : 2,
        title : '',
        skin : 'self-def', /* 自定义按钮样式 */
        anim : '5', /* 动画效果 */
        area : [ '80%', '450px' ],
        content : ctx + '/bas/checkSystem/selectCheckSystem?checkTypeCode=itemType02',
        btn : [ '关闭' ],
        btnAlign: 'c',
        btn2 : function(index) {
            layer.close(index);
        }
    });
}

/**
 * @Title: taskAdd
 * @author: ZMY
 * @date: 2019年03月27日19:33:44
 * @Description: 专项任务-修改
 * @param:
 */
function taskEdit(id) {
    layer.open({
        type : 2,
        title : '修改专项任务',
        skin : 'self-def', /* 自定义按钮样式 */
        anim : '5', /* 动画效果 */
        area : [ '80%', '600px' ],
        content : ctx + '/bus/busTask/form?id='+id,
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