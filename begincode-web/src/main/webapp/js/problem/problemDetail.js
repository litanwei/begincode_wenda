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
                showModel(data.msg);
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
                    showModel(data.msg);
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
                    showModel(data.msg);
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
                    showModel(data.msg);
                }
            }
        })
    })


});