<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>现场检查方案管理</title>
	<meta name="decorator" content="default"/>
	<script src="${ctxScript}/bus/programme.js" type="text/javascript"></script>
	<script type="text/javascript">
		$(document).ready(function() {});
		function page(n,s) {
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/bus/busProgramme/">日常检查</a></li>
		<li><a href="${ctx}/bus/busProgramme/form">现场检查方案添加</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="busProgramme" action="${ctx}/bus/busProgramme/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>时间：</label>
				<form:select path="planYear" class="input-medium">
					<form:option value="" label="请选择"/>
					<form:options items="${fns:getDictList('planYear')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>检查对象：</label>
				<form:input path="companyName" htmlEscape="false" maxlength="200" class="input-medium"/>
			</li>
			<li><label>状态：</label>
				<form:select path="state" class="input-medium">
					<form:option value="" label="请选择"/>
					<form:options items="${fns:getDictList('state')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<%--<li class="btns"><input class="btn btn-primary" type="button" value="重置" onclick="resetSubmit('searchForm')"/></li>--%>
			<shiro:hasPermission name="bus:busProgramme:edit">
				<li class="btns"><input class="btn btn-primary" type="button" value="新增" onclick="programmeAdd()" /></li>
			</shiro:hasPermission>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>时间</th>
				<th>序号</th>
				<th>检查对象</th>
				<th>职责分工</th>
				<th>检查方案状态</th>
				<th>检查状态</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="busProgramme" varStatus="index">
				<tr>
					<td>
						<c:out value="${busProgramme.planYear}月" />
					</td>
					<td>
						${(index.index+1)+(page.pageNo-1)*page.pageSize}
					</td>
					<td>
						${busProgramme.companyName}
					</td>
					<td>
						${busProgramme.lawyer}
					</td>
					<td>
						${fns:getDictLabel(busProgramme.state, 'state', '')}
					</td>
					<td>
						${fns:getDictLabel(busProgramme.checkState, 'checkState', '')}
					</td>
					<c:choose>
						<c:when test="${busProgramme.state eq 0}">
							<shiro:hasPermission name="bus:busProgramme:edit">
								<td>
									<a href="${ctx}/bus/busProgramme/form?id=${busProgramme.id}">新建</a>
								</td>
							</shiro:hasPermission>
						</c:when>
						<c:when test="${busProgramme.state eq 1 or busProgramme.state eq 2}">
							<shiro:hasPermission name="bus:busProgramme:edit">
								<td>
									<a href="${ctx}/bus/busProgramme/form?id=${busProgramme.id}">修改</a>
								</td>
							</shiro:hasPermission>
						</c:when>
						<c:otherwise>
							<shiro:hasPermission name="bus:busProgramme:view">
								<td>
									<a href="${ctx}/bus/busProgramme/form?id=${busProgramme.id}">查看</a>
								</td>
							</shiro:hasPermission>
						</c:otherwise>
					</c:choose>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>