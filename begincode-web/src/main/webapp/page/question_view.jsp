<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="commons/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
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


<div class="continer">
    <div class="post-topheader">
        <div class="container">
            <div class="row">
                <div class="col-md-9 col-sm-8 col-xs-12">

                    <div class="post-topheader__info">
                        <h1 class="h3 post-topheader__info--title" id="questionTitle" data-id="1010000006602336">
                            <a href="/q/1010000006602336">${problem.title}</a>
                        </h1>

                        <span class="keyword-list "> 
										<a href="" target="_blank" class="list-tag">html</a><a href="" target="_blank" class="list-tag">css</a>
								</span>
                        <span id="problemId" extra="${problem.problemId}" class="hidden"></span>
                        <div class="question-author">
                            <a href="#" class="mr5" id="problemUser"><strong>${problem.userName}</strong></a>
                            ${problemTime}提问 </span>
                        </div>
                    </div>
                </div>
                <div class="col-md-3 col-sm-4 col-xs-12 hidden-xs">
                    <ul class="post-topheader__side list-unstyled">
                        <li>
                            <button type="button" id="sideFollow" class="btn btn-danger btn-sm"
                                    data-id="1010000006602336" data-do="follow" data-type="question"
                                    data-toggle="tooltip" data-placement="right" title="关注后将获得更新提醒">关注
                            </button>
                            <strong>1</strong> 关注
                        </li>
                        <li>
                            <button type="button" id="sideBookmark" class="btn btn-default btn-sm"
                                    data-id="1010000006602336" data-type="question">收藏
                            </button>
                            <strong id="sideBookmarked">${problem.collectCount}</strong> 收藏，<strong
                                class="no-stress">${problem.viewCount}</strong> 浏览
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="container">



    <div class="row">
        <div class="col-md-9">

            <p class="lead">正文显示</p>
            <blockquote>
                ${problem.content}
            </blockquote>

            <h6>h6. Bootstrap heading <small>Secondary text</small></h6>
            <pre><code class="language-html" data-lang="html">You can use the mark tag to <span class="nt">&lt;mark&gt;</span>highlight<span class="nt">&lt;/mark&gt;</span> text.</code></pre>

            <center>
                <button type="button" class="btn btn-primary">点击投票(12)</button>
            </center>


            <div></div>
            <c:forEach items="${answerList}" var="answer" varStatus="temp">
                <hr>
                <div class="post-offset">
                    <c:if test="${answer.adopt == 1}">
                        <div class="qa-rank">
                            <div class="answers">
                                <small>已采纳</small>
                            </div>
                        </div>
                    </c:if>
                    <div class="answer fmt" data-id="1020000006591589" >
                        <p> ${answer.content}</p>
                    </div>
                    <div class="row answer__info--row">
                        <div class="post-opt col-md-8 col-sm-8 col-xs-10">
                            <ul class="list-inline mb0">
                                <li><a href="#"><td>${newTime[temp.count-1]}</td></a>
                                    <span class="text-muted"></span></li>
                                <li data-toggle="tooltip" data-placement="top" title="" class="edit-btn js__rank-check">
                                    <a href="javascript:void(0);" onclick="sendFeedback(${answer.answerId})">反馈</a>
                                </li>
                                <li><a href="javascript:void(0);" class="comments" data-id="1020000006591589"
                                       data-target="#comment-1020000006591589">
                                    评论</a></li>
                                <li class="dropdown">
                                    <a href="javascript:void(0);" class="dropdown-toggle" data-toggle="dropdown">更多<b
                                            class="caret"></b></a>
                                </li>
                            </ul>
                        </div>
                        <div class="col-md-2 col-sm-2 col-xs-2 answer__info--author-avatar">
                        </div>
                        <div class="col-md-2 col-sm-2 hidden-xs answer__info--author">
                            <div class=" answer__info--author-warp">
                                <a class="answer__info--author-name" title="${answer.userName}"
                                   href="/u/fishenal">${answer.userName}</a><span
                                    class="answer__info--author-rank"></span></div>
                        </div>
                    </div>
                </div>
            </c:forEach>


            <p>
            <hr>
            <div id="summernote"></div>
            </p>
            <p>
                <button type="button" class="btn btn-success" id="answerSend">提交答案</button>
            </p>
        </div>
        <div class="col-md-3">

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
                    相似问题
                </a>
                <a href="#" class="list-group-item">问题111</a>
                <a href="#" class="list-group-item">问题222</a>
            </div>
        </div>
    </div>
</div>
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="${ctx}/js/jquery-3.1.0.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="${ctx}/js/bootstrap.js"></script>

<link href="${ctx}/summernote/summernote.css" rel="stylesheet">
<script src="${ctx}/summernote/summernote.js"></script>
<script src="${ctx}/js/answer/answer.js"></script>

</body>
</html>