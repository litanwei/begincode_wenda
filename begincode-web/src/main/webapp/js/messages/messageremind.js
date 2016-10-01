$(function(){
		$(".col-md-9 a[id='messageId']").on('click',function(){
			messageremind(1);
			$.ajax({
				type: "POST",
				url:"/message/pagesize.htm",
				success : function(pagesize) {
					paginationByMessage(parseInt(pagesize));
				}
			});
		})
	});
	function messageremind(nowpage) {
		$.ajax({
			type: "POST",
			dataType:"json",
			url : "/message/messageremind.htm?nowpage="+nowpage,
			error:function(data){
				alert("失败了");
			},
			success : function(data) {
				var jsonarry =data;
				jsontext="";
				if(jsonarry!=null&&jsonarry!=""){
					$.each(jsonarry,function(key, value){
						labelcontent=labelswitching(value.label_name);
						var time;
						var content;
						if(value.create_date==null){
							time=formatTime(value.pr_username, 0, value.answer_username, value.pr_createtime);
							content=value.pr_content;
						}else{
							time=formatTime(value.pr_username, value.answer_count, value.answer_username, value.create_date);
							content=value.pr_content;
						}
						jsontext=jsontext+" <section class=\"stream-list__item\">"+"<div class=\"qa-rank\">"+
						"<div class=\"votes hidden-xs\">"+value.vote_count+"<small>投票</small></div>"+
						"<div class=\"answers\">"+value.answer_count+"<small>回答</small></div>"+
						"<div class=\"views hidden-xs\">"+value.view_count+"<small>浏览</small></div></div>"+	
						"<div class=\"summary\">"+"<ul class=\"author list-inline\"><li><a href=\"#\">"+
						time+"</a></li></ul>"+
						"<span class=\"keyword-list\">"+"<h2 class=\"title l\"><a id="+value.message_id+" href=\""+"/problem/"+value.problem_id+".htm\" onclick=\"monitoringMessageClick(this)\" >"+content+"</a></h2>"+
						labelcontent+
						"</span></div></section>";
					})
				}else{
					jsontext="目前没有信息";
				}
				$("#messages .container-fluid").html(jsontext);
			}
		});
	}
	//标签转换xx,ss,gg,转换为单个标签组
	var labelswitching=function(label){
		if(label==null){
			return "";
		}
		var labelarray=label.split(",");
		var labelcontent="";
		$.each(labelarray,function(key,value){
			labelcontent=labelcontent+"<a href=\"#\" target=\"_blank\" class=\"list-tag\">"+value+"</a>"
		});
		return labelcontent;
	}
	//监控message信息的点击事件
	//用途-->改变message表的已读状态
	var monitoringMessageClick=function(message){
		$.ajax({
			type: "POST",
			url : "/message/"+message.id+".htm",
		});
	}
	//自定义分页
	function paginationByMessage(countpage) {
	    $("#paginationId").empty();
	    $("#paginationId").jqPaginator({
	    	totalCounts: countpage,
	    	pageSize:15,
	    	visiblePages: 4,
	        first: '<li class="first"><a href="javascript:void(0);">首页<\/a><\/li>',
	        prev: '<li class="prev"><a href="javascript:void(0);"><i class="arrow arrow2"><\/i>上一页<\/a><\/li>',
	        next: '<li class="next"><a href="javascript:void(0);">下一页<i class="arrow arrow3"><\/i><\/a><\/li>',
	        last: '<li class="last"><a href="javascript:void(0);">末页<\/a><\/li>',
	        page: '<li class="page"><a href="javascript:void(0);">{{page}}<\/a><\/li>',
	        activeClass: 'active',
	        onPageChange: function (num, type) {
	        	messageremind(num);
	        }
	    })
	}
