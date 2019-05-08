<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><!-- modules/bas/itemForm.jsp --></title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					var num = $("#spots").find("td").length;
					if(0 === num){
						top.$.jBox.tip("请您添加检查项目！","info");
					}else{
						$.ajax({
	                        url: "${ctx}/bas/item/save",
	                        type: "post",
	                        data: $(form).serialize(),
	                        success: function (result) {
	                        		top.$.jBox.tip("保存成功！","info");
	                                parent.location.reload();
	                        },
	                        error: function (e) {
	                            layer.msg(e, {icon: 5});
	                        }
	                    }); 
					}
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
			if(0===$("#spots").find("td").length){
				addSpot();
			}
		});
		
		$(document).ready(function() {
			var spotMax = 30;
			if($('div.spot').size() >= spotMax) {
				$(obj).hide();
			}
			$("input#add").click(function() {
				addSpot(this, spotMax);
			});
		});

		function addSpot() {
			var $table = $("#spots"),index = $table.find("tr").length;
			var $tr = $("<tr id = '" + index + "'><td width='80%'></td><td width='20%'></td></tr>");
			$tr.find("td").each(function(i,n){
				if(i === 0){
					var $name = $("<input type='text' maxlength='50' style='width:90%' class='required' />");
					$name.attr("name","list["+index+"].name");
					$(n).append($name);
				}else{
					var $id = $("<input type='hidden' />");
					$id.attr("name","list["+index+"].id");
					$(n).append($id);
					var $a = $("<a href='javascript:void(0);'><font color='red'>删除</font></a>");
					$a.attr("onclick","delSpot(this);");
					$(n).append($a);
				}
			});
			$table.append($tr);
		};
		
		function delSpot(a) {
			var $delTr = $(a).parent().parent();
			$delTr.nextAll().each(function(i,n){
				var index = parseInt($(n).attr("id"));
				$(n).attr("id",index-1);
				var $name = $(n).find("[name='list["+index+"].name']");
				$name.attr("name","list["+(index-1)+"].name");
				var $id = $(n).find("[name='list["+index+"].id']");
				$id.attr("name","list["+(index-1)+"].id");
			});
			$delTr.remove();
		}
		
	</script>
</head>
<body>
	<form:form id="inputForm" modelAttribute="item" action="${ctx}/bas/item/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<table class="tableStyle">
			<tr>
				<td width="30%" class="viewModeEven"><font color="red">*</font>指标名称：</td>
				<td><form:input path="name" htmlEscape="false" maxlength="50" class="required input-xlarge"/></td>
			</tr>
			<tr>
				<td width="30%" class="viewModeEven"><font color="red">*</font>指标类型：</td>
				<td>
					<form:select path="itemTypeCode" class="required input-medium">
						<form:option value="" label="请选择"/>
						<form:options items="${fns:getDictList('itemType')}" itemLabel="label"  itemValue="value" htmlEscape="false"/>
					</form:select>
				</td>
			</tr>
			<tr>
				<td width="30%" class="viewModeEven"><font color="red">*</font>检查依据：</td>
				<td><form:input path="checkBasis" htmlEscape="false" maxlength="50"  class="required input-xlarge"/></td>
			</tr>
			<tr>
				<td width="30%" class="viewModeEven"><font color="red">*</font>检查方法：</td>
				<td>
					<form:textarea path="checkMethod" htmlEscape="false" maxlength="50" class="required input-xlarge"  />
				</td>
			</tr>
			<tr>
				<td width="30%" class="viewModeEven"><font color="red">*</font>检查项目：</td>
				<td>
						<table id="spots" style="width:100%;border: 0;">
							<c:forEach items="${item.list}" var="record" varStatus="vs">
								<tr id="${vs.index}">
									<td width="80%">
										<form:input path="list[${vs.index}].name" maxlength="50" cssStyle="width:90%;" cssClass="required" />
									</td>
									<td width="20%">
										<form:hidden path="list[${vs.index}].id" />
										<a href="javascript:void(0);" onclick="delSpot(this);"><font color="red">删除</font></a>
									</td>
								</tr>
							</c:forEach>
						</table>
					<input class="btn btn-primary" type="button" onclick="addSpot()"  value="新增" />
				</td>
			</tr>
		</table>
	</form:form>
</body>
</html>