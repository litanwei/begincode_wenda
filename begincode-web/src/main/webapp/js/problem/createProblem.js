/**
 * Created by Stay on 2016/10/12.
 */
$(document).ready(function () {
    summerInit("#summernote");
    //提交问题点击事件
    $("#problemSend").click(function () {
        if (checkProblemForm()) {
            $('#content').val($('#summernote').summernote('code')); //使summernote里面的内容放到隐藏的content中
            $("#problemSend").attr("disabled", "disabled");
            $("#problemSend").attr("value", "正在提交");
            $.ajax({
                data: $("#problemForm").serializeArray(),
                type: "POST",
                url: ctx + "/problem/store.htm",
                dataType: "json",
                success: function (data) {
                    console.log(data);
                    if (data.code == 0) {
                        $("#problemSend").removeAttr("disabled");
                        window.location.href = ctx + "/";
                    } else {
                        showModelNoBack(data.msg);
                    }
                },
            });
        }
    })
})
/**
 * 提问表单验证
 * @returns {boolean} 通过返回true 失败返回false
 */
function checkProblemForm() {
    if ($("#title").val().trim() == "" || $("#title").val().trim().length > 100) {
        showModelNoBack("标题为空,或者字数大于100");
        return false;
    } else if (!checkLabel($("#labelName").val())) {
        showModelNoBack("标签为空,或者有非法字符");
        return false;
    }
    return true;
}

/**
 * 检查标签名是否为空 或者有不属于字母 数字 中文 下划线 中划线的字符出现
 * @param label
 * @returns {boolean}  不符合返回false 符合返回true
 */
function checkLabel(label) {
    var labelList = label.replace("，", ",").split(",");
    var pattern = /^[a-zA-Z0-9_\-\u4e00-\u9fa5]+$/;
    if (label.trim() == null && label.trim() == "") {
        return false;
    }
    if (labelList.length > 0) {
        for (var i = 0; i < labelList.length; i++) {
            if (!pattern.test(labelList[i])) {
                return false;
            }
        }
        return true;
    } else {
        return false;
    }
}