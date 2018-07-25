package scau.zxck.utils;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.alibaba.fastjson.JSON;

/**
 * Created by lishunpeng on 2015/11/13.
 */

class ParamObj extends Object {
  private  Boolean isAdmin;
  private  String admin_Password;
  private  String admin_Cell;
  private  String admin_Name;
  private  String admin_Email;
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

  public ParamObj(Boolean isAdmin, String admin_Password, String admin_Cell, String admin_Name, String admin_Email,int status) {
    this.isAdmin = isAdmin;
    this.admin_Password = admin_Password;
    this.admin_Cell = admin_Cell;
    this.admin_Name = admin_Name;
    this.admin_Email = admin_Email;
    this.status=status;
  }

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }
}


@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:config/spring/spring.xml"})
public class UnionStaffServiceTest {
  // 记得配置log4j.properties ,的命令行输出水平是debug
  protected Log logger = LogFactory.getLog(UnionStaffServiceTest.class);


  @Autowired
  protected WebApplicationContext wac;
  protected MockMvc mockMvc;

  @Before() // 这个方法在每个方法执行之前都会执行一遍
  public void setup() {
    mockMvc = MockMvcBuilders.webAppContextSetup(wac).build(); // 初始化MockMvc对象
  }

  @org.junit.Test
  public void getAllCategoryTest() throws Exception {
    ParamObj paramObj=new ParamObj(true,"12345678","","1","",200);
    String str=JSON.toJSON(paramObj).toString();
    System.out.println(str);
    String responseString = mockMvc.perform( post("/login").contentType(MediaType.APPLICATION_JSON).content(str)).andDo(print())
            .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
  }
}
