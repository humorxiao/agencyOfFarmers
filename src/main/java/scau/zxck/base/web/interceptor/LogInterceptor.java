package scau.zxck.base.web.interceptor;

import com.alibaba.fastjson.JSON;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by leeshunpeng on 2015/6/17.
 */
/* 自定义拦截器需要实现 HandlerInterceptor接口 或者 扩展 HandlerInterceptorAdapter类 */
public class LogInterceptor implements HandlerInterceptor{/*extends HandlerInterceptorAdapter*/
    private static Logger logger = Logger.getLogger(LogInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object obj) throws Exception {
        if(obj != null){
            logger.info("preHandle:" + JSON.toJSONString(obj));
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object obj, ModelAndView modelAndView) throws Exception {
        if(obj != null && modelAndView != null){
            logger.info("postHandle:" + JSON.toJSONString(obj));
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object obj, Exception e) throws Exception {
        if(obj != null){
            logger.info("afterCompletion:" + JSON.toJSONString(obj));
        }
    }
}
