<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="commons/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
      <meta name="keywords" content="问答系统,BeginCode问答"/>
      <meta name="description" content="创建问题,开源问答系统,BeginCode问答"/>
      <%@ include file="commons/meta.jsp"%>
      <title>创建问题,BeginCode问答</title>

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
		

<form id="problemForm"  methond="post">
  <div id="warning"></div>
  <div class="form-group">
    <label><span class="labelinfoblue"></span>问题标题</label>
    <input type="text" id="title" class="form-control" name="problem.title" placeholder="请输入标题">
  </div>
  <div class="form-group ">
    <label><span class="labelinfoblue"></span>问题关键字(以逗号分隔,输入关键字能更快得到答案!)</label>
    <input type="text" id="labelName" class="form-control" name="label.labelName"  placeholder="请输入关键字，逗号分隔">
  </div>
  <input type="hidden" name="">
  <label><span class="labelinfoblue"></span>问题正文</label>
  <input type="hidden" name="problem.content" id="content" value=""/>
   <div id="summernote"><p></p></div>
  
<nav class="navbar navbar-default navbar-fixed-bottom">
  <div class="container-fluid align-center">
	
        <button type="button" id="problemSend" class="btn btn-primary martop10">提交问题</button>
      
  </div>
</nav>
</form>
</div>
	</div>

</div>

  <!-- 模态框（Modal） -->
  <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
      <div class="modal-dialog">
          <div class="modal-content">
              <div class="modal-header">
                  <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                  </button>
                  <h4 class="modal-title" id="myModalLabel">
                      警告
                  </h4>
              </div>
              <div class="modal-body">
                  请检查是否登录！
              </div>
              <div class="modal-footer">
                  <a type="button" class="btn btn-primary" data-dismiss="modal">关闭</a>
              </div>
          </div><!-- /.modal-content -->
      </div><!-- /.modal -->
  </div>
  <!--提问后提示弹出框-->
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
                  <a type="button" class="btn btn-primary" data-dismiss="modal">关闭</a>
              </div>
          </div>
      </div>
  </div>

  <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
  <script src="${ctx}/js/jquery/jquery-3.1.0.min.js"></script>
  <!-- Include all compiled plugins (below), or include individual files as needed -->
  <script src="${ctx}/js/bootstrap/bootstrap.js"></script>
  <script src="${ctx}/js/problem/createProblem.js"></script>
  <link href="${ctx}/summernote/summernote.css" rel="stylesheet">
  <script src="${ctx}/summernote/summernote.js"></script>
  <script src="${ctx}/js/summernotePlugin.js"></script>

  </body>
</html>