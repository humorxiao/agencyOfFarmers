package scau.zxck.web.admin;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
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
import scau.zxck.base.dao.mybatis.Conditions;
import scau.zxck.base.exception.BaseException;
import scau.zxck.entity.market.UnionInfo;
import scau.zxck.service.market.IUnionInfoService;
import scau.zxck.entity.market.UnionStaff;
import scau.zxck.service.market.IUnionStaffService;

import scau.zxck.entity.market.UnionGoodsInfo;
import scau.zxck.service.market.IUnionGoodsInfoService;

import java.io.BufferedReader;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;
import java.sql.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * Created by suruijia on 2016/2/6.
 */

@Controller
@RequestMapping("/")
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration("classpath:config/spring/spring.xml")
public class UnionInfoAction {
    @Autowired
    private IUnionInfoService unionInfoService;
    @Autowired
    private IUnionStaffService unionStaffService;
    @Autowired
    private IUnionGoodsInfoService unionGoodsInfoService;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private HttpSession session;

    /**
     * 获取分类
     *
     * @return
     * @throws BaseException
     */
    @RequestMapping(value = "getLikesUnions", method = RequestMethod.POST)
    public String getLikesUnions(String jsonStr) throws Exception {
        JSONArray jsonarr = new JSONArray();
//        BufferedReader br = request.getReader();
//        String str, wholeStr = "";
//        while ((str = br.readLine()) != null) {
//            wholeStr += str;
//        }
//        jsonStr = wholeStr;
//      HttpServletRequest request =
//              ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//        HttpSession session = request.getSession();
        String likes = request.getParameter("likes");
        // String likes=new String("谢衍生");
        likes = java.net.URLDecoder.decode(likes, "utf-8");
        if (likes != null) {
            Conditions conditions = new Conditions();
            List list = unionInfoService.list(conditions.like("union_name", "%" + likes + "%").or()
                    .like("union_master", "%" + likes + "%").or().like("union_address", "%" + likes + "%")
                    .or().like("union_cell", "%" + likes + "%"));

            for (Iterator iter = ((java.util.List) list).iterator(); iter.hasNext(); ) {
                JSONObject temp = new JSONObject();
                UnionInfo union = (UnionInfo) iter.next();
                temp.put("Union_PK", union.getId());
                temp.put("Union_Name", union.getUnion_name());
                temp.put("Union_Master", union.getUnion_master());
                temp.put("Union_License", union.getUnion_license());
                temp.put("Union_Address", union.getUnion_address());

                Date d = Date.valueOf(union.getUnion_establish());
                SimpleDateFormat m1 = new SimpleDateFormat("yyyy-MM-dd");
                temp.put("Union_Establish", m1.format(d));//格式化字符串
                temp.put("Union_Asset", union.getUnion_asset());
                temp.put("Union_Tele", union.getUnion_tele());
                temp.put("Union_Cell", union.getUnion_cell());
                temp.put("Union_Email", union.getUnion_email());
                char c = union.getUnion_mark();
                temp.put("Union_Mark", c);

                jsonarr.add(temp);
                //temp.clear();
            }
        }
        String r = jsonarr.toString();
        // System.out.println(r);
        return "success";
    }

    @RequestMapping(value = "getAllUnionInfo", method = RequestMethod.POST)
    public String getAllUnionInfo() throws BaseException {
        String r = new String();

        List<UnionInfo> list =
                unionInfoService.listUnionInfo();
        //return a List contain all UnionInfo
        //needs to return String jsonArray contain jsonObject UnionInf
        JSONArray jsAry = new JSONArray();
        //jsAry= JSONArray.parseArray(list.toString());
        // System.out.println(jsAry.getJSONObject(3).get("Union_Address"));


        for (UnionInfo e : list) {
            JSONObject json1 = new JSONObject();
            json1.put("Union_PK", e.getId());
            json1.put("Union_name", e.getUnion_name());
            json1.put("Union_Master", e.getUnion_master());
            json1.put("Union_License", e.getUnion_license());
            json1.put("Union_Address", e.getUnion_address());

            json1.put("Union_Establish", e.getUnion_establish());
            json1.put("Union_Asset", e.getUnion_asset());
            json1.put("Union_Cell", e.getUnion_cell());
            json1.put("Union_Email", e.getUnion_email());
            json1.put("Union_Tel", e.getUnion_tele());
            char c = e.getUnion_mark();
            json1.put("Union_Mark", c);

            jsAry.add(json1);
        }
        return r;
    }


