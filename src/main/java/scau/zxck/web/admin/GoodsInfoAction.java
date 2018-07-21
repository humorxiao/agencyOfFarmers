package scau.zxck.web.admin;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sun.xml.internal.rngom.parse.host.Base;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import scau.zxck.base.dao.BaseDao;
import scau.zxck.base.dao.mybatis.Conditions;
import scau.zxck.base.exception.BaseException;
import scau.zxck.entity.market.GoodsInfo;
import scau.zxck.entity.market.UnionInfo;
import scau.zxck.service.market.IGoodsInfoService;
import scau.zxck.service.market.IUnionInfoService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.util.Iterator;
import java.util.List;

@Controller
@RequestMapping("/")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:config/spring/spring.xml")
public class GoodsInfoAction {
  @Autowired
  private IGoodsInfoService goodsInfoService;
  @Autowired
  private IUnionInfoService unionInfoService;
  @Autowired
  private HttpServletRequest request;
  @Autowired
  private HttpSession session;
  @RequestMapping(value = "getOneGood", method = RequestMethod.POST)
  public String getOneGood(String jsonStr) throws BaseException {
    JSONObject data = JSONObject.parseObject(jsonStr);
    String r = "";
    GoodsInfo goods = goodsInfoService.findById(data.get("Goods_PK").toString());
    JSONObject temp = new JSONObject();
    temp.put("Goods_PK", goods.getId());
    temp.put("Goods_Name", goods.getGoods_name());
    temp.put("Goods_Type", goods.getGoods_type());
    temp.put("Goods_Num", goods.getGoods_num());
    temp.put("Goods_Price", goods.getGoods_price());
    temp.put("Goods_Mark", goods.getGoods_mark());
    temp.put("Goods_Show", goods.getGoods_show());
    temp.put("Goods_Picture", goods.getGoods_picture());
    temp.put("Goods_Season", goods.getGoods_season());
    temp.put("Goods_Blossom", goods.getGoods_blossom());
    temp.put("Goods_Fruit", goods.getGoods_fruit());
    temp.put("Goods_Mature", goods.getGoods_mature());
    temp.put("Goods_Expiration", goods.getGoods_expiration());
    temp.put("Goods_Reserve_1", goods.getGoods_reserve_1());
    temp.put("Goods_Reserve_2", goods.getGoods_reserve_2());
    r = temp.toString();
    return "success";
  }

  @RequestMapping(value = "getAllTypeGoods", method = RequestMethod.POST)
  public String getAllTypesGoods(String jsonStr) throws BaseException {
    long startTime = System.currentTimeMillis();
    JSONObject data = JSONObject.parseObject(jsonStr);
    String r = "";
    List list = goodsInfoService.listAll();
    JSONArray jsonarr = new JSONArray();
    for (Iterator iter = ((java.util.List) list).iterator(); iter.hasNext();) {
      JSONObject temp = new JSONObject();
      GoodsInfo goods = (GoodsInfo) iter.next();

      temp.put("Goods_PK", goods.getId());

      temp.put("Goods_Name", goods.getGoods_name());
      temp.put("Goods_Type", goods.getGoods_type());
      temp.put("Goods_Num", goods.getGoods_num());
      temp.put("Goods_Price", goods.getGoods_price());
      temp.put("Goods_Mark", goods.getGoods_mark());
      temp.put("Goods_Show", goods.getGoods_show());
      temp.put("Goods_Picture", goods.getGoods_picture());
      temp.put("Goods_Season", goods.getGoods_season());
      temp.put("Goods_Blossom", goods.getGoods_blossom());
      temp.put("Goods_Fruit", goods.getGoods_fruit());
      temp.put("Goods_Mature", goods.getGoods_mature());
      temp.put("Goods_Expiration", goods.getGoods_expiration());
      temp.put("Goods_Reserve_1", goods.getGoods_reserve_1());
      jsonarr.add(temp);

    }
    r = jsonarr.toString();
    long endTime = System.currentTimeMillis();
    System.out.println("Running time: " + (endTime - startTime) + "ms");
    return "success";
  }

