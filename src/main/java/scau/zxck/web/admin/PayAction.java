package scau.zxck.web.admin;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.internal.util.AlipaySignature;
import com.github.wxpay.sdk.WXPayUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.alipay.api.*;
import com.alipay.api.request.*;
import scau.zxck.entity.market.OrderInfo;
import scau.zxck.pay.AlipayConfig;
import scau.zxck.pay.WXPayConfigImpl;
import scau.zxck.pay.WXPaySender;
import scau.zxck.service.market.IOrderInfoService;
import scau.zxck.utils.FlushWriteUtil;
import scau.zxck.utils.ReadJSONUtil;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Controller
@RequestMapping("/")
public class PayAction {
  @Autowired
  private IOrderInfoService orderInfoService;
  @Autowired
  private HttpServletRequest request;

  @RequestMapping(value = "alipay", method = RequestMethod.POST)
  public void alipay(HttpServletResponse response) throws Exception {
    AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);
    JSONObject data = ReadJSONUtil.readJSONStr(request);
    //设置请求参数
    AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
    alipayRequest.setReturnUrl(AlipayConfig.return_url);
    alipayRequest.setNotifyUrl(AlipayConfig.notify_url);

    //商户订单号，商户网站订单系统中唯一订单号，必填
    String out_trade_no = data.get("Order_ID").toString();
    //付款金额，必填
    String total_amount = data.get("Order_PayPrice").toString();
    //订单名称，必填
    String subject = data.get("Order_PK").toString();
    alipayRequest.setBizContent("{\"out_trade_no\":\"" + out_trade_no + "\","
      + "\"total_amount\":\"" + total_amount + "\","
      + "\"subject\":\"" + subject + "\","
      + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");

    //若想给BizContent增加其他可选请求参数，以增加自定义超时时间参数timeout_express来举例说明
    //alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
    //		+ "\"total_amount\":\""+ total_amount +"\","
    //		+ "\"subject\":\""+ subject +"\","
    //		+ "\"body\":\""+ body +"\","
    //		+ "\"timeout_express\":\"10m\","
    //		+ "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
    //请求参数可查阅【电脑网站支付的API文档-alipay.trade.page.pay-请求参数】章节
    /*System.out.println("{\"out_trade_no\":\""+ out_trade_no +"\","
      + "\"total_amount\":\""+ total_amount +"\","
      + "\"subject\":\""+ subject +"\","
      + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");*/
    //请求
    String result = alipayClient.pageExecute(alipayRequest).getBody();
    //输出
    final PrintWriter out = response.getWriter();
    out.println(result);
  }

  @RequestMapping(value = "wxpay", method = RequestMethod.POST)
  public void wxpay(HttpServletResponse response) throws Exception {
    JSONObject data = ReadJSONUtil.readJSONStr(request);
    JSONObject object = new JSONObject();
    object.put("product", data.get("body").toString());
    object.put("Order_ID", data.get("out_trade_no").toString());
    object.put("Order_PayPrice", data.get("total_fee").toString());
    object.put("ip", data.get("ip").toString());
    WXPaySender wxPaySender = WXPaySender.getInstance();
    String result = wxPaySender.doUnifiedOrder(object).toString();
    FlushWriteUtil.flushWrite(response, result);
  }

  @RequestMapping(value = "alipayreturn", method = RequestMethod.POST)
  public void alipayreturn(HttpServletResponse response) throws Exception {
    Map<String, String> params = new HashMap<String, String>();
    Map<String, String[]> requestParams = request.getParameterMap();
    for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
      String name = (String) iter.next();
      String[] values = (String[]) requestParams.get(name);
      String valueStr = "";
      for (int i = 0; i < values.length; i++) {
        valueStr = (i == values.length - 1) ? valueStr + values[i]
          : valueStr + values[i] + ",";
      }
      //乱码解决，这段代码在出现乱码时使用
      //valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
      params.put(name, valueStr);
    }
    boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type); //调用SDK验证签名

    //——请在这里编写您的程序（以下代码仅作参考）——
    if (signVerified) {

      //商户订单号
      String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");

      //支付宝交易号
      String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");

      //付款金额
      String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"), "UTF-8");

      JSONObject data = new JSONObject();

      OrderInfo orderInfo = orderInfoService.findById(out_trade_no);
      orderInfo.setOrder_no(trade_no);

      /*DataAccessProxy access=new DataAccessProxy();
      JSONObject order=access.getIDOrder(data);*/
      Date date = new Date();
      orderInfo.setOrder_paytime((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(date));
      orderInfo.setOrder_ispay(true);
      orderInfo.setOrder_state(6);

      orderInfoService.updateById(orderInfo);
      System.out.println(request.getContextPath());
      response.sendRedirect("/");


      //——请在这里编写您的程序（以上代码仅作参考）——

    }
  }

  @RequestMapping(value = "WXPayTradeReciver", method = RequestMethod.POST)
  public void WXPayTradeReciver(HttpServletResponse response) throws Exception {
    WXPayConfigImpl config = WXPayConfigImpl.getInstance();
    long serialVersionUID = 1L;
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

      final OrderInfo orderInfo = orderInfoService.findById(result.get("out_trade_no").toString());


      //支付宝交易号

      //String date1 = result.get("time_end");
      //System.out.println((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(date1));
      //付款金额
      float total_amount = (float) Float.parseFloat(result.get("total_fee").toString());
      float Order_PayPrice = orderInfo.getOrder_payprice();
      System.out.println(Order_PayPrice);
      System.out.println(total_amount);
      if (Math.round(Order_PayPrice * 100) == total_amount) {
        orderInfo.setOrder_state(6);
        orderInfo.setOrder_paytime((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date()));
        orderInfo.setOrder_no(result.get("transaction_id").toString());
        orderInfo.setOrder_ispay(true);
        orderInfoService.updateById(orderInfo);
      }
    }
  }

  @RequestMapping(value = "getwxOrderStatus", method = RequestMethod.POST)
  public void getwxOrderStatus(HttpServletResponse response) throws Exception {
    JSONObject data = ReadJSONUtil.readJSONStr(request);
    WXPaySender hdwx = WXPaySender.getInstance();
    String result = hdwx.doOrderQuery(data.get("Out_Trade_No").toString()).toString();
    FlushWriteUtil.flushWrite(response, result);
  }

}
