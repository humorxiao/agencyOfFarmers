package scau.zxck.web.admin;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import scau.zxck.base.dao.mybatis.Conditions;
import scau.zxck.dao.market.GoodsInfoDao;
import scau.zxck.entity.market.GoodsInfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.crypto.Data;
import java.util.Iterator;
import java.util.List;

@Controller
@RequestMapping("/")
public class GetLikesGoodsAction {
  @Autowired
  private GoodsInfoDao goodsInfoDao;

  @RequestMapping(value = "getLikesGoods", method = RequestMethod.POST)
  public String getLikesAction(String jsonStr) throws Exception {
    JSONObject data = JSONObject.parseObject(jsonStr);
    String r = "";
    HttpServletRequest request =
        ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    HttpSession session = request.getSession();
    String likes = request.getParameter("likes");
    likes = java.net.URLDecoder.decode(likes, "utf-8");
    Conditions conditions = new Conditions();
    JSONArray jsonarr = new JSONArray();
    if (likes != null) {
      List list = goodsInfoDao.list(conditions.like("goods_name", data.get("Goods_Name")));
      for (Iterator iter = ((java.util.List) list).iterator(); iter.hasNext();) {
        JSONObject temp = new JSONObject();
        GoodsInfo goods = (GoodsInfo) iter.next();

        temp.put("Goods_PK", goods.getId());
        temp.put("Goods_Name", goods.getGoods_name());
        temp.put("Goods_Type", goods.getGoods_type());
        temp.put("Goods_Num", goods.getGoods_num());
        temp.put("Goods_Price", goods.getGoods_price());
        temp.put("Goods_Mark", goods.getGoods_mark());
        temp.put("Goods_Show", goods.getGoods_show());
        temp.put("Goods_Picture", goods.getGoods_picture());
        jsonarr.add(temp);
      }
      r=jsonarr.toString();
    }
    return null;
  }
}
