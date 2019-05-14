<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>加班计划申请管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
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
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/overtime/overtimePlan/">加班计划申请列表</a></li>
		<li class="active"><a href="${ctx}/overtime/overtimePlan/form?id=${overtimePlan.id}">加班计划申请<shiro:hasPermission name="overtime:overtimePlan:edit">${not empty overtimePlan.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="overtime:overtimePlan:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="overtimePlan" action="${ctx}/overtime/overtimePlan/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">apply_user_id：</label>
			<div class="controls">
				<form:input path="applyUserId" htmlEscape="false" maxlength="4000" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">apply_user_name：</label>
			<div class="controls">
				<form:input path="applyUserName" htmlEscape="false" maxlength="4000" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">start_time：</label>
			<div class="controls">
				<input name="startTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${overtimePlan.startTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">end_time：</label>
			<div class="controls">
				<input name="endTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${overtimePlan.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">duration：</label>
			<div class="controls">
				<form:input path="duration" htmlEscape="false" maxlength="4000" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">over_time_type：</label>
			<div class="controls">
				<form:input path="overTimeType" htmlEscape="false" maxlength="10" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">project_name：</label>
			<div class="controls">
				<form:input path="projectName" htmlEscape="false" maxlength="10" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">project_code：</label>
			<div class="controls">
				<form:input path="projectCode" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">acceptor：</label>
			<div class="controls">
				<form:input path="acceptor" htmlEscape="false" maxlength="10" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">job_content：</label>
			<div class="controls">
				<form:input path="jobContent" htmlEscape="false" maxlength="500" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">approval_status：</label>
			<div class="controls">
				<form:input path="approvalStatus" htmlEscape="false" maxlength="10" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="overtime:overtimePlan:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>