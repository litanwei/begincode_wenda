<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改</title>
</head>
<body>
<form action="${pageContext.request.contextPath }/test/modify.htm" method="post">
<input type="hidden" name="id" value="${admin.id }"/>
<table align="center" border="1" width="300px">
    <tr>
    	<th>用户名</th>
        <td><input type="text" name="username" value="${admin.username }"/></td>
    </tr>
    <tr>
    	<th>年龄</th>
        <td><input type="text" name="password" value="${admin.password }"/></td>
    </tr>
    <tr>
        <td colspan="2" align="center">
        <input type="submit" value="保存"/>&nbsp;
        <input type="reset" value="重置"/></td>
    </tr>
</table>
</body>
</html>