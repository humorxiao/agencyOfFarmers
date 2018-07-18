package test.scau.zxck.web.admin; 

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/** 
* LoginAction Tester. 
* 
* @author <Authors name> 
* @since <pre>���� 18, 2018</pre> 
* @version 1.0 
*/
class ParamObj extends Object {
    private Boolean isAdmin;
    private String admin_Password;
    private String admin_Cell;
    private String admin_Name;
    private String admin_Email;
    private int status;

    public Boolean getAdmin() {
        return isAdmin;
    }

    public void setAdmin(Boolean admin) {
        isAdmin = admin;
    }

    public String getAdmin_Password() {
        return admin_Password;
    }

    public void setAdmin_Password(String admin_Password) {
        this.admin_Password = admin_Password;
    }

    public String getAdmin_Cell() {
        return admin_Cell;
    }

    public void setAdmin_Cell(String admin_Cell) {
        this.admin_Cell = admin_Cell;
    }

    public String getAdmin_Name() {
        return admin_Name;
    }

    public void setAdmin_Name(String admin_Name) {
        this.admin_Name = admin_Name;
    }

    public String getAdmin_Email() {
        return admin_Email;
    }

    public void setAdmin_Email(String admin_Email) {
        this.admin_Email = admin_Email;
    }

    public ParamObj(Boolean isAdmin, String admin_Password, String admin_Cell, String admin_Name, String admin_Email, int status) {
        this.isAdmin = isAdmin;
        this.admin_Password = admin_Password;
        this.admin_Cell = admin_Cell;
        this.admin_Name = admin_Name;
        this.admin_Email = admin_Email;
        this.status = status;
    }
}
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:config/spring/spring.xml"})
@WebAppConfiguration
public class LoginActionTest {
    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

@Before
public void before() throws Exception {
    mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
} 

@After
public void after() throws Exception { 
}


/** 
* 
* Method: login(@RequestParam("jsonStr") String jsonStr) 
* 
*/ 
@Test
public void testLogin() throws Exception {
    ParamObj paramObj=new ParamObj(true,"12345678","","1","",200);
    String str=JSON.toJSON(paramObj).toString();
    System.out.println(str);
    String responseString = mockMvc.perform(MockMvcRequestBuilders.post("/login", str))
            .andReturn().getResponse().getContentAsString();
    System.out.println(responseString);
} 


} 
