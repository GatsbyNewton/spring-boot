package edu.wzm.aop;

import edu.wzm.exception.CustomException;
import edu.wzm.exception.ErrorCode;
import edu.wzm.tool.ResponseUtils;
import edu.wzm.vo.Request;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class ExceptionAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionAspect.class);

    @Around("execution(* edu.wzm.controller.*.*(..))")
    private Object aroundController(ProceedingJoinPoint joinPoint){
        ServletRequestAttributes attributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String uri = request.getRequestURL().toString();
        String ip = request.getRemoteAddr();
        String method = request.getMethod();

        Object[] args = joinPoint.getArgs();
        String requestMethod = joinPoint.getSignature().getName();
        Request requestVo;
        if (args.length > 0 && args[0] instanceof Request){
            requestVo = (Request) args[0];
            requestVo.setMethod(requestMethod);
            BindingResult bindingResult = (BindingResult)args[1];
            if (bindingResult.hasErrors()){
                ObjectError error = bindingResult.getAllErrors().get(0);
                LOGGER.error("url={}, ip={}, method={}", uri, ip, method, error.getDefaultMessage());
                return ResponseUtils.build(ErrorCode.FAILED, error.getDefaultMessage(), requestVo);
            }
        }else {
            requestVo = new Request() {};
            requestVo.setMethod(requestMethod);
        }

        try {
            Object result = joinPoint.proceed();
            return ResponseUtils.build(ErrorCode.SUCCESS, requestVo, result);
        }catch (Throwable throwable){
            if (throwable instanceof CustomException){
                CustomException ce = (CustomException)throwable;
                LOGGER.error("url={}, ip={}, method={}", uri, ip, method, ce);
                return ResponseUtils.build(ce.getCode(), ce.getMessage(), requestVo);
            }

            LOGGER.error("url={}, ip={}, method={}", uri, ip, method, throwable);
            return ResponseUtils.build(ErrorCode.FAILED, requestVo);
        }
    }
}
