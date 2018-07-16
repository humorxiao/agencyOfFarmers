package scau.zxck.web.admin;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import scau.zxck.base.exception.BaseException;
import scau.zxck.entity.market.GoodsLog;
import scau.zxck.service.market.IGoodsLogService;

import java.util.Iterator;
import java.util.List;

@Controller
@RequestMapping("/")
public class GoodsLogAction {
  @Autowired
  private IGoodsLogService goodsLogService;

  @RequestMapping(value = "getAllGoodsLogPaging", method = RequestMethod.POST)
  public String getAllGoodsLogPaging(String jsonStr) throws BaseException {
    List list = goodsLogService.listAll();
    JSONArray jsonarr = new JSONArray();
    for (Iterator iter = ((java.util.List) list).iterator(); iter.hasNext();) {
      JSONObject temp = new JSONObject();
      GoodsLog gl = (GoodsLog) iter.next();

      temp.put("GL_PK", gl.getId());
      temp.put("Goods_PK", gl.getGoods_info_id());
      temp.put("Goods_In", gl.getGoods_in());
      temp.put("Goods_Out", gl.getGoods_out());
      temp.put("Goods_PriceChange", gl.getGoods_pricechange());
      temp.put("GL_Time", gl.getGl_time().toLocaleString());

      jsonarr.add(temp);
    }
    String r=jsonarr.toString();
    return "success";
  }
}
