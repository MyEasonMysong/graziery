/**
 * @Title: webUploaderSupportCheck
 * @author wzl
 * @date 2017年11月30日
 * @Description: 显示图片
 */
function webUploaderSupportCheck() {
	if (!WebUploader.Uploader.support()) {
		var error = "上传控件不支持您的浏览器！请尝试升级flash版本或者使用Chrome引擎的浏览器。<a target='_blank' href='http://se.360.cn'>下载页面</a>";
		if (window.console) {
			window.console.log(error);
		}
		alert(error);
		return;
	}
	return true;
}

/**
 * @Title: photoSwfUpLoad
 * @author wzl
 * @date 2017年11月30日
 * @Description: 显示图片
 * @param: basePath
 * @param: animalId
 */
function photoSwfUpLoad(basePath, animalId) {
	if (!webUploaderSupportCheck()) {
		return;
	}
	var $ = jQuery, state = 'pending', uploader;
	uploader = WebUploader
			.create({
				resize : false,
				swf : basePath + 'static/webuploader/Uploader.swf',
				server : 'ajaxUpload!uploadPhoto.action',
				pick: {
					"id":'#upload' + animalId,   
					"multiple":false   //禁止多选。     
				},  
				auto : true,
				accept : {
					title : 'Images',
					extensions : 'jpg,png,jpeg',
					mimeTypes : 'image/jpeg,image/png'
				},
				formData : {
					'a' : Math.random() * 10000,
					'annex.bid' : animalId,
					'annex.tblName' : "d_animal",
					'annex.fileType' : "photo"
				},
				duplicate: true,//允许上传重复文件
				fileVal : 'myAnnex'
			});
	/** 文件上传成功 */
	uploader.on('uploadSuccess', function(file, response) {
		var oldAnnexId = $("#oldUploadId").val();
		if (oldAnnexId) {
			deleteOldAnnex(oldAnnexId);
		}
		var annexId = response.annex.id;
		if (annexId) {
			if (annexId) {
				$("#oldUploadId").val(annexId);
				$("#newAnnexId").val(annexId);
				var $a = $("<a>" + file.name + "</a>");
				$a.attr("href", "javascript:searchListOpen('" + file.name + "','" + annexId + "');");
				var $img = $("<img style='vertical-align:middle;' class='hand' src='" + basePath + "static/libs/icons/delete.gif'></img>");
				$img.attr("onclick", "deleteAnnex('" + annexId + "');");
				$("#td" + animalId).empty();
				var $span = $("<span id='" + annexId + "'></span>");
				$span.append($a);
				$span.append("&nbsp;&nbsp;");
				$span.append($img);
				$("#td" + animalId).append($span);
			}
		}
	});
}
/**
 * 
 * @Title: deleteOldAnnex
 * @author wzl
 * @date 2017年11月30日
 * @Description: 显示图片
 * @param: id
 */
function deleteOldAnnex(id) {
	$.ajax({
		url : "ajaxDownload!deleteById.action",
		type : "post",
		data : "annex.id=" + id,
		success : function(data) {
			$("#" + id).remove();
		},
		error : function(req) {
			top.Dialog.alert(req.responseText);
		}
	});
}

/**
 * 
 * @Title: reloadAnnex
 * @author wzl
 * @date 2017年12月5日
 * @Description: 加载附件
 * @param: basePath
 * @param: id
 * @param: fileId
 * @param: fileName
 */
function reloadViewAnnex(basePath, id, fileId, fileName) {
	if(fileId){
		var $a = $("<a>" + fileName + "</a>");
		$a.attr("href", "javascript:searchListOpen('" + fileName + "','" + fileId + "');");
		var $span = $("<span id='td" + fileId + "'></span>");
		$span.append($a);
		$span.append("&nbsp;&nbsp;");
		$("#td" + id).append($span);
	}
}

/**
 * 
 * @Title: ImgUpLoad
 * @author wzl
 * @date 2017年12月5日
 * @Description: 加载附件
 * @param: basePath
 * @param: animalHandleId
 */
