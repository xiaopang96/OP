<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>注册</title>

<jsp:include page="/admin/header.jsp" />
</head>

<body class="gray-bg">

	<div class="middle-box text-center loginscreen   animated fadeInDown">
		<div class="rows">

			<h3>欢迎注册 XX</h3>
			<p>创建一个XX新账户</p>
			<form id="regForm" class="m-t col-md-5 col-md-offset-4" role="form" action="login.html" method="post">
				<input type="hidden" value="register" name="method" />
				<div class="form-group">
					<input type="text" class="form-control" name="loginName" placeholder="请输入用户名">
				</div>
				<div class="form-group">
					<input type="password" class="form-control" name="password" placeholder="请输入密码">
				</div>
				<div class="form-group">
					<input type="password" class="form-control" placeholder="请再次输入密码">
				</div>
				<div class="form-group text-left">
					<div class="checkbox i-checks">
						<label class="no-padding"> <input type="checkbox"><i></i> 我同意注册协议
						</label>
					</div>
				</div>
				<button type="button" class="btn btn-primary block full-width m-b">注 册</button>

				<p class="text-muted text-center">
					<small>已经有账户了？</small><a href="login.jsp">点此登录</a>
				</p>

			</form>
		</div>
	</div>
	<jsp:include page="/admin/footer.jsp" />
	<script>
	
		$("button").click(function() {
			$.ajax({
				url : "UserServlet",
				method : "post",
				data : $("#regForm").serialize(),
				success : function(res) {
					if (res.code == 0) {
						$.modal.msgSuccess("注册成功");
						return true;
					} else {
						$.modal.msgSuccess(res.message);
						return false;
					}
				}
			});
		});
	
	
		$(document).ready(function() {
			$('.i-checks').iCheck({
				checkboxClass : 'icheckbox_square-green',
				radioClass : 'iradio_square-green',
			});
		});
	</script>


</body>

</html>
