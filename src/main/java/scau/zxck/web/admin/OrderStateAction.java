package scau.zxck.web.admin;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sun.xml.internal.rngom.parse.host.Base;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import scau.zxck.base.dao.mybatis.Conditions;
import scau.zxck.base.exception.BaseException;
import scau.zxck.entity.market.GoodsInfo;
import scau.zxck.entity.market.GoodsLog;
import scau.zxck.entity.market.OrderInfo;
import scau.zxck.service.market.IGoodsInfoService;
import scau.zxck.service.market.IGoodsLogService;
import scau.zxck.service.market.IOrderInfoService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Controller
@RequestMapping("/")
public class OrderStateAction {
  @Autowired
  private IOrderInfoService orderInfoService;
  @Autowired
  private IGoodsLogService goodsLogService;
  @Autowired
  private IGoodsInfoService goodsInfoService;

  @RequestMapping(value = "changeOrderState", method = RequestMethod.POST)
  public String changeOrderState(String jsonStr) throws BaseException {
    String r = "";
    JSONObject data = JSONObject.parseObject(jsonStr);
    if ((int) data.get("Order_State") == 2) { // 取消订单
      Conditions conditions = new Conditions();
      List list = orderInfoService.list(conditions.eq("id", data.get("Order_ID").toString()));
      JSONObject temp = new JSONObject();
      if (!list.isEmpty()) {
        OrderInfo order = (OrderInfo) list.get(0);

        temp.put("Order_PK", order.getId());
        temp.put("User_PK", order.getUser_info_id());
        temp.put("Order_ID", order.getOrder_id());
        temp.put("Order_No", order.getOrder_no());
        temp.put("Goods_List", order.getGoods_list());
        temp.put("Goods_Num", order.getGoods_num());
        temp.put("Goods_Prices", order.getGoods_prices());
        temp.put("Order_Time", order.getOrder_time());
        temp.put("Order_IsPay", order.isOrder_ispay());
        if (order.getOrder_paytime().equals(new String("0001-1-1 1:01:01"))) {
          temp.put("Order_PayTime", "");
        } else {
          temp.put("Order_PayTime", order.getOrder_paytime());
        }
        temp.put("Order_PayPrice", order.getOrder_payprice());
        temp.put("Order_State", order.getOrder_state());
        temp.put("Order_TrackNum", order.getOrder_tracknum());
        temp.put("Order_Company", order.getOrder_company());
        temp.put("Order_Website", order.getOrder_website());
        temp.put("Order_Aftersale", order.getOrder_aftersale());
        temp.put("Order_Reserve_1", order.getOrder_reserve_1());
      }
      String[] goodslist = ((String) temp.get("Goods_List")).split("#");
      String[] goodsnum = ((String) temp.get("Goods_Num")).split("#");
      for (int i = 0; i < goodslist.length; i++) {// 循环操作订单中的每一项商品
        JSONObject log = new JSONObject();
        log.put("Goods_PK", goodslist[i]);
        log.put("Goods_In", goodsnum[i]);
        log.put("Goods_Out", 0);
        log.put("Goods_PriceChange", 0);
        log.put("GL_Time", (new SimpleDateFormat("yyyy-MM-dd HH:MM:ss")).format(new Date()));
        // 增加进销存日志
        GoodsLog temp1 = new GoodsLog();
        temp1.setGoods_info_id(log.get("Goods_PK").toString());
        temp1.setGoods_in((int) Integer.parseInt(log.get("Goods_In").toString()));
        temp1.setGoods_out((int) Integer.parseInt(log.get("Goods_Out").toString()));
        temp1.setGoods_pricechange(
            (float) Float.parseFloat(log.get("Goods_PriceChange").toString()));
        temp1.setGl_time(Timestamp.valueOf(log.get("GL_Time").toString()).toString());
        goodsLogService.add(temp1);
        JSONObject goodspk = new JSONObject();
        goodspk.put("Goods_PK", goodslist[i]);
        JSONObject temp2 = new JSONObject();

        List list1 = goodsInfoService.list(conditions.eq("id", goodspk.get("Goods_PK").toString()));
        if (!list1.isEmpty()) {
          GoodsInfo goods = (GoodsInfo) list1.get(0);

          temp2.put("Goods_PK", goods.getId());
          temp2.put("Goods_Name", goods.getGoods_name());
          temp2.put("Goods_Type", goods.getGoods_type());
          temp2.put("Goods_Num", goods.getGoods_num());
          temp2.put("Goods_Price", goods.getGoods_price());
          temp2.put("Goods_Mark", goods.getGoods_mark());
          temp2.put("Goods_Show", goods.getGoods_show());
          temp2.put("Goods_Picture", goods.getGoods_picture());
          temp2.put("Goods_Season", goods.getGoods_season());
          temp2.put("Goods_Blossom", goods.getGoods_blossom());
          temp2.put("Goods_Fruit", goods.getGoods_fruit());
          temp2.put("Goods_Mature", goods.getGoods_mature());
          temp2.put("Goods_Expiration", goods.getGoods_expiration());
          temp2.put("Goods_Reserve_1", goods.getGoods_reserve_1());
          temp2.put("Goods_Reserve_2", goods.getGoods_reserve_2());
        }
        int num = (int) temp2.get("Goods_Num") + Integer.parseInt(goodsnum[i]);
        temp2.put("Goods_Num", num);
        GoodsInfo temp3 = goodsInfoService.findById(temp2.get("Goods_PK").toString());
        temp3.setGoods_name(temp2.get("Goods_Name").toString());
        temp3.setGoods_type((int) Integer.parseInt(temp2.get("Goods_Type").toString()));
        temp3.setGoods_num((int) Integer.parseInt(temp2.get("Goods_Num").toString()));
        temp3.setGoods_price((float) Float.parseFloat(temp2.get("Goods_Price").toString()));
        temp3.setGoods_mark(temp2.get("Goods_Mark").toString().charAt(0));
        temp3.setGoods_show(temp2.get("Goods_Show").toString().charAt(0));
        temp3.setGoods_picture(temp2.get("Goods_Picture").toString());
        temp3.setGoods_season((int) Integer.parseInt(temp2.get("Goods_Season").toString()));
        temp3.setGoods_blossom(temp2.get("Goods_Blossom").toString());
        temp3.setGoods_fruit(temp2.get("Goods_Fruit").toString());
        temp3.setGoods_mature(temp2.get("Goods_Mature").toString());
        temp3.setGoods_expiration(temp2.get("Goods_Expiration").toString());
        // access.updateGoodsInfo(goods);// 返回库存
        goodsInfoService.updateById(temp3);
      }
    }
    JSONObject tempy = new JSONObject();
    Conditions conditions = new Conditions();
    // String hql = "from OrderInfo where order_id='" + json.get("Order_ID").toString() + "'";
    List list = orderInfoService.list(conditions.eq("order_id", data.get("Order_ID")));
    if (!list.isEmpty()) {
      OrderInfo order = (OrderInfo) list.get(0);

      tempy.put("Order_PK", order.getId());
      tempy.put("User_PK", order.getUser_info_id());
      tempy.put("Order_ID", order.getOrder_id());
      tempy.put("Order_No", order.getOrder_no());
      tempy.put("Goods_List", order.getGoods_list());
      tempy.put("Goods_Num", order.getGoods_num());
      tempy.put("Goods_Prices", order.getGoods_prices());
      tempy.put("Order_Time", order.getOrder_time());
      tempy.put("Order_IsPay", order.isOrder_ispay());
      if (order.getOrder_paytime().equals(new String("0001-1-1 1:01:01"))) {
        tempy.put("Order_PayTime", "");
      } else {
        tempy.put("Order_PayTime", order.getOrder_paytime());
      }
      tempy.put("Order_PayPrice", order.getOrder_payprice());
      // 关键
      tempy.put("Order_State", (int) Integer.parseInt(data.get("Order_State").toString()));

      tempy.put("Order_TrackNum", order.getOrder_tracknum());
      tempy.put("Order_Company", order.getOrder_company());
      tempy.put("Order_Website", order.getOrder_website());
      tempy.put("Order_Aftersale", order.getOrder_aftersale());
      tempy.put("Order_Reserve_1", order.getOrder_reserve_1());
    }
    OrderInfo tempz = orderInfoService.findById(data.get("Order_PK").toString());
    tempz.setUser_info_id((data.get("User_PK").toString()));
    tempz.setOrder_id(data.get("Order_ID").toString());
    tempz.setOrder_no(data.get("Order_No").toString());
    tempz.setGoods_list(data.get("Goods_List").toString());
    tempz.setGoods_num(data.get("Goods_Num").toString());
    tempz.setGoods_prices(data.get("Goods_Prices").toString());
    tempz.setOrder_time(Timestamp.valueOf(data.get("Order_Time").toString()).toString());
    tempz.setOrder_ispay((boolean) data.get("Order_IsPay"));
    if (!data.get("Order_PayTime").equals(new String(""))) {
      tempz.setOrder_paytime(Timestamp.valueOf(data.get("Order_PayTime").toString()).toString());
    }
    /*
     * else {
     * 
     * try { String date1 = "0001-01-01 01:01:01"; Date strD = (Date) (new
     * SimpleDateFormat("yyyy-MM-dd HH:MM:ss")).parse(date1);
     * temp.setOrder_paytime(Timestamp.valueOf((new
     * SimpleDateFormat("yyyy-MM-dd HH:MM:ss")).format(strD))); } catch (ParseException e) {
     * e.printStackTrace(); } }
     */
    tempz.setOrder_payprice((float) Float.parseFloat(data.get("Order_PayPrice").toString()));
    tempz.setOrder_state((int) Integer.parseInt(data.get("Order_State").toString()));
    tempz.setOrder_tracknum(data.get("Order_TrackNum").toString());
    tempz.setOrder_company(data.get("Order_Company").toString());
    tempz.setOrder_website(data.get("Order_Website").toString());
    tempz.setOrder_aftersale((int) Integer.parseInt(data.get("Order_Aftersale").toString()));
    tempz.setOrder_reserve_1(data.get("Order_Reserve_1").toString());
    try {
      orderInfoService.updateById(tempz);
      r = "{\"status\":1}";
    } catch (Exception e) {
      e.printStackTrace();
      r = "{\"status\":0}";
    }
    // boolean ret = access.changeOrderState(data);
    return "success";
  }