function ImgUpLoad(basePath, animalHandleId) {
	if (!webUploaderSupportCheck()) {
		return;
	}
	var $ = jQuery, state = 'pending', uploader;
	debugger
	uploader = WebUploader
			.create({
				resize : false,
				swf : basePath + 'static/webuploader/Uploader.swf',
				server : 'ajaxUpload!uploadImg.action',
				pick: {
					"id":'#upload'
				},  
				auto : true,
				accept : {
					title : 'Images',
					extensions : 'jpg,png,jpeg',
					mimeTypes : 'image/jpeg,image/png'
				},
				formData : {
					'a' : Math.random() * 10000,
					'annex.bid' : animalHandleId,
					'annex.tblName' : "d_animal_handle",
					'annex.fileType' : "photo"
				},
				duplicate: true,//允许上传重复文件
				fileVal : 'myAnnex'
			});
	/** 文件上传成功 */
	uploader.on('uploadSuccess', function(file, response) {
		var annexId = response.annex.id;
		if (annexId) {
			var $a = $("<a>" + file.name + "</a>");
			$a.attr("href", "javascript:searchListOpen('" + file.name + "','" + annexId + "');");
			var $img = $("<img style='vertical-align:middle;' class='hand' src='" + basePath + "static/libs/icons/delete.gif'></img>");
			$img.attr("onclick", "deleteAnnex('" + annexId + "');");
			var $span = $("<span id='" + annexId + "'></span>");
			$span.append($a);
			$span.append("&nbsp;&nbsp;");
			$span.append($img);
			$("#td").append($span);
		}
	});
}
/*************statr 通用*************************/
/**
 * @Title: uploadPhoto
 * @author: MS
 * @date: 2017年1月4日
 * @Description: 上传附件(只是图片)
 * @param: tblName(表名)
 * @param: bid(表ID)
 * @param: picker(渲染上传按钮的事件)
 * @param: fileType(文件类型)
 * @param: echoContainerId(文件名显示的控件)
 */
function uploadPhoto(tblName,bid,picker,fileType,echoContainerId){
	if (!webUploaderSupportCheck()) {
		return;
	}
	var $ = jQuery, state = 'pending', uploader;
	var fileSize = 0;
	uploader = WebUploader.create({
		auto : true,
		resize : false,
		swf : 'static/webuploader/Uploader.swf',
		server : "ajaxUpload!uploadFile.action?newDate=" + new Date().format("yyyy-MM-dd"),
		pick : '#' + picker,
		accept : {
			title : 'Images',
			extensions : 'jpg,png,jpeg',
			mimeTypes : 'image/jpeg,image/png,image/jpg'
		},
		formData : {
			'a' : Math.random() * 10000,
			'annex.bid' : bid,
			'annex.tblName' : tblName,
			'annex.fileType' : fileType
		},
		duplicate: true,//允许上传重复文件
		fileVal : 'myAnnex'
	});	
	/** 文件上传成功 */
	uploader.on('uploadSuccess', function(file, response) {
		var annexId = response.annex.id;
		if (annexId) {
			var deleteStr = "<a href='#' onclick='deleteAnnex(\"" + annexId + "\");'><img style='vertical-align:middle;' src='static/libs/icons/delete.gif'/></a>";
			$("#" + echoContainerId).append(
				"<span id='" + annexId + "'>" + "<a href='#' onclick='searchListOpen(\"" + file.name + "\",\""
				 + annexId + "\");'>" + file.name + "</a>" + "  " + deleteStr + " ;</span>");
		}
		$("#" + echoContainerId).render();
	});
	/** 文件上传失败 */
	uploader.on('uploadError', function(file) {
		top.Dialog.alert(file.name + '上传失败');
	});
	uploader.on('error', function(type) {
		if (fileSize === 0) {
			top.Dialog.alert('请不要上传空白文件!');
			return;
		}
		if (type === 'Q_TYPE_DENIED') {
			var errorMsg = [];
			$.each(uploader.options.accept, function(i) {
				errorMsg.push(uploader.options.accept[i].extensions);
			});
			top.Dialog.alert('文件类型错误!请上传' + errorMsg.join(',') + '文件!');
			return;
		}
		top.Dialog.alert('上传出错,请重试或联系项目开发人员');
	});
}
/**
 * @Title: uploadFile
 * @author: MS
 * @date: 2017年1月4日
 * @Description: 上传附件(其他文件类型)
 * @param: tblName(表名)
 * @param: bid(表ID)
 * @param: picker(渲染上传按钮的事件)
 * @param: fileType(文件类型)
 * @param: echoContainerId(文件名显示的控件)
 */
