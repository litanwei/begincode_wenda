<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="../js/jquery/jquery-3.1.0.min.js"></script>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>查询所有联系人</title>
    <style type="text/css">
        table td{
            /*文字居中*/
            text-align:center;
        }

        /*合并表格的边框*/
        table{
            border-collapse:collapse;
        }
    </style>
    <link href="../css/bootstrap.css" rel="stylesheet">
    <link href="../css/qu.css" rel="stylesheet">
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>

<jsp:include page="/page/core/top.jsp" />
<center><h3>查询所有用户</h3></center>
<table align="center" border="1" width="700px">
    <tr>
        <th>id</th>
        <th>用户名</th>
        <th>密码</th>
        <th>操作</th>
    </tr>
    <c:forEach items="${list}" var="con" varStatus="varSta">
        <tr>
            <td>${con.id }</td>
            <td>${con.username }</td>
            <td>${con.password }</td>
            <td><a href="${pageContext.request.contextPath }/test/modifySkip.htm?id=${con.id}">修改</a>&nbsp;<a href="${pageContext.request.contextPath }/test/delete.htm?id=${con.id}">删除</a></td>
        </tr>
    </c:forEach>
    <tr>
        <td colspan="8" align="center"><a href="${pageContext.request.contextPath }/page/add.jsp">[添加用户]</a></td>
    </tr>
</table>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="../js/bootstrap/bootstrap.js"></script>
</body>
</html>

