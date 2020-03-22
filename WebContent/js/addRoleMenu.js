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
//jquery验证
$("form").validate({
    //规则
    rules: {
        txtName: {
            required: true,
            rangelength: [4, 10]
        },
        power: {
            required: true,

        },
        showSort: {
            required: true,
        }

    },
    //提示消息，如果不加有默认消息，默认消息在messages_zh.js中

    //设置统一提交处理，如果不加此行，则采用form默认提交
    submitHandler: function (form) {

        form.submit();
    }
});