<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><!-- modules/bas/entList.jsp --></title>
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

        function selectCheck(ths) {
            if (ths.checked == false) {
                $("input[name=all]:checkbox").prop('checked', false);
            }else {
                var count = $("input[name='id']:checkbox:checked").length;
                if (count == $("input[name='id']:checkbox").length) {
                    $("input[name=all]:checkbox").prop("checked", true);
                }
            }
        }


        var checkType = ${fns:getDictListJson('checkType')};
        function setEnts(){
            var vals = [];
            var $addEnt = $(window.parent.document.getElementById("addEnt"));
            $("input[name='id']:checkbox:checked").each(function (index, item) {
                var entId = $(this).val();
                vals.push(entId);
                var row = $(this).parent("td").parent("tr");
                var name = row.find("[id='name']").html();//注意html()和val()
                var areaName = row.find("[id='areaName']").html();//注意html()和val()
                var industryType = row.find("[id='industryType']").html();//注意html()和val()
                var month = "5";

                var $tr = $("<tr id = '" + index + "'><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td></tr>");
                $tr.find("td").each(function(i,n){
                    if(i === 0){
                        var $index = $("<span>"+(index+1)+"</span>");
                        $(n).append($index);
                    }else if(i === 1){
                        var $checkbox = $("<input type='checkbox' style='text-align: center'/>");
                        $checkbox.attr("name","list["+index+"].checkboxId");
                        $(n).append($checkbox);
                    }else if(i === 2){
                        var $month = $("<input type='hidden' />");
                        $month.attr("name","list["+index+"].planMonth");
                        $month.attr("value",month);
                        var $monthStr = $("<span>"+month+"</span>");
                        $(n).append($monthStr);
                        $(n).append($month);
                    }else if(i === 3){
                        var $entId = $("<input type='hidden' />");
                        $entId.attr("name","list["+index+"].entId");
                        $entId.attr("value",entId);
                        $(n).append($entId);
                        var $name = $("<span>"+name+"</span>");
                        $(n).append($name);
                    }else if(i === 4){
                        var $areaName = $("<span>"+areaName+"</span>");
                        $(n).append($areaName);
                    }else if(i === 5){
                        var $industryType = $("<span>"+industryType+"</span>");
                        $(n).append($industryType);
                    }else if(i === 6){
                        var $items = $("<input type='text' />");
                        $items.attr("name","list["+index+"].items");
                        $(n).append($items);
                    }else if(i === 7){
                        var $checkType = $("<select>");
                        for (var i=0;i<checkType.length;i++)
                        {
                            var $option = $("<option value='"+checkType[i].value+"'>"+checkType[i].label+"</option>");
                            $checkType.append($option)
                        }
                        $checkType.append($("</select>"))
                        $checkType.attr("name","list["+index+"].checkTypeCode");
                        $(n).append($checkType);
                    }else if(i === 8){
                        var $dutyType = $("<select>");
                        for (var i=0;i<checkType.length;i++)
                        {
                            var $option = $("<option value='"+checkType[i].value+"'>"+checkType[i].label+"</option>");
                            $dutyType.append($option)
                        }
                        $dutyType.append($("</select>"))
                        $dutyType.attr("name","list["+index+"].dutyTypeCode");
                        $(n).append($dutyType);
                    }else{
                        var $a = $("<a href='javascript:void(0);'><font color='red'>删除</font></a>");
                        $a.attr("onclick","delSpot(this);");
                        $(n).append($a);
                    }
                });
                $addEnt.append($tr);
            });
            var ids=vals.join(",");
            if(ids==""){
                layer.alert('请选择企业', {icon: 7});
                return false;
            }
            parent.layer.closeAll();
		}
        function closeLayer(){
            parent.layer.closeAll();
		}
	</script>
</head>
<body>
	<form:form id="searchForm" modelAttribute="ent" action="${ctx}/bas/ent/selectForPlan" method="post" class="breadcrumb form-search">
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
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th width="5%">序号</th>
				<th><input type="checkbox" name="all"></th>
				<th width="20%">企业名称</th>
				<th>属地</th>
				<th>所属行业</th>
				<th>法定代表人</th>
			</tr>
		</thead>
		<tbody id="tb">
		<c:forEach items="${page.list}" var="ent" varStatus="index">
			<tr>
				<td>
					${(index.index+1)+(page.pageNo-1)*page.pageSize}
				</td>
				<td>
					<input type="checkbox" name="id" value="${ent.id }" onclick="selectCheck(this)">
				</td>
				<td>
					<span id="name">${ent.name}</span>
				</td>
				<td>
					<span id="areaName">${ent.area.name}</span>
				</td>
				<td>
					<span id="industryType">${fns:getDictLabel(ent.industryTypeCode, 'industryType', '')}</span>
				</td>
				<td>
					${ent.legalPersonName}
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
	<div class="form-actions">
		<input id="btnCancel" class="btn" type="button" value="关闭" onclick="closeLayer()"/>&nbsp;
		<input id="btnSubmit" class="btn btn-primary" type="button" value="保存" onclick="setEnts();"/>
	</div>
</body>
</html>