<%--
  Created by IntelliJ IDEA.
  User: ksi64
  Date: 2021-08-17
  Time: 오전 10:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    Cookie cookie = new Cookie("sample", "1111");
    //쿠키 만들었으니 담아서 보내기. response활용.

    cookie.setMaxAge(60*60*24);//1day

    response.addCookie(cookie);//Set-Cookie의 HTTP헤더로 변경됨


%>

<!--쿠키 만들어보기-->
<h1>쿠키가 만들어 졌음 ...</h1>
</body>
</html>
