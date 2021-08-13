package org.zerock.m2.controller;


import lombok.extern.log4j.Log4j2;
import org.zerock.m2.dto.MemberDTO;
import org.zerock.m2.dto.MsgDTO;
import org.zerock.m2.service.MsgService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Log4j2
@WebServlet(name = "ListController",  value = "/msg/list")
// /msg/list 는 컨트롤러의 경로!!
//모든 request는 컨트롤러가 제일먼저 받는다.
public class ListController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        log.info("list controller doGet....");

        //로그인 체크 로직
        HttpSession session = request.getSession();
        Object memberObj = session.getAttribute("member");

        //로그인 관련 정보 없음 - 로그인 안한 사용자.
        if(memberObj == null){
            response.sendRedirect("/login");
            return;//반환하는 키워드가 아님
        }

        MemberDTO memberDTO = (MemberDTO)memberObj;
        //다운 캐스팅 : 어떤 객체를 바라볼때 객체의 타입을 특정한 타입으로 줄여서 보는 것.

        String user = memberDTO.getMid();

//        String user = request.getParameter("whom"); (기존에 있던 문장)
//                //쿼리 스트링에서 데이터 뽑을때 제일 많이 쓰이는 구문 request.getParameter

        Map<String, List<MsgDTO>> result = MsgService.INSTANCE.getList(user);
        //"user"로 앞에서 받은 whom값 넣어주기. ?whom=user5 식으로 URL 통해 값 전달가능
        //현재 목록이 R목록(받는), S목록(보내는) 2종류이기 때문에 따로따로 보내는 것이 좋다.
        //최대한 펴서 보내주기.
        //jsp(view)로 택배 전달 ,,
        request.setAttribute("Receive", result.get("R"));//정보를 보내는 역할, 그중 R목록보내기
        request.setAttribute("Send", result.get("S"));//정보를 보내는 역할, 그중 S목록보내기


        request.getRequestDispatcher("/WEB-INF/msg/list.jsp").forward(request, response);



    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

}

