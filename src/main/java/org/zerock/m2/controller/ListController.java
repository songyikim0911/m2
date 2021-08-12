package org.zerock.m2.controller;


import lombok.extern.log4j.Log4j2;
import org.zerock.m2.dto.MsgDTO;
import org.zerock.m2.service.MsgService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Log4j2
@WebServlet(name = "ListController", value = "/msg/list")
// /msg/list 는 컨트롤러의 경로!!
//모든 request는 컨트롤러가 제일먼저 받는다.
public class ListController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        log.info("list controller doGet....");

        Map<String, List<MsgDTO>> result = MsgService.INSTANCE.getList("user5");
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

