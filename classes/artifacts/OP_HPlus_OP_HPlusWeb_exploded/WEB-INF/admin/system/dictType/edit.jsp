<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh">
<meta charset="utf-8">
<jsp:include page="/admin/header.jsp" />
<body class="white-bg">
    <div class="wrapper wrapper-content animated fadeInRight ibox-content">
        <form class="form-horizontal m" id="form-dictType" >
            <input id="dictId" name="dictId" value="${dictType.dictId}"  type="hidden">
            <div class="form-group">	
                <label class="col-sm-3 control-label">字典名称：</label>
                <div class="col-sm-8">
                    <input id="dictName" name="dictName" value="${dictType.dictName}"  class="form-control" type="text">
                </div>
            </div>
            <div class="form-group">	
                <label class="col-sm-3 control-label">字典类型：</label>
                <div class="col-sm-8">
                    <input id="dictType" name="dictType" value="${dictType.dictType}"  class="form-control" type="text">
                </div>
            </div>
            <div class="form-group">	
                <label class="col-sm-3 control-label">状态（0正常 1停用）：</label>
                <div class="col-sm-8">
                    <input id="status" name="status" value="${dictType.status}"  class="form-control" type="text">
                </div>
            </div>
            <div class="form-group">	
                <label class="col-sm-3 control-label">备注：</label>
                <div class="col-sm-8">
                    <input id="remark" name="remark" value="${dictType.remark}"  class="form-control" type="text">
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
		var prefix = ctx + "admin/dictType"
		$("#form-dictType").validate({
			rules:{},
			messages : {},
			submitHandler: function(form) {
				$.operate.submit(ctx + "/DictTypeServlet?method=saveOrUpdate", $('#form-dictType').serialize(),'saveOrUpdate');
			}
		});
	</script>
</body>
</html>
