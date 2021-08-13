<%--
  Created by IntelliJ IDEA.
  User: ksi64
  Date: 2021-08-13
  Time: 오후 12:39
  To change this template use File | Settings | File Templates.
--%>
<%
    //HttpSession session = requqest.getSession();
    session.removeAttribute("name");//값 지운 후
    session.invalidate();//무효화 (안정성up)
%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

</body>
</html>
