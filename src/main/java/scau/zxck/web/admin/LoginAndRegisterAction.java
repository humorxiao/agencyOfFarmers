package scau.zxck.web.admin;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import scau.zxck.entity.market.SystemUserLog;
import scau.zxck.entity.sys.SystemUserInfo;
import scau.zxck.service.market.ISystemLogService;
import scau.zxck.service.sys.ISystemUserService;
import scau.zxck.utils.ReadJSON;
import scau.zxck.utils.TransformToMD5;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/")
public class LoginAndRegisterAction {
    @Autowired
    private ISystemUserService systemUserService;
    @Autowired
    private ISystemLogService systemLogService;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private HttpSession session;

    @RequestMapping(value = "loginAndRegister", method = RequestMethod.POST)
    public void loginAndRegister(HttpServletResponse response) throws Exception {
        String r = "{\"status\":\"\",\"msg\":\"\",\"loginId\":\"\"}";//返回的字符串
        JSONObject data = new ReadJSON().readJson(request);
        request.setCharacterEncoding("utf-8");
        String userid = data.get("userid").toString();
        String username = data.get("username").toString();
        String password = data.get("password").toString();
        String type = data.get("type").toString();
        if (type.equals("0")) {//登录
            //验证登录操作
            SystemUserInfo user = systemUserService.findById(userid);
            if (user == null) r = "{\"status\":\"-1\",\"msg\":\"账号错误\"}";
            else if (password.equals(user.getPassword())) {//成功
                session.setAttribute("loginUser", user);
                Date date = new Date();
                SystemUserLog log = new SystemUserLog();
                log.setLogin_time(date.toString());
                log.setSystemUserInfo(user);
                log.setSystem_user_info_id(user.getId());
                systemLogService.add(log);
                session.setAttribute("loginfo", log);
                r = "{\"status\":\"1\",\"msg\":\"登录成功\"}";
            } else {
                r = "{\"status\":\"-1\",\"msg\":\"账号或者密码错误\"}";
            }
        } else {//注册
            //插入数据库操作
            //注册操作
            SystemUserInfo u = systemUserService.findById(userid);
            if (u != null) {
                r = "{\"status\":\"-1\",\"msg\":\"该账号已被注册\"}";
            } else {
                SystemUserInfo user = new SystemUserInfo();
                user.setId(userid);
                user.setName(username);
                user.setPassword(TransformToMD5.makeMD5(password));
                systemUserService.add(user);//成功
                r = "{\"status\":\"1\",\"msg\":\"注册成功\"}";
            }
        }
        PrintWriter out = response.getWriter();
        out.flush();
        out.write(r);
        out.flush();
    }

    @RequestMapping(value = "loginout", method = RequestMethod.POST)
    public void loginout(HttpServletResponse response) throws Exception {
        String r = "{\"status\":\"\",\"msg\":\"\"}";//返回的字符串
        SystemUserLog log = (SystemUserLog) session.getAttribute("loginfo");
        session.removeAttribute("loginUser");
        SystemUserInfo user = (SystemUserInfo) session.getAttribute("loginUser");
        if (user == null) {
            log.setLeave_time(new Date().toString());
            systemLogService.updateById(log);
            session.removeAttribute("loginfo");
            r = "{\"status\":\"1\",\"msg\":\"成功登出\"}";
        } else {
            r = "{\"status\":\"-1\",\"msg\":\"登出失败\"}";
        }
        PrintWriter out = response.getWriter();
        out.flush();
        out.write(r);
        out.flush();
    }
}
