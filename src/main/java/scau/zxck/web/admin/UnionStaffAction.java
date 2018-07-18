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
import scau.zxck.base.exception.BaseException;
import scau.zxck.entity.market.UnionStaff;
import scau.zxck.service.market.IUnionStaffService;
import scau.zxck.utils.DataManagerUtil;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by suruijia on 2016/2/6.
 */
@Controller
@RequestMapping("/")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:config/spring/spring.xml")
public class UnionStaffAction {
  @Autowired
  private IUnionStaffService unionStaffService;

  /**
   * 获取分类
   *
   * @return
   * @throws BaseException
   */
  @RequestMapping(value = "getAllUnionStaff", method = RequestMethod.POST)
  @ResponseBody
  public String getAllUnionStaff(String jsonStr) throws BaseException {
    JSONObject json = JSON.parseObject(jsonStr);
    JSONObject temp = new JSONObject();
    String r=new String();

    List<UnionStaff> list =
            unionStaffService.listUnionStaff();
   //return a List contain all UnionInfo
  //needs to return String jsonArray contain jsonObject UnionInfo
       JSONArray jsAry=new JSONArray();
        for(UnionStaff staff:list){
            temp.put("id", staff.getId());
            temp.put("Union_Info_Id", staff.getUnion_info_id());
            temp.put("Staff_Name", staff.getStaff_name());
            temp.put("Staff_Sex", staff.getStaff_sex());
//            Date d = staff.getStaff_birthday();
//            SimpleDateFormat m1 = new SimpleDateFormat("yyyy-MM-dd");
            temp.put("Staff_Birthday",staff.getStaff_birthday());
            temp.put("Staff_Address", staff.getStaff_address());
            temp.put("Staff_Phone", staff.getStaff_phone());
            temp.put("Staff_ID", staff.getStaff_id());
            temp.put("Staff_Email", staff.getStaff_email());

            jsAry.add(temp);
            temp.clear();
        }

         r=jsAry.toString();
         JSONObject js1=new JSONObject();
         js1=jsAry.getJSONObject(3);
         String s2=js1.toString();
           System.out.println(s2);
        return r;

  }


  @RequestMapping(value="addUnionStaff",method=RequestMethod.POST)
  @ResponseBody
  @Test
  public String addUnionStaff(String jsonStr)throws BaseException{
        JSONObject json=JSONObject.parseObject(jsonStr);
        UnionStaff temp=new UnionStaff();
      temp.setId(json.get("id").toString());
      temp.setStaff_name(json.get("Staff_Name").toString());
      temp.setStaff_sex((int)Integer.parseInt(json.get("Staff_Sex").toString()));
      temp.setStaff_birthday(Date.valueOf(json.get("Staff_Birthday").toString()));
      temp.setStaff_address(json.get("Staff_Address").toString());
      temp.setStaff_phone(json.get("Staff_Phone").toString());
      temp.setStaff_id(json.get("Staff_ID").toString());
      temp.setStaff_email(json.get("Staff_Email").toString());

      try{
          unionStaffService.addUnionStaff(temp);
          return "{\"status\":1}";
      }catch (Exception e){
          return "{\"status\":0}";
      }

  }

  @RequestMapping(value="updateUnionStaff",method=RequestMethod.POST)
  @ResponseBody
  public String updateUnionStaff(String jsonStr)throws BaseException{
        JSONObject json=JSONObject.parseObject(jsonStr);
      UnionStaff temp = (UnionStaff)unionStaffService.findOne((String)json.get("id"));
      temp.setUnion_info_id((String)json.get("Union_Info_Id"));
      temp.setStaff_name(json.get("Staff_Name").toString());
      temp.setStaff_sex((int)Integer.parseInt(json.get("Staff_Sex").toString()));
      temp.setStaff_birthday(Date.valueOf(json.get("Staff_Birthday").toString()));
      temp.setStaff_address(json.get("Staff_Address").toString());
      temp.setStaff_phone(json.get("Staff_Phone").toString());
      temp.setStaff_id(json.get("Staff_ID").toString());
      temp.setStaff_email(json.get("Staff_Email").toString());
      try {
          unionStaffService.updateUnionStaff(temp);
          return "{\"status\":1}";
      }catch (Exception e){
          return "{\"status\":0}";
      }

  }
  @RequestMapping(value="deleteUnionStaff",method=RequestMethod.POST)
  @ResponseBody
  public String deleteUnionStaff(String jsonStr)throws BaseException{
    JSONObject json=JSONObject.parseObject(jsonStr);
    String id=(String)json.get("id");
    try {
      unionStaffService.deleteUnionStaff(id);
      return "{\"status\":1}";//success
    }catch (Exception e){
      return "{\"status\":0}";
    }
  }
@Test
    public void test(){
      Date date=Date.valueOf("2015-10-21");
      SimpleDateFormat m1=new SimpleDateFormat("yyyy-MM-dd");
      String r1=m1.format(date);
      System.out.println(r1);
}
}
