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
  
  
    <nav class="navbar navbar-default marbot0">
  <div class="container-fluid">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="#">BeginCode</a>
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav">
        <li class="active"><a href="#">Link <span class="sr-only">(current)</span></a></li>
        <li><a href="#">Link</a></li>
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Dropdown <span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li><a href="#">问答</a></li>
            <li><a href="#">Another action</a></li>
            <li><a href="#">Something else here</a></li>
            <li role="separator" class="divider"></li>
            <li><a href="#">Separated link</a></li>
            <li role="separator" class="divider"></li>
            <li><a href="#">One more separated link</a></li>
          </ul>
        </li>
      </ul>
      
      <ul class="nav navbar-nav navbar-right">
        <form class="navbar-form navbar-left" role="search">
        <div class="form-group">
          <input type="text" class="form-control" placeholder="搜索问题和答案">
        </div>
        <button type="submit" class="btn btn-default">Submit</button>
      </form>
      </ul>
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>
<div class="continer">
<div class="post-topheader">
        <div class="container">
            <div class="row">
                <div class="col-md-9 col-sm-8 col-xs-12">
                   
                    <div class="post-topheader__info">
                        <h1 class="h3 post-topheader__info--title" id="questionTitle" data-id="1010000006602336">
                            <a href="/q/1010000006602336">求面向对象问题</a>
                        </h1>

                        <span class="keyword-list "> 
										<a href="" target="_blank" class="list-tag">html</a><a href="" target="_blank" class="list-tag">css</a>
								</span>

                        <div class="question-author">
                            <a href="/u/ikobe" class="mr5"><strong>ikobe</strong></a>
                            17 分钟前提问 </span>
                        </div>
                    </div>
                </div>
                <div class="col-md-3 col-sm-4 col-xs-12 hidden-xs">
                    <ul class="post-topheader__side list-unstyled">
                        <li>
                                                            <button type="button" id="sideFollow" class="btn btn-danger btn-sm"
                                        data-id="1010000006602336" data-do="follow" data-type="question"
                                        data-toggle="tooltip" data-placement="right" title="关注后将获得更新提醒">关注
                                </button>
                                                        <strong>0</strong> 关注
                        </li>
                        <li>
                                                            <button type="button" id="sideBookmark" class="btn btn-default btn-sm"
                                        data-id="1010000006602336" data-type="question">收藏
                                </button>
                                                        <strong id="sideBookmarked">0</strong> 收藏，<strong
                                    class="no-stress">22</strong> 浏览
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
	</div>
<div class="container">



<div class="row">
  <div class="col-md-9">
   
    <p class="lead">正文显示</p>
   <blockquote>
  <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer posuere erat a ante.</p>
  <footer>Someone famous in <cite title="Source Title">Source Title</cite></footer>
</blockquote>

<h6>h6. Bootstrap heading <small>Secondary text</small></h6>
<pre><code class="language-html" data-lang="html">You can use the mark tag to <span class="nt">&lt;mark&gt;</span>highlight<span class="nt">&lt;/mark&gt;</span> text.</code></pre>

<center>
<button type="button" class="btn btn-primary">点击投票(12)</button>
  
</center>

<hr>

<div class="post-offset">
 <div class="qa-rank">
<div class="answers">
                <small>已采纳</small>
            </div>
			</div>
            <div class="answer fmt" data-id="1020000006591589">
                                    <p>不明白这个需求的产生，如果在login之前需要进行check，那完全可以在methods层面做，你这样增加了数据和展现的耦合。<br>而且dologin和check之间看起来是有顺序关系的，有必要在js做控制啊</p>
                            </div>

            
                        
            <div class="row answer__info--row">
                <div class="post-opt col-md-8 col-sm-8 col-xs-10">
                    <ul class="list-inline mb0">
                        
                        <li><a href="/q/1010000006591181/a-1020000006591589"> 5 小时前回答</a> <span class="text-muted">

                    </span></li>
                                                    <li data-toggle="tooltip" data-placement="top" title="" class="edit-btn js__rank-check"><a href="javascript:;">编辑</a>
                            </li>
                        
                        <li><a href="javascript:void(0);" class="comments" data-id="1020000006591589"
                               data-target="#comment-1020000006591589">
                                评论</a></li>
                        
                                                    <li class="dropdown">
                                <a href="javascript:void(0);" class="dropdown-toggle" data-toggle="dropdown">更多<b
                                            class="caret"></b></a>
                                
                            </li>
                                            </ul>
                </div>

                <div class="col-md-2 col-sm-2 col-xs-2 answer__info--author-avatar">
                    
                </div>
                <div class="col-md-2 col-sm-2 hidden-xs answer__info--author">
                    <div class=" answer__info--author-warp">
                        <a class="answer__info--author-name" title="fishenal"
                           href="/u/fishenal">fishenal</a><span
                                class="answer__info--author-rank"></span></div>
                </div>
            </div>
</div>			
<hr>
 
<div class="post-offset">
            <div class="answer fmt" data-id="1020000006591589">
                                    <p>不明白这个需求的产生，如果在login之前需要进行check，那完全可以在methods层面做，你这样增加了数据和展现的耦合。<br>而且dologin和check之间看起来是有顺序关系的，有必要在js做控制啊</p>
                            </div>

            
                        
            <div class="row answer__info--row">
                <div class="post-opt col-md-8 col-sm-8 col-xs-10">
                    <ul class="list-inline mb0">
                        
                        <li><a href="/q/1010000006591181/a-1020000006591589"> 5 小时前回答</a> <span class="text-muted">

                    </span></li>
                                                    <li data-toggle="tooltip" data-placement="top" title="" class="edit-btn js__rank-check"><a href="javascript:;">编辑</a>
                            </li>
                        
                        <li><a href="javascript:void(0);" class="comments" data-id="1020000006591589"
                               data-target="#comment-1020000006591589">
                                评论</a></li>
                        
                                                    <li class="dropdown">
                                <a href="javascript:void(0);" class="dropdown-toggle" data-toggle="dropdown">举报<b
                                            class="caret"></b></a>
                                
                            </li>
                                            </ul>
                </div>

                <div class="col-md-2 col-sm-2 col-xs-2 answer__info--author-avatar">
                
                </div>
                <div class="col-md-2 col-sm-2 hidden-xs answer__info--author">
                    <div class=" answer__info--author-warp">
                        <a class="answer__info--author-name" title="fishenal"
                           href="/u/fishenal">fishenal</a><span
                                class="answer__info--author-rank"></span></div>
                </div>
            </div>
</div>	














<p>


<hr>
<div id="summernote"><p>Hello Summernote</p></div>
</p>
  <p>
  <button type="button" class="btn btn-success">提交答案</button>
  </p>
  
  </div>
 
<div class="col-md-3">

 <div class="panel panel-default">
  <div class="panel-heading">
    <h3 class="panel-title">热门标签</h3>
  </div>
  <div class="panel-body">
    <span class="tag-list">
        <a href="" target="_blank" class="list-tag">java</a><a href="" target="_blank" class="list-tag">基础类型</a>
		<a href="" target="_blank" class="list-tag">接口</a><a href="" target="_blank" class="list-tag">面向对象</a>
		<a href="" target="_blank" class="list-tag">框架</a><a href="" target="_blank" class="list-tag">SSM</a>
	</span>
  </div>
</div>

<div class="list-group">
  <a href="#" class="list-group-item disabled">
    相似问题
  </a>
  <a href="#" class="list-group-item">问题111</a>
  <a href="#" class="list-group-item">问题222</a>

</div>
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