  @RequestMapping(value = "changeOrderAfterSale", method = RequestMethod.POST)
  public String changeOrderAfterSale(String jsonStr) throws Exception {
    String r = "";
    JSONObject data = JSONObject.parseObject(jsonStr);
    JSONObject temp = new JSONObject();
    Conditions conditions = new Conditions();
    // String hql = "from OrderInfo where order_id='" + json.get("Order_ID").toString() + "'";
    List list = orderInfoService.list(conditions.eq("id", data.get("Order_ID").toString()));

    if (!list.isEmpty()) {
      OrderInfo order = (OrderInfo) list.get(0);

      temp.put("Order_PK", order.getId());
      temp.put("User_PK", order.getUser_info_id());
      temp.put("Order_ID", order.getOrder_id());
      temp.put("Order_No", order.getOrder_no());
      temp.put("Goods_List", order.getGoods_list());
      temp.put("Goods_Num", order.getGoods_num());
      temp.put("Goods_Prices", order.getGoods_prices());
      temp.put("Order_Time", order.getOrder_time());
      temp.put("Order_IsPay", order.isOrder_ispay());
      if (order.getOrder_paytime().equals(new String("0001-1-1 1:01:01"))) {
        temp.put("Order_PayTime", "");
      } else {
        temp.put("Order_PayTime", order.getOrder_paytime());
      }
      temp.put("Order_PayPrice", order.getOrder_payprice());
      temp.put("Order_State", order.getOrder_state());
      temp.put("Order_TrackNum", order.getOrder_tracknum());
      temp.put("Order_Company", order.getOrder_company());
      temp.put("Order_Website", order.getOrder_website());
      // 关键
      temp.put("Order_Aftersale", (int) Integer.parseInt(data.get("Order_Aftersale").toString()));

      temp.put("Order_Reserve_1", order.getOrder_reserve_1());
    }
    OrderInfo temp1 = orderInfoService.findById(temp.get("Order_PK").toString());
    temp1.setUser_info_id(temp.get("User_PK").toString());
    temp1.setOrder_id(temp.get("Order_ID").toString());
    temp1.setOrder_no(temp.get("Order_No").toString());
    temp1.setGoods_list(temp.get("Goods_List").toString());
    temp1.setGoods_num(temp.get("Goods_Num").toString());
    temp1.setGoods_prices(temp.get("Goods_Prices").toString());
    temp1.setOrder_time(Timestamp.valueOf(temp.get("Order_Time").toString()).toString());
    temp1.setOrder_ispay((boolean) temp.get("Order_IsPay"));
    if (!temp.get("Order_PayTime").equals(new String(""))) {
      temp1.setOrder_paytime(Timestamp.valueOf(temp.get("Order_PayTime").toString()).toString());
    }
    /*
     * else {
     * 
     * try { String date1 = "0001-01-01 01:01:01"; Date strD = (Date) (new
     * SimpleDateFormat("yyyy-MM-dd HH:MM:ss")).parse(date1);
     * temp.setOrder_paytime(Timestamp.valueOf((new
     * SimpleDateFormat("yyyy-MM-dd HH:MM:ss")).format(strD))); } catch (ParseException e) {
     * e.printStackTrace(); } }
     */
    temp1.setOrder_payprice((float) Float.parseFloat(temp.get("Order_PayPrice").toString()));
    temp1.setOrder_state((int) Integer.parseInt(temp.get("Order_State").toString()));
    temp1.setOrder_tracknum(temp.get("Order_TrackNum").toString());
    temp1.setOrder_company(temp.get("Order_Company").toString());
    temp1.setOrder_website(temp.get("Order_Website").toString());
    temp1.setOrder_aftersale((int) Integer.parseInt(temp.get("Order_Aftersale").toString()));
    temp1.setOrder_reserve_1(temp.get("Order_Reserve_1").toString());
    // temp.setUserInfo(new UserInfo());
    try {
      orderInfoService.updateById(temp1);
      r = "{\"status\":1}";
    } catch (Exception e) {
      r = "{\"status\":0}";
    }
    return "success";
  }