function uploadFile(tblName,bid,picker,fileType,echoContainerId){
	if (!webUploaderSupportCheck()) {
		return;
	}
	var $ = jQuery, state = 'pending', uploader;
	var fileSize = 0;
	uploader = WebUploader.create({
		auto : true,
		resize : false,
		swf : 'static/webuploader/Uploader.swf',
		server : "ajaxUpload!uploadFile.action?newDate=" + new Date().format("yyyy-MM-dd"),
		pick : '#' + picker,
		accept : {
			title : 'Images',
			extensions : 'doc,docx,xls,xlsx,txt,rar,zip,pdf',
			mimeTypes : 'application/msword,application/vnd.ms-excel'
				+',text/plain,application/x-zip-compressed,application/pdf,application/vnd.openxmlformats-officedocument.wordprocessingml.document'
				+',application/vnd.openxmlformats-officedocument.spreadsheetml.sheet,application/rar'//显示的媒体类型,就是window选择文件类型
		},
		formData : {
			'a' : Math.random() * 10000,
			'annex.bid' : bid,
			'annex.tblName' : tblName,
			'annex.fileType' : fileType
		},
		duplicate: true,//允许上传重复文件
		fileVal : 'myAnnex'
	});	
	/** 文件上传成功 */
	uploader.on('uploadSuccess', function(file, response) {
		var annexId = response.annex.id;
		if (annexId) {
			var deleteStr = "<a href='#' onclick='deleteAnnex(\"" + annexId + "\");'><img style='vertical-align:middle;' src='static/libs/icons/delete.gif'/></a>";
			$("#" + echoContainerId).append(
				"<span id='" + annexId + "'>" + "<a href='#' onclick='searchListOpen(\"" + file.name + "\",\""
				 + annexId + "\");'>" + file.name + "</a>" + "  " + deleteStr + " ;</span>");
		}
		$("#" + echoContainerId).render();
	});
	/** 文件上传失败 */
	uploader.on('uploadError', function(file) {
		top.Dialog.alert(file.name + '上传失败');
	});
	uploader.on('error', function(type) {
		if (fileSize === 0) {
			top.Dialog.alert('请不要上传空白文件!');
			return;
		}
		if (type === 'Q_TYPE_DENIED') {
			var errorMsg = [];
			$.each(uploader.options.accept, function(i) {
				errorMsg.push(uploader.options.accept[i].extensions);
			});
			top.Dialog.alert('文件类型错误!请上传' + errorMsg.join(',') + '文件!');
			return;
		}
		top.Dialog.alert('上传出错,请重试或联系项目开发人员');
	});
}
/**
 * @Title: uploadAllFile
 * @author: MS
 * @date: 2017年1月4日
 * @Description: 上传附件(其他文件类型)
 * @param: tblName(表名)
 * @param: bid(表ID)
 * @param: picker(渲染上传按钮的事件)
 * @param: fileType(文件类型)
 * @param: echoContainerId(文件名显示的控件)
 */
