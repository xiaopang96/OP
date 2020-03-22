$("#bootstrap-table").bootstrapTable({
    url: "#",      //请求后台的URL（*）
    method: 'GET',                      //请求方式（*）
    toolbar: '#toolbar',              //工具按钮用哪个容器
    striped: true,                      //是否显示行间隔色
    cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
    pagination: true,                   //是否显示分页（*）
    showPaginationSwitch: true,         //显示切换分页
    showFooter: false,                    //显示底部，默认不显示
    showFullscreen: false,               //显示全屏
    showHeader: true,                    //显示头部，默认显示
    showExport: true,                    //显示导出
    showColumns: true,                  //是否显示所有的列（选择显示的列）
    showRefresh: true,                  //是否显示刷新按钮
    sortable: true,                     //是否启用排序
    sortOrder: "asc",                   //排序方式
    sidePagination: "client",           //分页方式：client客户端分页，server服务端分页（*）
    pageNumber: 1,                      //初始化加载第一页，默认第一页,并记录
    pageSize: 5,                     //每页的记录行数（*）
    pageList: [5, 10, 15, 20],        //可供选择的每页的行数（*）
    search: false,                      //是否显示表格搜索
    strictSearch: true,
    minimumCountColumns: 2,             //最少允许的列数
    clickToSelect: true,                //是否启用点击选中行
    uniqueId: "id",                     //每一行的唯一标识，一般为主键列
    showToggle: true,                   //是否显示详细视图和列表视图的切换按钮
    cardView: false,                    //是否显示详细视图
    detailView: false,                  //是否显示父子表
    columns: [{
        checkbox: true
    },
        {
            field: '',
            title: '编号',
        },
        {
            field: '',
            title: '标题',
        },
        {
            field: '',
            title: '发布人',
        },

        {
            field: '',
            title: '模板路径',

        },
        {
            field: '',
            title: '发布时间',

        },
        {
            field: '',
            title: '状态',

        },
        {
            title: '操作',
            align: 'center',
            formatter: formatterOpt
        }]
});

function formatterOpt(value, row, index) {
    var actions = [];
    actions.push('<a class="btn btn-default btn-xs btnRemove"  href="javascript:void(0);" ><i class="fa fa-search"></i>查看</a> ');
    actions.push('<a class="btn btn-info btn-xs btnRefresh" href="javascript:void(0);" data-toggle="modal" data-target="#updateModal" onclick="editRoles(' + row.roleId + ')"><i class="fa fa-edit"></i>编辑</a>');
    actions.push('<a class="btn btn-danger btn-xs btnRemove"  href="javascript:void(0);" ><i class="fa fa-remove"></i>删除</a> ');
    return actions.join('');
}




