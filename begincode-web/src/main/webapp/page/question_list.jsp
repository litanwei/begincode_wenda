<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="commons/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<%@ include file="commons/meta.jsp"%>
<title>Bootstrap 101 Template</title>

<!-- Bootstrap -->
<link href="../css/bootstrap.css" rel="stylesheet">
<link href="../css/qu.css" rel="stylesheet">
<link href="../css/pagination.css" rel="stylesheet">
</head>
<body>

	<jsp:include page="/page/core/top.jsp" />


	<div class="container-fluid">

		<h1 style="text-align: center;">【${label }】</h1>
		<br>
		<table style="float: center;">
			<tr>
				<td>问题ID</td>
				<td>问题标题</td>
				<td>提问者</td>
			</tr>
			<c:forEach items="${proLabel}" var="con" varStatus="varSta">
				<tr>
					<td>${con.problemId }</td>
					<td>${con.title }</td>
					<td>${con.userName }</td>
				</tr>
			</c:forEach>
		</table>

	
	</div>

	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script src="../js/jquery-3.1.0.min.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="../js/bootstrap.js"></script>


</body>
</html>



