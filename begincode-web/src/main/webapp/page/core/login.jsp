<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/meta.jsp"%>
<!DOCTYPE html>
<html lang="zh-cn">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
<title>加入我们,初学者论坛,课程发布</title>
<!-- Bootstrap -->
<link href="${ctx}/css/bootstrap.css" rel="stylesheet">
 <script src="${ctx}/js/jquery.min.js"></script>
<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
      <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>
<body  >
	<jsp:include page="/page/core/top.jsp" />


 <div class="bs-docs-header" id="content"  style="margin-bottom:20px;">
      <div class="container">
        <h1>加入BeginCode开发社区</h1>
         <p style="text-align: center;">让我们共同为那些努力成为开发者的朋友分享你的经验和知识，让我们一起共同进步</p> 
        
      </div>
    </div>
    
     
	
	<div class="container .col-xs-" >
		<div class="row" >
					<div class="col-md-12" >
							  <div class="panel panel-primary" style="margin-top:0;" >
							  	<div class="panel-body">
							  	<div class="row">
							  		<div class="col-md-7">
					<form>	  		
							   <h3 >登录BeginCode </h3><div style="color:red;font-size:15px;"></div>
		<hr style="border:none;border-top:1px solid blue; height:0;" />
        <p> 
   <div class="form-group" >
    <label for="email"><span class="labelinfodanger" ></span>电子邮箱</label>
    <input  class="form-control required"    id="email" name="email" placeholder="电子邮箱"   >
  </div>
 
     <div class="form-group">
    <label for="pwd" ><span class="labelinfosuccess" ></span>密码</label>
    <input    class="form-control required" id="pwd" type="password" placeholder="输入密码" >
  </div>
      <div class="form-group">
  	<label for="repwd" ><span class="labelinfowarn" ></span>是否记住密码</label>
  	<input type="hidden" name="rem" value="0" id="rem" />
    <div class="btn-group btn-group-justified">
  <div class="btn-group">
    <button type="button" class="btn btn-danger" id="rempwd" >否</button>
  </div>
  <div class="btn-group">
    <button type="button" class="btn btn-default" id="unrempwd"  >是</button>
  </div>
</div>
  </div>
    
  <div style="margin-top:10px;text-align:center;">
   <button type="button" class="btn btn-primary"  id="send"  style="width:100px;">登录</button> 
   </div>
<div style="margin-top:10px;text-align:center;color:red;font-weight:bold;" id="msgInfo">
	${msg}
   </div>
</p>
</form>	
							  		
							  		</div>
							  		<div class="col-md-5">
							  			<h3>欢迎您的到来</h3>
							  			<p>
							  				1、发布博客、与大家共同分享你的知识
							  			</p>
							  			<p>
							  				2、参与课程学习跟踪课程进展
							  			</p>
							  			<p>
							  				3、获得最新课程，技术的通知
							  			</p>
							  			<p>
							  				4、认识更多的朋友
							  			</p>
							  		</div>
							  	
							  	</div>
	
</div>

							  </div>
							  
 
						    
 
					</div>
					
			 </div>
			

	</div>
	<!-- foot -->
	<jsp:include page="/page/core/foot.jsp" />

	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="${ctx}/js/bootstrap.min.js"></script>
	<script src="${ctx}/js/md5.js"></script>
</body>
</html>

