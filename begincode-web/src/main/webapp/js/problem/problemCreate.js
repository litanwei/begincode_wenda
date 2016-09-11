$(document).ready(function () {
    $("#problemSend").click(function () {
        // if (QC.Login.check()) {
            if (checkProblemForm()) {
                $('#content').val($('#summernote').summernote('code')); //使summernote里面的内容放到隐藏的content中
                $("#problemSend").attr("disabled", "disabled");
                $("#problemSend").attr("value", "正在提交");
                $.ajax({
                    data: $("#problemForm").serializeArray(),
                    type: "POST",
                    url: "/problem.htm",
                    dataType: "json",
                    success: function (data) {
                        if (data != null) {
                            alert(data.msg);
                            $("#problemSend").removeAttr("disabled");
                            window.location.href = "/";
                        }
                    },
                    error: function (data) {
                        alert(data.msg);
                        $("#problemSend").removeAttr("disabled");
                    }
                });
            }
        // } else {
        //     //如果检测到没登录 则提示用户
        //     if ($("#warn").length) {
        //     } else {
        //         $("#warning").append('<div id="warn" class="alert alert-warning"> <a href="#" class="close" data-dismiss="alert">  </a> ' +
        //             '<strong>通知！</strong>登录后才能开始提问。 </div>');
        //     }
        // }
    })
    //把用户id和nickName赋给对应的隐藏域中
    var openId = {openId: getCookie("openId")};
    $.ajax({
        data: openId,
        type: "POST",
        dataType: "json",
        url: "/user/openId.htm",
        success: function (begincodeUser) {
            $("#begincodeUserId").attr("value", begincodeUser.begincodeUserId);
            $("#userName").attr("value", begincodeUser.nickname);
        },
        error: function () {
            alert("系统异常！,请重新提问");
            window.location.href = "/";
        }
    });

});
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


function checkProblemForm() {
    if ($("#title").val().trim() == "") {
        $("#titleWarning").html('<p><font size="3" face="arial" color="red">标题不能为空</font></p>');
        return false;
    }
    return true;
}


/**
 * 检查标签名是否为空 或者有不属于字母 数字 中文 下划线的字符出现
 * @param labelId   传入的id名
 * @returns {boolean}  含有不符合要求的字符返回false  正确返回true
 */
function checkLabel(labelId) {
    var label = $("#" + labelId).val();
    var labelList = label.replace("，", ",").split(",");
    var pattern = /^[\u4e00-\u9fa5_a-zA-Z0-9]+$/;
    if (label.trim() == null) {
        return false;
    }
    for (var labelName in labelList) {
        if (pattern.test(labelName)) {
            continue;
        }
        else {
            return false;
        }
    }
    return true;
}



