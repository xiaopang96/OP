<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="zh">
<meta charset="utf-8">
<jsp:include page="/admin/header.jsp" />


<body class="hold-transition box box-main">
	<input id="deptId" name="deptId" type="hidden" />
	<input id="deptName" name="deptName" type="hidden" />
	<div class="wrapper">
		<div class="treeShowHideButton">
			<label id="btnShow" title="显示搜索" style="display: none;">︾</label> <label id="btnHide" title="隐藏搜索">︽</label>
		</div>
		<div class="treeSearchInput" id="search">
			<label for="keyword">关键字：</label><input type="text" class="empty" id="keyword" maxlength="50">
			<button class="btn" id="btn" onclick="searchNode()">搜索</button>
		</div>
		<div class="treeExpandCollapse">
			<a href="javascript:" id="btnExpand">展开</a> / <a href="javascript:" id="btnCollapse">折叠</a>
		</div>
		<div id="tree" class="ztree treeselect"></div>
	</div>
	<div class="layui-layer-btn">
		<a class="layui-layer-btn0" onclick="sel()"><i class="fa fa-check"></i> 确定</a> <a class="layui-layer-btn1" onclick="$.modal.close()"><i class="fa fa-close"></i> 关闭</a>
	</div>


	<jsp:include page="/admin/footer.jsp" />
	<script type="text/javascript">
		var currentTreeNode;
		var treeOptions = {
			url : 'DeptServlet?method=findTree',
			id : 'deptId',
			parentId : 'parentId',
			name : 'deptName',
			checkFlag : false,
			clk : function(event, treeId, treeNode) {
				// 此处treeNode 为当前节点
				currentTreeNode = treeNode;
				$("#deptName").val(treeNode.deptName);
				$("#deptId").val(treeNode.deptId);
			}
		}
		$.tree.init(treeOptions);
	
		function sel() {
			/* if (currentTreeNode.isParent == true) {
				$.modal.msgWarning("请选择具体子部门");
			}else{ */
				parent.$("#deptId").val($("#deptId").val());
				parent.$("#deptName").val($("#deptName").val());
				var index = parent.layer.getFrameIndex(window.name);
				parent.layer.close(index);
			
	
		}
	
		/*  function searchNode(){
			var zTree = $.fn.zTree.getZTreeObj("tree");
			var nodeList = zTree.getNodesByParamFuzzy("deptName", $("#keyword").val());
			 $.fn.zTree.init($("#tree"), $.tree.settings, nodeList);
		}  */
	
		$("#keyword").bind("change cut input propertychange", searchNode);
		$("#keyword").bind("keydown", function(e) {
			if (e.which == 13) {
				searchNode();
			}
		});
		function searchNode() {
			$.tree.search($("#keyword").val());
		}
	
	
		//关键词搜索框折叠切换
		$(".treeShowHideButton").click(function() {
			$('#search').slideToggle(200);
			$('#btnShow').toggle();
			$('#btnHide').toggle();
			$('#keyword').focus();
		});
	</script>
</body>
</html>
