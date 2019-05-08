package com.sxkj.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * com.sxkj.aop.LogAop
 *
 * @author zwd
 * @Description LogAop
 * @Date Create in 2018-06-20 0020 9:55
 * @Modified
 */
@Aspect
@Component
public class LogAop {
    private static final Logger LOGGER = LoggerFactory.getLogger(LogAop.class);
//    @Autowired
//    private LogQueue logQueue;

    @Pointcut("execution(public * com.sxkj.gaia.*.*Controller.*(..))")
    public void webLog(){}
    /** 前置 */
    @Before("webLog()")
    public void deBefore(JoinPoint joinPoint) throws Throwable {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        LOGGER.info("url={}",request.getRequestURL());
        LOGGER.info("methode={}",request.getMethod());
        LOGGER.info("id={}",request.getRemoteAddr());
        LOGGER.info("class_method={}",joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        LOGGER.info("args={}",joinPoint.getArgs());
        //logQueue.add(request);
    }

    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void doAfterReturning(Object ret) throws Throwable {
        LOGGER.info("response={}",ret.toString());
    }

    /** 后置异常通知 */
    @AfterThrowing(pointcut = "webLog()",throwing = "e")
    public void doThrows(Throwable e){
        System.out.println("方法异常时执行....."+e);
    }

    /** 后置最终通知,final增强，不管是抛出异常或者正常退出都会执行 */
    //@After("webLog()")
    public void after(JoinPoint jp){
        System.out.println("方法最后执行.....");
    }

    /** 环绕通知,环绕增强，相当于MethodInterceptor */
    @Around("webLog()")
    public Object arround(ProceedingJoinPoint pjp) {
        System.out.println("方法环绕start.....");
        try {
            Object o =  pjp.proceed();
            System.out.println("方法环绕proceed，结果是 :" + o);
            return o;
        } catch (Throwable e) {
            return null;
        }
    }
}
