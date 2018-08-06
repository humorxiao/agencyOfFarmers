//package test.scau.zxck.serviceImpl.market;
//
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//import org.junit.Test;
//import org.junit.Before;
//import org.junit.After;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.transaction.TransactionConfiguration;
//import org.springframework.test.context.web.WebAppConfiguration;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.web.context.WebApplicationContext;
//import scau.zxck.web.admin.LoginAction;
//import scau.zxck.web.admin.TestAction;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
//import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;
//
///**
// * LoginService Tester.
// *
// * @author <Authors name>
// * @since
// *
// *        <pre>
// * ���� 19, 2018
// *        </pre>
// *
// * @version 1.0
// */
//@RunWith(SpringJUnit4ClassRunner.class)
//@WebAppConfiguration
//@ContextConfiguration({"classpath:config/spring/spring.xml"})
//public class LoginServiceTest {
////  protected Log logger = LogFactory.getLog(LoginAction.class);
////  @Autowired
////  private WebApplicationContext wac;
////  private MockMvc mockMvc;
//
//  @Before
//  public void before() throws Exception {
////    this.mockMvc = webAppContextSetup(this.wac).build();
//  }
//
//  @After
//  public void after() throws Exception {
//
//  }
//
//  /**
//   *
//   * Method: findOne(String id)
//   *
//   */
//  @Test
//  public void testFindOne() throws Exception {
//    // TODO: Test goes here...
////    LoginAction loginAction = new LoginAction();
////    TestAction testAction=new TestAction();
//    LoginAction loginAction = new LoginAction();
//    MockMvc mockMvc = standaloneSetup(loginAction).build();
//    String jsonStr =
//            "{\"isAdmin\":false,\"User_Password\":\"12345678\",\"User_Cell\":\"18814167467\",\"User_Name\":\"林莹莹\",\"User_Email\":\"1624471560@qq.com\"}";
//    String responseString = mockMvc.perform((post("/login"))
//            .contentType(MediaType.APPLICATION_JSON).content(jsonStr)
//    ).andExpect(status().isOk()).andDo(print()).andReturn().getResponse().getContentAsString();
//    System.out.println(responseString);
//  }
//
//
//}

package test.scau.zxck.serviceImpl.market;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import scau.zxck.entity.market.SystemUserLog;
import scau.zxck.entity.sys.SystemUserInfo;
import scau.zxck.service.market.ISystemLogService;
import scau.zxck.service.sys.ISystemUserService;
import scau.zxck.utils.ToJSONString;
import scau.zxck.web.admin.*;
import scau.zxck.web.test.SystemUserInfotest;
import scau.zxck.web.test.UserInfoTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

/**
 * LoginService Tester.
 *
 * @author <Authors name>
 * @since
 *
 *        <pre>
 * ���� 19, 2018
 *        </pre>
 *
 * @version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:config/spring/spring.xml","classpath:config/spring/web/spring-mvc.xml"})
@WebAppConfiguration
public class LoginServiceTest {
  //  protected Log logger = LogFactory.getLog(LoginAction.class);
  @Autowired
  private WebApplicationContext wac;
  @Autowired
  private CheckLoginUserAction loginAction;
  @Autowired
  private ISystemUserService systemUserService;
  @Autowired


  private TestAction testAction;
  @Autowired
  private MockHttpSession session;
  @Autowired
  private ISystemLogService systemLogService;
  private MockMvc mockMvc;
  private static ObjectMapper mapper=new ObjectMapper();
  @Before
  public void before() throws Exception {
//    mockMvc = standaloneSetup(testAction).build();
//    mockMvc=MockMvcBuilders.webAppContextSetup(wac).build();
  }

  @After
  public void after() throws Exception {

  }

  /**
   *
   * Method: findOne(String id)
   *
   *
   */
  @Test
  public void testFindOne() throws Exception {
    // TODO: Test goes here...
    mockMvc = standaloneSetup(loginAction).build();
    String jsonStr;
//            ="{\"isAdmin\":true,\"Admin_Password\":\"12345678\",\"Admin_Cell\":\"\",\"Admin_Name\":\"1\",\"Admin_Email\":\"\"}";
    SystemUserInfotest   systemUserInfotest=new SystemUserInfotest("100","25d55ad283aa400af464c76d713c07ad","陈永铭","");
         jsonStr=mapper.writeValueAsString(systemUserInfotest);
String ke =mapper.writeValueAsString(systemUserService.findById("100"));
    System.out.println(jsonStr);
    jsonStr=ToJSONString.toJSON(jsonStr);
    System.out.println(jsonStr);
    ke=ToJSONString.toJSON(ke);
    System.out.println(ke);
//        "{\"isAdmin\":false,\"User_Password\":\"12345678\",\"User_Cell\":\"18814167467\",\"User_Name\":\"林莹莹\",\"User_Email\":\"1624471560@qq.com\"}";
//    mockMvc = standaloneSetup(testAction).build();
    SystemUserLog log=systemLogService.findById("7d0bd45b30ff4deab86ba8082821d3ae");
          session.setAttribute("loginfo",log);
          session.setAttribute("loginUser",systemUserInfotest);
    String responseString = mockMvc.perform((post("/checkLoginUser"))
            .contentType(MediaType.APPLICATION_JSON).content(jsonStr).session(session)
    ).andExpect(status().isOk()).andDo(print()).andReturn().getResponse().getContentAsString();
    System.out.println(responseString);
  }


}