  @RequestMapping(value = "getTypeGoods", method = RequestMethod.POST)
  public String getTypeGoods(String jsonStr) throws BaseException {
    long startTime = System.currentTimeMillis();
    JSONObject data = JSONObject.parseObject(jsonStr);
    String r = "";
    Conditions conditions = new Conditions();
    List list =
        goodsInfoService.list(conditions.eq("Goods_Type", data.get("Goods_Type").toString()));
    JSONArray jsonArray = new JSONArray();
    for (Iterator iter = ((java.util.List) list).iterator(); iter.hasNext();) {
      JSONObject temp = new JSONObject();
      GoodsInfo goods = (GoodsInfo) iter.next();

      temp.put("Goods_PK", goods.getId());
      temp.put("Goods_Name", goods.getGoods_name());
      temp.put("Goods_Type", goods.getGoods_type());
      temp.put("Goods_Num", goods.getGoods_num());
      temp.put("Goods_Price", goods.getGoods_price());
      temp.put("Goods_Mark", goods.getGoods_mark());
      temp.put("Goods_Show", goods.getGoods_show());
      temp.put("Goods_Picture", goods.getGoods_picture());

      jsonArray.add(temp);
    }
    r = jsonArray.toString();
    long endTime = System.currentTimeMillis();
    System.out.println("Running time: " + (endTime - startTime) + "ms");
    return "success";
  }

  @RequestMapping(value = "getSpecialGoods", method = RequestMethod.POST)
  public String getSpecialGoods(String jsonStr) throws BaseException {
    long starttime = System.currentTimeMillis();
    JSONObject data = JSONObject.parseObject(jsonStr);
    String r = "";
    Conditions conditions = new Conditions();
    Integer mark = Integer.parseInt(data.get("Goods_Mark").toString());
    List list = goodsInfoService.list(conditions.eq("goods_show", mark));
    JSONArray jsonArray = new JSONArray();
    for (Iterator iter = ((java.util.List) list).iterator(); iter.hasNext();) {
      JSONObject temp = new JSONObject();
      GoodsInfo goods = (GoodsInfo) iter.next();

      temp.put("Goods_PK", goods.getId());
      temp.put("Goods_Name", goods.getGoods_name());
      temp.put("Goods_Type", goods.getGoods_type());
      temp.put("Goods_Num", goods.getGoods_num());
      temp.put("Goods_Price", goods.getGoods_price());
      temp.put("Goods_Mark", goods.getGoods_mark());
      temp.put("Goods_Show", goods.getGoods_show());
      temp.put("Goods_Picture", goods.getGoods_picture());
      temp.put("Goods_Reserve_1", goods.getGoods_reserve_1());
      jsonArray.add(temp);
    }
    r = jsonArray.toString();
    long endtime = System.currentTimeMillis();
    System.out.println("Running time: " + (endtime - starttime) + "ms");
    return "success";
  }

  @RequestMapping(value = "getDiscountGoods", method = RequestMethod.POST)
  public String getDiscountGoods(String jsonStr) throws BaseException {
    long starttime = System.currentTimeMillis();
    JSONObject data = JSONObject.parseObject(jsonStr);
    String r = "";
    Conditions conditions = new Conditions();
    Integer mark = Integer.parseInt(data.get("Goods_Mark").toString());
    List list = goodsInfoService.list(conditions.eq("goods_show", mark));
    JSONArray jsonArray = new JSONArray();
    for (Iterator iter = ((java.util.List) list).iterator(); iter.hasNext();) {
      JSONObject temp = new JSONObject();
      GoodsInfo goods = (GoodsInfo) iter.next();

      temp.put("Goods_PK", goods.getId());
      temp.put("Goods_Name", goods.getGoods_name());
      temp.put("Goods_Type", goods.getGoods_type());
      temp.put("Goods_Num", goods.getGoods_num());
      temp.put("Goods_Price", goods.getGoods_price());
      temp.put("Goods_Mark", goods.getGoods_mark());
      temp.put("Goods_Show", goods.getGoods_show());
      temp.put("Goods_Picture", goods.getGoods_picture());
      temp.put("Goods_Reserve_1", goods.getGoods_reserve_1());
      jsonArray.add(temp);
    }
    r = jsonArray.toString();
    long endtime = System.currentTimeMillis();
    System.out.println("Running time: " + (endtime - starttime) + "ms");
    return "success";
  }

