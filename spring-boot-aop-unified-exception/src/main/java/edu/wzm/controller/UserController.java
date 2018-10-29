package edu.wzm.controller;

import edu.wzm.vo.UserVo;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class UserController {

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public Object get(@Valid UserVo user, BindingResult result){
        int res = 1/0; // 测试异常
        return user;
    }
}
