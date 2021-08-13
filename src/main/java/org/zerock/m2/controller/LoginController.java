package org.zerock.m2.controller;

import lombok.extern.log4j.Log4j2;
import org.zerock.m2.dto.MemberDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

//GET일때는 아이디와 패스워드 입력
//POST일때는 로그인 처리 -> /msg/list
@WebServlet(name="", value="")
@Log4j2
public class LoginController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        log.info("GET login");

        request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //파라미터 수집, mid mpw
        String mid = request.getParameter("mid");
        String mpw = request.getParameter("mpw");
        /*사용자 정보 -  정보 구한다.  ----> 실패시) 사용자의 정보가 없다.
        -->다시 get방식으로 로그인페이지 값*/
        MemberDTO memberDTO =MemberDTO.builder()
                .mid(mid)
                .mpw(mpw)
                .mname("사용자"+mid)
                .nickname("사용자"+mid)
                .build();

        //세션에 setAttribute("member", 사용자 정보)

        HttpSession session = request.getSession();
        session.setAttribute("member", memberDTO);

        //   /msg/list 로 리다이렉트 시킨다.



    }
}
