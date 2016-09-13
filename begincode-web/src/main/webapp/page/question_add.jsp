<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
	<div class="col-md-12 col-sm-12 col-xs-12">
		

<form>
  <div class="form-group">
     
    <input type="text" class="form-control" id="exampleInputEmail1" placeholder="请输入标题">
  </div>
  <div class="form-group ">

    <input type="text" class="form-control" id="exampleInputPassword1" placeholder="请输入关键字，逗号分隔">
  </div>
   <div id="summernote" ><p>Hello Summernote</p></div>
  
<nav class="navbar navbar-default navbar-fixed-bottom">
  <div class="container-fluid align-center">
	
        <button type="submit" class="btn btn-primary martop10">提交问题</button>
      
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

  <script>
    $(document).ready(function() {
        $('#summernote').summernote({
  toolbar: [
    // [groupName, [list of button]]
    ['style', ['bold', 'italic', 'underline', 'clear']],
    ['insert', ['picture', 'link', 'table', 'hr']],
    ['fontsize', ['fontsize']],
    ['color', ['color']],
    ['para', ['ul', 'ol', 'paragraph']],
    ['height', ['height']],
	['musc', ['codeview']],
  ],
   height: 300,
     hint: {
    mentions: ['jayden', 'sam', 'alvin', 'david'],
    match: /\B@(\w*)$/,
    search: function (keyword, callback) {
      callback($.grep(this.mentions, function (item) {
        return item.indexOf(keyword) == 0;
      }));
    },
    content: function (item) {
      return '@' + item;
    }    
  }
});
    
	
});

  </script>
  </body>
</html>