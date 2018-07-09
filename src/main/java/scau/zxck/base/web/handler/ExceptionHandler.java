package scau.zxck.base.web.handler;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import scau.zxck.base.exception.BaseException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by leeshunpeng on 2015/6/17.
 */
public class ExceptionHandler implements HandlerExceptionResolver{
    private static Logger logger = Logger.getLogger(ExceptionHandler.class);

    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object obj, Exception ex) {
        //设置错误信息
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("ex", ex);
        model.put("obj", obj);

        // 根据不同错误转向不同页面
        if(ex instanceof BaseException) {
            return new ModelAndView("error/error-business", model);
        }else {
            return new ModelAndView("error/error", model);
        }
    }
}
