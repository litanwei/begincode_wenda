/**
 * 页面加载后事件
 * 亢良
 * 2016年8月29日
 */

$(document).ready(function () {
    getLabels();
    getActivers();
    MessageAndMyProblem();
});
function getLabels() {
    $.ajax({
        type: 'POST',
        url: ctx + "/label/getLabel.htm",
        success: function (data) {
            var list = data.data;
            if (list != null && list != "") {
                for (var i = 0; i < list.length; i++) {
                    $(".tag-list").append("<a target='_blank' class='list-tag ' style='cursor:pointer' href='"+ctx +"/label/selectProblemLabel.htm?id="+ list[i].labelId +"'>" + list[i].labelName + "</a>");
                }
            }
        },
        dataType: 'json'
    });
}
function getActivers() {
    $(".list-group").html("<a class='list-group-item disabled'>最活跃用户</a>");
    $.ajax({
        type: 'POST',
        url: ctx + "/user/activer.htm",
        success: function (data) {
            var list = data.data;
            if (list != null && list != "") {
                for (var i = 0; i < list.length; i++) {
                    $(".list-group").append("<a class='list-group-item' href='"+ctx+"/user/"+list[i].nickname+".htm'>" + list[i].nickname + "");
                }
            }
        },
        dataType: 'json'
    });
}

function MessageAndMyProblem(){
    if(getCookie("openId")=="" && getCookie("accessToken")==""){
        $("#myProblemId").remove();
        $("#messageId").remove();
    }
}

/**
 * 根据cookieName查找对应的值
 * @param cookieName
 * @returns {*}
 */
function getCookie(cookieName) {
    var strCookie = document.cookie;
    var arrCookie = strCookie.split("; ");
    for (var i = 0; i < arrCookie.length; i++) {
        var arr = arrCookie[i].split("=");
        if (cookieName == arr[0]) {
            return arr[1];
        }
    }
    return "";
}