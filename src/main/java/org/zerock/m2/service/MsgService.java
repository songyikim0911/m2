package org.zerock.m2.service;

import lombok.extern.log4j.Log4j2;
import org.zerock.m2.dao.MsgDAO;
import org.zerock.m2.dto.MsgDTO;

import java.util.List;
import java.util.Map;

@Log4j2
public enum MsgService {
    //서비스 계층은 고객의 요구사항을 반영한다.

    INSTANCE;//인스턴스의 갯수를 정할 수 있는데, 하나만 만들 경우 보통 이 이름으로 만듬.

    public void remove(Long mno, String who) throws RuntimeException{
        log.info("service remove..." + mno, who);
        MsgDAO.INSTANCE.delete(mno, who);

    }

    public void register(MsgDTO msgDTO) throws RuntimeException{

        log.info("service register...." + msgDTO);

        MsgDAO.INSTANCE.insert(msgDTO);

    }

    public MsgDTO read(Long mno) throws RuntimeException{
        return MsgDAO.INSTANCE.select(mno);
    }


    //DAO호출, 호출 시 얼마나 걸리는지 log로 남기면 나중에 걸리는시간을 변경하는 튜닝이 가능해짐.
    public Map<String, List<MsgDTO>> getList(String user) throws RuntimeException{
        //고객이 원하는것을 가져오고 싶음, getList
        //예외는 잘 모르겠으면RuntimeException
        long start = System.currentTimeMillis();//시작하는 시간 찍기

        Map<String, List<MsgDTO>> result = MsgDAO.INSTANCE.selectList(user);//결과 가져오기

        long end = System.currentTimeMillis();//끝날 때 또 시간 찍기 ( sql문 시행할떄 얼마나 걸리는지 확인 가능)

        log.info("TIME:" + (end - start));

        return result;


    }


}
