<%--
  Created by IntelliJ IDEA.
  User: ksi64
  Date: 2021-08-10
  Time: 오전 11:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>Hello2 JSP Page</h1>

    <h2>${msg}</h2> <!--아까 컨트롤러에 작성한 key를 쓸 수 있다.-->
<!--$를 "EL" 이라고한다. 결과 출력/ 루프돌리는것. 등에 쓰임.
 지금 \${}안에는, 1+2같은 연산을 넣을 수도있고,그러면 결과값이나오고...  arr을 넣으면 주소값이나오고..
 arr.length같이 값이 나오는 것을 넣을 수 있다.. 단,jstl은 퍼블리셔들 을위한문법이라 복잡하니 c태그를 쓰는것을 권장.
 삼항 연산자도 넣을 수 있고, if else도 가능 단,, 기재방법이 좀 복잡함 ->jsp 표현식 문법과 사용법 검색해서 사용!!!
  이 el안에서는 어떻게든 값만 만들어내면되는것... -->

    <ul>
        <c:forEach items="${arr}" var="str">
            <li>${str}</li>   <!--forEach 개념 꼭 정리하기, var안에 str 이 0~배열끝까지 도는거임. -->
        </c:forEach>
    </ul>

</body>
</html>
