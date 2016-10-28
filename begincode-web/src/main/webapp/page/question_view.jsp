<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="commons/taglibs.jsp" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <%@ include file="commons/meta.jsp" %>
    <title>${problem.title} BeginCode问答</title>

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
<jsp:include page="/page/core/top.jsp"/>

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
                            <c:forEach items="${labels}" var="label">
                                <a href="" target="_blank" class="list-tag">${label.labelName}</a>
                            </c:forEach>
                        </span>
                        <span id="problemId" extra="${problem.problemId}"  class="hidden"></span>

                        <div class="question-author">
                            <a href="#" class="mr5" id="problemUser"><strong>${problem.userName}</strong></a>
                            ${problemTime}提问 </span>
                        </div>
                    </div>
                </div>
                <div class="col-md-3 col-sm-4 col-xs-12 hidden-xs">
                    <ul class="post-topheader__side list-unstyled">
                        <li>
                            <c:choose>
                                <c:when test="${proAttention.collect == '1'}">
                                    <button type="button" id="collection" class="btn btn-danger btn-sm"
                                            data-id="1010000006602336" data-do="follow" data-type="question"
                                            data-toggle="tooltip" data-placement="right" title="收藏后更新将会提醒">已收藏
                                    </button>
                                    <strong id="collectionNumber">${problem.collectCount}</strong> 收藏
                                </c:when>
                                <c:when test="${proAttention.collect == '0'}">
                                    <button type="button" id="collection" class="btn btn-danger btn-sm"
                                            data-id="1010000006602336" data-do="follow" data-type="question"
                                            data-toggle="tooltip" data-placement="right" title="收藏后更新将会提醒">收藏
                                    </button>
                                    <strong id="collectionNumber">${problem.collectCount}</strong> 收藏
                                </c:when>
                                <c:otherwise>
                                    <button type="button" id="collection" class="btn btn-danger btn-sm"
                                            data-id="1010000006602336" data-do="follow" data-type="question"
                                            data-toggle="tooltip" data-placement="right" title="收藏后更新将会提醒">收藏
                                    </button>
                                    <strong id="collectionNumber">${problem.collectCount}</strong> 收藏
                                </c:otherwise>
                            </c:choose>
                        </li>
                        <li>
                            <c:choose>
                                <c:when test="${proAttention.vote == '0'}">
                                    <button type="button" id="vote" class="btn btn-default btn-sm"
                                            data-id="1010000006602336" data-type="question">投票
                                    </button>
                                    <strong id="voteNumber">${problem.voteCount}</strong> 投票，
                                </c:when>
                                <c:when test="${proAttention.vote == '1'}">
                                    <button type="button" id="vote" class="btn btn-default btn-sm"
                                            data-id="1010000006602336" data-type="question">已投票
                                    </button>
                                    <strong id="voteNumber">${problem.voteCount}</strong> 投票，
                                </c:when>
                                <c:otherwise>
                                    <button type="button" id="vote" class="btn btn-default btn-sm"
                                            data-id="1010000006602336" data-type="question">投票
                                    </button>
                                    <strong id="voteNumber">${problem.voteCount}</strong> 投票，
                                </c:otherwise>
                            </c:choose>
                            <strong id="viewNumber"
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
            ${problem.content}

            <center>
                <button type="button" class="btn btn-primary">点击投票(${problem.voteCount})</button>
            </center>
            <div id="answerAdopt"></div>

            <c:forEach items="${answerAdoptList}" var="answer" varStatus="temp">

                <div class="post-offset">
                    <hr>
                    <div class="answer fmt" data-id="1020000006591589">
                        <p> ${answer.content}</p>
                    </div>
                    <div class="row answer__info--row">
                        <div class="post-opt col-md-8 col-sm-8 col-xs-10">
                            <ul class="list-inline mb0">
                                <li>
                                    <td>${newAdoptTime[temp.count-1]}</td>
                                    <span class="text-muted"></span>
                                </li>
                                <li data-toggle="tooltip" data-placement="top" title="" class="edit-btn js__rank-check">
                                    <a href="javascript:void(0);" onclick="sendFeedback(${answer.answerId})">举报</a>
                                </li>


                                <c:choose>
                                    <c:when test="${answer.adopt == 1}">
                                        <li><span class="label label-danger">已采纳</span>
                                        </li>
                                    </c:when>
                                    <c:otherwise>
                                        <li data-toggle="tooltip" data-placement="top" title=""
                                            class="edit-btn js__rank-check">
                                            <a href="javascript:void(0);" onclick="sendAdoptAnswer(${answer.answerId})">采纳</a>
                                        </li>
                                    </c:otherwise>
                                </c:choose>
                                    <%--<li class="dropdown">--%>
                                    <%--<a href="javascript:void(0);" class="dropdown-toggle" data-toggle="dropdown">更多<b--%>
                                    <%--class="caret"></b></a>--%>
                                    <%--</li>--%>
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

            <div id="answerUpdate"></div>
            <c:forEach items="${answerNoAdoptList}" var="answer" varStatus="temp">

                <div class="post-offset" id="answer${answer.answerId}">
                    <hr>
                    <div class="answer fmt" data-id="1020000006591589">
                        <p> ${answer.content}</p>
                    </div>
                    <div class="row answer__info--row">
                        <div class="post-opt col-md-8 col-sm-8 col-xs-10">
                            <ul class="list-inline mb0">
                                <li>
                                    <td>${newNoAdoptTime[temp.count-1]}</td>
                                    <span class="text-muted"></span>
                                </li>
                                <li data-toggle="tooltip" data-placement="top" title="" class="edit-btn js__rank-check">
                                    <a href="javascript:void(0);" onclick="sendFeedback(${answer.answerId})">举报</a>
                                </li>


                                <c:choose>
                                    <c:when test="${answer.adopt == 1}">
                                        <li><span class="label label-danger">已采纳</span>
                                        </li>
                                    </c:when>
                                    <c:otherwise>
                                        <li data-toggle="tooltip" data-placement="top" title=""
                                            class="edit-btn js__rank-check">
                                            <a href="javascript:void(0);" onclick="sendAdoptAnswer(${answer.answerId})">采纳</a>
                                        </li>
                                    </c:otherwise>
                                </c:choose>
                                    <%--<li class="dropdown">--%>
                                    <%--<a href="javascript:void(0);" class="dropdown-toggle" data-toggle="dropdown">更多<b--%>
                                    <%--class="caret"></b></a>--%>
                                    <%--</li>--%>
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


            <form id="answerForm" method="post">
                <p>
                <hr>

                <div id="summernote"></div>
                <input type="hidden" name="answer.problemId" id="problemId" value="${problem.problemId}"/>
                <input type="hidden" name="answer.content" id="content" value=""/>
                </p>
                <p>
                    <button type="button" class="btn btn-success" id="answerSend">提交答案</button>
                </p>
            </form>
        </div>
        <div class="col-md-3">

            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">热门标签</h3>
                </div>
                <div class="panel-body">
				    <span class="tag-list" id="labelBody">

					</span>
                </div>
            </div>
            <%--<div class="list-group">--%>
            <%--<a href="#" class="list-group-item disabled">--%>
            <%--相似问题--%>
            <%--</a>--%>
            <%--<a href="#" class="list-group-item">问题111</a>--%>
            <%--<a href="#" class="list-group-item">问题222</a>--%>
            <%--</div>--%>
        </div>
    </div>
</div>
<input type="hidden" value="${problem.problemId}" id="problem_id"/>
<!--提问后提示弹出框-->
<div class="modal fade" id="ajaxModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                </button>
                <h4 class="modal-title" id="myModalLabel">
                    提示
                </h4>
            </div>
            <div class="modal-body">
                <div id="errorMessage"></div>
            </div>
            <div class="modal-footer">
                 <a href="#" class="btn btn-success" data-dismiss="modal">关闭</a>
            </div>
        </div>
    </div>
</div>
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="${ctx}/js/jquery/jquery-3.1.0.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="${ctx}/js/bootstrap/bootstrap.js"></script>
<link href="${ctx}/summernote/summernote.css" rel="stylesheet">
<script src="${ctx}/summernote/summernote.js"></script>
<script src="${ctx}/js/answer/answer.js"></script>
<script src="${ctx}/js/summernotePlugin.js"></script>
<script src="${ctx}/js/commons/timeUtil.js"></script>
<script src="${ctx}/js/problem/problemDetail.js"></script>
<script type="text/javascript" src="${ctx}/js/getLabel.js"></script>
</body>
</html>