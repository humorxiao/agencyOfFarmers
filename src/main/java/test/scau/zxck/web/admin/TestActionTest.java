package test.scau.zxck.web.admin;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import scau.zxck.web.admin.GoodsInfoAction;
import scau.zxck.web.admin.TestAction;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:config/spring/spring.xml",
    "classpath:config/spring/web/spring-mvc.xml"})
@WebAppConfiguration
public class TestActionTest {
  @Autowired
  private MockHttpServletRequest request;
  @Autowired
  private MockHttpSession session;
  private MockMvc mockMvc;
  @Autowired
  private TestAction testAction;
  @Autowired
  private GoodsInfoAction goodsInfoAction;
  @Before
  public void before() throws Exception {}

  @After
  public void after() throws Exception {}

  @Test
  public void testS() throws Exception {
    mockMvc = standaloneSetup(testAction).build();
    session.setAttribute("yhx", "666777888999");
    session.setAttribute("yyy", "111222333");
    session.setAttribute("yjl", "666");
    session.setAttribute("yjm", "666666666");
    String s = mockMvc.perform(post("/test").session(session)).andExpect(view().name("success"))
        .andDo(print()).andReturn().getResponse().getContentAsString();
    System.out.println(s);
  }
  @Test
  public void testA() throws Exception{
    mockMvc=standaloneSetup(goodsInfoAction).build();
    String s = mockMvc.perform(post("/recentlyPerchase").session(session)).andExpect(view().name("success"))
            .andDo(print()).andReturn().getResponse().getContentAsString();
    System.out.println(s);
  }

}
