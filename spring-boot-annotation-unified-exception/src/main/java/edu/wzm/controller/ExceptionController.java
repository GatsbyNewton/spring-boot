package edu.wzm.controller;

import edu.wzm.exception.CustomException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExceptionController {

    @RequestMapping(value = "/exception")
    public Object test(@RequestParam("number") int number)throws Exception{
        if (number == 0) {
            int res = 1 / 0;
        }else {
            throw new CustomException("test");
        }
        return "CustomException";
    }
}
