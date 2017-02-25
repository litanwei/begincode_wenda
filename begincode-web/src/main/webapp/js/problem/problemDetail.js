/**
 * Created by Stay on 2016/10/26.
 */
$(document).ready(function () {
    var problemId = $("#problem_id").val();
    $.ajax({
        type: "GET",
        url: ctx + "/problem/view/" + problemId + ".htm",
        dataType: "json",
        success: function (data) {
            if (data.code != 0) {
                showModelNoBack(data.msg);
            }
        }
    });

    $("#collection").click(function () {
        $.ajax({
            type: "POST",
            url: ctx + "/problem/collect/" + problemId + ".htm",
            dataType: "json",
            success: function (data) {
                if (data.code == 0) {
                    if (data.data == 1) {
                        $("#collection").html("已收藏");
                        var collectNum = $("#collectionNumber").html();
                        $("#collectionNumber").html((parseInt(collectNum) + 1));
                    }
                    if (data.data == 0) {
                        $("#collection").html("收藏");
                        var collectNum = $("#collectionNumber").html();
                        $("#collectionNumber").html((parseInt(collectNum) - 1));
                    }
                } else {
                    showModelNoBack(data.msg);
                }
            }
        })
    })

    $("#vote").click(function () {
        $.ajax({
            type: "POST",
            url: ctx + "/problem/vote/" + problemId + ".htm",
            dataType: "json",
            success: function (data) {
                if (data.code == 0) {
                    if (data.data == 1) {
                        $("#vote").html("已投票");
                        var voteNum = $("#voteNumber").html();
                        $("#voteNumber").html((parseInt(voteNum) + 1));
                    }
                    if (data.data == 0) {
                        $("#vote").html("投票");
                        var voteNum = $("#voteNumber").html();
                        $("#voteNumber").html((parseInt(voteNum) - 1));
                    }
                } else {
                    showModelNoBack(data.msg);
                }
            }
        })
    })

    $("#clickVote").click(function () {
        $.ajax({
            type: "POST",
            url: ctx + "/problem/vote/" + problemId + ".htm",
            dataType: "json",
            success: function (data) {
                if (data.code == 0) {
                    if (data.data == 1) {
                        $("#vote").html("已投票");
                        var voteNum = $("#voteNumber").html();
                        $("#voteNumber").html((parseInt(voteNum) + 1));
                    }
                    if (data.data == 0) {
                        $("#vote").html("投票");
                        var voteNum = $("#voteNumber").html();
                        $("#voteNumber").html((parseInt(voteNum) - 1));
                    }
                } else {
                    showModelNoBack(data.msg);
                }
            }
        })
    })
    $.ajax({
        type: 'POST',
        url: ctx + "/label/getLabel.htm",
        dataType: "json",
        async: true,
        success: function (data) {
            var list = data.data;
            for (var i = 0; i < list.length; i++) {
                $("#labelBody").append("<a target='_blank' class='list-tag ' href='" + ctx + "/label/selectProblemLabel.htm?id=" + list[i].labelId + "'>" + list[i].labelName + "</a>");
            }
        }
    });

    // 回复 绑定回复内容 问题id
    $("#answerSend").click(function () {
        if ($('#summernote').summernote('isEmpty')) {
            showModelNoBack("回复为空，请填写您的回复。");
        } else {
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
                        updateAnswer(data, "answerUpdate", 2);
                        showModelNoBack(data.msg);
                        setTimeout(function () {
                            $("#ajaxModal").modal("hide")
                        }, 2000);
                        $(document).scrollTop(0);
                        $("#answerSend").removeAttr("disabled");//按钮可用
                        $('#summernote').summernote('code', markupStr);
                        save();
                        edit1();
                    } else {
                        showModelNoBack(data.msg);
                        $("#answerSend").removeAttr("disabled");//按钮可用
                    }
                    $("#answerSend").removeAttr("disabled");//按钮可用
                }
            });
        }
    })

});

