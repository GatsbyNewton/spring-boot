package edu.wzm.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {ArithmeticException.class, CustomException.class})
    @ResponseBody
    public ErrorInfo<String> jsonExceptionHandler(HttpServletRequest request, Exception e) throws Exception{
        ErrorInfo<String> info = new ErrorInfo<>(ErrorCode.FAILED);
        info.setData(e.getMessage());
        info.setUrl(request.getRequestURL().toString());

        return info;
    }

    @ExceptionHandler(value = Exception.class)
    public ModelAndView defaultExceptionHandler(HttpServletRequest request, Exception e) throws Exception {
        ModelAndView mav = new ModelAndView();
        mav.addObject("url", request.getRequestURL());
        mav.addObject("exception", e);
        mav.setViewName("Error");

        return mav;
    }
}