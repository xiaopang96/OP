<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="zh">
<meta charset="utf-8">
<jsp:include page="/admin/header.jsp" />
<body class="white-bg">
	<div class="wrapper wrapper-content animated fadeInRight ibox-content">
		<form class="form-horizontal m" id="form-user">
			<input id="userId" name="userId" value="${user.userId}" type="hidden">

			<div class="form-group">
				<label class="col-sm-3 control-label">登录账号：</label>
				<div class="col-sm-8">
					<input id="loginName" name="loginName" value="${user.loginName}" class="form-control" type="text">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">部门名称：</label>
				<div class="col-sm-8">
					<input id="deptId" name="deptId" type="hidden" value="${user.deptId}" />
					<input id="deptName" name=deptName value="${user.deptName}" readonly onclick="selectDeptTree()" class="form-control" type="text">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">用户昵称：</label>
				<div class="col-sm-8">
					<input id="userName" name="userName" value="${user.userName}" class="form-control" type="text">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">密码：</label>
				<div class="col-sm-8">
					<input id="password" name="password" value="" class="form-control" type="password">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">用户邮箱：</label>
				<div class="col-sm-8">
					<input id="email" name="email" value="${user.email}" class="form-control" type="text">
				</div>
			</div>

			<div class="form-group">
				<label class="col-sm-3 control-label">手机号码：</label>
				<div class="col-sm-8">
					<input id="phonenumber" name="phonenumber" value="${user.phonenumber}" class="form-control" type="text">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">用户性别：</label>
				<div class="col-sm-8">
					<select name="sex" id="sex" class="form-control">
						<c:forEach items="${genders}" var="item">
							<c:choose>
								<c:when test="${user.sex==item.dictValue}">
									<option value="${item.dictValue}" selected>${item.dictLabel}</option>
								</c:when>
								<c:otherwise>
									<option value="${item.dictValue}">${item.dictLabel}</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</select>
				</div>
			</div>

			<div class="form-group">
				<label class="col-sm-3 control-label">帐号状态：</label>
				<div class="col-sm-8">
					<input name="status" id="status" type="hidden" value="${user.status}" />
					 <input id="statu" class="form-control" type="checkbox">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">岗位：</label>
				<div class="col-sm-8">
					<select name="postIds" id="postIds" class="form-control" multiple>
						<c:forEach items="${posts}" var="item">
							<c:choose>
								<c:when test="${fn:contains(fn:join(user.postIds,','),item.postId) }">
									<option value="${item.postId}" selected> ${item.postName} </option>
								</c:when>
								<c:otherwise>
									<option value="${item.postId}"> ${item.postName} </option>
								</c:otherwise>
							</c:choose>
							
						</c:forEach>
					</select>
				</div>
			</div>
			
			<div class="form-group">
				<label class="col-sm-3 control-label">角色：</label>
				<div class="col-sm-8 " >
					<c:forEach items="${roles}" var="item">
						<c:choose>
							<c:when test="${fn:contains(fn:join(user.roleIds,','),item.roleId) }">
								<input type="checkbox" name="roleIds" checked value="${item.roleId}"/> ${item.roleName}
							</c:when>
							<c:otherwise>
								<input type="checkbox" name="roleIds"  value="${item.roleId}"/> ${item.roleName}
							</c:otherwise>
						</c:choose>
							
					</c:forEach>
				</div>
			</div>

			<div class="form-group">
				<div class="form-control-static col-sm-offset-9">
					<button type="submit" class="btn btn-primary">提交</button>
					<button onclick="$.modal.close()" class="btn btn-danger" type="button">关闭</button>
				</div>
			</div>
		</form>
	</div>
	<jsp:include page="/admin/footer.jsp" />

	<input name="tt" type="checkbox" data-size="small">


	<script type="text/javascript">
	
		 $('input[name="roleIds"]').iCheck({
		    checkboxClass: 'icheckbox_square-green',
		    increaseArea: '20%' // optional
		  });
	
	
		//多选
		$("#postIds").select2({
			multiple : true,
		//maximumSelectionLength: 3  //最多能够选择的个数
		});
	
		//状态switch
		$('#statu').bootstrapSwitch({
			onText : "正常",
			offText : "停用",
			onColor : "success",
			offColor : "danger",
			onSwitchChange : function(event, state) {
				//正常
				if (state)
					$("#status").val(0);
				//停用
				else
					$("#status").val(1);
			}
		})
		if ($("#status").val() == 1)
			$('#statu').bootstrapSwitch('state', false);
		else
			$('#statu').bootstrapSwitch('state', true);
	
		
	    var ctx = "http://localhost:8086";
		var prefix = ctx + "admin/user";
		$("#form-user").validate({
			rules : {},
			messages : {},
			submitHandler : function(form) {
				$.operate.submit(ctx + "/UserServlet?method=saveOrUpdate", $('#form-user').serialize(), 'saveOrUpdate');
			}
		});
		
		function selectDeptTree(){
			var url = ctx + "/DeptServlet?method=to_tree";
			$.modal.open("选择部门",url,'380','380');
		}
	</script>
</body>
</html>
