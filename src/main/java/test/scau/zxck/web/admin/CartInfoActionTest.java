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
import scau.zxck.entity.market.CartInfo;
import scau.zxck.utils.ToJSONStringUtil;
import scau.zxck.web.admin.CartInfoAction;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

/** 
* CartInfoAction Tester. 
* 
* @author <Authors name> 
* @since <pre>���� 20, 2018</pre> 
* @version 1.0 
*/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:config/spring/spring.xml","classpath:config/spring/web/spring-mvc.xml"})
@WebAppConfiguration
public class CartInfoActionTest {
    @Autowired
    private CartInfoAction cartInfoAction;
    @Autowired
    private MockHttpServletRequest mockHttpServletRequest;
    @Autowired
    private MockHttpServletResponse mockHttpServletResponse;
    @Autowired
    private MockHttpSession mockHttpSession;
    private ObjectMapper mapper=new ObjectMapper();
    private MockMvc mockMvc;


@Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: getCartAction(String jsonStr) 
* 
*/ 
@Test
public void testGetCartAction() throws Exception { 
//TODO: Test goes here... 
} 
@Test
public void testGetCart() throws Exception {
    CartInfo cartInfo=new CartInfo("100006","1","","","");
    String jsonStr=mapper.writeValueAsString(cartInfo);
    System.out.println(jsonStr);
    jsonStr=ToJSONStringUtil.toJSON(jsonStr);
    System.out.println(jsonStr);
//    jsonStr="{\"User_PK\":100003,\"Goods_List\":\"100006\",\"Goods_Num\":\"1\",\"Cart_Reserve_1\":\"\",\"Cart_Reserve_2\":\"\",\"Remark\":\"\"}";
    mockHttpSession.setAttribute("User_PK","100003");
    mockMvc=standaloneSetup(cartInfoAction).build();
    String responseString = mockMvc.perform((post("/getCart").session(mockHttpSession))
            .contentType(MediaType.APPLICATION_JSON).content(jsonStr)
    ).andExpect(status().isOk()).andDo(print()).andReturn().getResponse().getContentAsString();
    System.out.println(responseString);
}
    @Test
    public void testAlertCart() throws Exception {

    }

} 
