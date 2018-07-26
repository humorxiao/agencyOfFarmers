package scau.zxck.web.admin;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import scau.zxck.base.dao.mybatis.Conditions;
import scau.zxck.base.exception.BaseException;
import scau.zxck.entity.market.GoodsInfo;
import scau.zxck.entity.market.UserCollection;
import scau.zxck.entity.sys.UserInfo;
import scau.zxck.service.market.IUserCollectService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
//import java.sql.Timestamp;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Controller
@RequestMapping("/")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:config/spring/spring.xml")
@WebAppConfiguration
public class CollectInfoAction {
    @Autowired
    private IUserCollectService userCollectService;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private HttpSession session;


    @RequestMapping(value = "addCollect", method = RequestMethod.POST)
    public String addCollection(String jsonStr) throws Exception {
        String r = "";
//        BufferedReader br = request.getReader();
//        String str, wholeStr = "";
//        while((str = br.readLine()) != null){
//            wholeStr += str;
//        }
//        jsonStr=wholeStr;
        JSONObject data = JSONObject.parseObject(jsonStr);
//        HttpServletRequest request =
//                ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//        HttpSession session = request.getSession();
        if (session.getAttribute("User_PK") != null) {
            data.put("User_PK", session.getAttribute("User_PK"));
        } else {
            data.put("User_PK", "");
        }
        data.put("Collect_Time", (new SimpleDateFormat("yyyy-MM-dd HH:MM:ss")).format(new Date()));
        // UserComments userComments=new UserComments();
        // userComments.setUser_info_id(data.get("User_PK").toString());
        // userComments.setGoods_info_id(data.get("Goods_PK").toString());
        // userComments.setComm_rank((int)Integer.parseInt(data.get("Comm_Rank").toString()));
        // userComments.setComm_text(data.get("Comm_Text").toString());
        // userComments.setComm_time(String.valueOf(data.get("Comm_Time").toString()));
        // userComments.setUserinfo(new UserInfoTest());
        // userComments.setGoodsinfo(new GoodsInfo());
        UserCollection userCollection = new UserCollection();
        userCollection.setUser_info_id(data.get("User_PK").toString());
        userCollection.setGoods_info_id(data.get("Goods_PK").toString());
        userCollection.setCollect_time(Timestamp.valueOf(data.get("Collect_Time").toString()).toString());
        userCollection.setUserinfo(new UserInfo());
        userCollection.setGoodsinfo(new GoodsInfo());
        try {
            userCollectService.add(userCollection);
            r = "{\"status\":1}";
        } catch (Exception e) {
            e.printStackTrace();
            r = "{\"status\":0}";
        }

        return "success";
    }


  
    @RequestMapping(value = "removeCollect", method = RequestMethod.POST)
    public String removeCollect(String jsonStr) throws Exception {
        String r = "";
//        BufferedReader br = request.getReader();
//        String str, wholeStr = "";
//        while((str = br.readLine()) != null){
//            wholeStr += str;
//        }
//        jsonStr=wholeStr;
        JSONObject data = JSONObject.parseObject(jsonStr);
//        HttpServletRequest request =
//                ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//        HttpSession session = request.getSession();
        if (session.getAttribute("User_PK") != null) {
            data.put("User_PK", session.getAttribute("User_PK"));
        } else {
            data.put("User_PK", "");
        }
        try {
            userCollectService.deleteByIds(data.get("Collect_PK").toString());
            r = "{\"status\":1}";
        } catch (BaseException e) {
            e.printStackTrace();
            r = "{\"status\":0}";
        }
        return "success";
    }
  

