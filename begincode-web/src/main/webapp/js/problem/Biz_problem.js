/**
 * Created by Stay on 2016/9/15.
 */
$(function(){
	StatisticalPro();
	StatisticalPorView();
	$(".post-topheader__side button").on('click',StatisticalPro);
})
function StatisticalPro(status){
		var buttons = this;
		var urls;
		if(buttons.id==null){
			urls=window.location.href.replace("problem",
					"problem/load/load");
		}else{
			urls = window.location.href.replace("problem",
					"problem/click/"+buttons.id + "");
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
				$("#viewNumber").html(result["viewNumber"]);
			}
		});
}
function StatisticalPorView(){
	var urls=window.location.href.replace("problem","problem/view");
	$.ajax({
		type:"GET",
		url:urls,
		dataType : "JSON",
		success: function(data){
			$("#viewNumber").html(data.data);
		}
	})
}
