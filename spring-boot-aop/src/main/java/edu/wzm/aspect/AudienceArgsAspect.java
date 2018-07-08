package edu.wzm.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
public class AudienceArgsAspect {

    private Map<String, Long> trackCounts = new HashMap<>();

    /**
     * 传参的切点。
     * 需要关注的是切点表达式中的 args(name) 限定符。它表明传递给 play() 方法的 String 类型参数也会传递到
     * 通知中去。参数的名称 name 也与切点方法签名中的参数相匹配。
     * @param name
     */
    @Pointcut("execution(* edu.wzm.service.PerformanceService.play(String))" +
            "&& args(name)")
    public void trackPlayed(String name){}

    @Before("trackPlayed(name)")
    public void countTrack(String name){
        long currentCount = getPlayCount(name);
        trackCounts.put(name, currentCount + 1);
    }

    public long getPlayCount(String name){
        return trackCounts.containsKey(name) ? trackCounts.get(name) : 0;
    }
}
