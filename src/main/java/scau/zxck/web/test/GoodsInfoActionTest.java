package scau.zxck.web.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.ibatis.jdbc.Null;
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
import scau.zxck.entity.market.GoodsInfo;
import scau.zxck.service.market.IGoodsInfoService;
import scau.zxck.service.market.INodeInfoService;
import scau.zxck.service.market.ITypeInfoService;
import scau.zxck.utils.ToJSONString;
import scau.zxck.web.admin.GoodsInfoAction;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

/**
 * GoodsInfoAction Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>???? 20, 2018</pre>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:config/spring/spring.xml", "classpath:config/spring/web/spring-mvc.xml"})
@WebAppConfiguration
public class GoodsInfoActionTest {
    private MockMvc mockMvc;
    private ObjectMapper mapper = new ObjectMapper();
    @Autowired
    private GoodsInfoAction goodsInfoAction;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private INodeInfoService nodeInfoService;
    @Autowired
    private ITypeInfoService typeInfoService;
    @Autowired
    private IGoodsInfoService goodsInfoService;
    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: getOneGood(String jsonStr)
     */
    @Test
    public void testGetOneGood() throws Exception {
//TODO: Test goes here...
        mockMvc = standaloneSetup(goodsInfoAction).build();
        GoodsInfo goodsInfo = new GoodsInfo("100001");
//   goodsInfo.setId("100001");
        String jsonStr = mapper.writeValueAsString(goodsInfo);
        System.out.println(jsonStr);
        jsonStr = ToJSONString.toJSON(jsonStr);
        System.out.println(jsonStr);
//    jsonStr = "{\"id\":\"100001\",\"Goods_name\":null,\"Goods_type\":0,\"Goods_num\":0,\"Goods_price\":0.0," +
//            "\"Goods_mark\":\"\\u0000\",\"Goods_show\":\"\\u0000\",\"Goods_picture\":null,\"Goods_season\":0," +
//            "\"Goods_blossom\":null,\"Goods_fruit\":null,\"Goods_mature\":null,\"Goods_expiration\":null," +
//            "\"Goods_reserve_1\":null,\"Goods_reserve_2\":null,\"Remark\":null}";
        String responseString = mockMvc.perform((post("/getOneGood"))
                .contentType(MediaType.APPLICATION_JSON).content(jsonStr)
        ).andExpect(status().isOk()).andDo(print()).andReturn().getResponse().getContentAsString();
        System.out.println(responseString);
    }

    /**
     * Method: getAllTypesGoods(String jsonStr)
     */
    @Test
    public void testGetAllTypesGoods() throws Exception {
        System.out.println(typeInfoService.findById("1")==null);
//        System.out.println(goodsInfoService.findById("100000"));
    }

/**
 *
 * Method: getTypeGoods(String jsonStr)
 *
 */
////@Test
//public void testGetTypeGoods() throws Exception {
////TODO: Test goes here...
//    mockMvc = standaloneSetup(goodsInfoAction).build();
//    GoodsInfo goodsInfo = new GoodsInfo(6);
//    String jsonStr = mapper.writeValueAsString(goodsInfo);
//    System.out.println(jsonStr);
//    jsonStr = ToJSONString.toJSON(jsonStr);
//    System.out.println(jsonStr);
//    String responseString = mockMvc.perform((post("/getTypeGoods"))
//            .contentType(MediaType.APPLICATION_JSON).content(jsonStr)
//    ).andExpect(status().isOk()).andDo(print()).andReturn().getResponse().getContentAsString();
//    System.out.println(responseString);
//}

/**
 *
 * Method: getSpecialGoods(String jsonStr)
 *
 */
