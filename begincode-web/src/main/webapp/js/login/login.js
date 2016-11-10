//显示login弹窗
var layerIndex;
function view_login() {
	layerIndex = layer.open({
		type : 2,
		title : '',
		shadeClose : true,
		shade : false,
		area : [ '400px', '400px' ],
		content : '/user/view/login/login.htm'
	});
	layer.close(layerIndex - 1);
}
// 显示register弹窗
function view_register() {
	layerIndex = layer.open({
		type : 2,
		title : '',
		shadeClose : true,
		shade : false,
		area : [ '680px', '500px' ],
		content : '/user/view/login/register.htm'
	});
	layer.close(layerIndex - 1);
	img_change($(".vcode_img"));
}
// 提交注册表单
function form_register_submit(form) {
	if (validate_form(form)) {
		$.ajax({
			data : $(form).serialize(),
			type : "post",
			dataType : "json",
			url : "/user/login/registerUser/client.htm",
			success : function(data) {
				if (data.data == true) {
					// parent.location.reload();//刷新主页面 ########可选
					parent.layer.close(layerIndex);
					check_loginStauts();// 检查是否登录
				} else {
					img_change($(".vcode_img"));
				}
			}
		});
	} else {
		img_change($(".vcode_img"));
	}
}
// 可选非法字符
// else if(!/^[a-z\d\u4E00-\u9FA5]+$/.test(nname.val())){
// add_remind(nname,'昵称包含非法字符')
// return false;
// }
// 验证form表单！
function validate_form(form) {
	var uname = $(form.username);
	var nname = $(form.nickname);
	var pwd = $(form.password);
	var cpwd = $(form.checkpassword);
	var email = $(form.email);
	if (uname.val() == '') {
		add_remind(uname, '请输入用户名');
		return false;
	} else if (uname.val().length < 6 || uname.val().length > 15) {
		add_remind(uname, '长度应为6~15位之间')
		return false;
	} else if (!/^[a-zA-Z0-9_]{6,15}$/.test(uname.val())) {
		add_remind(uname, '包含非法字符')
		return false;
	} else if (nname.val() == '') {
		add_remind(nname, '请输入用户昵称')
		return false;
	} else if (nname.val().indexOf("nbsp;") >= 0) {
		add_remind(nname, '昵称包含非法字符')
		return false;
	} else if (pwd.val() == '') {
		add_remind(pwd, '请输入密码');
		return false;
	} else if (cpwd.val() == '') {
		add_remind(cpwd, '请再次输入密码');
		return false;
	} else if (pwd.val().length < 6 || pwd.val().length > 20) {
		add_remind(pwd, '密码应为6~20位之间');
		return false;
	} else if (pwd.val() != cpwd.val()) {
		add_remind(pwd, '两次密码不一致');
		add_remind(cpwd, '两次输入的密码不一样');
		return false;
	} else if (email.val() == '') {
		add_remind(email, '请输入邮箱');
		return false;
	} else if (!/\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*/.test(email.val())) {
		add_remind(email, '邮箱格式不正确')
		return false;
	}
	return true;
}
// 红色提醒字！
function add_remind(jObject, text) {
	jObject.parent().append(
			'<div class="h5_valid_tip"><p>' + text + '</p></div>');
	jObject.parent().addClass('h5_valid_wrap');
	jObject.on('keyup', function() {
		jObject.parent().removeClass('h5_valid_wrap');
		jObject.parent().find('.h5_valid_tip').remove();
		jObject.off('keyup');
	});
}
// 改变验证图片！
function img_change(dom) {
	dom.src = "/image/vcode.htm?t=" + Math.random();
}

// 检查登录状态 true表示成功登录！
function check_loginStauts() {
	$.ajax({
		dataType : "json",
		url : "/user/check/checkLogin.htm",
		success : function(data) {
			if (data.data == true) {
				changeLoignDiv(true);
			}
		}
	});
}

// 登录！
function login_submit(form) {
	var uname = $(form.username);
	var pwd = $(form.password);
	if (uname.val() == "") {
		add_remind(uname, '请输入用户名');
		return false;
	} else if (pwd.val() == "") {
		add_remind(pwd, '请输入密码');
		return false;
	}
	$.ajax({
		data : $(form).serialize(),
		dataType : "json",
		url : "/user/login/loginUser/client.htm",
		success : function(data) {
			if (data.data == true) {
				parent.layer.close(layerIndex);
				check_loginStauts(); // 登录成功检查一次
			}
			;
		}
	});
}

// 退出登录!
function exitLogin() {
	$.ajax({
		type : "post",
		url : "/user/loginClean.htm",
		success : function(data) {
			changeLoignDiv(false);
		}
	});
}
// 修改div登录显示!
function changeLoignDiv(boolen) {
	var _html;
	if (boolen == false) {
		_html = $("#failLoginDiv").html();
	} else {
		_html = $("#successLoginDiv").html();
	}
	$.ajax({
		type : "post",
		dataType : "json",
		url : "/user/userInfo.htm",
		success : function(data) {
			var user = data.data;
			disposeDiv(_html, user);
		}
	});
}
// 替换div中的参数!
function disposeDiv(_html, array) {
	var reg = new RegExp(/\[(.*?)\]/, 'igm');
	_html = _html.replace(reg, function(node, key) {
		return array[key];
	});
	$("#simpleLogin").html(_html);
}

// QQ登录页面打开
function login_submitByQQ() {
	QC.Login.showPopup();
	layer.close(layerIndex);
}
// QQ登录成功会回调
var paras = {};
QC.api("get_user_info", paras).success(
		function(s) {// 成功回调
			reqd = s.data;
			QC.Login.getMe(function(openId, accessToken) {
				regUser(reqd.nickname, reqd.figureurl, reqd.gender,
						reqd.province, reqd.city, reqd.year, openId,
						accessToken);
			});
		});
// QQ注册或登录
function regUser(nickName, figureurl, gender, province, city, year, openId,
		accessToken) {
	jQuery.ajax({
		type : "POST",
		url : "/user/login/loginUser/QQ.htm",
		data : "nickname=" + nickName + "&pic=" + figureurl + "&sex=" + gender
				+ "&openId=" + openId + "&accessToken=" + accessToken,
		dataType : "json",
		success : function(data) {
			QC.Login.signOut();
			location.reload();
		}
	});

}
