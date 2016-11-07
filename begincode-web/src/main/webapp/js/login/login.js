function view_login(){
	layer.open({
		type : 2,
		title : '',
		shadeClose : true,
		shade : false,
		area : [ '400px', '400px' ],
		content : '/user/view_login.htm'
	});
}
function view_register(){
	layer.open({
		type : 2,
		title : '',
		shadeClose : true,
		shade : false,
		area : [ '680px', '500px' ],
		content : '/user/view_register.htm'
	});
}