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
import scau.zxck.service.sys.IUserLoginService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

 @Controller
 @RequestMapping("/")
public class PageAction {
  @Autowired
  private IUnionNewsService unionNewsService;
  @Autowired
  private IOrderInfoService orderInfoService;
  @Autowired
  private IUserLoginService userLoginService;
   @Autowired
   private HttpServletRequest request;
  @RequestMapping(value = "getBulletinNoPage", method = RequestMethod.POST)
  public void getBulletinNoPage( HttpServletResponse response) throws Exception {
    JSONArray jsonarr = new JSONArray();
    List list = unionNewsService.listAll();
    for (Iterator iter = ((java.util.List) list).iterator(); iter.hasNext();) {
      JSONObject temp = new JSONObject();
      UnionNews news = (UnionNews) iter.next();
      temp.put("News_PK", news.getId());
      temp.put("News_Title", news.getNews_title());
      temp.put("News_Text", news.getNews_text());
      temp.put("News_Time", news.getNews_time());
      if (news.getNews_mark() == 2) {
        jsonarr.add(temp);
      }
    }
    String r = jsonarr.toString();
    PrintWriter out=response.getWriter();
    out.flush();
    out.write(r);
    out.flush();
  }

  @RequestMapping(value = "getBulletin", method = RequestMethod.POST)
  public void getBulletin(HttpServletResponse response) throws Exception {
    String r="";
    BufferedReader br = request.getReader();
    String str, wholeStr = "";
    while((str = br.readLine()) != null){
      wholeStr += str;
    }
    String jsonStr=wholeStr;
    JSONObject pageInfo = JSONObject.parseObject(jsonStr);
    JSONArray jsonarr = new JSONArray();
    List list = unionNewsService.listAll();
    for (Iterator iter = ((java.util.List) list).iterator(); iter.hasNext();) {
      JSONObject temp = new JSONObject();
      UnionNews news = (UnionNews) iter.next();
      temp.put("News_PK", news.getId());
      temp.put("News_Title", news.getNews_title());
      temp.put("News_Text", news.getNews_text());
      temp.put("News_Time", news.getNews_time());
      if (news.getNews_mark() == 2) {
        jsonarr.add(temp);
      }
    }
     r = JSONArrayPaging(jsonarr, pageInfo).toString();
    PrintWriter out=response.getWriter();
    out.flush();
    out.write(r);
    out.flush();
  }
//  @Test
  @RequestMapping(value = "getAfterSaleOrderPaging", method = RequestMethod.POST)
  public void getAfterSaleOrderPaging(String jsonStr2,HttpServletResponse response) throws Exception {
    String r="";
    BufferedReader br = request.getReader();
    String str, wholeStr = "";
    while((str = br.readLine()) != null){
      wholeStr += str;
    }
    String jsonStr=wholeStr;
    JSONObject data = JSONObject.parseObject(jsonStr);
    int state = (int) Integer.parseInt(data.get("afterSale").toString());
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
  r = JSONArrayPaging(jsonarr, pageInfo).toString();
    PrintWriter out=response.getWriter();
    out.flush();
    out.write(r);
    out.flush();
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
