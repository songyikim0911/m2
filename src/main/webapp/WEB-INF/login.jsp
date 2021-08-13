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

<c:if test = "${param.result != null}">
<div class="warnDiv">
    <h1>Login Failed</h1>
    </div>
</c:if>

<form action="/login" method="post">
    <input type="text" name ="mid">
    <input type="text" name ="mpw">
    <button type="submit">LOGIN</button>
</form>

</body>
</html>
