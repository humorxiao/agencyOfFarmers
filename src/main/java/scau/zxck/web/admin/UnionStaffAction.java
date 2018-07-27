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
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import scau.zxck.base.dao.mybatis.Conditions;
import scau.zxck.base.exception.BaseException;
import scau.zxck.entity.market.UnionStaff;
import scau.zxck.service.market.IUnionStaffService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;

/**
 * Created by suruijia on 2016/2/6.
 */

@Controller
@RequestMapping("/")
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration("classpath:config/spring/spring.xml")
public class UnionStaffAction {
    @Autowired
    private IUnionStaffService unionStaffService;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private HttpSession session;

    @RequestMapping(value = "getLikesStaffs", method = RequestMethod.POST)
    public void getLikesStaffs(String jsonStr, HttpServletResponse response) throws Exception {
//        BufferedReader br = request.getReader();
//        String str, wholeStr = "";
//        while((str = br.readLine()) != null){
//            wholeStr += str;
//        }
//        jsonStr=wholeStr;
//        HttpServletRequest request =
//                ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//        HttpSession session = request.getSession();
        String likes = request.getParameter("likes");
        likes = java.net.URLDecoder.decode(likes, "utf-8");
        JSONArray jsonarr = new JSONArray();
        if (likes != null) {
            Conditions conditions = new Conditions();
            List list = unionStaffService.list(conditions.like("staff_name", "%" + likes + "%").or()
                    .like("staff_address", "%" + likes + "%").or().like("staff_phone", "%" + likes + "%"));
            for (Iterator iter = ((List) list).iterator(); iter.hasNext(); ) {
                JSONObject temp = new JSONObject();
                UnionStaff staff = (UnionStaff) iter.next();
                temp.put("Staff_PK", staff.getId());
                temp.put("Union_PK", staff.getUnion_info_id());
                temp.put("Staff_Name", staff.getStaff_name());
                temp.put("Staff_Sex", staff.getStaff_sex());
                Date d = Date.valueOf(staff.getStaff_birthday());
                SimpleDateFormat m1 = new SimpleDateFormat("yyyy-MM-dd");

                temp.put("Staff_Birthday", m1.format(d));
                temp.put("Staff_Address", staff.getStaff_address());
                temp.put("Staff_Phone", staff.getStaff_phone());
                temp.put("Staff_ID", staff.getStaff_id());
                temp.put("Staff_Email", staff.getStaff_email());
                jsonarr.add(temp);
            }
        }
        String r = jsonarr.toString();
        PrintWriter out = response.getWriter();
        out.flush();
        out.write(r);
        out.flush();
    }

    @RequestMapping(value = "getAllUnionStaff", method = RequestMethod.POST)
    public void getAllUnionStaff(HttpServletResponse response) throws Exception {
        String r = new String();
        List<UnionStaff> list =
                unionStaffService.listUnionStaff();
        JSONArray jsAry = new JSONArray();
        for (UnionStaff staff : list) {
            JSONObject temp = new JSONObject();
            temp.put("Staff_PK", staff.getId());
            temp.put("Union_Info_Id", staff.getUnion_info_id());
            temp.put("Staff_Name", staff.getStaff_name());
            temp.put("Staff_Sex", staff.getStaff_sex());
            temp.put("Staff_Birthday", staff.getStaff_birthday());
            temp.put("Staff_Address", staff.getStaff_address());
            temp.put("Staff_Phone", staff.getStaff_phone());
            temp.put("Staff_ID", staff.getStaff_id());
            temp.put("Staff_Email", staff.getStaff_email());
            jsAry.add(temp);
        }
        r = jsAry.toString();
//        if (jsAry != null) {
//            r = "{\"status\":1}";
//        } else
//            r = "{\"status\":0}";
        PrintWriter out = response.getWriter();
        out.flush();
        out.write(r);
        out.flush();
    }


    @RequestMapping(value = "addUnionStaff", method = RequestMethod.POST)
    public void addUnionStaff(String jsonStr, HttpServletResponse response) throws Exception {
        UnionStaff temp = new UnionStaff();
        String r = "";
        JSONObject json = JSONObject.parseObject(jsonStr);
        temp.setStaff_name(json.get("Staff_Name").toString());
        temp.setStaff_sex((int) Integer.parseInt(json.get("Staff_Sex").toString()));
        temp.setStaff_birthday(json.get("Staff_Birthday").toString());
        temp.setStaff_address(json.get("Staff_Address").toString());
        temp.setStaff_phone(json.get("Staff_Phone").toString());
        temp.setStaff_id(json.get("Staff_ID").toString());
        temp.setStaff_email(json.get("Staff_Email").toString());
        try {
            unionStaffService.addUnionStaff(temp);
            r = "{\"status\":1}";
        } catch (Exception e) {
            r = "{\"status\":0}";
        }
        PrintWriter out = response.getWriter();
        out.flush();
        out.write(r);
        out.flush();
    }

    @RequestMapping(value = "updateUnionStaff", method = RequestMethod.POST)
    public void updateUnionStaff(String jsonStr, HttpServletResponse response) throws Exception {
        String r = "";
        JSONObject json = JSONObject.parseObject(jsonStr);
        try {
            UnionStaff temp = (UnionStaff) unionStaffService.findOne((String) json.get("id"));
            if (json.get("Union_Info_Id") != null) {
                temp.setUnion_info_id((String) json.get("Union_Info_Id"));
            }
            if (json.get("Staff_Name") != null) {
                temp.setStaff_name((String) json.get("Union_Info_Id"));
            }
            if (json.get("Staff_Sex") != null) {
                temp.setStaff_sex((int) Integer.parseInt(json.get("Staff_Sex").toString()));
            }
            if (json.get("Staff_Birthday") != null) {
                temp.setStaff_birthday((String) json.get("Staff_Birthday"));
            }
            if (json.get("Staff_Address") != null) {
                temp.setStaff_address((String) json.get("Staff_Address"));
            }
            if (json.get("Staff_Phone") != null) {
                temp.setStaff_phone((String) json.get("Staff_Phone"));
            }
            if (json.get("Staff_ID") != null) {
                temp.setStaff_id((String) json.get("Staff_ID"));
            }
            if (json.get("Staff_Email") != null) {
                temp.setStaff_email((String) json.get("Staff_Email"));
            }
            unionStaffService.updateUnionStaff(temp);
            r = "{\"status\":1}";
        } catch (Exception e) {
            r = "{\"status\":0}";
        }
        PrintWriter out = response.getWriter();
        out.flush();
        out.write(r);
        out.flush();
    }

    @RequestMapping(value = "deleteUnionStaff", method = RequestMethod.POST)
    public void deleteUnionStaff(String jsonStr, HttpServletResponse response) throws Exception {
        JSONObject json = JSONObject.parseObject(jsonStr);
        String r = "";
        String id = (String) json.get("id");
        try {
            unionStaffService.deleteUnionStaff(id);
            r = "{\"status\":1}";//success
        } catch (Exception e) {
            r = "{\"status\":0}";
        }
        PrintWriter out = response.getWriter();
        out.flush();
        out.write(r);
        out.flush();
    }
}
