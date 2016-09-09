

$(document).ready(function(){
    $("#problemSend").click(function() {
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
                }
            },
            error: function () {
                alert("提交失败");
                $("#problemSend").removeAttr("disabled");
            }
        });
    })
});


function checkTitle(titleId)
{
    var title = $("#"+titleId).val();
    if(title.trim() == null)
    {
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
    var label = $("#"+labelId).val();
    var labelList = label.replace("，",",").split(",");
    var pattern = /^[\u4e00-\u9fa5_a-zA-Z0-9]+$/;
    if(label.trim() == null)
    {
        return false;
    }
    for(var labelName in labelList)
    {
        if(pattern.test(labelName))
        {
            continue;
        }
        else {
            return false;
        }
    }
    return true;
}
function checkContent(contentId)
{
    var content = $("#"+contentId).val();
    if(content.trim() == null)
    {
        return false;
    }
    return true;
}



function submitProblem()
{
    $("#problemSend").attr("disabled","disabled");
    $("#problemSend").attr("value","正在提交");
    $.ajax({
        data:$("#problemForm").serializeArray(),
        type:"POST",
        url:"/problem.htm",
        dataType:"json",
        success:function(data){
            if(data!=null)
            {
                alert(data.msg);
                $("#problemSend").removeAttr("disabled");
            }

        },
        error:function(){
            alert("提交失败");
            $("#problemSend").removeAttr("disabled");
        }
    });
}



