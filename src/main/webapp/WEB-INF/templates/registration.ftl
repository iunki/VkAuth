<#assign sf=JspTaglibs["http://www.springframework.org/tags/form"]>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="/resources/css/bootstrap.css"/>
    <link rel="stylesheet" type="text/css" href="/resources/css/main.css"/>

    <script src="/resources/js/jquery-2.1.3.min.js"></script>
</head>
<body>

<div class="col-md-4 col-md-offset-4">
<@sf.form action="/registration" method="post" modelAttribute="userform">
    <div class="form-group">
        <label for="username">Логин</label>
        <@sf.input path="username" id="username" cssClass="form-control" placeholder="Логин"/>
        <@sf.errors path="username"/>
    </div>
    <div class="form-group">
        <label for="firstName">Имя</label>
        <@sf.input path="firstName" id="firstName" cssClass="form-control" placeholder="Имя"/>
        <@sf.errors path="firstName"/>
    </div>
    <div class="form-group">
        <label for="lastName">Фамилия</label>
        <@sf.input path="lastName" id="lastName" cssClass="form-control" placeholder="Фамилия"/>
        <@sf.errors path="lastName"/>
    </div>
    <div class="form-group">
        <label for="password">Пароль</label>
        <@sf.input path="password" id="password" cssClass="form-control" placeholder="Пароль"/>
        <@sf.errors path="password"/>
    </div>
    <button type="submit" class="btn btn-default">Зарегистрироваться</button>
</@sf.form>
</div>

<a href="/registration/vk">Зарегистрироваться через VKONTAKTE</a>
</body>
</html>