    @RequestMapping(value = "addUnionInfo", method = RequestMethod.POST)
    public String addUnionInfo(String jsonStr) throws BaseException {
        //  String jsonStr=new String("{\"Union_Mark\":\"0\",\"Union_Establish\":\"2015-10-21\",\"Union_Asset\":1000000,\"Union_Name\":\"连平县新龙绿野康种养专业合作社\",\"Union_Address\":\"连平县元善镇新龙村东门路东23号\",\"Union_Email\":\"13376788278@189.cn\",\"Union_Tele\":\"13380941939\",\"Union_Cell\":\"13380941939\",\"Union_Master\":\"余房淦\",\"Union_License\":\"93441623MA4UJ4209F\"}");
        JSONObject json = JSONObject.parseObject(jsonStr);
        UnionInfo temp = new UnionInfo();
        temp.setUnion_name(json.get("Union_Name").toString());
        temp.setUnion_master(json.get("Union_Master").toString());
        temp.setUnion_license(json.get("Union_License").toString());
        temp.setUnion_address(json.get("Union_Address").toString());

        temp.setUnion_establish(json.get("Union_Establish").toString());
        //temp.setUnion_establish(date);
        temp.setUnion_asset((int) Integer.parseInt(json.get("Union_Asset").toString()));
        temp.setUnion_tele(json.get("Union_Tele").toString());
        temp.setUnion_cell(json.get("Union_Cell").toString());
        temp.setUnion_email(json.get("Union_Email").toString());
        char c = json.get("Union_Mark").toString().charAt(0);
        temp.setUnion_mark(c);

        String ret = unionInfoService.addUnionInfo(temp);//check whether success
        if (ret != null) {
            //  System.out.println("1");
            return "{\"status\":1}";
        } else {
            //  System.out.println("0");
            return "{\"status\":0}";
        }
    }

    @RequestMapping(value = "updateUnionInfo", method = RequestMethod.POST)
  /*
    update needs id key
   */
    public String updateUnionInfo(String jsonStr) throws BaseException {
        JSONObject json = JSONObject.parseObject(jsonStr);
        try {
            UnionInfo temp = unionInfoService.findOne(json.get("id").toString());
            if (json.get("Union_Name") != null) {
                temp.setUnion_name(json.get("Union_Name").toString());
            }
            if (json.get("Union_Master") != null) {
                temp.setUnion_master(json.get("Union_Master").toString());
            }
            if (json.get("Union_License") != null) {
                temp.setUnion_license(json.get("Union_License").toString());
            }
            if (json.get("Union_Address") != null) {
                temp.setUnion_address(json.get("Union_Address").toString());
            }
            if (json.get("Union_Establish") != null) {
                temp.setUnion_establish(json.get("Union_Establish").toString());
            }
            if (json.get("Union_Asset") != null) {
                temp.setUnion_asset((int) Integer.parseInt(json.get("Union_Asset").toString()));
            }
            if (json.get("Union_Tele") != null) {
                temp.setUnion_tele(json.get("Union_Tele").toString());
            }
            if (json.get("Union_Cell") != null) {
                temp.setUnion_cell(json.get("Union_Cell").toString());
            }
            if (json.get("Union_Email") != null) {
                temp.setUnion_email(json.get("Union_Email").toString());
            }
            if (json.get("Union_Mark") != null) {
                temp.setUnion_mark(json.get("Union_Mark").toString().charAt(0));
            }

            unionInfoService.updateUnionInfo(temp);//check whether success
            return "{\"status\":1}";

        } catch (Exception e) {
            return "{\"status\":0}";
        }

    }

    @RequestMapping(value = "deleteUnionInfo", method = RequestMethod.POST)
    @Test
    public String deleteUnionInfo(String jsonStr) throws BaseException {
//        String jsonStr = new String("{\"Union_PK\":\"100000\"}");
        JSONObject json = JSONObject.parseObject(jsonStr);
        String id = (String) json.get("Union_PK");
        System.out.println(id);
        Conditions conditions = new Conditions();

        List<UnionStaff> listStaff = unionStaffService.list(conditions.eq("union_info_id", id));
        if (listStaff != null) {
            for (UnionStaff e : listStaff) {
                unionStaffService.deleteUnionStaff(e.getId());//把跟他关联的外键全部删除掉
            }
        }
        Conditions conditions1 = new Conditions();
        List<UnionGoodsInfo> listUGoods = unionGoodsInfoService.list(conditions1.eq("union_info_id", id));
        if (listUGoods != null) {
            for (UnionGoodsInfo e : listUGoods) {
                unionGoodsInfoService.delete(e.getId());
            }
        }
        unionInfoService.deleteUnionInfo(id);
        System.out.println("1");
        return "{\"status\":1}";//success
    }
}
