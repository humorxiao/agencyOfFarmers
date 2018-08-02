//package scau.zxck.web.admin;
//
//import org.junit.Test;
//import org.junit.Before;
//import org.junit.After;
//
///**
//* TestAction Tester.
//*
//* @author <Authors name>
//* @since <pre>���� 19, 2018</pre>
//* @version 1.0
//*/
//public class TestActionTest {
//
//@Before
//public void before() throws Exception {
//}
//
//@After
//public void after() throws Exception {
//}
//
///**
//*
//* Method: s(String string)
//*
//*/
//@Test
//public void testS() throws Exception {
////TODO: Test goes here...
//}
//
//
//}
//TestActionTest
package test.scau.zxck.serviceImpl.market;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import scau.zxck.web.admin.TestAction;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

/**
 * LoginService Tester.
 *
 * @author <Authors name>
 * @since
 *
 *        <pre>
 * ���� 19, 2018
 *        </pre>
 *
 * @version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({"classpath:config/spring/spring.xml"})
public class TestActionTest {
//  protected Log logger = LogFactory.getLog(LoginAction.class);
//  @Autowired
//  private WebApplicationContext wac;
//  private MockMvc mockMvc;

    @Before
    public void before() throws Exception {
//    this.mockMvc = webAppContextSetup(this.wac).build();
    }

    @After
    public void after() throws Exception {

    }

    /**
     *
     * Method: findOne(String id)
     *
     */
    @Test
    public void testFindOne() throws Exception {
        // TODO: Test goes here...
//    LoginAction loginAction = new LoginAction();
        TestAction testAction=new TestAction();
        MockMvc mockMvc = standaloneSetup(testAction).build();
        String jsonStr =
                "{\"isAdmin\":false,\"User_Password\":\"12345678\",\"User_Cell\":\"18814167467\",\"User_Name\":\"林莹莹\",\"User_Email\":\"1624471560@qq.com\"}";
        String responseString = mockMvc.perform((post("/test"))
                .contentType(MediaType.APPLICATION_JSON).content(jsonStr)
        ).andExpect(status().isOk()).andDo(print()).andReturn().getResponse().getContentAsString();
        System.out.println(responseString);
    }


}