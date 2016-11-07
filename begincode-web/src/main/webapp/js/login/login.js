//显示login弹窗
function view_login(){
	var layerIndex=layer.open({
		type : 2,
		title : '',
		shadeClose : true,
		shade : false,
		area : [ '400px', '400px' ],
		content : '/user/view/login/login.htm'
	});
	parent.layer.close(layerIndex-1);
}
//显示register弹窗
function view_register(){
	var layerIndex=layer.open({
		type : 2,
		title : '',
		shadeClose : true,
		shade : false,
		area : [ '680px', '500px' ],
		content : '/user/view/login/register.htm'
	});
	parent.layer.close(layerIndex-1);
}
//提交表单
function form_submit(form){
	if(validate_form(form)){
		$.ajax({
			data:$(form).serialize(),
			dataType:"json",
			url:"/user/registerByUsername.htm",
			success : function(data) {
				parent.location.reload();//属性主页面
				check_loginStauts();//检查是否登录
			}
		});
	}else{
		img_change($(".vcode_img"));
	}
}
//验证form表单
function validate_form(form){
	var uname=$(form.username);
	var nname=$(form.nickname);
	var pwd=$(form.password);
	var cpwd=$(form.checkpassword);
	var email=$(form.email);
	if(uname.val() == ''){
        add_remind(uname,'请输入用户名');
        return false;
    }else if(uname.val().length<6||uname.val().length>15){
		add_remind(uname,'长度应为6~15位之间')
		return false;
	}else if(!/^[a-zA-Z0-9_]{6,15}$/.test(uname.val())){
		add_remind(uname,'包含非法字符')
		return false;
	}else if(nname.val()==''){
		add_remind(nname,'请输入用户昵称')
		return false;
	}else if(!/^[a-z\d\u4E00-\u9FA5]+$/.test(nname.val())){
		add_remind(nname,'包含非法字符')
		return false;
	}else if(pwd.val() == ''){
        add_remind(pwd,'请输入密码');
        return false;
    }else if(cpwd.val() == ''){
        add_remind(cpwd,'请再次输入密码');
        return false;
    }else if(pwd.val().length < 6 || pwd.val().length > 20){
        add_remind(pwd,'密码应为6~20位之间');
        return false;
    }else if(pwd.val() != cpwd.val()){
        add_remind(pwd,'两次密码不一致');
        add_remind(cpwd,'两次输入的密码不一样');
        return false;
    }else if(email.val() == ''){
        add_remind(email,'请输入邮箱');
        return false;
    }else if(!/\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*/.test(email.val())){
		add_remind(email,'邮箱格式不正确')
		return false;
	}
	return true;
	}
//红色提醒字
function add_remind(jObject,text){
	jObject.parent().append('<div class="h5_valid_tip"><p>'+text+'</p></div>');
	jObject.parent().addClass('h5_valid_wrap');
	 jObject.on('keyup',function(){
        jObject.parent().removeClass('h5_valid_wrap');
        jObject.parent().find('.h5_valid_tip').remove();
        jObject.off('keyup');
    });
	}
//改变验证图片
function img_change(dom){
	dom.src="/image/vcode.htm?t="+Math.random();  
}
//登录
function login_submit(form) {
	var uname=$(form.username);
	var pwd=$(form.password);
	if(uname.val()==""){
		 add_remind(uname,'请输入用户名');
	     return false;
	}else if(pwd.val()==""){
		  add_remind(pwd,'请输入密码');
	       return false;
	}
	$.ajax({
		data:$(form).serialize(),
		dataType:"json",
		url:"/user/loginUser.htm",
		success : function(data) {
			if(data.data.success==null){
				alert(data.data.error);
			}else{
				parent.location.reload();
				check_loginStauts();
			};
		}
	});	
}
//检查登录状态 true表示成功登录
function check_login(){
	$.ajax({
		dataType:"json",
		url:"/user/checkLogin.htm",
		success : function(data) {
			alert(data.data);
			if(data.data==true){
				changeLoignDiv(true);
			}
		}
	});	
}
//修改div登录显示
function changeLoignDiv(boolen){
	var nickname;
	$.ajax({
		url:"/user/getLoginUser.htm",
		dataType:"json",
		success:function(data){
			nickanem=data.data.nickname;
			if(boolen==true){
				var changeDiv='<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">欢迎:'+nickanem+'<span class="caret"></span></a>'+
			    '<ul class="dropdown-menu">'+
			    ' <li><a href="javascript:void(0)">博文管理</a></li>'+
			    ' <li role="separator" class="divider"></li>'+
			    ' <li><a href="javascript:void(0)" onclick="login_delete()">退出</a></li>'+
			    ' </ul>';
				$("#simpleLogin",parent.document).html(changeDiv);
			}
			if(boolen==false){
				var changeDiv='<a href="javascript:void(0)" onclick="login_sumbit_show()">登录</a> '+
				'<a href="javascript:void(0)" onclick="register_sumbit_show()">注册</a>';
				$("#simpleLogin",parent.document).html(changeDiv);
			}
		}
	});
	
	
}
function register_sumbit_show() {
	parent.layer.open({
		type : 2,
		title : '',
		shadeClose : true,
		shade : false,
		area : [ '400px', '500px' ],
		content : '/user/register_view.htm'
	});
}
function login_sumbit_show() {
	parent.layer.open({
		type : 2,
		title : '',
		shadeClose : true,
		shade : false,
		area : [ '400px', '400px' ],
		content : 'http://www.begincode.net/user/login_view.htm'
	});
}
