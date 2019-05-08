<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>检查计划子表管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/bus/busPlanDetail/">检查计划子表列表</a></li>
		<shiro:hasPermission name="bus:busPlanDetail:edit"><li><a href="${ctx}/bus/busPlanDetail/form">检查计划子表添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="busPlanDetail" action="${ctx}/bus/busPlanDetail/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>操作时间</th>
				<th>备注</th>
				<shiro:hasPermission name="bus:busPlanDetail:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="busPlanDetail">
			<tr>
				<td><a href="${ctx}/bus/busPlanDetail/form?id=${busPlanDetail.id}">
					<fmt:formatDate value="${busPlanDetail.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</a></td>
				<td>
					${busPlanDetail.remarks}
				</td>
				<shiro:hasPermission name="bus:busPlanDetail:edit"><td>
    				<a href="${ctx}/bus/busPlanDetail/form?id=${busPlanDetail.id}">修改</a>
					<a href="${ctx}/bus/busPlanDetail/delete?id=${busPlanDetail.id}" onclick="return confirmx('确认要删除该检查计划子表吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>