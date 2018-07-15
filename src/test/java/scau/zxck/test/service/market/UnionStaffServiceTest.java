package scau.zxck.test.service.market;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.apache.shiro.session.Session;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Log4jConfigurer;

import com.alibaba.fastjson.JSON;

import scau.zxck.base.exception.BaseException;
import scau.zxck.dao.sys.AdminInfoDao;
import scau.zxck.entity.market.UnionStaff;
import scau.zxck.service.market.IUnionStaffService;
import test_mybatis.Student;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;


/**
 * Created by lishunpeng on 2015/11/13.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:config/spring/spring.xml"})
public class UnionStaffServiceTest {
  private static Logger logger = Logger.getLogger(UnionStaffServiceTest2.class);

  @Before
  public void before() {
    try {
      Log4jConfigurer.initLogging("classpath:config/log/log4j.properties");
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }

  @Autowired
  private IUnionStaffService unionStaffService;

  // @Autowired
  // private RoleDao roleDao;

  @Test
  public void navServiceTest() throws BaseException {
    List<UnionStaff> unionStaffList = unionStaffService.listUnionStaff();

    logger.info("===========================================");
    logger.info(JSON.toJSONString(unionStaffList));
    logger.info("===========================================");

  }
  public static void main(String[] args) {
    System.out.println("aaaaa");
  }
}