        @RequestMapping(value = "getCollect", method = RequestMethod.POST)
        public String getCollect(String jsonStr) throws Exception {
            JSONObject data = JSONObject.parseObject(jsonStr);
            // BufferedReader br = request.getReader();
            // String str, wholeStr = "";
            // while((str = br.readLine()) != null){
            // wholeStr += str;
            // }
            // jsonStr=wholeStr;
            // HttpServletRequest request =
            // ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            // HttpSession session = request.getSession();
            if (session.getAttribute("User_PK") != null) {
                data.put("User_PK", session.getAttribute("User_PK"));
            } else {
                data.put("User_PK", "");
            }
            JSONArray jsonarr = new JSONArray();
            Conditions conditions = new Conditions();
            List list =
                    userCollectService.list(conditions.eq("user_info_id", data.get("User_PK").toString()));
            // DataSearch.collectByID((int)Integer.parseInt(json.get("User_PK").toString()));
            for (Iterator iter = ((java.util.List) list).iterator(); iter.hasNext();) {
                JSONObject temp = new JSONObject();
                UserCollection collect = (UserCollection) iter.next();


                temp.put("Collect_PK", collect.getId());
                temp.put("User_PK", collect.getUser_info_id());
                temp.put("Goods_PK", collect.getGoods_info_id());
                temp.put("Collect_Time", collect.getCollect_time().toString());
                jsonarr.add(temp);
            }
//        r=jsonarr.toString();
        return "success";
    }
 
}



//测试代码如下

//package scau.zxck.web.admin;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONArray;
//import com.alibaba.fastjson.JSONObject;
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//import org.junit.Before;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.MediaType;
//import org.springframework.stereotype.Controller;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.web.WebAppConfiguration;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.context.WebApplicationContext;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//import scau.zxck.base.dao.mybatis.Conditions;
//import scau.zxck.base.exception.BaseException;
//import scau.zxck.entity.market.GoodsInfo;
//import scau.zxck.entity.market.UserCollection;
//import scau.zxck.entity.market.UserComments;
//import scau.zxck.entity.sys.UserInfo;
//import scau.zxck.service.market.IUserCollectService;
//import scau.zxck.web.test.UserInfoTest;
//

// import javax.servlet.http.HttpServletRequest;
// import javax.servlet.http.HttpSession;
// import java.sql.Timestamp;
// import java.text.SimpleDateFormat;
// import java.util.Date;
// import java.util.Iterator;
// import java.util.List;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;
//import java.sql.Timestamp;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.Iterator;
//import java.util.List;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//class ParamObj1 extends Object{
//    private String user_PK;
//    private String goods_PK;
//    private String collect_Time;
//    private String collect_PK;
//
//  public String getGoods_PK() {
//    return goods_PK;
//  }
//
//  public void setGoods_PK(String goods_PK) {
//    this.goods_PK = goods_PK;
//  }
//
//  public String getUser_PK() {
//    return user_PK;
//  }
//
//  public void setUser_PK(String user_PK) {
//    this.user_PK = user_PK;
//  }
//
//  public String getCollect_Time() {
//    return collect_Time;
//  }
//
//  public void setCollect_Time(String collect_Time) {
//    this.collect_Time = collect_Time;
//  }
//
//  public ParamObj1(String user_PK, String goods_PK, String collect_Time){
//    this.setUser_PK(user_PK);
//    this.setGoods_PK(goods_PK);
//    this.setCollect_Time(collect_Time);
//  }
////    public ParamObj1(String collect_PK){
////        this.setUser_PK(user_PK);
////    }
//    public ParamObj1(String user_PK){
//      this.setUser_PK(user_PK);
//    }
//}
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@WebAppConfiguration
//@ContextConfiguration(locations = {"classpath:config/spring/spring.xml"})
//@Controller
//@RequestMapping("/")
//public class CollectInfoAction {
//  @Autowired
//  private IUserCollectService userCollectService;
//
// @org.junit.Test
// @RequestMapping(value = "addCollect", method = RequestMethod.POST)
// public void addCollection() throws BaseException {
// String r = "";
// ParamObj1 paramObj1 = new ParamObj1("100003","100017","2018-07-18 16:17:56");
// String jsonStr=JSON.toJSON(paramObj1).toString();
// jsonStr="{\"Collect_Time\":\"2018-07-18
// 16:17:56\",\"Goods_PK\":\"100017\",\"User_PK\":\"100003\"}";
// JSONObject data = JSONObject.parseObject(jsonStr);
// System.out.println(jsonStr);

