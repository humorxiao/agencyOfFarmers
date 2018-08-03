package scau.zxck.web.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.ibatis.jdbc.Null;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import scau.zxck.entity.market.GoodsInfo;
import scau.zxck.utils.ToJSONString;
import scau.zxck.web.admin.GoodsInfoAction;
import scau.zxck.web.admin.LoginAction;

import javax.servlet.http.HttpServletRequest;

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
public class GoodsInfoActionTest {
    private MockMvc mockMvc;
    private ObjectMapper mapper = new ObjectMapper();
    @Autowired
    private LoginAction loginAction;
    @Autowired
    private HttpServletRequest  request;


@Before
public void before() throws Exception {
}

@After
public void after() throws Exception {
}

/**
*
* Method: getOneGood(String jsonStr)
*
*/
@Test
public void testGetOneGood() throws Exception {
////TODO: Test goes here...
//    mockMvc = standaloneSetup(loginAction).build();
//
////   goodsInfo.setId("100001");
//
//    String jsonStr = new String("{ \"isAdmin\": \"true\", \"Admin_Password\": \"25d55ad283aa400af464c76d713c07ad\", \"Admin_Cell\": \"\", \"Admin_Name\": \"1\", \"Admin_Email\": \"\" }");
//
//    String responseString = mockMvc.perform((post("/login"))
//            .contentType(MediaType.APPLICATION_JSON).content(jsonStr)
//    ).andExpect(status().isOk()).andDo(print()).andReturn().getResponse().getContentAsString();
//    System.out.println(responseString);
}

/**
*
* Method: getAllTypesGoods(String jsonStr)
*
*/
@Test
public void testGetAllTypesGoods() throws Exception {
//TODO: Test goes here...
//    mockMvc = standaloneSetup(goodsInfoAction).build();
//    GoodsInfo goodsInfo = new GoodsInfo();
//    goodsInfo.setId("100001");
//    String jsonStr = mapper.writeValueAsString(goodsInfo);
//    System.out.println(jsonStr);
//    jsonStr = ToJSONString.toJSON(jsonStr);
//    System.out.println(jsonStr);
//    String responseString = mockMvc.perform((post("/getOneGood"))
//            .contentType(MediaType.APPLICATION_JSON).content(jsonStr)
//    ).andExpect(status().isOk()).andDo(print()).andReturn().getResponse().getContentAsString();
//    System.out.println(responseString);
    System.out.println("success");
}

/**
*
* Method: getTypeGoods(String jsonStr)
*
*/
////@Test
//public void testGetTypeGoods() throws Exception {
////TODO: Test goes here...
//    mockMvc = standaloneSetup(goodsInfoAction).build();
//    GoodsInfo goodsInfo = new GoodsInfo(6);
//    String jsonStr = mapper.writeValueAsString(goodsInfo);
//    System.out.println(jsonStr);
//    jsonStr = ToJSONString.toJSON(jsonStr);
//    System.out.println(jsonStr);
//    String responseString = mockMvc.perform((post("/getTypeGoods"))
//            .contentType(MediaType.APPLICATION_JSON).content(jsonStr)
//    ).andExpect(status().isOk()).andDo(print()).andReturn().getResponse().getContentAsString();
//    System.out.println(responseString);
//}

/**
*
* Method: getSpecialGoods(String jsonStr)
*
*/
//@Test
//public void testGetSpecialGoods() throws Exception {
////TODO: Test goes here...
//    mockMvc = standaloneSetup(goodsInfoAction).build();
//    GoodsInfo goodsInfo = new GoodsInfo('0');
//    String jsonStr = mapper.writeValueAsString(goodsInfo);
//    System.out.println(jsonStr);
//    jsonStr = ToJSONString.toJSON(jsonStr);
//    System.out.println(jsonStr);
//    String responseString = mockMvc.perform((post("/getSpecialGoods"))
//            .contentType(MediaType.APPLICATION_JSON).content(jsonStr)
//    ).andExpect(status().isOk()).andDo(print()).andReturn().getResponse().getContentAsString();
//    System.out.println(responseString);
//}

/**
*
* Method: getDiscountGoods(String jsonStr)
*
*/
//@Test
//public void testGetDiscountGoods() throws Exception {
////TODO: Test goes here...
//    mockMvc = standaloneSetup(goodsInfoAction).build();
//    GoodsInfo goodsInfo = new GoodsInfo('2');
//    String jsonStr = mapper.writeValueAsString(goodsInfo);
//    System.out.println(jsonStr);
//    jsonStr = ToJSONString.toJSON(jsonStr);
//    System.out.println(jsonStr);
//    String responseString = mockMvc.perform((post("/getDiscountGoods"))
//            .contentType(MediaType.APPLICATION_JSON).content(jsonStr)
//    ).andExpect(status().isOk()).andDo(print()).andReturn().getResponse().getContentAsString();
//    System.out.println(responseString);
//}

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
