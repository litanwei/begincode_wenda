var flag;

$(document).ready(function () {
    edit1();

});
//显示无工具栏富文本
var edit1 = function () {
    flag = true;
    $('#summernote').summernote({
        focus: true,
        placeholder: '请输入回答，邀请人回答请输入@。',
        toolbar: false,
        height: 120,
    });
}
//切换富文本状态
var updataSummernote = function () {
    if (flag == true) {
        save();
        edit();
        flag = false;
    }
}
//显示富文本 工具栏 @人功能
var edit = function () {
    summerInit('.click2edit');   //调用summernotePlugin.js 里面的 summerInit() 方法
}
//清除富文本
var save = function () {
    $('.click2edit').summernote('destroy');
};

