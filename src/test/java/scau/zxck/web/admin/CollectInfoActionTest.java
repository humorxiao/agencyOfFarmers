package scau.zxck.web.admin;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import scau.zxck.entity.market.UserCollection;
import scau.zxck.utils.ToJSONString;

import javax.servlet.http.HttpServletRequest;

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
    private MockMvc mockMvc;
    private ObjectMapper mapper = new ObjectMapper();
    @Autowired
    private CollectInfoAction collectInfoAction;
    @Autowired
    private MockHttpSession mockHttpSession;


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
    mockMvc = standaloneSetup(collectInfoAction).build();
    UserCollection userCollection = new UserCollection("100003","100016","2018-07-21 10:17:56");
    String jsonStr  = mapper.writeValueAsString(userCollection);
    System.out.println(jsonStr);
    jsonStr = ToJSONString.toJSON(jsonStr);
    System.out.println(jsonStr);
    mockHttpSession.setAttribute("User_PK","100003");
//    mockHttpSession.setAttribute("Goods_PK","100018");
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
    mockMvc = standaloneSetup(collectInfoAction).build();
    UserCollection userCollection = new UserCollection("f3da90055b014586a022373b95c57003","100003");
    String jsonStr  = mapper.writeValueAsString(userCollection);
    System.out.println(jsonStr);
    jsonStr = ToJSONString.toJSON(jsonStr);
    System.out.println(jsonStr);
    mockHttpSession.setAttribute("User_PK","100003");
//    mockHttpSession.setAttribute("Goods_PK","100018");
    String responseString = mockMvc.perform((post("/removeCollect").session(mockHttpSession))
            .contentType(MediaType.APPLICATION_JSON).content(jsonStr)
    ).andExpect(status().isOk()).andDo(print()).andReturn().getResponse().getContentAsString();
    System.out.println(responseString);
} 

/** 
* 
* Method: getCollect(String jsonStr) 
* 
*/ 
@Test
public void testGetCollect() throws Exception { 
//TODO: Test goes here...
    mockMvc = standaloneSetup(collectInfoAction).build();
    UserCollection userCollection = new UserCollection("100003");
    String jsonStr  = mapper.writeValueAsString(userCollection);
    System.out.println(jsonStr);
    jsonStr = ToJSONString.toJSON(jsonStr);
    System.out.println(jsonStr);
    mockHttpSession.setAttribute("User_PK","100003");
//    mockHttpSession.setAttribute("Goods_PK","100018");
    String responseString = mockMvc.perform((post("/getCollect").session(mockHttpSession))
            .contentType(MediaType.APPLICATION_JSON).content(jsonStr)
    ).andExpect(status().isOk()).andDo(print()).andReturn().getResponse().getContentAsString();
    System.out.println(responseString);
} 


} 
