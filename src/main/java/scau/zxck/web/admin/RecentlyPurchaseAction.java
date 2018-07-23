package scau.zxck.web.admin;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import scau.zxck.base.dao.mybatis.Conditions;
import scau.zxck.base.exception.BaseException;
import scau.zxck.entity.market.OrderInfo;
import scau.zxck.entity.market.GoodsInfo;
import scau.zxck.entity.market.UserComments;
import scau.zxck.entity.sys.UserInfo;
import scau.zxck.service.market.IGoodsInfoService;
import scau.zxck.service.market.IOrderInfoService;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

@Controller
@RequestMapping("/")
public class RecentlyPurchaseAction {
  @Autowired
  private IOrderInfoService orderInfoService;
  @Autowired
  private IGoodsInfoService goodsInfoService;
  @Autowired
  private HttpServletRequest request;
  @Autowired
  private HttpSession session;
  @RequestMapping(value = "recentlyPurchase",method = RequestMethod.POST)
  public String recentlyPurchase(String jsonStr) throws Exception{
    String r="";
//    jsonStr="{\"User_PK\":\"100003\"}";
    BufferedReader br = request.getReader();
      String str, wholeStr = "";
      while((str = br.readLine()) != null){
          wholeStr += str;
      }
      jsonStr=wholeStr;
    JSONObject data = JSONObject.parseObject(jsonStr);
    Conditions conditions=new Conditions();
    List list=orderInfoService.list(conditions.eq("user_info_id",data.get("User_PK").toString()).and().eq("order_isPay",1));
//    System.out.println(cartInfo.getGoods_list());
    ArrayList<String> arrayList=new ArrayList<>();
    for (Iterator iter = ((java.util.List) list).iterator(); iter.hasNext();){
      OrderInfo orderInfo=(OrderInfo)iter.next();
      String[] ss=orderInfo.getGoods_list().split("#");
      for(int i=0;i<ss.length;i++){
        arrayList.add(ss[i]);
      }
    }
    JSONArray jsonArray=new JSONArray();
    if(arrayList.size()>=4){
      for(int i=arrayList.size()-1;i>=arrayList.size()-4;i--) {
        GoodsInfo goodsInfo = goodsInfoService.findById(arrayList.get(i));
        JSONObject temp=new JSONObject();
        temp.put("Goods_PK", goodsInfo.getId());
        temp.put("Goods_Name", goodsInfo.getGoods_name());
        temp.put("Goods_Type", goodsInfo.getGoods_type());
        temp.put("Goods_Num", goodsInfo.getGoods_num());
        temp.put("Goods_Price", goodsInfo.getGoods_price());
        temp.put("Goods_Mark", goodsInfo.getGoods_mark());
        temp.put("Goods_Show", goodsInfo.getGoods_show());
        temp.put("Goods_Picture", goodsInfo.getGoods_picture());
        temp.put("Goods_Reverse_1",goodsInfo.getGoods_reserve_1());
        jsonArray.add(temp);
      }
      r=jsonArray.toString();
    }
    else{
      int mark=1;
      Conditions conditions1=new Conditions();
      List list1=goodsInfoService.list(conditions1.eq("goods_show",mark));
      for(int i=0;i<4;i++){
        GoodsInfo goodsInfo=(GoodsInfo)list1.get(i);
        JSONObject temp=new JSONObject();
        temp.put("Goods_PK", goodsInfo.getId());
        temp.put("Goods_Name", goodsInfo.getGoods_name());
        temp.put("Goods_Type", goodsInfo.getGoods_type());
        temp.put("Goods_Num", goodsInfo.getGoods_num());
        temp.put("Goods_Price", goodsInfo.getGoods_price());
        temp.put("Goods_Mark", goodsInfo.getGoods_mark());
        temp.put("Goods_Show", goodsInfo.getGoods_show());
        temp.put("Goods_Picture", goodsInfo.getGoods_picture());
        temp.put("Goods_Reverse_1",goodsInfo.getGoods_reserve_1());
        jsonArray.add(temp);
      }
      r=jsonArray.toString();
    }
    System.out.println(r);
    return "success";
  }
}
