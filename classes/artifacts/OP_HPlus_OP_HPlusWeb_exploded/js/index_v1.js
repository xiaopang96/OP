$(function () {
    //获取当前日期并且格式化

    var today = new Array('星期日', '星期一', '星期二', '星期三', '星期四', '星期五', '星期六');

    function format(data) {
        if(data<10){
            return "0"+data;
        }
        return data;
    }

    function dateFrmat(loginName) {
        var now = new Date();
        var year = now.getFullYear();
        var mouth = now.getMonth() + 1;
        var date = now.getDate();
        var hour = now.getHours();
        var greeting='';
        if(hour>=6&&hour<=8){
            greeting="早上好！"
        }else if(hour>8&&hour<=10){
            greeting="上午好！"
        }else if(hour>10&&hour<=14){
            greeting="中午好！"
        }else if(hour>14&&hour<=18){
            greeting="下午好！"
        }else{
            greeting="晚上好！"
        }
        var munites = now.getMinutes();
        var second = now.getSeconds();
        var day = now.getDay();
        var week = today[day];

        var nowTime =year + "年" + format(mouth) + "月" + format(date) + "日" +
            "" + format(hour) + "：" + format(munites) + "：" + format(second) ;
        var str = "亲爱的"+loginName+"，"+greeting+" 欢迎使用万和CMS管理系统。当前时间为:"+'<span>&nbsp;&nbsp;&nbsp;&nbsp;</span>'  + nowTime+ " " + week;
        $("#nowTime").html(str);
        setTimeout(dateFrmat(loginName),1000);
        return nowTime;
    }


	$.ajax({
		url:"UserServlet",
		method:"post",
		data:"method=findUserBySession",
		dataType:"JSON",
		success:function(user){
		      //登录时间
		    var loginTime = dateFrmat(user.loginName);

		    $(".loginTime").html(loginTime.split("日")[0]+'日'+'<br>'+loginTime.split("日")[1]+"<br>");

		}
	});
    




});