/**
 * Created by Stay on 2016/10/17.
 */
$(document).ready(function () {
    //用户名获取
    var name = $("#nickName").html();
    var myChart = echarts.init(document.getElementById('echart'));
    $.ajax({
        type: "GET",
        url: "/user/echarts/" + name + ".htm",
        dataType: "json",
        success: function (data) {
            myChart.showLoading();
            if (data.code == 0) {
                myChart.hideLoading();
                var mapData = JSON.stringify(data.data.seriesData).replace("}", "").replace("{", "").replace("\"","").split(",");
                var array = [];
                for (var j = 0; j < mapData.length; j++) {
                    var map = {};
                    var tempMap = mapData[j].replace("\"","").replace("\"","").split(":");
                    map.value = tempMap[1];
                    map.name = tempMap[0];
                    array[j] = map;
                }
                //开始获取标签名
                var labelList = [];
                for (var i = 0; i < data.data.label.length; i++) {
                    labelList.push(data.data.label[i].labelName);
                }
                myChart.setOption({
                    tooltip: {
                        trigger: 'item',
                        formatter: "{a} <br/>{b}: {c} ({d}%)"
                    },
                    legend: {
                        orient: 'horizontal',
                        x: 'right',
                        data: labelList,
                    },
                    series: [
                        {
                            name: '技术栈',
                            type: 'pie',
                            radius: ['50%', '70%'],
                            avoidLabelOverlap: false,
                            label: {
                                normal: {
                                    show: false,
                                    position: 'center'
                                },
                                emphasis: {
                                    show: true,
                                    textStyle: {
                                        fontSize: '12',
                                        fontWeight: 'bold'
                                    }
                                }
                            },
                            labelLine: {
                                normal: {
                                    show: false
                                }
                            },
                            data: array
                        }
                    ]
                })

            }
        }
    });
})
