//显示login弹窗！
var layerIndex;
function view_login(){
	layerIndex=layer.open({
		type : 2,
		title : '',
		shadeClose : true,
		shade : false,
		area : [ '400px', '400px' ],
		content : '/user/view/login/login.htm'
	});
	parent.layer.close(layerIndex-1);
}
//显示register弹窗！
function view_register(){
	layerIndex=layer.open({
		type : 2,
		title : '',
		shadeClose : true,
		shade : false,
		area : [ '680px', '500px' ],
		content : '/user/view/login/register.htm'
	});
	parent.layer.close(layerIndex-1);
}
//提交注册表单！
function form_register_submit(form){
	if(validate_form(form)){
		$.ajax({
			data:$(form).serialize(),
			dataType:"json",
			url:"/user/registerUser.htm",
			success : function(data) {
				if(data.data==true){
//					parent.location.reload();//刷新主页面  ########可选
					parent.layer.close(layerIndex);
					check_loginStauts();//检查是否登录
				}
			}
		});
	}else{
		img_change($(".vcode_img"));
	}
}
//可选非法字符
//else if(!/^[a-z\d\u4E00-\u9FA5]+$/.test(nname.val())){
//	add_remind(nname,'昵称包含非法字符')
//	return false;
//}
//验证form表单！
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
	} else if(nname.val().indexOf("nbsp;")>0){
		add_remind(nname,'昵称包含非法字符')
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
//红色提醒字！
function add_remind(jObject,text){
	jObject.parent().append('<div class="h5_valid_tip"><p>'+text+'</p></div>');
	jObject.parent().addClass('h5_valid_wrap');
	 jObject.on('keyup',function(){
        jObject.parent().removeClass('h5_valid_wrap');
        jObject.parent().find('.h5_valid_tip').remove();
        jObject.off('keyup');
    });
	}
//改变验证图片！
function img_change(dom){
	dom.src="/image/vcode.htm?t="+Math.random();  
}
//登录！
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
			if(data.data==true){
//				parent.location.reload();//登录成功刷新主页面  ￥￥￥￥￥￥￥可选可控制
				parent.layer.close(layerIndex);
				check_loginStauts(); //登录成功检查一次
			};
		}
	});	
}
//检查登录状态 true表示成功登录！
function check_loginStauts(){
	$.ajax({
		dataType:"json",
		url:"/user/checkLogin.htm",
		success : function(data) {
			if(data.data==true){
				changeLoignDiv(true);
			}else{
				changeLoignDiv(false);
			}
		}
	});	
}

//退出登录!
function exitLogin(){
	alert("准备退出");
	$.ajax({
		type:"post",
		url:"/user/loginClean.htm",
		success : function(data){
			check_loginStauts();
		}
	});
}
//修改div登录显示
function changeLoignDiv(boolen){
	var _html;
	if(boolen==false){
	  _html=$("#failLoginDiv").html();
	  $("#simpleLogin").html(_html);
	  return;
	}else{
	  _html=$("#successLoginDiv").html();
	}
	$.ajax({
		dataType:"json",
		url:"/user/userInfo.htm",
		success : function(data){
			var user=data.data;
			disposeDiv(_html,user);
			if(user!=null){
				var d="\[(.*?)\]";
			}
		}
	});
}
//替换div中的参数
function disposeDiv(_html,array){
	var reg = new RegExp(/\[(.*?)\]/,'igm');
	_html=_html.replace(reg,function(node, key){
		return array[key];
	});
	$("#simpleLogin").html(_html);
}
