package edu.wzm.aspect;

import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AudienceJuniorAspect {

    @Before("execution(* edu.wzm.service.PerformanceService.perform(..))")
    public void silenceCellPhones(){
        System.out.println(("[AudienceJuniorAspect] Silencing cell phone"));
    }

    @Before("execution(* edu.wzm.service.PerformanceService.perform(..))")
    public void takeSeats(){
        System.out.println("[AudienceJuniorAspect] Taking seats");
    }

    @AfterReturning("execution(* edu.wzm.service.PerformanceService.perform(..))")
    public void applause(){
        System.out.println("[AudienceJuniorAspect] CLAP CLAP CLAP!!!");
    }

    @AfterThrowing("execution(* edu.wzm.service.PerformanceService.perform(..))")
    public void demandRefund(){
        System.out.println("[AudienceJuniorAspect] Demanding refund");
    }
}
