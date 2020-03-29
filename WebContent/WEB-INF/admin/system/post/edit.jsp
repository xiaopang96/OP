<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh">
<meta charset="utf-8">
<jsp:include page="/admin/header.jsp" />
<body class="white-bg">
    <div class="wrapper wrapper-content animated fadeInRight ibox-content">
        <form class="form-horizontal m" id="form-post" >
            <input id="postId" name="postId" value="${post.postId}"  type="hidden">
            <div class="form-group">	
                <label class="col-sm-3 control-label">岗位编码：</label>
                <div class="col-sm-8">
                    <input id="postCode" name="postCode" value="${post.postCode}"  class="form-control" type="text">
                </div>
            </div>
            <div class="form-group">	
                <label class="col-sm-3 control-label">岗位名称：</label>
                <div class="col-sm-8">
                    <input id="postName" name="postName" value="${post.postName}"  class="form-control" type="text">
                </div>
            </div>
            <div class="form-group">	
                <label class="col-sm-3 control-label">显示顺序：</label>
                <div class="col-sm-8">
                    <input id="postSort" name="postSort" value="${post.postSort}"  class="form-control" type="text">
                </div>
            </div>
            <div class="form-group">	
                <label class="col-sm-3 control-label">状态：</label>
                <div class="col-sm-8">
					<input id="status" name="status" value="${post.status}" class="form-control" type="hidden"> 
					<input id="statu" class="form-control" type="checkbox">
				</div>
            </div>
            <div class="form-group">	
                <label class="col-sm-3 control-label">备注：</label>
                <div class="col-sm-8">
                    <input id="remark" name="remark" value="${post.remark}"  class="form-control" type="text">
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
		var prefix = ctx + "admin/post";
		$("#form-post").validate({
			rules:{},
			messages : {},
			submitHandler: function(form) {
				$.operate.submit(ctx + "/PostServlet?method=saveOrUpdate", $('#form-post').serialize(),'saveOrUpdate');
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
	</script>
</body>
</html>
