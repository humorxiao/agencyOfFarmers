package test.scau.zxck.web.admin; 

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import scau.zxck.entity.market.OrderInfo;
import scau.zxck.utils.ToJSONString;
import scau.zxck.web.admin.CartInfoAction;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

/** 
* RecentlyPurchaseAction Tester. 
* 
* @author <Authors name> 
* @since <pre>���� 23, 2018</pre> 
* @version 1.0 
*/
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:config/spring/spring.xml","classpath:config/spring/web/spring-mvc.xml"})
public class RecentlyPurchaseActionTest {

    @Autowired
    private CartInfoAction cartInfoAction;

    @Autowired
    private MockHttpServletRequest mockHttpServletRequest;
    @Autowired
    private MockHttpServletResponse mockHttpServletResponse;
    @Autowired
    private MockHttpSession mockHttpSession;

    private MockMvc mockMvc;
    private static ObjectMapper mapper=new ObjectMapper();
@Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: recentlyPurchase(String jsonStr) 
* 
*/ 
@Test
public void testRecentlyPurchase() throws Exception { 
//TODO: Test goes here...

  // UserInfo userInfo = new UserInfo("12345678", "林天真", "13421166393", "123@qq.com", 2, "2017-03-30 14:03:03", "林天真", "445202199412019022", "0", "", "", "");
     OrderInfo orderInfo = new OrderInfo("100003");
    String jsonStr = mapper.writeValueAsString(orderInfo);
    System.out.println(jsonStr);
    jsonStr=ToJSONString.toJSON(jsonStr);
    System.out.println(jsonStr);
    mockHttpSession.setAttribute("User_PK","100003");
    mockMvc = standaloneSetup(cartInfoAction).build();
    String responseString = mockMvc.perform((post("/recentlyPurchase").session(mockHttpSession))
            .contentType(MediaType.APPLICATION_JSON).content(jsonStr)
    ).andExpect(status().isOk()).andDo(print()).andReturn().getResponse().getContentAsString();
    System.out.println(responseString);
} 


} 
