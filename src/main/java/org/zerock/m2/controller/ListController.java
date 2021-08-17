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

        //2개 혹은 1개!
        Cookie[] allCookies = request.getCookies();
        boolean checkCookie = false;

        String user = null;

        //로그인 후 브라우저 종료 -> 로그인쿠키는 파일에 저장되있고 세션쿠키는삭제됨
        //재로그인시 로그인쿠키1개만으로 인증가능 이러한 로그인쿠키를 가진지 체크하는 로직이 아래 로직임.
        if(allCookies != null && allCookies.length > 0 ){//if 쿠키가 1개이상이라도 존재한다면, 아래 루프!
            for(int i = 0; i< allCookies.length; i++){
                Cookie ck = allCookies[i];//ck에 쿠키값을 집어넣음.
                if(ck.getName().equals("login")){//로그인이라는 이름이 있다 -> 로그인한 사용자라는 의미.
                    checkCookie = true;
                    user = ck.getValue();//값을 얻어서, user에 셋팅.
                    //기존 정보로 진행 하는 경우. 우리에게 mid 만 필요하고, 해당 값은 이미 login쿠키안에 있다.
                }
            }
        }



        //로그인 관련 정보 없음 - 로그인 안한 사용자.
        if(memberObj == null && checkCookie == false){
            //checkCookie == false조건 추가, 쿠키정보도 없는지 확인해야. chckCookie가 없거나 만료된 쿠키면 null이다.
            //참고로, 만료된 쿠키는 브라우저가 보내지 않아서 신경 쓸 필요가없다.
            response.sendRedirect("/login");
            return;//반환하는 키워드가 아님
        }

        //쿠키로 접속한 경우 memberObj는 null이됨..
        //이런 경우, 쿠키에 있는 정보 중 멤버 정보를 가져와야함
        //단, 쿠키에 있는 정보는 id만 있음..!


        if(memberObj !=null){ //새로 로그인한 사용자에 대한 로직.  세션에서 부터 값을 가져와서 아래 로직진행.
            MemberDTO memberDTO = (MemberDTO)memberObj;
            //다운 캐스팅 : 어떤 객체를 바라볼때 객체의 타입을 특정한 타입으로 줄여서 보는 것.
            user = memberDTO.getMid();
        }


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

