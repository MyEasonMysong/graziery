<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><!-- modules/bas/entQrList.jsp --></title>
	<meta name="decorator" content="default"/>
	<script src="${ctxScript}/bas/ent.js" type="text/javascript"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			$("input[name=all]").click(function () {
		        if (this.checked) {
		            $("#tb :checkbox").prop("checked", true);
		        } else {
		            $("#tb :checkbox").prop("checked", false);
		        }
		    });
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
		//单选 设置name=id
		function entCheck(ths) {
		    if (ths.checked == false) {
		        $("input[name=all]:checkbox").prop('checked', false);
		    }else {
		        var count = $("input[name='id']:checkbox:checked").length;
		        if (count == $("input[name='id']:checkbox").length) {
		            $("input[name=all]:checkbox").prop("checked", true);
		        }
		    }
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="javascript:;">企业列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="ent" action="${ctx}/bas/ent/qrList" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>企业名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>属地：</label>
				<form:select path="areaCode" class="input-medium">
					<form:option value="" label="请选择"/>
					<form:options items="${areaList}" itemLabel="name" itemValue="code" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>所属行业：</label>
				<form:select path="industryTypeCode" class="input-medium">
					<form:option value="" label="请选择"/>
					<form:options items="${fns:getDictList('industryType')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li class="btns"><input class="btn btn-primary" type="submit" value="查询"/></li>
			<shiro:hasPermission name="bas:ent:down"><li class="btns"><input class="btn btn-primary" type="button" value="批量下载二维码" onclick="batchDownQrCode()"/></li></shiro:hasPermission>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th width="5%">序号</th>
				<th width="5%"><input type="checkbox" name="all">全选</th>
				<th width="20%">企业名称</th>
				<th>属地</th>
				<th>所属行业</th>
				<th>法定代表人</th>
				<th width="20%">操作</th>
			</tr>
		</thead>
		<tbody id="tb">
		<c:forEach items="${page.list}" var="ent" varStatus="index">
			<tr>
				<td>
					${(index.index+1)+(page.pageNo-1)*page.pageSize}
				</td>
				<td>
					<input type="checkbox" name="id" value="${ent.id }" onclick="entCheck(this)">
				</td>
				<td>
					${ent.name}
				</td>
				<td>
					${ent.area.name}
				</td>
				<td>
					${fns:getDictLabel(ent.industryTypeCode, 'industryType', '')}
				</td>
				<td>
					${ent.legalPersonName}
				</td>
				<td>
    				<a href="javascript:;" onclick="entQrView('${ent.id}')">查看</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>