  @RequestMapping(value = "getAllGoods", method = RequestMethod.POST)
  public String getAllGoods(String jsonStr) throws BaseException {
    JSONArray jsonarr = new JSONArray();
    List list = goodsInfoService.listAll();

    for (Iterator iter = ((java.util.List) list).iterator(); iter.hasNext();) {
      JSONObject temp = new JSONObject();
      GoodsInfo goods = (GoodsInfo) iter.next();

      temp.put("Goods_PK", goods.getId());

      temp.put("Goods_Name", goods.getGoods_name());
      temp.put("Goods_Type", goods.getGoods_type());
      temp.put("Goods_Num", goods.getGoods_num());
      temp.put("Goods_Price", goods.getGoods_price());
      temp.put("Goods_Mark", goods.getGoods_mark());
      temp.put("Goods_Show", goods.getGoods_show());
      temp.put("Goods_Picture", goods.getGoods_picture());
      temp.put("Goods_Season", goods.getGoods_season());
      temp.put("Goods_Blossom", goods.getGoods_blossom());
      temp.put("Goods_Fruit", goods.getGoods_fruit());
      temp.put("Goods_Mature", goods.getGoods_mature());
      temp.put("Goods_Expiration", goods.getGoods_expiration());
      temp.put("Goods_Reserve_1", goods.getGoods_reserve_1());
      jsonarr.add(temp);

    }
    String r = jsonarr.toString();
    return "success";
  }

  @RequestMapping(value = "addGoods", method = RequestMethod.POST)
  public String addGoods(String jsonStr) throws BaseException {
    String r = "";
    JSONObject json = JSONObject.parseObject(jsonStr);
    GoodsInfo temp = new GoodsInfo();
    temp.setGoods_name(json.get("Goods_Name").toString());
    temp.setGoods_type((int) Integer.parseInt(json.get("Goods_Type").toString()));
    temp.setGoods_num((int) Integer.parseInt(json.get("Goods_Num").toString()));
    temp.setGoods_price((float) Float.parseFloat(json.get("Goods_Price").toString()));
    temp.setGoods_mark(json.get("Goods_Mark").toString().charAt(0));
    temp.setGoods_show(json.get("Goods_Show").toString().charAt(0));
    temp.setGoods_picture(json.get("Goods_Picture").toString());
    temp.setGoods_season((int) Integer.parseInt(json.get("Goods_Season").toString()));
    temp.setGoods_blossom(json.get("Goods_Blossom").toString());
    temp.setGoods_fruit(json.get("Goods_Fruit").toString());
    temp.setGoods_mature(json.get("Goods_Mature").toString());
    temp.setGoods_expiration(json.get("Goods_Expiration").toString());
    temp.setGoods_expiration(json.get("Goods_Reserve_1").toString());
    try {
      goodsInfoService.add(temp);
      r = "{\"status\":1}";
    } catch (Exception e) {
      e.printStackTrace();
      r = "{\"status\":0}";
    }
    return "success";
  }

