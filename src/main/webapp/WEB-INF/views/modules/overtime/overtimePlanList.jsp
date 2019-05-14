<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>加班计划申请管理</title>
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
		<li class="active"><a href="${ctx}/overtime/overtimePlan/">加班计划申请列表</a></li>
		<shiro:hasPermission name="overtime:overtimePlan:edit"><li><a href="${ctx}/overtime/overtimePlan/form">加班计划申请添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="overtimePlan" action="${ctx}/overtime/overtimePlan/" method="post" class="breadcrumb form-search">
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
				<th>apply_user_id</th>
				<th>apply_user_name</th>
				<th>start_time</th>
				<th>end_time</th>
				<th>duration</th>
				<th>over_time_type</th>
				<th>project_name</th>
				<th>project_code</th>
				<th>acceptor</th>
				<th>job_content</th>
				<th>approval_status</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="overtime:overtimePlan:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="overtimePlan">
			<tr>
				<td><a href="${ctx}/overtime/overtimePlan/form?id=${overtimePlan.id}">
					${overtimePlan.applyUserId}
				</a></td>
				<td>
					${overtimePlan.applyUserName}
				</td>
				<td>
					<fmt:formatDate value="${overtimePlan.startTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${overtimePlan.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${overtimePlan.duration}
				</td>
				<td>
					${overtimePlan.overTimeType}
				</td>
				<td>
					${overtimePlan.projectName}
				</td>
				<td>
					${overtimePlan.projectCode}
				</td>
				<td>
					${overtimePlan.acceptor}
				</td>
				<td>
					${overtimePlan.jobContent}
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
				<shiro:hasPermission name="overtime:overtimePlan:edit"><td>
    				<a href="${ctx}/overtime/overtimePlan/form?id=${overtimePlan.id}">修改</a>
					<a href="${ctx}/overtime/overtimePlan/delete?id=${overtimePlan.id}" onclick="return confirmx('确认要删除该加班计划申请吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>