// 回复反馈
function sendFeedback(answerId) {
    var answer = new FormData();
    answer.append("answerId", answerId);
    $.ajax({
        data: answer,
        type: "POST",
        url: ctx+"/answer/feedback.htm",
        dataType: "json",
        contentType: false,
        processData: false,
        success: function (data) {
            showModel(data.msg);
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
        url: ctx+"/answer/answerAdopt.htm",
        dataType: "json",
        contentType: false,
        processData: false,
        success: function (data) {
            if (data.code == 0) {
                if (data.data != null) {
                    updateAnswer(data, "answerAdopt");
                    showModel(data.msg);
                    var answerDivId = 'answer'+data.data.answerId;
                    $("#"+answerDivId).hide();
                    $(document).scrollTop(0);
                }
            } else {
                showModel(data.msg);
            }
        }
    })
}


// 回复 绑定回复内容 问题id
$(document).ready(function () {
    $("#answerSend").click(function () {
        var content = $('#content').val($('#summernote').summernote('code')); //使summernote里面的内容放到隐藏的content中
        $("#answerSend").attr("disabled","disabled");//按钮不可用
        $.ajax({
            data: $("#answerForm").serializeArray(),
            type: "POST",
            url: ctx+"/answer/reply.htm",
            dataType: "json",
            async:true,
            success: function (data) {
                if (data.code == 0) {
                    updateAnswer(data, "answerUpdate");
                    showModel(data.msg);
                    $(document).scrollTop(0);
                    $("#answerSend").removeAttr("disabled");//按钮可用
                }else{
                    showModel(data.msg);
                    $("#answerSend").removeAttr("disabled");//按钮可用
                }
                $("#answerSend").removeAttr("disabled");//按钮可用
            }
        });
    })
})

//动态添加回复
function updateAnswer(data,id) {
    $("#" + id).empty();
    if (data.data.adopt == 1) {
        solve =
            '<li><span class="label label-danger">已采纳</span>'
            +'</li>';
    } else {
        solve =
            '<li data-toggle="tooltip" data-placement="top" title="" class="edit-btn js__rank-check">'
            +'<a href="javascript:void(0);" onclick="sendAdoptAnswer('
            + data.data.answerId
            + ')">采纳</a>'
            +'</li>';
    }
    var answerUpdate =
        '<div class="post-offset">'
        +'<hr>'
        +'<div class="answer fmt" data-id="1020000006591589">'
        +'<p>'
        +data.data.content
        +'</p>'
        +'</div>'
        +'<div class="row answer__info--row">'
        +'<div class="post-opt col-md-8 col-sm-8 col-xs-10">'
        +'<ul class="list-inline mb0">'
        +'<li>'
        +'<td>'
        + formatTime(null,1,null,data.data.createTime)
        + '</td>'
        +'<span class="text-muted"></span>'
        +'</li>'
        +'<li data-toggle="tooltip" data-placement="top" title="" class="edit-btn js__rank-check">'
        +'<a href="javascript:void(0);" onclick="sendFeedback('
        + data.data.answerId
        + ')">举报</a>'
        +'</li>'
        + solve
        +'</ul>'
        +'</div>'
        +'<div class="col-md-2 col-sm-2 col-xs-2 answer__info--author-avatar">'
        +'</div>'
        +'<div class="col-md-2 col-sm-2 hidden-xs answer__info--author">'
        +'<div class=" answer__info--author-warp">'
        +'<a class="answer__info--author-name" title="'
        + data.data.userName
        + '"'
        +'href="/u/fishenal">'
        + data.data.userName
        + '</a><span class="answer__info--author-rank"></span></div>'
        +'</div>'
        +'</div>'
        +'</div>';
    $("#" + id).after(answerUpdate);

}
//模拟框
function showModel(msg) {
    $("#errorMessage").html(msg);
    $("#ajaxModal").modal({backdrop: 'static', keyboard: false}).modal("show");   //禁用点击空白地方关闭modal框
}


