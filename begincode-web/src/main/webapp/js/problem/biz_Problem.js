/**
 * Created by Stay on 2016/9/15.
 */
$(function(){
	var p_id =  $("#problem_id").attr("value");
	statisticalPro();
	statisticalProView(p_id);
	$(".post-topheader__side button").on('click',statisticalPro);
})
function statisticalPro(status){
		var problem_id =  $("#problem_id").attr("value");
		var buttons = this;
		var urls;
		if(buttons.id==null){
			urls=ctx+"/problem/load/load/"+problem_id+".htm";
		}else{
			urls=ctx+"/problem/click/"+buttons.id+"/"+problem_id+".htm";
		}
		$.ajax({
			type : "POST",
			dataType : "JSON",
			url : urls,
			error : function(data) {
				alert(data.msg);
			},
			success : function(data) {
				var result = data.data;
				if (result["vote"] == true) {
					$("#vote").html("已投票");
				}else{
					$("#vote").html("投票");
				};
				if(result["collection"]==true){
					$("#collection").html("已收藏");
				}else{
					$("#collection").html("收藏")
				};
				$("#collectionNumber").html(result["collectionNumber"]);
				$("#voteNumber").html(result["voteNumber"]);
				if(buttons.id!=null){
					$("#viewNumber").html(result["viewNumber"]);
				}
			}
		});
}
function statisticalProView(problem_id){
	$.ajax({
		type:"GET",
		url:ctx+"/problem/view/"+problem_id+".htm",
		dataType : "JSON",
		success: function(data){
			$("#viewNumber").html(data.data);
		}
	})
}
