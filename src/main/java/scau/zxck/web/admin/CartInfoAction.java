package scau.zxck.web.admin;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import scau.zxck.entity.market.CartInfo;
import scau.zxck.entity.market.GoodsInfo;
import scau.zxck.service.market.ICartInfoService;
import scau.zxck.service.market.IGoodsInfoService;
import scau.zxck.serviceImpl.market.CartInfoService;
import scau.zxck.utils.FlushWriteUtil;
import scau.zxck.utils.ReadJSONUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/")
public class CartInfoAction {
  @Autowired
  private ICartInfoService cartInfoService;
  @Autowired
  private IGoodsInfoService goodsInfoService;
  @Autowired
  private HttpServletRequest request;
  @Autowired
  private HttpSession session;

  @RequestMapping(value = "getCart", method = RequestMethod.POST)
  public void getCartAction(HttpServletResponse response) throws Exception {
    String r = "";
    JSONObject data = new JSONObject();
    if (session.getAttribute("User_PK") != null) {
      data.put("User_PK", session.getAttribute("User_PK"));
      data.put("Cart_PK", session.getAttribute("Cart_PK"));
    } else {
      data.put("User_PK", "");
      data.put("Cart_PK", "");
    }
    CartInfo cartInfo = cartInfoService.findById(data.get("User_PK").toString());
    JSONObject temp = new JSONObject();
    temp.put("Cart_PK", cartInfo.getId());
    temp.put("Goods_List", cartInfo.getGoods_list());
    temp.put("Goods_Num", cartInfo.getGoods_num());
    JSONArray jsonArray = new JSONArray();
    String[] string = cartInfo.getGoods_list().split("#");
    String[] string2 = cartInfo.getGoods_num().split("#");
    for (int i = 0; i < string.length; i++) {
      GoodsInfo goodsInfo = goodsInfoService.findById(string[i]);
      JSONObject temp1 = new JSONObject();
      temp1.put("Goods_PK", goodsInfo.getId());
      temp1.put("Goods_Name", goodsInfo.getGoods_name());
      temp1.put("Goods_Type", goodsInfo.getGoods_type());
      temp1.put("Goods_Num", (int) Integer.parseInt(string2[i]));
      temp1.put("Goods_Price", goodsInfo.getGoods_price());
      temp1.put("Goods_Mark", goodsInfo.getGoods_mark());
      temp1.put("Goods_Show", goodsInfo.getGoods_show());
      temp1.put("Goods_Picture", goodsInfo.getGoods_picture());
      jsonArray.add(temp1);
    }
    r = jsonArray.toString();
    FlushWriteUtil.flushWrite(response, r);
//        System.out.println(r);
  }

  @RequestMapping(value = "alterCart", method = RequestMethod.POST)
  public void alterCart(HttpServletResponse response) throws Exception {
    String r = "";
    JSONObject data = ReadJSONUtil.readJSONStr(request);
    data.put("Cart_PK", session.getAttribute("User_PK").toString());
    CartInfo cartInfo = cartInfoService.findById(data.get("Cart_PK").toString());
    cartInfo.setGoods_list(data.get("Goods_List").toString());
    cartInfo.setGoods_num(data.
      get("Goods_Num").toString());
    try {
      cartInfoService.updateById(cartInfo);
      r = "{\"status\":1}";
    } catch (Exception e) {
      e.printStackTrace();
      r = "{\"status\":0}";
    }
    FlushWriteUtil.flushWrite(response, r);
  }

  @RequestMapping(value = "addCart", method = RequestMethod.POST)
  public void addCart(HttpServletResponse response) throws Exception {
    JSONObject data=ReadJSONUtil.readJSONStr(request);
    String goods_pk=data.get("Goods_PK").toString();
    int num=Integer.parseInt(data.get("Goods_Num").toString());
    String cart_pk=session.getAttribute("User_PK").toString();
    CartInfo cartInfo=cartInfoService.findById(cart_pk);
    String[] goods_list=cartInfo.getGoods_list().split("#");
    String[] goods_num=cartInfo.getGoods_num().split("#");
    int k=0;
    boolean flag=false;
    for(int i=0;i<goods_list.length;i++){
      if(goods_list[i].equals(goods_pk)){
        flag=true;
        k=i;
        break;
      }
    }
    if(flag){
      int later=Integer.parseInt(goods_num[k])+num;
      String s="";
      for(int i=0;i<goods_num.length;i++){
        if(i==k) s+=later+"#";
        else s+=goods_num[i]+"#";
      }
      cartInfo.setGoods_num(s);
    }else{
      String s=cartInfo.getGoods_list();
      String s2=cartInfo.getGoods_num();
      cartInfo.setGoods_list(s+goods_pk+"#");
      cartInfo.setGoods_num(s2+num+"#");
    }
    JSONObject temp=new JSONObject();
    try {
      cartInfoService.updateById(cartInfo);
      temp.put("status",1);
      FlushWriteUtil.flushWrite(response,temp.toString());
    }catch (Exception e){
      e.printStackTrace();
      temp.put("status",0);
      FlushWriteUtil.flushWrite(response,temp.toString());
    }
  }
}
