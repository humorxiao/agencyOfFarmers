package test.scau.zxck.web.admin;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import scau.zxck.web.admin.OutAction;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
/**
* OutAction Tester.
*
* @author <Authors name>
* @since <pre>���� 10, 2018</pre>
* @version 1.0
*/
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:config/spring/spring.xml","classpath:config/spring/web/spring-mvc.xml"})
public class OutActionTest {
private MockMvc mockMvc;
@Autowired
private OutAction outAction;
@Autowired
private MockHttpSession session;
@Before
public void before() throws Exception {
  mockMvc=standaloneSetup(outAction).build();
}

@After
public void after() throws Exception {
}

/**
*
* Method: out()
*
*/
@Test
public void testOut() throws Exception {
//TODO: Test goes here...
  session.setAttribute("isAdmin",true);
  session.setAttribute("Admin_PK",1);
  String s=mockMvc.perform(post("/out").session(session))
    .andExpect(status().isOk()).andDo(print()).andReturn()
    .getResponse().getContentAsString();
  System.out.println(s);
}


}
