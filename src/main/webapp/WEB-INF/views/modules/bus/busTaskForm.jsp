<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>专项任务管理</title>
	<meta name="decorator" content="default"/>
	<link type="text/css" rel="stylesheet" href="${ctxStatic}/webuploader/css/webuploader.css" />
	<script type="text/javascript" src="${ctxStatic}/webuploader/js/webuploader.js"></script>
	<script type="text/javascript" src="${ctxStatic}/webuploader/webuploadTools.js"></script>
	<script src="${ctxScript}/bus/task.js" type="text/javascript"></script>
	<script type="text/javascript" src="${ctxStatic}/uuid.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
            var lid = $("#id").val();
            if (lid == "" || lid == null) {
                $("#id").val(uuid());
            }
            $("#picker").initFileWebUpload("bus_task", $("#id").val(), "file", 5, "imagesAndFiles");
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
                    $("#picker").saveUpload(function(){
                        form.submit();
                        top.$.jBox.tip("保存成功！","info");
                        parent.location.reload();
					});
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
	</script>
</head>
<body>
	<form:form id="inputForm" modelAttribute="busTask" action="${ctx}/bus/busTask/save" method="post" class="form-horizontal">
		<form:hidden path="checkSystemId" id="checkSystemId"/>
		<form:hidden path="id" id="id"/>
		<sys:message content="${message}"/>
		<table class="tableStyle">
			<tr>
				<td width="20%" style="text-align: right"><font color="red">*</font>任务名称：</td>
				<td width="80%" colspan="3">
					<form:input path="taskName" htmlEscape="false" maxlength="50" class="required input-xlarge "/>
				</td>
			</tr>
			<tr>
				<td width="20%" style="text-align: right"><font color="red">*</font>年度：</td>
				<td width="30%">
					<form:select path="taskYear" class="required input-medium">
						<form:option value="" label="请选择"/>
						<form:options items="${fns:getDictList('year')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</td>
				<td width="20%" style="text-align: right"><font color="red">*</font>专项任务级别：</td>
				<td width="30%">
					<form:select path="taskLevel" class="required input-medium">
						<form:option value="" label="请选择"/>
						<form:options items="${fns:getDictList('taskLevel')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</td>
			</tr>
			<tr>
				<td style="text-align: right"><font color="red">*</font>任务开始时间：</td>
				<td>
					<input name="beginDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
						   value="<fmt:formatDate value="${busTask.beginDate}" pattern="yyyy-MM-dd"/>"
						   onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
				</td>
				<td style="text-align: right"><font color="red">*</font>任务结束时间：</td>
				<td>
					<input name="endDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
						   value="<fmt:formatDate value="${busTask.endDate}" pattern="yyyy-MM-dd"/>"
						   onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
				</td>
			</tr>
			<tr>
				<td style="text-align: right"><font color="red">*</font>负责科室：</td>
				<td>
					<sys:treeselect id="chargeOfficeId" name="chargeOfficeId" value="${busTask.chargeOfficeId}" labelName="charge.name" labelValue="${busTask.charge.name}"
									title="部门" url="/sys/office/treeData?type=2" cssClass="required" allowClear="true" notAllowSelectParent="true"/>
				</td>
				<td style="text-align: right">参与科室：</td>
				<td>
					<sys:treeselect id="partakeOfficeId" name="partakeOfficeId" value="${busTask.partakeOfficeId}" labelName="partake.name" labelValue="${busTask.partake.name}"
									title="部门" url="/sys/office/treeData?type=2" cssClass="" allowClear="true" notAllowSelectParent="true" />
				</td>
			</tr>
			<tr>
				<td style="text-align: right">计划企业数量：</td>
				<td>
					<form:input path="checkNum" htmlEscape="false" maxlength="8" class="number input-xlarge "/>
				</td>
				<td style="text-align: right"><font color="red">*</font>检查内容：</td>
				<td>
					<span id="systemName">${busTask.checkSystem.name}</span>
					<input id="selectEnt" class="btn btn-primary" type="button" value="选择" onclick="selectCheckSystem();"/>&nbsp;&nbsp;
				</td>
			</tr>
			<tr>
				<td style="text-align: right">任务文件</td>
				<td colspan="3">
					<div id="picker"></div>
				</td>
			</tr>
		</table>
	</form:form>
</body>
</html>