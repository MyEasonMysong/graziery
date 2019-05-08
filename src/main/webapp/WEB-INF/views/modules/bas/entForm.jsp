<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><!-- modules/bas/entForm.jsp --></title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					/* loading('正在提交，请稍等...');
					form.submit(); */
					$.ajax({
                        url: "${ctx}/bas/ent/save",
                        type: "post",
                        data: $(form).serialize(),
                        success: function (msg) {
                            /* 1：对号 */
                            top.$.jBox.tip("保存成功！","info");
                            parent.location.reload();
                        },
                        error: function (e) {
                            /* 5：不高兴 */
                            layer.msg(e, {icon: 5});
                        }
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
	<form:form id="inputForm" modelAttribute="ent" action="${ctx}/bas/ent/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<table class="tableStyle">
			<tr>
				<td width="20%" class="viewModeEven"><font color="red">*</font>企业名称：</td>
				<td width="30%"><form:input path="name" htmlEscape="false" maxlength="50" class="required input-xlarge"/></td>
				<td width="20%" class="viewModeEven"><font color="red">*</font>所属行业：</td>
				<td width="30%">
					<form:select path="industryTypeCode" class="required input-xlarge">
						<form:option value="" label="请选择"/>
						<form:options items="${fns:getDictList('industryType')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</td>
			</tr>
			<tr>
				<td class="viewModeEven"><font color="red">*</font>企业统一信用代码：</td>
				<td colspan="3"><form:input path="creditCode" htmlEscape="false" maxlength="50"  class="required input-xlarge"/></td>
			</tr>
			<tr>
				<td class="viewModeEven"><font color="red">*</font>属地：</td>
				<td >
					<form:select path="areaCode" class="required input-xlarge">
						<form:option value="" label="请选择"/>
						<form:options items="${areaList}" itemLabel="name" itemValue="code" htmlEscape="false"/>
					</form:select>
				</td>
				<td class="viewModeEven">邮政编码：</td>
				<td ><form:input path="postCode" htmlEscape="false" maxlength="50" class="input-xlarge" /></td>
			</tr>
			<tr>
				<td class="viewModeEven">地址：</td>
				<td colspan="3"><form:input path="address" htmlEscape="false" maxlength="50" class="input-xlarge" /></td>
			</tr>
			<tr>
				<td class="viewModeEven"><font color="red">*</font>法定代表人：</td>
				<td colspan="3"><form:input path="legalPersonName" htmlEscape="false" maxlength="50"  class="required input-xlarge"/></td>
			</tr>
			<tr>
				<td class="viewModeEven">职务：</td>
				<td ><form:input path="legalPersonDuty" htmlEscape="false" maxlength="50" class="input-xlarge" /></td>
				<td class="viewModeEven"><font color="red">*</font>联系电话：</td>
				<td ><form:input path="legalPersonTel" htmlEscape="false" maxlength="50"  class="required input-xlarge"/></td>
			</tr>
			<tr>
				<td class="viewModeEven"><font color="red">*</font>负责人：</td>
				<td colspan="3"><form:input path="principalPersonName" htmlEscape="false" maxlength="50"  class="required input-xlarge"/></td>
			</tr>
			<tr>
				<td class="viewModeEven">职务：</td>
				<td ><form:input path="principalPersonDuty" htmlEscape="false" maxlength="50" class="input-xlarge" /></td>
				<td class="viewModeEven"><font color="red">*</font>联系电话：</td>
				<td ><form:input path="principalPersonTel" htmlEscape="false" maxlength="50"  class="required input-xlarge"/></td>
			</tr>
		</table>
	</form:form>
</body>
</html>