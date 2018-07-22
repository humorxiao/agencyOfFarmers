package scau.zxck.web.admin;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import scau.zxck.base.exception.BaseException;
import scau.zxck.entity.market.CartInfo;
import scau.zxck.service.market.ICartInfoService;
import scau.zxck.service.market.IGoodsInfoService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;

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
    @RequestMapping(value = "getCart",method = RequestMethod.POST)
    public String getCartAction(String jsonStr) throws Exception {
        String r="";
//        BufferedReader br = request.getReader();
//        String str, wholeStr = "";
//        while((str = br.readLine()) != null){
//            wholeStr += str;
//        }
//        jsonStr=wholeStr;
        JSONObject data=JSONObject.parseObject(jsonStr);
//        HttpSession session=request.getSession();
        if(session.getAttribute("User_PK")!=null){
            data.put("User_PK",session.getAttribute("User_PK"));
            data.put("Cart_PK",session.getAttribute("Cart_PK"));
        }
        else {
            data.put("User_PK","");
            data.put("Cart_PK","");
        }
        CartInfo cartInfo=cartInfoService.findById(data.get("User_PK").toString());
        JSONObject temp=new JSONObject();
        temp.put("Cart_PK",cartInfo.getId());
        temp.put("Goods_List",cartInfo.getGoods_list());
        temp.put("Goods_Num",cartInfo.getGoods_num());
        r=temp.toString();
//        System.out.println(r);
        return "success";
    }
    @RequestMapping(value = "alterCart",method = RequestMethod.POST)
//    @ResponseBody
    public String alterCart(String jsonStr) throws Exception {
        String r="";
//        BufferedReader br = request.getReader();
//        String str, wholeStr = "";
//        while((str = br.readLine()) != null){
//            wholeStr += str;
//        }
//        jsonStr=wholeStr;
        JSONObject data=JSONObject.parseObject(jsonStr);
        CartInfo cartInfo=cartInfoService.findById(data.get("Cart_PK").toString());
        cartInfo.setGoods_list(data.get("Goods_List").toString());
        cartInfo.setGoods_num(data.get("Goods_Num").toString());
        try {
            cartInfoService.updateById(cartInfo);
            r="{\"status\":1}";
        }catch (Exception e){
            e.printStackTrace();
            r="{\"status\":0}";
        }
        return r;
    }
}
