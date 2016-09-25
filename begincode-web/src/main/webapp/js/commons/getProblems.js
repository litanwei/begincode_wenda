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
function getProblems(page, id) {
    $("#" + id).empty();
    var labelDiv = new Array();
    var lbName = "";
    $.each(page.data, function (i) {
        for(var j=0;j<page.data[i].labelNameList.length;j++){
        lbName = lbName + '<a href="#" target="_blank" class="list-tag">' + page.data[i].labelNameList[j] + '</a>';
        }
        labelDiv[i] = lbName;
        lbName = "";
    });
    $.each(page.data, function (i) {
        var solve = "";
        if (page.data[i].problem.solve == 0) {
            solve = '<div class="answers answered">' +  numFormat(page.data[i].problem.answerCount) + '<small>回答</small></div>';
        } else {
            solve = '<div class="answers solved">' +  numFormat(page.data[i].problem.answerCount) + '<small>解决</small></div>';
        }
        var problemList = '<section class="stream-list__item">'
            + '<div class="qa-rank">'
            + '<div class="votes plus hidden-xs">'
            + numFormat(page.data[i].problem.voteCount)
            + '<small>投票</small>'
            + '</div>'
            + solve
            + '<div class="views hidden-xs">'
            + numFormat(page.data[i].problem.viewCount)
            + '<small>浏览</small>'
            + '</div>'
            + '</div>'
            + '<div class="summary">'
            + '<ul class="author list-inline ">'
            + '<li>'
            + '<a style="text-decoration:none;">'
            + formatTime(page.data[i].problem.userName, page.data[i].problem.answerCount, page.data[i].answerName,page.data[i].problem.createTime)
            + '</a>'
            + '</li>'
            + '</ul>'
            + '<span class="keyword-list ">'
            + '<h2 class="title l"><a href="/problem/'
            + page.data[i].problem.problemId + ".htm"
            + '">'
            + page.data[i].problem.title
            + '</a></h2>'
            + labelDiv[i]
            + '</span></div></section>';
        $("#" + id).append(problemList);
    });
}