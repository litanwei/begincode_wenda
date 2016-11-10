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
            + problemFormatTime(page.data.data[i].problem.userName, page.data.data[i].problem.answerCount, page.data.data[i].answerName,page.data.data[i].problem.createTime,page.data.data[i].answerTime)
            + '</a>'
            + '</li>'
            + '</ul>'
            + '<span class="keyword-list ">'
            + '<h2 class="title l"><a href="'+ctx+'/problem/'
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
 * 此方法用于返回 xxx 前提问(回答)
 * @param problemAuthorName   问题的作者
 * @param size    问题对应的回答数
 * @param answerName   回答者名称
 * @param time     问题创建的时间  (时间格式: yyyy-MM-dd HH:mm:ss)
 * @returns {string}   返回的文字
 */
function problemFormatTime(problemAuthorName, size, answerName, time,answerTime) {
    console.log(answerTime);
    var date = new Date(time);
    var dateNow = new Date();
    var diffTime = (dateNow.getTime() - date.getTime()) / 1000;
    var answerDate = new Date(answerTime);
    var answerDateNow = new Date();
    var answerDiffTime = (answerDateNow.getTime() - answerDate.getTime()) / 1000;
    if (size == 0) {
        if (diffTime < 60) {
            return "<a href='"+ctx+"/user/" + problemAuthorName + ".htm'>" + problemAuthorName + "</a>" + " " + " 刚刚提问";
        }
        else if (diffTime > 60 && diffTime < 3600) {
            return "<a href='"+ctx+"/user/" + problemAuthorName + ".htm'>" + problemAuthorName + "</a>" + " " + Math.ceil(diffTime / 60) + "分钟前提问";
        }
        else if (diffTime > 3600 && diffTime < 86400) {
            return "<a href='"+ctx+"/user/" + problemAuthorName + ".htm'>" + problemAuthorName + "</a>" + " " + Math.ceil(diffTime / 60 / 60) + "小时前提问";
        } else if (diffTime > 86400 && diffTime < 259000) {
            return "<a href='"+ctx+"/user/" + problemAuthorName + ".htm'>" + problemAuthorName + "</a>" + " " + Math.ceil(diffTime / 60 / 60 / 24) + "天前提问";
        } else {
            return "<a href='"+ctx+"/user/" + problemAuthorName + ".htm'>" + problemAuthorName + "</a>" + " " + (date.getMonth() + 1) + "月" + date.getDate() + "日" + "前提问";
        }
    } else {
        if (answerDiffTime < 60) {
            return "<a href="+ctx+"'/user/" + answerName + ".htm'>" + answerName + "</a>" + " "  + "刚刚回答";
        }
        else if (answerDiffTime > 60 && answerDiffTime < 3600) {
            return "<a href='"+ctx+"/user/" + answerName + ".htm'>" + answerName + "</a>" + " "  + Math.ceil(answerDiffTime / 60) + "分钟前回答";
        }
        else if (answerDiffTime > 3600 && answerDiffTime < 86400) {
            return "<a href='"+ctx+"/user/" + answerName + ".htm'>" + answerName + "</a>" + " "  + Math.ceil(answerDiffTime / 60 / 60) + "小时前回答";
        } else if (answerDiffTime > 86400 && answerDiffTime < 259000) {
            return "<a href='"+ctx+"/user/" + answerName + ".htm'>" + answerName + "</a>" + " "  + Math.ceil(answerDiffTime / 60 / 60 / 24) + "天前回答";
        } else {
            return "<a href='"+ctx+"/user/" + answerName + ".htm'>" + answerName + "</a>" + " "  + (answerDate.getMonth() + 1) + "月" + answerDate.getDate() + "日" + "前回答";
        }
    }
}
