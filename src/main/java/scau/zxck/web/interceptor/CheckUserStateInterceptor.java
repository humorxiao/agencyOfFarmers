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
        if (id == null) {
          //当检测到用户未登录时就直接转到登录页面
//            httpServletResponse.sendRedirect("index.jsp");
          httpServletRequest.getRequestDispatcher("WEB-INF/pages/register.html").forward(httpServletRequest, httpServletResponse);
          return false;
        } else {
          UserInfo userInfo=userLoginService.findById(id);
            if (userInfo== null) {
//                httpServletResponse.sendRedirect("WEB-INF/pages/success.jsp");
              httpServletRequest.getRequestDispatcher("WEB-INF/pages/register.html").forward(httpServletRequest, httpServletResponse);
                return false;
            } else {
              logger.info(userInfo.getUser_name());
              return true;
            }
        }
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
