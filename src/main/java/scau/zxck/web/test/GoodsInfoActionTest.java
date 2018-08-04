package scau.zxck.web.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.ibatis.jdbc.Null;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.test.context.transaction.TransactionConfiguration;
import scau.zxck.entity.market.GoodsInfo;
import scau.zxck.entity.sys.SystemUserInfo;
import scau.zxck.utils.ToJSONString;
import scau.zxck.web.admin.GoodsInfoAction;
import scau.zxck.web.admin.LoginAction;
import scau.zxck.web.admin.*;
import scau.zxck.entity.market.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.sql.Date;
import java.text.SimpleDateFormat;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

/**
* GoodsInfoAction Tester.
*
* @author <Authors name>
* @since <pre>���� 20, 2018</pre>
* @version 1.0
*/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:config/spring/spring.xml","classpath:config/spring/web/spring-mvc.xml"})
@WebAppConfiguration

@TransactionConfiguration(defaultRollback = false)
@Transactional

public class GoodsInfoActionTest {
    private MockMvc mockMvc;
    private ObjectMapper mapper = new ObjectMapper();
    @Autowired
    private LoginAndRegisterAction loginAndRegisterAction;
    @Autowired
    private ValueAction valueAction;
    @Autowired
    private HttpServletRequest  request;
  @Autowired
  private MockHttpSession session;

  private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
@Before
public void before() throws Exception {
}

@After
public void after() throws Exception {
}

@Test
public void testGetAllTypesGoods() throws Exception {
//TODO: Test goes here...
      SystemUserInfo loginUser=new SystemUserInfo();
      loginUser.setId("100");
      session.setAttribute("loginUser",loginUser);
      String date=simpleDateFormat.format(new java.util.Date());
      mockMvc = standaloneSetup(valueAction).build();
      String jsonStr1="{\"value\":\"1\",\"typeid\":\"1\",\"nodeid\":\"123\",\"recordingtime\":\""+date+"\",\"note\":\"hello world\"}";
      String responseString1= mockMvc.perform((post("/addValue"))
      .contentType(MediaType.APPLICATION_JSON).content(jsonStr1).session(session)
    ).andExpect(status().isOk()).andDo(print()).andReturn().getResponse().getContentAsString();
  System.out.println(responseString1);
}
@Test
  public void test() throws Exception {
  SystemUserInfo loginUser=new SystemUserInfo();
  loginUser.setId("100");
  session.setAttribute("loginUser",loginUser);
 String str="{\"rows\":\"1\",\"page\":\"1\",\"starttime\":\"2018-08-04 21:30:43\",\"endtime\":\"2018-08-04 21:48:18\",\"typeid\":\"1\",\"nodeid\":\"123\"}";
  // String str="{\"itemid\":\"62ab03b6b5a945d69ff2752bb4f15b25\"}";
 // String str="{\"rows\":\"3\",\"page\":\"1\",\"typeid\":\"1\",\"nodeid\":\"123\"}";
    mockMvc = standaloneSetup(valueAction).build();
   String responseString1= mockMvc.perform((post("/searchValues")).contentType(MediaType.APPLICATION_JSON).content(str).session(session)
    ).andExpect(status().isOk()).andDo(print()).andReturn().getResponse().getContentAsString();
    System.out.println(responseString1);
  }

/**
*
* Method: getAllGoods(String jsonStr)
*
*/
@Test
public void testGetAllGoods() throws Exception {
//TODO: Test goes here...
}

/**
*
* Method: addGoods(String jsonStr)
*
*/
@Test
public void testAddGoods() throws Exception {
//TODO: Test goes here...
}

/**
*
* Method: updateGoodsInfo(String jsonStr)
*
*/
@Test
public void testUpdateGoodsInfo() throws Exception {
//TODO: Test goes here...
}

/**
*
* Method: deleteGoodsInfo(String jsonStr)
*
*/
@Test
public void testDeleteGoodsInfo() throws Exception {
//TODO: Test goes here...
}

/**
*
* Method: getLikesAction(String jsonStr)
*
*/

//getLikesGoods方法没有进行测试 属性不明
}
