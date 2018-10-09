package scau.zxck.web.listener;

import javafx.scene.control.Alert;
import scau.zxck.entity.sys.AdminInfo;
import scau.zxck.entity.sys.UserInfo;

import javax.servlet.ServletContext;
import javax.servlet.http.*;
import javax.swing.*;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class UserSessionListener implements HttpSessionListener {
  private static Map<String, HttpSession> usersessionMap = new HashMap<>();
  private static Map<String, String> sessionuserMap = new HashMap<>();

  public static int isAreadyEnter(HttpSession session, UserInfo userInfo, HttpServletResponse response) throws Exception {
    response.setContentType("text ml; charset=GB2312");
    PrintWriter out = response.getWriter();
    String userMseeage = userInfo.getUser_email() + userInfo.getUser_name() + userInfo.getUser_cell();
    boolean flag = false, flag2;
    if (usersessionMap.containsKey(userMseeage)) {
      flag = true;
    }
    if (flag) {
      HttpSession session1 = usersessionMap.remove(userMseeage);
      if (sessionuserMap.containsKey(session1.getId())) {
        sessionuserMap.remove(session1.getId());
        usersessionMap.put(userMseeage, session);
        sessionuserMap.put(session.getId(), userMseeage);
        flag2 = true;
      } else {
        session1.invalidate();
        usersessionMap.put(userMseeage, session);
        sessionuserMap.put(session.getId(), userMseeage);
        flag2 = false;
      }
      if (flag2) return -1;
      else return 0;
    } else {
      usersessionMap.put(userMseeage, session);
      sessionuserMap.put(session.getId(), userMseeage);
      return 1;
    }
  }

  public static int isAreadyEnter2(HttpSession session, AdminInfo adminInfo, HttpServletResponse response) throws Exception {
    response.setContentType("text ml; charset=GB2312");
    PrintWriter out = response.getWriter();
    String userMseeage = adminInfo.getAdmin_cell() + adminInfo.getAdmin_email() + adminInfo.getAdmin_name();
    boolean flag = false, flag2;
    if (usersessionMap.containsKey(userMseeage)) {
      flag = true;
    }
    if (flag) {
      HttpSession session1 = usersessionMap.remove(userMseeage);
      if (sessionuserMap.containsValue(session1.getId())) {
        sessionuserMap.remove(session1.getId());
        usersessionMap.put(userMseeage, session);
        sessionuserMap.put(session.getId(), userMseeage);
        flag2 = true;
      } else {
        session1.invalidate();
        usersessionMap.put(userMseeage, session);
        sessionuserMap.put(session.getId(), userMseeage);
        flag2 = false;
      }
//            out.print("<script>alert('登录过了还想登录？你是不是癫的？？？');</script>");
//            out.close();
      if (flag2) return -1;
      else return 0;
    } else {
      usersessionMap.put(userMseeage, session);
      sessionuserMap.put(session.getId(), userMseeage);
      return 1;
    }
  }
//  public static boolean isOnline(HttpSession session,String string) {
//    return sessionuserMap.containsKey(session.getId());
//  }

  public static void out(HttpSession session) {
    usersessionMap.remove(sessionuserMap.remove(session.getId()));
  }

  @Override
  public void sessionCreated(HttpSessionEvent httpSessionEvent) {
    System.out.println("session创建成功！！！！");
  }

  @Override
  public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
    System.out.println("session销毁成功！！！");
    usersessionMap.remove(sessionuserMap.remove(httpSessionEvent.getSession().getId()));
  }
}
