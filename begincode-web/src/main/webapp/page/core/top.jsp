<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../commons/taglibs.jsp"%>
<script type="text/javascript"
	src="http://qzonestyle.gtimg.cn/qzone/openapi/qc_loader.js"
	data-appid="101230380"
	data-redirecturi="http://www.begincode.net/login.html" charset="utf-8"></script>
<script src="${ctx}/js/jquery/jquery-3.1.0.min.js"></script>
<script src="${ctx}/js/jquery/jqPaginator.min.js"></script>
<script src="${pageContext.request.contextPath}/js/layer/layer.js"></script>
<script src="${ctx}/js/login/login.js"></script>
<script type="text/javascript">
	var ctx="${ctx}";
	checkLoginStatus();
	function register_sumbit_show() {
		layer.open({
			type : 2,
			title : '',
			shadeClose : true,
			shade : false,
			area : [ '400px', '500px' ],
			content : '/user/register_view.htm'
		});
	}
	function login_sumbit_show() {
		layer.open({
			type : 2,
			title : '',
			shadeClose : true,
			shade : false,
			area : [ '400px', '400px' ],
			content : 'http://www.begincode.net/user/login_view.htm'
		});
	}
	function login_delete(){
		$.ajax({
			data:"",
			type:"post",
			url:"/user/loginClean.htm"
		});
		parent.location.reload();
	}
</script>
<style type="text/css">
#loginShowTop {
	float: left;
	margin-top: 8px;
}
</style>
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
					<div id="loginShowTop">
						<a href="javascript:void(0)" onclick="login_sumbit_show()">登录</a> 
						<a href="javascript:void(0)" onclick="register_sumbit_show()">注册</a>
					</div>
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
	<!-- /.container-fluid -->
</nav>