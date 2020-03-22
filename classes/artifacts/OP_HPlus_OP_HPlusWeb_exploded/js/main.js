$(function () {
	
	$.ajax({
		url:"UserServlet",
		method:"post",
		data:"method=findUserBySession",
		dataType:"JSON",
		success:function(user){
			$("#loginName").text(user.loginName);
			$("#deptName").text(user.deptName);
		}
	});
	
// 清理缓存
	$(".clearCache").click(function() {
        window.sessionStorage.clear();
        window.localStorage.clear();
        var index = layer.msg('正在清理缓存。。。', {
            icon : 16,
            time : false,
            shade : 0.8
        });
        setTimeout(function() {
            layer.close(index);
            layer.msg("清理成功");
        }, 1000);
    });


	//锁屏
    $(".lockcms").on("click", function() {
        window.localStorage.setItem("lockcms", true);
        lockPage();
    })

    function lockPage() {
        layer
            .open({
                title : false,
                scrollbar : false,
                type : 1,
                area : [ '400px', '300px' ],
                content : '<form  class="form-horizontal"  >'
                    + '<div  id="lock-box" class="text-center">'
                    + '<div ><img src="img/a1.jpg" class="userAvatar img-thumbnail" height="100px" width="100px"/></div>'
                    + '<div class="alert alert-success col-sm-10 col-sm-offset-1" >请输入解锁密码</div>'
                    + '<div class="form-group">'
                    + '<div class="col-sm-6 col-sm-offset-1">'
                    + '<input type="password" class="form-control lock"   autocomplete="off" placeholder="请输入解锁密码" name="lockPwd" id="lockPwd" />'
                    + '</div>'
                    + '<button class="btn btn-primary col-sm-4" id="unlock">解锁</button>'
                    + '</div>' + '</div>' + '</form>',
                closeBtn : 0,
                shade : 0.9,
                success : function() {

                }
            })
        $(".lock").focus();
    }


    if (window.localStorage.getItem("lockcms") == "true") {
        lockPage();
    }

    //解锁
    $("body").on("click", "#unlock", function() {
        if ($(".lock").val() == '') {
            layer.msg("请输入密码");
            $(this).siblings(".lock").focus();
        } else {
            if ($(".lock").val() == "123456") {
                window.localStorage.setItem("lockcms", false);
                $(this).siblings(".lock").val('');
                layer.closeAll("page");
            } else {
                layer.msg("密码错误，请重试");
                $(this).siblings(".lock").val('').focus();
            }
        }
    });
    $(document).on('keydown', function(event) {
        var event = event || window.event;
        if (event.keyCode == 13) {
            $("#unlock").click();
        }
    });

});