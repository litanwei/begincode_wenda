<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="commons/taglibs.jsp" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="${ctx}/js/jquery/jquery-3.1.0.min.js"></script>
    <script src="${ctx}/js/jquery/jqPaginator.min.js"></script>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <%@ include file="commons/meta.jsp" %>
    <title>BeginCode问答</title>
    <!-- Bootstrap -->
    <link href="${ctx}/css/bootstrap.css" rel="stylesheet">
    <link href="${ctx}/css/qu.css" rel="stylesheet">
    <%--<link href="${ctx}/css/pagination.css" rel="stylesheet">--%>
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>

<jsp:include page="/page/core/top.jsp"/>
<ul class="nav nav-tabs" role="tablist">
    <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab"
                                              data-toggle="tab">${label}</a></li>
</ul>
<div>
    <div class="tab-content">
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
                    <li>
                        <a href="/u/guaixiaoguiyq"><c:if test="${pl.}"></c:if></a>
                        <span class="split"></span>
                        <a href="/q/1010000006240373/a-1020000006241989">${pl.problem.updateTime}前回答</a>
                    </li>
                </ul>

                <span class="keyword-list ">
						<h2 class="title l"><a href="/q/1010000006240373">${pl.problem.title}</a></h2>

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
    </c:forEach>
            </div>
        </div>
    <div class="row">
        <div class="col-md-9">

            <!--分页栏-->
            <nav style="text-align: center">
                <ul id="paginationId" class="pagination">
                </ul>
            </nav>
        </div>


    </div>

</div>


<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="${ctx}/js/bootstrap/bootstrap.js"></script>
<script src="${ctx}/js/problem/getProblems.js"></script>
<script src="${ctx}/js/commons/timeUtil.js"></script>
<script src="${ctx}/js/commons/jqpagination.js"></script>
<script src="${ctx}/js/problem/problem.js"></script>


</body>
</html>







