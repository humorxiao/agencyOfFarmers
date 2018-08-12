package test.scau.zxck.web.admin;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.mock.web.portlet.MockActionRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import scau.zxck.web.admin.RegisterAction;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
/**
* RegisterAction Tester.
*
* @author <Authors name>
* @since <pre>���� 9, 2018</pre>
* @version 1.0
*/
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:config/spring/spring.xml","classpath:config/spring/web/spring-mvc.xml"})
public class RegisterActionTest {
@Autowired
private RegisterAction registerAction;
@Autowired
private MockHttpServletRequest request;
@Autowired
private MockHttpSession session;
private MockMvc mockMvc;
@Before
public void before() throws Exception {
  mockMvc=standaloneSetup(registerAction).build();
}

@After
public void after() throws Exception {
}

/**
*
* Method: register(HttpServletResponse response)
*
*/
@Test
public void testRegister() throws Exception {
//TODO: Test goes here...
  String jsonStr="{\n" +
    "          \"User_ID\": \"445121199801134629\",\n" +
    "          \"User_Password\": \"11111111\",\n" +
    "          \"User_Name\": \"大霞宝\",\n" +
    "          \"User_Email\": \"905594203@qq.com\",\n" +
    "          \"User_RegTime\": \"2018-8-9 21:13:50\",\n" +
    "          \"User_Cell\":\"15813359528\",\n" +
    "          \"User_Sex\":\"2\",\n" +
    "          \"User_Realname\": \"莫晓霞\"\n" +
    "        }";
  System.out.println(jsonStr);
  String responseString=mockMvc.perform(post("/register")
    .contentType(MediaType.APPLICATION_JSON).content(jsonStr)
  ).andExpect(status().isOk()).andDo(print()).andReturn().getResponse().getContentAsString();
  System.out.println(responseString);
}

/**
*
* Method: validateAccount(HttpServletResponse response)
*
*/
@Test
public void testValidateAccount() throws Exception {
//TODO: Test goes here...
}


}
