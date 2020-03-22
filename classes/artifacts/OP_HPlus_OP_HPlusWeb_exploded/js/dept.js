var $table = $('#table');

$.ajax({
    post: "get",
    url: "data/deptlist.json",
    dataType: 'json',
    success: function (data) {
        init(data);
    }
});

function init(data) {

    $table.bootstrapTable({
        data: data,
        idField: 'deptId',
        parentIdField: 'parentId',
        expandAll: false,
        dataType: 'json',
        columns: [

            {
                field: 'deptName',
                title: '部门名称',
            },
            {
                field: 'orderNum',
                title: '排序',
                align: "center"
            },
            {
                field: 'status',
                title: '状态',
                align: "center",
                formatter: function (value,item, index) {
                    if (value == '0') {
                        return '<span class="badge badge-primary">正常</span>';
                    } else if (value == '1') {
                        return '<span class="badge badge-danger">停用</span>';
                    }
                }
            },
            {
                field: 'createTimeStr',
                title: '创建时间',
                align: "center"
            },
            {
                title: '操作',
                align: 'center',
                events: operateEvents,
                formatter:  operateFormatter

            }],


        // bootstrap-table-treegrid.js 插件配置 -- start

        //在哪一列展开树形
        treeShowField: 'deptName',
        //指定父id列
        parentIdField: 'parentId',

        onResetView: function (data) {
            $table.treegrid({
                initialState: 'collapse',// 所有节点都折叠
                treeColumn: 0,
                expanderExpandedClass: 'glyphicon glyphicon-chevron-down',  //图标样式
                expanderCollapsedClass: 'glyphicon glyphicon-chevron-right',
                onChange: function () {
                    $table.bootstrapTable('resetWidth');
                }
            });

            //只展开树形的第一级节点
            $table.treegrid('getRootNodes').treegrid('expand');

        },
        onCheck: function (row) {
            var datas = $table.bootstrapTable('getData');
            // 勾选子类
            selectChilds(datas, row, "postId", "parentId", true);

            // 勾选父类
            selectParentChecked(datas, row, "postId", "parentId")

            // 刷新数据
            $table.bootstrapTable('load', datas);
        },

        onUncheck: function (row) {
            var datas = $table.bootstrapTable('getData');
            selectChilds(datas, row, "postId", "parentId", false);
            $table.bootstrapTable('load', datas);
        },
        // bootstrap-table-treetreegrid.js 插件配置 -- end
    });

};

// 格式化按钮
function operateFormatter(value, row, index) {
    if (row.parentId != 0) {
    return [
        '<button type="button" class="RoleOfedit btn  btn-success" ><i class="fa fa-pencil-square-o" ></i>&nbsp;编辑</button>',
        '<button type="button" class="RoleOfadd btn btn-info" ><i class="fa fa-plus" ></i>&nbsp;新增</button>',
        '<button type="button" class="RoleOfdelete btn btn-danger" ><i class="fa fa-trash-o" ></i>&nbsp;删除</button>'
    ].join('');

    } else {
        return "";
    }

}

//初始化操作按钮的方法
window.operateEvents = {
    'click .RoleOfadd': function (e, value, row, index) {
        add(row.postId);
    },
    'click .RoleOfdelete': function (e, value, row, index) {
        del(row.postId);
    },
    'click .RoleOfedit': function (e, value, row, index) {
        update(row.postId);
    }
};

/**
 * 选中父项时，同时选中子项
 * @param datas 所有的数据
 * @param row 当前数据
 * @param postId postId 字段名
 * @param parentId 父id字段名
 */
function selectChilds(datas, row, postId, parentId, checked) {
    for (var i in datas) {
        if (datas[i][parentId] == row[postId]) {
            datas[i].check = checked;
            selectChilds(datas, datas[i], postId, parentId, checked);
        }
        ;
    }
}

function selectParentChecked(datas, row, postId, parentId) {
    for (var i in datas) {
        if (datas[i][postId] == row[parentId]) {
            datas[i].check = true;
            selectParentChecked(datas, datas[i], postId, parentId);
        }

    }
}


function add(postId) {
    layer.open({
        type: 2,
        title: '添加部门',
        shadeClose: true,
        shade: 0.8,
        maxmin: true,
        area: ['800px', '90%'],
        content: 'admin/dept/add.html/' //iframe的url
    });

}


function del(postId) {


}

function update(postId) {
    layer.open({
        type: 2,
        title: '添加部门',
        shadeClose: true,
        shade: 0.8,
        maxmin: true,
        area: ['800px', '90%'],
        content: 'admin/dept/edit.html/' //iframe的url
    });
}

$("#newAdd").click(function () {
    layer.open({
        type: 2,
        title: '添加部门',
        shadeClose: true,
        shade: 0.8,
        maxmin: true,
        area: ['800px', '90%'],
        content: 'admin/dept/add.html/' //iframe的url
    });

});