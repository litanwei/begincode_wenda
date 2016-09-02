

$(document).ready(function(){
    $("#problemSend").click(function(){
        $("#problemSend").attr("disabled","disabled");
        $("#problemSend").attr("value","正在提交");
        $('#content').val($('#summernote').summernote('code')); //使summernote里面的内容放到隐藏的content中
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
    });
});