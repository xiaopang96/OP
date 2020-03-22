<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<base href="${pageContext.request.contextPath }/">
<!-- jQuery脚本 -->
<script src="js/plugins/jquery/jquery.js"></script>
<!--bootstrap脚本  -->
<script src="js/plugins/bootstrap/bootstrap.js"></script>
<!--jquery validate脚本-->
<script src="js/plugins/validate/jquery.validate.min.js"></script>
<script src="js/plugins/validate/messages_zh.min.js"></script>
<!--bootstrap-table插件-->
<script src="js/plugins/bootstrap-table/src/bootstrap-table.js"></script>
<script src="js/plugins/bootstrap-table/src/extensions/export/bootstrap-table-export.js"></script>
<script src="js/plugins/bootstrap-table/src/extensions/editable/bootstrap-table-editable.js"></script>
<script src="js/plugins/bootstrap-export/tableExport.js"></script>
<!--layout布局脚本-->
<script src="js/plugins/jquery-layout/jquery.layout-latest.js"></script>
<!--ztree脚本  -->
<script src="js/plugins/jquery-ztree/3.5/js/jquery.ztree.all-3.5.js"></script>
<!--bootstrap-switch脚本 -->
<script src="js/plugins/bootstrap-switch/bootstrap-switch.js"></script>

<!--bootstrap-treetable脚本 -->
<script src="js/plugins/bootstrap-treetable/bootstrap-treetable.js"></script>

<!-- jQuery下拉和复选插件脚本  -->
<script src="js/plugins/iCheck/icheck.js"></script>
<script src="js/plugins/select/select2.js"></script>
<!-- 遮罩层脚本 -->
<script src="js/plugins/blockUI/jquery.blockUI.js"></script>
<script src="js/plugins/layer/layer.min.js"></script>
<!--公共脚本-->
<script src="js/common.js"></script>
<!-- 获取contextPath -->
<input type="hidden" value="${pageContext.request.contextPath }" id="ctx">
<script>
var ctx = $("#ctx").val()+"/";
$("#search").click(function() {
	$("#bootstrap-table").bootstrapTable("refresh")
	return false;
});
</script>