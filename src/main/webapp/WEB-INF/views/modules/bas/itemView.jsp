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

	</script>
</head>
<body>
	<form:form id="inputForm" modelAttribute="item" action="${ctx}/bas/item/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<table class="tableStyle">
			<tr>
				<td width="30%" class="viewModeEven">指标名称：</td>
				<td>${item.name}</td>
			</tr>
			<tr>
				<td width="30%" class="viewModeEven">指标类型：</td>
				<td>
					${fns:getDictLabel(item.itemTypeCode, 'itemType', '')}
				</td>
			</tr>
			<tr>
				<td width="30%" class="viewModeEven">检查依据：</td>
				<td>${item.checkBasis}</td>
			</tr>
			<tr>
				<td width="30%" class="viewModeEven">检查方法：</td>
				<td>
					${item.checkMethod}
				</td>
			</tr>
			<tr>
				<td width="30%" class="viewModeEven">检查项目：</td>
				<td>
						<table id="spots" style="width:100%;border: 0;">
							<c:forEach items="${item.list}" var="record" varStatus="vs">
								<tr>
									<td width="10%">
										（${vs.index + 1}）
									</td>
									<td width="90%">
										${record.name}
									</td>
								</tr>
							</c:forEach>
						</table>
				</td>
			</tr>
		</table>
	</form:form>
</body>
</html>