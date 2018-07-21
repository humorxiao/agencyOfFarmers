package test.scau.zxck.web.admin;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import scau.zxck.entity.market.CartInfo;
import scau.zxck.utils.ToJSONString;
import scau.zxck.web.admin.CartInfoAction;
import scau.zxck.web.admin.LoginAction;
import scau.zxck.web.admin.TestAction;
import scau.zxck.web.test.UserInfoTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

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
@ContextConfiguration({"classpath:config/spring/spring.xml","classpath:config/spring/web/spring-mvc.xml"})
@WebAppConfiguration
public class LoginActionTest {
    //  protected Log logger = LogFactory.getLog(LoginAction.class);
//    @Autowired
//    private WebApplicationContext wac;
    @Autowired
    private LoginAction loginAction;
//    @Autowired
//    private CartInfoAction cartInfoAction;
    @Autowired
    private TestAction testAction;
    @Autowired
    private MockHttpServletRequest mockHttpServletRequest;
    @Autowired
    private MockHttpServletResponse mockHttpServletResponse;
    @Autowired
    private MockHttpSession mockHttpSession;

    private MockMvc mockMvc;
    private static ObjectMapper mapper=new ObjectMapper();
    @Before
    public void before() throws Exception {
//    mockMvc = standaloneSetup(testAction).build();
//    mockMvc=MockMvcBuilders.webAppContextSetup(wac).build();
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
        mockMvc = standaloneSetup(loginAction).build();
        UserInfoTest userInfoTest = new UserInfoTest("true","12345678","1","default","1");
        String jsonStr=mapper.writeValueAsString(userInfoTest);
        System.out.println(jsonStr);
        jsonStr=ToJSONString.toJSON(jsonStr);
        System.out.println(jsonStr);
//        jsonStr="{\"isAdmin\":true,\"Admin_Password\":\"12345678\",\"Admin_Cell\":\"1\",\"Admin_Name\":\"default\",\"Admin_Email\":\"1\"}";
//        System.out.println(jsonStr);
        mockHttpSession.setAttribute("Admin_PK","101");
        String responseString = mockMvc.perform((post("/login").session(mockHttpSession))
                .contentType(MediaType.APPLICATION_JSON).content(jsonStr)
        ).andExpect(status().isOk()).andDo(print()).andReturn().getResponse().getContentAsString();
        System.out.println(responseString);
    }


}