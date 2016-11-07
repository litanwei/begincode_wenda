<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../commons/taglibs.jsp"%>
<script type="text/javascript"
	src="http://qzonestyle.gtimg.cn/qzone/openapi/qc_loader.js"
	data-appid="101230380"
	data-redirecturi="http://www.begincode.net/login.html" charset="utf-8"></script>
<script src="${ctx}/js/jquery/jquery-3.1.0.min.js"></script>
<script src="${ctx}/js/jquery/jqPaginator.min.js"></script>
<script src="${ctx}/js/layer/layer.js"></script>
<script src="${ctx}/js/login/login.js"></script>
<script type="text/javascript">
	var ctx = "${ctx}";
	function one() {
		var html=$("#successLoginDiv").html();
		alert(html);
		$("#simpleLogin").html(html);
	}
</script>
<!-- 这是成功登录的页面 -->
<script id="successLoginDiv" type="text/html">
	<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">欢迎:username<span class="caret"></span></a>
	<ul class="dropdown-menu">
		<li><a href="javascript:void(0)">博文管理</a></li>
		<li role="separator" class="divider"></li>
		<li><a href="javascript:void(0)" onclick="exitLogin()">退出</a></li>
	</ul>
</script>
<!-- 这是失败的页面 -->
<script id="failLoginDiv" type="text/html">
	<a href="javascript:void(0)" onclick="login_sumbit_show()">登录</a>
	<a href="javascript:void(0)" onclick="register_sumbit_show()">注册</a>
</script>
<nav class="navbar navbar-default" role="navigation">
	<div class="container-fluid">
		<!-- Brand and toggle get grouped for better mobile display -->

		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="http://www.begincode.net"><img
				alt="初学者社区" src="${ctx}/images/navlogo.gif" style="height: 30px;"></a>
		</div>

		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">

			<ul class="nav navbar-nav">
				<li class=""><a href="http://www.begincode.net/course/"
					class="dropdown-toggle">在线教程 </a></li>
				<li class=""><a href="http://www.begincode.net/course/"
					class="dropdown-toggle">问答 </a></li>


			</ul>
			<div id="navbar" class="navbar-collapse collapse">

				<form class="navbar-form navbar-right" action="${ctx}/search.htm"
					method="get">
					<span id="simpleLogin" class="dropdown"> <a
						href="JavaScript:void(0)" onclick="view_login()">登录</a> <a
						href="JavaScript:void(0)" onclick="view_register()">注册</a>
					</span>
					<div class="form-group">
						<input type="text" name="q" class="form-control "
							placeholder="教程,资源,问题">
					</div>
					<button type="submit" class="btn btn-primary">搜索</button>
				</form>
			</div>
		</div>
		<!-- /.navbar-collapse -->
	</div>
	<div id="test">
		<input type="button" onclick="one()" value="点击获取">
	</div>
	<span>我要获取值啊${sessionScope.checkLogin}</span>
	<!-- /.container-fluid -->
</nav>