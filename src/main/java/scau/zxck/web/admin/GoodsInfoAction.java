package scau.zxck.web.admin;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import scau.zxck.base.dao.mybatis.Conditions;
import scau.zxck.base.exception.BaseException;
import scau.zxck.entity.market.GoodsInfo;
import scau.zxck.service.market.IGoodsInfoService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Iterator;
import java.util.List;

@Controller
@RequestMapping("/")
public class GoodsInfoAction {
    @Autowired
    private IGoodsInfoService goodsInfoService;

    @RequestMapping(value = "getOneGood", method = RequestMethod.POST)
    public String getOneGood(String jsonStr) throws BaseException {
        JSONObject data = JSONObject.parseObject(jsonStr);
        String r = "";
        GoodsInfo goods = goodsInfoService.findById(data.get("Goods_PK").toString());
        JSONObject temp = new JSONObject();
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
        r = temp.toString();
        return null;
    }

    @RequestMapping(value = "getAllTypeGoods", method = RequestMethod.POST)
    public String getAllTypesGoods(String jsonStr) throws BaseException {
        long startTime = System.currentTimeMillis();
        JSONObject data = JSONObject.parseObject(jsonStr);
        String r = "";
        List list = goodsInfoService.listAll();
        JSONArray jsonarr = new JSONArray();
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
            temp.put("Goods_Season", goods.getGoods_season());
            temp.put("Goods_Blossom", goods.getGoods_blossom());
            temp.put("Goods_Fruit", goods.getGoods_fruit());
            temp.put("Goods_Mature", goods.getGoods_mature());
            temp.put("Goods_Expiration", goods.getGoods_expiration());
            temp.put("Goods_Reserve_1", goods.getGoods_reserve_1());
            jsonarr.add(temp);

        }
        r = jsonarr.toString();
        long endTime = System.currentTimeMillis();
        System.out.println("Running time: " + (endTime - startTime) + "ms");
        return null;
    }

    @RequestMapping(value = "getTypeGoods", method = RequestMethod.POST)
    public String getTypeGoods(String jsonStr) throws BaseException {
        long startTime = System.currentTimeMillis();
        JSONObject data = JSONObject.parseObject(jsonStr);
        String r = "";
        Conditions conditions = new Conditions();
        List list =
                goodsInfoService.list(conditions.eq("Goods_Type", data.get("Goods_Type").toString()));
        JSONArray jsonArray = new JSONArray();
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

            jsonArray.add(temp);
        }
        r = jsonArray.toString();
        long endTime = System.currentTimeMillis();
        System.out.println("Running time: " + (endTime - startTime) + "ms");
        return null;
    }

    @RequestMapping(value = "getSpecialGoods", method = RequestMethod.POST)
    public String getSpecialGoods(String jsonStr) throws BaseException {
        long starttime = System.currentTimeMillis();
        JSONObject data = JSONObject.parseObject(jsonStr);
        String r = "";
        Conditions conditions = new Conditions();
        Integer mark = Integer.parseInt(data.get("Goods_Mark").toString());
        List list = goodsInfoService.list(conditions.eq("goods_show", mark));
        JSONArray jsonArray = new JSONArray();
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
            temp.put("Goods_Reserve_1", goods.getGoods_reserve_1());
            jsonArray.add(temp);
        }
        r = jsonArray.toString();
        long endtime = System.currentTimeMillis();
        System.out.println("Running time: " + (endtime - starttime) + "ms");
        return null;
    }

    @RequestMapping(value = "getDiscountGoods", method = RequestMethod.POST)
    public String getDiscountGoods(String jsonStr) throws BaseException {
        long starttime = System.currentTimeMillis();
        JSONObject data = JSONObject.parseObject(jsonStr);
        String r = "";
        Conditions conditions = new Conditions();
        Integer mark = Integer.parseInt(data.get("Goods_Mark").toString());
        List list = goodsInfoService.list(conditions.eq("goods_show", mark));
        JSONArray jsonArray = new JSONArray();
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
            temp.put("Goods_Reserve_1", goods.getGoods_reserve_1());
            jsonArray.add(temp);
        }
        r = jsonArray.toString();
        long endtime = System.currentTimeMillis();
        System.out.println("Running time: " + (endtime - starttime) + "ms");
        return null;
    }

    @RequestMapping(value = "getAllGoods", method = RequestMethod.POST)
    public String getAllGoods(String jsonStr) throws BaseException {
        JSONArray jsonarr = new JSONArray();
        List list = goodsInfoService.listAll();

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
            temp.put("Goods_Season", goods.getGoods_season());
            temp.put("Goods_Blossom", goods.getGoods_blossom());
            temp.put("Goods_Fruit", goods.getGoods_fruit());
            temp.put("Goods_Mature", goods.getGoods_mature());
            temp.put("Goods_Expiration", goods.getGoods_expiration());
            temp.put("Goods_Reserve_1", goods.getGoods_reserve_1());
            jsonarr.add(temp);

        }
        String r = jsonarr.toString();
        return "success";
    }

//    @RequestMapping(value = "addGoods", method = RequestMethod.POST)
//    public String addGoods(String jsonStr) throws BaseException {
//        String r = "";
//        JSONObject json = JSONObject.parseObject(jsonStr);
//        GoodsInfo temp = new GoodsInfo();
//        temp.setGoods_name(json.get("Goods_Name").toString());
//        temp.setGoods_type((int) Integer.parseInt(json.get("Goods_Type").toString()));
//        temp.setGoods_num((int) Integer.parseInt(json.get("Goods_Num").toString()));
//        temp.setGoods_price((float) Float.parseFloat(json.get("Goods_Price").toString()));
//        temp.setGoods_mark(json.get("Goods_Mark").toString().charAt(0));
//        temp.setGoods_show(json.get("Goods_Show").toString().charAt(0));
//        temp.setGoods_picture(json.get("Goods_Picture").toString());
//        temp.setGoods_season((int) Integer.parseInt(json.get("Goods_Season").toString()));
//        temp.setGoods_blossom(json.get("Goods_Blossom").toString());
//        temp.setGoods_fruit(json.get("Goods_Fruit").toString());
//        temp.setGoods_mature(json.get("Goods_Mature").toString());
//        temp.setGoods_expiration(json.get("Goods_Expiration").toString());
//        temp.setGoods_expiration(json.get("Goods_Reserve_1").toString());
//        try {
//            goodsInfoService.add(temp);
//            r = "{\"status\":1}";
//        } catch (Exception e) {
//            e.printStackTrace();
//            r = "{\"status\":0}";
//        }
//        return "success";
//    }
//
//    @RequestMapping(value = "updateGoodsInfo", method = RequestMethod.POST)
//    public String updateGoodsInfo(String jsonStr) throws BaseException {
//        String r = "";
//        JSONObject json = JSONObject.parseObject(jsonStr);
//        GoodsInfo temp = goodsInfoService.findById(json.get("Goods_PK").toString());
//        temp.setGoods_name(json.get("Goods_Name").toString());
//        temp.setGoods_type((int) Integer.parseInt(json.get("Goods_Type").toString()));
//        temp.setGoods_num((int) Integer.parseInt(json.get("Goods_Num").toString()));
//        temp.setGoods_price((float) Float.parseFloat(json.get("Goods_Price").toString()));
//        temp.setGoods_mark(json.get("Goods_Mark").toString().charAt(0));
//        temp.setGoods_show(json.get("Goods_Show").toString().charAt(0));
//        temp.setGoods_picture(json.get("Goods_Picture").toString());
//        temp.setGoods_season((int) Integer.parseInt(json.get("Goods_Season").toString()));
//        temp.setGoods_blossom(json.get("Goods_Blossom").toString());
//        temp.setGoods_fruit(json.get("Goods_Fruit").toString());
//        temp.setGoods_mature(json.get("Goods_Mature").toString());
//        temp.setGoods_expiration(json.get("Goods_Expiration").toString());
//        try {
//            goodsInfoService.updateById(temp);
//            r = "{\"status\":1}";
//        } catch (Exception e) {
//            e.printStackTrace();
//            r = "{\"status\":0}";
//        }
//        return "success";
//    }
//
//    @RequestMapping(value = "deleteGoodsInfo",method = RequestMethod.POST)
//    public String deleteGoodsInfo (String jsonStr) throws BaseException{
//        String r="";
//        JSONObject json=JSONObject.parseObject(jsonStr);
//        GoodsInfo temp = goodsInfoService.findById(json.get("Goods_PK").toString());
//        temp.setGoods_name(json.get("Goods_Name").toString());
//        temp.setGoods_type((int)Integer.parseInt(json.get("Goods_Type").toString()));
//        temp.setGoods_num((int)Integer.parseInt(json.get("Goods_Num").toString()));
//        temp.setGoods_price((float)Float.parseFloat(json.get("Goods_Price").toString()));
//        temp.setGoods_mark(json.get("Goods_Mark").toString().charAt(0));
//        temp.setGoods_show(json.get("Goods_Show").toString().charAt(0));
//        temp.setGoods_picture(json.get("Goods_Picture").toString());
//        temp.setGoods_season((int)Integer.parseInt(json.get("Goods_Season").toString()));
//        temp.setGoods_blossom(json.get("Goods_Blossom").toString());
//        temp.setGoods_fruit(json.get("Goods_Fruit").toString());
//        temp.setGoods_mature(json.get("Goods_Mature").toString());
//        temp.setGoods_expiration(json.get("Goods_Expiration").toString());
//        try {
//            goodsInfoService.updateById(temp);
//            r="{\"status\":1}";
//        }catch (Exception e){
//            e.printStackTrace();
//            r="{\"status\":0}";
//        }
//        return "success";
//    }
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
            List list = goodsInfoService.list(conditions.like("goods_name", "%"+data.get("Goods_Name")+"%"));
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
        return "success";
    }

}


