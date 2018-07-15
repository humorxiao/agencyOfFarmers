package scau.zxck.web.admin;

import com.alibaba.fastjson.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import scau.zxck.base.dao.mybatis.Conditions;
import scau.zxck.service.market.IGoodsInfoService;
import sun.awt.SunHints;

import java.util.List;

@Controller
@RequestMapping("/")
public class CommentsAction {
    @Autowired
    private IGoodsInfoService goodsInfoService;
    @RequestMapping(value = "getGoodsComments",method = RequestMethod.POST)
    public String getGoodsComments(String jsonStr){
        JSONArray jsonArray=new JSONArray();
        Conditions conditions=new Conditions();
        List list=goodsInfoService.list(conditions.eq(""))
    }
}
