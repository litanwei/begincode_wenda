<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="commons/taglibs.jsp" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <script src="${ctx}/js/jquery/jquery-3.1.0.min.js"></script>
    <script src="${ctx}/js/jquery/jqPaginator.min.js"></script>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="keywords" content="问答系统,BeginCode问答"/>
    <meta name="description" content="开源问答系统,BeginCode问答"/>
    <%@ include file="commons/meta.jsp" %>
    <title>BeginCode问答</title>
    <link href="${ctx}/css/bootstrap.css" rel="stylesheet">
    <link href="${ctx}/css/qu.css" rel="stylesheet">
    <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
</head>
<body>
<jsp:include page="/page/core/top.jsp"/>
<div class="container-fluid">
    <ul class="nav nav-tabs" role="tablist">
        <li role="presentation" class="active"><a href="#home"
                                                  aria-controls="home" role="tab"
                                                  data-toggle="tab">${label.labelName}</a></li>
    </ul>
    <div class="row">
        <div class="tab-content">
            <div class="col-md-9">
                <div role="tabpanel" class="tab-pane active" id="home">
                    <div class="container-fluid">
                        <c:forEach items="${proLabel}" var="pl" varStatus="varSta">
                            <section class="stream-list__item">
                                <div class="qa-rank">
                                    <div class="votes plus hidden-xs">
                                            ${pl.problem.voteCount}
                                        <small>投票</small>
                                    </div>
                                    <div class="answers answered">
                                            ${pl.problem.answerCount}
                                        <small>回答</small>
                                    </div>
                                    <div class="views hidden-xs">
                                            ${pl.problem.viewCount}
                                        <small>浏览</small>
                                    </div>
                                </div>
                                <div class="summary">
                                    <ul class="author list-inline ">
                                        <li><a href="${ctx}/user/${pl.problem.userName}.htm">${pl.problem.userName}</a> <span
                                                class="split"></span> <a style="text-decoration:none;"><fmt:formatDate
                                                value="${pl.problem.createTime}" pattern="yyyy-MM-dd"/>提问</a>
                                        </li>
                                    </ul>
                                    <span class="keyword-list ">
											<h2 class="title l">
												<a href="${ctx}/problem/${pl.problem.problemId}.htm">${pl.problem.title}</a>
											</h2> <c:forEach items="${pl.labell}" var="p" varStatus="varStau">
												<a href="${ctx}/label/selectProblemLabel.htm?id=${p.labelId}"
                                                   target="_blank" class="list-tag">${p.labelName}</a>
                                    </c:forEach>
										</span>
                                </div>

                            </section>
                        </c:forEach>
                    </div>
                </div>
            </div>
            <div class="col-md-3">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">热门标签</h3>
                    </div>
                    <div class="panel-body">
                        <span class="tag-list" id="labelBody"> </span>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="${ctx}/js/bootstrap/bootstrap.js"></script>
<script src="${ctx}/js/messages/messageremind.js"></script>
<script src="${ctx}/js/commons/timeUtil.js"></script>
<script type="text/javascript" src="${ctx}/js/index/onLoad.js"></script>
</body>
</html>
