package scau.zxck.test.service.market;

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
  private static Boolean isAdmin=true;
  private static String admin_Password="12345678";
  private static String admin_Cell="";
  private static String admin_Name="1";
  private static String admin_Email="";

//  public ParamObj() {
//    Boolean isAdmin = true;
//    String admin_Password = "12345678";
//    String admin_Cell = "";
//    String admin_Name = "1";
//    String admin_Email = "";
//  }
//
//  public static Boolean getIsAdmin() {
//    return isAdmin;
//  }
//
//  public static void setIsAdmin(Boolean isAdmin) {
//    ParamObj.isAdmin = isAdmin;
//  }
//
//  public static String getAdmin_Password() {
//    return admin_Password;
//  }
//
//  public static void setAdmin_Password(String admin_Password) {
//    ParamObj.admin_Password = admin_Password;
//  }
//
//  public static String getAdmin_Cell() {
//    return admin_Cell;
//  }
//
//  public static void setAdmin_Cell(String admin_Cell) {
//    ParamObj.admin_Cell = admin_Cell;
//  }
//
//  public static String getAdmin_Name() {
//    return admin_Name;
//  }
//
//  public static void setAdmin_Name(String admin_Name) {
//    ParamObj.admin_Name = admin_Name;
//  }
//
//  public static String getAdmin_Email() {
//    return admin_Email;
//  }
//
//  public static void setAdmin_Email(String admin_Email) {
//    ParamObj.admin_Email = admin_Email;
//  }
}


@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration(value = "src/main/webapp")
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
//    String jsonStr =
//        "{\"isAdmin\":true,\"Admin_Password\":\"12345678\",\"Admin_Cell\":\"\",\"Admin_Name\":\"1\",\"Admin_Email\":\"\"}";
//    MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/loginx")).andReturn();
//    MockHttpServletRequest request = mvcResult.getRequest();
//    System.out.println(request==null);
//    System.out.println(request.getAttribute("yhx")==null);
//    String s=request.getAttribute("yhx").toString();
//    System.out.println(s);
    ParamObj paramObj=new ParamObj();
    String str=JSONObject.toJSONString(paramObj);
    System.out.println(str);
  }
}
