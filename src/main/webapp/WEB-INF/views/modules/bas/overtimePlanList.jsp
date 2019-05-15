<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>加班申请管理</title>
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
		<li class="active"><a href="${ctx}/bas/overtimePlan/">加班申请列表</a></li>
		<shiro:hasPermission name="bas:overtimePlan:edit"><li><a href="${ctx}/bas/overtimePlan/form">加班申请添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="overtimePlan" action="${ctx}/bas/overtimePlan/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>申请人：</label>
				<form:input path="applyBy" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>加班项目：</label>
				<form:input path="project" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>申请人</th>
				<th>加班时长</th>
				<th>加班项目</th>
				<th>申请状态</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="bas:overtimePlan:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="overtimePlan">
			<tr>
				<td><a href="${ctx}/bas/overtimePlan/form?id=${overtimePlan.id}">
					${overtimePlan.applyBy}
				</a></td>
				<td>
					${overtimePlan.duration}
				</td>
				<td>
					${overtimePlan.project}
				</td>
				<td>
					${overtimePlan.approvalStatus}
				</td>
				<td>
					<fmt:formatDate value="${overtimePlan.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${overtimePlan.remarks}
				</td>
				<shiro:hasPermission name="bas:overtimePlan:edit"><td>
    				<a href="${ctx}/bas/overtimePlan/form?id=${overtimePlan.id}">修改</a>
					<a href="${ctx}/bas/overtimePlan/delete?id=${overtimePlan.id}" onclick="return confirmx('确认要删除该加班申请吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>