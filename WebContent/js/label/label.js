var $table = $('#table');

$.ajax({
    post: "get",
    url: "data/programa.json",
    dataType: 'json',
    success: function (data) {
        init(data);
    }
});

function init(data) {

    $table.bootstrapTable({
        data: data,
        idField: 'menuId',
        parentIdField: 'parentId',
        expandAll: false,
        dataType: 'json',
        columns: [
            {
                field: 'check', checkbox: true, formatter: function (value, row, index) {
                    if (row.check == true) {
                        // console.log(row.serverName);
                        //设置选中
                        return {checked: true};
                    }
                }
            },
            {
                field: 'menuName',
                title: '文章类别',
                width: '50%'


            },

            {
                title: '类型',
                field: 'menuType',
                width: '10%',
                align: "center",
                formatter: function (value, row, index) {
                    if (value == 'M') {
                        return '<span class="label label-success">热门</span>';
                    }
                    else if (value == 'C') {
                        return '<span class="label label-primary">技术</span>';
                    }
                    else if (value == 'F') {
                        return '<span class="label label-warning">其他</span>';
                    }
                }

            },


            {
                field: 'operate',
                title: '操作',
                align: 'center',
                events: operateEvents,
                formatter: 'operateFormatter'
            },
        ],


        // bootstrap-table-treegrid.js 插件配置 -- start

        //在哪一列展开树形
        treeShowField: 'menuName',
        //指定父id列
        parentIdField: 'parentId',

        onResetView: function (data) {
            $table.treegrid({
                initialState: 'collapse',// 所有节点都折叠
                treeColumn: 1,
                expanderExpandedClass: 'glyphicon glyphicon-chevron-down',  //图标样式
                expanderCollapsedClass: 'glyphicon glyphicon-chevron-right',
                onChange: function () {
                    $table.bootstrapTable('resetWidth');
                }
            });

            //只展开树形的第一级节点
            // $table.treegrid('getRootNodes').treegrid('expand');

        },
        onCheck: function (row) {
            var datas = $table.bootstrapTable('getData');
            // 勾选子类
            selectChilds(datas, row, "menuId", "parentId", true);

            // 勾选父类
            selectParentChecked(datas, row, "menuId", "parentId")

            // 刷新数据
            $table.bootstrapTable('load', datas);
        },

        onUncheck: function (row) {
            var datas = $table.bootstrapTable('getData');
            selectChilds(datas, row, "menuId", "parentId", false);
            $table.bootstrapTable('load', datas);
        },
        // bootstrap-table-treetreegrid.js 插件配置 -- end
    });
};

// 格式化按钮
function operateFormatter(value, row, index) {
    return [
        '<button type="button" class="RoleOfedit btn  btn-success btn-sm" ><i class="fa fa-pencil-square-o" ></i>&nbsp;编辑文章</button>',
        '<button type="button" class="RoleOfadd btn btn-info btn-sm" ><i class="fa fa-plus" ></i>&nbsp;新增文章</button>',
        '<button type="button" class="RoleOfdelete btn btn-danger btn-sm" ><i class="fa fa-trash-o" ></i>&nbsp;删除文章</button>'
    ].join('');

}

//初始化操作按钮的方法
window.operateEvents = {
    'click .RoleOfadd': function (e, value, row, index) {
        add(row.menuId);
    },
    'click .RoleOfdelete': function (e, value, row, index) {
        del(row.menuId);
    },
    'click .RoleOfedit': function (e, value, row, index) {
        update(row.menuId);
    }
};

/**
 * 选中父项时，同时选中子项
 * @param datas 所有的数据
 * @param row 当前数据
 * @param menuId menuId 字段名
 * @param parentId 父id字段名
 */
function selectChilds(datas, row, menuId, parentId, checked) {
    for (var i in datas) {
        if (datas[i][parentId] == row[menuId]) {
            datas[i].check = checked;
            selectChilds(datas, datas[i], menuId, parentId, checked);
        }
        ;
    }
}

function selectParentChecked(datas, row, menuId, parentId) {
    for (var i in datas) {
        if (datas[i][menuId] == row[parentId]) {
            datas[i].check = true;
            selectParentChecked(datas, datas[i], menuId, parentId);
        }
        ;
    }
}


function add(menuId) {
    layer.open({
        type: 2,
        title: '添加文章',
        shadeClose: true,
        shade: 0.8,
        maxmin: true,
        area: ['800px', '90%'],
        content: 'admin/menu/add.html/' //iframe的url
    });

}

$("#newAdd").click(function () {
    layer.open({
        type: 2,
        title: '添加文章',
        shadeClose: true,
        shade: 0.8,
        maxmin: true,
        area: ['800px', '90%'],
        content: 'admin/menu/add.html/' //iframe的url
    });

});

function del(menuId) {


}

function update(menuId) {
    layer.open({
        type: 2,
        title: '编辑文章',
        shadeClose: true,
        shade: 0.8,
        maxmin: true,
        area: ['800px', '90%'],
        content: 'admin/menu/edit.html/' //iframe的url
    });
}

