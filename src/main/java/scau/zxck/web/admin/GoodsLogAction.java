package scau.zxck.web.admin;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import scau.zxck.base.dao.mybatis.Conditions;
import scau.zxck.entity.market.GoodsInfo;
import scau.zxck.entity.market.GoodsLog;
import scau.zxck.service.market.IGoodsInfoService;
import scau.zxck.service.market.IGoodsLogService;
import scau.zxck.utils.FlushWriteUtil;
import scau.zxck.utils.JSONArrayPagingUtil;
import scau.zxck.utils.ReadJSONUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Controller
@RequestMapping("/")
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration("classpath:config/spring/spring.xml")
public class GoodsLogAction {
    @Autowired
    private IGoodsLogService goodsLogService;
    @Autowired
    private IGoodsInfoService goodsInfoService;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private HttpSession session;

    @RequestMapping(value = "getAllGoodsLogPaging", method = RequestMethod.POST)

    public void getAllGoodsLogPaging( HttpServletResponse response) throws Exception {

        JSONObject pageInfo= ReadJSONUtil.readJSONStr(request);
        List list = goodsLogService.listAll();
        JSONArray jsonarr = new JSONArray();
        for (Iterator iter = ((java.util.List) list).iterator(); iter.hasNext(); ) {
            JSONObject temp = new JSONObject();
            GoodsLog gl = (GoodsLog) iter.next();
            temp.put("GL_PK", gl.getId());
            temp.put("Goods_PK", gl.getGoods_info_id());
            temp.put("Goods_In", gl.getGoods_in());
            temp.put("Goods_Out", gl.getGoods_out());
            temp.put("Goods_PriceChange", gl.getGoods_pricechange());
            temp.put("GL_Time", gl.getGl_time());
            jsonarr.add(temp);
        }
        String r = JSONArrayPagingUtil.JSONArrayPaging(jsonarr, pageInfo).toString();
        FlushWriteUtil.flushWrite(response,r);
    }

    @RequestMapping(value = "addGoodsLog", method = RequestMethod.POST)
    public void addGoodsLog(HttpServletResponse response) throws Exception {
        JSONObject data= ReadJSONUtil.readJSONStr(request);
        JSONObject temp = new JSONObject();
        Conditions conditions = new Conditions();
        List list = goodsInfoService.list(conditions.eq("id", data.get("Goods_PK").toString()));
        if (!list.isEmpty()) {
            GoodsInfo goods = (GoodsInfo) list.get(0);
            temp.put("Goods_PK", goods.getId());
            temp.put("Goods_Name", goods.getGoods_name());
            temp.put("Goods_Type", goods.getGoods_type());
            temp.put("Goods_Num", goods.getGoods_num());
            temp.put("Goods_Price", goods.getGoods_price());
            temp.put("Goods_Mark", goods.getGoods_mark());
            temp.put("Goods_Show", goods.getGoods_show());
            temp.put("Goods_Picture", goods.getGoods_picture());
            temp.put("Goods_Season", goods.getGoods_season());
            temp.put("Goods_Blossom", goods.getGoods_blossom());
            temp.put("Goods_Fruit", goods.getGoods_fruit());
            temp.put("Goods_Mature", goods.getGoods_mature());
            temp.put("Goods_Expiration", goods.getGoods_expiration());
            temp.put("Goods_Reserve_1", goods.getGoods_reserve_1());
            temp.put("Goods_Reserve_2", goods.getGoods_reserve_2());
        }
        int num = (int) temp.get("Goods_Num");
        float price = (float) temp.get("Goods_Price");
        if (Integer.parseInt(String.valueOf(data.get("Goods_In"))) != 0) {
            num += Integer.parseInt(String.valueOf(data.get("Goods_In")));
        }
        if (Integer.parseInt(String.valueOf(data.get("Goods_Out"))) != 0) {
            num -= Integer.parseInt(String.valueOf(data.get("Goods_Out")));
        }
        if (Float.parseFloat(String.valueOf(data.get("Goods_PriceChange"))) != 0) {
            price = Float.parseFloat(String.valueOf(data.get("Goods_PriceChange")));
        }
        temp.put("Goods_Num", num);
        temp.put("Goods_Price", price);
        GoodsInfo tempx = goodsInfoService.findById(temp.get("Goods_PK").toString());
        tempx.setGoods_name(temp.get("Goods_Name").toString());
        tempx.setGoods_type((int) Integer.parseInt(temp.get("Goods_Type").toString()));
        tempx.setGoods_num((int) Integer.parseInt(temp.get("Goods_Num").toString()));
        tempx.setGoods_price((float) Float.parseFloat(temp.get("Goods_Price").toString()));
        tempx.setGoods_mark(temp.get("Goods_Mark").toString().charAt(0));
        tempx.setGoods_show(temp.get("Goods_Show").toString().charAt(0));
        tempx.setGoods_picture(temp.get("Goods_Picture").toString());
        tempx.setGoods_season((int) Integer.parseInt(temp.get("Goods_Season").toString()));
        tempx.setGoods_blossom(temp.get("Goods_Blossom").toString());
        tempx.setGoods_fruit(temp.get("Goods_Fruit").toString());
        tempx.setGoods_mature(temp.get("Goods_Mature").toString());
        tempx.setGoods_expiration(temp.get("Goods_Expiration").toString());
        goodsInfoService.updateById(tempx);
        data.put("GL_Time", (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date()));
        GoodsLog tempy = new GoodsLog();
        tempy.setGoods_info_id(data.get("Goods_PK").toString());
        tempy.setGoods_in((int) Integer.parseInt(data.get("Goods_In").toString()));
        tempy.setGoods_out((int) Integer.parseInt(data.get("Goods_Out").toString()));
        tempy.setGoods_pricechange((float) Float.parseFloat(data.get("Goods_PriceChange").toString()));
        tempy.setGl_time((String) data.get("GL_Time"));
        boolean ret;
        try {
            goodsLogService.add(tempy);
            ret = true;
        } catch (Exception e) {
            e.printStackTrace();
            ret = false;
        }
        String r = "";
        if (ret) {
            r = "{\"status\":1}";
        } else {
            r = "{\"status\":0}";
        }
        FlushWriteUtil.flushWrite(response,r);
    }
}
