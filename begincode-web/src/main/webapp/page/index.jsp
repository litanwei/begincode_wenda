<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="commons/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="../js/jquery/jquery-3.1.0.min.js"></script>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <%@ include file="commons/meta.jsp"%>
    <title>Bootstrap 101 Template</title>
    <!-- Bootstrap -->
    <link href="${ctx}/css/bootstrap.css" rel="stylesheet">
    <link href="${ctx}/css/qu.css" rel="stylesheet">
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>

<jsp:include page="/page/core/top.jsp" />
<div class="container-fluid">

    <div class="row">
        <div class="col-md-9">
            <!-- Nav tabs -->
            <ul class="nav nav-tabs" role="tablist">
                <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab"
                                                          data-toggle="tab">最新的</a></li>
                <li role="presentation"><a href="#profile" aria-controls="profile" role="tab" data-toggle="tab">热门的</a>
                </li>
                <li role="presentation"><a href="#messages" aria-controls="messages" role="tab"
                                           data-toggle="tab">未回答的</a></li>
                <li role="presentation"><a href="#messages" aria-controls="messages" role="tab"
                                           data-toggle="tab">我的问题</a></li>
                <li role="presentation"><a href="#messages" aria-controls="messages" role="tab"
                                           data-toggle="tab">@我的</a></li>
            </ul>


            <!-- Tab panes -->
            <div class="tab-content">
                <div role="tabpanel" class="tab-pane active" id="home">
                    <div class="container-fluid">
                        <section class="stream-list__item">
                            <div class="qa-rank">
                                <div class="votes hidden-xs">
                                    0
                                    <small>投票</small>
                                </div>
                                <div class="answers">
                                    0
                                    <small>回答</small>
                                </div>
                                <div class="views hidden-xs">
                                    13
                                    <small>浏览</small>
                                </div>
                            </div>
                            <div class="summary">
                                <ul class="author list-inline">
                                    <li>

                                        <span class="split"></span>
                                        <a href="/q/1010000006241941" class="askDate" data-created="1470910517">7
                                            分钟前提问</a>
                                    </li>
                                </ul>
			<span class="keyword-list ">
            <h2 class="title l"><a href="/q/1010000006241941">可以用代码去触发一个元素的css:active状态吗？</a></h2>
                      
										<a href="" target="_blank" class="list-tag">html</a><a href="" target="_blank"
                                                                                               class="list-tag">css</a>
								</span>
                            </div>
                        </section>


                        <section class="stream-list__item">
                            <div class="qa-rank">
                                <div class="votes plus hidden-xs">
                                    1
                                    <small>投票</small>
                                </div>
                                <div class="answers answered">
                                    4
                                    <small>回答</small>
                                </div>
                                <div class="views hidden-xs">
                                    55
                                    <small>浏览</small>
                                </div>
                            </div>
                            <div class="summary">
                                <ul class="author list-inline ">
                                    <li>
                                        <a href="/u/guaixiaoguiyq">乖小鬼YQ</a>
                                        <span class="split"></span>
                                        <a href="/q/1010000006240373/a-1020000006241989">6 分钟前回答</a>
                                    </li>
                                </ul>
            
                        <span class="keyword-list ">
						<h2 class="title l"><a href="/q/1010000006240373">在DOM树种，给出任意2个node，如何找出两者之间所有的node？在DOM树种，给出任意2个node，如何找出两者之间所有的node？
                            在DOM树种，给出任意2个node，如何找出两者之间所有的node？
                            在DOM树种，给出任意2个node，如何找出两者之间所有的node？</a></h2>
						
										<a href="" target="_blank" class="list-tag">html</a><a href="" target="_blank"
                                                                                               class="list-tag">css</a>
										<a href="" target="_blank" class="list-tag">html</a><a href="" target="_blank"
                                                                                               class="list-tag">css</a>
										<a href="" target="_blank" class="list-tag">html</a><a href="" target="_blank"
                                                                                               class="list-tag">css</a>
										<a href="" target="_blank" class="list-tag">html</a><a href="" target="_blank"
                                                                                               class="list-tag">css</a>
								</span>
                            </div>
                        </section>


                    </div>
                </div>

                <div role="tabpanel" class="tab-pane" id="profile">
                    <div class="container-fluid">
                        <section class="stream-list__item">
                            <div class="qa-rank">
                                <div class="votes hidden-xs">
                                    0
                                    <small>投票</small>
                                </div>
                                <div class="answers">
                                    0
                                    <small>回答</small>
                                </div>
                                <div class="views hidden-xs">
                                    13
                                    <small>浏览</small>
                                </div>
                            </div>
                            <div class="summary">
                                <ul class="author list-inline">
                                    <li>

                                        <span class="split"></span>
                                        <a href="/q/1010000006241941" class="askDate" data-created="1470910517">7
                                            分钟前提问</a>
                                    </li>
                                </ul>
			<span class="keyword-list ">
            <h2 class="title l"><a href="/q/1010000006241941">可以用代码去触发一个元素的css:active状态吗？</a></h2>
                      
										<a href="" target="_blank" class="list-tag">html</a><a href="" target="_blank"
                                                                                               class="list-tag">css</a>
								</span>
                            </div>
                        </section>


                        <section class="stream-list__item">
                            <div class="qa-rank">
                                <div class="votes plus hidden-xs">
                                    23
                                    <small>投票</small>
                                </div>
                                <div class="answers answered">
                                    4
                                    <small>回答</small>
                                </div>
                                <div class="views hidden-xs">
                                    55
                                    <small>浏览</small>
                                </div>
                            </div>
                            <div class="summary">
                                <ul class="author list-inline ">
                                    <li>
                                        <a href="/u/guaixiaoguiyq">乖小鬼YQ</a>
                                        <span class="split"></span>
                                        <a href="/q/1010000006240373/a-1020000006241989">6 分钟前回答</a>
                                    </li>
                                </ul>
            
                        <span class="keyword-list ">
						<h2 class="title l"><a href="/q/1010000006240373">在DOM树种，给出任意2个node，如何找出两者之间所有的node？在DOM树种，给出任意2个node，如何找出两者之间所有的node？
                            在DOM树种，给出任意2个node，如何找出两者之间所有的node？
                            在DOM树种，给出任意2个node，如何找出两者之间所有的node？</a></h2>
						
										<a href="" target="_blank" class="list-tag">html</a><a href="" target="_blank"
                                                                                               class="list-tag">css</a>
										<a href="" target="_blank" class="list-tag">html</a><a href="" target="_blank"
                                                                                               class="list-tag">css</a>
										<a href="" target="_blank" class="list-tag">html</a><a href="" target="_blank"
                                                                                               class="list-tag">css</a>
										<a href="" target="_blank" class="list-tag">html</a><a href="" target="_blank"
                                                                                               class="list-tag">css</a>
								</span>
                            </div>
                        </section>


                    </div>
                </div>
                <div role="tabpanel" class="tab-pane" id="messages">
                    <div class="container-fluid">
                        <section class="stream-list__item">
                            <div class="qa-rank">
                                <div class="votes hidden-xs">
                                    10
                                    <small>投票</small>
                                </div>
                                <div class="answers">
                                    10
                                    <small>回答</small>
                                </div>
                                <div class="views hidden-xs">
                                    13
                                    <small>浏览</small>
                                </div>
                            </div>
                            <div class="summary">
                                <ul class="author list-inline">
                                    <li>

                                        <span class="split"></span>
                                        <a href="/q/1010000006241941" class="askDate" data-created="1470910517">7
                                            分钟前提问</a>
                                    </li>
                                </ul>
			<span class="keyword-list ">
            <h2 class="title l"><a href="/q/1010000006241941">可以用代码去触发一个元素的css:active状态吗？</a></h2>
                      
										<a href="" target="_blank" class="list-tag">html</a><a href="" target="_blank"
                                                                                               class="list-tag">css</a>
								</span>
                            </div>
                        </section>


                        <section class="stream-list__item">
                            <div class="qa-rank">
                                <div class="votes plus hidden-xs">
                                    12
                                    <small>投票</small>
                                </div>
                                <div class="answers answered">
                                    4
                                    <small>回答</small>
                                </div>
                                <div class="views hidden-xs">
                                    5
                                    <small>浏览</small>
                                </div>
                            </div>
                            <div class="summary">
                                <ul class="author list-inline ">
                                    <li>
                                        <a href="/u/guaixiaoguiyq">乖小鬼YQ</a>
                                        <span class="split"></span>
                                        <a href="/q/1010000006240373/a-1020000006241989">6 分钟前回答</a>
                                    </li>
                                </ul>
            
                        <span class="keyword-list ">
						<h2 class="title l"><a href="/q/1010000006240373">在DOM树种，给出任意2个node，如何找出两者之间所有的node？在DOM树种，给出任意2个node，如何找出两者之间所有的node？
                            在DOM树种，给出任意2个node，如何找出两者之间所有的node？
                            在DOM树种，给出任意2个node，如何找出两者之间所有的node？</a></h2>
						
										<a href="" target="_blank" class="list-tag">html</a><a href="" target="_blank"
                                                                                               class="list-tag">css</a>
										<a href="" target="_blank" class="list-tag">html</a><a href="" target="_blank"
                                                                                               class="list-tag">css</a>
										<a href="" target="_blank" class="list-tag">html</a><a href="" target="_blank"
                                                                                               class="list-tag">css</a>
										<a href="" target="_blank" class="list-tag">html</a><a href="" target="_blank"
                                                                                               class="list-tag">css</a>
								</span>
                            </div>
                        </section>

                    </div>
                </div>

            </div>

            <nav style="text-align: center">
                <ul class="pagination">
                    <li class="disabled"><a href="#" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a>
                    </li>
                    <li class="active"><a href="#">1 <span class="sr-only">(current)</span></a></li>
                    <li><a href="#">1 <span class="sr-only">(current)</span></a></li>
                    <li><a href="#">1 <span class="sr-only">(current)</span></a></li>
                    <li><a href="#">1 <span class="sr-only">(current)</span></a></li>
                    <li><a href="#">1 <span class="sr-only">(current)</span></a></li>
                    <li><a href="#">1 <span class="sr-only">(current)</span></a></li>
                </ul>
            </nav>


        </div>

        <div class="col-md-3">
            <p>
                <a href="${ctx}/problem/create.htm" class="btn btn-primary btn-lg btn-block">我要提问</a>
            </p>
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">热门标签</h3>
                </div>
                <div class="panel-body">
    <span class="tag-list">
        <a href="" target="_blank" class="list-tag">java</a><a href="" target="_blank" class="list-tag">基础类型</a>
		<a href="" target="_blank" class="list-tag">接口</a><a href="" target="_blank" class="list-tag">面向对象</a>
		<a href="" target="_blank" class="list-tag">框架</a><a href="" target="_blank" class="list-tag">SSM</a>
	</span>
                </div>
            </div>

            <div class="list-group">
                <a href="#" class="list-group-item disabled">
                    最活跃用户
                </a>
                <c:forEach items="${list }" var="user">
                <a href="#" class="list-group-item" onclick="${user.begincodeUserId}">${user.nickname }</a>
                
				</c:forEach>
            </div>
        </div>

    </div>

</div>

<c:if test="${!empty msg}" var="condition" scope="request">
    <script type="text/javascript">
        $(document).ready(function(){
            $("#myModal").modal("show");
        });
    </script>
</c:if>
<!-- 模态框（Modal） -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                </button>
                <h4 class="modal-title" id="myModalLabel">
                    警告
                </h4>
            </div>
            <div class="modal-body">
                ${msg}
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" onclick="javascript:history.back(-1);">关闭</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>


<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="../js/bootstrap/bootstrap.js"></script>
<script src="../js/message/messageremind.js"></script>
</body>
</html>