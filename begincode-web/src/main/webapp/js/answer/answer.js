// 回答富文本
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
// 发送回答
$(document).ready(function () {
    $("#answerSend").click(function () {

        var content = $('#summernote').summernote('code');

        var answer = new FormData();
        answer.append("content", content);
        $.ajax({
            data: answer,
            type: "POST",
            url: "/answer/reply.htm",
            dataType: "json",
            cache: false,
            contentType: false,
            processData: false,
            success: function (data) {
            },
            error: function (data) {

            }
        });
    })
})