function uploadAllFile(tblName,bid,picker,fileType,echoContainerId){
	if (!webUploaderSupportCheck()) {
		return;
	}
	var $ = jQuery, state = 'pending', uploader;
	var fileSize = 0;
	uploader = WebUploader.create({
		auto : true,
		resize : false,
		swf : 'static/webuploader/Uploader.swf',
		server : "ajaxUpload!uploadFile.action?newDate=" + new Date().format("yyyy-MM-dd"),
		pick : '#' + picker,
		accept : {
			title : 'Images',
			extensions : 'jpg,png,jpeg,doc,docx,xls,xlsx,txt,rar,zip,pdf',
			mimeTypes : 'image/jpg,image/jpeg,image/png,application/msword,application/vnd.ms-excel'
				+',text/plain,application/x-zip-compressed,application/pdf,application/vnd.openxmlformats-officedocument.wordprocessingml.document'
				+',application/vnd.openxmlformats-officedocument.spreadsheetml.sheet,application/rar'//显示的媒体类型,就是window选择文件类型
		},
		formData : {
			'a' : Math.random() * 10000,
			'annex.bid' : bid,
			'annex.tblName' : tblName,
			'annex.fileType' : fileType
		},
		duplicate: true,//允许上传重复文件
		fileVal : 'myAnnex'
	});	
	/** 文件上传成功 */
	uploader.on('uploadSuccess', function(file, response) {
		var annexId = response.annex.id;
		if (annexId) {
			var deleteStr = "<a href='#' onclick='deleteAnnex(\"" + annexId + "\");'><img style='vertical-align:middle;' src='static/libs/icons/delete.gif'/></a>";
			$("#" + echoContainerId).append(
				"<span id='" + annexId + "'>" + "<a href='#' onclick='searchListOpen(\"" + file.name + "\",\""
				 + annexId + "\");'>" + file.name + "</a>" + "  " + deleteStr + " ;</span>");
		}
		$("#" + echoContainerId).render();
	});
	/** 文件上传失败 */
	uploader.on('uploadError', function(file) {
		top.Dialog.alert(file.name + '上传失败');
	});
	uploader.on('error', function(type) {
		if (fileSize === 0) {
			top.Dialog.alert('请不要上传空白文件!');
			return;
		}
		if (type === 'Q_TYPE_DENIED') {
			var errorMsg = [];
			$.each(uploader.options.accept, function(i) {
				errorMsg.push(uploader.options.accept[i].extensions);
			});
			top.Dialog.alert('文件类型错误!请上传' + errorMsg.join(',') + '文件!');
			return;
		}
		top.Dialog.alert('上传出错,请重试或联系项目开发人员');
	});
}
/**
 * @Title: searchListOpen
 * @author:MS
 * @date 2017年11月30日
 * @Description: 显示附件
 * @param: title
 * @param: id
 */
function searchListOpen(title, id){
	var sel = "";
	var imageType = "jpg,png,jpeg";
	if (title != null && title != "") {
		sel = title.substr(title.lastIndexOf('.') + 1, title.length);
	}
	if (imageType.indexOf(sel) > -1){
		top.Dialog.open({
			InnerHtml : "<img src='download!showById.action?id=" + id + "' width='800' height='600' />",
			Title : "查看",
			Width : 800,
			Height : 600
		});
	} else {
		window.location = "download!downloadById.action?id=" + id;
	}
}
/**
 * @Title: annexDatas
 * @author: MS
 * @date: 2017年1月4日
 * @Description: 加载附件
 * @param: tblName(表名)
 * @param: bid(表ID)
 * @param: fileType(文件类型)
 * @param: picker(渲染上传按钮的事件)
 * @param: echoContainerId(文件名显示的控件)
 */
function annexDatas(tblName, bid, fileType,containerId,callback){
	$.ajax({
		url : 'ajaxAnnex!searchList.action',
		data : {
			"TblName" : tblName,
			"BID" : bid,
			"fileType" :fileType
		},
		type : 'post',
		dataType : 'json',
		async : true,
		success : function(result) {
			callback(containerId, result.gridJson);
		}
	});
}
/**
 * @Title: showAnnexEdit
 * @author: MS
 * @date: 2017年11月30日
 * @Description:附件列表可删除
 * @param: containerId(显示附件容器)
 * @param: gridJson(附件信息)
 */
function showAnnexEdit(containerId, gridJson){
	if (typeof gridJson === 'string') {
		gridJson = JSON.parse(gridJson);
	}
	for (var i = 0, len = gridJson.length; i < len; i++) {
		if (!!gridJson[i]) {
			var annex = gridJson[i];
			var deleteStr = "<a href='#' onclick='deleteAnnex(\"" + annex.id + "\");'><img style='vertical-align:middle;' src='static/libs/icons/delete.gif'/></a>";
			$("#" + containerId).append(
				"<span id='" + annex.id + "'>" + "<a href='#' onclick='searchListOpen(\"" + annex.fileName + "\",\""
				 + annex.id + "\");'>" + annex.fileName + "</a>" + "  " + deleteStr + " ;</span>");
		}
	}
}
/**
 * @Title: showAnnexView
 * @author: MS
 * @date: 2017年11月30日
 * @Description:附件列表不可删除
 * @param: containerId(显示附件容器)
 * @param: gridJson(附件信息)
 */
