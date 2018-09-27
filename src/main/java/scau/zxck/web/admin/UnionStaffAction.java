package scau.zxck.web.admin;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import scau.zxck.base.dao.mybatis.Conditions;
import scau.zxck.entity.market.UnionInfo;
import scau.zxck.entity.market.UnionStaff;
import scau.zxck.service.market.IUnionInfoService;
import scau.zxck.service.market.IUnionStaffService;
import scau.zxck.utils.FlushWriteUtil;
import scau.zxck.utils.ReadJSONUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;

/**
 * @author YHX
 * @DATE 2018/9/20 0020 8:43
 */
@Controller
@RequestMapping("/")
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration("classpath:config/spring/spring.xml")
public class UnionStaffAction {
  @Autowired
  private IUnionStaffService unionStaffService;
  @Autowired
  private IUnionInfoService unionInfoService;
  @Autowired
  private HttpServletRequest request;
  @Autowired
  private HttpSession session;

  @RequestMapping(value = "getLikesStaffs", method = RequestMethod.POST)
  public void getLikesStaffs(HttpServletResponse response) throws Exception {
    JSONObject data = ReadJSONUtil.readJSONStr(request);
    String likes = data.get("likes").toString();
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
    FlushWriteUtil.flushWrite(response, r);
  }

  @RequestMapping(value = "getAllUnionStaff", method = RequestMethod.POST)
  public void getAllUnionStaff(HttpServletResponse response) throws Exception {
    String r = new String();
    List list=unionStaffService.listAll();
    JSONArray jsAry = new JSONArray();
    for (Iterator iter = ((List) list).iterator(); iter.hasNext(); ) {
      UnionStaff staff=(UnionStaff)iter.next();
      JSONObject temp = new JSONObject();
      temp.put("Staff_PK", staff.getId());
      temp.put("Union_PK", staff.getUnion_info_id());
      UnionInfo unionInfo = unionInfoService.findOne(staff.getUnion_info_id());
      temp.put("Union_name", unionInfo.getUnion_name());
      temp.put("Union_Master", unionInfo.getUnion_master());
      temp.put("Union_License", unionInfo.getUnion_license());
      temp.put("Union_Address", unionInfo.getUnion_address());
      temp.put("Union_Establish", unionInfo.getUnion_establish());
      temp.put("Union_Asset", unionInfo.getUnion_asset());
      temp.put("Union_Cell", unionInfo.getUnion_cell());
      temp.put("Union_Email", unionInfo.getUnion_email());
      temp.put("Union_Tel", unionInfo.getUnion_tele());
      char c = unionInfo.getUnion_mark();
      temp.put("Union_Mark", c);
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
    FlushWriteUtil.flushWrite(response, r);
  }


  @RequestMapping(value = "addUnionStaff", method = RequestMethod.POST)
  public void addUnionStaff(HttpServletResponse response) throws Exception {
    UnionStaff temp = new UnionStaff();
    String r = "";
    JSONObject json = ReadJSONUtil.readJSONStr(request);
    temp.setUnion_info_id(json.get("Union_PK").toString());
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
    FlushWriteUtil.flushWrite(response, r);
  }

  @RequestMapping(value = "updateUnionStaff", method = RequestMethod.POST)
  public void updateUnionStaff(HttpServletResponse response) throws Exception {
    String r = "";
    JSONObject json = ReadJSONUtil.readJSONStr(request);
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
    FlushWriteUtil.flushWrite(response, r);
  }

  @RequestMapping(value = "deleteUnionStaff", method = RequestMethod.POST)
  public void deleteUnionStaff(HttpServletResponse response) throws Exception {
    JSONObject json = ReadJSONUtil.readJSONStr(request);
    String r = "";
    String id = (String) json.get("id");
    try {
      unionStaffService.deleteUnionStaff(id);
      r = "{\"status\":1}";//success
    } catch (Exception e) {
      r = "{\"status\":0}";
    }
    FlushWriteUtil.flushWrite(response, r);
  }
}
