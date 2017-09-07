<#assign basePath=request.contextPath />
<!DOCTYPE html>
<html>
<head>
    <meta content="text/html;charset=UTF-8"/>
    <base id="basePath" href="${basePath}">
    <title>登录页面</title>
    <link rel="stylesheet" href="${basePath}/css/bootstrap.min.css"/>
    <style type="text/css">
        body {
            padding-top: 50px;
        }

        .starter-template {
            padding: 40px 15px;
            text-align: center;
        }
    </style>
</head>
<body>

<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">Spring Security演示</a>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <li><a href="${basePath}/"> 首页 </a></li>

            </ul>
        </div><!--/.nav-collapse -->
    </div>
</nav>
<div class="container">

    <div class="starter-template">
        <h2>使用账号密码登录</h2>
        <#if msg??>用户密码错误，请重新输入</#if>
        <form name="form" action="${basePath}/login" method="POST">
            <div class="form-group">
                <label for="username">账号</label>
                <input type="text" class="form-control" name="j_username" value="" placeholder="账号"/>
            </div>
            <div class="form-group">
                <label for="password">密码</label>
                <input type="password" class="form-control" name="j_password" placeholder="密码"/>
            </div>
            <input type="submit" id="login" value="Login" class="btn btn-primary"/>
        </form>
    </div>
</div>
</body>
</html>