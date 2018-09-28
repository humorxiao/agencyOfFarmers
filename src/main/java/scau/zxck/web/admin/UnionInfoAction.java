package scau.zxck.web.admin;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import scau.zxck.base.dao.mybatis.Conditions;
import scau.zxck.entity.market.UnionInfo;
import scau.zxck.service.market.IUnionInfoService;
import scau.zxck.service.market.IUnionStaffService;

import scau.zxck.service.market.IUnionGoodsInfoService;

import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;
import java.sql.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import scau.zxck.utils.FlushWriteUtil;
import scau.zxck.utils.ReadJSONUtil;

/**
 * @author YHX
 * @DATE 2018/9/20 0020 8:43
 */
@Controller
@RequestMapping("/")
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

  @RequestMapping(value = "getLikesUnions", method = RequestMethod.POST)
  public void getLikesUnions(HttpServletResponse response) throws Exception {
    JSONArray jsonarr = new JSONArray();
    JSONObject data = ReadJSONUtil.readJSONStr(request);
    String likes = data.get("likes").toString();
    likes = java.net.URLDecoder.decode(likes, "utf-8");
    if (likes != null) {
      Conditions conditions = new Conditions();
      List list = unionInfoService.list(conditions.eq("union_mark",0).and().leftBracket().like("union_name", "%" + likes + "%").or()
        .like("union_master", "%" + likes + "%").or().like("union_address", "%" + likes + "%")
        .or().like("union_cell", "%" + likes + "%").rightBracket());

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
      }
    }
    String r = jsonarr.toString();
    FlushWriteUtil.flushWrite(response, r);
  }

  @RequestMapping(value = "getAllUnionInfo", method = RequestMethod.POST)
  public void getAllUnionInfo(HttpServletResponse response) throws Exception {
    String r = new String();
    Conditions conditions = new Conditions();
    List list = unionInfoService.list(conditions.eq("union_mark", 0));
    JSONArray jsAry = new JSONArray();
    for (Iterator iter = ((java.util.List) list).iterator(); iter.hasNext(); ) {
      JSONObject json1 = new JSONObject();
      UnionInfo e = (UnionInfo) iter.next();
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
    r = jsAry.toString();
    FlushWriteUtil.flushWrite(response, r);
  }


  @RequestMapping(value = "addUnionInfo", method = RequestMethod.POST)
  public void addUnionInfo(HttpServletResponse response) throws Exception {
    JSONObject json = ReadJSONUtil.readJSONStr(request);
    UnionInfo temp = new UnionInfo();
    temp.setUnion_name(json.get("Union_Name").toString());
    temp.setUnion_master(json.get("Union_Master").toString());
    temp.setUnion_license(json.get("Union_License").toString());
    temp.setUnion_address(json.get("Union_Address").toString());
    temp.setUnion_establish(json.get("Union_Establish").toString());
    temp.setUnion_asset((int) Integer.parseInt(json.get("Union_Asset").toString()));
    temp.setUnion_tele(json.get("Union_Tele").toString());
    temp.setUnion_cell(json.get("Union_Cell").toString());
    temp.setUnion_email(json.get("Union_Email").toString());
    char c = json.get("Union_Mark").toString().charAt(0);
    temp.setUnion_mark(c);
    JSONObject temp1=new JSONObject();
   try {
     unionInfoService.addUnionInfo(temp);//check whether success
     temp1.put("status",1);
     temp1.put("Union_PK",temp.getId());
     FlushWriteUtil.flushWrite(response, temp1.toString());
   }catch (Exception e){
     e.printStackTrace();
     temp1.put("status",0);
     FlushWriteUtil.flushWrite(response, temp1.toString());
   }
  }

  @RequestMapping(value = "updateUnionInfo", method = RequestMethod.POST)
  public void updateUnionInfo(HttpServletResponse response) throws Exception {
    JSONObject json = ReadJSONUtil.readJSONStr(request);
    String r = "";
    try {
      UnionInfo temp = unionInfoService.findOne(json.get("Union_PK").toString());
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
        temp.setUnion_asset(Integer.parseInt(json.get("Union_Asset").toString()));
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
      r = "{\"status\":1}";
      FlushWriteUtil.flushWrite(response, r);
    } catch (Exception e) {
      e.printStackTrace();
      r = "{\"status\":0}";
      FlushWriteUtil.flushWrite(response, r);
    }
  }

  @RequestMapping(value = "deleteUnionInfo", method = RequestMethod.POST)
  public void deleteUnionInfo(HttpServletResponse response) throws Exception {
    String r;
    JSONObject json = ReadJSONUtil.readJSONStr(request);
    String id = (String) json.get("Union_PK");
    UnionInfo unionInfo = unionInfoService.findOne(id);
    unionInfo.setUnion_mark('1');
    try {
      unionInfoService.updateUnionInfo(unionInfo);
      r = "{\"status\":1}";//success
    } catch (Exception e) {
      e.printStackTrace();
      r = "{\"status\":0}";
    }
    FlushWriteUtil.flushWrite(response, r);
  }
}
