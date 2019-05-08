<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title><!-- modules/bas/entQrView.jsp --></title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	$(document).ready(function() {

	});
</script>
</head>
<body>
	<h3 align="center">${ent.name }</h3>
	<div align="center">
		<img align="middle" src="${fns:getConfig('attachmentUrl')}/ent_qr/${fileName}">
	</div>
</body>
</html>