// UserCollection userCollection = new UserCollection();
// userCollection.setUser_info_id(data.get("User_PK").toString());
// userCollection.setGoods_info_id(data.get("Goods_PK").toString());
// userCollection.setCollect_time(Timestamp.valueOf(data.get("Collect_Time").toString()).toString());
// userCollection.setUserinfo(new UserInfo());
// userCollection.setGoodsinfo(new GoodsInfo());
// try {
// userCollectService.add(userCollection);
// r = "{\"status\":1}";
// } catch (Exception e) {
// e.printStackTrace();
// r = "{\"status\":0}";
// }
// System.out.println(r);
// }
//  @org.junit.Test
//  @RequestMapping(value = "addCollect", method = RequestMethod.POST)
//  public void addCollection() throws BaseException {
//    String r = "";
//    ParamObj1 paramObj1 = new ParamObj1("100003","100017","2018-07-18 16:17:56");
//    String jsonStr=JSON.toJSON(paramObj1).toString();
//    jsonStr="{\"Collect_Time\":\"2018-07-18 16:17:56\",\"Goods_PK\":\"100017\",\"User_PK\":\"100003\"}";
//    JSONObject data = JSONObject.parseObject(jsonStr);
//    System.out.println(jsonStr);
//
//    UserCollection userCollection = new UserCollection();
//    userCollection.setUser_info_id(data.get("User_PK").toString());
//    userCollection.setGoods_info_id(data.get("Goods_PK").toString());
//    userCollection.setCollect_time(Timestamp.valueOf(data.get("Collect_Time").toString()).toString());
//    userCollection.setUserinfo(new UserInfo());
//    userCollection.setGoodsinfo(new GoodsInfo());
//    try {
//      userCollectService.add(userCollection);
//      r = "{\"status\":1}";
//    } catch (Exception e) {
//      e.printStackTrace();
//      r = "{\"status\":0}";
//    }
//    System.out.println(r);
//  }
//
//  @org.junit.Test
//  @RequestMapping(value = "removeCollect", method = RequestMethod.POST)
//  public void removeCollect() {
//      String r = "";
//      ParamObj1 paramObj2 = new ParamObj1("dc97e4ac3a0a45eb966cceb7f604f03b");
//      String jsonStr2=JSON.toJSON(paramObj2).toString();
//      System.out.println(jsonStr2);
//      jsonStr2="{\"Collect_PK\":\"dc97e4ac3a0a45eb966cceb7f604f03b\"}";
//      System.out.println(jsonStr2);
//    JSONObject data = JSONObject.parseObject(jsonStr2);
//    try {
//      userCollectService.deleteByIds(data.get("Collect_PK").toString());
//      r = "{\"status\":1}";
//    } catch (BaseException e) {
//      e.printStackTrace();
//      r = "{\"status\":0}";
//    }
//    System.out.println(r);
//  }
//
//  @org.junit.Test
//  @RequestMapping(value = "getCollect", method = RequestMethod.POST)
//  public void getCollect() throws BaseException {
//      String r = "";
//      ParamObj1 paramObj3 = new ParamObj1("100003");
//      String jsonStr3=JSON.toJSON(paramObj3).toString();
//      System.out.println(jsonStr3);
//      jsonStr3="{\"User_PK\":\"100003\"}";
//      System.out.println(jsonStr3);
//      JSONObject data = JSONObject.parseObject(jsonStr3);
//    JSONArray jsonarr = new JSONArray();
//    Conditions conditions = new Conditions();
//    List list =
//        userCollectService.list(conditions.eq("user_info_id", data.get("User_PK").toString()));
//    // DataSearch.collectByID((int)Integer.parseInt(json.get("User_PK").toString()));
//
//    for (Iterator iter = ((java.util.List) list).iterator(); iter.hasNext();) {
//      JSONObject temp = new JSONObject();
//      UserCollection collect = (UserCollection) iter.next();
//
//      temp.put("Collect_PK", collect.getId());
//      temp.put("User_PK", collect.getUser_info_id());
//      temp.put("Goods_PK", collect.getGoods_info_id());
//      temp.put("Collect_Time", collect.getCollect_time());
//
//      jsonarr.add(temp);
//    }
//    r=jsonarr.toString();
//    System.out.println(r);
//  }
//}
