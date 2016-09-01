/**
 * 获取活跃用户
 * 亢良
 * 2016年8月29日
 */
	
	
window.onload=function(){
	getActivers();
};
function getActivers(){
	$(".list-group").html("<a class='list-group-item disabled'>最活跃用户<label style='float: right;' onclick='getActivers();'>【刷新】</label></a>");
	$.ajax({
		type: 'POST',
		url: "user/activer.htm" ,
		success: function(data){
			if(data != null && data != ""){
				var list = data.list;
				for (var i = 0; i < list.length; i++) {
					$(".list-group").append("<a class='list-group-item'>"+list[i].nickname+"</a>");
				}
				$(".list-group").append("<a class='list-group-item disabled'onclick='hideActivers();'>【收起】</a>");
			}else{
				alert("服务器忙！");
			}
		} ,
		dataType: 'json'
	});
	}
	function hideActivers(){
		$(".list-group").html("<a class='list-group-item disabled'>最活跃用户<label style='float: right;' onclick='getActivers();'>【展开】</label></a>");
	}
