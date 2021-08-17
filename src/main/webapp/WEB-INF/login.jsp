<%--
  Created by IntelliJ IDEA.
  User: ksi64
  Date: 2021-08-13
  Time: 오후 12:15
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Login</h1>


<style>
    .warnDiv{
        background-color: #c46d4c;
    }
</style>

<c:if test = "${ param.result != null}">
    <!--값이 있으면 (기존 세션 쿠키를 가지고 있다면 ) , 로그인 실패로 간주하고
    아래 문장을 실행 -->
<div class="warnDiv">
    <h1>Login Failed</h1>
    </div>
</c:if>
<form action="/login" method="post">
    <input type="text" name ="mid">
    <input type="text" name ="mpw">
    <input type="checkbox" name="remember">Remember-me
    <button type="submit">LOGIN</button>
</form>

</body>
</html>
