package scau.zxck.web.admin;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import scau.zxck.base.dao.mybatis.Conditions;
import scau.zxck.base.exception.BaseException;
import scau.zxck.entity.market.GoodsInfo;
import scau.zxck.service.market.IGoodsInfoService;

import java.util.Iterator;
import java.util.List;

@Controller
@RequestMapping("/")
public class GoodsInfoAction {
    @Autowired
    private IGoodsInfoService goodsInfoService;
    @RequestMapping(value = "getOneGood",method = RequestMethod.POST)
    public String getOneGood(String jsonStr) throws BaseException {
        JSONObject data=JSONObject.parseObject(jsonStr);
        String r="";
        GoodsInfo goods=goodsInfoService.findById(data.get("Goods_PK").toString());
        JSONObject temp=new JSONObject();
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
        r=temp.toString();
        return null;
    }
    @RequestMapping(value = "getAllTypeGoods",method = RequestMethod.POST)
    public String getAllTypesGoods(String jsonStr) throws BaseException {
        long startTime=System.currentTimeMillis();
        JSONObject data=JSONObject.parseObject(jsonStr);
        String r="";
        List list=goodsInfoService.listAll();
        JSONArray jsonarr = new JSONArray();
        for(Iterator iter = ((java.util.List) list).iterator(); iter.hasNext();)
        {
            JSONObject temp = new JSONObject();
            GoodsInfo goods =(GoodsInfo)iter.next();

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
            jsonarr.add(temp);

        }
        r=jsonarr.toString();
        long endTime=System.currentTimeMillis();
        System.out.println("Running time: "+(endTime-startTime)+"ms");
        return null;
    }
    @RequestMapping(value = "getTypeGoods",method = RequestMethod.POST)
    public String getTypeGoods(String jsonStr) throws BaseException {
        long startTime=System.currentTimeMillis();
        JSONObject data=JSONObject.parseObject(jsonStr);
        String r="";
        Conditions conditions=new Conditions();
        List list=goodsInfoService.list(conditions.eq("Goods_Type",data.get("Goods_Type").toString()));
        JSONArray jsonArray=new JSONArray();
        for(Iterator iter = ((java.util.List) list).iterator(); iter.hasNext();)
        {
            JSONObject temp = new JSONObject();
            GoodsInfo goods =(GoodsInfo)iter.next();

            temp.put("Goods_PK", goods.getId());
            temp.put("Goods_Name", goods.getGoods_name());
            temp.put("Goods_Type", goods.getGoods_type());
            temp.put("Goods_Num", goods.getGoods_num());
            temp.put("Goods_Price", goods.getGoods_price());
            temp.put("Goods_Mark", goods.getGoods_mark());
            temp.put("Goods_Show", goods.getGoods_show());
            temp.put("Goods_Picture", goods.getGoods_picture());

            jsonArray.add(temp);
        }
        r=jsonArray.toString();
        long endTime=System.currentTimeMillis();
        System.out.println("Running time: "+(endTime-startTime)+"ms");
        return null;
    }
    @RequestMapping(value = "getSpecialGoods", method = RequestMethod.POST)
    public String getSpecialGoods(String jsonStr) throws BaseException {
        long starttime=System.currentTimeMillis();
        JSONObject data=JSONObject.parseObject(jsonStr);
        String r="";
        Conditions conditions=new Conditions();
        Integer mark=Integer.parseInt(data.get("Goods_Mark").toString());
        List list=goodsInfoService.list(conditions.eq("goods_show",mark));
        JSONArray jsonArray=new JSONArray();
        for(Iterator iter = ((java.util.List) list).iterator(); iter.hasNext();)
        {
            JSONObject temp = new JSONObject();
            GoodsInfo goods =(GoodsInfo)iter.next();

            temp.put("Goods_PK", goods.getId());
            temp.put("Goods_Name", goods.getGoods_name());
            temp.put("Goods_Type", goods.getGoods_type());
            temp.put("Goods_Num", goods.getGoods_num());
            temp.put("Goods_Price", goods.getGoods_price());
            temp.put("Goods_Mark", goods.getGoods_mark());
            temp.put("Goods_Show", goods.getGoods_show());
            temp.put("Goods_Picture", goods.getGoods_picture());
            temp.put("Goods_Reserve_1", goods.getGoods_reserve_1());
            jsonArray.add(temp);
        }
        r=jsonArray.toString();
        long endtime=System.currentTimeMillis();
        System.out.println("Running time: "+(endtime-starttime)+"ms");
        return null;
    }
    @RequestMapping(value = "getDiscountGoods",method = RequestMethod.POST)
    public String getDiscountGoods(String jsonStr) throws BaseException {
        long starttime=System.currentTimeMillis();
        JSONObject data=JSONObject.parseObject(jsonStr);
        String r="";
        Conditions conditions=new Conditions();
        Integer mark=Integer.parseInt(data.get("Goods_Mark").toString());
        List list=goodsInfoService.list(conditions.eq("goods_show",mark));
        JSONArray jsonArray=new JSONArray();
        for(Iterator iter = ((java.util.List) list).iterator(); iter.hasNext();)
        {
            JSONObject temp = new JSONObject();
            GoodsInfo goods =(GoodsInfo)iter.next();

            temp.put("Goods_PK", goods.getId());
            temp.put("Goods_Name", goods.getGoods_name());
            temp.put("Goods_Type", goods.getGoods_type());
            temp.put("Goods_Num", goods.getGoods_num());
            temp.put("Goods_Price", goods.getGoods_price());
            temp.put("Goods_Mark", goods.getGoods_mark());
            temp.put("Goods_Show", goods.getGoods_show());
            temp.put("Goods_Picture", goods.getGoods_picture());
            temp.put("Goods_Reserve_1", goods.getGoods_reserve_1());
            jsonArray.add(temp);
        }
        r=jsonArray.toString();
        long endtime=System.currentTimeMillis();
        System.out.println("Running time: "+(endtime-starttime)+"ms");
        return null;
    }
}
