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
public class UnionStaffAction {
  @Autowired
  private IUnionStaffService unionStaffService;
  @Autowired
  private IUnionInfoService unionInfoService;
  @Autowired
  private HttpServletRequest request;
  @Autowired
  private HttpSession session;
  private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
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
      FlushWriteUtil.flushWrite(response, r);
    } catch (Exception e) {
      e.printStackTrace();
      r = "{\"status\":0}";
      FlushWriteUtil.flushWrite(response, r);
    }
  }

  @RequestMapping(value = "updateUnionStaff", method = RequestMethod.POST)
  public void updateUnionStaff(HttpServletResponse response) throws Exception {
    String r = "";
    JSONObject temp=new JSONObject();
    JSONObject data = ReadJSONUtil.readJSONStr(request);
    Conditions conditions=new Conditions();
    List list=unionStaffService.list(conditions.eq("staff_iD",data.get("Staff_ID").toString()));
    UnionStaff unionStaff=(UnionStaff)list.get(0);
    unionStaff.setStaff_name(data.get("Staff_Name").toString());
    unionStaff.setStaff_sex((int)Integer.parseInt(data.get("Staff_Sex").toString()));
    unionStaff.setStaff_birthday(data.get("Staff_Birthday").toString());
    unionStaff.setStaff_address(data.get("Staff_Address").toString());
    unionStaff.setStaff_phone(data.get("Staff_Phone").toString());
    unionStaff.setStaff_id(data.get("Staff_ID").toString());
    unionStaff.setStaff_email(data.get("Staff_Email").toString());
    unionStaff.setUnion_info_id(data.get("Union_PK").toString());
    try {
      unionStaffService.updateUnionStaff(unionStaff);
      temp.put("status",1);
      FlushWriteUtil.flushWrite(response,temp.toString());
    }catch (Exception e){
      e.printStackTrace();
      FlushWriteUtil.flushWrite(response,temp.toString());
    }
  }

  @RequestMapping(value = "deleteUnionStaff", method = RequestMethod.POST)
  public void deleteUnionStaff(HttpServletResponse response) throws Exception {
    String r = "";
    JSONObject data=ReadJSONUtil.readJSONStr(request);
    try {
      unionStaffService.deleteUnionStaff(data.get("Staff_PK").toString());
      r = "{\"status\":1}";//success
      FlushWriteUtil.flushWrite(response, r);
    } catch (Exception e) {
      e.printStackTrace();
      r = "{\"status\":0}";
      FlushWriteUtil.flushWrite(response, r);
    }
  }
}
