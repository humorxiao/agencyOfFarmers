package scau.zxck.web.interceptor;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import scau.zxck.entity.sys.UserInfo;
import scau.zxck.service.sys.IUserLoginService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CheckUserStateInterceptor implements HandlerInterceptor {
    private static Logger logger = Logger.getLogger(LogInterceptor.class);
    @Autowired
    private IUserLoginService userLoginService;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        HttpSession session = httpServletRequest.getSession();
        String id = (String) session.getAttribute("User_PK");
        String r;
        if (id == null) {
            System.out.println("404啊啊啊");
            httpServletResponse.sendRedirect("index.jsp");//这个路劲还在研究中
            return false;
        } else {
            if (userLoginService.findById(id) == null) {
                System.out.println("404怎么办");
                httpServletResponse.sendRedirect("index.jsp");//这个路劲还在研究中
                return false;
            } else return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
