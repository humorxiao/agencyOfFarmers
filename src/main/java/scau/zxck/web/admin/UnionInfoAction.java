package scau.zxck.web.admin;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.junit.runner.Request;
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
import scau.zxck.entity.market.UnionInfo;
import scau.zxck.service.market.IUnionInfoService;
import scau.zxck.service.market.IUnionInfoService;
import scau.zxck.utils.DataManagerUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;
import java.sql.Date;

/**
 * Created by suruijia on 2016/2/6.
 */
@Controller
@RequestMapping("/")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:config/spring/spring.xml")
public class UnionInfoAction {
  @Autowired
  private IUnionInfoService unionInfoService;
  private DataManagerUtil dataManagerUtil = new DataManagerUtil();

  /**
   * 获取分类
   * 
   * @return
   * @throws BaseException
   */
  @RequestMapping(value = "getAllUnionInfo", method = RequestMethod.POST)
  @ResponseBody
  public String getAllUnionInfo(String jsonStr) throws BaseException {
    JSONObject json = JSON.parseObject(jsonStr);
    JSONObject temp = new JSONObject();
    String r=new String();

    List<UnionInfo> list =
            unionInfoService.listUnionInfo();
   //return a List contain all UnionInfo
  //needs to return String jsonArray contain jsonObject UnionInfo
       JSONArray jsAry=new JSONArray();
      JSONObject json1=new JSONObject();
       for(UnionInfo e:list){
         json1.put("id",e.getId());
         json1.put("Union_name",e.getUnion_name());
          json1.put("Union_Master",e.getUnion_master());
          json1.put("Union_License",e.getUnion_license());
          json1.put("Union_Address",e.getUnion_address());
          Date d=e.getUnion_establish();
         SimpleDateFormat m1=new SimpleDateFormat("yyyy-MM-dd");
         json1.put("Union_Establish",m1.format(d));
         json1.put("Union_Asset",e.getUnion_asset());
         json1.put("Union_Cell",e.getUnion_cell());
         json1.put("Union_Email",e.getUnion_email());
         json1.put("Union_Tel",e.getUnion_tele());
         char c=e.getUnion_mark();
         json1.put("Union_Mark",c);

         jsAry.add(json1);
         System.out.println(json1.get("id"));
         String s1=json1.toString();
         System.out.println(s1);
         json1.clear();
       }
       if(jsAry!=null)
       {
         r=jsAry.toString();
         JSONObject js1=new JSONObject();
         js1=jsAry.getJSONObject(3);
         String s2=js1.toString();
           System.out.println(s2);
       }
        return r;
  }


  @RequestMapping(value="addUnionInfo",method=RequestMethod.POST)
  @ResponseBody
  @Test
  public void addUnionInfo()throws BaseException{
        JSONObject json=JSONObject.parseObject("{\"Union_Mark\":\"0\",\"Union_Establish\":\"2055-10-21\",\"Union_Asset\":1000000,\"Union_Name\":\"连平县新龙绿野康种养专业合作社\",\"Union_Address\":\"连平县元善镇新龙村东门路东23号\",\"Union_Email\":\"13376788278@189.cn\",\"Union_Tele\":\"13380941939\",\"Union_Cell\":\"13380941939\",\"Union_Master\":\"余房淦\",\"Union_License\":\"93441623MA4UJ4209F\"}");
        UnionInfo temp=new UnionInfo();
        temp.setUnion_name(json.get("Union_Name").toString());
        temp.setUnion_master(json.get("Union_Master").toString());
        temp.setUnion_license(json.get("Union_License").toString());
        temp.setUnion_address(json.get("Union_Address").toString());
        System.out.println("hello world");

      SimpleDateFormat bartDateFormat =   new SimpleDateFormat("yyyy-MM-dd");

      String dateStringToParse = "2022-7-12";

      try{

          java.util.Date date = bartDateFormat.parse(dateStringToParse);

          java.sql.Date sqlDate = new java.sql.Date(date.getTime());
          //temp.setUnion_establish(sqlDate.getTime());
          System.out.println(sqlDate.toString());
          temp.setUnion_establish(sqlDate);
      }

      catch (Exception ex) {

          System.out.println(ex.getMessage());

      }

        //temp.setUnion_establish(date);
        temp.setUnion_asset((int)Integer.parseInt(json.get("Union_Asset").toString()));
        temp.setUnion_tele(json.get("Union_Tele").toString());
        temp.setUnion_cell(json.get("Union_Cell").toString());
        temp.setUnion_email(json.get("Union_Email").toString());
        char c = json.get("Union_Mark").toString().charAt(0);
        temp.setUnion_mark(c);

        String ret=unionInfoService.addUnionInfo(temp);//check whether success
        if(ret!=null){
            System.out.println("{\"status\":1}");
         // return "{\"status\":1}";
        }else{
            System.out.println("{\"status\":0}");
         // return "{\"status\":0}";
        }
  }

  @RequestMapping(value="updateUnionInfo",method=RequestMethod.POST)
  @ResponseBody
  public String updateUnionInfo(String jsonStr)throws BaseException{
        JSONObject json=JSONObject.parseObject(jsonStr);
    UnionInfo temp=new UnionInfo();
    temp.setUnion_name(json.get("Union_Name").toString());
    temp.setUnion_master(json.get("Union_Master").toString());
    temp.setUnion_license(json.get("Union_License").toString());
    temp.setUnion_address(json.get("Union_Address").toString());
    temp.setUnion_establish(Date.valueOf(json.get("Union_Establish").toString()));
    temp.setUnion_asset((int)Integer.parseInt(json.get("Union_Asset").toString()));
    temp.setUnion_tele(json.get("Union_Tele").toString());
    temp.setUnion_cell(json.get("Union_Cell").toString());
    temp.setUnion_email(json.get("Union_Email").toString());
    temp.setUnion_mark(json.get("Union_Mark").toString().charAt(0));
    try {
      unionInfoService.updateUnionInfo(temp);//check whether success
      return "{\"status\":1}";//success
    }catch(Exception e){
      return "{\"status\":0}";
    }

  }
  @RequestMapping(value="deleteUnionInfo",method=RequestMethod.POST)
  @ResponseBody
  public String deleteUnionInfo(String jsonStr)throws BaseException{
    JSONObject json=JSONObject.parseObject(jsonStr);
    String id=(String)json.get("id");
    try {
      unionInfoService.deleteUnionInfo(id);
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
