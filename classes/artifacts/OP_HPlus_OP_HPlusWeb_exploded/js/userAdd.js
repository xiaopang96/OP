var zNodes;
$.ajax({
    post: "get",
    url: "data/menuTree.json",
    dataType: 'json',
    success: function (data) {
        zNodes = data;
        $.fn.zTree.init($("#menuTrees"), setting, zNodes);
        var treeObj = $.fn.zTree.getZTreeObj("menuTrees");
        treeObj.expandAll(false);
    }
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