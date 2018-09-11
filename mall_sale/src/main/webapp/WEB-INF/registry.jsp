<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@page isELIgnored="false"  %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath %>">
    <link rel="stylesheet" type="text/css" href="css/sign.css">
    <link rel="shortcut icon" type="image/icon" href="images/jd.ico">
<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
    function checkName(username) {
        var $div = $("#userName").parent();
        if(username == ""){
            $div.attr("class","box error");
            $(".username").text("用户名不能为空");
        }else {
            $.post("checkName.do", {yh_mch: username}, function (data) {
                if (data == "true") {
                    $div.attr("class", "box error");
                    $(".username_tip").attr("class","username_tip tip error");
                    $(".username_span").text("用户名已存在");
                } else {
                    $div.attr("class", "box right");
                    $(".username_tip").attr("class","username_tip tip");
                    $(".username_span").text("");
                }
            });
        }
    }

    function checkPw(pw) {
        if(pw == ""){
            $div2.attr("class","box error");
            $(".pwd2_tip").attr("class","pwd2_tip tip error");
            $(".pwd2_span").text("密码不能为空");
        }else {
            var pw1 = $("#pwd").val();
            var $div1 = $("#pwd").parent();
            var $div2 = $("#pwd2").parent();
            if(pw1 != pw){
                $div2.attr("class","box error");
                $(".pwd2_tip").attr("class","pwd2_tip tip error");
                $(".pwd2_span").text("两次密码输入不一致");
            }else {
                $div2.attr("class","box right");
                $(".pwd2_tip").attr("class","pwd2_tip tip");
                $(".pwd2_span").text("");
            }
        }
    }
    function registry(dom) {
       if($("#ck").attr("checked") != "checked") {
           alert("请仔细阅读协议！");
       }else {
           if($(".error").size()>0){
               alert("表单填写有误，请检查！")
           }else {
              $("#userform").submit();
           }
       }

    }
    // 1、获得焦点，内容为空，tip默认提示
    // 2、失去焦点，内容为空，tip为隐藏
    // 3、其他情况（按键抬起，失去焦点且内容不为空，或最后表单总验证）
    //    按键抬起为空，报错，不能为空
    //    内容匹配，成功
    //    内容不匹配，失败
    // 4、密码要进行安全等级检测，含数字、字母、特殊字符为强，两种为中，一种为弱
    // 5、确认密码失去焦点的时候就要验证是否一致
</script>
<title>硅谷商城</title>
</head>
<body>
<!--头部-->
<div class="header">
    <a class="logo" href="##"></a>
    <div class="desc">欢迎注册</div>
</div>
<!--版心-->
<div class="container">
    <form id="userform" action="registry.do" method="post">
    <!--京东注册模块-->
    <div class="register">
        <!--用户名-->
        <div class="register-box">
            <!--表单项-->
            <div class="box default">
                <label for="userName">用&nbsp;户&nbsp;名</label>
                <input type="text" id="userName" placeholder="您的账户名和登录名" name="yh_mch" onblur="checkName(this.value)"/>
                <i></i>
            </div>
            <!--提示信息-->
            <div class="username_tip tip">
                <i></i>
                <span class="username_span"></span>
            </div>
        </div>
        <!--设置密码-->
        <div class="register-box">
            <!--表单项-->
            <div class="box default">
                <label for="pwd">设 置 密 码</label>
                <input type="password" id="pwd" placeholder="建议至少两种字符组合" name="yh_mm" />
                <i></i>
            </div>
            <!--提示信息-->
            <div class="tip">
                <i></i>
                <span></span>
            </div>
        </div>
        <!--确认密码-->
        <div class="register-box">
            <!--表单项-->
            <div class="box default">
                <label for="pwd2">确 认 密 码</label>
                <input type="password" id="pwd2" placeholder="请再次输入密码" onblur="checkPw(this.value)" name="mm2"/>
                <i></i>
            </div>
            <!--提示信息-->
            <div class="pwd2_tip tip">
                <i></i>
                <span class="pwd2_span"></span>
            </div>
        </div>
        <!--设置密码-->
        <div class="register-box">
            <!--表单项-->
            <div class="box default">
                <label for="email">邮 箱 验 证</label>
                <input type="text" id="email" placeholder="请输入邮箱" name="yh_yx"/>
                <i></i>
            </div>
            <!--提示信息-->
            <div class="tip">
                <i></i>
                <span ></span>
            </div>
        </div>
        <!--手机验证-->
        <div class="register-box">
            <!--表单项-->
            <div class="box default">
                <label for="mobile">手 机 验 证</label>
                <input type="text" id="mobile" placeholder="请输入手机号" name="yh_shjh"/>
                <i></i>
            </div>
            <!--提示信息-->
            <div class="tip">
                <i></i>
                <span></span>
            </div>
        </div>
        <!--注册协议-->
        <div class="register-box xieyi">
            <!--表单项-->
            <div class="box default">
                <input type="checkbox" id="ck" />
                <span>我已阅读并同意<a href="##">《京东用户注册协议》</a></span>
            </div>
            <!--提示信息-->
            <div class="tip">
                <i></i>
                <span></span>
            </div>
        </div>
        <!--注册-->
        <button id="btn" type="button" onclick="registry()">注册</button>
    </div>
    </form>
</div>
</body>
</html>
