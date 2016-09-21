/*!
 * Bootstrap v3.3.5 (http://getbootstrap.com)
 * Copyright 2011-2015 Twitter, Inc.
 * Licensed under the MIT license
 */

$(function(){
		$(".col-md-9 a[aria-controls='messages']").on('click',function(){
			messageremind()
		})
	});
	function messageremind() {
		$.ajax({
			type: "POST",
			data:"nowpage=1&userid=1",
			dataType:"json",
			url : "/message/messageremind.htm",
			error:function(data){
				alert("失败了");
			},
			success : function(data) {
				var jsonarry =data.data;
				jsontext="";
				if(jsonarry!=null){
					$.each(jsonarry,function(key, value){
						labelcontent=labelswitching(value.label_name);
						var time=createtime(value.create_date);
						jsontext=jsontext+" <section class=\"stream-list__item\">"+"<div class=\"qa-rank\">"+
						"<div class=\"votes hidden-xs\">"+value.vote_count+"<small>投票</small></div>"+
						"<div class=\"answers\">"+value.answer_count+"<small>回答</small></div>"+
						"<div class=\"views hidden-xs\">"+value.view_count+"<small>浏览</small></div></div>"+	
						"<div class=\"summary\">"+"<ul class=\"author list-inline\"><li><a href=\"#\">"+value.answer_username+"</a><span class=\"split\"></span><a href=\"#\" class=\"askDate\" data-created=\"1470910517\">"+
						time+"提问</a></li></ul>"+
						"<span class=\"keyword-list\">"+"<h2 class=\"title l\"><a href=\"#\">"+value.answer_content+"</a></h2>"+
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
	/*
	 * 这里是处理yyyy-MM-dd HH:mm:ss 格式的date 返回一个time
	 * 
	 */
	var createtime=function(time){
		var nowdate=new Date();
		var olddate=new Date(time);
		var date=formatTime(nowdate,olddate);
		return  date;
	}
	
	/**
	 * nowdate,olddate 为 date格式
	 * 这里处理时间信息格式化 
	 */
	var formatTime=function(nowdate,olddate) {
		var differTime=nowdate.getTime()-olddate.getTime();
	    var time = parseInt(differTime/1000);
	    if (time != null && time != ""){
	        if (time < 60) {
	            var s =time;
	            time = s + '秒前';
	        } else if (time > 60 && time < 3600) {
	            var m = parseInt(time / 60);
	            time = m + "分钟前";
	        } else if (time >= 3600 && time < 86400) {
	            time =olddate.toLocaleTimeString();
	        } else if (time >= 86400) {
	            time = olddate.toLocaleString().replace(/[/]/g, '-');
	        }
	    }  
	    return time;   
	} 
	//标签转换xx,ss,gg,转换为单个标签组
	var labelswitching=function(label){
		var labelarray=label.split(",");
		var labelcontent="";
		$.each(labelarray,function(key,value){
			labelcontent=labelcontent+"<a href=\"#\" target=\"_blank\" class=\"list-tag\">"+value+"</a>"
		});
		return labelcontent;
	}
