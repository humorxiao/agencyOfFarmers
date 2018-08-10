package scau.zxck.web.admin;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import scau.zxck.base.dao.mybatis.Conditions;
import scau.zxck.entity.market.SignInLog;
import scau.zxck.entity.sys.AdminInfo;
import scau.zxck.entity.sys.UserInfo;
import scau.zxck.service.market.ISignInLogService;
import scau.zxck.service.sys.IAdminLoginService;
import scau.zxck.service.sys.IUserLoginService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by suruijia on 2016/2/6.
 */
@Controller
@RequestMapping("/")
public class HelloAction {
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
  @RequestMapping(value = "hello", method = RequestMethod.POST)

  public String Hello() {
    System.out.println("Hello!");
    return "success";
  }
}
