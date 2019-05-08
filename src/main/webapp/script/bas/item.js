"use strict"
/**
 * @Title: itemAdd
 * @author: lht
 * @date: 2019年1月24日9:33:44
 * @Description: 指标项管理-新增（修改）
 * @param: 
 */
function itemForm(id) {
	
	var a;
	
	if(null != id && "" != id){
		a = '修改指标项';
	} else {
		a = '新增指标项';
	}
	
	layer.open({
		type : 2,
		title : a,
		skin : 'self-def', /* 自定义按钮样式 */
		anim : '5', /* 动画效果 */
		area : [ '750px', '500px' ],
		content : ctx + '/bas/item/form?id='+id,
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
 * @Title: itemView
 * @author: lht
 * @date: 2019年1月24日9:33:44
 * @Description: 指标项管理-查看
 * @param: 
 */
function itemView(id) {
	layer.open({
		type : 2,
		title : '指标详情',
		skin : 'self-def', /* 自定义按钮样式 */
		anim : '5', /* 动画效果 */
		area : [ '750px', '500px' ],
		content : ctx + '/bas/item/view?id=' + id,
		btn : [ '关闭' ],
		yes : function(index) {
			layer.close(index);
		}
	});
}

/**
 * @Title: itemRestForm
 * @author: lht
 * @date: 2019年1月24日9:33:44
 * @Description: 指标项管理-重置
 * @param: 
 */
function itemRestForm(formID , btnID){
	var myForm = "#" + formID;
	var text = $(myForm + " input[type='text']");
	$.each(text, function(i, n) {
		n.value = "";
	});
	var select = $(myForm + " select");
	$.each(select, function(i, n) {
		if(n.options.length >0){
			$(n).val(n.options[0].value);			
		}
	});
	
	$("#" +  btnID).click();
}
