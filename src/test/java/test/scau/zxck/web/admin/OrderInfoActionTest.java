package test.scau.zxck.web.admin;

import com.alibaba.fastjson.JSON;

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

import scau.zxck.web.admin.GoodsInfoAction;
import scau.zxck.web.admin.OrderInfoAction;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.SimpleFormatter;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

/**
 * Created by lishunpeng on 2015/11/13.
 */




@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({"classpath:config/spring/spring.xml","classpath:config/spring/web/spring-mvc.xml"})
public class OrderInfoActionTest {
  // 记得配置log4j.properties ,的命令行输出水平是debug
  protected Log logger = LogFactory.getLog(OrderInfoActionTest.class);


  @Autowired
  private WebApplicationContext wac;
  private MockMvc mockMvc;
  @Autowired
  private OrderInfoAction orderInfoAction;
  @Before() // 这个方法在每个方法执行之前都会执行一遍
  public void setup() {
    mockMvc = standaloneSetup(orderInfoAction).build(); // 初始化MockMvc对象
  }

  @org.junit.Test
  public void getAllCategoryTest() throws Exception {
    //ParamObj paramObj=new ParamObj(true,"12345678","","1",""/*,200*/);
        /*String str=JSON.toJSON(paramObj).toString();
        System.out.println(str);*/

    String name="{\"status\":\"0\"}";
    String password="guanshui";
    //System.out.println(gson.toJson(name));
    ;
    String  s="{\n" +
       "\t\"User_PK\":\"100003\",\n" +
       "\t\"Order_ID\":\"201721010217\",\n" +
       "\t\"Order_No\":\"\",\n" +
       "\t\"Goods_List\":\"100002#\",\n" +
       "\t\"Goods_Num\":\"1#\",\n" +
       "\t\"Goods_Price\":\"10#\",\n" +
       "\t\"Goods_Type\":\"2\",\n" +
       "\t\"Order_Time\":\"2017-03-30 20:03:46\",\n" +
       "\t\"Order_IsPay\":true,\n" +
       "\t\"Order_PayTime\":\"2017-03-30 20:03:46\",\n" +
       "\t\"Order_Tracknum\":\"\",\n" +
       "\t\"Order_Company\":\"\",\n" +
       "\t\"Order_Website\":\"\",\n" +
       "\t\"Order_Aftersale\":\"0\",\n" +
       "\t\"Order_PayPrice\":\"20\",\n" +
       "\t\"Order_State\":1,\n" +
       "\t\"Order_Reserve_1\":\"13416137226;65;65;656565;\"\n" +
       "}";


    String responseString = mockMvc.perform( post("/addOrder").contentType(MediaType.APPLICATION_JSON_VALUE).content(s)).andDo(print())
      .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
  }
}
