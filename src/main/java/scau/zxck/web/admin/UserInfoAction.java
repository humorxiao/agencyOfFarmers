package scau.zxck.web.admin;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import scau.zxck.base.dao.mybatis.Conditions;
import scau.zxck.entity.market.CartInfo;
import scau.zxck.entity.market.DeliveryAddress;
import scau.zxck.entity.sys.UserInfo;
import scau.zxck.service.market.IDeliveryAddressService;
import scau.zxck.service.sys.IUserLoginService;
import scau.zxck.utils.*;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

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
  @Autowired
  private ServletContext application;
  @RequestMapping(value = "getUserInfo", method = RequestMethod.POST)

  public void getUserInfo(HttpServletResponse response) throws Exception {
    JSONObject data = ReadJSONUtil.readJSONStr(request);
    JSONObject temp = new JSONObject();
    String r = "";
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
    FlushWriteUtil.flushWrite(response, r);
  }

  @RequestMapping(value = "resetPassword", method = RequestMethod.POST)
  public void resetPassword(HttpServletResponse response) throws Exception {
    JSONObject data = ReadJSONUtil.readJSONStr(request);
    JSONObject temp = new JSONObject();
    Conditions conditions = new Conditions();
    String string = data.get("User_Name").toString() + data.get("User_Cell").toString() + data.get("User_Email").toString();
    List list = userLoginService.list(conditions.eq("user_name", string).or().eq("user_cell", string).or().eq("user_email", string));
    UserInfo userInfo = (UserInfo) list.get(0);
    Random random = new Random();
    String newPassword = "";
    for (int i = 0; i < 6; i++) {
      newPassword += random.nextInt(10);
    }
    application.setAttribute("newPassword",newPassword);
    newPassword=TransformToMD5Util.makeMD5(newPassword);
    userInfo.setUser_password(newPassword);
    userInfo.setNewPassword(newPassword);
    userLoginService.updateById(userInfo);
    SendEmailUtil sendEmailUtil = new SendEmailUtil(userInfo.getUser_email(), CodeUtil.generateUniqueCode());
    new Thread(sendEmailUtil).start();
    temp.put("status",1);
    FlushWriteUtil.flushWrite(response, temp.toString());
  }

  @RequestMapping(value = "updateUserInfo", method = RequestMethod.POST)
  public void updateUserInfo(HttpServletResponse response) throws Exception {
    JSONObject data = ReadJSONUtil.readJSONStr(request);
    String r = "";
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
    FlushWriteUtil.flushWrite(response, r);
  }

  @RequestMapping(value = "getUserDeliveryAddress", method = RequestMethod.POST)
  public void getUserDeliveryAddress(HttpServletResponse response) throws Exception {
    String r = "";
    JSONObject data = ReadJSONUtil.readJSONStr(request);
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
    FlushWriteUtil.flushWrite(response, r);
  }

  @RequestMapping(value = "updateUserDeliveryAddress", method = RequestMethod.POST)
  public void updateUserDeliveryAddress(HttpServletResponse response) throws Exception {
    String r = "";
    JSONObject data = ReadJSONUtil.readJSONStr(request);
    if (session.getAttribute("User_PK") != null) {
      data.put("Deliv_PK",  session.getAttribute("User_PK"));
      data.put("User_PK",  session.getAttribute("User_PK"));
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
    FlushWriteUtil.flushWrite(response, r);
  }

  @RequestMapping(value = "removeBannedUser", method = RequestMethod.POST)
  public void removeBannedUser(HttpServletResponse response) throws Exception {
    String r = "";
    JSONObject data = ReadJSONUtil.readJSONStr(request);
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
    FlushWriteUtil.flushWrite(response, r);
  }

  @RequestMapping(value = "addUserBanned", method = RequestMethod.POST)
  public void addUserBanned(HttpServletResponse response) throws Exception {
    String r = "";
    JSONObject data = ReadJSONUtil.readJSONStr(request);
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
    FlushWriteUtil.flushWrite(response, r);
  }

  @RequestMapping(value = "getBannedUserInfo", method = RequestMethod.POST)
  public void getBannedUserInfo(HttpServletResponse response) throws Exception {
    String r = "";
    JSONObject data = ReadJSONUtil.readJSONStr(request);
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
    FlushWriteUtil.flushWrite(response, r);
  }

  @RequestMapping(value = "getLikesUser", method = RequestMethod.POST)
  public void getLikesUser(HttpServletResponse response) throws Exception, UnsupportedEncodingException, IOException {
    JSONObject data = ReadJSONUtil.readJSONStr(request);
    String likes = data.get("likes").toString();
    likes = java.net.URLDecoder.decode(likes, "utf-8");
    JSONArray jsonarr = new JSONArray();
    if (likes != null) {
      Conditions conditions = new Conditions();
      List list = userLoginService.list(conditions.like("user_name", "%" + likes + "%").or()
        .like("user_realname", "%" + likes + "%").or().like("user_cell", "%" + likes + "%"));
      if (!list.isEmpty()) {
        for (Iterator iter = ((List) list).iterator(); iter.hasNext(); ) {
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
    String r = jsonarr.toString();
    FlushWriteUtil.flushWrite(response, r);
  }
}
