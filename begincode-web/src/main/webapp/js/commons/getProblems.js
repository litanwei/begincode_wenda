/**
 * Created by Stay on 2016/9/17.
 */

/**
 * 此函数用于ajax后 返回map对象
 * map必须有的key: answerSize value:调用Problemhandler里面的problemToAnswerSize方法  (问题对应的回答数)
 *                 labelName  value:调用Problemhandler里面的problemToLabel方法    (对应的标签名)
 *                 problems     value:调用Problemhandler对应你要查找的问题集合
 *                 answer     value: 调用Problemhandler selectOrderByProblemId的方法
 * @param map
 * @param id    要在div后添加问题列表 的id号
 */
function getProblems(map, id) {
    $("#" + id).empty();
    var size = new Array();
    var answer = new Array();
    var labelDiv = new Array();
    var lbName = "";
    $.each(map.answerSize, function (i) {
        size[i] = map.answerSize[i];
    });
    $.each(map.answer, function (i) {
        answer[i] = map.answer[i];
    });
    $.each(map.labelName, function (i) {
        var labelName = map.labelName[i];
        var label = labelName.toString().split(",");
        if (label.length == 1) {
            lbName = '<a href="#" target="_blank"  class="list-tag">' + label[0] + '</a>';
        } else {
            for (var j = 0; j < label.length; j++) {
                lbName = lbName + '<a href="#" target="_blank" class="list-tag">' + label[j] + '</a>';
            }
        }
        labelDiv[i] = lbName;
        lbName = "";
    });
    $.each(map.problems.page.data, function (i) {
        var solve = "";
        if (map.problems.page.data[i].solve == 0) {
            solve = '<div class="answers answered">' +  numFormat(size[i]) + '<small>回答</small></div>';
        } else {
            solve = '<div class="answers solved">' +  numFormat(size[i]) + '<small>解决</small></div>';
        }
        var problemList = '<section class="stream-list__item">'
            + '<div class="qa-rank">'
            + '<div class="votes plus hidden-xs">'
            + numFormat(map.problems.page.data[i].voteCount)
            + '<small>投票</small>'
            + '</div>'
            + solve
            + '<div class="views hidden-xs">'
            + numFormat(map.problems.page.data[i].viewCount)
            + '<small>浏览</small>'
            + '</div>'
            + '</div>'
            + '<div class="summary">'
            + '<ul class="author list-inline ">'
            + '<li>'
            + '<a style="text-decoration:none;">'
            + formatTime(map.problems.page.data[i].userName, size[i], answer[i], map.problems.page.data[i].createTime)
            + '</a>'
            + '</li>'
            + '</ul>'
            + '<span class="keyword-list ">'
            + '<h2 class="title l"><a href="/problem/'
            + map.problems.page.data[i].problemId + ".htm"
            + '">'
            + map.problems.page.data[i].title
            + '</a></h2>'
            + labelDiv[i+1]
            + '</span></div></section>';
        $("#" + id).append(problemList);
    });
}