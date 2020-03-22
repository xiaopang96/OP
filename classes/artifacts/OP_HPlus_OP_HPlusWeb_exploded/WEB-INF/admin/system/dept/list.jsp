<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh">
<meta charset="utf-8">
<title>部门</title>
<jsp:include page="/admin/header.jsp" />
<style>
.treetable-tbody{
margin-top:19px;
}
</style>
<body>
    <!-- 版心 -->
	<div class="container">
		<div class="row">
			<!--查询条件行  -->
			<!-- bootstrap表格行 -->
			<div class="col-sm-12 select-info">
				<div class="fixed-table-toolbar">
					<div class="bs-bars pull-left">
						<!-- 工具栏 -->
						<div class="btn-group hidden-xs" id="toolbar" role="group">
							<button class="btn btn-outline btn-success btn-rounded" onclick="$.operate.add()"> <i class="fa fa-plus"></i> 新增</button>
						</div>
					</div>
				</div>
				<!-- bootstrap表格显示 -->
				<table class="bootstrap-table select-info" id="bootstrap-table"></table>
			</div>
		</div>
	</div>
    <jsp:include page="/admin/footer.jsp" />
   <script type="text/javascript">
       
        var prefix = ctx + "admin/dept"

        $(function() {
            var options = {
               urlName : "DeptServlet",
               parentId:"parentId",
				id:"deptId",
                modalName: "部门",
                expand: true,
                columns: [{
		            field : 'state',
					checkbox : true,
					align : 'center',
					valign : 'middle'
		        },	
				{
					field : 'deptName', 
					title : '部门名称' ,
					align : 'center',
					valign : 'middle',
					sortable : true
				},	
				{
					field : 'orderNum', 
					title : '显示顺序' ,
					align : 'center',
					valign : 'middle',
					sortable : true
				},	
				
				{
					field : 'status', 
					title : '状态' ,
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
				},	
				{
					field : 'createTimeStr', 
					title : '创建时间' ,
					align : 'center',
					valign : 'middle',
					sortable : true
				},
		        {
		            title: '操作',
		            align: 'center',
			        formatter : function(value, row, index) {
						var actions = [];
						actions.push('<a class="RoleOfedit btn btn-primary btn-xs " href="javascript:void(0);" onclick="$.operate.edit(\'' + row.deptId + '\')"><i class="fa fa-edit"></i>编辑</a> ');
						actions.push('<a class="RoleOfadd btn btn-success btn-xs "  href="javascript:void(0);" onclick="$.operate.add(\'' + row.deptId + '\')"><i class="fa fa-plus"></i>新增</a> ');
						actions.push('<a class="RoleOfdel btn btn-danger btn-xs "  href="javascript:void(0);" onclick="$.operate.remove(\'' + row.deptId + '\')"><i class="fa fa-remove"></i>删除</a> ');
						return actions.join('');
					}
		        }]
            };
            $.treeTable.init(options);
            
          //初始化操作按钮的方法
            window.operateEvents = {
                'click .RoleOfadd': function (e, value, row, index) {
                    add(row.deptId);
                },
                'click .RoleOfdelete': function (e, value, row, index) {
                    del(row.deptId);
                },
                'click .RoleOfedit': function (e, value, row, index) {
                    update(row.deptId);
                }
            };
          
        });
    </script>
</body>
</html>