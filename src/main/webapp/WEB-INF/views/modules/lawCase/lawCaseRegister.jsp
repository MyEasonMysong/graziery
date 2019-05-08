<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>立案登记</title>
	<meta name="decorator" content="default"/>
	<%--<script src="${ctxScript}/bus/plan.js" type="text/javascript"></script>--%>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
	</script>
</head>
<body>
	<div style="width: 100%; text-align: center">
		<form:form id="searchForm" modelAttribute="lawCase" action="${ctx}/lawCase/submission" method="post" class="breadcrumb form-search" style="text-align: center">
			<table id="lowCaseSubmissionTable" style="width: 500px">
				<tr>
					<td colspan="4" style="text-align: center">安全生产行政执法文书</td>
				</tr>
				<tr>
					<td colspan="4" style="text-align: center;border-bottom:2pt double #000000"></td>
				</tr>
				<tr>
					<td colspan="4" style="text-align: center">立案审批表</td>
				</tr>
				<tr>
					<td colspan="4" style="text-align: center">（松）安监立〔2019〕第（危化—1号）</td>
				</tr>
				<tr>
					<td style="text-align: left">案由</td>
					<td colspan="3" style="text-align: center">下拉选</td>
				</tr>
				<tr>
					<td style="text-align: left">案件来源</td>
					<td style="text-align: center">下拉选</td>
					<td style="text-align: center">时间</td>
					<td style="text-align: center">自己选</td>
				</tr>
				<tr>
					<td style="text-align: left">当事人</td>
					<td style="text-align: center;border-bottom:1px solid #000000">有两种方式，自选，相关信息回显，或者全部手填</td>
					<td style="text-align: center">电话</td>
					<td style="text-align: center;border-bottom:1px solid #000000">回显或自填</td>
				</tr>
				<tr>
					<td style="text-align: left">法定代表人/负责人</td>
					<td colspan="3" style="text-align: center;border-bottom:1px solid #000000">回显或自填</td>
				</tr>
				<tr>
					<td style="text-align: left">当事人地址</td>
					<td style="text-align: center;border-bottom:1px solid #000000">回显或自填</td>
					<td style="text-align: center">邮政编码</td>
					<td style="text-align: center;border-bottom:1px solid #000000">回显或自填</td>
				</tr>
				<tr>
					<td colspan="4" style="border:#000000 solid 1px;">1</td>
				</tr>
				<tr>
					<td colspan="4" style="border:#000000 solid 1px;">2</td>
				</tr>
				<tr>
					<td colspan="2" style="border:#000000 solid 1px;">3</td>
					<td colspan="2" style="border:#000000 solid 1px;">4</td>
				</tr>
			</table>
		</form:form>
	</div>
</body>
</html>