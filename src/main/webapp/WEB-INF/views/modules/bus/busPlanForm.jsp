<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>检查计划管理</title>
	<meta name="decorator" content="default"/>
	<script src="${ctxScript}/bus/plan.js" type="text/javascript"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
                    top.$.jBox.tip("保存成功！","info");
                    parent.location.reload();
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
	<form:form id="inputForm" modelAttribute="busPlan" action="${ctx}/bus/busPlan/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
		<div style="text-align: center;margin-top: 20px;font-size:large">${officeName}监督检查计划</div>
		<div style="margin-top: 20px;font-size:large">总体情况</div>
		<table class="tableStyle">
			<tr>
				<td width="20%" style="text-align: right"><font color="red">*</font>年度：</td>
				<td width="30%">
					<form:select path="planYear" class="required input-medium">
						<form:option value="" label="请选择"/>
						<form:options items="${fns:getDictList('year')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</td>
				<td width="20%" style="text-align: right"><font color="red">*</font>计划检查企业数量：</td>
				<td width="30%">
					<form:input path="checkNum" htmlEscape="false" maxlength="8" class="required number input-xlarge "/>
				</td>
			</tr>
		</table>
		<div style="margin-top: 20px;font-size:large">
			详细计划
			<input id="selectEnt" class="btn btn-primary" type="button" value="选择检查对象" onclick="selectEnts();"/>&nbsp;&nbsp;
			<input id="batchDelete" class="btn btn-primary" type="button" value="批量删除"/>
		</div>
		<table class="tableStyle" id="addEnt">
			<tr>
				<th>序号</th>
				<th><input type="checkbox" name="all"></th>
				<th>月份</th>
				<th>检查对象</th>
				<th>属地</th>
				<th>所属行业</th>
				<th>检查主要事项</th>
				<th>检查方式</th>
				<th>职责分工</th>
				<th>操作</th>
			</tr>
			<tbody id="addEntTbody">
			</tbody>
		</table>
	</form:form>
</body>
</html>