/**
 * Created by Stay on 2016/9/17.
 */

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
        for(var j=0;j<page.data.data[i].labelNameList.length;j++){
        lbName = lbName + '<a href="#" target="_blank" class="list-tag">' + page.data.data[i].labelNameList[j] + '</a>';
        }
        labelDiv[i] = lbName;
        lbName = "";
    });
    $.each(page.data.data, function (i) {
        var solve = "";
        if (page.data.data[i].problem.solve == 0) {
            solve = '<div class="answers answered">' +  numFormat(page.data.data[i].problem.answerCount) + '<small>回答</small></div>';
        } else {
            solve = '<div class="answers solved">' +  numFormat(page.data.data[i].problem.answerCount) + '<small>解决</small></div>';
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
            + formatTime(page.data.data[i].problem.userName, page.data.data[i].problem.answerCount, page.data.data[i].answerName,page.data.data[i].problem.createTime)
            + '</a>'
            + '</li>'
            + '</ul>'
            + '<span class="keyword-list ">'
            + '<h2 class="title l"><a href="/problem/'
            + page.data.data[i].problem.problemId + ".htm"
            + '">'
            + page.data.data[i].problem.title
            + '</a></h2>'
            + labelDiv[i]
            + '</span></div></section>';
        $("#" + id).append(problemList);
    });
}