package edu.wzm.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description 访问http://localhost:8080/swagger-ui.html
 * @Date 2025/8/17
 * @Version 1.0
 * @Author jimmy
 **/
@Api(tags = "登录请求")
@RestController
@RequestMapping("/swagger")
public class SwaggerController {

    @GetMapping("/login")
    @ApiOperation(value = "登录检测", notes = "根据用户名，判断用户是否存在")
    public String login() {
        return "hello, Swagger";
    }
}
