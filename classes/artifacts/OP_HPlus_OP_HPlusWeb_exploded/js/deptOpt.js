$(function () {

    $("#treeName").click(function () {
        layer.open({
            type: 2,
            title: '选择部门',
            shadeClose: true,
            shade: 0.8,
            area: ['400px', '90%'],
            content: 'admin/dept/parentDept.html/' //iframe的url
        });
    });

//    选择图标
    $("#icon").click(function () {
        layer.open({
            type: 2,
            title: '选择图标',
            shadeClose: true,
            shade: 0.8,
            area: ['400px', '90%'],
            content: 'admin/menu/icon.html/' //iframe的url
        });
    });

});