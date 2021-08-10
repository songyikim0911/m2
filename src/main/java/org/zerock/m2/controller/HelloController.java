package org.zerock.m2.controller;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "HelloController", value = "/hello")
//jsp는 이 servlet(컨트롤러)를 통해서만 들어갈 수 있다.
//현재 jsp는 WEB-INF(웹브라우저에 보여지지 않는 경로)안에 있다.
public class HelloController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        /*
        컨트롤러의 판단(결과) 2가지.
        1.forward 시키는것. forward()-> 자신의 결과를 다른 jsp에서 출력하도록 유도.
        2.response.sendRedirect()-> 다른곳으로 가게 유도.
        (include방식은 잊고, 이 2가지만 생각하기)

         */

   /*모델을 쓰는 방법?
   request.setAttribute() 키, 값의 형태로 데이터를 담는다.(저장)
   이 데이터를 "모델"이라고 부른다.
   이 "모델" 을 jsp에서 출력한다.
    */

        System.out.println("Hello Controller doGet");

        //RequestDispatcher 중요! 현재 들어온 요청을 다른 리소스로 전달하는 역할을 함.
        //HttpServerletRequest를 이용.

        String data = "생성자 데이터";
        String[] arr = {"AAA","BBB","CCC","DDD","EEE","FFF"};
        request.setAttribute("msg", data);
        request.setAttribute("arr", arr);
        //값에 태그를 붙여준다고 생각하면됨 (request.setAttribute(태그,값)
        //msg를 주고, msg의 내용은 data.
        //hello2.jsp는 이 내용을 끄집어 쓸 수 있다.

        RequestDispatcher dispatcher
                =request.getRequestDispatcher("/WEB-INF/hello2.jsp");

        //모델2의 기본적인틀!
        //request.getRequestDispatcher내에 경로값을 바꾸면서 원하는 페이지를 조절 할 수 있다.
        //보이는 URL은 같게 하면서 실제 연동되는 JSP는 조절이 가능해짐.

        dispatcher.forward(request, response);//컨트롤러가 만든 결과는 다 비우고,jsp내용만 처리
        //forward의 의미. 출력버퍼.
    }

}