//赞同按钮处理
//var agreeFlag = 0;
$(".votebar :button").click(function () {
    var answerId = $(this).parent().prev("input").val();
    var thisClick = $(this);
    if (thisClick.hasClass("click-like")) {
        if (thisClick.hasClass("pressed")) {
            //agreeFlag = 0;
            ansAgree(answerId,'0',thisClick);
        } else {
            //agreeFlag = 1;
            ansAgree(answerId,'1',thisClick);
        }
    } else {
        if (thisClick.hasClass("pressed")) {
            //agreeFlag = 0;
            ansOpposition(answerId, '0', thisClick);
        } else {
            //agreeFlag = 2;
            ansOpposition(answerId, '2', thisClick);
        }
    }
    //ansOpposition(answerId, agreeFlag, thisClick);
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
            showModelNoBack(data.msg);
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
                updateAnswer(data, "answerAdopt", 1);
                showModelNoBack(data.msg);
                setTimeout(function () {
                    $("#ajaxModal").modal("hide")
                }, 2000);
                var answerDivId = 'answer' + data.data.answerId;
                $("#" + answerDivId).hide();
                $(document).scrollTop(0);
            } else {
                showModelNoBack(data.msg);
            }
        }
    })
}

//赞同 后台交互
function ansAgree(answerId,agreeFlag,thisClick) {
    var ansAgree = new FormData();
    ansAgree.append("agreeFlag",agreeFlag);
    ansAgree.append("answerId",answerId);
    $.ajax({
        data: ansAgree,
        type: "POST",
        url: ctx + "/ansAgree/setAgree.htm",
        dataType: "json",
        contentType: false,
        processData: false,
        success: function (data) {
            if (data.code == 0) {
                agreeClick(thisClick);
            }else {
                showModelNoBack(data.msg);
            }
            thisClick.next().removeAttr("disabled");
            thisClick.removeAttr("disabled");
        }
    })
}

//反对 后台交互
function ansOpposition(answerId,agreeFlag,thisClick) {
        var ansAgree = new FormData();
        ansAgree.append("agreeFlag", agreeFlag);
        ansAgree.append("answerId", answerId);
        $.ajax({
            data: ansAgree,
            type: "POST",
            url: ctx + "/ansAgree/setOpposition.htm",
            dataType: "json",
            contentType: false,
            processData: false,
            success: function (data) {
                if (data.code == 0) {
                    agreeClick(thisClick);
                } else {
                    showModelNoBack(data.msg);
                }
                thisClick.removeAttr("disabled");
                thisClick.prev().removeAttr("disabled");
            }
        })

}

