<%@page import="java.util.Date" contentType="text/html;charset=UTF-8" %>
<%@include file="/WEB-INF/views/include/taglib.jsp"%>
<%Date date = new Date(); %>
<html>
<head>
	<title><!-- modules/bas/itemList.jsp --></title>
	<meta name="decorator" content="default"/>
	<script src="${ctxScript}/bas/item.js?<%=date.getTime()%>" type="text/javascript"></script>
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
		<li class="active"><a href="javascript:;">指标项列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="item" action="${ctx}/bas/item/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>指标名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>指标类型：</label>
				<form:select path="itemTypeCode" class="input-medium">
					<form:option value="" label="请选择"/>
					<form:options items="${fns:getDictList('itemType')}" itemLabel="label"  itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="btns"><input class="btn btn-primary" type="button" value="重置" onclick="itemRestForm('searchForm' , 'btnSubmit');"/></li>
			<shiro:hasPermission name="bas:item:edit">
				<li class="btns">
					<input class="btn btn-primary" type="button" value="新增" onclick="itemForm('')"/>
				</li>
			</shiro:hasPermission>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th width="5%">序号</th>
				<th width="20%">指标名称</th>
				<th>指标类型</th>
				<th>检查依据</th>
				<th>检查方法</th>
				<th>检查项数量</th>
				<shiro:hasPermission name="bas:item:edit"><th width="20%">操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		
		<c:forEach items="${page.list}" var="item" varStatus="index">
			<tr>
				<td>
					${(index.index+1)+(page.pageNo-1)*page.pageSize}
				</td>
				<td>
					${item.name}
				</td>
				<td>
					${fns:getDictLabel(item.itemTypeCode, 'itemType', '')}
				</td>	
				<td>
					${item.checkBasis}
				</td>
				<td>
					${item.checkMethod}
				</td>
				<td>
					${item.itemSubAmount}
				</td>
				<shiro:hasPermission name="bas:item:edit"><td>
    				<a href="javascript:;" onclick="itemView('${item.id}')">查看</a>
					<a href="javascript:;" onclick="itemForm('${item.id}')">修改</a>
					<a href="${ctx}/bas/item/delete?id=${item.id}" onclick="return confirmx('确认要删除该指标项吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>