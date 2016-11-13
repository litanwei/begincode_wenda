/**
 * 获取热门标签、查询标签
 * 亢良
 * 2016年8月29日
 */
	
	

function getLabels(){
	
	$.ajax({
		type: 'POST',
		url: ctx+"/label/getLabel.htm" ,
		success: function(data){
			var list = data.data;
			if(list != null && list != ""){
				
				for (var i = 0; i < list.length; i++) {
					$(".tag-list").append("<a target='_blank' class='list-tag ' style='cursor:pointer' onclick='selectLabel("+list[i].labelId+")'>"+list[i].labelName+"</a>");
				}
				
			}
		} ,
		dataType: 'json'
	});
	}
	function selectLabel(id){
		window.location.href=ctx+"/label/selectProblemLabel.htm?id="+id;
	}
