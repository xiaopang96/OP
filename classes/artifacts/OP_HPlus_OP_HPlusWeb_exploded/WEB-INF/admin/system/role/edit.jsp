<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="zh">
<meta charset="utf-8">
<jsp:include page="/admin/header.jsp" />
<body class="white-bg">
	<div class="wrapper wrapper-content animated fadeInRight ibox-content">
		<form class="form-horizontal m" id="form-role">
			<input id="roleId" name="roleId" value="${role.roleId}" type="hidden">
			<div class="form-group">
				<label class="col-sm-3 control-label">角色名称：</label>
				<div class="col-sm-8">
					<input id="roleName" name="roleName" value="${role.roleName}" class="form-control" type="text">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">角色权限字符串：</label>
				<div class="col-sm-8">
					<input id="roleKey" name="roleKey" value="${role.roleKey}" class="form-control" type="text">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">显示顺序：</label>
				<div class="col-sm-8">
					<input id="roleSort" name="roleSort" value="${role.roleSort}" class="form-control" type="text">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">角色状态:</label>
				<div class="col-sm-8">
					<input id="status" name="status" value="${role.status}" class="form-control" type="hidden"> 
					<input id="statu" class="form-control" type="checkbox">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">备注：</label>
				<div class="col-sm-8">
					<input id="remark" name="remark" value="${role.remark}" class="form-control" type="text">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">权限菜单：</label>
				<div class="col-sm-8">
					<input id="ids" name="ids" value="${role.menuIds}" class="form-control" type="text">
					<div id="tree" class="ztree treeselect"></div>
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
	<script type="text/javascript">
		var prefix = ctx + "admin/role"
		$("#form-role").validate({
			rules : {},
			messages : {},
			submitHandler : function(form) {
				
				//获取树
				var tree = $.fn.zTree.getZTreeObj("tree");
				//获取所有的选中的节点 
				var chkNodes = tree.getCheckedNodes(true);
				//把所有选中的节点加入数组
				var res = [];
				for (var i = 0; i < chkNodes.length; i++) {
					res.push(chkNodes[i].id);
				}
				$("#ids").val(res.join(","));
				
				$.operate.submit(ctx + "/RoleServlet?method=saveOrUpdate", $('#form-role').serialize(), 'saveOrUpdate');
			}
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
	
		var treeOptions = {
			url : 'MenuServlet?method=findTree&roleId='+$("#roleId").val(),
			checkFlag : true,
			expand : false
		}
		$.tree.init(treeOptions);
	</script>
</body>
</html>
