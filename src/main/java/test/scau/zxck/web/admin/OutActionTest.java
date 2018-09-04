package test.scau.zxck.web.admin;

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
import scau.zxck.web.admin.OutAction;
import scau.zxck.web.admin.PageAction;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

/**
 * OutAction Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>���� 10, 2018</pre>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:config/spring/spring.xml", "classpath:config/spring/web/spring-mvc.xml"})
public class OutActionTest {
  private MockMvc mockMvc;
  @Autowired
  private OutAction outAction;
  @Autowired
  private PageAction pageAction;
  @Autowired
  private MockHttpSession session;

  @Before
  public void before() throws Exception {
    mockMvc = standaloneSetup(pageAction).build();
  }

  @After
  public void after() throws Exception {
  }

  /**
   * Method: out()
   */
  @Test
  public void testOut() throws Exception {

    String jsonStr = "";
    String responseString = mockMvc.perform(post("/register")
      .contentType(MediaType.APPLICATION_JSON).content(jsonStr)
    ).andExpect(status().isOk()).andDo(print()).andReturn().getResponse().getContentAsString();
    System.out.println(responseString);
  }
}
