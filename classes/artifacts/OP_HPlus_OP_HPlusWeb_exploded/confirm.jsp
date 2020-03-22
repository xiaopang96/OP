<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>友情提醒 </title>
<jsp:include page="/admin/header.jsp" />
</head>
<body class="gray-bg">

${param.path }
<input type="hidden" id="msg" value="${param.msg}"/>
<input type="hidden" id="path" value="${param.path}"/>


<jsp:include page="/admin/footer.jsp" />
<script>
$.modal.confirm("尚未登录，请先登录",function(){
	window.location.href= ctx+"login.jsp?path="+$("#path").val();
});

</script>
</body>
</html>
