<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: saber
  Date: 2016/8/25
  Time: 21:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/css/reveal.css">
    <script type="text/javascript" src="http://code.jquery.com/jquery-1.6.min.js"></script>
    <script type="text/javascript" src="/js/jquery.reveal.js"></script>
</head>
<body>

测试登录

<c:if test="${msg=='需要登陆'}" var="condition" scope="request">
    <script type="text/javascript">
        $(function() {
            $('#alink').trigger("click");
        })
    </script>>
</c:if>
<div id="myModal" class="reveal-modal">
    <h1>警告</h1>
    <p>检测到您的操作处于未登录或登录时间超时状态，请重新登录。</p>
    <input type="button" value="确定"
           onclick="javascript:history.go(-1);">
    <a class="close-reveal-modal">&#215;</a>
    <a id ="alink" data-reveal-id="myModal" data-animation="fade"></a>
</div>
</body>
</html>
