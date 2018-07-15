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
import scau.zxck.service.sys.IAdminLoginService;
import scau.zxck.service.sys.IUserLoginService;
import scau.zxck.utils.DataManagerUtil;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Date;
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
  private DataManagerUtil dataManagerUtil = new DataManagerUtil();

  /**
   * 获取分类
   * 
   * @return
   * @throws BaseException
   */
  @RequestMapping(value = "login", method = RequestMethod.POST)
  // 登录(已写入日志)
  public String login(String jsonStr) throws BaseException {
    JSONObject json = JSON.parseObject(jsonStr);
    JSONObject temp = new JSONObject();

    if ((boolean) json.get("isAdmin")) {
      Conditions conditions = new Conditions();
      List list =
          adminLoginService.list(conditions.eq("admin_name", json.get("Admin_Name").toString()).or()
              .eq("admin_cell", json.get("Admin_Cell").toString()).or()
              .eq("admin_email", json.get("Admin_Email").toString()).and()
              .eq("admin_password", json.get("Admin_Password").toString()));
      if (list.isEmpty()) {
        temp.put("isCorrect", false);
      } else {
        temp.put("isCorrect", true);
        AdminInfo admin = (AdminInfo) list.get(0);
        temp.put("Admin_PK", admin.getId());
        // temp.put("User_PK", 1);
        temp.put("SignIn_IsAdmin", true);
      }

    } else {
      Conditions conditions = new Conditions();
      // List list = manager.search4LoginUser(json);
      List list = null;
      list = userLoginService.list(conditions.eq("user_name", json.get("User_Name").toString()).or()
          .eq("user_cell", json.get("User_Cell").toString()).or()
          .eq("user_email", json.get("User_Email").toString()).and()
          .eq("user_password", json.get("User_Password").toString()));
      if (list.isEmpty()) {
        temp.put("isCorrect", false);
        // System.out.println("fail"+"\n");
      } else {
        temp.put("isCorrect", true);
        UserInfo user = (UserInfo) list.get(0);
        temp.put("User_PK", user.getId());
        // temp.put("Admin_PK", 0);
        temp.put("SignIn_IsAdmin", false);
        // System.out.println("success"+"\n");
      }
    }

    if ((boolean) temp.get("isCorrect") == true) {
      // 登录日志
      temp.put("SignIn_Time", new Timestamp(System.currentTimeMillis()).toString());
      insertSignInLog(temp);
    }
    JSONObject ret = temp;
    String r = ret.toString();
    HttpServletRequest request =
            ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    HttpSession session=request.getSession();
    if ((boolean) ret.get("isCorrect")) {
      session.setAttribute("isAdmin",(boolean)json.get("isAdmin"));
      if((boolean)json.get("isAdmin")){
        session.setAttribute("Admin_PK",ret.get("Admin_PK"));
      }
      else{
        session.setAttribute("User_PK",ret.get("User_PK"));
      }
    }
    return null;
  }


  public void insertSignInLog(JSONObject json) throws BaseException {
    if ((boolean) json.get("SignIn_IsAdmin") == true) {
      dataManagerUtil.insertData("SignInLogAdmin", json);
    } else {
      dataManagerUtil.insertData("SignInLogUser", json);
    }
  }

}
