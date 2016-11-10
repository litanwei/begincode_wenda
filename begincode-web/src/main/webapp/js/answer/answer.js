// 回复 绑定回复内容 问题id
$(document).ready(function () {
    $("#answerSend").click(function () {
        var markupStr = '';
        var content = $('#content').val($('#summernote').summernote('code')); //使summernote里面的内容放到隐藏的content中
        $("#answerSend").attr("disabled", "disabled");//按钮不可用
        $.ajax({
            data: $("#answerForm").serializeArray(),
            type: "POST",
            url: ctx + "/answer/reply.htm",
            dataType: "json",
            async: true,
            success: function (data) {
                if (data.code == 0) {
                    updateAnswer(data, "answerUpdate",2);
                    showModel(data.msg);
                    setTimeout(function () {
                        $("#ajaxModal").modal("hide")
                    }, 2000);
                    $(document).scrollTop(0);
                    $("#answerSend").removeAttr("disabled");//按钮可用
                    $('#summernote').summernote('code', markupStr);
                    save();
                    edit1();
                } else {
                    showModel(data.msg);
                    $("#answerSend").removeAttr("disabled");//按钮可用
                }
                $("#answerSend").removeAttr("disabled");//按钮可用
            }
        });
    })

//赞同按钮处理
    var agreeFlag = 0;
    $(".click-like").click(function () {
        var answerId = $(this).parent().prev("input").val();

        if($(this).hasClass("pressed")){
            $(this).removeClass("pressed")
            $(this).attr("title","赞同");
            agreeFlag = 0;
            var agreeNum = $(this).children("span").html();
            $(this).children("span").html(parseInt(agreeNum) - 1);
        }else {
            $(this).addClass("pressed");
            $(this).attr("title","取消赞同");
            var agreeNum = $(this).children("span").html();
            $(this).children("span").html(parseInt(agreeNum) + 1);
            agreeFlag = 1;
            if($(this).next().hasClass("pressed")){
                $(this).next().removeClass("pressed");
                $(this).next().attr("title","取消反对")

            }
        }
        ansAgree(answerId,agreeFlag);
    })
    //反对按钮处理
    $(".click-dislike").click(function () {
        var answerId = $(this).parent().prev("input").val();
        if($(this).hasClass("pressed")){
            $(this).removeClass("pressed")
            $(this).attr("title","反对");
            agreeFlag = 0;
        }else {
            $(this).addClass("pressed");
            $(this).attr("title","取消反对");
            agreeFlag = 2;
            if($(this).prev().hasClass("pressed")){
                $(this).prev().removeClass("pressed");
                $(this).prev().attr("title","赞同");
                var agreeNum = $(this).prev().children("span").html();
                $(this).prev().children("span").html(parseInt(agreeNum) - 1);
            }
        }
        ansAgree(answerId,agreeFlag);
    })

})


// 回复反馈
function sendFeedback(answerId) {
    var answer = new FormData();
    answer.append("answerId", answerId);
    $.ajax({
        data: answer,
        type: "POST",
        url: ctx + "/answer/feedback.htm",
        dataType: "json",
        contentType: false,
        processData: false,
        success: function (data) {
            showModel(data.msg);
            setTimeout(function () {
                $("#ajaxModal").modal("hide")
            }, 2000);
        }
    })
}
//回复采纳
function sendAdoptAnswer(answerId) {
    var answer = new FormData();
    answer.append("answerId", answerId);
    $.ajax({
        data: answer,
        type: "POST",
        url: ctx + "/answer/answerAdopt.htm",
        dataType: "json",
        contentType: false,
        processData: false,
        success: function (data) {
            if (data.code == 0) {
                updateAnswer(data, "answerAdopt",1);
                showModel(data.msg);
                setTimeout(function () {
                    $("#ajaxModal").modal("hide")
                }, 2000);
                var answerDivId = 'answer' + data.data.answerId;
                $("#" + answerDivId).hide();
                $(document).scrollTop(0);
            } else {
                showModel(data.msg);
            }
        }
    })
}

function ansAgree(answerId,agreeFlag) {
    var ansAgree = new FormData();
    ansAgree.append("agreeFlag",agreeFlag);
    ansAgree.append("answerId",answerId);
    $.ajax({
        data: ansAgree,
        type: "POST",
        url: ctx + "/ansAgree/set.htm",
        dataType: "json",
        contentType: false,
        processData: false,
        success: function (data) {
            if (data.code != 0) {
                showModel(data.msg);
            }
        }
    })
}

