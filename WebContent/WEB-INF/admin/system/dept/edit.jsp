<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh">
<meta charset="utf-8">
<jsp:include page="/admin/header.jsp" />
<body class="white-bg">
    <div class="wrapper wrapper-content animated fadeInRight ibox-content">
        <form class="form-horizontal m" id="form-dept" >
            <input name="deptId" value="${dept.deptId}"  type="hidden">
           <div class="form-group">
				<label class="col-sm-3 control-label">上级部门：</label>
				<div class="col-sm-8">
					<input id="deptId" name="parentId" type="hidden" value="${dept.parentId}" />
					<input id="deptName" name=parentDeptName value="${dept.parentDeptName}" readonly onclick="selectDeptTree()" class="form-control" type="text">
				</div>
			</div>
           <div class="form-group">
				<label class="col-sm-3 control-label">部门名称：</label>
				<div class="col-sm-8">
					<input id="Name" name=deptName value="${dept.deptName}" class="form-control" type="text">
				</div>
			</div>
            <div class="form-group">	
                <label class="col-sm-3 control-label">显示顺序：</label>
                <div class="col-sm-8">
                    <input id="orderNum" name="orderNum" value="${dept.orderNum}"  class="form-control" type="text">
                </div>
            </div>
            <div class="form-group">	
                <label class="col-sm-3 control-label">负责人：</label>
                <div class="col-sm-8">
                    <input id="leader" name="leader" value="${dept.leader}"  class="form-control" type="text">
                </div>
            </div>
            <div class="form-group">	
                <label class="col-sm-3 control-label">联系电话：</label>
                <div class="col-sm-8">
                    <input id="phone" name="phone" value="${dept.phone}"  class="form-control" type="text">
                </div>
            </div>
            <div class="form-group">	
                <label class="col-sm-3 control-label">邮箱：</label>
                <div class="col-sm-8">
                    <input id="email" name="email" value="${dept.email}"  class="form-control" type="text">
                </div>
            </div>
            <div class="form-group">
				<label class="col-sm-3 control-label">部门状态：</label>
				<div class="col-sm-8">
					<input name="status" id="status" type="hidden" value="${dept.status}" />
					 <input id="statu" class="form-control" type="checkbox">
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
		var prefix = ctx + "admin/dept"
		$("#form-dept").validate({
			rules:{},
			messages : {},
			submitHandler: function(form) {
				$.operate.submit(ctx + "/DeptServlet?method=saveOrUpdate", $('#form-dept').serialize(),'saveOrUpdate');
			}
		});
		
		function selectDeptTree(){
			var url = ctx + "DeptServlet?method=to_tree"
			$.modal.open("选择部门",url,'380','380');
		}
		
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
	
	</script>
</body>
</html>
