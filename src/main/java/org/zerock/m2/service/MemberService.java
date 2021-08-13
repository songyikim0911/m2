package org.zerock.m2.service;

import org.zerock.m2.dao.MemberDAO;
import org.zerock.m2.dto.MemberDTO;

public enum MemberService {

    INSTANCE;

    public MemberDTO login(String mid, String mpw) throws RuntimeException{
        return MemberDAO.INSTANCE.selectForLogin(mid,mpw);
    }
}