  @RequestMapping(value = "updateGoodsInfo", method = RequestMethod.POST)
  public String updateGoodsInfo(String jsonStr) throws BaseException {

    String r = "";
    JSONObject json = JSONObject.parseObject(jsonStr);
    GoodsInfo temp = goodsInfoService.findById(json.get("Goods_PK").toString());
    temp.setGoods_name(json.get("Goods_Name").toString());
    temp.setGoods_type((int) Integer.parseInt(json.get("Goods_Type").toString()));
    temp.setGoods_num((int) Integer.parseInt(json.get("Goods_Num").toString()));
    temp.setGoods_price((float) Float.parseFloat(json.get("Goods_Price").toString()));
    temp.setGoods_mark(json.get("Goods_Mark").toString().charAt(0));
    temp.setGoods_show(json.get("Goods_Show").toString().charAt(0));
    temp.setGoods_picture(json.get("Goods_Picture").toString());
    temp.setGoods_season((int) Integer.parseInt(json.get("Goods_Season").toString()));
    temp.setGoods_blossom(json.get("Goods_Blossom").toString());
    temp.setGoods_fruit(json.get("Goods_Fruit").toString());
    temp.setGoods_mature(json.get("Goods_Mature").toString());
    temp.setGoods_expiration(json.get("Goods_Expiration").toString());
    try {
      goodsInfoService.updateById(temp);
      r = "{\"status\":1}";
    } catch (Exception e) {
      e.printStackTrace();
      r = "{\"status\":0}";
    }
    return "success";
  }

  @RequestMapping(value = "deleteGoodsInfo",method = RequestMethod.POST)
    public String deleteGoodsInfo (String jsonStr) throws BaseException{
      String r="";
      JSONObject json=JSONObject.parseObject(jsonStr);
      GoodsInfo temp = goodsInfoService.findById(json.get("Goods_PK").toString());
      temp.setGoods_name(json.get("Goods_Name").toString());
      temp.setGoods_type((int)Integer.parseInt(json.get("Goods_Type").toString()));
      temp.setGoods_num((int)Integer.parseInt(json.get("Goods_Num").toString()));
      temp.setGoods_price((float)Float.parseFloat(json.get("Goods_Price").toString()));
      temp.setGoods_mark(json.get("Goods_Mark").toString().charAt(0));
      temp.setGoods_show(json.get("Goods_Show").toString().charAt(0));
      temp.setGoods_picture(json.get("Goods_Picture").toString());
      temp.setGoods_season((int)Integer.parseInt(json.get("Goods_Season").toString()));
      temp.setGoods_blossom(json.get("Goods_Blossom").toString());
      temp.setGoods_fruit(json.get("Goods_Fruit").toString());
      temp.setGoods_mature(json.get("Goods_Mature").toString());
      temp.setGoods_expiration(json.get("Goods_Expiration").toString());
      try {
          goodsInfoService.updateById(temp);
          r="{\"status\":1}";
      }catch (Exception e){
          e.printStackTrace();
          r="{\"status\":0}";
      }
      return "success";
  }

  @RequestMapping(value = "getLikesGoods", method = RequestMethod.POST)
  public String getLikesAction(String jsonStr) throws Exception {
    JSONObject data = JSONObject.parseObject(jsonStr);
    String r = "";
    BufferedReader br = request.getReader();
    String str, wholeStr = "";
    while((str = br.readLine()) != null){
      wholeStr += str;
    }
    jsonStr=wholeStr;
//    HttpServletRequest request =
//            ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//    HttpSession session = request.getSession();
    String likes = request.getParameter("likes");
    likes = java.net.URLDecoder.decode(likes, "utf-8");
    Conditions conditions = new Conditions();
    JSONArray jsonarr = new JSONArray();
    if (likes != null) {
      List list = goodsInfoService.list(conditions.like("goods_name", "%"+data.get("Goods_Name")+"%"));
      for (Iterator iter = ((java.util.List) list).iterator(); iter.hasNext();) {
        JSONObject temp = new JSONObject();
        GoodsInfo goods = (GoodsInfo) iter.next();

        temp.put("Goods_PK", goods.getId());
        temp.put("Goods_Name", goods.getGoods_name());
        temp.put("Goods_Type", goods.getGoods_type());
        temp.put("Goods_Num", goods.getGoods_num());
        temp.put("Goods_Price", goods.getGoods_price());
        temp.put("Goods_Mark", goods.getGoods_mark());
        temp.put("Goods_Show", goods.getGoods_show());
        temp.put("Goods_Picture", goods.getGoods_picture());
        jsonarr.add(temp);
      }
      r=jsonarr.toString();
    }
    return "success";
  }

}
