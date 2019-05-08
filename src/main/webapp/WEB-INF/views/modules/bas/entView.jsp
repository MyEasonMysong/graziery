<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title><!-- modules/bas/entView.jsp --></title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	$(document).ready(function() {

	});
</script>
</head>
<body>
	<table class="tableStyle">
		<tr>
			<td width="20%" class="viewModeEven">企业名称：</td>
			<td width="30%">${ent.name }</td>
			<td width="20%" class="viewModeEven">所属行业：</td>
			<td width="30%">${fns:getDictLabel(ent.industryTypeCode, 'industryType', '')}</td>
		</tr>
		<tr>
			<td class="viewModeEven">企业统一信用代码：</td>
			<td colspan="3">${ent.creditCode }</td>
		</tr>
		<tr>
			<td class="viewModeEven">属地：</td>
			<td>${ent.area.name}</td>
			<td class="viewModeEven">邮政编码：</td>
			<td>${ent.postCode }</td>
		</tr>
		<tr>
			<td class="viewModeEven">地址：</td>
			<td colspan="3">${ent.address }</td>
		</tr>
		<tr>
			<td class="viewModeEven">法定代表人：</td>
			<td colspan="3">${ent.legalPersonName }</td>
		</tr>
		<tr>
			<td class="viewModeEven">职务：</td>
			<td>${ent.legalPersonDuty }</td>
			<td class="viewModeEven">联系电话：</td>
			<td>${ent.legalPersonTel }</td>
		</tr>
		<tr>
			<td class="viewModeEven">负责人：</td>
			<td colspan="3">${ent.principalPersonName }</td>
		</tr>
		<tr>
			<td class="viewModeEven">职务：</td>
			<td>${ent.principalPersonDuty }</td>
			<td class="viewModeEven">联系电话：</td>
			<td>${ent.principalPersonTel }</td>
		</tr>
	</table>
</body>
</html>