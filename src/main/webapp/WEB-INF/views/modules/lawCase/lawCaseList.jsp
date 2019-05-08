<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>111111检查计划管理</title>
	<meta name="decorator" content="default"/>
	<script src="${ctxScript}/bus/plan.js" type="text/javascript"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/bus/busPlan/">检查计划列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="busPlan" action="${ctx}/bus/busPlan/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>年度：</label>
				<form:select path="planYear" class="input-medium">
					<form:option value="" label="请选择"/>
					<form:options items="${fns:getDictList('year')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<shiro:hasPermission name="bus:busPlan:edit">
				<li class="btns"><input class="btn btn-primary" type="button" value="新增" onclick="planAdd()" /></li>
			</shiro:hasPermission>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>序号</th>
				<th>年度</th>
				<th>检查进度</th>
				<th>状态</th>
				<th>上次编辑时间</th>
				<shiro:hasPermission name="bus:busPlan:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="busPlan" varStatus="index">
			<tr>
				<td>
					${(index.index+1)+(page.pageNo-1)*page.pageSize}
				</td>
				<td>
					${busPlan.planYear}
				</td>
				<td>
					${busPlan.noCheckNum}家/${busPlan.checkNum}家
				</td>
				<td>
					<c:if test="${busPlan.planStatus==0}">
						已结束
					</c:if>
					<c:if test="${busPlan.planStatus==1}">
						进行中
					</c:if>
				</td>
				<td>
					<fmt:formatDate value="${busPlan.updateDate}" pattern="yyyy-MM-dd"/>
				</td>
				<shiro:hasPermission name="bus:busPlan:edit"><td>
    				<a href="${ctx}/bus/busPlan/form?id=${busPlan.id}">修改</a>
					<a href="${ctx}/bus/busPlan/delete?id=${busPlan.id}" onclick="return confirmx('确认要删除该检查计划吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>