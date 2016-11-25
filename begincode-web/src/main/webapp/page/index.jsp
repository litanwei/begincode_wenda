<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="commons/taglibs.jsp" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <script src="${ctx}/js/jquery/jquery-3.1.0.min.js"></script>
    <script src="${ctx}/js/jquery/jqPaginator.min.js"></script>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
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
    <div class="row">
        <div class="col-md-9">
            <ul class="nav nav-tabs" role="tablist" id="myTab">
                <li role="presentation" class="active"><a id="newProblemId" href="#home" aria-controls="home" role="tab"
                                                          data-toggle="tab">最新的</a></li>
                <li role="presentation"><a href="#hotProblems" tabindex="-1" id="hotProblemId" aria-controls="profile"
                                           role="tab"
                                           data-toggle="tab">热门的</a>
                </li>
                <li role="presentation"><a id="noAnswerProblemId" href="#noAnswerProblems" aria-controls="messages"
                                           role="tab"
                                           data-toggle="tab">未回答的</a></li>
                <li role="presentation" id="myProblemId"></li>
                <li role="presentation" id="messageId"></li>
            </ul>
            <div class="tab-content">
                <div role="tabpanel" class="tab-pane fade in active" id="home">
                    <div id="newProblem" class="container-fluid">
                    </div>
                </div>
                <div role="tabpanel" class="tab-pane fade" class="tab-pane" id="hotProblems">
                    <div id="hotProblem" class="container-fluid">
                    </div>
                </div>
                <div role="tabpanel" class="tab-pane" id="noAnswerProblems">
                    <div id="noAnswerProblem" class="container-fluid">
                    </div>
                </div>
                <div role="tabpanel" class="tab-pane" id="myProblems">
                    <div id="myProblem" class="container-fluid">
                    </div>
                </div>
                <div role="tabpanel" class="tab-pane" id="messages">
                    <div id="message" class="container-fluid">
                    </div>
                </div>
            </div>
            <nav style="text-align: center">
                <ul id="paginationId" class="pagination">
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
				    <span class="tag-list" id="labelBody">

					</span>
                </div>
            </div>
            <div class="list-group">
                <a class='list-group-item disabled'>最活跃用户</a>
            </div>
        </div>
    </div>
</div>
<c:if test="${!empty msg}" var="condition" scope="request">
    <script type="text/javascript">
        $(document).ready(function () {
            $("#myModal").modal("show");
        });
    </script>
</c:if>
<jsp:include page="/page/core/modal.jsp"/>
<jsp:include page="/page/core/foot.jsp"/>
<script src="${ctx}/js/bootstrap/bootstrap.js"></script>
<script src="${ctx}/js/commons/timeUtil.js"></script>
<script src="${ctx}/js/commons/jqpagination.js"></script>
<script src="${ctx}/js/problem/getProblems.js"></script>
<script src="${ctx}/js/problem/problem.js"></script>
<script src="${ctx}/js/index/onLoad.js"></script>
</body>
</html>