<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>现场检查方案管理</title>
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
	<form:form id="inputForm" modelAttribute="busProgramme" action="${ctx}/bus/busProgramme/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
		<table id="lowCaseSubmissionTable" border="1" style="width: 80%; height: 500px">
			<tr>
				<td colspan="4" style="text-align: center">安全生产行政执法文书</td>
			</tr>
			<tr>
				<td colspan="4" style="text-align: center;border-bottom:2pt double #000000"></td>
			</tr>
			<tr>
				<td colspan="4" style="text-align: center">现场检查方案</td>
			</tr>
			<tr>
				<td colspan="4" style="text-align: center">（松）安监查〔2019〕危化001号</td>
			</tr>
			<tr>
				<td style="text-align: left">被检查单位</td>
				<td colspan="3" style="text-align: center">
					<div class="control-group">
						<label class="control-label">被检查对象名称：</label>
						<div class="controls">
							<form:input path="companyName" htmlEscape="false" maxlength="200" class="input-xlarge "/>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td style="text-align: left">地址</td>
				<td colspan="3" style="text-align: center">输入</td>
			</tr>
			<tr>
				<td style="text-align: left">联系人</td>
				<td style="text-align: center">输入</td>
				<td style="text-align: center">所属行业</td>
				<td style="text-align: center">
					<form:select path="industry" class="input-medium">
						<form:option value="" label="请选择"/>
						<form:options items="${fns:getDictList('industry')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</td>
			</tr>
			<tr>
				<td style="text-align: left">检查时间</td>
				<td colspan="3" style="text-align: center">
					<input name="checkdate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
						   onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
				</td>
			</tr>
			<tr>
				<td style="text-align: left">行政执法人员</td>
				<td colspan="3" style="text-align: center">
					<form:checkbox path="lawyer" items="${fns:getDictList('industry')}"
							itemLabel="label" itemValue="value" htmlEscape="false" />
					<form:input path="lawyer" htmlEscape="false" class="input-xlarge" type="hidden" />
				</td>
			</tr>
			<tr>
				<td style="text-align: left">检查内容</td>
				<td colspan="3" style="text-align: center">多选</td>
			</tr>
			<tr>
				<td style="text-align: left">检查方式</td>
				<td colspan="3" style="text-align: center">单选</td>
			</tr>
			<tr>
				<td style="text-align: left">审核意见</td>
				<td style="text-align: center">输入</td>
				<td style="text-align: center">审批意见</td>
				<td style="text-align: center">输入</td>
			</tr>
			<tr>
				<td style="text-align: left">备注</td>
				<td colspan="3" style="text-align: center">输入</td>
			</tr>
			<tr>
				<td style="text-align: left">招聘专家</td>
				<td colspan="3" style="text-align: center">多选</td>
			</tr>
		</table>



		<div class="control-group">
			<label class="control-label">计划表id：</label>
			<div class="controls">
				<form:input path="planId" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">任务表id：</label>
			<div class="controls">
				<form:input path="taskId" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">指标表id：</label>
			<div class="controls">
				<form:input path="itemId" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">方案名称：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="200" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">方案编号：</label>
			<div class="controls">
				<form:input path="number" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">地址：</label>
			<div class="controls">
				<form:input path="address" htmlEscape="false" maxlength="200" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">联系人：</label>
			<div class="controls">
				<form:input path="linkman" htmlEscape="false" maxlength="20" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">检查时间：</label>
			<div class="controls">
				<input name="checkdate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${busProgramme.checkdate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">检查内容：</label>
			<div class="controls">
				<form:input path="contents" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">检查方式：</label>
			<div class="controls">
				<form:input path="checkType" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">检查类型（0:日常 1：专项）：</label>
			<div class="controls">
				<form:input path="checkModel" htmlEscape="false" maxlength="1" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">审核意见：</label>
			<div class="controls">
				<form:input path="examineOpinion" htmlEscape="false" maxlength="200" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">审批意见：</label>
			<div class="controls">
				<form:input path="approvalOpinion" htmlEscape="false" maxlength="200" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">拟聘专家：</label>
			<div class="controls">
				<form:input path="experts" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">状态值（0：未提交 10：未审核 20：未审批 30 ：通过）：</label>
			<div class="controls">
				<form:input path="state" htmlEscape="false" maxlength="2" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="200" class="input-xxlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="bus:busProgramme:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>