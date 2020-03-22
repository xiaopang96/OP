$(function () {


    var zNodes;
//菜单数据加载

    $.ajax({
        post: "get",
        url: "data/deptTree.json",
        dataType: 'json',
        success: function (data) {
            zNodes = data;
            $.fn.zTree.init($("#menuTrees"), setting, zNodes);
            var treeObj = $.fn.zTree.getZTreeObj("menuTrees");
            treeObj.expandAll(false);
        }
    });


    $("#btnExpand").click(function () {
        var treeObj = $.fn.zTree.getZTreeObj("menuTrees");
        treeObj.expandAll(true);
    });

    $("#btnCollapse").click(function () {
        var treeObj = $.fn.zTree.getZTreeObj("menuTrees");
        treeObj.expandAll(false);
    });

    var setting = {
        check: {
            enable: true
        },
        data: {
            simpleData: {
                enable: true
            }
        }
    };

    //关键词搜索框折叠切换
    $(".treeShowHideButton").click(function () {
        $('#search').slideToggle(200);
        $('#btnShow').toggle();
        $('#btnHide').toggle();
        $('#keyword').focus();
    });

});