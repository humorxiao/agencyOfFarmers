package scau.zxck.web.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import scau.zxck.base.exception.BaseException;
import scau.zxck.entity.market.CartInfo;
import scau.zxck.entity.sys.AdminInfo;
import scau.zxck.serviceImpl.market.CartInfoService;

@Controller
@RequestMapping("/")
public class CartInfoAction {
    @Autowired
    private CartInfoService cartInfoService;
    @RequestMapping(value = "login2", method = RequestMethod.POST)
    public String login(String goods_list, String goods_num) throws BaseException {
        CartInfo cartInfo=new CartInfo();
        cartInfo.setGoods_list(goods_list);
        cartInfo.setGoods_num(goods_num);
        cartInfoService.add(cartInfo);
        return "success";
    }

}
