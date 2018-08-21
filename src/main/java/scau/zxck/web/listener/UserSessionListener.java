package scau.zxck.web.listener;

import javafx.scene.control.Alert;

import javax.servlet.ServletContext;
import javax.servlet.http.*;
import javax.swing.*;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class UserSessionListener implements HttpSessionListener {
  private static Map<String, HttpSession> usersessionMap = new HashMap<>();
  private static Map<String, String> sessionuserMap = new HashMap<>();

  public static boolean isAreadyEnter(HttpSession session, String userMseeage, HttpServletResponse response) throws Exception {
    response.setContentType("text ml; charset=GB2312");
    PrintWriter out = response.getWriter();
    boolean flag = false;
    if (usersessionMap.containsKey(userMseeage)) {
      flag = true;
    }
    if (flag) {
      sessionuserMap.remove(usersessionMap.remove(userMseeage));
      usersessionMap.put(userMseeage, session);
      sessionuserMap.put(session.getId(), userMseeage);
//            out.print("<script>alert('登录过了还想登录？你是不是癫的？？？');</script>");
//            out.close();
      return true;
    } else {
      usersessionMap.put(userMseeage, session);
      sessionuserMap.put(session.getId(), userMseeage);
      return false;
    }
  }

  public static boolean isOnline(HttpSession session) {
    return sessionuserMap.containsKey(session.getId());
  }
  public static void out(HttpSession session){
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
