<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Stay
  Date: 2016/9/30
  Time: 16:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="${ctx}/js/jquery/jquery-3.1.0.min.js"></script>
    <script src="${ctx}/js/jquery/jqPaginator.min.js"></script>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <%@ include file="commons/meta.jsp" %>
    <title>Bootstrap 101 Template</title>
    <!-- Bootstrap -->
    <link href="${ctx}/css/bootstrap.css" rel="stylesheet">
    <link href="${ctx}/css/qu.css" rel="stylesheet">
    <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<jsp:include page="/page/core/top.jsp"/>


<div class="row">
    <div class="col-md-1">

    </div>
    <div class="col-md-8">
        <div id="search"></div>
        <c:choose>
            <c:when test="${searchList != null}">
                <h3 class="h5 mt0">找到约 <strong>${totalPage}</strong> 条结果</h3>
                <c:forEach var="u" items="${searchList}" varStatus="status">
                    <section class="stream-list__item">
                        <c:if test="${u.solve == '1'}">
                            <div class="qa-rank">
                                <div class="answers solved">
                                    <small>解决</small>
                                </div>
                            </div>
                        </c:if>
                        <div class="summary">
                <span class="keyword-list ">
                  <h2 class="title l"><a href="${ctx}/problem/${u.problemId}.htm">${u.title}</a></h2><br/>
                    <h5>${u.content}</h5>
                </span>
                        </div>
                    </section>
                </c:forEach>
                ${pagination}
            </c:when>
            <c:when test="${searchList == null}">
                <h3 style="color:red">站内无资源，或者关键字输入有误，请重新搜索！</h3>
            </c:when>
        </c:choose>


    </div>
    <div class="col-md-3">
        <p>
            <a href="http://www.begincode.com/problem/create.htm" class="btn btn-primary btn-lg btn-block">我要提问</a>
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
    </div>
</div>
</body>
</html>
