<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh">
<meta charset="utf-8">
<title>字典类型</title>
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
									字典主键： <input type="text" name="dictId">
							</li>
							<li>
									字典名称： <input type="text" name="dictName">
							</li>
							<li>
									字典类型： <input type="text" name="dictType">
							</li>
							<li>
									状态（0正常 1停用）： <input type="text" name="status">
							</li>
							<li>
									创建者： <input type="text" name="createBy">
							</li>
							<li>
									创建时间： <input type="text" name="createTime">
							</li>
							<li>
									更新者： <input type="text" name="updateBy">
							</li>
							<li>
									更新时间： <input type="text" name="updateTime">
							</li>
							<li>
									备注： <input type="text" name="remark">
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
       
        var prefix = ctx + "admin/dictType"

        $(function() {
            var options = {
               urlName : "DictTypeServlet",
               sortName : "dictId",
				sortOrder : "desc",
				id:"dictId",
                modalName: "字典类型",
                columns: [{
		            field : 'state',
					checkbox : true,
					align : 'center',
					valign : 'middle'
		        },
				{
					field : 'dictId', 
					title : '字典主键' ,
					align : 'center',
					valign : 'middle',
					sortable : true
				},	
				{
					field : 'dictName', 
					title : '字典名称' ,
					align : 'center',
					valign : 'middle',
					sortable : true
				},	
				{
					field : 'dictType', 
					title : '字典类型' ,
					align : 'center',
					valign : 'middle',
					sortable : true
				},	
				{
					field : 'status', 
					title : '状态（0正常 1停用）' ,
					align : 'center',
					valign : 'middle',
					sortable : true
				},	
				{
					field : 'createBy', 
					title : '创建者' ,
					align : 'center',
					valign : 'middle',
					sortable : true
				},	
				{
					field : 'createTimeStr', 
					title : '创建时间' ,
					align : 'center',
					valign : 'middle',
					sortable : true
				},

				{
					field : 'updateBy', 
					title : '更新者' ,
					align : 'center',
					valign : 'middle',
					sortable : true
				},	
				{
					field : 'updateTimeStr', 
					title : '更新时间' ,
					align : 'center',
					valign : 'middle',
					sortable : true
				},

				{
					field : 'remark', 
					title : '备注' ,
					align : 'center',
					valign : 'middle',
					sortable : true
				},	
		        {
		            title: '操作',
		            align: 'center',
			        formatter : function(value, row, index) {
						var actions = [];
						actions.push('<a class="btn btn-primary btn-xs " href="javascript:void(0);" onclick="$.operate.edit(\'' + row.dictId + '\')"><i class="fa fa-edit"></i>编辑</a> ');
						actions.push('<a class="btn btn-danger btn-xs "  href="javascript:void(0);" onclick="$.operate.remove(\'' + row.dictId + '\')"><i class="fa fa-remove"></i>删除</a> ');
						return actions.join('');
					}
		        }]
            };
            $.table.init(options);
        });
    </script>
</body>
</html>