  @RequestMapping(value = "getOrdersByStateAndUser", method = RequestMethod.POST)
  public String getOrdersByStateAndUser(String jsonStr) throws BaseException {
    String r = "";
    JSONObject data = JSONObject.parseObject(jsonStr);
    HttpServletRequest request =
        ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    HttpSession session = request.getSession();
    if (session.getAttribute("User_PK") != null) {
      data.put("User_PK", (int) session.getAttribute("User_PK"));
    } else {
      data.put("User_PK", "");
    }
    JSONArray jsonarr = new JSONArray();
    Conditions conditions = new Conditions();
    // String hql = "from OrderInfo where order_state=" +
    // (int)Integer.parseInt(json.get("Order_State").toString())
    // + "AND user_pk=" + (int)Integer.parseInt(json.get("User_PK").toString()) ;
    List list =
        orderInfoService.list(conditions.eq("order_state", data.get("Order_State").toString()).and()
            .eq("user_info_id", data.get("User_PK").toString()));
    // DataSearch.searchByHQL(hql);

    for (Iterator iter = ((java.util.List) list).iterator(); iter.hasNext();) {
      JSONObject temp = new JSONObject();

      OrderInfo order = (OrderInfo) iter.next();

      temp.put("Order_PK", order.getId());
      temp.put("User_PK", order.getUser_info_id());
      temp.put("Order_ID", order.getOrder_id());
      temp.put("Order_No", order.getOrder_no());
      temp.put("Goods_List", order.getGoods_list());
      temp.put("Goods_Num", order.getGoods_num());
      temp.put("Goods_Prices", order.getGoods_prices());
      temp.put("Order_Time", order.getOrder_time());
      temp.put("Order_IsPay", order.isOrder_ispay());
      if (order.getOrder_paytime().equals(new String("0001-1-1 1:01:01"))) {
        temp.put("Order_PayTime", "");
      } else {
        temp.put("Order_PayTime", order.getOrder_paytime());
      }
      temp.put("Order_PayPrice", order.getOrder_payprice());
      temp.put("Order_State", order.getOrder_state());
      temp.put("Order_TrackNum", order.getOrder_tracknum());
      temp.put("Order_Company", order.getOrder_company());
      temp.put("Order_Website", order.getOrder_website());
      temp.put("Order_Aftersale", order.getOrder_aftersale());
      temp.put("Order_Reserve_1", order.getOrder_reserve_1());
      jsonarr.add(temp);
    }
    r = jsonarr.toString();
    return "success";
  }

