<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="zh">
<meta charset="utf-8">
<jsp:include page="/admin/header.jsp" />
<body class="white-bg">
    <div class="wrapper wrapper-content animated fadeInRight ibox-content">
        <form class="form-horizontal m" id="form-menu" >
            <input id="menuId" name="menuId" value="${menu.menuId}"  type="hidden">
            <div class="form-group">	
                <label class="col-sm-3 control-label">上级菜单：</label>
                <div class="col-sm-8">
                    <input id="parentId" name="parentId" readonly value="${menu.parentId}"  class="form-control" type="text">
					<input id="parentMenuName" name=parentMenuName value="${menu.parentMenuName}"  readonly onclick="selectMenuTree()" class="form-control" type="text">
                </div>
            </div>
              <div class="form-group">	
                <label class="col-sm-3 control-label">菜单类型:</label>
                <div class="col-sm-8" id="menuTypeDiv">
                	<c:choose>
                		<c:when test="${menu.menuType=='M'}">
                			<input  name="menuType" value="M"  class="form-control" type="radio" checked >目录
                		</c:when>
                		<c:otherwise>
                			<input  name="menuType" value="M"  class="form-control" type="radio" >目录
                		</c:otherwise>
                	</c:choose>
                    <c:choose>
                		<c:when test="${menu.menuType=='C'}">
                			<input  name="menuType" value="C"  class="form-control" type="radio" checked >菜单
                		</c:when>
                		<c:otherwise>
                			<input  name="menuType" value="C"  class="form-control" type="radio" >菜单
                		</c:otherwise>
                	</c:choose>
                	<c:choose>
                		<c:when test="${menu.menuType=='F'}">
                			<input  name="menuType" value="F"  class="form-control" type="radio" checked >按钮
                		</c:when>
                		<c:otherwise>
                			<input  name="menuType" value="F"  class="form-control" type="radio" >按钮
                		</c:otherwise>
                	</c:choose>
                    
                </div>
            </div>
            <div class="form-group">	
                <label class="col-sm-3 control-label ">菜单名称：</label>
                <div class="col-sm-8">
                    <input id="menuName" name="menuName" value="${menu.menuName}"  class="form-control" type="text">
                </div>
            </div>
           <div class="form-group">	
                <label class="col-sm-3 control-label url">请求地址：</label>
                <div class="col-sm-8">
                    <input id="url" name="url" value="${menu.url}"  class="form-control" type="text">
                </div>
            </div>
            <div class="form-group">	
                <label class="col-sm-3 control-label perms">权限标识：</label>
                <div class="col-sm-8">
                    <input id="perms" name="perms" value="${menu.perms}"  class="form-control" type="text">
                </div>
            </div>
            <div class="form-group">	
                <label class="col-sm-3 control-label">显示顺序：</label>
                <div class="col-sm-8">
                    <input id="orderNum" name="orderNum" value="${menu.orderNum}"  class="form-control" type="text">
                </div>
            </div>
             <div class="form-group">	
                <label class="col-sm-3 control-label icon">菜单图标：</label>
                <div class="col-sm-8">
                    <input id="icon" name="icon" value="${menu.icon}" placeholder="选择图标"  class="form-control" type="text">
               		<div class="icon-drop animated flipInX" style="display: none;max-height:200px;overflow-y:auto">
               			<jsp:include page="icon.jsp"></jsp:include>
               		</div>
                </div>
            </div>
          
            <div class="form-group">	
                <label class="col-sm-3 control-label">菜单状态：</label>
                <div class="col-sm-8">
                	
               	<c:forEach items="${datas}" var="item">
					<c:choose>
						<c:when test="${menu.visible==item.dictValue}">
							   <input id="visible" name="visible" value="${item.dictValue}" checked   type="radio">${item.dictLabel}
						</c:when>
						<c:otherwise>
							<input id="visible" name="visible" value="${item.dictValue}"    type="radio">${item.dictLabel}
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
    <script type="text/javascript">
    
    	$("input[name='icon']").focus(function() {
    		$(".icon-drop").show();
    	});
    
    	$(".icon-drop").find(".ico-list i").click(function() {
    		$("#icon").val($(this).prop('class'));
    		$(".icon-drop").hide();
    	});
    
    	$("input[name='menuType']").on('ifChecked', function(event) {
    		var menuType = $(event.target).val();
    		if (menuType == "M") {
    			$(".url").parent().hide();
    			$(".perms").parent().hide();
    			$(".icon").parent().show();
    
    		} else if (menuType == "C") {
    			$(".url").parent().show();
    			$(".perms").parent().show();
    			$(".icon").parent().hide();
    
    		} else {
    			$(".url").parent().hide();
    			$(".perms").parent().show();
    			$(".icon").parent().hide();
    
    		}
    	});
    	if($.common.isEmpty($("#parentId").val())){
    		$("input[name='menuType']:first").prop("checked",true);
    		$("input[name='menuType']:first").trigger('ifChecked');
    	}else{
    		$("input[name='menuType']:checked").trigger('ifChecked');
    	}
    
    	$('input[type="radio"]').iCheck({
    		checkboxClass : 'icheckbox_square-green',
    		radioClass : 'iradio_square-green',
    		increaseArea : '20%' // optional
    	});
    
    	var prefix = ctx + "admin/menu"
    	$("#form-menu").validate({
    		rules : {},
    		messages : {},
    		submitHandler : function(form) {
    			$.operate.submit(ctx + "/MenuServlet?method=saveOrUpdate", $('#form-menu').serialize(), 'saveOrUpdate');
    		}
    	});
    
    	function selectMenuTree() {
    		var url = ctx + "MenuServlet?method=to_tree"
    		$.modal.open("选择菜单", url, '380', '380');
    	}
    </script>
</body>
</html>
