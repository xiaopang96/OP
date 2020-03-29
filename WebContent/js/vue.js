
var vm = new Vue({
    el:'.gray-bg',
    data:{

    },
    //为什么方法跳不过去呢？
    methods:{
        updatePassword(){
            /*$.modal.fn1();
            $.modal.openHtml("修改密码","修改密码",'')*/
            layer
                .open({
                    content: "修改密码",
                    type: 1,
                    title: ["修改密码"],
                    btn: ['确定', '取消'],
                    skin:"layui-layer-molv",
                    area:["500px","300px"]
                });
        },
        /*reload(){
            layer.open({
                    content : '刷新'
            })
        },*/
        findDetail(){
            //$.modal.openHtml("查看详情","查看详情",'')
            layer
                .open({
                    content: "查看详情",
                    type: 1,
                    title: ["查看详情"],
                    btn: ['确定', '取消'],
                    skin:"layui-layer-molv",
                    area:["500px","300px"]
                });
        },
        signOut(){
            var b =confirm("是否确认退出");
            if(b){
                window.location.href="WEB-INF/lib/druid-1.1.11.jar!/support/http/resources/login.html";
                //window.location.href="login.jsp"
            }
        },
        lockScreen(){
            window.location.href="lockscreen.html";
        },
        fullScreen(){
            if (!document.fullscreenElement && // alternative standard method
                !document.mozFullScreenElement && !document.webkitFullscreenElement) {// current working methods
                if (document.documentElement.requestFullscreen) {
                    document.documentElement.requestFullscreen();
                } else if (document.documentElement.mozRequestFullScreen) {
                    document.documentElement.mozRequestFullScreen();
                } else if (document.documentElement.webkitRequestFullscreen) {
                    document.documentElement.webkitRequestFullscreen(Element.ALLOW_KEYBOARD_INPUT);
                }
            } else {
                if (document.cancelFullScreen) {
                    document.cancelFullScreen();
                } else if (document.mozCancelFullScreen) {
                    document.mozCancelFullScreen();
                } else if (document.webkitCancelFullScreen) {
                    document.webkitCancelFullScreen();
                }
            }
        },
        clearCache(){
            var b =confirm("确认清除缓存吗？");
            if(b){
                localStorage.clear();
                sessionStorage.clear();
            }
        },
        refresh(){
            //实现子窗口中的页面刷新，就是让iframe中的src属性值重新赋值一次
            $("iframe").each(function(index,ele){
                var displayValue = $(ele).css("display");
                if(displayValue=="inline"){
                    //获取原来的src的值
                    var srcValue= $(ele).prop("src");
                    $(ele).prop("src",srcValue);
                }
            });
        }
    }
});

// 清理缓存
$(".reload").click(function() {
    window.sessionStorage.clear();
    window.localStorage.clear();
    var index = layer.msg('正在刷新。。。', {
        icon : 16,
        time : false,
        shade : 0.8
    });
    setTimeout(function() {
        layer.close(index);
        layer.msg("刷新成功");
    }, 1000);
});
