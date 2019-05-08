<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><!-- modules/bas/checkSystemList.jsp --></title>
	<meta name="decorator" content="default"/>
	<script src="${ctxScript}/bas/checkSystem.js" type="text/javascript"></script>
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
		<li class="active"><a href="${ctx}/bas/checkSystem/">检查体系表列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="checkSystem" action="${ctx}/bas/checkSystem/" method="post" class="breadcrumb form-search">
		<form:hidden path="checkTypeCode"/>
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label style="width: 95px;">指标体系名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>适用行业：</label>
				<form:select path="industryTypeCode" class="input-medium">
					<form:option value="" label="请选择"/>
					<form:options items="${fns:getDictList('industryType')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>编辑人：</label>
				<form:input path="updateBy" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<shiro:hasPermission name="bas:checkSystem:edit"><li class="btns"><input class="btn btn-primary" type="button" value="新增" onclick="checkSystemAdd()"/></li></shiro:hasPermission>
			<li class="clearfix"></li>
		</ul>
	</form:form>

	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>序号</th>
				<th>指标体系名称</th>
				<th>适用行业</th>
				<th>指标项数量</th>
				<th>编辑人</th>
				<th>更新日期</th>
				<shiro:hasPermission name="bas:checkSystem:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="checkSystem" varStatus="index">
			<tr>
				<td>
					${(index.index+1)+(page.pageNo-1)*page.pageSize}
				</td>
				<td>
					${checkSystem.name}
				</td>
				<td>
					${fns:getDictLabel(checkSystem.industryTypeCode, 'industryType', '')}
				</td>
				<td>
					${checkSystem.itemAmount}项，${checkSystem.itemSubAmount}个检查项目
				</td>
				<td>
					${checkSystem.updateBy.name}
				</td>
				<td>
					<fmt:formatDate value="${checkSystem.updateDate}" pattern="yyyy-MM-dd"/>
				</td>
				<shiro:hasPermission name="bas:checkSystem:edit"><td>
					<a href="javascript:;" onclick="checkSystemView('${checkSystem.id}')">查看</a>
					<a href="javascript:;" onclick="checkSystemEdit('${checkSystem.id}')">修改</a>
					<a href="${ctx}/bas/checkSystem/delete?id=${checkSystem.id}" onclick="return confirmx('确认要删除该企业吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>