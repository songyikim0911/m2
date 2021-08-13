<%--
  Created by IntelliJ IDEA.
  User: ksi64
  Date: 2021-08-13
  Time: 오후 12:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <!--//webapp바로 밑에서 만든 이유 - 브라우저에서 바로 접근하려고-->
</head>
<body>
<h1>Fake Login</h1>
<%
    String userid = request.getParameter("userid");
    //HttpSession session = request.getSession();
    //원래는 request ,response 선언이 필요하지만 jsp는 이미 해당 선언이 되어있다.
    session.setAttribute("name", userid);

%>

<h2><%=userid%>님 로그인 되었음</h2>

</body>
</html>