function showAnnexView(containerId, gridJson){
	if (typeof gridJson === 'string') {
		gridJson = JSON.parse(gridJson);
	}
	for (var i = 0, len = gridJson.length; i < len; i++) {
		if (!!gridJson[i]) {
			var annex = gridJson[i];
			$("#" + containerId).append(
					"<span id='" + annex.id + "'>" + "<a href='#' onclick='searchListOpen(\"" + annex.fileName + "\",\""
					 + annex.id + "\");'>" + annex.fileName+";"+"</span>");
		}
	}
}
/**
 * @Title: deleteAnnex
 * @author: wzl
 * @date: 2017年11月30日
 * @Description:删除附件
 * @param: id
 */
function deleteAnnex(id) {
	top.Dialog.confirm("您确定要删除该附件？", function(){
		$.ajax({
			url : "ajaxDownload!deleteById.action",
			type : "post",
			data : "annex.id=" + id,
			success : function(data) {
				var status = data.status;
				if ("" == status) {
					$("#" + id).remove();
				} else {
					top.Dialog.alert(status);
				}
			},
			error : function(req) {
				top.Dialog.alert(req.responseText);
			}
		});
	});
}
/**
 * @Title: upAndDownUpdateIsReal
 * @author: MS
 * @date: 2017年11月30日
 * @Description:修改附件
 * @param: tblName(表名)
 * @param: bid(关联id)
 */
function upAndDownUpdateIsReal(tblName, bid) {
	var msg = "";
	$.ajax({
		url : "ajaxUpload!updateIsReal.action",
		type : "post",
		data : "tblName=" + tblName + "&&bid=" + bid + "&&isReal=1",
		success : function(data) {
			msg = data.status;
			if ("" != msg) {
				top.Dialog.alert(msg);
			}
		},
		error : function(req) {
			top.Dialog.alert(req.responseText)
		}
	});
	return ("" == msg);
}
/**
 * @Title: addBtnCancel
 * @author: MS
 * @date: 2017年11月30日
 * @Description:新增取消
 * @param: tblName(表名)
 * @param: bid(关联id)
 */
function addBtnCancel(tblName, bid) {
	var msg = "";
	$.ajax({
		url : "ajaxUpload!addCancel.action",
		type : "post",
		data : "tblName=" + tblName + "&&bid=" + bid,
		async : false,
		success : function(data) {
			msg = data.status;
			if ("" != msg) {
				top.Dialog.alert(msg);
			}
		},
		error : function(req) {
			top.Dialog.alert(req.responseText)
		}
	});
	return ("" == msg);
}
/**
 * @Title: editBtnCancel
 * @author: MS
 * @date: 2017年11月30日
 * @Description:修改取消
 * @param: tblName(表名)
 * @param: bid(关联id)
 */
function editBtnCancel(tblName, bid) {
	var msg = "";
	$.ajax({
		url : "ajaxUpload!editCancel.action",
		type : "post",
		data : "tblName=" + tblName + "&&bid=" + bid,
		async : false,
		success : function(data) {
			msg = data.status;
			if ("" != msg) {
				top.Dialog.alert(msg);
			}
		},
		error : function(req) {
			top.Dialog.alert(req.responseText)
		}
	});
	return ("" == msg);
}
/**
 * @Title: uploadImageLimitOne(只上传一个图片)
 * @author: MS
 * @date 2017年5月31日
 * @Description: 
 * @param: tblName(表名)
 * @param: bid(表ID)
 * @param: picker(渲染上传按钮的事件)
 * @param: echoContainerId(容器id)
 * @param: echoCallback(上传调用事件)
 */
