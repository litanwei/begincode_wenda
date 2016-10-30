/**
 * Created by Stay on 2016/10/23.
 */
$(document).ready(function () {
    $("#messageId").click(function () {
        $.ajax({
            type: "POST",
            url: ctx+"/message/list.htm?page=1",
            dataType: "json",
            success: function (data) {
                if(data.code == 0){
                    messageHtml(data.data.data,"message");
                    messagePagination(data.data,"/list","message","POST");
                }else{
                    showModel(data.msg);
                }
            }
        })
    })
});


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
                titleSub = '<h2 class="title l"><a href="/message/problem/'
                    + data[i].problemId + ".htm"
                    + '"data-toggle="tooltip" data-placement="top" title='
                    + data[i].title
                    + '>'
                    + data[i].title.substring(0,30);
                    + '</a></h2>';
            }else{
                titleSub = '<h2 class="title l"><a href="/message/problem/'
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
                + '<a href="/message/answer/'
                + data[i].problemId
                + '.htm'
                + '?answerId='+data[i].answerId
                + '"data-toggle="tooltip" data-placement="top" title="'
                + data[i].content
                + '">'
                + data[i].content.substring(0,30)
                + '</a></h2>';
            }else{
                contentSub = '<h2 class="title l">'
                    + '<a href="/message/answer/'
                    + data[i].problemId
                    + '.htm'
                    + '?answerId='+data[i].answerId
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

