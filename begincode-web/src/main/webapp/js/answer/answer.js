// 回复反馈
function sendFeedback(answerId) {
    var answer = new FormData();
    answer.append("answerId", answerId);
    $.ajax({
        data: answer,
        type: "POST",
        url: "/problem/feedback.htm",
        dataType: "json",
        contentType: false,
        processData: false,
        success: function (data) {
            if (data != null) {
                alert(data.msg);
            }
        }, error: function() {
            alert("提交错误!");
        },
    })
}
// summernote初始化
$(document).ready(function() {
    $('#summernote').summernote({
        height: 150,
        toolbar: [
            // [groupName, [list of button]]
            ['style', ['bold', 'italic', 'underline', 'clear']],
            ['insert', ['picture', 'link', 'table', 'hr']],
            ['fontsize', ['fontsize']],
            ['color', ['color']],
            ['para', ['ul', 'ol', 'paragraph']],
            ['height', ['height']],
            ['musc', ['codeview']],
        ],
        hint: {
            mentions: ['jayden', 'sam', 'alvin', 'david'],
            match: /\B@(\w*)$/,
            search: function (keyword, callback) {
                callback($.grep(this.mentions, function (item) {
                    return item.indexOf(keyword) == 0;
                }));
            },
            content: function (item) {
                return '@' + item;
            }
        }
    });
});
// 回复 绑定回复内容 问题id
$(document).ready(function () {
    $("#answerSend").click(function () {
        $("#answerSend").attr("disabled", true);
        var problemId = $('#problemId').attr('extra');
        var content = $('#summernote').summernote('code');
        var answer = new FormData();
        answer.append("content", content);
        answer.append("problemId", problemId);
        $("#answerSend").attr("disabled","disabled");//按钮不可用
        $.ajax({
            data: answer,
            type: "POST",
            url: "/problem/reply.htm",
            dataType: "json",
            cache: false,
            async: false,
            contentType: false,
            processData: false,
            success: function (data) {
                if(data!=null)
                {
                    alert(data.msg);
                    window.location.reload();
                    $("#answerSend").removeAttr("disabled");//按钮可用
                }
            }, error:function(){
                alert("提交失败");
                $("#answerSend").removeAttr("disabled");//按钮可用
            }

        });
    })
})