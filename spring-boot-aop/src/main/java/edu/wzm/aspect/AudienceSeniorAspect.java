package edu.wzm.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Aspect
@Component
public class AudienceSeniorAspect {

    @Pointcut("execution(* edu.wzm.service.PerformanceService.perform(..))")
    public void performance(){}

    /**
     * 环绕通知是最为强大的通知类型。它能够让你所编写的逻辑将被通知的目标方法完全包装起来，
     * 实际上就像在一个通知方法中同时编写前置通知和后置通知。
     * @param joinPoint ProceedingJoinPoint 作为参数，这个对象是必须要有的，因为你要在通知中通过它来调用被
     *                  通知的方法。通知方法中可以做任何的事情，当要将控制权交给被通知的方法时，它需要调用
     *                  ProceedingJoinPoint 的 proceed() 方法。
     * @return 返回被执行方法 edu.wzm.service.PerformanceService.perform(..) 的返回值
     */
    @Around("performance()")
    public Object watchPerformance(ProceedingJoinPoint joinPoint){
        try {
            System.out.println("[AudienceSeniorAspect] Silencing cell phone");
            System.out.println("[AudienceSeniorAspect] Taking seats");
            Object result = joinPoint.proceed();
            System.out.println("[AudienceSeniorAspect] " + Objects.toString(result));
            System.out.println("[AudienceSeniorAspect] CLAP CLAP CLAP!!!");

            return result;
        }catch (Throwable e){
            System.out.println("[AudienceSeniorAspect] Demanding a refund");

            return "Failed";
        }
    }
}
