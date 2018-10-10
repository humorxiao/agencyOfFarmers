package scau.zxck.pay;

import com.github.wxpay.sdk.WXPayUtil;


import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import scau.zxck.base.dao.mybatis.Conditions;
import scau.zxck.entity.market.OrderInfo;
import scau.zxck.entity.sys.UserInfo;
import scau.zxck.service.market.IOrderInfoService;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 接受微信系统统一下单异步推送
 *
 * @author Zero Lee
 * @date 2017-12-20
 * @time 14:43
 */

public class WXPayTradeReciver extends HttpServlet {
  @Autowired
  private IOrderInfoService orderInfoService;
  private WXPayConfigImpl config = WXPayConfigImpl.getInstance();

  private static final long serialVersionUID = 1L;

  public WXPayTradeReciver() throws Exception {
    super();
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    try {
      this.doPost(request, response);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
    try {
      // 获取推送结果
      InputStream in = request.getInputStream();
      int len = 0;
      byte[] b = new byte[1024];
      ByteArrayOutputStream bos = new ByteArrayOutputStream();
      while ((len = in.read(b)) != -1) {
        bos.write(b, 0, len);
      }

      byte[] c = bos.toByteArray();
      String xmlStr = new String(c, "utf-8");
      if (WXPayUtil.isSignatureValid(xmlStr, config.getKey())) {

        // 转换报文格式为map
        Map<String, String> result = WXPayUtil.xmlToMap(xmlStr);
        System.out.println(result);
//                DataAccessProxy access=new DataAccessProxy();
        JSONObject data = new JSONObject();
        data.put("Order_ID", result.get("out_trade_no"));
        Conditions conditions = new Conditions();
        List list = orderInfoService.list(conditions.eq("order_id", data.get("Order_ID").toString()));
        OrderInfo order = (OrderInfo) list.get(0);
        data.put("Order_PK", order.getId());
        data.put("User_PK", order.getUser_info_id());
        data.put("Order_ID", order.getOrder_id());
        data.put("Order_No", order.getOrder_no());
        data.put("Goods_List", order.getGoods_list());
        data.put("Goods_Num", order.getGoods_num());
        data.put("Goods_Prices", order.getGoods_prices());
        data.put("Order_Time", order.getOrder_time());
        data.put("Order_IsPay", order.isOrder_ispay());
        if (order.getOrder_paytime().equals(new String("0001-1-1 1:01:01"))) {
          data.put("Order_PayTime", "");
        } else {
          data.put("Order_PayTime", order.getOrder_paytime());
        }
        data.put("Order_PayPrice", order.getOrder_payprice());
        data.put("Order_State", order.getOrder_state());
        data.put("Order_TrackNum", order.getOrder_tracknum());
        data.put("Order_Company", order.getOrder_company());
        data.put("Order_Website", order.getOrder_website());
        data.put("Order_Aftersale", order.getOrder_aftersale());
        data.put("Order_Reserve_1", order.getOrder_reserve_1());
//                data = access.getIDOrder(data);
        System.out.println(data);
        //支付宝交易号
        String trade_no = result.get("transaction_id");
        //String date1 = result.get("time_end");
        //System.out.println((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(date1));
        //付款金额
        float total_amount = (float) Float.parseFloat(result.get("total_fee").toString());
        float Order_PayPrice = (float) Float.parseFloat(data.get("Order_PayPrice").toString());
        System.out.println(Order_PayPrice);
        System.out.println(total_amount);
        if (Math.round(Order_PayPrice * 100) == total_amount) {
          data.put("Order_No", trade_no);
          Date date = new Date();
          data.put("Order_PayTime", (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(date));
          data.put("Order_IsPay", true);
          data.put("Order_State", "6");
//          boolean ret = access.updateOrder(data);
          Conditions conditions1=new Conditions();
          List list1=orderInfoService.list(conditions1.eq("order_id",data.get("Order_ID").toString()));
          OrderInfo temp=(OrderInfo)list1.get(0);
          temp.setUser_info_id(data.get("User_PK").toString());
          temp.setOrder_id(data.get("Order_ID").toString());
          temp.setOrder_no(data.get("Order_No").toString());
          temp.setGoods_list(data.get("Goods_List").toString());
          temp.setGoods_num(data.get("Goods_Num").toString());
          temp.setGoods_prices(data.get("Goods_Prices").toString());
          temp.setOrder_time(data.get("Order_Time").toString());
          temp.setOrder_ispay((boolean)data.get("Order_IsPay"));
          if(!data.get("Order_PayTime").equals(new String(""))){
            temp.setOrder_paytime(data.get("Order_PayTime").toString());
          }
          temp.setOrder_payprice((float)Float.parseFloat(data.get("Order_PayPrice").toString()));
          temp.setOrder_state((int)Integer.parseInt(data.get("Order_State").toString()));
          temp.setOrder_tracknum(data.get("Order_TrackNum").toString());
          temp.setOrder_company(data.get("Order_Company").toString());
          temp.setOrder_website(data.get("Order_Website").toString());
          temp.setOrder_aftersale((int)Integer.parseInt(data.get("Order_Aftersale").toString()));
          temp.setOrder_reserve_1(data.get("Order_Reserve_1").toString());
          temp.setUserInfo(new UserInfo());
          try {
            // 响应微信系统
            orderInfoService.updateById(temp);
            HashMap<String, String> resp = new HashMap<>();
            resp.put("return_code", "SUCCESS");
            resp.put("return_msg", "OK");
            response.getOutputStream().write(WXPayUtil.mapToXml(resp).getBytes());
          }catch (Exception e){
            e.printStackTrace();
            System.out.println("订单更新失败");
          }
        } else {
          System.out.println("微信通知结果价格与订单价格不同");
        }
      } else {
        System.out.println("微信通知结果签名失败");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

  }
}
