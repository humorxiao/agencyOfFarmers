package scau.zxck.web.admin;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import scau.zxck.base.dao.mybatis.Conditions;
import scau.zxck.base.exception.BaseException;
import scau.zxck.entity.market.*;
import scau.zxck.entity.sys.AdminInfo;
import scau.zxck.entity.sys.UserInfo;
import scau.zxck.service.market.ISignInLogService;
import scau.zxck.service.sys.IAdminLoginService;
import scau.zxck.service.sys.IUserLoginService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by suruijia on 2016/2/6.
 */
@Controller
@RequestMapping("/")
public class LoginAction {
  @Autowired
  private IAdminLoginService adminLoginService;
  @Autowired
  private IUserLoginService userLoginService;
  @Autowired
  private ISignInLogService signInLogService;
  @Autowired
  private HttpServletRequest request;
<<<<<<< HEAD
=======
  @Autowired
  private HttpSession session;
>>>>>>> 785363630ee1a153e9bb320a4195d3d6983177bf
  @RequestMapping(value = "login", method = RequestMethod.POST)
  public String login( String jsonStr) throws Exception {
//    String jsonStr =
//        "{\"isAdmin\":false,\"User_Password\":\"12345678\",\"User_Cell\":\"18814167467\",\"User_Name\":\"林莹莹\",\"User_Email\":\"1624471560@qq.com\"}";
//    HttpSession session=request.getSession();
<<<<<<< HEAD
    BufferedReader br = request.getReader();
    String str, wholeStr = "";
    while((str = br.readLine()) != null){
      wholeStr += str;
    }
    jsonStr=wholeStr;
=======

>>>>>>> 785363630ee1a153e9bb320a4195d3d6983177bf
    String r = "";
    JSONObject data = JSON.parseObject(jsonStr);
    JSONObject temp = new JSONObject();
//    System.out.println(jsonStr);
    if ((boolean) data.get("isAdmin")) {
      Conditions conditions = new Conditions();
      List list =
          adminLoginService.list(conditions.eq("admin_name", data.get("Admin_Name").toString()).or()
              .eq("admin_cell", data.get("Admin_Cell").toString()).or()
              .eq("admin_email", data.get("Admin_Email").toString()).and()
              .eq("admin_password", data.get("Admin_Password").toString()));
      if (list.isEmpty()) {
        temp.put("isCorrect", false);
      } else {
        temp.put("isCorrect", true);
        AdminInfo admin = (AdminInfo) list.get(0);
        temp.put("Admin_PK", admin.getId());
        temp.put("SignIn_IsAdmin", true);
      }

    } else {
      Conditions conditions = new Conditions();
      List list = null;
      list = userLoginService.list(conditions.eq("user_name", data.get("User_Name").toString()).or()
          .eq("user_cell", data.get("User_Cell").toString()).or()
          .eq("user_email", data.get("User_Email").toString()).and()
          .eq("user_password", data.get("User_Password").toString()));
      if (list.isEmpty()) {
        temp.put("isCorrect", false);
      } else {
        temp.put("isCorrect", true);
        UserInfo user = (UserInfo) list.get(0);
        temp.put("User_PK", user.getId());
        temp.put("SignIn_IsAdmin", false);
      }
    }

<<<<<<< HEAD
    if ((boolean) temp.get("isCorrect") == true) {
=======
    if ( temp.get("isCorrect") == "true") {
>>>>>>> 785363630ee1a153e9bb320a4195d3d6983177bf
      // 登录日志
      temp.put("SignIn_Time", new Timestamp(System.currentTimeMillis()).toString());
      if ( temp.get("SignIn_IsAdmin") == "true") {
        SignInLog temp1 = new SignInLog();
        temp1.setSignin_isadmin((boolean) temp.get("SignIn_IsAdmin"));
        temp1.setAdmin_info_id(temp.get("Admin_PK").toString());
        temp1.setSignin_time(temp.get("SignIn_Time").toString());
        signInLogService.add(temp1);
      } else {
        SignInLog temp1 = new SignInLog();
        temp1.setSignin_isadmin((boolean) temp.get("SignIn_IsAdmin"));
        temp1.setUser_info_id(temp.get("User_PK").toString());
        temp1.setSignin_time(temp.get("SignIn_Time").toString());
        signInLogService.add(temp1);
      }
    }
<<<<<<< HEAD
    HttpServletRequest request =
        ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    HttpSession session = request.getSession();
=======
//    HttpSession session = request.getSession();
>>>>>>> 785363630ee1a153e9bb320a4195d3d6983177bf
    if ((boolean) temp.get("isCorrect")) {
      session.setAttribute("isAdmin", (boolean) data.get("isAdmin"));
      if ((boolean) data.get("isAdmin")) {
        session.setAttribute("Admin_PK", temp.get("Admin_PK"));
      } else {
        session.setAttribute("User_PK", temp.get("User_PK"));
      }
    }
    return "success";
  }

}
