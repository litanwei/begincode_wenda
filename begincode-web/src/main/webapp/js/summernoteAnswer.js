
$(document).ready(function() {
   edit1()
});

//显示无工具栏富文本
var edit1 = function () {
    $('#summernote').summernote({
        focus: true,
        placeholder: '请输入回答，邀请人回答请输入@。',
        toolbar: false,
        height: 120,
    });

}
//切换富文本状态
var updataSummernote = function () {
    save();
    edit();
}
//显示富文本 工具栏 @人功能
var edit = function() {
    $('.click2edit').summernote({
        focus: true,
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
        height: 100,
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

};

//清除富文本
var save = function() {
    $('.click2edit').summernote('destroy');
};

//查询需要@的用户名
function mentionsinit() {
    var result;
    $.ajax({
        type:"POST",
        url:ctx+"/user/users.htm",
        dataType:"json",
        async:false,
        success:function(data){
            result = data.data;
        }
    });
    return result;
};