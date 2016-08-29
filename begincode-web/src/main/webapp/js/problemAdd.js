

$(document).ready(function(){
    $("#questionSend").click(function(){
        $('#content').val($('#summernote').summernote('code')); //使summernote里面的内容放到隐藏的content中
        console.log($("#questionForm").serializeArray());
        $.ajax({
            data:$("#questionForm").serializeArray(),
            type:"POST",
            url:"/problem/addProblem.htm",
            dataType:"json",
            success:function(data){
                alert(data.msg);
            }
        });
    });




});