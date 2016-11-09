/**
 * 获取活跃用户
 * 亢良
 * 2016年8月29日
 */
	
	

    function getLabels(){

        $.ajax({
            type: 'POST',
            url: "getLabel.htm" ,
            success: function(data){

                var list = data.data;
                if(list != null && list != ""){

                    for (var i = 0; i < list.length; i++) {
                        $(".tag-list").append("<a target='_blank' class='list-tag' onclick='selectLabel("+list[i].labelId+")'>"+list[i].labelName+"</a>");
                    }

                }
            } ,
            dataType: 'json'
        });
    }
    function selectLabel(id){
        window.location.href="/label/selectProblemLabel.htm?id="+id;
    }