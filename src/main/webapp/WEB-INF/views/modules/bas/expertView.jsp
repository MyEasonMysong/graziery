<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title><!-- modules/bas/expertView.jsp --></title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	$(document).ready(function() {
	});
</script>
</head>
<body>
	<table class="tableStyle">
		<tr>
			<td width="20%" class="viewModeEven">姓名：</td>
			<td>${expert.name }</td>
		</tr>
		<tr>
			<td class="viewModeEven">专业：</td>
			<td>${expert.specialty }</td>
		</tr>
		<tr>
			<td class="viewModeEven">职称：</td>
			<td>${expert.professionalTitle }</td>
		</tr>
		<tr>
			<td class="viewModeEven">联系电话：</td>
			<td>${expert.tel }</td>
		</tr>
		<tr>
			<td class="viewModeEven">工作单位：</td>
			<td>${expert.company }</td>
		</tr>
		<tr>
			<td class="viewModeEven">备注：</td>
			<td><pre>${expert.remarks }</pre></td>
		</tr>
	</table>
</body>
</html>