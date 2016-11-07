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
 <div class="main login_main" id="main">
        <div class="panel login_panel fix" id="loginPanel">
            <div class="form_login">
                <form class="form" method="post" id="loginForm">
                    <div class="clear">&nbsp;</div>
                    <div class="tr input_wrap loginname_wrap">
                        <i class="login_username_icon"></i>
                        <input value="" type="text" title="用户名" class="loginname" name="username" id="username" placeholder="用户名-可使用3dm论坛帐号登录"><i class="iconfont mobile_ico"></i>
                        <i class="iconfont email_ico"></i>
                    </div>
                    <div class="tr input_wrap password_wrap">
                    <i class="login_userpas_icon"></i>
                        <div class="password_wrap1">
                            <input value="" required="required" title="别忘了密码" type="password" class="password" name="password" id="password" placeholder="密码">
                        </div>
                    </div>
                    
                    <ul class="quick_nav fix">
                        <li class="first">
                            <input value="" type="checkbox" name="remember" checked="checked" class="common_chk">记住我 
                        </li>
                        <li style="text-align: right;">
                            <a href="http://yeyou.3dmgame.com/account/findpassword" target="_blank" class="forget">忘记密码?</a>
                        </li>
                      
                    </ul>
                    <div class="tr submit_wrap">
                        <a class="submit" onclick="window.parent.login_submit(loginForm)">登 录</a>
                        <a class="go_reg" onclick="window.parent.view_register()">注册</a>
                    </div>
                    <div class="QQ_login" onclick="qq_login()"><i class="QQicon_login"></i><span class="make_lgoin">使用QQ登录</span></div>
                </form>
            </div>
        </div>
    </div>
</body>
</html>