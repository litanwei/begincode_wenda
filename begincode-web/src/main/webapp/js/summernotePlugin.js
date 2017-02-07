var docId ;
function summerInit(documentId) {
    docId = documentId;
    $(documentId).summernote({
        lang: 'zh-CN',
        callbacks: {
            onImageUpload: function (files,editor,$editable) { //the onImageUpload API
                sendFile(files[0],editor,$editable);
            }
        },
        toolbar: [
            ['style', ['style','bold', 'italic', 'underline', 'clear']],
            ['insert', ['picture', 'link', 'table', 'hr']],
            ['fontsize', ['fontsize']],
            ['color', ['color']],
            ['para', ['ul', 'ol', 'paragraph']],
            ['height', ['height']],
            // ['musc', ['codeview']],
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
            template: function (item) {
                return '<a id="message'+ item.split(",")[0] +'" class="message_">@' + item.split(",")[1] + '</a>';
            },
            content: function (item) {
                return $("#message"+ item.split(",")[0]).attr("href",ctx+"/user/"+item.split(",")[0]+".htm").removeAttr("id")[0];
            }
        }
    });
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
}

function sendFile(file,editor,welEditable) {
    data = new FormData();
    data.append("file", file);
    $.ajax({
        data: data,
        type: "POST",
        url: ctx +"/summernote/imgUpload.htm",
        cache: false,
        contentType: false,
        processData: false,
        dataType:"json",
        success: function(data) {
            if (data.code == 0) {
                $(docId).summernote('insertImage', "http://ohjuerr0t.bkt.clouddn.com/"+data.data.key);
            } else {
                showModelNoBack(data.msg);
            }
        }
    });
}