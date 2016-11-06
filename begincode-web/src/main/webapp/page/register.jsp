<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="${ctx}/js/jquery/jquery-3.1.0.min.js"></script>
<script src="${ctx}/js/layer/layer.js"></script>
<script src="${ctx}/js/login/login.js"></script>
<link href="${ctx}/css/register.css" rel="stylesheet">
</head>
<body>
<div class="form_reg">
            <div class="header fix">
                <h2>注册</h2>
            </div>
            <form class="tabs_content" method="post" onsubmit="reg_submit(this)" id="formdata" >
                <div class="tr input_wrap loginname_wrap" id="tttttt">
                    <input autocomplete="off" title="请输入登录名" class="loginname" type="text" name="username" id="username" placeholder='用户名'/>
                </div>
                <div class="tr input_wrap loginname_wrap">
                    <input autocomplete="off" title="请输入用户昵称" class="nickname" type="text" name="nickname" id="nickname" placeholder='用户昵称'/>
                </div>
                <div class="tr input_wrap passwd_wrap">
                    <input autocomplete="off" title="别忘了密码" class="passwd" type="password" name="password" id="password" placeholder='密码'>
                </div>
                <div class="tr input_wrap check_passwd_wrap">
                    <input autocomplete="off" title="别忘了密码" class="checkpasswd" type="password" name="checkpassword" id="checkpassword" placeholder='确认密码'>
                </div>
                <div class="tr input_wrap email_wrap">
                    <input requiredtitle="别忘了邮箱" patterntitle="邮箱格式不正确" class="email" type="text" name="email" id="email" placeholder='邮箱地址'>
                </div>
                <div class="tr input_wrap vcode_wrap">
                    <input title="别忘了验证码" class="vcode" type="text" name="vcode" id="vcode" placeholder='验证码'>
                    <img src="/user/valicode.htm" id="vcode_img" class="vcode_img" title="看不清？点击换一张！" style="visibility: visible" onclick="changeImg(this)" >
                </div>
                <div class="tr submit_wrap">  
                    <a class="submit" onclick="reg_submit(formdata)">注 册</a>
                </div>
            </form>
            <div class="quick_nav tr">
                <a class="go_login" onclick="login_sumbit_show()" >直接登录 &gt;</a>
            </div>
        </div>
</body>
</html>