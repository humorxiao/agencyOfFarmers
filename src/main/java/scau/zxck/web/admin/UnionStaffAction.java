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
import scau.zxck.base.exception.BaseException;
import scau.zxck.entity.market.UnionInfo;
import scau.zxck.entity.market.UnionNews;
import scau.zxck.entity.market.UnionStaff;
import scau.zxck.service.market.IUnionInfoService;
import scau.zxck.service.market.IUnionNewsService;
import scau.zxck.service.market.IUnionStaffService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Controller
@RequestMapping("/")
public class UnionStaffAction {
  @Autowired
  private IUnionStaffService unionStaffService;
  @Autowired
  private IUnionNewsService unionNewsService;
  @Autowired
  private IUnionInfoService unionInfoService;

  @RequestMapping(value = "getLikesUnions", method = RequestMethod.POST)
  public String getLikesUnions(String jsonStr) throws Exception {
    JSONArray jsonarr = new JSONArray();
    HttpServletRequest request =
        ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    HttpSession session = request.getSession();
    String likes = request.getParameter("likes");
    likes = java.net.URLDecoder.decode(likes, "utf-8");
    if (likes != null) {
      Conditions conditions = new Conditions();
      List list = unionInfoService.list(conditions.like("union_name", "%" + likes + "%").or()
          .like("union_master", "%" + likes + "%").or().like("union_address", "%" + likes + "%")
          .or().like("union_cell", "%" + likes + "%"));

      for (Iterator iter = ((java.util.List) list).iterator(); iter.hasNext();) {
        JSONObject temp = new JSONObject();
        UnionInfo union = (UnionInfo) iter.next();
        temp.put("Union_PK", union.getId());
        temp.put("Union_Name", union.getUnion_name());
        temp.put("Union_Master", union.getUnion_master());
        temp.put("Union_License", union.getUnion_license());
        temp.put("Union_Address", union.getUnion_address());

        Date d = union.getUnion_establish();
        SimpleDateFormat m1 = new SimpleDateFormat("yyyy-MM-dd");
        temp.put("Union_Establish", m1.format(d));
        temp.put("Union_Asset", union.getUnion_asset());
        temp.put("Union_Tele", union.getUnion_tele());
        temp.put("Union_Cell", union.getUnion_cell());
        temp.put("Union_Email", union.getUnion_email());
        char c = union.getUnion_mark();
        temp.put("Union_Mark", c);

        jsonarr.add(temp);
        temp.clear();
      }
    }
    String r=jsonarr.toString();
    return "success";
  }

  @RequestMapping(value = "getLikesStaffs", method = RequestMethod.POST)
  public String getLikesStaffs(String jsonStr) throws Exception {
    HttpServletRequest request =
        ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    HttpSession session = request.getSession();
    String likes = request.getParameter("likes");
    likes = java.net.URLDecoder.decode(likes, "utf-8");
    JSONArray jsonarr = new JSONArray();
    if (likes != null) {
      Conditions conditions = new Conditions();
      List list = unionStaffService.list(conditions.like("staff_name", "%" + likes + "%").or()
          .like("staff_address", "%" + likes + "%").or().like("staff_phone", "%" + likes + "%"));
      for (Iterator iter = ((java.util.List) list).iterator(); iter.hasNext();) {
        JSONObject temp = new JSONObject();
        UnionStaff staff = (UnionStaff) iter.next();

        temp.put("Staff_PK", staff.getId());
        temp.put("Union_PK", staff.getUnion_info_id());
        temp.put("Staff_Name", staff.getStaff_name());
        temp.put("Staff_Sex", staff.getStaff_sex());
        Date d = staff.getStaff_birthday();
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
    return "success";
  }
}
