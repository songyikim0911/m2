package org.zerock.m2.controller;

import org.zerock.m2.dto.MemberDTO;
import org.zerock.m2.dto.MsgDTO;
import org.zerock.m2.service.MsgService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ReadController", value = "/msg/read")
public class ReadController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //로그인 체크 로직
        HttpSession session = request.getSession();
        Object memberObj = session.getAttribute("member");

        //로그인 관련 정보 없음 - 로그인 안한 사용자.
        if(memberObj == null){
            response.sendRedirect("/login");
            return;//반환하는 키워드가 아님
        }


        Long mno = Long.parseLong(request.getParameter("mno"));

        MsgDTO msgDTO = MsgService.INSTANCE.read(mno);

        request.setAttribute("dto", msgDTO); //택배 포장

        request.getRequestDispatcher("/WEB-INF/msg/read.jsp").forward(request,response); //송장 붙여~~!, 보내~
    }

}