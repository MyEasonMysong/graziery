<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>专项任务管理</title>
	<meta name="decorator" content="default"/>
	<script src="${ctxScript}/bus/task.js" type="text/javascript"></script>
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
		<li class="active"><a href="${ctx}/bus/busTask/">专项任务列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="busTask" action="${ctx}/bus/busTask/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>年度：</label>
				<form:select path="taskYear" class="input-medium">
					<form:option value="" label="请选择"/>
					<form:options items="${fns:getDictList('year')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>任务名称：</label>
				<form:input path="taskName" htmlEscape="false"/>
			</li>
			<li><label>任务级别：</label>
				<form:select path="taskLevel" class="input-medium">
					<form:option value="" label="请选择"/>
					<form:options items="${fns:getDictList('taskLevel')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label style="width: auto">任务开始时间：</label>
				<input name="beginDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</li>
			<li><label style="width: auto">任务结束时间：</label>
				<input name="endDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<shiro:hasPermission name="bus:busTask:edit">
				<li class="btns"><input class="btn btn-primary" type="button" value="新增" onclick="taskAdd()" /></li>
			</shiro:hasPermission>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>序号</th>
				<th>年度</th>
				<th>任务名称</th>
				<th>任务开始时间</th>
				<th>任务结束时间</th>
				<th>任务级别</th>
				<th>已检查企业数量</th>
				<shiro:hasPermission name="bus:busTask:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="busTask" varStatus="index">
			<tr>
				<td>
					${(index.index+1)+(page.pageNo-1)*page.pageSize}
				</td>
				<td>
					${busTask.taskYear}
				</td>
				<td>
					${busTask.taskName}
				</td>
				<td>
					<fmt:formatDate value="${busTask.beginDate}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					<fmt:formatDate value="${busTask.endDate}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					${fns:getDictLabel(busTask.taskLevel, 'taskLevel', '')}
				</td>
				<td>
					0家
				</td>
				<shiro:hasPermission name="bus:busTask:edit"><td>
                    <a href="javascript:;" onclick="taskEdit('${busTask.id}')">修改</a>
					<a href="${ctx}/bus/busTask/delete?id=${busTask.id}" onclick="return confirmx('确认要删除该专项任务吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>