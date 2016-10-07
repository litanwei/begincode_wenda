/**
 * Created by Stay on 2016/9/15.
 */
$(function(){
	StatisTicalPro();
	$(".post-topheader__side button").on('click',StatisTicalPro);
})
var StatisTicalPro=function(){
		var buttons = this;
		var urls;
		if(status==null){
			urls=window.location.href.replace("problem",
					"problem/load/load/");
		}else{
			urls = window.location.href.replace("problem",
					"problem/click/"+buttons.id + "");
		}
		$.ajax({
			type : "POST",
			dataType : "JSON",
			url : urls,
			error : function(data) {
				alert("失败了");
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
