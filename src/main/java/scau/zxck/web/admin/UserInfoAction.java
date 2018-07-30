package scau.zxck.web.admin;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sun.xml.internal.rngom.parse.host.Base;
//import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import scau.zxck.base.dao.BaseDao;
import scau.zxck.base.dao.mybatis.Conditions;
import scau.zxck.base.exception.BaseException;
import scau.zxck.entity.market.CartInfo;
import scau.zxck.entity.market.DeliveryAddress;
import scau.zxck.entity.sys.UserInfo;
import scau.zxck.service.market.IDeliveryAddressService;
import scau.zxck.service.sys.IUserLoginService;
//import sun.security.krb5.EncryptedData;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sound.midi.Soundbank;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;

@Controller
@RequestMapping("/")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:config/spring/spring.xml")
public class UserInfoAction {
  @Autowired
  private IUserLoginService userLoginService;
  @Autowired
  private IDeliveryAddressService deliveryAddressService;
  @Autowired
  private HttpServletRequest request;
  @Autowired
  private HttpSession session;
  @RequestMapping(value = "getUserInfo", method = RequestMethod.POST)

  public void getUserInfo(HttpServletResponse response) throws Exception {
    String r="";
    BufferedReader br = request.getReader();
    String str, wholeStr = "";
    while((str = br.readLine()) != null){
      wholeStr += str;
    }
    String jsonStr=wholeStr;
    JSONObject data = JSONObject.parseObject(jsonStr);
    JSONObject temp = new JSONObject();

    UserInfo userInfo = userLoginService.findById(data.get("User_PK").toString());
    temp.put("User_PK", userInfo.getId());
    temp.put("User_Name", userInfo.getUser_name());
    temp.put("User_Cell", userInfo.getUser_cell());
    temp.put("User_Email", userInfo.getUser_email());
    temp.put("User_Sex", userInfo.getUser_sex());
    temp.put("User_RegTime", userInfo.getUser_regtime());
    temp.put("User_Realname", userInfo.getUser_realname());
    temp.put("User_ID", userInfo.getUser_id());
    r = temp.toString();
    PrintWriter out=response.getWriter();
    out.flush();
    out.write(r);
    out.flush();
  }

  @RequestMapping(value = "updateUserInfo", method = RequestMethod.POST)
  public void updateUserInfo(HttpServletResponse response) throws Exception {
    String r="";
    BufferedReader br = request.getReader();
    String str, wholeStr = "";
    while((str = br.readLine()) != null){
      wholeStr += str;
    }
    String jsonStr=wholeStr;
    JSONObject data = JSONObject.parseObject(jsonStr);
    UserInfo temp = userLoginService.findById(data.get("User_PK").toString());
    data.put("user_password", temp.getUser_password());
    temp.setUser_password(data.get("User_Password").toString());
    temp.setUser_name(data.get("User_Name").toString());
    temp.setUser_cell(data.get("User_Cell").toString());
    temp.setUser_email(data.get("User_Email").toString());
    temp.setUser_sex((int) Integer.parseInt(data.get("User_Sex").toString()));
    temp.setUser_regtime(String.valueOf(data.get("User_RegTime").toString()));
    temp.setUser_realname(data.get("User_Realname").toString());
    temp.setUser_id(data.get("User_ID").toString());
    String c = data.get("User_Mark").toString();
    temp.setUser_mark(c);
    temp.setCart(new CartInfo());
    temp.setDeliveryaddress(new DeliveryAddress());
    Conditions conditions = new Conditions();
    try {
      userLoginService.update(temp, conditions.eq("id", data.get("User_PK").toString()));
      r = "{\"status\":1}";
    } catch (Exception e) {
      r = "{\"status\":0}";
    }
    PrintWriter out=response.getWriter();
    out.flush();
    out.write(r);
    out.flush();
  }

