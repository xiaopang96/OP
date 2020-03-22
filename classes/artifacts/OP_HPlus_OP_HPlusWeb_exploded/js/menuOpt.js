$(function () {

    $("#treeName").click(function () {
        layer.open({
            type: 2,
            title: '选择菜单',
            shadeClose: true,
            shade: 0.8,
            area: ['400px', '90%'],
            content: 'admin/menu/parentMenu.html/' //iframe的url
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