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