  @RequestMapping(value = "getUserDeliveryAddress", method = RequestMethod.POST)
  public void getUserDeliveryAddress(HttpServletResponse response) throws Exception {
     String r="";
    BufferedReader br = request.getReader();
    String str, wholeStr = "";
    while((str = br.readLine()) != null){
      wholeStr += str;
    }
    String jsonStr=wholeStr;
    JSONObject data = JSONObject.parseObject(jsonStr);
    if (session.getAttribute("User_PK") != null) {
      data.put("Deliv_PK", session.getAttribute("User_PK"));
      data.put("User_PK", session.getAttribute("User_PK"));
    } else {
      data.put("Deliv_PK", "");
      data.put("User_PK", "");
    }
    JSONObject temp = new JSONObject();
    DeliveryAddress address = deliveryAddressService.findById(data.get("User_PK").toString());
    temp.put("Deliv_PK", address.getId());
    temp.put("Deliv_Cell", address.getDeliv_cell());
    temp.put("Deliv_Name", address.getDeliv_name());
    temp.put("Deliv_Address", address.getDeliv_address());
    temp.put("Deliv_Zipcode", address.getDeliv_zipcode());
    r = temp.toString();
    PrintWriter out=response.getWriter();
    out.flush();
    out.write(r);
    out.flush();
  }

  @RequestMapping(value = "updateUserDeliveryAddress", method = RequestMethod.POST)
  public void updateUserDeliveryAddress(HttpServletResponse response) throws Exception {
    String r="";
    BufferedReader br = request.getReader();
    String str, wholeStr = "";
    while((str = br.readLine()) != null){
      wholeStr += str;
    }
    String jsonStr=wholeStr;
    JSONObject data = JSONObject.parseObject(jsonStr);
//    BufferedReader br = request.getReader();
//    String str, wholeStr = "";
//    while((str = br.readLine()) != null){
//      wholeStr += str;
//    }
//    jsonStr=wholeStr;
//    HttpServletRequest request =
//        ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//    HttpSession session = request.getSession();
    if (session.getAttribute("User_PK") != null) {
      data.put("Deliv_PK", (int) session.getAttribute("User_PK"));
      data.put("User_PK", (int) session.getAttribute("User_PK"));
    } else {
      data.put("Deliv_PK", "");
      data.put("User_PK", "");
    }
    DeliveryAddress temp = deliveryAddressService.findById(data.get("Deliv_PK").toString());
    temp.setDeliv_cell(data.get("Deliv_Cell").toString());
    temp.setDeliv_name(data.get("Deliv_Name").toString());
    temp.setDeliv_address(data.get("Deliv_Address").toString());
    temp.setDeliv_zipcode(data.get("Deliv_Zipcode").toString());
    try {
      deliveryAddressService.updateById(temp);
      r = "{\"status\":1}";
    } catch (Exception e) {
      e.printStackTrace();
      r = "{\"status\":0}";
    }
    PrintWriter out=response.getWriter();
    out.flush();
    out.write(r);
    out.flush();
  }

  @RequestMapping(value = "removeBannedUser", method = RequestMethod.POST)
  public void removeBannedUser(String jsonStr,HttpServletResponse response) throws Exception {
    String r = "";
    JSONObject data = JSONObject.parseObject(jsonStr);
    data.put("User_Mark", "");
    UserInfo userInfo = userLoginService.findById(data.get("User_PK").toString());
    data.put("User_Password", userInfo.getUser_password());
    UserInfo temp = userLoginService.findById(data.get("User_PK").toString());
    temp.setUser_password(data.get("User_Password").toString());
    temp.setUser_name(data.get("User_Name").toString());
    temp.setUser_cell(data.get("User_Cell").toString());
    temp.setUser_email(data.get("User_Email").toString());
    temp.setUser_sex((int) Integer.parseInt(data.get("User_Sex").toString()));
    temp.setUser_regtime(String.valueOf(data.get("User_RegTime").toString()).toString());
    temp.setUser_realname(data.get("User_Realname").toString());
    temp.setUser_id(data.get("User_ID").toString());
    String c = data.get("User_Mark").toString();
    temp.setUser_mark(c);
    temp.setCart(new CartInfo());
    temp.setDeliveryaddress(new DeliveryAddress());
    try {
      userLoginService.updateById(temp);
      r = "{\"status\":1}";
    } catch (Exception e) {
      e.printStackTrace();
      r = "{\"status\":0}";
    }
    PrintWriter out=response.getWriter();
    out.flush();
    out.write(r);
    out.flush();
  }

