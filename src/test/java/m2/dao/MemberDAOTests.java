package m2.dao;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.zerock.m2.dao.MemberDAO;
import org.zerock.m2.dto.MemberDTO;

@Log4j2
public class MemberDAOTests {

    @Test
    public void testLogin(){
        String mid = "user1";
        String mpw = "user1";

        MemberDTO dto = MemberDAO.INSTANCE.selectForLogin(mid,mpw);

        log.info(dto);

    }
}
