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
import scau.zxck.entity.market.UserCollection;
import scau.zxck.utils.ToJSONString;
import scau.zxck.web.admin.CartInfoAction;
import scau.zxck.web.admin.CollectInfoAction;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

/** 
* CollectInfoAction Tester. 
* 
* @author <Authors name> 
* @since <pre>���� 21, 2018</pre> 
* @version 1.0 
*/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:config/spring/spring.xml","classpath:config/spring/web/spring-mvc.xml"})
@WebAppConfiguration
public class CollectInfoActionTest {


    @Autowired
    private CartInfoAction cartInfoAction;
    @Autowired
    private MockHttpServletRequest mockHttpServletRequest;
    @Autowired
    private MockHttpServletResponse mockHttpServletResponse;
    @Autowired
    private MockHttpSession mockHttpSession;
    @Autowired
    private CollectInfoAction collectInfoAction;
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
* Method: addCollection(String jsonStr) 
* 
*/ 
@Test
public void testAddCollection() throws Exception { 
//TODO: Test goes here...

    UserCollection userCollection = new UserCollection("100003","100003","2018-03-30 14:03:31","","","");
    String jsonStr = mapper.writeValueAsString(userCollection);
    System.out.println(jsonStr);
    jsonStr=ToJSONString.toJSON(jsonStr);
    System.out.println(jsonStr);
    mockHttpSession.setAttribute("User_PK","100003");
    mockMvc = standaloneSetup(collectInfoAction).build();
    String responseString = mockMvc.perform((post("/addCollect").session(mockHttpSession))
            .contentType(MediaType.APPLICATION_JSON).content(jsonStr)
    ).andExpect(status().isOk()).andDo(print()).andReturn().getResponse().getContentAsString();
    System.out.println(responseString);


} 

/** 
* 
* Method: removeCollect(String jsonStr) 
* 
*/ 
@Test
public void testRemoveCollect() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: getCollect(String jsonStr) 
* 
*/ 
@Test
public void testGetCollect() throws Exception { 
//TODO: Test goes here... 
} 


} 
