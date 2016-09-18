$(document).ready(function () {
    $("#problemSend").click(function () {
        if (checkProblemForm()) {
            $('#content').val($('#summernote').summernote('code')); //使summernote里面的内容放到隐藏的content中
            $("#problemSend").attr("disabled", "disabled");
            $("#problemSend").attr("value", "正在提交");
            $.ajax({
                data: $("#problemForm").serializeArray(),
                type: "POST",
                url: "/problem/store.htm",
                dataType: "json",
                success: function (data) {
                    if (data.success != null) {
                        alert(data.success);
                        $("#problemSend").removeAttr("disabled");
                        window.location.href = "/";
                    } else if (data.msg != null) {
                        $("#myModal").modal("show");
                    }
                },
                error: function (data) {
                    alert("请检查输入,或者网络连接!");
                    $("#problemSend").removeAttr("disabled");
                }
            });
        }
    })

});


/**
 * 提问表单验证
 * @returns {boolean} 通过返回true 失败返回false
 */
function checkProblemForm() {
    if ($("#title").val().trim() == "" || $("#title").val().trim().length > 100) {
        $("#valueValidate").html("标题为空,或者字数大于100");
        $("#valueModal").modal("show");
        return false;
    } else if ($("#labelName").val().trim() == "") {
        $("#valueValidate").html("标签为空,请输入一个标签!");
        $("#valueModal").modal("show");
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