//动态添加回复
function updateAnswer(data,id,ansAgreeFlag) {
    $("#" + id).empty();
    if (ansAgreeFlag == 1) {
        var agree = '<button id="click-like" class="click-like up pressed" aria-pressed="true" title="取消赞同">'
            +'<i class="vote-arrow"></i>'
            +'<span class="count">'
            +data.data.agreeCount
            +'</span>'
            +'</button>'
            +'<button id="click-dislike" class="click-dislike down" aria-pressed="false" title="反对">'
            +'<i class="vote-arrow"></i>'
            +'</button>';
        var adopt = "";
    }else{
        var agree = '<button id="click-like" class="click-like up" aria-pressed="false" title="赞同">'
            +'<i class="vote-arrow"></i>'
            +'<span class="count">'
            +data.data.agreeCount
            +'</span>'
            +'</button>'
            +'<button id="click-dislike" class="click-dislike down" aria-pressed="false" title="反对">'
            +'<i class="vote-arrow"></i>'
            +'</button>';
        var adopt = '<li class="edit-btn js__rank-check" data-toggle="tooltip"  data-placement="top" >'
            +'<a href="javascript:;" onclick="sendAdoptAnswer('
            +data.data.answerId
            +')">采纳</a>'
            +'</li>';
    }
    if (data.data.adopt == 1) {
        var solve = '<a class="rcmd-label">采纳</a>';
    }else{
        var solve ='';
    }
    var answerUpdate =
        '<article class="widget-question__item">'
        +'<hr>'
        +'<input type="hidden" value="'
        + data.data.answerId
        +'"/>'
        +'<div class="votebar">'
        +agree
        +solve
        +'</div>'
        +'<div class="post-offset">'
        +'<div class="answer-fmt" data-id="1010000007316290" ><p>'
        +data.data.content
        +'</p></div>'
        +'<div class="row">'
        +'<div class="post-opt col-md-8">'
        +'<ul class="list-inline mb0">'
        +'<li><a href="javascript:;">'
        +formatTime(data.data.createTime)
        +"回答"
        +'</a></li>'
        +adopt
        +'<li class="edit-btn js__rank-check" data-toggle="tooltip"  data-placement="top" >'
        +'<a href="javascript:;" onclick="sendFeedback('
        +data.data.answerId
        +')">举报</a>'
        +'</li>'
        +'<li class="dropdown js__content-ops" data-module="question" data-typetext="问题"data-id="1010000007316290">'
        +'<a href="javascript:void(0);" class="dropdown-toggle"data-toggle="dropdown">更多<b class="caret"></b></a>'
        +'<ul class="dropdown-menu dropdown-menu-left"><li><a href="javascript:void(0);"data-toggle="modal"data-target="#911"data-action="close">关上把你能耐的</a></li></ul>'
        +'</ul>'
        +'</li>'
        +'</ul>'
        +'</div>'
        +'<div class="col-md-2 col-sm-2 col-xs-2 answer__info--author-avatar">'
        +'</div>'
        +'<div class="col-md-2 col-sm-2 hidden-xs answer__info--author">'
        +'<div class=" answer__info--author-warp">'
        +'<a '
        +'href="'+ctx+'/user/'+data.data.userName+'.htm">'
        +data.data.userName
        +'</a><span class="answer__info--author-rank"> </span></div>'
        +'</div>'
        +'</div>'
        +'</div>'
        +'</article>';
    $("#" + id).after(answerUpdate);

    $(".votebar").on("click","button", function() {
        var agreeFlag = 0;
        var answerId = $(this).parent().prev("input").val();
        if($(this).hasClass("click-like")) {
            if ($(this).hasClass("pressed")) {
                $(this).removeClass("pressed")
                agreeFlag = 0;
                var agreeNum = $(this).children("span").html();
                $(this).children("span").html(parseInt(agreeNum) - 1);
            } else {
                $(this).addClass("pressed");
                var agreeNum = $(this).children("span").html();
                $(this).children("span").html(parseInt(agreeNum) + 1);
                agreeFlag = 1;
                if ($(this).next().hasClass("pressed")) {
                    $(this).next().removeClass("pressed");
                }
            }
            ansAgree(answerId, agreeFlag);
        }else {
            //反对按钮处理
            if ($(this).hasClass("pressed")) {
                $(this).removeClass("pressed")
                agreeFlag = 0;
            } else {
                $(this).addClass("pressed");
                agreeFlag = 2;
                if ($(this).prev().hasClass("pressed")) {
                    $(this).prev().removeClass("pressed");
                    var agreeNum = $(this).prev().children("span").html();
                    $(this).prev().children("span").html(parseInt(agreeNum) - 1);
                }
            }
            ansAgree(answerId, agreeFlag);

        }
    })

}
//模拟框
function showModel(msg) {
    $("#errorMessage").html(msg);
    $("#ajaxModal").modal({backdrop: 'static', keyboard: false}).modal("show");   //禁用点击空白地方关闭modal框
}





