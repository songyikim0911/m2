package org.zerock.m2.controller;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "CalcController", value = "/calc")
public class CalcController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher dispatcher//get방식은 Input과 연결
                =request.getRequestDispatcher("/WEB-INF/calcInput.jsp");

        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher dispatcher//post 방식은 calcResult와 연결
                =request.getRequestDispatcher("/WEB-INF/calcResult.jsp");

        dispatcher.forward(request, response);


    }
}
