/**
 * 分页插件 亢良 2016年8月29日
 */
$(function() {
	pageUtilJs();
	$("#page").paginate({
		count 		: 33,
		start 		: 1,
		display     : 16,
		border					: true,
		border_color			: '#fff',
		text_color  			: '#fff',
		background_color    	: 'black',	
		border_hover_color		: '#ccc',
		text_hover_color  		: '#000',
		background_hover_color	: '#fff', 
		images					: false,
		mouse					: 'press'
	});
});
function pageUtilJs(url,i,totalNum,pageCount,params){
	url += "?" + (params==0?"":(params+"&doList=true&"));
	
	var text ="<div class='inline pull-right page'>"+totalNum+" Records"+ i+"/"+pageCount+" ";
	
	if(i!=1){
		text += "<a href='"+url+"i="+(i-1)+"'>Previous</a>";
	}
	
	for(var k = i ; k < i+4 ; k++){
		if(k-2>0 && k-2<=pageCount && k!=(i+2)){
			text += "<a href='"+url+"i="+(k-2)+"'>"+(k-2)+"</a>";
		}
		if((k-2)== i){
			text += " " + (k-2);
		}
	}
		
	if(i!=parseInt(pageCount)){
		text += "<a href='"+url+"i="+(i+1)+"'>Next</a>";
	}
	
	text += "   <input type='text' id='jumpI' style='width: 40px'/><a href='#' onclick='jumpTo(\""+url+"\","+pageCount+")'>Go</a>";
		
				
	text +=	"	<a href='"+url+"i="+pageCount+"'>Last</a>";
	$("#page").append(text);
}

function jumpTo(url,pageCount) {
	var jumpI = $('#jumpI').val();
	if (jumpI == null || jumpI == '') {
		BUI.Message.Alert('请输入跳转到的页面');
		return;
	}
	var reg = new RegExp('^[0-9]*$');
	if (!reg.test(jumpI)) {
		BUI.Message.Alert('请输入数字');
		return;
	}
	if (parseInt(jumpI) < 0) {
		jumpI = 1;
	}
	if (parseInt(jumpI) > pageCount) {
		jumpI = pageCount;
	}
	window.location.href = url+'i=' + jumpI;
}
