<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><!-- modules/bas/expertForm.jsp --></title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					 $.ajax({
                        url: "${ctx}/bas/expert/save",
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
	<form:form id="inputForm" modelAttribute="expert" action="${ctx}/bas/expert/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<table class="tableStyle">
			<tr>
				<td width="20%" class="viewModeEven"><font color="red">*</font>姓名：</td>
				<td><form:input path="name" htmlEscape="false" maxlength="50" style="width:80%" class="required"/></td>
			</tr>
			<tr>
				<td class="viewModeEven"><font color="red">*</font>专业：</td>
				<td><form:input path="specialty" htmlEscape="false" maxlength="50" style="width:80%" class="required"/></td>
			</tr>
			<tr>
				<td class="viewModeEven"><font color="red">*</font>职称：</td>
				<td><form:input path="professionalTitle" htmlEscape="false" maxlength="50" style="width:80%" class="required"/></td>
			</tr>
			<tr>
				<td class="viewModeEven"><font color="red">*</font>联系电话：</td>
				<td><form:input path="tel" htmlEscape="false" maxlength="50" style="width:80%" class="required"/></td>
			</tr>
			<tr>
				<td class="viewModeEven"><font color="red">*</font>工作单位：</td>
				<td><form:input path="company" htmlEscape="false" maxlength="50" style="width:80%" class="required"/></td>
			</tr>
			<tr>
				<td class="viewModeEven"><font color="red">*</font>备注：</td>
				<td><form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" style="width:80%" class="required"/></td>
			</tr>
		</table>
	</form:form>
</body>
</html>