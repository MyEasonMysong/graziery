"use strict"

function programmeAdd() {
    layer.open({
        type : 2,
        title : '新建现场检查方案',
        skin : 'self-def', /* 自定义按钮样式 */
        anim : '5', /* 动画效果 */
        area : [ '80%', '600px' ],
        content : ctx + '/bus/busProgramme/form',
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