function uploadImageLimitOne(tblName, bid, picker, echoContainerId, echoCallback){
	if (!webUploaderSupportCheck()) {
		return;
	}
	var $ = jQuery, state = 'pending', uploader;
	var fileSize = 0;
	var fileSingleSizeLimit = undefined;
	uploader = WebUploader.create({
		auto : true,
		resize : false,
		swf : 'static/webuploader/Uploader.swf',
		server : "ajaxUpload!uploadFileLimitOne.action",
		pick : '#' + picker,
		accept : {
			title : 'Images',
			extensions : 'jpg,png,jpeg',
			mimeTypes : 'image/jpg,image/jpeg,image/png'
		},
		formData : {
			'a' : Math.random() * 10000,
			'annex.bid' : bid,
			'annex.tblName' : tblName
		},
		duplicate: true,//允许上传重复文件
		fileVal : 'myAnnex'
	});	
	/** 文件上传成功 */
	uploader.on('uploadSuccess', function(file, response) {
		var annexId = response.annex.id;
		if (annexId) {
			var deleteStr = "<a href='#' onclick='deleteAnnex(\"" + annexId + "\");'>" +
					"<img style='vertical-align:middle;' src='static/libs/icons/delete.gif'/></a>";
			$("#" + echoContainerId).html(
					"<span id='" + annexId + "'>" + "<a href='javascript:searchListOpen(\"" + response.annex.bid + "\",\"" + 
					response.annex.tblName + "\"," + "\"" + annexId + "\",\""
							+ file.name + "\");'>" + file.name + "</a>" + "  " + deleteStr + "</span>");
			}
		$("#" + echoContainerId).render();
		top.Dialog.alert('文件上传成功!');
	});
	/** 文件上传失败 */
	uploader.on('uploadError', function(file) {
		top.Dialog.alert(file.name + '上传失败');
	});
	uploader.on('error', function(type,file) {
		fileSize = file.size;
		if (fileSize === 0) {
			top.Dialog.alert('请不要上传空白文件!');
			return;
		}
		if (type === 'Q_TYPE_DENIED') {
			var errorMsg = [];
			$.each(uploader.options.accept, function(i) {
				errorMsg.push(uploader.options.accept[i].extensions);
			});
			top.Dialog.alert('文件类型错误!请上传' + errorMsg.join(',') + '文件!');
			return;
		}
		top.Dialog.alert('上传出错,请重试或联系项目开发人员');
	});
}
/**
 * @Title: uploadFileLimitOne(只上传一个除图片类型)
 * @author: MS
 * @date 2017年5月31日
 * @Description: 
 * @param: tblName(表名)
 * @param: bid(表ID)
 * @param: picker(渲染上传按钮的事件)
 * @param: echoContainerId(容器id)
 * @param: echoCallback(上传调用事件)
 */
function uploadFileLimitOne(tblName, bid, picker, echoContainerId, echoCallback){
	if (!webUploaderSupportCheck()) {
		return;
	}
	var $ = jQuery, state = 'pending', uploader;
	var fileSize = 0;
	var fileSingleSizeLimit = undefined;
	uploader = WebUploader.create({
		auto : true,
		resize : false,
		swf : 'static/webuploader/Uploader.swf',
		server : "ajaxUpload!uploadFileLimitOne.action",
		pick : '#' + picker,
		accept : {
			title : 'Images',
			extensions : 'doc,docx,xls,xlsx,txt,rar,zip,pdf',
			mimeTypes : 'application/msword,application/vnd.ms-excel'
				+',text/plain,application/x-zip-compressed,application/pdf,application/vnd.openxmlformats-officedocument.wordprocessingml.document'
				+',application/vnd.openxmlformats-officedocument.spreadsheetml.sheet,application/rar'//显示的媒体类型,就是window选择文件类型
		},
		formData : {
			'a' : Math.random() * 10000,
			'annex.bid' : bid,
			'annex.tblName' : tblName
		},
		duplicate: true,//允许上传重复文件
		fileVal : 'myAnnex'
	});	
	/** 文件上传成功 */
	uploader.on('uploadSuccess', function(file, response) {
		var annexId = response.annex.id;
		if (annexId) {
			var deleteStr = "<a href='#' onclick='deleteAnnex(\"" + annexId + "\");'>" +
					"<img style='vertical-align:middle;' src='static/libs/icons/delete.gif'/></a>";
			$("#" + echoContainerId).html(
					"<span id='" + annexId + "'>" + "<a href='javascript:searchListOpen(\"" + response.annex.bid + "\",\"" + 
					response.annex.tblName + "\"," + "\"" + annexId + "\",\""
							+ file.name + "\");'>" + file.name + "</a>" + "  " + deleteStr + "</span>");
			}
		$("#" + echoContainerId).render();
		top.Dialog.alert('文件上传成功!');
	});
	/** 文件上传失败 */
	uploader.on('uploadError', function(file) {
		top.Dialog.alert(file.name + '上传失败');
	});
	uploader.on('error', function(type,file) {
		fileSize = file.size;
		if (fileSize === 0) {
			top.Dialog.alert('请不要上传空白文件!');
			return;
		}
		if (type === 'Q_TYPE_DENIED') {
			var errorMsg = [];
			$.each(uploader.options.accept, function(i) {
				errorMsg.push(uploader.options.accept[i].extensions);
			});
			top.Dialog.alert('文件类型错误!请上传' + errorMsg.join(',') + '文件!');
			return;
		}
		top.Dialog.alert('上传出错,请重试或联系项目开发人员');
	});
}
/**
 * @Title: uploadAllLimitOne(只上传一个,所有类型)
 * @author: MS
 * @date 2017年5月31日
 * @Description: 
 * @param: tblName(表名)
 * @param: bid(表ID)
 * @param: picker(渲染上传按钮的事件)
 * @param: echoContainerId(容器id)
 * @param: echoCallback(上传调用事件)
 */