  @RequestMapping(value = "addUserBanned", method = RequestMethod.POST)
  public void addUserBanned(HttpServletResponse response) throws Exception {
    String r="";
    BufferedReader br = request.getReader();
    String str, wholeStr = "";
    while((str = br.readLine()) != null){
      wholeStr += str;
    }
    String jsonStr=wholeStr;
    JSONObject data = JSONObject.parseObject(jsonStr);
    data.put("User_Mark", "1");
    UserInfo userInfo = userLoginService.findById(data.get("User_PK").toString());
    data.put("User_Password", userInfo.getUser_password());
    UserInfo temp = userLoginService.findById(data.get("User_PK").toString());
    temp.setUser_password(data.get("User_Password").toString());
    temp.setUser_name(data.get("User_Name").toString());
    temp.setUser_cell(data.get("User_Cell").toString());
    temp.setUser_email(data.get("User_Email").toString());
    temp.setUser_sex((int) Integer.parseInt(data.get("User_Sex").toString()));
    temp.setUser_regtime(String.valueOf(data.get("User_RegTime").toString()));
    temp.setUser_realname(data.get("User_Realname").toString());
    temp.setUser_id(data.get("User_ID").toString());
    String c = data.get("User_Mark").toString();
    temp.setUser_mark(c);
    temp.setCart(new CartInfo());
    temp.setDeliveryaddress(new DeliveryAddress());
    try {
      userLoginService.updateById(temp);
      r = "{\"status\":1}";
    } catch (Exception e) {
      e.printStackTrace();
      r = "{\"status\":0}";
    }
    PrintWriter out=response.getWriter();
    out.flush();
    out.write(r);
    out.flush();
  }

  @RequestMapping(value = "getBannedUserInfo", method = RequestMethod.POST)
  public void getBannedUserInfo(HttpServletResponse response) throws Exception {
    String r="";
    BufferedReader br = request.getReader();
    String str, wholeStr = "";
    while((str = br.readLine()) != null){
      wholeStr += str;
    }
    String jsonStr=wholeStr;
    JSONObject data = JSONObject.parseObject(jsonStr);
    JSONObject temp = new JSONObject();
    UserInfo user = userLoginService.findById(data.get("User_PK").toString());
    temp.put("User_PK", user.getId());
    temp.put("User_Name", user.getUser_name());
    temp.put("User_Cell", user.getUser_cell());
    temp.put("User_Email", user.getUser_email());
    temp.put("User_Sex", user.getUser_sex());
    temp.put("User_RegTime", user.getUser_regtime().toString());
    temp.put("User_Realname", user.getUser_realname());
    temp.put("User_ID", user.getUser_id());
    r = temp.toString();
    PrintWriter out=response.getWriter();
    out.flush();
    out.write(r);
    out.flush();
  }

  @RequestMapping(value = "getLikesUser", method = RequestMethod.POST)
  public void getLikesUser(HttpServletResponse response) throws BaseException, UnsupportedEncodingException,IOException {
//    BufferedReader br = request.getReader();
//    String str, wholeStr = "";
//    while((str = br.readLine()) != null){
//      wholeStr += str;
//    }
//    jsonStr=wholeStr;
//    HttpServletRequest request =
//        ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//    HttpSession session = request.getSession();
    String likes = request.getParameter("likes");
    likes = java.net.URLDecoder.decode(likes, "utf-8");
      JSONArray jsonarr = new JSONArray();
    if (likes != null) {
      Conditions conditions = new Conditions();
      List list = userLoginService.list(conditions.like("user_name", "%" + likes + "%").or()
          .like("user_realname", "%" + likes + "%").or().like("user_cell", "%" + likes + "%"));
      if (!list.isEmpty()) {
        for (Iterator iter = ((List) list).iterator(); iter.hasNext();) {
          JSONObject temp = new JSONObject();
          UserInfo user = (UserInfo) iter.next();
          temp.put("User_PK", user.getId());
          temp.put("User_Name", user.getUser_name());
          temp.put("User_Cell", user.getUser_cell());
          temp.put("User_Email", user.getUser_email());
          temp.put("User_Sex", user.getUser_sex());
          SimpleDateFormat m1 = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
          temp.put("User_RegTime", user.getUser_regtime());
          temp.put("User_Realname", user.getUser_realname());
          temp.put("User_ID", user.getUser_id());
          temp.put("User_Mark", user.getUser_mark());
          jsonarr.add(temp);
        }
      }
    }
    String r=jsonarr.toString();
    PrintWriter out=response.getWriter();
    out.flush();
    out.write(r);
    out.flush();
  }
}
