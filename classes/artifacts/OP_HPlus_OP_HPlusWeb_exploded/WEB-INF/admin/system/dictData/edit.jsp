<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh">
<meta charset="utf-8">
<jsp:include page="/admin/header.jsp" />
<body class="white-bg">
    <div class="wrapper wrapper-content animated fadeInRight ibox-content">
        <form class="form-horizontal m" id="form-dictData" >
            <input id="dictCode" name="dictCode" value="${dictData.dictCode}"  type="hidden">
            <div class="form-group">	
                <label class="col-sm-3 control-label">字典排序：</label>
                <div class="col-sm-8">
                    <input id="dictSort" name="dictSort" value="${dictData.dictSort}"  class="form-control" type="text">
                </div>
            </div>
            <div class="form-group">	
                <label class="col-sm-3 control-label">字典标签：</label>
                <div class="col-sm-8">
                    <input id="dictLabel" name="dictLabel" value="${dictData.dictLabel}"  class="form-control" type="text">
                </div>
            </div>
            <div class="form-group">	
                <label class="col-sm-3 control-label">字典键值：</label>
                <div class="col-sm-8">
                    <input id="dictValue" name="dictValue" value="${dictData.dictValue}"  class="form-control" type="text">
                </div>
            </div>
            <div class="form-group">	
                <label class="col-sm-3 control-label">字典类型：</label>
                <div class="col-sm-8">
                    <input id="dictType" name="dictType" value="${dictData.dictType}"  class="form-control" type="text">
                </div>
            </div>
            <div class="form-group">	
                <label class="col-sm-3 control-label">样式属性：</label>
                <div class="col-sm-8">
                    <input id="cssClass" name="cssClass" value="${dictData.cssClass}"  class="form-control" type="text">
                </div>
            </div>
            <div class="form-group">	
                <label class="col-sm-3 control-label">是否默认（Y是 N否）：</label>
                <div class="col-sm-8">
                    <input id="isDefault" name="isDefault" value="${dictData.isDefault}"  class="form-control" type="text">
                </div>
            </div>
            <div class="form-group">	
                <label class="col-sm-3 control-label">状态（0正常 1停用）：</label>
                <div class="col-sm-8">
                    <input id="status" name="status" value="${dictData.status}"  class="form-control" type="text">
                </div>
            </div>
            <div class="form-group">	
                <label class="col-sm-3 control-label">备注：</label>
                <div class="col-sm-8">
                    <input id="remark" name="remark" value="${dictData.remark}"  class="form-control" type="text">
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
		var prefix = ctx + "admin/dictData"
		$("#form-dictData").validate({
			rules:{},
			messages : {},
			submitHandler: function(form) {
				$.operate.submit(ctx + "/DictDataServlet?method=saveOrUpdate", $('#form-dictData').serialize(),'saveOrUpdate');
			}
		});
	</script>
</body>
</html>
