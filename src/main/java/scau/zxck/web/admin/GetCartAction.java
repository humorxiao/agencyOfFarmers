package scau.zxck.web.admin;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import scau.zxck.base.exception.BaseException;
import scau.zxck.dao.market.CartInfoDao;
import scau.zxck.entity.market.CartInfo;
import scau.zxck.service.market.ICartInfoService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
public class GetCartAction {
    @Autowired
    private ICartInfoService cartInfoService;
    @RequestMapping(value = "getCart",method = RequestMethod.POST)
    public String getCartAction(String jsonStr) throws BaseException {
        String r="";
        JSONObject data=JSONObject.parseObject(jsonStr);
        HttpServletRequest request =
                ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session=request.getSession();
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
        return null;
    }
}
