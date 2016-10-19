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
    <title>${user.nickname} - BeginCode</title>
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
<div class="container-fluid">
    <div class="row">
        <div class="col-md-3">
            <div class="jumbotron">
                <div class="container">
                    <div>
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h3 class="panel-title">个人信息</h3>
                            </div>
                            <div class="col-sm-4 col-md-3">
                                <a href="#" class="thumbnail">
                                    <img src="${user.pic}">
                                </a>
                            </div>
                            <h3 id="nickName">${user.nickname}</h3>
                            <h5><fmt:formatDate value="${user.cdate}" type="date" dateStyle="long"/>加入begincode </h5>
                            <div id="echart" style="width: 200px;height:300px;"></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-md-9">

            <!-- Nav tabs -->
            <ul class="nav nav-tabs" role="tablist" id="myTab">
                <li role="presentation" class="active"><a id="problem" href="#problem" aria-controls="problems" role="tab"
                                                          data-toggle="tab">他的提问${problemSize}</a></li>
                <li role="presentation"><a href="#answers" tabindex="-1" id="answer" aria-controls="profile"
                                           role="tab"
                                           data-toggle="tab">他的回答${answerSize}</a>
                </li>
                <li role="presentation"><a id="collection" href="#collections" aria-controls="messages"
                                           role="tab"
                                           data-toggle="tab">他的收藏${collectSize}</a></li>
            </ul>


            <div class="tab-content">
                <!-- 他的提问 -->
                <div role="tabpanel" class="tab-pane fade in active" id="problems">
                    <div id="hisProblems" class="container-fluid">
                    </div>
                </div>
                <!--他的回答-->
                <div role="tabpanel" class="tab-pane fade" class="tab-pane" id="answers">
                    <div id="hisAnswers" class="container-fluid">
                    </div>
                </div>
                <!--他的收藏-->
                <div role="tabpanel" class="tab-pane" id="collections">
                    <div id="hisCollections" class="container-fluid">
                    </div>
                </div>
            </div>

            <!--分页栏-->
            <nav style="text-align: center">
                <ul id="paginationId" class="pagination">
                </ul>
            </nav>
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
<!--未登录提问时调用-->
<!-- 模态框（Modal） -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                </button>
                <h4 class="modal-title" id="myModalLabel1">
                    警告
                </h4>
            </div>
            <div class="modal-body">
                ${msg}
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" onclick="history.back(-1);">关闭</button>
            </div>
        </div>
    </div>
</div>
<!--我的问题tab 未登录时调用-->
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
                <a type="button" class="btn btn-primary" href="/">关闭</a>
            </div>
        </div>
    </div>
</div>
<jsp:include page="/page/core/foot.jsp"/>


<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="${ctx}/js/bootstrap/bootstrap.js"></script>
<script src="${ctx}/js/messages/messageremind.js"></script>
<script src="${ctx}/js/commons/getProblems.js"></script>
<script src="${ctx}/js/commons/timeUtil.js"></script>
<script src="${ctx}/js/commons/jqpagination.js"></script>
<script src="${ctx}/js/echarts/echarts.js"></script>
<script src="${ctx}/js/echarts/createEcharts.js"></script>
<script src="${ctx}/js/problem/userProblem.js"></script>

<!-- 获取活跃用户 -->
<script type="text/javascript" src="${ctx}/js/getActivers.js"></script>
<!-- 获取热门标签 、查询标签-->
<script type="text/javascript" src="${ctx}/js/getLabel.js"></script>
</body>
</html>