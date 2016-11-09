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
					$("#labelBody").append("<a href='' target='_blank' class='list-tag' onclick='selectLabel("+list[i].labelId+")'>"+list[i].labelName+"</a>");
				}
				
			}
		} ,
		dataType: 'json'
	});
	}
<<<<<<< HEAD
	function selectLabel(id){
		window.location.href="/label/selectProblemLabel.htm?id="+id;
	}
=======

>>>>>>> refs/remotes/origin/master
