package scau.zxck.test.service.market;

<<<<<<< Updated upstream
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
=======
import com.alibaba.fastjson.JSON;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
>>>>>>> Stashed changes
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
import scau.zxck.dao.market.CartInfoDao;
import scau.zxck.dao.sys.AdminInfoDao;
import scau.zxck.entity.market.CartInfo;
import scau.zxck.entity.market.UnionStaff;
import scau.zxck.entity.sys.AdminInfo;
import scau.zxck.service.market.IUnionStaffService;
import scau.zxck.serviceImpl.market.UnionStaffService;
import sun.tools.java.ClassPath;
import test_mybatis.Student;

import javax.xml.stream.Location;

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

  public void test() {
    String s = this.getClass().getResource("/mybatis_config.xml").getFile().toString();
    System.out.println(s);
  }

<<<<<<< Updated upstream
  public static void main(String[] args) throws IOException {
    // 根据 mybatis-config.xml 配置的信息得到 sqlSessionFactory
//    String resource = UnionStaffServiceTest.class.getClass().getResource("/mybatis_config.xml")
//        .getFile().toString();
    InputStream inputStream =
        Resources.getResourceAsStream("mybatis_config.xml");
    SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    // 然后根据 sqlSessionFactory 得到 session
    SqlSession session = sqlSessionFactory.openSession();
    // 最后通过 session 的 selectList() 方法调用 sql 语句 listStudent
    List<Student> listStudent = session.selectList("listStudent");
    for (Student student : listStudent) {
      System.out.println("ID:" + student.getId() + ",NAME:" + student.getName());
    }
  }
=======
    public static void main(String[] args) throws Exception {
        AdminInfo adminInfo=new AdminInfo();
        adminInfo.setAdmin_cell("12345");
        adminInfo.setAdmin_email("1769969562@qq.com");
        adminInfo.setAdmin_name("yhx");
        adminInfo.setAdmin_password("123456");
        adminInfo.setRemark("aaaaa");
        AdminInfoDao adminInfoDao=new AdminInfoDao();
        adminInfoDao.add(adminInfo);
    }

>>>>>>> Stashed changes
}
