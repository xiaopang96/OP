<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh">
<meta charset="utf-8">
<title>岗位</title>
<jsp:include page="/admin/header.jsp" />
<body>
    <!-- 版心 -->
	<div class="container">
		<div class="row">
			<!--查询条件行  -->
			<div class="col-sm-12 select-info">
				<form class="" id="condition-form">
					<div class="select-list">
						<ul>
							
							<li>
									编码： <input type="text" name="postCode">
							</li>
							<li>
									岗位名称： <input type="text" name="postName">
							</li>
						
							<li>
									状态： 
							<select name="status">
								<option value="">全部</option>
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
							<button class="btn btn-outline btn-success btn-rounded" onclick="$.operate.add()"> <i class="fa fa-plus"></i> 新增</button>
							<button class="btn btn-outline btn-danger btn-rounded"  onclick="$.operate.remove()"> <i class="fa fa-trash-o"></i> 删除</button>
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
       
        var prefix = ctx + "admin/post";

        $(function() {
            var options = {
               urlName : "PostServlet",
               sortName : "postId",
				sortOrder : "desc",
				id:"postId",
                modalName: "岗位",
                columns: [{
		            field : 'state',
					checkbox : true,
					align : 'center',
					valign : 'middle'
		        },
				{
					field : 'postId', 
					title : '岗位ID' ,
					align : 'center',
					valign : 'middle',
					sortable : true
				},	
				{
					field : 'postCode', 
					title : '岗位编码' ,
					align : 'center',
					valign : 'middle',
					sortable : true
				},	
				{
					field : 'postName', 
					title : '岗位名称' ,
					align : 'center',
					valign : 'middle',
					sortable : true
				},	
				{
					field : 'postSort', 
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
						actions.push('<a class="btn btn-primary btn-xs " href="javascript:void(0);" onclick="$.operate.edit(\'' + row.postId + '\')"><i class="fa fa-edit"></i>编辑</a> ');
						actions.push('<a class="btn btn-danger btn-xs "  href="javascript:void(0);" onclick="$.operate.remove(\'' + row.postId + '\')"><i class="fa fa-remove"></i>删除</a> ');
						return actions.join('');
					}
		        }]
            };
            $.table.init(options);
        });
    </script>
</body>
</html>