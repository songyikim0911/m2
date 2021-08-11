package org.zerock.m2.controller;

import lombok.extern.log4j.Log4j2;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "CalcController", value = "/calc")
@Log4j2//log변수 생성!!! log 변수 기능을 쓸 수 있다.
public class CalcController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("CalcController doGet........");
        //이제 System.out.println 대신 log를 쓰면된다.
        //log.trace/log.info/log.debug/log.warn/log.error/log.fatal
        //trace가 로그레벨이제일낮음.info로 할 경우 trace는 로그에 안나옴.
        //<loggers><root level="error">/debug/info 레벨 설정에 따라
        //그 레벨이상의 log만 표출.ex)error로 한경우 error와 fatal만 뜸.
        //개발시에는 debug 또는 info레벨을 많이씀.

        RequestDispatcher dispatcher//get방식은 Input과 연결
                =request.getRequestDispatcher("/WEB-INF/calcInput.jsp");

        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        log.info("doPost..........");

        String num1Str = request.getParameter("num1");
        String num2Str = request.getParameter("num2");
        String oper = request.getParameter("oper");

        log.info("num1Str: "+num1Str);
        log.info("num2Str: "+num2Str);
        log.info("oper: "+oper);




        RequestDispatcher dispatcher//post 방식은 calcResult와 연결
                =request.getRequestDispatcher("/WEB-INF/calcResult.jsp");

        dispatcher.forward(request, response);


    }
}
