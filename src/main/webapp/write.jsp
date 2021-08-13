<%--
  Created by IntelliJ IDEA.
  User: ksi64
  Date: 2021-08-13
  Time: 오후 12:12
  To change this template use File | Settings | File Templates.
--%>
<%
  //HttpSession session = request.getSession();
  Object obj = session.getAttribute("name");
  //로그인 한적이 없다면 null이 나올것 -> 그런 경우 로그인페이지로 redirect 코드

  if(obj == null){
    response.sendRedirect("/login.jsp");
    return;
  }
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Write Page</h1>
<h2><%=obj%></h2>

<form action="/logout.jsp" method="post">
  <button type="submit">LOGOUT</button>
</form>
</body>
</html>
