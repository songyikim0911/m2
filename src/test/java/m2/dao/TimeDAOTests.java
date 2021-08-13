package m2.dao;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.zerock.m2.dao.TimeDAO;


@Log4j2
public class TimeDAOTests {

  @Test
  public void testGetTime() {
    log.info("test get time..................");

    String timeStr = TimeDAO.INSTANCE.getTime();

    Assertions.assertNotNull(timeStr);

  }
}
