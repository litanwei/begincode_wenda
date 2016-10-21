/**
 * Created by Stay on 2016/10/17.
 */
$(document).ready(function () {
    //用户名获取
    var name = $("#nickName").html();
    $.ajax({
        type: "GET",
        url: "/user/problem/" + name + ".htm",
        dataType: "json",
        success: function (data) {
            if (data.code == 0) {
                getProblems(data, "hisProblems");
                userPagination(data.data, "/problem/" + name, "hisProblems", "GET");
            } else {
                showModel(data.msg);
            }
        }
    });

    $("#problem").click(function () {
        $.ajax({
            type: "GET",
            url: "/user/problem/" + name + ".htm",
            dataType: "json",
            success: function (data) {
                if (data.code == 0) {
                    getProblems(data, "hisProblems");
                    userPagination(data.data, "/problem/" + name, "hisProblems", "GET");
                } else {
                    showModel(data.msg);
                }
            }
        });
    });

    $("#answer").click(function () {
        $.ajax({
            type: "GET",
            url: "/user/answer/" + name + ".htm?page=1",
            dataType: "json",
            success: function (data) {
                if (data.code == 0) {
                    answerHtml("hisAnswers", data.data);
                    userPagination(data.data, "/answer/" + name, "hisAnswers", "GET");
                }
            }
        });
    });

    $("#collection").click(function () {
        $.ajax({
            type: "GET",
            url: "/user/collect/" + name + ".htm?page=1",
            dataType: "json",
            success: function (data) {
                if (data.code == 0) {
                    getProblems(data,"hisCollections");
                    userPagination(data.data,"/collect/"+name,"hisCollections","GET");
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
        if (data.data[i].adopt == 0) {
            adoptDiv = '<div class="answers">未采纳</div>';
        } else {
            adoptDiv = '<div class="answers solved">被采纳</div>';
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
            + '<a style="text-decoration:none;">'
            + formatTime(data.data[i].username, 1, data.data[i].username, data.data[i].createTime)
            + '</a>'
            + '</li>'
            + '</ul>'
            + '<span class="keyword-list ">'
            + '<h2 class="title l">'
            + '<a href="/problem/'
            + data.data[i].problemId
            + '.htm">'
            + data.data[i].content
            + '</a></h2>'
            + '</span>'
            + '</div>'
            + '</section>'
            + '</div>'
            + '</div>'
            + '</div>';
        $("#" + id).append(answerList);
    });
}