//@Test
//public void testGetSpecialGoods() throws Exception {
////TODO: Test goes here...
//    mockMvc = standaloneSetup(goodsInfoAction).build();
//    GoodsInfo goodsInfo = new GoodsInfo('0');
//    String jsonStr = mapper.writeValueAsString(goodsInfo);
//    System.out.println(jsonStr);
//    jsonStr = ToJSONString.toJSON(jsonStr);
//    System.out.println(jsonStr);
//    String responseString = mockMvc.perform((post("/getSpecialGoods"))
//            .contentType(MediaType.APPLICATION_JSON).content(jsonStr)
//    ).andExpect(status().isOk()).andDo(print()).andReturn().getResponse().getContentAsString();
//    System.out.println(responseString);
//}

/**
 *
 * Method: getDiscountGoods(String jsonStr)
 *
 */
//@Test
//public void testGetDiscountGoods() throws Exception {
////TODO: Test goes here...
//    mockMvc = standaloneSetup(goodsInfoAction).build();
//    GoodsInfo goodsInfo = new GoodsInfo('2');
//    String jsonStr = mapper.writeValueAsString(goodsInfo);
//    System.out.println(jsonStr);
//    jsonStr = ToJSONString.toJSON(jsonStr);
//    System.out.println(jsonStr);
//    String responseString = mockMvc.perform((post("/getDiscountGoods"))
//            .contentType(MediaType.APPLICATION_JSON).content(jsonStr)
//    ).andExpect(status().isOk()).andDo(print()).andReturn().getResponse().getContentAsString();
//    System.out.println(responseString);
//}

    /**
     * Method: getAllGoods(String jsonStr)
     */
    @Test
    public void testGetAllGoods() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: addGoods(String jsonStr)
     */
    @Test
    public void testAddGoods() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: updateGoodsInfo(String jsonStr)
     */
    @Test
    public void testUpdateGoodsInfo() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: deleteGoodsInfo(String jsonStr)
     */
    @Test
    public void testDeleteGoodsInfo() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: getLikesAction(String jsonStr)
     */
    @Test
    public void testGetLikesAction() throws Exception {
//TODO: Test goes here...
//    String jsonStr = "{\"Goods_Name\":\"名茶\"}";
//    JSONObject data = JSONObject.parseObject(jsonStr);
//      System.out.println(jsonStr);
//    String r = "";
////    HttpServletRequest request =
////            ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
////    HttpSession session = request.getSession();
////    String likes = request.getParameter("likes");
////    "{\"Goods_Season\":0,\"Goods_Price\":0,\"Goods_Type\":0,\"Goods_Show\":\"\\u0000\"," +
////      "\"Goods_Mark\":\"0\",\"Goods_Num\":0}"
//      String likes = "Goods_Name";
////    likes = java.net.URLDecoder.decode(likes, "utf-8");
//    Conditions conditions = new Conditions();
//    JSONArray jsonarr = new JSONArray();
//    if (likes != null) {
//      List list = goodsInfoService.list(conditions.like("goods_name", "%"+data.get("Goods_Name")+"%"));
        mockMvc = standaloneSetup(goodsInfoAction).build();
        GoodsInfo goodsInfo = new GoodsInfo("名茶");
        String jsonStr = mapper.writeValueAsString(goodsInfo);
        System.out.println(jsonStr);
        jsonStr = ToJSONString.toJSON(jsonStr);
        System.out.println(jsonStr);
        String likes = request.getParameter("likes");
//    "{\"Goods_Season\":0,\"Goods_Price\":0,\"Goods_Type\":0,\"Goods_Show\":\"\\u0000\"," +
//      "\"Goods_Mark\":\"0\",\"Goods_Num\":0}"
//      String likes = "Goods_Name";
        likes = java.net.URLDecoder.decode(likes, "utf-8");
        String responseString = mockMvc.perform((post("/getLikesGoods"))
                .contentType(MediaType.APPLICATION_JSON).content(jsonStr)
        ).andExpect(status().isOk()).andDo(print()).andReturn().getResponse().getContentAsString();
        System.out.println(responseString);
    }

//getLikesGoods方法没有进行测试 属性不明
} 