  @RequestMapping(value = "getOrdersByAftersaleAndUser", method = RequestMethod.POST)
  public String getOrdersByAftersaleAndUser(String jsonStr) throws BaseException {
    String r = "";
    JSONObject data = JSONObject.parseObject(jsonStr);
    HttpServletRequest request =
        ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    HttpSession session = request.getSession();
    if (session.getAttribute("User_PK") != null) {
      data.put("User_PK", (int) session.getAttribute("User_PK"));
    } else {
      data.put("User_PK", "");
    }
    JSONArray jsonarr = new JSONArray();
    Conditions conditions = new Conditions();
    // String hql = "from OrderInfo where order_aftersale=" +
    // (int)Integer.parseInt(json.get("Order_Aftersale").toString())
    // + "AND user_pk=" + (int)Integer.parseInt(json.get("User_PK").toString()) ;
    List list = orderInfoService.list(conditions
        .eq("order_aftersale", (int) Integer.parseInt(data.get("Order_Aftersale").toString())).and()
        .eq("user_info_id", data.get("User_PK").toString()));
    // DataSearch.searchByHQL(hql);

    for (Iterator iter = ((java.util.List) list).iterator(); iter.hasNext();) {
      JSONObject temp = new JSONObject();

      OrderInfo order = (OrderInfo) iter.next();

      temp.put("Order_PK", order.getId());
      temp.put("User_PK", order.getUser_info_id());
      temp.put("Order_ID", order.getOrder_id());
      temp.put("Order_No", order.getOrder_no());
      temp.put("Goods_List", order.getGoods_list());
      temp.put("Goods_Num", order.getGoods_num());
      temp.put("Goods_Prices", order.getGoods_prices());
      temp.put("Order_Time", order.getOrder_time());
      temp.put("Order_IsPay", order.isOrder_ispay());
      if (order.getOrder_paytime().equals(new String("0001-1-1 1:01:01"))) {
        temp.put("Order_PayTime", "");
      } else {
        temp.put("Order_PayTime", order.getOrder_paytime());
      }
      temp.put("Order_PayPrice", order.getOrder_payprice());
      temp.put("Order_State", order.getOrder_state());
      temp.put("Order_TrackNum", order.getOrder_tracknum());
      temp.put("Order_Company", order.getOrder_company());
      temp.put("Order_Website", order.getOrder_website());
      temp.put("Order_Aftersale", order.getOrder_aftersale());
      temp.put("Order_Reserve_1", order.getOrder_reserve_1());
      jsonarr.add(temp);
    }
    r=jsonarr.toString();
    return "success";
  }

}
