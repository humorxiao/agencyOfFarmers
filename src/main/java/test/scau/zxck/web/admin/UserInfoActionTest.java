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
import scau.zxck.entity.market.DeliveryAddress;
import scau.zxck.utils.ToJSONStringUtil;
import scau.zxck.web.admin.UserInfoAction;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

/** 
* UserInfoAction Tester. 
* 
* @author <Authors name> 
* @since <pre>���� 20, 2018</pre> 
* @version 1.0 
*/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:config/spring/web/spring-mvc.xml", "classpath:config/spring/spring.xml"})
@WebAppConfiguration
public class UserInfoActionTest {

    @Autowired
    private UserInfoAction userInfoAction;
    @Autowired
    private MockHttpServletRequest mockHttpServletRequest;
    @Autowired
    private MockHttpServletResponse mockHttpServletResponse;
    @Autowired
    private MockHttpSession mockHttpSession;
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
* Method: getUserInfo(String jsonStr) 
* 
*/ 
@Test
public void testGetUserInfo() throws Exception { 
//TODO: Test goes here...

} 

/** 
* 
* Method: updateUserInfo(String jsonStr) 
* 
*/ 
@Test
public void testUpdateUserInfo() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: getUserDeliveryAddress(String jsonStr) 
* 
*/ 
@Test
public void testGetUserDeliveryAddress() throws Exception { 
//TODO: Test goes here... 

    DeliveryAddress deliveryAddress = new DeliveryAddress("13421166393","林先生","广东省揭阳市某某区某某街道","522000","");
   String jsonStr = mapper.writeValueAsString(deliveryAddress);
    System.out.println(jsonStr);
    jsonStr=ToJSONStringUtil.toJSON(jsonStr);
    System.out.println(jsonStr);
    mockHttpSession.setAttribute("User_PK","100003");
    mockMvc = standaloneSetup(userInfoAction).build();
    String responseString = mockMvc.perform((post("/getUserDeliveryAddress").session(mockHttpSession))
            .contentType(MediaType.APPLICATION_JSON).content(jsonStr)
    ).andExpect(status().isOk()).andDo(print()).andReturn().getResponse().getContentAsString();
    System.out.println(responseString);
}

/** 
* 
* Method: updateUserDeliveryAddress(String jsonStr) 
* 
*/ 
@Test
public void testUpdateUserDeliveryAddress() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: removeBannedUser(String jsonStr) 
* 
*/ 
@Test
public void testRemoveBannedUser() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: addUserBanned(String jsonStr) 
* 
*/ 
@Test
public void testAddUserBanned() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: getBannedUserInfo(String jsonStr) 
* 
*/ 
@Test
public void testGetBannedUserInfo() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: getLikesUser(String jsonStr) 
* 
*/ 
@Test
public void testGetLikesUser() throws Exception { 
//TODO: Test goes here... 
} 


} 
