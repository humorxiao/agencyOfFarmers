package scau.zxck.serviceImpl.market;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import scau.zxck.base.exception.BaseException;
import scau.zxck.dao.market.UserkeDao;


import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:config/spring/spring.xml"})

public class UserkeServiceTest {
  @Autowired
  private UserkeDao userkeDao;
  @Test
  public void findById() throws BaseException {
       System.out.println(userkeDao.findById("1").getUsername()
       );
  }
}
