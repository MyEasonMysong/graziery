<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title><!-- modules/bas/checkSystemView.jsp --></title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	$(document).ready(function() {

	});
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
	.t_a_center{
		text-align: center !important;
	}
	.m_b_20{
		margin-bottom:20px;
		border:1px solid #0099ff;
	}
</style>
</head>
<body>
<form:form id="inputForm" modelAttribute="checkSystem" action="${ctx}/bas/checkSystem/save" method="post" class="form-horizontal">
<form:hidden path="id"/>
<table class="tableStyle m_b_20">
<tr>
	<td width="20%"  class="viewModeEven t_a_center">指标体系名称</td>
	<td>${checkSystem.name }</td>
	<td width="20%"  class="viewModeEven t_a_center">适用行业</td>
	<td>${fns:getDictLabel(checkSystem.industryTypeCode, 'industryType', '')}</td>
</tr>
</table>
<sys:message content="${message}"/>
<table id="contentTable" class="table table-striped table-bordered table-condensed">

	<thead>
	<tr>
		<th>序号</th>
		<th>指标项</th>
		<th>检查方法</th>
	</tr>
	</thead>
	<tbody>
	<c:forEach items="${itemsList}" var="itemHead" varStatus="index1">
		<c:if test="${itemHead.parent.id == 0 }">
			<tr style="height: 50px;">
				<td>
					<div class="press"  id="pressarrow${itemHead.id}">
						<div id="arrow${itemHead.id}" class="arrow" onclick="arrow(this.id)"></div>
					</div>${index1.index+1-index2.end}
				</td>
				<td>
						${itemHead.name}
				</td>
				<td>
						${itemHead.checkMethod}
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
					</tr>
				</c:if>
			</c:forEach>
		</c:if>
	</c:forEach>
	</form:form>
	</tbody>
</table>
<div class="pagination">${page}</div>
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