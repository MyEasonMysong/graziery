(function ($, window) {
	
	//全局变量，保存uploader供其它方法调用，如保存
    var updataLoadarrayObj = new Array();
    var uploaderNum = 0;
    // var ctx = "http://"+document.location.host+"/a";
    // var ctxStatic = "http://"+document.location.host+"/static";
    /**
	 * 
	 * @Title: initWebUpload
	 * @author qjc
	 * @date 2018-2-11 
	 * @Description: baidu上传附件插件初始化
	 * @param string item 当前标签this
	 * @param JSON options 参数配置
	 */
    var initWebUpload = function(item, options) {
		if (!WebUploader.Uploader.support()) {
			var error = "上传控件不支持您的浏览器！请尝试升级flash版本或者使用Chrome引擎的浏览器。<a target='_blank' href='http://se.360.cn'>下载页面</a>";
			if (window.console) {
				window.console.log(error);
			}
			$(item).text(error);
			return;
		}
		// 创建默认参数
        var defaults = {
        	pickerName: "选择文件", 
        	tableName: undefined,
        	tableId: undefined,
        	type: undefined,
        	acceptType: undefined,//上传文件类型all/images/files/imagesAndFiles
            innerOptions: { //自定义百度插件的属性
            	auto: false,
            	onAllComplete: function (event) { }, // 当所有file都上传后执行的回调函数
                onComplete: function (event) { }// 每上传一个file的回调函数
            },
            fileNumLimit: undefined,//验证文件总数量, 超出则不允许加入队列
            fileSizeLimit: undefined,//验证文件总大小是否超出限制, 超出则不允许加入队列。
            fileSingleSizeLimit: 10*1024*1024,//验证单个文件大小是否超出限制, 超出则不允许加入队列
            filePickerTag: '<span></span>', //选择文件标签
    		queueTag: '<span></span>',//文件列表容器
    		downQueueTag: '<span></span>',//后台加载附件标签
    		liTag: '<span></span>',//单个文件标签
    		imgWrapTag: '',//图片小图标预览标签 选填
    		titleTag: '',//文件名称标签 选填
    		progressTag: '<span></span>',//文件上传进度标签
    		btnsTag: '<span>&nbsp;&nbsp;删除&nbsp;&nbsp;</span>'//文件删除标签
        };
        //alert(JSON.stringify(defaults));
        var opts = $.extend(defaults, options);
		// 添加的文件数量
		var fileCount = 0;
		// 添加的文件总大小
		var fileSize = 0;
		// 所有文件的进度信息，key为file id
		var percentages = {};
		// 附件容器
		var $wrap = $(item);
		// 文件选择容器
		var $filePicker = $(opts.filePickerTag).attr("id",$wrap.attr("id")+"_filePicker").appendTo($wrap);
		// 图片列表容器
		var $queue = $(opts.queueTag).addClass("queueList").appendTo($wrap);
		// 加载数据库附件容器
		var $downQueue;
		if(opts.downQueueTag){
			$downQueue = $(opts.downQueueTag).addClass("downQueueList").appendTo($wrap);
		}
		// 加载数据库附件
		var downQueue = function() {
			fileList(opts.tableName, opts.tableId, opts.type, function(data) {
				for (var i = 0, len = data.length; i < len; i++) {
					if (!!data[i]) {
						addDownList(data[i]);
					}
				}
			});
		}();
    	// 总体进度条-点击保存会遮罩表单
		if($(".webuploadProgress").length){
			var $progress = $(".webuploadProgress");
		}else{
			var $progress = $('<div class="webuploadProgress"><div class="progress" style="position:absolute;left:0;width:100%;height:100%;'
					+'top:0;background:#f3f8ff;opacity:0.6;filter:alpha(opacity=60);z-index:10000;"></div>'
					+'<div class="progressLine" style="position: absolute;z-index:10001; left: 50%; top:50%;margin-left:-120px;margin-top:-50px;'
					+'width: 190px; line-height: 42px; padding-left: 50px; padding-right: 5px;'
					+'background: #fff url('+ctxStatic+'/webuploader/img/p_loading.gif) no-repeat scroll 5px; border: 2px solid #3d88d9;'
					+'color: #696969; font-family:\'Microsoft YaHei\';">'
					+'</div></div>').appendTo($("body")).hide();
		}
		if($("."+$wrap.attr('id')+"_percentCount").length){
			var $progresSpan =$progress.find(".progressLine");
		}else{
			var $progresSpan =$('<div><span>附件已上传</span><span class="'+$wrap.attr("id")+'_percentCount">0%</span><span>，请等待......</span></br></div>').appendTo($progress.find(".progressLine"));
		}
        var acceptJson = function() {
			switch (opts.acceptType) {
			case "all":
				return;
			case "images":
				return {
					accept : {
						title : 'Images',
						extensions : 'jpeg,jpg,png',
						mimeTypes : '.jpeg,.jpg,.png'
					}
				};
			case "files":
				return {
					accept : {
						title : 'Files',
						extensions : 'pdf,doc,docx,xml,xls,xlsx,ppt,pptx,wps,rar,zip,txt,text',
						mimeTypes : '.pdf,.doc,.docx,.xml,.xls,.xlsx,.ppt,.pptx,.wps,.rar,.zip,.txt,.text'
					}
				};
			case "imagesAndFiles":
				return {
					accept : {
						title : '.png,.jpg,.JPG,.jpeg,.doc,.docx,.xls,.rar,.txt,.zip,.wav,.mps,.avi,.flv,.mp3,.img,.pdf,.PDF',
						extensions : 'png,jpg,JPG,jpeg,doc,docx,xls,rar,txt,zip,wav,mps,avi,flv,mp3,img,pdf,PDF',
						mimeTypes : '.png,.jpg,.JPG,.jpeg,.doc,.docx,.xls,.rar,.txt,.zip,.wav,.mps,.avi,.flv,.mp3,.img,.pdf,.PDF'
					}
				};
			default:
				return ;	
			}
		}();
		var innerOptions = $.extend(opts.innerOptions,acceptJson);
		// 参数初始化
		var webuploaderoptions = $.extend({
			pick : {
				id : "#"+$wrap.attr("id")+"_filePicker",
				label : opts.pickerName
			},
			swf : ctxStatic+"/webuploader/Uploader.swf",
			server : ctx+'/sys/sysAnnex/upload',
			auto : opts.auto,
			formData : {
				'tableId' : opts.tableId,
				'tableName' : opts.tableName,
				'type': opts.type,
				"t" : new Date().getTime()
			},
			multiple : true,
			fileNumLimit : opts.fileNumLimit, //文件上传最大数由onBeforeFileQueued方法控制
            fileSingleSizeLimit: opts.fileSingleSizeLimit,//验证单个文件大小是否超出限制, 超出则不允许加入队列
			fileVal : "file"
		}, innerOptions);
		var uploader= WebUploader.create(webuploaderoptions);
        // 判断附件是否自动上传
        if (opts.auto) {
        	// 当文件被加入队列以后触发
            uploader.on('fileQueued', function (file) {
            	addFile(file);
                uploader.upload();
            });
        } else {
        	// 当文件被加入队列以后触发
            uploader.on('fileQueued', function (file) {//队列事件
            	addFile(file);
            });
        }
        // 当文件被加入队列之前触发，此事件的handler返回值为false，则此文件不会被添加进入队列。
        uploader.on('beforeFileQueued',function(file) {
			if (1 == opts.fileNumLimit && fileCount == 1) {
				for (var i = 0; i < uploader.getFiles().length; i++) {
					// 将图片从上传序列移除
					uploader.removeFile(uploader.getFiles()[i]);
				}
				uploader.reset();
				if($downQueue.children().css("display") != "none"){
					$downQueue.children().hide();
					$downQueue.children().addClass("del");
				}
			} else if (fileCount+1 > opts.fileNumLimit) {
				if(!$wrap.is('.filled')){
					$wrap.addClass("filled");
					alert("最多只能上传" + opts.fileNumLimit);
					$filePicker.find("label").unbind('click').on('click', function() {
						alert("最多只能上传" + opts.fileNumLimit);
					});
				}
				return false;
			}
			if(file.size>=opts.fileSingleSizeLimit){
                alertLayer("单个文件不能大于" + opts.fileSingleSizeLimit/1024/1024+"M",1);
              //  alert("单个文件不能大于" + opts.fileSingleSizeLimit/1024/1024+"M");
			}
        });
        // 当文件被移除队列后触发
		uploader.on('fileDequeued',function(file) {
			fileCount--;
			fileSize -= file.size;
			$wrap.removeClass("filled");
			$filePicker.find("label").unbind('click').on('click', function() {
				$filePicker.find("input").trigger('click');
			});
			var $li = $("#"+$wrap.attr("id") + "_" + file.id);
			if ($li) {
				$li.remove();
			}
		});
		// 文件上传过程中创建进度条实时显示。
		uploader.on('uploadProgress', function(file, percentage) {
			var $li = $("#"+$wrap.attr("id") + "_" + file.id);
			var $percent = $li.find('.progress');
			$percent.html(parseInt(percentage * 100) + '%');
			percentages[file.id][1] = percentage;
			updateTotalProgress();
		});
		// 所有文件上传结束时
		uploader.on('uploadFinished', function() {
			var stats = uploader.getStats();
			// 判断需要删除已加载的文件
			if ($downQueue) {
				$downQueue.find(".del").each(function() {
					deletePubAttachmentList($(this).attr("id"));
				});
			}
		});
		// 当某个事件执行的时候执行
		uploader.on('all', function(type) {
			switch (type) {
			case 'uploadFinished':
				break;
			// 当点击上传后，隐藏保存按钮并显示遮罩进度div
			case 'startUpload':
				$progress.show();
				if(!$queue.html()){
					$progresSpan.hide();
				}
				$progress.find(".progressLine").css("margin-top","-"+$progress.find(".progressLine").height()/2+"px");
				break;
			case 'stopUpload':
				break;
			}
		});
		var addFile = function(file) {
			fileCount++;
			fileSize += file.size;
			var $li = $(opts.liTag).attr("id", $wrap.attr("id") + "_" + file.id);
			var $imgWrap;
			if(opts.imgWrapTag){
				$imgWrap = $(opts.imgWrapTag).appendTo($li);
			}
			var $title;
			if(opts.titleTag){
				$title = $(opts.titleTag).appendTo($li);
			}
			var $progress = $(opts.progressTag).addClass("progress").appendTo($li);
			var $btns = $(opts.btnsTag).appendTo($li);
			if ($title) {
				$title.html(file.name);
				$title.on('click', function() {
					// 图片放大预览
					enlargePicture(file);
				});
			}
			if ($imgWrap) {
				if (file.getStatus() === 'invalid') {
					alert('文件不合格，不能重试上传。');
				} else {
					uploader.makeThumb(file, function(error, src) {
						if (error) {
							// $imgWrap.replaceWith('<span>不能预览</span>');
							$imgWrap.attr("src", ctxStatic+"/webuploader/img/p_timg.jpg");
							$imgWrap.attr("width", "120px");
							$imgWrap.attr("height", "160px");
						} else {
							$imgWrap.attr("src", src);
						}
						$imgWrap.attr("title", file.name);
					}, 120, 160);
				}
				// 点击图片放大显示
				$imgWrap.on('click', function() {
					// 图片放大预览
					enlargePicture(file);
				});
			}
			// 判断文件是否上传成功
			file.on('statuschange', function(cur, prev) {
				if (prev === 'queued') {
					$btns.remove();
				}
				if (cur === 'complete') {
					$li.addClass("success");
				}
			});
			// $queue为容器
			$li.appendTo($queue);
			percentages[file.id] = [ file.size, 0 ];
			$btns.on('click', function() {
				uploader.removeFile(file);
			});
		};
		// 图片放大预览
		var enlargePicture = function(file) {
			if (file.getStatus() === 'invalid') {
				alert('文件不合格，不能重试上传。');
			} else {
				uploader.makeThumb(file, function(error, src) {
					if (error) {
						alert('非图片不能预览');
						return;
					}
					var img = '<a class="fancybox"><img src="' + src + '"></a>';
				});
			}
		};
		var addDownList = function(sysAnnex) {
			var sel = "";
			var imageType = "jpg,png,jpeg";
			if (sysAnnex.fileName) {
				sel = sysAnnex.fileName.substr(sysAnnex.fileName.lastIndexOf('.') + 1, sysAnnex.fileName.length);
			}
			fileCount++;
			fileSize += sysAnnex.fileSize;
			var $li = $(opts.liTag).attr("id", sysAnnex.id);
			var $imgWrap;
			if(opts.imgWrapTag){
				$imgWrap = $(opts.imgWrapTag).appendTo($li);
			}
			var $title;
			if(opts.titleTag){
				$title = $(opts.titleTag).appendTo($li);
			}
			var $btns = $(opts.btnsTag).appendTo($li);
			if ($title) {
				$title.html(sysAnnex.fileName);
				$title.on('click', function() {
					// 图片放大预览
					searchListOpen(sysAnnex.fileName, sysAnnex.id);
				});
			}
			if ($imgWrap) {
				if (imageType.indexOf(sel) > -1) {
					$imgWrap.attr("src",  ctx +"/sys/sysAnnex/download?id=" + sysAnnex.id);
				}else{
					$imgWrap.attr("src", ctxStatic+"/webuploader/img/p_timg.jpg");
				}
				$imgWrap.attr("width", "120px");
				$imgWrap.attr("height", "160px");
				$imgWrap.attr("title",sysAnnex.fileName);
				// 点击图片放大显示
				$imgWrap.on('click', function() {
					// 图片放大预览
					searchListOpen(sysAnnex.fileName, sysAnnex.id);
				});
			}
			// $queue为容器
			$li.appendTo($downQueue);
			$btns.on('click', function() {
				fileCount--;
				$wrap.removeClass("filled");
				$filePicker.find("label").unbind('click').on('click', function() {
					$filePicker.find("input").trigger('click');
				});
				$("#" + sysAnnex.id).hide();
				$("#" + sysAnnex.id).addClass("del");
			});
		};
		// 进度百分比合并
		var updateTotalProgress = function() {
			var loaded = 0;
			var total = 0;
			var percent;
			$.each(percentages, function(k, v) {
				total += v[0];
				loaded += v[0] * v[1];
			});
			percent = total ? loaded / total : 0;
			$("."+$wrap.attr("id")+"_percentCount").html(Math.round(percent * 100) + '%');
		};
		// 全局变量
        updataLoadarrayObj[$wrap.attr("id")] = uploader;
    };
    
    /**
	 * 
	 * @Title: fileList
	 * @author qjc
	 * @date 2018-2-11 
	 * @Description: 查询已上传的附件
	 * @param string tableName 业务表名
	 * @param string tableId 关联id
	 * @param string type 业务类型
	 * @param callback  回调函数
	 */
	var fileList = function(tableName,tableId,type,callback){
		var jsonDate = {
			"tableName" : tableName,
			"tableId" : tableId,
			"fileType" : type
		};

        $.ajax({
			url : ctx+'/sys/sysAnnex/searchList',
			data : jsonDate,
			type : 'post',
			dataType : 'json',
			async : true,
			success : function(result) {
				callback(result);
			}
		});
	};
	
	/**
	 * 
	 * @Title: searchListOpen
	 * @author qjc
	 * @date 2018-2-11 
	 * @Description: 查询单个附件路径并返显或下载
	 * @param string title 附件名称
	 * @param string id 附件id
	 */
	var searchListOpen = function(title, id) {
		var sel = "";
		var imageType = "jpg,png,jpeg";
		if (title != null && title != "") {
			sel = title.substr(title.lastIndexOf('.') + 1, title.length);
		}
		window.location = ctx+"/sys/sysAnnex/download?id=" + id;
	};
	/**
	 * 
	 * @Title: deletePubAttachmentList
	 * @author lidan
	 * @date 2018-2-11 
	 * @Description: 删除单个附件
	 * @param string id 附件id
	 */
	var deletePubAttachmentList = function(id) {
		$.ajax({
			url : ctx +"/sys/sysAnnex/delete",
			type : "post",
			data : "id=" + id,
			success : function(data) {
			},
			error : function(req) {
			}
		});
	};
	
	/**
	 * 
	 * @Title: initFileWebUpload
	 * @author qjc
	 * @date 2018-2-11 
	 * @Description: 调用baidu上传附件插件初始化
	 * @param string tableName 业务表名
	 * @param string tableId 关联id
	 * @param string type 业务类型
	 * @param int fileNumLimit  最大上传附件数 
	 * @param string acceptType 附件文件类型(all 全部,images 图片,files 文本类文件,imagesAndFiles 图片及文本类文件 )
	 */
	$.fn.initFileWebUpload = function(tableName, tableId, type, fileNumLimit, acceptType) {
		var ele = this;
        var delGif = ctxStatic+"/webuploader/img/delete.gif";
		var options = {
			pickerName: "上传附件",
			tableName : tableName,
			tableId : tableId,
			type : type,
			fileNumLimit : fileNumLimit,// 验证文件总数量, 超出则不允许加入队列
			acceptType : acceptType,
			filePickerTag: '<span></span>', // 选择文件标签 必填
	    	queueTag: '<span></span>',// 文件列表容器 必填
	    	downQueueTag: '<span></span>',// 后台加载附件标签 必填
	    	liTag: '<span></span>',// 单个文件标签 必填
	    	//imgWrapTag: '<img class="hand">',// 图片小图标预览标签 选填 设置为空则不显示
	    	titleTag: '<br /><a class="blue underLine hand" style="cursor:pointer;"></a>',// 文件名称标签 选填 设置为空则不显示
	    	progressTag: '<span></span>',// 文件上传进度标签 必填
	    	//btnsTag: '<span class="red hand">&nbsp;&nbsp;删除&nbsp;&nbsp;</span>',// 文件删除标签 必填
	    	btnsTag: '<img class="delImg hand" title="删除" style="vertical-align:middle;cursor:pointer;" src="'+delGif+'"/>',// 文件删除标签 必填
			innerOptions : {} // 配置百度插件参数
		};
		if (typeof WebUploader == 'undefined') {
			var casspath = ctxStatic+"/webuploader/css/webuploader.css";
			$("<link>").attr({
				rel : "stylesheet",
				type : "text/css",
				href : casspath
			}).appendTo("head");
			var jspath = ctxStatic+"/webuploader/js/webuploader.js";
			$.getScript(jspath).done(function() {
				initWebUpload(ele, options);
			}).fail(function() {
				alert("请检查webuploader的路径是否正确!")
			});
		} else {
			initWebUpload(ele, options);
		}
	}

	/**
	 * 
	 * @Title: saveUpload
	 * @author qjc
	 * @date 2018-2-11 
	 * @Description: 保存
	 */
	$.fn.saveUpload = function (callback) {
		//js array的key不为数字时获取长度
        var uploadrFile = updataLoadarrayObj[$(this).attr("id")];
        uploadrFile.upload();
        // 所有文件上传结束时
        uploadrFile.on('uploadFinished', function() {
			var stats = uploadrFile.getStats();
			uploaderNum++;
			// 上传失败提示重新上传 uploadFailNum 上传失败的文件数
			if (stats.uploadFailNum) {
				uploaderNum = 0;
				alert('附件上传失败，请到编辑窗口重新上传!');
                $(".webuploadProgress").css("display","none");
			}
			if(uploaderNum == Object.keys(updataLoadarrayObj).length){
				uploaderNum = 0;
               $(".webuploadProgress").css("display","none");
                if(!!callback){
                    callback();
                }
			}
		});
    }
	
	/**
	 * 
	 * @Title: cleanUpload
	 * @author lidan
	 * @date 2018-2-11 
	 * @Description: 清空附件
	 */
	$.fn.cleanUpload = function () {
        var uploadrFile = updataLoadarrayObj[$(this).attr("id")];
        var fileslist = uploadrFile.getFiles();
        console.log(Object.keys(updataLoadarrayObj).length);
        console.log(updataLoadarrayObj);
        for (var i in fileslist) {
            uploadrFile.removeFile(fileslist[i]);
        }
        $('.'+$(this).attr("id")+'_percentCount').parent().remove();
        //alert($('.'+$(this).attr("id")+'_percentCount').parent().html());
        delete updataLoadarrayObj[$(this).attr("id")];
        console.log(Object.keys(updataLoadarrayObj).length);
        console.log(updataLoadarrayObj);
    }
	/**
	 * 
	 * @Title: initWebUploadOption
	 * @author qjc
	 * @date 2018-2-11 
	 * @Description: 调用baidu上传附件插件初始化
	 * @param json options 参数配置
	 */
	$.fn.initWebUploadOption = function(options) {
		var ele = this;
		if (typeof WebUploader == 'undefined') {
			var casspath = ctxStatic+"/webuploader/css/webuploader.css";
			$("<link>").attr({
				rel : "stylesheet",
				type : "text/css",
				href : casspath
			}).appendTo("head");
			var jspath =  ctxStatic+"/webuploader/js/webuploader.js";
			$.getScript(jspath).done(function() {
				initWebUpload(ele, options);
			}).fail(function() {
				alert("请检查webuploader的路径是否正确!")
			});
		} else {
			initWebUpload(ele, options);
		}
	}
	
	$.fn.viewFile = function(tableName,tableId,type) {
		var ele = this;
		fileList(tableName, tableId, type, function(data) {
			for (var i = 0, len = data.length; i < len; i++) {
				if (!!data[i]) {
					viewList(ele,data[i],'img');
				}
			}
		});
	}
	
	$.fn.viewFileName = function(tableName,tableId,type) {
		var ele = this;
		fileList(tableName, tableId, type, function(data) {
			for (var i = 0, len = data.length; i < len; i++) {
				if (!!data[i]) {
					viewList(ele,data[i],'name');
				}
			}
		});
	}
	
	var viewList = function(tag,sysAnnex,type) {
		pubAttachmentsel = "";
		var imageType = "jpg,png,jpeg";
		if (sysAnnex.fileName) {
			sel = sysAnnex.fileName.substr(sysAnnex.fileName.lastIndexOf('.') + 1, sysAnnex.fileName.length);
		}
		var $li = $(tag);
		var $title = $('<a style="cursor:pointer;"></a>').appendTo($li);
		if ($title) {
			$title.html(sysAnnex.fileName+'&nbsp;&nbsp;<br />');
			$title.on('click', function() {
				// 图片放大预览
				searchListOpen(sysAnnex.fileName, sysAnnex.id);
			});
		}
	};
	/**
	 * 
	 * @Title: validateFileByRequired
	 * @author lxy
	 * @date 2018-4-17 
	 * @Description: 必填校验
	 * @param proId 渲染组件的id
	 */
	//
	$.fn.validateFileByRequired = function(proId){
		//默认附件检验状态
		var fileCheckFlag=true;
		//上传附件列表
		var queueList=$("#"+proId+" .queueList");
		//下载附件列表
		var downQueueList=$("#"+proId+" .downQueueList");
		//如果下载附件列表里的附件都删除，并且待上传列表里为空，则验证不通过
		var allFileNum=downQueueList.children('span').length;
		if(allFileNum!=0){
			var delFileNum=0;
			downQueueList.children('span').each(function(i,n){
	             if($(this).attr("class")=="del"){
	            	 delFileNum++;
	             }
	      	});
			if(allFileNum==delFileNum && queueList.html()==""){
				fileCheckFlag=false;
			}
		}else{
			if(queueList.html()==""){
				fileCheckFlag=false;
			}
		}
		return fileCheckFlag;
	}
	
	/**
	 * 
	 * @Title: saveUploadAndSubmit
	 * @author qjc
	 * @date 2018-5-3 
	 * @Description: 保存并提交
	 * @param:
	 */
	$.fn.saveUploadAndSubmit = function () {
		//js array的key不为数字时获取长度
        var uploadrFile = updataLoadarrayObj[$(this).attr("id")];
        uploadrFile.upload();
        // 所有文件上传结束时
        uploadrFile.on('uploadFinished', function() {
			var stats = uploadrFile.getStats();
			uploaderNum++;
			// 上传失败提示重新上传 uploadFailNum 上传失败的文件数
			if (stats.uploadFailNum) {
				uploaderNum = 0;
				alert('附件上传失败，请到编辑窗口重新上传!');
                $(".webuploadProgress").css("display","none");
			}
			if(uploaderNum == Object.keys(updataLoadarrayObj).length){
				uploaderNum = 0;
               $(".webuploadProgress").css("display","none");
			}
		});
    }
 	$.fn.saveUploadAndForm = function (form) {
 		//js array的key不为数字时获取长度
         var uploadrFile = updataLoadarrayObj[$(this).attr("id")];
         uploadrFile.upload();
         // 所有文件上传结束时
         uploadrFile.on('uploadFinished', function() {
 			var stats = uploadrFile.getStats();
 			uploaderNum++;
 			// 上传失败提示重新上传 uploadFailNum 上传失败的文件数
 			if (stats.uploadFailNum) {
 				uploaderNum = 0;
 				alert('附件上传失败，请到编辑窗口重新上传!');
                 $(".webuploadProgress").css("display","none");
                 saveForm(form);
 			}
 			if(uploaderNum == Object.keys(updataLoadarrayObj).length){
 				uploaderNum = 0;
                $(".webuploadProgress").css("display","none");
                saveForm(form);
 			}
 		});
     }
})(jQuery, window);