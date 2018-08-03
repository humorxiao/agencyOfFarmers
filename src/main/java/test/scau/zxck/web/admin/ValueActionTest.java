package test.scau.zxck.web.admin; 

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import scau.zxck.web.admin.ValueAction;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
/** 
* ValueAction Tester. 
* 
* @author <Authors name> 
* @since <pre>���� 2, 2018</pre> 
* @version 1.0 
*/
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:config/spring/spring.xml","classpath:config/spring/web/spring-mvc.xml"})
public class ValueActionTest { 
@Autowired
private ValueAction valueAction;
@Autowired
private MockHttpSession session;
@Autowired
private MockHttpServletRequest request;
private ObjectMapper mapper=new ObjectMapper();
private MockMvc mockMvc;
@Before
public void before() throws Exception {
    mockMvc=standaloneSetup(valueAction).build();
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: addValue(HttpServletResponse response) 
* 
*/ 
@Test
public void testAddValue() throws Exception { 
//TODO: Test goes here...
    String jsonStr;
    session.setAttribute("loginUser","1");
    jsonStr="{\"value\":\"1\",\"typeid\":\"1\",\"nodeid\":\"1\",\"recordingtime\":\"2018-08-02 20:29:21\",\"note\":\"1\"}";
    String responseString=mockMvc.perform(post("/addValue")
            .contentType(MediaType.APPLICATION_JSON).content(jsonStr)
            .session(session)
    ).andExpect(status().isOk()).andDo(print()).andReturn()
            .getResponse().getContentAsString();
    System.out.println(responseString);
} 

/** 
* 
* Method: allValues(HttpServletResponse response) 
* 
*/ 
@Test
public void testAllValues() throws Exception { 
//TODO: Test goes here...
    String responseString=mockMvc.perform(post("/allValues")
//            .contentType(MediaType.APPLICATION_JSON).content(jsonStr)
//            .session(session)
    ).andExpect(status().isOk()).andDo(print()).andReturn()
            .getResponse().getContentAsString();
    System.out.println(responseString);
} 

/** 
* 
* Method: deleteValueItem(HttpServletResponse response) 
* 
*/ 
@Test
public void testDeleteValueItem() throws Exception { 
//TODO: Test goes here...
    String jsonStr;
    session.setAttribute("loginUser","101");
    jsonStr="{\"value\":\"1\",\"typeid\":\"1\",\"nodeid\":\"1\",\"recordingtime\":\"2018-08-02 20:29:21\",\"note\":\"1\"}";
    String responseString=mockMvc.perform(post("/addValue")
            .contentType(MediaType.APPLICATION_JSON).content(jsonStr)
            .session(session)
    ).andExpect(status().isOk()).andDo(print()).andReturn()
            .getResponse().getContentAsString();
    System.out.println(responseString);
} 

/** 
* 
* Method: getMyValues(HttpServletResponse response) 
* 
*/ 
@Test
public void testGetMyValues() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: getValueItem(HttpServletResponse response) 
* 
*/ 
@Test
public void testGetValueItem() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: searchAllValuesByType(HttpServletResponse response) 
* 
*/ 
@Test
public void testSearchAllValuesByType() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: searchValues(HttpServletResponse response) 
* 
*/ 
@Test
public void testSearchValues() throws Exception { 
//TODO: Test goes here... 
} 


} 
