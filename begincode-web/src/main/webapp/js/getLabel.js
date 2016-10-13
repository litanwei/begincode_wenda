/**
 * 获取热门标签、查询标签
 * 亢良
 * 2016年8月29日
 */
	
	
window.onload=function(){
	getLabels();
};
function getLabels(){
	$.ajax({
		type: 'POST',
		url: "/label/getLabel.htm" ,
		success: function(list){
			if(list != null && list != ""){
				for (var i = 0; i < list.length; i++) {
					$("#labelBody").append("<a href='' target='_blank' class='list-tag' onclick='selectLabel("+list[i].labelId+")'>"+list[i].labelName+"</a>");
				}
			}else{
				alert("服务器忙！");
			}
		} ,
		dataType: 'json'
	});
	}
	function selectLabel(id){
		alert(id);
	}
