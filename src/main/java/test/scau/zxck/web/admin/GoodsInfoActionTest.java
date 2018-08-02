package test.scau.zxck.web.admin; 

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import scau.zxck.entity.market.GoodsInfo;
import scau.zxck.web.admin.GoodsInfoAction;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

/** 
* GoodsInfoAction Tester. 
* 
* @author <Authors name> 
* @since <pre>???? 2, 2018</pre> 
* @version 1.0 
*/
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:config/spring/spring.xml","classpath:config/spring/web/spring-mvc.xml"})
public class GoodsInfoActionTest { 
@Autowired
private MockHttpServletRequest request;
@Autowired
private MockHttpSession session;
@Autowired
private GoodsInfoAction goodsInfoAction;
private MockMvc mockMvc;
private ObjectMapper mapper=new ObjectMapper();
@Before
public void before() throws Exception {
    mockMvc=standaloneSetup(goodsInfoAction).build();
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: getOneGood(String jsonStr, HttpServletResponse response) 
* 
*/ 
@Test
public void testGetOneGood() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: getAllTypesGoods(String jsonStr, HttpServletResponse response) 
* 
*/ 
@Test
public void testGetAllTypesGoods() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: getTypeGoods(String jsonStr, HttpServletResponse response) 
* 
*/ 
@Test
public void testGetTypeGoods() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: getSpecialGoods(HttpServletResponse response) 
* 
*/ 
@Test
public void testGetSpecialGoods() throws Exception { 
//TODO: Test goes here...
    String jsonStr;
//    GoodsInfo goodsInfo=new GoodsInfo("")
   String responseString= mockMvc.perform(post("/getSpecialGoods")
//            .contentType(MediaType.APPLICATION_JSON).content(jsonStr)
   )
            .andExpect(status().isOk()).andDo(print())
           .andReturn().getResponse().getContentAsString();
    System.out.println(responseString);
} 

/** 
* 
* Method: getDiscountGoods(HttpServletResponse response) 
* 
*/ 
@Test
public void testGetDiscountGoods() throws Exception { 
//TODO: Test goes here...
    String responseString= mockMvc.perform(post("/getSpecialGoods")
//            .contentType(MediaType.APPLICATION_JSON).content(jsonStr)
    )
            .andExpect(status().isOk()).andDo(print())
            .andReturn().getResponse().getContentAsString();
    System.out.println(responseString);
} 

/** 
* 
* Method: getAllGoods(String jsonStr, HttpServletResponse response) 
* 
*/ 
@Test
public void testGetAllGoods() throws Exception { 
//TODO: Test goes here...
    Date date=(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).parse("2018-08-02 20:20:20");
    System.out.println((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(date));
} 

/** 
* 
* Method: addGoods(String jsonStr, HttpServletResponse response) 
* 
*/ 
@Test
public void testAddGoods() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: updateGoodsInfo(String jsonStr, HttpServletResponse response) 
* 
*/ 
@Test
public void testUpdateGoodsInfo() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: deleteGoodsInfo(String jsonStr, HttpServletResponse response) 
* 
*/ 
@Test
public void testDeleteGoodsInfo() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: getLikesAction(String jsonStr, HttpServletResponse response) 
* 
*/ 
@Test
public void testGetLikesAction() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: guessYouLike(String jsonStr, HttpServletResponse response) 
* 
*/ 
@Test
public void testGuessYouLike() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: recentlyPerchase(String jsonStr, HttpServletResponse response) 
* 
*/ 
@Test
public void testRecentlyPerchase() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: chooseSixSpecialGoods(String jsonStr, HttpServletResponse response) 
* 
*/ 
@Test
public void testChooseSixSpecialGoods() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: getSixDiscountGoods(String jsonStr, HttpServletResponse response) 
* 
*/ 
@Test
public void testGetSixDiscountGoods() throws Exception { 
//TODO: Test goes here... 
} 


} 
