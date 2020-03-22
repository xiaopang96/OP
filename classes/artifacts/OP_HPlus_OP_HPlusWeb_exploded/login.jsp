<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<title>登录</title>
<%-- <jsp:include page="/admin/header.jsp" /> --%>
<%@ include file="/admin/header.jsp" %>
<link rel="stylesheet" href="css/lrtk.css">
</head>
<body>
	<!-- 代码 开始 -->
	<div id="login">
		<div class="wrapper">
			<h3 align="center">欢迎使用万和CMS系统</h3>
			<div class="login">
				<form class="container offset1 loginform">
					<input type="hidden" name="path" value="${param.path }"/>
					<div id="owl-login">
						<div class="hand"></div>
						<div class="hand hand-r"></div>
						<div class="arms">
							<div class="arm"></div>
							<div class="arm arm-r"></div>
						</div>
					</div>
					<div class="pad">
						<input type="hidden" name="_csrf" value="9IAtUxV2CatyxHiK2LxzOsT6wtBE6h8BpzOmk=">
						<div class="control-group">
							<div class="controls">
								<label for="loginName" class="control-label fa fa-envelope"></label> <input id="loginName" type="text" name="loginName" placeholder="账号" tabindex="1" value="admin" autofocus="autofocus" class="form-control input-medium">
							</div>
						</div>
						<div class="control-group">
							<div class="controls">
								<label for="password" class="control-label fa fa-asterisk"></label> <input id="password" type="password" name="password" placeholder="密码" tabindex="2" value="123456" class="form-control input-medium">
							</div>
						</div>
						<div class="control-group">
							<div class="controls">
								<label for="vcode" class="control-label fa fa-key"></label> <input id="vcode" type="text" name="vcode" placeholder="验证码" tabindex="2" class="form-control input-medium">
							</div>
						</div>

						<div class="control-group">
							<div class="controls">
								<img src="kaptcha.jpg" id="kaptchaImage">
							</div>
						</div>
					</div>
					<div class="form-actions">
						<a href="#" tabindex="5" class="btn pull-left btn-link text-muted">忘记密码?</a> <a href="register.jsp" tabindex="6" class="btn btn-link text-muted">注册</a> <a class="btn btn-primary" type="button" tabindex="4" href="javascript:login()">登录</a>
					</div>
				</form>
			</div>
		</div>
	
		<%-- <jsp:include page="/admin/footer.jsp" /> --%>
		<%@include file="/admin/footer.jsp" %>
		<script>
		
			/*登录*/
			function login() {
				$.modal.loading("正在验证登录，请稍后...");
				$.ajax({
					url : "UserServlet?method=login",
					type : "post",
					data : $(".loginform").serialize(),
					dataType : "json",
					success : function(res) {
						if (res.code == 0) {
							window.location.href =ctx+ res.message;
						} else {
							$.modal.closeLoading();
							$.modal.msgError(res.message);
						}
					}
				});
			}
		
			$('#kaptchaImage').click(
				function() {
					$(this).hide().attr('src', 'kaptcha.jpg?' + Math.floor(Math.random() * 100)).fadeIn();
				}
			);
		
			$(function() {
		
				/*捂住眼睛的js*/
				$('#login #password').focus(function() {
					$('#owl-login').addClass('password');
				}).blur(function() {
					$('#owl-login').removeClass('password');
				});
			});
		</script>
	</div>
	<!-- 代码 结束 -->
</body>
</html>