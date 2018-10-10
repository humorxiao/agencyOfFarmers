package scau.zxck.pay;
import com.github.wxpay.sdk.WXPayUtil;


import net.sf.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
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

    private WXPayConfigImpl config = WXPayConfigImpl.getInstance();

    private static final long serialVersionUID = 1L;

    public WXPayTradeReciver() throws Exception {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	try{
            // 获取推送结果
            InputStream in = request.getInputStream();
            int len =0;
            byte[] b = new byte[1024];
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            while((len = in.read(b)) != -1){
                bos.write(b, 0, len);
            }

            byte[] c = bos.toByteArray();
            String xmlStr =  new String(c,"utf-8");
            if (WXPayUtil.isSignatureValid(xmlStr, config.getKey())) {

                // 转换报文格式为map
                Map<String, String> result = WXPayUtil.xmlToMap(xmlStr);
                System.out.println(result);
                DataAccessProxy access=new DataAccessProxy();
                JSONObject data = new JSONObject();
                data.put("Order_ID", result.get("out_trade_no"));
                data = access.getIDOrder(data);
                System.out.println(data);
        		//支付宝交易号
        		String trade_no = result.get("transaction_id");
        		//String date1 = result.get("time_end");
        		//System.out.println((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(date1));
        		//付款金额
        		float total_amount = (float)Float.parseFloat(result.get("total_fee").toString());
        		float Order_PayPrice = (float)Float.parseFloat(data.get("Order_PayPrice").toString());
        		System.out.println(Order_PayPrice);
        		System.out.println(total_amount);
        		if (Math.round(Order_PayPrice * 100) == total_amount) {
        			data.put("Order_No", trade_no);
        			Date date=new Date();
        			data.put("Order_PayTime",(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(date));
        			data.put("Order_IsPay",true);
        			data.put("Order_State","6");
        			boolean ret=access.updateOrder(data);
        			if(ret == true) {
        				// 响应微信系统
        				HashMap<String, String> resp = new HashMap<>();
                        resp.put("return_code", "SUCCESS");
                        resp.put("return_msg", "OK");
                        response.getOutputStream().write(WXPayUtil.mapToXml(resp).getBytes());
        			}
        			else {
        				System.out.println("订单更新失败");
        			}
        		}
        		else {
    				System.out.println("微信通知结果价格与订单价格不同");
    			}
            }
            else {
				System.out.println("微信通知结果签名失败");
			}
        }catch(Exception e){
            e.printStackTrace();
        }

    }
}
