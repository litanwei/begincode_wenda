<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="commons/taglibs.jsp" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="keywords" content="${problem.title}"/>
    <meta name="description" content="${problem.title},开源问答系统,BeginCode问答"/>
    <%@ include file="commons/meta.jsp" %>
    <title>${problem.title},BeginCode问答</title>
    <link href="${ctx}/css/bootstrap.css" rel="stylesheet">
    <link href="${ctx}/css/qu.css" rel="stylesheet">
    <link href="${ctx}/css/answer.css" rel="stylesheet">
    <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
</head>

<body>
<jsp:include page="${ctx}/page/core/top.jsp"/>

<div class="continer">
    <div class="post-topheader">
        <div class="container">
            <div class="row">
                <div class="col-md-9 col-sm-8 col-xs-12">

                    <div class="post-topheader__info">
                        <h1 class="h3 post-topheader__info--title" id="questionTitle" data-id="1010000006602336">
                            <a>${problem.title}</a>
                        </h1>

                        <span class="keyword-list ">
                            <c:forEach items="${labels}" var="label">
                                <a href="" target="_blank" class="list-tag">${label.labelName}</a>
                            </c:forEach>
                        </span>
                        <div class="question-author">
                            <a href="${ctx}/user/${problem.userName}.htm" class="mr5" id="problemUser"><strong>${problem.userName}</strong></a>
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
            <div class="info-mod alert-info alert">     ${problem.content}
            </div>
            <form id="answerForm" method="post">
                <p>
                <hr>
                <div onclick="updataSummernote()">
                    <div id="summernote" class="click2edit"></div>
                </div>
                <input type="hidden" name="answer.problemId" id="problemId" value="${problem.problemId}"/>
                <input type="hidden" name="answer.content" id="content" value=""/>
                </p>
                <div class="container-fluid align-center">
                    <button type="button" class="btn btn-primary" id="answerSend" >发布回答</button>
                </div>
            </form>

            <div id="answerAdopt"></div>
            <c:forEach items="${answerAdoptList}" var="answer" varStatus="temp">
                <article class="widget-question__item">
                    <hr>
                    <input type="hidden" value="${answer.answerId}"/>
                    <div class="votebar">
                        <c:choose>
                            <c:when test="${answerAdoptAgreeFlag[temp.count-1] == 1}">
                                <button id="click-like" class="click-like up pressed" aria-pressed="true" title="取消赞同">
                                    <i class="vote-arrow"></i>
                                    <span class="count">${answer.agreeCount}</span>
                                </button>
                                <button id="click-dislike" class="click-dislike down" aria-pressed="false" title="反对">
                                    <i class="vote-arrow"></i>
                                </button>
                            </c:when>
                            <c:when test="${answerAdoptAgreeFlag[temp.count-1] == 2}">
                                <button id="click-like" class="click-like up" aria-pressed="false" title="赞同">
                                    <i class="vote-arrow"></i>
                                    <span class="count">${answer.agreeCount}</span>
                                </button>
                                <button id="click-dislike" class="click-dislike down  pressed" aria-pressed="true" title="取消反对">
                                    <i class="vote-arrow"></i>
                                </button>
                            </c:when>
                            <c:otherwise>
                                <button id="click-like" class="click-like up" aria-pressed="false" title="赞同">
                                    <i class="vote-arrow"></i>
                                    <span class="count">${answer.agreeCount}</span>
                                </button>
                                <button id="click-dislike" class="click-dislike down" aria-pressed="false" title="反对">
                                    <i class="vote-arrow"></i>
                                </button>
                            </c:otherwise>
                        </c:choose>
                        <c:choose>
                            <c:when test="${answer.adopt == 1}">
                                <a class="rcmd-label">采纳</a>
                            </c:when>
                        </c:choose>
                        <c:choose>
                            <c:when test="${(answer.agreeCount>(answer.oppositionCount*10))&&answer.oppositionCount>0}">
                                <a class="rcmd-label">推荐</a>
                            </c:when>
                        </c:choose>

                    </div>
                    <div class="post-offset">

                        <div class="answer-fmt" data-id="1010000007316290" >
                            <p> ${answer.content}</p>
                        </div>
                        <div class="row">
                            <div class="post-opt col-md-8">
                                <ul class="list-inline mb0">
                                    <li><a href="javascript:;">${newAdoptTime[temp.count-1]}</a></li>
                                    <li class="edit-btn js__rank-check" data-toggle="tooltip"  data-placement="top" >
                                        <a href="javascript:;" onclick="sendFeedback(${answer.answerId})">举报</a>
                                    </li>

                                    <li class="dropdown js__content-ops" data-module="question" data-typetext="问题"
                                        data-id="1010000007316290">
                                        <a href="javascript:void(0);" class="dropdown-toggle"
                                           data-toggle="dropdown">更多<b class="caret"></b></a>
                                        <ul class="dropdown-menu dropdown-menu-left">
                                            <li><a href="javascript:void(0);"
                                                   data-toggle="modal"
                                                   data-target="#911"
                                                   data-action="close">关闭</a></li>
                                        </ul>
                                    </li>
                                </ul>
                            </div>
                            <div class="col-md-2 col-sm-2 col-xs-2 answer__info--author-avatar">
                            </div>
                            <div class="col-md-2 col-sm-2 hidden-xs answer__info--author">
                                <div class=" answer__info--author-warp">
                                    <a  title="${answer.userName}"
                                        href="${ctx}/user/${answer.begincodeUserId}.htm">${answer.userName}</a><span
                                        class="answer__info--author-rank"></span></div>
                            </div>
                        </div>
                    </div>
                </article>
            </c:forEach>

            <div id="answerUpdate"></div>
            <c:forEach items="${answerNoAdoptList}" var="answer" varStatus="temp">
                <article class="widget-question__item" id="answer${answer.answerId}">
                    <hr>
                    <input type="hidden" value="${answer.answerId}"/>
                    <div class="votebar">
                        <c:choose>
                            <c:when test="${answerNoAdoptAgreeFlag[temp.count-1] == 1}">
                                <button id="click-like" class="click-like up pressed" aria-pressed="true" title="取消赞同">
                                    <i class="vote-arrow"></i>
                                    <span class="count">${answer.agreeCount}</span>
                                </button>
                                <button id="click-dislike" class="click-dislike down" aria-pressed="false" title="反对">
                                    <i class="vote-arrow"></i>
                                </button>
                            </c:when>
                            <c:when test="${answerNoAdoptAgreeFlag[temp.count-1] == 2}">
                                <button id="click-like" class="click-like up" aria-pressed="false" title="赞同">
                                    <i class="vote-arrow"></i>
                                    <span class="count">${answer.agreeCount}</span>
                                </button>
                                <button id="click-dislike" class="click-dislike down  pressed" aria-pressed="true" title="取消反对">
                                    <i class="vote-arrow"></i>
                                </button>
                            </c:when>
                            <c:otherwise>
                                <button id="click-like" class="click-like up" aria-pressed="false" title="赞同">
                                    <i class="vote-arrow"></i>
                                    <span class="count">${answer.agreeCount}</span>
                                </button>
                                <button id="click-dislike" class="click-dislike down" aria-pressed="false" title="反对">
                                    <i class="vote-arrow"></i>
                                </button>
                            </c:otherwise>
                        </c:choose>
                    </div>
                    <div class="post-offset">
                        <div class="answer-fmt" data-id="1010000007316290" >
                            <p> ${answer.content}</p>
                        </div>
                        <div class="row">
                            <div class="post-opt col-md-8">
                                <ul class="list-inline mb0">
                                    <li><a href="javascript:;">${newNoAdoptTime[temp.count-1]}</a></li>
                                    <li class="edit-btn js__rank-check" data-toggle="tooltip"  data-placement="top" >
                                        <a href="javascript:;" onclick="sendAdoptAnswer(${answer.answerId})">采纳</a>
                                    </li>
                                    <li class="edit-btn js__rank-check" data-toggle="tooltip"  data-placement="top" >
                                        <a href="javascript:;" onclick="sendFeedback(${answer.answerId})">举报</a>
                                    </li>

                                    <li class="dropdown js__content-ops" data-module="question" data-typetext="问题"
                                        data-id="1010000007316290">
                                        <a href="javascript:void(0);" class="dropdown-toggle"
                                           data-toggle="dropdown">更多<b class="caret"></b></a>
                                        <ul class="dropdown-menu dropdown-menu-left">
                                            <li><a href="javascript:void(0);"
                                                   data-toggle="modal"
                                                   data-target="#911"
                                                   data-action="close">关闭</a></li>
                                        </ul>
                                    </li>
                                </ul>
                            </div>
                            <div class="col-md-2 col-sm-2 col-xs-2 answer__info--author-avatar">
                            </div>
                            <div class="col-md-2 col-sm-2 hidden-xs answer__info--author">
                                <div class=" answer__info--author-warp">
                                    <a title="${answer.userName}"
                                       href="${ctx}/user/${answer.begincodeUserId}.htm">${answer.userName}</a><span
                                        class="answer__info--author-rank"></span></div>
                            </div>
                        </div>
                    </div>
                </article>
            </c:forEach>
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
        </div>
    </div>
</div>
<input type="hidden" value="${problem.problemId}" id="problem_id"/>
<jsp:include page="${ctx}/page/core/modal.jsp"/>
<jsp:include page="${ctx}/page/core/foot.jsp"/>
<script src="${ctx}/js/jquery/jquery.js"></script>
<script src="${ctx}/js/bootstrap/bootstrap.js"></script>
<link href="${ctx}/summernote/summernote.css" rel="stylesheet">
<script src="${ctx}/summernote/summernote.js"></script>
<script src="${ctx}/summernote/lang/summernote-zh-CN.min.js"></script>
<script src="${ctx}/js/summernotePlugin.js"></script>
<script src="${ctx}/js/summernoteAnswer.js"></script>
<script src="${ctx}/js/commons/timeUtil.js"></script>
<script src="${ctx}/js/problem/problemDetail.js"></script>
</body>
</html>