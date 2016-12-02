/**
 * 页面加载后事件
 * 亢良
 * 2016年8月29日
 */

$(function () {
    getLabels();
    getActivers();
    messageClick();
});
$(document).ready(function () {
    MessageAndMyProblem();
})
function getLabels() {
    $.ajax({
        type: 'POST',
        url: ctx + "/label/getLabel.htm",
        success: function (data) {
            var list = data.data;
            if (list != null && list != "") {
                for (var i = 0; i < list.length; i++) {
                    $(".tag-list").append("<a target='_blank' class='list-tag ' style='cursor:pointer' href='"+ctx +"/label/selectProblemLabel.htm?id="+ list[i].labelId +"'>" + list[i].labelName + "</a>");
                }
            }
        },
        dataType: 'json'
    });
}
function getActivers() {
    $(".list-group").html("<a class='list-group-item disabled'>最活跃用户</a>");
    $.ajax({
        type: 'POST',
        url: ctx + "/user/activer.htm",
        success: function (data) {
            var list = data.data;
            if (list != null && list != "") {
                for (var i = 0; i < list.length; i++) {
                    $(".list-group").append("<a class='list-group-item' href='"+ctx+"/user/"+list[i].begincodeUserId+".htm'>" + list[i].nickname + "");
                }
            }
        },
        dataType: 'json'
    });
}
function MessageAndMyProblem(){
    if(getCookie("openId")!="" && getCookie("accessToken")!=""){
        $("#myProblemId").html('<a id="myProblemId" href="#myProblems" aria-controls="messages" role="tab" data-toggle="tab">我的问题</a>');
        $("#messageId").html(' <a id="messageId" href="#messages" aria-controls="messages" role="tab" data-toggle="tab">@我的 <span class="badge" id="messageCount"></span></a>');
        setInterval (function() {
            $.ajax({
                type: "POST",
                dataType:"JSON",
                url:ctx+"/message/count.htm",
                success : function(data) {
                    if(data.code == 0){
                        $("#messageCount").html(data.data);
                    }
                }
            });
        },5000);
    }
}


function messageClick() {
    $("#messageId").click(function () {
        messageCount();
        $.ajax({
            type: "POST",
            url: ctx+"/message/list.htm?page=1",
            dataType: "json",
            success: function (data) {
                if(data.code == 0){
                    messageHtml(data.data.data,"message");
                    messagePagination(data.data,"/list","message","POST");
                }else{
                    showModelBack(data.msg);
                }
            }
        })
    })
};

function messageCount(){
    $.ajax({
        type: "POST",
        dataType:"JSON",
        url:ctx+"/message/count.htm",
        success : function(data) {
            if(data.code == 0){
                $("#messageCount").html(data.data);
            }
        }
    });
};

function messageHtml(data,id) {
    $("#"+id).empty();
    $.each(data,function (i) {
        if(data[i].title!=null){
            var solve = "";
            if (data[i].solve == 0) {
                solve = '<div class="answers answered">' +  numFormat(data[i].answerCount) + '<small>回答</small></div>';
            } else {
                solve = '<div class="answers solved">' +  numFormat(data[i].answerCount) + '<small>解决</small></div>';
            }
            var titleSub = "";
            if(data[i].title.length>30){
                titleSub = '<h2 class="title l"><a href="'+ctx+'/problem/message/'
                    + data[i].problemId + ".htm"
                    + '"data-toggle="tooltip" data-placement="top" title='
                    + data[i].title
                    + '>'
                    + data[i].title.substring(0,30);
                + '</a></h2>';
            }else{
                titleSub = '<h2 class="title l"><a href="'+ctx+'/problem/message/'
                    + data[i].problemId + '.htm'
                    + '" data-toggle="tooltip" data-placement="top">'
                    + data[i].title;
                + '</a></h2>';
            }

            var problemList = '<section class="stream-list__item">'
                + '<div class="qa-rank">'
                + '<div class="votes plus hidden-xs">'
                + numFormat(data[i].voteCount)
                + '<small>投票</small>'
                + '</div>'
                + solve
                + '<div class="views hidden-xs">'
                + numFormat(data[i].viewCount)
                + '<small>浏览</small>'
                + '</div>'
                + '</div>'
                + '<div class="summary">'
                + '<ul class="author list-inline ">'
                + '<li>'
                + '<a style="text-decoration:none;">'+ data[i].userName +'  '
                + formatTime(data[i].createTime)
                + '</a>'
                + '</li>'
                + '</ul>'
                + '<span class="keyword-list ">'
                + titleSub
                + '</span></div></section>';
            $("#" +id).append(problemList);
        }else{
            var contentSub="";
            if (data[i].adopt == 0) {
                adoptDiv = '<div class="answers">0<small>未采纳</small></div>';
            } else {
                adoptDiv = '<div class="answers solved">1<small>被采纳</small></div>';
            }
            if(data[i].content.length>30){
                contentSub='<h2 class="title l">'
                    + '<a href="'+ctx+'/problem/answer/'
                    + data[i].answerId+"/"+data[i].problemId
                    + '.htm'
                    + '"data-toggle="tooltip" data-placement="top" title="'
                    + data[i].content
                    + '">'
                    + data[i].content.substring(0,30)
                    + '</a></h2>';
            }else{
                contentSub = '<h2 class="title l">'
                    + '<a href="'+ctx+'/problem/answer/'
                    + data[i].answerId+"/"+data[i].problemId
                    + '.htm'
                    + '"data-toggle="tooltip" data-placement="top">'
                    + data[i].content
                    + '</a></h2>';
            }
            var answerList = '<section class="stream-list__item">'
                + '<div class="qa-rank">'
                + '<div class="votes hidden-xs">'
                + numFormat(data[i].agreeCount)
                + '<small>赞同</small>'
                + '</div>'
                + adoptDiv
                + '<div class="views hidden-xs">'
                + numFormat(data[i].oppositionCount)
                + '<small>反对</small>'
                + '</div>'
                + '</div>'
                + '<div class="summary">'
                + '<ul class="author list-inline">'
                + '<li>'
                + '<a style="text-decoration:none;">'+ data[i].userName + '  '
                + formatTime(data[i].createTime)
                + '</a>'
                + '</li>'
                + '</ul>'
                + '<span class="keyword-list ">'
                + contentSub
                + '</span>'
                + '</div>'
                + '</section>';
            $("#" + id).append(answerList);
            $("[data-toggle='tooltip']").tooltip();
        }
    })
}


/**
 * 根据cookieName查找对应的值
 * @param cookieName
 * @returns {*}
 */
function getCookie(cookieName) {
    var strCookie = document.cookie;
    var arrCookie = strCookie.split("; ");
    for (var i = 0; i < arrCookie.length; i++) {
        var arr = arrCookie[i].split("=");
        if (cookieName == arr[0]) {
            return arr[1];
        }
    }
    return "";
}
