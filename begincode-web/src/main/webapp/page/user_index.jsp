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
    <title>${user.nickname} - BeginCode</title>
    <link href="${ctx}/css/bootstrap.css" rel="stylesheet">
    <link href="${ctx}/css/qu.css" rel="stylesheet">
    <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
</head>
<body>
<jsp:include page="/page/core/top.jsp"/>
<div class="container-fluid">
    <div class="row clearfix">
        <div class="col-md-2 column">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">
                        个人信息
                    </h3>
                </div>
                <div class="panel-body">
                    <div class="col-sm-6 col-md-5">
                        <a href="${user.pic}" class="thumbnail">
                            <img src="${user.pic}">
                        </a>
                    </div>
                    <h4>${user.nickname}</h4>
                    <h6><fmt:formatDate value="${user.cdate}" type="date" dateStyle="long"/>加入BeginCode </h6>
                    <div id="echart" style="width: 200px;height:450px;"></div>
                </div>
            </div>
        </div>
       <input id="begincodeUserId" type="hidden" value="${user.begincodeUserId}"/>
        <div class="col-md-10 column">
            <div class="tabbable" id="tabs-657849">
                <ul class="nav nav-tabs">
                    <li class="active">
                        <a id="problem" href="#problems" data-toggle="tab">他的提问${problemSize}</a>
                    </li>
                    <li>
                        <a id="answer" href="#answers" data-toggle="tab">他的回答${answerSize}</a>
                    </li>
                    <li>
                        <a id="collection" href="#collections" data-toggle="tab">他的收藏${collectSize}</a>
                    </li>
                </ul>
                <div class="tab-content">
                    <div role="tabpanel" class="tab-pane fade in active" id="problems">
                        <div id="hisProblems" class="container-fluid">
                        </div>
                    </div>
                    <div role="tabpanel" class="tab-pane fade" class="tab-pane" id="answers">
                        <div id="hisAnswers" class="container-fluid">
                        </div>
                    </div>
                    <div role="tabpanel" class="tab-pane" id="collections">
                        <div id="hisCollections" class="container-fluid">
                        </div>
                    </div>
                    <nav style="text-align: center">
                        <ul id="paginationId" class="pagination">
                        </ul>
                    </nav>
                </div>
            </div>
        </div>
    </div>
</div>
</div>
<jsp:include page="/page/core/foot.jsp"/>
<script src="${ctx}/js/bootstrap/bootstrap.js"></script>
<script src="${ctx}/js/commons/timeUtil.js"></script>
<script src="${ctx}/js/commons/jqpagination.js"></script>
<script src="${ctx}/js/echarts/echarts.js"></script>
<script src="${ctx}/js/echarts/createEcharts.js"></script>
<script src="${ctx}/js/problem/userProblem.js"></script>
</body>
</html>