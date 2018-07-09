package scau.zxck.base.web.interceptor;

import org.apache.log4j.Logger;
import org.springframework.core.NamedThreadLocal;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by leeshunpeng on 2015/6/22.
 */
public class StopWatchHandlerInterceptor  extends HandlerInterceptorAdapter{
    private static Logger logger = Logger.getLogger(StopWatchHandlerInterceptor.class);
    private NamedThreadLocal<Long> startTimeThreadLocal = new NamedThreadLocal<Long>("StopWatch-StartTime");

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        long startTime = System.currentTimeMillis();
        startTimeThreadLocal.set(startTime);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        long endTime = System.currentTimeMillis();
        long startTime = startTimeThreadLocal.get();
        String uri = request.getRequestURI();
        logger.info("handle uri:" + uri + " for " + (endTime - startTime) + " ms.");
    }
}
