<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>检查计划管理</title>
	<meta name="decorator" content="default"/>
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
					<form:input path="checkNum" htmlEscape="false" maxlength="8" class="required input-xlarge "/>
				</td>
			</tr>
		</table>
	</form:form>
</body>
</html>