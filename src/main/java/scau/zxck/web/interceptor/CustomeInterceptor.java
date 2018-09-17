package scau.zxck.web.interceptor;

import com.alibaba.fastjson.JSON;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import scau.zxck.entity.sys.User;
import scau.zxck.service.sys.IUserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by leeshunpeng on 2015/6/17.
 */
/* 自定义拦截器需要实现 HandlerInterceptor接口 或者 扩展 HandlerInterceptorAdapter类 */
public class CustomeInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o)
            throws Exception {
        System.out.println("CustomInterceptor....preHandle");
        //对浏览器的请求进行放行处理
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView)
            throws Exception {
        System.out.println("CustomInterceptor....postHandle");
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e)
            throws Exception {
        System.out.println("CustomInterceptor....afterCompletion");
    }
}