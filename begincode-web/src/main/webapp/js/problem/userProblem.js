/**
 * Created by Stay on 2016/10/17.
 */
$(document).ready(function () {
    //用户名获取
    var begincodeUserId = $("#begincodeUserId").val();
    $.ajax({
        type: "GET",
        url: ctx+"/user/problem/" + begincodeUserId + ".htm",
        dataType: "json",
        success: function (data) {
            if (data.code == 0) {
                getProblems(data, "hisProblems");
                userPagination(data.data, "/problem/" + begincodeUserId, "hisProblems", "GET");
            } else {
                showModel(data.msg);
            }
        }
    });

    $("#problem").click(function () {
        $.ajax({
            type: "GET",
            url: ctx+ "/user/problem/" + begincodeUserId + ".htm",
            dataType: "json",
            success: function (data) {
                if (data.code == 0) {
                    getProblems(data, "hisProblems");
                    userPagination(data.data, "/problem/" + begincodeUserId, "hisProblems", "GET");
                } else {
                    showModel(data.msg);
                }
            }
        });
    });

    $("#answer").click(function () {
        $.ajax({
            type: "GET",
            url: ctx+"/user/answer/" + begincodeUserId + ".htm?page=1",
            dataType: "json",
            success: function (data) {
                if (data.code == 0) {
                    answerHtml("hisAnswers", data.data);
                    userAnswerPagination(data.data, "/answer/" + begincodeUserId, "hisAnswers", "GET");
                }
            }
        });
    });

    $("#collection").click(function () {
        $.ajax({
            type: "GET",
            url: ctx+"/user/collect/" + begincodeUserId + ".htm?page=1",
            dataType: "json",
            success: function (data) {
                if (data.code == 0) {
                    getProblems(data,"hisCollections");
                    userPagination(data.data,"/collect/"+begincodeUserId,"hisCollections","GET");
                }
            }
        })
    });


});

function showModel(msg) {
    $("#errorMessage").html(msg);
    $("#ajaxModal").modal({backdrop: 'static', keyboard: false}).modal("show");   //禁用点击空白地方关闭modal框
}


function answerHtml(id, data) {
    $("#" + id).empty();
    var adoptDiv = "";
    $.each(data.data, function (i) {
        var content = data.data[i].content;
        content = content.replace(/<\/?[^>]*>/g,'').substring(0,100);
        if (data.data[i].adopt == 0) {
            adoptDiv = '<div class="answers">0<small>未采纳</small></div>';
        } else {
            adoptDiv = '<div class="answers solved">1<small>被采纳</small></div>';
        }
        var answerList = '<div class="tab-content">'
            + '<div role="tabpanel" class="tab-pane active" id="home">'
            + '<div class="container-fluid">'
            + '<section class="stream-list__item">'
            + '<div class="qa-rank">'
            + '<div class="votes hidden-xs">'
            + data.data[i].agreeCount
            + '<small>赞同</small>'
            + '</div>'
            + adoptDiv
            + '<div class="views hidden-xs">'
            + data.data[i].oppositionCount
            + '<small>反对</small>'
            + '</div>'
            + '</div>'
            + '<div class="summary">'
            + '<ul class="author list-inline">'
            + '<li>'
            + '<a style="text-decoration:none;">' + data.data[i].userName + '  '
            + formatTime(data.data[i].createTime)
            + '</a>'
            + '</li>'
            + '</ul>'
            + '<span class="keyword-list ">'
            + '<h2 class="title l">'
            + '<a href=" '+ ctx+'/problem/'
            + data.data[i].problemId
            + '.htm">'
            + content
            + ' </a></h2>'
            + '</span>'
            + '</div>'
            + '</section>'
            + '</div>'
            + '</div>'
            + '</div>';
        $("#" + id).append(answerList);
    });
}

/**
 * 此函数用于ajax后 返回Object对象
 *
 * @param page
 * @param id    要在div后添加问题列表 的id号
 */
function getProblems(page, id) {
    $("#" + id).empty();
    var labelDiv = new Array();
    var lbName = "";
    $.each(page.data.data, function (i) {
        for (var j = 0; j < page.data.data[i].labelNameList.length; j++) {
            lbName = lbName + '<a href="" style="text-decoration:none;" target="_blank" class="list-tag">' + page.data.data[i].labelNameList[j] + '</a>';
        }
        labelDiv[i] = lbName;
        lbName = "";
    });
    $.each(page.data.data, function (i) {
        var solve = "";
        if (page.data.data[i].problem.solve == 0) {
            solve = '<div class="answers answered">' + numFormat(page.data.data[i].problem.answerCount) + '<small>回答</small></div>';
        } else {
            solve = '<div class="answers solved">' + numFormat(page.data.data[i].problem.answerCount) + '<small>解决</small></div>';
        }
        var problemList = '<section class="stream-list__item">'
            + '<div class="qa-rank">'
            + '<div class="votes plus hidden-xs">'
            + numFormat(page.data.data[i].problem.voteCount)
            + '<small>投票</small>'
            + '</div>'
            + solve
            + '<div class="views hidden-xs">'
            + numFormat(page.data.data[i].problem.viewCount)
            + '<small>浏览</small>'
            + '</div>'
            + '</div>'
            + '<div class="summary">'
            + '<ul class="author list-inline ">'
            + '<li>'
            + '<a style="text-decoration:none;">'
            + problemFormatTime(page.data.data[i].answerUserId,page.data.data[i].problem.begincodeUserId,page.data.data[i].problem.userName, page.data.data[i].problem.answerCount, page.data.data[i].answerName, page.data.data[i].problem.createTime, page.data.data[i].answerTime)
            + '</a>'
            + '</li>'
            + '</ul>'
            + '<span class="keyword-list ">'
            + '<h2 class="title l"><a href="' + ctx + '/problem/'
            + page.data.data[i].problem.problemId + ".htm"
            + '">'
            + page.data.data[i].problem.title
            + '</a></h2>'
            + labelDiv[i]
            + '</span></div></section>';
        $("#" + id).append(problemList);
    });
}

/**
 *
 * @param answerUserId   回答用户id
 * @param problemAuthorNameId  提问作者id
 * @param problemAuthorName  作者名
 * @param size   提问数
 * @param answerName  回答人的名字
 * @param time   提问时间
 * @param answerTime  回答时间
 * @returns {string}
 */
function problemFormatTime(answerUserId,problemAuthorNameId,problemAuthorName, size, answerName, time, answerTime) {
    if (size == 0) {
        return "<a href='" + ctx + "/user/" + problemAuthorNameId + ".htm'>" + problemAuthorName + "</a>" + " " + formatTime(time);
    } else {
        return "<a href=" + ctx + "'/user/" + answerUserId + ".htm'>" + answerName + "</a>" + " " + formatTime(answerTime);
    }
}
