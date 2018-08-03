package test.scau.zxck.serviceImpl.market;

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
import org.springframework.web.context.WebApplicationContext;
import scau.zxck.utils.ToJSONString;
import scau.zxck.web.admin.LoginAction;
import scau.zxck.web.admin.TestAction;
import scau.zxck.web.test.UserInfoTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:config/spring/spring.xml",
    "classpath:config/spring/web/spring-mvc.xml"})
@WebAppConfiguration
public class LoginServiceTest {
  private static ObjectMapper mapper = new ObjectMapper();
  @Autowired
  private WebApplicationContext wac;
  @Autowired
  private LoginAction loginAction;
  @Autowired
  private TestAction testAction;
  private MockMvc mockMvc;
  @Autowired
  private MockHttpSession session;
  @Autowired
  private MockHttpServletRequest request;

  @Before
  public void before() throws Exception {}

  @After
  public void after() throws Exception {

  }

  @Test
  public void testFindOne() throws Exception {
    mockMvc = standaloneSetup(loginAction).build();
    String jsonStr;
    UserInfoTest userInfoTest = new UserInfoTest("true", "12345678", "", "1", "");
    jsonStr = mapper.writeValueAsString(userInfoTest);
    System.out.println(jsonStr);
    jsonStr = ToJSONString.toJSON(jsonStr);
    System.out.println(jsonStr);
    String responseString = mockMvc
        .perform((post("/login")).session(session).contentType(MediaType.APPLICATION_JSON)
            .content(jsonStr))
        .andExpect(status().isOk()).andDo(print()).andReturn().getResponse().getContentAsString();
    System.out.println(responseString);
  }
}
