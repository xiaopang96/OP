(function($) {
    $.extend({
        model:{
            fn1:function () {
                alert("我是扩展的方法！")
            },
            openHtml(title,content,callback){
                layer
                    .open({
                        content: content,
                        type: 1,//可传入的值有：0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（tips层）
                        title: [title],
                        btn: ['确定', '取消'],
                        skin:"layui-layer-molv",
                        area:["500px","300px"],
                        yes: callback
                    });
            },
        },
        common:{
            setTreeData(source, id, parentId, children) {
                let cloneData = JSON.parse(JSON.stringify(source));
                let tree = cloneData.filter(father => {
                    let branchArr = cloneData.filter(child => {
                        return father[id] == child[parentId]
                    });
                    if (branchArr.length > 0) {
                        father[children] = branchArr
                    }
                    return father[parentId] == 0    //如果第一层不是parentId=0，请自行修改
                });
                return tree;
            }
        }
    });
})(jQuery);