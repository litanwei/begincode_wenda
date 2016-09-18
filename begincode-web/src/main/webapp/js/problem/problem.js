/**
 * Created by Stay on 2016/9/15.
 */
$(document).ready(function () {
    $.ajax({
        type: "GET",
        url: "/problem/newProblems.htm",
        dataType: "json",
        success: function (data) {
            getProblems(data, "newProblem");
        }
    });
    $("#hotProblemId").click(function () {
        $("#hotProblem").empty();
        $.ajax({
            type: "GET",
            url: "/problem/hotProblems.htm",
            dataType: "json",
            success: function (data) {
                getProblems(data,"hotProblem");
            }
        });
    });
    $("#myProblemId").click(function () {
        $("#myProblem").empty();
        $.ajax({
            type: "POST",
            url: "/problem/myProblems.htm",
            dataType: "json",
            success: function (data) {
                if (data.msg != null) {
                    $("#errorMessage").html(data.msg);
                    $("#ajaxModal").modal({backdrop: 'static', keyboard: false}).modal("show");   //禁用点击空白地方关闭modal框
                } else {
                    getProblems(data, "myProblem");
                }
            }
        });
    });
   /* $("#noAnswerProblems").click(function () {
        $("#noAnswerProblems").empty();
        $.ajax({
            type : "GET",
            url : "/problem/noAnswerProblems.htm",
            dataType : "json",
            success: function (data) {
                // getProblems(data,"noAnserProblem");
            }
        });

    });*/


});
