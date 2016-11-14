$(document).ready(function() {
    $('#summernote').summernote({
        callbacks: {
            onImageUpload: function (files) { //the onImageUpload API
                img = sendFile(files[0]);
            }
        },
        toolbar: [
            // [groupName, [list of button]]
            ['style', ['style','bold', 'italic', 'underline', 'clear']],
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
            url:ctx+"/user/users.htm",
            dataType:"json",
            async:false,
            success:function(data){
                result = data.data;
            }
        });
        return result;
    }
});

function sendFile(file) {
    var filename = false;
    try{
        filename = file['name'];
    } catch(e){
        filename = false;
    }
    if(!filename){
        $(".note-alarm").remove();
    }
    //以上防止在图片在编辑器内拖拽引发第二次上传导致的提示错误
    var ext = filename.substr(filename.lastIndexOf("."));
    ext = ext.toUpperCase();
    //name是文件名，自己随意定义，aid是我自己增加的属性用于区分文件用户
    data = new FormData();
    data.append("file", file);
    data.append("token","22liIyhpQX6QXv-tFS6HpWGWnAD6J4M8bRKOaDMKv:zjGSlI9tXPxicotT15kPp79N99o=:eyJzY29wZSI6InlhbmdzaiIsImRlYWRsaW5lIjozMjE2MzMyMzUwfQ==");
//	    url = ctx+"/image";
    url="http://upload.qiniu.com/"
    $.ajax({
        data: data,
        type: "POST",
        url: url,
        cache: false,
        contentType: false,
        processData: false,
        success: function (url) {
            if(url != ""){
                $('#summernote').summernote('insertImage', "http://7xjcjk.com1.z0.glb.clouddn.com/"+url.key);
            }else{
                alert("图片上传失败");
            }

        }
    });
}