//测试代码如下
//package scau.zxck.web.admin;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONArray;
//import com.alibaba.fastjson.JSONObject;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.web.WebAppConfiguration;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//import scau.zxck.base.dao.mybatis.Conditions;
//import scau.zxck.base.exception.BaseException;
//import scau.zxck.entity.market.GoodsInfo;
//import scau.zxck.service.market.IGoodsInfoService;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Set;
//
//class ParamObjOfGoods extends Object{
//    private String goods_PK;
//    private String goods_Name;
//    private int goods_Type;
//    private int goods_Num;
//    private float goods_Price;
//    private char goods_Mark;
//    private char goods_Show;
//    private String goods_Picture;
//    private int goods_Season;
//    private String goods_Blossom;
//    private String goods_Fruit;
//    private String goods_Mature;
//    private String goods_Expiration;
//    private String goods_Reserve_1;
//    private String goods_Reserve_2;
//
//    public String getGoods_PK() {
//        return goods_PK;
//    }
//
//    public void setGoods_PK(String goods_PK) {
//        this.goods_PK = goods_PK;
//    }
//
//    public String getGoods_Name() {
//        return goods_Name;
//    }
//
//    public void setGoods_Name(String goods_Name) {
//        this.goods_Name = goods_Name;
//    }
//
//    public int getGoods_Type() {
//        return goods_Type;
//    }
//
//    public void setGoods_Type(int goods_Type) {
//        this.goods_Type = goods_Type;
//    }
//
//    public int getGoods_Num() {
//        return goods_Num;
//    }
//
//    public void setGoods_Num(int goods_Num) {
//        this.goods_Num = goods_Num;
//    }
//
//    public float getGoods_Price() {
//        return goods_Price;
//    }
//
//    public void setGoods_Price(float goods_Price) {
//        this.goods_Price = goods_Price;
//    }
//
//    public char getGoods_Mark() {
//        return goods_Mark;
//    }
//
//    public void setGoods_Mark(char goods_Mark) {
//        this.goods_Mark = goods_Mark;
//    }
//
//    public char getGoods_Show() {
//        return goods_Show;
//    }
//
//    public void setGoods_Show(char goods_Show) {
//        this.goods_Show = goods_Show;
//    }
//
//    public String getGoods_Picture() {
//        return goods_Picture;
//    }
//
//    public void setGoods_Picture(String goods_Picture) {
//        this.goods_Picture = goods_Picture;
//    }
//
//    public int getGoods_Season() {
//        return goods_Season;
//    }
//
//    public void setGoods_Season(int goods_Season) {
//        this.goods_Season = goods_Season;
//    }
//
//    public String getGoods_Blossom() {
//        return goods_Blossom;
//    }
//
//    public void setGoods_Blossom(String goods_Blossom) {
//        this.goods_Blossom = goods_Blossom;
//    }
//
//    public String getGoods_Fruit() {
//        return goods_Fruit;
//    }
//
//    public void setGoods_Fruit(String goods_Fruit) {
//        this.goods_Fruit = goods_Fruit;
//    }
//
//    public String getGoods_Mature() {
//        return goods_Mature;
//    }
//
//    public void setGoods_Mature(String goods_Mature) {
//        this.goods_Mature = goods_Mature;
//    }
//
//    public String getGoods_Expiration() {
//        return goods_Expiration;
//    }
//
//    public void setGoods_Expiration(String goods_Expiration) {
//        this.goods_Expiration = goods_Expiration;
//    }
//
//    public String getGoods_Reserve_1() {
//        return goods_Reserve_1;
//    }
//
//    public void setGoods_Reserve_1(String goods_Reserve_1) {
//        this.goods_Reserve_1 = goods_Reserve_1;
//    }
//
//    public String getGoods_Reserve_2() {
//        return goods_Reserve_2;
//    }
//
//    public void setGoods_Reserve_2(String goods_Reserve_2) {
//        this.goods_Reserve_2 = goods_Reserve_2;
//    }
//
//    public ParamObjOfGoods(String goods_PK, String goods_Name, int goods_Type,
//                           int goods_Num, float goods_Price, char goods_Mark,
//                           char goods_Show, String goods_Picture,
//                           int goods_Season, String goods_Blossom,
//                           String goods_Fruit, String goods_Mature,
//                           String goods_Expiration, String goods_Reserve_1, String goods_Reserve_2) {
//        this.goods_PK = goods_PK;
//        this.goods_Name = goods_Name;
//        this.goods_Type = goods_Type;
//        this.goods_Num = goods_Num;
//        this.goods_Price = goods_Price;
//        this.goods_Mark = goods_Mark;
//        this.goods_Show = goods_Show;
//        this.goods_Picture = goods_Picture;
//        this.goods_Season = goods_Season;
//        this.goods_Blossom = goods_Blossom;
//        this.goods_Fruit = goods_Fruit;
//        this.goods_Mature = goods_Mature;
//        this.goods_Expiration = goods_Expiration;
//        this.goods_Reserve_1 = goods_Reserve_1;
//        this.goods_Reserve_2 = goods_Reserve_2;
//    }
//
//    public ParamObjOfGoods(int goods_Type) {
//        this.goods_Type = goods_Type;
//    }
//
//    public ParamObjOfGoods(char goods_Mark) {
//        this.goods_Mark = goods_Mark;
//    }
//}
//
//
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@WebAppConfiguration
//@ContextConfiguration(locations = {"classpath:config/spring/spring.xml"})
//@Controller
//@RequestMapping("/")
//public class GoodsInfoAction {
//    @Autowired
//    private IGoodsInfoService goodsInfoService;
//
//    @org.junit.Test
//    @RequestMapping(value = "getOneGood", method = RequestMethod.POST)
//    public void getOneGood() throws BaseException {
//        ParamObjOfGoods paramObjOfGoods1 = new ParamObjOfGoods("100000", "鹰嘴桃(省内)",
//                1, 11997, 88, '0', '1',
//                "timg.jpg", 1, "3月份", "4-6月份",
//                "7月份", "5-7天", "每箱8斤",
//                "yingzuitao.jpg#yingzuitao.jpg");
//        String jsonStr = JSON.toJSON(paramObjOfGoods1).toString();
//        System.out.println(jsonStr);
//        jsonStr="{\"Goods_Season\":1,\"Goods_Price\":88,\"Goods_Blossom\":\"3月份\",\"Goods_Expiration\":\"5-7天\"," +
//                "\"Goods_PK\":\"100000\",\"Goods_Type\":1,\"Goods_Reserve_1\":\"每箱8斤\"," +
//                "\"Goods_Reserve_2\":\"yingzuitao.jpg#yingzuitao.jpg\",\"Goods_Show\":\"1\",\"Goods_Mark\":\"0\"," +
//                "\"Goods_Mature\":\"7月份\",\"Goods_Name\":\"鹰嘴桃(省内)\",\"Goods_Num\":11997,\"Goods_Picture\":\"timg" +
//                ".jpg\",\"Goods_Fruit\":\"4-6月份\"}";
//        JSONObject data = JSONObject.parseObject(jsonStr);
//        String r = "";
//        GoodsInfo goods = goodsInfoService.findById(data.get("Goods_PK").toString());
//        JSONObject temp = new JSONObject();
//        temp.put("Goods_PK", goods.getId());
//        temp.put("Goods_Name", goods.getGoods_name());
//        temp.put("Goods_Type", goods.getGoods_type());
//        temp.put("Goods_Num", goods.getGoods_num());
//        temp.put("Goods_Price", goods.getGoods_price());
//        temp.put("Goods_Mark", goods.getGoods_mark());
//        temp.put("Goods_Show", goods.getGoods_show());
//        temp.put("Goods_Picture", goods.getGoods_picture());
//        temp.put("Goods_Season", goods.getGoods_season());
//        temp.put("Goods_Blossom", goods.getGoods_blossom());
//        temp.put("Goods_Fruit", goods.getGoods_fruit());
//        temp.put("Goods_Mature", goods.getGoods_mature());
//        temp.put("Goods_Expiration", goods.getGoods_expiration());
//        temp.put("Goods_Reserve_1", goods.getGoods_reserve_1());
//        temp.put("Goods_Reserve_2", goods.getGoods_reserve_2());
//        r = temp.toString();
//        System.out.println(r);
//    }
//
//
//  @org.junit.Test
//  @RequestMapping(value = "getAllTypeGoods", method = RequestMethod.POST)
//  public void getAllTypesGoods() throws BaseException {
//    //  String jsonStr = JSON.toJSON(paramObjOfGoods1).toString();
//    long startTime = System.currentTimeMillis();
//    //JSONObject data = JSONObject.parseObject(jsonStr);
//    String r = "";
//    List list = goodsInfoService.listAll();
//    JSONArray jsonarr = new JSONArray();
//    for (Iterator iter = ((java.util.List) list).iterator(); iter.hasNext();) {
//      JSONObject temp = new JSONObject();
//      GoodsInfo goods = (GoodsInfo) iter.next();
//
//      temp.put("Goods_PK", goods.getId());
//
//      temp.put("Goods_Name", goods.getGoods_name());
//      temp.put("Goods_Type", goods.getGoods_type());
//      temp.put("Goods_Num", goods.getGoods_num());
//      temp.put("Goods_Price", goods.getGoods_price());
//      temp.put("Goods_Mark", goods.getGoods_mark());
//      temp.put("Goods_Show", goods.getGoods_show());
//      temp.put("Goods_Picture", goods.getGoods_picture());
//      temp.put("Goods_Season", goods.getGoods_season());
//      temp.put("Goods_Blossom", goods.getGoods_blossom());
//      temp.put("Goods_Fruit", goods.getGoods_fruit());
//      temp.put("Goods_Mature", goods.getGoods_mature());
//      temp.put("Goods_Expiration", goods.getGoods_expiration());
//      temp.put("Goods_Reserve_1", goods.getGoods_reserve_1());
//      jsonarr.add(temp);
//
//    }
//    r = jsonarr.toString();
//    System.out.println(r);
//    long endTime = System.currentTimeMillis();
//    System.out.println("Running time: " + (endTime - startTime) + "ms");
////    return null;
//  }
//
//  @org.junit.Test
//  @RequestMapping(value = "getTypeGoods", method = RequestMethod.POST)
//  public void getTypeGoods() throws BaseException {
//    long startTime = System.currentTimeMillis();
//      ParamObjOfGoods paramObjOfGoods1 = new ParamObjOfGoods(2);
//    String jsonStr = JSON.toJSON(paramObjOfGoods1).toString();
//      System.out.println(jsonStr);
//      jsonStr = "{\"Goods_Season\":0,\"Goods_Price\":0,\"Goods_Type\":2,\"Goods_Show\":\"\\u0000\"," +
//              "\"Goods_Mark\":\"\\u0000\",\"Goods_Num\":0}";
//    JSONObject data = JSONObject.parseObject(jsonStr);
//      System.out.println(jsonStr);
//    String r = "";
//    Conditions conditions = new Conditions();
//    List list =
//        goodsInfoService.list(conditions.eq("Goods_Type", data.get("Goods_Type").toString()));
//    JSONArray jsonArray = new JSONArray();
//    for (Iterator iter = ((java.util.List) list).iterator(); iter.hasNext();) {
//      JSONObject temp = new JSONObject();
//      GoodsInfo goods = (GoodsInfo) iter.next();
//
//      temp.put("Goods_PK", goods.getId());
//      temp.put("Goods_Name", goods.getGoods_name());
//      temp.put("Goods_Type", goods.getGoods_type());
//      temp.put("Goods_Num", goods.getGoods_num());
//      temp.put("Goods_Price", goods.getGoods_price());
//      temp.put("Goods_Mark", goods.getGoods_mark());
//      temp.put("Goods_Show", goods.getGoods_show());
//      temp.put("Goods_Picture", goods.getGoods_picture());
//
//      jsonArray.add(temp);
//    }
//    r = jsonArray.toString();
//    System.out.println(r);
//    long endTime = System.currentTimeMillis();
//    System.out.println("Running time: " + (endTime - startTime) + "ms");
////    return null;
//  }
//
////    {
//        //测试数据结果
////  //[{"Goods_PK":"100001","Goods_Picture":"guomiao.jpg","Goods_Num":11999,"Goods_Name":"高山名茶半斤","Goods_Price":240,"Goods_Show":"2","Goods_Mark":"0","Goods_Type":2},
////  {"Goods_PK":"100003","Goods_Picture":"fengmi.jpg","Goods_Num":11002,"Goods_Name":"蜂蜜","Goods_Price":268,"Goods_Show":"1","Goods_Mark":"0","Goods_Type":2},
////    {"Goods_PK":"100015","Goods_Picture":"shugan.jpg","Goods_Num":11500,"Goods_Name":"薯干","Goods_Price":20,"Goods_Show":"0","Goods_Mark":"0","Goods_Type":2},
////    {"Goods_PK":"100022","Goods_Picture":"chayou.jpg","Goods_Num":11998,"Goods_Name":"高山茶油二斤","Goods_Price":8,"Goods_Show":"2","Goods_Mark":"0","Goods_Type":2},
////    {"Goods_PK":"100024","Goods_Picture":"shusifen.jpg","Goods_Num":12000,"Goods_Name":"红薯粉丝","Goods_Price":78,"Goods_Show":"0","Goods_Mark":"0","Goods_Type":2},
////    {"Goods_PK":"100025","Goods_Picture":"mifensi.jpg","Goods_Num":11997,"Goods_Name":"米粉丝","Goods_Price":48,"Goods_Show":"0","Goods_Mark":"0","Goods_Type":2},
////    {"Goods_PK":"100026","Goods_Picture":"xianggu.jpg","Goods_Num":11000,"Goods_Name":"香菇","Goods_Price":0.01,"Goods_Show":"0","Goods_Mark":"0","Goods_Type":2},
////    {"Goods_PK":"100027","Goods_Picture":"muer.jpg","Goods_Num":11000,"Goods_Name":"木耳","Goods_Price":0.01,"Goods_Show":"0","Goods_Mark":"0","Goods_Type":2},
////    {"Goods_PK":"100028","Goods_Picture":"lingzhi.jpg","Goods_Num":11000,"Goods_Name":"灵芝","Goods_Price":0.01,"Goods_Show":"0","Goods_Mark":"0","Goods_Type":2},
////    {"Goods_PK":"100029","Goods_Picture":"chaye2.jpg","Goods_Num":11998,"Goods_Name":"高山名茶一斤","Goods_Price":480,"Goods_Show":"0","Goods_Mark":"0","Goods_Type":2},
////    {"Goods_PK":"100030","Goods_Picture":"chayou2.jpg","Goods_Num":11998,"Goods_Name":"高山茶油十斤","Goods_Price":8,"Goods_Show":"0","Goods_Mark":"0","Goods_Type":2}]
//
////    }
//
//
//
//
//  @org.junit.Test
//  @RequestMapping(value = "getSpecialGoods", method = RequestMethod.POST)
//  public void getSpecialGoods() throws BaseException {
//    long starttime = System.currentTimeMillis();
//    ParamObjOfGoods paramObjOfGoods1 = new ParamObjOfGoods('0');
//      String jsonStr = JSON.toJSON(paramObjOfGoods1).toString();
//      System.out.println(jsonStr);
//      jsonStr ="{\"Goods_Season\":0,\"Goods_Price\":0,\"Goods_Type\":0,\"Goods_Show\":\"\\u0000\"," +
//              "\"Goods_Mark\":\"0\",\"Goods_Num\":0}";
//    JSONObject data = JSONObject.parseObject(jsonStr);
//      System.out.println(jsonStr);
//    String r = "";
//    Conditions conditions = new Conditions();
//    Integer mark = Integer.parseInt(data.get("Goods_Mark").toString());
//    List list = goodsInfoService.list(conditions.eq("goods_show", mark));
//    JSONArray jsonArray = new JSONArray();
//    for (Iterator iter = ((java.util.List) list).iterator(); iter.hasNext();) {
//      JSONObject temp = new JSONObject();
//      GoodsInfo goods = (GoodsInfo) iter.next();
//
//      temp.put("Goods_PK", goods.getId());
//      temp.put("Goods_Name", goods.getGoods_name());
//      temp.put("Goods_Type", goods.getGoods_type());
//      temp.put("Goods_Num", goods.getGoods_num());
//      temp.put("Goods_Price", goods.getGoods_price());
//      temp.put("Goods_Mark", goods.getGoods_mark());
//      temp.put("Goods_Show", goods.getGoods_show());
//      temp.put("Goods_Picture", goods.getGoods_picture());
//      temp.put("Goods_Reserve_1", goods.getGoods_reserve_1());
//      jsonArray.add(temp);
//    }
//    r = jsonArray.toString();
//      System.out.println(r);
//    long endtime = System.currentTimeMillis();
//    System.out.println("Running time: " + (endtime - starttime) + "ms");
////    return null;
//  }
////  测试数据结果
////  [{"Goods_PK":"100005","Goods_Picture":"shuiyu.jpg","Goods_Reserve_1":"无","Goods_Num":11128,"Goods_Name":"水鱼","Goods_Price":500,"Goods_Show":"0","Goods_Mark":"0","Goods_Type":4},{"Goods_PK":"100006","Goods_Picture":"zaoli.jpg","Goods_Reserve_1":"无","Goods_Num":11973,"Goods_Name":"早李","Goods_Price":2,"Goods_Show":"0","Goods_Mark":"0","Goods_Type":1},{"Goods_PK":"100009","Goods_Picture":"zhu.jpg","Goods_Reserve_1":"无","Goods_Num":11918,"Goods_Name":"生猪","Goods_Price":2000,"Goods_Show":"0","Goods_Mark":"0","Goods_Type":5},{"Goods_PK":"100011","Goods_Picture":"fanshu.jpg","Goods_Reserve_1":"无","Goods_Num":11994,"Goods_Name":"番薯","Goods_Price":10,"Goods_Show":"0","Goods_Mark":"0","Goods_Type":3},{"Goods_PK":"100012","Goods_Picture":"shuidao.jpg","Goods_Reserve_1":"无","Goods_Num":111000,"Goods_Name":"水稻","Goods_Price":2,"Goods_Show":"0","Goods_Mark":"0","Goods_Type":3},{"Goods_PK":"100013","Goods_Picture":"yumi.jpg","Goods_Reserve_1":"无","Goods_Num":111000,"Goods_Name":"玉米","Goods_Price":5,"Goods_Show":"0","Goods_Mark":"0","Goods_Type":3},{"Goods_PK":"100015","Goods_Picture":"shugan.jpg","Goods_Reserve_1":"无","Goods_Num":11500,"Goods_Name":"薯干","Goods_Price":20,"Goods_Show":"0","Goods_Mark":"0","Goods_Type":2},{"Goods_PK":"100016","Goods_Picture":"yangrou.jpg","Goods_Reserve_1":"无","Goods_Num":11200,"Goods_Name":"生羊","Goods_Price":100,"Goods_Show":"0","Goods_Mark":"0","Goods_Type":5},{"Goods_PK":"100017","Goods_Picture":"shandiji.jpg","Goods_Reserve_1":"无","Goods_Num":11200,"Goods_Name":"山地鸡","Goods_Price":20,"Goods_Show":"0","Goods_Mark":"0","Goods_Type":5},{"Goods_PK":"100018","Goods_Picture":"huosuan.jpg","Goods_Reserve_1":"无","Goods_Num":11500,"Goods_Name":"火蒜","Goods_Price":20,"Goods_Show":"0","Goods_Mark":"0","Goods_Type":3},{"Goods_PK":"100019","Goods_Picture":"guomiao.jpg","Goods_Reserve_1":"无","Goods_Num":111000,"Goods_Name":"果苗","Goods_Price":10,"Goods_Show":"0","Goods_Mark":"0","Goods_Type":6},{"Goods_PK":"100020","Goods_Picture":"shankengyu.jpg","Goods_Reserve_1":"无","Goods_Num":11995,"Goods_Name":"山坑鱼","Goods_Price":2,"Goods_Show":"0","Goods_Mark":"0","Goods_Type":4},{"Goods_PK":"100021","Goods_Picture":"zhushu.jpg","Goods_Reserve_1":"无","Goods_Num":11993,"Goods_Name":"竹鼠","Goods_Price":2.5,"Goods_Show":"0","Goods_Mark":"0","Goods_Type":5},{"Goods_PK":"100024","Goods_Picture":"shusifen.jpg","Goods_Reserve_1":"每箱5斤","Goods_Num":12000,"Goods_Name":"红薯粉丝","Goods_Price":78,"Goods_Show":"0","Goods_Mark":"0","Goods_Type":2},{"Goods_PK":"100025","Goods_Picture":"mifensi.jpg","Goods_Reserve_1":"每箱5斤","Goods_Num":11997,"Goods_Name":"米粉丝","Goods_Price":48,"Goods_Show":"0","Goods_Mark":"0","Goods_Type":2},{"Goods_PK":"100026","Goods_Picture":"xianggu.jpg","Goods_Reserve_1":"无","Goods_Num":11000,"Goods_Name":"香菇","Goods_Price":0.01,"Goods_Show":"0","Goods_Mark":"0","Goods_Type":2},{"Goods_PK":"100027","Goods_Picture":"muer.jpg","Goods_Reserve_1":"无","Goods_Num":11000,"Goods_Name":"木耳","Goods_Price":0.01,"Goods_Show":"0","Goods_Mark":"0","Goods_Type":2},{"Goods_PK":"100028","Goods_Picture":"lingzhi.jpg","Goods_Reserve_1":"无","Goods_Num":11000,"Goods_Name":"灵芝","Goods_Price":0.01,"Goods_Show":"0","Goods_Mark":"0","Goods_Type":2},{"Goods_PK":"100029","Goods_Picture":"chaye2.jpg","Goods_Reserve_1":"每包1斤，批发100斤起380元。","Goods_Num":11998,"Goods_Name":"高山名茶一斤","Goods_Price":480,"Goods_Show":"0","Goods_Mark":"0","Goods_Type":2},{"Goods_PK":"100030","Goods_Picture":"chayou2.jpg","Goods_Reserve_1":"每瓶10斤","Goods_Num":11998,"Goods_Name":"高山茶油十斤","Goods_Price":8,"Goods_Show":"0","Goods_Mark":"0","Goods_Type":2}]
//
//
//  @org.junit.Test
//  @RequestMapping(value = "getDiscountGoods", method = RequestMethod.POST)
//  public void getDiscountGoods() throws BaseException {
//    long starttime = System.currentTimeMillis();
//      ParamObjOfGoods paramObjOfGoods1 = new ParamObjOfGoods('1');
//      String jsonStr = JSON.toJSON(paramObjOfGoods1).toString();
//      System.out.println(jsonStr);
//      jsonStr ="{\"Goods_Season\":0,\"Goods_Price\":0,\"Goods_Type\":0,\"Goods_Show\":\"\\u0000\"," +
//              "\"Goods_Mark\":\"1\",\"Goods_Num\":0}\n";
//      JSONObject data = JSONObject.parseObject(jsonStr);
//      System.out.println(jsonStr);
//    String r = "";
//    Conditions conditions = new Conditions();
//    Integer mark = Integer.parseInt(data.get("Goods_Mark").toString());
//    List list = goodsInfoService.list(conditions.eq("goods_show", mark));
//    JSONArray jsonArray = new JSONArray();
//    for (Iterator iter = ((java.util.List) list).iterator(); iter.hasNext();) {
//      JSONObject temp = new JSONObject();
//      GoodsInfo goods = (GoodsInfo) iter.next();
//
//      temp.put("Goods_PK", goods.getId());
//      temp.put("Goods_Name", goods.getGoods_name());
//      temp.put("Goods_Type", goods.getGoods_type());
//      temp.put("Goods_Num", goods.getGoods_num());
//      temp.put("Goods_Price", goods.getGoods_price());
//      temp.put("Goods_Mark", goods.getGoods_mark());
//      temp.put("Goods_Show", goods.getGoods_show());
//      temp.put("Goods_Picture", goods.getGoods_picture());
//      temp.put("Goods_Reserve_1", goods.getGoods_reserve_1());
//      jsonArray.add(temp);
//    }
//    r = jsonArray.toString();
//      System.out.println(r);
//    long endtime = System.currentTimeMillis();
//    System.out.println("Running time: " + (endtime - starttime) + "ms");
////    return null;
//  }
//    //  测试数据结果
////  [{"Goods_PK":"100000","Goods_Picture":"timg.jpg","Goods_Reserve_1":"每箱8斤","Goods_Num":11997,"Goods_Name":"鹰嘴桃(省内)","Goods_Price":88,"Goods_Show":"1","Goods_Mark":"0","Goods_Type":1},{"Goods_PK":"100003","Goods_Picture":"fengmi.jpg","Goods_Reserve_1":"每瓶2斤，冬糖268元，春糖168元。","Goods_Num":11002,"Goods_Name":"蜂蜜","Goods_Price":268,"Goods_Show":"1","Goods_Mark":"0","Goods_Type":2},{"Goods_PK":"100004","Goods_Picture":"sanhuali.jpg","Goods_Reserve_1":"每箱10斤","Goods_Num":11997,"Goods_Name":"三华李","Goods_Price":288,"Goods_Show":"1","Goods_Mark":"0","Goods_Type":1},{"Goods_PK":"100010","Goods_Picture":"shatangju.jpg","Goods_Reserve_1":"每箱10斤","Goods_Num":11998,"Goods_Name":"砂糖桔","Goods_Price":5,"Goods_Show":"1","Goods_Mark":"0","Goods_Type":1},{"Goods_PK":"100023","Goods_Picture":"lizhi.jpg","Goods_Reserve_1":"无","Goods_Num":11000,"Goods_Name":"荔枝","Goods_Price":5,"Goods_Show":"1","Goods_Mark":"0","Goods_Type":1},{"Goods_PK":"100031","Goods_Picture":"yingzuitao.jpg","Goods_Reserve_1":"每箱8斤。省外发货城市：哈尔滨、长春、沈阳、北京、天津、廊坊、大连、烟台、青岛、威海、潍坊、济南、石家庄、太原、西安、郑州、芜湖、合肥、江苏、上海、浙江、长沙、武汉、南昌、重庆、成都、绵阳、简阳、贵阳、南宁、三亚。","Goods_Num":11999,"Goods_Name":"鹰嘴桃(省外)","Goods_Price":168,"Goods_Show":"1","Goods_Mark":"0","Goods_Type":1}]
//
//
//  @org.junit.Test
//  @RequestMapping(value = "getAllGoods", method = RequestMethod.POST)
//  public void getAllGoods() throws BaseException {
//    JSONArray jsonarr = new JSONArray();
//    List list = goodsInfoService.listAll();
//
//    for (Iterator iter = ((java.util.List) list).iterator(); iter.hasNext();) {
//      JSONObject temp = new JSONObject();
//      GoodsInfo goods = (GoodsInfo) iter.next();
//
//      temp.put("Goods_PK", goods.getId());
//
//      temp.put("Goods_Name", goods.getGoods_name());
//      temp.put("Goods_Type", goods.getGoods_type());
//      temp.put("Goods_Num", goods.getGoods_num());
//      temp.put("Goods_Price", goods.getGoods_price());
//      temp.put("Goods_Mark", goods.getGoods_mark());
//      temp.put("Goods_Show", goods.getGoods_show());
//      temp.put("Goods_Picture", goods.getGoods_picture());
//      temp.put("Goods_Season", goods.getGoods_season());
//      temp.put("Goods_Blossom", goods.getGoods_blossom());
//      temp.put("Goods_Fruit", goods.getGoods_fruit());
//      temp.put("Goods_Mature", goods.getGoods_mature());
//      temp.put("Goods_Expiration", goods.getGoods_expiration());
//      temp.put("Goods_Reserve_1", goods.getGoods_reserve_1());
//      jsonarr.add(temp);
//
//    }
//    String r = jsonarr.toString();
//      System.out.println(r);
////    return "success";
//  }
////  测试数据结果
////[{"Goods_PK":"100000","Goods_Blossom":"3月份","Goods_Num":11997,"Goods_Fruit":"4-6月份","Goods_Name":"鹰嘴桃(省内)","Goods_Price":88,"Goods_Mark":"0","Goods_Season":1,"Goods_Picture":"timg.jpg","Goods_Reserve_1":"每箱8斤","Goods_Mature":"7月份","Goods_Expiration":"5-7天","Goods_Show":"1","Goods_Type":1},{"Goods_PK":"100001","Goods_Blossom":"无","Goods_Num":11999,"Goods_Fruit":"无","Goods_Name":"高山名茶半斤","Goods_Price":240,"Goods_Mark":"0","Goods_Season":2,"Goods_Picture":"guomiao.jpg","Goods_Reserve_1":"每包半斤，批发100斤起380元。","Goods_Mature":"无","Goods_Expiration":"10-20年","Goods_Show":"2","Goods_Type":2},{"Goods_PK":"100002","Goods_Blossom":"3月份","Goods_Num":11000,"Goods_Fruit":"8-9月份","Goods_Name":"板栗","Goods_Price":18,"Goods_Mark":"0","Goods_Season":1,"Goods_Picture":"banli.jpg","Goods_Reserve_1":"无","Goods_Mature":"10月份","Goods_Expiration":"一年","Goods_Show":"2","Goods_Type":3},{"Goods_PK":"100003","Goods_Blossom":"无","Goods_Num":11002,"Goods_Fruit":"无","Goods_Name":"蜂蜜","Goods_Price":268,"Goods_Mark":"0","Goods_Season":2,"Goods_Picture":"fengmi.jpg","Goods_Reserve_1":"每瓶2斤，冬糖268元，春糖168元。","Goods_Mature":"无","Goods_Expiration":"1年","Goods_Show":"1","Goods_Type":2},{"Goods_PK":"100004","Goods_Blossom":"2-3月份","Goods_Num":11997,"Goods_Fruit":"4-5月份","Goods_Name":"三华李","Goods_Price":288,"Goods_Mark":"0","Goods_Season":1,"Goods_Picture":"sanhuali.jpg","Goods_Reserve_1":"每箱10斤","Goods_Mature":"6月份","Goods_Expiration":"10天左右","Goods_Show":"1","Goods_Type":1},{"Goods_PK":"100005","Goods_Blossom":"无","Goods_Num":11128,"Goods_Fruit":"无","Goods_Name":"水鱼","Goods_Price":500,"Goods_Mark":"0","Goods_Season":-1,"Goods_Picture":"shuiyu.jpg","Goods_Reserve_1":"无","Goods_Mature":"不定期","Goods_Expiration":"鲜活类","Goods_Show":"0","Goods_Type":4},{"Goods_PK":"100006","Goods_Blossom":"2-3月份","Goods_Num":11973,"Goods_Fruit":"3-4月份","Goods_Name":"早李","Goods_Price":2,"Goods_Mark":"0","Goods_Season":1,"Goods_Picture":"zaoli.jpg","Goods_Reserve_1":"无","Goods_Mature":"4-5月份","Goods_Expiration":"10天左右","Goods_Show":"0","Goods_Type":1},{"Goods_PK":"100007","Goods_Blossom":"5，6月份","Goods_Num":11002,"Goods_Fruit":"7，8月份","Goods_Name":"茶枝柑","Goods_Price":5,"Goods_Mark":"0","Goods_Season":1,"Goods_Picture":"chazhigan.jpg","Goods_Reserve_1":"无","Goods_Mature":"10月份","Goods_Expiration":"7天左右","Goods_Show":"2","Goods_Type":1},{"Goods_PK":"100008","Goods_Blossom":"5，6月份","Goods_Num":11000,"Goods_Fruit":"7，8月份","Goods_Name":"贡柑","Goods_Price":7,"Goods_Mark":"0","Goods_Season":1,"Goods_Picture":"gonggan.jpg","Goods_Reserve_1":"每箱10斤","Goods_Mature":"10月份","Goods_Expiration":"7天左右","Goods_Show":"2","Goods_Type":1},{"Goods_PK":"100009","Goods_Blossom":"-1","Goods_Num":11918,"Goods_Fruit":"无","Goods_Name":"生猪","Goods_Price":2000,"Goods_Mark":"0","Goods_Season":-1,"Goods_Picture":"zhu.jpg","Goods_Reserve_1":"无","Goods_Mature":"无","Goods_Expiration":"无","Goods_Show":"0","Goods_Type":5},{"Goods_PK":"100010","Goods_Blossom":"1","Goods_Num":11998,"Goods_Fruit":"5，6月份","Goods_Name":"砂糖桔","Goods_Price":5,"Goods_Mark":"0","Goods_Season":1,"Goods_Picture":"shatangju.jpg","Goods_Reserve_1":"每箱10斤","Goods_Mature":"7，8月份","Goods_Expiration":"10月份","Goods_Show":"1","Goods_Type":1},{"Goods_PK":"100011","Goods_Blossom":"2","Goods_Num":11994,"Goods_Fruit":"无","Goods_Name":"番薯","Goods_Price":10,"Goods_Mark":"0","Goods_Season":2,"Goods_Picture":"fanshu.jpg","Goods_Reserve_1":"无","Goods_Mature":"无","Goods_Expiration":"6/10月份","Goods_Show":"0","Goods_Type":3},{"Goods_PK":"100012","Goods_Blossom":"2","Goods_Num":111000,"Goods_Fruit":"无","Goods_Name":"水稻","Goods_Price":2,"Goods_Mark":"0","Goods_Season":2,"Goods_Picture":"shuidao.jpg","Goods_Reserve_1":"无","Goods_Mature":"无","Goods_Expiration":"7/12月份","Goods_Show":"0","Goods_Type":3},{"Goods_PK":"100013","Goods_Blossom":"4","Goods_Num":111000,"Goods_Fruit":"无","Goods_Name":"玉米","Goods_Price":5,"Goods_Mark":"0","Goods_Season":4,"Goods_Picture":"yumi.jpg","Goods_Reserve_1":"无","Goods_Mature":"无","Goods_Expiration":"每年每季","Goods_Show":"0","Goods_Type":3},{"Goods_PK":"100014","Goods_Blossom":"2","Goods_Num":111000,"Goods_Fruit":"4，7月份","Goods_Name":"百香果","Goods_Price":10,"Goods_Mark":"0","Goods_Season":2,"Goods_Picture":"baixiangguo.jpg","Goods_Reserve_1":"每箱10斤","Goods_Mature":"6，8，10月份","Goods_Expiration":"9，11，1月份","Goods_Show":"2","Goods_Type":1},{"Goods_PK":"100015","Goods_Blossom":"无","Goods_Num":11500,"Goods_Fruit":"无","Goods_Name":"薯干","Goods_Price":20,"Goods_Mark":"0","Goods_Season":4,"Goods_Picture":"shugan.jpg","Goods_Reserve_1":"无","Goods_Mature":"每年每季","Goods_Expiration":"5天左右","Goods_Show":"0","Goods_Type":2},{"Goods_PK":"100016","Goods_Blossom":"4-5月份","Goods_Num":11200,"Goods_Fruit":"6-7月份","Goods_Name":"生羊","Goods_Price":100,"Goods_Mark":"0","Goods_Season":1,"Goods_Picture":"yangrou.jpg","Goods_Reserve_1":"无","Goods_Mature":"9-10月份","Goods_Expiration":"一年或以上","Goods_Show":"0","Goods_Type":5},{"Goods_PK":"100017","Goods_Blossom":"无","Goods_Num":11200,"Goods_Fruit":"无","Goods_Name":"山地鸡","Goods_Price":20,"Goods_Mark":"0","Goods_Season":4,"Goods_Picture":"shandiji.jpg","Goods_Reserve_1":"无","Goods_Mature":"无","Goods_Expiration":"无","Goods_Show":"0","Goods_Type":5},{"Goods_PK":"100018","Goods_Blossom":"无","Goods_Num":11500,"Goods_Fruit":"无","Goods_Name":"火蒜","Goods_Price":20,"Goods_Mark":"0","Goods_Season":4,"Goods_Picture":"huosuan.jpg","Goods_Reserve_1":"无","Goods_Mature":"每年每季","Goods_Expiration":"5天左右","Goods_Show":"0","Goods_Type":3},{"Goods_PK":"100019","Goods_Blossom":"无","Goods_Num":111000,"Goods_Fruit":"无","Goods_Name":"果苗","Goods_Price":10,"Goods_Mark":"0","Goods_Season":1,"Goods_Picture":"guomiao.jpg","Goods_Reserve_1":"无","Goods_Mature":"无","Goods_Expiration":"无","Goods_Show":"0","Goods_Type":6},{"Goods_PK":"100020","Goods_Blossom":"无","Goods_Num":11995,"Goods_Fruit":"无","Goods_Name":"山坑鱼","Goods_Price":2,"Goods_Mark":"0","Goods_Season":4,"Goods_Picture":"shankengyu.jpg","Goods_Reserve_1":"无","Goods_Mature":"无","Goods_Expiration":"无","Goods_Show":"0","Goods_Type":4},{"Goods_PK":"100021","Goods_Blossom":"无","Goods_Num":11993,"Goods_Fruit":"无","Goods_Name":"竹鼠","Goods_Price":2.5,"Goods_Mark":"0","Goods_Season":-1,"Goods_Picture":"zhushu.jpg","Goods_Reserve_1":"无","Goods_Mature":"无","Goods_Expiration":"鲜活类","Goods_Show":"0","Goods_Type":5},{"Goods_PK":"100022","Goods_Blossom":"无","Goods_Num":11998,"Goods_Fruit":"无","Goods_Name":"高山茶油二斤","Goods_Price":8,"Goods_Mark":"0","Goods_Season":-1,"Goods_Picture":"chayou.jpg","Goods_Reserve_1":"每瓶2斤","Goods_Mature":"无","Goods_Expiration":"一年或以上","Goods_Show":"2","Goods_Type":2},{"Goods_PK":"100023","Goods_Blossom":"4-5月","Goods_Num":11000,"Goods_Fruit":"5-6月","Goods_Name":"荔枝","Goods_Price":5,"Goods_Mark":"0","Goods_Season":1,"Goods_Picture":"lizhi.jpg","Goods_Reserve_1":"无","Goods_Mature":"6-7月","Goods_Expiration":"10天","Goods_Show":"1","Goods_Type":1},{"Goods_PK":"100024","Goods_Blossom":"无","Goods_Num":12000,"Goods_Fruit":"无","Goods_Name":"红薯粉丝","Goods_Price":78,"Goods_Mark":"0","Goods_Season":-1,"Goods_Picture":"shusifen.jpg","Goods_Reserve_1":"每箱5斤","Goods_Mature":"无","Goods_Expiration":"无","Goods_Show":"0","Goods_Type":2},{"Goods_PK":"100025","Goods_Blossom":"无","Goods_Num":11997,"Goods_Fruit":"无","Goods_Name":"米粉丝","Goods_Price":48,"Goods_Mark":"0","Goods_Season":-1,"Goods_Picture":"mifensi.jpg","Goods_Reserve_1":"每箱5斤","Goods_Mature":"无","Goods_Expiration":"无","Goods_Show":"0","Goods_Type":2},{"Goods_PK":"100026","Goods_Blossom":"无","Goods_Num":11000,"Goods_Fruit":"无","Goods_Name":"香菇","Goods_Price":0.01,"Goods_Mark":"0","Goods_Season":-1,"Goods_Picture":"xianggu.jpg","Goods_Reserve_1":"无","Goods_Mature":"无","Goods_Expiration":"无","Goods_Show":"0","Goods_Type":2},{"Goods_PK":"100027","Goods_Blossom":"无","Goods_Num":11000,"Goods_Fruit":"无","Goods_Name":"木耳","Goods_Price":0.01,"Goods_Mark":"0","Goods_Season":-1,"Goods_Picture":"muer.jpg","Goods_Reserve_1":"无","Goods_Mature":"无","Goods_Expiration":"无","Goods_Show":"0","Goods_Type":2},{"Goods_PK":"100028","Goods_Blossom":"无","Goods_Num":11000,"Goods_Fruit":"无","Goods_Name":"灵芝","Goods_Price":0.01,"Goods_Mark":"0","Goods_Season":-1,"Goods_Picture":"lingzhi.jpg","Goods_Reserve_1":"无","Goods_Mature":"无","Goods_Expiration":"无","Goods_Show":"0","Goods_Type":2},{"Goods_PK":"100029","Goods_Blossom":"无","Goods_Num":11998,"Goods_Fruit":"无","Goods_Name":"高山名茶一斤","Goods_Price":480,"Goods_Mark":"0","Goods_Season":2,"Goods_Picture":"chaye2.jpg","Goods_Reserve_1":"每包1斤，批发100斤起380元。","Goods_Mature":"无","Goods_Expiration":"10-20年","Goods_Show":"0","Goods_Type":2},{"Goods_PK":"100030","Goods_Blossom":"无","Goods_Num":11998,"Goods_Fruit":"无","Goods_Name":"高山茶油十斤","Goods_Price":8,"Goods_Mark":"0","Goods_Season":-1,"Goods_Picture":"chayou2.jpg","Goods_Reserve_1":"每瓶10斤","Goods_Mature":"无","Goods_Expiration":"一年或以上","Goods_Show":"0","Goods_Type":2},{"Goods_PK":"100031","Goods_Blossom":"3月份","Goods_Num":11999,"Goods_Fruit":"4-6月份","Goods_Name":"鹰嘴桃(省外)","Goods_Price":168,"Goods_Mark":"0","Goods_Season":1,"Goods_Picture":"yingzuitao.jpg","Goods_Reserve_1":"每箱8斤。省外发货城市：哈尔滨、长春、沈阳、北京、天津、廊坊、大连、烟台、青岛、威海、潍坊、济南、石家庄、太原、西安、郑州、芜湖、合肥、江苏、上海、浙江、长沙、武汉、南昌、重庆、成都、绵阳、简阳、贵阳、南宁、三亚。","Goods_Mature":"7月份","Goods_Expiration":"5-7天","Goods_Show":"1","Goods_Type":1}]
//
////  @org.junit.Test
////  @RequestMapping(value = "addGoods", method = RequestMethod.POST)
////  public String addGoods(String jsonStr) throws BaseException {
////    String r = "";
////    JSONObject json = JSONObject.parseObject(jsonStr);
////    GoodsInfo temp = new GoodsInfo();
////    temp.setGoods_name(json.get("Goods_Name").toString());
////    temp.setGoods_type((int) Integer.parseInt(json.get("Goods_Type").toString()));
////    temp.setGoods_num((int) Integer.parseInt(json.get("Goods_Num").toString()));
////    temp.setGoods_price((float) Float.parseFloat(json.get("Goods_Price").toString()));
////    temp.setGoods_mark(json.get("Goods_Mark").toString().charAt(0));
////    temp.setGoods_show(json.get("Goods_Show").toString().charAt(0));
////    temp.setGoods_picture(json.get("Goods_Picture").toString());
////    temp.setGoods_season((int) Integer.parseInt(json.get("Goods_Season").toString()));
////    temp.setGoods_blossom(json.get("Goods_Blossom").toString());
////    temp.setGoods_fruit(json.get("Goods_Fruit").toString());
////    temp.setGoods_mature(json.get("Goods_Mature").toString());
////    temp.setGoods_expiration(json.get("Goods_Expiration").toString());
////    temp.setGoods_expiration(json.get("Goods_Reserve_1").toString());
////    try {
////      goodsInfoService.add(temp);
////      r = "{\"status\":1}";
////    } catch (Exception e) {
////      e.printStackTrace();
////      r = "{\"status\":0}";
////    }
////    return "success";
////  }
////
////  @org.junit.Test
////  @RequestMapping(value = "updateGoodsInfo", method = RequestMethod.POST)
////  public String updateGoodsInfo(String jsonStr) throws BaseException {
////    String r = "";
////    JSONObject json = JSONObject.parseObject(jsonStr);
////    GoodsInfo temp = goodsInfoService.findById(json.get("Goods_PK").toString());
////    temp.setGoods_name(json.get("Goods_Name").toString());
////    temp.setGoods_type((int) Integer.parseInt(json.get("Goods_Type").toString()));
////    temp.setGoods_num((int) Integer.parseInt(json.get("Goods_Num").toString()));
////    temp.setGoods_price((float) Float.parseFloat(json.get("Goods_Price").toString()));
////    temp.setGoods_mark(json.get("Goods_Mark").toString().charAt(0));
////    temp.setGoods_show(json.get("Goods_Show").toString().charAt(0));
////    temp.setGoods_picture(json.get("Goods_Picture").toString());
////    temp.setGoods_season((int) Integer.parseInt(json.get("Goods_Season").toString()));
////    temp.setGoods_blossom(json.get("Goods_Blossom").toString());
////    temp.setGoods_fruit(json.get("Goods_Fruit").toString());
////    temp.setGoods_mature(json.get("Goods_Mature").toString());
////    temp.setGoods_expiration(json.get("Goods_Expiration").toString());
////    try {
////      goodsInfoService.updateById(temp);
////      r = "{\"status\":1}";
////    } catch (Exception e) {
////      e.printStackTrace();
////      r = "{\"status\":0}";
////    }
////    return "success";
////  }
////
////  @org.junit.Test
////  @RequestMapping(value = "deleteGoodsInfo",method = RequestMethod.POST)
////    public String deleteGoodsInfo (String jsonStr) throws BaseException{
////      String r="";
////      JSONObject json=JSONObject.parseObject(jsonStr);
////      GoodsInfo temp = goodsInfoService.findById(json.get("Goods_PK").toString());
////      temp.setGoods_name(json.get("Goods_Name").toString());
////      temp.setGoods_type((int)Integer.parseInt(json.get("Goods_Type").toString()));
////      temp.setGoods_num((int)Integer.parseInt(json.get("Goods_Num").toString()));
////      temp.setGoods_price((float)Float.parseFloat(json.get("Goods_Price").toString()));
////      temp.setGoods_mark(json.get("Goods_Mark").toString().charAt(0));
////      temp.setGoods_show(json.get("Goods_Show").toString().charAt(0));
////      temp.setGoods_picture(json.get("Goods_Picture").toString());
////      temp.setGoods_season((int)Integer.parseInt(json.get("Goods_Season").toString()));
////      temp.setGoods_blossom(json.get("Goods_Blossom").toString());
////      temp.setGoods_fruit(json.get("Goods_Fruit").toString());
////      temp.setGoods_mature(json.get("Goods_Mature").toString());
////      temp.setGoods_expiration(json.get("Goods_Expiration").toString());
////      try {
////          goodsInfoService.updateById(temp);
////          r="{\"status\":1}";
////      }catch (Exception e){
////          e.printStackTrace();
////          r="{\"status\":0}";
////      }
////      return "success";
////  }
//
//  @org.junit.Test
//  @RequestMapping(value = "getLikesGoods", method = RequestMethod.POST)
//  public void getLikesAction() throws Exception {
//      String jsonStr = "{\"Goods_Name\":\"名茶\"}";
//    JSONObject data = JSONObject.parseObject(jsonStr);
//      System.out.println(jsonStr);
//    String r = "";
////    HttpServletRequest request =
////            ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
////    HttpSession session = request.getSession();
////    String likes = request.getParameter("likes");
////    "{\"Goods_Season\":0,\"Goods_Price\":0,\"Goods_Type\":0,\"Goods_Show\":\"\\u0000\"," +
////      "\"Goods_Mark\":\"0\",\"Goods_Num\":0}"
//      String likes = "Goods_Name";
////    likes = java.net.URLDecoder.decode(likes, "utf-8");
//    Conditions conditions = new Conditions();
//    JSONArray jsonarr = new JSONArray();
//    if (likes != null) {
//      List list = goodsInfoService.list(conditions.like("goods_name", "%"+data.get("Goods_Name")+"%"));
//      for (Iterator iter = ((java.util.List) list).iterator(); iter.hasNext();) {
//        JSONObject temp = new JSONObject();
//        GoodsInfo goods = (GoodsInfo) iter.next();
//
//        temp.put("Goods_PK", goods.getId());
//        temp.put("Goods_Name", goods.getGoods_name());
//        temp.put("Goods_Type", goods.getGoods_type());
//        temp.put("Goods_Num", goods.getGoods_num());
//        temp.put("Goods_Price", goods.getGoods_price());
//        temp.put("Goods_Mark", goods.getGoods_mark());
//        temp.put("Goods_Show", goods.getGoods_show());
//        temp.put("Goods_Picture", goods.getGoods_picture());
//        jsonarr.add(temp);
//      }
//      r=jsonarr.toString();
//        System.out.println(r);
//    }
////    return "success";
//  }
////{"Goods_Name":"名茶"}
////[{"Goods_PK":"100001","Goods_Picture":"guomiao.jpg","Goods_Num":11999,"Goods_Name":"高山名茶半斤","Goods_Price":240,"Goods_Show":"2","Goods_Mark":"0","Goods_Type":2},{"Goods_PK":"100029","Goods_Picture":"chaye2.jpg","Goods_Num":11998,"Goods_Name":"高山名茶一斤","Goods_Price":480,"Goods_Show":"0","Goods_Mark":"0","Goods_Type":2}]
////  {"Goods_Name":"板"}
////[{"Goods_PK":"100002","Goods_Picture":"banli.jpg","Goods_Num":11000,"Goods_Name":"板栗","Goods_Price":18,"Goods_Show":"2","Goods_Mark":"0","Goods_Type":3}]
//
////}
//}