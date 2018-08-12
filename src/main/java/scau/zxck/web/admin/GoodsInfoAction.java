package scau.zxck.web.admin;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import scau.zxck.base.dao.mybatis.Conditions;
import scau.zxck.entity.market.*;
import scau.zxck.service.market.*;
import scau.zxck.utils.FlushWriteUtil;
import scau.zxck.utils.ReadJSONUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Controller
@RequestMapping("/")
public class GoodsInfoAction {
  @Autowired
  private IGoodsInfoService goodsInfoService;
  @Autowired
  private IUserCollectService userCollectionService;
  @Autowired
  private IOrderInfoService orderInfoService;
  @Autowired
  private HttpServletRequest request;
  @Autowired
  private HttpSession session;
  @RequestMapping(value = "getOneGood", method = RequestMethod.POST)
  public void getOneGood( HttpServletResponse response) throws Exception {
    JSONObject data= ReadJSONUtil.readJSONStr(request);
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
    FlushWriteUtil.flushWrite(response,r);
  }

  @RequestMapping(value = "getAllTypeGoods", method = RequestMethod.POST)
  public void getAllTypesGoods( HttpServletResponse response) throws Exception {
    long startTime = System.currentTimeMillis();
    JSONArray jsonarr1 = new JSONArray();
    String r = "";
    for (int i = 1; i <= 6; i++) {
      Conditions conditions = new Conditions();
      List list = goodsInfoService.list(conditions.eq("goods_type", i));
      JSONArray jsonarr = new JSONArray();
      for (Iterator iter = ((java.util.List) list).iterator(); iter.hasNext(); ) {
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
      jsonarr1.add(jsonarr);
    }
    r = jsonarr1.toString();
    long endTime = System.currentTimeMillis();
    System.out.println("Running time: " + (endTime - startTime) + "ms");
    FlushWriteUtil.flushWrite(response,r);
  }

  @RequestMapping(value = "getTypeGoods", method = RequestMethod.POST)
  public void getTypeGoods( HttpServletResponse response) throws Exception {
    long startTime = System.currentTimeMillis();
    JSONObject data= ReadJSONUtil.readJSONStr(request);
    String r = "";
    Conditions conditions = new Conditions();
    List list =
      goodsInfoService.list(conditions.eq("Goods_Type", data.get("Goods_Type").toString()));
    JSONArray jsonArray = new JSONArray();
    for (Iterator iter = ((java.util.List) list).iterator(); iter.hasNext(); ) {
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
    FlushWriteUtil.flushWrite(response,r);
  }

  @RequestMapping(value = "getSpecialGoods", method = RequestMethod.POST)
  public void getSpecialGoods(HttpServletResponse response) throws Exception {
    long starttime = System.currentTimeMillis();
    String r = "";
    Conditions conditions = new Conditions();
    List list = goodsInfoService.list(conditions.eq("goods_show", '1'));
    long endtime1=System.currentTimeMillis();
    System.out.println("Running time: " + (endtime1 - starttime) + "ms");
    JSONArray jsonArray = new JSONArray();
    for (Iterator iter = ((java.util.List) list).iterator(); iter.hasNext(); ) {
      JSONObject temp = new JSONObject();
      GoodsInfo goods = (GoodsInfo) iter.next();
      if (goods.getGoods_mark() == '0') {
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
    }
    r = jsonArray.toString();
    long endtime = System.currentTimeMillis();
    System.out.println("Running time: " + (endtime - starttime) + "ms");
    FlushWriteUtil.flushWrite(response,r);
  }

  @RequestMapping(value = "getDiscountGoods", method = RequestMethod.POST)
  public void getDiscountGoods(HttpServletResponse response) throws Exception {
    long starttime = System.currentTimeMillis();
    String r = "";
    Conditions conditions = new Conditions();
    List list = goodsInfoService.list(conditions.eq("goods_show", '2'));
    JSONArray jsonArray = new JSONArray();
    for (Iterator iter = ((java.util.List) list).iterator(); iter.hasNext(); ) {
      JSONObject temp = new JSONObject();
      GoodsInfo goods = (GoodsInfo) iter.next();
      if (goods.getGoods_mark() == '0') {
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
    }
    r = jsonArray.toString();
    long endtime = System.currentTimeMillis();
    System.out.println("Running time: " + (endtime - starttime) + "ms");
    FlushWriteUtil.flushWrite(response,r);
  }

  @RequestMapping(value = "getAllGoods", method = RequestMethod.POST)
  public void getAllGoods( HttpServletResponse response) throws Exception {
    JSONArray jsonarr = new JSONArray();
    List list = goodsInfoService.listAll();
    for (Iterator iter = ((java.util.List) list).iterator(); iter.hasNext(); ) {
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
    FlushWriteUtil.flushWrite(response,r);
  }

  @RequestMapping(value = "addGoods", method = RequestMethod.POST)
  public void addGoods( HttpServletResponse response) throws Exception {
    String r = "";
    JSONObject data= ReadJSONUtil.readJSONStr(request);
    GoodsInfo temp = new GoodsInfo();
    temp.setGoods_name(data.get("Goods_Name").toString());
    temp.setGoods_type((int) Integer.parseInt(data.get("Goods_Type").toString()));
    temp.setGoods_num((int) Integer.parseInt(data.get("Goods_Num").toString()));
    temp.setGoods_price((float) Float.parseFloat(data.get("Goods_Price").toString()));
    temp.setGoods_mark(data.get("Goods_Mark").toString().charAt(0));
    temp.setGoods_show(data.get("Goods_Show").toString().charAt(0));
    temp.setGoods_picture(data.get("Goods_Picture").toString());
    temp.setGoods_season((int) Integer.parseInt(data.get("Goods_Season").toString()));
    temp.setGoods_blossom(data.get("Goods_Blossom").toString());
    temp.setGoods_fruit(data.get("Goods_Fruit").toString());
    temp.setGoods_mature(data.get("Goods_Mature").toString());
    temp.setGoods_expiration(data.get("Goods_Expiration").toString());
    temp.setGoods_expiration(data.get("Goods_Reserve_1").toString());
    try {
      goodsInfoService.add(temp);
      r = "{\"status\":1}";
    } catch (Exception e) {
      e.printStackTrace();
      r = "{\"status\":0}";
    }
    FlushWriteUtil.flushWrite(response,r);
  }

  @RequestMapping(value = "updateGoodsInfo", method = RequestMethod.POST)
  public void updateGoodsInfo( HttpServletResponse response) throws Exception {

    String r = "";
    JSONObject data= ReadJSONUtil.readJSONStr(request);
    GoodsInfo temp = goodsInfoService.findById(data.get("Goods_PK").toString());
    temp.setGoods_name(data.get("Goods_Name").toString());
    temp.setGoods_type((int) Integer.parseInt(data.get("Goods_Type").toString()));
    temp.setGoods_num((int) Integer.parseInt(data.get("Goods_Num").toString()));
    temp.setGoods_price((float) Float.parseFloat(data.get("Goods_Price").toString()));
    temp.setGoods_mark(data.get("Goods_Mark").toString().charAt(0));
    temp.setGoods_show(data.get("Goods_Show").toString().charAt(0));
    temp.setGoods_picture(data.get("Goods_Picture").toString());
    temp.setGoods_season((int) Integer.parseInt(data.get("Goods_Season").toString()));
    temp.setGoods_blossom(data.get("Goods_Blossom").toString());
    temp.setGoods_fruit(data.get("Goods_Fruit").toString());
    temp.setGoods_mature(data.get("Goods_Mature").toString());
    temp.setGoods_expiration(data.get("Goods_Expiration").toString());
    try {
      goodsInfoService.updateById(temp);
      r = "{\"status\":1}";
    } catch (Exception e) {
      e.printStackTrace();
      r = "{\"status\":0}";
    }
    FlushWriteUtil.flushWrite(response,r);
  }

  @RequestMapping(value = "deleteGoodsInfo", method = RequestMethod.POST)
  public void deleteGoodsInfo( HttpServletResponse response) throws Exception {
    String r = "";
    JSONObject data= ReadJSONUtil.readJSONStr(request);
    GoodsInfo temp = goodsInfoService.findById(data.get("Goods_PK").toString());
    temp.setGoods_name(data.get("Goods_Name").toString());
    temp.setGoods_type((int) Integer.parseInt(data.get("Goods_Type").toString()));
    temp.setGoods_num((int) Integer.parseInt(data.get("Goods_Num").toString()));
    temp.setGoods_price((float) Float.parseFloat(data.get("Goods_Price").toString()));
    temp.setGoods_mark(data.get("Goods_Mark").toString().charAt(0));
    temp.setGoods_show(data.get("Goods_Show").toString().charAt(0));
    temp.setGoods_picture(data.get("Goods_Picture").toString());
    temp.setGoods_season((int) Integer.parseInt(data.get("Goods_Season").toString()));
    temp.setGoods_blossom(data.get("Goods_Blossom").toString());
    temp.setGoods_fruit(data.get("Goods_Fruit").toString());
    temp.setGoods_mature(data.get("Goods_Mature").toString());
    temp.setGoods_expiration(data.get("Goods_Expiration").toString());
    try {
      goodsInfoService.updateById(temp);
      r = "{\"status\":1}";
    } catch (Exception e) {
      e.printStackTrace();
      r = "{\"status\":0}";
    }
    FlushWriteUtil.flushWrite(response,r);
  }

  @RequestMapping(value = "getLikesGoods", method = RequestMethod.POST)
  public void getLikesAction( HttpServletResponse response) throws Exception {
    JSONObject data= ReadJSONUtil.readJSONStr(request);
    String r = "";
    String likes = data.get("likes").toString();
    likes = java.net.URLDecoder.decode(likes, "utf-8");
    Conditions conditions = new Conditions();
    JSONArray jsonarr = new JSONArray();
    if (likes != null) {
      List list =
        goodsInfoService.list(conditions.like("goods_name", "%" + data.get("Goods_Name") + "%"));
      for (Iterator iter = ((java.util.List) list).iterator(); iter.hasNext(); ) {
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
      r = jsonarr.toString();
    }
    FlushWriteUtil.flushWrite(response,r);
  }

  @RequestMapping(value = "guessYouLike", method = RequestMethod.POST)
  public void guessYouLike( HttpServletResponse response) throws Exception {
    JSONObject data= new JSONObject();
    data.put("User_PK",session.getAttribute("User_PK"));
    JSONArray jsonArray = new JSONArray();
    String r = "";
    Conditions conditions = new Conditions();
    List list =
      userCollectionService.list(conditions.eq("user_info_id", data.get("User_PK").toString()));
    ArrayList<UserCollection> arrayList = new ArrayList<>();
    if (list.size() >= 4) {
      for (Iterator iter = ((java.util.List) list).iterator(); iter.hasNext(); ) {
        arrayList.add((UserCollection) iter.next());
      }
      for (int i = 0; i < arrayList.size(); i++) {
        JSONObject temp = new JSONObject();
        UserCollection userCollection = arrayList.get(i);
        String goods_pk = userCollection.getGoods_info_id();
        GoodsInfo goodsInfo = goodsInfoService.findById(goods_pk);
        temp.put("Goods_PK", goodsInfo.getId());
        temp.put("Goods_Name", goodsInfo.getGoods_name());
        temp.put("Goods_Type", goodsInfo.getGoods_type());
        temp.put("Goods_Num", goodsInfo.getGoods_num());
        temp.put("Goods_Price", goodsInfo.getGoods_price());
        temp.put("Goods_Mark", goodsInfo.getGoods_mark());
        temp.put("Goods_Show", goodsInfo.getGoods_show());
        temp.put("Goods_Picture", goodsInfo.getGoods_picture());
        jsonArray.add(temp);
      }
    } else {
      int mark = 1;
      for (int i = 0; i < 4; i++) {
        List list1 = goodsInfoService.list(conditions.eq("goods_show", mark));
        GoodsInfo goodsInfo = (GoodsInfo) list1.get(0);
        JSONObject temp = new JSONObject();
        temp.put("Goods_PK", goodsInfo.getId());
        temp.put("Goods_Name", goodsInfo.getGoods_name());
        temp.put("Goods_Type", goodsInfo.getGoods_type());
        temp.put("Goods_Num", goodsInfo.getGoods_num());
        temp.put("Goods_Price", goodsInfo.getGoods_price());
        temp.put("Goods_Mark", goodsInfo.getGoods_mark());
        temp.put("Goods_Show", goodsInfo.getGoods_show());
        temp.put("Goods_Picture", goodsInfo.getGoods_picture());
        temp.put("Goods_Reverse_1", goodsInfo.getGoods_reserve_1());
        jsonArray.add(temp);
      }
    }
    r = jsonArray.toString();
    FlushWriteUtil.flushWrite(response,r);
  }

  @Test
  @RequestMapping(value = "recentlyPerchase", method = RequestMethod.POST)
  public void recentlyPerchase( HttpServletResponse response) throws Exception {
    String r = "";
    JSONObject data= new JSONObject();
    data.put("User_PK",session.getAttribute("User_PK").toString());
    Conditions conditions = new Conditions();
    List list = orderInfoService.list(conditions.eq("user_info_id", data.get("User_PK").toString()).and().eq("order_isPay", 1));
    ArrayList<String> arrayList = new ArrayList<>();
    for (Iterator iter = ((java.util.List) list).iterator(); iter.hasNext(); ) {
      OrderInfo orderInfo = (OrderInfo) iter.next();
      String[] ss = orderInfo.getGoods_list().split("#");
      for (int i = 0; i < ss.length; i++) {
        arrayList.add(ss[i]);
      }
    }
    JSONArray jsonArray = new JSONArray();
    if (arrayList.size() >= 4) {
      for (int i = arrayList.size() - 1; i >= arrayList.size() - 4; i--) {
        GoodsInfo goodsInfo = goodsInfoService.findById(arrayList.get(i));
        JSONObject temp = new JSONObject();
        temp.put("Goods_PK", goodsInfo.getId());
        temp.put("Goods_Name", goodsInfo.getGoods_name());
        temp.put("Goods_Type", goodsInfo.getGoods_type());
        temp.put("Goods_Num", goodsInfo.getGoods_num());
        temp.put("Goods_Price", goodsInfo.getGoods_price());
        temp.put("Goods_Mark", goodsInfo.getGoods_mark());
        temp.put("Goods_Show", goodsInfo.getGoods_show());
        temp.put("Goods_Picture", goodsInfo.getGoods_picture());
        temp.put("Goods_Reverse_1", goodsInfo.getGoods_reserve_1());
        jsonArray.add(temp);
      }
      r = jsonArray.toString();
    } else {
      int mark = 1;
      for (int i = 0; i < 4; i++) {
        List list1 = goodsInfoService.list(conditions.eq("goods_show", mark));
        GoodsInfo goodsInfo = (GoodsInfo) list1.get(0);
        JSONObject temp = new JSONObject();
        temp.put("Goods_PK", goodsInfo.getId());
        temp.put("Goods_Name", goodsInfo.getGoods_name());
        temp.put("Goods_Type", goodsInfo.getGoods_type());
        temp.put("Goods_Num", goodsInfo.getGoods_num());
        temp.put("Goods_Price", goodsInfo.getGoods_price());
        temp.put("Goods_Mark", goodsInfo.getGoods_mark());
        temp.put("Goods_Show", goodsInfo.getGoods_show());
        temp.put("Goods_Picture", goodsInfo.getGoods_picture());
        temp.put("Goods_Reverse_1", goodsInfo.getGoods_reserve_1());
        jsonArray.add(temp);
      }
      r = jsonArray.toString();
    }
    FlushWriteUtil.flushWrite(response,r);
  }

  @RequestMapping(value = "chooseSixSpecialGoods", method = RequestMethod.POST)
  public void chooseSixSpecialGoods( HttpServletResponse response) throws Exception {
    JSONObject data= ReadJSONUtil.readJSONStr(request);
    Conditions conditions = new Conditions();
    List list = goodsInfoService.list(conditions.eq("goods_show", '1'));
    for (Iterator iter = ((java.util.List) list).iterator(); iter.hasNext(); ) {
      GoodsInfo goodsInfo = (GoodsInfo) iter.next();
      goodsInfo.setGoods_show('0');
      goodsInfoService.updateById(goodsInfo);
    }
    String[] goodspks = data.get("Goods_PK").toString().split("#");
    for (int i = 0; i < goodspks.length; i++) {
      GoodsInfo goodsInfo = goodsInfoService.findById(goodspks[i]);
      goodsInfo.setGoods_show('1');
      goodsInfoService.updateById(goodsInfo);
    }
  }

  @RequestMapping(value = "chooseSixDiscountGoods", method = RequestMethod.POST)
  public void getSixDiscountGoods(HttpServletResponse response) throws Exception {
    JSONObject data= ReadJSONUtil.readJSONStr(request);
    Conditions conditions = new Conditions();
    List list = goodsInfoService.list(conditions.eq("goods_show", '2'));
    for (Iterator iter = ((java.util.List) list).iterator(); iter.hasNext(); ) {
      GoodsInfo goodsInfo = (GoodsInfo) iter.next();
      goodsInfo.setGoods_show('0');
      goodsInfoService.updateById(goodsInfo);
    }
    String[] goodspks = data.get("Goods_PK").toString().split("#");
    for (int i = 0; i < goodspks.length; i++) {
      GoodsInfo goodsInfo = goodsInfoService.findById(goodspks[i]);
      goodsInfo.setGoods_show('2');
      goodsInfoService.updateById(goodsInfo);
    }
  }
}
