package scau.zxck.base.aop.aspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import scau.zxck.base.aop.annotation.ConsumeTime;

/**
 * Created by lishunpeng on 2015/11/13.
 */
@Component
@Aspect
public class ConsumeTimeAspect {
    private static Logger logger = Logger.getLogger(ConsumeTimeAspect.class);

    @Around("@annotation(ct)")
    public Object TimeCalculatorHandler(ProceedingJoinPoint pjp, ConsumeTime ct) throws Throwable {
        Object retVal = null;
        long startTime = System.currentTimeMillis();
        retVal = pjp.proceed();
        long endTime = System.currentTimeMillis();
        logger.debug("ConsumeTimeAspect ======================================");
        logger.debug(pjp.getSignature().getName() + " : " + (endTime - startTime) +  "ms");
        logger.debug("ConsumeTimeAspect ======================================");

        return retVal;
    }
}
