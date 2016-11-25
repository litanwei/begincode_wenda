<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="../commons/taglibs.jsp"%>
<script type="text/javascript"
        src="http://qzonestyle.gtimg.cn/qzone/openapi/qc_loader.js"
        data-appid="101230380"
        data-redirecturi="http://www.begincode.net/login.html"
        charset="utf-8"></script>
<script type="text/javascript">
    var ctx = "${ctx}";
    var reqd;
    //调用QC.Login方法，指定btnId参数将按钮绑定在容器节点中
    QC.Login({
                //btnId：插入按钮的节点id，必选
                btnId:"qqLoginBtn",
                //用户需要确认的scope授权项，可选，默认all
                scope : "get_user_info,upload_pic,get_user_cbinfo",//展示授权，全部可用授权可填 all
                //按钮尺寸，可用值[A_XL| A_L| A_M| A_S|  B_M| B_S| C_S]，可选，默认B_S
                size: "B_M",
                display : "pc"//应用场景，可选
            }, function(reqData, opts){//登录成功
                reqd = reqData;
                //根据返回数据，更换按钮显示状态方法
                var dom = document.getElementById(opts['btnId']),
                        _logoutTemplate = [
                            '<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">欢迎:{nickname}<span class="caret"></span></a>',
                            '<ul class="dropdown-menu">',
                            ' <li><a href="${ctx}/blog/blogManage">博文管理</a></li>',
                            ' <li role="separator" class="divider"></li>',
                            ' <li><a href="javascript:QC.Login.signOut();">退出</a></li>',
                            ' </ul>'
                        ].join("");
                dom && (dom.innerHTML = QC.String.format(_logoutTemplate, {
                    nickname: QC.String.escHTML(reqData.nickname),
                    figureurl: reqData.figureurl
                }));
                QC.Login.getMe(function (openId, accessToken) {
                    //调用注册或者存储方法
                    regUser(reqd.nickname, reqd.figureurl, reqd.gender, reqd.province, reqd.city, reqd.year, openId, accessToken);
                });
            }, function (opts) {//注销成功
                alert('QQ登录 注销成功');
                window.location.reload();
                delUser(reqd.nickname);
                sessionStorage.clear();
            }
    );
    function regUser(nickName, figureurl, gender, province, city, year, openId, accessToken) {
        jQuery.ajax({
            type: "POST",
            url: ctx+"/user/login.htm",
            data: "nickname=" + nickName + "&pic=" + figureurl + "&sex=" + gender + "&openId=" + openId + "&accessToken=" + accessToken,
            dataType: "json",
            success:  function(codes){
                alert(codes);
            }
        });
        //利用sessionStorage存储标识 登录成功刷新界面
        if (sessionStorage.getItem('refreshed') != 'true') {
            window.location.reload();
        }
        sessionStorage.setItem('refreshed', 'true')
    }
    function delUser(nickName) {
        jQuery.ajax({
            type: "POST",
            url: ctx+"/user/loginClean.htm",
            data: "nickname=" + nickName,
            dataType: "json",
            success: function (codes) {
                alert(codes);
            }
        });
    }

</script>

<nav class="navbar navbar-default" role="navigation">
    <div class="container-fluid">

        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed"
                    data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                <span class="sr-only">Toggle navigation</span> <span
                    class="icon-bar"></span> <span class="icon-bar"></span> <span
                    class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="http://www.begincode.net"><img
                    alt="初学者社区" src="${ctx}/images/navlogo.gif" style="height: 30px;"></a>
        </div>

        <div class="collapse navbar-collapse"
             id="bs-example-navbar-collapse-1">

            <ul class="nav navbar-nav">
                <li class=""><a href="http://www.begincode.net/course/" class="dropdown-toggle">在线教程
                </a></li>
                <li class=""><a href="http://www.begincode.net/wenda/" class="dropdown-toggle">问答
                </a></li>
                <li class=""><a href="http://www.begincode.net/code/" class="dropdown-toggle">代码分享</a>
                </li>
                <li class=""><a href="http://www.begincode.net/blog/" class="dropdown-toggle">博客文章</a>
                </li>
                <li class=""><a href="http://www.begincode.net/resource/" class="dropdown-toggle">学习资源</a>
                </li>


            </ul>
            <div id="navbar" class="navbar-collapse collapse">

                <form class="navbar-form navbar-right" action="${ctx}/search.htm" method="get">
                    <span id="qqLoginBtn" class="dropdown"></span>
                    <div class="form-group">
                        <input type="text" name="q" class="form-control " placeholder="教程,资源,问题">
                    </div>
                    <button type="submit" class="btn btn-primary">搜索</button>
                </form>
            </div>
        </div>
    </div>
</nav>