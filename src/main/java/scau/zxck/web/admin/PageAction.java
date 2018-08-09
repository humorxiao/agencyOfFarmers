package scau.zxck.web.admin;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import scau.zxck.base.dao.mybatis.Conditions;
import scau.zxck.entity.market.OrderInfo;
import scau.zxck.entity.market.UnionNews;
import scau.zxck.service.market.IOrderInfoService;
import scau.zxck.service.market.IUnionNewsService;
import scau.zxck.service.sys.IUserLoginService;
import scau.zxck.utils.FlushWriteUtil;
import scau.zxck.utils.JSONArrayPagingUtil;
import scau.zxck.utils.ReadJSONUtil;

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
  public void getBulletinNoPage(HttpServletResponse response) throws Exception {
    JSONArray jsonarr = new JSONArray();
    List list = unionNewsService.listAll();
    for (Iterator iter = ((java.util.List) list).iterator(); iter.hasNext(); ) {
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
    FlushWriteUtil.flushWrite(response, r);
  }

  @RequestMapping(value = "getBulletin", method = RequestMethod.POST)
  public void getBulletin(HttpServletResponse response) throws Exception {
    JSONObject pageInfo = ReadJSONUtil.readJSONStr(request);
    JSONArray jsonarr = new JSONArray();
    List list = unionNewsService.listAll();
    for (Iterator iter = ((java.util.List) list).iterator(); iter.hasNext(); ) {
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
    String r = JSONArrayPagingUtil.JSONArrayPaging(jsonarr, pageInfo).toString();
    FlushWriteUtil.flushWrite(response, r);
  }

  //  @Test
  @RequestMapping(value = "getAfterSaleOrderPaging", method = RequestMethod.POST)
  public void getAfterSaleOrderPaging(String jsonStr2, HttpServletResponse response) throws Exception {
    JSONObject data = ReadJSONUtil.readJSONStr(request);
    int state = (int) Integer.parseInt(data.get("afterSale").toString());
    JSONObject pageInfo = JSONObject.parseObject(jsonStr2);
    JSONArray jsonarr = new JSONArray();
    JSONArray jsonarr1 = new JSONArray();
    Conditions conditions = new Conditions();
    List list = orderInfoService.list(conditions.eq("order_aftersale", state));
    for (Iterator iter = ((java.util.List) list).iterator(); iter.hasNext(); ) {
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
    String r = JSONArrayPagingUtil.JSONArrayPaging(jsonarr, pageInfo).toString();
    FlushWriteUtil.flushWrite(response, r);
  }
}