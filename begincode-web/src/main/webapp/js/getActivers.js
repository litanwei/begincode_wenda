/**
 * 获取活跃用户
 * 亢良
 * 2016年8月29日
 */
	
	

function getActivers(){
	$(".list-group").html("<a class='list-group-item disabled'>最活跃用户</a>");
	$.ajax({
		type: 'POST',
		url: "user/activer.htm" ,
		success: function(data){
			var list = data.data;
			if(list != null && list != ""){
				
				for (var i = 0; i < list.length; i++) {
					$(".list-group").append("<a class='list-group-item'>"+list[i].nickname+"</a>");
				}
			}
		} ,
		dataType: 'json'
	});
	}