function uploadAllLimitOne(tblName, bid, picker, echoContainerId, echoCallback){
	if (!webUploaderSupportCheck()) {
		return;
	}
	var $ = jQuery, state = 'pending', uploader;
	var fileSize = 0;
	var fileSingleSizeLimit = undefined;
	uploader = WebUploader.create({
		auto : true,
		resize : false,
		swf : 'static/webuploader/Uploader.swf',
		server : "ajaxUpload!uploadFileLimitOne.action",
		pick : '#' + picker,
		accept : {
			title : 'Images',
			extensions : 'jpg,png,jpeg,doc,docx,xls,xlsx,txt,rar,zip,pdf',
			mimeTypes : 'image/jpg,image/jpeg,image/png,application/msword,application/vnd.ms-excel'
				+',text/plain,application/x-zip-compressed,application/pdf,application/vnd.openxmlformats-officedocument.wordprocessingml.document'
				+',application/vnd.openxmlformats-officedocument.spreadsheetml.sheet,application/rar'//显示的媒体类型,就是window选择文件类型
		},
		formData : {
			'a' : Math.random() * 10000,
			'annex.bid' : bid,
			'annex.tblName' : tblName
		},
		duplicate: true,//允许上传重复文件
		fileVal : 'myAnnex'
	});	
	/** 文件上传成功 */
	uploader.on('uploadSuccess', function(file, response) {
		var annexId = response.annex.id;
		if (annexId) {
			var deleteStr = "<a href='#' onclick='deleteAnnex(\"" + annexId + "\");'>" +
					"<img style='vertical-align:middle;' src='static/libs/icons/delete.gif'/></a>";
			$("#" + echoContainerId).html(
					"<span id='" + annexId + "'>" + "<a href='javascript:searchListOpen(\"" + response.annex.bid + "\",\"" + 
					response.annex.tblName + "\"," + "\"" + annexId + "\",\""
							+ file.name + "\");'>" + file.name + "</a>" + "  " + deleteStr + "</span>");
			}
		$("#" + echoContainerId).render();
		top.Dialog.alert('文件上传成功!');
	});
	/** 文件上传失败 */
	uploader.on('uploadError', function(file) {
		top.Dialog.alert(file.name + '上传失败');
	});
	uploader.on('error', function(type,file) {
		fileSize = file.size;
		if (fileSize === 0) {
			top.Dialog.alert('请不要上传空白文件!');
			return;
		}
		if (type === 'Q_TYPE_DENIED') {
			var errorMsg = [];
			$.each(uploader.options.accept, function(i) {
				errorMsg.push(uploader.options.accept[i].extensions);
			});
			top.Dialog.alert('文件类型错误!请上传' + errorMsg.join(',') + '文件!');
			return;
		}
		top.Dialog.alert('上传出错,请重试或联系项目开发人员');
	});
}
/*************statr 通用*************************/
/**
 * @date: 2018年1月24日
 * @Description: 时间格式转换
 * @param: fmt(格式)
 */
Date.prototype.format = function(fmt) { 
    var o = { 
       "M+" : this.getMonth()+1,                 //月份 
       "d+" : this.getDate(),                    //日 
       "h+" : this.getHours(),                   //小时 
   }; 
   if(/(y+)/.test(fmt)) {
           fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length)); 
   }
    for(var k in o) {
       if(new RegExp("("+ k +")").test(fmt)){
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
        }
    }
   return fmt; 
}