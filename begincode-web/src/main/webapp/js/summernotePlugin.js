$(document).ready(function() {
    $('#summernote').summernote({
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
        height: 300,
        hint: {
            mentions: mentionsinit(),
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
    //查询需要@的用户名
    function mentionsinit() {
        var result;
        $.ajax({
            type:"POST",
            url:"/user/users.htm",
            dataType:"json",
            async:false,
            success:function(data){
                result = data.data;
            }
        });
        return result;
    }





});