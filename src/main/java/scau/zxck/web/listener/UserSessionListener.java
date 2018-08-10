package scau.zxck.web.listener;

import javafx.scene.control.Alert;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import javax.swing.*;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class UserSessionListener implements HttpSessionListener {
    private static Map<String,HttpSession> usersessionMap=new HashMap<>();
    private static Map<String,String> sessionuserMap=new HashMap<>();

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {

    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
//        HttpSession session=httpSessionEvent.getSession();
//        ServletContext application=session.getServletContext();
//        Map<String,String> userSessionMap=(Map)application.getAttribute("userSessionMap");
//        if(userSessionMap!=null){
//            String sessionID=userSessionMap.get((String)session.getAttribute("loginID"));
//            if(sessionID!=null){
//                userSessionMap.remove(session.getAttribute("loginID"));
//            }
//        }
        usersessionMap.remove(sessionuserMap.remove(httpSessionEvent.getSession().getId()));
    }
    public static boolean isAreadyEnter(HttpSession session, String userMseeage, HttpServletResponse response) throws Exception{
        response.setContentType("text ml; charset=GB2312");
        PrintWriter out=response.getWriter();
        boolean flag=false;
        int i;
        String[] strings=userMseeage.split("#");
        for(i=0;i<strings.length;i++){
            if(usersessionMap.containsKey(strings[i])){
                flag=true;
                break;
            }
        }
        if(flag){
            sessionuserMap.remove(usersessionMap.remove(strings[i]));
            usersessionMap.put(userMseeage,session);
            sessionuserMap.put(session.getId(),userMseeage);
//            out.print("<script>alert('登录过了还想登录？你是不是癫的？？？');</script>");
//            out.close();
            return true;
        }
        else{
            usersessionMap.put(userMseeage,session);
            sessionuserMap.put(session.getId(),userMseeage);
            return false;
        }
    }
    public static boolean isOnline(HttpSession session){
        return sessionuserMap.containsKey(session.getId());
    }
}
