﻿<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
    <link href="${ctx}/css/bootstrap.css" rel="stylesheet">
	 <link href="${ctx}/css/qu.css" rel="stylesheet">
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>
  <body>


  <jsp:include page="/page/core/top.jsp" />

<div class="container-fluid">
<div class="row ">
	<div class="col-md-12 col-sm-12 col-xs-12"">
		

<form id="questionForm"  methond="post">
  <div class="form-group">
    <input type="hidden" name="begincodeUserId" value="1"/>
    <input type="hidden" name="userName" value="yang"/>
    <label ><span class="labelinfoblue"></span>问题标题</label>
    <input type="text" class="form-control" name="title" placeholder="请输入标题">
  </div>
  <div class="form-group ">
    <label><span class="labelinfoblue"></span>问题关键字(以逗号分隔)</label>
    <input type="text" class="form-control" name="labelName"  placeholder="请输入关键字，逗号分隔">
  </div>
  <input type="hidden" name="">
  <label><span class="labelinfoblue"></span>问题正文</label>
  <input type="hidden" name="content" id="content" value=""/>
   <div id="summernote"><p></p></div>
  
<nav class="navbar navbar-default navbar-fixed-bottom">
  <div class="container-fluid align-center">
	
        <button type="button" id="questionSend" class="btn btn-primary martop10">提交问题</button>
      
  </div>
</nav>
</form>
</div>
	</div>

</div>


  <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
  <script src="${ctx}/js/jquery-3.1.0.min.js"></script>
  <!-- Include all compiled plugins (below), or include individual files as needed -->
  <script src="${ctx}/js/bootstrap.js"></script>
  <link href="${ctx}/summernote/summernote.css" rel="stylesheet">
  <script src="${ctx}/summernote/summernote.js"></script>
  <script src="${ctx}/js/problemAdd.js"></script>
  <script src="${ctx}/js/summernoteInit.js"></script>

  </body>
</html>