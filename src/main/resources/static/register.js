var countdown=60;
/**
 * 基础验证---手机号，密码格式验证
 */
$(document).ready(function(){
    $("#submit01").on("click", function() {
        if (!func()) {
            console.log("submit  fail...................!");
            return false;
        }
        var tel = $("#tel").val();//获取表单元素值
        var code = $("#code").val();
        var name = $("#name").val();
        var password = $("#password").val();
        var data = {
            tel : tel,
            code:code,
            password : password,
            name : name
        };
        $.ajax({
            type : "POST",
            url : "/register",
            data : data,
            dataType : 'json',
            success : function(msg) {
                if ("03"==msg.errorCode) {
                    console.log("submit................success");

                    alert("注册成功");
                    location.href = "/tologin";
                    window.setTimeout("reloadyemian();",18000);
                    return false;
                } else if("01"==msg.errorCode) {
                    alert("该会员已存在，立即登陆!");
                    console.log("注册失败，请重试!");
                    $("#myform")[0].reset();
                    return false;
                }
                else if("02"==msg.errorCode){
                    alert("验证码错误！");
                    console.log("验证码错误！");
                    $("#telDiv").attr("style","display:none;");
                    $("#imageDiv").attr("style","display:block;");
                    $("#password").attr("style","display:none;");
                    $("#submit").attr("style","display:none;");
                    reloadCode();
                    $("#volidate").val("");
                    $("#password").val("");
                    return false;
                }
            },
            error : function(XMLHttpRequest, textStatus, errorThrown) {
                // 状态码
                console.log(XMLHttpRequest.status);
                // 状态
                console.log(XMLHttpRequest.readyState);
                // 错误信息
                console.log(textStatus);
            }

        });

    });

    /**
     * 验证用户是否已经注册过
     * @returns {boolean}
     */
    function func() {

        var tel = $("#tel").val();//获取表单元素值
        var password = $("#password").val();
        if (!(/^1[34578]\d{9}$/.test(tel))) {
            alert("手机号输入错误");
            return false;
        }

        if(tel===""){
            alert("手机号不能为空！");
            //window.location.href="register.jsp";
            return false;
        }


        if(password==="")
        {
            alert("密码不能为空！");
            return false;
        }
        if(password.length<4){
            alert("密码不能少于4位！");
            return false;
        }

        return true;


    }


    /**
     * 图形验证码 切换验证码
     */
    function reloadCode() {//切换验证码
        var time = new Date().getTime();
        document.getElementById("imagecode").src = "validateImage?d=" + time;
    }



    /**
     *  发送验证码
     */
    $("#sendCode").on("click",function(){

        var tel=$("#tel").val();//获取手机号码
        var code = $("#code").val();//获取表单元素值
        if (!(/^1[34578]\d{9}$/.test(tel))) {
            alert("手机号输入错误");
            return false;
        }

        if(tel==""){
            alert("手机号不能为空！");
            //window.location.href="register.jsp";
            return false;
        }

        var obj = $("#sendCode");
        settime(obj);
        alert("手机验证码已经成功发送,请注意查收!");
        var data = {
            tel:tel,
        };
        $.ajax({
            type : "POST",
            url : "/createCode",
            data : data,
            dataType : 'json',
            success : function(msg) {
                if ("03"==msg.errorCode) {
                    console.log("success");
                    alert("注册成功");
                    return true;
                }
                else if("11"==msg.errorCode){
                    alert("提示：一个手机号一分钟只能获取一个验证码！");
                    return true;
                }
            }


        });

    });

    /**
     * 用来继续验证码时间相关
     * @param obj
     */
    function settime(obj) { //发送验证码倒计时
        if (countdown== 0) {
            obj.attr('disabled',false);
            //obj.removeattr("disabled");
            obj.val("免费获取验证码");
            countdown =3;
            return;
        } else {
            obj.attr('disabled',true);
            obj.val("重新发送(" + countdown + ")");
            countdown--;
        }
        setTimeout(function() {
                settime(obj) }
            ,1000)
    }

    /**
     * 图形验证码模块
     */
    $("#yanzheng").on("click",function(){
        var validateCode=$("#validateCode").val();
        var data={validateCode:validateCode};
        $.ajax({
            type:"POST",
            url : "/ValidateImage",
            data : data,
            dataType : 'json',
            success : function(msg) {
                if ("1"==msg.errorCode) {
                    $("#imageDiv").attr("style","display:none;");
                    $("#telDiv").attr("style","display:block;");
                    $("#submit").attr("style","display:block;");
                    $("#password").attr("style","display:block;");
                    $("#validateCode").val("");
                    return false;
                }else if("0"==msg.errorCode){
                    alert("图形验证码错误！输入正确可继续获取手机验证码！");
                    $("#validateCode").val("");
                    reloadCode();
                    return false;
                }
            }
        });
    });
//提交注册模块

})
