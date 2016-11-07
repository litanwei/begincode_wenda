<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="${ctx}/css/login.css" rel="stylesheet">
</head>
<body>
<div class="main" id="main">
    <div class="panel reg_panel" id="regPanel">
        <div class="form_reg">
            <div class="header fix">
                <h2>注册</h2>
            </div>
            <form class="form form1 tabs_content" method="post">
                <div class="tr input_wrap loginname_wrap">
                    <input  title="请输入用户名" type="text" name="username" id="username" placeholder="用户名">
                </div>
                  <div class="tr input_wrap loginname_wrap">
                    <input  title="请输入用户昵称" type="text" name="nickname" id="nickname" placeholder="用户昵称">
                </div>
                <div class="tr input_wrap passwd_wrap">
                    <input title="别忘了密码" type="password" name="password" id="password" placeholder="密码">
                </div>
                <div class="tr input_wrap check_passwd_wrap">
                    <input  title="别忘了密码" type="password" name="checkpassword" id="checkpassword" placeholder="确认密码">
                </div>
                <div class="tr input_wrap email_wrap">
                    <input title="别忘了邮箱" type="text" name="email" id="email" placeholder="邮箱地址">
                </div>
                <div class="tr input_wrap vcode_wrap">
                    <input title="别忘了验证码" class="vcode" type="text" name="vcode" id="regvcode" placeholder="验证码">
                    <img src="/image/vcode.htm" class="vcode_img" title="看不清？点击换一张！" style="visibility: visible;" onclick="window.parent.img_change(this)">
                </div>
                <div class="tr rule_wrap">
                </div>
                <div class="tr submit_wrap">
                    <a class="submit" onclick="reg_submit()">注 册</a>
                </div>
            </form>
            <div class="quick_nav tr">
                <a class="go_login" onclick="window.parent.view_login()">直接登录 &gt;</a>
            </div>
        </div>
        <div class="divider_line"></div>
        <div class="oauth_login">
            <div class="title">授权登录</div>
            <div class="oauth_frequent fix">
                <a data-type="3" class="btn qq_btn" onclick="qq_login()"><b></b>QQ登录</a>
            </div>
        </div>
    </div>
</div>
</body>
</html>