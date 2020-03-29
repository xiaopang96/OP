<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="zh">
<meta charset="utf-8">
<title>用户</title>
<%-- <jsp:include page="/admin/header.jsp" /> --%>
<%@ include file="/admin/header.jsp"%>
<body>
	<div class="ui-layout-center">
		<!-- 版心 -->
		<div class="container">
			<div class="row">
				<!--查询条件行  -->
				<div class="col-sm-12 select-info">
					<form class="" id="condition-form">
						<input type="hidden" id="deptIds" name="deptIds">
						<div class="select-list">
							<ul>
								<li>登录账号： <input type="text" name="loginName">
								</li>
								<li>手机号码： <input type="text" name="phonenumber">
								</li>
								<li>帐号状态： <select name="status" id="status">
										<option value="">所有</option>
                                        <c:forEach items="${datas}"  var="item">
                                            <option value="${item.dictValue }">${item.dictLabel }</option>
                                        </c:forEach>
								</select>
								</li>
								<li>
									<button id="search" class="btn btn-rounded btn-primary">
										<i class="fa fa-search"></i>搜索
									</button>
								</li>
							</ul>
						</div>
					</form>
				</div>

				<!-- bootstrap表格行 -->
				<div class="col-sm-12 select-info">
					<div class="fixed-table-toolbar">
						<div class="bs-bars pull-left">
							<!-- 工具栏 -->
							<div class="btn-group hidden-xs" id="toolbar" role="group">
								<button class="btn btn-outline btn-success btn-rounded" onclick="$.operate.add()">
									<i class="fa fa-plus"></i> 新增
								</button>
								<button class="btn btn-outline btn-danger btn-rounded" onclick="$.operate.remove()">
									<i class="fa fa-trash-o"></i> 删除
								</button>
							</div>
						</div>
					</div>
					<!-- bootstrap表格显示 -->
					<table class="bootstrap-table select-info" id="bootstrap-table"></table>
				</div>
			</div>
		</div>
	</div>

	<div class="ui-layout-west">
		<div class="box-header">
			<div class="title">
				<i class="fa fa-bank"></i> 组织机构
			</div>
			<div class="tools">
				<button type="button" class="btn btn-box-tool menuItem" title="管理机构">
					<i class="fa fa-edit"></i>
				</button>
				<button type="button" class="btn btn-box-tool changeMenu" id="btnExpand" title="展开" style="display: none;">
					<i class="fa fa-chevron-up"></i>
				</button>
				<button type="button" class="btn btn-box-tool changeMenu" id="btnCollapse" title="折叠" style="display: inline-block;">
					<i class="fa fa-chevron-down"></i>
				</button>
				<button type="button" class="btn btn-box-tool" id="btnRefresh" title="刷新机构">
					<i class="fa fa-refresh"></i>
				</button>
			</div>
		</div>
		<div class="zTreeDemoBackground left">
			<ul id="tree" class="ztree"></ul>
		</div>
	</div>
	<%-- <jsp:include page="/admin/footer.jsp" /> --%>
	<jsp:include page="/admin/footer.jsp" />
	<script type="text/javascript" src="js/system/user.js"></script>
	<script>
	//用户状态
	$.ajax({
		url : "DictDataServlet?method=dictDataByType&dictType=sys_normal_disable",
		method : "post",
		dataType : "json",
		success : function(data) {
			var str = "";
			$(data).each(function(index, item) {
				str += "<option value='" + item.dictValue + "'>" + item.dictLabel + "</option>";
			});
			$("#status").append(str);
		}
	});

	//layout相关属性
	$("body").layout({
		applyDefaultStyles : true, //应用默认样式
		west__size : 220, //pane的大小
		spacing_open : 8, //边框的间隙
	});

	var prefix = ctx + "admin/user"
	$(function() {
		var options = {
			urlName : "UserServlet",
			sortName : "userId",
			sortOrder : "desc",
			id : "userId",
			modalName : "用户",
			columns : [ {
				field : 'state',
				checkbox : true,
				align : 'center',
				valign : 'middle'
			}, {
				field : 'userId',
				title : '用户ID',
				align : 'center',
				valign : 'middle',
				sortable : true
			}, {
				field : 'loginName',
				title : '登录账号',
				align : 'center',
				valign : 'middle',
				sortable : true
			}, {
				field : 'userName',
				title : '用户昵称',
				align : 'center',
				valign : 'middle',
				sortable : true
			}, {
				field : 'deptId',
				title : '部门编号 ',
				align : 'center',
				valign : 'middle',
				sortable : true
			}, {
				field : 'email',
				title : '用户邮箱',
				align : 'center',
				valign : 'middle',
				sortable : true
			}, {
				field : 'phonenumber',
				title : '手机号码',
				align : 'center',
				valign : 'middle',
				sortable : true
			},

				{
					field : 'status',
					title : '帐号状态',
					align : 'center',
					valign : 'middle',
					sortable : true,
					formatter : function(value, row, index) {
						if (value == '0') {
							return '<span class="badge badge-primary">正常</span>';
						} else if (value == '1') {
							return '<span class="badge badge-danger">停用</span>';
						}
					}
				}, {
					field : 'createTimeStr',
					title : '创建时间',
					align : 'center',
					valign : 'middle',
					sortable : true
				},

				{
					title : '操作',
					align : 'center',
					formatter : function(value, row, index) {
						var actions = [];
								actions.push('<a class="btn btn-success btn-xs " href="javascript:void(0);" onclick="$.operate.reset(\'' + row.userId + '\')"><i class="fa fa-key"></i>重置</a> ');
								actions.push('<a class="btn btn-primary btn-xs " href="javascript:void(0);" onclick="$.operate.edit(\'' + row.userId + '\')"><i class="fa fa-edit"></i>编辑</a> ');
								actions.push('<a class="btn btn-danger btn-xs "  href="javascript:void(0);" onclick="$.operate.remove(\'' + row.userId + '\')"><i class="fa fa-remove"></i>删除</a> ');
						return actions.join('');
					}
				} ]
		};
		$.table.init(options);
	});

	//=====================获取部门菜单数据然后默认展开======================


	var treeOptions = {
			url : 'DeptServlet?method=findTree',
			id : 'deptId',
			parentId : 'parentId',
			name : 'deptName',
			checkFlag : false,
			clk:function(event, treeId, treeNode) {
				// 此处treeNode 为当前节点
				var ids = [];
				ids = $.tree.getChildren(ids, treeNode);
				$("#deptIds").val(ids.join(","));
				$.table.search();
			}
		};
	$.tree.init(treeOptions); 

	//========================折叠菜单的交换显示===================================
	</script>
</body>
</html>