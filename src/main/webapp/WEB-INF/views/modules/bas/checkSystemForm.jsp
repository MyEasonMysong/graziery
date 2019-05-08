<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><!-- modules/bas/checkSystemForm.jsp --></title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();

			$("#inputForm").validate({
				submitHandler: function(form){
                    $.ajax({
                        url: "${ctx}/bas/checkSystem/save",
                        type: "post",
                        data: $(form).serialize(),
                        success: function (msg) {
                            /* 1：对号 */
                            top.$.jBox.tip("保存成功！","info");
                            parent.location.reload();
                        },
                        error: function (e) {
                            /* 5：不高兴 */
                            layer.msg(e, {icon: 5});
                        }
                    });
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
		});
		function itemResetForm(){
			//重置功能用
		}

        function searchTr(){
            var id = $("#select").val();
			var top = $("#"+id).position().top-$("#head").position().top+1;
			$(".tableList").animate({scrollTop:top},100);
            $("#"+id).css({"background":"red"});
            setTimeout(function () {
                $("#"+id).css({"background":"none"});
            },400);
        }

        function checkBox(id,parentId){
		    //判断是否为父级
		    if(parentId === 0){
		        //如果为父级 将其子级选中状态与其统一
                if($("#"+id).attr("checked")){
                    $("."+id).attr("checked",true);
                }else{
                    $("."+id).attr("checked",false);
                }
			}else {
                //如果为子级 判断父级是否被选中 如若未被选中 则将其选中  如若所有子级都未被选中  则取消其父级的选中状态
                parentId = $("#"+id).attr('class');
		        if(checked(parentId)){
                    $("#"+parentId).attr("checked",true);
				}else{
                    $("#"+parentId).attr("checked",false);
				}
			}
			function checked(parentId){
               var flag = false;
               //遍历子级所有 当且仅当所有子级都未被选中 返回false  否则返回true
                $("."+parentId).each(function(i,n){
                    if($(n).attr("checked")==='checked'){
                        flag = true;
                    }
                });
                return flag;
			}

        }
	</script>
	<style>
		.press{
			width: 15px;
			height: 17px;
			overflow: hidden;
			position: relative;
			float: left;
			left: 15%;
			transform-origin: 50% 50%;
			transform:rotate(90deg);
			top:3px;
		}
		.arrow{
			width: 13px;
			height: 13px;
			background: #333;
			-webkit-transform: rotate(45deg);
			-moz-transform: rotate(45deg);
			-ms-transform: rotate(45deg);
			-o-transform: rotate(45deg);
			transform: rotate(45deg);
			position: absolute;
			left: -7px;
			top: 2px;
			cursor: pointer;

		}
		.m_b_20{
			margin-bottom:20px;
			border:1px solid #0099ff;
		}
		.tableList{
			height: 500px;
			overflow-x: hidden;
			overflow-y: scroll;
			position: relative;
		}
		td{
			position: relative;
		}
	</style>
</head>
<body>
<form:form id="inputForm" modelAttribute="checkSystem" action="${ctx}/bas/checkSystem/save" method="post" class="form-horizontal">
	<form:hidden path="id"/>
	<form:hidden path="checkTypeCode"/>
	<table class="tableStyle m_b_20">
		<tr>
			<td width="20%" class="viewModeEven">指标体系名称：</td>
			<td><form:input path="name" htmlEscape="false" maxlength="50" style="width:80%" class="required"/></td>
			<td width="20%" class="viewModeEven">适用行业：</td>
			<td>
				<form:select path="industryTypeCode" class="input-medium">
					<form:option value="" label="请选择"/>
					<form:options items="${fns:getDictList('industryType')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</td>
		</tr>
	</table>
<div>
	<input id="select" type="text">
	<input type="button" class="btn btn-primary" value="查询" onclick="searchTr()"/>
</div>
<sys:message content="${message}"/>
<div class="tableList">
<table id="contentTable" class="table table-striped table-bordered table-condensed">
	<thead id="head">
	<tr>
		<th>序号</th>
		<th>指标项</th>
		<th>检查方法</th>
		<th>操作</th>
	</tr>
	</thead>
	<tbody>
	<c:forEach items="${itemList}" var="itemHead" varStatus="index1">
		<c:if test="${itemHead.parent.id == 0 }">
		<tr id="${index1.index+1-index2.end}" style="height: 50px;" >
			<td>
				<div class="press"  id="pressarrow${itemHead.id}">
					<div id="arrow${itemHead.id}" class="arrow" onclick="arrow(this.id)"></div>
				</div>${index1.index+1-index2.end}<a href=""></a>
			</td>
			<td>
				${itemHead.name}
			</td>
			<td>
				${itemHead.checkMethod}
			</td>
			<td>
				<input type="checkbox"  name="itemHead" <c:if test="${itemHead.checked == 'checked'}"> checked </c:if>
					   class="${itemHead.parent.id}" id="${itemHead.id}" value="${itemHead.id},${itemHead.parent.id},${itemHead.checkMethod}"
					   onclick="checkBox(this.id,${itemHead.parent.id})">
			</td>
		</tr>
		<c:forEach items="${itemHead.list}" var="itemBody"  varStatus="index2" >
			<c:if test="${itemBody.parent.id == itemHead.id }">
			<tr class="arrow${itemBody.parent.id}">
				<td style="text-align:right">
					(${index1.index+1}.${index2.index+1})
				</td>
				<td>
					${itemBody.name}
				</td>
				<td>
				</td>
				<td>
					<input type="checkbox" name="itemBody" <c:if test="${itemBody.checked == 'checked'}"> checked </c:if>
						   class="${itemBody.parent.id}" id="${itemBody.id}" value="${itemBody.id},${itemBody.parent.id},${itemBody.checkMethod}"
						   onclick="checkBox(this.id,'${itemBody.parent.id}')">
				</td>
			</tr>
			</c:if>
		</c:forEach>
		</c:if>
	</c:forEach>
	</form:form>
	</tbody>
</table>
</div>
<script>
	function arrow(id){
	    //更改按钮点击后的样式
        var aa = $("#press"+id).css('top');
        if(aa === '1px'){
            $("#press"+id).css({"transform":"rotate(90deg)","top":"3px","left": "17%"});
		}else{
            $("#press"+id).css({"transform":"rotate(0deg)","top":"1px","left": "16%"});
		}
 		//修改子菜单隐藏效果
        $("."+id).toggle();
	}
</script>
</body>
</html>