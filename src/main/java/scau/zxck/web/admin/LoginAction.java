package scau.zxck.web.admin;


import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import scau.zxck.base.dao.mybatis.Conditions;
import scau.zxck.entity.market.*;
import scau.zxck.entity.sys.AdminInfo;
import scau.zxck.entity.sys.UserInfo;
import scau.zxck.service.market.ISignInLogService;
import scau.zxck.service.sys.IAdminLoginService;
import scau.zxck.service.sys.IUserLoginService;
import scau.zxck.utils.FlushWriteUtil;
import scau.zxck.utils.ReadJSONUtil;
import scau.zxck.web.listener.UserSessionListener;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.List;

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
  @Autowired
  private HttpSession session;

  @RequestMapping(value = "login", method = RequestMethod.POST)
  public void login(HttpServletResponse response) throws Exception {
    String r = "";
    JSONObject data = ReadJSONUtil.readJSONStr(request);
//    JSONObject data = JSONObject.parseObject(jsonStr);
    JSONObject temp = new JSONObject();
    if ((boolean) data.get("isAdmin")) {
      Conditions conditions = new Conditions();
      List list =
        adminLoginService.list(conditions.eq("admin_password", data.get("Admin_Password").toString()).and().leftBracket()
          .eq("admin_name", data.get("Admin_Name").toString()).or()
          .eq("admin_cell", data.get("Admin_Cell").toString()).or()
          .eq("admin_email", data.get("Admin_Email").toString()).rightBracket()
        );
      if (list.isEmpty()) {
        temp.put("isCorrect", false);
        temp.put("reLogin", false);
      } else {
        /**
         * 管理员在同一台浏览器重复登录返回值-1，在不同浏览器重复登录返回0,（虽然会登录成功
         * ，但是前一次登录的账号会被刷掉），正常登录返回1
         */
        int yhx=UserSessionListener.isAreadyEnter2(session, (AdminInfo) list.get(0), response);
        if (yhx == -1) {
          temp.put("isCorrect", false);
          temp.put("reLogin", true);
          FlushWriteUtil.flushWrite(response, r);
        } else if (yhx == 0) {
          temp.put("isCorrect", true);
          AdminInfo admin = (AdminInfo) list.get(0);
          temp.put("Admin_PK", admin.getId());
          temp.put("SignIn_IsAdmin", true);
          temp.put("reLogin", true);
        } else {
          temp.put("isCorrect", true);
          AdminInfo admin = (AdminInfo) list.get(0);
          temp.put("Admin_PK", admin.getId());
          temp.put("SignIn_IsAdmin", true);
          temp.put("reLogin", false);
        }
      }
    } else {
      Conditions conditions = new Conditions();
      List list = null;
      list = userLoginService.list(conditions.eq("user_password", data.get("User_Password").toString()).and().leftBracket()
        .eq("user_name", data.get("User_Name").toString()).or()
        .eq("user_cell", data.get("User_Cell").toString()).or()
        .eq("user_email", data.get("User_Email").toString()).rightBracket().and()
        .eq("user_mark", 0)
      );
      if (list.isEmpty()) {
        temp.put("isCorrect", false);
        temp.put("reLogin", false);
      } else {
        /**
         * 普通用户在同一台浏览器重复登录返回值-1（这样的重复登录在第二次的时候不会成功），在不同浏览器重复登录返回0,（虽然会登录成功
         * ，但是前一次登录的账号会被刷掉），正常登录返回1
         */
        int yhx=UserSessionListener.isAreadyEnter(session, (UserInfo) list.get(0), response);
        if (yhx==-1) {
          temp.put("isCorrect", false);
          temp.put("reLogin", true);
          FlushWriteUtil.flushWrite(response, r);
        } else if (yhx == 0) {
          temp.put("isCorrect", true);
          UserInfo user = (UserInfo) list.get(0);
          temp.put("User_PK", user.getId());
          temp.put("SignIn_IsAdmin", false);
          temp.put("reLogin", true);
        } else {
          temp.put("isCorrect", true);
          UserInfo user = (UserInfo) list.get(0);
          temp.put("User_PK", user.getId());
          temp.put("SignIn_IsAdmin", false);
          temp.put("reLogin", false);
        }
      }
    }

    if ((boolean) temp.get("isCorrect")) {
      // 登录日志
      temp.put("SignIn_Time", new Timestamp(System.currentTimeMillis()).toString());
      if ((boolean)temp.get("SignIn_IsAdmin")) {
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
    if ((boolean) temp.get("isCorrect")) {
      session.setAttribute("isAdmin", (boolean) data.get("isAdmin"));
      if ((boolean) data.get("isAdmin")) {
        session.setAttribute("Admin_PK", temp.get("Admin_PK"));
      } else {
        session.setAttribute("User_PK", temp.get("User_PK"));
      }
    }
    r = temp.toString();
    FlushWriteUtil.flushWrite(response, r);
  }

}
