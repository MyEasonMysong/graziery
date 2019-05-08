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

        function setCheckSystem(id,name){
           window.parent.document.getElementById("systemName").innerHTML = name;
           window.parent.document.getElementById("checkSystemId").value = id;
           parent.layer.closeAll();
        }
	</script>
</head>
<body>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed" style="margin-top: 50px;">
		<thead>
			<tr>
				<th>序号</th>
				<th>指标体系名称</th>
				<th>适用行业</th>
				<th>指标项数量</th>
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
				<shiro:hasPermission name="bas:checkSystem:edit"><td>
					<a href="javascript:;" onclick="setCheckSystem('${checkSystem.id}','${checkSystem.name}')">选择</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>