//动态添加回复
function updateAnswer(data, id, ansAgreeFlag) {
    $("#" + id).empty();
    if (ansAgreeFlag == 1) {
        var agree = '<button id="click-like" class="click-like up pressed" aria-pressed="true" title="取消赞同">'
            + '<i class="vote-arrow"></i>'
            + '<span class="count">'
            + data.data.agreeCount
            + '</span>'
            + '</button>'
            + '<button id="click-dislike" class="click-dislike down" aria-pressed="false" title="反对">'
            + '<i class="vote-arrow"></i>'
            + '</button>';
        var adopt = "";
    } else {
        var agree = '<button id="click-like" class="click-like up" aria-pressed="false" title="赞同">'
            + '<i class="vote-arrow"></i>'
            + '<span class="count">'
            + data.data.agreeCount
            + '</span>'
            + '</button>'
            + '<button id="click-dislike" class="click-dislike down" aria-pressed="false" title="反对">'
            + '<i class="vote-arrow"></i>'
            + '</button>';
        var adopt = '<li class="edit-btn js__rank-check" data-toggle="tooltip"  data-placement="top" >'
            + '<a href="javascript:;" onclick="sendAdoptAnswer('
            + data.data.answerId
            + ')">采纳</a>'
            + '</li>';
    }
    if (data.data.adopt == 1) {
        var solve = '<a class="rcmd-label">采纳</a>';
    } else {
        var solve = '';
    }
    var answerUpdate =
        '<article class="widget-question__item">'
        + '<hr>'
        + '<input type="hidden" value="'
        + data.data.answerId
        + '"/>'
        + '<div class="votebar">'
        + agree
        + solve
        + '</div>'
        + '<div class="post-offset">'
        + '<div class="answer-fmt" data-id="1010000007316290" ><p>'
        + data.data.content
        + '</p></div>'
        + '<div class="row">'
        + '<div class="post-opt col-md-8">'
        + '<ul class="list-inline mb0">'
        + '<li><a href="javascript:;">'
        + formatTime(data.data.createTime)
        + "回答"
        + '</a></li>'
        + adopt
        + '<li class="edit-btn js__rank-check" data-toggle="tooltip"  data-placement="top" >'
        + '<a href="javascript:;" onclick="sendFeedback('
        + data.data.answerId
        + ')">举报</a>'
        + '</li>'
        + '<li class="dropdown js__content-ops" data-module="question" data-typetext="问题"data-id="1010000007316290">'
        + '<a href="javascript:void(0);" class="dropdown-toggle"data-toggle="dropdown">更多<b class="caret"></b></a>'
        + '<ul class="dropdown-menu dropdown-menu-left"><li><a href="javascript:void(0);"data-toggle="modal"data-target="#911"data-action="close">关上把你能耐的</a></li></ul>'
        + '</ul>'
        + '</li>'
        + '</ul>'
        + '</div>'
        + '<div class="col-md-2 col-sm-2 col-xs-2 answer__info--author-avatar">'
        + '</div>'
        + '<div class="col-md-2 col-sm-2 hidden-xs answer__info--author">'
        + '<div class=" answer__info--author-warp">'
        + '<a '
        + 'href="' + ctx + '/user/' + data.data.begincodeUserId + '.htm">'
        + data.data.userName
        + '</a><span class="answer__info--author-rank"> </span></div>'
        + '</div>'
        + '</div>'
        + '</div>'
        + '</article>';
    $("#" + id).after(answerUpdate);

    $(".votebar").on("click", "button", function () {
        //var agreeFlag = 0;
        var thisClick = $(this);
        var answerId = $(this).parent().prev("input").val();
        if (thisClick.hasClass("click-like")) {
            if (thisClick.hasClass("pressed")) {
                //agreeFlag = 0;
                ansAgree(answerId, '0', thisClick);
            } else {
                //agreeFlag = 1;
                ansAgree(answerId, '1', thisClick);
            }
        } else {
            if (thisClick.hasClass("pressed")) {
                //agreeFlag = 0;
                ansOpposition(answerId, '0', thisClick);
                //ansAgree(answerId, 0, thisClick)
            } else {
                //agreeFlag = 2;
                ansOpposition(answerId, '2', thisClick);
                //ansAgree(answerId, 2, thisClick)
            }
        }


        //ansAgree(answerId, agreeFlag, thisClick)
    })

}

function agreeClick(thisClick) {
    if (thisClick.hasClass("click-like")) {
        if (thisClick.hasClass("pressed")) {
            thisClick.removeClass("pressed")
            thisClick.attr("title", "赞同");
            var agreeNum = thisClick.children("span").html();
            thisClick.children("span").html(parseInt(agreeNum) - 1);
        } else {
            thisClick.addClass("pressed");
            thisClick.attr("title", "取消赞同");
            var agreeNum = thisClick.children("span").html();
            thisClick.children("span").html(parseInt(agreeNum) + 1);
            if (thisClick.next().hasClass("pressed")) {
                thisClick.next().removeClass("pressed");
                thisClick.next().attr("title", "取消反对")
            }
        }
    } else {
        //反对按钮处理
        if (thisClick.hasClass("pressed")) {
            thisClick.removeClass("pressed");
            thisClick.attr("title", "反对");
        } else {
            thisClick.addClass("pressed");
            thisClick.attr("title", "取消反对");
            if (thisClick.prev().hasClass("pressed")) {
                thisClick.prev().removeClass("pressed");
                thisClick.prev().attr("title", "赞同");
                var agreeNum = thisClick.prev().children("span").html();
                thisClick.prev().children("span").html(parseInt(agreeNum) - 1);
            }
        }
    }
}

