<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title><!-- modules/bas/expertList.jsp --></title>
<meta name="decorator" content="default" />
<script src="${ctxScript}/bas/expert.js" type="text/javascript"></script>
<script type="text/javascript">
	$(document).ready(function() {

	});
	function page(n, s) {
		$("#pageNo").val(n);
		$("#pageSize").val(s);
		$("#searchForm").submit();
		return false;
	}
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="javascript:;">专家库列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="expert" action="${ctx}/bas/expert/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}" />
		<ul class="ul-form">
			<li><label>姓名：</label> <form:input path="name" htmlEscape="false" maxlength="50" class="input-medium" /></li>
			<li><label>职称：</label> <form:input path="professionalTitle" htmlEscape="false" maxlength="50" class="input-medium" /></li>
			<li class="btns"><input class="btn btn-primary" type="submit" value="查询" /></li>
			<shiro:hasPermission name="bas:expert:edit">
				<li class="btns"><input class="btn btn-primary" type="button" value="新增" onclick="expertAdd()" /></li>
			</shiro:hasPermission>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}" />
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th width="5%">序号</th>
				<th width="15%">姓名</th>
				<th >专业</th>
				<th >职称</th>
				<th width="15%">联系电话</th>
				<th width="20%">工作单位</th>
				<shiro:hasPermission name="bas:expert:edit">
					<th width="20%">操作</th>
				</shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="expert" varStatus="index">
				<tr>
					<td>
						${(index.index+1)+(page.pageNo-1)*page.pageSize}
					</td>
					<td>${expert.name}</td>
					<td>${expert.specialty}</td>
					<td>${expert.professionalTitle}</td>
					<td>${expert.tel}</td>
					<td>${expert.company}</td>
					<shiro:hasPermission name="bas:expert:edit">
						<td>
							<a href="javascript:;" onclick="expertView('${expert.id}')">查看</a>
							<a href="javascript:;" onclick="expertEdit('${expert.id}')">修改</a>
							<a href="${ctx}/bas/expert/delete?id=${expert.id}" onclick="return confirmx('确认要删除该专家吗？', this.href)">删除</a>
						</td>
					</shiro:hasPermission>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>