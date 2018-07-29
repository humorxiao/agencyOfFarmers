package scau.zxck.test.service.market;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

/**
 * Created by lishunpeng on 2015/11/13.
 */

class ParamObj extends Object {
  private  Boolean isAdmin;
  private  String admin_Password;
  private  String admin_Cell;
  private  String admin_Name;
  private  String admin_Email;
//  private int status;
  public Boolean getAdmin() {
    return isAdmin;
  }

  public void setAdmin(Boolean admin) {
    isAdmin = admin;
  }

  public String getAdmin_Password() {
    return admin_Password;
  }

  public void setAdmin_Password(String admin_Password) {
    this.admin_Password = admin_Password;
  }

  public String getAdmin_Cell() {
    return admin_Cell;
  }

  public void setAdmin_Cell(String admin_Cell) {
    this.admin_Cell = admin_Cell;
  }

  public String getAdmin_Name() {
    return admin_Name;
  }

  public void setAdmin_Name(String admin_Name) {
    this.admin_Name = admin_Name;
  }

  public String getAdmin_Email() {
    return admin_Email;
  }

  public void setAdmin_Email(String admin_Email) {
    this.admin_Email = admin_Email;
  }

  public ParamObj(Boolean isAdmin, String admin_Password, String admin_Cell, String admin_Name, String admin_Email/*,int status*/) {
    this.isAdmin = isAdmin;
    this.admin_Password = admin_Password;
    this.admin_Cell = admin_Cell;
    this.admin_Name = admin_Name;
    this.admin_Email = admin_Email;
//    this.status=status;
  }

//  public int getStatus() {
//    return status;
//  }

//  public void setStatus(int status) {
//    this.status = status;
//  }
}


@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:config/spring/spring.xml"})
public class UnionStaffServiceTest {
  // 记得配置log4j.properties ,的命令行输出水平是debug
  protected Log logger = LogFactory.getLog(UnionStaffServiceTest.class);


  @Autowired
  protected WebApplicationContext wac;
  protected MockMvc mockMvc;

  @Before() // 这个方法在每个方法执行之前都会执行一遍
  public void setup() {
    mockMvc = MockMvcBuilders.webAppContextSetup(wac).build(); // 初始化MockMvc对象
  }

  @org.junit.Test
  public void getAllCategoryTest() throws Exception {
    ParamObj paramObj=new ParamObj(true,"12345678","","1",""/*,200*/);
    String str=JSON.toJSON(paramObj).toString();
    System.out.println(str);
    String responseString = mockMvc.perform( post("/login").contentType(MediaType.APPLICATION_JSON).content(str)).andDo(print())
            .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
  }
}

//原版UnionStaffService
//原版UnionStaffService
//原版UnionStaffService
//原版UnionStaffService如下

//package scau.zxck.test.service.market;
//
//        import org.apache.ibatis.io.Resources;
//        import org.apache.ibatis.session.SqlSessionFactory;
//        import org.apache.log4j.Logger;
//        import org.apache.shiro.session.Session;
//        import org.junit.Before;
//        import org.junit.Test;
//        import org.junit.runner.RunWith;
//        import org.mybatis.spring.SqlSessionFactoryBean;
//        import org.springframework.beans.factory.annotation.Autowired;
//        import org.springframework.context.ApplicationContext;
//        import org.springframework.context.support.ClassPathXmlApplicationContext;
//        import org.springframework.test.context.ContextConfiguration;
//        import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//        import org.springframework.util.Log4jConfigurer;
//
//        import com.alibaba.fastjson.JSON;
//
//        import scau.zxck.base.exception.BaseException;
//        import scau.zxck.dao.sys.AdminInfoDao;
//        import scau.zxck.entity.market.UnionStaff;
//        import scau.zxck.service.market.IUnionStaffService;
//        import test_mybatis.Student;
//
//        import java.io.FileNotFoundException;
//        import java.io.IOException;
//        import java.io.InputStream;
//        import java.util.List;
//
//
///**
// * Created by lishunpeng on 2015/11/13.
// */
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {"classpath:config/spring/spring.xml"})
//public class UnionStaffServiceTest {
//  private static Logger logger = Logger.getLogger(UnionStaffServiceTest2.class);
//
//  @Before
//  public void before() {
//    try {
//      Log4jConfigurer.initLogging("classpath:config/log/log4j.properties");
//    } catch (FileNotFoundException e) {
//      e.printStackTrace();
//    }
//  }
//
//  @Autowired
//  private IUnionStaffService unionStaffService;
//
//  // @Autowired
//  // private RoleDao roleDao;
//
//  @Test
//  public void navServiceTest() throws BaseException {
//    List<UnionStaff> unionStaffList = unionStaffService.listUnionStaff();
//
//    logger.info("===========================================");
//    logger.info(JSON.toJSONString(unionStaffList));
//    logger.info("===========================================");
//
//  }
//  public static void main(String[] args) {
//    System.out.println("aaaaa");
//  }
//}
