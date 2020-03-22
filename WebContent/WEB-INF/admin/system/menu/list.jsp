<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh">
<meta charset="utf-8">
<title>菜单权限</title>
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
							<button class="btn btn-outline btn-success btn-rounded" onclick="$.operate.add()">
								<i class="fa fa-plus"></i> 新增
							</button>
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
       
        var prefix = ctx + "admin/menu"

        $(function() {
            var options = {
            	urlName : "MenuServlet",
				id:"menuId",
				parentId:"parentId",
				modalName:'菜单',
				expand:false,
                columns: [{
		            field : 'state',
					radio : true,
					align : 'center',
					valign : 'middle'
		        },
				{
					field : 'menuName', 
					title : '菜单名称' ,
					align : 'center'
				},	
				{
					field : 'orderNum', 
					title : '显示顺序' ,
					align : 'center'
				},	
				{
					field : 'url', 
					title : '请求地址' ,
					align : 'center'
				},	
				{
					field : 'menuType', 
					title : '类型' ,
					align : 'center',
					formatter:function(value,row,index){
						 if (row.menuType == 'M') {
			                    return '<span class="label label-success">目录</span>';
			                }
			                else if (row.menuType == 'C') {
			                    return '<span class="label label-primary">菜单</span>';
			                }
			                else if (row.menuType == 'F') {
			                    return '<span class="label label-warning">按钮</span>';
			                }
					}
				},	
				{
					field : 'visible', 
					title : '状态' ,
					align : 'center',
					formatter:function(value,row,index){
						 if (row.visible == '0') {
			                    return '<span class="label label-success">显示</span>';
			                }
						 else{
							 return '<span class="label label-danger">隐藏</span>';
						 }
					}
				},	
				{
					field : 'perms', 
					title : '权限标识' ,
					align : 'center'
				},	
		        {
		            title: '操作',
		            align: 'center',
			        formatter : function(value, row, index) {
						var actions = [];
						actions.push('<a class="btn btn-primary btn-xs " href="javascript:void(0);" onclick="$.operate.add(\'' + row.menuId + '\')"><i class="fa fa-edit"></i>新增</a> ');
						actions.push('<a class="btn btn-success btn-xs " href="javascript:void(0);" onclick="$.operate.edit(\'' + row.menuId + '\')"><i class="fa fa-edit"></i>编辑</a> ');
						actions.push('<a class="btn btn-danger btn-xs "  href="javascript:void(0);" onclick="$.operate.remove(\'' + row.menuId + '\')"><i class="fa fa-remove"></i>删除</a> ');
						return actions.join('');
					}
		        }]
            };
            $.treeTable.init(options);
        });

    </script>
</body>
</html>