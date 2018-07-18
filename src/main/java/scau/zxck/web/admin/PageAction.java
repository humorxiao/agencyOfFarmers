package scau.zxck.web.admin;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import scau.zxck.base.dao.mybatis.Conditions;
import scau.zxck.base.exception.BaseException;
import scau.zxck.entity.market.OrderInfo;
import scau.zxck.entity.market.UnionNews;
import scau.zxck.service.market.IOrderInfoService;
import scau.zxck.service.market.IUnionNewsService;

import java.util.Iterator;
import java.util.List;

// @Controller
// @RequestMapping("/")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:config/spring/spring.xml")
public class PageAction {
  @Autowired
  private IUnionNewsService unionNewsService;
  @Autowired
  private IOrderInfoService orderInfoService;

  @RequestMapping(value = "getBulletinNoPage", method = RequestMethod.POST)
  // @Test
  public String getBulletinNoPage() throws BaseException {
    JSONArray jsonarr = new JSONArray();

    List list = unionNewsService.listAll();

    for (Iterator iter = ((java.util.List) list).iterator(); iter.hasNext();) {
      JSONObject temp = new JSONObject();
      UnionNews news = (UnionNews) iter.next();

      temp.put("News_PK", news.getId());
      temp.put("News_Title", news.getNews_title());
      temp.put("News_Text", news.getNews_text());
      temp.put("News_Time", news.getNews_time().toLocaleString());

      if (news.getNews_mark() == 2) {
        jsonarr.add(temp);
      }
    }
    String r = jsonarr.toString();
    // System.out.println(r);
    return "success";
  }

  @RequestMapping(value = "getBulletin", method = RequestMethod.POST)
  public String getBulletin(String jsonStr) throws BaseException {
    JSONObject pageInfo = JSONObject.parseObject(jsonStr);
    JSONArray jsonarr = new JSONArray();

    List list = unionNewsService.listAll();
    // SearchAllData("UnionNews");

    for (Iterator iter = ((java.util.List) list).iterator(); iter.hasNext();) {
      JSONObject temp = new JSONObject();
      UnionNews news = (UnionNews) iter.next();

      temp.put("News_PK", news.getId());
      temp.put("News_Title", news.getNews_title());
      temp.put("News_Text", news.getNews_text());
      temp.put("News_Time", news.getNews_time().toLocaleString());

      if (news.getNews_mark() == 2) {
        jsonarr.add(temp);
      }
    }
    String r = JSONArrayPaging(jsonarr, pageInfo).toString();
    return "success";
  }

  @RequestMapping(value = "getStateOrderPaging", method = RequestMethod.POST)
  @Test
  public void getStateOrderPaging() throws BaseException {
    String jsonStr = "{\"state\":\"1\"}";
    String jsonStr2 = "";
    JSONObject data = JSONObject.parseObject(jsonStr);
    JSONObject pageInfo = JSONObject.parseObject(jsonStr2);
    int state = (int) data.get("state");
    JSONArray jsonarr = new JSONArray();

    // String hql = "from OrderInfo where order_state=" + state;
    Conditions conditions = new Conditions();
    List list = orderInfoService.list(conditions.eq("order_state", state));
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
      temp.put("Order_Time", order.getOrder_time().toLocaleString());
      temp.put("Order_IsPay", order.isOrder_ispay());
      if (order.getOrder_paytime().toLocaleString().equals(new String("0001-1-1 1:01:01"))) {
        temp.put("Order_PayTime", "");
      } else {
        temp.put("Order_PayTime", order.getOrder_paytime().toLocaleString());
      }
      temp.put("Order_PayPrice", order.getOrder_payprice());
      temp.put("Order_State", order.getOrder_state());
      temp.put("Order_TrackNum", order.getOrder_tracknum());
      temp.put("Order_Company", order.getOrder_company());
      temp.put("Order_Website", order.getOrder_website());
      temp.put("Order_Aftersale", order.getOrder_aftersale());
      temp.put("Order_Reserve_1", order.getOrder_reserve_1());
      temp.put("User_Name", order.getUserInfo().getUser_name());
      jsonarr.add(temp);
    }
    String r = JSONArrayPaging(jsonarr, pageInfo).toString();
    System.out.println(r);
//    return "success";
  }

  @RequestMapping(value = "getAfterSaleOrderPaging", method = RequestMethod.POST)
  public String getAfterSaleOrderPaging(String jsonStr, String jsonStr2) throws BaseException {
    JSONObject data = JSONObject.parseObject(jsonStr);
    int state = (int) data.get("afterSale");
    JSONObject pageInfo = JSONObject.parseObject(jsonStr2);
    JSONArray jsonarr = new JSONArray();
    Conditions conditions = new Conditions();
    List list = orderInfoService.list(conditions.eq("order_aftersale", state));

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
      temp.put("Order_Time", order.getOrder_time().toLocaleString());
      temp.put("Order_IsPay", order.isOrder_ispay());
      if (order.getOrder_paytime().toLocaleString().equals(new String("0001-1-1 1:01:01"))) {
        temp.put("Order_PayTime", "");
      } else {
        temp.put("Order_PayTime", order.getOrder_paytime().toLocaleString());
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
    String r = JSONArrayPaging(jsonarr, pageInfo).toString();
    return "success";
  }

  public JSONArray JSONArrayPaging(JSONArray arr, JSONObject json) {
    JSONArray temparr = new JSONArray();
    JSONObject firstjson = new JSONObject();

    firstjson.put("Size", arr.size());

    if (arr.size() < json.getIntValue("NumPerPage")) {
      firstjson.put("PageNum", 1);
    } else {
      if (arr.size() % json.getIntValue("NumPerPage") == 0) {
        firstjson.put("PageNum", arr.size() / json.getIntValue("NumPerPage"));
      } else {
        firstjson.put("PageNum", (arr.size() / json.getIntValue("NumPerPage")) + 1);
      }
    }
    firstjson.put("NowPage", json.getIntValue("Page"));
    firstjson.put("NumPerPage", json.getIntValue("NumPerPage"));

    temparr.add(firstjson);
    for (int i = (json.getIntValue("Page") - 1) * json.getIntValue("NumPerPage"); i < arr
        .size(); i++) {
      temparr.add(arr.get(i));
      if (i >= json.getIntValue("Page") * json.getIntValue("NumPerPage") - 1)
        break;
    }

    return temparr;
  }
}
