package scau.zxck.test.service.market;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import scau.zxck.web.admin.LoginAction;

/**
 * @author zhzh 2015-4-7
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:config/spring/spring.xml","classpath:config/spring/web/spring-mvc.xml"})
// 当然 你可以声明一个事务管理 每个单元测试都进行事务回滚 无论成功与否
@TransactionConfiguration(defaultRollback = true)
@Transactional
public class TestController2 {
  @Autowired
  private WebApplicationContext wac;
  public static ObjectMapper mapper = new ObjectMapper();
  private MockMvc mockMvc;

  @Before
  public void setup() {
    this.mockMvc = webAppContextSetup(this.wac).build();
  }

  @Test
  public void testLogin() throws Exception {
    SoftInfo softInfo=new SoftInfo("100000","yhx");
    String jsonStr=mapper.writeValueAsString(softInfo);
    System.out.println(jsonStr);
  }

  /*
   * @Test //有些单元测试你不希望回滚
   * 
   * @Rollback(false) public void testInsert() throws Exception {
   * mockMvc.perform((post("/insertTest"))).andExpect(status().isOk()) .andDo(print()); }
   */
}
